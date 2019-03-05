WHENEVER SQLERROR EXIT ROLLBACK;
-- =============================================================
-- 2018/03/13 Modificacions en la Taula Plugins #160
-- =============================================================

ALTER TABLE pfi_plugin ADD codi VARCHAR2(255 CHAR);

ALTER TABLE pfi_plugin ADD ordre NUMBER(10,0);

ALTER TABLE pfi_plugin ADD politicadeus NUMBER(10,0) DEFAULT 0 NOT NULL;
COMMENT ON COLUMN pfi_plugin.politicadeus IS '0 Plantilla, 1 Només entitat, 2 Ho pot usar tothom';

ALTER TABLE pfi_plugin ADD politicamostrarpropietats NUMBER(10,0) DEFAULT 2 NOT NULL;
COMMENT ON COLUMN pfi_plugin.politicamostrarpropietats IS '0 => No mostrar ni propietats administrador ni propietats entitat, 1 => Permetre editar propietats entitat però no mostrar propietats administrador,  2 => Permetre editar propietats entitat i mostrar propietats administrador, 3 => Permetre editar propietats entitat i editar propietats administrador';

-- =============================================================
-- 2018/03/13 Modificacions en la Taula PropietatGlobal #118
-- =============================================================

ALTER TABLE pfi_propietatglobal MODIFY descripcio VARCHAR2(1000 CHAR);

-- =============================================================
-- 2018/03/14 Tipus Base pel TipusDocumentals #161
-- =============================================================

ALTER TABLE pfi_tipusdocument ADD tipusdocumentbaseid NUMBER(19,0) DEFAULT 99 NOT NULL;
COMMENT ON COLUMN pfi_tipusdocument.tipusdocumentbaseid IS 'Associació tipus estandard 	1 al 99) definits a les NTI';

-- =============================================================
-- 2018/03/14 Motiu de la firma a nivell Firma #163
-- =============================================================

ALTER TABLE pfi_firma ADD motiu VARCHAR2(255 CHAR);

-- =============================================================
-- 2018/03/14 Nous camps a PeticioDeFirma #164
-- =============================================================

ALTER TABLE pfi_peticiodefirma ADD tipusoperaciofirma NUMBER(10,0) DEFAULT 0 NOT NULL;
COMMENT ON COLUMN pfi_peticiodefirma.tipusoperaciofirma IS '0: firma,1: cofirma, 2: contrafirma.';

ALTER TABLE pfi_peticiodefirma ADD expedientcodi VARCHAR2(255 CHAR);
ALTER TABLE pfi_peticiodefirma ADD expedientnom VARCHAR2(255 CHAR);
ALTER TABLE pfi_peticiodefirma ADD expedienturl VARCHAR2(255 CHAR);

ALTER TABLE pfi_peticiodefirma ADD procedimentcodi VARCHAR2(255 CHAR);
ALTER TABLE pfi_peticiodefirma ADD procedimentnom VARCHAR2(255 CHAR);

ALTER TABLE pfi_peticiodefirma ADD informacioaddicionalavaluable DOUBLE PRECISION;

ALTER TABLE pfi_peticiodefirma ADD firmaoriginaldetachedid NUMBER(19,0);
ALTER TABLE pfi_peticiodefirma ADD CONSTRAINT pfi_petifirma_fitxer_ori_fk FOREIGN KEY (firmaoriginaldetachedid) REFERENCES pfi_fitxer (fitxerid);
COMMENT ON COLUMN pfi_peticiodefirma.firmaoriginaldetachedid IS 'Camp de tipus fitxer que conté la firma en casos de cofirmes i contrafirmes detached de tipus CAdEs i XAdES';
create index pfi_petifirma_firmaori_fk_i on pfi_peticiodefirma(firmaoriginaldetachedid);

-- ===========================================
-- 2018/03/15 Política de Custòdia #165
-- ===========================================

