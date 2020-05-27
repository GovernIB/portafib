

-- ================================================
-- 2020-05-27 Optimitzar consultes #434
-- ================================================

-- Indexos per optimitzar les consultes sobre bitàcoles
-- Alerta! Comprovar abans que els índexos no existeixen!!!!!
create index pfi_bitacola_entitatid_i on pfi_bitacola (entitatid);
create index pfi_bitacola_data_i on pfi_bitacola (data);
create index pfi_bitacola_tipusobjecte_i on pfi_bitacola (tipusobjecte);
create index pfi_bitacola_tipusoperacio_i on pfi_bitacola (tipusoperacio);
