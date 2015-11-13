

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
    

