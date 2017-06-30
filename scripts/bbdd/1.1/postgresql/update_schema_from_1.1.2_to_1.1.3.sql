
-- ========================================
-- 2017/06/27 Netejar Peticions de Firma d'arxius innecessaris
-- ========================================

ALTER TABLE pfi_peticiodefirma ALTER COLUMN fitxerafirmarid DROP NOT NULL;
