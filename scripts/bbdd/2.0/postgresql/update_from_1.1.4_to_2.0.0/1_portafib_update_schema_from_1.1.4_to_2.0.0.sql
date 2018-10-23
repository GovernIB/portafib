
-- =============================================================
-- 2018/03/13 Modificacions en la Taula Plugins #160
-- =============================================================

ALTER TABLE pfi_plugin ADD COLUMN codi character varying (255);

ALTER TABLE pfi_plugin ADD COLUMN ordre integer;

ALTER TABLE pfi_plugin ADD COLUMN politicadeus integer NOT NULL DEFAULT 0;
COMMENT ON COLUMN pfi_plugin.politicadeus IS '0 Plantilla, 1 Només entitat, 2 Ho pot usar tothom';

ALTER TABLE pfi_plugin ADD COLUMN politicamostrarpropietats integer NOT NULL DEFAULT 2;
COMMENT ON COLUMN pfi_plugin.politicamostrarpropietats IS '0 => No mostrar ni propietats administrador ni propietats entitat, 1 => Permetre editar propietats entitat però no mostrar propietats administrador,  2 => Permetre editar propietats entitat i mostrar propietats administrador, 3 => Permetre editar propietats entitat i editar propietats administrador';


-- =============================================================
-- 2018/03/13 Modificacions en la Taula PropietatGlobal #118
-- =============================================================

ALTER TABLE pfi_propietatglobal ALTER COLUMN descripcio TYPE character varying (1000);

-- =============================================================
-- 2018/03/14 Tipus Base pel TipusDocumentals #161
-- =============================================================

ALTER TABLE pfi_tipusdocument ADD COLUMN tipusdocumentbaseid bigint NOT NULL DEFAULT 99;
COMMENT ON COLUMN pfi_tipusdocument.tipusdocumentbaseid IS 'Associació tipus estandard 	1 al 99) definits a les NTI';

-- =============================================================
-- 2018/03/14 Motiu de la firma a nivell Firma #163
-- =============================================================

ALTER TABLE pfi_firma ADD COLUMN motiu character varying (255);

-- =============================================================
-- 2018/03/14 Nous camps a PeticioDeFirma #164
-- =============================================================

ALTER TABLE pfi_peticiodefirma ADD COLUMN tipusoperaciofirma integer NOT NULL DEFAULT 0;
COMMENT ON COLUMN pfi_peticiodefirma.tipusoperaciofirma IS '0: firma,1: cofirma, 2: contrafirma.';

ALTER TABLE pfi_peticiodefirma ADD COLUMN expedientcodi character varying (255);
ALTER TABLE pfi_peticiodefirma ADD COLUMN expedientnom character varying (255);
ALTER TABLE pfi_peticiodefirma ADD COLUMN expedienturl character varying (255);

ALTER TABLE pfi_peticiodefirma ADD COLUMN procedimentcodi character varying (255);
ALTER TABLE pfi_peticiodefirma ADD COLUMN procedimentnom character varying (255);

ALTER TABLE pfi_peticiodefirma ADD COLUMN informacioaddicionalavaluable double precision;

ALTER TABLE pfi_peticiodefirma ADD COLUMN firmaoriginaldetachedid bigint;
ALTER TABLE pfi_peticiodefirma ADD CONSTRAINT pfi_petifirma_fitxer_ori_fk FOREIGN KEY firmaoriginaldetachedid REFERENCES  pfi_fitxer fitxerid ON UPDATE NO ACTION ON DELETE NO ACTION;
COMMENT ON COLUMN pfi_peticiodefirma.firmaoriginaldetachedid IS 'Camp de tipus fitxer que conté la firma en casos de cofirmes i contrafirmes detached de tipus CAdEs i XAdES';
create index pfi_petifirma_firmaori_fk_i on pfi_peticiodefirma 	firmaoriginaldetachedid;

-- ===========================================
-- 2018/03/15 Política de Custòdia #165
-- ===========================================

ALTER TABLE pfi_entitat
  ADD COLUMN politicacustodia integer NOT NULL DEFAULT 0;
