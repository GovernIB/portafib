--
-- PostgreSQL database dump
--

-- Dumped from database version 8.4.14
-- Dumped by pg_dump version 9.3.1
-- Started on 2014-09-04 12:01:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 7 (class 2615 OID 68319)
-- Name: portafib; Type: SCHEMA; Schema: -; Owner: portafib
--

CREATE SCHEMA portafib;


ALTER SCHEMA portafib OWNER TO portafib;

SET search_path = portafib, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 199935)
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
-- TOC entry 141 (class 1259 OID 69468)
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
-- TOC entry 142 (class 1259 OID 69470)
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
-- TOC entry 143 (class 1259 OID 69479)
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
-- TOC entry 144 (class 1259 OID 69496)
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
-- TOC entry 145 (class 1259 OID 69502)
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
-- TOC entry 180 (class 1259 OID 191664)
-- Name: pfi_codibarres; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_codibarres (
    codibarresid character varying(255) NOT NULL,
    nom character varying(50) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE portafib.pfi_codibarres OWNER TO portafib;

--
-- TOC entry 146 (class 1259 OID 69508)
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
-- TOC entry 2342 (class 0 OID 0)
-- Dependencies: 146
-- Name: COLUMN pfi_colaboraciodelegacio.revisor; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_colaboraciodelegacio.revisor IS 'Només es per col.laborador i indica si es obligatori que aquell col.laborador digui la seva.';


--
-- TOC entry 178 (class 1259 OID 191641)
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
-- TOC entry 147 (class 1259 OID 69519)
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
    policyurldocument character varying(255)
);


ALTER TABLE portafib.pfi_entitat OWNER TO portafib;

--
-- TOC entry 148 (class 1259 OID 69529)
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
-- TOC entry 149 (class 1259 OID 69535)
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
-- TOC entry 2343 (class 0 OID 0)
-- Dependencies: 149
-- Name: COLUMN pfi_firma.destinatariid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_firma.destinatariid IS 'Si val null significa que s''ha de substituir pel Sol·licitant de la petició (només podrà valer null en plantilles de flux de firmes)';


--
-- TOC entry 2344 (class 0 OID 0)
-- Dependencies: 149
-- Name: COLUMN pfi_firma.mostrarrubrica; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_firma.mostrarrubrica IS 'Dibuixar la firma(rubrica) de l''usuari persona si s''ha definit la caixa';


