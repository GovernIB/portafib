--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2015-12-01 09:50:00

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: portafib; Type: SCHEMA; Schema: -; Owner: portafib
--

CREATE SCHEMA portafib;


ALTER SCHEMA portafib OWNER TO portafib;

SET search_path = portafib, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: pfi_algorismedefirma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_algorismedefirma (
    algorismedefirmaid bigint NOT NULL,
    nom character varying(100) NOT NULL,
    descripcio character varying(255),
    suportat boolean
);


ALTER TABLE portafib.pfi_algorismedefirma OWNER TO portafib;

--
-- Name: pfi_portafib_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE pfi_portafib_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_portafib_seq OWNER TO portafib;

--
-- Name: pfi_annex; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_annex (
    annexid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    fitxerid bigint NOT NULL,
    peticiodefirmaid bigint NOT NULL,
    adjuntar boolean NOT NULL,
    firmar boolean NOT NULL
);


ALTER TABLE portafib.pfi_annex OWNER TO portafib;

--
-- Name: pfi_annexfirmat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_annexfirmat (
    annexfirmatid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    fitxerid bigint NOT NULL,
    annexid bigint NOT NULL,
    firmaid bigint NOT NULL
);


ALTER TABLE portafib.pfi_annexfirmat OWNER TO portafib;

--
-- Name: pfi_bitacola; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_bitacola (
    bitacolaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    peticiodefirmaid bigint NOT NULL,
    data timestamp without time zone NOT NULL,
    descripcio character varying(255) NOT NULL,
    usuarientitatid character varying(101) NOT NULL
);


ALTER TABLE portafib.pfi_bitacola OWNER TO portafib;

--
-- Name: pfi_blocdefirmes; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_blocdefirmes (
    blocdefirmesid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    datafinalitzacio timestamp without time zone,
    fluxdefirmesid bigint NOT NULL,
    minimdefirmes integer NOT NULL,
    ordre integer NOT NULL
);


ALTER TABLE portafib.pfi_blocdefirmes OWNER TO portafib;

