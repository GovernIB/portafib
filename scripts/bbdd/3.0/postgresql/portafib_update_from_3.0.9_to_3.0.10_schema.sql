

--######################################################################
--##### 19/11/2025 Afergir Icona a PLugin de Firma Web #1106
--######################################################################

ALTER TABLE pfi_plugin ADD COLUMN iconaid bigint;
ALTER TABLE pfi_plugin ADD CONSTRAINT pfi_plugin_fitxer_icona_fk FOREIGN KEY (iconaid) REFERENCES pfi_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION;

create index pfi_plugin_iconaid_fk_i on pfi_plugin (iconaid);