COMMENT ON COLUMN pfi_entitat.politicacustodia IS '0: No permetre, 1:Només Plantilles de l''Entitat 	No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat 	Per defecte Actiu), 4: Opcional plantilla Entitat 	Per defecte NO Actiu), 5: Llibertat Total 	selecció, edició i us)';

ALTER TABLE pfi_usuarientitat ADD COLUMN politicacustodia integer NOT NULL DEFAULT 0;
COMMENT ON COLUMN pfi_usuarientitat.politicacustodia IS '-1: el que digui l''entitat, 0: No permetre, 1:Només Plantilles de l''''Entitat 	No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat 	Per defecte Actiu), 4: Opcional plantilla Entitat 	Per defecte NO Actiu), 5: Llibertat Total 	selecció, edició i us)';



-- ===========================================
-- 2018/03/27 Política de Taula de Firmes #166
-- ===========================================

ALTER TABLE pfi_entitat ADD COLUMN propietatstaulafirmes text;

ALTER TABLE pfi_entitat ADD COLUMN politicataulafirmes integer NOT NULL DEFAULT 2;
COMMENT ON COLUMN pfi_entitat.politicataulafirmes IS '0 no es permet taules de firmes, 1 definit en l''entitat, 2 opcional per defecte el definit a l''entitat, 3 opcional per defecte sense taula de firmes';

ALTER TABLE pfi_entitat ADD COLUMN posiciotaulafirmes integer NOT NULL DEFAULT 1;
COMMENT ON COLUMN pfi_entitat.posiciotaulafirmes IS 'SENSETAULA = 0; PRIMERAPAGINA = 1; DARRERAPAGINA = -1; DEFINIT_EN_FIRMA	RUBRICA)=2';

DROP INDEX pfi_petifirma_postaulaid_fk_i;

ALTER TABLE pfi_peticiodefirma DROP CONSTRAINT pfi_petifirma_postaufir_fk;

DROP TABLE pfi_posiciotaulafirmes;




-- ===========================================
-- 2018/04/10 Estadístiques #168
-- ===========================================

CREATE TABLE pfi_estadistica (
   estadisticaid bigint NOT NULL DEFAULT nextval 'pfi_portafib_seq', 
   tipus integer NOT NULL,
   usuariaplicacioid character varying(101),
   usuarientitatid character varying(101),
   data timestamp without time zone NOT NULL, 
   entitatid character varying (50),
   valor double precision, 
   parametres character varying	(3000), 
   CONSTRAINT pfi_estadistica_pk PRIMARY KEY 	estadisticaid,
   CONSTRAINT pfi_estadis_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid) ON UPDATE NO ACTION ON DELETE NO ACTION
);

COMMENT ON COLUMN pfi_estadistica.tipus IS 'Ha de ser combobox';

COMMENT ON COLUMN pfi_estadistica.usuariaplicacioid IS 'No te la clau forània amb pfi_usuariaplicacio ja que si s''esborra l''usuari aplicació, haurien de quedar les estadistiques.';

create index pfi_estadistica_pk_i on pfi_estadistica 	(estadisticaid);

create index pfi_estadistica_entitatid_fk_i on pfi_estadistica 	(entitatid);


-- =================================================
-- 2018/04/10 (& 2018/09/13) Revisor de Firma #169
-- =================================================

ALTER TABLE pfi_firma
  ADD COLUMN minimderevisors integer NOT NULL DEFAULT 0;


CREATE TABLE pfi_revisordefirma (
   revisordefirmaid bigint NOT NULL DEFAULT nextval 'pfi_portafib_seq', 
   usuarientitatid character varying(101) NOT NULL,   
   firmaid bigint NOT NULL,
   obligatori boolean NOT NULL, 
   CONSTRAINT pfi_revisordefirma_pk PRIMARY KEY (revisordefirmaid), 
   CONSTRAINT pfi_revfirma_usrentitat_fk FOREIGN KEY (usuarientitatid) REFERENCES pfi_usuarientitat (usuarientitatid) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT pfi_revfirma_firma_fk FOREIGN KEY 	(firmaid) REFERENCES pfi_firma 	(firmaid) ON UPDATE NO ACTION ON DELETE NO ACTION
);


create index pfi_revisordefirma_pk_i on pfi_revisordefirma (revisordefirmaid);

create index pfi_revfirma_usrentitat_fk_i on pfi_revisordefirma (usuarientitatid);

create index pfi_revfirma_firmaid_fk_i on pfi_revisordefirma (firmaid);

-- ===============================================================
-- 2018/04/11 Incorporar en Entitat camps per validar Firmes #171
-- ===============================================================

ALTER TABLE pfi_entitat
  ADD COLUMN pluginvalidafirmesid bigint;
ALTER TABLE pfi_entitat
  ADD CONSTRAINT pfi_entitat_plugin_vafi_fk FOREIGN KEY (pluginvalidafirmesid) REFERENCES pfi_plugin 	(pluginid) ON UPDATE NO ACTION ON DELETE NO ACTION;
create index pfi_entitat_pluginvalfir_fk_i on pfi_entitat (pluginvalidafirmesid);


ALTER TABLE pfi_entitat
  ADD COLUMN pluginvalidacertificatid bigint;
ALTER TABLE pfi_entitat
  ADD CONSTRAINT pfi_entitat_plugin_cert_fk FOREIGN KEY 	(pluginvalidacertificatid) REFERENCES pfi_plugin 	(pluginid) ON UPDATE NO ACTION ON DELETE NO ACTION;
create index pfi_entitat_pluginvalcer_fk_i on pfi_entitat 	(pluginvalidacertificatid);


ALTER TABLE pfi_entitat
  ADD COLUMN checkcanviatdocfirmat boolean NOT NULL DEFAULT true;




-- ===========================================
-- 2018/04/11 Plugin de Rubriques #172
-- ===========================================

ALTER TABLE pfi_entitat
  ADD COLUMN pluginrubricaid bigint;
ALTER TABLE pfi_entitat
  ADD CONSTRAINT pfi_entitat_plugin_rubr_fk FOREIGN KEY 	(pluginrubricaid) REFERENCES pfi_plugin 	(pluginid) ON UPDATE NO ACTION ON DELETE NO ACTION;

create index pfi_entitat_pluginrubri_fk_i on pfi_entitat 	(pluginrubricaid);

-- ======================================================================
-- 2018/04/12 Configuració de Firma de UsuariApp 	API Firma Simple) #148
-- ======================================================================

CREATE TABLE pfi_usuariaplicacioconfig (
	
  usuariaplicacioconfigid bigint NOT NULL DEFAULT nextval	('pfi_portafib_seq'),
  usuariaplicacioid character varying	(101) NOT NULL,
  uspoliticadefirma integer NOT NULL DEFAULT 0, 
  policyidentifier character varying	(100),
  policyidentifierhash character varying	(256),
  policyidentifierhashalgorithm character varying	(50),
  policyurldocument character varying	(255),
  filtrecertificats text,
  tipusoperaciofirma integer NOT NULL DEFAULT 0, -- 0 firma, 1 contrafirma 2, cofirma
  tipusfirmaid integer NOT NULL,
  algorismedefirmaid integer,
  modedefirma boolean NOT NULL,
  motiudelegacioid bigint,
  firmatperformatid bigint,
  politicacustodia integer NOT NULL DEFAULT 0,
  custodiainfoid bigint,
  politicataulafirmes integer NOT NULL DEFAULT 0,
  posiciotaulafirmesid integer NOT NULL DEFAULT 0,
  propietatstaulafirmes text,
  politicasegellatdetemps integer NOT NULL DEFAULT 0,
  pluginsegellatid bigint,
  comprovarniffirma boolean, -- Null => Valor definit a l'entitat
  checkcanviatdocfirmat boolean, -- Null => Valor definit a l'entitat
  validarfirma boolean, -- Indica si validar la firma amb el Plugin de validació definit a l'entitat
  validarcertificat boolean, -- Null => Valor definit a l'entitat
  pluginfirmaservidorid bigint,
  upgradesignformat integer,
  maxfirmesenservidor integer,
  htmlperllistarpluginsfirmaweb text,
  logincertificateid bigint,

  CONSTRAINT pfi_usuariaplicacioconfig_pk PRIMARY KEY 	(usuariaplicacioconfigid),
  CONSTRAINT pfi_confapp_algofirma_fk FOREIGN KEY 	(algorismedefirmaid)
      REFERENCES pfi_algorismedefirma 	(algorismedefirmaid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pfi_confapp_custodia_fk FOREIGN KEY 	(custodiainfoid)
      REFERENCES pfi_custodiainfo 	(custodiainfoid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pfi_confapp_fitxer_cert_fk FOREIGN KEY 	(logincertificateid)
      REFERENCES pfi_fitxer 	(fitxerid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pfi_confapp_plugin_fsrv_fk FOREIGN KEY 	(pluginfirmaservidorid)
      REFERENCES pfi_plugin 	(pluginid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pfi_confapp_plugin_seg_fk FOREIGN KEY 	(pluginsegellatid)
      REFERENCES pfi_plugin 	(pluginid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pfi_confapp_tipusfirma_fk FOREIGN KEY 	(tipusfirmaid)
      REFERENCES pfi_tipusfirma 	(tipusfirmaid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pfi_confapp_traduccio_firm_fk FOREIGN KEY 	(firmatperformatid)
      REFERENCES pfi_traduccio 	(traduccioid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pfi_confapp_traduccio_moti_fk FOREIGN KEY 	(motiudelegacioid)
      REFERENCES pfi_traduccio 	(traduccioid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pfi_confapp_usrapp_fk FOREIGN KEY 	(usuariaplicacioid)
      REFERENCES pfi_usuariaplicacio 	(usuariaplicacioid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pfi_confapp_usrapp_uk UNIQUE 	(usuariaplicacioid)
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

ALTER TABLE pfi_usuariaplicacio ADD COLUMN politicadepluginfirmaweb integer NOT NULL DEFAULT 0;
COMMENT ON COLUMN pfi_usuariaplicacio.politicadepluginfirmaweb IS '0 - Només plugins de l''entitat, 1 - Plugins de l''entitat més plugins addicionals 	afegir o llevar), 2 - Només plugins addicionals 	Només els que tenguin marcat afegir)';

ALTER TABLE pfi_usuarientitat  ADD COLUMN politicadepluginfirmaweb integer NOT NULL DEFAULT 0;
COMMENT ON COLUMN pfi_usuarientitat.politicadepluginfirmaweb IS ' 0 - Només plugins de l''''entitat, 1 - Plugins de l''''entitat més plugins addicionals 	afegir o llevar), 2 - Només plugins addicionals 	Només els que tenguin marcat afegir)''';

