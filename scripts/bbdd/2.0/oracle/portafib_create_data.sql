
WHENEVER SQLERROR EXIT ROLLBACK;

-- TIPUS DE DOCUMENT COMU
INSERT INTO pfi_traduccio(traduccioid) VALUES (1);
INSERT INTO pfi_traduccio(traduccioid) VALUES (2);
INSERT INTO pfi_traduccio(traduccioid) VALUES (3);
INSERT INTO pfi_traduccio(traduccioid) VALUES (4);
INSERT INTO pfi_traduccio(traduccioid) VALUES (5);
INSERT INTO pfi_traduccio(traduccioid) VALUES (6);
INSERT INTO pfi_traduccio(traduccioid) VALUES (7);
INSERT INTO pfi_traduccio(traduccioid) VALUES (8);
INSERT INTO pfi_traduccio(traduccioid) VALUES (9);
INSERT INTO pfi_traduccio(traduccioid) VALUES (10);
INSERT INTO pfi_traduccio(traduccioid) VALUES (11);
INSERT INTO pfi_traduccio(traduccioid) VALUES (12);
INSERT INTO pfi_traduccio(traduccioid) VALUES (13);
INSERT INTO pfi_traduccio(traduccioid) VALUES (14);
INSERT INTO pfi_traduccio(traduccioid) VALUES (15);
INSERT INTO pfi_traduccio(traduccioid) VALUES (16);
INSERT INTO pfi_traduccio(traduccioid) VALUES (17);
INSERT INTO pfi_traduccio(traduccioid) VALUES (18);
INSERT INTO pfi_traduccio(traduccioid) VALUES (19);
INSERT INTO pfi_traduccio(traduccioid) VALUES (20);
INSERT INTO pfi_traduccio(traduccioid) VALUES (99);

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (1, 'ca', 'Resoluci�');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (1, 'es', 'Resoluci�n');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (2, 'ca', 'Acord');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (2, 'es', 'Acuerdo');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (3, 'ca', 'Contracte');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (3, 'es', 'Contrato');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (4, 'ca', 'Conveni');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (4, 'es', 'Convenio');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (5, 'ca', 'Declaraci�');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (5, 'es', 'Declaraci�n');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (6, 'ca', 'Comunicaci�');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (6, 'es', 'Comunicaci�n');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (7, 'ca', 'Notificaci�');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (7, 'es', 'Notificaci�n');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (8, 'ca', 'Publicaci�');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (8, 'es', 'Publicaci�n');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (9, 'ca', 'Justificant de recepci�');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (9, 'es', 'Justificante de recepci�n');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (10, 'ca', 'Acta');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (10, 'es', 'Acta');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (11, 'ca', 'Certificat');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (11, 'es', 'Certificado');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (12, 'ca', 'Dilig�ncia');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (12, 'es', 'Diligencia');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (13, 'ca', 'Informe');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (13, 'es', 'Informe');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (14, 'ca', 'Sol.licitud');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (14, 'es', 'Solicitud');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (15, 'ca', 'Den�ncia');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (15, 'es', 'Denuncia');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (16, 'ca', 'Al.legaci�');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (16, 'es', 'Alegaci�n');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (17, 'ca', 'Recurs');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (17, 'es', 'Recurso');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (18, 'ca', 'Comunicaci� al ciutad�');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (18, 'es', 'Comunicaci�n al ciudadano');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (19, 'ca', 'Factura');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (19, 'es', 'Factura');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (20, 'ca', 'Altres confiscats');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (20, 'es', 'Otros confiscados');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (99, 'ca', 'Altres tipus de documents');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (99, 'es', 'Otros tipos de documentos');

INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (1, 1, 'Document de decisi� de tipus Resoluci�', 1, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (2, 2, 'Document de decisi� de tipus Acord', 2, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (3, 3, 'Document de decisi� de tipus Contracte', 3, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (4, 4, 'Document de decisi� de tipus Conveni', 4, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (5, 5, 'Document de decisi� de tipus Declaraci�', 5, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (6, 6, 'Document de transmissi� de tipus Comunicaci�', 6, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (7, 7, 'Document de transmissi� de tipus Notificaci�', 7, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (8, 8, 'Document de transmissi� de tipus Publicaci�', 8, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (9, 9, 'Document de transmissi� de tipus Justificant de recepci�', 9, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (10, 10, 'Document de const�ncia de tipus Acta', 10, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (11, 11, 'Document de const�ncia de tipus Certificat', 11, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (12, 12, 'Document de const�ncia de tipus Dilig�ncia', 12, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (13, 13, 'Document de judici de tipus Informe', 13, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (14, 14, 'Document de ciutad� de tipus Sol.licitud', 14, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (15, 15, 'Document de ciutad� de tipus Den�ncia', 15, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (16, 16, 'Document de ciutad� de tipus Al.legaci�', 16, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (17, 17, 'Document de ciutad� de tipus Recurs', 17, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (18, 18, 'Document de ciutad� de tipus Comunicaci� al ciudad�', 18, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (19, 19, 'Document de ciutad� de tipus Factura', 19, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (20, 20, 'Document de ciutad� de tipus Altres confiscats', 20, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, tipusdocumentbaseid, descripcio, nom, usuariaplicacioid) VALUES (99, 99, 'Altres tipus de documents', 99, NULL);



-- ROLES

INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_ADMIN', 'Administrador PortaFIB', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_DEST', 'Destinatari', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_DELE', 'Delegat', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_COLA', 'Col�laborador', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_ADEN', 'Administrador d''Entitat', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_SOLI', 'Sol�licitant', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_REVI', 'Revisor de Firmes', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('PFI_ADMIN', 'Rol Administrador per Usuaris Aplicaci�', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('PFI_USER', 'Rol B�sic per Usuaris Aplicaci�', NULL);


-- TIPUS NOTIFICACIO

INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (0, NULL, 'notificacioavis.peticio_en_proces', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (10, NULL, 'notificacioavis.requerit_per_validar', 1);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (15, NULL, 'notificacioavis.descartat_per_validar', 1);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (20, NULL, 'notificacioavis.requerit_per_firmar', 1);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (25, NULL, 'notificacioavis.descartat_per_firmar', 1);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (30, NULL, 'notificacioavis.validat', 1);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (40, NULL, 'notificacioavis.invalidat', 1);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (50, NULL, 'notificacioavis.firma_parcial', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (60, NULL, 'notificacioavis.peticio_firmada', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (70, NULL, 'notificacioavis.peticio_rebutjada', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (80, NULL, 'notificacioavis.peticio_pausada', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (90, NULL, 'notificacioavis.requerit_per_revisar', 1);

-- IDIOMES

INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('ca', 'Catal�', 1, 1);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('es', 'Castellano', 1, 2);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('en', 'English', 0, 3);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('de', 'Deutsch', 0, 5);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('en_US', 'English USA', 0, 7);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('fr', 'Fran�ais', 0, 4);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('it', 'Italiano', 0, 6);


-- CODIS DE BARRES

INSERT INTO pfi_codibarres (codibarresid, nom, descripcio) VALUES ('org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin', 'Pdf417', NULL);
INSERT INTO pfi_codibarres (codibarresid, nom, descripcio) VALUES ('org.fundaciobit.plugins.barcode.qrcode.QrCodePlugin', 'QrCode', NULL);
INSERT INTO pfi_codibarres (codibarresid, nom, descripcio) VALUES ('org.fundaciobit.plugins.barcode.barcode128.BarCode128Plugin', 'BarCode128', NULL);




-- ===================================
-- 2015/11/11 Plantilla Plugin de Firma (modificat 2016/02/22)
-- ===================================

INSERT INTO pfi_traduccio(traduccioid) VALUES (101);
INSERT INTO pfi_traduccio(traduccioid) VALUES (111);
INSERT INTO pfi_traduccio(traduccioid) VALUES (102);
INSERT INTO pfi_traduccio(traduccioid) VALUES (122);

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (101, 'ca', 'Plugin de Firma de MiniApplet en Servidor');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (101, 'es', 'Plugin de Firma de MiniApplet en Servidor');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (111, 'ca', 'Plugin de Firma de MiniApplet en Servidor');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (111, 'es', 'Plugin de Firma de MiniApplet en Servidor');

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (102, 'ca', 'Plugin de Firma de MiniApplet (Applet/JavaWebStart)');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (102, 'es', 'Plugin de Firma de MiniApplet (Applet/JavaWebStart)');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (122, 'ca', 'Plugin de Firma de MiniApplet (Applet/JavaWebStart)');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (122, 'es', 'Plugin de Firma de MiniApplet (Applet/JavaWebStart)');

INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus, politicadeus, 
            politicamostrarpropietats, codi)
            VALUES (1, 101, 111, 'org.fundaciobit.plugins.signatureweb.miniappletinserver.MiniAppletInServerSignatureWebPlugin', 'es.caib.portafib.plugins.signatureweb.miniappletinserver.base_dir=D:/dades/dades/CarpetesPersonals/Programacio/portafib-files/postgresql_1.1/MINIAPPLETINSERVER', NULL, NULL, 0, 0, 0, 2, '1');
INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus, politicadeus, 
            politicamostrarpropietats, codi)
            VALUES (2, 102, 122, 'org.fundaciobit.plugins.signatureweb.miniappletinclient.MiniAppletInClientSignatureWebPlugin', NULL, NULL, NULL, 1, 0, 0, 2, '2');
            
-- =======================================
-- 2015/11/25 Plantilla Plugin de Segellat de Temps
-- =======================================

INSERT INTO pfi_traduccio(traduccioid) VALUES (104);
INSERT INTO pfi_traduccio(traduccioid) VALUES (144);
INSERT INTO pfi_traduccio(traduccioid) VALUES (105);
INSERT INTO pfi_traduccio(traduccioid) VALUES (155);


INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (104, 'ca', 'Plantilla Plugin de Segell de Temps de CatCert');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (104, 'es', 'Plantilla Plugin de Sellado de Tiempo de CatCert');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (144, 'ca', 'Ag�ncia Catalana de Certificaci�');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (144, 'es', 'Agencia Catalana de Certificaci�n');


INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (105, 'ca', 'Plantilla Plugin de Segell de Temps de @firma (TS@)');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (105, 'es', 'Plantilla Plugin de Sellado de Tiempo de @firma (TS@)');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (155, 'ca', 'Ministeri d''Hisenda i Administracions P�bliques');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (155, 'es', 'Ministerio de Hacienda y Administraciones P�blicas');

INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus, politicadeus, 
            politicamostrarpropietats, codi) VALUES (4, 104, 144, 'org.fundaciobit.plugins.timestamp.catcertrfc.CatCertRfcTimeStampPlugin', 'es.caib.portafib.plugins.timestamp.catcertrfc.url_rfc=http://psis.catcert.net/psis/catcert/tsp
es.caib.portafib.plugins.timestamp.catcertrfc.oid_rfc3161=0.4.0.2023.1.1
es.caib.portafib.plugins.timestamp.catcertrfc.hashalgorithm=SHA-512', NULL, NULL, 1, 1, 0, 2, '4');


INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus, politicadeus, 
            politicamostrarpropietats, codi) VALUES (5, 105, 155, 'org.fundaciobit.plugins.timestamp.afirmarfc.AfirmaRFCTimeStampPlugin', '#################################################
#Configuraci�n para el cliente TSA Java s�lo RFC#
#################################################
#Identificador de la aplicaci�n cliente
es.caib.portafib.plugins.timestamp.afirmarfc.applicationid=gobbal.fbit.portafib

#OID politica de timestamping de la TSA del MINHAP
es.caib.portafib.plugins.timestamp.afirmarfc.oid_rfc3161=2.16.724.1.3.1.1.4.2.1.2

es.caib.portafib.plugins.timestamp.afirmarfc.hashalgorithm=SHA-512


#####################################################################
#Configuracion autenticacion al servicio RFC3161+HTTPS (puerto 8443)#
#####################################################################

#URL para la conexi�n al servicio RFC3161 + HTTPS (puerto 8443)
es.caib.portafib.plugins.timestamp.afirmarfc.url_rfc=https://des-tsafirma.redsara.es:8443/tsamap/TspHttpServer

#Ruta y clave del certificado para la autenticaci�n del servicio RFC3161 + HTTPS
#Este certificado debe ser el certificado de autenticaci�n HTTPS cliente dado de alta para su aplicaci�n para el servicio RFC3161 + HTTPS (partes p�blica y privada)
es.caib.portafib.plugins.timestamp.afirmarfc.auth.cert.p12.path=D:/dades/dades/CarpetesPersonals/Programacio/portafib-files/afirma/nou/keystore.p12
es.caib.portafib.plugins.timestamp.afirmarfc.auth.cert.p12.password=x7E7f9vU8QH8


#####################################################################
# Certificados de Servidor Aceptados RFC3161+HTTPS  (Opcional)      #
#####################################################################

#Configuracion general de confianza para los servicios por HTTPS: RFC3161+HTTPS (puerto 8443)
#Este certificado debe ser el certificado SSL de servidor la TSA (parte publica)
es.caib.portafib.plugins.timestamp.afirmarfc.server.trustkeystore.path=D:/dades/dades/CarpetesPersonals/Programacio/pluginsib-1.0/plugins-timestamp/afirmarfc/trustkeystore/truststore.jks
es.caib.portafib.plugins.timestamp.afirmarfc.server.trustkeystore.password=123456789', NULL, NULL, 1, 1, 0, 2, '5');



-- ========================================
-- 2015/12/01 Plugin de Cust�dia Documental
-- ========================================

INSERT INTO pfi_traduccio(traduccioid) VALUES (106);
INSERT INTO pfi_traduccio(traduccioid) VALUES (166);
INSERT INTO pfi_traduccio(traduccioid) VALUES (107);
INSERT INTO pfi_traduccio(traduccioid) VALUES (177);


INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (106, 'ca', 'Plugin de Cust�dia de FileSystem');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (106, 'es', 'Plugin de Custodia de FileSystem');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (166, 'ca', 'Plugin de Cust�dia de FileSystem');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (166, 'es', 'Plugin de Custodia de FileSystem');


INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (107, 'ca', 'Plugin de Cust�dia per Alfresco');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (107, 'es', 'Plugin de Custodia para Alfresco');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (177, 'ca', 'Plugin de Cust�dia per Alfresco');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (177, 'es', 'Plugin de Custodia para Alfresco');


INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus, politicadeus, 
            politicamostrarpropietats, codi) VALUES (6, 106, 166, 'org.fundaciobit.plugins.documentcustody.filesystem.FileSystemDocumentCustodyPlugin', 'es.caib.portafib.plugins.documentcustody.filesystem.basedir=D:\\dades\\dades\\CarpetesPersonals\\Programacio\\portafib-1.1-jboss-5.1.0.GA\\server\\default\\deployportafib\\custodia.war
es.caib.portafib.plugins.documentcustody.filesystem.prefix=CUST_
# {0} = custodyID | {1} = URL.Encoded(custodyID)  | {2} = HASH
#es.caib.portafib.plugins.documentcustody.filesystem.baseurl=http://localhost:8080/custodia/index.jsp?custodyID={1}
es.caib.portafib.plugins.documentcustody.filesystem.baseurl=http://localhost:8080/custodia/index.jsp?hash={2}

es.caib.portafib.plugins.documentcustody.filesystem.hash.password=portafib

#  MD2, MD5, SHA,SHA-256,SHA-384,SHA-512
es.caib.portafib.plugins.documentcustody.filesystem.hash.algorithm=MD5', NULL, NULL, 1, 2, 0, 2, '6');

INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus, politicadeus, 
            politicamostrarpropietats, codi) VALUES (7, 107, 177, 'org.fundaciobit.plugins.documentcustody.alfresco.base.AlfrescoBaseDocumentCustodyPlugin', '# WS or ATOM
es.caib.portafib.plugins.documentcustody.alfresco.access.method=ATOM
 
# Depends of Method and Alfresco version (alfresco 5)
es.caib.portafib.plugins.documentcustody.alfresco.url=http://localhost:9080/alfresco/api/-default-/public/cmis/versions/1.0/atom

es.caib.portafib.plugins.documentcustody.alfresco.access.user=anadal
es.caib.portafib.plugins.documentcustody.alfresco.access.pass=anadal
      
es.caib.portafib.plugins.documentcustody.alfresco.basepath=/test
 
# Only for WS
# es.caib.portafib.plugins.documentcustody.alfresco.repository=b886bad2-998d-4674-a120-1fcc2f1f533c

# Only for ATOM: Elegir una de les dues
es.caib.portafib.plugins.documentcustody.alfresco.site=ODES
#es.caib.portafib.plugins.documentcustody.alfresco.fullsitepath=/Sites/ODES/documentLibrary', NULL, NULL, 0, 2, 0, 2, '7');


-- ========================================
-- 2016/01/12 Propietats Globals
-- ========================================

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (1, 'es.caib.portafib.editableuser', 'false', 'Si est� a true permet als usuaris editar l''email  dels usuari-persona i usuaris-entitats, aix� com el logo dels usuaris-entitat. En cas contrari, �nicament �s l''administrador d''entitat que pot fer canvis en aquest camps');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (2, 'es.caib.portafib.numberoferrorsinnotificationtosendmail', NULL, 'Opcional. A partir de quants d''errors en una notificaci� callback s''enviar� un correu al responsable de l''usuari aplicaci�. Per defecte mai s''envia un correu');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (3, 'es.caib.portafib.numberoferrorstopausenotification', NULL, 'Opcional. A partir de quants d''errors en una notificaci� callback aquesta autom�ticament es pausar�. Per defecte es reintenten indefinidament.');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (4, 'es.caib.portafib.notificationtimelapse', NULL, 'Opcional. Valor per defecte 60000ms (1 minut). Ha de ser major de 15000. Temps m�nim que s''espera abans de reintentar una notificaci� fallida en ms.');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (5, 'es.caib.portafib.signaturemodule.absoluteurl', NULL, 'Opcional. Serveix per Plugins de Firma que han d''accedir externament al Servidor de PortaFIB. Si no es defineix llavors obt� la URL absoluta de la petici�.(Necessari quan el Apache-Proxy no t� activat "ProxyPreserveHost On").');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (6, 'es.caib.portafib.maxuploadsizeinbytes', NULL, 'Tamany m�xim de pujada de fitxers en bytes. No definit significa sense l�mit ');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (7, 'es.caib.portafib.maxfitxeradaptatsizeinbytes', NULL, 'Tamany m�xim del fitxer PDF una vegada se li han afegit els annexes i taula de firmes. No definit significa sense l�mit');     

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (8, 'es.caib.portafib.url', 'http://localhost:8080/portafib', 'URL p�blica d''acc�s a l''aplicaci� de PortaFIB. Es requereix fonamentalment per l''inclusi� de URLs cap a PortaFIB en l''enviament de correus');
 
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (9, 'es.caib.portafib.email.from', 'portafib@portafib.org', '�s l''adre�a d''email des d''on s''enviaran les notificacions per correu als usuaris');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (10, 'es.caib.portafib.emailsgroupedsendercronexpression', NULL, 'Opcional. Expressi� cron que indica cada quan s''ha d''executar l''enviador de correus quan s''han definit enviament d''avisos agrupats. Per defecte s''executa cada dia a les 6:00 (0 0 6 1/1 * ? *).');
      
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (11, 'es.caib.portafib.automaticredirect', 'false', 'Si val true llavors redirecciona segons el contexte:(a)Si entra amb http dins portafibs llavors redirecciona a portafib (b)Si entra amb https dins portafib i existeix portafib/s llavors redirecciona a portafib/s. Si val false llavors no fa res.');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (12, 'es.caib.portafib.defaultentity', NULL, 'Si val null incica que l''alta de persones i usuaris l''ha de realitzar l''AdEn. En cas contrari s''utilitzar� aquest valor com a entitat on donar d''alta automaticament persona i usuari. En entorns CAIB aquesta propietat es ignorada');
  
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (13, 'es.caib.portafib.defaultrolesincreation', NULL, 'Indica els roles virtuals a asssignar per defecte a l''usuari-entitat quan aquest es crea autom�ticament. Es tracta d''una llista de roles separats per comes.Els valors possibles s�n: ROLE_SOLI,ROLE_DEST,ROLE_DELE i ROLE_COLA. En entorns CAIB �s ignorada');
  
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (14, 'es.caib.portafib.entitatidforagentssql', NULL, 'Opcional excepte en entorns de la CAIB. Entitat sobre la qual s''aplicaran les accions del [Agents Seycon]. Veure punt [Gesti� de Rols a traves de triggers Oracle] del manual d''instal�laci� per m�s informaci�.');
  
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (15, 'es.caib.portafib.passwordforagentssql', NULL, 'Opcional excepte en entorns de la CAIB. Contrasenya (o clau de pas) per comprovar que les peticions http realment provenen d''un trigger de BBDD. Veure punt [Gesti� de Rols a traves de triggers Oracle] del manual d''instal�laci� per m�s informaci�.');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (16, 'es.caib.portafib.logouturl', NULL, 'Opcional. Afegeix una nova opci� de men� davall de �Configuraci� del men� de la cap�alera (superior dreta) que indica una URL que servir� per poder abandonar PortaFIB. Per aplicar canvis requereix reiniciar servidor.');

-- Actualitzam la Sequencia per a que no sobreescriqui valors posats a pinyo fix
-- IMPORTANT !!!!  Ha d'estar al final de l'script SQL
ALTER SEQUENCE pfi_portafib_seq INCREMENT BY 1000;
SELECT pfi_portafib_seq.NEXTVAL FROM dual;
ALTER SEQUENCE pfi_portafib_seq INCREMENT BY 1;