ALTER TABLE pfi_entitat ADD politicacustodia NUMBER(10,0) DEFAULT 0 NOT NULL;
COMMENT ON COLUMN pfi_entitat.politicacustodia IS '0: No permetre, 1:Només Plantilles de l''Entitat 	No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat 	Per defecte Actiu), 4: Opcional plantilla Entitat 	Per defecte NO Actiu), 5: Llibertat Total 	selecció, edició i us)';

ALTER TABLE pfi_usuarientitat ADD politicacustodia NUMBER(10,0) DEFAULT 0 NOT NULL;
COMMENT ON COLUMN pfi_usuarientitat.politicacustodia IS '-1: el que digui l''entitat, 0: No permetre, 1:Només Plantilles de l''''Entitat 	No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat 	Per defecte Actiu), 4: Opcional plantilla Entitat 	Per defecte NO Actiu), 5: Llibertat Total 	selecció, edició i us)';

-- ===========================================
-- 2018/03/27 Política de Taula de Firmes #166
-- ===========================================

ALTER TABLE pfi_entitat ADD propietatstaulafirmes CLOB;

ALTER TABLE pfi_entitat ADD politicataulafirmes NUMBER(10,0) DEFAULT 2 NOT NULL;
COMMENT ON COLUMN pfi_entitat.politicataulafirmes IS '0 no es permet taules de firmes, 1 definit en l''entitat, 2 opcional per defecte el definit a l''entitat, 3 opcional per defecte sense taula de firmes';

ALTER TABLE pfi_entitat ADD posiciotaulafirmes NUMBER(10,0) DEFAULT 1 NOT NULL;
COMMENT ON COLUMN pfi_entitat.posiciotaulafirmes IS 'SENSETAULA = 0; PRIMERAPAGINA = 1; DARRERAPAGINA = -1; DEFINIT_EN_FIRMA	RUBRICA)=2';

DROP INDEX pfi_petifirma_postaulaid_fk_i;

ALTER TABLE pfi_peticiodefirma DROP CONSTRAINT pfi_petifirma_postaufir_fk;

DROP TABLE pfi_posiciotaulafirmes;

-- ===========================================
-- 2018/04/10 Estadístiques #168
-- ===========================================

CREATE TABLE pfi_estadistica (
   estadisticaid NUMBER(19,0) NOT NULL, 
   tipus NUMBER(10,0) NOT NULL,
   usuariaplicacioid VARCHAR2(101 CHAR),
   usuarientitatid VARCHAR2(101 CHAR),
   data TIMESTAMP NOT NULL, 
   entitatid VARCHAR2(50 CHAR),
   valor DOUBLE PRECISION, 
   parametres VARCHAR2(3000 CHAR), 
   CONSTRAINT pfi_estadistica_pk PRIMARY KEY (estadisticaid),
   CONSTRAINT pfi_estadis_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid)
);

COMMENT ON COLUMN pfi_estadistica.tipus IS 'Ha de ser combobox';
COMMENT ON COLUMN pfi_estadistica.usuariaplicacioid IS 'No te la clau forània amb pfi_usuariaplicacio ja que si s''esborra l''usuari aplicació, haurien de quedar les estadistiques.';

--create index pfi_estadistica_pk_i on pfi_estadistica (estadisticaid);
create index pfi_estadistica_entitatid_fk_i on pfi_estadistica (entitatid);

-- =================================================
-- 2018/04/10 (& 2018/09/13) Revisor de Firma #169
-- =================================================

ALTER TABLE pfi_firma ADD minimderevisors NUMBER(10,0) DEFAULT 0 NOT NULL;

CREATE TABLE pfi_revisordefirma (
   revisordefirmaid NUMBER(19,0) NOT NULL, 
   usuarientitatid VARCHAR2(101 CHAR) NOT NULL,   
   firmaid NUMBER(19,0) NOT NULL,
   obligatori NUMBER(1,0) NOT NULL, 
   CONSTRAINT pfi_revisordefirma_pk PRIMARY KEY (revisordefirmaid), 
   CONSTRAINT pfi_revfirma_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat (usuarientitatid),
   CONSTRAINT pfi_revfirma_firma_fk FOREIGN KEY (firmaid) REFERENCES pfi_firma (firmaid)
);

--create index pfi_revisordefirma_pk_i on pfi_revisordefirma (revisordefirmaid);
create index pfi_revfirma_usrentitat_fk_i on pfi_revisordefirma (usuarientitatid);
create index pfi_revfirma_firmaid_fk_i on pfi_revisordefirma (firmaid);

-- ===============================================================
-- 2018/04/11 Incorporar en Entitat camps per validar Firmes #171
-- ===============================================================

ALTER TABLE pfi_entitat ADD pluginvalidafirmesid NUMBER(19,0);
ALTER TABLE pfi_entitat ADD CONSTRAINT pfi_entitat_plugin_vafi_fk FOREIGN KEY (pluginvalidafirmesid) REFERENCES pfi_plugin (pluginid);
create index pfi_entitat_pluginvalfir_fk_i on pfi_entitat (pluginvalidafirmesid);

ALTER TABLE pfi_entitat ADD pluginvalidacertificatid NUMBER(19,0);
ALTER TABLE pfi_entitat ADD CONSTRAINT pfi_entitat_plugin_cert_fk FOREIGN KEY (pluginvalidacertificatid) REFERENCES pfi_plugin (pluginid);
create index pfi_entitat_pluginvalcer_fk_i on pfi_entitat 	(pluginvalidacertificatid);

ALTER TABLE pfi_entitat ADD checkcanviatdocfirmat NUMBER(1,0) DEFAULT 1 NOT NULL;

-- ===========================================
-- 2018/04/11 Plugin de Rubriques #172
-- ===========================================

ALTER TABLE pfi_entitat ADD pluginrubricaid NUMBER(19,0);
ALTER TABLE pfi_entitat ADD CONSTRAINT pfi_entitat_plugin_rubr_fk FOREIGN KEY (pluginrubricaid) REFERENCES pfi_plugin (pluginid);

create index pfi_entitat_pluginrubri_fk_i on pfi_entitat 	(pluginrubricaid);

-- ======================================================================
-- 2018/04/12 Configuració de Firma de UsuariApp 	API Firma Simple) #148
-- ======================================================================

CREATE TABLE pfi_usuariaplicacioconfig (
  usuariaplicacioconfigid NUMBER(19,0) NOT NULL,
  usuariaplicacioid VARCHAR2(101 CHAR) NOT NULL,
  uspoliticadefirma NUMBER(10,0) DEFAULT 0 NOT NULL,
  policyidentifier VARCHAR2(100 CHAR),
  policyidentifierhash VARCHAR2(256 CHAR),
  policyidentifierhashalgorithm VARCHAR2(50 CHAR),
  policyurldocument VARCHAR2(255 CHAR),
  filtrecertificats CLOB,
  tipusoperaciofirma NUMBER(10,0) DEFAULT 0 NOT NULL, -- 0 firma, 1 contrafirma 2, cofirma
  tipusfirmaid NUMBER(10,0) NOT NULL,
  algorismedefirmaid NUMBER(10,0),
  modedefirma NUMBER(1,0) NOT NULL,
  motiudelegacioid NUMBER(19,0),
  firmatperformatid NUMBER(19,0),
  politicacustodia NUMBER(10,0) DEFAULT 0 NOT NULL,
  custodiainfoid NUMBER(19,0),
  politicataulafirmes NUMBER(10,0) DEFAULT 0 NOT NULL,
  posiciotaulafirmesid NUMBER(10,0) DEFAULT 0 NOT NULL,
  propietatstaulafirmes CLOB,
  politicasegellatdetemps NUMBER(10,0) DEFAULT 0 NOT NULL,
  pluginsegellatid NUMBER(19,0),
  comprovarniffirma NUMBER(1,0), -- Null => Valor definit a l'entitat
  checkcanviatdocfirmat NUMBER(1,0), -- Null => Valor definit a l'entitat
  validarfirma NUMBER(1,0), -- Indica si validar la firma amb el Plugin de validació definit a l'entitat
  validarcertificat NUMBER(1,0), -- Null => Valor definit a l'entitat
  pluginfirmaservidorid NUMBER(19,0),
  upgradesignformat NUMBER(10,0),
  htmlperllistarpluginsfirmaweb CLOB,
  logincertificateid NUMBER(19,0),

  CONSTRAINT pfi_usuariaplicacioconfig_pk PRIMARY KEY (usuariaplicacioconfigid),
  CONSTRAINT pfi_confapp_algofirma_fk FOREIGN KEY (algorismedefirmaid) REFERENCES pfi_algorismedefirma (algorismedefirmaid),
  CONSTRAINT pfi_confapp_custodia_fk FOREIGN KEY (custodiainfoid) REFERENCES pfi_custodiainfo (custodiainfoid),
  CONSTRAINT pfi_confapp_fitxer_cert_fk FOREIGN KEY (logincertificateid) REFERENCES pfi_fitxer (fitxerid),
  CONSTRAINT pfi_confapp_plugin_fsrv_fk FOREIGN KEY (pluginfirmaservidorid) REFERENCES pfi_plugin (pluginid),
  CONSTRAINT pfi_confapp_plugin_seg_fk FOREIGN KEY (pluginsegellatid) REFERENCES pfi_plugin (pluginid),
  CONSTRAINT pfi_confapp_tipusfirma_fk FOREIGN KEY (tipusfirmaid) REFERENCES pfi_tipusfirma (tipusfirmaid),
  CONSTRAINT pfi_confapp_traduccio_firm_fk FOREIGN KEY (firmatperformatid) REFERENCES pfi_traduccio (traduccioid),
  CONSTRAINT pfi_confapp_traduccio_moti_fk FOREIGN KEY (motiudelegacioid) REFERENCES pfi_traduccio (traduccioid),
  CONSTRAINT pfi_confapp_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio (usuariaplicacioid),
  CONSTRAINT pfi_confapp_usrapp_uk UNIQUE (usuariaplicacioid)
);

COMMENT ON COLUMN pfi_usuariaplicacioconfig.politicataulafirmes IS '-1 definit en l''entitat, 0 no es permet taules de firmes, 1  obligatori politica definida en la configuració d''usuari aplicació o entitat, 2 opcional, per defecte el definit a l''entitat';
COMMENT ON COLUMN pfi_usuariaplicacioconfig.politicasegellatdetemps IS 'DEFINIT_EN_ENTITAT=-1;NOUSAR=0;US_OBLIGATORI=1;USUARI_ELEGEIX_PER_DEFECTE_SI=2;USUARI_ELEGEIX_PER_DEFECTE_NO=3;';
COMMENT ON COLUMN pfi_usuariaplicacioconfig.politicacustodia IS '-1: el que digui l''entitat, 0: No permetre, 1: Només Plantilles de l''''Entitat 	No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat, 4: Opcional plantilla Entitat, 5: Llibertat Total 	selecció, edició i us), 6: Custòdia de la Configuració de usuariAplicacio';
COMMENT ON COLUMN pfi_usuariaplicacioconfig.validarcertificat IS 'NULL => Lo que digui l''entitat';
COMMENT ON COLUMN pfi_usuariaplicacioconfig.uspoliticadefirma IS '-1=> usar politica de firma de l''entitat, 0 => no usar politica de firma,  1=> usar politica d''aquesta configuracio, 2 => L''usuari web o usuari-app elegeixen la politica de firma';
COMMENT ON COLUMN pfi_usuariaplicacioconfig.tipusoperaciofirma IS '0 firma, 1 contrafirma 2, cofirma';
COMMENT ON COLUMN pfi_usuariaplicacioconfig.comprovarniffirma IS 'Null => Valor definit a l''entitat';
COMMENT ON COLUMN pfi_usuariaplicacioconfig.checkcanviatdocfirmat IS '-- Null => Valor definit a l''entitat';
COMMENT ON COLUMN pfi_usuariaplicacioconfig.validarfirma IS 'Indica si validar la firma amb el Plugin de validació definit a l''entitat';


