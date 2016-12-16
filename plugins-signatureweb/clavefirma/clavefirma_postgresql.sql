--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2016-11-28 13:00:09

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 172 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1949 (class 0 OID 0)
-- Dependencies: 172
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 66829)
-- Name: tb_aplicaciones; Type: TABLE; Schema: public; Owner: clavefirma; Tablespace: 
--

CREATE TABLE tb_aplicaciones (
    id character varying(48) NOT NULL,
    nombre character varying(45) NOT NULL,
    responsable character varying(45) NOT NULL,
    resp_correo character varying(45) DEFAULT NULL::character varying,
    resp_telefono character varying(30) DEFAULT NULL::character varying,
    fecha_alta timestamp without time zone NOT NULL,
    cer character varying(5000) NOT NULL,
    huella character varying(28) NOT NULL
);


ALTER TABLE public.tb_aplicaciones OWNER TO clavefirma;

--
-- TOC entry 171 (class 1259 OID 66839)
-- Name: tb_configuracion; Type: TABLE; Schema: public; Owner: clavefirma; Tablespace: 
--

CREATE TABLE tb_configuracion (
    parametro character varying(30) NOT NULL,
    valor character varying(45) DEFAULT NULL::character varying
);


ALTER TABLE public.tb_configuracion OWNER TO clavefirma;

--
-- TOC entry 1940 (class 0 OID 66829)
-- Dependencies: 170
-- Data for Name: tb_aplicaciones; Type: TABLE DATA; Schema: public; Owner: clavefirma
--

