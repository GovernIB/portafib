


--  ----------------------------------------------
--   Nous camps a Firma i a Peticio de Firma #281
--  ----------------------------------------------

ALTER TABLE pfi_firma
  ADD COLUMN checkadministrationidofsigner boolean;
ALTER TABLE pfi_firma
  ADD COLUMN checkdocumentmodifications boolean;
ALTER TABLE pfi_firma
  ADD COLUMN checkvalidationsignature boolean;
ALTER TABLE pfi_firma
  ADD COLUMN perfildefirma character varying(50);
  
  
  
ALTER TABLE pfi_peticiodefirma
  ADD COLUMN origenpeticiodefirma integer NOT NULL DEFAULT 0;

ALTER TABLE pfi_peticiodefirma
  ADD COLUMN configuraciodefirmaid bigint;
ALTER TABLE pfi_peticiodefirma
  ADD CONSTRAINT pfi_petifirma_confapp_fk FOREIGN KEY (configuraciodefirmaid) REFERENCES pfi_usuariaplicacioconfig (usuariaplicacioconfigid) ON UPDATE NO ACTION ON DELETE NO ACTION;
create index pfi_petifirma_conffirma_fk_i on pfi_peticiodefirma (configuraciodefirmaid);


  