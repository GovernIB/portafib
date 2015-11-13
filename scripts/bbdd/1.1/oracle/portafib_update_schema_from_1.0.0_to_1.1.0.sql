
-- =============================================================
--  11/11/2015 Modul de Firma i relació amb Tipus de Documents
-- =============================================================



    create table pfi_moduldefirma (
        moduldefirmaid number(19,0) not null,
        actiu number(1,0) not null,
        classe varchar2(255 char) not null,
        descripciocurtaid number(19,0) not null,
        entitatid varchar2(50 char),
        nomid number(19,0) not null,
        propertiesadmin clob,
        propertiesentitat clob
    );

    create table pfi_modulfirmapertipusdoc (
        id number(19,0) not null,
        moduldefirmaid number(19,0) not null,
        nom varchar2(100 char) not null,
        tipusdocumentid number(19,0) not null
    );
    
    
    create index pfi_modfirm_desccurtaid_fk_i on pfi_moduldefirma (descripciocurtaid);
    create index pfi_moduldefirma_pk_i on pfi_moduldefirma (moduldefirmaid);
    create index pfi_moduldefirma_nomid_fk_i on pfi_moduldefirma (nomid);
    create index pfi_modfirm_entitatid_fk_i on pfi_moduldefirma (entitatid);
    create index pfi_mofitido_modfirma_fk_i on pfi_modulfirmapertipusdoc (moduldefirmaid);
    create index pfi_modulfirmapertipusdoc_pk_i on pfi_modulfirmapertipusdoc (id);
    create index pfi_mofitido_tipusdoc_fk_i on pfi_modulfirmapertipusdoc (tipusdocumentid);
    
    alter table pfi_moduldefirma add constraint pfi_moduldefirma_pk primary key (moduldefirmaid);
    alter table pfi_modulfirmapertipusdoc add constraint pfi_modulfirmapertipusdoc_pk primary key (id);
    
    
    alter table pfi_moduldefirma 
        add constraint pfi_modfirm_entitat_fk 
        foreign key (entitatid) 
        references pfi_entitat;
    alter table pfi_moduldefirma 
        add constraint pfi_modfirm_traduccio_nom_fk 
        foreign key (nomid) 
        references pfi_traduccio;
    alter table pfi_moduldefirma 
        add constraint pfi_modfirm_traduccio_desc_fk 
        foreign key (descripciocurtaid) 
        references pfi_traduccio;
    alter table pfi_modulfirmapertipusdoc 
        add constraint pfi_mofitido_modfirm_fk 
        foreign key (moduldefirmaid) 
        references pfi_moduldefirma;
    alter table pfi_modulfirmapertipusdoc 
        add constraint pfi_mofitido_tipusdoc_fk 
        foreign key (tipusdocumentid) 
        references pfi_tipusdocument;
    
    
    alter table pfi_modulfirmapertipusdoc add constraint pfi_mofitido_modfirm_tipdoc_uk unique (tipusdocumentid, moduldefirmaid);
    
    -- XYZ
    grant select,insert,delete,update on pfi_moduldefirma to www_portafib;
    grant select,insert,delete,update on pfi_modulfirmapertipusdoc to www_portafib;

-- =============================================================
--  2015/11/13 Incrementar tamany camp mime de pfi_fitxer
-- =============================================================
    
ALTER TABLE pfi_fitxer MODIFY (  mime varchar2(255 char) );


-- =============================================================
--  2015/11/13 Afegir camp rebreagrupat a la taula pfi_rebreavis
-- =============================================================

ALTER TABLE pfi_rebreavis ADD rebreagrupat number(1,0)  DEFAULT 0 NOT NULL;


-- =================================================================================
--  2015/11/13 Afegir camps d'entitat que estaven fins ara dins de les propietats
-- =================================================================================

ALTER TABLE pfi_entitat ADD algorismedefirmaid number(10,0) DEFAULT 0 not null;
ALTER TABLE pfi_entitat ADD comprovarcertificatclientcert number(1,0) DEFAULT 1 not null;
ALTER TABLE pfi_entitat ADD comprovarniffirma number(1,0) DEFAULT 1 not null;
ALTER TABLE pfi_entitat ADD motiudelegacioid number(19,0);
ALTER TABLE pfi_entitat ADD firmatperformatid number(19,0);

create index pfi_entitat_motiudele_fk_i on pfi_entitat (motiudelegacioid);
create index pfi_entitat_algofirma_fk_i on pfi_entitat (algorismedefirmaid);
create index pfi_entitat_firmatper_fk_i on pfi_entitat (firmatperformatid);

alter table pfi_entitat add constraint pfi_entitat_algofirma_fk foreign key (algorismedefirmaid) references pfi_algorismedefirma;
alter table pfi_entitat add constraint pfi_entitat_traduccio_moti_fk foreign key (motiudelegacioid) references pfi_traduccio;
alter table pfi_entitat add constraint pfi_entitat_traduccio_firm_fk foreign key (firmatperformatid) references pfi_traduccio;







