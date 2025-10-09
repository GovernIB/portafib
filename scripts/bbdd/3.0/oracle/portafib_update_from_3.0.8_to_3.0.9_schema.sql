


--######################################################################
--##### 28/08/2025 Gestió via BBDD de Correus Agrupats #1063 
--######################################################################


CREATE SEQUENCE pfi_correuagrupat_seq  START WITH 1000 increment by 1;

grant select,alter on pfi_correuagrupat_seq to www_portafib;

CREATE TABLE pfi_correuagrupat
(
   correuagrupatid number(19,0) DEFAULT pfi_correuagrupat_seq.nextval, 
   email varchar2(255 char) NOT NULL, 
   subject varchar2(255 char) NOT NULL, 
   message clob NOT NULL, 
   html number(1,0) NOT NULL, 
   usuarientitatid varchar2(101 char) NOT NULL, 
   datacreacio timestamp NOT NULL,
   error clob
);

alter table pfi_correuagrupat add CONSTRAINT pfi_correuagrupat_pk PRIMARY KEY (correuagrupatid);

alter table pfi_correuagrupat add CONSTRAINT pfi_correagrup_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat (usuarientitatid);

create index pfi_correagrup_usrentitat_fk_i on pfi_correuagrupat (usuarientitatid);

grant select,alter on pfi_correuagrupat to www_portafib;




--######################################################################
--##### 17/09/2025 Cercar solució al problema d'ignorar validació de NIf en Certificats de Pseudònim #1035
--######################################################################

CREATE SEQUENCE pfi_pseudonim_seq START WITH 1000 increment by 1;

grant select,insert,delete,update on pfi_pseudonim_seq to www_portafib;

CREATE TABLE pfi_pseudonim
(
   pseudonimid number(19,0) DEFAULT pfi_pseudonim_seq.nextval, 
   pseudonim varchar2(255 char) NOT NULL, 
   nif varchar2(255 char) NOT NULL
);

alter table pfi_pseudonim add CONSTRAINT pfi_pseudonim_pk PRIMARY KEY (pseudonimid);

grant select,insert,delete,update on pfi_pseudonim to www_portafib;
