ALTER SESSION SET CURRENT_SCHEMA = PORTAFIB;

SET DEFINE OFF;
SET SQLBLANKLINES ON;
SET SQLPREFIX '%';

-- Deshabilitar totes les claus foranes (type R) de l'schema PORTAFIB
BEGIN
    FOR c IN
        (SELECT c.owner, c.table_name, c.constraint_name
         FROM ALL_CONSTRAINTS c
         WHERE c.OWNER = 'PORTAFIB'
           AND c.constraint_type = 'R'
           AND c.status = 'ENABLED')
        LOOP
            dbms_utility.exec_ddl_statement('alter table "' || c.owner || '"."' || c.table_name || '" disable constraint ' || c.constraint_name);
        END LOOP;
END;
/

INSERT INTO PFI_BLOCDEFIRMES (BLOCDEFIRMESID,DATAFINALITZACIO,FLUXDEFIRMESID,MINIMDEFIRMES,ORDRE) VALUES (1469,NULL,1468,1,10);
INSERT INTO PFI_BLOCDEFIRMES (BLOCDEFIRMESID,DATAFINALITZACIO,FLUXDEFIRMESID,MINIMDEFIRMES,ORDRE) VALUES (168263,NULL,168262,1,10);
INSERT INTO PFI_BLOCDEFIRMES (BLOCDEFIRMESID,DATAFINALITZACIO,FLUXDEFIRMESID,MINIMDEFIRMES,ORDRE) VALUES (229117,NULL,229116,1,10);
INSERT INTO PFI_BLOCDEFIRMES (BLOCDEFIRMESID,DATAFINALITZACIO,FLUXDEFIRMESID,MINIMDEFIRMES,ORDRE) VALUES (197799,NULL,197798,1,10);

INSERT INTO PFI_CODIBARRES (CODIBARRESID,DESCRIPCIO,NOM) VALUES ('org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin',NULL,'Pdf417');
INSERT INTO PFI_CODIBARRES (CODIBARRESID,DESCRIPCIO,NOM) VALUES ('org.fundaciobit.plugins.barcode.qrcode.QrCodePlugin',NULL,'QrCode');
INSERT INTO PFI_CODIBARRES (CODIBARRESID,DESCRIPCIO,NOM) VALUES ('org.fundaciobit.plugins.barcode.barcode128.BarCode128Plugin',NULL,'BarCode128');

INSERT INTO PFI_COLABORACIODELEGACIO (COLABORACIODELEGACIOID,ACTIVA,COLABORADORDELEGATID,DATAFI,DATAINICI,DESCRIPCIO,DESTINATARIID,ESDELEGAT,FITXERAUTORITZACIOID,MOTIU,MOTIUDESHABILITADA,REVISOR) VALUES (1311,1,'fundaciobit_cola1',TIMESTAMP'2020-11-22 11:34:45',TIMESTAMP'2020-11-16 11:39:44',NULL,'fundaciobit_pruebas',0,NULL,'cola no obligatori',NULL,0);
INSERT INTO PFI_COLABORACIODELEGACIO (COLABORACIODELEGACIOID,ACTIVA,COLABORADORDELEGATID,DATAFI,DATAINICI,DESCRIPCIO,DESTINATARIID,ESDELEGAT,FITXERAUTORITZACIOID,MOTIU,MOTIUDESHABILITADA,REVISOR) VALUES (1314,1,'fundaciobit_cola2',TIMESTAMP'2020-11-22 11:35:53',TIMESTAMP'2020-11-16 11:38:53',NULL,'fundaciobit_pruebas',0,NULL,'colaborador obligatori',NULL,1);
INSERT INTO PFI_COLABORACIODELEGACIO (COLABORACIODELEGACIOID,ACTIVA,COLABORADORDELEGATID,DATAFI,DATAINICI,DESCRIPCIO,DESTINATARIID,ESDELEGAT,FITXERAUTORITZACIOID,MOTIU,MOTIUDESHABILITADA,REVISOR) VALUES (145847,1,'fundaciobit_persona',NULL,TIMESTAMP'2021-02-19 12:33:00',NULL,'fundaciobit_pruebas',1,145849,'tests',NULL,0);
INSERT INTO PFI_COLABORACIODELEGACIO (COLABORACIODELEGACIOID,ACTIVA,COLABORADORDELEGATID,DATAFI,DATAINICI,DESCRIPCIO,DESTINATARIID,ESDELEGAT,FITXERAUTORITZACIOID,MOTIU,MOTIUDESHABILITADA,REVISOR) VALUES (164987,1,'fundaciobit_cola1',NULL,TIMESTAMP'2021-03-02 08:45:00',NULL,'fundaciobit_pruebas',0,NULL,'execució tests',NULL,0);

