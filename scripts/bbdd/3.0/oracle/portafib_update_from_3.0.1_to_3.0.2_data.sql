
--######################################################################
--#####  12/11/2024  Ampliar els modes de firma de PortaFIB (SIGN_MODE) #813 
--######################################################################

-- Els XAdEs Detached (tipusfirma 1 i modefirma 1) les hem de convertir en XAdES Internally SIGN_MODE_INTERNALLY_DETACHED = 4;
UPDATE pfi_peticiodefirma SET  modedefirma=4 WHERE tipusfirmaid=1 AND modedefirma=1;

UPDATE pfi_usuariaplicacioconfig SET modedefirma=4 WHERE tipusfirmaid=1 AND modedefirma=1;



--######################################################################
--#####  22/11/2024  Activar els avisos per email per defecte a Portafib #914
--######################################################################

insert into pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid)
select pfi_propietatglobal_seq.nextval as propietaglobalid ,'es.caib.portafib.addnotificationstonewuser' as clau, 
        'Afegida a la versió 3.0.2. Valor per defecte false. Si val true, als nous usuaris se´ls donarà d´alta en les notificacions de tipus ´Requerit per firmar´. ´Requerit per revisar´ i ´Requerit per validar´' as descripcio,
        entitatid
        from pfi_entitat;