--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2015-11-13 11:31:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 30632)
-- Name: portafib; Type: SCHEMA; Schema: -; Owner: portafib
--

CREATE SCHEMA portafib;


ALTER SCHEMA portafib OWNER TO portafib;

SET search_path = portafib, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 171 (class 1259 OID 30633)
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
-- TOC entry 172 (class 1259 OID 30636)
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
-- TOC entry 173 (class 1259 OID 30638)
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
-- TOC entry 174 (class 1259 OID 30642)
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
-- TOC entry 175 (class 1259 OID 30646)
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
-- TOC entry 176 (class 1259 OID 30650)
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
-- TOC entry 177 (class 1259 OID 30654)
-- Name: pfi_codibarres; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_codibarres (
    codibarresid character varying(255) NOT NULL,
    nom character varying(50) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE portafib.pfi_codibarres OWNER TO portafib;

--
-- TOC entry 178 (class 1259 OID 30660)
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
-- TOC entry 2540 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN pfi_colaboraciodelegacio.revisor; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_colaboraciodelegacio.revisor IS 'Només es per col.laborador i indica si es obligatori que aquell col.laborador digui la seva.';


--
-- TOC entry 179 (class 1259 OID 30670)
-- Name: pfi_custodiainfo; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_custodiainfo (
    custodiainfoid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    custodiapluginid character varying(255),
    custodiapluginclassid character varying(255) NOT NULL,
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
    editable boolean NOT NULL
);


ALTER TABLE portafib.pfi_custodiainfo OWNER TO portafib;

--
-- TOC entry 180 (class 1259 OID 30677)
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
    comprovarcertificatclientcert boolean DEFAULT true NOT NULL,
    comprovarniffirma boolean DEFAULT true NOT NULL
);


ALTER TABLE portafib.pfi_entitat OWNER TO portafib;

--
-- TOC entry 181 (class 1259 OID 30685)
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
-- TOC entry 182 (class 1259 OID 30690)
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
-- TOC entry 2541 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN pfi_firma.destinatariid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_firma.destinatariid IS 'Si val null significa que s''ha de substituir pel Sol·licitant de la petició (només podrà valer null en plantilles de flux de firmes)';


--
-- TOC entry 2542 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN pfi_firma.mostrarrubrica; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_firma.mostrarrubrica IS 'Dibuixar la firma(rubrica) de l''usuari persona si s''ha definit la caixa';


--
-- TOC entry 183 (class 1259 OID 30697)
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
-- TOC entry 184 (class 1259 OID 30705)
-- Name: pfi_fluxdefirmes; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_fluxdefirmes (
    fluxdefirmesid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL
);


ALTER TABLE portafib.pfi_fluxdefirmes OWNER TO portafib;

--
-- TOC entry 185 (class 1259 OID 30709)
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
-- TOC entry 186 (class 1259 OID 30713)
-- Name: pfi_grupentitatusuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_grupentitatusuarientitat (
    grupentitatusuarientitatid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    grupentitatid bigint NOT NULL
);


ALTER TABLE portafib.pfi_grupentitatusuarientitat OWNER TO portafib;

--
-- TOC entry 187 (class 1259 OID 30717)
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
-- TOC entry 188 (class 1259 OID 30722)
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
-- TOC entry 215 (class 1259 OID 56385)
-- Name: pfi_moduldefirma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_moduldefirma (
    moduldefirmaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    descripciocurtaid bigint NOT NULL,
    classe character varying(255) NOT NULL,
    propertiesadmin text,
    propertiesentitat text,
    entitatid character varying(50),
    actiu boolean NOT NULL
);


ALTER TABLE portafib.pfi_moduldefirma OWNER TO portafib;

--
-- TOC entry 2543 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN pfi_moduldefirma.entitatid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_moduldefirma.entitatid IS 'Si val null indica que és de l''Administrador. En cas conytrari ja és una instanciació d''una Entitat';


--
-- TOC entry 216 (class 1259 OID 56413)
-- Name: pfi_modulfirmapertipusdoc; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_modulfirmapertipusdoc (
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    tipusdocumentid bigint NOT NULL,
    moduldefirmaid bigint NOT NULL,
    nom character varying(100) NOT NULL
);


ALTER TABLE portafib.pfi_modulfirmapertipusdoc OWNER TO portafib;

--
-- TOC entry 189 (class 1259 OID 30730)
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
-- TOC entry 190 (class 1259 OID 30738)
-- Name: pfi_permisgrupplantilla; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_permisgrupplantilla (
    permisgrupplantillaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    grupentitatid bigint NOT NULL,
    fluxdefirmesid bigint NOT NULL
);


ALTER TABLE portafib.pfi_permisgrupplantilla OWNER TO portafib;

--
-- TOC entry 191 (class 1259 OID 30742)
-- Name: pfi_permisusuariplantilla; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_permisusuariplantilla (
    permisusuariplantillaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    fluxdefirmesid bigint NOT NULL
);


ALTER TABLE portafib.pfi_permisusuariplantilla OWNER TO portafib;

--
-- TOC entry 192 (class 1259 OID 30746)
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
    avisweb boolean DEFAULT false NOT NULL
);


ALTER TABLE portafib.pfi_peticiodefirma OWNER TO portafib;

--
-- TOC entry 2544 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN pfi_peticiodefirma.posiciotaulafirmesid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_peticiodefirma.posiciotaulafirmesid IS 'Posicio taula de firmes: primera o darrera pàgina';


--
-- TOC entry 193 (class 1259 OID 30758)
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
-- TOC entry 194 (class 1259 OID 30766)
-- Name: pfi_posiciopagina; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_posiciopagina (
    posiciopaginaid bigint NOT NULL,
    nom character varying(255) NOT NULL
);


ALTER TABLE portafib.pfi_posiciopagina OWNER TO portafib;

