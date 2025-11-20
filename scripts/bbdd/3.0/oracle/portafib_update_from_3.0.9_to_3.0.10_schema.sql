

--######################################################################
--##### 19/11/2025 Afergir Icona a PLugin de Firma Web #1106
--######################################################################

ALTER TABLE pfi_plugin ADD iconaid NUMBER(19);

ALTER TABLE pfi_plugin ADD CONSTRAINT pfi_plugin_fitxer_icona_fk 
    FOREIGN KEY (iconaid)
    REFERENCES pfi_fitxer (fitxerid)
    ON DELETE NO ACTION;

CREATE INDEX pfi_plugin_iconaid_fk_i ON pfi_plugin (iconaid);
