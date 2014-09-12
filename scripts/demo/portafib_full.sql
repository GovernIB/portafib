--
-- PostgreSQL database dump
--

-- Dumped from database version 8.4.14
-- Dumped by pg_dump version 9.3.1
-- Started on 2014-05-14 14:22:36

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 2273 (class 1262 OID 68318)
-- Name: portafib; Type: DATABASE; Schema: -; Owner: portafib
--

CREATE DATABASE portafib WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';


ALTER DATABASE portafib OWNER TO portafib;

\connect portafib

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

--
-- TOC entry 620 (class 2612 OID 16386)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = portafib, pg_catalog;

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

SET default_tablespace = '';

SET default_with_oids = false;

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
-- TOC entry 182 (class 1259 OID 191664)
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
-- TOC entry 2277 (class 0 OID 0)
-- Dependencies: 146
-- Name: COLUMN pfi_colaboraciodelegacio.revisor; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_colaboraciodelegacio.revisor IS 'Només es per col.laborador i indica si es obligatori que aquell col.laborador digui la seva.';


--
-- TOC entry 180 (class 1259 OID 191641)
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
    datacustodia timestamp without time zone
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
    web character varying(250) NOT NULL
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
-- TOC entry 2278 (class 0 OID 0)
-- Dependencies: 149
-- Name: COLUMN pfi_firma.destinatariid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_firma.destinatariid IS 'Si val null significa que s''ha de substituir pel Sol·licitant de la petició (només podrà valer null en plantilles de flux de firmes)';


--
-- TOC entry 2279 (class 0 OID 0)
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
-- TOC entry 176 (class 1259 OID 191429)
-- Name: pfi_grupentitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_grupentitat (
    grupentitatid bigint NOT NULL,
    nom character varying(100) NOT NULL,
    descripcio character varying(255),
    entitatid character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_grupentitat OWNER TO portafib;

--
-- TOC entry 178 (class 1259 OID 191454)
-- Name: pfi_grupentitatusuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_grupentitatusuarientitat (
    grupentitatusuarientitatid bigint NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    grupentitatid bigint NOT NULL
);


ALTER TABLE portafib.pfi_grupentitatusuarientitat OWNER TO portafib;

--
-- TOC entry 171 (class 1259 OID 92635)
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
    valor character varying(1000) NOT NULL
);


ALTER TABLE portafib.pfi_metadada OWNER TO portafib;

--
-- TOC entry 153 (class 1259 OID 69574)
-- Name: pfi_mitjadecomunicacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_mitjadecomunicacio (
    mitjadecomunicacioid character varying(50) NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying,
    javaclass character varying(255) NOT NULL,
    actiu boolean
);


ALTER TABLE portafib.pfi_mitjadecomunicacio OWNER TO portafib;

--
-- TOC entry 154 (class 1259 OID 69583)
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
-- TOC entry 177 (class 1259 OID 191434)
-- Name: pfi_permisgrupplantilla; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_permisgrupplantilla (
    permisgrupplantillaid bigint NOT NULL,
    grupentitatid bigint NOT NULL,
    fluxdefirmesid bigint NOT NULL
);


ALTER TABLE portafib.pfi_permisgrupplantilla OWNER TO portafib;

--
-- TOC entry 179 (class 1259 OID 191465)
-- Name: pfi_permisusuariplantilla; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_permisusuariplantilla (
    permisusuariplantillaid bigint NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    fluxdefirmesid bigint NOT NULL
);


ALTER TABLE portafib.pfi_permisusuariplantilla OWNER TO portafib;

--
-- TOC entry 155 (class 1259 OID 69592)
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
    informacioadicional character varying(500),
    logosegellid bigint,
    custodiainfoid bigint
);


ALTER TABLE portafib.pfi_peticiodefirma OWNER TO portafib;

--
-- TOC entry 2280 (class 0 OID 0)
-- Dependencies: 155
-- Name: COLUMN pfi_peticiodefirma.posiciotaulafirmesid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_peticiodefirma.posiciotaulafirmesid IS 'Posicio taula de firmes: primera o darrera pàgina';


--
-- TOC entry 156 (class 1259 OID 69606)
-- Name: pfi_peticiofirmausuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_peticiofirmausuarientitat (
    peticiodefirmaid bigint NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    mitjadecomunicacioid character varying(50),
    mitjadecomunicacioadreza character varying(256),
    avisweb boolean
);


ALTER TABLE portafib.pfi_peticiofirmausuarientitat OWNER TO portafib;

--
-- TOC entry 170 (class 1259 OID 92610)
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
-- TOC entry 181 (class 1259 OID 191661)
-- Name: pfi_posiciopagina; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_posiciopagina (
    posiciopaginaid bigint NOT NULL,
    nom character varying(255) NOT NULL
);


ALTER TABLE portafib.pfi_posiciopagina OWNER TO portafib;

