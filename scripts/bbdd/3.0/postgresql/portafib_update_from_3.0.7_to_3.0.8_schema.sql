
--######################################################################
--##### 17/06/2025 Afegir informació del PLugin que ha realitzat la Firmes en totes les Apis #1043
--######################################################################


ALTER TABLE pfi_firma
  ADD COLUMN signaturepluginid bigint;

COMMENT ON COLUMN pfi_firma.signaturepluginid IS 'No feim un ForeignKey a la taula de Plugins per Evitar problems d''esborrat de plugins.';

