
-- =============================================================
--  2018/03/13 Modificacions en la Taula Plugins #160
-- =============================================================

ALTER TABLE pfi_plugin MODIFY (codi NOT NULL);


-- ===========================================
-- 2018/03/15 Política de Custòdia #165
-- ===========================================

-- XYZ ZZZ ALTER TABLE pfi_usuarientitat DROP COLUMN potcustodiar;
-- XYZ ZZZ ALTER TABLE pfi_usuariaplicacio DROP COLUMN potcustodiar;