--
-- PostgreSQL database dump
--

-- Dumped from database version 8.4.14
-- Dumped by pg_dump version 9.3.1
-- Started on 2014-05-14 14:22:52

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = portafib, pg_catalog;

--
-- TOC entry 2268 (class 0 OID 191664)
-- Dependencies: 182
-- Data for Name: pfi_codibarres; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_codibarres (codibarresid, nom, descripcio) VALUES ('org.fundaciobit.plugins.barcode.qrcode.QrCodePlugin', 'QrCode', NULL);
INSERT INTO pfi_codibarres (codibarresid, nom, descripcio) VALUES ('org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin', 'Pdf417', NULL);
INSERT INTO pfi_codibarres (codibarresid, nom, descripcio) VALUES ('org.fundaciobit.plugins.barcode.barcode128.BarCode128Plugin', 'BarCode128', NULL);


--
-- TOC entry 2236 (class 0 OID 69544)
-- Dependencies: 150
-- Data for Name: pfi_fitxer; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (74751, NULL, 'image/jpeg', 'GuiaDePresentacio.jpg', 135015);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (74752, NULL, 'text/plain', 'postgresql_dump.txt', 507);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (74753, NULL, 'application/pdf', 'GuiaDePresentacio.pdf', 157000);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (999, NULL, 'application/pdf', 'FormulariAutoritzacioCAIB.pdf', 53759);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (92850, NULL, 'image/x-icon', 'favicon.ico', 32988);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80150, NULL, 'image/x-icon', 'fundaciobit.ico', 1150);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (600, NULL, 'image/png', 'govern1A.png', 24761);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80151, NULL, 'image/png', 'fundaciobit-logo-cap.png', 7962);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80152, NULL, 'image/png', 'fundaciobit-logo-peu.png', 1552);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80153, NULL, 'image/jpeg', 'logotaulafirmesfundaciobit.jpg', 2410);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80154, NULL, 'image/x-icon', 'caib.ico', 318);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (800, NULL, 'application/pdf', 'hola.pdf', 29653);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80155, NULL, 'image/png', 'govern-logo-cap.png', 3593);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80156, NULL, 'image/png', 'govern-logo-peu.png', 2595);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (2800, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (80157, NULL, 'image/jpeg', 'logotaulafirmescaib.jpg', 4793);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (92851, NULL, 'image/png', 'sonespases_logo_cap.png', 25113);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (92852, NULL, 'image/png', 'sonespases_logo_peu.png', 6872);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (92853, NULL, 'image/png', 'sonespases_logo_taula_firmes.png', 10323);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (92854, NULL, 'application/pdf', 'formulariDelegacio.pdf', 56808);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (5450, NULL, 'application/pdf', 'holaX.pdf', 13965);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (5900, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (5901, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95950, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95951, '', 'application/pdf', 'TaulaDeFirmesDePeticio_96000.pdf', 27579);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95952, '', 'application/pdf', 'PeticioFirma_96000.pdf', 71848);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95953, '', 'application/pdf', 'PeticioFirma_96000.pdf', 120598);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95954, '', 'application/pdf', 'PeticioFirma_96000.pdf', 169466);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (96300, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (96301, NULL, 'image/jpeg', 'radiografia.jpg', 124244);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (96302, '', 'application/pdf', 'TaulaDeFirmesDePeticio_96350.pdf', 151859);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (96303, '', 'application/pdf', 'PeticioFirma_96350.pdf', 196029);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (107702, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (107703, '', 'application/pdf', 'TaulaDeFirmesDePeticio_107951.pdf', 20110);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (50959, '', 'application/pdf', 'FitxerAutoritzacioDelegacio_51150.pdf', 68078);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (666, NULL, 'application/pdf', 'formulariDelegacio.pdf', 56837);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (104201, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75806, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75807, '', 'application/pdf', 'TaulaDeFirmesDePeticio_75852.pdf', 19839);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75808, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75809, '', 'application/pdf', 'TaulaDeFirmesDePeticio_75853.pdf', 19792);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (72751, NULL, 'image/jpeg', 'GuiaDePresentacio.jpg', 135015);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (72752, NULL, 'text/plain', 'EsticFent.txt', 99522);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (72753, NULL, 'application/pdf', 'GuiaDePresentacio.pdf', 157000);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (72754, NULL, 'image/png', 'cilma.png', 4055);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75810, '', 'application/pdf', 'PeticioFirma_75853.pdf', 36332);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75811, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75812, '', 'application/pdf', 'TaulaDeFirmesDePeticio_75854.pdf', 19798);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (75813, '', 'application/pdf', 'FitxerAutoritzacioDelegacio_76000.pdf', 69367);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (94150, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (79762, '', 'application/pdf', 'FitxerAutoritzacioDelegacio_80001.pdf', 69379);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95960, NULL, 'application/pdf', 'hola.pdf', 12899);
INSERT INTO pfi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (95961, NULL, 'image/jpeg', 'radiografia.jpg', 124244);


