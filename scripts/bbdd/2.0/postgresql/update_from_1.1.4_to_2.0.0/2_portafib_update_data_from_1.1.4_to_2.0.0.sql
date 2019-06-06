
-- ================================================
-- 2018/03/13 Modificacions en la Taula Plugins #160
-- ================================================

UPDATE pfi_plugin SET codi=entitatid || '_' || pluginid::text WHERE entitatid is not null;

UPDATE pfi_plugin SET codi=pluginid::text WHERE entitatid is null;

UPDATE pfi_plugin SET politicadeus=1 WHERE entitatid is not null;

-- ================================================
-- 2018/03/14 Tipus Base pel TipusDocumentals #161
-- ================================================

UPDATE pfi_tipusdocument SET tipusdocumentbaseid=tipusdocumentid WHERE tipusdocumentid < 100 AND tipusdocumentid >= 0;

-- ===========================================
-- 2018/03/15 Política de Custòdia #165
-- ===========================================

-- No permetre
UPDATE pfi_entitat SET politicacustodia=0 WHERE custodiainfoid IS NULL;
-- Definit en l'entitat
UPDATE pfi_entitat SET politicacustodia=4 WHERE custodiainfoid IS NOT NULL;

-- Definit en l'entitat
UPDATE pfi_usuarientitat SET politicacustodia=4 WHERE potcustodiar=true;
-- No permetre
UPDATE pfi_usuarientitat SET politicacustodia=0 WHERE potcustodiar=false;
-- El que digui l'entitat
UPDATE pfi_usuarientitat SET politicacustodia=-1 WHERE potcustodiar is null;

-- Definit en l'entitat
UPDATE pfi_usuariaplicacio SET politicacustodia=4 WHERE potcustodiar=true;
-- No permetre
UPDATE pfi_usuariaplicacio SET politicacustodia=0 WHERE potcustodiar=false;
-- El que digui l'entitat
UPDATE pfi_usuariaplicacio SET politicacustodia=-1 WHERE potcustodiar is null;

-- ======================================================================
-- 2018/05/01 Configuració de Firma de UsuariApp 	Us política de Firma) #148
-- ======================================================================

UPDATE pfi_entitat SET uspoliticadefirma=1 where policyidentifier is not NULL;


-- =================================================
-- 2018/09/13 Revisor de Firma #169
-- =================================================

INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_REVI', 'Revisor de Firmes', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (90, NULL, 'notificacioavis.requerit_per_revisar', true);


-- ======================================================================
-- 2018/11/08 Neteja de taules de BBDD #199
-- ======================================================================

UPDATE pfi_peticiodefirma SET prioritatid=3 WHERE prioritatid <3;
UPDATE pfi_peticiodefirma SET prioritatid=7 WHERE prioritatid >7;




