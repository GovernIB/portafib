

-- ================================================
-- 2020-05-27 Optimitzar consultes #434
-- ================================================

-- Indexos per optimitzar les consultes sobre bitàcoles
-- Comprovar abans que els índexos no existeixen.
create index pfi_bitacola_data_i on pfi_bitacola (data);
create index pfi_bitacola_objecteid_i on pfi_bitacola (objecteid);
create index pfi_bitacola_enttipobj_i on pfi_bitacola (entitatid, tipusobjecte);
create index pfi_bitacola_enttipope_i on pfi_bitacola (entitatid, tipusoperacio);
-- Comprovar si els indexos existeixen
drop index pfi_bitacola_entitatid_i;
drop index pfi_bitacola_tipusobjecte_i;
drop index pfi_bitacola_tipusoperacio_i;

-- Indexos per optimitzar les consultes sobre notificacions
create index pfi_notificacio_datacreacio_i on pfi_notificacio (datacreacio);
create index pfi_notificacio_usrappid_i on pfi_notificacio (usuariaplicacioid);
create index pfi_notificacio_bloqreint_i on pfi_notificacio (bloquejada, reintents);
-- Renombrar l'index ja que la clau forana ja no existeix
alter index pfi_notifica_peticioid_fk_i rename to pfi_notifica_peticioid_i;


-- ================================================
-- 2020-06-04 Els identificadors de l'API de passarela no caben dins la bitàcola #436
-- ================================================

ALTER TABLE pfi_bitacola MODIFY (objecteid varchar2(100 char) );