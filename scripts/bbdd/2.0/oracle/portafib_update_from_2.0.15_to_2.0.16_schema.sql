

-- ===============================================================
-- 2021-02-10  Rendiment de la consulta de peticions pendents #535
-- ===============================================================

drop index pfi_estatfirma_estatinid_fk_i;
create index pfi_estatfirma_estats_i on pfi_estatdefirma (tipusestatdefirmainicialid, tipusestatdefirmafinalid);

drop index pfi_estatfirma_usrentid_fk_i;
create index pfi_estatfirma_usrestats_i on pfi_estatdefirma (usuarientitatid, tipusestatdefirmainicialid, tipusestatdefirmafinalid);

-- ===============================================================
-- 2021-02-23  Permetre que l'activació o no de la validació dins l'entitat sigui independent #545
-- ===============================================================

ALTER TABLE PFI_ENTITAT ADD VALIDARFIRMA NUMBER(1,0);
-- per defecte val true si tenim el plugin definit i false si no
UPDATE PFI_ENTITAT SET VALIDARFIRMA = nvl2(PLUGINVALIDAFIRMESID, 1, 0);
-- Una vegada inicialitzat, el fixam a not null
ALTER TABLE PFI_ENTITAT MODIFY VALIDARFIRMA NUMBER(1,0) NOT NULL;
