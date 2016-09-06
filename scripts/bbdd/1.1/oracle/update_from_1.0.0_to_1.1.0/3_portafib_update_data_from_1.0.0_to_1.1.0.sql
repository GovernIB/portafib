-- ===================================
-- 2015/11/11 Plugin de Firma (modificat 2016/02/22)
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
            propertiesentitat, entitatid, actiu, tipus)
            VALUES (1, 101, 111, 'org.fundaciobit.plugins.signatureweb.miniappletinserver.MiniAppletInServerSignatureWebPlugin', 'es.caib.portafib.plugins.signatureweb.miniappletinserver.base_dir=D:/dades/dades/CarpetesPersonals/Programacio/portafib-files/postgresql_1.1/MINIAPPLETINSERVER', NULL, NULL, 0, 0);
INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus)
            VALUES (2, 102, 122, 'org.fundaciobit.plugins.signatureweb.miniappletinclient.MiniAppletInClientSignatureWebPlugin', NULL, NULL, NULL, 1, 0);


            
-- =======================================
-- 2015/11/25 Plugin de Segellat de Temps
-- =======================================

INSERT INTO pfi_traduccio(traduccioid) VALUES (104);
INSERT INTO pfi_traduccio(traduccioid) VALUES (144);
INSERT INTO pfi_traduccio(traduccioid) VALUES (105);
INSERT INTO pfi_traduccio(traduccioid) VALUES (155);


INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (104, 'ca', 'Plantilla Plugin de Segell de Temps de CatCert');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (104, 'es', 'Plantilla Plugin de Sellado de Tiempo de CatCert');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (144, 'ca', 'Agència Catalana de Certificació');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (144, 'es', 'Agencia Catalana de Certificación');


INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (105, 'ca', 'Plantilla Plugin de Segell de Temps de @firma (TS@)');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (105, 'es', 'Plantilla Plugin de Sellado de Tiempo de @firma (TS@)');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (155, 'ca', 'Ministeri d''Hisenda i Administracions Públiques');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (155, 'es', 'Ministerio de Hacienda y Administraciones Públicas');