CREATE TABLE pfi_pluginfirmawebperusrent (
	
   pluginfirmawebperusrentid bigint NOT NULL DEFAULT nextval	('pfi_portafib_seq'), 
   usuarientitatid character varying	(101) NOT NULL, 
   pluginfirmawebid bigint NOT NULL, 
   accio integer NOT NULL DEFAULT 1, 
   CONSTRAINT pfi_pluginfirmawebperusrent_pk PRIMARY KEY 	(pluginfirmawebperusrentid), 
   CONSTRAINT pfi_pfwpue_usrentitat_fk FOREIGN KEY 	(usuarientitatid) REFERENCES pfi_usuarientitat 	(usuarientitatid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT pfi_pfwpue_plugin_fk FOREIGN KEY 	(pluginfirmawebid) REFERENCES pfi_plugin 	(pluginid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT pfi_pfwpue_usuent_plug_uk UNIQUE 	(usuarientitatid, pluginfirmawebid)
);

COMMENT ON COLUMN pfi_pluginfirmawebperusrent.accio IS 'Valors:  -1 eliminar, 1 afegir';

create index pfi_pfwpue_pk_i on pfi_pluginfirmawebperusrent 	(pluginfirmawebperusrentid);
create index pfi_pfwpue_usrentid_fk_i on pfi_pluginfirmawebperusrent 	(usuarientitatid);
create index pfi_pfwpue_plugin_fk_i on pfi_pluginfirmawebperusrent 	(pluginfirmawebid);


CREATE TABLE pfi_pluginfirmawebperusrapp (
	
   pluginfirmawebperusrappid bigint NOT NULL DEFAULT nextval	('pfi_portafib_seq'), 
   usuariaplicacioid character varying	(101) NOT NULL, 
   pluginfirmawebid bigint NOT NULL, 
   accio integer NOT NULL DEFAULT 1, 
   CONSTRAINT pfi_pluginfirmawebperusrapp_pk PRIMARY KEY 	(pluginfirmawebperusrappid), 
   CONSTRAINT pfi_pfwpua_usrapp_fk FOREIGN KEY 	(usuariaplicacioid) REFERENCES pfi_usuariaplicacio 	(usuariaplicacioid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT pfi_pfwpua_plugin_fk FOREIGN KEY 	(pluginfirmawebid) REFERENCES pfi_plugin 	(pluginid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT pfi_pfwpua_usuapp_plug_uk UNIQUE 	(usuariaplicacioid, pluginfirmawebid)
);
COMMENT ON COLUMN pfi_pluginfirmawebperusrapp.accio IS 'Valors:  -1 eliminar, 1 afegir';

create index pfi_pfwpua_pk_i on pfi_pluginfirmawebperusrapp 	(pluginfirmawebperusrappid);
create index pfi_pfwpua_usrappid_fk_i on pfi_pluginfirmawebperusrapp 	(usuariaplicacioid);
create index pfi_pfwpua_plugin_fk_i on pfi_pluginfirmawebperusrapp 	(pluginfirmawebid);


-- ======================================================================
-- 2018/04/16 Taula de Cridades a Plugins 	(bitàcola de cridades) #170
-- ======================================================================

CREATE TABLE pfi_plugincridada (
	
   plugincridadaid bigint NOT NULL DEFAULT nextval	('pfi_portafib_seq'), 
   entitatid character varying	(50), 
   data timestamp with time zone NOT NULL, 
   pluginid bigint NOT NULL,
   metodeplugin character varying	(100) NOT NULL, 
   parametrestext text,
   parametresfitxerid bigint,
   retorntext text,
   retornfitxerid bigint,
   tipusresultat integer NOT NULL, 
   tempsexecucio bigint, 
   CONSTRAINT pfi_plugcrida_fitxer_retor_fk FOREIGN KEY (retornfitxerid) REFERENCES pfi_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT pfi_plugcrida_fitxer_param_fk FOREIGN KEY (parametresfitxerid) REFERENCES pfi_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT pfi_plugcrida_plugin_fk FOREIGN KEY (pluginid) REFERENCES pfi_plugin (pluginid) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT pfi_plugincridada_pk PRIMARY KEY 	(plugincridadaid), 
   CONSTRAINT pfi_plugcrida_entitat_fk FOREIGN KEY 	(entitatid) REFERENCES pfi_entitat 	(entitatid) ON UPDATE NO ACTION ON DELETE NO ACTION
);
COMMENT ON COLUMN pfi_plugincridada.tipusresultat IS '0 => error, 1 => ok';
COMMENT ON COLUMN pfi_plugincridada.resultat IS 'conte error si falla i dades resultat si va bé.';
COMMENT ON COLUMN pfi_plugincridada.tempsexecucio IS 'milisegons execucio';

create index pfi_plugincridada_pk_i on pfi_plugincridada 	(plugincridadaid);
create index pfi_plugcrida_entitatid_fk_i on pfi_plugincridada 	(entitatid);
create index pfi_plugcrida_pluginid_fk_i on pfi_plugincridada (pluginid);
create index pfi_plugcrida_paramfitxer_fk_i on pfi_plugincridada (parametresfitxerid);
create index pfi_plugcrida_retorfitxer_fk_i on pfi_plugincridada (retornfitxerid);

-- ======================================================================
-- 2018/05/01 Configuració de Firma de UsuariApp 	Us política de Firma) #148
-- ======================================================================
 
 ALTER TABLE pfi_entitat ADD COLUMN uspoliticadefirma integer NOT NULL DEFAULT 0;
 COMMENT ON COLUMN pfi_entitat.uspoliticadefirma IS '-1=> usar politica de firma de l''entitat, 0 => no usar politica de firma,  1=> usar politica d''aquesta configuracio, 2 => L''usuari web o usuari-app elegeixen la politica de firma';

    