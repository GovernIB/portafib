


--  ----------------------------------------------
--   Nous camps a Firma i a Peticio de Firma #281
--  ----------------------------------------------

ALTER TABLE pfi_firma
  ADD COLUMN checkadministrationidofsigner NUMBER(1,0);
ALTER TABLE pfi_firma
  ADD COLUMN checkdocumentmodifications NUMBER(1,0);
ALTER TABLE pfi_firma
  ADD COLUMN checkvalidationsignature NUMBER(1,0);
ALTER TABLE pfi_firma
  ADD COLUMN perfildefirma varchar2(50);
  
  
  
ALTER TABLE pfi_peticiodefirma
  ADD COLUMN origenpeticiodefirma NUMBER(10,0) NOT NULL DEFAULT 0;

ALTER TABLE pfi_peticiodefirma
  ADD COLUMN configuraciodefirmaid NUMBER(19,0);
ALTER TABLE pfi_peticiodefirma
  ADD CONSTRAINT pfi_petifirma_confapp_fk FOREIGN KEY (configuraciodefirmaid) REFERENCES pfi_usuariaplicacioconfig (usuariaplicacioconfigid);
create index pfi_petifirma_conffirma_fk_i on pfi_peticiodefirma (configuraciodefirmaid);


  