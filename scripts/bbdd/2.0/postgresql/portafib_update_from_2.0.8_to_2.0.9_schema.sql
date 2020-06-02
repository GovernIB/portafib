

-- ================================================
-- 2020-05-27 Optimitzar consultes #434
-- ================================================

-- Indexos per optimitzar les consultes sobre bitàcoles
create index pfi_bitacola_entitatid_i on pfi_bitacola (entitatid);
create index pfi_bitacola_data_i on pfi_bitacola (data);
create index pfi_bitacola_enttipobj_i on pfi_bitacola (entitatid, tipusobjecte);
create index pfi_bitacola_enttipope_i on pfi_bitacola (entitatid, tipusoperacio);
-- Comprovar si els indexos existeixen
drop index pfi_bitacola_tipusobjecte_i;
drop index pfi_bitacola_tipusoperacio_i;

-- Indexos per optimitzar les consultes sobre notificacions
create index pfi_notificacio_datacreacio_i on pfi_notificacio (datacreacio);
-- Renombrar l'index ja que la clau forana ja no existeix
alter index pfi_notifica_peticioid_fk_i rename to pfi_notifica_peticioid_i;