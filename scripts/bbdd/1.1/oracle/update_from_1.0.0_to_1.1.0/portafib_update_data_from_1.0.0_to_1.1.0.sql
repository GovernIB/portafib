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
es.caib.portafib.plugins.timestamp.catcertrfc.hashalgorithm=SHA-512', NULL, NULL, 1, 1);


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
es.caib.portafib.plugins.timestamp.afirmarfc.server.trustkeystore.password=123456789', NULL, NULL, 1, 1);


-- ========================================
-- 2015/12/01 Plugin de Custòdia Documental
-- ========================================


INSERT INTO pfi_traduccio(traduccioid) VALUES (106);
INSERT INTO pfi_traduccio(traduccioid) VALUES (166);
INSERT INTO pfi_traduccio(traduccioid) VALUES (107);
INSERT INTO pfi_traduccio(traduccioid) VALUES (177);


INSERT INTO pfi_traducciomap VALUES (106, 'ca', 'Plugin de Custòdia de FileSystem');
INSERT INTO pfi_traducciomap VALUES (106, 'es', 'Plugin de Custodia de FileSystem');
INSERT INTO pfi_traducciomap VALUES (166, 'ca', 'Plugin de Custòdia de FileSystem');
INSERT INTO pfi_traducciomap VALUES (166, 'es', 'Plugin de Custodia de FileSystem');


INSERT INTO pfi_traducciomap VALUES (107, 'ca', 'Plugin de Custòdia per Alfresco');
INSERT INTO pfi_traducciomap VALUES (107, 'es', 'Plugin de Custodia para Alfresco');
INSERT INTO pfi_traducciomap VALUES (177, 'ca', 'Plugin de Custòdia per Alfresco');
INSERT INTO pfi_traducciomap VALUES (177, 'es', 'Plugin de Custodia para Alfresco');


INSERT INTO pfi_plugin VALUES (6, 106, 166, 'org.fundaciobit.plugins.documentcustody.filesystem.FileSystemDocumentCustodyPlugin', 'es.caib.portafib.plugins.documentcustody.filesystem.basedir=D:\\dades\\dades\\CarpetesPersonals\\Programacio\\portafib-1.1-jboss-5.1.0.GA\\server\\default\\deployportafib\\custodia.war
es.caib.portafib.plugins.documentcustody.filesystem.prefix=CUST_
# {0} = custodyID | {1} = URL.Encoded(custodyID)  | {2} = HASH
#es.caib.portafib.plugins.documentcustody.filesystem.baseurl=http://localhost:8080/custodia/index.jsp?custodyID={1}
es.caib.portafib.plugins.documentcustody.filesystem.baseurl=http://localhost:8080/custodia/index.jsp?hash={2}

es.caib.portafib.plugins.documentcustody.filesystem.hash.password=portafib

#  MD2, MD5, SHA,SHA-256,SHA-384,SHA-512
es.caib.portafib.plugins.documentcustody.filesystem.hash.algorithm=MD5', NULL, NULL, 1, 2);
INSERT INTO pfi_plugin VALUES (7, 107, 177, 'org.fundaciobit.plugins.documentcustody.alfresco.base.AlfrescoBaseDocumentCustodyPlugin', '# WS or ATOM
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
#es.caib.portafib.plugins.documentcustody.alfresco.fullsitepath=/Sites/ODES/documentLibrary', NULL, NULL, 0, 2);


ALTER TABLE pfi_custodiainfo ADD COLUMN pluginid bigint NOT NULL DEFAULT 6;
create index pfi_custodiainfo_pluginid_fk_i on pfi_custodiainfo (pluginid);
ALTER TABLE pfi_custodiainfo ADD CONSTRAINT pfi_custodia_plugin_fk FOREIGN KEY (pluginid) REFERENCES pfi_plugin (pluginid);
ALTER TABLE pfi_custodiainfo DROP COLUMN custodiapluginclassid;





