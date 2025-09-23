



--######################################################################
--##### 28/08/2025 Gestió via BBDD de Correus Agrupats #1063 
--######################################################################

CREATE SEQUENCE pfi_correuagrupat_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;

CREATE TABLE pfi_correuagrupat
(
   correuagrupatid bigint NOT NULL DEFAULT nextval('pfi_correuagrupat_seq'), 
   email character varying(255) NOT NULL, 
   subject character varying(255) NOT NULL, 
   message text NOT NULL, 
   html boolean NOT NULL, 
   usuarientitatid character varying(101) NOT NULL, 
   datacreacio timestamp without time zone NOT NULL,
   error text,
   CONSTRAINT pfi_correuagrupat_pk PRIMARY KEY (correuagrupatid), 
   CONSTRAINT pfi_correagrup_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat (usuarientitatid) ON UPDATE NO ACTION ON DELETE NO ACTION
);


create index pfi_correuagrupat_pk_i on pfi_correuagrupat (correuagrupatid);
create index pfi_correagrup_usrentitat_fk_i on pfi_correuagrupat (usuarientitatid);



--######################################################################
--##### 17/09/2025 Cercar solució al problema d'ignorar validació de NIf en Certificats de Pseudònim #1035
--######################################################################


CREATE SEQUENCE pfi_pseudonim_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
  
  
CREATE TABLE pfi_pseudonim
(
   pseudonimid bigint NOT NULL DEFAULT nextval('pfi_pseudonim_seq'), 
   pseudonim character varying(255) NOT NULL, 
   nif character varying(255) NOT NULL, 
   CONSTRAINT pfi_pseudonim_pk PRIMARY KEY (pseudonimid)
);

create index pfi_pseudonim_pk_i on pfi_pseudonim (pseudonimid);
  