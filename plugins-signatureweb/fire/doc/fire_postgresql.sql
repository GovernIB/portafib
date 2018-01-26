
SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;



CREATE TABLE tb_aplicaciones (
    id character varying(48) NOT NULL,
    nombre character varying(45) NOT NULL,
    responsable character varying(45) NOT NULL,
    resp_correo character varying(45) DEFAULT NULL::character varying,
    resp_telefono character varying(30) DEFAULT NULL::character varying,
    fecha_alta timestamp without time zone NOT NULL,
    cer character varying(5000) NOT NULL,
    huella character varying(28) NOT NULL,
    CONSTRAINT tb_aplicaciones_pkey PRIMARY KEY (id)
);



CREATE TABLE tb_configuracion (
    parametro character varying(30) NOT NULL,
    valor character varying(45) DEFAULT NULL::character varying,
    CONSTRAINT tb_configuracion_pkey PRIMARY KEY (parametro)
);

-- La contrasenya esta en SHA256_BASE64. Valor actual 1111
INSERT INTO tb_configuracion VALUES ('admin_pass', 'D/4avRoIIVNTwjPW4AlhPpXuxCU4Mqdhryj/N6xaFQw=');
