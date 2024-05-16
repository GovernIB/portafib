--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.25
-- Dumped by pg_dump version 9.3.25
-- Started on 2024-05-15 11:13:08

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 7 (class 2615 OID 25952)
-- Name: portafib; Type: SCHEMA; Schema: -; Owner: portafib
--

CREATE SCHEMA portafib;


ALTER SCHEMA portafib OWNER TO portafib;

--
-- TOC entry 216 (class 1259 OID 55736)
-- Name: pfi_annex_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_annex_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_annex_seq OWNER TO portafib;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 25955)
-- Name: pfi_annex; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_annex (
    annexid bigint DEFAULT nextval('portafib.pfi_annex_seq'::regclass) NOT NULL,
    fitxerid bigint NOT NULL,
    peticiodefirmaid bigint NOT NULL,
    adjuntar boolean NOT NULL,
    firmar boolean NOT NULL
);


ALTER TABLE portafib.pfi_annex OWNER TO portafib;

--
-- TOC entry 217 (class 1259 OID 55738)
-- Name: pfi_annexfirmat_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_annexfirmat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_annexfirmat_seq OWNER TO portafib;

--
-- TOC entry 173 (class 1259 OID 25959)
-- Name: pfi_annexfirmat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_annexfirmat (
    annexfirmatid bigint DEFAULT nextval('portafib.pfi_annexfirmat_seq'::regclass) NOT NULL,
    fitxerid bigint NOT NULL,
    annexid bigint NOT NULL,
    firmaid bigint NOT NULL
);


ALTER TABLE portafib.pfi_annexfirmat OWNER TO portafib;

--
-- TOC entry 218 (class 1259 OID 55740)
-- Name: pfi_bitacola_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_bitacola_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_bitacola_seq OWNER TO portafib;

--
-- TOC entry 174 (class 1259 OID 25963)
-- Name: pfi_bitacola; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_bitacola (
    bitacolaid bigint DEFAULT nextval('portafib.pfi_bitacola_seq'::regclass) NOT NULL,
    data timestamp without time zone NOT NULL,
    descripcio character varying(255),
    objecteid character varying(100) NOT NULL,
    tipusobjecte integer NOT NULL,
    entitatid character varying(50) NOT NULL,
    usuariid character varying(101) NOT NULL,
    tipusoperacio integer NOT NULL,
    objecteserialitzat text
);


ALTER TABLE portafib.pfi_bitacola OWNER TO portafib;

--
-- TOC entry 219 (class 1259 OID 55742)
-- Name: pfi_blocdefirmes_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_blocdefirmes_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_blocdefirmes_seq OWNER TO portafib;

--
-- TOC entry 175 (class 1259 OID 25970)
-- Name: pfi_blocdefirmes; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_blocdefirmes (
    blocdefirmesid bigint DEFAULT nextval('portafib.pfi_blocdefirmes_seq'::regclass) NOT NULL,
    datafinalitzacio timestamp without time zone,
    fluxdefirmesid bigint NOT NULL,
    minimdefirmes integer NOT NULL,
    ordre integer NOT NULL
);


ALTER TABLE portafib.pfi_blocdefirmes OWNER TO portafib;