--
-- TOC entry 150 (class 1259 OID 69544)
-- Name: pfi_fitxer; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_fitxer (
    fitxerid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(45) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE portafib.pfi_fitxer OWNER TO portafib;

--
-- TOC entry 151 (class 1259 OID 69554)
-- Name: pfi_fluxdefirmes; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_fluxdefirmes (
    fluxdefirmesid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_fluxdefirmes OWNER TO portafib;

--
-- TOC entry 174 (class 1259 OID 191429)
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
-- TOC entry 176 (class 1259 OID 191454)
-- Name: pfi_grupentitatusuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_grupentitatusuarientitat (
    grupentitatusuarientitatid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    grupentitatid bigint NOT NULL
);


ALTER TABLE portafib.pfi_grupentitatusuarientitat OWNER TO portafib;

--
-- TOC entry 169 (class 1259 OID 92635)
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
-- TOC entry 152 (class 1259 OID 69564)
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
-- TOC entry 153 (class 1259 OID 69583)
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
-- TOC entry 175 (class 1259 OID 191434)
-- Name: pfi_permisgrupplantilla; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_permisgrupplantilla (
    permisgrupplantillaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    grupentitatid bigint NOT NULL,
    fluxdefirmesid bigint NOT NULL
);


ALTER TABLE portafib.pfi_permisgrupplantilla OWNER TO portafib;

--
-- TOC entry 177 (class 1259 OID 191465)
-- Name: pfi_permisusuariplantilla; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_permisusuariplantilla (
    permisusuariplantillaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    fluxdefirmesid bigint NOT NULL
);


ALTER TABLE portafib.pfi_permisusuariplantilla OWNER TO portafib;

--
-- TOC entry 154 (class 1259 OID 69592)
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
-- TOC entry 2345 (class 0 OID 0)
-- Dependencies: 154
-- Name: COLUMN pfi_peticiodefirma.posiciotaulafirmesid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_peticiodefirma.posiciotaulafirmesid IS 'Posicio taula de firmes: primera o darrera pàgina';


--
-- TOC entry 168 (class 1259 OID 92610)
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
-- TOC entry 179 (class 1259 OID 191661)
-- Name: pfi_posiciopagina; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_posiciopagina (
    posiciopaginaid bigint NOT NULL,
    nom character varying(255) NOT NULL
);


ALTER TABLE portafib.pfi_posiciopagina OWNER TO portafib;

--
-- TOC entry 170 (class 1259 OID 109038)
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
-- TOC entry 171 (class 1259 OID 109105)
-- Name: pfi_prioritat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_prioritat (
    prioritatid integer NOT NULL,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_prioritat OWNER TO portafib;

--
-- TOC entry 155 (class 1259 OID 69611)
-- Name: pfi_rebreavis; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_rebreavis (
    tipusnotificacioid bigint NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_rebreavis OWNER TO portafib;

--
-- TOC entry 156 (class 1259 OID 69626)
-- Name: pfi_role; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_role (
    roleid character varying(50) NOT NULL,
    nom character varying(50) DEFAULT NULL::character varying NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying
);


ALTER TABLE portafib.pfi_role OWNER TO portafib;

--
-- TOC entry 158 (class 1259 OID 69647)
-- Name: pfi_roleusuariaplicacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_roleusuariaplicacio (
    usuariaplicacioid character varying(50) NOT NULL,
    roleid character varying(50) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_roleusuariaplicacio OWNER TO portafib;

--
-- TOC entry 157 (class 1259 OID 69638)
-- Name: pfi_roleusuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_roleusuarientitat (
    roleid character varying(50) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_roleusuarientitat OWNER TO portafib;

--
-- TOC entry 159 (class 1259 OID 69652)
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
-- TOC entry 160 (class 1259 OID 69661)
-- Name: pfi_tipusdocumentcoladele; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusdocumentcoladele (
    colaboraciodelegacioid bigint NOT NULL,
    tipusdocumentid bigint NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_tipusdocumentcoladele OWNER TO portafib;

--
-- TOC entry 173 (class 1259 OID 117254)
-- Name: pfi_tipusestatdefirmafinal; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusestatdefirmafinal (
    tipusestatdefirmafinalid bigint NOT NULL,
    nom character varying(50) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE portafib.pfi_tipusestatdefirmafinal OWNER TO portafib;

--
-- TOC entry 161 (class 1259 OID 69666)
-- Name: pfi_tipusestatdefirmainicial; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusestatdefirmainicial (
    tipusestatdefirmainicialid bigint NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_tipusestatdefirmainicial OWNER TO portafib;

--
-- TOC entry 162 (class 1259 OID 69672)
-- Name: pfi_tipusestatpeticiodefirma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusestatpeticiodefirma (
    tipusestatpeticiodefirmaid bigint NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_tipusestatpeticiodefirma OWNER TO portafib;

--
-- TOC entry 163 (class 1259 OID 69681)
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
-- TOC entry 182 (class 1259 OID 199953)
-- Name: pfi_tipusmetadada; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusmetadada (
    tipusmetadadaid integer NOT NULL,
    nom character varying(100) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE portafib.pfi_tipusmetadada OWNER TO portafib;

--
-- TOC entry 164 (class 1259 OID 69690)
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
-- TOC entry 2346 (class 0 OID 0)
-- Dependencies: 164
-- Name: COLUMN pfi_tipusnotificacio.esavis; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_tipusnotificacio.esavis IS 'Si es avis val true
Si és notificació es false
Si pot ser avis i notificació llavors val null
';


--
-- TOC entry 184 (class 1259 OID 210385)
-- Name: pfi_traduccio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_traduccio (
    traduccioid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_traduccio OWNER TO portafib;

--
-- TOC entry 183 (class 1259 OID 210326)
-- Name: pfi_traducciomap; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(5) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE portafib.pfi_traducciomap OWNER TO portafib;

--
-- TOC entry 167 (class 1259 OID 69715)
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
-- TOC entry 2347 (class 0 OID 0)
-- Dependencies: 167
-- Name: TABLE pfi_usuariaplicacio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON TABLE pfi_usuariaplicacio IS 'Usuari de tipus màquina que realitzarà peticions a PortaFIB';


--
-- TOC entry 2348 (class 0 OID 0)
-- Dependencies: 167
-- Name: COLUMN pfi_usuariaplicacio.emailadmin; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuariaplicacio.emailadmin IS 'Correu de la persona encarregada d''aquest usuari-Màquina';


--
-- TOC entry 2349 (class 0 OID 0)
-- Dependencies: 167
-- Name: COLUMN pfi_usuariaplicacio.callbackurl; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuariaplicacio.callbackurl IS 'Adreça on esta implementat el servei de recepció de notificacions associades a les peticions de firma realitzades per aquest usuari-màquina';


--
-- TOC entry 2350 (class 0 OID 0)
-- Dependencies: 167
-- Name: COLUMN pfi_usuariaplicacio.callbackversio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuariaplicacio.callbackversio IS 'La versió 1 és la compatible amb INDRA i la versió 2 és l''especifica del nou Portafirmes';


--
-- TOC entry 166 (class 1259 OID 69706)
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
-- TOC entry 172 (class 1259 OID 109116)
-- Name: pfi_usuarientitatfavorit; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_usuarientitatfavorit (
    origenid character varying(101) NOT NULL,
    favoritid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_usuarientitatfavorit OWNER TO portafib;

--
-- TOC entry 165 (class 1259 OID 69699)
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
-- TOC entry 2351 (class 0 OID 0)
-- Dependencies: 165
-- Name: COLUMN pfi_usuaripersona.rubricaid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuaripersona.rubricaid IS 'és la firma gràfica de la persona';


--
-- TOC entry 2154 (class 2606 OID 202144)
-- Name: pfi_algofirma_nom_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_algorismedefirma
    ADD CONSTRAINT pfi_algofirma_nom_uk UNIQUE (nom);


--
-- TOC entry 2156 (class 2606 OID 199939)
-- Name: pfi_algorismedefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_algorismedefirma
    ADD CONSTRAINT pfi_algorismedefirma_pk PRIMARY KEY (algorismedefirmaid);


--
-- TOC entry 1935 (class 2606 OID 70224)
-- Name: pfi_annex_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_annex
    ADD CONSTRAINT pfi_annex_pk PRIMARY KEY (annexid);


--
-- TOC entry 1941 (class 2606 OID 70241)
-- Name: pfi_annexfirmat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT pfi_annexfirmat_pk PRIMARY KEY (annexfirmatid);


--
-- TOC entry 1945 (class 2606 OID 70263)
-- Name: pfi_bitacola_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_bitacola
    ADD CONSTRAINT pfi_bitacola_pk PRIMARY KEY (bitacolaid);


--
-- TOC entry 1949 (class 2606 OID 70272)
-- Name: pfi_blocdefirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_blocdefirmes
    ADD CONSTRAINT pfi_blocdefirmes_pk PRIMARY KEY (blocdefirmesid);


--
-- TOC entry 2151 (class 2606 OID 191671)
-- Name: pfi_codibarres_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_codibarres
    ADD CONSTRAINT pfi_codibarres_pk PRIMARY KEY (codibarresid);


--
-- TOC entry 1956 (class 2606 OID 70285)
-- Name: pfi_colaboraciodelegacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colaboraciodelegacio_pk PRIMARY KEY (colaboraciodelegacioid);


--
-- TOC entry 2145 (class 2606 OID 191660)
-- Name: pfi_custodiainfo_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodiainfo_pk PRIMARY KEY (custodiainfoid);


--
-- TOC entry 1964 (class 2606 OID 69528)
-- Name: pfi_entitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_pk PRIMARY KEY (entitatid);


--
-- TOC entry 1969 (class 2606 OID 69534)
-- Name: pfi_estatdefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatdefirma_pk PRIMARY KEY (estatdefirmaid);


--
-- TOC entry 2054 (class 2606 OID 69671)
-- Name: pfi_estfirmini_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusestatdefirmainicial
    ADD CONSTRAINT pfi_estfirmini_pk PRIMARY KEY (tipusestatdefirmainicialid);


--
-- TOC entry 2057 (class 2606 OID 69680)
-- Name: pfi_estpetfirm_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusestatpeticiodefirma
    ADD CONSTRAINT pfi_estpetfirm_pk PRIMARY KEY (tipusestatpeticiodefirmaid);


--
-- TOC entry 2104 (class 2606 OID 202119)
-- Name: pfi_favorit_origfavo_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_favorit_origfavo_uk UNIQUE (origenid, favoritid);


--
-- TOC entry 1980 (class 2606 OID 70303)
-- Name: pfi_firma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_pk PRIMARY KEY (firmaid);


--
-- TOC entry 1983 (class 2606 OID 70326)
-- Name: pfi_fitxer_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_fitxer
    ADD CONSTRAINT pfi_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1986 (class 2606 OID 70361)
-- Name: pfi_fluxdefirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_fluxdefirmes
    ADD CONSTRAINT pfi_fluxdefirmes_pk PRIMARY KEY (fluxdefirmesid);


--
-- TOC entry 2112 (class 2606 OID 210537)
-- Name: pfi_grupentita_nomentitat_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitat
    ADD CONSTRAINT pfi_grupentita_nomentitat_uk UNIQUE (nom, entitatid);


--
-- TOC entry 2115 (class 2606 OID 191433)
-- Name: pfi_grupentitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitat
    ADD CONSTRAINT pfi_grupentitat_pk PRIMARY KEY (grupentitatid);


--
-- TOC entry 2126 (class 2606 OID 191458)
-- Name: pfi_grupusrent_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_pk PRIMARY KEY (grupentitatusuarientitatid);


--
-- TOC entry 2130 (class 2606 OID 202121)
-- Name: pfi_grupusrent_usrgrup_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_usrgrup_uk UNIQUE (usuarientitatid, grupentitatid);


--
-- TOC entry 2093 (class 2606 OID 96099)
-- Name: pfi_idioma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_idioma
    ADD CONSTRAINT pfi_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1990 (class 2606 OID 70382)
-- Name: pfi_metadada_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_metadada
    ADD CONSTRAINT pfi_metadada_pk PRIMARY KEY (metadadaid);


--
-- TOC entry 1996 (class 2606 OID 69591)
-- Name: pfi_notificacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_notificacio
    ADD CONSTRAINT pfi_notificacio_pk PRIMARY KEY (notificacioid);


--
-- TOC entry 2120 (class 2606 OID 202123)
-- Name: pfi_permisgrpl_grupflux_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrpl_grupflux_uk UNIQUE (grupentitatid, fluxdefirmesid);


--
-- TOC entry 2122 (class 2606 OID 191438)
-- Name: pfi_permisgrupplantilla_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrupplantilla_pk PRIMARY KEY (permisgrupplantillaid);


--
-- TOC entry 2134 (class 2606 OID 202125)
-- Name: pfi_permisuspl_usrflux_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisuspl_usrflux_uk UNIQUE (usuarientitatid, fluxdefirmesid);


--
-- TOC entry 2136 (class 2606 OID 191469)
-- Name: pfi_permisusuariplantilla_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisusuariplantilla_pk PRIMARY KEY (permisusuariplantillaid);


--
-- TOC entry 2067 (class 2606 OID 202142)
-- Name: pfi_persona_nif_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_persona_nif_uk UNIQUE (nif);


--
-- TOC entry 1999 (class 2606 OID 70411)
-- Name: pfi_peticiodefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_peticiodefirma_pk PRIMARY KEY (peticiodefirmaid);


--
-- TOC entry 2007 (class 2606 OID 202146)
-- Name: pfi_petifirma_fluxfirmesid_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fluxfirmesid_uk UNIQUE (fluxdefirmesid);


--
-- TOC entry 2090 (class 2606 OID 96051)
-- Name: pfi_plantillafluxdefirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantillafluxdefirmes_pk PRIMARY KEY (fluxdefirmesid);


--
-- TOC entry 2148 (class 2606 OID 191673)
-- Name: pfi_posiciopagina_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_posiciopagina
    ADD CONSTRAINT pfi_posiciopagina_pk PRIMARY KEY (posiciopaginaid);


--
-- TOC entry 2096 (class 2606 OID 109045)
-- Name: pfi_posiciotaulafirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_posiciotaulafirmes
    ADD CONSTRAINT pfi_posiciotaulafirmes_pk PRIMARY KEY (posiciotaulafirmesid);


--
-- TOC entry 2099 (class 2606 OID 109115)
-- Name: pfi_prioritat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_prioritat
    ADD CONSTRAINT pfi_prioritat_pk PRIMARY KEY (prioritatid);


--
-- TOC entry 2018 (class 2606 OID 117201)
-- Name: pfi_rebreavis_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_pk PRIMARY KEY (id);


--
-- TOC entry 2022 (class 2606 OID 202127)
-- Name: pfi_rebreavis_tnotiusr_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_tnotiusr_uk UNIQUE (tipusnotificacioid, usuarientitatid);


--
-- TOC entry 2025 (class 2606 OID 69632)
-- Name: pfi_role_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_role
    ADD CONSTRAINT pfi_role_pk PRIMARY KEY (roleid);


--
-- TOC entry 2035 (class 2606 OID 202129)
-- Name: pfi_roleusrapp_approle_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusrapp_approle_uk UNIQUE (usuariaplicacioid, roleid);


--
-- TOC entry 2029 (class 2606 OID 202131)
-- Name: pfi_roleusrent_roleusrent_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusrent_roleusrent_uk UNIQUE (roleid, usuarientitatid);


--
-- TOC entry 2039 (class 2606 OID 117232)
-- Name: pfi_roleusuariaplicacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusuariaplicacio_pk PRIMARY KEY (id);


--
-- TOC entry 2032 (class 2606 OID 117222)
-- Name: pfi_roleusuarientitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusuarientitat_pk PRIMARY KEY (id);


--
-- TOC entry 2159 (class 2606 OID 202148)
-- Name: pfi_tipmetada_nom_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusmetadada
    ADD CONSTRAINT pfi_tipmetada_nom_uk UNIQUE (nom);


--
-- TOC entry 2047 (class 2606 OID 202133)
-- Name: pfi_tipusdoccd_codetdoc_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdoccd_codetdoc_uk UNIQUE (colaboraciodelegacioid, tipusdocumentid);


--
-- TOC entry 2044 (class 2606 OID 69660)
-- Name: pfi_tipusdocument_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdocument_pk PRIMARY KEY (tipusdocumentid);


--
-- TOC entry 2051 (class 2606 OID 117210)
-- Name: pfi_tipusdocumentcoladele_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdocumentcoladele_pk PRIMARY KEY (id);


--
-- TOC entry 2110 (class 2606 OID 117258)
-- Name: pfi_tipusestatdefirmafinal_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusestatdefirmafinal
    ADD CONSTRAINT pfi_tipusestatdefirmafinal_pk PRIMARY KEY (tipusestatdefirmafinalid);


--
-- TOC entry 2060 (class 2606 OID 69689)
-- Name: pfi_tipusfirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusfirma
    ADD CONSTRAINT pfi_tipusfirma_pk PRIMARY KEY (tipusfirmaid);


--
-- TOC entry 2161 (class 2606 OID 199969)
-- Name: pfi_tipusmetadada_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusmetadada
    ADD CONSTRAINT pfi_tipusmetadada_pk PRIMARY KEY (tipusmetadadaid);


--
-- TOC entry 2063 (class 2606 OID 69698)
-- Name: pfi_tipusnotificacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusnotificacio
    ADD CONSTRAINT pfi_tipusnotificacio_pk PRIMARY KEY (tipusnotificacioid);


--
-- TOC entry 2168 (class 2606 OID 210396)
-- Name: pfi_traduccio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_traduccio
    ADD CONSTRAINT pfi_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 2164 (class 2606 OID 210501)
-- Name: pfi_traducciomap_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_traducciomap
    ADD CONSTRAINT pfi_traducciomap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 2076 (class 2606 OID 202117)
-- Name: pfi_usrentitat_perentcar_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_perentcar_uk UNIQUE (usuaripersonaid, entitatid, carrec);


--
-- TOC entry 2085 (class 2606 OID 191527)
-- Name: pfi_usuariaplicacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usuariaplicacio_pk PRIMARY KEY (usuariaplicacioid);


--
-- TOC entry 2079 (class 2606 OID 191227)
-- Name: pfi_usuarientitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usuarientitat_pk PRIMARY KEY (usuarientitatid);


--
-- TOC entry 2106 (class 2606 OID 109148)
-- Name: pfi_usuarientitatfavorit_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_usuarientitatfavorit_pk PRIMARY KEY (id);


--
-- TOC entry 2071 (class 2606 OID 202135)
-- Name: pfi_usuaripersona_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_usuaripersona_pk PRIMARY KEY (usuaripersonaid);


--
-- TOC entry 2157 (class 1259 OID 202149)
-- Name: pfi_algorismedefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_algorismedefirma_pk_i ON pfi_algorismedefirma USING btree (algorismedefirmaid);


--
-- TOC entry 1932 (class 1259 OID 69727)
-- Name: pfi_annex_fitxerid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annex_fitxerid_fk_i ON pfi_annex USING btree (fitxerid);


--
-- TOC entry 1933 (class 1259 OID 69728)
-- Name: pfi_annex_petdefirmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annex_petdefirmaid_fk_i ON pfi_annex USING btree (peticiodefirmaid);


--
-- TOC entry 1936 (class 1259 OID 202150)
-- Name: pfi_annex_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annex_pk_i ON pfi_annex USING btree (annexid);


--
-- TOC entry 1937 (class 1259 OID 69740)
-- Name: pfi_annexfirmat_annexid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_annexid_fk_i ON pfi_annexfirmat USING btree (annexid);


--
-- TOC entry 1938 (class 1259 OID 69739)
-- Name: pfi_annexfirmat_firmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_firmaid_fk_i ON pfi_annexfirmat USING btree (firmaid);


--
-- TOC entry 1939 (class 1259 OID 69741)
-- Name: pfi_annexfirmat_fitxerid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_fitxerid_fk_i ON pfi_annexfirmat USING btree (fitxerid);


--
-- TOC entry 1942 (class 1259 OID 202151)
-- Name: pfi_annexfirmat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_pk_i ON pfi_annexfirmat USING btree (annexfirmatid);


--
-- TOC entry 1943 (class 1259 OID 69966)
-- Name: pfi_bitacola_peticid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_peticid_fk_i ON pfi_bitacola USING btree (peticiodefirmaid);


--
-- TOC entry 1946 (class 1259 OID 202152)
-- Name: pfi_bitacola_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_pk_i ON pfi_bitacola USING btree (bitacolaid);


--
-- TOC entry 1947 (class 1259 OID 191305)
-- Name: pfi_bitacola_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_usrentid_fk_i ON pfi_bitacola USING btree (usuarientitatid);


--
-- TOC entry 1950 (class 1259 OID 202153)
-- Name: pfi_blocdefirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_blocdefirmes_pk_i ON pfi_blocdefirmes USING btree (blocdefirmesid);


--
-- TOC entry 1951 (class 1259 OID 69978)
-- Name: pfi_blocfirmes_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_blocfirmes_fluxid_fk_i ON pfi_blocdefirmes USING btree (fluxdefirmesid);


--
-- TOC entry 2152 (class 1259 OID 202154)
-- Name: pfi_codibarres_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_codibarres_pk_i ON pfi_codibarres USING btree (codibarresid);


--
-- TOC entry 1952 (class 1259 OID 191333)
-- Name: pfi_colabdeleg_coldelid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colabdeleg_coldelid_fk_i ON pfi_colaboraciodelegacio USING btree (colaboradordelegatid);


--
-- TOC entry 1953 (class 1259 OID 191317)
-- Name: pfi_colabdeleg_destid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colabdeleg_destid_fk_i ON pfi_colaboraciodelegacio USING btree (destinatariid);


--
-- TOC entry 1954 (class 1259 OID 69986)
-- Name: pfi_colabdeleg_fitautoid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colabdeleg_fitautoid_fk_i ON pfi_colaboraciodelegacio USING btree (fitxerautoritzacioid);


--
-- TOC entry 1957 (class 1259 OID 202155)
-- Name: pfi_colaboraciodelegacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colaboraciodelegacio_pk_i ON pfi_colaboraciodelegacio USING btree (colaboraciodelegacioid);


--
-- TOC entry 2138 (class 1259 OID 202192)
-- Name: pfi_custodia_codbarrpos_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_codbarrpos_fk_i ON pfi_custodiainfo USING btree (codibarresposiciopaginaid);


--
-- TOC entry 2139 (class 1259 OID 202191)
-- Name: pfi_custodia_codibarid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_codibarid_fk_i ON pfi_custodiainfo USING btree (codibarresid);


--
-- TOC entry 2140 (class 1259 OID 202195)
-- Name: pfi_custodia_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_entitatid_fk_i ON pfi_custodiainfo USING btree (entitatid);


--
-- TOC entry 2141 (class 1259 OID 202190)
-- Name: pfi_custodia_msgpospagid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_msgpospagid_fk_i ON pfi_custodiainfo USING btree (missatgeposiciopaginaid);


--
-- TOC entry 2142 (class 1259 OID 202194)
-- Name: pfi_custodia_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_usrappid_fk_i ON pfi_custodiainfo USING btree (usuariaplicacioid);


--
-- TOC entry 2143 (class 1259 OID 202193)
-- Name: pfi_custodia_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_usrentid_fk_i ON pfi_custodiainfo USING btree (usuarientitatid);


--
-- TOC entry 2146 (class 1259 OID 202156)
-- Name: pfi_custodiainfo_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodiainfo_pk_i ON pfi_custodiainfo USING btree (custodiainfoid);


--
-- TOC entry 1958 (class 1259 OID 202196)
-- Name: pfi_entitat_faviconid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_faviconid_fk_i ON pfi_entitat USING btree (faviconid);


--
-- TOC entry 1959 (class 1259 OID 202199)
-- Name: pfi_entitat_logosegellid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_logosegellid_fk_i ON pfi_entitat USING btree (logosegellid);


--
-- TOC entry 1960 (class 1259 OID 202197)
-- Name: pfi_entitat_logowebid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_logowebid_fk_i ON pfi_entitat USING btree (logowebid);


--
-- TOC entry 1961 (class 1259 OID 202198)
-- Name: pfi_entitat_logowebpeuid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_logowebpeuid_fk_i ON pfi_entitat USING btree (logowebpeuid);


--
-- TOC entry 1962 (class 1259 OID 202200)
-- Name: pfi_entitat_pdfautoriid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_pdfautoriid_fk_i ON pfi_entitat USING btree (pdfautoritzaciodelegacioid);


--
-- TOC entry 1965 (class 1259 OID 202157)
-- Name: pfi_entitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_pk_i ON pfi_entitat USING btree (entitatid);


--
-- TOC entry 1966 (class 1259 OID 202201)
-- Name: pfi_entitat_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_usrappid_fk_i ON pfi_entitat USING btree (usuariaplicacioid);


--
-- TOC entry 1967 (class 1259 OID 69906)
-- Name: pfi_estatdefirma_firmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatdefirma_firmaid_fk_i ON pfi_estatdefirma USING btree (firmaid);


--
-- TOC entry 1970 (class 1259 OID 69907)
-- Name: pfi_estatdefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatdefirma_pk_i ON pfi_estatdefirma USING btree (estatdefirmaid);


--
-- TOC entry 1971 (class 1259 OID 202203)
-- Name: pfi_estatfirma_coladele_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_coladele_fk_i ON pfi_estatdefirma USING btree (colaboraciodelegacioid);


--
-- TOC entry 1972 (class 1259 OID 202202)
-- Name: pfi_estatfirma_estatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_estatid_fk_i ON pfi_estatdefirma USING btree (tipusestatdefirmafinalid);


--
-- TOC entry 1973 (class 1259 OID 69905)
-- Name: pfi_estatfirma_estatinid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_estatinid_fk_i ON pfi_estatdefirma USING btree (tipusestatdefirmainicialid);


--
-- TOC entry 1974 (class 1259 OID 191403)
-- Name: pfi_estatfirma_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_usrentid_fk_i ON pfi_estatdefirma USING btree (usuarientitatid);


--
-- TOC entry 2108 (class 1259 OID 202179)
-- Name: pfi_estfirmafi_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estfirmafi_pk_i ON pfi_tipusestatdefirmafinal USING btree (tipusestatdefirmafinalid);


--
-- TOC entry 2055 (class 1259 OID 202180)
-- Name: pfi_estfirmini_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estfirmini_pk_i ON pfi_tipusestatdefirmainicial USING btree (tipusestatdefirmainicialid);


--
-- TOC entry 2058 (class 1259 OID 202181)
-- Name: pfi_estpetfirm_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estpetfirm_pk_i ON pfi_tipusestatpeticiodefirma USING btree (tipusestatpeticiodefirmaid);


--
-- TOC entry 2101 (class 1259 OID 202235)
-- Name: pfi_favorit_favoritid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_favorit_favoritid_fk_i ON pfi_usuarientitatfavorit USING btree (favoritid);


--
-- TOC entry 2102 (class 1259 OID 202234)
-- Name: pfi_favorit_origenid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_favorit_origenid_fk_i ON pfi_usuarientitatfavorit USING btree (origenid);


--
-- TOC entry 1975 (class 1259 OID 69926)
-- Name: pfi_firma_blocdefirmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_blocdefirmaid_fk_i ON pfi_firma USING btree (blocdefirmaid);


--
-- TOC entry 1976 (class 1259 OID 191387)
-- Name: pfi_firma_destinatariid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_destinatariid_fk_i ON pfi_firma USING btree (destinatariid);


--
-- TOC entry 1977 (class 1259 OID 202204)
-- Name: pfi_firma_estatfirmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_estatfirmaid_fk_i ON pfi_firma USING btree (tipusestatdefirmafinalid);


--
-- TOC entry 1978 (class 1259 OID 69924)
-- Name: pfi_firma_fitxerfirmatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_fitxerfirmatid_fk_i ON pfi_firma USING btree (fitxerfirmatid);


--
-- TOC entry 1981 (class 1259 OID 202158)
-- Name: pfi_firma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_pk_i ON pfi_firma USING btree (firmaid);


--
-- TOC entry 1984 (class 1259 OID 202159)
-- Name: pfi_fitxer_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_fitxer_pk_i ON pfi_fitxer USING btree (fitxerid);


--
-- TOC entry 1987 (class 1259 OID 202160)
-- Name: pfi_fluxdefirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_fluxdefirmes_pk_i ON pfi_fluxdefirmes USING btree (fluxdefirmesid);


--
-- TOC entry 2113 (class 1259 OID 202205)
-- Name: pfi_grupentitat_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupentitat_entitatid_fk_i ON pfi_grupentitat USING btree (entitatid);


--
-- TOC entry 2116 (class 1259 OID 202161)
-- Name: pfi_grupentitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupentitat_pk_i ON pfi_grupentitat USING btree (grupentitatid);


--
-- TOC entry 2124 (class 1259 OID 202207)
-- Name: pfi_grupusrent_grupentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupusrent_grupentid_fk_i ON pfi_grupentitatusuarientitat USING btree (grupentitatid);


--
-- TOC entry 2127 (class 1259 OID 202162)
-- Name: pfi_grupusrent_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupusrent_pk_i ON pfi_grupentitatusuarientitat USING btree (grupentitatusuarientitatid);


--
-- TOC entry 2128 (class 1259 OID 202206)
-- Name: pfi_grupusrent_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupusrent_usrentid_fk_i ON pfi_grupentitatusuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2094 (class 1259 OID 202163)
-- Name: pfi_idioma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_idioma_pk_i ON pfi_idioma USING btree (idiomaid);


--
-- TOC entry 1988 (class 1259 OID 69948)
-- Name: pfi_metadada_peticioid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_metadada_peticioid_fk_i ON pfi_metadada USING btree (peticiodefirmaid);


--
-- TOC entry 1991 (class 1259 OID 202164)
-- Name: pfi_metadada_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_metadada_pk_i ON pfi_metadada USING btree (metadadaid);


--
-- TOC entry 1992 (class 1259 OID 202208)
-- Name: pfi_metadada_tipusmetaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_metadada_tipusmetaid_fk_i ON pfi_metadada USING btree (tipusmetadadaid);


--
-- TOC entry 1993 (class 1259 OID 69955)
-- Name: pfi_notifica_peticioid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notifica_peticioid_fk_i ON pfi_notificacio USING btree (peticiodefirmaid);


--
-- TOC entry 1994 (class 1259 OID 69954)
-- Name: pfi_notifica_tiponotiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notifica_tiponotiid_fk_i ON pfi_notificacio USING btree (tipusnotificacioid);


--
-- TOC entry 1997 (class 1259 OID 202165)
-- Name: pfi_notificacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notificacio_pk_i ON pfi_notificacio USING btree (notificacioid);


--
-- TOC entry 2117 (class 1259 OID 202210)
-- Name: pfi_permisgrpl_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisgrpl_fluxid_fk_i ON pfi_permisgrupplantilla USING btree (fluxdefirmesid);


--
-- TOC entry 2118 (class 1259 OID 202209)
-- Name: pfi_permisgrpl_grupentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisgrpl_grupentid_fk_i ON pfi_permisgrupplantilla USING btree (grupentitatid);


--
-- TOC entry 2123 (class 1259 OID 202166)
-- Name: pfi_permisgrupplantilla_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisgrupplantilla_pk_i ON pfi_permisgrupplantilla USING btree (permisgrupplantillaid);


--
-- TOC entry 2131 (class 1259 OID 202212)
-- Name: pfi_permisuspl_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisuspl_fluxid_fk_i ON pfi_permisusuariplantilla USING btree (fluxdefirmesid);


--
-- TOC entry 2132 (class 1259 OID 202211)
-- Name: pfi_permisuspl_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisuspl_usrentid_fk_i ON pfi_permisusuariplantilla USING btree (usuarientitatid);


--
-- TOC entry 2137 (class 1259 OID 202167)
-- Name: pfi_permisusuariplantilla_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisusuariplantilla_pk_i ON pfi_permisusuariplantilla USING btree (permisusuariplantillaid);


--
-- TOC entry 2065 (class 1259 OID 202236)
-- Name: pfi_persona_idiomaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_persona_idiomaid_fk_i ON pfi_usuaripersona USING btree (idiomaid);


--
-- TOC entry 2068 (class 1259 OID 202237)
-- Name: pfi_persona_rubricaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_persona_rubricaid_fk_i ON pfi_usuaripersona USING btree (rubricaid);


--
-- TOC entry 2000 (class 1259 OID 202168)
-- Name: pfi_peticiodefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_peticiodefirma_pk_i ON pfi_peticiodefirma USING btree (peticiodefirmaid);


--
-- TOC entry 2001 (class 1259 OID 202217)
-- Name: pfi_petifirma_algofirmid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_algofirmid_fk_i ON pfi_peticiodefirma USING btree (algorismedefirmaid);


--
-- TOC entry 2002 (class 1259 OID 202222)
-- Name: pfi_petifirma_custinfoid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_custinfoid_fk_i ON pfi_peticiodefirma USING btree (custodiainfoid);


--
-- TOC entry 2003 (class 1259 OID 202218)
-- Name: pfi_petifirma_estatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_estatid_fk_i ON pfi_peticiodefirma USING btree (tipusestatpeticiodefirmaid);


--
-- TOC entry 2004 (class 1259 OID 202213)
-- Name: pfi_petifirma_fitxeadaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_fitxeadaid_fk_i ON pfi_peticiodefirma USING btree (fitxeradaptatid);


--
-- TOC entry 2005 (class 1259 OID 69836)
-- Name: pfi_petifirma_fitxerid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_fitxerid_fk_i ON pfi_peticiodefirma USING btree (fitxerafirmarid);


--
-- TOC entry 2008 (class 1259 OID 69837)
-- Name: pfi_petifirma_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_fluxid_fk_i ON pfi_peticiodefirma USING btree (fluxdefirmesid);


--
-- TOC entry 2009 (class 1259 OID 202219)
-- Name: pfi_petifirma_idiomaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_idiomaid_fk_i ON pfi_peticiodefirma USING btree (idiomaid);


--
-- TOC entry 2010 (class 1259 OID 202221)
-- Name: pfi_petifirma_logosegid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_logosegid_fk_i ON pfi_peticiodefirma USING btree (logosegellid);


--
-- TOC entry 2011 (class 1259 OID 202215)
-- Name: pfi_petifirma_postaulaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_postaulaid_fk_i ON pfi_peticiodefirma USING btree (posiciotaulafirmesid);


--
-- TOC entry 2012 (class 1259 OID 202220)
-- Name: pfi_petifirma_prioritatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_prioritatid_fk_i ON pfi_peticiodefirma USING btree (prioritatid);


--
-- TOC entry 2013 (class 1259 OID 202216)
-- Name: pfi_petifirma_tipofirmid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_tipofirmid_fk_i ON pfi_peticiodefirma USING btree (tipusfirmaid);


--
-- TOC entry 2014 (class 1259 OID 202214)
-- Name: pfi_petifirma_tipusdocid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_tipusdocid_fk_i ON pfi_peticiodefirma USING btree (tipusdocumentid);


--
-- TOC entry 2015 (class 1259 OID 191565)
-- Name: pfi_petifirma_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_usrappid_fk_i ON pfi_peticiodefirma USING btree (usuariaplicacioid);


--
-- TOC entry 2016 (class 1259 OID 202223)
-- Name: pfi_petifirma_usrentiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_usrentiid_fk_i ON pfi_peticiodefirma USING btree (usuarientitatid);


--
-- TOC entry 2087 (class 1259 OID 202225)
-- Name: pfi_plantiflfi_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plantiflfi_usrappid_fk_i ON pfi_plantillafluxdefirmes USING btree (usuariaplicacioid);


--
-- TOC entry 2088 (class 1259 OID 202224)
-- Name: pfi_plantiflfi_usrentiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plantiflfi_usrentiid_fk_i ON pfi_plantillafluxdefirmes USING btree (usuarientitatid);


--
-- TOC entry 2091 (class 1259 OID 202169)
-- Name: pfi_plantillafluxdefirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plantillafluxdefirmes_pk_i ON pfi_plantillafluxdefirmes USING btree (fluxdefirmesid);


--
-- TOC entry 2149 (class 1259 OID 202170)
-- Name: pfi_posiciopagina_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_posiciopagina_pk_i ON pfi_posiciopagina USING btree (posiciopaginaid);


--
-- TOC entry 2097 (class 1259 OID 202171)
-- Name: pfi_posiciotaulafirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_posiciotaulafirmes_pk_i ON pfi_posiciotaulafirmes USING btree (posiciotaulafirmesid);


--
-- TOC entry 2100 (class 1259 OID 202172)
-- Name: pfi_prioritat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_prioritat_pk_i ON pfi_prioritat USING btree (prioritatid);


--
-- TOC entry 2019 (class 1259 OID 202173)
-- Name: pfi_rebreavis_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_rebreavis_pk_i ON pfi_rebreavis USING btree (id);


--
-- TOC entry 2020 (class 1259 OID 69893)
-- Name: pfi_rebreavis_tiponotiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_rebreavis_tiponotiid_fk_i ON pfi_rebreavis USING btree (tipusnotificacioid);


--
-- TOC entry 2023 (class 1259 OID 191290)
-- Name: pfi_rebreavis_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_rebreavis_usrentid_fk_i ON pfi_rebreavis USING btree (usuarientitatid);


--
-- TOC entry 2026 (class 1259 OID 202174)
-- Name: pfi_role_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_role_pk_i ON pfi_role USING btree (roleid);


--
-- TOC entry 2036 (class 1259 OID 69815)
-- Name: pfi_roleusrapp_roleid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrapp_roleid_fk_i ON pfi_roleusuariaplicacio USING btree (roleid);


--
-- TOC entry 2037 (class 1259 OID 202226)
-- Name: pfi_roleusrapp_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrapp_usrappid_fk_i ON pfi_roleusuariaplicacio USING btree (usuariaplicacioid);


--
-- TOC entry 2027 (class 1259 OID 202227)
-- Name: pfi_roleusrent_roleid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrent_roleid_fk_i ON pfi_roleusuarientitat USING btree (roleid);


--
-- TOC entry 2030 (class 1259 OID 191373)
-- Name: pfi_roleusrent_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrent_usrentid_fk_i ON pfi_roleusuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2040 (class 1259 OID 202175)
-- Name: pfi_roleusuariaplicacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusuariaplicacio_pk_i ON pfi_roleusuariaplicacio USING btree (id);


--
-- TOC entry 2033 (class 1259 OID 202176)
-- Name: pfi_roleusuarientitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusuarientitat_pk_i ON pfi_roleusuarientitat USING btree (id);


--
-- TOC entry 2041 (class 1259 OID 202228)
-- Name: pfi_tipusdoc_usuariappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdoc_usuariappid_fk_i ON pfi_tipusdocument USING btree (usuariaplicacioid);


--
-- TOC entry 2048 (class 1259 OID 202229)
-- Name: pfi_tipusdoccd_coldelid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdoccd_coldelid_fk_i ON pfi_tipusdocumentcoladele USING btree (colaboraciodelegacioid);


--
-- TOC entry 2049 (class 1259 OID 69769)
-- Name: pfi_tipusdoccd_tipusdocid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdoccd_tipusdocid_fk_i ON pfi_tipusdocumentcoladele USING btree (tipusdocumentid);


--
-- TOC entry 2042 (class 1259 OID 210527)
-- Name: pfi_tipusdocument_nom_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdocument_nom_fk_i ON pfi_tipusdocument USING btree (nom);


--
-- TOC entry 2045 (class 1259 OID 202177)
-- Name: pfi_tipusdocument_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdocument_pk_i ON pfi_tipusdocument USING btree (tipusdocumentid);


--
-- TOC entry 2052 (class 1259 OID 202178)
-- Name: pfi_tipusdocumentcoladele_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdocumentcoladele_pk_i ON pfi_tipusdocumentcoladele USING btree (id);


--
-- TOC entry 2061 (class 1259 OID 202182)
-- Name: pfi_tipusfirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusfirma_pk_i ON pfi_tipusfirma USING btree (tipusfirmaid);


--
-- TOC entry 2162 (class 1259 OID 202183)
-- Name: pfi_tipusmetadada_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusmetadada_pk_i ON pfi_tipusmetadada USING btree (tipusmetadadaid);


--
-- TOC entry 2064 (class 1259 OID 202184)
-- Name: pfi_tipusnotificacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusnotificacio_pk_i ON pfi_tipusnotificacio USING btree (tipusnotificacioid);


--
-- TOC entry 2169 (class 1259 OID 210461)
-- Name: pfi_traduccio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_traduccio_pk_i ON pfi_traduccio USING btree (traduccioid);


--
-- TOC entry 2165 (class 1259 OID 210529)
-- Name: pfi_traducmap_idiomaid_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_traducmap_idiomaid_pk_i ON pfi_traducciomap USING btree (traducciomapid);


--
-- TOC entry 2166 (class 1259 OID 210528)
-- Name: pfi_traducmap_tradmapid_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_traducmap_tradmapid_pk_i ON pfi_traducciomap USING btree (traducciomapid);


--
-- TOC entry 2081 (class 1259 OID 202230)
-- Name: pfi_usrapp_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_entitatid_fk_i ON pfi_usuariaplicacio USING btree (entitatid);


--
-- TOC entry 2082 (class 1259 OID 202231)
-- Name: pfi_usrapp_idiomaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_idiomaid_fk_i ON pfi_usuariaplicacio USING btree (idiomaid);


--
-- TOC entry 2083 (class 1259 OID 202232)
-- Name: pfi_usrapp_logosegellid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_logosegellid_fk_i ON pfi_usuariaplicacio USING btree (logosegellid);


--
-- TOC entry 2073 (class 1259 OID 69786)
-- Name: pfi_usrentitat_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_entitatid_fk_i ON pfi_usuarientitat USING btree (entitatid);


--
-- TOC entry 2074 (class 1259 OID 202233)
-- Name: pfi_usrentitat_logosegid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_logosegid_fk_i ON pfi_usuarientitat USING btree (logosegellid);


--
-- TOC entry 2077 (class 1259 OID 69787)
-- Name: pfi_usrentitat_personaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_personaid_fk_i ON pfi_usuarientitat USING btree (usuaripersonaid);


--
-- TOC entry 2086 (class 1259 OID 202185)
-- Name: pfi_usuariaplicacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuariaplicacio_pk_i ON pfi_usuariaplicacio USING btree (usuariaplicacioid);


--
-- TOC entry 2080 (class 1259 OID 202186)
-- Name: pfi_usuarientitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuarientitat_pk_i ON pfi_usuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2107 (class 1259 OID 202187)
-- Name: pfi_usuarientitatfavorit_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuarientitatfavorit_pk_i ON pfi_usuarientitatfavorit USING btree (id);


--
-- TOC entry 2069 (class 1259 OID 202189)
-- Name: pfi_usuaripersona_nif_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuaripersona_nif_i ON pfi_usuaripersona USING btree (nif);


--
-- TOC entry 2072 (class 1259 OID 202188)
-- Name: pfi_usuaripersona_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuaripersona_pk_i ON pfi_usuaripersona USING btree (usuaripersonaid);


--
-- TOC entry 2172 (class 2606 OID 201721)
-- Name: pfi_anexfirmat_annex_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT pfi_anexfirmat_annex_fk FOREIGN KEY (annexid) REFERENCES pfi_annex(annexid);


--
-- TOC entry 2173 (class 2606 OID 201751)
-- Name: pfi_anexfirmat_firma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT pfi_anexfirmat_firma_fk FOREIGN KEY (firmaid) REFERENCES pfi_firma(firmaid);


--
-- TOC entry 2174 (class 2606 OID 202046)
-- Name: pfi_anexfirmat_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT pfi_anexfirmat_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2171 (class 2606 OID 202041)
-- Name: pfi_annex_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annex
    ADD CONSTRAINT pfi_annex_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2170 (class 2606 OID 201746)
-- Name: pfi_annex_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annex
    ADD CONSTRAINT pfi_annex_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2175 (class 2606 OID 201756)
-- Name: pfi_bitacola_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_bitacola
    ADD CONSTRAINT pfi_bitacola_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2176 (class 2606 OID 201761)
-- Name: pfi_bitacola_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_bitacola
    ADD CONSTRAINT pfi_bitacola_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2177 (class 2606 OID 201766)
-- Name: pfi_blocfirmes_fluxfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_blocdefirmes
    ADD CONSTRAINT pfi_blocfirmes_fluxfirmes_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_fluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2180 (class 2606 OID 202051)
-- Name: pfi_colabdeleg_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colabdeleg_fitxer_fk FOREIGN KEY (fitxerautoritzacioid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2178 (class 2606 OID 202021)
-- Name: pfi_colabdeleg_usrentitat_c_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colabdeleg_usrentitat_c_fk FOREIGN KEY (colaboradordelegatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2179 (class 2606 OID 202026)
-- Name: pfi_colabdeleg_usrentitat_d_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colabdeleg_usrentitat_d_fk FOREIGN KEY (destinatariid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2246 (class 2606 OID 201736)
-- Name: pfi_custodia_codibarres_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_codibarres_fk FOREIGN KEY (codibarresid) REFERENCES pfi_codibarres(codibarresid);


--
-- TOC entry 2244 (class 2606 OID 201726)
-- Name: pfi_custodia_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2248 (class 2606 OID 202011)
-- Name: pfi_custodia_pospagina_bar_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_pospagina_bar_fk FOREIGN KEY (codibarresposiciopaginaid) REFERENCES pfi_posiciopagina(posiciopaginaid);


--
-- TOC entry 2249 (class 2606 OID 202016)
-- Name: pfi_custodia_pospagina_msg_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_pospagina_msg_fk FOREIGN KEY (missatgeposiciopaginaid) REFERENCES pfi_posiciopagina(posiciopaginaid);


--
-- TOC entry 2247 (class 2606 OID 201741)
-- Name: pfi_custodia_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2245 (class 2606 OID 201731)
-- Name: pfi_custodia_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2184 (class 2606 OID 202086)
-- Name: pfi_entitat_fitxer_icon_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_icon_fk FOREIGN KEY (faviconid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2186 (class 2606 OID 202096)
-- Name: pfi_entitat_fitxer_loca_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_loca_fk FOREIGN KEY (logowebid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2185 (class 2606 OID 202091)
-- Name: pfi_entitat_fitxer_lope_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_lope_fk FOREIGN KEY (logowebpeuid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2182 (class 2606 OID 202076)
-- Name: pfi_entitat_fitxer_lose_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_lose_fk FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2183 (class 2606 OID 202081)
-- Name: pfi_entitat_fitxer_pdfd_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_pdfd_fk FOREIGN KEY (pdfautoritzaciodelegacioid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2181 (class 2606 OID 201771)
-- Name: pfi_entitat_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2191 (class 2606 OID 201796)
-- Name: pfi_estatfirma_colabdeleg_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_colabdeleg_fk FOREIGN KEY (colaboraciodelegacioid) REFERENCES pfi_colaboraciodelegacio(colaboraciodelegacioid);


--
-- TOC entry 2188 (class 2606 OID 201781)
-- Name: pfi_estatfirma_estfirmafi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_estfirmafi_fk FOREIGN KEY (tipusestatdefirmafinalid) REFERENCES pfi_tipusestatdefirmafinal(tipusestatdefirmafinalid);


--
-- TOC entry 2187 (class 2606 OID 201776)
-- Name: pfi_estatfirma_estfirmini_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_estfirmini_fk FOREIGN KEY (tipusestatdefirmainicialid) REFERENCES pfi_tipusestatdefirmainicial(tipusestatdefirmainicialid);


--
-- TOC entry 2190 (class 2606 OID 201791)
-- Name: pfi_estatfirma_firma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_firma_fk FOREIGN KEY (firmaid) REFERENCES pfi_firma(firmaid);


--
-- TOC entry 2189 (class 2606 OID 201786)
-- Name: pfi_estatfirma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2235 (class 2606 OID 202031)
-- Name: pfi_favorit_usrentitat_fav_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_favorit_usrentitat_fav_fk FOREIGN KEY (favoritid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2236 (class 2606 OID 202036)
-- Name: pfi_favorit_usrentitat_ori_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_favorit_usrentitat_ori_fk FOREIGN KEY (origenid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2193 (class 2606 OID 201806)
-- Name: pfi_firma_blocfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_blocfirmes_fk FOREIGN KEY (blocdefirmaid) REFERENCES pfi_blocdefirmes(blocdefirmesid);


--
-- TOC entry 2194 (class 2606 OID 201811)
-- Name: pfi_firma_estfirmafi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_estfirmafi_fk FOREIGN KEY (tipusestatdefirmafinalid) REFERENCES pfi_tipusestatdefirmafinal(tipusestatdefirmafinalid);


--
-- TOC entry 2195 (class 2606 OID 202056)
-- Name: pfi_firma_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_fitxer_fk FOREIGN KEY (fitxerfirmatid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2192 (class 2606 OID 201801)
-- Name: pfi_firma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_usrentitat_fk FOREIGN KEY (destinatariid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2237 (class 2606 OID 201816)
-- Name: pfi_grupentita_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_grupentitat
    ADD CONSTRAINT pfi_grupentita_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2240 (class 2606 OID 201821)
-- Name: pfi_grupusrent_grupentita_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_grupentita_fk FOREIGN KEY (grupentitatid) REFERENCES pfi_grupentitat(grupentitatid);


--
-- TOC entry 2241 (class 2606 OID 201826)
-- Name: pfi_grupusrent_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2196 (class 2606 OID 201831)
-- Name: pfi_metadada_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_metadada
    ADD CONSTRAINT pfi_metadada_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2197 (class 2606 OID 201836)
-- Name: pfi_metadada_tipmetada_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_metadada
    ADD CONSTRAINT pfi_metadada_tipmetada_fk FOREIGN KEY (tipusmetadadaid) REFERENCES pfi_tipusmetadada(tipusmetadadaid);


--
-- TOC entry 2199 (class 2606 OID 201846)
-- Name: pfi_notifica_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_notificacio
    ADD CONSTRAINT pfi_notifica_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2198 (class 2606 OID 201841)
-- Name: pfi_notifica_tipnotific_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_notificacio
    ADD CONSTRAINT pfi_notifica_tipnotific_fk FOREIGN KEY (tipusnotificacioid) REFERENCES pfi_tipusnotificacio(tipusnotificacioid);


--
-- TOC entry 2238 (class 2606 OID 201851)
-- Name: pfi_permisgrpl_grupentita_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrpl_grupentita_fk FOREIGN KEY (grupentitatid) REFERENCES pfi_grupentitat(grupentitatid);


--
-- TOC entry 2239 (class 2606 OID 201856)
-- Name: pfi_permisgrpl_plantiflfi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrpl_plantiflfi_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_plantillafluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2242 (class 2606 OID 201861)
-- Name: pfi_permisuspl_plantiflfi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisuspl_plantiflfi_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_plantillafluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2243 (class 2606 OID 201866)
-- Name: pfi_permisuspl_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisuspl_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2225 (class 2606 OID 202071)
-- Name: pfi_persona_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_persona_fitxer_fk FOREIGN KEY (rubricaid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2224 (class 2606 OID 202006)
-- Name: pfi_persona_idioma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_persona_idioma_fk FOREIGN KEY (idiomaid) REFERENCES pfi_idioma(idiomaid);


--
-- TOC entry 2209 (class 2606 OID 201916)
-- Name: pfi_petifirma_algofirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_algofirma_fk FOREIGN KEY (algorismedefirmaid) REFERENCES pfi_algorismedefirma(algorismedefirmaid);


--
-- TOC entry 2203 (class 2606 OID 201886)
-- Name: pfi_petifirma_custodia_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_custodia_fk FOREIGN KEY (custodiainfoid) REFERENCES pfi_custodiainfo(custodiainfoid);


--
-- TOC entry 2205 (class 2606 OID 201896)
-- Name: pfi_petifirma_estpetfirm_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_estpetfirm_fk FOREIGN KEY (tipusestatpeticiodefirmaid) REFERENCES pfi_tipusestatpeticiodefirma(tipusestatpeticiodefirmaid);


--
-- TOC entry 2212 (class 2606 OID 202106)
-- Name: pfi_petifirma_fitxer_ada_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_ada_fk FOREIGN KEY (fitxeradaptatid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2211 (class 2606 OID 202101)
-- Name: pfi_petifirma_fitxer_fir_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_fir_fk FOREIGN KEY (fitxerafirmarid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2213 (class 2606 OID 202111)
-- Name: pfi_petifirma_fitxer_log_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_log_fk FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2210 (class 2606 OID 201921)
-- Name: pfi_petifirma_fluxfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fluxfirmes_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_fluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2207 (class 2606 OID 201906)
-- Name: pfi_petifirma_idioma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_idioma_fk FOREIGN KEY (idiomaid) REFERENCES pfi_idioma(idiomaid);


--
-- TOC entry 2206 (class 2606 OID 201901)
-- Name: pfi_petifirma_postaufir_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_postaufir_fk FOREIGN KEY (posiciotaulafirmesid) REFERENCES pfi_posiciotaulafirmes(posiciotaulafirmesid);


--
-- TOC entry 2202 (class 2606 OID 201881)
-- Name: pfi_petifirma_prioritat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_prioritat_fk FOREIGN KEY (prioritatid) REFERENCES pfi_prioritat(prioritatid);


--
-- TOC entry 2200 (class 2606 OID 201871)
-- Name: pfi_petifirma_tipusdoc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES pfi_tipusdocument(tipusdocumentid);


--
-- TOC entry 2204 (class 2606 OID 201891)
-- Name: pfi_petifirma_tipusfirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_tipusfirma_fk FOREIGN KEY (tipusfirmaid) REFERENCES pfi_tipusfirma(tipusfirmaid);


--
-- TOC entry 2208 (class 2606 OID 201911)
-- Name: pfi_petifirma_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2201 (class 2606 OID 201876)
-- Name: pfi_petifirma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2232 (class 2606 OID 201926)
-- Name: pfi_plantiflfi_fluxfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantiflfi_fluxfirmes_fk FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_fluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2234 (class 2606 OID 201936)
-- Name: pfi_plantiflfi_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantiflfi_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2233 (class 2606 OID 201931)
-- Name: pfi_plantiflfi_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantiflfi_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2214 (class 2606 OID 201941)
-- Name: pfi_rebreavis_tipnotific_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_tipnotific_fk FOREIGN KEY (tipusnotificacioid) REFERENCES pfi_tipusnotificacio(tipusnotificacioid);


--
-- TOC entry 2215 (class 2606 OID 201946)
-- Name: pfi_rebreavis_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2218 (class 2606 OID 201951)
-- Name: pfi_roleusrapp_role_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusrapp_role_fk FOREIGN KEY (roleid) REFERENCES pfi_role(roleid);


--
-- TOC entry 2219 (class 2606 OID 201956)
-- Name: pfi_roleusrapp_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusrapp_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2216 (class 2606 OID 201961)
-- Name: pfi_roleusrent_role_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusrent_role_fk FOREIGN KEY (roleid) REFERENCES pfi_role(roleid);


--
-- TOC entry 2217 (class 2606 OID 201966)
-- Name: pfi_roleusrent_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusrent_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2220 (class 2606 OID 210511)
-- Name: pfi_tipusdoc_traduccio_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdoc_traduccio_fk FOREIGN KEY (nom) REFERENCES pfi_traduccio(traduccioid);


--
-- TOC entry 2221 (class 2606 OID 201971)
-- Name: pfi_tipusdoc_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdoc_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2223 (class 2606 OID 201981)
-- Name: pfi_tipusdoccd_colabdeleg_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdoccd_colabdeleg_fk FOREIGN KEY (colaboraciodelegacioid) REFERENCES pfi_colaboraciodelegacio(colaboraciodelegacioid);


--
-- TOC entry 2222 (class 2606 OID 201976)
-- Name: pfi_tipusdoccd_tipusdoc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdoccd_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES pfi_tipusdocument(tipusdocumentid);


--
-- TOC entry 2250 (class 2606 OID 210469)
-- Name: pfi_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_traducciomap
    ADD CONSTRAINT pfi_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES pfi_traduccio(traduccioid);


--
-- TOC entry 2230 (class 2606 OID 201991)
-- Name: pfi_usrapp_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2231 (class 2606 OID 202061)
-- Name: pfi_usrapp_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_fitxer_fk FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2229 (class 2606 OID 201986)
-- Name: pfi_usrapp_idioma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_idioma_fk FOREIGN KEY (idiomaid) REFERENCES pfi_idioma(idiomaid);


--
-- TOC entry 2227 (class 2606 OID 201996)
-- Name: pfi_usrentitat_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2228 (class 2606 OID 202066)
-- Name: pfi_usrentitat_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_fitxer_fk FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2226 (class 2606 OID 202136)
-- Name: pfi_usrentitat_persona_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_persona_fk FOREIGN KEY (usuaripersonaid) REFERENCES pfi_usuaripersona(usuaripersonaid);


--
-- TOC entry 2341 (class 0 OID 0)
-- Dependencies: 7
-- Name: portafib; Type: ACL; Schema: -; Owner: portafib
--

REVOKE ALL ON SCHEMA portafib FROM PUBLIC;
REVOKE ALL ON SCHEMA portafib FROM portafib;
GRANT ALL ON SCHEMA portafib TO portafib;


-- Completed on 2014-09-04 12:01:47

--
-- PostgreSQL database dump complete
--

