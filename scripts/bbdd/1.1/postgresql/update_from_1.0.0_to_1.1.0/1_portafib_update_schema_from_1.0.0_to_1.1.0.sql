
-- =============================================================
--  2015/11/11 Modul de Firma i relació amb Tipus de Documents
-- =============================================================


CREATE TABLE pfi_plugin (
    pluginid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    descripciocurtaid bigint NOT NULL,
    classe character varying(255) NOT NULL,
    propertiesadmin text,
    propertiesentitat text,
    entitatid character varying(50),
    actiu boolean NOT NULL,
    tipus integer NOT NULL
);
ALTER TABLE portafib.pfi_plugin OWNER TO portafib;
COMMENT ON COLUMN pfi_plugin.entitatid IS 'Si val null indica que és de l''Administrador. En cas conytrari ja és una instanciació d''una Entitat';

CREATE TABLE pfi_modulfirmapertipusdoc (
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    tipusdocumentid bigint NOT NULL,
    pluginid bigint NOT NULL,
    nom character varying(100) NOT NULL
);

ALTER TABLE portafib.pfi_modulfirmapertipusdoc OWNER TO portafib;
ALTER TABLE ONLY pfi_plugin
    ADD CONSTRAINT pfi_plugin_pk PRIMARY KEY (pluginid);
ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_modulfirmapertipusdoc_pk PRIMARY KEY (id);
ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_plugin_tipdoc_uk UNIQUE (tipusdocumentid, pluginid);

CREATE INDEX pfi_plugin_desccurtaid_fk_i ON pfi_plugin USING btree (descripciocurtaid);
CREATE INDEX pfi_plugin_entitatid_fk_i ON pfi_plugin USING btree (entitatid);
CREATE INDEX pfi_plugin_nomid_fk_i ON pfi_plugin USING btree (nomid);
CREATE INDEX pfi_plugin_pk_i ON pfi_plugin USING btree (pluginid);
CREATE INDEX pfi_modulfirmapertipusdoc_pk_i ON pfi_modulfirmapertipusdoc USING btree (id);
CREATE INDEX pfi_mofitido_plugin_fk_i ON pfi_modulfirmapertipusdoc USING btree (pluginid);
CREATE INDEX pfi_mofitido_tipusdoc_fk_i ON pfi_modulfirmapertipusdoc USING btree (tipusdocumentid);


ALTER TABLE ONLY pfi_plugin
    ADD CONSTRAINT pfi_plugin_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);
ALTER TABLE ONLY pfi_plugin
    ADD CONSTRAINT pfi_plugin_traduccio_desc_fk FOREIGN KEY (descripciocurtaid) REFERENCES pfi_traduccio(traduccioid);
ALTER TABLE ONLY pfi_plugin
    ADD CONSTRAINT pfi_plugin_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES pfi_traduccio(traduccioid);
ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_plugin_fk FOREIGN KEY (pluginid) REFERENCES pfi_plugin(pluginid);
ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES pfi_tipusdocument(tipusdocumentid);

-- =============================================================
--  2015/11/13 Incrementar tamany camp mime de pfi_fitxer
-- =============================================================

ALTER TABLE pfi_fitxer ALTER COLUMN mime TYPE character varying(255);


-- =============================================================
--  2015/11/13 Afegir camp rebreagrupat a la taula pfi_rebreavis
-- =============================================================

ALTER TABLE pfi_rebreavis ADD COLUMN rebreagrupat boolean NOT NULL DEFAULT false;

-- =================================================================================
--  2015/11/13 Afegir camps d'entitat que estaven fins ara dins de les propietats
-- =================================================================================

ALTER TABLE pfi_entitat ADD COLUMN motiudelegacioid bigint;
ALTER TABLE pfi_entitat ADD COLUMN firmatperformatid bigint;
ALTER TABLE pfi_entitat ADD COLUMN algorismedefirmaid bigint NOT NULL DEFAULT 0;
ALTER TABLE pfi_entitat ADD COLUMN comprovarniffirma boolean NOT NULL DEFAULT true;

ALTER TABLE pfi_entitat ADD CONSTRAINT pfi_entitat_algofirma_fk FOREIGN KEY (algorismedefirmaid)  REFERENCES pfi_algorismedefirma (algorismedefirmaid);
ALTER TABLE pfi_entitat ADD CONSTRAINT pfi_entitat_traduccio_firm_fk FOREIGN KEY (firmatperformatid) REFERENCES pfi_traduccio (traduccioid);
ALTER TABLE pfi_entitat ADD CONSTRAINT pfi_entitat_traduccio_moti_fk FOREIGN KEY (motiudelegacioid) REFERENCES pfi_traduccio (traduccioid);

create index pfi_entitat_motiudele_fk_i on pfi_entitat (motiudelegacioid);
create index pfi_entitat_firmatper_fk_i on pfi_entitat (firmatperformatid);
create index pfi_entitat_algofirma_fk_i on pfi_entitat (algorismedefirmaid);


-- ======================================================
--  2015/11/13 Custòdia per defecte dins Entitat
-- ======================================================

ALTER TABLE pfi_entitat ADD custodiainfoid bigint;

ALTER TABLE pfi_entitat ADD CONSTRAINT pfi_entitat_custodia_fk FOREIGN KEY (custodiainfoid) REFERENCES pfi_custodiainfo (custodiainfoid);

create index pfi_entitat_custodiadef_fk_i on pfi_entitat (custodiainfoid);


-- =============================================================
--  2015/11/25 Segellat de Temps
-- =============================================================

ALTER TABLE pfi_entitat ADD pluginid bigint;
ALTER TABLE pfi_entitat ADD segelldetempsviaweb integer DEFAULT 0 NOT NULL;

COMMENT ON COLUMN pfi_entitat.pluginid IS 'Plugin de segellat de temps';

ALTER TABLE pfi_peticiodefirma ADD segellatdetemps boolean DEFAULT false NOT NULL;

CREATE INDEX pfi_entitat_segelltemps_fk_i ON pfi_entitat (pluginid);

ALTER TABLE pfi_entitat ADD CONSTRAINT pfi_entitat_plugin_fk FOREIGN KEY (pluginid) REFERENCES pfi_plugin(pluginid);


-- =============================================================
--  2015/12/01 Taula de Propietats Globals
-- =============================================================

CREATE TABLE pfi_propietatglobal (
    clau character varying(255) NOT NULL,
    valor character varying(255),
    descripcio character varying(255),
    entitatid character varying(50),
    propietatglobalid bigint DEFAULT nextval('pfi_portafib_seq') NOT NULL
);

ALTER TABLE ONLY pfi_propietatglobal
    ADD CONSTRAINT pfi_propietat_clau_entitat_uk UNIQUE (clau, entitatid);
ALTER TABLE ONLY pfi_propietatglobal
    ADD CONSTRAINT pfi_propietatglobal_pk PRIMARY KEY (propietatglobalid);

CREATE INDEX pfi_propietat_entitatid_fk_i ON pfi_propietatglobal USING btree (entitatid);
CREATE INDEX pfi_propietatglobal_pk_i ON pfi_propietatglobal USING btree (propietatglobalid);

ALTER TABLE ONLY pfi_propietatglobal
    ADD CONSTRAINT pfi_propietat_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);













    