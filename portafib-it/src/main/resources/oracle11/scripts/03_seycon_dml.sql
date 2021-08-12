
-- Executar dins l'schema de SEYCON
ALTER SESSION SET CURRENT_SCHEMA = SEYCON;

INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('admin','admin',NULL,'admin','12345678Z');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('senserol','senserol',NULL,'Sense Rol','11111111A');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('dest2','dest2',NULL,'Destinatari2','00000002T');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('soli1','soli1',NULL,'Solicitant1','00000001S');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('dest1','dest1',NULL,'Destinatari1','00000001T');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('dele2','dele2',NULL,'Delegat 2','00000002D');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('dele1','dele1',NULL,'Delegat 1','00000001D');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('cola1','cola1',NULL,'Colaborador 1','00000001C');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('aden1','aden1',NULL,'Admin Entitat de FundacioBit','00000001E');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('cola2','cola2',NULL,'Colaborador 2','00000002C');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('soli2','soli2',NULL,'Solicitant2','00000002S');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('revi1','revi1',NULL,'Revisor 1','00000003T');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('revi2','revi2',NULL,'Revisor 2','00000004G');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('revi3','revi3',NULL,'Revisor 3','00000005M');

INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('pruebas','pruebas',NULL,'Pruebas Eidas Certificado','99999999R');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('ciudadano','ciudadano',NULL,'Ciutadano Ficticio Activo','99999990S');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('taimi','taimi',NULL,'Taimi Virta Toivonen','X0000018H');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('persona','persona',NULL,'Persona de la Peça de Proves','00000000T');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('viafirmo','viafirmo',NULL,'Viafirmo Profesional Fortress','62800225J');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('siauser','siauser',NULL,'Siauser Ibsalut Ibsalut','11111111H');
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('siatres','siatres',NULL,'Siatres Ibsalut Ibsalut','55846601W');

INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('test','test',NULL,'Usuari de Test','11223344T');

INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('$app','app',NULL,NULL,NULL);
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('$appadmin','appadmin',NULL,NULL,NULL);
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('$appindra','appindra',NULL,NULL,NULL);
INSERT INTO SC_WL_USUARI (USU_CODI,USU_PASS,USU_DATCAD,USU_NOM,USU_NIF) VALUES ('$entitat','entitat',NULL,NULL,NULL);

INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('$app','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('$appadmin','PFI_ADMIN');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('$appindra','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('$entitat','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('aden1','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('admin','PFI_ADMIN');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('ciudadano','tothom');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('cola1','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('cola2','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('dele1','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('dele2','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('dest1','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('dest2','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('persona','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('pruebas','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('revi1','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('revi2','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('revi3','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('senserol','PFI_ADMIN');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('siatres','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('siauser','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('soli1','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('soli2','PFI_USER');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('taimi','tothom');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('test','tothom');
INSERT INTO SC_WL_USUGRU (UGR_CODUSU,UGR_CODGRU) VALUES ('viafirmo','PFI_USER');