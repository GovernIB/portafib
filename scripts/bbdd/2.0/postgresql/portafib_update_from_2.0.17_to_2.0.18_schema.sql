
-- ===============================================================
-- 2021-04-13 Permetre als usuaris aplicació insertar usuaris del sistema d'autenticació que no estan dins PortaFIB #569
-- ===============================================================

ALTER TABLE PFI_USUARIAPLICACIO ADD CREARUSUARIS bool DEFAULT false NOT NULL;