--
-- Name: pfi_codibarres; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_codibarres (
    codibarresid character varying(255) NOT NULL,
    nom character varying(50) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE portafib.pfi_codibarres OWNER TO portafib;

--
-- Name: pfi_colaboraciodelegacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_colaboraciodelegacio (
    colaboraciodelegacioid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    datafi timestamp without time zone,
    datainici timestamp without time zone NOT NULL,
    destinatariid character varying(101) NOT NULL,
    motiu character varying(60) NOT NULL,
    colaboradordelegatid character varying(101) NOT NULL,
    motiudeshabilitada character varying(255) DEFAULT NULL::character varying,
    fitxerautoritzacioid bigint,
    activa boolean DEFAULT true NOT NULL,
    revisor boolean DEFAULT false NOT NULL,
    esdelegat boolean NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE portafib.pfi_colaboraciodelegacio OWNER TO portafib;

--
-- Dependencies: 178
-- Name: COLUMN pfi_colaboraciodelegacio.revisor; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_colaboraciodelegacio.revisor IS 'Només es per col.laborador i indica si es obligatori que aquell col.laborador digui la seva.';


--
-- Name: pfi_custodiainfo; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_custodiainfo (
    custodiainfoid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    custodiapluginid character varying(255),
    custodiar boolean NOT NULL,
    urlfitxercustodiat character varying(500),
    missatge character varying(3000) NOT NULL,
    missatgeposiciopaginaid bigint NOT NULL,
    codibarresid character varying(255) NOT NULL,
    codibarresposiciopaginaid bigint NOT NULL,
    usuarientitatid character varying(101),
    usuariaplicacioid character varying(101),
    nomplantilla character varying(255),
    entitatid character varying(50),
    pagines character varying(255) NOT NULL,
    codibarrestext character varying(255) NOT NULL,
    titolpeticio character varying(255),
    datacustodia timestamp without time zone,
    custodiapluginparametres character varying(3000),
    editable boolean NOT NULL,
    pluginid bigint NOT NULL
);


ALTER TABLE portafib.pfi_custodiainfo OWNER TO portafib;

--
-- Dependencies: 179
-- Name: COLUMN pfi_custodiainfo.custodiapluginid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_custodiainfo.custodiapluginid IS 'Identificador de document retornat per Custòdia';


--
-- Name: pfi_entitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_entitat (
    entitatid character varying(50) NOT NULL,
    filtrecertificats text NOT NULL,
    nom character varying(50) NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying,
    adrezahtml character varying(2000) NOT NULL,
    activa boolean DEFAULT true NOT NULL,
    suporttelefon character varying(50),
    suportweb character varying(250),
    suportemail character varying(100),
    usuariaplicacioid character varying(101),
    pdfautoritzaciodelegacioid bigint NOT NULL,
    faviconid bigint NOT NULL,
    logowebid bigint NOT NULL,
    logowebpeuid bigint NOT NULL,
    logosegellid bigint NOT NULL,
    maxuploadsize bigint,
    maxsizefitxeradaptat bigint,
    maxfilestosignatsametime integer,
    web character varying(250) NOT NULL,
    policyidentifier character varying(100),
    policyidentifierhash text,
    policyidentifierhashalgorithm character varying(50),
    policyurldocument character varying(255),
    motiudelegacioid bigint,
    firmatperformatid bigint,
    algorismedefirmaid bigint DEFAULT 0 NOT NULL,
    comprovarniffirma boolean DEFAULT true NOT NULL,
    custodiainfoid bigint,
    pluginid bigint,
    segelldetempsviaweb integer DEFAULT 0 NOT NULL
);


ALTER TABLE portafib.pfi_entitat OWNER TO portafib;

--
-- Dependencies: 180
-- Name: COLUMN pfi_entitat.pluginid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_entitat.pluginid IS 'Plugin de segellat de temps';


--
-- Name: pfi_estatdefirma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_estatdefirma (
    estatdefirmaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    datafi timestamp without time zone,
    datainici timestamp without time zone NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying,
    tipusestatdefirmainicialid bigint NOT NULL,
    firmaid bigint NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    colaboraciodelegacioid bigint,
    tipusestatdefirmafinalid bigint
);


ALTER TABLE portafib.pfi_estatdefirma OWNER TO portafib;

--
-- Name: pfi_firma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_firma (
    firmaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    destinatariid character varying(101),
    blocdefirmaid bigint NOT NULL,
    fitxerfirmatid bigint,
    numfirmadocument integer,
    obligatori boolean NOT NULL,
    caixa_pagina integer NOT NULL,
    caixa_x integer,
    caixa_y integer,
    caixa_ample integer,
    caixa_alt integer,
    numeroseriecertificat numeric,
    emissorcertificat character varying(1000),
    nomcertificat character varying(1000),
    tipusestatdefirmafinalid bigint,
    mostrarrubrica boolean NOT NULL
);


ALTER TABLE portafib.pfi_firma OWNER TO portafib;

--
-- Dependencies: 182
-- Name: COLUMN pfi_firma.destinatariid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_firma.destinatariid IS 'Si val null significa que s''ha de substituir pel Sol·licitant de la petició (només podrà valer null en plantilles de flux de firmes)';


--
-- Dependencies: 182
-- Name: COLUMN pfi_firma.mostrarrubrica; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_firma.mostrarrubrica IS 'Dibuixar la firma(rubrica) de l''usuari persona si s''ha definit la caixa';


--
-- Name: pfi_fitxer; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_fitxer (
    fitxerid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE portafib.pfi_fitxer OWNER TO portafib;

--
-- Name: pfi_fluxdefirmes; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_fluxdefirmes (
    fluxdefirmesid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL
);


ALTER TABLE portafib.pfi_fluxdefirmes OWNER TO portafib;

--
-- Name: pfi_grupentitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_grupentitat (
    grupentitatid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    nom character varying(100) NOT NULL,
    descripcio character varying(255),
    entitatid character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_grupentitat OWNER TO portafib;

--
-- Name: pfi_grupentitatusuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_grupentitatusuarientitat (
    grupentitatusuarientitatid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    grupentitatid bigint NOT NULL
);


ALTER TABLE portafib.pfi_grupentitatusuarientitat OWNER TO portafib;

--
-- Name: pfi_idioma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE portafib.pfi_idioma OWNER TO portafib;

--
-- Name: pfi_metadada; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_metadada (
    metadadaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL,
    peticiodefirmaid bigint NOT NULL,
    valor text NOT NULL,
    tipusmetadadaid integer NOT NULL
);


ALTER TABLE portafib.pfi_metadada OWNER TO portafib;

--
-- Name: pfi_modulfirmapertipusdoc; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_modulfirmapertipusdoc (
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    tipusdocumentid bigint NOT NULL,
    pluginid bigint NOT NULL,
    nom character varying(100) NOT NULL
);


ALTER TABLE portafib.pfi_modulfirmapertipusdoc OWNER TO portafib;

--
-- Name: pfi_notificacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_notificacio (
    notificacioid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    peticiodefirmaid bigint NOT NULL,
    tipusnotificacioid bigint NOT NULL,
    descripcio text,
    datacreacio timestamp without time zone NOT NULL,
    dataenviament timestamp without time zone,
    error text,
    bloquejada boolean NOT NULL,
    reintents integer DEFAULT 0 NOT NULL,
    dataerror timestamp with time zone
);


ALTER TABLE portafib.pfi_notificacio OWNER TO portafib;

--
-- Name: pfi_permisgrupplantilla; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_permisgrupplantilla (
    permisgrupplantillaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    grupentitatid bigint NOT NULL,
    fluxdefirmesid bigint NOT NULL
);


ALTER TABLE portafib.pfi_permisgrupplantilla OWNER TO portafib;

--
-- Name: pfi_permisusuariplantilla; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_permisusuariplantilla (
    permisusuariplantillaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    fluxdefirmesid bigint NOT NULL
);


ALTER TABLE portafib.pfi_permisusuariplantilla OWNER TO portafib;

--
-- Name: pfi_peticiodefirma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_peticiodefirma (
    peticiodefirmaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    datacaducitat timestamp without time zone NOT NULL,
    datafinal timestamp without time zone,
    datasolicitud timestamp without time zone,
    tipusdocumentid bigint NOT NULL,
    fitxerafirmarid bigint NOT NULL,
    fitxeradaptatid bigint,
    titol character varying(255) NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying,
    tipusfirmaid bigint NOT NULL,
    motiuderebuig character varying(255) DEFAULT NULL::character varying,
    usuariaplicacioid character varying(101) NOT NULL,
    tipusestatpeticiodefirmaid bigint NOT NULL,
    fluxdefirmesid bigint NOT NULL,
    posiciotaulafirmesid bigint NOT NULL,
    descripciotipusdocument character varying(255) DEFAULT NULL::character varying,
    idiomaid character varying(5) NOT NULL,
    prioritatid integer DEFAULT 5 NOT NULL,
    motiu character varying(255) NOT NULL,
    remitentnom character varying(100) NOT NULL,
    remitentdescripcio character varying(500),
    informacioaddicional character varying(500),
    logosegellid bigint,
    custodiainfoid bigint,
    algorismedefirmaid bigint NOT NULL,
    modedefirma boolean NOT NULL,
    usuarientitatid character varying(101),
    avisweb boolean DEFAULT false NOT NULL,
    segellatdetemps boolean DEFAULT false NOT NULL
);


ALTER TABLE portafib.pfi_peticiodefirma OWNER TO portafib;

--
-- Dependencies: 192
-- Name: COLUMN pfi_peticiodefirma.posiciotaulafirmesid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_peticiodefirma.posiciotaulafirmesid IS 'Posicio taula de firmes: primera o darrera pàgina';


--
-- Name: pfi_plantillafluxdefirmes; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_plantillafluxdefirmes (
    fluxdefirmesid bigint NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying NOT NULL,
    usuarientitatid character varying(101),
    compartir boolean DEFAULT false,
    usuariaplicacioid character varying(101)
);


ALTER TABLE portafib.pfi_plantillafluxdefirmes OWNER TO portafib;

--
-- Name: pfi_plugin; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_plugin (
    pluginid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    descripciocurtaid bigint NOT NULL,
    classe character varying(255) NOT NULL,
    propertiesadmin text,
    propertiesentitat text,
    entitatid character varying(50),
    actiu boolean NOT NULL,
    tipus integer NOT NULL
);


ALTER TABLE portafib.pfi_plugin OWNER TO portafib;

--
-- Dependencies: 215
-- Name: COLUMN pfi_plugin.entitatid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_plugin.entitatid IS 'Si val null indica que és de l''Administrador. En cas conytrari ja és una instanciació d''una Entitat';


--
-- Name: pfi_posiciopagina; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_posiciopagina (
    posiciopaginaid bigint NOT NULL,
    nom character varying(255) NOT NULL
);


ALTER TABLE portafib.pfi_posiciopagina OWNER TO portafib;

--
-- Name: pfi_posiciotaulafirmes; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_posiciotaulafirmes (
    posiciotaulafirmesid bigint NOT NULL,
    nom character varying(50) NOT NULL,
    descripcio character varying(255),
    suportada boolean DEFAULT true NOT NULL
);


ALTER TABLE portafib.pfi_posiciotaulafirmes OWNER TO portafib;

--
-- Name: pfi_prioritat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_prioritat (
    prioritatid integer NOT NULL,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_prioritat OWNER TO portafib;

--
-- Name: pfi_rebreavis; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_rebreavis (
    tipusnotificacioid bigint NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    rebreagrupat boolean DEFAULT false NOT NULL
);


ALTER TABLE portafib.pfi_rebreavis OWNER TO portafib;

--
-- Name: pfi_role; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_role (
    roleid character varying(50) NOT NULL,
    nom character varying(50) DEFAULT NULL::character varying NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying
);


ALTER TABLE portafib.pfi_role OWNER TO portafib;

--
-- Name: pfi_roleusuariaplicacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_roleusuariaplicacio (
    usuariaplicacioid character varying(50) NOT NULL,
    roleid character varying(50) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_roleusuariaplicacio OWNER TO portafib;

--
-- Name: pfi_roleusuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_roleusuarientitat (
    roleid character varying(50) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_roleusuarientitat OWNER TO portafib;

--
-- Name: pfi_tipusdocument; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusdocument (
    tipusdocumentid bigint NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    usuariaplicacioid character varying(50),
    nom bigint NOT NULL
);


ALTER TABLE portafib.pfi_tipusdocument OWNER TO portafib;

--
-- Name: pfi_tipusdocumentcoladele; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusdocumentcoladele (
    colaboraciodelegacioid bigint NOT NULL,
    tipusdocumentid bigint NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_tipusdocumentcoladele OWNER TO portafib;

--
-- Name: pfi_tipusestatdefirmafinal; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusestatdefirmafinal (
    tipusestatdefirmafinalid bigint NOT NULL,
    nom character varying(50) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE portafib.pfi_tipusestatdefirmafinal OWNER TO portafib;

--
-- Name: pfi_tipusestatdefirmainicial; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusestatdefirmainicial (
    tipusestatdefirmainicialid bigint NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_tipusestatdefirmainicial OWNER TO portafib;

--
-- Name: pfi_tipusestatpeticiodefirma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusestatpeticiodefirma (
    tipusestatpeticiodefirmaid bigint NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_tipusestatpeticiodefirma OWNER TO portafib;

--
-- Name: pfi_tipusfirma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusfirma (
    tipusfirmaid bigint NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL,
    suportada boolean DEFAULT true NOT NULL
);


ALTER TABLE portafib.pfi_tipusfirma OWNER TO portafib;

--
-- Name: pfi_tipusmetadada; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusmetadada (
    tipusmetadadaid integer NOT NULL,
    nom character varying(100) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE portafib.pfi_tipusmetadada OWNER TO portafib;

--
-- Name: pfi_tipusnotificacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusnotificacio (
    tipusnotificacioid bigint NOT NULL,
    descripcio character varying(250) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL,
    esavis boolean
);


ALTER TABLE portafib.pfi_tipusnotificacio OWNER TO portafib;

--
-- Dependencies: 208
-- Name: COLUMN pfi_tipusnotificacio.esavis; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_tipusnotificacio.esavis IS 'Si es avis val true
Si és notificació es false
Si pot ser avis i notificació llavors val null
';


--
-- Name: pfi_traduccio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_traduccio (
    traduccioid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_traduccio OWNER TO portafib;

--
-- Name: pfi_traducciomap; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(5) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE portafib.pfi_traducciomap OWNER TO portafib;

--
-- Name: pfi_usuariaplicacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_usuariaplicacio (
    contrasenya character varying(50),
    entitatid character varying(50) NOT NULL,
    emailadmin character varying(100) NOT NULL,
    callbackurl character varying(400) NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying,
    callbackversio integer DEFAULT 2 NOT NULL,
    actiu boolean DEFAULT true NOT NULL,
    idiomaid character varying(5) NOT NULL,
    usuariaplicacioid character varying(101) NOT NULL,
    logosegellid bigint,
    potcustodiar boolean DEFAULT false
);


ALTER TABLE portafib.pfi_usuariaplicacio OWNER TO portafib;

--
-- Dependencies: 211
-- Name: TABLE pfi_usuariaplicacio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON TABLE pfi_usuariaplicacio IS 'Usuari de tipus màquina que realitzarà peticions a PortaFIB';


--
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacio.emailadmin; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuariaplicacio.emailadmin IS 'Correu de la persona encarregada d''aquest usuari-Màquina';


--
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacio.callbackurl; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuariaplicacio.callbackurl IS 'Adreça on esta implementat el servei de recepció de notificacions associades a les peticions de firma realitzades per aquest usuari-màquina';


--
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacio.callbackversio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuariaplicacio.callbackversio IS 'La versió 1 és la compatible amb INDRA i la versió 2 és l''especifica del nou Portafirmes';


--
-- Name: pfi_usuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_usuarientitat (
    usuarientitatid character varying(101) NOT NULL,
    usuaripersonaid character varying(50) NOT NULL,
    entitatid character varying(50) NOT NULL,
    email character varying(100),
    actiu boolean DEFAULT true NOT NULL,
    predeterminat boolean DEFAULT false NOT NULL,
    carrec character varying(150) DEFAULT NULL::character varying,
    rebretotselsavisos boolean DEFAULT false NOT NULL,
    logosegellid bigint,
    potcustodiar boolean DEFAULT false
);


ALTER TABLE portafib.pfi_usuarientitat OWNER TO portafib;

--
-- Name: pfi_usuarientitatfavorit; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_usuarientitatfavorit (
    origenid character varying(101) NOT NULL,
    favoritid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_usuarientitatfavorit OWNER TO portafib;

--
-- Name: pfi_usuaripersona; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_usuaripersona (
    usuaripersonaid character varying(50) NOT NULL,
    nom character varying(50) NOT NULL,
    llinatges character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    nif character varying(9) NOT NULL,
    idiomaid character varying(5) NOT NULL,
    rubricaid bigint
);


ALTER TABLE portafib.pfi_usuaripersona OWNER TO portafib;

--
-- Dependencies: 214
-- Name: COLUMN pfi_usuaripersona.rubricaid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuaripersona.rubricaid IS 'és la firma gràfica de la persona';


--
-- Name: pfi_algofirma_nom_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_algorismedefirma
    ADD CONSTRAINT pfi_algofirma_nom_uk UNIQUE (nom);


--
-- Name: pfi_algorismedefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_algorismedefirma
    ADD CONSTRAINT pfi_algorismedefirma_pk PRIMARY KEY (algorismedefirmaid);


--
-- Name: pfi_annex_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_annex
    ADD CONSTRAINT pfi_annex_pk PRIMARY KEY (annexid);


--
-- Name: pfi_annexfirmat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT pfi_annexfirmat_pk PRIMARY KEY (annexfirmatid);


--
-- Name: pfi_bitacola_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_bitacola
    ADD CONSTRAINT pfi_bitacola_pk PRIMARY KEY (bitacolaid);


--
-- Name: pfi_blocdefirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_blocdefirmes
    ADD CONSTRAINT pfi_blocdefirmes_pk PRIMARY KEY (blocdefirmesid);


--
-- Name: pfi_codibarres_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_codibarres
    ADD CONSTRAINT pfi_codibarres_pk PRIMARY KEY (codibarresid);


--
-- Name: pfi_colaboraciodelegacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colaboraciodelegacio_pk PRIMARY KEY (colaboraciodelegacioid);


--
-- Name: pfi_custodiainfo_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodiainfo_pk PRIMARY KEY (custodiainfoid);


--
-- Name: pfi_entitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_pk PRIMARY KEY (entitatid);


--
-- Name: pfi_estatdefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatdefirma_pk PRIMARY KEY (estatdefirmaid);


--
-- Name: pfi_estfirmini_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusestatdefirmainicial
    ADD CONSTRAINT pfi_estfirmini_pk PRIMARY KEY (tipusestatdefirmainicialid);


--
-- Name: pfi_estpetfirm_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusestatpeticiodefirma
    ADD CONSTRAINT pfi_estpetfirm_pk PRIMARY KEY (tipusestatpeticiodefirmaid);


--
-- Name: pfi_favorit_origfavo_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_favorit_origfavo_uk UNIQUE (origenid, favoritid);


--
-- Name: pfi_firma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_pk PRIMARY KEY (firmaid);


--
-- Name: pfi_fitxer_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_fitxer
    ADD CONSTRAINT pfi_fitxer_pk PRIMARY KEY (fitxerid);


--
-- Name: pfi_fluxdefirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_fluxdefirmes
    ADD CONSTRAINT pfi_fluxdefirmes_pk PRIMARY KEY (fluxdefirmesid);


--
-- Name: pfi_grupentita_nomentitat_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitat
    ADD CONSTRAINT pfi_grupentita_nomentitat_uk UNIQUE (nom, entitatid);


--
-- Name: pfi_grupentitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitat
    ADD CONSTRAINT pfi_grupentitat_pk PRIMARY KEY (grupentitatid);


--
-- Name: pfi_grupusrent_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_pk PRIMARY KEY (grupentitatusuarientitatid);


--
-- Name: pfi_grupusrent_usrgrup_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_usrgrup_uk UNIQUE (usuarientitatid, grupentitatid);


--
-- Name: pfi_idioma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_idioma
    ADD CONSTRAINT pfi_idioma_pk PRIMARY KEY (idiomaid);


--
-- Name: pfi_metadada_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_metadada
    ADD CONSTRAINT pfi_metadada_pk PRIMARY KEY (metadadaid);


--
-- Name: pfi_modulfirmapertipusdoc_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_modulfirmapertipusdoc_pk PRIMARY KEY (id);


--
-- Name: pfi_mofitido_modfirm_tipdoc_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_modfirm_tipdoc_uk UNIQUE (tipusdocumentid, pluginid);


--
-- Name: pfi_notificacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_notificacio
    ADD CONSTRAINT pfi_notificacio_pk PRIMARY KEY (notificacioid);


--
-- Name: pfi_permisgrpl_grupflux_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrpl_grupflux_uk UNIQUE (grupentitatid, fluxdefirmesid);


--
-- Name: pfi_permisgrupplantilla_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrupplantilla_pk PRIMARY KEY (permisgrupplantillaid);


--
-- Name: pfi_permisuspl_usrflux_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisuspl_usrflux_uk UNIQUE (usuarientitatid, fluxdefirmesid);


--
-- Name: pfi_permisusuariplantilla_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisusuariplantilla_pk PRIMARY KEY (permisusuariplantillaid);


--
-- Name: pfi_persona_nif_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_persona_nif_uk UNIQUE (nif);


--
-- Name: pfi_peticiodefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_peticiodefirma_pk PRIMARY KEY (peticiodefirmaid);


--
-- Name: pfi_petifirma_fluxfirmesid_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fluxfirmesid_uk UNIQUE (fluxdefirmesid);


--
-- Name: pfi_plantillafluxdefirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantillafluxdefirmes_pk PRIMARY KEY (fluxdefirmesid);


--
-- Name: pfi_plugin_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_plugin
    ADD CONSTRAINT pfi_plugin_pk PRIMARY KEY (pluginid);


--
-- Name: pfi_posiciopagina_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_posiciopagina
    ADD CONSTRAINT pfi_posiciopagina_pk PRIMARY KEY (posiciopaginaid);


--
-- Name: pfi_posiciotaulafirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_posiciotaulafirmes
    ADD CONSTRAINT pfi_posiciotaulafirmes_pk PRIMARY KEY (posiciotaulafirmesid);


--
-- Name: pfi_prioritat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_prioritat
    ADD CONSTRAINT pfi_prioritat_pk PRIMARY KEY (prioritatid);


--
-- Name: pfi_rebreavis_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_pk PRIMARY KEY (id);


--
-- Name: pfi_rebreavis_tnotiusr_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_tnotiusr_uk UNIQUE (tipusnotificacioid, usuarientitatid);


--
-- Name: pfi_role_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_role
    ADD CONSTRAINT pfi_role_pk PRIMARY KEY (roleid);


--
-- Name: pfi_roleusrapp_approle_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusrapp_approle_uk UNIQUE (usuariaplicacioid, roleid);


--
-- Name: pfi_roleusrent_roleusrent_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusrent_roleusrent_uk UNIQUE (roleid, usuarientitatid);


--
-- Name: pfi_roleusuariaplicacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusuariaplicacio_pk PRIMARY KEY (id);


--
-- Name: pfi_roleusuarientitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusuarientitat_pk PRIMARY KEY (id);


--
-- Name: pfi_tipmetada_nom_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusmetadada
    ADD CONSTRAINT pfi_tipmetada_nom_uk UNIQUE (nom);


--
-- Name: pfi_tipusdoccd_codetdoc_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdoccd_codetdoc_uk UNIQUE (colaboraciodelegacioid, tipusdocumentid);


--
-- Name: pfi_tipusdocument_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdocument_pk PRIMARY KEY (tipusdocumentid);


--
-- Name: pfi_tipusdocumentcoladele_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdocumentcoladele_pk PRIMARY KEY (id);


--
-- Name: pfi_tipusestatdefirmafinal_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusestatdefirmafinal
    ADD CONSTRAINT pfi_tipusestatdefirmafinal_pk PRIMARY KEY (tipusestatdefirmafinalid);


--
-- Name: pfi_tipusfirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusfirma
    ADD CONSTRAINT pfi_tipusfirma_pk PRIMARY KEY (tipusfirmaid);


--
-- Name: pfi_tipusmetadada_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusmetadada
    ADD CONSTRAINT pfi_tipusmetadada_pk PRIMARY KEY (tipusmetadadaid);


--
-- Name: pfi_tipusnotificacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusnotificacio
    ADD CONSTRAINT pfi_tipusnotificacio_pk PRIMARY KEY (tipusnotificacioid);


--
-- Name: pfi_traduccio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_traduccio
    ADD CONSTRAINT pfi_traduccio_pk PRIMARY KEY (traduccioid);


--
-- Name: pfi_traducciomap_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_traducciomap
    ADD CONSTRAINT pfi_traducciomap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- Name: pfi_usrentitat_perentcar_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_perentcar_uk UNIQUE (usuaripersonaid, entitatid, carrec);


--
-- Name: pfi_usuariaplicacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usuariaplicacio_pk PRIMARY KEY (usuariaplicacioid);


--
-- Name: pfi_usuarientitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usuarientitat_pk PRIMARY KEY (usuarientitatid);


--
-- Name: pfi_usuarientitatfavorit_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_usuarientitatfavorit_pk PRIMARY KEY (id);


--
-- Name: pfi_usuaripersona_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_usuaripersona_pk PRIMARY KEY (usuaripersonaid);


--
-- Name: pfi_algorismedefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_algorismedefirma_pk_i ON pfi_algorismedefirma USING btree (algorismedefirmaid);


--
-- Name: pfi_annex_fitxerid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annex_fitxerid_fk_i ON pfi_annex USING btree (fitxerid);


--
-- Name: pfi_annex_petdefirmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annex_petdefirmaid_fk_i ON pfi_annex USING btree (peticiodefirmaid);


--
-- Name: pfi_annex_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annex_pk_i ON pfi_annex USING btree (annexid);


--
-- Name: pfi_annexfirmat_annexid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_annexid_fk_i ON pfi_annexfirmat USING btree (annexid);


--
-- Name: pfi_annexfirmat_firmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_firmaid_fk_i ON pfi_annexfirmat USING btree (firmaid);


--
-- Name: pfi_annexfirmat_fitxerid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_fitxerid_fk_i ON pfi_annexfirmat USING btree (fitxerid);


--
-- Name: pfi_annexfirmat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_pk_i ON pfi_annexfirmat USING btree (annexfirmatid);


--
-- Name: pfi_bitacola_peticid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_peticid_fk_i ON pfi_bitacola USING btree (peticiodefirmaid);


--
-- Name: pfi_bitacola_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_pk_i ON pfi_bitacola USING btree (bitacolaid);


--
-- Name: pfi_bitacola_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_usrentid_fk_i ON pfi_bitacola USING btree (usuarientitatid);


--
-- Name: pfi_blocdefirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_blocdefirmes_pk_i ON pfi_blocdefirmes USING btree (blocdefirmesid);


--
-- Name: pfi_blocfirmes_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_blocfirmes_fluxid_fk_i ON pfi_blocdefirmes USING btree (fluxdefirmesid);


--
-- Name: pfi_codibarres_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_codibarres_pk_i ON pfi_codibarres USING btree (codibarresid);


--
-- Name: pfi_colabdeleg_coldelid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colabdeleg_coldelid_fk_i ON pfi_colaboraciodelegacio USING btree (colaboradordelegatid);


--
-- Name: pfi_colabdeleg_destid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colabdeleg_destid_fk_i ON pfi_colaboraciodelegacio USING btree (destinatariid);


--
-- Name: pfi_colabdeleg_fitautoid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colabdeleg_fitautoid_fk_i ON pfi_colaboraciodelegacio USING btree (fitxerautoritzacioid);


--
-- Name: pfi_colaboraciodelegacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colaboraciodelegacio_pk_i ON pfi_colaboraciodelegacio USING btree (colaboraciodelegacioid);


--
-- Name: pfi_custodia_codbarrpos_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_codbarrpos_fk_i ON pfi_custodiainfo USING btree (codibarresposiciopaginaid);


--
-- Name: pfi_custodia_codibarid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_codibarid_fk_i ON pfi_custodiainfo USING btree (codibarresid);


--
-- Name: pfi_custodia_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_entitatid_fk_i ON pfi_custodiainfo USING btree (entitatid);


--
-- Name: pfi_custodia_msgpospagid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_msgpospagid_fk_i ON pfi_custodiainfo USING btree (missatgeposiciopaginaid);


--
-- Name: pfi_custodia_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_usrappid_fk_i ON pfi_custodiainfo USING btree (usuariaplicacioid);


--
-- Name: pfi_custodia_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_usrentid_fk_i ON pfi_custodiainfo USING btree (usuarientitatid);


--
-- Name: pfi_custodiainfo_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodiainfo_pk_i ON pfi_custodiainfo USING btree (custodiainfoid);


--
-- Name: pfi_custodiainfo_pluginid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodiainfo_pluginid_fk_i ON pfi_custodiainfo USING btree (pluginid);


--
-- Name: pfi_entitat_algofirma_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_algofirma_fk_i ON pfi_entitat USING btree (algorismedefirmaid);


--
-- Name: pfi_entitat_custodiadef_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_custodiadef_fk_i ON pfi_entitat USING btree (custodiainfoid);


--
-- Name: pfi_entitat_faviconid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_faviconid_fk_i ON pfi_entitat USING btree (faviconid);


--
-- Name: pfi_entitat_firmatper_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_firmatper_fk_i ON pfi_entitat USING btree (firmatperformatid);


--
-- Name: pfi_entitat_logosegellid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_logosegellid_fk_i ON pfi_entitat USING btree (logosegellid);


--
-- Name: pfi_entitat_logowebid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_logowebid_fk_i ON pfi_entitat USING btree (logowebid);


--
-- Name: pfi_entitat_logowebpeuid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_logowebpeuid_fk_i ON pfi_entitat USING btree (logowebpeuid);


--
-- Name: pfi_entitat_motiudele_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_motiudele_fk_i ON pfi_entitat USING btree (motiudelegacioid);


--
-- Name: pfi_entitat_pdfautoriid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_pdfautoriid_fk_i ON pfi_entitat USING btree (pdfautoritzaciodelegacioid);


--
-- Name: pfi_entitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_pk_i ON pfi_entitat USING btree (entitatid);


--
-- Name: pfi_entitat_segelltemps_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_segelltemps_fk_i ON pfi_entitat USING btree (pluginid);


--
-- Name: pfi_entitat_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_usrappid_fk_i ON pfi_entitat USING btree (usuariaplicacioid);


--
-- Name: pfi_estatdefirma_firmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatdefirma_firmaid_fk_i ON pfi_estatdefirma USING btree (firmaid);


--
-- Name: pfi_estatdefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatdefirma_pk_i ON pfi_estatdefirma USING btree (estatdefirmaid);


--
-- Name: pfi_estatfirma_coladele_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_coladele_fk_i ON pfi_estatdefirma USING btree (colaboraciodelegacioid);


--
-- Name: pfi_estatfirma_estatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_estatid_fk_i ON pfi_estatdefirma USING btree (tipusestatdefirmafinalid);


--
-- Name: pfi_estatfirma_estatinid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_estatinid_fk_i ON pfi_estatdefirma USING btree (tipusestatdefirmainicialid);


--
-- Name: pfi_estatfirma_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_usrentid_fk_i ON pfi_estatdefirma USING btree (usuarientitatid);


--
-- Name: pfi_estfirmafi_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estfirmafi_pk_i ON pfi_tipusestatdefirmafinal USING btree (tipusestatdefirmafinalid);


--
-- Name: pfi_estfirmini_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estfirmini_pk_i ON pfi_tipusestatdefirmainicial USING btree (tipusestatdefirmainicialid);


--
-- Name: pfi_estpetfirm_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estpetfirm_pk_i ON pfi_tipusestatpeticiodefirma USING btree (tipusestatpeticiodefirmaid);


--
-- Name: pfi_favorit_favoritid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_favorit_favoritid_fk_i ON pfi_usuarientitatfavorit USING btree (favoritid);


--
-- Name: pfi_favorit_origenid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_favorit_origenid_fk_i ON pfi_usuarientitatfavorit USING btree (origenid);


--
-- Name: pfi_firma_blocdefirmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_blocdefirmaid_fk_i ON pfi_firma USING btree (blocdefirmaid);


--
-- Name: pfi_firma_destinatariid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_destinatariid_fk_i ON pfi_firma USING btree (destinatariid);


--
-- Name: pfi_firma_estatfirmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_estatfirmaid_fk_i ON pfi_firma USING btree (tipusestatdefirmafinalid);


--
-- Name: pfi_firma_fitxerfirmatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_fitxerfirmatid_fk_i ON pfi_firma USING btree (fitxerfirmatid);


--
-- Name: pfi_firma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_pk_i ON pfi_firma USING btree (firmaid);


--
-- Name: pfi_fitxer_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_fitxer_pk_i ON pfi_fitxer USING btree (fitxerid);


--
-- Name: pfi_fluxdefirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_fluxdefirmes_pk_i ON pfi_fluxdefirmes USING btree (fluxdefirmesid);


--
-- Name: pfi_grupentitat_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupentitat_entitatid_fk_i ON pfi_grupentitat USING btree (entitatid);


--
-- Name: pfi_grupentitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupentitat_pk_i ON pfi_grupentitat USING btree (grupentitatid);


--
-- Name: pfi_grupusrent_grupentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupusrent_grupentid_fk_i ON pfi_grupentitatusuarientitat USING btree (grupentitatid);


--
-- Name: pfi_grupusrent_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupusrent_pk_i ON pfi_grupentitatusuarientitat USING btree (grupentitatusuarientitatid);


--
-- Name: pfi_grupusrent_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupusrent_usrentid_fk_i ON pfi_grupentitatusuarientitat USING btree (usuarientitatid);


--
-- Name: pfi_idioma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_idioma_pk_i ON pfi_idioma USING btree (idiomaid);


--
-- Name: pfi_metadada_peticioid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_metadada_peticioid_fk_i ON pfi_metadada USING btree (peticiodefirmaid);


--
-- Name: pfi_metadada_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_metadada_pk_i ON pfi_metadada USING btree (metadadaid);


--
-- Name: pfi_metadada_tipusmetaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_metadada_tipusmetaid_fk_i ON pfi_metadada USING btree (tipusmetadadaid);


--
-- Name: pfi_modulfirmapertipusdoc_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_modulfirmapertipusdoc_pk_i ON pfi_modulfirmapertipusdoc USING btree (id);


--
-- Name: pfi_mofitido_modfirma_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_mofitido_modfirma_fk_i ON pfi_modulfirmapertipusdoc USING btree (pluginid);


--
-- Name: pfi_mofitido_tipusdoc_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_mofitido_tipusdoc_fk_i ON pfi_modulfirmapertipusdoc USING btree (tipusdocumentid);


--
-- Name: pfi_notifica_peticioid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notifica_peticioid_fk_i ON pfi_notificacio USING btree (peticiodefirmaid);


--
-- Name: pfi_notifica_tiponotiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notifica_tiponotiid_fk_i ON pfi_notificacio USING btree (tipusnotificacioid);


--
-- Name: pfi_notificacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notificacio_pk_i ON pfi_notificacio USING btree (notificacioid);


--
-- Name: pfi_permisgrpl_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisgrpl_fluxid_fk_i ON pfi_permisgrupplantilla USING btree (fluxdefirmesid);


--
-- Name: pfi_permisgrpl_grupentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisgrpl_grupentid_fk_i ON pfi_permisgrupplantilla USING btree (grupentitatid);


--
-- Name: pfi_permisgrupplantilla_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisgrupplantilla_pk_i ON pfi_permisgrupplantilla USING btree (permisgrupplantillaid);


--
-- Name: pfi_permisuspl_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisuspl_fluxid_fk_i ON pfi_permisusuariplantilla USING btree (fluxdefirmesid);


--
-- Name: pfi_permisuspl_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisuspl_usrentid_fk_i ON pfi_permisusuariplantilla USING btree (usuarientitatid);


--
-- Name: pfi_permisusuariplantilla_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisusuariplantilla_pk_i ON pfi_permisusuariplantilla USING btree (permisusuariplantillaid);


--
-- Name: pfi_persona_idiomaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_persona_idiomaid_fk_i ON pfi_usuaripersona USING btree (idiomaid);


--
-- Name: pfi_persona_rubricaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_persona_rubricaid_fk_i ON pfi_usuaripersona USING btree (rubricaid);


--
-- Name: pfi_peticiodefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_peticiodefirma_pk_i ON pfi_peticiodefirma USING btree (peticiodefirmaid);


--
-- Name: pfi_petifirma_algofirmid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_algofirmid_fk_i ON pfi_peticiodefirma USING btree (algorismedefirmaid);


--
-- Name: pfi_petifirma_custinfoid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_custinfoid_fk_i ON pfi_peticiodefirma USING btree (custodiainfoid);


--
-- Name: pfi_petifirma_estatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_estatid_fk_i ON pfi_peticiodefirma USING btree (tipusestatpeticiodefirmaid);


--
-- Name: pfi_petifirma_fitxeadaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_fitxeadaid_fk_i ON pfi_peticiodefirma USING btree (fitxeradaptatid);


--
-- Name: pfi_petifirma_fitxerid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_fitxerid_fk_i ON pfi_peticiodefirma USING btree (fitxerafirmarid);


--
-- Name: pfi_petifirma_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_fluxid_fk_i ON pfi_peticiodefirma USING btree (fluxdefirmesid);


--
-- Name: pfi_petifirma_idiomaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_idiomaid_fk_i ON pfi_peticiodefirma USING btree (idiomaid);


--
-- Name: pfi_petifirma_logosegid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_logosegid_fk_i ON pfi_peticiodefirma USING btree (logosegellid);


--
-- Name: pfi_petifirma_postaulaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_postaulaid_fk_i ON pfi_peticiodefirma USING btree (posiciotaulafirmesid);


--
-- Name: pfi_petifirma_prioritatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_prioritatid_fk_i ON pfi_peticiodefirma USING btree (prioritatid);


--
-- Name: pfi_petifirma_tipofirmid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_tipofirmid_fk_i ON pfi_peticiodefirma USING btree (tipusfirmaid);


--
-- Name: pfi_petifirma_tipusdocid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_tipusdocid_fk_i ON pfi_peticiodefirma USING btree (tipusdocumentid);


--
-- Name: pfi_petifirma_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_usrappid_fk_i ON pfi_peticiodefirma USING btree (usuariaplicacioid);


--
-- Name: pfi_petifirma_usrentiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_usrentiid_fk_i ON pfi_peticiodefirma USING btree (usuarientitatid);


--
-- Name: pfi_plantiflfi_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plantiflfi_usrappid_fk_i ON pfi_plantillafluxdefirmes USING btree (usuariaplicacioid);


--
-- Name: pfi_plantiflfi_usrentiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plantiflfi_usrentiid_fk_i ON pfi_plantillafluxdefirmes USING btree (usuarientitatid);


--
-- Name: pfi_plantillafluxdefirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plantillafluxdefirmes_pk_i ON pfi_plantillafluxdefirmes USING btree (fluxdefirmesid);


--
-- Name: pfi_plugin_desccurtaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugin_desccurtaid_fk_i ON pfi_plugin USING btree (descripciocurtaid);


--
-- Name: pfi_plugin_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugin_entitatid_fk_i ON pfi_plugin USING btree (entitatid);


--
-- Name: pfi_plugin_nomid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugin_nomid_fk_i ON pfi_plugin USING btree (nomid);


--
-- Name: pfi_plugin_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugin_pk_i ON pfi_plugin USING btree (pluginid);


--
-- Name: pfi_posiciopagina_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_posiciopagina_pk_i ON pfi_posiciopagina USING btree (posiciopaginaid);


--
-- Name: pfi_posiciotaulafirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_posiciotaulafirmes_pk_i ON pfi_posiciotaulafirmes USING btree (posiciotaulafirmesid);


--
-- Name: pfi_prioritat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_prioritat_pk_i ON pfi_prioritat USING btree (prioritatid);


--
-- Name: pfi_rebreavis_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_rebreavis_pk_i ON pfi_rebreavis USING btree (id);


--
-- Name: pfi_rebreavis_tiponotiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_rebreavis_tiponotiid_fk_i ON pfi_rebreavis USING btree (tipusnotificacioid);


--
-- Name: pfi_rebreavis_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_rebreavis_usrentid_fk_i ON pfi_rebreavis USING btree (usuarientitatid);


--
-- Name: pfi_role_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_role_pk_i ON pfi_role USING btree (roleid);


--
-- Name: pfi_roleusrapp_roleid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrapp_roleid_fk_i ON pfi_roleusuariaplicacio USING btree (roleid);


--
-- Name: pfi_roleusrapp_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrapp_usrappid_fk_i ON pfi_roleusuariaplicacio USING btree (usuariaplicacioid);


--
-- Name: pfi_roleusrent_roleid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrent_roleid_fk_i ON pfi_roleusuarientitat USING btree (roleid);


--
-- Name: pfi_roleusrent_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrent_usrentid_fk_i ON pfi_roleusuarientitat USING btree (usuarientitatid);


--
-- Name: pfi_roleusuariaplicacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusuariaplicacio_pk_i ON pfi_roleusuariaplicacio USING btree (id);


--
-- Name: pfi_roleusuarientitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusuarientitat_pk_i ON pfi_roleusuarientitat USING btree (id);


--
-- Name: pfi_tipusdoc_usuariappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdoc_usuariappid_fk_i ON pfi_tipusdocument USING btree (usuariaplicacioid);


--
-- Name: pfi_tipusdoccd_coldelid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdoccd_coldelid_fk_i ON pfi_tipusdocumentcoladele USING btree (colaboraciodelegacioid);


--
-- Name: pfi_tipusdoccd_tipusdocid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdoccd_tipusdocid_fk_i ON pfi_tipusdocumentcoladele USING btree (tipusdocumentid);


--
-- Name: pfi_tipusdocument_nom_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdocument_nom_fk_i ON pfi_tipusdocument USING btree (nom);


--
-- Name: pfi_tipusdocument_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdocument_pk_i ON pfi_tipusdocument USING btree (tipusdocumentid);


--
-- Name: pfi_tipusdocumentcoladele_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdocumentcoladele_pk_i ON pfi_tipusdocumentcoladele USING btree (id);


--
-- Name: pfi_tipusfirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusfirma_pk_i ON pfi_tipusfirma USING btree (tipusfirmaid);


--
-- Name: pfi_tipusmetadada_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusmetadada_pk_i ON pfi_tipusmetadada USING btree (tipusmetadadaid);


--
-- Name: pfi_tipusnotificacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusnotificacio_pk_i ON pfi_tipusnotificacio USING btree (tipusnotificacioid);


--
-- Name: pfi_traduccio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_traduccio_pk_i ON pfi_traduccio USING btree (traduccioid);


--
-- Name: pfi_traducmap_idiomaid_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_traducmap_idiomaid_pk_i ON pfi_traducciomap USING btree (traducciomapid);


--
-- Name: pfi_traducmap_tradmapid_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_traducmap_tradmapid_pk_i ON pfi_traducciomap USING btree (traducciomapid);


--
-- Name: pfi_usrapp_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_entitatid_fk_i ON pfi_usuariaplicacio USING btree (entitatid);


--
-- Name: pfi_usrapp_idiomaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_idiomaid_fk_i ON pfi_usuariaplicacio USING btree (idiomaid);


--
-- Name: pfi_usrapp_logosegellid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_logosegellid_fk_i ON pfi_usuariaplicacio USING btree (logosegellid);


--
-- Name: pfi_usrentitat_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_entitatid_fk_i ON pfi_usuarientitat USING btree (entitatid);


--
-- Name: pfi_usrentitat_logosegid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_logosegid_fk_i ON pfi_usuarientitat USING btree (logosegellid);


--
-- Name: pfi_usrentitat_personaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_personaid_fk_i ON pfi_usuarientitat USING btree (usuaripersonaid);


--
-- Name: pfi_usuariaplicacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuariaplicacio_pk_i ON pfi_usuariaplicacio USING btree (usuariaplicacioid);


--
-- Name: pfi_usuarientitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuarientitat_pk_i ON pfi_usuarientitat USING btree (usuarientitatid);


--
-- Name: pfi_usuarientitatfavorit_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuarientitatfavorit_pk_i ON pfi_usuarientitatfavorit USING btree (id);


--
-- Name: pfi_usuaripersona_nif_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuaripersona_nif_i ON pfi_usuaripersona USING btree (nif);


--
-- Name: pfi_usuaripersona_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuaripersona_pk_i ON pfi_usuaripersona USING btree (usuaripersonaid);


--
-- Name: pfi_anexfirmat_annex_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT pfi_anexfirmat_annex_fk FOREIGN KEY (annexid) REFERENCES pfi_annex(annexid);


--
-- Name: pfi_anexfirmat_firma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT pfi_anexfirmat_firma_fk FOREIGN KEY (firmaid) REFERENCES pfi_firma(firmaid);


--
-- Name: pfi_anexfirmat_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT pfi_anexfirmat_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_annex_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annex
    ADD CONSTRAINT pfi_annex_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_annex_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annex
    ADD CONSTRAINT pfi_annex_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- Name: pfi_bitacola_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_bitacola
    ADD CONSTRAINT pfi_bitacola_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- Name: pfi_bitacola_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_bitacola
    ADD CONSTRAINT pfi_bitacola_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_blocfirmes_fluxfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_blocdefirmes
    ADD CONSTRAINT pfi_blocfirmes_fluxfirmes_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_fluxdefirmes(fluxdefirmesid);


--
-- Name: pfi_colabdeleg_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colabdeleg_fitxer_fk FOREIGN KEY (fitxerautoritzacioid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_colabdeleg_usrentitat_c_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colabdeleg_usrentitat_c_fk FOREIGN KEY (colaboradordelegatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_colabdeleg_usrentitat_d_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colabdeleg_usrentitat_d_fk FOREIGN KEY (destinatariid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_custodia_codibarres_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_codibarres_fk FOREIGN KEY (codibarresid) REFERENCES pfi_codibarres(codibarresid);


--
-- Name: pfi_custodia_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- Name: pfi_custodia_plugin_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_plugin_fk FOREIGN KEY (pluginid) REFERENCES pfi_plugin(pluginid);


--
-- Name: pfi_custodia_pospagina_bar_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_pospagina_bar_fk FOREIGN KEY (codibarresposiciopaginaid) REFERENCES pfi_posiciopagina(posiciopaginaid);


--
-- Name: pfi_custodia_pospagina_msg_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_pospagina_msg_fk FOREIGN KEY (missatgeposiciopaginaid) REFERENCES pfi_posiciopagina(posiciopaginaid);


--
-- Name: pfi_custodia_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- Name: pfi_custodia_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_entitat_algofirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_algofirma_fk FOREIGN KEY (algorismedefirmaid) REFERENCES pfi_algorismedefirma(algorismedefirmaid);


--
-- Name: pfi_entitat_custodia_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_custodia_fk FOREIGN KEY (custodiainfoid) REFERENCES pfi_custodiainfo(custodiainfoid);


--
-- Name: pfi_entitat_fitxer_icon_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_icon_fk FOREIGN KEY (faviconid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_entitat_fitxer_loca_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_loca_fk FOREIGN KEY (logowebid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_entitat_fitxer_lope_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_lope_fk FOREIGN KEY (logowebpeuid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_entitat_fitxer_lose_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_lose_fk FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_entitat_fitxer_pdfd_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_pdfd_fk FOREIGN KEY (pdfautoritzaciodelegacioid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_entitat_plugin_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_plugin_fk FOREIGN KEY (pluginid) REFERENCES pfi_plugin(pluginid);


--
-- Name: pfi_entitat_traduccio_firm_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_traduccio_firm_fk FOREIGN KEY (firmatperformatid) REFERENCES pfi_traduccio(traduccioid);


--
-- Name: pfi_entitat_traduccio_moti_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_traduccio_moti_fk FOREIGN KEY (motiudelegacioid) REFERENCES pfi_traduccio(traduccioid);


--
-- Name: pfi_entitat_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- Name: pfi_estatfirma_colabdeleg_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_colabdeleg_fk FOREIGN KEY (colaboraciodelegacioid) REFERENCES pfi_colaboraciodelegacio(colaboraciodelegacioid);


--
-- Name: pfi_estatfirma_estfirmafi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_estfirmafi_fk FOREIGN KEY (tipusestatdefirmafinalid) REFERENCES pfi_tipusestatdefirmafinal(tipusestatdefirmafinalid);


--
-- Name: pfi_estatfirma_estfirmini_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_estfirmini_fk FOREIGN KEY (tipusestatdefirmainicialid) REFERENCES pfi_tipusestatdefirmainicial(tipusestatdefirmainicialid);


--
-- Name: pfi_estatfirma_firma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_firma_fk FOREIGN KEY (firmaid) REFERENCES pfi_firma(firmaid);


--
-- Name: pfi_estatfirma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_favorit_usrentitat_fav_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_favorit_usrentitat_fav_fk FOREIGN KEY (favoritid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_favorit_usrentitat_ori_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_favorit_usrentitat_ori_fk FOREIGN KEY (origenid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_firma_blocfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_blocfirmes_fk FOREIGN KEY (blocdefirmaid) REFERENCES pfi_blocdefirmes(blocdefirmesid);


--
-- Name: pfi_firma_estfirmafi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_estfirmafi_fk FOREIGN KEY (tipusestatdefirmafinalid) REFERENCES pfi_tipusestatdefirmafinal(tipusestatdefirmafinalid);


--
-- Name: pfi_firma_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_fitxer_fk FOREIGN KEY (fitxerfirmatid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_firma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_usrentitat_fk FOREIGN KEY (destinatariid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_grupentita_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_grupentitat
    ADD CONSTRAINT pfi_grupentita_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- Name: pfi_grupusrent_grupentita_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_grupentita_fk FOREIGN KEY (grupentitatid) REFERENCES pfi_grupentitat(grupentitatid);


--
-- Name: pfi_grupusrent_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_metadada_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_metadada
    ADD CONSTRAINT pfi_metadada_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- Name: pfi_metadada_tipmetada_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_metadada
    ADD CONSTRAINT pfi_metadada_tipmetada_fk FOREIGN KEY (tipusmetadadaid) REFERENCES pfi_tipusmetadada(tipusmetadadaid);


--
-- Name: pfi_mofitido_plugin_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_plugin_fk FOREIGN KEY (pluginid) REFERENCES pfi_plugin(pluginid);


--
-- Name: pfi_mofitido_tipusdoc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES pfi_tipusdocument(tipusdocumentid);


--
-- Name: pfi_notifica_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_notificacio
    ADD CONSTRAINT pfi_notifica_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- Name: pfi_notifica_tipnotific_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_notificacio
    ADD CONSTRAINT pfi_notifica_tipnotific_fk FOREIGN KEY (tipusnotificacioid) REFERENCES pfi_tipusnotificacio(tipusnotificacioid);


--
-- Name: pfi_permisgrpl_grupentita_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrpl_grupentita_fk FOREIGN KEY (grupentitatid) REFERENCES pfi_grupentitat(grupentitatid);


--
-- Name: pfi_permisgrpl_plantiflfi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrpl_plantiflfi_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_plantillafluxdefirmes(fluxdefirmesid);


--
-- Name: pfi_permisuspl_plantiflfi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisuspl_plantiflfi_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_plantillafluxdefirmes(fluxdefirmesid);


--
-- Name: pfi_permisuspl_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisuspl_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_persona_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_persona_fitxer_fk FOREIGN KEY (rubricaid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_persona_idioma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_persona_idioma_fk FOREIGN KEY (idiomaid) REFERENCES pfi_idioma(idiomaid);


--
-- Name: pfi_petifirma_algofirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_algofirma_fk FOREIGN KEY (algorismedefirmaid) REFERENCES pfi_algorismedefirma(algorismedefirmaid);


--
-- Name: pfi_petifirma_custodia_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_custodia_fk FOREIGN KEY (custodiainfoid) REFERENCES pfi_custodiainfo(custodiainfoid);


--
-- Name: pfi_petifirma_estpetfirm_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_estpetfirm_fk FOREIGN KEY (tipusestatpeticiodefirmaid) REFERENCES pfi_tipusestatpeticiodefirma(tipusestatpeticiodefirmaid);


--
-- Name: pfi_petifirma_fitxer_ada_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_ada_fk FOREIGN KEY (fitxeradaptatid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_petifirma_fitxer_fir_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_fir_fk FOREIGN KEY (fitxerafirmarid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_petifirma_fitxer_log_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_log_fk FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_petifirma_fluxfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fluxfirmes_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_fluxdefirmes(fluxdefirmesid);


--
-- Name: pfi_petifirma_idioma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_idioma_fk FOREIGN KEY (idiomaid) REFERENCES pfi_idioma(idiomaid);


--
-- Name: pfi_petifirma_postaufir_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_postaufir_fk FOREIGN KEY (posiciotaulafirmesid) REFERENCES pfi_posiciotaulafirmes(posiciotaulafirmesid);


--
-- Name: pfi_petifirma_prioritat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_prioritat_fk FOREIGN KEY (prioritatid) REFERENCES pfi_prioritat(prioritatid);


--
-- Name: pfi_petifirma_tipusdoc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES pfi_tipusdocument(tipusdocumentid);


--
-- Name: pfi_petifirma_tipusfirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_tipusfirma_fk FOREIGN KEY (tipusfirmaid) REFERENCES pfi_tipusfirma(tipusfirmaid);


--
-- Name: pfi_petifirma_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- Name: pfi_petifirma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_plantiflfi_fluxfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantiflfi_fluxfirmes_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_fluxdefirmes(fluxdefirmesid);


--
-- Name: pfi_plantiflfi_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantiflfi_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- Name: pfi_plantiflfi_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantiflfi_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_plugin_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plugin
    ADD CONSTRAINT pfi_plugin_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- Name: pfi_plugin_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plugin
    ADD CONSTRAINT pfi_plugin_traduccio_desc_fk FOREIGN KEY (descripciocurtaid) REFERENCES pfi_traduccio(traduccioid);


--
-- Name: pfi_plugin_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plugin
    ADD CONSTRAINT pfi_plugin_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES pfi_traduccio(traduccioid);


--
-- Name: pfi_rebreavis_tipnotific_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_tipnotific_fk FOREIGN KEY (tipusnotificacioid) REFERENCES pfi_tipusnotificacio(tipusnotificacioid);


--
-- Name: pfi_rebreavis_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_roleusrapp_role_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusrapp_role_fk FOREIGN KEY (roleid) REFERENCES pfi_role(roleid);


--
-- Name: pfi_roleusrapp_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusrapp_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- Name: pfi_roleusrent_role_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusrent_role_fk FOREIGN KEY (roleid) REFERENCES pfi_role(roleid);


--
-- Name: pfi_roleusrent_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusrent_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- Name: pfi_tipusdoc_traduccio_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdoc_traduccio_fk FOREIGN KEY (nom) REFERENCES pfi_traduccio(traduccioid);


--
-- Name: pfi_tipusdoc_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdoc_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- Name: pfi_tipusdoccd_colabdeleg_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdoccd_colabdeleg_fk FOREIGN KEY (colaboraciodelegacioid) REFERENCES pfi_colaboraciodelegacio(colaboraciodelegacioid);


--
-- Name: pfi_tipusdoccd_tipusdoc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdoccd_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES pfi_tipusdocument(tipusdocumentid);


--
-- Name: pfi_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_traducciomap
    ADD CONSTRAINT pfi_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES pfi_traduccio(traduccioid);


--
-- Name: pfi_usrapp_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- Name: pfi_usrapp_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_fitxer_fk FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_usrapp_idioma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_idioma_fk FOREIGN KEY (idiomaid) REFERENCES pfi_idioma(idiomaid);


--
-- Name: pfi_usrentitat_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- Name: pfi_usrentitat_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_fitxer_fk FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- Name: pfi_usrentitat_persona_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_persona_fk FOREIGN KEY (usuaripersonaid) REFERENCES pfi_usuaripersona(usuaripersonaid);


--
-- Dependencies: 6
-- Name: portafib; Type: ACL; Schema: -; Owner: portafib
--

REVOKE ALL ON SCHEMA portafib FROM PUBLIC;
REVOKE ALL ON SCHEMA portafib FROM portafib;
GRANT ALL ON SCHEMA portafib TO portafib;


--
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-12-01 09:50:01

--
-- PostgreSQL database dump complete
--

