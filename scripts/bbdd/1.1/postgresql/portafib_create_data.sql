BEGIN;

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
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (15, 'es', 'Denúncia');
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





-- POSICICO DE LA TAULA DE FIRMES

INSERT INTO pfi_posiciotaulafirmes (posiciotaulafirmesid,nom, descripcio, suportada)   VALUES (0,'taulafirmes.sense_taula_de_firmes', 'Sense taula de firmes, la posicio de les firmes es definiex en la taula pfi_firma', true);
INSERT INTO pfi_posiciotaulafirmes (posiciotaulafirmesid,nom, descripcio, suportada)   VALUES (1,'taulafirmes.taula_de_firmes_primera_pagina', 'Taula de firmes en la primera pàgina', true);
INSERT INTO pfi_posiciotaulafirmes (posiciotaulafirmesid,nom, descripcio, suportada) VALUES (-1,'taulafirmes.taula_de_firmes_darrera_pagina', 'Taula de firmes en la darrera pàgina', true);


-- PRIORITATS

INSERT INTO pfi_prioritat (prioritatid,nom) VALUES (0,'prioritat.baixa');
INSERT INTO pfi_prioritat (prioritatid,nom) VALUES (5,'prioritat.normal');
INSERT INTO pfi_prioritat (prioritatid,nom) VALUES (9,'prioritat.alta');


-- TIPUS DE FIRMA

INSERT INTO pfi_tipusfirma (tipusfirmaid, descripcio, nom, suportada) VALUES (0, 'PADES', 'PADES', true);
INSERT INTO pfi_tipusfirma (tipusfirmaid, descripcio, nom, suportada) VALUES (1, 'XADES', 'XADES', false);
INSERT INTO pfi_tipusfirma (tipusfirmaid, descripcio, nom, suportada) VALUES (2, 'CADES', 'CADES', false);


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
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (10, NULL, 'notificacioavis.requerit_per_validar', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (15, NULL, 'notificacioavis.descartat_per_validar', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (20, NULL, 'notificacioavis.requerit_per_firmar', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (25, NULL, 'notificacioavis.descartat_per_firmar', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (30, NULL, 'notificacioavis.validat', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (40, NULL, 'notificacioavis.invalidat', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (50, NULL, 'notificacioavis.firma_parcial', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (60, NULL, 'notificacioavis.peticio_firmada', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (70, NULL, 'notificacioavis.peticio_rebutjada', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (80, NULL, 'notificacioavis.peticio_pausada', NULL);


-- IDIOMES

INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('ca', 'Català', true, 1);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('es', 'Castellano', true, 2);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('en', 'English', false, 3);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('de', 'Deutsch', false, 5);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('en_US', 'English USA', false, 7);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('fr', 'Français', false, 4);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('it', 'Italiano', false, 6);


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

INSERT INTO pfi_algorismedefirma (algorismedefirmaid, nom, descripcio, suportat) VALUES (0, 'SHA1withRSA', NULL, true);
INSERT INTO pfi_algorismedefirma (algorismedefirmaid, nom, descripcio, suportat) VALUES (1, 'SHA256withRSA', NULL, true);
INSERT INTO pfi_algorismedefirma (algorismedefirmaid, nom, descripcio, suportat) VALUES (2, 'SHA384withRSA', NULL, true);
INSERT INTO pfi_algorismedefirma (algorismedefirmaid, nom, descripcio, suportat) VALUES (3, 'SHA512withRSA', NULL, true);

-- TIPUS DE METADADA

INSERT INTO pfi_tipusmetadada (tipusmetadadaid, nom, descripcio) VALUES (0, 'tipusmetadada.string', 'java.lang.String');
INSERT INTO pfi_tipusmetadada (tipusmetadadaid, nom, descripcio) VALUES (1, 'tipusmetadada.integer', 'java.math.BigInteger');
INSERT INTO pfi_tipusmetadada (tipusmetadadaid, nom, descripcio) VALUES (2, 'tipusmetadada.decimal', 'java.math.BigDecimal');
INSERT INTO pfi_tipusmetadada (tipusmetadadaid, nom, descripcio) VALUES (3, 'tipusmetadada.boolean', 'java.lang.Boolean');
INSERT INTO pfi_tipusmetadada (tipusmetadadaid, nom, descripcio) VALUES (4, 'tipusmetadada.base64', 'org.fundaciobit.plugins.utils.Base64');
INSERT INTO pfi_tipusmetadada (tipusmetadadaid, nom, descripcio) VALUES (5, 'tipusmetadada.date', 'org.fundaciobit.plugins.utils.ISO8601');


-- ===================================
-- 2015/11/11 Plantilla Plugin de Firma
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
            VALUES (1, 101, 111, 'org.fundaciobit.plugins.signatureweb.miniappletinserver.MiniAppletInServerSignatureWebPlugin', 'es.caib.portafib.plugins.signatureweb.miniappletinserver.base_dir=D:/dades/dades/CarpetesPersonals/Programacio/portafib-files/postgresql_1.1/MINIAPPLETINSERVER', NULL, NULL, false, 0);
INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus)
            VALUES (2, 102, 122, 'org.fundaciobit.plugins.signatureweb.miniappletinclient.MiniAppletAsAppletSignatureWebPlugin', NULL, NULL, NULL, true, 0);
INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus)
            VALUES (3, 103, 133, 'org.fundaciobit.plugins.signatureweb.miniappletinclient.MiniAppletAsJavaWebStartSignatureWebPlugin', NULL, NULL, NULL, true, 0);

            
-- =======================================
-- 2015/11/25 Plantilla  Plugin de Segellat de Temps
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


--  Actualitzam la Sequencia per a que no sobreescriqui valors posats a pinyo fix

ALTER SEQUENCE pfi_portafib_seq RESTART WITH 1000;


COMMIT;