-- ===================================
-- 2015/11/11 Plugin de Firma
-- ===================================

INSERT INTO pfi_traduccio(traduccioid) VALUES (101);
INSERT INTO pfi_traduccio(traduccioid) VALUES (111);
INSERT INTO pfi_traduccio(traduccioid) VALUES (102);
INSERT INTO pfi_traduccio(traduccioid) VALUES (122);
INSERT INTO pfi_traduccio(traduccioid) VALUES (103);
INSERT INTO pfi_traduccio(traduccioid) VALUES (133);


INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (101, 'ca', 'Plugin de Firma de MiniApplet en Servidor');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (101, 'es', 'Plugin de Firma de MiniApplet en Servidor');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (111, 'ca', 'Plugin de Firma de MiniApplet en Servidor');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (111, 'es', 'Plugin de Firma de MiniApplet en Servidor');

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (102, 'ca', 'Plugin de Firma de MiniApplet com Applet');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (102, 'es', 'Plugin de Firma de MiniApplet como Applet');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (122, 'ca', 'Plugin de Firma de MiniApplet com Applet');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (122, 'es', 'Plugin de Firma de MiniApplet como Applet');

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (103, 'ca', 'Plugin de Firma de MiniApplet com JavaWebStart');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (103, 'es', 'Plugin de Firma de MiniApplet como JavaWebStart');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (133, 'ca', 'Plugin de Firma de MiniApplet com JavaWebStart');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (133, 'es', 'Plugin de Firma de MiniApplet como JavaWebStart');


INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus)
            VALUES (1, 101, 111, 'org.fundaciobit.plugins.signatureweb.miniappletinserver.MiniAppletInServerSignatureWebPlugin', 'es.caib.portafib.plugins.signatureweb.miniappletinserver.base_dir=D:/dades/dades/CarpetesPersonals/Programacio/portafib-files/postgresql_1.1/MINIAPPLETINSERVER', NULL, NULL, 0, 0);
INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus)
            VALUES (2, 102, 122, 'org.fundaciobit.plugins.signatureweb.miniappletinclient.MiniAppletAsAppletSignatureWebPlugin', NULL, NULL, NULL, 1, 0);
INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus)
            VALUES (3, 103, 133, 'org.fundaciobit.plugins.signatureweb.miniappletinclient.MiniAppletAsJavaWebStartSignatureWebPlugin', NULL, NULL, NULL, 1, 0);

            
-- =======================================
-- 2015/11/25 Plugin de Segellat de Temps
-- =======================================

INSERT INTO pfi_traduccio(traduccioid) VALUES (104);
INSERT INTO pfi_traduccio(traduccioid) VALUES (144);
INSERT INTO pfi_traduccio(traduccioid) VALUES (105);
INSERT INTO pfi_traduccio(traduccioid) VALUES (155);


INSERT INTO pfi_traducciomap VALUES (104, 'ca', 'Plantilla Plugin de Segell de Temps de CatCert');
INSERT INTO pfi_traducciomap VALUES (104, 'es', 'Plantilla Plugin de Sellado de Tiempo de CatCert');
INSERT INTO pfi_traducciomap VALUES (144, 'ca', 'Agència Catalana de Certificació');
INSERT INTO pfi_traducciomap VALUES (144, 'es', 'Agencia Catalana de Certificación');


INSERT INTO pfi_traducciomap VALUES (105, 'ca', 'Plantilla Plugin de Segell de Temps de @firma (TS@)');
INSERT INTO pfi_traducciomap VALUES (105, 'es', 'Plantilla Plugin de Sellado de Tiempo de @firma (TS@)');
INSERT INTO pfi_traducciomap VALUES (155, 'ca', 'Ministeri d''Hisenda i Administracions Públiques');
INSERT INTO pfi_traducciomap VALUES (155, 'es', 'Ministerio de Hacienda y Administraciones Públicas');

INSERT INTO pfi_plugin VALUES (4, 104, 144, 'org.fundaciobit.plugins.timestamp.catcertrfc.CatCertRfcTimeStampPlugin', 'es.caib.portafib.plugins.timestamp.catcertrfc.url_rfc=http://psis.catcert.net/psis/catcert/tsp
es.caib.portafib.plugins.timestamp.catcertrfc.oid_rfc3161=0.4.0.2023.1.1
es.caib.portafib.plugins.timestamp.catcertrfc.hashalgorithm=SHA-512', NULL, NULL, true, 1);


INSERT INTO pfi_plugin VALUES (5, 105, 155, 'org.fundaciobit.plugins.timestamp.afirmarfc.AfirmaRFCTimeStampPlugin', '#################################################
#Configuración para el cliente TSA Java sólo RFC#
#################################################
#Identificador de la aplicación cliente
es.caib.portafib.plugins.timestamp.afirmarfc.applicationid=gobbal.fbit.portafib

#OID politica de timestamping de la TSA del MINHAP
es.caib.portafib.plugins.timestamp.afirmarfc.oid_rfc3161=2.16.724.1.3.1.1.4.2.1.2

es.caib.portafib.plugins.timestamp.afirmarfc.hashalgorithm=SHA-512


#####################################################################
#Configuracion autenticacion al servicio RFC3161+HTTPS (puerto 8443)#
#####################################################################

#URL para la conexión al servicio RFC3161 + HTTPS (puerto 8443)
es.caib.portafib.plugins.timestamp.afirmarfc.url_rfc=https://des-tsafirma.redsara.es:8443/tsamap/TspHttpServer

#Ruta y clave del certificado para la autenticación del servicio RFC3161 + HTTPS
#Este certificado debe ser el certificado de autenticación HTTPS cliente dado de alta para su aplicación para el servicio RFC3161 + HTTPS (partes píblica y privada)
es.caib.portafib.plugins.timestamp.afirmarfc.auth.cert.p12.path=D:/dades/dades/CarpetesPersonals/Programacio/portafib-files/afirma/nou/keystore.p12
es.caib.portafib.plugins.timestamp.afirmarfc.auth.cert.p12.password=x7E7f9vU8QH8


#####################################################################
# Certificados de Servidor Aceptados RFC3161+HTTPS  (Opcional)      #
#####################################################################

#Configuracion general de confianza para los servicios por HTTPS: RFC3161+HTTPS (puerto 8443)
#Este certificado debe ser el certificado SSL de servidor la TSA (parte publica)
es.caib.portafib.plugins.timestamp.afirmarfc.server.trustkeystore.path=D:/dades/dades/CarpetesPersonals/Programacio/pluginsib-1.0/plugins-timestamp/afirmarfc/trustkeystore/truststore.jks
es.caib.portafib.plugins.timestamp.afirmarfc.server.trustkeystore.password=123456789', NULL, NULL, true, 1);







