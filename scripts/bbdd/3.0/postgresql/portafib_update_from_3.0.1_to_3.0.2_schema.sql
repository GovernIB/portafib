
--######################################################################
--##### 12/11/2024  Ampliar els modes de firma de PortaFIB (SIGN_MODE) #813 
--######################################################################

ALTER TABLE pfi_peticiodefirma ALTER COLUMN modedefirma DROP DEFAULT;
ALTER TABLE pfi_peticiodefirma ALTER COLUMN modedefirma TYPE numeric(1,0) USING modedefirma::int::numeric(1,0);
ALTER TABLE pfi_peticiodefirma ALTER COLUMN modedefirma TYPE integer;

ALTER TABLE pfi_usuariaplicacioconfig ALTER COLUMN modedefirma DROP DEFAULT;
ALTER TABLE pfi_usuariaplicacioconfig ALTER COLUMN modedefirma TYPE numeric(1,0) USING modedefirma::int::numeric(1,0);
ALTER TABLE pfi_usuariaplicacioconfig ALTER COLUMN modedefirma TYPE integer;
