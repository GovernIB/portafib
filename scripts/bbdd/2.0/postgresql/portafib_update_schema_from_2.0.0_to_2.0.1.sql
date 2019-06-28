
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


--  ---------------------------------
--   Nous camps de CustodyInfo #280
--  ---------------------------------


ALTER TABLE pfi_custodiainfo
  ADD COLUMN csv character varying(500);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN csvgenerationdefinition character varying(500);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN csvvalidationweb character varying(500);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN originalfiledirecturl character varying(500);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN printablefiledirecturl character varying(500);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN enifiledirecturl character varying(500);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN expedientid character varying(250);
ALTER TABLE pfi_custodiainfo
  ADD COLUMN documentid character varying(250);
COMMENT ON COLUMN pfi_custodiainfo.expedientid IS 'Futura compatibilitat amb Plugin Arxiu';
COMMENT ON COLUMN pfi_custodiainfo.documentid IS 'Futura compatibilitat amb Plugin Arxiu';



--  -----------------------------------------------------------------------
--   Eliminar Certificat de Configuració de Firma per UsuariAplicacio #279
--  -----------------------------------------------------------------------

ALTER TABLE pfi_usuariaplicacioconfig DROP COLUMN logincertificateid;


  