--
-- TOC entry 172 (class 1259 OID 109038)
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
-- TOC entry 173 (class 1259 OID 109105)
-- Name: pfi_prioritat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_prioritat (
    prioritatid integer NOT NULL,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_prioritat OWNER TO portafib;

--
-- TOC entry 157 (class 1259 OID 69611)
-- Name: pfi_rebreavis; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_rebreavis (
    tipusnotificacioid bigint NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_rebreavis OWNER TO portafib;

--
-- TOC entry 158 (class 1259 OID 69626)
-- Name: pfi_role; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_role (
    roleid character varying(50) NOT NULL,
    nom character varying(50) DEFAULT NULL::character varying NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying
);


ALTER TABLE portafib.pfi_role OWNER TO portafib;

--
-- TOC entry 160 (class 1259 OID 69647)
-- Name: pfi_roleusuariaplicacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_roleusuariaplicacio (
    usuariaplicacioid character varying(50) NOT NULL,
    roleid character varying(50) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_roleusuariaplicacio OWNER TO portafib;

--
-- TOC entry 159 (class 1259 OID 69638)
-- Name: pfi_roleusuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_roleusuarientitat (
    roleid character varying(50) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_roleusuarientitat OWNER TO portafib;

--
-- TOC entry 161 (class 1259 OID 69652)
-- Name: pfi_tipusdocument; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusdocument (
    tipusdocumentid bigint NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL,
    usuariaplicacioid character varying(50)
);


ALTER TABLE portafib.pfi_tipusdocument OWNER TO portafib;

--
-- TOC entry 162 (class 1259 OID 69661)
-- Name: pfi_tipusdocumentcoladele; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusdocumentcoladele (
    colaboraciodelegacioid bigint NOT NULL,
    tipusdocumentid bigint NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_tipusdocumentcoladele OWNER TO portafib;

--
-- TOC entry 175 (class 1259 OID 117254)
-- Name: pfi_tipusestatdefirmafinal; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusestatdefirmafinal (
    tipusestatdefirmafinalid bigint NOT NULL,
    nom character varying(50) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE portafib.pfi_tipusestatdefirmafinal OWNER TO portafib;

--
-- TOC entry 163 (class 1259 OID 69666)
-- Name: pfi_tipusestatdefirmainicial; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusestatdefirmainicial (
    tipusestatdefirmainicialid bigint NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_tipusestatdefirmainicial OWNER TO portafib;

--
-- TOC entry 164 (class 1259 OID 69672)
-- Name: pfi_tipusestatpeticiodefirma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_tipusestatpeticiodefirma (
    tipusestatpeticiodefirmaid bigint NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_tipusestatpeticiodefirma OWNER TO portafib;

--
-- TOC entry 165 (class 1259 OID 69681)
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
-- TOC entry 166 (class 1259 OID 69690)
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
-- TOC entry 2281 (class 0 OID 0)
-- Dependencies: 166
-- Name: COLUMN pfi_tipusnotificacio.esavis; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_tipusnotificacio.esavis IS 'Si es avis val true
Si és notificació es false
Si pot ser avis i notificació llavors val null
';


--
-- TOC entry 169 (class 1259 OID 69715)
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
    potcustodiar boolean DEFAULT false NOT NULL
);


ALTER TABLE portafib.pfi_usuariaplicacio OWNER TO portafib;

--
-- TOC entry 2282 (class 0 OID 0)
-- Dependencies: 169
-- Name: TABLE pfi_usuariaplicacio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON TABLE pfi_usuariaplicacio IS 'Usuari de tipus màquina que realitzarà peticions a PortaFIB';


--
-- TOC entry 2283 (class 0 OID 0)
-- Dependencies: 169
-- Name: COLUMN pfi_usuariaplicacio.emailadmin; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuariaplicacio.emailadmin IS 'Correu de la persona encarregada d''aquest usuari-Màquina';


--
-- TOC entry 2284 (class 0 OID 0)
-- Dependencies: 169
-- Name: COLUMN pfi_usuariaplicacio.callbackurl; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuariaplicacio.callbackurl IS 'Adreça on esta implementat el servei de recepció de notificacions associades a les peticions de firma realitzades per aquest usuari-màquina';


--
-- TOC entry 2285 (class 0 OID 0)
-- Dependencies: 169
-- Name: COLUMN pfi_usuariaplicacio.callbackversio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuariaplicacio.callbackversio IS 'La versió 1 és la compatible amb INDRA i la versió 2 és l''especifica del nou Portafirmes';


--
-- TOC entry 168 (class 1259 OID 69706)
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
    potcustodiar boolean DEFAULT false NOT NULL
);


ALTER TABLE portafib.pfi_usuarientitat OWNER TO portafib;

--
-- TOC entry 174 (class 1259 OID 109116)
-- Name: pfi_usuarientitatfavorit; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE pfi_usuarientitatfavorit (
    origenid character varying(101) NOT NULL,
    favoritid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_usuarientitatfavorit OWNER TO portafib;

--
-- TOC entry 167 (class 1259 OID 69699)
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
-- TOC entry 2286 (class 0 OID 0)
-- Dependencies: 167
-- Name: COLUMN pfi_usuaripersona.rubricaid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN pfi_usuaripersona.rubricaid IS 'és la firma gràfica de la persona';


--
-- TOC entry 2228 (class 0 OID 69470)
-- Dependencies: 142
-- Data for Name: pfi_annex; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_annex (annexid, fitxerid, peticiodefirmaid, adjuntar, firmar) VALUES (96100, 95961, 96002, true, true);
INSERT INTO pfi_annex (annexid, fitxerid, peticiodefirmaid, adjuntar, firmar) VALUES (96400, 96301, 96350, true, true);


--
-- TOC entry 2229 (class 0 OID 69479)
-- Dependencies: 143
-- Data for Name: pfi_annexfirmat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2230 (class 0 OID 69496)
-- Dependencies: 144
-- Data for Name: pfi_bitacola; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2231 (class 0 OID 69502)
-- Dependencies: 145
-- Data for Name: pfi_blocdefirmes; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (75705, '2013-12-04 10:13:05.617', 75654, 1, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (75704, '2013-12-04 10:11:31.242', 75653, 1, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (85801, NULL, 85900, 1, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (34755, NULL, 34701, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107852, NULL, 107801, 1, 7);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107853, NULL, 107801, 1, 4);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107854, NULL, 107801, 1, 0);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (95854, NULL, 95802, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107855, NULL, 107801, 1, 6);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107856, NULL, 107801, 1, 2);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107857, NULL, 107801, 1, 1);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (96201, NULL, 96150, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107858, NULL, 107801, 1, 8);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (34753, NULL, 34701, 2, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107859, NULL, 107801, 1, 5);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (34752, NULL, 34701, 1, 30);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (96200, '2014-03-26 09:44:36.109', 96150, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (95853, NULL, 95802, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107860, NULL, 107801, 1, 3);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (93102, NULL, 93050, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (93101, NULL, 93050, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (33400, NULL, 3051, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (75703, '2014-03-24 12:28:21.181', 75652, 1, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (94250, NULL, 94200, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (94251, NULL, 94200, 1, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (95851, '2014-03-25 12:25:12.784', 95800, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (95850, '2014-03-25 12:26:07.138', 95800, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (18050, NULL, 18000, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (18051, NULL, 18000, 3, 30);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (18200, NULL, 18000, 1, 40);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (23151, NULL, 18000, 2, 50);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (30450, NULL, 30400, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (30451, NULL, 30400, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (104101, NULL, 104051, 1, 0);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (33501, NULL, 3051, 2, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (30201, NULL, 3051, 1, 30);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (75702, NULL, 75652, 1, 20);


--
-- TOC entry 2268 (class 0 OID 191664)
-- Dependencies: 182
-- Data for Name: pfi_codibarres; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_codibarres (codibarresid, nom, descripcio) VALUES ('org.fundaciobit.plugins.barcode.qrcode.QrCodePlugin', 'QrCode', NULL);
INSERT INTO pfi_codibarres (codibarresid, nom, descripcio) VALUES ('org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin', 'Pdf417', NULL);
INSERT INTO pfi_codibarres (codibarresid, nom, descripcio) VALUES ('org.fundaciobit.plugins.barcode.barcode128.BarCode128Plugin', 'BarCode128', NULL);


--
-- TOC entry 2232 (class 0 OID 69508)
-- Dependencies: 146
-- Data for Name: pfi_colaboraciodelegacio; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (76000, NULL, '2013-12-04 10:27:28', 'fundaciobit_dest1', 'Per l´article 3/203 del BOIB', 'fundaciobit_dele1', NULL, 75813, true, false, true, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (61950, NULL, '2013-10-31 09:34:29', 'fundaciobit_anadal', 'Colaborador ACTIU', 'fundaciobit_cola1', NULL, NULL, false, false, false, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (5500, NULL, '2013-05-13 15:46:18', 'fundaciobit_anadal', 'per fer proves', 'fundaciobit_dele1', 'perque si me dona la gana', 5450, false, false, true, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (31001, NULL, '2013-09-12 14:39:04', 'fundaciobit_dest1', 'Necessit ajuda', 'fundaciobit_cola1', NULL, NULL, true, false, false, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (31500, NULL, '2013-09-13 09:25:19', 'fundaciobit_dest1', 'Un altra col·laborador', 'fundaciobit_cola2', NULL, NULL, true, false, false, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (5950, NULL, '2013-05-13 12:26:39', 'fundaciobit_anadal', 'Es un colaborador', 'fundaciobit_cola1', 'dghsgdhdfghd', 5900, false, false, false, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (5951, NULL, '2013-05-13 12:27:53', 'fundaciobit_anadal', 'Es un colaborador', 'fundaciobit_cola2', 'Em lia la DEMO', 5901, false, false, false, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (51150, NULL, '2013-10-04 10:55:49', 'fundaciobit_anadal', 'Boib 201', 'fundaciobit_dboerner', 'borrar', 50959, false, false, true, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (80001, NULL, '2013-12-16 15:07:12', 'fundaciobit_anadal', '12345567890 123456789 12345456789 123456789 123456789 123456', 'fundaciobit_anadal', NULL, 79762, true, false, true, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (93700, '2014-08-31 12:04:11', '2014-08-01 12:34:10', 'sonespases_dest1', 'Estic de Vacances', 'sonespases_anadal', NULL, NULL, false, false, true, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (93701, NULL, '2014-03-24 12:35:20', 'sonespases_dest1', 'Ajuda a revisar document', 'sonespases_anadal', NULL, NULL, false, false, false, NULL);


--
-- TOC entry 2266 (class 0 OID 191641)
-- Dependencies: 180
-- Data for Name: pfi_custodiainfo; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2233 (class 0 OID 69519)
-- Dependencies: 147
-- Data for Name: pfi_entitat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_entitat (entitatid, filtrecertificats, nom, descripcio, adrezahtml, activa, suporttelefon, suportweb, suportemail, usuariaplicacioid, pdfautoritzaciodelegacioid, faviconid, logowebid, logowebpeuid, logosegellid, maxuploadsize, maxsizefitxeradaptat, maxfilestosignatsametime, web) VALUES ('fundaciobit', '#ssl:  Serial Number of Certificate
#checkexpiration:true or false ''
#policyid:1.3.6.1.4.1.17326.10.9.2.2.2,1.3.6.1.4.1.17326.1.3.3.2
#sign_cert_usage:  (sense valor) només certificats per firmar
#rfc2254_subject:(&(cn=Antoni Nadal Bennasar*))
rfc2254_issuer:|(cn=AC DNIE 001)(cn=AC DNIE 002)(cn=AC DNIE 003)(cn=AC Camerfirma Certificados Camerales)(cn=AC CAMERFIRMA AAPP)(cn=EC-ACC)(cn=FundacioBit-OTAE)(cn=IBIT_CA)(OU=FNMT Clase 2 CA))', 'Fundacio Bit', NULL, '<p><a href="http://www.ibit.org/"> <strong>Fundaci&oacute; Bit.</strong><br /> </a> <small> Centre Empresarial Son Espanyol<br /> C/ Laura Bassi CP 07121, Palma<br /> Telf. 971 784 730 Fax. 971 784 636 </small></p>', true, '971177283', 'http://otaeweb.ibit.org/', 'otae@ibit.org', 'anadalapp', 666, 80150, 80151, 80152, 80153, NULL, NULL, NULL, 'http://www.fundaciobit.org');
INSERT INTO pfi_entitat (entitatid, filtrecertificats, nom, descripcio, adrezahtml, activa, suporttelefon, suportweb, suportemail, usuariaplicacioid, pdfautoritzaciodelegacioid, faviconid, logowebid, logowebpeuid, logosegellid, maxuploadsize, maxsizefitxeradaptat, maxfilestosignatsametime, web) VALUES ('sonespases', '#ssl:  Serial Number of Certificate
#checkexpiration:true or false ''
#policyid:1.3.6.1.4.1.17326.10.9.2.2.2,1.3.6.1.4.1.17326.1.3.3.2
#sign_cert_usage:  (sense valor) només certificats per firmar
#rfc2254_subject:(&(cn=Antoni Nadal Bennasar*))
rfc2254_issuer:|(cn=AC DNIE 001)(cn=AC Camerfirma Certificados Camerales)(cn=AC CAMERFIRMA AAPP)(cn=EC-ACC)(cn=FundacioBit-OTAE)(cn=IBIT_CA)(OU=FNMT Clase 2 CA))', 'Hospital Son Espases', 'Hospital Universitari Son Espases', '<p><a href="www.hospitalsonespases.es/&lrm;"><strong>Hospital Universitari Son Espases</strong><br /></a><small>Carretera de Valldemossa, 79<br />CP 07120 - Palma - Illes Balears<br />Telf. 871 205 000 </small></p>', true, '871205000', 'http://www.hospitalsonespases.es/index.php?lang=ca&Itemid=710', 'hse.informacio.cites@ssib.es', 'sonespases_app', 92854, 92850, 92851, 92852, 92853, NULL, NULL, NULL, 'http://www.hospitalsonespases.es');
INSERT INTO pfi_entitat (entitatid, filtrecertificats, nom, descripcio, adrezahtml, activa, suporttelefon, suportweb, suportemail, usuariaplicacioid, pdfautoritzaciodelegacioid, faviconid, logowebid, logowebpeuid, logosegellid, maxuploadsize, maxsizefitxeradaptat, maxfilestosignatsametime, web) VALUES ('caib', '#ssl:  Serial Number of Certificate
#checkexpiration:true or false ''
#policyid:1.3.6.1.4.1.17326.10.9.2.2.2,1.3.6.1.4.1.17326.1.3.3.2
#sign_cert_usage:  (sense valor) només certificats per firmar
#rfc2254_subject:(&(cn=Antoni Nadal Bennasar*))
rfc2254_issuer:|(cn=AC DNIE 001)(cn=AC DNIE 002)(cn=AC DNIE 003)(cn=AC Camerfirma Certificados Camerales)(cn=AC CAMERFIRMA AAPP)(cn=EC-ACC)(cn=FundacioBit-OTAE)(cn=IBIT_CA)(OU=FNMT Clase 2 CA))', 'Govern Illes Balears', NULL, '<p><strong><a href="http://www.caib.es/">Govern de les Illes Balears.</a></strong> <br /> <small> C/ de la Llotja, 3 - 07012 Palma<br /> Tel&egrave;fon 971 17 65 65 - Fax 971 17 62 21 </small></p>', true, '012', 'https://proves.caib.es/sistrafront/protected/init.do?modelo=IN0014CON&version=1&centre=WEB&tipus_escrit=PTD&language=es&asunto=PROVA%20TR%C0MIT%20TAF%2001', 'suport@caib.es', 'caibapp', 999, 80154, 80155, 80156, 80157, NULL, NULL, NULL, 'http://www.caib.es');


--
-- TOC entry 2234 (class 0 OID 69529)
-- Dependencies: 148
-- Data for Name: pfi_estatdefirma; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (75906, '2013-12-04 10:11:31.148', '2013-12-04 10:11:09.335', '', 1, 75754, 'fundaciobit_anadal', NULL, 2);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (75907, '2013-12-04 10:11:31.148', '2013-12-04 10:11:09.46', 'Alguna altra persona ja ha firmat la peticio', 0, 75754, 'fundaciobit_cola1', 61950, 4);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (75908, '2013-12-04 10:13:05.617', '2013-12-04 10:12:38.867', 'Per provar si rebuig funciona', 1, 75755, 'fundaciobit_anadal', NULL, 3);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (75909, '2013-12-04 10:13:05.617', '2013-12-04 10:12:39.007', 'La petició ha sigut rebutjada per un altre usuari', 0, 75755, 'fundaciobit_cola1', 61950, 4);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96051, '2014-03-25 12:24:33.292', '2014-03-25 12:21:57.338', '', 1, 95902, 'sonespases_dest1', NULL, 2);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96052, '2014-03-25 12:25:12.753', '2014-03-25 12:21:57.354', '', 1, 95903, 'sonespases_anadal', NULL, 2);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96050, '2014-03-25 12:25:12.753', '2014-03-25 12:21:57.244', 'Firma no necessaria al haver arribat al mínim de firmes requerides', 1, 95901, 'sonespases_anadal', NULL, 4);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96053, '2014-03-25 12:26:07.122', '2014-03-25 12:25:12.831', '', 1, 95900, 'sonespases_dest1', NULL, 2);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (108002, NULL, '2014-05-14 12:03:53.019', '', 1, 107904, 'fundaciobit_anadal', NULL, NULL);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (108003, NULL, '2014-05-14 12:03:53.05', '', 1, 107904, 'fundaciobit_anadal', 80001, NULL);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96451, '2014-03-26 09:42:46.296', '2014-03-26 09:37:03.609', '', 1, 96252, 'sonespases_dest1', NULL, 2);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96452, '2014-03-26 09:44:36.109', '2014-03-26 09:37:03.625', 'Hi ha errors', 1, 96250, 'sonespases_anadal', NULL, 3);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96450, '2014-03-26 09:44:36.109', '2014-03-26 09:37:03.359', 'La petició ha sigut rebutjada per un altre usuari', 1, 96251, 'sonespases_anadal', NULL, 4);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (75904, '2014-03-24 12:28:21.181', '2013-12-04 10:09:00.554', 'No està bé', 1, 75753, 'fundaciobit_anadal', NULL, 3);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (75905, '2014-03-24 12:28:21.181', '2013-12-04 10:09:00.695', 'La petició ha sigut rebutjada per un altre usuari', 0, 75753, 'fundaciobit_cola1', 61950, 4);


--
-- TOC entry 2235 (class 0 OID 69535)
-- Dependencies: 149
-- Data for Name: pfi_firma; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (93152, 'sonespases_gerent', 93102, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (93153, 'sonespases_anadal', 93101, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (93154, 'sonespases_ajudant_radiologia', 93101, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (93155, 'sonespases_cap_radiologia', 93101, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (18100, 'fundaciobit_president', 18050, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, 33460682019971198397969807784903978913, '123123', '13232123', NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (18101, 'fundaciobit_secretari', 18051, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (18150, 'fundaciobit_dest2', 18051, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (18500, 'fundaciobit_dest1', 18051, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (23201, 'fundaciobit_secretari', 23151, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (23202, 'fundaciobit_pcomite', 23151, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (30251, 'fundaciobit_president', 30201, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (30502, 'fundaciobit_president', 30451, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (32501, 'fundaciobit_secretari', 30450, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (33450, 'fundaciobit_dest1', 33400, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (18300, 'fundaciobit_anadal', 18200, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (33451, 'fundaciobit_dest2', 33400, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (33552, 'fundaciobit_secretari', 33501, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (33651, 'fundaciobit_cola2', 33501, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34803, 'fundaciobit_dest2', 34752, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34804, 'fundaciobit_president', 34753, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34805, 'fundaciobit_secretari', 34753, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34807, 'fundaciobit_dest1', 34755, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34808, 'fundaciobit_cola1', 34755, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34809, 'fundaciobit_dele1', 34755, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34810, 'fundaciobit_soli1', 34755, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (50801, 'fundaciobit_dest1', 30450, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (75752, 'fundaciobit_dest1', 75702, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (75754, 'fundaciobit_president', 75704, 75810, 1, true, 0, NULL, NULL, NULL, NULL, 13852547191753327203, 'C=ES,O=AC CAMERFIRMA S.A.,L=MADRID (Ver en https://www.camerfirma.com/address),OU=AC CAMERFIRMA,SERIALNUMBER=A82743287,CN=AC CAMERFIRMA AAPP', '2.5.4.13=Qualified Certificate: AAPP-SEP-M-SW-KUSU,CN=CERITIFICAT DE PROVES DE LA DGIDT,SERIALNUMBER=S0711001H,OU=sello electrónico,O=COMUNITAT AUTÒNOMA DE LES ILLES BALEARS,C=ES', 2, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (75755, 'fundaciobit_president', 75705, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (85851, 'fundaciobit_desactivar', 85801, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (75753, 'fundaciobit_president', 75703, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (94300, 'fundaciobit_dest1', 94250, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (94301, 'fundaciobit_president', 94251, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95901, 'sonespases_anadal', 95851, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95903, 'sonespases_ajudant_radiologia', 95851, 95953, 2, false, 0, NULL, NULL, NULL, NULL, 1023931205, 'C=ES,O=FNMT,OU=FNMT Clase 2 CA', 'C=es,O=FNMT,OU=FNMT Clase 2 CA,OU=500070015,CN=NOMBRE NADAL BENNASAR ANTONI - NIF 43096845C', 4, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95902, 'sonespases_cap_radiologia', 95851, 95952, 1, true, 0, NULL, NULL, NULL, NULL, 1023931205, 'C=ES,O=FNMT,OU=FNMT Clase 2 CA', 'C=es,O=FNMT,OU=FNMT Clase 2 CA,OU=500070015,CN=NOMBRE NADAL BENNASAR ANTONI - NIF 43096845C', 4, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95900, 'sonespases_gerent', 95850, 95954, 3, true, 0, NULL, NULL, NULL, NULL, 1023931205, 'C=ES,O=FNMT,OU=FNMT Clase 2 CA', 'C=es,O=FNMT,OU=FNMT Clase 2 CA,OU=500070015,CN=NOMBRE NADAL BENNASAR ANTONI - NIF 43096845C', 2, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95907, 'sonespases_anadal', 95853, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95909, 'sonespases_gerent', 95854, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (96250, 'sonespases_anadal', 96200, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (96253, 'sonespases_gerent', 96201, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (96252, 'sonespases_cap_radiologia', 96200, 96303, 1, true, 0, NULL, NULL, NULL, NULL, 1023931205, 'C=ES,O=FNMT,OU=FNMT Clase 2 CA', 'C=es,O=FNMT,OU=FNMT Clase 2 CA,OU=500070015,CN=NOMBRE NADAL BENNASAR ANTONI - NIF 43096845C', 2, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (96251, 'sonespases_ajudant_radiologia', 96200, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95906, 'sonespases_cap_radiologia', 95853, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95908, 'sonespases_ajudant_radiologia', 95853, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (104151, 'fundaciobit_president', 104101, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107902, 'fundaciobit_dest1', 107852, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107903, 'fundaciobit_cola2', 107853, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107904, 'fundaciobit_coordinador', 107854, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107905, 'fundaciobit_dest2', 107855, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107906, 'fundaciobit_pcomite', 107856, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107907, 'fundaciobit_president', 107857, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107908, 'fundaciobit_soli1', 107858, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107909, 'fundaciobit_cola1', 107859, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107910, 'fundaciobit_secretari', 107860, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);


--
-- TOC entry 2236 (class 0 OID 69544)
-- Dependencies: 150
-- Data for Name: pfi_fitxer; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (74751, NULL, 'image/jpeg', 'GuiaDePresentacio.jpg', 135015);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (74752, NULL, 'text/plain', 'postgresql_dump.txt', 507);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (74753, NULL, 'application/pdf', 'GuiaDePresentacio.pdf', 157000);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (999, NULL, 'application/pdf', 'FormulariAutoritzacioCAIB.pdf', 53759);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (92850, NULL, 'image/x-icon', 'favicon.ico', 32988);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80150, NULL, 'image/x-icon', 'fundaciobit.ico', 1150);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (600, NULL, 'image/png', 'govern1A.png', 24761);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80151, NULL, 'image/png', 'fundaciobit-logo-cap.png', 7962);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80152, NULL, 'image/png', 'fundaciobit-logo-peu.png', 1552);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80153, NULL, 'image/jpeg', 'logotaulafirmesfundaciobit.jpg', 2410);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80154, NULL, 'image/x-icon', 'caib.ico', 318);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (800, NULL, 'application/pdf', 'hola.pdf', 29653);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80155, NULL, 'image/png', 'govern-logo-cap.png', 3593);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80156, NULL, 'image/png', 'govern-logo-peu.png', 2595);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (2800, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80157, NULL, 'image/jpeg', 'logotaulafirmescaib.jpg', 4793);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (92851, NULL, 'image/png', 'sonespases_logo_cap.png', 25113);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (92852, NULL, 'image/png', 'sonespases_logo_peu.png', 6872);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (92853, NULL, 'image/png', 'sonespases_logo_taula_firmes.png', 10323);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (92854, NULL, 'application/pdf', 'formulariDelegacio.pdf', 56808);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (5450, NULL, 'application/pdf', 'holaX.pdf', 13965);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (5900, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (5901, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95950, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95951, '', 'application/pdf', 'TaulaDeFirmesDePeticio_96000.pdf', 27579);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95952, '', 'application/pdf', 'PeticioFirma_96000.pdf', 71848);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95953, '', 'application/pdf', 'PeticioFirma_96000.pdf', 120598);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95954, '', 'application/pdf', 'PeticioFirma_96000.pdf', 169466);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (96300, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (96301, NULL, 'image/jpeg', 'radiografia.jpg', 124244);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (96302, '', 'application/pdf', 'TaulaDeFirmesDePeticio_96350.pdf', 151859);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (96303, '', 'application/pdf', 'PeticioFirma_96350.pdf', 196029);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (107702, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (107703, '', 'application/pdf', 'TaulaDeFirmesDePeticio_107951.pdf', 20110);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (50959, '', 'application/pdf', 'FitxerAutoritzacioDelegacio_51150.pdf', 68078);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (666, NULL, 'application/pdf', 'formulariDelegacio.pdf', 56837);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (104201, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75806, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75807, '', 'application/pdf', 'TaulaDeFirmesDePeticio_75852.pdf', 19839);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75808, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75809, '', 'application/pdf', 'TaulaDeFirmesDePeticio_75853.pdf', 19792);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (72751, NULL, 'image/jpeg', 'GuiaDePresentacio.jpg', 135015);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (72752, NULL, 'text/plain', 'EsticFent.txt', 99522);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (72753, NULL, 'application/pdf', 'GuiaDePresentacio.pdf', 157000);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (72754, NULL, 'image/png', 'cilma.png', 4055);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75810, '', 'application/pdf', 'PeticioFirma_75853.pdf', 36332);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75811, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75812, '', 'application/pdf', 'TaulaDeFirmesDePeticio_75854.pdf', 19798);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75813, '', 'application/pdf', 'FitxerAutoritzacioDelegacio_76000.pdf', 69367);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (94150, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (79762, '', 'application/pdf', 'FitxerAutoritzacioDelegacio_80001.pdf', 69379);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95960, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95961, NULL, 'image/jpeg', 'radiografia.jpg', 124244);


--
-- TOC entry 2237 (class 0 OID 69554)
-- Dependencies: 151
-- Data for Name: pfi_fluxdefirmes; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (94200, 'Còpia de Petició Caducada');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (104051, 'fghdfghdfgh');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (18000, 'President i Secretari');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (3051, 'Plantilla Destinataris');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (30400, 'Destinatari, Secretari i President');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (75652, 'Petició Caducada');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (75653, 'Petició Firmada');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (75654, 'Petició Rebutjada');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (34701, 'PLANTILLA GRAN');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (93050, 'Document Radiologia');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (95800, 'Peticio Acceptada');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (95802, 'Informe Radiologia');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (96150, 'Prova Son Espases');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (107801, 'ertyetyerty');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (85900, 'PLANTILLA DESACTIVAR');


--
-- TOC entry 2262 (class 0 OID 191429)
-- Dependencies: 176
-- Data for Name: pfi_grupentitat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2264 (class 0 OID 191454)
-- Dependencies: 178
-- Data for Name: pfi_grupentitatusuarientitat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2257 (class 0 OID 92635)
-- Dependencies: 171
-- Data for Name: pfi_idioma; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('ca', 'Català', true, 1);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('es', 'Castellano', true, 2);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('de', 'Deutsch', false, 5);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('en_US', 'English USA', false, 7);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('fr', 'Français', false, 4);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('it', 'Italiano', false, 6);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('en', 'English', false, 3);


--
-- TOC entry 2238 (class 0 OID 69564)
-- Dependencies: 152
-- Data for Name: pfi_metadada; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2239 (class 0 OID 69574)
-- Dependencies: 153
-- Data for Name: pfi_mitjadecomunicacio; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2240 (class 0 OID 69583)
-- Dependencies: 154
-- Data for Name: pfi_notificacio; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2263 (class 0 OID 191434)
-- Dependencies: 177
-- Data for Name: pfi_permisgrupplantilla; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2265 (class 0 OID 191465)
-- Dependencies: 179
-- Data for Name: pfi_permisusuariplantilla; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2241 (class 0 OID 69592)
-- Dependencies: 155
-- Data for Name: pfi_peticiodefirma; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (75854, '2014-01-04 10:12:23', '2013-12-04 10:13:05.617', '2013-12-04 10:12:38.757', 2, 75811, 75812, 'Petició Rebutjada', 'Petició Rebutjada', 0, 'Per provar si rebuig funciona', 'anadalapp', 3, 75654, 1, NULL, 'ca', 0, 'Provar Petició Rebutjada', 'Solicitant deFundacioBit', 'soli1@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (107951, '2014-06-14 12:03:37', NULL, '2014-05-14 12:03:52.847', 2, 107702, 107703, 'ertyetyerty', 'ertyetyerty', 0, NULL, 'anadalapp', 1, 107801, 1, NULL, 'ca', 0, 'sdfgsdfgsdfg', 'Antoni Nadal Bennasar', 'anadal@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (75853, '2014-01-04 10:10:40', '2013-12-04 10:11:31.242', '2013-12-04 10:11:09.21', 2, 75808, 75809, 'Petició Firmada', 'Petició Firmada', 0, NULL, 'anadalapp', 4, 75653, 1, NULL, 'ca', 0, 'Prova de peticio Firmada', 'Solicitant deFundacioBit', 'soli1@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (104251, '2014-05-31 10:52:47', NULL, '2014-05-13 13:12:25', 2, 104201, NULL, 'fghdfghdfgh', 'fghdfghdfgh', 0, NULL, 'anadalapp', 0, 104051, 1, NULL, 'ca', 0, 'sdfgsdfgsdfg', 'Antoni Nadal Bennasar (anadalapp)', 'anadal@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (75852, '2013-12-03 10:08:25', '2014-03-24 12:28:21.181', '2013-11-01 10:09:00', 10, 75806, 75807, 'Petició Caducada', 'Petició Caducada', 0, 'No està bé', 'anadalapp', 3, 75652, 1, NULL, 'ca', 0, 'Prova Petició Caducada', 'Solicitant deFundacioBit', 'soli1@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (94350, '2013-12-03 10:08:25', NULL, '2014-03-24 13:03:17.169', 10, 94150, NULL, 'Còpia de Petició Caducada', 'Petició Caducada', 0, NULL, 'anadalapp', 0, 94200, 1, NULL, 'ca', 0, 'Prova Petició Caducada', 'Solicitant deFundacioBit', 'soli1@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (96000, '2014-04-25 12:21:21', '2014-03-25 12:26:07.138', '2014-03-25 12:21:57.166', 2, 95950, 95951, 'Peticio Acceptada', 'Peticio Acceptada', 0, NULL, 'sonespases_app', 4, 95800, 1, NULL, 'ca', 0, 'Pre fer demo', 'Solicitant deFundacioBit', 'soli1@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (96350, '2014-04-26 09:34:09', '2014-03-26 09:44:36.109', '2014-03-26 09:37:03.281', 13, 96300, 96302, 'Prova Son Espases', 'Prova Son Espases', 0, 'Hi ha errors', 'sonespases_app', 3, 96150, 1, NULL, 'ca', 0, 'Prova DEMO', 'Solicitant deFundacioBit', 'anadal@fundaciobit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (96002, '2014-04-25 13:21:09', NULL, '2014-03-28 08:04:30', 13, 95960, NULL, 'Informe Radiologia', 'Informe Radiologia', 0, NULL, 'sonespases_app', 0, 95802, 1, NULL, 'ca', 5, 'Motiu DEMO', 'Solicitant deFundacioBit', 'soli1@ibit.org', NULL, NULL, NULL);


--
-- TOC entry 2242 (class 0 OID 69606)
-- Dependencies: 156
-- Data for Name: pfi_peticiofirmausuarientitat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (75853, 'fundaciobit_soli1', NULL, NULL, false);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (75854, 'fundaciobit_soli1', NULL, NULL, false);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (75852, 'fundaciobit_soli1', NULL, NULL, true);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (94350, 'fundaciobit_soli1', NULL, NULL, false);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (96000, 'sonespases_soli1', NULL, NULL, false);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (96350, 'sonespases_soli1', NULL, NULL, true);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (96002, 'sonespases_soli1', NULL, NULL, false);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (107951, 'fundaciobit_anadal', NULL, NULL, false);


--
-- TOC entry 2256 (class 0 OID 92610)
-- Dependencies: 170
-- Data for Name: pfi_plantillafluxdefirmes; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_plantillafluxdefirmes (fluxdefirmesid, descripcio, usuarientitatid, compartir, usuariaplicacioid) VALUES (3051, 'Tots els destinataris', 'fundaciobit_anadal', true, NULL);
INSERT INTO pfi_plantillafluxdefirmes (fluxdefirmesid, descripcio, usuarientitatid, compartir, usuariaplicacioid) VALUES (18000, 'Plantilla President i Secretari', 'fundaciobit_anadal', false, NULL);
INSERT INTO pfi_plantillafluxdefirmes (fluxdefirmesid, descripcio, usuarientitatid, compartir, usuariaplicacioid) VALUES (30400, 'Firmes de Secretari i Destinatari i després de President.', 'fundaciobit_soli1', false, NULL);
INSERT INTO pfi_plantillafluxdefirmes (fluxdefirmesid, descripcio, usuarientitatid, compartir, usuariaplicacioid) VALUES (34701, 'Demo', 'fundaciobit_soli1', false, NULL);
INSERT INTO pfi_plantillafluxdefirmes (fluxdefirmesid, descripcio, usuarientitatid, compartir, usuariaplicacioid) VALUES (85900, 'dfyhdfhdfgh', 'fundaciobit_anadal', false, NULL);
INSERT INTO pfi_plantillafluxdefirmes (fluxdefirmesid, descripcio, usuarientitatid, compartir, usuariaplicacioid) VALUES (93050, 'Gerent,  Cap de Radiologia i un ajudant', 'sonespases_soli1', false, NULL);


--
-- TOC entry 2287 (class 0 OID 0)
-- Dependencies: 141
-- Name: pfi_portafib_seq; Type: SEQUENCE SET; Schema: portafib; Owner: portafib
--

SELECT pg_catalog.setval('pfi_portafib_seq', 2168, true);


--
-- TOC entry 2267 (class 0 OID 191661)
-- Dependencies: 181
-- Data for Name: pfi_posiciopagina; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (1, 'posiciopagina.adalt');
INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (0, 'posiciopagina.cap');
INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (2, 'posiciopagina.abaix');
INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (3, 'posiciopagina.esquerra');
INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (4, 'posiciopagina.dreta');


--
-- TOC entry 2258 (class 0 OID 109038)
-- Dependencies: 172
-- Data for Name: pfi_posiciotaulafirmes; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_posiciotaulafirmes (posiciotaulafirmesid, nom, descripcio, suportada) VALUES (1, 'taulafirmes.taula_de_firmes_primera_pagina', 'Taula de firmes en la primera pàgina', true);
INSERT INTO pfi_posiciotaulafirmes (posiciotaulafirmesid, nom, descripcio, suportada) VALUES (0, 'taulafirmes.sense_taula_de_firmes', 'Sense taula de firmes, la posicio de les firmes es definiex en la taula pfi_firma', true);
INSERT INTO pfi_posiciotaulafirmes (posiciotaulafirmesid, nom, descripcio, suportada) VALUES (-1, 'taulafirmes.taula_de_firmes_darrera_pagina', 'Taula de firmes en la darrera pàgina', true);


--
-- TOC entry 2259 (class 0 OID 109105)
-- Dependencies: 173
-- Data for Name: pfi_prioritat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_prioritat (prioritatid, nom) VALUES (0, 'prioritat.baixa');
INSERT INTO pfi_prioritat (prioritatid, nom) VALUES (9, 'prioritat.alta');
INSERT INTO pfi_prioritat (prioritatid, nom) VALUES (5, 'prioritat.normal');


--
-- TOC entry 2243 (class 0 OID 69611)
-- Dependencies: 157
-- Data for Name: pfi_rebreavis; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_rebreavis (tipusnotificacioid, usuarientitatid, id) VALUES (60, 'fundaciobit_soli1', 92800);
INSERT INTO pfi_rebreavis (tipusnotificacioid, usuarientitatid, id) VALUES (70, 'fundaciobit_soli1', 92801);
INSERT INTO pfi_rebreavis (tipusnotificacioid, usuarientitatid, id) VALUES (20, 'sonespases_dest1', 93600);
INSERT INTO pfi_rebreavis (tipusnotificacioid, usuarientitatid, id) VALUES (60, 'sonespases_soli1', 93601);
INSERT INTO pfi_rebreavis (tipusnotificacioid, usuarientitatid, id) VALUES (70, 'sonespases_soli1', 93602);


--
-- TOC entry 2244 (class 0 OID 69626)
-- Dependencies: 158
-- Data for Name: pfi_role; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_ADMIN', 'Administrador PortaFIB', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_DEST', 'Destinatari', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_DELE', 'Delegat', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_COLA', 'Col·laborador', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_ADEN', 'Administrador d''Entitat', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_SOLI', 'Sol·licitant', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('PFI_ADMIN', 'Rol Administrador per Usuaris Aplicació', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('PFI_USER', 'Rol Bàsic per Usuaris Aplicació', NULL);


--
-- TOC entry 2246 (class 0 OID 69647)
-- Dependencies: 160
-- Data for Name: pfi_roleusuariaplicacio; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_roleusuariaplicacio (usuariaplicacioid, roleid, id) VALUES ('fundaciobit_usrapp', 'PFI_ADMIN', 98663);
INSERT INTO pfi_roleusuariaplicacio (usuariaplicacioid, roleid, id) VALUES ('fundaciobit_usrapp', 'PFI_USER', 98664);
INSERT INTO pfi_roleusuariaplicacio (usuariaplicacioid, roleid, id) VALUES ('anadalapp', 'PFI_USER', 105150);
INSERT INTO pfi_roleusuariaplicacio (usuariaplicacioid, roleid, id) VALUES ('fundaciobithola', 'PFI_ADMIN', 105151);


--
-- TOC entry 2245 (class 0 OID 69638)
-- Dependencies: 159
-- Data for Name: pfi_roleusuarientitat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_anadal', 73);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'fundaciobit_anadal', 75);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_COLA', 'fundaciobit_anadal', 76);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'fundaciobit_anadal', 77);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'caib_anadal', 79);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_dest1', 5200);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'fundaciobit_dele1', 5400);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_COLA', 'fundaciobit_cola2', 5850);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_COLA', 'fundaciobit_cola1', 5851);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_COLA', 'caib_cola2', 5853);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_dest2', 6650);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'caib_marilen', 11600);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'caib_marilen', 11601);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'fundaciobit_marilen', 11602);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_COLA', 'fundaciobit_marilen', 11604);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'fundaciobit_marilen', 11605);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_marilen', 11606);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'fundaciobit_marilen', 11607);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'fundaciobit_dboerner', 23350);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'fundaciobit_dboerner', 28450);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_secretari', 28451);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_pcomite', 28452);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_president', 28453);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'fundaciobit_soli1', 30300);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'caib_anadal', 31650);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'fundaciobit_anadal', 31651);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'fundaciobit_aden1', 34500);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_dboerner', 35400);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'fundaciobit_provamarilen', 49050);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'caib_aden2', 60150);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'caib_soli2', 60151);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'fundaciobit_president', 61200);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'fundaciobit_dest2', 74000);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_fsalas', 82700);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_coordinador', 85650);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'fundaciobit_des', 85950);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_des', 86100);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'sonespases_aden3', 92900);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'sonespases_soli1', 93000);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'sonespases_anadal', 93450);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'sonespases_dest1', 93451);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'sonespases_anadal', 93452);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_COLA', 'sonespases_anadal', 93453);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_soli1', 108050);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_cola2', 108051);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_cola1', 108052);


--
-- TOC entry 2247 (class 0 OID 69652)
-- Dependencies: 161
-- Data for Name: pfi_tipusdocument; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (2, 'Document de decisió de tipus Acord', 'tipusdocument.TD02', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (3, 'Document de decisió de tipus Contracte', 'tipusdocument.TD03', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (4, 'Document de decisió de tipus Conveni', 'tipusdocument.TD04', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (5, 'Document de decisió de tipus Declaració', 'tipusdocument.TD05', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (6, 'Document de transmissió de tipus Comunicació', 'tipusdocument.TD06', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (7, 'Document de transmissió de tipus Notificació', 'tipusdocument.TD07', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (8, 'Document de transmissió de tipus Publicació', 'tipusdocument.TD08', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (9, 'Document de transmissió de tipus Justificant de recepció', 'tipusdocument.TD09', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (10, 'Document de constància de tipus Acta', 'tipusdocument.TD10', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (11, 'Document de constància de tipus Certificat', 'tipusdocument.TD11', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (12, 'Document de constància de tipus Diligència', 'tipusdocument.TD12', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (13, 'Document de judici de tipus Informe', 'tipusdocument.TD13', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (14, 'Document de ciutadà de tipus Sol.licitud', 'tipusdocument.TD14', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (15, 'Document de ciutadà de tipus Denúncia', 'tipusdocument.TD15', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (16, 'Document de ciutadà de tipus Al.legació', 'tipusdocument.TD16', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (17, 'Document de ciutadà de tipus Recurs', 'tipusdocument.TD17', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (18, 'Document de ciutadà de tipus Comunicació al ciudadà', 'tipusdocument.TD18', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (19, 'Document de ciutadà de tipus Factura', 'tipusdocument.TD19', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (20, 'Document de ciutadà de tipus Altres confiscats', 'tipusdocument.TD20', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (21, 'Altres tipus de documents', 'tipusdocument.TD99', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (1, 'Document de decisió de tipus Resolució', 'tipusdocument.TD01', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (999, 'Tipo estándar', 'Altres', 'anadalapp');


--
-- TOC entry 2248 (class 0 OID 69661)
-- Dependencies: 162
-- Data for Name: pfi_tipusdocumentcoladele; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusdocumentcoladele (colaboraciodelegacioid, tipusdocumentid, id) VALUES (93700, 17, 93750);
INSERT INTO pfi_tipusdocumentcoladele (colaboraciodelegacioid, tipusdocumentid, id) VALUES (93700, 8, 93751);
INSERT INTO pfi_tipusdocumentcoladele (colaboraciodelegacioid, tipusdocumentid, id) VALUES (93700, 14, 93752);


--
-- TOC entry 2261 (class 0 OID 117254)
-- Dependencies: 175
-- Data for Name: pfi_tipusestatdefirmafinal; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, nom, descripcio) VALUES (0, 'tipusestatdefirmafinal.VALIDAT', NULL);
INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, nom, descripcio) VALUES (1, 'tipusestatdefirmafinal.INVALIDAT', NULL);
INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, nom, descripcio) VALUES (2, 'tipusestatdefirmafinal.FIRMAT', NULL);
INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, nom, descripcio) VALUES (3, 'tipusestatdefirmafinal.REBUTJAT', NULL);
INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, nom, descripcio) VALUES (4, 'tipusestatdefirmafinal.DESCARTAT', 'Es pasa a aquest estat quan la tasca encomnada ha sigut realitzada per un altra persona');


--
-- TOC entry 2249 (class 0 OID 69666)
-- Dependencies: 163
-- Data for Name: pfi_tipusestatdefirmainicial; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusestatdefirmainicial (tipusestatdefirmainicialid, descripcio, nom) VALUES (0, NULL, 'tipusestatdefirmainicial.ASSIGNAT_PER_VALIDAR');
INSERT INTO pfi_tipusestatdefirmainicial (tipusestatdefirmainicialid, descripcio, nom) VALUES (1, NULL, 'tipusestatdefirmainicial.ASSIGNAT_PER_FIRMAR');
INSERT INTO pfi_tipusestatdefirmainicial (tipusestatdefirmainicialid, descripcio, nom) VALUES (2, NULL, 'tipusestatdefirmainicial.REVISANT_PER_VALIDAR');


--
-- TOC entry 2250 (class 0 OID 69672)
-- Dependencies: 164
-- Data for Name: pfi_tipusestatpeticiodefirma; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (0, NULL, 'tipusestatpeticiodefirma.noiniciat');
INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (1, NULL, 'tipusestatpeticiodefirma.enprocess');
INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (2, NULL, 'tipusestatpeticiodefirma.pausat');
INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (3, NULL, 'tipusestatpeticiodefirma.rebutjat');
INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (4, NULL, 'tipusestatpeticiodefirma.firmat');


--
-- TOC entry 2251 (class 0 OID 69681)
-- Dependencies: 165
-- Data for Name: pfi_tipusfirma; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusfirma (tipusfirmaid, descripcio, nom, suportada) VALUES (2, '<p>CADES</p>', 'CADES', false);
INSERT INTO pfi_tipusfirma (tipusfirmaid, descripcio, nom, suportada) VALUES (0, '<p>PADES</p>', 'PADES', true);
INSERT INTO pfi_tipusfirma (tipusfirmaid, descripcio, nom, suportada) VALUES (1, '<p>XADES</p>', 'XADES', false);


--
-- TOC entry 2252 (class 0 OID 69690)
-- Dependencies: 166
-- Data for Name: pfi_tipusnotificacio; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (10, NULL, 'notificacioavis.requerit_per_validar', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (20, NULL, 'notificacioavis.requerit_per_firmar', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (30, NULL, 'notificacioavis.validat', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (40, NULL, 'notificacioavis.invalidat', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (50, NULL, 'notificacioavis.firma_parcial', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (15, NULL, 'notificacioavis.descartat_per_validar', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (25, NULL, 'notificacioavis.descartat_per_firmar', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (60, NULL, 'notificacioavis.firma_total', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (70, NULL, 'notificacioavis.rebutjat', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (0, NULL, 'notificacioavis.peticio_en_proces', NULL);


--
-- TOC entry 2255 (class 0 OID 69715)
-- Dependencies: 169
-- Data for Name: pfi_usuariaplicacio; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_usuariaplicacio (contrasenya, entitatid, emailadmin, callbackurl, descripcio, callbackversio, actiu, idiomaid, usuariaplicacioid, logosegellid, potcustodiar) VALUES ('caibapp', 'caib', 'anadal@ibit.org', 'dfsghdfghdfh', NULL, 1, true, 'ca', 'caibapp', NULL, false);
INSERT INTO pfi_usuariaplicacio (contrasenya, entitatid, emailadmin, callbackurl, descripcio, callbackversio, actiu, idiomaid, usuariaplicacioid, logosegellid, potcustodiar) VALUES ('sonespases_app', 'sonespases', 'anadal@fundaciobit.org', 'http://ww.sonespases.es', 'sdfgsdfgsdfg', 1, true, 'ca', 'sonespases_app', NULL, false);
INSERT INTO pfi_usuariaplicacio (contrasenya, entitatid, emailadmin, callbackurl, descripcio, callbackversio, actiu, idiomaid, usuariaplicacioid, logosegellid, potcustodiar) VALUES ('fundaciobit_senserol', 'fundaciobit', 'anadal@fundaciobit.org', 'http://localhost:8080/portafirmascb/web/services/MCGDWS', 'sdfgsdg', 1, true, 'ca', 'fundaciobit_senserol', NULL, false);
INSERT INTO pfi_usuariaplicacio (contrasenya, entitatid, emailadmin, callbackurl, descripcio, callbackversio, actiu, idiomaid, usuariaplicacioid, logosegellid, potcustodiar) VALUES ('fundaciobit_usrapp', 'fundaciobit', 'anadal@ibit.org', 'http://localhost:8080/portafirmascb/web/services/MCGDWS', NULL, 1, true, 'ca', 'fundaciobit_usrapp', NULL, false);
INSERT INTO pfi_usuariaplicacio (contrasenya, entitatid, emailadmin, callbackurl, descripcio, callbackversio, actiu, idiomaid, usuariaplicacioid, logosegellid, potcustodiar) VALUES ('fundaciobithola', 'fundaciobit', 'anadal@ibit.org', 'http://localhost:8080/portafirmascb/web/services/MCGDWS', 'sdfgsafgsdfg', 1, true, 'ca', 'fundaciobithola', NULL, false);
INSERT INTO pfi_usuariaplicacio (contrasenya, entitatid, emailadmin, callbackurl, descripcio, callbackversio, actiu, idiomaid, usuariaplicacioid, logosegellid, potcustodiar) VALUES ('anadalapp', 'fundaciobit', 'anadal@ibit.org', 'http://localhost:8080/portafirmascb/web/services/MCGDWS', 'asdfsdfsdf', 1, true, 'ca', 'anadalapp', NULL, true);


--
-- TOC entry 2254 (class 0 OID 69706)
-- Dependencies: 168
-- Data for Name: pfi_usuarientitat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_aden2', 'aden2', 'caib', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_cola2', 'cola2', 'caib', 'dcghdfghdfgh', true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_dele2', 'dele2', 'caib', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_dest2', 'dest2', 'caib', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_aden1', 'aden1', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_cola1', 'cola1', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_cola2', 'cola2', 'fundaciobit', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_dele1', 'dele1', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_dest2', 'dest2', 'fundaciobit', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_anadal', 'anadal', 'caib', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_secretari', 'anadal', 'fundaciobit', NULL, true, false, 'Secretari', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_provamarilen', 'provamarilen', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_president', 'anadal', 'fundaciobit', NULL, true, false, 'President', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_soli2', 'soli2', 'caib', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_dboerner', 'dboerner', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_convidat', 'convidat', 'fundaciobit', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_marilen', 'mgonzalez', 'caib', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_marilen', 'mgonzalez', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_pcomite', 'mgonzalez', 'fundaciobit', NULL, true, false, 'President comitè', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_fsalas', 'fsalas', 'fundaciobit', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_des', 'des', 'fundaciobit', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_coordinador', 'anadal', 'fundaciobit', NULL, true, false, 'Coordinador', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_soli1', 'soli1', 'fundaciobit', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_desactivar', 'des', 'fundaciobit', NULL, false, false, 'DESACTIVAR', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_aden3', 'aden3', 'sonespases', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_dele1', 'dele1', 'sonespases', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_cola1', 'cola1', 'sonespases', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_cap_radiologia', 'dest1', 'sonespases', NULL, true, false, 'Cap Radiologia', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_gerent', 'dest1', 'sonespases', NULL, true, false, 'Gerent Hospital', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_ajudant_radiologia', 'anadal', 'sonespases', NULL, true, false, 'Ajudant Radiologia', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_soli1', 'soli1', 'sonespases', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_anadal', 'anadal', 'sonespases', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_dest1', 'dest1', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_dest1', 'dest1', 'sonespases', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_anadal', 'anadal', 'fundaciobit', 'anadal@ibit.org', true, true, NULL, false, NULL, true);


--
-- TOC entry 2260 (class 0 OID 109116)
-- Dependencies: 174
-- Data for Name: pfi_usuarientitatfavorit; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_anadal', 'fundaciobit_cola1', 4902);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_anadal', 'fundaciobit_dest2', 131);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_anadal', 'fundaciobit_cola2', 23800);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_dest1', 'fundaciobit_dele1', 30950);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_dest1', 'fundaciobit_cola1', 30951);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_anadal', 'fundaciobit_soli1', 31450);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_dest1', 'fundaciobit_cola2', 31451);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_provamarilen', 'fundaciobit_provamarilen', 49000);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_anadal', 'fundaciobit_dest1', 50150);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_soli1', 'fundaciobit_dest1', 74551);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_soli1', 'fundaciobit_anadal', 75950);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_convidat', 'fundaciobit_convidat', 80100);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_fsalas', 'fundaciobit_fsalas', 82350);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_des', 'fundaciobit_des', 85750);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_soli1', 'sonespases_soli1', 92950);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_dest1', 'sonespases_dest1', 92951);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_dele1', 'sonespases_dele1', 92952);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_cola1', 'sonespases_cola1', 92953);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_soli1', 'sonespases_dest1', 92954);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_anadal', 'sonespases_anadal', 92955);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_soli1', 'sonespases_anadal', 92956);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_dest1', 'sonespases_anadal', 93650);


--
-- TOC entry 2253 (class 0 OID 69699)
-- Dependencies: 167
-- Data for Name: pfi_usuaripersona; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('dboerner', 'Daniel', 'Boerner', 'dboerner@ibit.org', 'X0468112Q', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('dele1', 'Delegat', 'deFundacioBit', 'anadal@ibit.org', '00000001D', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('cola1', 'Colaborador', 'deFundacioBit', 'anadal@ibit.org', '00000001C', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('dest1', 'Destinatari', 'deFundacioBit', 'anadal@ibit.org', '00000001T', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('cola2', 'Colaborador', 'deCaib', 'anadal@ibit.org', '00000002C', 'es', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('dest2', 'Destinatari', 'deCaib', 'anadal@ibit.org', '00000002T', 'es', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('dele2', 'Delegat', 'deCaib', 'anadal@ibit.org', '00000002D', 'es', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('aden1', 'Administrador-Entitat', 'deFundacioBit', 'anadal@ibit.org', '00000001E', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('aden2', 'Administrador-Entitat', 'deCaib', 'anadal@ibit.org', '00000002E', 'es', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('provamarilen', 'Marilen', 'Gonzi Gonzi', 'mgonzalez@ibit.org', '11222333D', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('soli2', 'Solicitant', 'deCaib', 'anadal@ibit.org', '00000002S', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('fsalas', 'Felip', 'Salas', 'fsalas@ibit.org', '18225456A', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('admin', 'Administrador', 'dePortafib', 'portafib@portafib.org', '12345678Z', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('convidat', 'Convidat', 'deFundacioBit', 'anadal@ibit.org', '00000003S', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('mgonzalez', 'Marilen', 'Gonzalez', 'mgonzalez@ibit.org', '44328254D', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('des', 'Usuari', 'Desactivat', 'anadal@ibit.org', '00000001N', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('anadal', 'Antoni', 'Nadal Bennasar', 'anadal@ibit.org', '43096845C', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('aden3', 'Admin Entitat', 'deSonEspases', 'anadal@fundaciobit.org', '00000003E', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('soli1', 'Solicitant', 'deFundacioBit', 'anadal@fundaciobit.org', '00000001S', 'ca', NULL);


--
-- TOC entry 1922 (class 2606 OID 70224)
-- Name: annex_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_annex
    ADD CONSTRAINT annex_pkey PRIMARY KEY (annexid);


--
-- TOC entry 1927 (class 2606 OID 70241)
-- Name: annexfirmat_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT annexfirmat_pkey PRIMARY KEY (annexfirmatid);


--
-- TOC entry 1930 (class 2606 OID 70263)
-- Name: bitacola_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_bitacola
    ADD CONSTRAINT bitacola_pkey PRIMARY KEY (bitacolaid);


--
-- TOC entry 1934 (class 2606 OID 70272)
-- Name: blocdefirmes_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_blocdefirmes
    ADD CONSTRAINT blocdefirmes_pkey PRIMARY KEY (blocdefirmesid);


--
-- TOC entry 2061 (class 2606 OID 191671)
-- Name: codibarres_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_codibarres
    ADD CONSTRAINT codibarres_pk PRIMARY KEY (codibarresid);


--
-- TOC entry 1939 (class 2606 OID 70285)
-- Name: colaboraciodelegacio_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT colaboraciodelegacio_pkey PRIMARY KEY (colaboraciodelegacioid);


--
-- TOC entry 2057 (class 2606 OID 191660)
-- Name: custodiainfo_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT custodiainfo_pk PRIMARY KEY (custodiainfoid);


--
-- TOC entry 1941 (class 2606 OID 69528)
-- Name: entitat_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT entitat_pkey PRIMARY KEY (entitatid);


--
-- TOC entry 1945 (class 2606 OID 69534)
-- Name: estatdefirma_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT estatdefirma_pkey PRIMARY KEY (estatdefirmaid);


--
-- TOC entry 1952 (class 2606 OID 70303)
-- Name: firma_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT firma_pkey PRIMARY KEY (firmaid);


--
-- TOC entry 1954 (class 2606 OID 70326)
-- Name: fitxer_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_fitxer
    ADD CONSTRAINT fitxer_pkey PRIMARY KEY (fitxerid);


--
-- TOC entry 1956 (class 2606 OID 70361)
-- Name: fluxdefirmes_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_fluxdefirmes
    ADD CONSTRAINT fluxdefirmes_pkey PRIMARY KEY (fluxdefirmesid);


--
-- TOC entry 2029 (class 2606 OID 96051)
-- Name: fluxdefirmesusuaripersona_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT fluxdefirmesusuaripersona_pkey PRIMARY KEY (fluxdefirmesid);


--
-- TOC entry 2043 (class 2606 OID 191433)
-- Name: grupentitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitat
    ADD CONSTRAINT grupentitat_pk PRIMARY KEY (grupentitatid);


--
-- TOC entry 2049 (class 2606 OID 191458)
-- Name: grupentitatusuarientitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT grupentitatusuarientitat_pk PRIMARY KEY (grupentitatusuarientitatid);


--
-- TOC entry 2031 (class 2606 OID 96099)
-- Name: idioma_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_idioma
    ADD CONSTRAINT idioma_pkey PRIMARY KEY (idiomaid);


--
-- TOC entry 1959 (class 2606 OID 70382)
-- Name: metadada_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_metadada
    ADD CONSTRAINT metadada_pkey PRIMARY KEY (metadadaid);


--
-- TOC entry 1961 (class 2606 OID 69582)
-- Name: mitjadecomunicacio_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_mitjadecomunicacio
    ADD CONSTRAINT mitjadecomunicacio_pkey PRIMARY KEY (mitjadecomunicacioid);


--
-- TOC entry 1964 (class 2606 OID 69591)
-- Name: notificacio_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_notificacio
    ADD CONSTRAINT notificacio_pkey PRIMARY KEY (notificacioid);


--
-- TOC entry 2045 (class 2606 OID 191438)
-- Name: permisgrupentitatflux_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT permisgrupentitatflux_pk PRIMARY KEY (permisgrupplantillaid);


--
-- TOC entry 2047 (class 2606 OID 191628)
-- Name: permisgrupentitatflux_unique; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT permisgrupentitatflux_unique UNIQUE (grupentitatid, fluxdefirmesid);


--
-- TOC entry 2053 (class 2606 OID 191469)
-- Name: permisusuarientitatflux_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT permisusuarientitatflux_pk PRIMARY KEY (permisusuariplantillaid);


--
-- TOC entry 2055 (class 2606 OID 191630)
-- Name: permisusuarientitatflux_unique; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT permisusuarientitatflux_unique UNIQUE (usuarientitatid, fluxdefirmesid);


--
-- TOC entry 1971 (class 2606 OID 70411)
-- Name: peticiodefirma_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT peticiodefirma_pkey PRIMARY KEY (peticiodefirmaid);


--
-- TOC entry 1980 (class 2606 OID 84445)
-- Name: peticiodefirmausuarientitat_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_peticiofirmausuarientitat
    ADD CONSTRAINT peticiodefirmausuarientitat_pkey PRIMARY KEY (peticiodefirmaid);


--
-- TOC entry 2051 (class 2606 OID 191626)
-- Name: pfi_grupentitatusuarientitat_unique; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupentitatusuarientitat_unique UNIQUE (usuarientitatid, grupentitatid);


--
-- TOC entry 1978 (class 2606 OID 133628)
-- Name: pfi_peticiodefirma_fluxdefirmesid_unique; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_peticiodefirma_fluxdefirmesid_unique UNIQUE (fluxdefirmesid);


--
-- TOC entry 2037 (class 2606 OID 109148)
-- Name: pfi_usuarientitatfavorit_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_usuarientitatfavorit_pkey PRIMARY KEY (id);


--
-- TOC entry 2017 (class 2606 OID 100831)
-- Name: pfi_usuaripersona_nif_unique; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_usuaripersona_nif_unique UNIQUE (nif);


--
-- TOC entry 2059 (class 2606 OID 191673)
-- Name: posiciopagina_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_posiciopagina
    ADD CONSTRAINT posiciopagina_pk PRIMARY KEY (posiciopaginaid);


--
-- TOC entry 2033 (class 2606 OID 109045)
-- Name: posiciotaulafirmesid_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_posiciotaulafirmes
    ADD CONSTRAINT posiciotaulafirmesid_pkey PRIMARY KEY (posiciotaulafirmesid);


--
-- TOC entry 2035 (class 2606 OID 109115)
-- Name: prioritat_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_prioritat
    ADD CONSTRAINT prioritat_pkey PRIMARY KEY (prioritatid);


--
-- TOC entry 1984 (class 2606 OID 117201)
-- Name: rebreavis_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT rebreavis_pkey PRIMARY KEY (id);


--
-- TOC entry 1987 (class 2606 OID 191292)
-- Name: rebreavis_unique_key; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT rebreavis_unique_key UNIQUE (tipusnotificacioid, usuarientitatid);


--
-- TOC entry 1990 (class 2606 OID 69632)
-- Name: role_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_role
    ADD CONSTRAINT role_pkey PRIMARY KEY (roleid);


--
-- TOC entry 1997 (class 2606 OID 117232)
-- Name: roleusuariaplicacio_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT roleusuariaplicacio_pkey PRIMARY KEY (id);


--
-- TOC entry 1999 (class 2606 OID 117234)
-- Name: roleusuariaplicacio_unique_fkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT roleusuariaplicacio_unique_fkey UNIQUE (usuariaplicacioid, roleid);


--
-- TOC entry 1992 (class 2606 OID 117222)
-- Name: roleusuarientitat_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT roleusuarientitat_pkey PRIMARY KEY (id);


--
-- TOC entry 1994 (class 2606 OID 191375)
-- Name: roleusuarientitat_unique_key; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT roleusuarientitat_unique_key UNIQUE (roleid, usuarientitatid);


--
-- TOC entry 2002 (class 2606 OID 69660)
-- Name: tipusdocument_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusdocument
    ADD CONSTRAINT tipusdocument_pkey PRIMARY KEY (tipusdocumentid);


--
-- TOC entry 2004 (class 2606 OID 117210)
-- Name: tipusdocumentpercolaboraciodelegacio_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT tipusdocumentpercolaboraciodelegacio_pkey PRIMARY KEY (id);


--
-- TOC entry 2007 (class 2606 OID 117212)
-- Name: tipusdocumentpercolaboraciodelegacio_unique_key; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT tipusdocumentpercolaboraciodelegacio_unique_key UNIQUE (colaboraciodelegacioid, tipusdocumentid);


--
-- TOC entry 2009 (class 2606 OID 69671)
-- Name: tipusestatdefirma_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusestatdefirmainicial
    ADD CONSTRAINT tipusestatdefirma_pkey PRIMARY KEY (tipusestatdefirmainicialid);


--
-- TOC entry 2041 (class 2606 OID 117258)
-- Name: tipusestatdefirmafinal_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusestatdefirmafinal
    ADD CONSTRAINT tipusestatdefirmafinal_pkey PRIMARY KEY (tipusestatdefirmafinalid);


--
-- TOC entry 2011 (class 2606 OID 69680)
-- Name: tipusestatpeticiodefirma_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusestatpeticiodefirma
    ADD CONSTRAINT tipusestatpeticiodefirma_pkey PRIMARY KEY (tipusestatpeticiodefirmaid);


--
-- TOC entry 2013 (class 2606 OID 69689)
-- Name: tipusfirma_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusfirma
    ADD CONSTRAINT tipusfirma_pkey PRIMARY KEY (tipusfirmaid);


--
-- TOC entry 2015 (class 2606 OID 69698)
-- Name: tipusnotificacio_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_tipusnotificacio
    ADD CONSTRAINT tipusnotificacio_pkey PRIMARY KEY (tipusnotificacioid);


--
-- TOC entry 2027 (class 2606 OID 191527)
-- Name: usuariaplicacio_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT usuariaplicacio_pkey PRIMARY KEY (usuariaplicacioid);


--
-- TOC entry 2022 (class 2606 OID 191227)
-- Name: usuarientitat_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT usuarientitat_pkey PRIMARY KEY (usuarientitatid);


--
-- TOC entry 2024 (class 2606 OID 133600)
-- Name: usuarientitat_unique; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT usuarientitat_unique UNIQUE (usuaripersonaid, entitatid, carrec);


--
-- TOC entry 2039 (class 2606 OID 191362)
-- Name: usuarientitatfavorit_unique_key; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT usuarientitatfavorit_unique_key UNIQUE (origenid, favoritid);


--
-- TOC entry 2019 (class 2606 OID 69705)
-- Name: usuaripersona_pkey; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT usuaripersona_pkey PRIMARY KEY (usuaripersonaid);


--
-- TOC entry 1919 (class 1259 OID 69727)
-- Name: annex_fitxerid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX annex_fitxerid_idx ON pfi_annex USING btree (fitxerid);


--
-- TOC entry 1920 (class 1259 OID 69728)
-- Name: annex_peticiodefirmaid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX annex_peticiodefirmaid_idx ON pfi_annex USING btree (peticiodefirmaid);


--
-- TOC entry 1923 (class 1259 OID 69740)
-- Name: annexfirmat_annexid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX annexfirmat_annexid_idx ON pfi_annexfirmat USING btree (annexid);


--
-- TOC entry 1924 (class 1259 OID 69739)
-- Name: annexfirmat_firmaid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX annexfirmat_firmaid_idx ON pfi_annexfirmat USING btree (firmaid);


--
-- TOC entry 1925 (class 1259 OID 69741)
-- Name: annexfirmat_fitxerid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX annexfirmat_fitxerid_idx ON pfi_annexfirmat USING btree (fitxerid);


--
-- TOC entry 1928 (class 1259 OID 69966)
-- Name: bitacola_peticiodefirmaid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX bitacola_peticiodefirmaid_idx ON pfi_bitacola USING btree (peticiodefirmaid);


--
-- TOC entry 1931 (class 1259 OID 191305)
-- Name: bitacola_usuarientitatid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX bitacola_usuarientitatid_idx ON pfi_bitacola USING btree (usuarientitatid);


--
-- TOC entry 1932 (class 1259 OID 69978)
-- Name: blocdefirmes_fluxdefirmesid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX blocdefirmes_fluxdefirmesid_idx ON pfi_blocdefirmes USING btree (fluxdefirmesid);


--
-- TOC entry 1935 (class 1259 OID 191333)
-- Name: colaboraciodelegacio_colaboradordelegatid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX colaboraciodelegacio_colaboradordelegatid_idx ON pfi_colaboraciodelegacio USING btree (colaboradordelegatid);


--
-- TOC entry 1936 (class 1259 OID 191317)
-- Name: colaboraciodelegacio_destinatariid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX colaboraciodelegacio_destinatariid_idx ON pfi_colaboraciodelegacio USING btree (destinatariid);


--
-- TOC entry 1937 (class 1259 OID 69986)
-- Name: colaboraciodelegacio_fitxerautoritzacioid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX colaboraciodelegacio_fitxerautoritzacioid_idx ON pfi_colaboraciodelegacio USING btree (fitxerautoritzacioid);


--
-- TOC entry 1942 (class 1259 OID 69907)
-- Name: estatdefirma_estatdefirmaid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX estatdefirma_estatdefirmaid_idx ON pfi_estatdefirma USING btree (estatdefirmaid);


--
-- TOC entry 1943 (class 1259 OID 69906)
-- Name: estatdefirma_firmaid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX estatdefirma_firmaid_idx ON pfi_estatdefirma USING btree (firmaid);


--
-- TOC entry 1946 (class 1259 OID 69905)
-- Name: estatdefirma_tipusestatdefirmaid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX estatdefirma_tipusestatdefirmaid_idx ON pfi_estatdefirma USING btree (tipusestatdefirmainicialid);


--
-- TOC entry 1947 (class 1259 OID 191403)
-- Name: estatdefirma_usuarientitatid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX estatdefirma_usuarientitatid_idx ON pfi_estatdefirma USING btree (usuarientitatid);


--
-- TOC entry 1948 (class 1259 OID 69926)
-- Name: firma_blocdefirmaid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX firma_blocdefirmaid_idx ON pfi_firma USING btree (blocdefirmaid);


--
-- TOC entry 1949 (class 1259 OID 191387)
-- Name: firma_destinatariid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX firma_destinatariid_idx ON pfi_firma USING btree (destinatariid);


--
-- TOC entry 1950 (class 1259 OID 69924)
-- Name: firma_fitxerid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX firma_fitxerid_idx ON pfi_firma USING btree (fitxerfirmatid);


--
-- TOC entry 1957 (class 1259 OID 69948)
-- Name: metadada_peticiodefirmaid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX metadada_peticiodefirmaid_idx ON pfi_metadada USING btree (peticiodefirmaid);


--
-- TOC entry 1962 (class 1259 OID 69955)
-- Name: notificacio_peticiodefirmaid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX notificacio_peticiodefirmaid_idx ON pfi_notificacio USING btree (peticiodefirmaid);


--
-- TOC entry 1965 (class 1259 OID 69954)
-- Name: notificacio_tipusnotificacioid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX notificacio_tipusnotificacioid_idx ON pfi_notificacio USING btree (tipusnotificacioid);


--
-- TOC entry 1966 (class 1259 OID 69834)
-- Name: peticiodefirma_descripcio_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX peticiodefirma_descripcio_idx ON pfi_peticiodefirma USING btree (descripcio);


--
-- TOC entry 1967 (class 1259 OID 69836)
-- Name: peticiodefirma_fitxerafirmarid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX peticiodefirma_fitxerafirmarid_idx ON pfi_peticiodefirma USING btree (fitxerafirmarid);


--
-- TOC entry 1968 (class 1259 OID 69837)
-- Name: peticiodefirma_fluxdefirmesid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX peticiodefirma_fluxdefirmesid_idx ON pfi_peticiodefirma USING btree (fluxdefirmesid);


--
-- TOC entry 1969 (class 1259 OID 69833)
-- Name: peticiodefirma_motiuderebuig_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX peticiodefirma_motiuderebuig_idx ON pfi_peticiodefirma USING btree (motiuderebuig);


--
-- TOC entry 1972 (class 1259 OID 69839)
-- Name: peticiodefirma_tipusdocumentid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX peticiodefirma_tipusdocumentid_idx ON pfi_peticiodefirma USING btree (tipusdocumentid);


--
-- TOC entry 1973 (class 1259 OID 69838)
-- Name: peticiodefirma_tipusestatpeticiodefirmaid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX peticiodefirma_tipusestatpeticiodefirmaid_idx ON pfi_peticiodefirma USING btree (tipusestatpeticiodefirmaid);


--
-- TOC entry 1974 (class 1259 OID 69832)
-- Name: peticiodefirma_tipusfirmaid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX peticiodefirma_tipusfirmaid_idx ON pfi_peticiodefirma USING btree (tipusfirmaid);


--
-- TOC entry 1975 (class 1259 OID 69835)
-- Name: peticiodefirma_titol_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX peticiodefirma_titol_idx ON pfi_peticiodefirma USING btree (titol);


--
-- TOC entry 1976 (class 1259 OID 191565)
-- Name: peticiodefirma_usuarimaquinaid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX peticiodefirma_usuarimaquinaid_idx ON pfi_peticiodefirma USING btree (usuariaplicacioid);


--
-- TOC entry 1981 (class 1259 OID 117235)
-- Name: peticiodefirmausuarientitat_receptordenotificacioid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX peticiodefirmausuarientitat_receptordenotificacioid_idx ON pfi_peticiofirmausuarientitat USING btree (mitjadecomunicacioid);


--
-- TOC entry 1982 (class 1259 OID 191417)
-- Name: peticiodefirmausuarientitat_usuarientitatid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX peticiodefirmausuarientitat_usuarientitatid_idx ON pfi_peticiofirmausuarientitat USING btree (usuarientitatid);


--
-- TOC entry 1985 (class 1259 OID 69893)
-- Name: rebreavis_tipusnotificacioid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX rebreavis_tipusnotificacioid_idx ON pfi_rebreavis USING btree (tipusnotificacioid);


--
-- TOC entry 1988 (class 1259 OID 191290)
-- Name: rebreavis_usuarientitatid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX rebreavis_usuarientitatid_idx ON pfi_rebreavis USING btree (usuarientitatid);


--
-- TOC entry 1995 (class 1259 OID 191373)
-- Name: roleusuarientitat_usuarientitatid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX roleusuarientitat_usuarientitatid_idx ON pfi_roleusuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2000 (class 1259 OID 69815)
-- Name: roleusuarimaquina_roleid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX roleusuarimaquina_roleid_idx ON pfi_roleusuariaplicacio USING btree (roleid);


--
-- TOC entry 2005 (class 1259 OID 69769)
-- Name: tipusdocumentpercolaboraciodelegacio_tipusdocumentid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX tipusdocumentpercolaboraciodelegacio_tipusdocumentid_idx ON pfi_tipusdocumentcoladele USING btree (tipusdocumentid);


--
-- TOC entry 2020 (class 1259 OID 69786)
-- Name: usuarientitat_entitatid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX usuarientitat_entitatid_idx ON pfi_usuarientitat USING btree (entitatid);


--
-- TOC entry 2025 (class 1259 OID 69787)
-- Name: usuarientitat_usuariid_idx; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX usuarientitat_usuariid_idx ON pfi_usuarientitat USING btree (usuaripersonaid);


--
-- TOC entry 2062 (class 2606 OID 70327)
-- Name: annex_fitxerid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annex
    ADD CONSTRAINT annex_fitxerid_fkey FOREIGN KEY (fitxerid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2063 (class 2606 OID 70412)
-- Name: annex_peticiodefirmaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annex
    ADD CONSTRAINT annex_peticiodefirmaid_fkey FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2064 (class 2606 OID 70225)
-- Name: annexfirmat_annexid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT annexfirmat_annexid_fkey FOREIGN KEY (annexid) REFERENCES pfi_annex(annexid);


--
-- TOC entry 2065 (class 2606 OID 70304)
-- Name: annexfirmat_firmaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT annexfirmat_firmaid_fkey FOREIGN KEY (firmaid) REFERENCES pfi_firma(firmaid);


--
-- TOC entry 2066 (class 2606 OID 70332)
-- Name: annexfirmat_fitxerid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_annexfirmat
    ADD CONSTRAINT annexfirmat_fitxerid_fkey FOREIGN KEY (fitxerid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2067 (class 2606 OID 70437)
-- Name: bitacola_peticiodefirmaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_bitacola
    ADD CONSTRAINT bitacola_peticiodefirmaid_fkey FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2068 (class 2606 OID 191306)
-- Name: bitacola_usuarientitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_bitacola
    ADD CONSTRAINT bitacola_usuarientitatid_fkey FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2069 (class 2606 OID 70367)
-- Name: blocdefirmes_fluxdefirmesid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_blocdefirmes
    ADD CONSTRAINT blocdefirmes_fluxdefirmesid_fkey FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_fluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2072 (class 2606 OID 191334)
-- Name: colaboraciodelegacio_colaboradordelegatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT colaboraciodelegacio_colaboradordelegatid_fkey FOREIGN KEY (colaboradordelegatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2071 (class 2606 OID 191318)
-- Name: colaboraciodelegacio_destinatariid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT colaboraciodelegacio_destinatariid_fkey FOREIGN KEY (destinatariid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2070 (class 2606 OID 70347)
-- Name: colaboraciodelegacio_fitxerautoritzacioid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_colaboraciodelegacio
    ADD CONSTRAINT colaboraciodelegacio_fitxerautoritzacioid_fkey FOREIGN KEY (fitxerautoritzacioid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2083 (class 2606 OID 70309)
-- Name: estatdefirma_firmaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT estatdefirma_firmaid_fkey FOREIGN KEY (firmaid) REFERENCES pfi_firma(firmaid);


--
-- TOC entry 2081 (class 2606 OID 69909)
-- Name: estatdefirma_tipusestatdefirmaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT estatdefirma_tipusestatdefirmaid_fkey FOREIGN KEY (tipusestatdefirmainicialid) REFERENCES pfi_tipusestatdefirmainicial(tipusestatdefirmainicialid);


--
-- TOC entry 2082 (class 2606 OID 191404)
-- Name: estatdefirma_usuarientitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT estatdefirma_usuarientitatid_fkey FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2086 (class 2606 OID 70273)
-- Name: firma_blocdefirmaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT firma_blocdefirmaid_fkey FOREIGN KEY (blocdefirmaid) REFERENCES pfi_blocdefirmes(blocdefirmesid);


--
-- TOC entry 2085 (class 2606 OID 191388)
-- Name: firma_destinatariid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT firma_destinatariid_fkey FOREIGN KEY (destinatariid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2087 (class 2606 OID 70342)
-- Name: firma_fitxerid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT firma_fitxerid_fkey FOREIGN KEY (fitxerfirmatid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2123 (class 2606 OID 191505)
-- Name: fluxdefirmesusuaripersona_fluxdefirmesid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT fluxdefirmesusuaripersona_fluxdefirmesid_fkey FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_fluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2124 (class 2606 OID 191601)
-- Name: fluxdefirmesusuaripersona_usuarientitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT fluxdefirmesusuaripersona_usuarientitatid_fkey FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2088 (class 2606 OID 70427)
-- Name: metadada_peticiodefirmaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_metadada
    ADD CONSTRAINT metadada_peticiodefirmaid_fkey FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2090 (class 2606 OID 70432)
-- Name: notificacio_peticiodefirmaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_notificacio
    ADD CONSTRAINT notificacio_peticiodefirmaid_fkey FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2089 (class 2606 OID 69956)
-- Name: notificacio_tipusnotificacioid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_notificacio
    ADD CONSTRAINT notificacio_tipusnotificacioid_fkey FOREIGN KEY (tipusnotificacioid) REFERENCES pfi_tipusnotificacio(tipusnotificacioid);


--
-- TOC entry 2100 (class 2606 OID 70337)
-- Name: peticiodefirma_fitxerafirmarid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT peticiodefirma_fitxerafirmarid_fkey FOREIGN KEY (fitxerafirmarid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2093 (class 2606 OID 109126)
-- Name: peticiodefirma_fitxertaulafirmesfitxersadjuntsid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT peticiodefirma_fitxertaulafirmesfitxersadjuntsid_fkey FOREIGN KEY (fitxeradaptatid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2101 (class 2606 OID 70362)
-- Name: peticiodefirma_fluxdefirmesid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT peticiodefirma_fluxdefirmesid_fkey FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_fluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2097 (class 2606 OID 69861)
-- Name: peticiodefirma_tipusdocumentid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT peticiodefirma_tipusdocumentid_fkey FOREIGN KEY (tipusdocumentid) REFERENCES pfi_tipusdocument(tipusdocumentid);


--
-- TOC entry 2096 (class 2606 OID 69856)
-- Name: peticiodefirma_tipusestatpeticiodefirmaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT peticiodefirma_tipusestatpeticiodefirmaid_fkey FOREIGN KEY (tipusestatpeticiodefirmaid) REFERENCES pfi_tipusestatpeticiodefirma(tipusestatpeticiodefirmaid);


--
-- TOC entry 2095 (class 2606 OID 69841)
-- Name: peticiodefirma_tipusfirmaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT peticiodefirma_tipusfirmaid_fkey FOREIGN KEY (tipusfirmaid) REFERENCES pfi_tipusfirma(tipusfirmaid);


--
-- TOC entry 2105 (class 2606 OID 70417)
-- Name: peticiodefirmausuarientitat_peticiodefirmaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiofirmausuarientitat
    ADD CONSTRAINT peticiodefirmausuarientitat_peticiodefirmaid_fkey FOREIGN KEY (peticiodefirmaid) REFERENCES pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2104 (class 2606 OID 191418)
-- Name: peticiodefirmausuarientitat_usuarientitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiofirmausuarientitat
    ADD CONSTRAINT peticiodefirmausuarientitat_usuarientitatid_fkey FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2139 (class 2606 OID 191717)
-- Name: pfi_custodiainfo_codibarresid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodiainfo_codibarresid_fkey FOREIGN KEY (codibarresid) REFERENCES pfi_codibarres(codibarresid);


--
-- TOC entry 2140 (class 2606 OID 191722)
-- Name: pfi_custodiainfo_codibarresposiciopaginaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodiainfo_codibarresposiciopaginaid_fkey FOREIGN KEY (codibarresposiciopaginaid) REFERENCES pfi_posiciopagina(posiciopaginaid);


--
-- TOC entry 2136 (class 2606 OID 191702)
-- Name: pfi_custodiainfo_entitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodiainfo_entitatid_fkey FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2135 (class 2606 OID 191733)
-- Name: pfi_custodiainfo_missatgeposiciopaginaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodiainfo_missatgeposiciopaginaid_fkey FOREIGN KEY (missatgeposiciopaginaid) REFERENCES pfi_posiciopagina(posiciopaginaid);


--
-- TOC entry 2138 (class 2606 OID 191712)
-- Name: pfi_custodiainfo_usuariaplicacioid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodiainfo_usuariaplicacioid_fkey FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2137 (class 2606 OID 191707)
-- Name: pfi_custodiainfo_usuarientitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_custodiainfo
    ADD CONSTRAINT pfi_custodiainfo_usuarientitatid_fkey FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2077 (class 2606 OID 179548)
-- Name: pfi_entitat_favicon_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_favicon_fkey FOREIGN KEY (faviconid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2076 (class 2606 OID 179543)
-- Name: pfi_entitat_logosegell_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_logosegell_fkey FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2075 (class 2606 OID 179538)
-- Name: pfi_entitat_logowebpeu_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_logowebpeu_fkey FOREIGN KEY (logowebpeuid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2074 (class 2606 OID 179533)
-- Name: pfi_entitat_logweb_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_logweb_fkey FOREIGN KEY (logowebid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2073 (class 2606 OID 158237)
-- Name: pfi_entitat_pdfdelegacioid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_pdfdelegacioid_fkey FOREIGN KEY (pdfautoritzaciodelegacioid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2078 (class 2606 OID 191589)
-- Name: pfi_entitat_usuariaplicacioperpeticions; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_entitat
    ADD CONSTRAINT pfi_entitat_usuariaplicacioperpeticions FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2080 (class 2606 OID 117249)
-- Name: pfi_estatdefirma_colaboraciodelegacioid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatdefirma_colaboraciodelegacioid_fkey FOREIGN KEY (colaboraciodelegacioid) REFERENCES pfi_colaboraciodelegacio(colaboraciodelegacioid);


--
-- TOC entry 2079 (class 2606 OID 117259)
-- Name: pfi_estatdefirma_tipusestatdefirmafinalid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_estatdefirma
    ADD CONSTRAINT pfi_estatdefirma_tipusestatdefirmafinalid_fkey FOREIGN KEY (tipusestatdefirmafinalid) REFERENCES pfi_tipusestatdefirmafinal(tipusestatdefirmafinalid);


--
-- TOC entry 2084 (class 2606 OID 175490)
-- Name: pfi_firma_tipusestatdefirmafinalid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_firma
    ADD CONSTRAINT pfi_firma_tipusestatdefirmafinalid_fkey FOREIGN KEY (tipusestatdefirmafinalid) REFERENCES pfi_tipusestatdefirmafinal(tipusestatdefirmafinalid);


--
-- TOC entry 2128 (class 2606 OID 191480)
-- Name: pfi_grupentitat_entitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_grupentitat
    ADD CONSTRAINT pfi_grupentitat_entitatid_fkey FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2131 (class 2606 OID 191485)
-- Name: pfi_grupentitatusuarientitat_grupentitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupentitatusuarientitat_grupentitatid_fkey FOREIGN KEY (grupentitatid) REFERENCES pfi_grupentitat(grupentitatid);


--
-- TOC entry 2132 (class 2606 OID 191490)
-- Name: pfi_grupentitatusuarientitat_usuarientitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupentitatusuarientitat_usuarientitatid_fkey FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2130 (class 2606 OID 191516)
-- Name: pfi_permisgrupentitatflux_fluxdefirmesid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrupentitatflux_fluxdefirmesid_fkey FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_plantillafluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2129 (class 2606 OID 191444)
-- Name: pfi_permisgrupentitatflux_grupentitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrupentitatflux_grupentitatid_fkey FOREIGN KEY (grupentitatid) REFERENCES pfi_grupentitat(grupentitatid);


--
-- TOC entry 2134 (class 2606 OID 191511)
-- Name: pfi_permisusuarientitatflux_fluxdefirmesid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisusuarientitatflux_fluxdefirmesid_fkey FOREIGN KEY (fluxdefirmesid) REFERENCES pfi_plantillafluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2133 (class 2606 OID 191470)
-- Name: pfi_permisusuarientitatflux_usuarientitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisusuarientitatflux_usuarientitatid_fkey FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2099 (class 2606 OID 191738)
-- Name: pfi_peticiodefirma_custodiainfoid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_peticiodefirma_custodiainfoid_fkey FOREIGN KEY (custodiainfoid) REFERENCES pfi_custodiainfo(custodiainfoid);


--
-- TOC entry 2091 (class 2606 OID 96119)
-- Name: pfi_peticiodefirma_idiomaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_peticiodefirma_idiomaid_fkey FOREIGN KEY (idiomaid) REFERENCES pfi_idioma(idiomaid);


--
-- TOC entry 2092 (class 2606 OID 187716)
-- Name: pfi_peticiodefirma_logosegellid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_peticiodefirma_logosegellid_fkey FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2094 (class 2606 OID 109046)
-- Name: pfi_peticiodefirma_posiciotaulafirmesid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_peticiodefirma_posiciotaulafirmesid_fkey FOREIGN KEY (posiciotaulafirmesid) REFERENCES pfi_posiciotaulafirmes(posiciotaulafirmesid);


--
-- TOC entry 2102 (class 2606 OID 109131)
-- Name: pfi_peticiodefirma_prioritat_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_peticiodefirma_prioritat_fkey FOREIGN KEY (prioritatid) REFERENCES pfi_prioritat(prioritatid);


--
-- TOC entry 2098 (class 2606 OID 191566)
-- Name: pfi_peticiodefirma_usuariaplicacioid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiodefirma
    ADD CONSTRAINT pfi_peticiodefirma_usuariaplicacioid_fkey FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2103 (class 2606 OID 117242)
-- Name: pfi_peticiodefirmausuarientitat_mitjadecomunicacioid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_peticiofirmausuarientitat
    ADD CONSTRAINT pfi_peticiodefirmausuarientitat_mitjadecomunicacioid_fkey FOREIGN KEY (mitjadecomunicacioid) REFERENCES pfi_mitjadecomunicacio(mitjadecomunicacioid);


--
-- TOC entry 2125 (class 2606 OID 191613)
-- Name: pfi_plantillafluxdefirmes_usuariaplicacioid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantillafluxdefirmes_usuariaplicacioid_fkey FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2110 (class 2606 OID 191533)
-- Name: pfi_roleusuariaplicacio_usuariaplicacioid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT pfi_roleusuariaplicacio_usuariaplicacioid_fkey FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2112 (class 2606 OID 191528)
-- Name: pfi_tipusdocument_usuariaplicacioid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdocument_usuariaplicacioid_fkey FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2120 (class 2606 OID 108998)
-- Name: pfi_usuariaplicacio_entitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usuariaplicacio_entitatid_fkey FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2122 (class 2606 OID 96124)
-- Name: pfi_usuariaplicacio_idiomaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usuariaplicacio_idiomaid_fkey FOREIGN KEY (idiomaid) REFERENCES pfi_idioma(idiomaid);


--
-- TOC entry 2121 (class 2606 OID 187711)
-- Name: pfi_usuariaplicacio_logosegellid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usuariaplicacio_logosegellid_fkey FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2117 (class 2606 OID 187706)
-- Name: pfi_usuarientitat_logosegellid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT pfi_usuarientitat_logosegellid_fkey FOREIGN KEY (logosegellid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2116 (class 2606 OID 96114)
-- Name: pfi_usuaripersona_idiomaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_usuaripersona_idiomaid_fkey FOREIGN KEY (idiomaid) REFERENCES pfi_idioma(idiomaid);


--
-- TOC entry 2115 (class 2606 OID 191636)
-- Name: pfi_usuaripersona_rubricaid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuaripersona
    ADD CONSTRAINT pfi_usuaripersona_rubricaid_fkey FOREIGN KEY (rubricaid) REFERENCES pfi_fitxer(fitxerid);


--
-- TOC entry 2106 (class 2606 OID 69900)
-- Name: rebreavis_tipusnotificacioid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT rebreavis_tipusnotificacioid_fkey FOREIGN KEY (tipusnotificacioid) REFERENCES pfi_tipusnotificacio(tipusnotificacioid);


--
-- TOC entry 2107 (class 2606 OID 191293)
-- Name: rebreavis_usuarientitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_rebreavis
    ADD CONSTRAINT rebreavis_usuarientitatid_fkey FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2108 (class 2606 OID 69805)
-- Name: roleusuarientitat_roleid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT roleusuarientitat_roleid_fkey FOREIGN KEY (roleid) REFERENCES pfi_role(roleid);


--
-- TOC entry 2109 (class 2606 OID 191376)
-- Name: roleusuarientitat_usuarientitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuarientitat
    ADD CONSTRAINT roleusuarientitat_usuarientitatid_fkey FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2111 (class 2606 OID 69816)
-- Name: roleusuarimaquina_roleid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_roleusuariaplicacio
    ADD CONSTRAINT roleusuarimaquina_roleid_fkey FOREIGN KEY (roleid) REFERENCES pfi_role(roleid);


--
-- TOC entry 2114 (class 2606 OID 70286)
-- Name: tipusdocumentpercolaboraciodelegaci_colaboraciodelegacioid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT tipusdocumentpercolaboraciodelegaci_colaboraciodelegacioid_fkey FOREIGN KEY (colaboraciodelegacioid) REFERENCES pfi_colaboraciodelegacio(colaboraciodelegacioid);


--
-- TOC entry 2113 (class 2606 OID 69770)
-- Name: tipusdocumentpercolaboraciodelegacio_tipusdocumentid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_tipusdocumentcoladele
    ADD CONSTRAINT tipusdocumentpercolaboraciodelegacio_tipusdocumentid_fkey FOREIGN KEY (tipusdocumentid) REFERENCES pfi_tipusdocument(tipusdocumentid);


--
-- TOC entry 2119 (class 2606 OID 69793)
-- Name: usuarientitat_entitatid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT usuarientitat_entitatid_fkey FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);


--
-- TOC entry 2118 (class 2606 OID 69788)
-- Name: usuarientitat_usuariid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitat
    ADD CONSTRAINT usuarientitat_usuariid_fkey FOREIGN KEY (usuaripersonaid) REFERENCES pfi_usuaripersona(usuaripersonaid);


--
-- TOC entry 2127 (class 2606 OID 191363)
-- Name: usuarientitatfavorit_favoritid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT usuarientitatfavorit_favoritid_fkey FOREIGN KEY (favoritid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2126 (class 2606 OID 191351)
-- Name: usuarientitatfavorit_origenid_fkey; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY pfi_usuarientitatfavorit
    ADD CONSTRAINT usuarientitatfavorit_origenid_fkey FOREIGN KEY (origenid) REFERENCES pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2274 (class 0 OID 0)
-- Dependencies: 7
-- Name: portafib; Type: ACL; Schema: -; Owner: portafib
--

REVOKE ALL ON SCHEMA portafib FROM PUBLIC;
REVOKE ALL ON SCHEMA portafib FROM portafib;
GRANT ALL ON SCHEMA portafib TO portafib;


--
-- TOC entry 2276 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: portafib
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM portafib;
GRANT ALL ON SCHEMA public TO portafib;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-05-14 14:22:37

--
-- PostgreSQL database dump complete
--

