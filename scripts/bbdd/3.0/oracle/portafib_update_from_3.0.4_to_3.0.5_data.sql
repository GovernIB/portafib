
--######################################################################
--##### 04/04/2025  Revisar enviament de correus als Administradors d'Agents CAIB #1022 
--######################################################################

INSERT INTO pfi_tipusnotificacio(tipusnotificacioid, descripcio, nom, esavis)  VALUES (100, null, 'notificacioavis.incidencies_administrador', null);


--######################################################################
--##### 04/04/2025  Revisar el rendiment de la secció de gestió de peticions de firma #1020
--######################################################################

CREATE INDEX pfi_petifirma_datasolicitud_i ON pfi_peticiodefirma (datasolicitud);