--
-- TOC entry 2233 (class 0 OID 69519)
-- Dependencies: 147
-- Data for Name: pfi_entitat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_entitat (entitatid, filtrecertificats, nom, descripcio, adrezahtml, activa, suporttelefon, suportweb, suportemail, usuariaplicacioid, pdfautoritzaciodelegacioid, faviconid, logowebid, logowebpeuid, logosegellid, maxuploadsize, maxsizefitxeradaptat, maxfilestosignatsametime, web) VALUES ('fundaciobit', '#ssl:  Serial Number of Certificate
#checkexpiration:true or false ''
#policyid:1.3.6.1.4.1.17326.10.9.2.2.2,1.3.6.1.4.1.17326.1.3.3.2
#sign_cert_usage:  (sense valor) només certificats per firmar
#rfc2254_subject:(&(cn=Antoni Nadal Bennasar*))
rfc2254_issuer:|(cn=AC DNIE 001)(cn=AC DNIE 002)(cn=AC DNIE 003)(cn=AC Camerfirma Certificados Camerales)(cn=AC CAMERFIRMA AAPP)(cn=EC-ACC)(cn=FundacioBit-OTAE)(cn=IBIT_CA)(OU=FNMT Clase 2 CA))', 'Fundacio Bit', NULL, '<p><a href="http://www.ibit.org/"> <strong>Fundaci&oacute; Bit.</strong><br /> </a> <small> Centre Empresarial Son Espanyol<br /> C/ Laura Bassi CP 07121, Palma<br /> Telf. 971 784 730 Fax. 971 784 636 </small></p>', true, '971177283', 'http://otaeweb.ibit.org/', 'otae@ibit.org', 'anadalapp', 666, 80150, 80151, 80152, 80153, NULL, NULL, NULL, 'http://www.fundaciobit.org');
INSERT INTO pfi_entitat (entitatid, filtrecertificats, nom, descripcio, adrezahtml, activa, suporttelefon, suportweb, suportemail, usuariaplicacioid, pdfautoritzaciodelegacioid, faviconid, logowebid, logowebpeuid, logosegellid, maxuploadsize, maxsizefitxeradaptat, maxfilestosignatsametime, web) VALUES ('sonespases', '#ssl:  Serial Number of Certificate
#checkexpiration:true or false ''
#policyid:1.3.6.1.4.1.17326.10.9.2.2.2,1.3.6.1.4.1.17326.1.3.3.2
#sign_cert_usage:  (sense valor) només certificats per firmar
#rfc2254_subject:(&(cn=Antoni Nadal Bennasar*))
rfc2254_issuer:|(cn=AC DNIE 001)(cn=AC Camerfirma Certificados Camerales)(cn=AC CAMERFIRMA AAPP)(cn=EC-ACC)(cn=FundacioBit-OTAE)(cn=IBIT_CA)(OU=FNMT Clase 2 CA))', 'Hospital Son Espases', 'Hospital Universitari Son Espases', '<p><a href="www.hospitalsonespases.es/&lrm;"><strong>Hospital Universitari Son Espases</strong><br /></a><small>Carretera de Valldemossa, 79<br />CP 07120 - Palma - Illes Balears<br />Telf. 871 205 000 </small></p>', true, '871205000', 'http://www.hospitalsonespases.es/index.php?lang=ca&Itemid=710', 'hse.informacio.cites@ssib.es', 'sonespases_app', 92854, 92850, 92851, 92852, 92853, NULL, NULL, NULL, 'http://www.hospitalsonespases.es');
INSERT INTO pfi_entitat (entitatid, filtrecertificats, nom, descripcio, adrezahtml, activa, suporttelefon, suportweb, suportemail, usuariaplicacioid, pdfautoritzaciodelegacioid, faviconid, logowebid, logowebpeuid, logosegellid, maxuploadsize, maxsizefitxeradaptat, maxfilestosignatsametime, web) VALUES ('caib', '#ssl:  Serial Number of Certificate
#checkexpiration:true or false ''
#policyid:1.3.6.1.4.1.17326.10.9.2.2.2,1.3.6.1.4.1.17326.1.3.3.2
#sign_cert_usage:  (sense valor) només certificats per firmar
#rfc2254_subject:(&(cn=Antoni Nadal Bennasar*))
rfc2254_issuer:|(cn=AC DNIE 001)(cn=AC DNIE 002)(cn=AC DNIE 003)(cn=AC Camerfirma Certificados Camerales)(cn=AC CAMERFIRMA AAPP)(cn=EC-ACC)(cn=FundacioBit-OTAE)(cn=IBIT_CA)(OU=FNMT Clase 2 CA))', 'Govern Illes Balears', NULL, '<p><strong><a href="http://www.caib.es/">Govern de les Illes Balears.</a></strong> <br /> <small> C/ de la Llotja, 3 - 07012 Palma<br /> Tel&egrave;fon 971 17 65 65 - Fax 971 17 62 21 </small></p>', true, '012', 'https://proves.caib.es/sistrafront/protected/init.do?modelo=IN0014CON&version=1&centre=WEB&tipus_escrit=PTD&language=es&asunto=PROVA%20TR%C0MIT%20TAF%2001', 'suport@caib.es', 'caibapp', 999, 80154, 80155, 80156, 80157, NULL, NULL, NULL, 'http://www.caib.es');


--
-- TOC entry 2257 (class 0 OID 92635)
-- Dependencies: 171
-- Data for Name: pfi_idioma; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('ca', 'Català', true, 1);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('es', 'Castellano', true, 2);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('de', 'Deutsch', false, 5);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('en_US', 'English USA', false, 7);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('fr', 'Français', false, 4);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('it', 'Italiano', false, 6);
INSERT INTO pfi_idioma (idiomaid, nom, suportat, ordre) VALUES ('en', 'English', false, 3);


--
-- TOC entry 2267 (class 0 OID 191661)
-- Dependencies: 181
-- Data for Name: pfi_posiciopagina; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (1, 'posiciopagina.adalt');
INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (0, 'posiciopagina.cap');
INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (2, 'posiciopagina.abaix');
INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (3, 'posiciopagina.esquerra');
INSERT INTO pfi_posiciopagina (posiciopaginaid, nom) VALUES (4, 'posiciopagina.dreta');


--
-- TOC entry 2255 (class 0 OID 69715)
-- Dependencies: 169
-- Data for Name: pfi_usuariaplicacio; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_usuariaplicacio (contrasenya, entitatid, emailadmin, callbackurl, descripcio, callbackversio, actiu, idiomaid, usuariaplicacioid, logosegellid, potcustodiar) VALUES ('caibapp', 'caib', 'anadal@ibit.org', 'dfsghdfghdfh', NULL, 1, true, 'ca', 'caibapp', NULL, false);
INSERT INTO pfi_usuariaplicacio (contrasenya, entitatid, emailadmin, callbackurl, descripcio, callbackversio, actiu, idiomaid, usuariaplicacioid, logosegellid, potcustodiar) VALUES ('sonespases_app', 'sonespases', 'anadal@fundaciobit.org', 'http://ww.sonespases.es', 'sdfgsdfgsdfg', 1, true, 'ca', 'sonespases_app', NULL, false);
INSERT INTO pfi_usuariaplicacio (contrasenya, entitatid, emailadmin, callbackurl, descripcio, callbackversio, actiu, idiomaid, usuariaplicacioid, logosegellid, potcustodiar) VALUES ('fundaciobit_senserol', 'fundaciobit', 'anadal@fundaciobit.org', 'http://localhost:8080/portafirmascb/web/services/MCGDWS', 'sdfgsdg', 1, true, 'ca', 'fundaciobit_senserol', NULL, false);
INSERT INTO pfi_usuariaplicacio (contrasenya, entitatid, emailadmin, callbackurl, descripcio, callbackversio, actiu, idiomaid, usuariaplicacioid, logosegellid, potcustodiar) VALUES ('fundaciobit_usrapp', 'fundaciobit', 'anadal@ibit.org', 'http://localhost:8080/portafirmascb/web/services/MCGDWS', NULL, 1, true, 'ca', 'fundaciobit_usrapp', NULL, false);
INSERT INTO pfi_usuariaplicacio (contrasenya, entitatid, emailadmin, callbackurl, descripcio, callbackversio, actiu, idiomaid, usuariaplicacioid, logosegellid, potcustodiar) VALUES ('fundaciobithola', 'fundaciobit', 'anadal@ibit.org', 'http://localhost:8080/portafirmascb/web/services/MCGDWS', 'sdfgsafgsdfg', 1, true, 'ca', 'fundaciobithola', NULL, false);
INSERT INTO pfi_usuariaplicacio (contrasenya, entitatid, emailadmin, callbackurl, descripcio, callbackversio, actiu, idiomaid, usuariaplicacioid, logosegellid, potcustodiar) VALUES ('anadalapp', 'fundaciobit', 'anadal@ibit.org', 'http://localhost:8080/portafirmascb/web/services/MCGDWS', 'asdfsdfsdf', 1, true, 'ca', 'anadalapp', NULL, true);