-- XYZ ZZZ*** Comentat perquè ha donat error, supose per la "CONSTRAINT pfi_confapp_usrapp_uk UNIQUE (usuariaplicacioid)"
create index pfi_usuariaplicacioconfig_pk_i on pfi_usuariaplicacioconfig 	(usuariaplicacioconfigid);
create index pfi_confapp_usuappid_fk_i on pfi_usuariaplicacioconfig 	(usuariaplicacioid);


 create index pfi_confapp_tipusfirma_fk_i on pfi_usuariaplicacioconfig 	(tipusfirmaid);
 create index pfi_confapp_algofirma_fk_i on pfi_usuariaplicacioconfig 	(algorismedefirmaid);
 create index pfi_confapp_motiudele_fk_i on pfi_usuariaplicacioconfig 	(motiudelegacioid);
 create index pfi_confapp_firmatper_fk_i on pfi_usuariaplicacioconfig 	(firmatperformatid);
 create index pfi_confapp_custinfo_fk_i on pfi_usuariaplicacioconfig 	(custodiainfoid);
 create index pfi_confapp_plugsegell_fk_i on pfi_usuariaplicacioconfig 	(pluginsegellatid);
 create index pfi_confapp_firmaserv_fk_i on pfi_usuariaplicacioconfig 	(pluginfirmaservidorid);
 create index pfi_confapp_logincert_fk_i on pfi_usuariaplicacioconfig 	(logincertificateid);