INSERT INTO pfi_plugin (
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin,
            propertiesentitat, entitatid, actiu, tipus)  VALUES (4, 104, 144, 'org.fundaciobit.plugins.timestamp.catcertrfc.CatCertRfcTimeStampPlugin', 'es.caib.portafib.plugins.timestamp.catcertrfc.url_rfc=http://psis.catcert.net/psis/catcert/tsp
es.caib.portafib.plugins.timestamp.catcertrfc.oid_rfc3161=0.4.0.2023.1.1
es.caib.portafib.plugins.timestamp.catcertrfc.hashalgorithm=SHA-512', NULL, NULL, 1, 1);


INSERT INTO pfi_plugin (
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin,
            propertiesentitat, entitatid, actiu, tipus)  VALUES (5, 105, 155, 'org.fundaciobit.plugins.timestamp.afirmarfc.AfirmaRFCTimeStampPlugin', '#################################################
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


INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (106, 'ca', 'Plugin de Custòdia de FileSystem');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (106, 'es', 'Plugin de Custodia de FileSystem');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (166, 'ca', 'Plugin de Custòdia de FileSystem');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (166, 'es', 'Plugin de Custodia de FileSystem');


INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (107, 'ca', 'Plugin de Custòdia per Alfresco');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (107, 'es', 'Plugin de Custodia para Alfresco');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (177, 'ca', 'Plugin de Custòdia per Alfresco');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (177, 'es', 'Plugin de Custodia para Alfresco');


INSERT INTO pfi_plugin (
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin,
            propertiesentitat, entitatid, actiu, tipus) VALUES (6, 106, 166, 'org.fundaciobit.plugins.documentcustody.filesystem.FileSystemDocumentCustodyPlugin', 'es.caib.portafib.plugins.documentcustody.filesystem.basedir=D:\\dades\\dades\\CarpetesPersonals\\Programacio\\portafib-1.1-jboss-5.1.0.GA\\server\\default\\deployportafib\\custodia.war
es.caib.portafib.plugins.documentcustody.filesystem.prefix=CUST_
# {0} = custodyID | {1} = URL.Encoded(custodyID)  | {2} = HASH
#es.caib.portafib.plugins.documentcustody.filesystem.baseurl=http://localhost:8080/custodia/index.jsp?custodyID={1}
es.caib.portafib.plugins.documentcustody.filesystem.baseurl=http://localhost:8080/custodia/index.jsp?hash={2}

es.caib.portafib.plugins.documentcustody.filesystem.hash.password=portafib

#  MD2, MD5, SHA,SHA-256,SHA-384,SHA-512
es.caib.portafib.plugins.documentcustody.filesystem.hash.algorithm=MD5', NULL, NULL, 1, 2);
INSERT INTO pfi_plugin (
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin,
            propertiesentitat, entitatid, actiu, tipus)  VALUES (7, 107, 177, 'org.fundaciobit.plugins.documentcustody.alfresco.base.AlfrescoBaseDocumentCustodyPlugin', '# WS or ATOM
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



-- ========================================
-- 2016/01/12 Propietats Globals
-- ========================================

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (1, 'es.caib.portafib.editableuser', 'false', 'Si està a true permet als usuaris editar l''email  dels usuari-persona i usuaris-entitats, així com el logo dels usuaris-entitat. En cas contrari, únicament és l''administrador d''entitat que pot fer canvis en aquest camps');
     
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (2, 'es.caib.portafib.numberoferrorsinnotificationtosendmail', NULL, 'Opcional. A partir de quants d''errors en una notificació callback s''enviarà un correu al responsable de l''usuari aplicació. Per defecte mai s''envia un correu');
      
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (3, 'es.caib.portafib.numberoferrorstopausenotification', NULL, 'Opcional. A partir de quants d''errors en una notificació callback aquesta automàticament es pausarà. Per defecte es reintenten indefinidament.');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (4, 'es.caib.portafib.notificationtimelapse', NULL, 'Opcional. Valor per defecte 60000ms (1 minut). Ha de ser major de 15000. Temps mínim que s''espera abans de reintentar una notificació fallida en ms.');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (5, 'es.caib.portafib.signaturemodule.absoluteurl', NULL, 'Opcional. Serveix per Plugins de Firma que han d''accedir externament al Servidor de PortaFIB. Si no es defineix llavors obté la URL absoluta de la petició.(Necessari quan el Apache-Proxy no té activat "ProxyPreserveHost On").');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (6, 'es.caib.portafib.maxuploadsizeinbytes', NULL, 'Tamany màxim de pujada de fitxers en bytes. No definit significa sense límit ');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (7, 'es.caib.portafib.maxfitxeradaptatsizeinbytes', NULL, 'Tamany màxim del fitxer PDF una vegada se li han afegit els annexes i taula de firmes. No definit significa sense límit');      

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (8, 'es.caib.portafib.url', 'http://localhost:8080/portafib', 'URL pública d''accés a l''aplicació de PortaFIB. Es requereix fonamentalment per l''inclusió de URLs cap a PortaFIB en l''enviament de correus');

INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (9, 'es.caib.portafib.email.from', 'portafib@portafib.org', 'És l''adreça d''email des d''on s''enviaran les notificacions per correu als usuaris');
  
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (10, 'es.caib.portafib.emailsgroupedsendercronexpression', NULL, 'Opcional. Expressió cron que indica cada quan s''ha d''executar l''enviador de correus quan s''han definit enviament d''avisos agrupats. Per defecte s''executa cada dia a les 6:00 (0 0 6 1/1 * ? *).');
      
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (11, 'es.caib.portafib.automaticredirect', 'false', 'Si val true llavors redirecciona segons el contexte:(a)Si entra amb http dins portafibs llavors redirecciona a portafib (b)Si entra amb https dins portafib i existeix portafib/s llavors redirecciona a portafib/s. Si val false llavors no fa res.');
  
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (12, 'es.caib.portafib.defaultentity', NULL, 'Si val null incica que l''alta de persones i usuaris l''ha de realitzar l''AdEn. En cas contrari s''utilitzarà aquest valor com a entitat on donar d''alta automaticament persona i usuari. En entorns CAIB aquesta propietat es ignorada');
  
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (13, 'es.caib.portafib.defaultrolesincreation', NULL, 'Indica els roles virtuals a asssignar per defecte a l''usuari-entitat quan aquest es crea automàticament. Es tracta d''una llista de roles separats per comes.Els valors possibles són: ROLE_SOLI,ROLE_DEST,ROLE_DELE i ROLE_COLA. En entorns CAIB és ignorada');
  
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (14, 'es.caib.portafib.entitatidforagentssql', NULL, 'Opcional excepte en entorns de la CAIB. Entitat sobre la qual s''aplicaran les accions del [Agents Seycon]. Veure punt [Gestió de Rols a traves de triggers Oracle] del manual d''instal·lació per més informació.');
  
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (15, 'es.caib.portafib.passwordforagentssql', NULL, 'Opcional excepte en entorns de la CAIB. Contrasenya (o clau de pas) per comprovar que les peticions http realment provenen d''un trigger de BBDD. Veure punt [Gestió de Rols a traves de triggers Oracle] del manual d''instal·lació per més informació.');
  
INSERT INTO pfi_propietatglobal(propietatglobalid, clau, valor, descripcio) 
  VALUES (16, 'es.caib.portafib.logouturl', NULL, 'Opcional. Afegeix una nova opció de menú davall de “Configuració�? del menú de la capçalera (superior dreta) que indica una URL que servirà per poder abandonar PortaFIB. Per aplicar canvis requereix reiniciar servidor.');
  

-- ========================================
-- 2016/01/12 Propietats d'Entitat
-- ========================================


CREATE OR REPLACE TRIGGER AutoIncrementPropietatGlobalID
BEFORE INSERT ON PFI_PROPIETATGLOBAL
FOR EACH ROW

BEGIN
  SELECT pfi_portafib_seq.NEXTVAL
  INTO   :new.PROPIETATGLOBALID
  FROM   dual;
END;
  
INSERT INTO pfi_propietatglobal(entitatid, clau, valor, descripcio) SELECT entitatid, 'es.caib.portafib.maxitemstoshowinautocomplete', '10', 'Opcional. Valor per defecte 10. En els formularis de cerques dinàmiques d''usuari, indica el màxim de resultats permesos per mostrar resultats de l''usuari.'  FROM pfi_entitat;

INSERT INTO pfi_propietatglobal(entitatid, clau, valor, descripcio) SELECT entitatid, 'es.caib.portafib.mincharstostartautocomplete', '2', 'Opcional. Valor per defecte 2. En formularis de cerques dinàmiques d''usuari, indica el mínim de caràcters que s''han d''escriure per a que  apareguin resultats. En entitats amb molts d''usuaris es recomana incrementar aquest valor a 3 o 4.'  FROM pfi_entitat;

INSERT INTO pfi_propietatglobal(entitatid, clau, valor, descripcio) SELECT entitatid, 'es.caib.portafib.maxtimelockedsigninms', NULL, 'Opcional. Indica Temps de validesa del Token de Firma només quan hi ha multiples firmes en un bloc o hi ha delegats definits. Es a dir, el temps màxim que un firmant pot tenir bloquejat un document durant la firma. Per defecte 3 minuts (180000).'  FROM pfi_entitat;
  
DROP TRIGGER AutoIncrementPropietatGlobalID;
