


--  ----------------------------------------------
--   Nous camps a Firma i a Peticio de Firma #281
--  ----------------------------------------------

ALTER TABLE pfi_firma
  ADD checkadministrationidofsigner NUMBER(1,0);
ALTER TABLE pfi_firma
  ADD checkdocumentmodifications NUMBER(1,0);
ALTER TABLE pfi_firma
  ADD checkvalidationsignature NUMBER(1,0);
ALTER TABLE pfi_firma
  ADD perfildefirma varchar2(50);
  
  
  
ALTER TABLE pfi_peticiodefirma
  ADD origenpeticiodefirma NUMBER(10,0) DEFAULT 0 NOT NULL;

ALTER TABLE pfi_peticiodefirma
  ADD configuraciodefirmaid NUMBER(19,0);
ALTER TABLE pfi_peticiodefirma
  ADD CONSTRAINT pfi_petifirma_confapp_fk FOREIGN KEY (configuraciodefirmaid) REFERENCES pfi_usuariaplicacioconfig (usuariaplicacioconfigid);
create index pfi_petifirma_conffirma_fk_i on pfi_peticiodefirma (configuraciodefirmaid);


--  ---------------------------------
--   Nous camps de CustodyInfo #280
--  ---------------------------------

ALTER TABLE pfi_custodiainfo
  ADD csv varchar2(500);
ALTER TABLE pfi_custodiainfo
  ADD csvgenerationdefinition varchar2(500);
ALTER TABLE pfi_custodiainfo
  ADD csvvalidationweb varchar2(500);
ALTER TABLE pfi_custodiainfo
  ADD originalfiledirecturl varchar2(500);
ALTER TABLE pfi_custodiainfo
  ADD printablefiledirecturl varchar2(500);
ALTER TABLE pfi_custodiainfo
  ADD enifiledirecturl varchar2(500);
ALTER TABLE pfi_custodiainfo
  ADD expedientid varchar2(250);
ALTER TABLE pfi_custodiainfo
  ADD documentid varchar2(250);
COMMENT ON COLUMN pfi_custodiainfo.expedientid IS 'Futura compatibilitat amb Plugin Arxiu';
COMMENT ON COLUMN pfi_custodiainfo.documentid IS 'Futura compatibilitat amb Plugin Arxiu';

--  -----------------------------------------------------------------------
--   Eliminar Certificat de Configuració de Firma per UsuariAplicacio #279
--  -----------------------------------------------------------------------

ALTER TABLE pfi_usuariaplicacioconfig DROP COLUMN logincertificateid;