-- ======================================================================
-- 2018/04/13 Afegir política de plugins de firma web #173
-- ======================================================================

ALTER TABLE pfi_usuariaplicacio ADD politicadepluginfirmaweb NUMBER(10,0) DEFAULT 0 NOT NULL;
COMMENT ON COLUMN pfi_usuariaplicacio.politicadepluginfirmaweb IS '0 - Només plugins de l''entitat, 1 - Plugins de l''entitat més plugins addicionals 	afegir o llevar), 2 - Només plugins addicionals 	Només els que tenguin marcat afegir)';

ALTER TABLE pfi_usuarientitat  ADD politicadepluginfirmaweb NUMBER(10,0) DEFAULT 0 NOT NULL;
COMMENT ON COLUMN pfi_usuarientitat.politicadepluginfirmaweb IS ' 0 - Només plugins de l''''entitat, 1 - Plugins de l''''entitat més plugins addicionals 	afegir o llevar), 2 - Només plugins addicionals 	Només els que tenguin marcat afegir)''';

CREATE TABLE pfi_pluginfirmawebperusrent (
	
   pluginfirmawebperusrentid NUMBER(19,0) NOT NULL,
   usuarientitatid VARCHAR2(101 CHAR) NOT NULL, 
   pluginfirmawebid NUMBER(19,0) NOT NULL, 
   accio NUMBER(10,0) DEFAULT 1 NOT NULL,
   CONSTRAINT pfi_pluginfirmawebperusrent_pk PRIMARY KEY (pluginfirmawebperusrentid), 
   CONSTRAINT pfi_pfwpue_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat (usuarientitatid), 
   CONSTRAINT pfi_pfwpue_plugin_fk FOREIGN KEY (pluginfirmawebid) REFERENCES pfi_plugin	(pluginid), 
   CONSTRAINT pfi_pfwpue_usuent_plug_uk UNIQUE (usuarientitatid, pluginfirmawebid)
);

