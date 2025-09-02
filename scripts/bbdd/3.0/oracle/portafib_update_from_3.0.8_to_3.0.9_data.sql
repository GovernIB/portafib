

--######################################################################
--##### 14/08/2025 Substituir el sistema actual CSP (Content Security Policy X-Frame-Options) #1075
--######################################################################

insert into pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid, valor)
   values(pfi_propietatglobal_seq.nextval, 'es.caib.portafib.csp-frame-ancestors', 'Opcional. Content Security Policy X-Frame-Options. Llistat de dominis separats per espai als que es permetra obrir iframes. Exemple http://10.215.216.175:1901', null, '');




--######################################################################
--##### 28/08/2025 Gestió via BBDD de Correus Agrupats #1063 
--######################################################################


CREATE SEQUENCE pfi_correuagrupat_seq  START WITH 1000 increment by 1;

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

grant select,insert,delete,update on pfi_correuagrupat to www_portafib;