--
-- TOC entry 2253 (class 0 OID 69699)
-- Dependencies: 167
-- Data for Name: pfi_usuaripersona; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('dboerner', 'Daniel', 'Boerner', 'dboerner@ibit.org', 'X0468112Q', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('dele1', 'Delegat', 'deFundacioBit', 'anadal@ibit.org', '00000001D', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('cola1', 'Colaborador', 'deFundacioBit', 'anadal@ibit.org', '00000001C', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('dest1', 'Destinatari', 'deFundacioBit', 'anadal@ibit.org', '00000001T', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('cola2', 'Colaborador', 'deCaib', 'anadal@ibit.org', '00000002C', 'es', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('dest2', 'Destinatari', 'deCaib', 'anadal@ibit.org', '00000002T', 'es', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('dele2', 'Delegat', 'deCaib', 'anadal@ibit.org', '00000002D', 'es', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('aden1', 'Administrador-Entitat', 'deFundacioBit', 'anadal@ibit.org', '00000001E', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('aden2', 'Administrador-Entitat', 'deCaib', 'anadal@ibit.org', '00000002E', 'es', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('provamarilen', 'Marilen', 'Gonzi Gonzi', 'mgonzalez@ibit.org', '11222333D', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('soli2', 'Solicitant', 'deCaib', 'anadal@ibit.org', '00000002S', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('fsalas', 'Felip', 'Salas', 'fsalas@ibit.org', '18225456A', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('admin', 'Administrador', 'dePortafib', 'portafib@portafib.org', '12345678Z', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('convidat', 'Convidat', 'deFundacioBit', 'anadal@ibit.org', '00000003S', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('mgonzalez', 'Marilen', 'Gonzalez', 'mgonzalez@ibit.org', '44328254D', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('des', 'Usuari', 'Desactivat', 'anadal@ibit.org', '00000001N', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('anadal', 'Antoni', 'Nadal Bennasar', 'anadal@ibit.org', '43096845C', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('aden3', 'Admin Entitat', 'deSonEspases', 'anadal@fundaciobit.org', '00000003E', 'ca', NULL);
INSERT INTO pfi_usuaripersona (usuaripersonaid, nom, llinatges, email, nif, idiomaid, rubricaid) VALUES ('soli1', 'Solicitant', 'deFundacioBit', 'anadal@fundaciobit.org', '00000001S', 'ca', NULL);


--
-- TOC entry 2254 (class 0 OID 69706)
-- Dependencies: 168
-- Data for Name: pfi_usuarientitat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_aden2', 'aden2', 'caib', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_cola2', 'cola2', 'caib', 'dcghdfghdfgh', true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_dele2', 'dele2', 'caib', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_dest2', 'dest2', 'caib', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_aden1', 'aden1', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_cola1', 'cola1', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_cola2', 'cola2', 'fundaciobit', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_dele1', 'dele1', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_dest2', 'dest2', 'fundaciobit', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_anadal', 'anadal', 'caib', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_secretari', 'anadal', 'fundaciobit', NULL, true, false, 'Secretari', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_provamarilen', 'provamarilen', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_president', 'anadal', 'fundaciobit', NULL, true, false, 'President', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_soli2', 'soli2', 'caib', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_dboerner', 'dboerner', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_convidat', 'convidat', 'fundaciobit', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('caib_marilen', 'mgonzalez', 'caib', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_marilen', 'mgonzalez', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_pcomite', 'mgonzalez', 'fundaciobit', NULL, true, false, 'President comitè', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_fsalas', 'fsalas', 'fundaciobit', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_des', 'des', 'fundaciobit', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_coordinador', 'anadal', 'fundaciobit', NULL, true, false, 'Coordinador', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_soli1', 'soli1', 'fundaciobit', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_desactivar', 'des', 'fundaciobit', NULL, false, false, 'DESACTIVAR', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_aden3', 'aden3', 'sonespases', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_dele1', 'dele1', 'sonespases', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_cola1', 'cola1', 'sonespases', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_cap_radiologia', 'dest1', 'sonespases', NULL, true, false, 'Cap Radiologia', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_gerent', 'dest1', 'sonespases', NULL, true, false, 'Gerent Hospital', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_ajudant_radiologia', 'anadal', 'sonespases', NULL, true, false, 'Ajudant Radiologia', false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_soli1', 'soli1', 'sonespases', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_anadal', 'anadal', 'sonespases', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_dest1', 'dest1', 'fundaciobit', NULL, true, true, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('sonespases_dest1', 'dest1', 'sonespases', NULL, true, false, NULL, false, NULL, false);
INSERT INTO pfi_usuarientitat (usuarientitatid, usuaripersonaid, entitatid, email, actiu, predeterminat, carrec, rebretotselsavisos, logosegellid, potcustodiar) VALUES ('fundaciobit_anadal', 'anadal', 'fundaciobit', 'anadal@ibit.org', true, true, NULL, false, NULL, true);


--
-- TOC entry 2266 (class 0 OID 191641)
-- Dependencies: 180
-- Data for Name: pfi_custodiainfo; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2237 (class 0 OID 69554)
-- Dependencies: 151
-- Data for Name: pfi_fluxdefirmes; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (94200, 'Còpia de Petició Caducada');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (104051, 'fghdfghdfgh');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (18000, 'President i Secretari');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (3051, 'Plantilla Destinataris');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (30400, 'Destinatari, Secretari i President');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (75652, 'Petició Caducada');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (75653, 'Petició Firmada');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (75654, 'Petició Rebutjada');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (34701, 'PLANTILLA GRAN');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (93050, 'Document Radiologia');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (95800, 'Peticio Acceptada');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (95802, 'Informe Radiologia');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (96150, 'Prova Son Espases');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (107801, 'ertyetyerty');
INSERT INTO pfi_fluxdefirmes (fluxdefirmesid, nom) VALUES (85900, 'PLANTILLA DESACTIVAR');


--
-- TOC entry 2258 (class 0 OID 109038)
-- Dependencies: 172
-- Data for Name: pfi_posiciotaulafirmes; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_posiciotaulafirmes (posiciotaulafirmesid, nom, descripcio, suportada) VALUES (1, 'taulafirmes.taula_de_firmes_primera_pagina', 'Taula de firmes en la primera pàgina', true);
INSERT INTO pfi_posiciotaulafirmes (posiciotaulafirmesid, nom, descripcio, suportada) VALUES (0, 'taulafirmes.sense_taula_de_firmes', 'Sense taula de firmes, la posicio de les firmes es definiex en la taula pfi_firma', true);
INSERT INTO pfi_posiciotaulafirmes (posiciotaulafirmesid, nom, descripcio, suportada) VALUES (-1, 'taulafirmes.taula_de_firmes_darrera_pagina', 'Taula de firmes en la darrera pàgina', true);


--
-- TOC entry 2259 (class 0 OID 109105)
-- Dependencies: 173
-- Data for Name: pfi_prioritat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_prioritat (prioritatid, nom) VALUES (0, 'prioritat.baixa');
INSERT INTO pfi_prioritat (prioritatid, nom) VALUES (9, 'prioritat.alta');
INSERT INTO pfi_prioritat (prioritatid, nom) VALUES (5, 'prioritat.normal');


--
-- TOC entry 2247 (class 0 OID 69652)
-- Dependencies: 161
-- Data for Name: pfi_tipusdocument; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (2, 'Document de decisió de tipus Acord', 'tipusdocument.TD02', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (3, 'Document de decisió de tipus Contracte', 'tipusdocument.TD03', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (4, 'Document de decisió de tipus Conveni', 'tipusdocument.TD04', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (5, 'Document de decisió de tipus Declaració', 'tipusdocument.TD05', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (6, 'Document de transmissió de tipus Comunicació', 'tipusdocument.TD06', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (7, 'Document de transmissió de tipus Notificació', 'tipusdocument.TD07', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (8, 'Document de transmissió de tipus Publicació', 'tipusdocument.TD08', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (9, 'Document de transmissió de tipus Justificant de recepció', 'tipusdocument.TD09', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (10, 'Document de constància de tipus Acta', 'tipusdocument.TD10', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (11, 'Document de constància de tipus Certificat', 'tipusdocument.TD11', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (12, 'Document de constància de tipus Diligència', 'tipusdocument.TD12', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (13, 'Document de judici de tipus Informe', 'tipusdocument.TD13', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (14, 'Document de ciutadà de tipus Sol.licitud', 'tipusdocument.TD14', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (15, 'Document de ciutadà de tipus Denúncia', 'tipusdocument.TD15', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (16, 'Document de ciutadà de tipus Al.legació', 'tipusdocument.TD16', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (17, 'Document de ciutadà de tipus Recurs', 'tipusdocument.TD17', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (18, 'Document de ciutadà de tipus Comunicació al ciudadà', 'tipusdocument.TD18', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (19, 'Document de ciutadà de tipus Factura', 'tipusdocument.TD19', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (20, 'Document de ciutadà de tipus Altres confiscats', 'tipusdocument.TD20', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (21, 'Altres tipus de documents', 'tipusdocument.TD99', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (1, 'Document de decisió de tipus Resolució', 'tipusdocument.TD01', NULL);
INSERT INTO pfi_tipusdocument (tipusdocumentid, descripcio, nom, usuariaplicacioid) VALUES (999, 'Tipo estándar', 'Altres', 'anadalapp');


--
-- TOC entry 2250 (class 0 OID 69672)
-- Dependencies: 164
-- Data for Name: pfi_tipusestatpeticiodefirma; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (0, NULL, 'tipusestatpeticiodefirma.noiniciat');
INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (1, NULL, 'tipusestatpeticiodefirma.enprocess');
INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (2, NULL, 'tipusestatpeticiodefirma.pausat');
INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (3, NULL, 'tipusestatpeticiodefirma.rebutjat');
INSERT INTO pfi_tipusestatpeticiodefirma (tipusestatpeticiodefirmaid, descripcio, nom) VALUES (4, NULL, 'tipusestatpeticiodefirma.firmat');


--
-- TOC entry 2251 (class 0 OID 69681)
-- Dependencies: 165
-- Data for Name: pfi_tipusfirma; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusfirma (tipusfirmaid, descripcio, nom, suportada) VALUES (2, '<p>CADES</p>', 'CADES', false);
INSERT INTO pfi_tipusfirma (tipusfirmaid, descripcio, nom, suportada) VALUES (0, '<p>PADES</p>', 'PADES', true);
INSERT INTO pfi_tipusfirma (tipusfirmaid, descripcio, nom, suportada) VALUES (1, '<p>XADES</p>', 'XADES', false);


--
-- TOC entry 2241 (class 0 OID 69592)
-- Dependencies: 155
-- Data for Name: pfi_peticiodefirma; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (75854, '2014-01-04 10:12:23', '2013-12-04 10:13:05.617', '2013-12-04 10:12:38.757', 2, 75811, 75812, 'Petició Rebutjada', 'Petició Rebutjada', 0, 'Per provar si rebuig funciona', 'anadalapp', 3, 75654, 1, NULL, 'ca', 0, 'Provar Petició Rebutjada', 'Solicitant deFundacioBit', 'soli1@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (107951, '2014-06-14 12:03:37', NULL, '2014-05-14 12:03:52.847', 2, 107702, 107703, 'ertyetyerty', 'ertyetyerty', 0, NULL, 'anadalapp', 1, 107801, 1, NULL, 'ca', 0, 'sdfgsdfgsdfg', 'Antoni Nadal Bennasar', 'anadal@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (75853, '2014-01-04 10:10:40', '2013-12-04 10:11:31.242', '2013-12-04 10:11:09.21', 2, 75808, 75809, 'Petició Firmada', 'Petició Firmada', 0, NULL, 'anadalapp', 4, 75653, 1, NULL, 'ca', 0, 'Prova de peticio Firmada', 'Solicitant deFundacioBit', 'soli1@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (104251, '2014-05-31 10:52:47', NULL, '2014-05-13 13:12:25', 2, 104201, NULL, 'fghdfghdfgh', 'fghdfghdfgh', 0, NULL, 'anadalapp', 0, 104051, 1, NULL, 'ca', 0, 'sdfgsdfgsdfg', 'Antoni Nadal Bennasar (anadalapp)', 'anadal@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (75852, '2013-12-03 10:08:25', '2014-03-24 12:28:21.181', '2013-11-01 10:09:00', 10, 75806, 75807, 'Petició Caducada', 'Petició Caducada', 0, 'No està bé', 'anadalapp', 3, 75652, 1, NULL, 'ca', 0, 'Prova Petició Caducada', 'Solicitant deFundacioBit', 'soli1@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (94350, '2013-12-03 10:08:25', NULL, '2014-03-24 13:03:17.169', 10, 94150, NULL, 'Còpia de Petició Caducada', 'Petició Caducada', 0, NULL, 'anadalapp', 0, 94200, 1, NULL, 'ca', 0, 'Prova Petició Caducada', 'Solicitant deFundacioBit', 'soli1@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (96000, '2014-04-25 12:21:21', '2014-03-25 12:26:07.138', '2014-03-25 12:21:57.166', 2, 95950, 95951, 'Peticio Acceptada', 'Peticio Acceptada', 0, NULL, 'sonespases_app', 4, 95800, 1, NULL, 'ca', 0, 'Pre fer demo', 'Solicitant deFundacioBit', 'soli1@ibit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (96350, '2014-04-26 09:34:09', '2014-03-26 09:44:36.109', '2014-03-26 09:37:03.281', 13, 96300, 96302, 'Prova Son Espases', 'Prova Son Espases', 0, 'Hi ha errors', 'sonespases_app', 3, 96150, 1, NULL, 'ca', 0, 'Prova DEMO', 'Solicitant deFundacioBit', 'anadal@fundaciobit.org', NULL, NULL, NULL);
INSERT INTO pfi_peticiodefirma (peticiodefirmaid, datacaducitat, datafinal, datasolicitud, tipusdocumentid, fitxerafirmarid, fitxeradaptatid, titol, descripcio, tipusfirmaid, motiuderebuig, usuariaplicacioid, tipusestatpeticiodefirmaid, fluxdefirmesid, posiciotaulafirmesid, descripciotipusdocument, idiomaid, prioritatid, motiu, remitentnom, remitentdescripcio, informacioadicional, logosegellid, custodiainfoid) VALUES (96002, '2014-04-25 13:21:09', NULL, '2014-03-28 08:04:30', 13, 95960, NULL, 'Informe Radiologia', 'Informe Radiologia', 0, NULL, 'sonespases_app', 0, 95802, 1, NULL, 'ca', 5, 'Motiu DEMO', 'Solicitant deFundacioBit', 'soli1@ibit.org', NULL, NULL, NULL);


--
-- TOC entry 2228 (class 0 OID 69470)
-- Dependencies: 142
-- Data for Name: pfi_annex; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_annex (annexid, fitxerid, peticiodefirmaid, adjuntar, firmar) VALUES (96100, 95961, 96002, true, true);
INSERT INTO pfi_annex (annexid, fitxerid, peticiodefirmaid, adjuntar, firmar) VALUES (96400, 96301, 96350, true, true);


--
-- TOC entry 2231 (class 0 OID 69502)
-- Dependencies: 145
-- Data for Name: pfi_blocdefirmes; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (75705, '2013-12-04 10:13:05.617', 75654, 1, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (75704, '2013-12-04 10:11:31.242', 75653, 1, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (85801, NULL, 85900, 1, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (34755, NULL, 34701, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107852, NULL, 107801, 1, 7);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107853, NULL, 107801, 1, 4);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107854, NULL, 107801, 1, 0);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (95854, NULL, 95802, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107855, NULL, 107801, 1, 6);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107856, NULL, 107801, 1, 2);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107857, NULL, 107801, 1, 1);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (96201, NULL, 96150, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107858, NULL, 107801, 1, 8);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (34753, NULL, 34701, 2, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107859, NULL, 107801, 1, 5);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (34752, NULL, 34701, 1, 30);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (96200, '2014-03-26 09:44:36.109', 96150, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (95853, NULL, 95802, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (107860, NULL, 107801, 1, 3);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (93102, NULL, 93050, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (93101, NULL, 93050, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (33400, NULL, 3051, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (75703, '2014-03-24 12:28:21.181', 75652, 1, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (94250, NULL, 94200, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (94251, NULL, 94200, 1, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (95851, '2014-03-25 12:25:12.784', 95800, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (95850, '2014-03-25 12:26:07.138', 95800, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (18050, NULL, 18000, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (18051, NULL, 18000, 3, 30);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (18200, NULL, 18000, 1, 40);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (23151, NULL, 18000, 2, 50);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (30450, NULL, 30400, 2, 10);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (30451, NULL, 30400, 1, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (104101, NULL, 104051, 1, 0);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (33501, NULL, 3051, 2, 20);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (30201, NULL, 3051, 1, 30);
INSERT INTO pfi_blocdefirmes (blocdefirmesid, datafinalitzacio, fluxdefirmesid, minimdefirmes, ordre) VALUES (75702, NULL, 75652, 1, 20);


--
-- TOC entry 2261 (class 0 OID 117254)
-- Dependencies: 175
-- Data for Name: pfi_tipusestatdefirmafinal; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, nom, descripcio) VALUES (0, 'tipusestatdefirmafinal.VALIDAT', NULL);
INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, nom, descripcio) VALUES (1, 'tipusestatdefirmafinal.INVALIDAT', NULL);
INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, nom, descripcio) VALUES (2, 'tipusestatdefirmafinal.FIRMAT', NULL);
INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, nom, descripcio) VALUES (3, 'tipusestatdefirmafinal.REBUTJAT', NULL);
INSERT INTO pfi_tipusestatdefirmafinal (tipusestatdefirmafinalid, nom, descripcio) VALUES (4, 'tipusestatdefirmafinal.DESCARTAT', 'Es pasa a aquest estat quan la tasca encomnada ha sigut realitzada per un altra persona');


--
-- TOC entry 2235 (class 0 OID 69535)
-- Dependencies: 149
-- Data for Name: pfi_firma; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (93152, 'sonespases_gerent', 93102, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (93153, 'sonespases_anadal', 93101, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (93154, 'sonespases_ajudant_radiologia', 93101, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (93155, 'sonespases_cap_radiologia', 93101, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (18100, 'fundaciobit_president', 18050, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, 33460682019971198397969807784903978913, '123123', '13232123', NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (18101, 'fundaciobit_secretari', 18051, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (18150, 'fundaciobit_dest2', 18051, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (18500, 'fundaciobit_dest1', 18051, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (23201, 'fundaciobit_secretari', 23151, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (23202, 'fundaciobit_pcomite', 23151, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (30251, 'fundaciobit_president', 30201, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (30502, 'fundaciobit_president', 30451, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (32501, 'fundaciobit_secretari', 30450, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (33450, 'fundaciobit_dest1', 33400, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (18300, 'fundaciobit_anadal', 18200, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (33451, 'fundaciobit_dest2', 33400, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (33552, 'fundaciobit_secretari', 33501, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (33651, 'fundaciobit_cola2', 33501, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34803, 'fundaciobit_dest2', 34752, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34804, 'fundaciobit_president', 34753, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34805, 'fundaciobit_secretari', 34753, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34807, 'fundaciobit_dest1', 34755, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34808, 'fundaciobit_cola1', 34755, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34809, 'fundaciobit_dele1', 34755, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (34810, 'fundaciobit_soli1', 34755, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (50801, 'fundaciobit_dest1', 30450, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (75752, 'fundaciobit_dest1', 75702, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (75754, 'fundaciobit_president', 75704, 75810, 1, true, 0, NULL, NULL, NULL, NULL, 13852547191753327203, 'C=ES,O=AC CAMERFIRMA S.A.,L=MADRID (Ver en https://www.camerfirma.com/address),OU=AC CAMERFIRMA,SERIALNUMBER=A82743287,CN=AC CAMERFIRMA AAPP', '2.5.4.13=Qualified Certificate: AAPP-SEP-M-SW-KUSU,CN=CERITIFICAT DE PROVES DE LA DGIDT,SERIALNUMBER=S0711001H,OU=sello electrónico,O=COMUNITAT AUTÒNOMA DE LES ILLES BALEARS,C=ES', 2, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (75755, 'fundaciobit_president', 75705, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (85851, 'fundaciobit_desactivar', 85801, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (75753, 'fundaciobit_president', 75703, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (94300, 'fundaciobit_dest1', 94250, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (94301, 'fundaciobit_president', 94251, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95901, 'sonespases_anadal', 95851, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95903, 'sonespases_ajudant_radiologia', 95851, 95953, 2, false, 0, NULL, NULL, NULL, NULL, 1023931205, 'C=ES,O=FNMT,OU=FNMT Clase 2 CA', 'C=es,O=FNMT,OU=FNMT Clase 2 CA,OU=500070015,CN=NOMBRE NADAL BENNASAR ANTONI - NIF 43096845C', 4, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95902, 'sonespases_cap_radiologia', 95851, 95952, 1, true, 0, NULL, NULL, NULL, NULL, 1023931205, 'C=ES,O=FNMT,OU=FNMT Clase 2 CA', 'C=es,O=FNMT,OU=FNMT Clase 2 CA,OU=500070015,CN=NOMBRE NADAL BENNASAR ANTONI - NIF 43096845C', 4, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95900, 'sonespases_gerent', 95850, 95954, 3, true, 0, NULL, NULL, NULL, NULL, 1023931205, 'C=ES,O=FNMT,OU=FNMT Clase 2 CA', 'C=es,O=FNMT,OU=FNMT Clase 2 CA,OU=500070015,CN=NOMBRE NADAL BENNASAR ANTONI - NIF 43096845C', 2, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95907, 'sonespases_anadal', 95853, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95909, 'sonespases_gerent', 95854, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (96250, 'sonespases_anadal', 96200, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (96253, 'sonespases_gerent', 96201, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (96252, 'sonespases_cap_radiologia', 96200, 96303, 1, true, 0, NULL, NULL, NULL, NULL, 1023931205, 'C=ES,O=FNMT,OU=FNMT Clase 2 CA', 'C=es,O=FNMT,OU=FNMT Clase 2 CA,OU=500070015,CN=NOMBRE NADAL BENNASAR ANTONI - NIF 43096845C', 2, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (96251, 'sonespases_ajudant_radiologia', 96200, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95906, 'sonespases_cap_radiologia', 95853, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (95908, 'sonespases_ajudant_radiologia', 95853, NULL, NULL, false, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (104151, 'fundaciobit_president', 104101, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107902, 'fundaciobit_dest1', 107852, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107903, 'fundaciobit_cola2', 107853, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107904, 'fundaciobit_coordinador', 107854, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107905, 'fundaciobit_dest2', 107855, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107906, 'fundaciobit_pcomite', 107856, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107907, 'fundaciobit_president', 107857, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107908, 'fundaciobit_soli1', 107858, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107909, 'fundaciobit_cola1', 107859, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO pfi_firma (firmaid, destinatariid, blocdefirmaid, fitxerfirmatid, numfirmadocument, obligatori, caixa_pagina, caixa_x, caixa_y, caixa_ample, caixa_alt, numeroseriecertificat, emissorcertificat, nomcertificat, tipusestatdefirmafinalid, mostrarrubrica) VALUES (107910, 'fundaciobit_secretari', 107860, NULL, NULL, true, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);


--
-- TOC entry 2229 (class 0 OID 69479)
-- Dependencies: 143
-- Data for Name: pfi_annexfirmat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2230 (class 0 OID 69496)
-- Dependencies: 144
-- Data for Name: pfi_bitacola; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2232 (class 0 OID 69508)
-- Dependencies: 146
-- Data for Name: pfi_colaboraciodelegacio; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (76000, NULL, '2013-12-04 10:27:28', 'fundaciobit_dest1', 'Per l´article 3/203 del BOIB', 'fundaciobit_dele1', NULL, 75813, true, false, true, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (61950, NULL, '2013-10-31 09:34:29', 'fundaciobit_anadal', 'Colaborador ACTIU', 'fundaciobit_cola1', NULL, NULL, false, false, false, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (5500, NULL, '2013-05-13 15:46:18', 'fundaciobit_anadal', 'per fer proves', 'fundaciobit_dele1', 'perque si me dona la gana', 5450, false, false, true, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (31001, NULL, '2013-09-12 14:39:04', 'fundaciobit_dest1', 'Necessit ajuda', 'fundaciobit_cola1', NULL, NULL, true, false, false, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (31500, NULL, '2013-09-13 09:25:19', 'fundaciobit_dest1', 'Un altra col·laborador', 'fundaciobit_cola2', NULL, NULL, true, false, false, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (5950, NULL, '2013-05-13 12:26:39', 'fundaciobit_anadal', 'Es un colaborador', 'fundaciobit_cola1', 'dghsgdhdfghd', 5900, false, false, false, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (5951, NULL, '2013-05-13 12:27:53', 'fundaciobit_anadal', 'Es un colaborador', 'fundaciobit_cola2', 'Em lia la DEMO', 5901, false, false, false, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (51150, NULL, '2013-10-04 10:55:49', 'fundaciobit_anadal', 'Boib 201', 'fundaciobit_dboerner', 'borrar', 50959, false, false, true, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (80001, NULL, '2013-12-16 15:07:12', 'fundaciobit_anadal', '12345567890 123456789 12345456789 123456789 123456789 123456', 'fundaciobit_anadal', NULL, 79762, true, false, true, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (93700, '2014-08-31 12:04:11', '2014-08-01 12:34:10', 'sonespases_dest1', 'Estic de Vacances', 'sonespases_anadal', NULL, NULL, false, false, true, NULL);
INSERT INTO pfi_colaboraciodelegacio (colaboraciodelegacioid, datafi, datainici, destinatariid, motiu, colaboradordelegatid, motiudeshabilitada, fitxerautoritzacioid, activa, revisor, esdelegat, descripcio) VALUES (93701, NULL, '2014-03-24 12:35:20', 'sonespases_dest1', 'Ajuda a revisar document', 'sonespases_anadal', NULL, NULL, false, false, false, NULL);


--
-- TOC entry 2249 (class 0 OID 69666)
-- Dependencies: 163
-- Data for Name: pfi_tipusestatdefirmainicial; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusestatdefirmainicial (tipusestatdefirmainicialid, descripcio, nom) VALUES (0, NULL, 'tipusestatdefirmainicial.ASSIGNAT_PER_VALIDAR');
INSERT INTO pfi_tipusestatdefirmainicial (tipusestatdefirmainicialid, descripcio, nom) VALUES (1, NULL, 'tipusestatdefirmainicial.ASSIGNAT_PER_FIRMAR');
INSERT INTO pfi_tipusestatdefirmainicial (tipusestatdefirmainicialid, descripcio, nom) VALUES (2, NULL, 'tipusestatdefirmainicial.REVISANT_PER_VALIDAR');


--
-- TOC entry 2234 (class 0 OID 69529)
-- Dependencies: 148
-- Data for Name: pfi_estatdefirma; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (75906, '2013-12-04 10:11:31.148', '2013-12-04 10:11:09.335', '', 1, 75754, 'fundaciobit_anadal', NULL, 2);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (75907, '2013-12-04 10:11:31.148', '2013-12-04 10:11:09.46', 'Alguna altra persona ja ha firmat la peticio', 0, 75754, 'fundaciobit_cola1', 61950, 4);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (75908, '2013-12-04 10:13:05.617', '2013-12-04 10:12:38.867', 'Per provar si rebuig funciona', 1, 75755, 'fundaciobit_anadal', NULL, 3);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (75909, '2013-12-04 10:13:05.617', '2013-12-04 10:12:39.007', 'La petició ha sigut rebutjada per un altre usuari', 0, 75755, 'fundaciobit_cola1', 61950, 4);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96051, '2014-03-25 12:24:33.292', '2014-03-25 12:21:57.338', '', 1, 95902, 'sonespases_dest1', NULL, 2);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96052, '2014-03-25 12:25:12.753', '2014-03-25 12:21:57.354', '', 1, 95903, 'sonespases_anadal', NULL, 2);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96050, '2014-03-25 12:25:12.753', '2014-03-25 12:21:57.244', 'Firma no necessaria al haver arribat al mínim de firmes requerides', 1, 95901, 'sonespases_anadal', NULL, 4);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96053, '2014-03-25 12:26:07.122', '2014-03-25 12:25:12.831', '', 1, 95900, 'sonespases_dest1', NULL, 2);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (108002, NULL, '2014-05-14 12:03:53.019', '', 1, 107904, 'fundaciobit_anadal', NULL, NULL);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (108003, NULL, '2014-05-14 12:03:53.05', '', 1, 107904, 'fundaciobit_anadal', 80001, NULL);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96451, '2014-03-26 09:42:46.296', '2014-03-26 09:37:03.609', '', 1, 96252, 'sonespases_dest1', NULL, 2);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96452, '2014-03-26 09:44:36.109', '2014-03-26 09:37:03.625', 'Hi ha errors', 1, 96250, 'sonespases_anadal', NULL, 3);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (96450, '2014-03-26 09:44:36.109', '2014-03-26 09:37:03.359', 'La petició ha sigut rebutjada per un altre usuari', 1, 96251, 'sonespases_anadal', NULL, 4);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (75904, '2014-03-24 12:28:21.181', '2013-12-04 10:09:00.554', 'No està bé', 1, 75753, 'fundaciobit_anadal', NULL, 3);
INSERT INTO pfi_estatdefirma (estatdefirmaid, datafi, datainici, descripcio, tipusestatdefirmainicialid, firmaid, usuarientitatid, colaboraciodelegacioid, tipusestatdefirmafinalid) VALUES (75905, '2014-03-24 12:28:21.181', '2013-12-04 10:09:00.695', 'La petició ha sigut rebutjada per un altre usuari', 0, 75753, 'fundaciobit_cola1', 61950, 4);


--
-- TOC entry 2262 (class 0 OID 191429)
-- Dependencies: 176
-- Data for Name: pfi_grupentitat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2264 (class 0 OID 191454)
-- Dependencies: 178
-- Data for Name: pfi_grupentitatusuarientitat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2238 (class 0 OID 69564)
-- Dependencies: 152
-- Data for Name: pfi_metadada; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2239 (class 0 OID 69574)
-- Dependencies: 153
-- Data for Name: pfi_mitjadecomunicacio; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2252 (class 0 OID 69690)
-- Dependencies: 166
-- Data for Name: pfi_tipusnotificacio; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (10, NULL, 'notificacioavis.requerit_per_validar', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (20, NULL, 'notificacioavis.requerit_per_firmar', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (30, NULL, 'notificacioavis.validat', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (40, NULL, 'notificacioavis.invalidat', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (50, NULL, 'notificacioavis.firma_parcial', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (15, NULL, 'notificacioavis.descartat_per_validar', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (25, NULL, 'notificacioavis.descartat_per_firmar', true);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (60, NULL, 'notificacioavis.firma_total', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (70, NULL, 'notificacioavis.rebutjat', NULL);
INSERT INTO pfi_tipusnotificacio (tipusnotificacioid, descripcio, nom, esavis) VALUES (0, NULL, 'notificacioavis.peticio_en_proces', NULL);


--
-- TOC entry 2240 (class 0 OID 69583)
-- Dependencies: 154
-- Data for Name: pfi_notificacio; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2256 (class 0 OID 92610)
-- Dependencies: 170
-- Data for Name: pfi_plantillafluxdefirmes; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_plantillafluxdefirmes (fluxdefirmesid, descripcio, usuarientitatid, compartir, usuariaplicacioid) VALUES (3051, 'Tots els destinataris', 'fundaciobit_anadal', true, NULL);
INSERT INTO pfi_plantillafluxdefirmes (fluxdefirmesid, descripcio, usuarientitatid, compartir, usuariaplicacioid) VALUES (18000, 'Plantilla President i Secretari', 'fundaciobit_anadal', false, NULL);
INSERT INTO pfi_plantillafluxdefirmes (fluxdefirmesid, descripcio, usuarientitatid, compartir, usuariaplicacioid) VALUES (30400, 'Firmes de Secretari i Destinatari i després de President.', 'fundaciobit_soli1', false, NULL);
INSERT INTO pfi_plantillafluxdefirmes (fluxdefirmesid, descripcio, usuarientitatid, compartir, usuariaplicacioid) VALUES (34701, 'Demo', 'fundaciobit_soli1', false, NULL);
INSERT INTO pfi_plantillafluxdefirmes (fluxdefirmesid, descripcio, usuarientitatid, compartir, usuariaplicacioid) VALUES (85900, 'dfyhdfhdfgh', 'fundaciobit_anadal', false, NULL);
INSERT INTO pfi_plantillafluxdefirmes (fluxdefirmesid, descripcio, usuarientitatid, compartir, usuariaplicacioid) VALUES (93050, 'Gerent,  Cap de Radiologia i un ajudant', 'sonespases_soli1', false, NULL);


--
-- TOC entry 2263 (class 0 OID 191434)
-- Dependencies: 177
-- Data for Name: pfi_permisgrupplantilla; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2265 (class 0 OID 191465)
-- Dependencies: 179
-- Data for Name: pfi_permisusuariplantilla; Type: TABLE DATA; Schema: portafib; Owner: portafib
--



--
-- TOC entry 2242 (class 0 OID 69606)
-- Dependencies: 156
-- Data for Name: pfi_peticiofirmausuarientitat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (75853, 'fundaciobit_soli1', NULL, NULL, false);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (75854, 'fundaciobit_soli1', NULL, NULL, false);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (75852, 'fundaciobit_soli1', NULL, NULL, true);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (94350, 'fundaciobit_soli1', NULL, NULL, false);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (96000, 'sonespases_soli1', NULL, NULL, false);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (96350, 'sonespases_soli1', NULL, NULL, true);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (96002, 'sonespases_soli1', NULL, NULL, false);
INSERT INTO pfi_peticiofirmausuarientitat (peticiodefirmaid, usuarientitatid, mitjadecomunicacioid, mitjadecomunicacioadreza, avisweb) VALUES (107951, 'fundaciobit_anadal', NULL, NULL, false);


--
-- TOC entry 2273 (class 0 OID 0)
-- Dependencies: 141
-- Name: pfi_portafib_seq; Type: SEQUENCE SET; Schema: portafib; Owner: portafib
--

SELECT pg_catalog.setval('pfi_portafib_seq', 2168, true);


--
-- TOC entry 2243 (class 0 OID 69611)
-- Dependencies: 157
-- Data for Name: pfi_rebreavis; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_rebreavis (tipusnotificacioid, usuarientitatid, id) VALUES (60, 'fundaciobit_soli1', 92800);
INSERT INTO pfi_rebreavis (tipusnotificacioid, usuarientitatid, id) VALUES (70, 'fundaciobit_soli1', 92801);
INSERT INTO pfi_rebreavis (tipusnotificacioid, usuarientitatid, id) VALUES (20, 'sonespases_dest1', 93600);
INSERT INTO pfi_rebreavis (tipusnotificacioid, usuarientitatid, id) VALUES (60, 'sonespases_soli1', 93601);
INSERT INTO pfi_rebreavis (tipusnotificacioid, usuarientitatid, id) VALUES (70, 'sonespases_soli1', 93602);


--
-- TOC entry 2244 (class 0 OID 69626)
-- Dependencies: 158
-- Data for Name: pfi_role; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_ADMIN', 'Administrador PortaFIB', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_DEST', 'Destinatari', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_DELE', 'Delegat', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_COLA', 'Col·laborador', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_ADEN', 'Administrador d''Entitat', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('ROLE_SOLI', 'Sol·licitant', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('PFI_ADMIN', 'Rol Administrador per Usuaris Aplicació', NULL);
INSERT INTO pfi_role (roleid, nom, descripcio) VALUES ('PFI_USER', 'Rol Bàsic per Usuaris Aplicació', NULL);


--
-- TOC entry 2246 (class 0 OID 69647)
-- Dependencies: 160
-- Data for Name: pfi_roleusuariaplicacio; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_roleusuariaplicacio (usuariaplicacioid, roleid, id) VALUES ('fundaciobit_usrapp', 'PFI_ADMIN', 98663);
INSERT INTO pfi_roleusuariaplicacio (usuariaplicacioid, roleid, id) VALUES ('fundaciobit_usrapp', 'PFI_USER', 98664);
INSERT INTO pfi_roleusuariaplicacio (usuariaplicacioid, roleid, id) VALUES ('anadalapp', 'PFI_USER', 105150);
INSERT INTO pfi_roleusuariaplicacio (usuariaplicacioid, roleid, id) VALUES ('fundaciobithola', 'PFI_ADMIN', 105151);


--
-- TOC entry 2245 (class 0 OID 69638)
-- Dependencies: 159
-- Data for Name: pfi_roleusuarientitat; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_anadal', 73);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'fundaciobit_anadal', 75);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_COLA', 'fundaciobit_anadal', 76);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'fundaciobit_anadal', 77);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'caib_anadal', 79);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_dest1', 5200);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'fundaciobit_dele1', 5400);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_COLA', 'fundaciobit_cola2', 5850);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_COLA', 'fundaciobit_cola1', 5851);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_COLA', 'caib_cola2', 5853);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_dest2', 6650);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'caib_marilen', 11600);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'caib_marilen', 11601);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'fundaciobit_marilen', 11602);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_COLA', 'fundaciobit_marilen', 11604);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'fundaciobit_marilen', 11605);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_marilen', 11606);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'fundaciobit_marilen', 11607);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'fundaciobit_dboerner', 23350);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'fundaciobit_dboerner', 28450);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_secretari', 28451);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_pcomite', 28452);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_president', 28453);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'fundaciobit_soli1', 30300);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'caib_anadal', 31650);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'fundaciobit_anadal', 31651);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'fundaciobit_aden1', 34500);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_dboerner', 35400);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'fundaciobit_provamarilen', 49050);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'caib_aden2', 60150);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'caib_soli2', 60151);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'fundaciobit_president', 61200);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'fundaciobit_dest2', 74000);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_fsalas', 82700);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_coordinador', 85650);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'fundaciobit_des', 85950);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_des', 86100);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_ADEN', 'sonespases_aden3', 92900);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_SOLI', 'sonespases_soli1', 93000);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'sonespases_anadal', 93450);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'sonespases_dest1', 93451);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DELE', 'sonespases_anadal', 93452);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_COLA', 'sonespases_anadal', 93453);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_soli1', 108050);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_cola2', 108051);
INSERT INTO pfi_roleusuarientitat (roleid, usuarientitatid, id) VALUES ('ROLE_DEST', 'fundaciobit_cola1', 108052);


--
-- TOC entry 2248 (class 0 OID 69661)
-- Dependencies: 162
-- Data for Name: pfi_tipusdocumentcoladele; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_tipusdocumentcoladele (colaboraciodelegacioid, tipusdocumentid, id) VALUES (93700, 17, 93750);
INSERT INTO pfi_tipusdocumentcoladele (colaboraciodelegacioid, tipusdocumentid, id) VALUES (93700, 8, 93751);
INSERT INTO pfi_tipusdocumentcoladele (colaboraciodelegacioid, tipusdocumentid, id) VALUES (93700, 14, 93752);


--
-- TOC entry 2260 (class 0 OID 109116)
-- Dependencies: 174
-- Data for Name: pfi_usuarientitatfavorit; Type: TABLE DATA; Schema: portafib; Owner: portafib
--

INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_anadal', 'fundaciobit_cola1', 4902);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_anadal', 'fundaciobit_dest2', 131);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_anadal', 'fundaciobit_cola2', 23800);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_dest1', 'fundaciobit_dele1', 30950);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_dest1', 'fundaciobit_cola1', 30951);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_anadal', 'fundaciobit_soli1', 31450);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_dest1', 'fundaciobit_cola2', 31451);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_provamarilen', 'fundaciobit_provamarilen', 49000);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_anadal', 'fundaciobit_dest1', 50150);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_soli1', 'fundaciobit_dest1', 74551);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_soli1', 'fundaciobit_anadal', 75950);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_convidat', 'fundaciobit_convidat', 80100);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_fsalas', 'fundaciobit_fsalas', 82350);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('fundaciobit_des', 'fundaciobit_des', 85750);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_soli1', 'sonespases_soli1', 92950);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_dest1', 'sonespases_dest1', 92951);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_dele1', 'sonespases_dele1', 92952);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_cola1', 'sonespases_cola1', 92953);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_soli1', 'sonespases_dest1', 92954);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_anadal', 'sonespases_anadal', 92955);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_soli1', 'sonespases_anadal', 92956);
INSERT INTO pfi_usuarientitatfavorit (origenid, favoritid, id) VALUES ('sonespases_dest1', 'sonespases_anadal', 93650);


-- Completed on 2014-05-14 14:22:52

--
-- PostgreSQL database dump complete
--