COMMENT ON COLUMN pfi_pluginfirmawebperusrent.accio IS 'Valors:  -1 eliminar, 1 afegir';

--
create index pfi_pfwpue_pk_i on pfi_pluginfirmawebperusrent 	(pluginfirmawebperusrentid);
create index pfi_pfwpue_usrentid_fk_i on pfi_pluginfirmawebperusrent 	(usuarientitatid);
create index pfi_pfwpue_plugin_fk_i on pfi_pluginfirmawebperusrent 	(pluginfirmawebid);


CREATE TABLE pfi_pluginfirmawebperusrapp (
	
   pluginfirmawebperusrappid NUMBER(19,0) NOT NULL,
   usuariaplicacioid VARCHAR2(101 CHAR) NOT NULL, 
   pluginfirmawebid NUMBER(19,0) NOT NULL, 
   accio NUMBER(10,0) DEFAULT 1 NOT NULL, 
   CONSTRAINT pfi_pluginfirmawebperusrapp_pk PRIMARY KEY (pluginfirmawebperusrappid), 
   CONSTRAINT pfi_pfwpua_usrapp_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio(usuariaplicacioid), 
   CONSTRAINT pfi_pfwpua_plugin_fk FOREIGN KEY (pluginfirmawebid) REFERENCES pfi_plugin (pluginid), 
   CONSTRAINT pfi_pfwpua_usuapp_plug_uk UNIQUE (usuariaplicacioid, pluginfirmawebid)
);
COMMENT ON COLUMN pfi_pluginfirmawebperusrapp.accio IS 'Valors:  -1 eliminar, 1 afegir';

