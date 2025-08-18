

--######################################################################
--##### 14/08/2025 Substituir el sistema actual CSP (Content Security Policy X-Frame-Options) #1075
--######################################################################

insert into pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid, valor)
   values(pfi_propietatglobal_seq.nextval, 'es.caib.portafib.csp-frame-ancestors', 'Opcional. Content Security Policy X-Frame-Options. Llistat de dominis separats per espai als que es permetra obrir iframes. Exemple http://10.215.216.175:1901', null, '');
