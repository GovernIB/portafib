

-- ===============================================================
-- 2021-02-10  Rendiment de la consulta de peticions pendents #535
-- ===============================================================

drop index pfi_estatfirma_estatinid_fk_i;
create index pfi_estatfirma_estats_i on pfi_estatdefirma (tipusestatdefirmainicialid, tipusestatdefirmafinalid);

drop index pfi_estatfirma_usrentid_fk_i;
create index pfi_estatfirma_usrestats_i on pfi_estatdefirma (usuarientitatid, tipusestatdefirmainicialid, tipusestatdefirmafinalid);
