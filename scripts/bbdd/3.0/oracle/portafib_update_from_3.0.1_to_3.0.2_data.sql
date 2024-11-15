
--######################################################################
--#####  12/11/2024  Ampliar els modes de firma de PortaFIB (SIGN_MODE) #813 
--######################################################################

-- Els XAdEs Detached (tipusfirma 1 i modefirma 1) les hem de convertir en XAdES Internally SIGN_MODE_INTERNALLY_DETACHED = 4;
UPDATE pfi_peticiodefirma SET  modedefirma=4 WHERE tipusfirmaid=1 AND modedefirma=1;

UPDATE pfi_usuariaplicacioconfig SET modedefirma=4 WHERE tipusfirmaid=1 AND modedefirma=1;