

-- ================================================
-- 2019-10-21 Normalitzar package del Plugin de AutoFirma #89 
-- ================================================

UPDATE pfi_plugin
  SET classe='org.fundaciobit.plugins.signatureweb.afirmatriphaseserver.AfirmaTriphaseSignatureWebPlugin'
  WHERE classe='org.fundaciobit.plugin.signatureweb.afirmatriphaseserver.AfirmaTriphaseSignatureWebPlugin';
  

UPDATE pfi_plugin
  SET classe='org.fundaciobit.plugins.signatureserver.afirmalibs.AfirmaLibsSignatureServerPlugin'
  WHERE classe='org.fundaciobit.plugin.signatureserver.afirmalibs.AfirmaLibsSignatureServerPlugin';
  
  
-- ================================================
-- 2019-10-21  Fer còpia de Configuració en Petició de Firma #371 
-- ================================================
 
 ALTER TABLE pfi_usuariaplicacioconfig
  ADD esdepeticio NUMBER(1,0) NOT NULL DEFAULT 0;