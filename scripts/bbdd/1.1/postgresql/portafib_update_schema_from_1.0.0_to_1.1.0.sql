
-- =============================================================
--  11/11/2015 Modul de Firma i relació amb Tipus de Documents
-- =============================================================


CREATE TABLE pfi_moduldefirma (
    moduldefirmaid bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    descripciocurtaid bigint NOT NULL,
    classe character varying(255) NOT NULL,
    propertiesadmin text,
    propertiesentitat text,
    entitatid character varying(50),
    actiu boolean NOT NULL
);
ALTER TABLE portafib.pfi_moduldefirma OWNER TO portafib;
COMMENT ON COLUMN pfi_moduldefirma.entitatid IS 'Si val null indica que és de l''Administrador. En cas conytrari ja Ã©s una instanciaciÃ³ d''una Entitat';

CREATE TABLE pfi_modulfirmapertipusdoc (
    id bigint DEFAULT nextval('pfi_portafib_seq'::regclass) NOT NULL,
    tipusdocumentid bigint NOT NULL,
    moduldefirmaid bigint NOT NULL,
    nom character varying(100) NOT NULL
);

ALTER TABLE portafib.pfi_modulfirmapertipusdoc OWNER TO portafib;
ALTER TABLE ONLY pfi_moduldefirma
    ADD CONSTRAINT pfi_moduldefirma_pk PRIMARY KEY (moduldefirmaid);
ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_modulfirmapertipusdoc_pk PRIMARY KEY (id);
ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_modfirm_tipdoc_uk UNIQUE (tipusdocumentid, moduldefirmaid);

CREATE INDEX pfi_modfirm_desccurtaid_fk_i ON pfi_moduldefirma USING btree (descripciocurtaid);
CREATE INDEX pfi_modfirm_entitatid_fk_i ON pfi_moduldefirma USING btree (entitatid);
CREATE INDEX pfi_moduldefirma_nomid_fk_i ON pfi_moduldefirma USING btree (nomid);
CREATE INDEX pfi_moduldefirma_pk_i ON pfi_moduldefirma USING btree (moduldefirmaid);
CREATE INDEX pfi_modulfirmapertipusdoc_pk_i ON pfi_modulfirmapertipusdoc USING btree (id);
CREATE INDEX pfi_mofitido_modfirma_fk_i ON pfi_modulfirmapertipusdoc USING btree (moduldefirmaid);
CREATE INDEX pfi_mofitido_tipusdoc_fk_i ON pfi_modulfirmapertipusdoc USING btree (tipusdocumentid);


ALTER TABLE ONLY pfi_moduldefirma
    ADD CONSTRAINT pfi_modfirm_entitat_fk FOREIGN KEY (entitatid) REFERENCES pfi_entitat(entitatid);
ALTER TABLE ONLY pfi_moduldefirma
    ADD CONSTRAINT pfi_modfirm_traduccio_desc_fk FOREIGN KEY (descripciocurtaid) REFERENCES pfi_traduccio(traduccioid);
ALTER TABLE ONLY pfi_moduldefirma
    ADD CONSTRAINT pfi_modfirm_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES pfi_traduccio(traduccioid);
ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_modfirm_fk FOREIGN KEY (moduldefirmaid) REFERENCES pfi_moduldefirma(moduldefirmaid);
ALTER TABLE ONLY pfi_modulfirmapertipusdoc
    ADD CONSTRAINT pfi_mofitido_tipusdoc_fk FOREIGN KEY (tipusdocumentid) REFERENCES pfi_tipusdocument(tipusdocumentid);


    