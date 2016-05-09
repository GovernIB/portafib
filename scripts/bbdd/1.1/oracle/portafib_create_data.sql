


-- ===========================================================================
-- És el mateix fitxer que posgresql però substiuint true per 1 i false per 0.
-- ===========================================================================

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

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (1, 'ca', 'Resolució');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (1, 'es', 'Resolución');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (2, 'ca', 'Acord');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (2, 'es', 'Acuerdo');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (3, 'ca', 'Contracte');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (3, 'es', 'Contrato');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (4, 'ca', 'Conveni');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (4, 'es', 'Convenio');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (5, 'ca', 'Declaració');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (5, 'es', 'Declaración');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (6, 'ca', 'Comunicació');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (6, 'es', 'Comunicación');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (7, 'ca', 'Notificació');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (7, 'es', 'Notificación');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (8, 'ca', 'Publicació');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (8, 'es', 'Publicación');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (9, 'ca', 'Justificant de recepció');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (9, 'es', 'Justificante de recepción');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (10, 'ca', 'Acta');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (10, 'es', 'Acta');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (11, 'ca', 'Certificat');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (11, 'es', 'Certificado');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (12, 'ca', 'Diligència');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (12, 'es', 'Diligencia');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (13, 'ca', 'Informe');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (13, 'es', 'Informe');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (14, 'ca', 'Sol.licitud');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (14, 'es', 'Solicitud');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (15, 'ca', 'Denúncia');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (15, 'es', 'Denuncia');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (16, 'ca', 'Al.legació');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (16, 'es', 'Alegación');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (17, 'ca', 'Recurs');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (17, 'es', 'Recurso');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (18, 'ca', 'Comunicació al ciutadà');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (18, 'es', 'Comunicación al ciudadano');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (19, 'ca', 'Factura');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (19, 'es', 'Factura');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (20, 'ca', 'Altres confiscats');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (20, 'es', 'Otros confiscados');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (99, 'ca', 'Altres tipus de documents');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (99, 'es', 'Otros tipus de documentos');

INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (1, 'Document de decisió de tipus Resolució', 1, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (2, 'Document de decisió de tipus Acord', 2, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (3, 'Document de decisió de tipus Contracte', 3, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (4, 'Document de decisió de tipus Conveni', 4, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (5, 'Document de decisió de tipus Declaració', 5, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (6, 'Document de transmissió de tipus Comunicació', 6, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (7, 'Document de transmissió de tipus Notificació', 7, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (8, 'Document de transmissió de tipus Publicació', 8, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (9, 'Document de transmissió de tipus Justificant de recepció', 9, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (10, 'Document de constància de tipus Acta', 10, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (11, 'Document de constància de tipus Certificat', 11, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (12, 'Document de constància de tipus Diligència', 12, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (13, 'Document de judici de tipus Informe', 13, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (14, 'Document de ciutadà de tipus Sol.licitud', 14, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (15, 'Document de ciutadà de tipus Denúncia', 15, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (16, 'Document de ciutadà de tipus Al.legació', 16, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (17, 'Document de ciutadà de tipus Recurs', 17, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (18, 'Document de ciutadà de tipus Comunicació al ciudadà', 18, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (19, 'Document de ciutadà de tipus Factura', 19, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (20, 'Document de ciutadà de tipus Altres confiscats', 20, NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (99, 'Altres tipus de documents', 99, NULL);



-- POSICIO DE LA TAULA DE FIRMES

INSERT INTO pfi_posiciotaulafirmes (posiciotaulafirmesid,nom, descripcio, suportada)   VALUES (0,'taulafirmes.sense_taula_de_firmes', 'Sense taula de firmes, la posicio de les firmes es definiex en la taula pfi_firma', 1);
INSERT INTO pfi_posiciotaulafirmes (posiciotaulafirmesid,nom, descripcio, suportada)   VALUES (1,'taulafirmes.taula_de_firmes_primera_pagina', 'Taula de firmes en la primera pàgina', 1);
INSERT INTO pfi_posiciotaulafirmes (posiciotaulafirmesid,nom, descripcio, suportada) VALUES (-1,'taulafirmes.taula_de_firmes_darrera_pagina', 'Taula de firmes en la darrera pàgina', 1);


-- PRIORITATS

INSERT INTO pfi_prioritat (prioritatid,nom) VALUES (0,'prioritat.baixa');
INSERT INTO pfi_prioritat (prioritatid,nom) VALUES (5,'prioritat.normal');
INSERT INTO pfi_prioritat (prioritatid,nom) VALUES (9,'prioritat.alta');


-- TIPUS DE FIRMA

INSERT INTO pfi_tipusfirma (tipusfirmaid, descripcio, nom, suportada) VALUES (0, 'PADES', 'PADES', 1);
INSERT INTO pfi_tipusfirma (tipusfirmaid, descripcio, nom, suportada) VALUES (1, 'XADES', 'XADES', 0);
INSERT INTO pfi_tipusfirma (tipusfirmaid, descripcio, nom, suportada) VALUES (2, 'CADES', 'CADES', 0);


-- TIPUS DE ESTAT DE PETICIO DE FIRMA

INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (0, NULL, 'tipusestatpeticiodefirma.noiniciat');
INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (1, NULL, 'tipusestatpeticiodefirma.enprocess');
INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (2, NULL, 'tipusestatpeticiodefirma.pausat');
INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (3, NULL, 'tipusestatpeticiodefirma.rebutjat');
INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (4, NULL, 'tipusestatpeticiodefirma.firmat');


-- TIPUS DE ESTAT DE FIRMA INICIAL

INSERT INTO pfi_tipusestatdefirmainicial (tipusestatdefirmainicialid, descripcio,  nom) VALUES (0, NULL, 'tipusestatdefirmainicial.ASSIGNAT_PER_VALIDAR');
INSERT INTO pfi_tipusestatdefirmainicial (tipusestatdefirmainicialid, descripcio,  nom) VALUES (1, NULL, 'tipusestatdefirmainicial.ASSIGNAT_PER_FIRMAR');
INSERT INTO pfi_tipusestatdefirmainicial (tipusestatdefirmainicialid, descripcio,  nom) VALUES (2, 'Indica que el colaborador ha indicat als altres possible colaboradors que ell ja s''ho mira per la qual coas el sistema descarta la tasca de la resta de colaboradors ', 'tipusestatdefirmainicial.REVISANT_PER_VALIDAR');

-- TIPUS DE ESTAT DE FIRMA FINAL

INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, descripcio, nom) VALUES (0, NULL, 'tipusestatdefirmafinal.VALIDAT');
INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, descripcio, nom) VALUES (1, NULL, 'tipusestatdefirmafinal.INVALIDAT');
INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, descripcio, nom) VALUES (2, NULL, 'tipusestatdefirmafinal.FIRMAT');
INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, descripcio, nom) VALUES (3, NULL, 'tipusestatdefirmafinal.REBUTJAT');
INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, descripcio, nom) VALUES (4, 'Es pasa a aquest estat quan la tasca encomnada ha sigut realitzada per un altra persona', 'tipusestatdefirmafinal.DESCARTAT');


-- ROLES

INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_ADMIN', 'Administrador PortaFIB', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_DEST', 'Destinatari', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_DELE', 'Delegat', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_COLA', 'Col·laborador', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_ADEN', 'Administrador d''Entitat', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_SOLI', 'Sol·licitant', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('PFI_ADMIN', 'Rol Administrador per Usuaris Aplicació', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('PFI_USER', 'Rol Bàsic per Usuaris Aplicació', NULL);


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

-- IDIOMES

INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('ca', 'Català', 1, 1);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('es', 'Castellano', 1, 2);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('en', 'English', 0, 3);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('de', 'Deutsch', 0, 5);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('en_US', 'English USA', 0, 7);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('fr', 'Français', 0, 4);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('it', 'Italiano', 0, 6);


-- CODIS DE BARRES

INSERT INTO pfi_codibarres (codibarresid, nom, descripcio) VALUES ('org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin', 'Pdf417', NULL);
INSERT INTO pfi_codibarres (codibarresid, nom, descripcio) VALUES ('org.fundaciobit.plugins.barcode.qrcode.QrCodePlugin', 'QrCode', NULL);
INSERT INTO pfi_codibarres (codibarresid, nom, descripcio) VALUES ('org.fundaciobit.plugins.barcode.barcode128.BarCode128Plugin', 'BarCode128', NULL);

-- POSICIO PAGINA

INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (0, 'posiciopagina.cap');
INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (1, 'posiciopagina.adalt');
INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (2, 'posiciopagina.abaix');
INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (3, 'posiciopagina.esquerra');
INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (4, 'posiciopagina.dreta');

-- ALGORISMES DE FIRMA

INSERT INTO pfi_algorismedefirma (algorismedefirmaid, nom, descripcio, suportat) VALUES (0, 'SHA1withRSA', NULL, 1);
INSERT INTO pfi_algorismedefirma (algorismedefirmaid, nom, descripcio, suportat) VALUES (1, 'SHA256withRSA', NULL, 1);
INSERT INTO pfi_algorismedefirma (algorismedefirmaid, nom, descripcio, suportat) VALUES (2, 'SHA384withRSA', NULL, 1);
INSERT INTO pfi_algorismedefirma (algorismedefirmaid, nom, descripcio, suportat) VALUES (3, 'SHA512withRSA', NULL, 1);

-- TIPUS DE METADADA

INSERT INTO pfi_tipusmetadada (tipusmetadadaid, nom, descripcio) VALUES (0, 'tipusmetadada.string', 'java.lang.String');
INSERT INTO pfi_tipusmetadada (tipusmetadadaid, nom, descripcio) VALUES (1, 'tipusmetadada.integer', 'java.math.BigInteger');
INSERT INTO pfi_tipusmetadada (tipusmetadadaid, nom, descripcio) VALUES (2, 'tipusmetadada.decimal', 'java.math.BigDecimal');
INSERT INTO pfi_tipusmetadada (tipusmetadadaid, nom, descripcio) VALUES (3, 'tipusmetadada.boolean', 'java.lang.Boolean');
INSERT INTO pfi_tipusmetadada (tipusmetadadaid, nom, descripcio) VALUES (4, 'tipusmetadada.base64', 'org.fundaciobit.plugins.utils.Base64');
INSERT INTO pfi_tipusmetadada (tipusmetadadaid, nom, descripcio) VALUES (5, 'tipusmetadada.date', 'org.fundaciobit.plugins.utils.ISO8601');


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
            propertiesentitat, entitatid, actiu, tipus)
            VALUES (1, 101, 111, 'org.fundaciobit.plugins.signatureweb.miniappletinserver.MiniAppletInServerSignatureWebPlugin', 'es.caib.portafib.plugins.signatureweb.miniappletinserver.base_dir=D:/dades/dades/CarpetesPersonals/Programacio/portafib-files/postgresql_1.1/MINIAPPLETINSERVER', NULL, NULL, 0, 0);
INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus)
            VALUES (2, 102, 122, 'org.fundaciobit.plugins.signatureweb.miniappletinclient.MiniAppletInClientSignatureWebPlugin', NULL, NULL, NULL, 1, 0);


            
-- =======================================
-- 2015/11/25 Plantilla Plugin de Segellat de Temps
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

INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus) VALUES (4, 104, 144, 'org.fundaciobit.plugins.timestamp.catcertrfc.CatCertRfcTimeStampPlugin', 'es.caib.portafib.plugins.timestamp.catcertrfc.url_rfc=http://psis.catcert.net/psis/catcert/tsp
es.caib.portafib.plugins.timestamp.catcertrfc.oid_rfc3161=0.4.0.2023.1.1
es.caib.portafib.plugins.timestamp.catcertrfc.hashalgorithm=SHA-512', NULL, NULL, 1, 1);


INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus) VALUES (5, 105, 155, 'org.fundaciobit.plugins.timestamp.afirmarfc.AfirmaRFCTimeStampPlugin', '#################################################
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


INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus) VALUES (6, 106, 166, 'org.fundaciobit.plugins.documentcustody.filesystem.FileSystemDocumentCustodyPlugin', 'es.caib.portafib.plugins.documentcustody.filesystem.basedir=D:\\dades\\dades\\CarpetesPersonals\\Programacio\\portafib-1.1-jboss-5.1.0.GA\\server\\default\\deployportafib\\custodia.war
es.caib.portafib.plugins.documentcustody.filesystem.prefix=CUST_
# {0} = custodyID | {1} = URL.Encoded(custodyID)  | {2} = HASH
#es.caib.portafib.plugins.documentcustody.filesystem.baseurl=http://localhost:8080/custodia/index.jsp?custodyID={1}
es.caib.portafib.plugins.documentcustody.filesystem.baseurl=http://localhost:8080/custodia/index.jsp?hash={2}

es.caib.portafib.plugins.documentcustody.filesystem.hash.password=portafib

#  MD2, MD5, SHA,SHA-256,SHA-384,SHA-512
es.caib.portafib.plugins.documentcustody.filesystem.hash.algorithm=MD5', NULL, NULL, 1, 2);
INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus) VALUES (7, 107, 177, 'org.fundaciobit.plugins.documentcustody.alfresco.base.AlfrescoBaseDocumentCustodyPlugin', '# WS or ATOM
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
  VALUES (16, 'es.caib.portafib.logouturl', NULL, 'Opcional. Afegeix una nova opció de menú davall de “Configuració” del menú de la capçalera (superior dreta) que indica una URL que servirà per poder abandonar PortaFIB. Per aplicar canvis requereix reiniciar servidor.');



-- Actualitzam la Sequencia per a que no sobreescriqui valors posats a pinyo fix
-- IMPORTANT !!!!  Ha d'estar al final de l'script SQL
ALTER SEQUENCE pfi_portafib_seq INCREMENT BY 1000;
SELECT pfi_portafib_seq.NEXTVAL FROM dual;
ALTER SEQUENCE pfi_portafib_seq INCREMENT BY 1;

