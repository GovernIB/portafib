-- ================================================
-- 2018/03/13 Modificacions en la Taula Plugins #160
-- ================================================

UPDATE pfi_plugin SET codi=entitatid || '_' || pluginid::text WHERE entitatid is not null;

UPDATE pfi_plugin SET codi=pluginid::text WHERE entitatid is null;

UPDATE pfi_plugin SET politicadeus=1 WHERE entitatid is not null;

-- ================================================
-- 2018/03/14 Tipus Base pel TipusDocumentals #161
-- ================================================

UPDATE pfi_tipusdocument SET tipusdocumentbaseid=tipusdocumentid WHERE tipusdocumentid < 100;

-- ===========================================
-- 2018/03/15 Política de Custòdia #165
-- ===========================================

UPDATE pfi_entitat SET politicacustodia=0 WHERE custodiainfoid IS NULL;
UPDATE pfi_entitat SET politicacustodia=4 WHERE custodiainfoid IS NOT NULL;

UPDATE pfi_usuarientitat SET politicacustodia=4 WHERE potcustodiar=true;
UPDATE pfi_usuarientitat SET politicacustodia=-1 WHERE potcustodiar=false;
UPDATE pfi_usuarientitat SET politicacustodia=-1 WHERE potcustodiar is null;

UPDATE pfi_usuariaplicacio SET politicacustodia=4 WHERE potcustodiar=true;
UPDATE pfi_usuariaplicacio SET politicacustodia=-1 WHERE potcustodiar=false;
UPDATE pfi_usuariaplicacio SET politicacustodia=-1 WHERE potcustodiar is null;