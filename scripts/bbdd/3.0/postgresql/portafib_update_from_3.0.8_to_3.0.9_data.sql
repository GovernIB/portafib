

--######################################################################
--##### 14/08/2025 Substituir el sistema actual CSP (Content Security Policy X-Frame-Options) #1075
--######################################################################

insert into pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid, valor)
   values(nextval('pfi_propietatglobal_seq'), 'es.caib.portafib.csp-frame-ancestors', 'Opcional. Content Security Policy X-Frame-Options. Llistat de dominis separats per espai als que es permetra obrir iframes. Exemple http://10.215.216.175:1901', null, '');


--######################################################################
--##### 11/09/2025 Capçaleres d'entitat durant la signatura WEB #1058
--######################################################################


INSERT INTO pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid)
SELECT nextval('pfi_propietatglobal_seq') as propietaglobalid ,'es.caib.portafib.signatureheader.backgroundcolor' AS clau, 
        'Opcional. Nou a la versió 3.0.9. Valor per defecte defecte és #2E8B57. En la pantalla de selecció del mòdul de firma posa una capçalera amb color de fons definit per aquesta propietat. Només es mostrarà la capçalera si la propietat es.caib.portafib.signatureheader.enabled val true' AS descripcio,
        entitatid
        FROM pfi_entitat;

   
INSERT INTO pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid)
SELECT nextval('pfi_propietatglobal_seq') as propietaglobalid ,'es.caib.portafib.signatureheader.logourl' AS clau, 
        'Opcional. Nou a la versió 3.0.9. Valor per defect el logo de l´entitat a la capçalera. En la pantalla de selecció del mòdul de firma posa una capçalera amb un logo definit per aquesta propietat. Només es mostrarà la capçalera si la propietat es.caib.portafib.signatureheader.enabled val true' AS descripcio,
        entitatid
        FROM pfi_entitat;
   

INSERT INTO pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid)
SELECT nextval('pfi_propietatglobal_seq') as propietaglobalid ,'es.caib.portafib.signatureheader.text' AS clau, 
        'Opcional. Nou a la versió 3.0.9. Per defecte és el nom de l´entitat. En la pantalla de selecció del mòdul de firma posa una capçalera amb un text definit per aquesta propietat. Si no esta definida el valor per defecte és el nom de l´entitat de PortaFIB. Si val "-" llavors significa que no es vol mostrar cap text. Només es mostrarà la capçalera si la propietat es.caib.portafib.signatureheader.enabled val true' AS descripcio,
        entitatid
        FROM pfi_entitat;
        
INSERT INTO pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid)
SELECT nextval('pfi_propietatglobal_seq') as propietaglobalid ,'es.caib.portafib.signatureheader.enabled' AS clau, 
        'Opcional. Valor per defecte false. Nou a la versió 3.0.9. En la pantalla de selecció del mòdul de firma posa una capçalera si aquesta propietat val true.' AS descripcio,
        entitatid
        FROM pfi_entitat;

