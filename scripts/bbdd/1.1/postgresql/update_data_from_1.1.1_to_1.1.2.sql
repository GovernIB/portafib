
-- ========================================
-- 2016/10/13 Activar usuaris quan es crein des d'Agents Seycon #83
-- ========================================

INSERT INTO pfi_propietatglobal(clau, valor, descripcio) VALUES ('es.caib.portafib.activeusuarientitatafteragentseyconcreation', NULL, 'Opcional. En entorns CAIB, quan un agent seycon dóna d´alta un usuari a PortaFIB, emprant aquesta propietat podem decidir si aquest usuari-entitat es crearà activat (true) o desactivat (false o no definit)');


-- ========================================
-- 2016/10/10 Suportar SMIME en Plugin de Autofirma de @firma #21
-- ========================================

INSERT INTO pfi_tipusfirma VALUES (3, '<p>SMIME</p>', 'SMIME', false);
