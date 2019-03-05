
-- ================================================
-- 2018/03/13 Modificacions en la Taula Plugins #160
-- ================================================

UPDATE pfi_plugin SET codi=entitatid || '_' || to_char(pluginid) WHERE entitatid is not null;

UPDATE pfi_plugin SET codi=to_char(pluginid) WHERE entitatid is null;

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

UPDATE pfi_usuarientitat SET politicacustodia=4 WHERE potcustodiar=1;
UPDATE pfi_usuarientitat SET politicacustodia=-1 WHERE potcustodiar=0;
UPDATE pfi_usuarientitat SET politicacustodia=-1 WHERE potcustodiar is null;

--- ***** No ha funcionat... No són necessaris
--UPDATE pfi_usuariaplicacio SET politicacustodia=4 WHERE potcustodiar=1;
--UPDATE pfi_usuariaplicacio SET politicacustodia=-1 WHERE potcustodiar=0;
--UPDATE pfi_usuariaplicacio SET politicacustodia=-1 WHERE potcustodiar is null;

-- ======================================================================
-- 2018/05/01 Configuració de Firma de UsuariApp 	Us política de Firma) #148
-- ======================================================================

UPDATE pfi_entitat SET uspoliticadefirma=1 where policyidentifier is not NULL;

-- ======================================================================
-- 2018/08/24 Actualització dels packages de pluginsib a versió 2.0
-- ======================================================================

UPDATE pfi_tipusmetadada SET descripcio='org.fundaciobit.pluginsib.core.utils.Base64' WHERE tipusmetadadaid=4;
UPDATE pfi_tipusmetadada SET descripcio='org.fundaciobit.pluginsib.core.utils.ISO8601' WHERE tipusmetadadaid=5;

-- =================================================
-- 2018/09/13 Revisor de Firma #169
-- =================================================

INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_REVI', 'Revisor de Firmes', NULL);
INSERT INTO pfi_tipusestatdefirmainicial(tipusestatdefirmainicialid, nom) VALUES (3,'tipusestatdefirmainicial.ASSIGNAT_PER_REVISAR');
INSERT INTO pfi_tipusestatdefirmafinal(tipusestatdefirmafinalid, nom, descripcio) VALUES (5, 'tipusestatdefirmafinal.ACCEPTAT', 'Es quan un revisor de firmes accepta la firma');
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (90, NULL, 'notificacioavis.requerit_per_revisar', 1);


-- ======================================================================
-- 2018/11/08 Neteja de taules de BBDD #199
-- ======================================================================

UPDATE pfi_peticiodefirma SET prioritatid=3 WHERE prioritatid <3;
UPDATE pfi_peticiodefirma SET prioritatid=7 WHERE prioritatid >7;