--
create index pfi_pfwpua_pk_i on pfi_pluginfirmawebperusrapp 	(pluginfirmawebperusrappid);
create index pfi_pfwpua_usrappid_fk_i on pfi_pluginfirmawebperusrapp 	(usuariaplicacioid);
create index pfi_pfwpua_plugin_fk_i on pfi_pluginfirmawebperusrapp 	(pluginfirmawebid);

-- ======================================================================
-- 2018/04/16 Taula de Cridades a Plugins 	(bitàcola de cridades) #170
-- ======================================================================

CREATE TABLE pfi_plugincridada (
	
   plugincridadaid NUMBER(19,0) NOT NULL, 
   entitatid VARCHAR2(50 CHAR), 
   data TIMESTAMP WITH TIME ZONE NOT NULL, 
   pluginid NUMBER(19,0) NOT NULL,
   metodeplugin VARCHAR2(100 CHAR) NOT NULL, 
   parametrestext CLOB,
   parametresfitxerid NUMBER(19,0),
   retorntext CLOB,
   retornfitxerid NUMBER(19,0),
   tipusresultat NUMBER(10,0) NOT NULL, 
   tempsexecucio NUMBER(19,0), 
   CONSTRAINT pfi_plugcrida_fitxer_retor_fk FOREIGN KEY (retornfitxerid) REFERENCES pfi_fitxer (fitxerid),
   CONSTRAINT pfi_plugcrida_fitxer_param_fk FOREIGN KEY (parametresfitxerid) REFERENCES pfi_fitxer (fitxerid),
   CONSTRAINT pfi_plugcrida_plugin_fk FOREIGN KEY (pluginid) REFERENCES pfi_plugin (pluginid),
   CONSTRAINT pfi_plugincridada_pk PRIMARY KEY (plugincridadaid), 
   CONSTRAINT pfi_plugcrida_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat (entitatid)
);
COMMENT ON COLUMN pfi_plugincridada.tipusresultat IS '0 => error, 1 => ok';
COMMENT ON COLUMN pfi_plugincridada.retorntext IS 'conte error si falla i dades resultat si va bé.';
COMMENT ON COLUMN pfi_plugincridada.tempsexecucio IS 'milisegons execucio';

--
create index pfi_plugincridada_pk_i on pfi_plugincridada 	(plugincridadaid);
create index pfi_plugcrida_entitatid_fk_i on pfi_plugincridada 	(entitatid);
create index pfi_plugcrida_pluginid_fk_i on pfi_plugincridada (pluginid);
create index pfi_plugcrida_paramfitxer_fk_i on pfi_plugincridada (parametresfitxerid);
create index pfi_plugcrida_retorfitxer_fk_i on pfi_plugincridada (retornfitxerid);

