
-- =============================================================
--  2018/03/13 Modificacions en la Taula Plugins #160
-- =============================================================

ALTER TABLE pfi_plugin ALTER COLUMN codi SET NOT NULL;


-- ===========================================
-- 2018/03/15 Política de Custòdia #165
-- ===========================================

ALTER TABLE pfi_usuarientitat DROP COLUMN potcustodiar;
ALTER TABLE pfi_usuariaplicacio DROP COLUMN potcustodiar;