--
-- TOC entry 195 (class 1259 OID 30769)
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
-- TOC entry 196 (class 1259 OID 30773)
-- Name: pfi_prioritat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_prioritat (
    prioritatid integer NOT NULL,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_prioritat OWNER TO portafib;

--
-- TOC entry 197 (class 1259 OID 30776)
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
-- TOC entry 198 (class 1259 OID 30780)
-- Name: pfi_role; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_role (
    roleid character varying(50) NOT NULL,
    nom character varying(50) DEFAULT NULL::character varying NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying
);


ALTER TABLE portafib.pfi_role OWNER TO portafib;

--
-- TOC entry 199 (class 1259 OID 30785)
-- Name: pfi_roleusuariaplicacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_roleusuariaplicacio (
    usuariaplicacioid character varying(50) NOT NULL,
    roleid character varying(50) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_roleusuariaplicacio OWNER TO portafib;

--
-- TOC entry 200 (class 1259 OID 30789)
-- Name: pfi_roleusuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_roleusuarientitat (
    roleid character varying(50) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_roleusuarientitat OWNER TO portafib;

--
-- TOC entry 201 (class 1259 OID 30793)
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
-- TOC entry 202 (class 1259 OID 30800)
-- Name: pfi_tipusdocumentcoladele; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusdocumentcoladele (
    colaboraciodelegacioid bigint NOT NULL,
    tipusdocumentid bigint NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_tipusdocumentcoladele OWNER TO portafib;

--
-- TOC entry 203 (class 1259 OID 30804)
-- Name: pfi_tipusestatdefirmafinal; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusestatdefirmafinal (
    tipusestatdefirmafinalid bigint NOT NULL,
    nom character varying(50) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE portafib.pfi_tipusestatdefirmafinal OWNER TO portafib;

--
-- TOC entry 204 (class 1259 OID 30807)
-- Name: pfi_tipusestatdefirmainicial; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusestatdefirmainicial (
    tipusestatdefirmainicialid bigint NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_tipusestatdefirmainicial OWNER TO portafib;

--
-- TOC entry 205 (class 1259 OID 30811)
-- Name: pfi_tipusestatpeticiodefirma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusestatpeticiodefirma (
    tipusestatpeticiodefirmaid bigint NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_tipusestatpeticiodefirma OWNER TO portafib;

--
-- TOC entry 206 (class 1259 OID 30818)
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
-- TOC entry 207 (class 1259 OID 30826)
-- Name: pfi_tipusmetadada; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusmetadada (
    tipusmetadadaid integer NOT NULL,
    nom character varying(100) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE portafib.pfi_tipusmetadada OWNER TO portafib;

--
-- TOC entry 208 (class 1259 OID 30829)
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
-- TOC entry 2545 (class 0 OID 0)
-- Dependencies: 208
-- Name: COLUMN pfi_tipusnotificacio.esavis; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_tipusnotificacio.esavis IS 'Si es avis val true
Si és notificació es false
Si pot ser avis i notificació llavors val null
';


--
-- TOC entry 209 (class 1259 OID 30833)
-- Name: pfi_traduccio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_traduccio (
    traduccioid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_traduccio OWNER TO portafib;

--
-- TOC entry 210 (class 1259 OID 30837)
-- Name: pfi_traducciomap; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(5) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE portafib.pfi_traducciomap OWNER TO portafib;

--
-- TOC entry 211 (class 1259 OID 30843)
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
-- TOC entry 2546 (class 0 OID 0)
-- Dependencies: 211
-- Name: TABLE pfi_usuariaplicacio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON TABLE pfi_usuariaplicacio IS 'Usuari de tipus màquina que realitzarà peticions a PortaFIB';


--
-- TOC entry 2547 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacio.emailadmin; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuariaplicacio.emailadmin IS 'Correu de la persona encarregada d''aquest usuari-Màquina';


--
-- TOC entry 2548 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacio.callbackurl; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuariaplicacio.callbackurl IS 'Adreça on esta implementat el servei de recepció de notificacions associades a les peticions de firma realitzades per aquest usuari-màquina';


--
-- TOC entry 2549 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacio.callbackversio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuariaplicacio.callbackversio IS 'La versió 1 és la compatible amb INDRA i la versió 2 és l''especifica del nou Portafirmes';


--
-- TOC entry 212 (class 1259 OID 30853)
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
-- TOC entry 213 (class 1259 OID 30861)
-- Name: pfi_usuarientitatfavorit; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_usuarientitatfavorit (
    origenid character varying(101) NOT NULL,
    favoritid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_usuarientitatfavorit OWNER TO portafib;

--
-- TOC entry 214 (class 1259 OID 30865)
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
-- TOC entry 2550 (class 0 OID 0)
-- Dependencies: 214
-- Name: COLUMN pfi_usuaripersona.rubricaid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuaripersona.rubricaid IS 'és la firma gràfica de la persona';


--
-- TOC entry 2082 (class 2606 OID 30869)
-- Name: pfi_algofirma_nom_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_algorismedefirma
    ADD CONSTRAINT pfi_algofirma_nom_uk UNIQUE (nom);


--
-- TOC entry 2084 (class 2606 OID 30871)
-- Name: pfi_algorismedefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_algorismedefirma
    ADD CONSTRAINT pfi_algorismedefirma_pk PRIMARY KEY (algorismedefirmaid);


--
-- TOC entry 2089 (class 2606 OID 30873)
-- Name: pfi_annex_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_annex
    ADD CONSTRAINT pfi_annex_pk PRIMARY KEY (annexid);


--
-- TOC entry 2095 (class 2606 OID 30875)
-- Name: pfi_annexfirmat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT pfi_annexfirmat_pk PRIMARY KEY (annexfirmatid);


--
-- TOC entry 2099 (class 2606 OID 30877)
-- Name: pfi_bitacola_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_bitacola
    ADD CONSTRAINT pfi_bitacola_pk PRIMARY KEY (bitacolaid);


--
-- TOC entry 2103 (class 2606 OID 30879)
-- Name: pfi_blocdefirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_blocdefirmes
    ADD CONSTRAINT pfi_blocdefirmes_pk PRIMARY KEY (blocdefirmesid);


--
-- TOC entry 2107 (class 2606 OID 30881)
-- Name: pfi_codibarres_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_codibarres
    ADD CONSTRAINT pfi_codibarres_pk PRIMARY KEY (codibarresid);


--
-- TOC entry 2113 (class 2606 OID 30883)
-- Name: pfi_colaboraciodelegacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colaboraciodelegacio_pk PRIMARY KEY (colaboraciodelegacioid);


--
-- TOC entry 2122 (class 2606 OID 30885)
-- Name: pfi_custodiainfo_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodiainfo_pk PRIMARY KEY (custodiainfoid);


--
-- TOC entry 2133 (class 2606 OID 30887)
-- Name: pfi_entitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_pk PRIMARY KEY (entitatid);


--
-- TOC entry 2138 (class 2606 OID 30889)
-- Name: pfi_estatdefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatdefirma_pk PRIMARY KEY (estatdefirmaid);


--
-- TOC entry 2270 (class 2606 OID 30891)
-- Name: pfi_estfirmini_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusestatdefirmainicial
    ADD CONSTRAINT pfi_estfirmini_pk PRIMARY KEY (tipusestatdefirmainicialid);


--
-- TOC entry 2273 (class 2606 OID 30893)
-- Name: pfi_estpetfirm_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusestatpeticiodefirma
    ADD CONSTRAINT pfi_estpetfirm_pk PRIMARY KEY (tipusestatpeticiodefirmaid);


--
-- TOC entry 2310 (class 2606 OID 30895)
-- Name: pfi_favorit_origfavo_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_favorit_origfavo_uk UNIQUE (origenid, favoritid);


--
-- TOC entry 2149 (class 2606 OID 30897)
-- Name: pfi_firma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_pk PRIMARY KEY (firmaid);


--
-- TOC entry 2152 (class 2606 OID 30899)
-- Name: pfi_fitxer_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_fitxer
    ADD CONSTRAINT pfi_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 2155 (class 2606 OID 30901)
-- Name: pfi_fluxdefirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_fluxdefirmes
    ADD CONSTRAINT pfi_fluxdefirmes_pk PRIMARY KEY (fluxdefirmesid);


--
-- TOC entry 2158 (class 2606 OID 30903)
-- Name: pfi_grupentita_nomentitat_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitat
    ADD CONSTRAINT pfi_grupentita_nomentitat_uk UNIQUE (nom, entitatid);


--
-- TOC entry 2161 (class 2606 OID 30905)
-- Name: pfi_grupentitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitat
    ADD CONSTRAINT pfi_grupentitat_pk PRIMARY KEY (grupentitatid);


--
-- TOC entry 2165 (class 2606 OID 30907)
-- Name: pfi_grupusrent_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_pk PRIMARY KEY (grupentitatusuarientitatid);


--
-- TOC entry 2169 (class 2606 OID 30909)
-- Name: pfi_grupusrent_usrgrup_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_usrgrup_uk UNIQUE (usuarientitatid, grupentitatid);


--
-- TOC entry 2171 (class 2606 OID 30911)
-- Name: pfi_idioma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_idioma
    ADD CONSTRAINT pfi_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 2175 (class 2606 OID 30913)
-- Name: pfi_metadada_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_metadada
    ADD CONSTRAINT pfi_metadada_pk PRIMARY KEY (metadadaid);


--
-- TOC entry 2326 (class 2606 OID 56392)
-- Name: pfi_moduldefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_moduldefirma
    ADD CONSTRAINT pfi_moduldefirma_pk PRIMARY KEY (moduldefirmaid);


--
-- TOC entry 2329 (class 2606 OID 56418)
-- Name: pfi_modulfirmapertipusdoc_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_modulfirmapertipusdoc_pk PRIMARY KEY (id);


--
-- TOC entry 2332 (class 2606 OID 56440)
-- Name: pfi_mofitido_modfirm_tipdoc_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_modfirm_tipdoc_uk UNIQUE (tipusdocumentid, moduldefirmaid);


--
-- TOC entry 2181 (class 2606 OID 30915)
-- Name: pfi_notificacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_notificacio
    ADD CONSTRAINT pfi_notificacio_pk PRIMARY KEY (notificacioid);


--
-- TOC entry 2186 (class 2606 OID 30917)
-- Name: pfi_permisgrpl_grupflux_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrpl_grupflux_uk UNIQUE (grupentitatid, fluxdefirmesid);


--
-- TOC entry 2188 (class 2606 OID 30919)
-- Name: pfi_permisgrupplantilla_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrupplantilla_pk PRIMARY KEY (permisgrupplantillaid);


--
-- TOC entry 2193 (class 2606 OID 30921)
-- Name: pfi_permisuspl_usrflux_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisuspl_usrflux_uk UNIQUE (usuarientitatid, fluxdefirmesid);


--
-- TOC entry 2195 (class 2606 OID 30923)
-- Name: pfi_permisusuariplantilla_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisusuariplantilla_pk PRIMARY KEY (permisusuariplantillaid);


--
-- TOC entry 2316 (class 2606 OID 30925)
-- Name: pfi_persona_nif_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_persona_nif_uk UNIQUE (nif);


--
-- TOC entry 2198 (class 2606 OID 30927)
-- Name: pfi_peticiodefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_peticiodefirma_pk PRIMARY KEY (peticiodefirmaid);


--
-- TOC entry 2206 (class 2606 OID 30929)
-- Name: pfi_petifirma_fluxfirmesid_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fluxfirmesid_uk UNIQUE (fluxdefirmesid);


--
-- TOC entry 2219 (class 2606 OID 30931)
-- Name: pfi_plantillafluxdefirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantillafluxdefirmes_pk PRIMARY KEY (fluxdefirmesid);


--
-- TOC entry 2222 (class 2606 OID 30933)
-- Name: pfi_posiciopagina_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_posiciopagina
    ADD CONSTRAINT pfi_posiciopagina_pk PRIMARY KEY (posiciopaginaid);


--
-- TOC entry 2225 (class 2606 OID 30935)
-- Name: pfi_posiciotaulafirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_posiciotaulafirmes
    ADD CONSTRAINT pfi_posiciotaulafirmes_pk PRIMARY KEY (posiciotaulafirmesid);


--
-- TOC entry 2228 (class 2606 OID 30937)
-- Name: pfi_prioritat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_prioritat
    ADD CONSTRAINT pfi_prioritat_pk PRIMARY KEY (prioritatid);


--
-- TOC entry 2231 (class 2606 OID 30939)
-- Name: pfi_rebreavis_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_pk PRIMARY KEY (id);


--
-- TOC entry 2235 (class 2606 OID 30941)
-- Name: pfi_rebreavis_tnotiusr_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_tnotiusr_uk UNIQUE (tipusnotificacioid, usuarientitatid);


--
-- TOC entry 2238 (class 2606 OID 30943)
-- Name: pfi_role_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_role
    ADD CONSTRAINT pfi_role_pk PRIMARY KEY (roleid);


--
-- TOC entry 2241 (class 2606 OID 30945)
-- Name: pfi_roleusrapp_approle_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusrapp_approle_uk UNIQUE (usuariaplicacioid, roleid);


--
-- TOC entry 2249 (class 2606 OID 30947)
-- Name: pfi_roleusrent_roleusrent_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusrent_roleusrent_uk UNIQUE (roleid, usuarientitatid);


--
-- TOC entry 2245 (class 2606 OID 30949)
-- Name: pfi_roleusuariaplicacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusuariaplicacio_pk PRIMARY KEY (id);


--
-- TOC entry 2252 (class 2606 OID 30951)
-- Name: pfi_roleusuarientitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusuarientitat_pk PRIMARY KEY (id);


--
-- TOC entry 2279 (class 2606 OID 30953)
-- Name: pfi_tipmetada_nom_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusmetadada
    ADD CONSTRAINT pfi_tipmetada_nom_uk UNIQUE (nom);


--
-- TOC entry 2260 (class 2606 OID 30955)
-- Name: pfi_tipusdoccd_codetdoc_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdoccd_codetdoc_uk UNIQUE (colaboraciodelegacioid, tipusdocumentid);


--
-- TOC entry 2257 (class 2606 OID 30957)
-- Name: pfi_tipusdocument_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdocument_pk PRIMARY KEY (tipusdocumentid);


--
-- TOC entry 2264 (class 2606 OID 30959)
-- Name: pfi_tipusdocumentcoladele_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdocumentcoladele_pk PRIMARY KEY (id);


--
-- TOC entry 2268 (class 2606 OID 30961)
-- Name: pfi_tipusestatdefirmafinal_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusestatdefirmafinal
    ADD CONSTRAINT pfi_tipusestatdefirmafinal_pk PRIMARY KEY (tipusestatdefirmafinalid);


--
-- TOC entry 2276 (class 2606 OID 30963)
-- Name: pfi_tipusfirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusfirma
    ADD CONSTRAINT pfi_tipusfirma_pk PRIMARY KEY (tipusfirmaid);


--
-- TOC entry 2281 (class 2606 OID 30965)
-- Name: pfi_tipusmetadada_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusmetadada
    ADD CONSTRAINT pfi_tipusmetadada_pk PRIMARY KEY (tipusmetadadaid);


--
-- TOC entry 2284 (class 2606 OID 30967)
-- Name: pfi_tipusnotificacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusnotificacio
    ADD CONSTRAINT pfi_tipusnotificacio_pk PRIMARY KEY (tipusnotificacioid);


--
-- TOC entry 2287 (class 2606 OID 30969)
-- Name: pfi_traduccio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_traduccio
    ADD CONSTRAINT pfi_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 2290 (class 2606 OID 30971)
-- Name: pfi_traducciomap_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_traducciomap
    ADD CONSTRAINT pfi_traducciomap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 2302 (class 2606 OID 30973)
-- Name: pfi_usrentitat_perentcar_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_perentcar_uk UNIQUE (usuaripersonaid, entitatid, carrec);


--
-- TOC entry 2297 (class 2606 OID 30975)
-- Name: pfi_usuariaplicacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usuariaplicacio_pk PRIMARY KEY (usuariaplicacioid);


--
-- TOC entry 2305 (class 2606 OID 30977)
-- Name: pfi_usuarientitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usuarientitat_pk PRIMARY KEY (usuarientitatid);


--
-- TOC entry 2312 (class 2606 OID 30979)
-- Name: pfi_usuarientitatfavorit_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_usuarientitatfavorit_pk PRIMARY KEY (id);


--
-- TOC entry 2320 (class 2606 OID 30981)
-- Name: pfi_usuaripersona_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_usuaripersona_pk PRIMARY KEY (usuaripersonaid);


--
-- TOC entry 2085 (class 1259 OID 30982)
-- Name: pfi_algorismedefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_algorismedefirma_pk_i ON pfi_algorismedefirma USING btree (algorismedefirmaid);


--
-- TOC entry 2086 (class 1259 OID 30983)
-- Name: pfi_annex_fitxerid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annex_fitxerid_fk_i ON pfi_annex USING btree (fitxerid);


--
-- TOC entry 2087 (class 1259 OID 30984)
-- Name: pfi_annex_petdefirmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annex_petdefirmaid_fk_i ON pfi_annex USING btree (peticiodefirmaid);


--
-- TOC entry 2090 (class 1259 OID 30985)
-- Name: pfi_annex_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annex_pk_i ON pfi_annex USING btree (annexid);


--
-- TOC entry 2091 (class 1259 OID 30986)
-- Name: pfi_annexfirmat_annexid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_annexid_fk_i ON pfi_annexfirmat USING btree (annexid);


--
-- TOC entry 2092 (class 1259 OID 30987)
-- Name: pfi_annexfirmat_firmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_firmaid_fk_i ON pfi_annexfirmat USING btree (firmaid);


--
-- TOC entry 2093 (class 1259 OID 30988)
-- Name: pfi_annexfirmat_fitxerid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_fitxerid_fk_i ON pfi_annexfirmat USING btree (fitxerid);


--
-- TOC entry 2096 (class 1259 OID 30989)
-- Name: pfi_annexfirmat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_pk_i ON pfi_annexfirmat USING btree (annexfirmatid);


--
-- TOC entry 2097 (class 1259 OID 30990)
-- Name: pfi_bitacola_peticid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_peticid_fk_i ON pfi_bitacola USING btree (peticiodefirmaid);


--
-- TOC entry 2100 (class 1259 OID 30991)
-- Name: pfi_bitacola_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_pk_i ON pfi_bitacola USING btree (bitacolaid);


--
-- TOC entry 2101 (class 1259 OID 30992)
-- Name: pfi_bitacola_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_usrentid_fk_i ON pfi_bitacola USING btree (usuarientitatid);


--
-- TOC entry 2104 (class 1259 OID 30993)
-- Name: pfi_blocdefirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_blocdefirmes_pk_i ON pfi_blocdefirmes USING btree (blocdefirmesid);


--
-- TOC entry 2105 (class 1259 OID 30994)
-- Name: pfi_blocfirmes_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_blocfirmes_fluxid_fk_i ON pfi_blocdefirmes USING btree (fluxdefirmesid);


--
-- TOC entry 2108 (class 1259 OID 30995)
-- Name: pfi_codibarres_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_codibarres_pk_i ON pfi_codibarres USING btree (codibarresid);


--
-- TOC entry 2109 (class 1259 OID 30996)
-- Name: pfi_colabdeleg_coldelid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colabdeleg_coldelid_fk_i ON pfi_colaboraciodelegacio USING btree (colaboradordelegatid);


--
-- TOC entry 2110 (class 1259 OID 30997)
-- Name: pfi_colabdeleg_destid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colabdeleg_destid_fk_i ON pfi_colaboraciodelegacio USING btree (destinatariid);


--
-- TOC entry 2111 (class 1259 OID 30998)
-- Name: pfi_colabdeleg_fitautoid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colabdeleg_fitautoid_fk_i ON pfi_colaboraciodelegacio USING btree (fitxerautoritzacioid);


--
-- TOC entry 2114 (class 1259 OID 30999)
-- Name: pfi_colaboraciodelegacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colaboraciodelegacio_pk_i ON pfi_colaboraciodelegacio USING btree (colaboraciodelegacioid);


--
-- TOC entry 2115 (class 1259 OID 31000)
-- Name: pfi_custodia_codbarrpos_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_codbarrpos_fk_i ON pfi_custodiainfo USING btree (codibarresposiciopaginaid);


--
-- TOC entry 2116 (class 1259 OID 31001)
-- Name: pfi_custodia_codibarid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_codibarid_fk_i ON pfi_custodiainfo USING btree (codibarresid);


--
-- TOC entry 2117 (class 1259 OID 31002)
-- Name: pfi_custodia_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_entitatid_fk_i ON pfi_custodiainfo USING btree (entitatid);


--
-- TOC entry 2118 (class 1259 OID 31003)
-- Name: pfi_custodia_msgpospagid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_msgpospagid_fk_i ON pfi_custodiainfo USING btree (missatgeposiciopaginaid);


--
-- TOC entry 2119 (class 1259 OID 31004)
-- Name: pfi_custodia_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_usrappid_fk_i ON pfi_custodiainfo USING btree (usuariaplicacioid);


--
-- TOC entry 2120 (class 1259 OID 31005)
-- Name: pfi_custodia_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_usrentid_fk_i ON pfi_custodiainfo USING btree (usuarientitatid);


--
-- TOC entry 2123 (class 1259 OID 31006)
-- Name: pfi_custodiainfo_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodiainfo_pk_i ON pfi_custodiainfo USING btree (custodiainfoid);


--
-- TOC entry 2124 (class 1259 OID 56575)
-- Name: pfi_entitat_algofirma_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_algofirma_fk_i ON pfi_entitat USING btree (algorismedefirmaid);


--
-- TOC entry 2125 (class 1259 OID 31007)
-- Name: pfi_entitat_faviconid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_faviconid_fk_i ON pfi_entitat USING btree (faviconid);


--
-- TOC entry 2126 (class 1259 OID 56574)
-- Name: pfi_entitat_firmatper_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_firmatper_fk_i ON pfi_entitat USING btree (firmatperformatid);


--
-- TOC entry 2127 (class 1259 OID 31008)
-- Name: pfi_entitat_logosegellid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_logosegellid_fk_i ON pfi_entitat USING btree (logosegellid);


--
-- TOC entry 2128 (class 1259 OID 31009)
-- Name: pfi_entitat_logowebid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_logowebid_fk_i ON pfi_entitat USING btree (logowebid);


--
-- TOC entry 2129 (class 1259 OID 31010)
-- Name: pfi_entitat_logowebpeuid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_logowebpeuid_fk_i ON pfi_entitat USING btree (logowebpeuid);


--
-- TOC entry 2130 (class 1259 OID 56573)
-- Name: pfi_entitat_motiudele_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_motiudele_fk_i ON pfi_entitat USING btree (motiudelegacioid);


--
-- TOC entry 2131 (class 1259 OID 31011)
-- Name: pfi_entitat_pdfautoriid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_pdfautoriid_fk_i ON pfi_entitat USING btree (pdfautoritzaciodelegacioid);


--
-- TOC entry 2134 (class 1259 OID 31012)
-- Name: pfi_entitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_pk_i ON pfi_entitat USING btree (entitatid);


--
-- TOC entry 2135 (class 1259 OID 31013)
-- Name: pfi_entitat_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_usrappid_fk_i ON pfi_entitat USING btree (usuariaplicacioid);


--
-- TOC entry 2136 (class 1259 OID 31014)
-- Name: pfi_estatdefirma_firmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatdefirma_firmaid_fk_i ON pfi_estatdefirma USING btree (firmaid);


--
-- TOC entry 2139 (class 1259 OID 31015)
-- Name: pfi_estatdefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatdefirma_pk_i ON pfi_estatdefirma USING btree (estatdefirmaid);


--
-- TOC entry 2140 (class 1259 OID 31016)
-- Name: pfi_estatfirma_coladele_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_coladele_fk_i ON pfi_estatdefirma USING btree (colaboraciodelegacioid);


--
-- TOC entry 2141 (class 1259 OID 31017)
-- Name: pfi_estatfirma_estatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_estatid_fk_i ON pfi_estatdefirma USING btree (tipusestatdefirmafinalid);


--
-- TOC entry 2142 (class 1259 OID 31018)
-- Name: pfi_estatfirma_estatinid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_estatinid_fk_i ON pfi_estatdefirma USING btree (tipusestatdefirmainicialid);


--
-- TOC entry 2143 (class 1259 OID 31019)
-- Name: pfi_estatfirma_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_usrentid_fk_i ON pfi_estatdefirma USING btree (usuarientitatid);


--
-- TOC entry 2266 (class 1259 OID 31020)
-- Name: pfi_estfirmafi_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estfirmafi_pk_i ON pfi_tipusestatdefirmafinal USING btree (tipusestatdefirmafinalid);


--
-- TOC entry 2271 (class 1259 OID 31021)
-- Name: pfi_estfirmini_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estfirmini_pk_i ON pfi_tipusestatdefirmainicial USING btree (tipusestatdefirmainicialid);


--
-- TOC entry 2274 (class 1259 OID 31022)
-- Name: pfi_estpetfirm_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estpetfirm_pk_i ON pfi_tipusestatpeticiodefirma USING btree (tipusestatpeticiodefirmaid);


--
-- TOC entry 2307 (class 1259 OID 31023)
-- Name: pfi_favorit_favoritid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_favorit_favoritid_fk_i ON pfi_usuarientitatfavorit USING btree (favoritid);


--
-- TOC entry 2308 (class 1259 OID 31024)
-- Name: pfi_favorit_origenid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_favorit_origenid_fk_i ON pfi_usuarientitatfavorit USING btree (origenid);


--
-- TOC entry 2144 (class 1259 OID 31025)
-- Name: pfi_firma_blocdefirmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_blocdefirmaid_fk_i ON pfi_firma USING btree (blocdefirmaid);


--
-- TOC entry 2145 (class 1259 OID 31026)
-- Name: pfi_firma_destinatariid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_destinatariid_fk_i ON pfi_firma USING btree (destinatariid);


--
-- TOC entry 2146 (class 1259 OID 31027)
-- Name: pfi_firma_estatfirmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_estatfirmaid_fk_i ON pfi_firma USING btree (tipusestatdefirmafinalid);


--
-- TOC entry 2147 (class 1259 OID 31028)
-- Name: pfi_firma_fitxerfirmatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_fitxerfirmatid_fk_i ON pfi_firma USING btree (fitxerfirmatid);


--
-- TOC entry 2150 (class 1259 OID 31029)
-- Name: pfi_firma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_pk_i ON pfi_firma USING btree (firmaid);


--
-- TOC entry 2153 (class 1259 OID 31030)
-- Name: pfi_fitxer_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_fitxer_pk_i ON pfi_fitxer USING btree (fitxerid);


--
-- TOC entry 2156 (class 1259 OID 31031)
-- Name: pfi_fluxdefirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_fluxdefirmes_pk_i ON pfi_fluxdefirmes USING btree (fluxdefirmesid);


--
-- TOC entry 2159 (class 1259 OID 31032)
-- Name: pfi_grupentitat_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupentitat_entitatid_fk_i ON pfi_grupentitat USING btree (entitatid);


--
-- TOC entry 2162 (class 1259 OID 31033)
-- Name: pfi_grupentitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupentitat_pk_i ON pfi_grupentitat USING btree (grupentitatid);


--
-- TOC entry 2163 (class 1259 OID 31034)
-- Name: pfi_grupusrent_grupentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupusrent_grupentid_fk_i ON pfi_grupentitatusuarientitat USING btree (grupentitatid);


--
-- TOC entry 2166 (class 1259 OID 31035)
-- Name: pfi_grupusrent_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupusrent_pk_i ON pfi_grupentitatusuarientitat USING btree (grupentitatusuarientitatid);


--
-- TOC entry 2167 (class 1259 OID 31036)
-- Name: pfi_grupusrent_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupusrent_usrentid_fk_i ON pfi_grupentitatusuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2172 (class 1259 OID 31037)
-- Name: pfi_idioma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_idioma_pk_i ON pfi_idioma USING btree (idiomaid);


--
-- TOC entry 2173 (class 1259 OID 31038)
-- Name: pfi_metadada_peticioid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_metadada_peticioid_fk_i ON pfi_metadada USING btree (peticiodefirmaid);


--
-- TOC entry 2176 (class 1259 OID 31039)
-- Name: pfi_metadada_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_metadada_pk_i ON pfi_metadada USING btree (metadadaid);


--
-- TOC entry 2177 (class 1259 OID 31040)
-- Name: pfi_metadada_tipusmetaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_metadada_tipusmetaid_fk_i ON pfi_metadada USING btree (tipusmetadadaid);


--
-- TOC entry 2322 (class 1259 OID 56443)
-- Name: pfi_modfirm_desccurtaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_modfirm_desccurtaid_fk_i ON pfi_moduldefirma USING btree (descripciocurtaid);


--
-- TOC entry 2323 (class 1259 OID 56444)
-- Name: pfi_modfirm_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_modfirm_entitatid_fk_i ON pfi_moduldefirma USING btree (entitatid);


--
-- TOC entry 2324 (class 1259 OID 56442)
-- Name: pfi_moduldefirma_nomid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_moduldefirma_nomid_fk_i ON pfi_moduldefirma USING btree (nomid);


--
-- TOC entry 2327 (class 1259 OID 56441)
-- Name: pfi_moduldefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_moduldefirma_pk_i ON pfi_moduldefirma USING btree (moduldefirmaid);


--
-- TOC entry 2330 (class 1259 OID 56445)
-- Name: pfi_modulfirmapertipusdoc_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_modulfirmapertipusdoc_pk_i ON pfi_modulfirmapertipusdoc USING btree (id);


--
-- TOC entry 2333 (class 1259 OID 56447)
-- Name: pfi_mofitido_modfirma_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_mofitido_modfirma_fk_i ON pfi_modulfirmapertipusdoc USING btree (moduldefirmaid);


--
-- TOC entry 2334 (class 1259 OID 56446)
-- Name: pfi_mofitido_tipusdoc_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_mofitido_tipusdoc_fk_i ON pfi_modulfirmapertipusdoc USING btree (tipusdocumentid);


--
-- TOC entry 2178 (class 1259 OID 31041)
-- Name: pfi_notifica_peticioid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notifica_peticioid_fk_i ON pfi_notificacio USING btree (peticiodefirmaid);


--
-- TOC entry 2179 (class 1259 OID 31042)
-- Name: pfi_notifica_tiponotiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notifica_tiponotiid_fk_i ON pfi_notificacio USING btree (tipusnotificacioid);


--
-- TOC entry 2182 (class 1259 OID 31043)
-- Name: pfi_notificacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notificacio_pk_i ON pfi_notificacio USING btree (notificacioid);


--
-- TOC entry 2183 (class 1259 OID 31044)
-- Name: pfi_permisgrpl_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisgrpl_fluxid_fk_i ON pfi_permisgrupplantilla USING btree (fluxdefirmesid);


--
-- TOC entry 2184 (class 1259 OID 31045)
-- Name: pfi_permisgrpl_grupentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisgrpl_grupentid_fk_i ON pfi_permisgrupplantilla USING btree (grupentitatid);


--
-- TOC entry 2189 (class 1259 OID 31046)
-- Name: pfi_permisgrupplantilla_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisgrupplantilla_pk_i ON pfi_permisgrupplantilla USING btree (permisgrupplantillaid);


--
-- TOC entry 2190 (class 1259 OID 31047)
-- Name: pfi_permisuspl_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisuspl_fluxid_fk_i ON pfi_permisusuariplantilla USING btree (fluxdefirmesid);


--
-- TOC entry 2191 (class 1259 OID 31048)
-- Name: pfi_permisuspl_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisuspl_usrentid_fk_i ON pfi_permisusuariplantilla USING btree (usuarientitatid);


--
-- TOC entry 2196 (class 1259 OID 31049)
-- Name: pfi_permisusuariplantilla_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisusuariplantilla_pk_i ON pfi_permisusuariplantilla USING btree (permisusuariplantillaid);


--
-- TOC entry 2314 (class 1259 OID 31050)
-- Name: pfi_persona_idiomaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_persona_idiomaid_fk_i ON pfi_usuaripersona USING btree (idiomaid);


--
-- TOC entry 2317 (class 1259 OID 31051)
-- Name: pfi_persona_rubricaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_persona_rubricaid_fk_i ON pfi_usuaripersona USING btree (rubricaid);


--
-- TOC entry 2199 (class 1259 OID 31052)
-- Name: pfi_peticiodefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_peticiodefirma_pk_i ON pfi_peticiodefirma USING btree (peticiodefirmaid);


--
-- TOC entry 2200 (class 1259 OID 31053)
-- Name: pfi_petifirma_algofirmid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_algofirmid_fk_i ON pfi_peticiodefirma USING btree (algorismedefirmaid);


--
-- TOC entry 2201 (class 1259 OID 31054)
-- Name: pfi_petifirma_custinfoid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_custinfoid_fk_i ON pfi_peticiodefirma USING btree (custodiainfoid);


--
-- TOC entry 2202 (class 1259 OID 31055)
-- Name: pfi_petifirma_estatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_estatid_fk_i ON pfi_peticiodefirma USING btree (tipusestatpeticiodefirmaid);


--
-- TOC entry 2203 (class 1259 OID 31056)
-- Name: pfi_petifirma_fitxeadaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_fitxeadaid_fk_i ON pfi_peticiodefirma USING btree (fitxeradaptatid);


--
-- TOC entry 2204 (class 1259 OID 31057)
-- Name: pfi_petifirma_fitxerid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_fitxerid_fk_i ON pfi_peticiodefirma USING btree (fitxerafirmarid);


--
-- TOC entry 2207 (class 1259 OID 31058)
-- Name: pfi_petifirma_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_fluxid_fk_i ON pfi_peticiodefirma USING btree (fluxdefirmesid);


--
-- TOC entry 2208 (class 1259 OID 31059)
-- Name: pfi_petifirma_idiomaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_idiomaid_fk_i ON pfi_peticiodefirma USING btree (idiomaid);


--
-- TOC entry 2209 (class 1259 OID 31060)
-- Name: pfi_petifirma_logosegid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_logosegid_fk_i ON pfi_peticiodefirma USING btree (logosegellid);


--
-- TOC entry 2210 (class 1259 OID 31061)
-- Name: pfi_petifirma_postaulaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_postaulaid_fk_i ON pfi_peticiodefirma USING btree (posiciotaulafirmesid);


--
-- TOC entry 2211 (class 1259 OID 31062)
-- Name: pfi_petifirma_prioritatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_prioritatid_fk_i ON pfi_peticiodefirma USING btree (prioritatid);


--
-- TOC entry 2212 (class 1259 OID 31063)
-- Name: pfi_petifirma_tipofirmid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_tipofirmid_fk_i ON pfi_peticiodefirma USING btree (tipusfirmaid);


--
-- TOC entry 2213 (class 1259 OID 31064)
-- Name: pfi_petifirma_tipusdocid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_tipusdocid_fk_i ON pfi_peticiodefirma USING btree (tipusdocumentid);


--
-- TOC entry 2214 (class 1259 OID 31065)
-- Name: pfi_petifirma_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_usrappid_fk_i ON pfi_peticiodefirma USING btree (usuariaplicacioid);


--
-- TOC entry 2215 (class 1259 OID 31066)
-- Name: pfi_petifirma_usrentiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_usrentiid_fk_i ON pfi_peticiodefirma USING btree (usuarientitatid);


--
-- TOC entry 2216 (class 1259 OID 31067)
-- Name: pfi_plantiflfi_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plantiflfi_usrappid_fk_i ON pfi_plantillafluxdefirmes USING btree (usuariaplicacioid);


--
-- TOC entry 2217 (class 1259 OID 31068)
-- Name: pfi_plantiflfi_usrentiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plantiflfi_usrentiid_fk_i ON pfi_plantillafluxdefirmes USING btree (usuarientitatid);


--
-- TOC entry 2220 (class 1259 OID 31069)
-- Name: pfi_plantillafluxdefirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plantillafluxdefirmes_pk_i ON pfi_plantillafluxdefirmes USING btree (fluxdefirmesid);


--
-- TOC entry 2223 (class 1259 OID 31070)
-- Name: pfi_posiciopagina_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_posiciopagina_pk_i ON pfi_posiciopagina USING btree (posiciopaginaid);


--
-- TOC entry 2226 (class 1259 OID 31071)
-- Name: pfi_posiciotaulafirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_posiciotaulafirmes_pk_i ON pfi_posiciotaulafirmes USING btree (posiciotaulafirmesid);


--
-- TOC entry 2229 (class 1259 OID 31072)
-- Name: pfi_prioritat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_prioritat_pk_i ON pfi_prioritat USING btree (prioritatid);


--
-- TOC entry 2232 (class 1259 OID 31073)
-- Name: pfi_rebreavis_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_rebreavis_pk_i ON pfi_rebreavis USING btree (id);


--
-- TOC entry 2233 (class 1259 OID 31074)
-- Name: pfi_rebreavis_tiponotiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_rebreavis_tiponotiid_fk_i ON pfi_rebreavis USING btree (tipusnotificacioid);


--
-- TOC entry 2236 (class 1259 OID 31075)
-- Name: pfi_rebreavis_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_rebreavis_usrentid_fk_i ON pfi_rebreavis USING btree (usuarientitatid);


--
-- TOC entry 2239 (class 1259 OID 31076)
-- Name: pfi_role_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_role_pk_i ON pfi_role USING btree (roleid);


--
-- TOC entry 2242 (class 1259 OID 31077)
-- Name: pfi_roleusrapp_roleid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrapp_roleid_fk_i ON pfi_roleusuariaplicacio USING btree (roleid);


--
-- TOC entry 2243 (class 1259 OID 31078)
-- Name: pfi_roleusrapp_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrapp_usrappid_fk_i ON pfi_roleusuariaplicacio USING btree (usuariaplicacioid);


--
-- TOC entry 2247 (class 1259 OID 31079)
-- Name: pfi_roleusrent_roleid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrent_roleid_fk_i ON pfi_roleusuarientitat USING btree (roleid);


--
-- TOC entry 2250 (class 1259 OID 31080)
-- Name: pfi_roleusrent_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrent_usrentid_fk_i ON pfi_roleusuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2246 (class 1259 OID 31081)
-- Name: pfi_roleusuariaplicacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusuariaplicacio_pk_i ON pfi_roleusuariaplicacio USING btree (id);


--
-- TOC entry 2253 (class 1259 OID 31082)
-- Name: pfi_roleusuarientitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusuarientitat_pk_i ON pfi_roleusuarientitat USING btree (id);


--
-- TOC entry 2254 (class 1259 OID 31083)
-- Name: pfi_tipusdoc_usuariappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdoc_usuariappid_fk_i ON pfi_tipusdocument USING btree (usuariaplicacioid);


--
-- TOC entry 2261 (class 1259 OID 31084)
-- Name: pfi_tipusdoccd_coldelid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdoccd_coldelid_fk_i ON pfi_tipusdocumentcoladele USING btree (colaboraciodelegacioid);


--
-- TOC entry 2262 (class 1259 OID 31085)
-- Name: pfi_tipusdoccd_tipusdocid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdoccd_tipusdocid_fk_i ON pfi_tipusdocumentcoladele USING btree (tipusdocumentid);


--
-- TOC entry 2255 (class 1259 OID 31086)
-- Name: pfi_tipusdocument_nom_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdocument_nom_fk_i ON pfi_tipusdocument USING btree (nom);


--
-- TOC entry 2258 (class 1259 OID 31087)
-- Name: pfi_tipusdocument_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdocument_pk_i ON pfi_tipusdocument USING btree (tipusdocumentid);


--
-- TOC entry 2265 (class 1259 OID 31088)
-- Name: pfi_tipusdocumentcoladele_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdocumentcoladele_pk_i ON pfi_tipusdocumentcoladele USING btree (id);


--
-- TOC entry 2277 (class 1259 OID 31089)
-- Name: pfi_tipusfirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusfirma_pk_i ON pfi_tipusfirma USING btree (tipusfirmaid);


--
-- TOC entry 2282 (class 1259 OID 31090)
-- Name: pfi_tipusmetadada_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusmetadada_pk_i ON pfi_tipusmetadada USING btree (tipusmetadadaid);


--
-- TOC entry 2285 (class 1259 OID 31091)
-- Name: pfi_tipusnotificacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusnotificacio_pk_i ON pfi_tipusnotificacio USING btree (tipusnotificacioid);


--
-- TOC entry 2288 (class 1259 OID 31092)
-- Name: pfi_traduccio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_traduccio_pk_i ON pfi_traduccio USING btree (traduccioid);


--
-- TOC entry 2291 (class 1259 OID 31093)
-- Name: pfi_traducmap_idiomaid_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_traducmap_idiomaid_pk_i ON pfi_traducciomap USING btree (traducciomapid);


--
-- TOC entry 2292 (class 1259 OID 31094)
-- Name: pfi_traducmap_tradmapid_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_traducmap_tradmapid_pk_i ON pfi_traducciomap USING btree (traducciomapid);


--
-- TOC entry 2293 (class 1259 OID 31095)
-- Name: pfi_usrapp_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_entitatid_fk_i ON pfi_usuariaplicacio USING btree (entitatid);


--
-- TOC entry 2294 (class 1259 OID 31096)
-- Name: pfi_usrapp_idiomaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_idiomaid_fk_i ON pfi_usuariaplicacio USING btree (idiomaid);


--
-- TOC entry 2295 (class 1259 OID 31097)
-- Name: pfi_usrapp_logosegellid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_logosegellid_fk_i ON pfi_usuariaplicacio USING btree (logosegellid);


--
-- TOC entry 2299 (class 1259 OID 31098)
-- Name: pfi_usrentitat_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_entitatid_fk_i ON pfi_usuarientitat USING btree (entitatid);


--
-- TOC entry 2300 (class 1259 OID 31099)
-- Name: pfi_usrentitat_logosegid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_logosegid_fk_i ON pfi_usuarientitat USING btree (logosegellid);


--
-- TOC entry 2303 (class 1259 OID 31100)
-- Name: pfi_usrentitat_personaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_personaid_fk_i ON pfi_usuarientitat USING btree (usuaripersonaid);


--
-- TOC entry 2298 (class 1259 OID 31101)
-- Name: pfi_usuariaplicacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuariaplicacio_pk_i ON pfi_usuariaplicacio USING btree (usuariaplicacioid);


--
-- TOC entry 2306 (class 1259 OID 31102)
-- Name: pfi_usuarientitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuarientitat_pk_i ON pfi_usuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2313 (class 1259 OID 31103)
-- Name: pfi_usuarientitatfavorit_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuarientitatfavorit_pk_i ON pfi_usuarientitatfavorit USING btree (id);


--
-- TOC entry 2318 (class 1259 OID 31104)
-- Name: pfi_usuaripersona_nif_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuaripersona_nif_i ON pfi_usuaripersona USING btree (nif);


--
-- TOC entry 2321 (class 1259 OID 31105)
-- Name: pfi_usuaripersona_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuaripersona_pk_i ON pfi_usuaripersona USING btree (usuaripersonaid);


--
-- TOC entry 2337 (class 2606 OID 31106)
-- Name: pfi_anexfirmat_annex_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT pfi_anexfirmat_annex_fk FOREIGN KEY (annexid) REFERENCES pfi_annex(annexid);


--
-- TOC entry 2338 (class 2606 OID 31111)
-- Name: pfi_anexfirmat_firma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT pfi_anexfirmat_firma_fk FOREIGN KEY (firmaid) REFERENCES pfi_firma(firmaid);


--
-- TOC entry 2339 (class 2606 OID 31116)
-- Name: pfi_anexfirmat_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT pfi_anexfirmat_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2335 (class 2606 OID 31121)
-- Name: pfi_annex_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annex
    ADD CONSTRAINT pfi_annex_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2336 (class 2606 OID 31126)
-- Name: pfi_annex_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annex
    ADD CONSTRAINT pfi_annex_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2340 (class 2606 OID 31131)
-- Name: pfi_bitacola_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_bitacola
    ADD CONSTRAINT pfi_bitacola_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2341 (class 2606 OID 31136)
-- Name: pfi_bitacola_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_bitacola
    ADD CONSTRAINT pfi_bitacola_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2342 (class 2606 OID 31141)
-- Name: pfi_blocfirmes_fluxfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_blocdefirmes
    ADD CONSTRAINT pfi_blocfirmes_fluxfirmes_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_fluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2343 (class 2606 OID 31146)
-- Name: pfi_colabdeleg_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colabdeleg_fitxer_fk FOREIGN KEY (fitxerautoritzacioid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2344 (class 2606 OID 31151)
-- Name: pfi_colabdeleg_usrentitat_c_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colabdeleg_usrentitat_c_fk FOREIGN KEY (colaboradordelegatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2345 (class 2606 OID 31156)
-- Name: pfi_colabdeleg_usrentitat_d_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colabdeleg_usrentitat_d_fk FOREIGN KEY (destinatariid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2346 (class 2606 OID 31161)
-- Name: pfi_custodia_codibarres_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_codibarres_fk FOREIGN KEY (codibarresid) REFERENCES pfi_codibarres(codibarresid);


--
-- TOC entry 2347 (class 2606 OID 31166)
-- Name: pfi_custodia_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2348 (class 2606 OID 31171)
-- Name: pfi_custodia_pospagina_bar_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_pospagina_bar_fk FOREIGN KEY (codibarresposiciopaginaid) REFERENCES pfi_posiciopagina(posiciopaginaid);


--
-- TOC entry 2349 (class 2606 OID 31176)
-- Name: pfi_custodia_pospagina_msg_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_pospagina_msg_fk FOREIGN KEY (missatgeposiciopaginaid) REFERENCES pfi_posiciopagina(posiciopaginaid);


--
-- TOC entry 2350 (class 2606 OID 31181)
-- Name: pfi_custodia_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2351 (class 2606 OID 31186)
-- Name: pfi_custodia_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2352 (class 2606 OID 56558)
-- Name: pfi_entitat_algofirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_algofirma_fk FOREIGN KEY (algorismedefirmaid) REFERENCES pfi_algorismedefirma(algorismedefirmaid);


--
-- TOC entry 2355 (class 2606 OID 31191)
-- Name: pfi_entitat_fitxer_icon_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_icon_fk FOREIGN KEY (faviconid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2356 (class 2606 OID 31196)
-- Name: pfi_entitat_fitxer_loca_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_loca_fk FOREIGN KEY (logowebid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2357 (class 2606 OID 31201)
-- Name: pfi_entitat_fitxer_lope_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_lope_fk FOREIGN KEY (logowebpeuid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2358 (class 2606 OID 31206)
-- Name: pfi_entitat_fitxer_lose_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_lose_fk FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2359 (class 2606 OID 31211)
-- Name: pfi_entitat_fitxer_pdfd_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_pdfd_fk FOREIGN KEY (pdfautoritzaciodelegacioid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2353 (class 2606 OID 56563)
-- Name: pfi_entitat_traduccio_firm_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_traduccio_firm_fk FOREIGN KEY (firmatperformatid) REFERENCES pfi_traduccio(traduccioid);


--
-- TOC entry 2354 (class 2606 OID 56568)
-- Name: pfi_entitat_traduccio_moti_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_traduccio_moti_fk FOREIGN KEY (motiudelegacioid) REFERENCES pfi_traduccio(traduccioid);


--
-- TOC entry 2360 (class 2606 OID 31216)
-- Name: pfi_entitat_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2361 (class 2606 OID 31221)
-- Name: pfi_estatfirma_colabdeleg_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_colabdeleg_fk FOREIGN KEY (colaboraciodelegacioid) REFERENCES pfi_colaboraciodelegacio(colaboraciodelegacioid);


--
-- TOC entry 2362 (class 2606 OID 31226)
-- Name: pfi_estatfirma_estfirmafi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_estfirmafi_fk FOREIGN KEY (tipusestatdefirmafinalid) REFERENCES pfi_tipusestatdefirmafinal(tipusestatdefirmafinalid);


--
-- TOC entry 2363 (class 2606 OID 31231)
-- Name: pfi_estatfirma_estfirmini_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_estfirmini_fk FOREIGN KEY (tipusestatdefirmainicialid) REFERENCES pfi_tipusestatdefirmainicial(tipusestatdefirmainicialid);


--
-- TOC entry 2364 (class 2606 OID 31236)
-- Name: pfi_estatfirma_firma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_firma_fk FOREIGN KEY (firmaid) REFERENCES pfi_firma(firmaid);


--
-- TOC entry 2365 (class 2606 OID 31241)
-- Name: pfi_estatfirma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2415 (class 2606 OID 31246)
-- Name: pfi_favorit_usrentitat_fav_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_favorit_usrentitat_fav_fk FOREIGN KEY (favoritid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2416 (class 2606 OID 31251)
-- Name: pfi_favorit_usrentitat_ori_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_favorit_usrentitat_ori_fk FOREIGN KEY (origenid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2366 (class 2606 OID 31256)
-- Name: pfi_firma_blocfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_blocfirmes_fk FOREIGN KEY (blocdefirmaid) REFERENCES pfi_blocdefirmes(blocdefirmesid);


--
-- TOC entry 2367 (class 2606 OID 31261)
-- Name: pfi_firma_estfirmafi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_estfirmafi_fk FOREIGN KEY (tipusestatdefirmafinalid) REFERENCES pfi_tipusestatdefirmafinal(tipusestatdefirmafinalid);


--
-- TOC entry 2368 (class 2606 OID 31266)
-- Name: pfi_firma_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_fitxer_fk FOREIGN KEY (fitxerfirmatid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2369 (class 2606 OID 31271)
-- Name: pfi_firma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_usrentitat_fk FOREIGN KEY (destinatariid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2370 (class 2606 OID 31276)
-- Name: pfi_grupentita_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_grupentitat
    ADD CONSTRAINT pfi_grupentita_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2371 (class 2606 OID 31281)
-- Name: pfi_grupusrent_grupentita_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_grupentita_fk FOREIGN KEY (grupentitatid) REFERENCES pfi_grupentitat(grupentitatid);


--
-- TOC entry 2372 (class 2606 OID 31286)
-- Name: pfi_grupusrent_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2373 (class 2606 OID 31291)
-- Name: pfi_metadada_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_metadada
    ADD CONSTRAINT pfi_metadada_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2374 (class 2606 OID 31296)
-- Name: pfi_metadada_tipmetada_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_metadada
    ADD CONSTRAINT pfi_metadada_tipmetada_fk FOREIGN KEY (tipusmetadadaid) REFERENCES pfi_tipusmetadada(tipusmetadadaid);


--
-- TOC entry 2421 (class 2606 OID 56401)
-- Name: pfi_modfirm_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_moduldefirma
    ADD CONSTRAINT pfi_modfirm_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2419 (class 2606 OID 56434)
-- Name: pfi_modfirm_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_moduldefirma
    ADD CONSTRAINT pfi_modfirm_traduccio_desc_fk FOREIGN KEY (descripciocurtaid) REFERENCES pfi_traduccio(traduccioid);


--
-- TOC entry 2420 (class 2606 OID 56429)
-- Name: pfi_modfirm_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_moduldefirma
    ADD CONSTRAINT pfi_modfirm_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES pfi_traduccio(traduccioid);


--
-- TOC entry 2422 (class 2606 OID 56419)
-- Name: pfi_mofitido_modfirm_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_modfirm_fk FOREIGN KEY (moduldefirmaid) REFERENCES pfi_moduldefirma(moduldefirmaid);


--
-- TOC entry 2423 (class 2606 OID 56424)
-- Name: pfi_mofitido_tipusdoc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES pfi_tipusdocument(tipusdocumentid);


--
-- TOC entry 2375 (class 2606 OID 31301)
-- Name: pfi_notifica_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_notificacio
    ADD CONSTRAINT pfi_notifica_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2376 (class 2606 OID 31306)
-- Name: pfi_notifica_tipnotific_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_notificacio
    ADD CONSTRAINT pfi_notifica_tipnotific_fk FOREIGN KEY (tipusnotificacioid) REFERENCES pfi_tipusnotificacio(tipusnotificacioid);


--
-- TOC entry 2377 (class 2606 OID 31311)
-- Name: pfi_permisgrpl_grupentita_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrpl_grupentita_fk FOREIGN KEY (grupentitatid) REFERENCES pfi_grupentitat(grupentitatid);


--
-- TOC entry 2378 (class 2606 OID 31316)
-- Name: pfi_permisgrpl_plantiflfi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrpl_plantiflfi_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_plantillafluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2379 (class 2606 OID 31321)
-- Name: pfi_permisuspl_plantiflfi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisuspl_plantiflfi_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_plantillafluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2380 (class 2606 OID 31326)
-- Name: pfi_permisuspl_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisuspl_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2417 (class 2606 OID 31331)
-- Name: pfi_persona_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_persona_fitxer_fk FOREIGN KEY (rubricaid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2418 (class 2606 OID 31336)
-- Name: pfi_persona_idioma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_persona_idioma_fk FOREIGN KEY (idiomaid) REFERENCES pfi_idioma(idiomaid);


--
-- TOC entry 2381 (class 2606 OID 31341)
-- Name: pfi_petifirma_algofirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_algofirma_fk FOREIGN KEY (algorismedefirmaid) REFERENCES pfi_algorismedefirma(algorismedefirmaid);


--
-- TOC entry 2382 (class 2606 OID 31346)
-- Name: pfi_petifirma_custodia_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_custodia_fk FOREIGN KEY (custodiainfoid) REFERENCES pfi_custodiainfo(custodiainfoid);


--
-- TOC entry 2383 (class 2606 OID 31351)
-- Name: pfi_petifirma_estpetfirm_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_estpetfirm_fk FOREIGN KEY (tipusestatpeticiodefirmaid) REFERENCES pfi_tipusestatpeticiodefirma(tipusestatpeticiodefirmaid);


--
-- TOC entry 2384 (class 2606 OID 31356)
-- Name: pfi_petifirma_fitxer_ada_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_ada_fk FOREIGN KEY (fitxeradaptatid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2385 (class 2606 OID 31361)
-- Name: pfi_petifirma_fitxer_fir_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_fir_fk FOREIGN KEY (fitxerafirmarid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2386 (class 2606 OID 31366)
-- Name: pfi_petifirma_fitxer_log_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_log_fk FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2387 (class 2606 OID 31371)
-- Name: pfi_petifirma_fluxfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fluxfirmes_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_fluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2388 (class 2606 OID 31376)
-- Name: pfi_petifirma_idioma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_idioma_fk FOREIGN KEY (idiomaid) REFERENCES pfi_idioma(idiomaid);


--
-- TOC entry 2389 (class 2606 OID 31381)
-- Name: pfi_petifirma_postaufir_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_postaufir_fk FOREIGN KEY (posiciotaulafirmesid) REFERENCES pfi_posiciotaulafirmes(posiciotaulafirmesid);


--
-- TOC entry 2390 (class 2606 OID 31386)
-- Name: pfi_petifirma_prioritat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_prioritat_fk FOREIGN KEY (prioritatid) REFERENCES pfi_prioritat(prioritatid);


--
-- TOC entry 2391 (class 2606 OID 31391)
-- Name: pfi_petifirma_tipusdoc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES pfi_tipusdocument(tipusdocumentid);


--
-- TOC entry 2392 (class 2606 OID 31396)
-- Name: pfi_petifirma_tipusfirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_tipusfirma_fk FOREIGN KEY (tipusfirmaid) REFERENCES pfi_tipusfirma(tipusfirmaid);


--
-- TOC entry 2393 (class 2606 OID 31401)
-- Name: pfi_petifirma_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2394 (class 2606 OID 31406)
-- Name: pfi_petifirma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2395 (class 2606 OID 31411)
-- Name: pfi_plantiflfi_fluxfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantiflfi_fluxfirmes_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_fluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2396 (class 2606 OID 31416)
-- Name: pfi_plantiflfi_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantiflfi_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2397 (class 2606 OID 31421)
-- Name: pfi_plantiflfi_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantiflfi_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2398 (class 2606 OID 31426)
-- Name: pfi_rebreavis_tipnotific_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_tipnotific_fk FOREIGN KEY (tipusnotificacioid) REFERENCES pfi_tipusnotificacio(tipusnotificacioid);


--
-- TOC entry 2399 (class 2606 OID 31431)
-- Name: pfi_rebreavis_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2400 (class 2606 OID 31436)
-- Name: pfi_roleusrapp_role_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusrapp_role_fk FOREIGN KEY (roleid) REFERENCES pfi_role(roleid);


--
-- TOC entry 2401 (class 2606 OID 31441)
-- Name: pfi_roleusrapp_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusrapp_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2402 (class 2606 OID 31446)
-- Name: pfi_roleusrent_role_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusrent_role_fk FOREIGN KEY (roleid) REFERENCES pfi_role(roleid);


--
-- TOC entry 2403 (class 2606 OID 31451)
-- Name: pfi_roleusrent_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusrent_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2404 (class 2606 OID 31456)
-- Name: pfi_tipusdoc_traduccio_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdoc_traduccio_fk FOREIGN KEY (nom) REFERENCES pfi_traduccio(traduccioid);


--
-- TOC entry 2405 (class 2606 OID 31461)
-- Name: pfi_tipusdoc_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdoc_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2406 (class 2606 OID 31466)
-- Name: pfi_tipusdoccd_colabdeleg_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdoccd_colabdeleg_fk FOREIGN KEY (colaboraciodelegacioid) REFERENCES pfi_colaboraciodelegacio(colaboraciodelegacioid);


--
-- TOC entry 2407 (class 2606 OID 31471)
-- Name: pfi_tipusdoccd_tipusdoc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdoccd_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES pfi_tipusdocument(tipusdocumentid);


--
-- TOC entry 2408 (class 2606 OID 31476)
-- Name: pfi_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_traducciomap
    ADD CONSTRAINT pfi_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES pfi_traduccio(traduccioid);


--
-- TOC entry 2409 (class 2606 OID 31481)
-- Name: pfi_usrapp_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2410 (class 2606 OID 31486)
-- Name: pfi_usrapp_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_fitxer_fk FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2411 (class 2606 OID 31491)
-- Name: pfi_usrapp_idioma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_idioma_fk FOREIGN KEY (idiomaid) REFERENCES pfi_idioma(idiomaid);


--
-- TOC entry 2412 (class 2606 OID 31496)
-- Name: pfi_usrentitat_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2413 (class 2606 OID 31501)
-- Name: pfi_usrentitat_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_fitxer_fk FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2414 (class 2606 OID 31506)
-- Name: pfi_usrentitat_persona_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_persona_fk FOREIGN KEY (usuaripersonaid) REFERENCES pfi_usuaripersona(usuaripersonaid);


--
-- TOC entry 2536 (class 0 OID 0)
-- Dependencies: 6
-- Name: portafib; Type: ACL; Schema: -; Owner: portafib
--

REVOKE ALL ON SCHEMA portafib FROM PUBLIC;
REVOKE ALL ON SCHEMA portafib FROM portafib;
GRANT ALL ON SCHEMA portafib TO portafib;


--
-- TOC entry 2538 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-11-13 11:31:48

--
-- PostgreSQL database dump complete
--