INSERT INTO tb_aplicaciones VALUES ('930CD4CDF298', 'ProvaNom', 'Antoni Nadal bennasar', 'anadal@fundaciobit.org', '600223673', '2016-11-07 00:00:00', 'MIIGnTCCBYWgAwIBAgIQEq02gLUPaypXqvLyghY2ezANBgkqhkiG9w0BAQsFADBL
MQswCQYDVQQGEwJFUzERMA8GA1UECgwIRk5NVC1SQ00xDjAMBgNVBAsMBUNlcmVz
MRkwFwYDVQQDDBBBQyBGTk1UIFVzdWFyaW9zMB4XDTE2MDgxMDA5MjUwNloXDTIw
MDgxMDA5MjUwNlowdzELMAkGA1UEBhMCRVMxEjAQBgNVBAUTCTQzMDk2ODQ1QzEX
MBUGA1UEBAwOTkFEQUwgQkVOTkFTQVIxDzANBgNVBCoMBkFOVE9OSTEqMCgGA1UE
AwwhTkFEQUwgQkVOTkFTQVIgQU5UT05JIC0gNDMwOTY4NDVDMIIBIjANBgkqhkiG
9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn2DWn9INedV1Od2pxxDVVfwwrgRiAunrCPqU
c7ZBWLACKmjIpfj84DPrXbOfGdiTVKPx6OyNY5xStWLnyIoMqoqwcuEFANSPPeyb
ChwXn/0hKzzg//533rDfk1xnHtTvKOGLtaHxt9QO3EWdZp7wNOS5NqVAgc6nTSKZ
ijIWGPOHe/YteqS5JO7CTQTn4g1B6+esvHlBaQ1aB3bLwpLdvYd7Cz5zYmjnTIfL
n8LM+asLn+WYM3cHPA81+NEcmrzcdU9SayUaoEN6C5BzRAwmunjf8hHKP+Zweg6f
B9tB73ZMJexdyKbHe3V0aA6AJ5Yo7xlwAWI6n01fpUugUN6qBQIDAQABo4IDTzCC
A0swgYIGA1UdEQR7MHmBE0VTUE9STEVSSUBHTUFJTC5DT02kYjBgMRgwFgYJKwYB
BAGsZgEEDAk0MzA5Njg0NUMxFzAVBgkrBgEEAaxmAQMMCEJFTk5BU0FSMRQwEgYJ
KwYBBAGsZgECDAVOQURBTDEVMBMGCSsGAQQBrGYBAQwGQU5UT05JMAwGA1UdEwEB
/wQCMAAwDgYDVR0PAQH/BAQDAgXgMCMGA1UdJQQcMBoGCCsGAQUFBwMEBggrBgEF
BQcDAgYEVR0lADAdBgNVHQ4EFgQUO9XJ3bJsaXXJsrFRbOhLEVqts58wHwYDVR0j
BBgwFoAUsdRPxCN5+kQFCcbrOc/oNbC4IGQwgYIGCCsGAQUFBwEBBHYwdDA9Bggr
BgEFBQcwAYYxaHR0cDovL29jc3B1c3UuY2VydC5mbm10LmVzL29jc3B1c3UvT2Nz
cFJlc3BvbmRlcjAzBggrBgEFBQcwAoYnaHR0cDovL3d3dy5jZXJ0LmZubXQuZXMv
Y2VydHMvQUNVU1UuY3J0MIHdBgNVHSAEgdUwgdIwgc8GCisGAQQBrGYDCgEwgcAw
KQYIKwYBBQUHAgEWHWh0dHA6Ly93d3cuY2VydC5mbm10LmVzL2RwY3MvMIGSBggr
BgEFBQcCAjCBhQyBgkNlcnRpZmljYWRvIHJlY29ub2NpZG8uIFN1amV0byBhIGxh
cyBjb25kaWNpb25lcyBkZSB1c28gZXhwdWVzdGFzIGVuIGxhIERQQyBkZSBsYSBG
Tk1ULVJDTSAoQy9Kb3JnZSBKdWFuIDEwNi0yODAwOS1NYWRyaWQtRXNwYcOxYSkw
JQYIKwYBBQUHAQMEGTAXMAgGBgQAjkYBATALBgYEAI5GAQMCAQ8wgbQGA1UdHwSB
rDCBqTCBpqCBo6CBoIaBnWxkYXA6Ly9sZGFwdXN1LmNlcnQuZm5tdC5lcy9jbj1D
Ukw3NDIsY249QUMlMjBGTk1UJTIwVXN1YXJpb3Msb3U9Q0VSRVMsbz1GTk1ULVJD
TSxjPUVTP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3Q7YmluYXJ5P2Jhc2U/b2Jq
ZWN0Y2xhc3M9Y1JMRGlzdHJpYnV0aW9uUG9pbnQwDQYJKoZIhvcNAQELBQADggEB
ACelbZkl/AO/bYUbK7eddcG4vhuQB+iLZJkMXeslQFzD70zggeSMeVHrmJDSKtKG
sZaM98n1BQoMLzpFZdbMH40bUNYBZwfd++niVtzcmRxwVZsBc6siZ4EyyQWw744B
WGbh36PDH/gmKLaOysMGva7Kzk+U77WZRBvMQlFCvctL6iGDD8tb7z/yPl17D34G
Fmz+lHS72vdeLjPAMtoUsMTSv/C9N7nieOO1bzLCAzGnjCQ2A/nukBZlfRfSicFL
0/k9N8wJmjYsjHmYsv6BpX05mtBzETsnLiNSADol1mIc7qAy4b2jN5jODVzlfEDZ
7cNF+RO5tHUZl6UmlmEOukQ=', 'gKorvyE6m3tvVIi5j/hkbyvPdbo=');


--
-- TOC entry 1941 (class 0 OID 66839)
-- Dependencies: 171
-- Data for Name: tb_configuracion; Type: TABLE DATA; Schema: public; Owner: clavefirma
--

INSERT INTO tb_configuracion VALUES ('admin_pass', 'D/4avRoIIVNTwjPW4AlhPpXuxCU4Mqdhryj/N6xaFQw=');


--
-- TOC entry 1830 (class 2606 OID 66838)
-- Name: tb_aplicaciones_pkey; Type: CONSTRAINT; Schema: public; Owner: clavefirma; Tablespace: 
--

ALTER TABLE ONLY tb_aplicaciones
    ADD CONSTRAINT tb_aplicaciones_pkey PRIMARY KEY (id);


--
-- TOC entry 1832 (class 2606 OID 66844)
-- Name: tb_configuracion_pkey; Type: CONSTRAINT; Schema: public; Owner: clavefirma; Tablespace: 
--

ALTER TABLE ONLY tb_configuracion
    ADD CONSTRAINT tb_configuracion_pkey PRIMARY KEY (parametro);


--
-- TOC entry 1948 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-11-28 13:00:10

--
-- PostgreSQL database dump complete
--

