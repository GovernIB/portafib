

--######################################################################
--##### 13/04/2026 Provar Plugin de Validació de Firmes d'UtilitatsFirma dins PortaFIB #1154
--######################################################################

INSERT INTO pfi_traduccio VALUES (500);
INSERT INTO pfi_traduccio VALUES (501);


INSERT INTO pfi_traducciomap VALUES (500, 'ca', 'Plugin de Validació de Firmes d''UtilitatsFirma');
INSERT INTO pfi_traducciomap VALUES (500, 'es', 'Plugin de Validación de Firmas de UtilitatsFirma');
INSERT INTO pfi_traducciomap VALUES (501, 'ca', 'Plugin de Validació de Firmes d''UtilitatsFirma');
INSERT INTO pfi_traducciomap VALUES (501, 'es', 'Plugin de Validación de Firmas de UtilitatsFirma');

INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus, codi, ordre, politicadeus, 
            politicamostrarpropietats, iconaid) VALUES (nextval('pfi_plugin_seq'), 500, 501, 'org.fundaciobit.pluginsib.validatesignature.utilitatsfirma.UtilitatsFirmaValidateSignaturePlugin', 'es.caib.portafib.pluginsib.validatesignature.utilitatsfirma.host=https://dev.caib.es/utilitatsfirmaapi/interna
es.caib.portafib.pluginsib.validatesignature.utilitatsfirma.username=<<username>>
es.caib.portafib.pluginsib.validatesignature.utilitatsfirma.password=<<password>>
es.caib.portafib.pluginsib.validatesignature.utilitatsfirma.languageui=ca', NULL, NULL, 1, 4, 'VALIDA_UTILFIRMA', 1, 2, 2, NULL);