-- ======================================================================
-- 2018/05/01 Configuració de Firma de UsuariApp 	Us política de Firma) #148
-- ======================================================================
 
 ALTER TABLE pfi_entitat ADD uspoliticadefirma NUMBER(10,0) DEFAULT 0 NOT NULL;
 COMMENT ON COLUMN pfi_entitat.uspoliticadefirma IS '-1=> usar politica de firma de l''entitat, 0 => no usar politica de firma,  1=> usar politica d''aquesta configuracio, 2 => L''usuari web o usuari-app elegeixen la politica de firma';

-- ======================================================================
-- 2018/11/08 Neteja de taules de BBDD #199
-- ======================================================================

ALTER TABLE pfi_usuariaplicacioconfig DROP CONSTRAINT pfi_confapp_tipusfirma_fk;
ALTER TABLE pfi_peticiodefirma DROP CONSTRAINT pfi_petifirma_tipusfirma_fk;
DROP TABLE pfi_tipusfirma;

ALTER TABLE pfi_metadada DROP CONSTRAINT pfi_metadada_tipmetada_fk;
DROP TABLE pfi_tipusmetadada;

ALTER TABLE pfi_peticiodefirma DROP CONSTRAINT pfi_petifirma_prioritat_fk;
DROP TABLE pfi_prioritat;

ALTER TABLE pfi_peticiodefirma DROP CONSTRAINT pfi_petifirma_algofirma_fk;
ALTER TABLE pfi_entitat DROP CONSTRAINT pfi_entitat_algofirma_fk;
ALTER TABLE pfi_usuariaplicacioconfig DROP CONSTRAINT pfi_confapp_algofirma_fk;
DROP TABLE pfi_algorismedefirma;

ALTER TABLE pfi_custodiainfo DROP CONSTRAINT pfi_custodia_pospagina_bar_fk;
ALTER TABLE pfi_custodiainfo DROP CONSTRAINT pfi_custodia_pospagina_msg_fk;
DROP TABLE pfi_posiciopagina;

ALTER TABLE pfi_peticiodefirma DROP CONSTRAINT pfi_petifirma_estpetfirm_fk;
DROP TABLE pfi_tipusestatpeticiodefirma;

ALTER TABLE pfi_estatdefirma DROP CONSTRAINT pfi_estatfirma_estfirmini_fk;
DROP TABLE pfi_tipusestatdefirmainicial;

ALTER TABLE pfi_firma DROP CONSTRAINT pfi_firma_estfirmafi_fk;
ALTER TABLE pfi_estatdefirma DROP CONSTRAINT pfi_estatfirma_estfirmafi_fk;
DROP TABLE pfi_tipusestatdefirmafinal;


-- ======================================================================
-- 2019/02/08  Perfils i Configuracions per l'API de Firma Simple #235 
-- ======================================================================

ALTER TABLE pfi_usuariaplicacioconfig  DROP CONSTRAINT pfi_confapp_usrapp_fk;
ALTER TABLE pfi_usuariaplicacioconfig  DROP CONSTRAINT pfi_confapp_usrapp_uk;

ALTER TABLE pfi_usuariaplicacioconfig  DROP COLUMN usuariaplicacioid;

ALTER TABLE pfi_usuariaplicacioconfig
  ADD COLUMN usenfirmaapisimpleservidor NUMBER(1,0) NOT NULL DEFAULT false;
ALTER TABLE pfi_usuariaplicacioconfig
  ADD COLUMN usenfirmaapisimpleweb NUMBER(1,0) NOT NULL DEFAULT false;
ALTER TABLE pfi_usuariaplicacioconfig
  ADD COLUMN usenfirmaweb NUMBER(1,0) NOT NULL DEFAULT false;
ALTER TABLE pfi_usuariaplicacioconfig
  ADD COLUMN usenfirmaws2 NUMBER(1,0) NOT NULL DEFAULT false;
ALTER TABLE pfi_usuariaplicacioconfig
  ADD COLUMN usenfirmapassarelaservidor NUMBER(1,0) NOT NULL DEFAULT false;
ALTER TABLE pfi_usuariaplicacioconfig
  ADD COLUMN usenfirmapassarelaweb NUMBER(1,0) NOT NULL DEFAULT false;
  
  