INSERT INTO PFI_CUSTODIAINFO (CUSTODIAINFOID,CODIBARRESID,CODIBARRESPOSICIOPAGINAID,CODIBARRESTEXT,CSV,CSVGENERATIONDEFINITION,CSVVALIDATIONWEB,CUSTODIAPLUGINID,CUSTODIAPLUGINPARAMETRES,CUSTODIAR,DATACUSTODIA,DOCUMENTID,EDITABLE,ENIFILEDIRECTURL,ENTITATID,EXPEDIENTID,MISSATGE,MISSATGEPOSICIOPAGINAID,NOMPLANTILLA,ORIGINALFILEDIRECTURL,PAGINES,PLUGINID,PRINTABLEFILEDIRECTURL,TITOLPETICIO,URLFITXERCUSTODIAT,USUARIAPLICACIOID,USUARIENTITATID) VALUES (157987,'org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin',3,'{0}',NULL,NULL,NULL,NULL,NULL,1,TIMESTAMP'2021-02-22 11:08:26',NULL,1,NULL,'fundaciobit',NULL,'#
#Mon Feb 22 11:08:26 CET 2021
ca=Document custodiat amb el sistema {2}. Identificador {1}. Data\:{3} URL de validació\:{0}. Valor especial\: {4}
es=Documento custodiado con el sistema {2}. Identificador \= {1}. URL de validación \= {0}. Fecha Custodia\:{3}. Valor especial\: {4}',3,'plantilla1',NULL,'*',6,NULL,NULL,NULL,NULL,NULL);
INSERT INTO PFI_CUSTODIAINFO (CUSTODIAINFOID,CODIBARRESID,CODIBARRESPOSICIOPAGINAID,CODIBARRESTEXT,CSV,CSVGENERATIONDEFINITION,CSVVALIDATIONWEB,CUSTODIAPLUGINID,CUSTODIAPLUGINPARAMETRES,CUSTODIAR,DATACUSTODIA,DOCUMENTID,EDITABLE,ENIFILEDIRECTURL,ENTITATID,EXPEDIENTID,MISSATGE,MISSATGEPOSICIOPAGINAID,NOMPLANTILLA,ORIGINALFILEDIRECTURL,PAGINES,PLUGINID,PRINTABLEFILEDIRECTURL,TITOLPETICIO,URLFITXERCUSTODIAT,USUARIAPLICACIOID,USUARIENTITATID) VALUES (158009,'org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin',3,'{0}',NULL,NULL,NULL,'1613989277935793331802509500',NULL,1,TIMESTAMP'2021-02-22 11:21:48.123',NULL,1,NULL,NULL,NULL,'#
#Mon Feb 22 11:19:55 CET 2021
ca=Document custodiat amb el sistema {2}. Identificador {1}. Data\:{3} URL de validació\:{0}. Valor especial\: {4}
es=Documento custodiado con el sistema {2}. Identificador \= {1}. URL de validación \= {0}. Fecha Custodia\:{3}. Valor especial\: {4}',3,NULL,'http://localhost:8080/custodia/index.jsp?hash=94cb82a53e472ea75aa39e0d5ab7cef5','*',6,NULL,'prova custòdiakkk','http://localhost:8080/custodia/index.jsp?hash=94cb82a53e472ea75aa39e0d5ab7cef5','$app','fundaciobit_aden1');
INSERT INTO PFI_CUSTODIAINFO (CUSTODIAINFOID,CODIBARRESID,CODIBARRESPOSICIOPAGINAID,CODIBARRESTEXT,CSV,CSVGENERATIONDEFINITION,CSVVALIDATIONWEB,CUSTODIAPLUGINID,CUSTODIAPLUGINPARAMETRES,CUSTODIAR,DATACUSTODIA,DOCUMENTID,EDITABLE,ENIFILEDIRECTURL,ENTITATID,EXPEDIENTID,MISSATGE,MISSATGEPOSICIOPAGINAID,NOMPLANTILLA,ORIGINALFILEDIRECTURL,PAGINES,PLUGINID,PRINTABLEFILEDIRECTURL,TITOLPETICIO,URLFITXERCUSTODIAT,USUARIAPLICACIOID,USUARIENTITATID) VALUES (158011,'org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin',3,'{0}',NULL,NULL,NULL,'1613989829282793883149977600',NULL,1,TIMESTAMP'2021-02-22 11:31:20.719',NULL,1,NULL,NULL,NULL,'#
#Mon Feb 22 11:20:29 CET 2021
ca=Document custodiat amb el sistema {2}. Identificador {1}. Data\:{3} URL de validació\:{0}. Valor especial\: {4}
es=Documento custodiado con el sistema {2}. Identificador \= {1}. URL de validación \= {0}. Fecha Custodia\:{3}. Valor especial\: {4}',3,NULL,'http://localhost:8080/custodia/index.jsp?hash=cf54107d3b9cb3be1ce5a22f6722c1bd','*',6,NULL,'prova custodia','http://localhost:8080/custodia/index.jsp?hash=cf54107d3b9cb3be1ce5a22f6722c1bd','$app','fundaciobit_aden1');

INSERT INTO PFI_ENTITAT (ENTITATID,ACTIVA,ADREZAHTML,ALGORISMEDEFIRMAID,CHECKCANVIATDOCFIRMAT,COMPROVARNIFFIRMA,CUSTODIAINFOID,DESCRIPCIO,FAVICONID,FILTRECERTIFICATS,FIRMATPERFORMATID,LOGOSEGELLID,LOGOWEBID,LOGOWEBPEUID,MAXFILESTOSIGNATSAMETIME,MAXSIZEFITXERADAPTAT,MAXUPLOADSIZE,MOTIUDELEGACIOID,NOM,PDFAUTORITZACIODELEGACIOID,PLUGINRUBRICAID,PLUGINID,PLUGINVALIDACERTIFICATID,PLUGINVALIDAFIRMESID,POLICYIDENTIFIER,POLICYIDENTIFIERHASH,POLICYIDENTIFIERHASHALGORITHM,POLICYURLDOCUMENT,POLITICACUSTODIA,SEGELLDETEMPSVIAWEB,POLITICATAULAFIRMES,POSICIOTAULAFIRMES,PROPIETATSTAULAFIRMES,SUPORTEMAIL,SUPORTTELEFON,SUPORTWEB,USPOLITICADEFIRMA,USUARIAPLICACIOID,WEB,VALIDARFIRMA) VALUES ('fundaciobit',1,'<p>carrer</p>',1,1,0,NULL,NULL,1001,TO_CLOB('#'),NULL,1004,1002,1003,NULL,NULL,NULL,NULL,'Fundació BIT',1005,NULL,165808,NULL,84446,NULL,NULL,NULL,NULL,0,3,2,1,NULL,NULL,NULL,NULL,0,'$app','https://www.fundaciobit.org/',1);

INSERT INTO PFI_FIRMA (FIRMAID,BLOCDEFIRMAID,CAIXA_ALT,CAIXA_AMPLE,CAIXA_PAGINA,CAIXA_X,CAIXA_Y,CHECKADMINISTRATIONIDOFSIGNER,CHECKDOCUMENTMODIFICATIONS,CHECKVALIDATIONSIGNATURE,DESTINATARIID,EMISSORCERTIFICAT,FITXERFIRMATID,MINIMDEREVISORS,MOSTRARRUBRICA,MOTIU,NOMCERTIFICAT,NUMFIRMADOCUMENT,NUMEROSERIECERTIFICAT,OBLIGATORI,PERFILDEFIRMA,TIPUSESTATDEFIRMAFINALID,EXTERN_EMAIL,EXTERN_IDIOMA,EXTERN_LLINATGES,EXTERN_NIVELLSEGURETAT,EXTERN_NOM,EXTERN_TOKEN) VALUES (1470,1469,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,'fundaciobit_pruebas',NULL,NULL,1,0,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO PFI_FIRMA (FIRMAID,BLOCDEFIRMAID,CAIXA_ALT,CAIXA_AMPLE,CAIXA_PAGINA,CAIXA_X,CAIXA_Y,CHECKADMINISTRATIONIDOFSIGNER,CHECKDOCUMENTMODIFICATIONS,CHECKVALIDATIONSIGNATURE,DESTINATARIID,EMISSORCERTIFICAT,FITXERFIRMATID,MINIMDEREVISORS,MOSTRARRUBRICA,MOTIU,NOMCERTIFICAT,NUMFIRMADOCUMENT,NUMEROSERIECERTIFICAT,OBLIGATORI,PERFILDEFIRMA,TIPUSESTATDEFIRMAFINALID,EXTERN_EMAIL,EXTERN_IDIOMA,EXTERN_LLINATGES,EXTERN_NIVELLSEGURETAT,EXTERN_NOM,EXTERN_TOKEN) VALUES (168264,168263,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,'fundaciobit_pruebas',NULL,NULL,0,0,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO PFI_FIRMA (FIRMAID,BLOCDEFIRMAID,CAIXA_ALT,CAIXA_AMPLE,CAIXA_PAGINA,CAIXA_X,CAIXA_Y,CHECKADMINISTRATIONIDOFSIGNER,CHECKDOCUMENTMODIFICATIONS,CHECKVALIDATIONSIGNATURE,DESTINATARIID,EMISSORCERTIFICAT,FITXERFIRMATID,MINIMDEREVISORS,MOSTRARRUBRICA,MOTIU,NOMCERTIFICAT,NUMFIRMADOCUMENT,NUMEROSERIECERTIFICAT,OBLIGATORI,PERFILDEFIRMA,TIPUSESTATDEFIRMAFINALID,EXTERN_EMAIL,EXTERN_IDIOMA,EXTERN_LLINATGES,EXTERN_NIVELLSEGURETAT,EXTERN_NOM,EXTERN_TOKEN) VALUES (229118,229117,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,'fundaciobit_pruebas',NULL,NULL,0,0,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO PFI_FIRMA (FIRMAID,BLOCDEFIRMAID,CAIXA_ALT,CAIXA_AMPLE,CAIXA_PAGINA,CAIXA_X,CAIXA_Y,CHECKADMINISTRATIONIDOFSIGNER,CHECKDOCUMENTMODIFICATIONS,CHECKVALIDATIONSIGNATURE,DESTINATARIID,EMISSORCERTIFICAT,FITXERFIRMATID,MINIMDEREVISORS,MOSTRARRUBRICA,MOTIU,NOMCERTIFICAT,NUMFIRMADOCUMENT,NUMEROSERIECERTIFICAT,OBLIGATORI,PERFILDEFIRMA,TIPUSESTATDEFIRMAFINALID,EXTERN_EMAIL,EXTERN_IDIOMA,EXTERN_LLINATGES,EXTERN_NIVELLSEGURETAT,EXTERN_NOM,EXTERN_TOKEN) VALUES (197800,197799,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,'fundaciobit_president',NULL,NULL,0,0,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

INSERT INTO PFI_FITXER (FITXERID,DESCRIPCIO,MIME,NOM,TAMANY) VALUES (1001,NULL,'image/x-icon','fundaciobit.ico',1150);
INSERT INTO PFI_FITXER (FITXERID,DESCRIPCIO,MIME,NOM,TAMANY) VALUES (1002,NULL,'image/png','fundaciobit-logo-cap.png',7962);
INSERT INTO PFI_FITXER (FITXERID,DESCRIPCIO,MIME,NOM,TAMANY) VALUES (1003,NULL,'image/png','fundaciobit-logo-peu.png',1552);
INSERT INTO PFI_FITXER (FITXERID,DESCRIPCIO,MIME,NOM,TAMANY) VALUES (1004,NULL,'image/jpeg','logotaulafirmesfundaciobit.jpg',2410);
INSERT INTO PFI_FITXER (FITXERID,DESCRIPCIO,MIME,NOM,TAMANY) VALUES (1005,NULL,'application/pdf','formulariDelegacio.pdf',65715);
INSERT INTO PFI_FITXER (FITXERID,DESCRIPCIO,MIME,NOM,TAMANY) VALUES (145849,NULL,'application/pdf','FitxerAutoritzacioDelegacio_145847.pdf',116948);

INSERT INTO PFI_FLUXDEFIRMES (FLUXDEFIRMESID,NOM) VALUES (1468,'prova amb revisors');
INSERT INTO PFI_FLUXDEFIRMES (FLUXDEFIRMESID,NOM) VALUES (168262,'Prova des de API REST àáèéòó- 1615900432605');
INSERT INTO PFI_FLUXDEFIRMES (FLUXDEFIRMESID,NOM) VALUES (229116,'Prova des de API REST àáèéòó- 1621934560766');
INSERT INTO PFI_FLUXDEFIRMES (FLUXDEFIRMESID,NOM) VALUES (197798,'Prova per test');

INSERT INTO PFI_IDIOMA (IDIOMAID,NOM,ORDRE,SUPORTAT) VALUES ('ca','Català',1,1);
INSERT INTO PFI_IDIOMA (IDIOMAID,NOM,ORDRE,SUPORTAT) VALUES ('es','Castellano',2,1);
INSERT INTO PFI_IDIOMA (IDIOMAID,NOM,ORDRE,SUPORTAT) VALUES ('it','Italiano',3,0);

INSERT INTO PFI_PERFILSPERUSRAPP (PERFILSPERUSRAPPID,USUARIAPLICACIOPERFILID,USUARIAPLICACIOID) VALUES (1023,1022,'$app');
INSERT INTO PFI_PERFILSPERUSRAPP (PERFILSPERUSRAPPID,USUARIAPLICACIOPERFILID,USUARIAPLICACIOID) VALUES (1493,1022,'$appadmin');
INSERT INTO PFI_PERFILSPERUSRAPP (PERFILSPERUSRAPPID,USUARIAPLICACIOPERFILID,USUARIAPLICACIOID) VALUES (84070,84069,'$app');
INSERT INTO PFI_PERFILSPERUSRAPP (PERFILSPERUSRAPPID,USUARIAPLICACIOPERFILID,USUARIAPLICACIOID) VALUES (84081,1022,'$appindra');
INSERT INTO PFI_PERFILSPERUSRAPP (PERFILSPERUSRAPPID,USUARIAPLICACIOPERFILID,USUARIAPLICACIOID) VALUES (145853,145852,'$entitat');
INSERT INTO PFI_PERFILSPERUSRAPP (PERFILSPERUSRAPPID,USUARIAPLICACIOPERFILID,USUARIAPLICACIOID) VALUES (211151,145852,'$app');
INSERT INTO PFI_PERFILSPERUSRAPP (PERFILSPERUSRAPPID,USUARIAPLICACIOPERFILID,USUARIAPLICACIOID) VALUES (228450,1022,'$entitat');

INSERT INTO PFI_PLANTILLAFLUXDEFIRMES (FLUXDEFIRMESID,COMPARTIR,DESCRIPCIO,USUARIAPLICACIOID,USUARIENTITATID) VALUES (1468,0,'prova amb revisors',NULL,'fundaciobit_aden1');
INSERT INTO PFI_PLANTILLAFLUXDEFIRMES (FLUXDEFIRMESID,COMPARTIR,DESCRIPCIO,USUARIAPLICACIOID,USUARIENTITATID) VALUES (229116,0,'test=true;user=aden1','$entitat',NULL);
INSERT INTO PFI_PLANTILLAFLUXDEFIRMES (FLUXDEFIRMESID,COMPARTIR,DESCRIPCIO,USUARIAPLICACIOID,USUARIENTITATID) VALUES (168262,0,'test=true;user=aden1','$app',NULL);
INSERT INTO PFI_PLANTILLAFLUXDEFIRMES (FLUXDEFIRMESID,COMPARTIR,DESCRIPCIO,USUARIAPLICACIOID,USUARIENTITATID) VALUES (197798,0,'test=true;user=aden1','$entitat',NULL);

INSERT INTO PFI_PLUGIN (PLUGINID,ACTIU,CLASSE,CODI,DESCRIPCIOCURTAID,ENTITATID,NOMID,ORDRE,POLITICADEUS,POLITICAMOSTRARPROPIETATS,PROPERTIESADMIN,PROPERTIESENTITAT,TIPUS) VALUES (6,1,'org.fundaciobit.plugins.documentcustody.filesystem.FileSystemDocumentCustodyPlugin','6',166,NULL,106,NULL,0,2,TO_CLOB('es.caib.portafib.plugins.documentcustody.filesystem.basedir=C:\\Users\\anton\\projectes\\portafib\\custodia
es.caib.portafib.plugins.documentcustody.filesystem.prefix=CUST_
# {0} = custodyID | {1} = URL.Encoded(custodyID)  | {2} = HASH
#es.caib.portafib.plugins.documentcustody.filesystem.baseurl=http://localhost:8080/custodia/index.jsp?custodyID={1}
es.caib.portafib.plugins.documentcustody.filesystem.baseurl=http://localhost:8080/custodia/index.jsp?hash={2}

es.caib.portafib.plugins.documentcustody.filesystem.hash.password=portafib

#  MD2, MD5, SHA,SHA-256,SHA-384,SHA-512
es.caib.portafib.plugins.documentcustody.filesystem.hash.algorithm=MD5'),NULL,2);
INSERT INTO PFI_PLUGIN (PLUGINID,ACTIU,CLASSE,CODI,DESCRIPCIOCURTAID,ENTITATID,NOMID,ORDRE,POLITICADEUS,POLITICAMOSTRARPROPIETATS,PROPERTIESADMIN,PROPERTIESENTITAT,TIPUS) VALUES (7,0,'org.fundaciobit.plugins.documentcustody.alfresco.base.AlfrescoBaseDocumentCustodyPlugin','7',177,NULL,107,NULL,0,2,TO_CLOB('# WS or ATOM
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
#es.caib.portafib.plugins.documentcustody.alfresco.fullsitepath=/Sites/ODES/documentLibrary'),NULL,2);
INSERT INTO PFI_PLUGIN (PLUGINID,ACTIU,CLASSE,CODI,DESCRIPCIOCURTAID,ENTITATID,NOMID,ORDRE,POLITICADEUS,POLITICAMOSTRARPROPIETATS,PROPERTIESADMIN,PROPERTIESENTITAT,TIPUS) VALUES (1042,1,'org.fundaciobit.plugins.signatureweb.afirmatriphaseserver.AfirmaTriphaseSignatureWebPlugin','autofirma',1041,'fundaciobit',1040,NULL,1,2,TO_CLOB('es.caib.portafib.plugins.signatureweb.autofirma.debug=true

# Opcional. Per defecte quan s''arranca AutoFirma des de Firefox s''usa el magatzem de certificats de Firefox.
# Si aquesta propietat val true llavors en Windows carrega els certificats del magatzem del Sistema Operatiu
es.caib.portafib.plugins.signatureweb.autofirma.firefoxinwindowsuseoskeystore=true

es.caib.portafib.plugins.signatureweb.autofirma.timeoutbase=600

# Opcional. Si val true mostrarà en la llista de llocs on descarregar Autofirma
# un nou item per descarregar Autofirma per Windows XP
es.caib.portafib.plugins.signatureweb.autofirma.downloadforwindowsxp=true

# Opcional. Per afegir un nou fitxer de javascript
#es.caib.portafib.plugins.signatureweb.autofirma.newjavascripturl=

# Opcional. Per afegir un nou fitxer de CSS
#es.caib.portafib.plugins.signatureweb.autofirma.newcssurl='),NULL,0);
INSERT INTO PFI_PLUGIN (PLUGINID,ACTIU,CLASSE,CODI,DESCRIPCIOCURTAID,ENTITATID,NOMID,ORDRE,POLITICADEUS,POLITICAMOSTRARPROPIETATS,PROPERTIESADMIN,PROPERTIESENTITAT,TIPUS) VALUES (1086,1,'org.fundaciobit.pluignsib.signatureweb.fortress.FortressSignatureWebPlugin','viafirma',1085,'fundaciobit',1084,1,1,2,TO_CLOB('# Adreça base del sevidor de viafirma fortress
es.caib.portafib.pluginsib.signatureweb.fortress.url=https://pre.firmacloud.com/fortress/

# dades d''autenticació del sistema
es.caib.portafib.pluginsib.signatureweb.fortress.client_id=[=SP["fortress.client_id"]]
es.caib.portafib.pluginsib.signatureweb.fortress.client_secret=[=SP["fortress.client_secret"]]

es.caib.portafib.pluginsib.signatureweb.fortress.debug=true'),NULL,0);
INSERT INTO PFI_PLUGIN (PLUGINID,ACTIU,CLASSE,CODI,DESCRIPCIOCURTAID,ENTITATID,NOMID,ORDRE,POLITICADEUS,POLITICAMOSTRARPROPIETATS,PROPERTIESADMIN,PROPERTIESENTITAT,TIPUS) VALUES (1146,1,'org.fundaciobit.plugins.signatureweb.fire.FIReSignatureWebPlugin','fire',1145,'fundaciobit',1144,NULL,1,2,TO_CLOB('# Exemple per configurar FIRe dins Plantilles de mòduls de Firma Web de Portafib

es.caib.portafib.plugins.signatureweb.fire.debug=false
es.caib.portafib.plugins.signatureweb.fire.appid=674AFFA34A8F
es.caib.portafib.plugins.signatureweb.fire.procedure=PROCEDURE

# deixar buid per permetre autofirma
es.caib.portafib.plugins.signatureweb.fire.certOrigin=clavefirma

es.caib.portafib.plugins.signatureweb.fire.appName=PortaFIB
es.caib.portafib.plugins.signatureweb.fire.fireUrl=https://firepre.caib.es/fire-signature/fireService

es.caib.portafib.plugins.signatureweb.fire.javax.net.ssl.keyStore=/run/secrets/keystore.p12
es.caib.portafib.plugins.signatureweb.fire.javax.net.ssl.keyStorePassword=[=SP["keystore.password"]]
es.caib.portafib.plugins.signatureweb.fire.javax.net.ssl.keyStoreType=PKCS12

es.caib.portafib.plugins.signatureweb.fire.javax.net.ssl.trustStore=all
#es.caib.portafib.plugins.signatureweb.fire.javax.net.ssl.trustStorePassword=
#es.caib.portafib.plugins.signatureweb.fire.javax.net.ssl.trustStoreType=
es.caib.portafib.plugins.signatureweb.fire.allowcertificategeneration=true
es.caib.portafib.plugins.signatureweb.fire.passfilterwhennonregistereduser=true
es.caib.portafib.plugins.signatureweb.fire.showinfowhennonregistereduser=true
es.caib.portafib.plugins.signatureweb.fire.passfilterwhenusercertificateblocked=true
es.caib.portafib.plugins.signatureweb.fire.showinfowhenusercertificateblocked=true
es.caib.portafib.plugins.signatureweb.fire.passfilterwhenuserhasweakregistry=true
es.caib.portafib.plugins.signatureweb.fire.showinfowhenuserhasweakregistry=true
es.caib.portafib.plugins.signatureweb.fire.ignore_certificate_filter=true
# {0} == username || {1} == administrationID (NIF)
es.caib.portafib.plugins.signatureweb.fire.userspattern={1}
es.caib.portafib.plugins.signatureweb.fire.cacheMaxEntries=100
es.caib.portafib.plugins.signatureweb.fire.cacheMaxTimeToLive=900'),NULL,0);
INSERT INTO PFI_PLUGIN (PLUGINID,ACTIU,CLASSE,CODI,DESCRIPCIOCURTAID,ENTITATID,NOMID,ORDRE,POLITICADEUS,POLITICAMOSTRARPROPIETATS,PROPERTIESADMIN,PROPERTIESENTITAT,TIPUS) VALUES (84066,1,'org.fundaciobit.plugins.signatureserver.afirmaserver.AfirmaServerSignatureServerPlugin','afirma_federat',84065,'fundaciobit',84064,NULL,1,2,TO_CLOB('#es.caib.portafib.plugins.signatureserver.afirmaserver.applicationID=CAIBDEV.FBIT
es.caib.portafib.plugins.signatureserver.afirmaserver.applicationID=CAIBDEV2.PORTAFIB
es.caib.portafib.plugins.signatureserver.afirmaserver.applicationID_TimeStamp=CAIBDEV2.PORTAFIB

es.caib.portafib.plugins.signatureserver.afirmaserver.debug=false
es.caib.portafib.plugins.signatureserver.afirmaserver.printxml=false

es.caib.portafib.plugins.signatureserver.afirmaserver.defaultAliasCertificate=afirmades-firma

es.caib.portafib.plugins.signatureserver.afirmaserver.TransformersTemplatesPath=/opt/jboss/afirma/transformersTemplates
es.caib.portafib.plugins.signatureserver.afirmaserver.endpoint=https://afirmades2.caib.es/afirmaws/services/DSSAfirmaSign
es.caib.portafib.plugins.signatureserver.afirmaserver.endpoint_upgrade=https://afirmades2.caib.es/afirmaws/services/DSSAfirmaVerify


es.caib.portafib.plugins.signatureserver.afirmaserver.connectTimeout=5000
es.caib.portafib.plugins.signatureserver.afirmaserver.readTimeout=5000

es.caib.portafib.plugins.signatureserver.afirmaserver.authorization.username=[=SP["afirma.username"]]
es.caib.portafib.plugins.signatureserver.afirmaserver.authorization.password=[=SP["afirma.password"]]
'),NULL,3);
INSERT INTO PFI_PLUGIN (PLUGINID,ACTIU,CLASSE,CODI,DESCRIPCIOCURTAID,ENTITATID,NOMID,ORDRE,POLITICADEUS,POLITICAMOSTRARPROPIETATS,PROPERTIESADMIN,PROPERTIESENTITAT,TIPUS) VALUES (84446,1,'org.fundaciobit.plugins.validatesignature.afirmacxf.AfirmaCxfValidateSignaturePlugin','valida_afirma',84445,'fundaciobit',84444,NULL,1,2,TO_CLOB('# Class org.fundaciobit.plugins.validatesignature.afirmacxf.AfirmaCxfValidateSignaturePlugin

es.caib.portafib.plugins.validatesignature.afirmacxf.TransformersTemplatesPath=/opt/jboss/afirma/transformersTemplates

#es.caib.portafib.plugins.validatesignature.afirmacxf.debug=true
#es.caib.portafib.plugins.validatesignature.afirmacxf.printxml=true

# Obligatiori. Aplicació definida dins "Gestión de Aplicaciones" de @firma federat
es.caib.portafib.plugins.validatesignature.afirmacxf.applicationID=CAIBDEV2.PORTAFIB

es.caib.portafib.plugins.validatesignature.afirmacxf.endpoint=https://afirmades2.caib.es/afirmaws/services/DSSAfirmaVerify

# USERNAME-PASSWORD Token
es.caib.portafib.plugins.validatesignature.afirmacxf.authorization.username=[=SP["afirma.username"]]
es.caib.portafib.plugins.validatesignature.afirmacxf.authorization.password=[=SP["afirma.password"]]
'),NULL,4);
INSERT INTO PFI_PLUGIN (PLUGINID,ACTIU,CLASSE,CODI,DESCRIPCIOCURTAID,ENTITATID,NOMID,ORDRE,POLITICADEUS,POLITICAMOSTRARPROPIETATS,PROPERTIESADMIN,PROPERTIESENTITAT,TIPUS) VALUES (145833,1,'org.fundaciobit.plugins.signatureweb.miniappletinserversia.MiniAppletInServerSIASignatureWebPlugin','sia',145832,'fundaciobit',145831,NULL,1,2,TO_CLOB('es.caib.portafib.plugins.signatureweb.miniappletinserversia.URL_GATEWAY=https://gateway.pre.sia.es/rss-gateway/HESS/OperationGateWayRSS
es.caib.portafib.plugins.signatureweb.miniappletinserversia.AUTH_STORE=/run/secrets/keystore-sia.p12
es.caib.portafib.plugins.signatureweb.miniappletinserversia.AUTH_STORE_PASS=[=SP["keystore-sia.password"]]
es.caib.portafib.plugins.signatureweb.miniappletinserversia.SSL_PROTOCOL=TLSv1
es.caib.portafib.plugins.signatureweb.miniappletinserversia.ForceSFDA=false

# si no hi ha filtres definits per defecte es exclusiu.
es.caib.portafib.plugins.signatureweb.miniappletinserversia.ignore_certificate_filter=true
es.caib.portafib.plugins.signatureweb.miniappletinserversia.skip_certificate_selection=true
es.caib.portafib.plugins.signatureweb.miniappletinserversia.cacheMaxEntries=100

# {0} == username || {1} == administrationID (NIF)
es.caib.portafib.plugins.signatureweb.miniappletinserversia.userspattern=IBSALUT_{1}'),NULL,0);
INSERT INTO PFI_PLUGIN (PLUGINID,ACTIU,CLASSE,CODI,DESCRIPCIOCURTAID,ENTITATID,NOMID,ORDRE,POLITICADEUS,POLITICAMOSTRARPROPIETATS,PROPERTIESADMIN,PROPERTIESENTITAT,TIPUS) VALUES (165808,1,'org.fundaciobit.plugins.timestamp.afirmarfc.AfirmaRFCTimeStampPlugin','5',165807,'fundaciobit',165806,NULL,1,2,TO_CLOB('#################################################
#Configuración para el cliente TSA Java sólo RFC#
#################################################
#Identificador de la aplicación cliente
es.caib.portafib.plugins.timestamp.afirmarfc.applicationid=gobbal.fbit.portafib

#OID politica de timestamping de la TSA del MINHAP
es.caib.portafib.plugins.timestamp.afirmarfc.oid_rfc3161=2.16.724.1.3.1.1.4.2.1.2

es.caib.portafib.plugins.timestamp.afirmarfc.hashalgorithm=SHA1


#####################################################################
#Configuracion autenticacion al servicio RFC3161+HTTPS (puerto 8443)#
#####################################################################

#URL para la conexión al servicio RFC3161 + HTTPS (puerto 8443)
es.caib.portafib.plugins.timestamp.afirmarfc.url_rfc=https://des-tsafirma.redsara.es:8443/tsamap/TspHttpServer

#Ruta y clave del certificado para la autenticación del servicio RFC3161 + HTTPS
#Este certificado debe ser el certificado de autenticación HTTPS cliente dado de alta para su aplicación para el servicio RFC3161 + HTTPS (partes píblica y privada)
es.caib.portafib.plugins.timestamp.afirmarfc.auth.cert.p12.path==/run/secrets/keystore.p12
es.caib.portafib.plugins.timestamp.afirmarfc.auth.cert.p12.password=[=SP["keystore.password"]]
'),NULL,1);

INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1,'es.caib.portafib.editableuser','Si està a true permet als usuaris editar l''email  dels usuari-persona i usuaris-entitats, així com el logo dels usuaris-entitat. En cas contrari, únicament és l''administrador d''entitat que pot fer canvis en aquest camps',NULL,'false');
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (2,'es.caib.portafib.numberoferrorsinnotificationtosendmail','Opcional. A partir de quants d''errors en una notificació callback s''enviarà un correu al responsable de l''usuari aplicació. Per defecte mai s''envia un correu',NULL,NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (3,'es.caib.portafib.numberoferrorstopausenotification','Opcional. A partir de quants d''errors en una notificació callback aquesta automàticament es pausarà. Per defecte es reintenten indefinidament.',NULL,NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (4,'es.caib.portafib.notificationtimelapse','Opcional. Valor per defecte 60000ms (1 minut). Ha de ser major de 15000. Temps mínim que s''espera abans de reintentar una notificació fallida en ms.',NULL,NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (5,'es.caib.portafib.signaturemodule.absoluteurl','Opcional. Serveix per Plugins de Firma que han d''accedir externament al Servidor de PortaFIB. Si no es defineix llavors obté la URL absoluta de la petició.(Necessari quan el Apache-Proxy no té activat "ProxyPreserveHost On").',NULL,'http://localhost:8080/portafib');
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (6,'es.caib.portafib.maxuploadsizeinbytes','Tamany màxim de pujada de fitxers en bytes. No definit significa sense límit ',NULL,NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (7,'es.caib.portafib.maxfitxeradaptatsizeinbytes','Tamany màxim del fitxer PDF una vegada se li han afegit els annexes i taula de firmes. No definit significa sense límit',NULL,NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (8,'es.caib.portafib.url','URL pública d''accés a l''aplicació de PortaFIB. Es requereix fonamentalment per l''inclusió de URLs cap a PortaFIB en l''enviament de correus',NULL,'http://localhost:8080/portafib');
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (9,'es.caib.portafib.email.from','És l''adreça d''email des d''on s''enviaran les notificacions per correu als usuaris',NULL,'portafib@portafib.org');
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (10,'es.caib.portafib.emailsgroupedsendercronexpression','Opcional. Expressió cron que indica cada quan s''ha d''executar l''enviador de correus quan s''han definit enviament d''avisos agrupats. Per defecte s''executa cada dia a les 6:00 (0 0 6 1/1 * ? *).',NULL,NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (11,'es.caib.portafib.automaticredirect','Si val true llavors redirecciona segons el contexte:(a)Si entra amb http dins portafibs llavors redirecciona a portafib (b)Si entra amb https dins portafib i existeix portafib/s llavors redirecciona a portafib/s. Si val false llavors no fa res.',NULL,'false');
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (12,'es.caib.portafib.defaultentity','Si val null incica que l''alta de persones i usuaris l''ha de realitzar l''AdEn. En cas contrari s''utilitzarà aquest valor com a entitat on donar d''alta automaticament persona i usuari. En entorns CAIB aquesta propietat es ignorada',NULL,NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (13,'es.caib.portafib.defaultrolesincreation','Indica els roles virtuals a asssignar per defecte a l''usuari-entitat quan aquest es crea automàticament. Es tracta d''una llista de roles separats per comes.Els valors possibles són: ROLE_SOLI,ROLE_DEST,ROLE_DELE i ROLE_COLA. En entorns CAIB és ignorada',NULL,NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (14,'es.caib.portafib.entitatidforagentssql','Opcional excepte en entorns de la CAIB. Entitat sobre la qual s''aplicaran les accions del [Agents Seycon]. Veure punt [Gestió de Rols a traves de triggers Oracle] del manual d''instal·lació per més informació.',NULL,NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (15,'es.caib.portafib.passwordforagentssql','Opcional excepte en entorns de la CAIB. Contrasenya (o clau de pas) per comprovar que les peticions http realment provenen d''un trigger de BBDD. Veure punt [Gestió de Rols a traves de triggers Oracle] del manual d''instal·lació per més informació.',NULL,NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (16,'es.caib.portafib.logouturl','Opcional. Afegeix una nova opció de menú davall de “Configuració” del menú de la capçalera (superior dreta) que indica una URL que servirà per poder abandonar PortaFIB. Per aplicar canvis requereix reiniciar servidor.',NULL,NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1006,'es.caib.portafib.alwayscreaterevision','Eliminat a la versió 2.0.1 Nou a la versió 2.0.0.1/2.0.1 Opcional. Amb fimes PAdES, si aquesta propietat val true (per defecte i recomanat), la firma sempre és genera mitjançant una revisió. Això permet validar el contingut a baix nivell del fitxer original amb el del fitxer signat. Però la creació de reivisions pot provocar que en determinats fitxers anteriors a la versió PDF 1.7 es generin signatures  no vàlides amb l''error ''urn:afirma:dss:1.0:profile:XSS:resultminor:PadESInvalidContentsKey'' Fixant aquest propietat a `false` permet generar una firma vàlida per aquests fitxers. Però caldrà desactivar l''opció. Comprovar que no s''hagi modificat durant la firma de l''entitat.','fundaciobit',NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1007,'es.caib.portafib.notificationwhencreatedelegaciocolaboracio',' Nou a la versió 1.1.1 .Opcional. Valor per defecte false. Indica si s’han d’enviar avisos via correu electrònic als delegats o col·laboradors quan són assignats per un destinatari. Existeix la mateixa propietat global que és usada com a valor per defecte (Veure punt  )','fundaciobit',NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1008,'es.caib.portafib.maxpeticiotitlelength','Opcional. Valor per defecte 0. Indica la longitud màxima del titol de una peticio de firma si el valor està fixat.','fundaciobit','40');
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1009,'es.caib.portafib.autofirmaallowed',' Nou a la versió 1.1.1 Opcional. Serveix per forçar la visibilitat de l''opció del Menú d''Inici. Aquí s''enumeren els diferents comportaments segons el valor: * true: sempre mostra l''opció de menú. * false: mai mostra l''opció  de menú. * null o no definit: consulta el role real PFI_AUTOFIRMA i es mostra si l''usuari té aquest rol i l''oculta si l''usuari no té aquest rol.','fundaciobit',NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1010,'es.caib.portafib.mincharstostartautocomplete',' Opcional. Valor per defecte 2. En els formularis de cerques dinàmiques d''usuari,  indica el mínim de caràcters que ha d''escriure l''usuari abans de que li apareguin els resultats de la cerca. En entitats amb molts d''usuaris es recomana incrementar aquest valora a 3 o 4 amb la finalitat de reduir càrrega de xarxa, servidor i bbdd.','fundaciobit',NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1011,'es.caib.portafib.transformpdfa',' Eliminat a la versió 2.0.1 Nou a la versió 2.0.0  Opcional. Amb firmes PAdES, si aquest valor es true i el tipus de PDF és PDF/A1 o PDF/A2 o PDF/A3, llavors es transforma el PDF per a que pugui ser adaptat (taula de firmes, ....), però perd la condició de PDF/A','fundaciobit',NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1012,'es.caib.portafib.maxitemstoshowinautocomplete',' Opcional. Valor per defecte 10. En els formularis de cerques dinàmiques d''usuari,           indica el màxim de resultats permesos per mostrar resultats de l''usuari.','fundaciobit',NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1013,'es.caib.portafib.maxtimelockedsigninms',' Opcional. Indica Temps de validesa del Token de Firma només quan hi ha múltiples firmes en un bloc o hi ha delegats definits. Es a dir, el temps màxim que un firmant pot tenir bloquejat un document mentre es realitza el procés de firma. Valor per defecte 3*60*1000 (180000), o sigui 3 minuts. Quan la firma es única en el bloc i no hi ha delegats definits llavors no hi ha bloqueig de temps. Nou a la versió 2.0.0  Es recomana posar uns 10 minuts (600000ms)','fundaciobit',NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1014,'es.caib.portafib.ignorecheckpostsign',' Eliminat a la versió 2.0.0  Nou a la versió 1.1.3 Opcional. Serveix per indicar a PortaFIB que revisi o no revisisi la manipulació del PDF firmat. En resum: * true: no revisa si la part original del PDF s''ha modificat * false, null o no definit: revisa si la part original del PDF s''ha modificat','fundaciobit',NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1015,'es.caib.portafib.avisosfirmespendents.diesabans',' Nou a la versió 1.1.1 .Opcional. Fa que s''enviïn correus als que tenen peticions de firma pendents. Indica el número de dies abans de la caducitat de la petició en que s''han de començar a enviar correus. Relacionat amb la PropietatsGlobal es.caib.portafib.avisosfirmespendents.cron (Veure punt  )','fundaciobit',NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1016,'es.caib.portafib.acceptTransformPDFA',' Nou a la versió 2.0.1 Opcional. Per defecte false. En firmes PAdES, si el tipus de PDF és PDF/A1 o PDF/A2 o PDF/A3 i si a més es requereix Estampar o Afegir Taula de Firmes o Annexar Documents, llavors això implica una transformació del PDF que a la vegada implica una pèrdua de la condició de PDF/A. Si val true s''accepta transformar el PDF/A i perdre a  la condició de PDF/A. Si val false es llança una excepció indicant que no es permeten Estampacions o Taules de Firmes o Annexes  en PDF/A.','fundaciobit',NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (1017,'es.caib.portafib.forcecleanpdf',' Eliminat a la versió 2.0.1 Nou a la versió 2.0.0  Opcional. Amb firmes PAdES, si aquesta propietat val true llavors es fa neteja del PDF per a que no tengui problemes amb el plugins de firma. Això implica que algunes característiques del PDF original es perdin.','fundaciobit',NULL);
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (168261,'es.caib.portafib.flowtemplate.absoluteurl',NULL,NULL,'http://localhost:8080/portafib');
INSERT INTO PFI_PROPIETATGLOBAL (PROPIETATGLOBALID,CLAU,DESCRIPCIO,ENTITATID,VALOR) VALUES (224183,'es.caib.portafib.portafiburlforexternalsignatures',NULL,NULL,'http://localhost:8080/portafib');

INSERT INTO PFI_REBREAVIS (ID,REBREAGRUPAT,TIPUSNOTIFICACIOID,USUARIENTITATID) VALUES (196642,0,80,'fundaciobit_aden1');
INSERT INTO PFI_REBREAVIS (ID,REBREAGRUPAT,TIPUSNOTIFICACIOID,USUARIENTITATID) VALUES (196641,0,15,'fundaciobit_pruebas');

INSERT INTO PFI_REVISORDEFIRMA (REVISORDEFIRMAID,FIRMAID,OBLIGATORI,USUARIENTITATID) VALUES (1471,1470,1,'fundaciobit_revi1');

INSERT INTO PFI_ROLE (ROLEID,DESCRIPCIO,NOM) VALUES ('ROLE_ADMIN',NULL,'Administrador PortaFIB');
INSERT INTO PFI_ROLE (ROLEID,DESCRIPCIO,NOM) VALUES ('ROLE_DEST',NULL,'Destinatari');
INSERT INTO PFI_ROLE (ROLEID,DESCRIPCIO,NOM) VALUES ('ROLE_DELE',NULL,'Delegat');
INSERT INTO PFI_ROLE (ROLEID,DESCRIPCIO,NOM) VALUES ('ROLE_COLA',NULL,'Col·laborador');
INSERT INTO PFI_ROLE (ROLEID,DESCRIPCIO,NOM) VALUES ('ROLE_ADEN',NULL,'Administrador d''Entitat');
INSERT INTO PFI_ROLE (ROLEID,DESCRIPCIO,NOM) VALUES ('ROLE_SOLI',NULL,'Sol·licitant');
INSERT INTO PFI_ROLE (ROLEID,DESCRIPCIO,NOM) VALUES ('ROLE_REVI',NULL,'Revisor de Firmes');
INSERT INTO PFI_ROLE (ROLEID,DESCRIPCIO,NOM) VALUES ('PFI_ADMIN',NULL,'Rol Administrador per Usuaris Aplicació');
INSERT INTO PFI_ROLE (ROLEID,DESCRIPCIO,NOM) VALUES ('PFI_USER',NULL,'Rol Bàsic per Usuaris Aplicació');

INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (1018,'ROLE_ADEN','fundaciobit_aden1');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (1033,'ROLE_DEST','fundaciobit_pruebas');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (1312,'ROLE_COLA','fundaciobit_cola1');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (1315,'ROLE_COLA','fundaciobit_cola2');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (1334,'ROLE_REVI','fundaciobit_revi1');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (1396,'ROLE_SOLI','fundaciobit_aden1');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (1428,'ROLE_REVI','fundaciobit_revi2');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (145841,'ROLE_DEST','fundaciobit_siauser');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (84414,'ROLE_DEST','fundaciobit_viafirmo');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (84986,'ROLE_DEST','fundaciobit_ciudadano');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (145848,'ROLE_DELE','fundaciobit_persona');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (207092,'ROLE_REVI','fundaciobit_revi3');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (157982,'ROLE_DEST','fundaciobit_siatres');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (198976,'ROLE_DEST','fundaciobit_taimi');
INSERT INTO PFI_ROLEUSUARIENTITAT (ID,ROLEID,USUARIENTITATID) VALUES (223480,'ROLE_REVI','fundaciobit_ciudadano');

INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (1,'Document de decisió de tipus Resolució',1,1,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (2,'Document de decisió de tipus Acord',2,2,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (3,'Document de decisió de tipus Contracte',3,3,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (4,'Document de decisió de tipus Conveni',4,4,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (5,'Document de decisió de tipus Declaració',5,5,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (6,'Document de transmissió de tipus Comunicació',6,6,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (7,'Document de transmissió de tipus Notificació',7,7,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (8,'Document de transmissió de tipus Publicació',8,8,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (9,'Document de transmissió de tipus Justificant de recepció',9,9,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (10,'Document de constància de tipus Acta',10,10,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (11,'Document de constància de tipus Certificat',11,11,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (12,'Document de constància de tipus Diligència',12,12,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (13,'Document de judici de tipus Informe',13,13,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (14,'Document de ciutadà de tipus Sol.licitud',14,14,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (15,'Document de ciutadà de tipus Denúncia',15,15,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (16,'Document de ciutadà de tipus Al.legació',16,16,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (17,'Document de ciutadà de tipus Recurs',17,17,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (18,'Document de ciutadà de tipus Comunicació al ciudadà',18,18,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (19,'Document de ciutadà de tipus Factura',19,19,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (20,'Document de ciutadà de tipus Altres confiscats',20,20,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (99,'Altres tipus de documents',99,99,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (51,'TD51 - Llei.',51,51,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (52,'TD52 - Moció',52,52,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (53,'TD53 - Instrucció.',53,53,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (54,'TD54 - Convocatòria.',54,54,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (55,'TD55 - Ordre del dia.',55,55,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (56,'TD56 - Informe de Ponència.',56,56,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (57,'TD57 - Dictamen de Comissió.',57,57,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (58,'TD58 - Iniciativa legislativa.',58,58,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (59,'TD59 - Pregunta.',59,59,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (60,'TD60 - Interpel·lació.',60,60,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (61,'TD61 - Resposta.',61,61,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (62,'TD62 - Proposició no de llei.',62,62,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (63,'TD63 - Esmena.',63,63,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (64,'TD64 - Proposta de resolució.',64,64,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (65,'TD65 - Compareixença.',65,65,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (66,'TD66 - Sol·licitud d´informació.',66,66,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (67,'TD67 - Escrit.',67,67,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (68,'TD68 - Iniciativa legislativa.',68,68,NULL);
INSERT INTO PFI_TIPUSDOCUMENT (TIPUSDOCUMENTID,DESCRIPCIO,NOM,TIPUSDOCUMENTBASEID,USUARIAPLICACIOID) VALUES (69,'TD69 - Petició.',69,69,NULL);

INSERT INTO PFI_TIPUSNOTIFICACIO (TIPUSNOTIFICACIOID,DESCRIPCIO,ESAVIS,NOM) VALUES (0,NULL,NULL,'notificacioavis.peticio_en_proces');
INSERT INTO PFI_TIPUSNOTIFICACIO (TIPUSNOTIFICACIOID,DESCRIPCIO,ESAVIS,NOM) VALUES (10,NULL,1,'notificacioavis.requerit_per_validar');
INSERT INTO PFI_TIPUSNOTIFICACIO (TIPUSNOTIFICACIOID,DESCRIPCIO,ESAVIS,NOM) VALUES (15,NULL,1,'notificacioavis.descartat_per_validar');
INSERT INTO PFI_TIPUSNOTIFICACIO (TIPUSNOTIFICACIOID,DESCRIPCIO,ESAVIS,NOM) VALUES (20,NULL,1,'notificacioavis.requerit_per_firmar');
INSERT INTO PFI_TIPUSNOTIFICACIO (TIPUSNOTIFICACIOID,DESCRIPCIO,ESAVIS,NOM) VALUES (25,NULL,1,'notificacioavis.descartat_per_firmar');
INSERT INTO PFI_TIPUSNOTIFICACIO (TIPUSNOTIFICACIOID,DESCRIPCIO,ESAVIS,NOM) VALUES (30,NULL,1,'notificacioavis.validat');
INSERT INTO PFI_TIPUSNOTIFICACIO (TIPUSNOTIFICACIOID,DESCRIPCIO,ESAVIS,NOM) VALUES (40,NULL,1,'notificacioavis.invalidat');
INSERT INTO PFI_TIPUSNOTIFICACIO (TIPUSNOTIFICACIOID,DESCRIPCIO,ESAVIS,NOM) VALUES (50,NULL,NULL,'notificacioavis.firma_parcial');
INSERT INTO PFI_TIPUSNOTIFICACIO (TIPUSNOTIFICACIOID,DESCRIPCIO,ESAVIS,NOM) VALUES (60,NULL,NULL,'notificacioavis.peticio_firmada');
INSERT INTO PFI_TIPUSNOTIFICACIO (TIPUSNOTIFICACIOID,DESCRIPCIO,ESAVIS,NOM) VALUES (70,NULL,NULL,'notificacioavis.peticio_rebutjada');
INSERT INTO PFI_TIPUSNOTIFICACIO (TIPUSNOTIFICACIOID,DESCRIPCIO,ESAVIS,NOM) VALUES (80,NULL,NULL,'notificacioavis.peticio_pausada');
INSERT INTO PFI_TIPUSNOTIFICACIO (TIPUSNOTIFICACIOID,DESCRIPCIO,ESAVIS,NOM) VALUES (90,NULL,1,'notificacioavis.requerit_per_revisar');

INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (1);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (2);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (3);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (4);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (5);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (6);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (7);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (8);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (9);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (10);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (11);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (12);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (13);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (14);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (15);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (16);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (17);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (18);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (19);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (20);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (51);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (52);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (53);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (54);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (55);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (56);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (57);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (58);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (59);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (60);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (61);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (62);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (63);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (64);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (65);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (66);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (67);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (68);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (69);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (99);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (106);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (107);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (166);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (177);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (1040);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (1041);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (1084);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (1085);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (1144);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (1145);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (84064);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (84065);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (84444);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (84445);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (145831);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (145832);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (165806);
INSERT INTO PFI_TRADUCCIO (TRADUCCIOID) VALUES (165807);

INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1,'Resolució','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1,'Resolución','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (2,'Acord','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (2,'Acuerdo','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (3,'Contracte','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (3,'Contrato','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (4,'Conveni','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (4,'Convenio','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (5,'Declaració','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (5,'Declaración','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (6,'Comunicació','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (6,'Comunicación','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (7,'Notificació','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (7,'Notificación','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (8,'Publicació','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (8,'Publicación','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (9,'Justificant de recepció','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (9,'Justificante de recepción','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (10,'Acta','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (10,'Acta','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (11,'Certificat','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (11,'Certificado','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (12,'Diligència','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (12,'Diligencia','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (13,'Informe','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (13,'Informe','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (14,'Sol.licitud','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (14,'Solicitud','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (15,'Denúncia','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (15,'Denuncia','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (16,'Al.legació','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (16,'Alegación','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (17,'Recurs','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (17,'Recurso','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (18,'Comunicació al ciutadà','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (18,'Comunicación al ciudadano','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (19,'Factura','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (19,'Factura','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (20,'Altres confiscats','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (20,'Otros confiscados','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (99,'Altres tipus de documents','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (99,'Otros tipos de documentos','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (51,'Llei.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (52,'Moció','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (53,'Instrucció.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (54,'Convocatòria.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (55,'Ordre del dia.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (56,'Informe de Ponència.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (57,'Dictamen de Comissió.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (58,'Iniciativa legislativa.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (59,'Pregunta.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (60,'Interpel·lació.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (61,'Resposta.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (62,'Proposició no de llei.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (63,'Esmena.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (64,'Proposta de resolució.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (65,'Compareixença.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (66,'Sol·licitud d´informació.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (67,'Escrit.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (68,'Iniciativa legislativa.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (69,'Petició.','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (51,'Ley.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (52,'Moción','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (53,'Instrucción.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (54,'Convocatoria.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (55,'Orden del día.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (56,'Informe de Ponencia.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (57,'Dictamen de Comisión.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (58,'Iniciativa legislativa.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (59,'Pregunta.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (60,'Interpelación.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (61,'Respuesta.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (62,'Proposición no de ley.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (63,'Enmienda.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (64,'Propuesta de resolución.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (65,'Comparecencia.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (66,'Solicitud de información.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (67,'Escrito.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (68,'Iniciativa legislativa.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (69,'Petición.','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (106,'Plugin de Custòdia de FileSystem','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (106,'Plugin de Custodia de FileSystem','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (166,'Plugin de Custòdia de FileSystem','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (166,'Plugin de Custodia de FileSystem','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (107,'Plugin de Custòdia per Alfresco','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (107,'Plugin de Custodia para Alfresco','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (177,'Plugin de Custòdia per Alfresco','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (177,'Plugin de Custodia para Alfresco','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1040,'Plugin de Firma Web @firma Autofirma & Client @firma Mòbil','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1040,'Plugin de Firma Web @firma Autofirma & Client @firma Mòbil','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1041,'Plugin de Firma Web @firma Autofirma & Client @firma Mòbil','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1041,'Plugin de Firma Web @firma Autofirma & Client @firma Mòbil','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1084,'Viafirma Fortress','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1084,'Viafirma Fortress','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1085,'Viafirma Fortress','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1085,'Viafirma Fortress','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1144,'FIRe','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1144,'FIRe','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1145,'FIRe','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (1145,'FIRe','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (84064,'Plugin de Firma en Servidor emprant @firma federat','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (84064,'Plugin de Firma en Servidor emprant @firma federat','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (84065,'Plugin de Firma en Servidor emprant @firma federat','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (84065,'Plugin de Firma en Servidor emprant @firma federat','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (84444,'Plugin Validació de Firmes emprant Serveis Web d''@firma','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (84444,'Plugin Validació de Firmes emprant Serveis Web d''@firma','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (84445,'Plugin Validació de Firmes emprant Serveis Web d''@firma','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (84445,'Plugin Validació de Firmes emprant Serveis Web d''@firma','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (165806,'Plantilla Plugin de Segell de Temps de @firma (TS@)','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (165806,'Plantilla Plugin de Sellado de Tiempo de @firma (TS@)','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (165807,'Ministeri d''Hisenda i Administracions Públiques','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (165807,'Ministerio de Hacienda y Administraciones Públicas','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (145831,'Firma Cloud SIA','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (145831,'Firma Cloud SIA','es');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (145832,'Firma Cloud SIA','ca');
INSERT INTO PFI_TRADUCCIOMAP (TRADUCCIOMAPID,VALOR,IDIOMAID) VALUES (145832,'Firma Cloud SIA','es');

INSERT INTO PFI_USUARIAPLICACIO (USUARIAPLICACIOID,ACTIU,CALLBACKURL,CALLBACKVERSIO,CUSTODIAINFOID,DESCRIPCIO,EMAILADMIN,ENTITATID,IDIOMAID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,CREARUSUARIS) VALUES ('$app',1,'http://localhost:8080/portafib/cb/v1/PortaFIBCallBack',1,NULL,NULL,'app@fundaciobit.org','fundaciobit','ca',NULL,0,0,0);
INSERT INTO PFI_USUARIAPLICACIO (USUARIAPLICACIOID,ACTIU,CALLBACKURL,CALLBACKVERSIO,CUSTODIAINFOID,DESCRIPCIO,EMAILADMIN,ENTITATID,IDIOMAID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,CREARUSUARIS) VALUES ('$appadmin',1,'http://localhost:8080/portafib/cb/v1/PortaFIBCallBack',1,NULL,NULL,'appadmin@fundaciobit.org','fundaciobit','ca',NULL,0,0,0);
INSERT INTO PFI_USUARIAPLICACIO (USUARIAPLICACIOID,ACTIU,CALLBACKURL,CALLBACKVERSIO,CUSTODIAINFOID,DESCRIPCIO,EMAILADMIN,ENTITATID,IDIOMAID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,CREARUSUARIS) VALUES ('$appindra',1,'http://localhost:8080/portafib/portafirmascb/v0/PortafirmasCallBack',0,NULL,NULL,'appindra@fundaciobit.org','fundaciobit','ca',NULL,0,0,0);
INSERT INTO PFI_USUARIAPLICACIO (USUARIAPLICACIOID,ACTIU,CALLBACKURL,CALLBACKVERSIO,CUSTODIAINFOID,DESCRIPCIO,EMAILADMIN,ENTITATID,IDIOMAID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,CREARUSUARIS) VALUES ('$entitat',1,'http://localhost:8080/portafib/cbrest/v1/event',2,NULL,NULL,'entitat@fundaciobit.org','fundaciobit','ca',NULL,0,0,1);

INSERT INTO PFI_USUARIAPLICACIOCONFIG (USUARIAPLICACIOCONFIGID,ALGORISMEDEFIRMAID,CHECKCANVIATDOCFIRMAT,COMPROVARNIFFIRMA,ENTITATID,ESDEPETICIO,FILTRECERTIFICATS,FIRMATPERFORMATID,HTMLPERLLISTARPLUGINSFIRMAWEB,MODEDEFIRMA,MOTIUDELEGACIOID,NOM,PLUGINFIRMASERVIDORID,PLUGINSEGELLATID,POLICYIDENTIFIER,POLICYIDENTIFIERHASH,POLICYIDENTIFIERHASHALGORITHM,POLICYURLDOCUMENT,POLITICASEGELLATDETEMPS,POLITICATAULAFIRMES,POSICIOTAULAFIRMESID,PROPIETATSTAULAFIRMES,TIPUSFIRMAID,TIPUSOPERACIOFIRMA,UPGRADESIGNFORMAT,USENFIRMAAPISIMPLESERVIDOR,USENFIRMAAPISIMPLEWEB,USENFIRMAWS2,USENFIRMAPASSARELASERVIDOR,USENFIRMAPASSARELAWEB,USENFIRMAWS1,USENFIRMAWEB,USPOLITICADEFIRMA,VALIDARCERTIFICAT,VALIDARFIRMA) VALUES (1020,NULL,1,1,'fundaciobit',0,NULL,NULL,NULL,0,NULL,'PADES',84066,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL,0,0,40,1,1,1,0,0,1,1,0,0,1);
INSERT INTO PFI_USUARIAPLICACIOCONFIG (USUARIAPLICACIOCONFIGID,ALGORISMEDEFIRMAID,CHECKCANVIATDOCFIRMAT,COMPROVARNIFFIRMA,ENTITATID,ESDEPETICIO,FILTRECERTIFICATS,FIRMATPERFORMATID,HTMLPERLLISTARPLUGINSFIRMAWEB,MODEDEFIRMA,MOTIUDELEGACIOID,NOM,PLUGINFIRMASERVIDORID,PLUGINSEGELLATID,POLICYIDENTIFIER,POLICYIDENTIFIERHASH,POLICYIDENTIFIERHASHALGORITHM,POLICYURLDOCUMENT,POLITICASEGELLATDETEMPS,POLITICATAULAFIRMES,POSICIOTAULAFIRMESID,PROPIETATSTAULAFIRMES,TIPUSFIRMAID,TIPUSOPERACIOFIRMA,UPGRADESIGNFORMAT,USENFIRMAAPISIMPLESERVIDOR,USENFIRMAAPISIMPLEWEB,USENFIRMAWS2,USENFIRMAPASSARELASERVIDOR,USENFIRMAPASSARELAWEB,USENFIRMAWS1,USENFIRMAWEB,USPOLITICADEFIRMA,VALIDARCERTIFICAT,VALIDARFIRMA) VALUES (84067,3,0,0,'fundaciobit',0,NULL,NULL,NULL,0,NULL,'servidor XAdES',84066,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL,1,0,22,1,0,0,1,0,0,0,0,0,0);
INSERT INTO PFI_USUARIAPLICACIOCONFIG (USUARIAPLICACIOCONFIGID,ALGORISMEDEFIRMAID,CHECKCANVIATDOCFIRMAT,COMPROVARNIFFIRMA,ENTITATID,ESDEPETICIO,FILTRECERTIFICATS,FIRMATPERFORMATID,HTMLPERLLISTARPLUGINSFIRMAWEB,MODEDEFIRMA,MOTIUDELEGACIOID,NOM,PLUGINFIRMASERVIDORID,PLUGINSEGELLATID,POLICYIDENTIFIER,POLICYIDENTIFIERHASH,POLICYIDENTIFIERHASHALGORITHM,POLICYURLDOCUMENT,POLITICASEGELLATDETEMPS,POLITICATAULAFIRMES,POSICIOTAULAFIRMESID,PROPIETATSTAULAFIRMES,TIPUSFIRMAID,TIPUSOPERACIOFIRMA,UPGRADESIGNFORMAT,USENFIRMAAPISIMPLESERVIDOR,USENFIRMAAPISIMPLEWEB,USENFIRMAWS2,USENFIRMAPASSARELASERVIDOR,USENFIRMAPASSARELAWEB,USENFIRMAWS1,USENFIRMAWEB,USPOLITICADEFIRMA,VALIDARCERTIFICAT,VALIDARFIRMA) VALUES (145850,1,0,1,'fundaciobit',0,NULL,NULL,NULL,0,NULL,'pades no validat',84066,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL,0,0,NULL,1,1,1,0,0,0,0,0,0,0);

INSERT INTO PFI_USUARIAPLICACIOPERFIL (USUARIAPLICACIOPERFILID,CODI,CONDICIO,USRAPPCONFIGURACIO1ID,USRAPPCONFIGURACIO2ID,USRAPPCONFIGURACIO3ID,USRAPPCONFIGURACIO4ID,USRAPPCONFIGURACIO5ID,DESCRIPCIO,NOM,URLBASE) VALUES (1022,'pades',NULL,1020,NULL,NULL,NULL,NULL,NULL,'pades',NULL);
INSERT INTO PFI_USUARIAPLICACIOPERFIL (USUARIAPLICACIOPERFILID,CODI,CONDICIO,USRAPPCONFIGURACIO1ID,USRAPPCONFIGURACIO2ID,USRAPPCONFIGURACIO3ID,USRAPPCONFIGURACIO4ID,USRAPPCONFIGURACIO5ID,DESCRIPCIO,NOM,URLBASE) VALUES (84069,'servidorxades',NULL,84067,NULL,NULL,NULL,NULL,NULL,'xades',NULL);
INSERT INTO PFI_USUARIAPLICACIOPERFIL (USUARIAPLICACIOPERFILID,CODI,CONDICIO,USRAPPCONFIGURACIO1ID,USRAPPCONFIGURACIO2ID,USRAPPCONFIGURACIO3ID,USRAPPCONFIGURACIO4ID,USRAPPCONFIGURACIO5ID,DESCRIPCIO,NOM,URLBASE) VALUES (145852,'pades-novalidat',NULL,145850,NULL,NULL,NULL,NULL,NULL,'pades no validat',NULL);

INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_aden1',1,NULL,NULL,NULL,'fundaciobit',NULL,-1,0,1,0,'aden1');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_pruebas',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,0,1,'pruebas');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_viafirmo',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,0,0,'viafirmo');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_cola1',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,0,0,'cola1');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_dest1',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,1,0,'dest1');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_revi1',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,0,1,'revi1');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_cola2',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,0,0,'cola2');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_revi2',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,0,0,'revi2');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_ciudadano',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,0,0,'ciudadano');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_siauser',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,0,0,'siauser');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_persona',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,0,1,'persona');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_siatres',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,0,0,'siatres');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_president',1,'President',NULL,NULL,'fundaciobit',NULL,0,0,0,0,'pruebas');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_taimi',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,0,1,'taimi');
INSERT INTO PFI_USUARIENTITAT (USUARIENTITATID,ACTIU,CARREC,CUSTODIAINFOID,EMAIL,ENTITATID,LOGOSEGELLID,POLITICACUSTODIA,POLITICADEPLUGINFIRMAWEB,PREDETERMINAT,REBRETOTSELSAVISOS,USUARIPERSONAID) VALUES ('fundaciobit_revi3',1,NULL,NULL,NULL,'fundaciobit',NULL,0,0,0,0,'revi3');

INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (1019,'fundaciobit_pruebas','fundaciobit_pruebas');
INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (1101,'fundaciobit_viafirmo','fundaciobit_viafirmo');
INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (1333,'fundaciobit_revi1','fundaciobit_revi1');
INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (1126,'fundaciobit_dest1','fundaciobit_dest1');
INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (1310,'fundaciobit_cola1','fundaciobit_cola1');
INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (1313,'fundaciobit_cola2','fundaciobit_cola2');
INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (1427,'fundaciobit_revi2','fundaciobit_revi2');
INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (84976,'fundaciobit_ciudadano','fundaciobit_ciudadano');
INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (145834,'fundaciobit_siauser','fundaciobit_siauser');
INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (145846,'fundaciobit_persona','fundaciobit_persona');
INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (207091,'fundaciobit_revi3','fundaciobit_revi3');
INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (157975,'fundaciobit_siatres','fundaciobit_siatres');
INSERT INTO PFI_USUARIENTITATFAVORIT (ID,FAVORITID,ORIGENID) VALUES (198968,'fundaciobit_taimi','fundaciobit_taimi');

INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('admin',NULL,'portafib@portafib.org','ca','admin','12345678Z','admin',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('aden1',NULL,'aden1@fundaciobit.org','ca','Entitat de FundacioBit','00000001E','Admin',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('pruebas',NULL,'pruebas@fundaciobit.org','ca','Eidas Certificado','99999999R','Pruebas',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('viafirmo',NULL,'viafirmo@fundaciobit.org','ca','Profesional Fortress','62800225J','Viafirmo',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('soli1',NULL,'soli1@fundaciobit.org','ca','soli1','00000001S','Solicitant1',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('dest1',NULL,'dest1@fundaciobit.org','ca','null','00000001T','Destinatari1',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('cola2',NULL,'cola2@fundaciobit.org','ca','2','00000002C','Colaborador',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('cola1',NULL,'cola1@fundaciobit.org','ca','1','00000001C','Colaborador',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('revi1',NULL,'revi1@fundaciobit.org','ca','1','00000003T','Revisor',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('revi2',NULL,'revi2@fundaciobit.org','ca','2','00000004G','Revisor',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('persona',NULL,'persona@fundaciobit.org','ca','de la Peça de Proves','00000000T','Persona',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('ciudadano',NULL,'ciudadano@fundaciobit.org','ca','Ficticio Activo','99999990S','Ciutadano',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('siauser',NULL,'siauser@fundaciobit.org','ca','Ibsalut Ibsalut','11111111H','Siauser',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('siatres',NULL,'siatres@fundaciobit.org','ca','Ibsalut Ibsalut','55846601W','Siatres',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('taimi',NULL,'taimi@fundaciobit.org','ca','Virta Toivonen','X0000018H','Taimi',NULL,1);
INSERT INTO PFI_USUARIPERSONA (USUARIPERSONAID,CONTRASENYA,EMAIL,IDIOMAID,LLINATGES,NIF,NOM,RUBRICAID,USUARIINTERN) VALUES ('revi3',NULL,'revi3@fundaciobit.org','ca','3','00000005M','Revisor',NULL,1);

-- Actualitzam la Sequencia per a que no sobreescriqui valors posats a pinyo fix
-- IMPORTANT !!!!  Ha d'estar al final de l'script SQL
ALTER SEQUENCE pfi_portafib_seq INCREMENT BY 230000;
SELECT pfi_portafib_seq.NEXTVAL FROM dual;
ALTER SEQUENCE pfi_portafib_seq INCREMENT BY 1;

-- Rehabilitar totes les claus foranes (type R) de l'schema PORTAFIB
BEGIN
    FOR c IN
        (SELECT c.owner, c.table_name, c.constraint_name
         FROM ALL_CONSTRAINTS c
         WHERE c.OWNER = 'PORTAFIB'
           AND c.constraint_type = 'R'
           AND c.status = 'DISABLED')
        LOOP
            dbms_utility.exec_ddl_statement('alter table "' || c.owner || '"."' || c.table_name || '" enable constraint ' || c.constraint_name);
        END LOOP;
END;
/