--
-- TOC entry 176 (class 1259 OID 25974)
-- Name: pfi_codibarres; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_codibarres (
    codibarresid character varying(255) NOT NULL,
    nom character varying(50) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE portafib.pfi_codibarres OWNER TO portafib;

--
-- TOC entry 220 (class 1259 OID 55744)
-- Name: pfi_colaboraciodelegacio_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_colaboraciodelegacio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_colaboraciodelegacio_seq OWNER TO portafib;

--
-- TOC entry 177 (class 1259 OID 25980)
-- Name: pfi_colaboraciodelegacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_colaboraciodelegacio (
    colaboraciodelegacioid bigint DEFAULT nextval('portafib.pfi_colaboraciodelegacio_seq'::regclass) NOT NULL,
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
-- TOC entry 2712 (class 0 OID 0)
-- Dependencies: 177
-- Name: COLUMN pfi_colaboraciodelegacio.revisor; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_colaboraciodelegacio.revisor IS 'Només es per col.laborador i indica si es obligatori que aquell col.laborador digui la seva.';


--
-- TOC entry 221 (class 1259 OID 55746)
-- Name: pfi_custodiainfo_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_custodiainfo_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_custodiainfo_seq OWNER TO portafib;

--
-- TOC entry 178 (class 1259 OID 25990)
-- Name: pfi_custodiainfo; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_custodiainfo (
    custodiainfoid bigint DEFAULT nextval('portafib.pfi_custodiainfo_seq'::regclass) NOT NULL,
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
    pluginid bigint NOT NULL,
    csv character varying(500),
    csvgenerationdefinition character varying(500),
    csvvalidationweb character varying(500),
    originalfiledirecturl character varying(500),
    printablefiledirecturl character varying(500),
    enifiledirecturl character varying(500),
    expedientid character varying(250),
    documentid character varying(250)
);


ALTER TABLE portafib.pfi_custodiainfo OWNER TO portafib;

--
-- TOC entry 2713 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN pfi_custodiainfo.custodiapluginid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_custodiainfo.custodiapluginid IS 'Identificador de document retornat per Custòdia';


--
-- TOC entry 2714 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN pfi_custodiainfo.expedientid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_custodiainfo.expedientid IS 'futura compatibilitat amb plugin arxiu';


--
-- TOC entry 2715 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN pfi_custodiainfo.documentid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_custodiainfo.documentid IS 'Futura compatibilitat amb Plugin Arxiu';


--
-- TOC entry 179 (class 1259 OID 25997)
-- Name: pfi_entitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_entitat (
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
    segelldetempsviaweb integer DEFAULT 0 NOT NULL,
    politicacustodia integer DEFAULT 0 NOT NULL,
    politicataulafirmes integer DEFAULT 2 NOT NULL,
    posiciotaulafirmes integer DEFAULT 1 NOT NULL,
    pluginvalidafirmesid bigint,
    pluginrubricaid bigint,
    checkcanviatdocfirmat boolean DEFAULT true NOT NULL,
    pluginvalidacertificatid bigint,
    uspoliticadefirma integer DEFAULT 0 NOT NULL,
    propietatstaulafirmes text,
    validarfirma boolean NOT NULL
);


ALTER TABLE portafib.pfi_entitat OWNER TO portafib;

--
-- TOC entry 2716 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN pfi_entitat.pluginid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_entitat.pluginid IS 'Plugin de segellat de temps';


--
-- TOC entry 2717 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN pfi_entitat.politicacustodia; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_entitat.politicacustodia IS '0: No permetre, 1:Només Plantilles de l''Entitat (No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat (Per defecte Actiu), 4: Opcional plantilla Entitat (Per defecte NO Actiu), 5: Llibertat Total (selecció, edició i us)';


--
-- TOC entry 2718 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN pfi_entitat.politicataulafirmes; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_entitat.politicataulafirmes IS '0 no es permet taules de firmes, 1 definit en l''entitat, 2 opcional per defecte el definit a l''entitat, 3 opcional per defecte sense taula de firmes';


--
-- TOC entry 2719 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN pfi_entitat.posiciotaulafirmes; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_entitat.posiciotaulafirmes IS 'SENSETAULA = 0; PRIMERAPAGINA = 1; DARRERAPAGINA = -1; DEFINIT_EN_FIRMA(RUBRICA)=2';


--
-- TOC entry 2720 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN pfi_entitat.uspoliticadefirma; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_entitat.uspoliticadefirma IS '-1=> usar politica de firma de l''entitat, 0 => no usar politica de firma,  1=> usar politica d''aquesta configuracio, 2 => L''usuari web o usuari-app elegeixen la politica de firma';


--
-- TOC entry 222 (class 1259 OID 55748)
-- Name: pfi_estadistica_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_estadistica_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_estadistica_seq OWNER TO portafib;

--
-- TOC entry 180 (class 1259 OID 26013)
-- Name: pfi_estadistica; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_estadistica (
    estadisticaid bigint DEFAULT nextval('portafib.pfi_estadistica_seq'::regclass) NOT NULL,
    tipus integer NOT NULL,
    data timestamp without time zone NOT NULL,
    entitatid character varying(50),
    valor double precision DEFAULT 1 NOT NULL,
    parametres character varying(3000),
    usuariaplicacioid character varying(101),
    usuarientitatid character varying(101)
);


ALTER TABLE portafib.pfi_estadistica OWNER TO portafib;

--
-- TOC entry 2721 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN pfi_estadistica.tipus; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_estadistica.tipus IS 'Ha de ser combobox';


--
-- TOC entry 2722 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN pfi_estadistica.usuariaplicacioid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_estadistica.usuariaplicacioid IS 'No te la clau forània amb pfi_usuariaplicacio ja que si s''esborra l''usuari aplicació, haurien de quedar les estadistiques.';


--
-- TOC entry 223 (class 1259 OID 55750)
-- Name: pfi_estatdefirma_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_estatdefirma_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_estatdefirma_seq OWNER TO portafib;

--
-- TOC entry 181 (class 1259 OID 26021)
-- Name: pfi_estatdefirma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_estatdefirma (
    estatdefirmaid bigint DEFAULT nextval('portafib.pfi_estatdefirma_seq'::regclass) NOT NULL,
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
-- TOC entry 224 (class 1259 OID 55752)
-- Name: pfi_firma_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_firma_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_firma_seq OWNER TO portafib;

--
-- TOC entry 182 (class 1259 OID 26026)
-- Name: pfi_firma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_firma (
    firmaid bigint DEFAULT nextval('portafib.pfi_firma_seq'::regclass) NOT NULL,
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
    mostrarrubrica boolean NOT NULL,
    motiu character varying(255),
    minimderevisors integer DEFAULT 0 NOT NULL,
    checkadministrationidofsigner boolean,
    checkdocumentmodifications boolean,
    checkvalidationsignature boolean,
    perfildefirma character varying(50),
    extern_nom character varying(100),
    extern_llinatges character varying(255),
    extern_email character varying(255),
    extern_idioma character varying(2),
    extern_token character varying(255),
    extern_nivellseguretat integer
);


ALTER TABLE portafib.pfi_firma OWNER TO portafib;

--
-- TOC entry 2723 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN pfi_firma.destinatariid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_firma.destinatariid IS 'Si val null significa que s''ha de substituir pel Sol·licitant de la petició (només podrà valer null en plantilles de flux de firmes)';


--
-- TOC entry 2724 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN pfi_firma.mostrarrubrica; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_firma.mostrarrubrica IS 'Dibuixar la firma(rubrica) de l''usuari persona si s''ha definit la caixa';


--
-- TOC entry 225 (class 1259 OID 55754)
-- Name: pfi_fitxer_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_fitxer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_fitxer_seq OWNER TO portafib;

--
-- TOC entry 183 (class 1259 OID 26034)
-- Name: pfi_fitxer; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_fitxer (
    fitxerid bigint DEFAULT nextval('portafib.pfi_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE portafib.pfi_fitxer OWNER TO portafib;

--
-- TOC entry 226 (class 1259 OID 55756)
-- Name: pfi_fluxdefirmes_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_fluxdefirmes_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_fluxdefirmes_seq OWNER TO portafib;

--
-- TOC entry 184 (class 1259 OID 26042)
-- Name: pfi_fluxdefirmes; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_fluxdefirmes (
    fluxdefirmesid bigint DEFAULT nextval('portafib.pfi_fluxdefirmes_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL
);


ALTER TABLE portafib.pfi_fluxdefirmes OWNER TO portafib;

--
-- TOC entry 227 (class 1259 OID 55758)
-- Name: pfi_grupentitat_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_grupentitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_grupentitat_seq OWNER TO portafib;

--
-- TOC entry 185 (class 1259 OID 26046)
-- Name: pfi_grupentitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_grupentitat (
    grupentitatid bigint DEFAULT nextval('portafib.pfi_grupentitat_seq'::regclass) NOT NULL,
    nom character varying(100) NOT NULL,
    descripcio character varying(255),
    entitatid character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_grupentitat OWNER TO portafib;

--
-- TOC entry 228 (class 1259 OID 55760)
-- Name: pfi_grupentitatusuarientitat_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_grupentitatusuarientitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_grupentitatusuarientitat_seq OWNER TO portafib;

--
-- TOC entry 186 (class 1259 OID 26050)
-- Name: pfi_grupentitatusuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_grupentitatusuarientitat (
    grupentitatusuarientitatid bigint DEFAULT nextval('portafib.pfi_grupentitatusuarientitat_seq'::regclass) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    grupentitatid bigint NOT NULL
);


ALTER TABLE portafib.pfi_grupentitatusuarientitat OWNER TO portafib;

--
-- TOC entry 187 (class 1259 OID 26054)
-- Name: pfi_idioma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE portafib.pfi_idioma OWNER TO portafib;

--
-- TOC entry 229 (class 1259 OID 55762)
-- Name: pfi_metadada_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_metadada_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_metadada_seq OWNER TO portafib;

--
-- TOC entry 188 (class 1259 OID 26059)
-- Name: pfi_metadada; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_metadada (
    metadadaid bigint DEFAULT nextval('portafib.pfi_metadada_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL,
    peticiodefirmaid bigint NOT NULL,
    valor text NOT NULL,
    tipusmetadadaid integer NOT NULL
);


ALTER TABLE portafib.pfi_metadada OWNER TO portafib;

--
-- TOC entry 230 (class 1259 OID 55764)
-- Name: pfi_modulfirmapertipusdoc_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_modulfirmapertipusdoc_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_modulfirmapertipusdoc_seq OWNER TO portafib;

--
-- TOC entry 189 (class 1259 OID 26067)
-- Name: pfi_modulfirmapertipusdoc; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_modulfirmapertipusdoc (
    id bigint DEFAULT nextval('portafib.pfi_modulfirmapertipusdoc_seq'::regclass) NOT NULL,
    tipusdocumentid bigint NOT NULL,
    pluginid bigint NOT NULL,
    nom character varying(100) NOT NULL
);


ALTER TABLE portafib.pfi_modulfirmapertipusdoc OWNER TO portafib;

--
-- TOC entry 231 (class 1259 OID 55766)
-- Name: pfi_notificacio_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_notificacio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_notificacio_seq OWNER TO portafib;

--
-- TOC entry 190 (class 1259 OID 26071)
-- Name: pfi_notificacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_notificacio (
    notificacioid bigint DEFAULT nextval('portafib.pfi_notificacio_seq'::regclass) NOT NULL,
    peticiodefirmaid bigint NOT NULL,
    tipusnotificacioid bigint NOT NULL,
    descripcio text,
    datacreacio timestamp without time zone NOT NULL,
    dataenviament timestamp without time zone,
    error text,
    bloquejada boolean NOT NULL,
    reintents integer DEFAULT 0 NOT NULL,
    dataerror timestamp with time zone,
    usuariaplicacioid character varying(101) NOT NULL
);


ALTER TABLE portafib.pfi_notificacio OWNER TO portafib;

--
-- TOC entry 232 (class 1259 OID 55768)
-- Name: pfi_perfilsperusrapp_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_perfilsperusrapp_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_perfilsperusrapp_seq OWNER TO portafib;

--
-- TOC entry 191 (class 1259 OID 26079)
-- Name: pfi_perfilsperusrapp; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_perfilsperusrapp (
    perfilsperusrappid bigint DEFAULT nextval('portafib.pfi_perfilsperusrapp_seq'::regclass) NOT NULL,
    usuariaplicacioperfilid bigint NOT NULL,
    usuariaplicacioid character varying(50) NOT NULL
);


ALTER TABLE portafib.pfi_perfilsperusrapp OWNER TO portafib;

--
-- TOC entry 233 (class 1259 OID 55770)
-- Name: pfi_permisgrupplantilla_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_permisgrupplantilla_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_permisgrupplantilla_seq OWNER TO portafib;

--
-- TOC entry 192 (class 1259 OID 26083)
-- Name: pfi_permisgrupplantilla; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_permisgrupplantilla (
    permisgrupplantillaid bigint DEFAULT nextval('portafib.pfi_permisgrupplantilla_seq'::regclass) NOT NULL,
    grupentitatid bigint NOT NULL,
    fluxdefirmesid bigint NOT NULL
);


ALTER TABLE portafib.pfi_permisgrupplantilla OWNER TO portafib;

--
-- TOC entry 234 (class 1259 OID 55772)
-- Name: pfi_permisusuariplantilla_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_permisusuariplantilla_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_permisusuariplantilla_seq OWNER TO portafib;

--
-- TOC entry 193 (class 1259 OID 26087)
-- Name: pfi_permisusuariplantilla; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_permisusuariplantilla (
    permisusuariplantillaid bigint DEFAULT nextval('portafib.pfi_permisusuariplantilla_seq'::regclass) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    fluxdefirmesid bigint NOT NULL
);


ALTER TABLE portafib.pfi_permisusuariplantilla OWNER TO portafib;

--
-- TOC entry 235 (class 1259 OID 55774)
-- Name: pfi_peticiodefirma_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_peticiodefirma_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_peticiodefirma_seq OWNER TO portafib;

--
-- TOC entry 194 (class 1259 OID 26091)
-- Name: pfi_peticiodefirma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_peticiodefirma (
    peticiodefirmaid bigint DEFAULT nextval('portafib.pfi_peticiodefirma_seq'::regclass) NOT NULL,
    datacaducitat timestamp without time zone NOT NULL,
    datafinal timestamp without time zone,
    datasolicitud timestamp without time zone,
    tipusdocumentid bigint NOT NULL,
    fitxerafirmarid bigint,
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
    segellatdetemps boolean DEFAULT false NOT NULL,
    tipusoperaciofirma integer DEFAULT 0 NOT NULL,
    expedientcodi character varying(255),
    expedientnom character varying(255),
    expedienturl character varying(255),
    procedimentcodi character varying(255),
    procedimentnom character varying(255),
    firmaoriginaldetachedid bigint,
    informacioaddicionalavaluable double precision,
    solicitantpersona2id character varying(101),
    solicitantpersona3id character varying(101),
    origenpeticiodefirma integer DEFAULT 0 NOT NULL,
    configuraciodefirmaid bigint
);


ALTER TABLE portafib.pfi_peticiodefirma OWNER TO portafib;

--
-- TOC entry 2725 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN pfi_peticiodefirma.posiciotaulafirmesid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_peticiodefirma.posiciotaulafirmesid IS 'Posicio taula de firmes: primera o darrera pàgina';


--
-- TOC entry 2726 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN pfi_peticiodefirma.tipusoperaciofirma; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_peticiodefirma.tipusoperaciofirma IS '0: firma,
1: cofirma 
2: contrafirma.';


--
-- TOC entry 2727 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN pfi_peticiodefirma.firmaoriginaldetachedid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_peticiodefirma.firmaoriginaldetachedid IS 'Camp de tipus fitxer que conté la firma en casos de cofirmes i contrafirmes detached de tipus CAdEs i XAdES';


--
-- TOC entry 195 (class 1259 OID 26106)
-- Name: pfi_plantillafluxdefirmes; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_plantillafluxdefirmes (
    fluxdefirmesid bigint NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying NOT NULL,
    usuarientitatid character varying(101),
    compartir boolean DEFAULT false,
    usuariaplicacioid character varying(101)
);


ALTER TABLE portafib.pfi_plantillafluxdefirmes OWNER TO portafib;

--
-- TOC entry 236 (class 1259 OID 55776)
-- Name: pfi_plugin_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_plugin_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_plugin_seq OWNER TO portafib;

--
-- TOC entry 196 (class 1259 OID 26114)
-- Name: pfi_plugin; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_plugin (
    pluginid bigint DEFAULT nextval('portafib.pfi_plugin_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    descripciocurtaid bigint NOT NULL,
    classe character varying(255) NOT NULL,
    propertiesadmin text,
    propertiesentitat text,
    entitatid character varying(50),
    actiu boolean NOT NULL,
    tipus integer NOT NULL,
    codi character varying(255) NOT NULL,
    ordre integer,
    politicadeus integer DEFAULT 0 NOT NULL,
    politicamostrarpropietats integer DEFAULT 2 NOT NULL
);


ALTER TABLE portafib.pfi_plugin OWNER TO portafib;

--
-- TOC entry 2728 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN pfi_plugin.entitatid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_plugin.entitatid IS 'Si val null indica que és de l''Administrador. En cas conytrari ja és una instanciació d''una Entitat';


--
-- TOC entry 2729 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN pfi_plugin.politicadeus; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_plugin.politicadeus IS '0 Plantilla
1 Només entitat
2 Ho pot usar tothom';


--
-- TOC entry 2730 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN pfi_plugin.politicamostrarpropietats; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_plugin.politicamostrarpropietats IS '0 => No mostrar ni propietats administrador ni propietats entitat, 1 => Permetre editar propietats entitat però no mostrar propietats administrador,  2 => Permetre editar propietats entitat i mostrar propietats administrador, 3 => Permetre editar propietats entitat i editar propietats administrador
';


--
-- TOC entry 237 (class 1259 OID 55778)
-- Name: pfi_plugincridada_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_plugincridada_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_plugincridada_seq OWNER TO portafib;

--
-- TOC entry 197 (class 1259 OID 26123)
-- Name: pfi_plugincridada; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_plugincridada (
    plugincridadaid bigint DEFAULT nextval('portafib.pfi_plugincridada_seq'::regclass) NOT NULL,
    entitatid character varying(50),
    data timestamp with time zone NOT NULL,
    metodeplugin character varying(100) NOT NULL,
    tipusresultat integer NOT NULL,
    tempsexecucio bigint,
    pluginid bigint NOT NULL,
    parametrestext text,
    parametresfitxerid bigint,
    retorntext text,
    retornfitxerid bigint
);


ALTER TABLE portafib.pfi_plugincridada OWNER TO portafib;

--
-- TOC entry 2731 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN pfi_plugincridada.tipusresultat; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_plugincridada.tipusresultat IS '0 => error, 1 => ok';


--
-- TOC entry 2732 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN pfi_plugincridada.tempsexecucio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_plugincridada.tempsexecucio IS 'milisegons execucio';


--
-- TOC entry 238 (class 1259 OID 55780)
-- Name: pfi_pluginfirmawebperusrapp_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_pluginfirmawebperusrapp_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_pluginfirmawebperusrapp_seq OWNER TO portafib;

--
-- TOC entry 198 (class 1259 OID 26130)
-- Name: pfi_pluginfirmawebperusrapp; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_pluginfirmawebperusrapp (
    pluginfirmawebperusrappid bigint DEFAULT nextval('portafib.pfi_pluginfirmawebperusrapp_seq'::regclass) NOT NULL,
    usuariaplicacioid character varying(101) NOT NULL,
    pluginfirmawebid bigint NOT NULL,
    accio integer DEFAULT 1 NOT NULL
);


ALTER TABLE portafib.pfi_pluginfirmawebperusrapp OWNER TO portafib;

--
-- TOC entry 2733 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN pfi_pluginfirmawebperusrapp.accio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_pluginfirmawebperusrapp.accio IS 'Valors:  -1 eliminar, 1 afegir';


--
-- TOC entry 239 (class 1259 OID 55782)
-- Name: pfi_pluginfirmawebperusrent_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_pluginfirmawebperusrent_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_pluginfirmawebperusrent_seq OWNER TO portafib;

--
-- TOC entry 199 (class 1259 OID 26135)
-- Name: pfi_pluginfirmawebperusrent; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_pluginfirmawebperusrent (
    pluginfirmawebperusrentid bigint DEFAULT nextval('portafib.pfi_pluginfirmawebperusrent_seq'::regclass) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    pluginfirmawebid bigint NOT NULL,
    accio integer DEFAULT 1 NOT NULL
);


ALTER TABLE portafib.pfi_pluginfirmawebperusrent OWNER TO portafib;

--
-- TOC entry 2734 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN pfi_pluginfirmawebperusrent.accio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_pluginfirmawebperusrent.accio IS 'Valors:  -1 eliminar, 1 afegir';


--
-- TOC entry 240 (class 1259 OID 55784)
-- Name: pfi_propietatglobal_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_propietatglobal_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_propietatglobal_seq OWNER TO portafib;

--
-- TOC entry 200 (class 1259 OID 26140)
-- Name: pfi_propietatglobal; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_propietatglobal (
    clau character varying(255) NOT NULL,
    valor character varying(255),
    descripcio character varying(1000),
    entitatid character varying(50),
    propietatglobalid bigint DEFAULT nextval('portafib.pfi_propietatglobal_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_propietatglobal OWNER TO portafib;

--
-- TOC entry 241 (class 1259 OID 55786)
-- Name: pfi_rebreavis_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_rebreavis_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_rebreavis_seq OWNER TO portafib;

--
-- TOC entry 201 (class 1259 OID 26147)
-- Name: pfi_rebreavis; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_rebreavis (
    tipusnotificacioid bigint NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('portafib.pfi_rebreavis_seq'::regclass) NOT NULL,
    rebreagrupat boolean DEFAULT false NOT NULL
);


ALTER TABLE portafib.pfi_rebreavis OWNER TO portafib;

--
-- TOC entry 249 (class 1259 OID 141049)
-- Name: pfi_revisordedestinatari_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_revisordedestinatari_seq
    START WITH 213740
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_revisordedestinatari_seq OWNER TO portafib;

--
-- TOC entry 250 (class 1259 OID 141051)
-- Name: pfi_revisordedestinatari; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_revisordedestinatari (
    revisordedestinatariid bigint DEFAULT nextval('portafib.pfi_revisordedestinatari_seq'::regclass) NOT NULL,
    destinatariid character varying(101) NOT NULL,
    revisorid character varying(101) NOT NULL
);


ALTER TABLE portafib.pfi_revisordedestinatari OWNER TO portafib;

--
-- TOC entry 242 (class 1259 OID 55788)
-- Name: pfi_revisordefirma_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_revisordefirma_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_revisordefirma_seq OWNER TO portafib;

--
-- TOC entry 202 (class 1259 OID 26152)
-- Name: pfi_revisordefirma; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_revisordefirma (
    revisordefirmaid bigint DEFAULT nextval('portafib.pfi_revisordefirma_seq'::regclass) NOT NULL,
    firmaid bigint NOT NULL,
    obligatori boolean NOT NULL,
    usuarientitatid character varying(101) NOT NULL
);


ALTER TABLE portafib.pfi_revisordefirma OWNER TO portafib;

--
-- TOC entry 203 (class 1259 OID 26156)
-- Name: pfi_role; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_role (
    roleid character varying(50) NOT NULL,
    nom character varying(50) DEFAULT NULL::character varying NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying
);


ALTER TABLE portafib.pfi_role OWNER TO portafib;

--
-- TOC entry 243 (class 1259 OID 55790)
-- Name: pfi_roleusuarientitat_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_roleusuarientitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_roleusuarientitat_seq OWNER TO portafib;

--
-- TOC entry 204 (class 1259 OID 26161)
-- Name: pfi_roleusuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_roleusuarientitat (
    roleid character varying(50) NOT NULL,
    usuarientitatid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('portafib.pfi_roleusuarientitat_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_roleusuarientitat OWNER TO portafib;

--
-- TOC entry 205 (class 1259 OID 26165)
-- Name: pfi_tipusdocument; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_tipusdocument (
    tipusdocumentid bigint NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    usuariaplicacioid character varying(50),
    nom bigint NOT NULL,
    tipusdocumentbaseid bigint DEFAULT 99 NOT NULL
);


ALTER TABLE portafib.pfi_tipusdocument OWNER TO portafib;

--
-- TOC entry 2735 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN pfi_tipusdocument.tipusdocumentbaseid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_tipusdocument.tipusdocumentbaseid IS 'Correspon només al tipus estandard (1 al 99) definits a les NTI';


--
-- TOC entry 244 (class 1259 OID 55792)
-- Name: pfi_tipusdocumentcoladele_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_tipusdocumentcoladele_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_tipusdocumentcoladele_seq OWNER TO portafib;

--
-- TOC entry 206 (class 1259 OID 26173)
-- Name: pfi_tipusdocumentcoladele; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_tipusdocumentcoladele (
    colaboraciodelegacioid bigint NOT NULL,
    tipusdocumentid bigint NOT NULL,
    id bigint DEFAULT nextval('portafib.pfi_tipusdocumentcoladele_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_tipusdocumentcoladele OWNER TO portafib;

--
-- TOC entry 207 (class 1259 OID 26177)
-- Name: pfi_tipusnotificacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_tipusnotificacio (
    tipusnotificacioid bigint NOT NULL,
    descripcio character varying(250) DEFAULT NULL::character varying,
    nom character varying(50) NOT NULL,
    esavis boolean
);


ALTER TABLE portafib.pfi_tipusnotificacio OWNER TO portafib;

--
-- TOC entry 2736 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN pfi_tipusnotificacio.esavis; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_tipusnotificacio.esavis IS 'Si es avis val true
Si és notificació es false
Si pot ser avis i notificació llavors val null
';


--
-- TOC entry 245 (class 1259 OID 55794)
-- Name: pfi_traduccio_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_traduccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_traduccio_seq OWNER TO portafib;

--
-- TOC entry 208 (class 1259 OID 26181)
-- Name: pfi_traduccio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_traduccio (
    traduccioid bigint DEFAULT nextval('portafib.pfi_traduccio_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_traduccio OWNER TO portafib;

--
-- TOC entry 209 (class 1259 OID 26185)
-- Name: pfi_traducciomap; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(5) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE portafib.pfi_traducciomap OWNER TO portafib;

--
-- TOC entry 210 (class 1259 OID 26191)
-- Name: pfi_usuariaplicacio; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_usuariaplicacio (
    entitatid character varying(50) NOT NULL,
    emailadmin character varying(100) NOT NULL,
    callbackurl character varying(400),
    descripcio character varying(255) DEFAULT NULL::character varying,
    callbackversio integer DEFAULT 2 NOT NULL,
    actiu boolean DEFAULT true NOT NULL,
    idiomaid character varying(5) NOT NULL,
    usuariaplicacioid character varying(101) NOT NULL,
    logosegellid bigint,
    politicadepluginfirmaweb integer DEFAULT 0 NOT NULL,
    politicacustodia integer DEFAULT 0 NOT NULL,
    custodiainfoid bigint,
    crearusuaris boolean DEFAULT false NOT NULL
);


ALTER TABLE portafib.pfi_usuariaplicacio OWNER TO portafib;

--
-- TOC entry 2737 (class 0 OID 0)
-- Dependencies: 210
-- Name: TABLE pfi_usuariaplicacio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON TABLE portafib.pfi_usuariaplicacio IS 'Usuari de tipus màquina que realitzarà peticions a PortaFIB';


--
-- TOC entry 2738 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN pfi_usuariaplicacio.emailadmin; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacio.emailadmin IS 'Correu de la persona encarregada d''aquest usuari-Màquina';


--
-- TOC entry 2739 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN pfi_usuariaplicacio.callbackurl; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacio.callbackurl IS 'Adreça on esta implementat el servei de recepció de notificacions associades a les peticions de firma realitzades per aquest usuari-màquina';


--
-- TOC entry 2740 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN pfi_usuariaplicacio.callbackversio; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacio.callbackversio IS 'La versió 1 és la compatible amb INDRA i la versió 2 és l''especifica del nou Portafirmes';


--
-- TOC entry 2741 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN pfi_usuariaplicacio.politicadepluginfirmaweb; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacio.politicadepluginfirmaweb IS '0 - Només plugins de l''entitat, 1 - Plugins de l''entitat més plugins addicionals (afegir o llevar), 2 - Només plugins addicionals (Només els que tenguin marcat afegir)';


--
-- TOC entry 2742 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN pfi_usuariaplicacio.politicacustodia; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacio.politicacustodia IS '-1: el que digui l''entitat, 0: No permetre, 1:Només Plantilles de l''''Entitat 	No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat 	Per defecte Actiu), 4: Opcional plantilla Entitat 	Per defecte NO Actiu), 5: Llibertat Total 	selecció, edició i us)';


--
-- TOC entry 246 (class 1259 OID 55796)
-- Name: pfi_usuariaplicacioconfig_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_usuariaplicacioconfig_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_usuariaplicacioconfig_seq OWNER TO portafib;

--
-- TOC entry 211 (class 1259 OID 26202)
-- Name: pfi_usuariaplicacioconfig; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_usuariaplicacioconfig (
    usuariaplicacioconfigid bigint DEFAULT nextval('portafib.pfi_usuariaplicacioconfig_seq'::regclass) NOT NULL,
    uspoliticadefirma integer DEFAULT 0 NOT NULL,
    policyidentifier character varying(100),
    policyidentifierhash character varying(256),
    policyidentifierhashalgorithm character varying(50),
    policyurldocument character varying(255),
    filtrecertificats text,
    tipusoperaciofirma integer DEFAULT 0 NOT NULL,
    tipusfirmaid integer NOT NULL,
    algorismedefirmaid integer,
    modedefirma boolean NOT NULL,
    motiudelegacioid bigint,
    firmatperformatid bigint,
    posiciotaulafirmesid integer DEFAULT 0 NOT NULL,
    pluginsegellatid bigint,
    comprovarniffirma boolean,
    checkcanviatdocfirmat boolean,
    pluginfirmaservidorid bigint,
    htmlperllistarpluginsfirmaweb text,
    validarfirma boolean,
    validarcertificat boolean,
    politicataulafirmes integer DEFAULT 0 NOT NULL,
    politicasegellatdetemps integer DEFAULT 0 NOT NULL,
    propietatstaulafirmes text,
    upgradesignformat integer,
    nom character varying(255) NOT NULL,
    entitatid character varying(50) NOT NULL,
    usenfirmaapisimpleservidor boolean DEFAULT false NOT NULL,
    usenfirmaapisimpleweb boolean DEFAULT false NOT NULL,
    usenfirmaweb boolean DEFAULT false NOT NULL,
    usenfirmaws2 boolean DEFAULT false NOT NULL,
    usenfirmapassarelaservidor boolean DEFAULT false NOT NULL,
    usenfirmapassarelaweb boolean DEFAULT false NOT NULL,
    usenfirmaws1 boolean DEFAULT false NOT NULL,
    esdepeticio boolean DEFAULT false NOT NULL
);


ALTER TABLE portafib.pfi_usuariaplicacioconfig OWNER TO portafib;

--
-- TOC entry 2743 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacioconfig.uspoliticadefirma; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacioconfig.uspoliticadefirma IS '-1=> usar politica de firma de l''entitat, 0 => no usar politica de firma,  1=> usar politica d''aquesta configuracio, 2 => L''usuari web o usuari-app elegeixen la politica de firma';


--
-- TOC entry 2744 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacioconfig.tipusoperaciofirma; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacioconfig.tipusoperaciofirma IS '0 firma, 1 contrafirma 2, cofirma';


--
-- TOC entry 2745 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacioconfig.comprovarniffirma; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacioconfig.comprovarniffirma IS 'Null => Valor definit a l''entitat';


--
-- TOC entry 2746 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacioconfig.checkcanviatdocfirmat; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacioconfig.checkcanviatdocfirmat IS '-- Null => Valor definit a l''entitat';


--
-- TOC entry 2747 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacioconfig.validarfirma; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacioconfig.validarfirma IS 'Indica si validar la firma amb el Plugin de validació definit a l''entitat';


--
-- TOC entry 2748 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacioconfig.validarcertificat; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacioconfig.validarcertificat IS 'NULL => Lo que digui l''entitat';


--
-- TOC entry 2749 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacioconfig.politicataulafirmes; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacioconfig.politicataulafirmes IS '-1 definit en l''entitat, 0 no es permet taules de firmes, 1  obligatori politica definida en la configuració d''usuari aplicació o entitat, 2 opcional, per defecte el definit a l''entitat';


--
-- TOC entry 2750 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN pfi_usuariaplicacioconfig.politicasegellatdetemps; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuariaplicacioconfig.politicasegellatdetemps IS 'DEFINIT_EN_ENTITAT=-1;NOUSAR=0;US_OBLIGATORI=1;USUARI_ELEGEIX_PER_DEFECTE_SI=2;USUARI_ELEGEIX_PER_DEFECTE_NO=3;';


--
-- TOC entry 247 (class 1259 OID 55798)
-- Name: pfi_usuariaplicacioperfil_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_usuariaplicacioperfil_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_usuariaplicacioperfil_seq OWNER TO portafib;

--
-- TOC entry 212 (class 1259 OID 26222)
-- Name: pfi_usuariaplicacioperfil; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_usuariaplicacioperfil (
    usuariaplicacioperfilid bigint DEFAULT nextval('portafib.pfi_usuariaplicacioperfil_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    descripcio character varying(500),
    condicio character varying(4000),
    usrappconfiguracio1id bigint NOT NULL,
    usrappconfiguracio2id bigint,
    usrappconfiguracio3id bigint,
    codi character varying(100) NOT NULL,
    usrappconfiguracio4id bigint,
    usrappconfiguracio5id bigint,
    urlbase character varying(255)
);


ALTER TABLE portafib.pfi_usuariaplicacioperfil OWNER TO portafib;

--
-- TOC entry 213 (class 1259 OID 26229)
-- Name: pfi_usuarientitat; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_usuarientitat (
    usuarientitatid character varying(101) NOT NULL,
    usuaripersonaid character varying(50) NOT NULL,
    entitatid character varying(50) NOT NULL,
    email character varying(100),
    actiu boolean DEFAULT true NOT NULL,
    predeterminat boolean DEFAULT false NOT NULL,
    carrec character varying(150) DEFAULT NULL::character varying,
    rebretotselsavisos boolean DEFAULT false NOT NULL,
    logosegellid bigint,
    politicacustodia integer,
    politicadepluginfirmaweb integer DEFAULT 0 NOT NULL,
    custodiainfoid bigint
);


ALTER TABLE portafib.pfi_usuarientitat OWNER TO portafib;

--
-- TOC entry 2751 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN pfi_usuarientitat.politicacustodia; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuarientitat.politicacustodia IS 'null: elque digui l''entitat, 0: No permetre, 1:Només Plantilles de l''''Entitat (No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat (Per defecte Actiu), 4: Opcional plantilla Entitat (Per defecte NO Actiu), 5: Llibertat Total (selecció, edició i us)';


--
-- TOC entry 2752 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN pfi_usuarientitat.politicadepluginfirmaweb; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuarientitat.politicadepluginfirmaweb IS ' 0 - Només plugins de l''''entitat, 1 - Plugins de l''''entitat més plugins addicionals (afegir o llevar), 2 - Només plugins addicionals (Només els que tenguin marcat afegir)''';


--
-- TOC entry 248 (class 1259 OID 55800)
-- Name: pfi_usuarientitatfavorit_seq; Type: SEQUENCE; Schema: portafib; Owner: portafib
--

CREATE SEQUENCE portafib.pfi_usuarientitatfavorit_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE portafib.pfi_usuarientitatfavorit_seq OWNER TO portafib;

--
-- TOC entry 214 (class 1259 OID 26237)
-- Name: pfi_usuarientitatfavorit; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_usuarientitatfavorit (
    origenid character varying(101) NOT NULL,
    favoritid character varying(101) NOT NULL,
    id bigint DEFAULT nextval('portafib.pfi_usuarientitatfavorit_seq'::regclass) NOT NULL
);


ALTER TABLE portafib.pfi_usuarientitatfavorit OWNER TO portafib;

--
-- TOC entry 215 (class 1259 OID 26241)
-- Name: pfi_usuaripersona; Type: TABLE; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE TABLE portafib.pfi_usuaripersona (
    usuaripersonaid character varying(50) NOT NULL,
    nom character varying(50) NOT NULL,
    llinatges character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    nif character varying(9) NOT NULL,
    idiomaid character varying(5) NOT NULL,
    rubricaid bigint,
    usuariintern boolean DEFAULT true NOT NULL,
    contrasenya character varying(255)
);


ALTER TABLE portafib.pfi_usuaripersona OWNER TO portafib;

--
-- TOC entry 2753 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN pfi_usuaripersona.rubricaid; Type: COMMENT; Schema: portafib; Owner: portafib
--

COMMENT ON COLUMN portafib.pfi_usuaripersona.rubricaid IS 'és la firma gràfica de la persona';


--
-- TOC entry 2190 (class 2606 OID 26270)
-- Name: pfi_annex_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_annex
    ADD CONSTRAINT pfi_annex_pk PRIMARY KEY (annexid);


--
-- TOC entry 2196 (class 2606 OID 26272)
-- Name: pfi_annexfirmat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_annexfirmat
    ADD CONSTRAINT pfi_annexfirmat_pk PRIMARY KEY (annexfirmatid);


--
-- TOC entry 2203 (class 2606 OID 26274)
-- Name: pfi_bitacola_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_bitacola
    ADD CONSTRAINT pfi_bitacola_pk PRIMARY KEY (bitacolaid);


--
-- TOC entry 2206 (class 2606 OID 26276)
-- Name: pfi_blocdefirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_blocdefirmes
    ADD CONSTRAINT pfi_blocdefirmes_pk PRIMARY KEY (blocdefirmesid);


--
-- TOC entry 2210 (class 2606 OID 26278)
-- Name: pfi_codibarres_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_codibarres
    ADD CONSTRAINT pfi_codibarres_pk PRIMARY KEY (codibarresid);


--
-- TOC entry 2216 (class 2606 OID 26280)
-- Name: pfi_colaboraciodelegacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colaboraciodelegacio_pk PRIMARY KEY (colaboraciodelegacioid);


--
-- TOC entry 2225 (class 2606 OID 26282)
-- Name: pfi_custodiainfo_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_custodiainfo
    ADD CONSTRAINT pfi_custodiainfo_pk PRIMARY KEY (custodiainfoid);


--
-- TOC entry 2238 (class 2606 OID 26284)
-- Name: pfi_entitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_pk PRIMARY KEY (entitatid);


--
-- TOC entry 2247 (class 2606 OID 26286)
-- Name: pfi_estadistica_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_estadistica
    ADD CONSTRAINT pfi_estadistica_pk PRIMARY KEY (estadisticaid);


--
-- TOC entry 2251 (class 2606 OID 26288)
-- Name: pfi_estatdefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_estatdefirma
    ADD CONSTRAINT pfi_estatdefirma_pk PRIMARY KEY (estatdefirmaid);


--
-- TOC entry 2470 (class 2606 OID 26290)
-- Name: pfi_favorit_origfavo_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_favorit_origfavo_uk UNIQUE (origenid, favoritid);


--
-- TOC entry 2262 (class 2606 OID 26292)
-- Name: pfi_firma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_firma
    ADD CONSTRAINT pfi_firma_pk PRIMARY KEY (firmaid);


--
-- TOC entry 2265 (class 2606 OID 26294)
-- Name: pfi_fitxer_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_fitxer
    ADD CONSTRAINT pfi_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 2268 (class 2606 OID 26296)
-- Name: pfi_fluxdefirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_fluxdefirmes
    ADD CONSTRAINT pfi_fluxdefirmes_pk PRIMARY KEY (fluxdefirmesid);


--
-- TOC entry 2271 (class 2606 OID 26298)
-- Name: pfi_grupentita_nomentitat_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_grupentitat
    ADD CONSTRAINT pfi_grupentita_nomentitat_uk UNIQUE (nom, entitatid);


--
-- TOC entry 2274 (class 2606 OID 26300)
-- Name: pfi_grupentitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_grupentitat
    ADD CONSTRAINT pfi_grupentitat_pk PRIMARY KEY (grupentitatid);


--
-- TOC entry 2278 (class 2606 OID 26302)
-- Name: pfi_grupusrent_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_pk PRIMARY KEY (grupentitatusuarientitatid);


--
-- TOC entry 2282 (class 2606 OID 26304)
-- Name: pfi_grupusrent_usrgrup_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_usrgrup_uk UNIQUE (usuarientitatid, grupentitatid);


--
-- TOC entry 2284 (class 2606 OID 26306)
-- Name: pfi_idioma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_idioma
    ADD CONSTRAINT pfi_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 2288 (class 2606 OID 26308)
-- Name: pfi_metadada_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_metadada
    ADD CONSTRAINT pfi_metadada_pk PRIMARY KEY (metadadaid);


--
-- TOC entry 2292 (class 2606 OID 26310)
-- Name: pfi_modulfirmapertipusdoc_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_modulfirmapertipusdoc_pk PRIMARY KEY (id);


--
-- TOC entry 2295 (class 2606 OID 26312)
-- Name: pfi_mofitido_modfirm_tipdoc_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_modfirm_tipdoc_uk UNIQUE (tipusdocumentid, pluginid);


--
-- TOC entry 2303 (class 2606 OID 26314)
-- Name: pfi_notificacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_notificacio
    ADD CONSTRAINT pfi_notificacio_pk PRIMARY KEY (notificacioid);


--
-- TOC entry 2454 (class 2606 OID 26316)
-- Name: pfi_perfilapp_codi_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioperfil
    ADD CONSTRAINT pfi_perfilapp_codi_uk UNIQUE (codi);


--
-- TOC entry 2307 (class 2606 OID 26318)
-- Name: pfi_perfilsperusrapp_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_perfilsperusrapp
    ADD CONSTRAINT pfi_perfilsperusrapp_pk PRIMARY KEY (perfilsperusrappid);


--
-- TOC entry 2311 (class 2606 OID 26320)
-- Name: pfi_perfilsua_uaid_perf_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_perfilsperusrapp
    ADD CONSTRAINT pfi_perfilsua_uaid_perf_uk UNIQUE (usuariaplicacioperfilid, usuariaplicacioid);


--
-- TOC entry 2316 (class 2606 OID 26322)
-- Name: pfi_permisgrpl_grupflux_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrpl_grupflux_uk UNIQUE (grupentitatid, fluxdefirmesid);


--
-- TOC entry 2318 (class 2606 OID 26324)
-- Name: pfi_permisgrupplantilla_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrupplantilla_pk PRIMARY KEY (permisgrupplantillaid);


--
-- TOC entry 2323 (class 2606 OID 26326)
-- Name: pfi_permisuspl_usrflux_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisuspl_usrflux_uk UNIQUE (usuarientitatid, fluxdefirmesid);


--
-- TOC entry 2325 (class 2606 OID 26328)
-- Name: pfi_permisusuariplantilla_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisusuariplantilla_pk PRIMARY KEY (permisusuariplantillaid);


--
-- TOC entry 2476 (class 2606 OID 26330)
-- Name: pfi_persona_nif_extern_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_usuaripersona
    ADD CONSTRAINT pfi_persona_nif_extern_uk UNIQUE (nif, usuariintern);


--
-- TOC entry 2328 (class 2606 OID 26332)
-- Name: pfi_peticiodefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_peticiodefirma_pk PRIMARY KEY (peticiodefirmaid);


--
-- TOC entry 2338 (class 2606 OID 26334)
-- Name: pfi_petifirma_fluxfirmesid_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fluxfirmesid_uk UNIQUE (fluxdefirmesid);


--
-- TOC entry 2371 (class 2606 OID 26336)
-- Name: pfi_pfwpua_usuapp_plug_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_pluginfirmawebperusrapp
    ADD CONSTRAINT pfi_pfwpua_usuapp_plug_uk UNIQUE (usuariaplicacioid, pluginfirmawebid);


--
-- TOC entry 2378 (class 2606 OID 26338)
-- Name: pfi_pfwpue_usuent_plug_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_pluginfirmawebperusrent
    ADD CONSTRAINT pfi_pfwpue_usuent_plug_uk UNIQUE (usuarientitatid, pluginfirmawebid);


--
-- TOC entry 2352 (class 2606 OID 26340)
-- Name: pfi_plantillafluxdefirmes_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantillafluxdefirmes_pk PRIMARY KEY (fluxdefirmesid);


--
-- TOC entry 2358 (class 2606 OID 26342)
-- Name: pfi_plugin_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_plugin
    ADD CONSTRAINT pfi_plugin_pk PRIMARY KEY (pluginid);


--
-- TOC entry 2365 (class 2606 OID 26344)
-- Name: pfi_plugincridada_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_plugincridada
    ADD CONSTRAINT pfi_plugincridada_pk PRIMARY KEY (plugincridadaid);


--
-- TOC entry 2373 (class 2606 OID 26346)
-- Name: pfi_pluginfirmawebperusrapp_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_pluginfirmawebperusrapp
    ADD CONSTRAINT pfi_pluginfirmawebperusrapp_pk PRIMARY KEY (pluginfirmawebperusrappid);


--
-- TOC entry 2380 (class 2606 OID 26348)
-- Name: pfi_pluginfirmawebperusrent_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_pluginfirmawebperusrent
    ADD CONSTRAINT pfi_pluginfirmawebperusrent_pk PRIMARY KEY (pluginfirmawebperusrentid);


--
-- TOC entry 2382 (class 2606 OID 26350)
-- Name: pfi_propietat_clau_entitat_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_propietatglobal
    ADD CONSTRAINT pfi_propietat_clau_entitat_uk UNIQUE (clau, entitatid);


--
-- TOC entry 2385 (class 2606 OID 26352)
-- Name: pfi_propietatglobal_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_propietatglobal
    ADD CONSTRAINT pfi_propietatglobal_pk PRIMARY KEY (propietatglobalid);


--
-- TOC entry 2388 (class 2606 OID 26354)
-- Name: pfi_rebreavis_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_pk PRIMARY KEY (id);


--
-- TOC entry 2392 (class 2606 OID 26356)
-- Name: pfi_rebreavis_tnotiusr_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_tnotiusr_uk UNIQUE (tipusnotificacioid, usuarientitatid);


--
-- TOC entry 2483 (class 2606 OID 141058)
-- Name: pfi_revdedest_dest_rev_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_revisordedestinatari
    ADD CONSTRAINT pfi_revdedest_dest_rev_uk UNIQUE (destinatariid, revisorid);


--
-- TOC entry 2487 (class 2606 OID 141056)
-- Name: pfi_revisordedestinatari_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_revisordedestinatari
    ADD CONSTRAINT pfi_revisordedestinatari_pk PRIMARY KEY (revisordedestinatariid);


--
-- TOC entry 2397 (class 2606 OID 26358)
-- Name: pfi_revisordefirma_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_revisordefirma
    ADD CONSTRAINT pfi_revisordefirma_pk PRIMARY KEY (revisordefirmaid);


--
-- TOC entry 2400 (class 2606 OID 26360)
-- Name: pfi_role_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_role
    ADD CONSTRAINT pfi_role_pk PRIMARY KEY (roleid);


--
-- TOC entry 2404 (class 2606 OID 26362)
-- Name: pfi_roleusrent_roleusrent_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusrent_roleusrent_uk UNIQUE (roleid, usuarientitatid);


--
-- TOC entry 2407 (class 2606 OID 26364)
-- Name: pfi_roleusuarientitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusuarientitat_pk PRIMARY KEY (id);


--
-- TOC entry 2415 (class 2606 OID 26366)
-- Name: pfi_tipusdoccd_codetdoc_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdoccd_codetdoc_uk UNIQUE (colaboraciodelegacioid, tipusdocumentid);


--
-- TOC entry 2412 (class 2606 OID 26368)
-- Name: pfi_tipusdocument_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdocument_pk PRIMARY KEY (tipusdocumentid);


--
-- TOC entry 2419 (class 2606 OID 26370)
-- Name: pfi_tipusdocumentcoladele_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdocumentcoladele_pk PRIMARY KEY (id);


--
-- TOC entry 2422 (class 2606 OID 26372)
-- Name: pfi_tipusnotificacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_tipusnotificacio
    ADD CONSTRAINT pfi_tipusnotificacio_pk PRIMARY KEY (tipusnotificacioid);


--
-- TOC entry 2425 (class 2606 OID 26374)
-- Name: pfi_traduccio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_traduccio
    ADD CONSTRAINT pfi_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 2428 (class 2606 OID 26376)
-- Name: pfi_traducciomap_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_traducciomap
    ADD CONSTRAINT pfi_traducciomap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 2462 (class 2606 OID 26378)
-- Name: pfi_usrentitat_perentcar_uk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_perentcar_uk UNIQUE (usuaripersonaid, entitatid, carrec);


--
-- TOC entry 2436 (class 2606 OID 26380)
-- Name: pfi_usuariaplicacio_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usuariaplicacio_pk PRIMARY KEY (usuariaplicacioid);


--
-- TOC entry 2446 (class 2606 OID 26382)
-- Name: pfi_usuariaplicacioconfig_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioconfig
    ADD CONSTRAINT pfi_usuariaplicacioconfig_pk PRIMARY KEY (usuariaplicacioconfigid);


--
-- TOC entry 2456 (class 2606 OID 26384)
-- Name: pfi_usuariaplicacioperfil_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioperfil
    ADD CONSTRAINT pfi_usuariaplicacioperfil_pk PRIMARY KEY (usuariaplicacioperfilid);


--
-- TOC entry 2465 (class 2606 OID 26386)
-- Name: pfi_usuarientitat_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_usuarientitat
    ADD CONSTRAINT pfi_usuarientitat_pk PRIMARY KEY (usuarientitatid);


--
-- TOC entry 2472 (class 2606 OID 26388)
-- Name: pfi_usuarientitatfavorit_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_usuarientitatfavorit_pk PRIMARY KEY (id);


--
-- TOC entry 2480 (class 2606 OID 26390)
-- Name: pfi_usuaripersona_pk; Type: CONSTRAINT; Schema: portafib; Owner: portafib; Tablespace: 
--

ALTER TABLE ONLY portafib.pfi_usuaripersona
    ADD CONSTRAINT pfi_usuaripersona_pk PRIMARY KEY (usuaripersonaid);


--
-- TOC entry 2187 (class 1259 OID 26391)
-- Name: pfi_annex_fitxerid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annex_fitxerid_fk_i ON portafib.pfi_annex USING btree (fitxerid);


--
-- TOC entry 2188 (class 1259 OID 26392)
-- Name: pfi_annex_petdefirmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annex_petdefirmaid_fk_i ON portafib.pfi_annex USING btree (peticiodefirmaid);


--
-- TOC entry 2191 (class 1259 OID 26393)
-- Name: pfi_annex_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annex_pk_i ON portafib.pfi_annex USING btree (annexid);


--
-- TOC entry 2192 (class 1259 OID 26394)
-- Name: pfi_annexfirmat_annexid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_annexid_fk_i ON portafib.pfi_annexfirmat USING btree (annexid);


--
-- TOC entry 2193 (class 1259 OID 26395)
-- Name: pfi_annexfirmat_firmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_firmaid_fk_i ON portafib.pfi_annexfirmat USING btree (firmaid);


--
-- TOC entry 2194 (class 1259 OID 26396)
-- Name: pfi_annexfirmat_fitxerid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_fitxerid_fk_i ON portafib.pfi_annexfirmat USING btree (fitxerid);


--
-- TOC entry 2197 (class 1259 OID 26397)
-- Name: pfi_annexfirmat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_annexfirmat_pk_i ON portafib.pfi_annexfirmat USING btree (annexfirmatid);


--
-- TOC entry 2198 (class 1259 OID 26398)
-- Name: pfi_bitacola_data_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_data_i ON portafib.pfi_bitacola USING btree (data);


--
-- TOC entry 2199 (class 1259 OID 26399)
-- Name: pfi_bitacola_enttipobj_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_enttipobj_i ON portafib.pfi_bitacola USING btree (entitatid, tipusobjecte);


--
-- TOC entry 2200 (class 1259 OID 26400)
-- Name: pfi_bitacola_enttipope_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_enttipope_i ON portafib.pfi_bitacola USING btree (entitatid, tipusoperacio);


--
-- TOC entry 2201 (class 1259 OID 26401)
-- Name: pfi_bitacola_objecteid_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_objecteid_i ON portafib.pfi_bitacola USING btree (objecteid);


--
-- TOC entry 2204 (class 1259 OID 26402)
-- Name: pfi_bitacola_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_bitacola_pk_i ON portafib.pfi_bitacola USING btree (bitacolaid);


--
-- TOC entry 2207 (class 1259 OID 26403)
-- Name: pfi_blocdefirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_blocdefirmes_pk_i ON portafib.pfi_blocdefirmes USING btree (blocdefirmesid);


--
-- TOC entry 2208 (class 1259 OID 26404)
-- Name: pfi_blocfirmes_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_blocfirmes_fluxid_fk_i ON portafib.pfi_blocdefirmes USING btree (fluxdefirmesid);


--
-- TOC entry 2211 (class 1259 OID 26405)
-- Name: pfi_codibarres_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_codibarres_pk_i ON portafib.pfi_codibarres USING btree (codibarresid);


--
-- TOC entry 2212 (class 1259 OID 26406)
-- Name: pfi_colabdeleg_coldelid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colabdeleg_coldelid_fk_i ON portafib.pfi_colaboraciodelegacio USING btree (colaboradordelegatid);


--
-- TOC entry 2213 (class 1259 OID 26407)
-- Name: pfi_colabdeleg_destid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colabdeleg_destid_fk_i ON portafib.pfi_colaboraciodelegacio USING btree (destinatariid);


--
-- TOC entry 2214 (class 1259 OID 26408)
-- Name: pfi_colabdeleg_fitautoid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colabdeleg_fitautoid_fk_i ON portafib.pfi_colaboraciodelegacio USING btree (fitxerautoritzacioid);


--
-- TOC entry 2217 (class 1259 OID 26409)
-- Name: pfi_colaboraciodelegacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_colaboraciodelegacio_pk_i ON portafib.pfi_colaboraciodelegacio USING btree (colaboraciodelegacioid);


--
-- TOC entry 2438 (class 1259 OID 26410)
-- Name: pfi_confapp_algofirma_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_confapp_algofirma_fk_i ON portafib.pfi_usuariaplicacioconfig USING btree (algorismedefirmaid);


--
-- TOC entry 2439 (class 1259 OID 26411)
-- Name: pfi_confapp_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_confapp_entitatid_fk_i ON portafib.pfi_usuariaplicacioconfig USING btree (entitatid);


--
-- TOC entry 2440 (class 1259 OID 26412)
-- Name: pfi_confapp_firmaserv_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_confapp_firmaserv_fk_i ON portafib.pfi_usuariaplicacioconfig USING btree (pluginfirmaservidorid);


--
-- TOC entry 2441 (class 1259 OID 26413)
-- Name: pfi_confapp_firmatper_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_confapp_firmatper_fk_i ON portafib.pfi_usuariaplicacioconfig USING btree (firmatperformatid);


--
-- TOC entry 2442 (class 1259 OID 26414)
-- Name: pfi_confapp_motiudele_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_confapp_motiudele_fk_i ON portafib.pfi_usuariaplicacioconfig USING btree (motiudelegacioid);


--
-- TOC entry 2443 (class 1259 OID 26415)
-- Name: pfi_confapp_plugsegell_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_confapp_plugsegell_fk_i ON portafib.pfi_usuariaplicacioconfig USING btree (pluginsegellatid);


--
-- TOC entry 2444 (class 1259 OID 26416)
-- Name: pfi_confapp_tipusfirma_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_confapp_tipusfirma_fk_i ON portafib.pfi_usuariaplicacioconfig USING btree (tipusfirmaid);


--
-- TOC entry 2218 (class 1259 OID 26417)
-- Name: pfi_custodia_codbarrpos_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_codbarrpos_fk_i ON portafib.pfi_custodiainfo USING btree (codibarresposiciopaginaid);


--
-- TOC entry 2219 (class 1259 OID 26418)
-- Name: pfi_custodia_codibarid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_codibarid_fk_i ON portafib.pfi_custodiainfo USING btree (codibarresid);


--
-- TOC entry 2220 (class 1259 OID 26419)
-- Name: pfi_custodia_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_entitatid_fk_i ON portafib.pfi_custodiainfo USING btree (entitatid);


--
-- TOC entry 2221 (class 1259 OID 26420)
-- Name: pfi_custodia_msgpospagid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_msgpospagid_fk_i ON portafib.pfi_custodiainfo USING btree (missatgeposiciopaginaid);


--
-- TOC entry 2222 (class 1259 OID 26421)
-- Name: pfi_custodia_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_usrappid_fk_i ON portafib.pfi_custodiainfo USING btree (usuariaplicacioid);


--
-- TOC entry 2223 (class 1259 OID 26422)
-- Name: pfi_custodia_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodia_usrentid_fk_i ON portafib.pfi_custodiainfo USING btree (usuarientitatid);


--
-- TOC entry 2226 (class 1259 OID 26423)
-- Name: pfi_custodiainfo_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodiainfo_pk_i ON portafib.pfi_custodiainfo USING btree (custodiainfoid);


--
-- TOC entry 2227 (class 1259 OID 26424)
-- Name: pfi_custodiainfo_pluginid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_custodiainfo_pluginid_fk_i ON portafib.pfi_custodiainfo USING btree (pluginid);


--
-- TOC entry 2228 (class 1259 OID 26425)
-- Name: pfi_entitat_algofirma_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_algofirma_fk_i ON portafib.pfi_entitat USING btree (algorismedefirmaid);


--
-- TOC entry 2229 (class 1259 OID 26426)
-- Name: pfi_entitat_custodiadef_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_custodiadef_fk_i ON portafib.pfi_entitat USING btree (custodiainfoid);


--
-- TOC entry 2230 (class 1259 OID 26427)
-- Name: pfi_entitat_faviconid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_faviconid_fk_i ON portafib.pfi_entitat USING btree (faviconid);


--
-- TOC entry 2231 (class 1259 OID 26428)
-- Name: pfi_entitat_firmatper_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_firmatper_fk_i ON portafib.pfi_entitat USING btree (firmatperformatid);


--
-- TOC entry 2232 (class 1259 OID 26429)
-- Name: pfi_entitat_logosegellid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_logosegellid_fk_i ON portafib.pfi_entitat USING btree (logosegellid);


--
-- TOC entry 2233 (class 1259 OID 26430)
-- Name: pfi_entitat_logowebid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_logowebid_fk_i ON portafib.pfi_entitat USING btree (logowebid);


--
-- TOC entry 2234 (class 1259 OID 26431)
-- Name: pfi_entitat_logowebpeuid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_logowebpeuid_fk_i ON portafib.pfi_entitat USING btree (logowebpeuid);


--
-- TOC entry 2235 (class 1259 OID 26432)
-- Name: pfi_entitat_motiudele_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_motiudele_fk_i ON portafib.pfi_entitat USING btree (motiudelegacioid);


--
-- TOC entry 2236 (class 1259 OID 26433)
-- Name: pfi_entitat_pdfautoriid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_pdfautoriid_fk_i ON portafib.pfi_entitat USING btree (pdfautoritzaciodelegacioid);


--
-- TOC entry 2239 (class 1259 OID 26434)
-- Name: pfi_entitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_pk_i ON portafib.pfi_entitat USING btree (entitatid);


--
-- TOC entry 2240 (class 1259 OID 26435)
-- Name: pfi_entitat_pluginrubri_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_pluginrubri_fk_i ON portafib.pfi_entitat USING btree (pluginrubricaid);


--
-- TOC entry 2241 (class 1259 OID 26436)
-- Name: pfi_entitat_pluginvalcer_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_pluginvalcer_fk_i ON portafib.pfi_entitat USING btree (pluginvalidacertificatid);


--
-- TOC entry 2242 (class 1259 OID 26437)
-- Name: pfi_entitat_pluginvalfir_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_pluginvalfir_fk_i ON portafib.pfi_entitat USING btree (pluginvalidafirmesid);


--
-- TOC entry 2243 (class 1259 OID 26438)
-- Name: pfi_entitat_segelltemps_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_segelltemps_fk_i ON portafib.pfi_entitat USING btree (pluginid);


--
-- TOC entry 2244 (class 1259 OID 26439)
-- Name: pfi_entitat_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_entitat_usrappid_fk_i ON portafib.pfi_entitat USING btree (usuariaplicacioid);


--
-- TOC entry 2245 (class 1259 OID 26440)
-- Name: pfi_estadistica_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estadistica_entitatid_fk_i ON portafib.pfi_estadistica USING btree (entitatid);


--
-- TOC entry 2248 (class 1259 OID 26441)
-- Name: pfi_estadistica_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estadistica_pk_i ON portafib.pfi_estadistica USING btree (estadisticaid);


--
-- TOC entry 2249 (class 1259 OID 26442)
-- Name: pfi_estatdefirma_firmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatdefirma_firmaid_fk_i ON portafib.pfi_estatdefirma USING btree (firmaid);


--
-- TOC entry 2252 (class 1259 OID 26443)
-- Name: pfi_estatdefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatdefirma_pk_i ON portafib.pfi_estatdefirma USING btree (estatdefirmaid);


--
-- TOC entry 2253 (class 1259 OID 26444)
-- Name: pfi_estatfirma_coladele_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_coladele_fk_i ON portafib.pfi_estatdefirma USING btree (colaboraciodelegacioid);


--
-- TOC entry 2254 (class 1259 OID 26445)
-- Name: pfi_estatfirma_estatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_estatid_fk_i ON portafib.pfi_estatdefirma USING btree (tipusestatdefirmafinalid);


--
-- TOC entry 2255 (class 1259 OID 47508)
-- Name: pfi_estatfirma_estats_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_estats_i ON portafib.pfi_estatdefirma USING btree (tipusestatdefirmainicialid, tipusestatdefirmafinalid);


--
-- TOC entry 2256 (class 1259 OID 47509)
-- Name: pfi_estatfirma_usuenti_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_estatfirma_usuenti_fk_i ON portafib.pfi_estatdefirma USING btree (usuarientitatid, tipusestatdefirmainicialid, tipusestatdefirmafinalid);


--
-- TOC entry 2467 (class 1259 OID 26448)
-- Name: pfi_favorit_favoritid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_favorit_favoritid_fk_i ON portafib.pfi_usuarientitatfavorit USING btree (favoritid);


--
-- TOC entry 2468 (class 1259 OID 26449)
-- Name: pfi_favorit_origenid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_favorit_origenid_fk_i ON portafib.pfi_usuarientitatfavorit USING btree (origenid);


--
-- TOC entry 2257 (class 1259 OID 26450)
-- Name: pfi_firma_blocdefirmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_blocdefirmaid_fk_i ON portafib.pfi_firma USING btree (blocdefirmaid);


--
-- TOC entry 2258 (class 1259 OID 26451)
-- Name: pfi_firma_destinatariid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_destinatariid_fk_i ON portafib.pfi_firma USING btree (destinatariid);


--
-- TOC entry 2259 (class 1259 OID 26452)
-- Name: pfi_firma_estatfirmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_estatfirmaid_fk_i ON portafib.pfi_firma USING btree (tipusestatdefirmafinalid);


--
-- TOC entry 2260 (class 1259 OID 26453)
-- Name: pfi_firma_fitxerfirmatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_fitxerfirmatid_fk_i ON portafib.pfi_firma USING btree (fitxerfirmatid);


--
-- TOC entry 2263 (class 1259 OID 26454)
-- Name: pfi_firma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_firma_pk_i ON portafib.pfi_firma USING btree (firmaid);


--
-- TOC entry 2266 (class 1259 OID 26455)
-- Name: pfi_fitxer_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_fitxer_pk_i ON portafib.pfi_fitxer USING btree (fitxerid);


--
-- TOC entry 2269 (class 1259 OID 26456)
-- Name: pfi_fluxdefirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_fluxdefirmes_pk_i ON portafib.pfi_fluxdefirmes USING btree (fluxdefirmesid);


--
-- TOC entry 2272 (class 1259 OID 26457)
-- Name: pfi_grupentitat_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupentitat_entitatid_fk_i ON portafib.pfi_grupentitat USING btree (entitatid);


--
-- TOC entry 2275 (class 1259 OID 26458)
-- Name: pfi_grupentitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupentitat_pk_i ON portafib.pfi_grupentitat USING btree (grupentitatid);


--
-- TOC entry 2276 (class 1259 OID 26459)
-- Name: pfi_grupusrent_grupentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupusrent_grupentid_fk_i ON portafib.pfi_grupentitatusuarientitat USING btree (grupentitatid);


--
-- TOC entry 2279 (class 1259 OID 26460)
-- Name: pfi_grupusrent_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupusrent_pk_i ON portafib.pfi_grupentitatusuarientitat USING btree (grupentitatusuarientitatid);


--
-- TOC entry 2280 (class 1259 OID 26461)
-- Name: pfi_grupusrent_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_grupusrent_usrentid_fk_i ON portafib.pfi_grupentitatusuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2285 (class 1259 OID 26462)
-- Name: pfi_idioma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_idioma_pk_i ON portafib.pfi_idioma USING btree (idiomaid);


--
-- TOC entry 2286 (class 1259 OID 26463)
-- Name: pfi_metadada_peticioid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_metadada_peticioid_fk_i ON portafib.pfi_metadada USING btree (peticiodefirmaid);


--
-- TOC entry 2289 (class 1259 OID 26464)
-- Name: pfi_metadada_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_metadada_pk_i ON portafib.pfi_metadada USING btree (metadadaid);


--
-- TOC entry 2290 (class 1259 OID 26465)
-- Name: pfi_metadada_tipusmetaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_metadada_tipusmetaid_fk_i ON portafib.pfi_metadada USING btree (tipusmetadadaid);


--
-- TOC entry 2293 (class 1259 OID 26466)
-- Name: pfi_modulfirmapertipusdoc_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_modulfirmapertipusdoc_pk_i ON portafib.pfi_modulfirmapertipusdoc USING btree (id);


--
-- TOC entry 2296 (class 1259 OID 26467)
-- Name: pfi_mofitido_modfirma_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_mofitido_modfirma_fk_i ON portafib.pfi_modulfirmapertipusdoc USING btree (pluginid);


--
-- TOC entry 2297 (class 1259 OID 26468)
-- Name: pfi_mofitido_tipusdoc_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_mofitido_tipusdoc_fk_i ON portafib.pfi_modulfirmapertipusdoc USING btree (tipusdocumentid);


--
-- TOC entry 2298 (class 1259 OID 26469)
-- Name: pfi_notifica_peticioid_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notifica_peticioid_i ON portafib.pfi_notificacio USING btree (peticiodefirmaid);


--
-- TOC entry 2299 (class 1259 OID 26470)
-- Name: pfi_notifica_tiponotiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notifica_tiponotiid_fk_i ON portafib.pfi_notificacio USING btree (tipusnotificacioid);


--
-- TOC entry 2300 (class 1259 OID 26471)
-- Name: pfi_notificacio_bloqreint_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notificacio_bloqreint_i ON portafib.pfi_notificacio USING btree (bloquejada, reintents);


--
-- TOC entry 2301 (class 1259 OID 26472)
-- Name: pfi_notificacio_datacreacio_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notificacio_datacreacio_i ON portafib.pfi_notificacio USING btree (datacreacio);


--
-- TOC entry 2304 (class 1259 OID 26473)
-- Name: pfi_notificacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notificacio_pk_i ON portafib.pfi_notificacio USING btree (notificacioid);


--
-- TOC entry 2305 (class 1259 OID 26474)
-- Name: pfi_notificacio_usrappid_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_notificacio_usrappid_i ON portafib.pfi_notificacio USING btree (usuariaplicacioid);


--
-- TOC entry 2448 (class 1259 OID 26475)
-- Name: pfi_perfilapp_appconf1id_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_perfilapp_appconf1id_fk_i ON portafib.pfi_usuariaplicacioperfil USING btree (usrappconfiguracio1id);


--
-- TOC entry 2449 (class 1259 OID 26476)
-- Name: pfi_perfilapp_appconf2id_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_perfilapp_appconf2id_fk_i ON portafib.pfi_usuariaplicacioperfil USING btree (usrappconfiguracio2id);


--
-- TOC entry 2450 (class 1259 OID 26477)
-- Name: pfi_perfilapp_appconf3id_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_perfilapp_appconf3id_fk_i ON portafib.pfi_usuariaplicacioperfil USING btree (usrappconfiguracio3id);


--
-- TOC entry 2451 (class 1259 OID 26478)
-- Name: pfi_perfilapp_appconf4id_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_perfilapp_appconf4id_fk_i ON portafib.pfi_usuariaplicacioperfil USING btree (usrappconfiguracio4id);


--
-- TOC entry 2452 (class 1259 OID 26479)
-- Name: pfi_perfilapp_appconf5id_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_perfilapp_appconf5id_fk_i ON portafib.pfi_usuariaplicacioperfil USING btree (usrappconfiguracio5id);


--
-- TOC entry 2308 (class 1259 OID 26480)
-- Name: pfi_perfilsperusrapp_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_perfilsperusrapp_pk_i ON portafib.pfi_perfilsperusrapp USING btree (perfilsperusrappid);


--
-- TOC entry 2309 (class 1259 OID 26481)
-- Name: pfi_perfilsua_perfilid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_perfilsua_perfilid_fk_i ON portafib.pfi_perfilsperusrapp USING btree (usuariaplicacioperfilid);


--
-- TOC entry 2312 (class 1259 OID 26482)
-- Name: pfi_perfilsua_usuappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_perfilsua_usuappid_fk_i ON portafib.pfi_perfilsperusrapp USING btree (usuariaplicacioid);


--
-- TOC entry 2313 (class 1259 OID 26483)
-- Name: pfi_permisgrpl_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisgrpl_fluxid_fk_i ON portafib.pfi_permisgrupplantilla USING btree (fluxdefirmesid);


--
-- TOC entry 2314 (class 1259 OID 26484)
-- Name: pfi_permisgrpl_grupentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisgrpl_grupentid_fk_i ON portafib.pfi_permisgrupplantilla USING btree (grupentitatid);


--
-- TOC entry 2319 (class 1259 OID 26485)
-- Name: pfi_permisgrupplantilla_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisgrupplantilla_pk_i ON portafib.pfi_permisgrupplantilla USING btree (permisgrupplantillaid);


--
-- TOC entry 2320 (class 1259 OID 26486)
-- Name: pfi_permisuspl_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisuspl_fluxid_fk_i ON portafib.pfi_permisusuariplantilla USING btree (fluxdefirmesid);


--
-- TOC entry 2321 (class 1259 OID 26487)
-- Name: pfi_permisuspl_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisuspl_usrentid_fk_i ON portafib.pfi_permisusuariplantilla USING btree (usuarientitatid);


--
-- TOC entry 2326 (class 1259 OID 26488)
-- Name: pfi_permisusuariplantilla_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_permisusuariplantilla_pk_i ON portafib.pfi_permisusuariplantilla USING btree (permisusuariplantillaid);


--
-- TOC entry 2474 (class 1259 OID 26489)
-- Name: pfi_persona_idiomaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_persona_idiomaid_fk_i ON portafib.pfi_usuaripersona USING btree (idiomaid);


--
-- TOC entry 2477 (class 1259 OID 26490)
-- Name: pfi_persona_rubricaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_persona_rubricaid_fk_i ON portafib.pfi_usuaripersona USING btree (rubricaid);


--
-- TOC entry 2329 (class 1259 OID 26491)
-- Name: pfi_peticiodefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_peticiodefirma_pk_i ON portafib.pfi_peticiodefirma USING btree (peticiodefirmaid);


--
-- TOC entry 2330 (class 1259 OID 26492)
-- Name: pfi_petifirma_algofirmid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_algofirmid_fk_i ON portafib.pfi_peticiodefirma USING btree (algorismedefirmaid);


--
-- TOC entry 2331 (class 1259 OID 26493)
-- Name: pfi_petifirma_conffirma_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_conffirma_fk_i ON portafib.pfi_peticiodefirma USING btree (configuraciodefirmaid);


--
-- TOC entry 2332 (class 1259 OID 26494)
-- Name: pfi_petifirma_custinfoid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_custinfoid_fk_i ON portafib.pfi_peticiodefirma USING btree (custodiainfoid);


--
-- TOC entry 2333 (class 1259 OID 26495)
-- Name: pfi_petifirma_estatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_estatid_fk_i ON portafib.pfi_peticiodefirma USING btree (tipusestatpeticiodefirmaid);


--
-- TOC entry 2334 (class 1259 OID 26496)
-- Name: pfi_petifirma_firmaori_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_firmaori_fk_i ON portafib.pfi_peticiodefirma USING btree (firmaoriginaldetachedid);


--
-- TOC entry 2335 (class 1259 OID 26497)
-- Name: pfi_petifirma_fitxeadaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_fitxeadaid_fk_i ON portafib.pfi_peticiodefirma USING btree (fitxeradaptatid);


--
-- TOC entry 2336 (class 1259 OID 26498)
-- Name: pfi_petifirma_fitxerid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_fitxerid_fk_i ON portafib.pfi_peticiodefirma USING btree (fitxerafirmarid);


--
-- TOC entry 2339 (class 1259 OID 26499)
-- Name: pfi_petifirma_fluxid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_fluxid_fk_i ON portafib.pfi_peticiodefirma USING btree (fluxdefirmesid);


--
-- TOC entry 2340 (class 1259 OID 26500)
-- Name: pfi_petifirma_idiomaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_idiomaid_fk_i ON portafib.pfi_peticiodefirma USING btree (idiomaid);


--
-- TOC entry 2341 (class 1259 OID 26501)
-- Name: pfi_petifirma_logosegid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_logosegid_fk_i ON portafib.pfi_peticiodefirma USING btree (logosegellid);


--
-- TOC entry 2342 (class 1259 OID 26502)
-- Name: pfi_petifirma_prioritatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_prioritatid_fk_i ON portafib.pfi_peticiodefirma USING btree (prioritatid);


--
-- TOC entry 2343 (class 1259 OID 26503)
-- Name: pfi_petifirma_solipers2_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_solipers2_fk_i ON portafib.pfi_peticiodefirma USING btree (solicitantpersona2id);


--
-- TOC entry 2344 (class 1259 OID 26504)
-- Name: pfi_petifirma_solipers3_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_solipers3_fk_i ON portafib.pfi_peticiodefirma USING btree (solicitantpersona3id);


--
-- TOC entry 2345 (class 1259 OID 26505)
-- Name: pfi_petifirma_tipofirmid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_tipofirmid_fk_i ON portafib.pfi_peticiodefirma USING btree (tipusfirmaid);


--
-- TOC entry 2346 (class 1259 OID 26506)
-- Name: pfi_petifirma_tipusdocid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_tipusdocid_fk_i ON portafib.pfi_peticiodefirma USING btree (tipusdocumentid);


--
-- TOC entry 2347 (class 1259 OID 26507)
-- Name: pfi_petifirma_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_usrappid_fk_i ON portafib.pfi_peticiodefirma USING btree (usuariaplicacioid);


--
-- TOC entry 2348 (class 1259 OID 26508)
-- Name: pfi_petifirma_usrentiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_petifirma_usrentiid_fk_i ON portafib.pfi_peticiodefirma USING btree (usuarientitatid);


--
-- TOC entry 2367 (class 1259 OID 26509)
-- Name: pfi_pfwpua_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_pfwpua_pk_i ON portafib.pfi_pluginfirmawebperusrapp USING btree (pluginfirmawebperusrappid);


--
-- TOC entry 2368 (class 1259 OID 26510)
-- Name: pfi_pfwpua_plugin_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_pfwpua_plugin_fk_i ON portafib.pfi_pluginfirmawebperusrapp USING btree (pluginfirmawebid);


--
-- TOC entry 2369 (class 1259 OID 26511)
-- Name: pfi_pfwpua_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_pfwpua_usrappid_fk_i ON portafib.pfi_pluginfirmawebperusrapp USING btree (usuariaplicacioid);


--
-- TOC entry 2374 (class 1259 OID 26512)
-- Name: pfi_pfwpue_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_pfwpue_pk_i ON portafib.pfi_pluginfirmawebperusrent USING btree (pluginfirmawebperusrentid);


--
-- TOC entry 2375 (class 1259 OID 26513)
-- Name: pfi_pfwpue_plugin_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_pfwpue_plugin_fk_i ON portafib.pfi_pluginfirmawebperusrent USING btree (pluginfirmawebid);


--
-- TOC entry 2376 (class 1259 OID 26514)
-- Name: pfi_pfwpue_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_pfwpue_usrentid_fk_i ON portafib.pfi_pluginfirmawebperusrent USING btree (usuarientitatid);


--
-- TOC entry 2349 (class 1259 OID 26515)
-- Name: pfi_plantiflfi_usrappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plantiflfi_usrappid_fk_i ON portafib.pfi_plantillafluxdefirmes USING btree (usuariaplicacioid);


--
-- TOC entry 2350 (class 1259 OID 26516)
-- Name: pfi_plantiflfi_usrentiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plantiflfi_usrentiid_fk_i ON portafib.pfi_plantillafluxdefirmes USING btree (usuarientitatid);


--
-- TOC entry 2353 (class 1259 OID 26517)
-- Name: pfi_plantillafluxdefirmes_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plantillafluxdefirmes_pk_i ON portafib.pfi_plantillafluxdefirmes USING btree (fluxdefirmesid);


--
-- TOC entry 2360 (class 1259 OID 26518)
-- Name: pfi_plugcrida_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugcrida_entitatid_fk_i ON portafib.pfi_plugincridada USING btree (entitatid);


--
-- TOC entry 2361 (class 1259 OID 26519)
-- Name: pfi_plugcrida_paramfitxer_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugcrida_paramfitxer_fk_i ON portafib.pfi_plugincridada USING btree (parametresfitxerid);


--
-- TOC entry 2362 (class 1259 OID 26520)
-- Name: pfi_plugcrida_pluginid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugcrida_pluginid_fk_i ON portafib.pfi_plugincridada USING btree (pluginid);


--
-- TOC entry 2363 (class 1259 OID 26521)
-- Name: pfi_plugcrida_retorfitxer_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugcrida_retorfitxer_fk_i ON portafib.pfi_plugincridada USING btree (retornfitxerid);


--
-- TOC entry 2354 (class 1259 OID 26522)
-- Name: pfi_plugin_desccurtaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugin_desccurtaid_fk_i ON portafib.pfi_plugin USING btree (descripciocurtaid);


--
-- TOC entry 2355 (class 1259 OID 26523)
-- Name: pfi_plugin_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugin_entitatid_fk_i ON portafib.pfi_plugin USING btree (entitatid);


--
-- TOC entry 2356 (class 1259 OID 26524)
-- Name: pfi_plugin_nomid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugin_nomid_fk_i ON portafib.pfi_plugin USING btree (nomid);


--
-- TOC entry 2359 (class 1259 OID 26525)
-- Name: pfi_plugin_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugin_pk_i ON portafib.pfi_plugin USING btree (pluginid);


--
-- TOC entry 2366 (class 1259 OID 26526)
-- Name: pfi_plugincridada_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_plugincridada_pk_i ON portafib.pfi_plugincridada USING btree (plugincridadaid);


--
-- TOC entry 2383 (class 1259 OID 26527)
-- Name: pfi_propietat_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_propietat_entitatid_fk_i ON portafib.pfi_propietatglobal USING btree (entitatid);


--
-- TOC entry 2386 (class 1259 OID 26528)
-- Name: pfi_propietatglobal_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_propietatglobal_pk_i ON portafib.pfi_propietatglobal USING btree (propietatglobalid);


--
-- TOC entry 2389 (class 1259 OID 26529)
-- Name: pfi_rebreavis_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_rebreavis_pk_i ON portafib.pfi_rebreavis USING btree (id);


--
-- TOC entry 2390 (class 1259 OID 26530)
-- Name: pfi_rebreavis_tiponotiid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_rebreavis_tiponotiid_fk_i ON portafib.pfi_rebreavis USING btree (tipusnotificacioid);


--
-- TOC entry 2393 (class 1259 OID 26531)
-- Name: pfi_rebreavis_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_rebreavis_usrentid_fk_i ON portafib.pfi_rebreavis USING btree (usuarientitatid);


--
-- TOC entry 2484 (class 1259 OID 141080)
-- Name: pfi_revdedest_destid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_revdedest_destid_fk_i ON portafib.pfi_revisordedestinatari USING btree (destinatariid);


--
-- TOC entry 2485 (class 1259 OID 141081)
-- Name: pfi_revdedest_revisorid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_revdedest_revisorid_fk_i ON portafib.pfi_revisordedestinatari USING btree (revisorid);


--
-- TOC entry 2394 (class 1259 OID 26532)
-- Name: pfi_revfirma_firmaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_revfirma_firmaid_fk_i ON portafib.pfi_revisordefirma USING btree (firmaid);


--
-- TOC entry 2395 (class 1259 OID 26533)
-- Name: pfi_revfirma_usrentitat_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_revfirma_usrentitat_fk_i ON portafib.pfi_revisordefirma USING btree (usuarientitatid);


--
-- TOC entry 2488 (class 1259 OID 141069)
-- Name: pfi_revisordedestinatari_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_revisordedestinatari_pk_i ON portafib.pfi_revisordedestinatari USING btree (revisordedestinatariid);


--
-- TOC entry 2398 (class 1259 OID 26534)
-- Name: pfi_revisordefirma_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_revisordefirma_pk_i ON portafib.pfi_revisordefirma USING btree (revisordefirmaid);


--
-- TOC entry 2401 (class 1259 OID 26535)
-- Name: pfi_role_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_role_pk_i ON portafib.pfi_role USING btree (roleid);


--
-- TOC entry 2402 (class 1259 OID 26536)
-- Name: pfi_roleusrent_roleid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrent_roleid_fk_i ON portafib.pfi_roleusuarientitat USING btree (roleid);


--
-- TOC entry 2405 (class 1259 OID 26537)
-- Name: pfi_roleusrent_usrentid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusrent_usrentid_fk_i ON portafib.pfi_roleusuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2408 (class 1259 OID 26538)
-- Name: pfi_roleusuarientitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_roleusuarientitat_pk_i ON portafib.pfi_roleusuarientitat USING btree (id);


--
-- TOC entry 2409 (class 1259 OID 26539)
-- Name: pfi_tipusdoc_usuariappid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdoc_usuariappid_fk_i ON portafib.pfi_tipusdocument USING btree (usuariaplicacioid);


--
-- TOC entry 2416 (class 1259 OID 26540)
-- Name: pfi_tipusdoccd_coldelid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdoccd_coldelid_fk_i ON portafib.pfi_tipusdocumentcoladele USING btree (colaboraciodelegacioid);


--
-- TOC entry 2417 (class 1259 OID 26541)
-- Name: pfi_tipusdoccd_tipusdocid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdoccd_tipusdocid_fk_i ON portafib.pfi_tipusdocumentcoladele USING btree (tipusdocumentid);


--
-- TOC entry 2410 (class 1259 OID 26542)
-- Name: pfi_tipusdocument_nom_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdocument_nom_fk_i ON portafib.pfi_tipusdocument USING btree (nom);


--
-- TOC entry 2413 (class 1259 OID 26543)
-- Name: pfi_tipusdocument_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdocument_pk_i ON portafib.pfi_tipusdocument USING btree (tipusdocumentid);


--
-- TOC entry 2420 (class 1259 OID 26544)
-- Name: pfi_tipusdocumentcoladele_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusdocumentcoladele_pk_i ON portafib.pfi_tipusdocumentcoladele USING btree (id);


--
-- TOC entry 2423 (class 1259 OID 26545)
-- Name: pfi_tipusnotificacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_tipusnotificacio_pk_i ON portafib.pfi_tipusnotificacio USING btree (tipusnotificacioid);


--
-- TOC entry 2426 (class 1259 OID 26546)
-- Name: pfi_traduccio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_traduccio_pk_i ON portafib.pfi_traduccio USING btree (traduccioid);


--
-- TOC entry 2429 (class 1259 OID 26547)
-- Name: pfi_traducmap_idiomaid_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_traducmap_idiomaid_pk_i ON portafib.pfi_traducciomap USING btree (traducciomapid);


--
-- TOC entry 2430 (class 1259 OID 26548)
-- Name: pfi_traducmap_tradmapid_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_traducmap_tradmapid_pk_i ON portafib.pfi_traducciomap USING btree (traducciomapid);


--
-- TOC entry 2431 (class 1259 OID 26549)
-- Name: pfi_usrapp_custodia_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_custodia_fk_i ON portafib.pfi_usuariaplicacio USING btree (custodiainfoid);


--
-- TOC entry 2432 (class 1259 OID 26550)
-- Name: pfi_usrapp_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_entitatid_fk_i ON portafib.pfi_usuariaplicacio USING btree (entitatid);


--
-- TOC entry 2433 (class 1259 OID 26551)
-- Name: pfi_usrapp_idiomaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_idiomaid_fk_i ON portafib.pfi_usuariaplicacio USING btree (idiomaid);


--
-- TOC entry 2434 (class 1259 OID 26552)
-- Name: pfi_usrapp_logosegellid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrapp_logosegellid_fk_i ON portafib.pfi_usuariaplicacio USING btree (logosegellid);


--
-- TOC entry 2458 (class 1259 OID 26553)
-- Name: pfi_usrentitat_custinfo_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_custinfo_fk_i ON portafib.pfi_usuarientitat USING btree (custodiainfoid);


--
-- TOC entry 2459 (class 1259 OID 26554)
-- Name: pfi_usrentitat_entitatid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_entitatid_fk_i ON portafib.pfi_usuarientitat USING btree (entitatid);


--
-- TOC entry 2460 (class 1259 OID 26555)
-- Name: pfi_usrentitat_logosegid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_logosegid_fk_i ON portafib.pfi_usuarientitat USING btree (logosegellid);


--
-- TOC entry 2463 (class 1259 OID 26556)
-- Name: pfi_usrentitat_personaid_fk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usrentitat_personaid_fk_i ON portafib.pfi_usuarientitat USING btree (usuaripersonaid);


--
-- TOC entry 2437 (class 1259 OID 26557)
-- Name: pfi_usuariaplicacio_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuariaplicacio_pk_i ON portafib.pfi_usuariaplicacio USING btree (usuariaplicacioid);


--
-- TOC entry 2447 (class 1259 OID 26558)
-- Name: pfi_usuariaplicacioconfig_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuariaplicacioconfig_pk_i ON portafib.pfi_usuariaplicacioconfig USING btree (usuariaplicacioconfigid);


--
-- TOC entry 2457 (class 1259 OID 26559)
-- Name: pfi_usuariaplicacioperfil_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuariaplicacioperfil_pk_i ON portafib.pfi_usuariaplicacioperfil USING btree (usuariaplicacioperfilid);


--
-- TOC entry 2466 (class 1259 OID 26560)
-- Name: pfi_usuarientitat_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuarientitat_pk_i ON portafib.pfi_usuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2473 (class 1259 OID 26561)
-- Name: pfi_usuarientitatfavorit_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuarientitatfavorit_pk_i ON portafib.pfi_usuarientitatfavorit USING btree (id);


--
-- TOC entry 2478 (class 1259 OID 26562)
-- Name: pfi_usuaripersona_nif_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuaripersona_nif_i ON portafib.pfi_usuaripersona USING btree (nif);


--
-- TOC entry 2481 (class 1259 OID 26563)
-- Name: pfi_usuaripersona_pk_i; Type: INDEX; Schema: portafib; Owner: portafib; Tablespace: 
--

CREATE INDEX pfi_usuaripersona_pk_i ON portafib.pfi_usuaripersona USING btree (usuaripersonaid);


--
-- TOC entry 2491 (class 2606 OID 26564)
-- Name: pfi_anexfirmat_annex_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_annexfirmat
    ADD CONSTRAINT pfi_anexfirmat_annex_fk FOREIGN KEY (annexid) REFERENCES portafib.pfi_annex(annexid);


--
-- TOC entry 2492 (class 2606 OID 26569)
-- Name: pfi_anexfirmat_firma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_annexfirmat
    ADD CONSTRAINT pfi_anexfirmat_firma_fk FOREIGN KEY (firmaid) REFERENCES portafib.pfi_firma(firmaid);


--
-- TOC entry 2493 (class 2606 OID 26574)
-- Name: pfi_anexfirmat_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_annexfirmat
    ADD CONSTRAINT pfi_anexfirmat_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2489 (class 2606 OID 26579)
-- Name: pfi_annex_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_annex
    ADD CONSTRAINT pfi_annex_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2490 (class 2606 OID 26584)
-- Name: pfi_annex_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_annex
    ADD CONSTRAINT pfi_annex_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES portafib.pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2494 (class 2606 OID 26589)
-- Name: pfi_blocfirmes_fluxfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_blocdefirmes
    ADD CONSTRAINT pfi_blocfirmes_fluxfirmes_fk FOREIGN KEY (fluxdefirmesid) REFERENCES portafib.pfi_fluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2495 (class 2606 OID 26594)
-- Name: pfi_colabdeleg_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colabdeleg_fitxer_fk FOREIGN KEY (fitxerautoritzacioid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2496 (class 2606 OID 26599)
-- Name: pfi_colabdeleg_usrentitat_c_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colabdeleg_usrentitat_c_fk FOREIGN KEY (colaboradordelegatid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2497 (class 2606 OID 26604)
-- Name: pfi_colabdeleg_usrentitat_d_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_colaboraciodelegacio
    ADD CONSTRAINT pfi_colabdeleg_usrentitat_d_fk FOREIGN KEY (destinatariid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2579 (class 2606 OID 26609)
-- Name: pfi_confapp_entitat_ent_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioconfig
    ADD CONSTRAINT pfi_confapp_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES portafib.pfi_entitat(entitatid);


--
-- TOC entry 2580 (class 2606 OID 26614)
-- Name: pfi_confapp_plugin_fsrv_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioconfig
    ADD CONSTRAINT pfi_confapp_plugin_fsrv_fk FOREIGN KEY (pluginfirmaservidorid) REFERENCES portafib.pfi_plugin(pluginid);


--
-- TOC entry 2581 (class 2606 OID 26619)
-- Name: pfi_confapp_plugin_seg_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioconfig
    ADD CONSTRAINT pfi_confapp_plugin_seg_fk FOREIGN KEY (pluginsegellatid) REFERENCES portafib.pfi_plugin(pluginid);


--
-- TOC entry 2582 (class 2606 OID 26624)
-- Name: pfi_confapp_traduccio_firm_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioconfig
    ADD CONSTRAINT pfi_confapp_traduccio_firm_fk FOREIGN KEY (firmatperformatid) REFERENCES portafib.pfi_traduccio(traduccioid);


--
-- TOC entry 2583 (class 2606 OID 26629)
-- Name: pfi_confapp_traduccio_moti_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioconfig
    ADD CONSTRAINT pfi_confapp_traduccio_moti_fk FOREIGN KEY (motiudelegacioid) REFERENCES portafib.pfi_traduccio(traduccioid);


--
-- TOC entry 2498 (class 2606 OID 26634)
-- Name: pfi_custodia_codibarres_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_codibarres_fk FOREIGN KEY (codibarresid) REFERENCES portafib.pfi_codibarres(codibarresid);


--
-- TOC entry 2499 (class 2606 OID 26639)
-- Name: pfi_custodia_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_entitat_fk FOREIGN KEY (entitatid) REFERENCES portafib.pfi_entitat(entitatid);


--
-- TOC entry 2500 (class 2606 OID 26644)
-- Name: pfi_custodia_plugin_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_plugin_fk FOREIGN KEY (pluginid) REFERENCES portafib.pfi_plugin(pluginid);


--
-- TOC entry 2501 (class 2606 OID 26649)
-- Name: pfi_custodia_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES portafib.pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2502 (class 2606 OID 26654)
-- Name: pfi_custodia_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_custodiainfo
    ADD CONSTRAINT pfi_custodia_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2503 (class 2606 OID 26659)
-- Name: pfi_entitat_custodia_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_custodia_fk FOREIGN KEY (custodiainfoid) REFERENCES portafib.pfi_custodiainfo(custodiainfoid);


--
-- TOC entry 2504 (class 2606 OID 26664)
-- Name: pfi_entitat_fitxer_icon_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_icon_fk FOREIGN KEY (faviconid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2505 (class 2606 OID 26669)
-- Name: pfi_entitat_fitxer_loca_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_loca_fk FOREIGN KEY (logowebid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2506 (class 2606 OID 26674)
-- Name: pfi_entitat_fitxer_lope_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_lope_fk FOREIGN KEY (logowebpeuid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2507 (class 2606 OID 26679)
-- Name: pfi_entitat_fitxer_lose_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_lose_fk FOREIGN KEY (logosegellid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2508 (class 2606 OID 26684)
-- Name: pfi_entitat_fitxer_pdfd_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_fitxer_pdfd_fk FOREIGN KEY (pdfautoritzaciodelegacioid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2509 (class 2606 OID 26689)
-- Name: pfi_entitat_plugin_cert_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_plugin_cert_fk FOREIGN KEY (pluginvalidacertificatid) REFERENCES portafib.pfi_plugin(pluginid);


--
-- TOC entry 2510 (class 2606 OID 26694)
-- Name: pfi_entitat_plugin_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_plugin_fk FOREIGN KEY (pluginid) REFERENCES portafib.pfi_plugin(pluginid);


--
-- TOC entry 2511 (class 2606 OID 26699)
-- Name: pfi_entitat_plugin_rubr_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_plugin_rubr_fk FOREIGN KEY (pluginrubricaid) REFERENCES portafib.pfi_plugin(pluginid);


--
-- TOC entry 2512 (class 2606 OID 26704)
-- Name: pfi_entitat_plugin_vafi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_plugin_vafi_fk FOREIGN KEY (pluginvalidafirmesid) REFERENCES portafib.pfi_plugin(pluginid);


--
-- TOC entry 2513 (class 2606 OID 26709)
-- Name: pfi_entitat_traduccio_firm_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_traduccio_firm_fk FOREIGN KEY (firmatperformatid) REFERENCES portafib.pfi_traduccio(traduccioid);


--
-- TOC entry 2514 (class 2606 OID 26714)
-- Name: pfi_entitat_traduccio_moti_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_traduccio_moti_fk FOREIGN KEY (motiudelegacioid) REFERENCES portafib.pfi_traduccio(traduccioid);


--
-- TOC entry 2515 (class 2606 OID 26719)
-- Name: pfi_entitat_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_entitat
    ADD CONSTRAINT pfi_entitat_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES portafib.pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2516 (class 2606 OID 26724)
-- Name: pfi_estadis_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_estadistica
    ADD CONSTRAINT pfi_estadis_entitat_fk FOREIGN KEY (entitatid) REFERENCES portafib.pfi_entitat(entitatid);


--
-- TOC entry 2517 (class 2606 OID 26729)
-- Name: pfi_estatfirma_colabdeleg_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_colabdeleg_fk FOREIGN KEY (colaboraciodelegacioid) REFERENCES portafib.pfi_colaboraciodelegacio(colaboraciodelegacioid);


--
-- TOC entry 2518 (class 2606 OID 26734)
-- Name: pfi_estatfirma_firma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_firma_fk FOREIGN KEY (firmaid) REFERENCES portafib.pfi_firma(firmaid);


--
-- TOC entry 2519 (class 2606 OID 26739)
-- Name: pfi_estatfirma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_estatdefirma
    ADD CONSTRAINT pfi_estatfirma_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2593 (class 2606 OID 26744)
-- Name: pfi_favorit_usrentitat_fav_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_favorit_usrentitat_fav_fk FOREIGN KEY (favoritid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2594 (class 2606 OID 26749)
-- Name: pfi_favorit_usrentitat_ori_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuarientitatfavorit
    ADD CONSTRAINT pfi_favorit_usrentitat_ori_fk FOREIGN KEY (origenid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2520 (class 2606 OID 26754)
-- Name: pfi_firma_blocfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_firma
    ADD CONSTRAINT pfi_firma_blocfirmes_fk FOREIGN KEY (blocdefirmaid) REFERENCES portafib.pfi_blocdefirmes(blocdefirmesid);


--
-- TOC entry 2521 (class 2606 OID 26759)
-- Name: pfi_firma_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_firma
    ADD CONSTRAINT pfi_firma_fitxer_fk FOREIGN KEY (fitxerfirmatid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2522 (class 2606 OID 26764)
-- Name: pfi_firma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_firma
    ADD CONSTRAINT pfi_firma_usrentitat_fk FOREIGN KEY (destinatariid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2523 (class 2606 OID 26769)
-- Name: pfi_grupentita_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_grupentitat
    ADD CONSTRAINT pfi_grupentita_entitat_fk FOREIGN KEY (entitatid) REFERENCES portafib.pfi_entitat(entitatid);


--
-- TOC entry 2524 (class 2606 OID 26774)
-- Name: pfi_grupusrent_grupentita_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_grupentita_fk FOREIGN KEY (grupentitatid) REFERENCES portafib.pfi_grupentitat(grupentitatid);


--
-- TOC entry 2525 (class 2606 OID 26779)
-- Name: pfi_grupusrent_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_grupentitatusuarientitat
    ADD CONSTRAINT pfi_grupusrent_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2526 (class 2606 OID 26784)
-- Name: pfi_metadada_petifirma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_metadada
    ADD CONSTRAINT pfi_metadada_petifirma_fk FOREIGN KEY (peticiodefirmaid) REFERENCES portafib.pfi_peticiodefirma(peticiodefirmaid);


--
-- TOC entry 2527 (class 2606 OID 26789)
-- Name: pfi_mofitido_plugin_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_plugin_fk FOREIGN KEY (pluginid) REFERENCES portafib.pfi_plugin(pluginid);


--
-- TOC entry 2528 (class 2606 OID 26794)
-- Name: pfi_mofitido_tipusdoc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES portafib.pfi_tipusdocument(tipusdocumentid);


--
-- TOC entry 2529 (class 2606 OID 26799)
-- Name: pfi_notifica_tipnotific_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_notificacio
    ADD CONSTRAINT pfi_notifica_tipnotific_fk FOREIGN KEY (tipusnotificacioid) REFERENCES portafib.pfi_tipusnotificacio(tipusnotificacioid);


--
-- TOC entry 2584 (class 2606 OID 26804)
-- Name: pfi_perfilapp_confapp_1_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioperfil
    ADD CONSTRAINT pfi_perfilapp_confapp_1_fk FOREIGN KEY (usrappconfiguracio1id) REFERENCES portafib.pfi_usuariaplicacioconfig(usuariaplicacioconfigid);


--
-- TOC entry 2585 (class 2606 OID 26809)
-- Name: pfi_perfilapp_confapp_2_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioperfil
    ADD CONSTRAINT pfi_perfilapp_confapp_2_fk FOREIGN KEY (usrappconfiguracio2id) REFERENCES portafib.pfi_usuariaplicacioconfig(usuariaplicacioconfigid);


--
-- TOC entry 2586 (class 2606 OID 26814)
-- Name: pfi_perfilapp_confapp_3_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioperfil
    ADD CONSTRAINT pfi_perfilapp_confapp_3_fk FOREIGN KEY (usrappconfiguracio3id) REFERENCES portafib.pfi_usuariaplicacioconfig(usuariaplicacioconfigid);


--
-- TOC entry 2587 (class 2606 OID 26819)
-- Name: pfi_perfilapp_confapp_4_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioperfil
    ADD CONSTRAINT pfi_perfilapp_confapp_4_fk FOREIGN KEY (usrappconfiguracio4id) REFERENCES portafib.pfi_usuariaplicacioconfig(usuariaplicacioconfigid);


--
-- TOC entry 2588 (class 2606 OID 26824)
-- Name: pfi_perfilapp_confapp_5_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacioperfil
    ADD CONSTRAINT pfi_perfilapp_confapp_5_fk FOREIGN KEY (usrappconfiguracio5id) REFERENCES portafib.pfi_usuariaplicacioconfig(usuariaplicacioconfigid);


--
-- TOC entry 2530 (class 2606 OID 26829)
-- Name: pfi_perfilsua_perfilapp_p_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_perfilsperusrapp
    ADD CONSTRAINT pfi_perfilsua_perfilapp_p_fk FOREIGN KEY (usuariaplicacioperfilid) REFERENCES portafib.pfi_usuariaplicacioperfil(usuariaplicacioperfilid);


--
-- TOC entry 2531 (class 2606 OID 26834)
-- Name: pfi_perfilsua_usrapp_usr_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_perfilsperusrapp
    ADD CONSTRAINT pfi_perfilsua_usrapp_usr_fk FOREIGN KEY (usuariaplicacioid) REFERENCES portafib.pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2532 (class 2606 OID 26839)
-- Name: pfi_permisgrpl_grupentita_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrpl_grupentita_fk FOREIGN KEY (grupentitatid) REFERENCES portafib.pfi_grupentitat(grupentitatid);


--
-- TOC entry 2533 (class 2606 OID 26844)
-- Name: pfi_permisgrpl_plantiflfi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_permisgrupplantilla
    ADD CONSTRAINT pfi_permisgrpl_plantiflfi_fk FOREIGN KEY (fluxdefirmesid) REFERENCES portafib.pfi_plantillafluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2534 (class 2606 OID 26849)
-- Name: pfi_permisuspl_plantiflfi_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisuspl_plantiflfi_fk FOREIGN KEY (fluxdefirmesid) REFERENCES portafib.pfi_plantillafluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2535 (class 2606 OID 26854)
-- Name: pfi_permisuspl_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_permisusuariplantilla
    ADD CONSTRAINT pfi_permisuspl_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2595 (class 2606 OID 26859)
-- Name: pfi_persona_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuaripersona
    ADD CONSTRAINT pfi_persona_fitxer_fk FOREIGN KEY (rubricaid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2596 (class 2606 OID 26864)
-- Name: pfi_persona_idioma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuaripersona
    ADD CONSTRAINT pfi_persona_idioma_fk FOREIGN KEY (idiomaid) REFERENCES portafib.pfi_idioma(idiomaid);


--
-- TOC entry 2536 (class 2606 OID 26869)
-- Name: pfi_petifirma_confapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_confapp_fk FOREIGN KEY (configuraciodefirmaid) REFERENCES portafib.pfi_usuariaplicacioconfig(usuariaplicacioconfigid);


--
-- TOC entry 2537 (class 2606 OID 26874)
-- Name: pfi_petifirma_custodia_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_custodia_fk FOREIGN KEY (custodiainfoid) REFERENCES portafib.pfi_custodiainfo(custodiainfoid);


--
-- TOC entry 2538 (class 2606 OID 26879)
-- Name: pfi_petifirma_fitxer_ada_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_ada_fk FOREIGN KEY (fitxeradaptatid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2539 (class 2606 OID 26884)
-- Name: pfi_petifirma_fitxer_fir_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_fir_fk FOREIGN KEY (fitxerafirmarid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2540 (class 2606 OID 26889)
-- Name: pfi_petifirma_fitxer_log_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_log_fk FOREIGN KEY (logosegellid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2541 (class 2606 OID 26894)
-- Name: pfi_petifirma_fitxer_ori_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fitxer_ori_fk FOREIGN KEY (firmaoriginaldetachedid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2542 (class 2606 OID 26899)
-- Name: pfi_petifirma_fluxfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_fluxfirmes_fk FOREIGN KEY (fluxdefirmesid) REFERENCES portafib.pfi_fluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2543 (class 2606 OID 26904)
-- Name: pfi_petifirma_idioma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_idioma_fk FOREIGN KEY (idiomaid) REFERENCES portafib.pfi_idioma(idiomaid);


--
-- TOC entry 2544 (class 2606 OID 26909)
-- Name: pfi_petifirma_tipusdoc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES portafib.pfi_tipusdocument(tipusdocumentid);


--
-- TOC entry 2545 (class 2606 OID 26914)
-- Name: pfi_petifirma_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES portafib.pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2546 (class 2606 OID 26919)
-- Name: pfi_petifirma_usrentitat_2_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_usrentitat_2_fk FOREIGN KEY (solicitantpersona2id) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2547 (class 2606 OID 26924)
-- Name: pfi_petifirma_usrentitat_3_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_usrentitat_3_fk FOREIGN KEY (solicitantpersona3id) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2548 (class 2606 OID 26929)
-- Name: pfi_petifirma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_peticiodefirma
    ADD CONSTRAINT pfi_petifirma_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2559 (class 2606 OID 26934)
-- Name: pfi_pfwpua_plugin_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_pluginfirmawebperusrapp
    ADD CONSTRAINT pfi_pfwpua_plugin_fk FOREIGN KEY (pluginfirmawebid) REFERENCES portafib.pfi_plugin(pluginid);


--
-- TOC entry 2560 (class 2606 OID 26939)
-- Name: pfi_pfwpua_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_pluginfirmawebperusrapp
    ADD CONSTRAINT pfi_pfwpua_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES portafib.pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2561 (class 2606 OID 26944)
-- Name: pfi_pfwpue_plugin_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_pluginfirmawebperusrent
    ADD CONSTRAINT pfi_pfwpue_plugin_fk FOREIGN KEY (pluginfirmawebid) REFERENCES portafib.pfi_plugin(pluginid);


--
-- TOC entry 2562 (class 2606 OID 26949)
-- Name: pfi_pfwpue_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_pluginfirmawebperusrent
    ADD CONSTRAINT pfi_pfwpue_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2549 (class 2606 OID 26954)
-- Name: pfi_plantiflfi_fluxfirmes_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantiflfi_fluxfirmes_fk FOREIGN KEY (fluxdefirmesid) REFERENCES portafib.pfi_fluxdefirmes(fluxdefirmesid);


--
-- TOC entry 2550 (class 2606 OID 26959)
-- Name: pfi_plantiflfi_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantiflfi_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES portafib.pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2551 (class 2606 OID 26964)
-- Name: pfi_plantiflfi_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_plantillafluxdefirmes
    ADD CONSTRAINT pfi_plantiflfi_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2555 (class 2606 OID 26969)
-- Name: pfi_plugcrida_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_plugincridada
    ADD CONSTRAINT pfi_plugcrida_entitat_fk FOREIGN KEY (entitatid) REFERENCES portafib.pfi_entitat(entitatid);


--
-- TOC entry 2556 (class 2606 OID 26974)
-- Name: pfi_plugcrida_fitxer_param_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_plugincridada
    ADD CONSTRAINT pfi_plugcrida_fitxer_param_fk FOREIGN KEY (parametresfitxerid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2557 (class 2606 OID 26979)
-- Name: pfi_plugcrida_fitxer_retor_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_plugincridada
    ADD CONSTRAINT pfi_plugcrida_fitxer_retor_fk FOREIGN KEY (retornfitxerid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2558 (class 2606 OID 26984)
-- Name: pfi_plugcrida_plugin_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_plugincridada
    ADD CONSTRAINT pfi_plugcrida_plugin_fk FOREIGN KEY (pluginid) REFERENCES portafib.pfi_plugin(pluginid);


--
-- TOC entry 2552 (class 2606 OID 26989)
-- Name: pfi_plugin_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_plugin
    ADD CONSTRAINT pfi_plugin_entitat_fk FOREIGN KEY (entitatid) REFERENCES portafib.pfi_entitat(entitatid);


--
-- TOC entry 2553 (class 2606 OID 26994)
-- Name: pfi_plugin_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_plugin
    ADD CONSTRAINT pfi_plugin_traduccio_desc_fk FOREIGN KEY (descripciocurtaid) REFERENCES portafib.pfi_traduccio(traduccioid);


--
-- TOC entry 2554 (class 2606 OID 26999)
-- Name: pfi_plugin_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_plugin
    ADD CONSTRAINT pfi_plugin_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES portafib.pfi_traduccio(traduccioid);


--
-- TOC entry 2563 (class 2606 OID 27004)
-- Name: pfi_propietat_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_propietatglobal
    ADD CONSTRAINT pfi_propietat_entitat_fk FOREIGN KEY (entitatid) REFERENCES portafib.pfi_entitat(entitatid);


--
-- TOC entry 2564 (class 2606 OID 27009)
-- Name: pfi_rebreavis_tipnotific_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_tipnotific_fk FOREIGN KEY (tipusnotificacioid) REFERENCES portafib.pfi_tipusnotificacio(tipusnotificacioid);


--
-- TOC entry 2565 (class 2606 OID 27014)
-- Name: pfi_rebreavis_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_rebreavis
    ADD CONSTRAINT pfi_rebreavis_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2598 (class 2606 OID 141070)
-- Name: pfi_revdedest_usrentitat_de_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_revisordedestinatari
    ADD CONSTRAINT pfi_revdedest_usrentitat_de_fk FOREIGN KEY (destinatariid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2597 (class 2606 OID 141075)
-- Name: pfi_revdedest_usrentitat_re_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_revisordedestinatari
    ADD CONSTRAINT pfi_revdedest_usrentitat_re_fk FOREIGN KEY (revisorid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2566 (class 2606 OID 27019)
-- Name: pfi_revfirma_firma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_revisordefirma
    ADD CONSTRAINT pfi_revfirma_firma_fk FOREIGN KEY (firmaid) REFERENCES portafib.pfi_firma(firmaid);


--
-- TOC entry 2567 (class 2606 OID 27024)
-- Name: pfi_revfirma_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_revisordefirma
    ADD CONSTRAINT pfi_revfirma_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2568 (class 2606 OID 27029)
-- Name: pfi_roleusrent_role_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusrent_role_fk FOREIGN KEY (roleid) REFERENCES portafib.pfi_role(roleid);


--
-- TOC entry 2569 (class 2606 OID 27034)
-- Name: pfi_roleusrent_usrentitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_roleusuarientitat
    ADD CONSTRAINT pfi_roleusrent_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES portafib.pfi_usuarientitat(usuarientitatid);


--
-- TOC entry 2570 (class 2606 OID 27039)
-- Name: pfi_tipusdoc_traduccio_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdoc_traduccio_fk FOREIGN KEY (nom) REFERENCES portafib.pfi_traduccio(traduccioid);


--
-- TOC entry 2571 (class 2606 OID 27044)
-- Name: pfi_tipusdoc_usrapp_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_tipusdocument
    ADD CONSTRAINT pfi_tipusdoc_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES portafib.pfi_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 2572 (class 2606 OID 27049)
-- Name: pfi_tipusdoccd_colabdeleg_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdoccd_colabdeleg_fk FOREIGN KEY (colaboraciodelegacioid) REFERENCES portafib.pfi_colaboraciodelegacio(colaboraciodelegacioid);


--
-- TOC entry 2573 (class 2606 OID 27054)
-- Name: pfi_tipusdoccd_tipusdoc_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_tipusdocumentcoladele
    ADD CONSTRAINT pfi_tipusdoccd_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES portafib.pfi_tipusdocument(tipusdocumentid);


--
-- TOC entry 2574 (class 2606 OID 27059)
-- Name: pfi_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_traducciomap
    ADD CONSTRAINT pfi_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES portafib.pfi_traduccio(traduccioid);


--
-- TOC entry 2575 (class 2606 OID 27064)
-- Name: pfi_usrapp_custodia_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_custodia_fk FOREIGN KEY (custodiainfoid) REFERENCES portafib.pfi_custodiainfo(custodiainfoid);


--
-- TOC entry 2576 (class 2606 OID 27069)
-- Name: pfi_usrapp_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_entitat_fk FOREIGN KEY (entitatid) REFERENCES portafib.pfi_entitat(entitatid);


--
-- TOC entry 2577 (class 2606 OID 27074)
-- Name: pfi_usrapp_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_fitxer_fk FOREIGN KEY (logosegellid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2578 (class 2606 OID 27079)
-- Name: pfi_usrapp_idioma_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuariaplicacio
    ADD CONSTRAINT pfi_usrapp_idioma_fk FOREIGN KEY (idiomaid) REFERENCES portafib.pfi_idioma(idiomaid);


--
-- TOC entry 2589 (class 2606 OID 27084)
-- Name: pfi_usrentitat_custodia_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_custodia_fk FOREIGN KEY (custodiainfoid) REFERENCES portafib.pfi_custodiainfo(custodiainfoid);


--
-- TOC entry 2590 (class 2606 OID 27089)
-- Name: pfi_usrentitat_entitat_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_entitat_fk FOREIGN KEY (entitatid) REFERENCES portafib.pfi_entitat(entitatid);


--
-- TOC entry 2591 (class 2606 OID 27094)
-- Name: pfi_usrentitat_fitxer_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_fitxer_fk FOREIGN KEY (logosegellid) REFERENCES portafib.pfi_fitxer(fitxerid);


--
-- TOC entry 2592 (class 2606 OID 27099)
-- Name: pfi_usrentitat_persona_fk; Type: FK CONSTRAINT; Schema: portafib; Owner: portafib
--

ALTER TABLE ONLY portafib.pfi_usuarientitat
    ADD CONSTRAINT pfi_usrentitat_persona_fk FOREIGN KEY (usuaripersonaid) REFERENCES portafib.pfi_usuaripersona(usuaripersonaid);


--
-- TOC entry 2711 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA portafib; Type: ACL; Schema: -; Owner: portafib
--

REVOKE ALL ON SCHEMA portafib FROM PUBLIC;
REVOKE ALL ON SCHEMA portafib FROM portafib;
GRANT ALL ON SCHEMA portafib TO portafib;


-- Completed on 2024-05-15 11:13:09

--
-- PostgreSQL database dump complete
--