ALTER TABLE pfi_usuariaplicacioconfig  ADD COLUMN nom VARCHAR2(255 CHAR);

UPDATE pfi_usuariaplicacioconfig SET nom='Configuracio amb ID' || usuariaplicacioconfigid;

ALTER TABLE pfi_usuariaplicacioconfig  ALTER COLUMN nom SET NOT NULL;

ALTER TABLE pfi_usuariaplicacioconfig ADD COLUMN entitatid VARCHAR2(50 CHAR) NOT NULL;

ALTER TABLE pfi_usuariaplicacioconfig
  ADD CONSTRAINT pfi_confapp_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat (entitatid) ON UPDATE NO ACTION ON DELETE NO ACTION;
  
create index pfi_confapp_entitatid_fk_i on pfi_usuariaplicacioconfig (entitatid);


CREATE TABLE portafib.pfi_usuariaplicacioperfil
(
   usuariaplicacioperfilid NUMBER(19,0) NOT NULL DEFAULT nextval('pfi_portafib_seq'), 
   nom VARCHAR2(255 CHAR) NOT NULL,
   codi VARCHAR2(100 CHAR) NOT NULL,
   descripcio VARCHAR2(500 CHAR), 
   condicio VARCHAR2(4000 CHAR), 
   usrappconfiguracio1id NUMBER(19,0) NOT NULL, 
   usrappconfiguracio2id NUMBER(19,0), 
   usrappconfiguracio3id NUMBER(19,0), 
   CONSTRAINT pfi_usuariaplicacioperfil_pk PRIMARY KEY (usuariaplicacioperfilid), 
   CONSTRAINT pfi_perfilapp_confapp_1_fk FOREIGN KEY (usrappconfiguracio1id) REFERENCES pfi_usuariaplicacioconfig (usuariaplicacioconfigid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT pfi_perfilapp_confapp_2_fk FOREIGN KEY (usrappconfiguracio2id) REFERENCES pfi_usuariaplicacioconfig (usuariaplicacioconfigid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT pfi_perfilapp_confapp_3_fk FOREIGN KEY (usrappconfiguracio3id) REFERENCES pfi_usuariaplicacioconfig (usuariaplicacioconfigid) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT pfi_perfilapp_codi_uk UNIQUE (codi)
);

create index pfi_usuariaplicacioperfil_pk_i on pfi_usuariaplicacioperfil (usuariaplicacioperfilid);
create index pfi_perfilapp_appconf1id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio1id);
create index pfi_perfilapp_appconf2id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio2id);
create index pfi_perfilapp_appconf3id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio3id);


CREATE TABLE portafib.pfi_perfilsperusrapp
(
   perfilsperusrappid NUMBER(19,0) NOT NULL DEFAULT nextval('pfi_portafib_seq'), 
   usuariaplicacioperfilid NUMBER(19,0) NOT NULL, 
   usuariaplicacioid VARCHAR2(50 CHAR)  NOT NULL, 
   CONSTRAINT pfi_perfilsperusrapp_pk PRIMARY KEY (perfilsperusrappid), 
   CONSTRAINT pfi_perfilsua_perfilapp_p_fk FOREIGN KEY (usuariaplicacioperfilid) REFERENCES pfi_usuariaplicacioperfil (usuariaplicacioperfilid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT pfi_perfilsua_usrapp_usr_fk FOREIGN KEY (usuariaplicacioid) REFERENCES pfi_usuariaplicacio (usuariaplicacioid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT pfi_perfilsua_uaid_perf_uk UNIQUE (usuariaplicacioperfilid, usuariaplicacioid)
);


create index pfi_perfilsperusrapp_pk_i on pfi_perfilsperusrapp (perfilsperusrappid);
create index pfi_perfilsua_perfilid_fk_i on pfi_perfilsperusrapp (usuariaplicacioperfilid);
create index pfi_perfilsua_usuappid_fk_i on pfi_perfilsperusrapp (usuariaplicacioid);
