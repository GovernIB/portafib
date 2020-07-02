
-- ================================================
-- 2020-07-01 Simplificar opcions de custòdia #465
-- ================================================

update pfi_entitat set politicacustodia = 2 where politicacustodia in (3, 4);
update pfi_usuariaplicacio set politicacustodia = 4 where politicacustodia = 3;