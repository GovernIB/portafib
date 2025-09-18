

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




--######################################################################
--##### 11/09/2025 Capçaleres d'entitat durant la signatura WEB #1058
--######################################################################

INSERT INTO pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid)
SELECT pfi_propietatglobal_seq.nextval as propietaglobalid ,'es.caib.portafib.signatureheader.backgroundcolor' AS clau, 
        'Opcional. Nou a la versió 3.0.9. Valor per defecte defecte és #2E8B57. En la pantalla de selecció del mòdul de firma posa una capçalera amb color de fons definit per aquesta propietat. Només es mostrarà la capçalera si la propietat es.caib.portafib.signatureheader.enabled val true' AS descripcio,
        entitatid
        FROM pfi_entitat;

   
INSERT INTO pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid)
SELECT pfi_propietatglobal_seq.nextval as propietaglobalid ,'es.caib.portafib.signatureheader.logourl' AS clau, 
        'Opcional. Nou a la versió 3.0.9. Valor per defect el logo de l´entitat a la capçalera. En la pantalla de selecció del mòdul de firma posa una capçalera amb un logo definit per aquesta propietat. Només es mostrarà la capçalera si la propietat es.caib.portafib.signatureheader.enabled val true' AS descripcio,
        entitatid
        FROM pfi_entitat;
   

INSERT INTO pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid)
SELECT pfi_propietatglobal_seq.nextval as propietaglobalid ,'es.caib.portafib.signatureheader.text' AS clau, 
        'Opcional. Nou a la versió 3.0.9. Per defecte és el nom de l´entitat. En la pantalla de selecció del mòdul de firma posa una capçalera amb un text definit per aquesta propietat. Si no esta definida el valor per defecte és el nom de l´entitat de PortaFIB. Si val "-" llavors significa que no es vol mostrar cap text. Només es mostrarà la capçalera si la propietat es.caib.portafib.signatureheader.enabled val true' AS descripcio,
        entitatid
        FROM pfi_entitat;
        
INSERT INTO pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid)
SELECT pfi_propietatglobal_seq.nextval as propietaglobalid ,'es.caib.portafib.signatureheader.enabled' AS clau, 
        'Opcional. Valor per defecte false. Nou a la versió 3.0.9. En la pantalla de selecció del mòdul de firma posa una capçalera si aquesta propietat val true.' AS descripcio,
        entitatid
        FROM pfi_entitat;


--######################################################################
--##### 17/09/2025 Cercar solució al problema d'ignorar validació de NIf en Certificats de Pseudònim #1035
--######################################################################

CREATE SEQUENCE pfi_pseudonim_seq START WITH 1000 increment by 1;

CREATE TABLE pfi_pseudonim
(
   pseudonimid number(19,0) DEFAULT pfi_pseudonim_seq.nextval, 
   pseudonim varchar2(255 char) NOT NULL, 
   nif varchar2(255 char) NOT NULL
);

alter table pfi_pseudonim add CONSTRAINT pfi_pseudonim_pk PRIMARY KEY (pseudonimid);

grant select,insert,delete,update on pfi_pseudonim to www_portafib;
