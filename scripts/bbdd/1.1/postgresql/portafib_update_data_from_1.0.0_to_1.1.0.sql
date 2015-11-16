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
            VALUES (1, 101, 111, 'org.fundaciobit.plugins.signatureweb.miniappletinserver.MiniAppletInServerSignatureWebPlugin', 'es.caib.portafib.plugins.signatureweb.miniappletinserver.base_dir=D:/dades/dades/CarpetesPersonals/Programacio/portafib-files/postgresql_1.1/MINIAPPLETINSERVER', NULL, NULL, false, 0);
INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus)
            VALUES (2, 102, 122, 'org.fundaciobit.plugins.signatureweb.miniappletinclient.MiniAppletAsAppletSignatureWebPlugin', NULL, NULL, NULL, true, 0);
INSERT INTO pfi_plugin(
            pluginid, nomid, descripciocurtaid, classe, propertiesadmin, 
            propertiesentitat, entitatid, actiu, tipus)
            VALUES (3, 103, 133, 'org.fundaciobit.plugins.signatureweb.miniappletinclient.MiniAppletAsJavaWebStartSignatureWebPlugin', NULL, NULL, NULL, true, 0);
