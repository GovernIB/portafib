


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


--  ---------------------------------
--   Nous camps de CustodyInfo #280
--  ---------------------------------

ALTER TABLE pfi_custodiainfo
  ADD COLUMN csv varchar2(500);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN csvgenerationdefinition varchar2(500);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN csvvalidationweb varchar2(500);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN originalfiledirecturl varchar2(500);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN printablefiledirecturl varchar2(500);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN enifiledirecturl varchar2(500);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN expedientid varchar2(250);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN documentid varchar2(250);
COMMENT ON COLUMN pfi_custodiainfo.expedientid IS 'Futura compatibilitat amb Plugin Arxiu';
COMMENT ON COLUMN pfi_custodiainfo.documentid IS 'Futura compatibilitat amb Plugin Arxiu';

--  -----------------------------------------------------------------------
--   Eliminar Certificat de Configuració de Firma per UsuariAplicacio #279
--  -----------------------------------------------------------------------

ALTER TABLE pfi_usuariaplicacioconfig DROP COLUMN logincertificateid;