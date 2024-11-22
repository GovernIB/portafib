
--######################################################################
--#####  12/11/2024  Ampliar els modes de firma de PortaFIB (SIGN_MODE) #813 
--######################################################################

-- Els XAdEs Detached (tipusfirma 1 i modefirma true) les hem de convertir en XAdES Internally SIGN_MODE_INTERNALLY_DETACHED = 4;
UPDATE pfi_peticiodefirma SET  modedefirma=4 WHERE tipusfirmaid=1 AND modedefirma=1;

UPDATE pfi_usuariaplicacioconfig SET  modedefirma=4 WHERE tipusfirmaid=1 AND modedefirma=1;



--######################################################################
--#####  22/11/2024  Activar els avisos per email per defecte a Portafib #914
--######################################################################

INSERT INTO pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid)
SELECT NEXTVAL('pfi_propietatglobal_seq') AS propietaglobalid ,'es.caib.portafib.addnotificationstonewuser' AS clau, 
        'Afegida a la versió 3.0.2. Valor per defecte false. Si val true, als nous usuaris se´ls donarà d´alta en les notificacions de tipus ´Requerit per firmar´. ´Requerit per revisar´ i ´Requerit per validar´' AS descripcio,
        entitatid
        FROM pfi_entitat;