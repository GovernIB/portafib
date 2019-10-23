
SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

CREATE SCHEMA portafib;
ALTER SCHEMA portafib OWNER TO portafib;

SET search_path = portafib, pg_catalog;
SET default_tablespace = '';
SET default_with_oids = false;

    create table pfi_annex (
        annexid int8 not null,
        adjuntar bool not null,
        firmar bool not null,
        fitxerid int8 not null,
        peticiodefirmaid int8 not null,
        primary key (annexid)
    );

    create table pfi_annexfirmat (
        annexfirmatid int8 not null,
        annexid int8 not null,
        firmaid int8 not null,
        fitxerid int8 not null,
        primary key (annexfirmatid)
    );

    create table pfi_bitacola (
        bitacolaid int8 not null,
        data timestamp not null,
        descripcio varchar(255),
        entitatid varchar(50) not null,
        objecteserialitzat text,
        objecteid varchar(50) not null,
        tipusobjecte int4 not null,
        tipusoperacio int4 not null,
        usuariid varchar(101) not null,
        primary key (bitacolaid)
    );

    create table pfi_blocdefirmes (
        blocdefirmesid int8 not null,
        datafinalitzacio timestamp,
        fluxdefirmesid int8 not null,
        minimdefirmes int4 not null,
        ordre int4 not null,
        primary key (blocdefirmesid)
    );

    create table pfi_codibarres (
        codibarresid varchar(255) not null,
        descripcio varchar(255),
        nom varchar(50) not null,
        primary key (codibarresid)
    );

    create table pfi_colaboraciodelegacio (
        colaboraciodelegacioid int8 not null,
        activa bool not null,
        colaboradordelegatid varchar(101) not null,
        datafi timestamp,
        datainici timestamp not null,
        descripcio varchar(255),
        destinatariid varchar(101) not null,
        esdelegat bool not null,
        fitxerautoritzacioid int8,
        motiu varchar(60) not null,
        motiudeshabilitada varchar(255),
        revisor bool not null,
        primary key (colaboraciodelegacioid)
    );

    create table pfi_custodiainfo (
        custodiainfoid int8 not null,
        codibarresid varchar(255) not null,
        codibarresposiciopaginaid int8 not null,
        codibarrestext varchar(255) not null,
        csv varchar(500),
        csvgenerationdefinition varchar(500),
        csvvalidationweb varchar(500),
        custodiapluginid varchar(255),
        custodiapluginparametres varchar(3000),
        custodiar bool not null,
        datacustodia timestamp,
        documentid varchar(250),
        editable bool not null,
        enifiledirecturl varchar(500),
        entitatid varchar(50),
        expedientid varchar(250),
        missatge varchar(3000) not null,
        missatgeposiciopaginaid int8 not null,
        nomplantilla varchar(255),
        originalfiledirecturl varchar(500),
        pagines varchar(255) not null,
        pluginid int8 not null,
        printablefiledirecturl varchar(500),
        titolpeticio varchar(255),
        urlfitxercustodiat varchar(500),
        usuariaplicacioid varchar(101),
        usuarientitatid varchar(101),
        primary key (custodiainfoid)
    );

    create table pfi_entitat (
        entitatid varchar(50) not null,
        activa bool not null,
        adrezahtml varchar(2000) not null,
        algorismedefirmaid int4 not null,
        checkcanviatdocfirmat bool not null,
        comprovarniffirma bool not null,
        custodiainfoid int8,
        descripcio varchar(255),
        faviconid int8 not null,
        filtrecertificats text not null,
        firmatperformatid int8,
        logosegellid int8 not null,
        logowebid int8 not null,
        logowebpeuid int8 not null,
        maxfilestosignatsametime int4,
        maxsizefitxeradaptat int8,
        maxuploadsize int8,
        motiudelegacioid int8,
        nom varchar(50) not null,
        pdfautoritzaciodelegacioid int8 not null,
        pluginrubricaid int8,
        pluginid int8,
        pluginvalidacertificatid int8,
        pluginvalidafirmesid int8,
        policyidentifier varchar(100),
        policyidentifierhash text,
        policyidentifierhashalgorithm varchar(50),
        policyurldocument varchar(255),
        politicacustodia int4 not null,
        segelldetempsviaweb int4 not null,
        politicataulafirmes int4 not null,
        posiciotaulafirmes int4 not null,
        propietatstaulafirmes text,
        suportemail varchar(100),
        suporttelefon varchar(50),
        suportweb varchar(250),
        uspoliticadefirma int4 not null,
        usuariaplicacioid varchar(101),
        web varchar(250) not null,
        primary key (entitatid)
    );

    create table pfi_estadistica (
        estadisticaid int8 not null,
        data timestamp not null,
        entitatid varchar(50),
        parametres varchar(3000),
        tipus int4 not null,
        usuariaplicacioid varchar(101),
        usuarientitatid varchar(101),
        valor float8 not null,
        primary key (estadisticaid)
    );

    create table pfi_estatdefirma (
        estatdefirmaid int8 not null,
        colaboraciodelegacioid int8,
        datafi timestamp,
        datainici timestamp not null,
        descripcio varchar(255),
        firmaid int8 not null,
        tipusestatdefirmafinalid int8,
        tipusestatdefirmainicialid int8 not null,
        usuarientitatid varchar(101) not null,
        primary key (estatdefirmaid)
    );

    create table pfi_firma (
        firmaid int8 not null,
        blocdefirmaid int8 not null,
        caixa_alt int4,
        caixa_ample int4,
        caixa_pagina int4 not null,
        caixa_x int4,
        caixa_y int4,
        checkadministrationidofsigner bool,
        checkdocumentmodifications bool,
        checkvalidationsignature bool,
        destinatariid varchar(101) not null,
        emissorcertificat varchar(1000),
        fitxerfirmatid int8,
        minimderevisors int4 not null,
        mostrarrubrica bool not null,
        motiu varchar(255),
        nomcertificat varchar(1000),
        numfirmadocument int4,
        numeroseriecertificat numeric(19, 2),
        obligatori bool not null,
        perfildefirma varchar(50),
        tipusestatdefirmafinalid int8,
        extern_email varchar(255),
        extern_idioma varchar(2),
        extern_llinatges varchar(255),
        extern_nivellseguretat int4,
        extern_nom varchar(100),
        extern_token varchar(255),
        primary key (firmaid)
    );

    create table pfi_fitxer (
        fitxerid int8 not null,
        descripcio varchar(1000),
        mime varchar(255) not null,
        nom varchar(255) not null,
        tamany int8 not null,
        primary key (fitxerid)
    );

    create table pfi_fluxdefirmes (
        fluxdefirmesid int8 not null,
        nom varchar(255) not null,
        primary key (fluxdefirmesid)
    );

    create table pfi_grupentitat (
        grupentitatid int8 not null,
        descripcio varchar(255),
        entitatid varchar(50) not null,
        nom varchar(100) not null,
        primary key (grupentitatid),
        unique (nom, entitatid)
    );

    create table pfi_grupentitatusuarientitat (
        grupentitatusuarientitatid int8 not null,
        grupentitatid int8 not null,
        usuarientitatid varchar(101) not null,
        primary key (grupentitatusuarientitatid),
        unique (usuarientitatid, grupentitatid)
    );

    create table pfi_idioma (
        idiomaid varchar(5) not null,
        nom varchar(50) not null,
        ordre int4 not null,
        suportat bool not null,
        primary key (idiomaid)
    );

    create table pfi_metadada (
        metadadaid int8 not null,
        descripcio varchar(1000),
        nom varchar(50) not null,
        peticiodefirmaid int8 not null,
        tipusmetadadaid int4 not null,
        valor text not null,
        primary key (metadadaid)
    );

    create table pfi_modulfirmapertipusdoc (
        id int8 not null,
        nom varchar(100) not null,
        pluginid int8 not null,
        tipusdocumentid int8 not null,
        primary key (id),
        unique (tipusdocumentid, pluginid)
    );

    create table pfi_notificacio (
        notificacioid int8 not null,
        bloquejada bool,
        datacreacio timestamp not null,
        dataenviament timestamp,
        dataerror timestamp,
        descripcio text,
        error text,
        peticiodefirmaid int8 not null,
        reintents int4 not null,
        tipusnotificacioid int8 not null,
        usuariaplicacioid varchar(101) not null,
        primary key (notificacioid)
    );

    create table pfi_perfilsperusrapp (
        perfilsperusrappid int8 not null,
        usuariaplicacioperfilid int8 not null,
        usuariaplicacioid varchar(50) not null,
        primary key (perfilsperusrappid),
        unique (usuariaplicacioperfilid, usuariaplicacioid)
    );

    create table pfi_permisgrupplantilla (
        permisgrupplantillaid int8 not null,
        grupentitatid int8 not null,
        fluxdefirmesid int8 not null,
        primary key (permisgrupplantillaid),
        unique (grupentitatid, fluxdefirmesid)
    );

    create table pfi_permisusuariplantilla (
        permisusuariplantillaid int8 not null,
        fluxdefirmesid int8 not null,
        usuarientitatid varchar(101) not null,
        primary key (permisusuariplantillaid),
        unique (usuarientitatid, fluxdefirmesid)
    );

    create table pfi_peticiodefirma (
        peticiodefirmaid int8 not null,
        algorismedefirmaid int4 not null,
        avisweb bool not null,
        configuraciodefirmaid int8,
        custodiainfoid int8,
        datacaducitat timestamp not null,
        datafinal timestamp,
        datasolicitud timestamp,
        descripcio varchar(255),
        descripciotipusdocument varchar(255),
        expedientcodi varchar(255),
        expedientnom varchar(255),
        expedienturl varchar(255),
        firmaoriginaldetachedid int8,
        fitxerafirmarid int8,
        fitxeradaptatid int8,
        fluxdefirmesid int8 not null unique,
        idiomaid varchar(5) not null,
        informacioaddicional varchar(500),
        informacioaddicionalavaluable float8,
        logosegellid int8,
        modedefirma bool not null,
        motiu varchar(255) not null,
        motiuderebuig varchar(255),
        origenpeticiodefirma int4 not null,
        posiciotaulafirmesid int4 not null,
        prioritatid int4 not null,
        procedimentcodi varchar(255),
        procedimentnom varchar(255),
        remitentdescripcio varchar(500),
        remitentnom varchar(100) not null,
        segellatdetemps bool not null,
        usuariaplicacioid varchar(101) not null,
        usuarientitatid varchar(101),
        solicitantpersona2id varchar(101),
        solicitantpersona3id varchar(101),
        tipusdocumentid int8 not null,
        tipusestatpeticiodefirmaid int4 not null,
        tipusfirmaid int4 not null,
        tipusoperaciofirma int4 not null,
        titol varchar(255) not null,
        primary key (peticiodefirmaid)
    );

    create table pfi_plantillafluxdefirmes (
        fluxdefirmesid int8 not null,
        compartir bool,
        descripcio varchar(1000) not null,
        usuariaplicacioid varchar(101),
        usuarientitatid varchar(101),
        primary key (fluxdefirmesid)
    );

    create table pfi_plugin (
        pluginid int8 not null,
        actiu bool not null,
        classe varchar(255) not null,
        codi varchar(255) not null,
        descripciocurtaid int8 not null,
        entitatid varchar(50),
        nomid int8 not null,
        ordre int4,
        politicadeus int4 not null,
        politicamostrarpropietats int4 not null,
        propertiesadmin text,
        propertiesentitat text,
        tipus int4 not null,
        primary key (pluginid)
    );

    create table pfi_plugincridada (
        plugincridadaid int8 not null,
        data timestamp not null,
        entitatid varchar(50),
        metodeplugin varchar(100) not null,
        parametresfitxerid int8,
        parametrestext text,
        pluginid int8 not null,
        retornfitxerid int8,
        retorntext text,
        tempsexecucio int8 not null,
        tipusresultat int4 not null,
        primary key (plugincridadaid)
    );

    create table pfi_pluginfirmawebperusrapp (
        pluginfirmawebperusrappid int8 not null,
        accio int4 not null,
        pluginfirmawebid int8 not null,
        usuariaplicacioid varchar(101) not null,
        primary key (pluginfirmawebperusrappid),
        unique (usuariaplicacioid, pluginfirmawebid)
    );

    create table pfi_pluginfirmawebperusrent (
        pluginfirmawebperusrentid int8 not null,
        accio int4 not null,
        pluginfirmawebid int8 not null,
        usuarientitatid varchar(101) not null,
        primary key (pluginfirmawebperusrentid),
        unique (usuarientitatid, pluginfirmawebid)
    );

    create table pfi_propietatglobal (
        propietatglobalid int8 not null,
        clau varchar(255) not null,
        descripcio varchar(1000),
        entitatid varchar(50),
        valor varchar(255),
        primary key (propietatglobalid),
        unique (clau, entitatid)
    );

    create table pfi_rebreavis (
        id int8 not null,
        rebreagrupat bool not null,
        tipusnotificacioid int8 not null,
        usuarientitatid varchar(101) not null,
        primary key (id),
        unique (tipusnotificacioid, usuarientitatid)
    );

    create table pfi_revisordefirma (
        revisordefirmaid int8 not null,
        firmaid int8 not null,
        obligatori bool not null,
        usuarientitatid varchar(101) not null,
        primary key (revisordefirmaid)
    );

    create table pfi_role (
        roleid varchar(50) not null,
        descripcio varchar(255),
        nom varchar(50) not null,
        primary key (roleid)
    );

    create table pfi_roleusuarientitat (
        id int8 not null,
        roleid varchar(50) not null,
        usuarientitatid varchar(101) not null,
        primary key (id),
        unique (roleid, usuarientitatid)
    );

    create table pfi_tipusdocument (
        tipusdocumentid int8 not null,
        descripcio varchar(1000),
        nom int8 not null,
        tipusdocumentbaseid int8 not null,
        usuariaplicacioid varchar(50),
        primary key (tipusdocumentid)
    );

    create table pfi_tipusdocumentcoladele (
        id int8 not null,
        colaboraciodelegacioid int8 not null,
        tipusdocumentid int8 not null,
        primary key (id),
        unique (colaboraciodelegacioid, tipusdocumentid)
    );

    create table pfi_tipusnotificacio (
        tipusnotificacioid int8 not null,
        descripcio varchar(250),
        esavis bool,
        nom varchar(50) not null,
        primary key (tipusnotificacioid)
    );

    create table pfi_traduccio (
        traduccioid int8 not null,
        primary key (traduccioid)
    );

    create table pfi_traducciomap (
        traducciomapid int8 not null,
        valor varchar(4000),
        idiomaid varchar(255),
        primary key (traducciomapid, idiomaid)
    );

    create table pfi_usuariaplicacio (
        usuariaplicacioid varchar(101) not null,
        actiu bool not null,
        callbackurl varchar(400) not null,
        callbackversio int4 not null,
        custodiainfoid int8,
        descripcio varchar(255),
        emailadmin varchar(100) not null,
        entitatid varchar(50) not null,
        idiomaid varchar(5) not null,
        logosegellid int8,
        politicacustodia int4 not null,
        politicadepluginfirmaweb int4 not null,
        primary key (usuariaplicacioid)
    );

    create table pfi_usuariaplicacioconfig (
        usuariaplicacioconfigid int8 not null,
        algorismedefirmaid int4,
        checkcanviatdocfirmat bool,
        comprovarniffirma bool,
        entitatid varchar(50) not null,
        esdepeticio bool not null,
        filtrecertificats text,
        firmatperformatid int8,
        htmlperllistarpluginsfirmaweb text,
        modedefirma bool not null,
        motiudelegacioid int8,
        nom varchar(255) not null,
        pluginfirmaservidorid int8,
        pluginsegellatid int8,
        policyidentifier varchar(100),
        policyidentifierhash varchar(256),
        policyidentifierhashalgorithm varchar(50),
        policyurldocument varchar(255),
        politicasegellatdetemps int4 not null,
        politicataulafirmes int4 not null,
        posiciotaulafirmesid int4 not null,
        propietatstaulafirmes text,
        tipusfirmaid int4 not null,
        tipusoperaciofirma int4 not null,
        upgradesignformat int4,
        usenfirmaapisimpleservidor bool not null,
        usenfirmaapisimpleweb bool not null,
        usenfirmaws2 bool not null,
        usenfirmapassarelaservidor bool not null,
        usenfirmapassarelaweb bool not null,
        usenfirmaws1 bool not null,
        usenfirmaweb bool not null,
        uspoliticadefirma int4 not null,
        validarcertificat bool,
        validarfirma bool,
        primary key (usuariaplicacioconfigid)
    );

    create table pfi_usuariaplicacioperfil (
        usuariaplicacioperfilid int8 not null,
        codi varchar(100) not null unique,
        condicio varchar(4000),
        usrappconfiguracio1id int8 not null,
        usrappconfiguracio2id int8,
        usrappconfiguracio3id int8,
        usrappconfiguracio4id int8,
        usrappconfiguracio5id int8,
        descripcio varchar(500),
        nom varchar(255) not null,
        urlbase varchar(255),
        primary key (usuariaplicacioperfilid)
    );

    create table pfi_usuarientitat (
        usuarientitatid varchar(101) not null,
        actiu bool not null,
        carrec varchar(150),
        custodiainfoid int8,
        email varchar(100),
        entitatid varchar(50) not null,
        logosegellid int8,
        politicacustodia int4 not null,
        politicadepluginfirmaweb int4 not null,
        predeterminat bool not null,
        rebretotselsavisos bool not null,
        usuaripersonaid varchar(50) not null,
        primary key (usuarientitatid),
        unique (usuaripersonaid, entitatid, carrec)
    );

    create table pfi_usuarientitatfavorit (
        id int8 not null,
        favoritid varchar(101) not null,
        origenid varchar(101) not null,
        primary key (id),
        unique (origenid, favoritid)
    );

    create table pfi_usuaripersona (
        usuaripersonaid varchar(50) not null,
        contrasenya varchar(255),
        email varchar(100) not null,
        idiomaid varchar(5) not null,
        llinatges varchar(100) not null,
        nif varchar(9) not null,
        nom varchar(50) not null,
        rubricaid int8,
        usuariintern bool not null,
        primary key (usuaripersonaid),
        unique (nif, usuariintern)
    );

    create index pfi_annex_petdefirmaid_fk_i on pfi_annex (peticiodefirmaid);

    create index pfi_annex_pk_i on pfi_annex (annexid);

    create index pfi_annex_fitxerid_fk_i on pfi_annex (fitxerid);

    alter table pfi_annex 
        add constraint pfi_annex_petifirma_fk 
        foreign key (peticiodefirmaid) 
        references pfi_peticiodefirma;

    alter table pfi_annex 
        add constraint pfi_annex_fitxer_fk 
        foreign key (fitxerid) 
        references pfi_fitxer;

    create index pfi_annexfirmat_fitxerid_fk_i on pfi_annexfirmat (fitxerid);

    create index pfi_annexfirmat_annexid_fk_i on pfi_annexfirmat (annexid);

    create index pfi_annexfirmat_pk_i on pfi_annexfirmat (annexfirmatid);

    create index pfi_annexfirmat_firmaid_fk_i on pfi_annexfirmat (firmaid);

    alter table pfi_annexfirmat 
        add constraint pfi_anexfirmat_annex_fk 
        foreign key (annexid) 
        references pfi_annex;

    alter table pfi_annexfirmat 
        add constraint pfi_anexfirmat_fitxer_fk 
        foreign key (fitxerid) 
        references pfi_fitxer;

    alter table pfi_annexfirmat 
        add constraint pfi_anexfirmat_firma_fk 
        foreign key (firmaid) 
        references pfi_firma;

    create index pfi_bitacola_pk_i on pfi_bitacola (bitacolaid);

    create index pfi_blocfirmes_fluxid_fk_i on pfi_blocdefirmes (fluxdefirmesid);

    create index pfi_blocdefirmes_pk_i on pfi_blocdefirmes (blocdefirmesid);

    alter table pfi_blocdefirmes 
        add constraint pfi_blocfirmes_fluxfirmes_fk 
        foreign key (fluxdefirmesid) 
        references pfi_fluxdefirmes;

    create index pfi_codibarres_pk_i on pfi_codibarres (codibarresid);

    create index pfi_colaboraciodelegacio_pk_i on pfi_colaboraciodelegacio (colaboraciodelegacioid);

    create index pfi_colabdeleg_fitautoid_fk_i on pfi_colaboraciodelegacio (fitxerautoritzacioid);

    create index pfi_colabdeleg_destid_fk_i on pfi_colaboraciodelegacio (destinatariid);

    create index pfi_colabdeleg_coldelid_fk_i on pfi_colaboraciodelegacio (colaboradordelegatid);

    alter table pfi_colaboraciodelegacio 
        add constraint pfi_colabdeleg_usrentitat_d_fk 
        foreign key (destinatariid) 
        references pfi_usuarientitat;

    alter table pfi_colaboraciodelegacio 
        add constraint pfi_colabdeleg_usrentitat_c_fk 
        foreign key (colaboradordelegatid) 
        references pfi_usuarientitat;

    alter table pfi_colaboraciodelegacio 
        add constraint pfi_colabdeleg_fitxer_fk 
        foreign key (fitxerautoritzacioid) 
        references pfi_fitxer;

    create index pfi_custodia_codbarrpos_fk_i on pfi_custodiainfo (codibarresposiciopaginaid);

    create index pfi_custodia_usrentid_fk_i on pfi_custodiainfo (usuarientitatid);

    create index pfi_custodiainfo_pk_i on pfi_custodiainfo (custodiainfoid);

    create index pfi_custodiainfo_pluginid_fk_i on pfi_custodiainfo (pluginid);

    create index pfi_custodia_usrappid_fk_i on pfi_custodiainfo (usuariaplicacioid);

    create index pfi_custodia_msgpospagid_fk_i on pfi_custodiainfo (missatgeposiciopaginaid);

    create index pfi_custodia_codibarid_fk_i on pfi_custodiainfo (codibarresid);

    create index pfi_custodia_entitatid_fk_i on pfi_custodiainfo (entitatid);

    alter table pfi_custodiainfo 
        add constraint pfi_custodia_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_custodiainfo 
        add constraint pfi_custodia_entitat_fk 
        foreign key (entitatid) 
        references pfi_entitat;

    alter table pfi_custodiainfo 
        add constraint pfi_custodia_plugin_fk 
        foreign key (pluginid) 
        references pfi_plugin;

    alter table pfi_custodiainfo 
        add constraint pfi_custodia_codibarres_fk 
        foreign key (codibarresid) 
        references pfi_codibarres;

    alter table pfi_custodiainfo 
        add constraint pfi_custodia_usrapp_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

    create index pfi_entitat_motiudele_fk_i on pfi_entitat (motiudelegacioid);

    create index pfi_entitat_pk_i on pfi_entitat (entitatid);

    create index pfi_entitat_pluginvalcer_fk_i on pfi_entitat (pluginvalidacertificatid);

    create index pfi_entitat_pluginrubri_fk_i on pfi_entitat (pluginrubricaid);

    create index pfi_entitat_pdfautoriid_fk_i on pfi_entitat (pdfautoritzaciodelegacioid);

    create index pfi_entitat_usrappid_fk_i on pfi_entitat (usuariaplicacioid);

    create index pfi_entitat_firmatper_fk_i on pfi_entitat (firmatperformatid);

    create index pfi_entitat_faviconid_fk_i on pfi_entitat (faviconid);

    create index pfi_entitat_algofirma_fk_i on pfi_entitat (algorismedefirmaid);

    create index pfi_entitat_pluginvalfir_fk_i on pfi_entitat (pluginvalidafirmesid);

    create index pfi_entitat_custodiadef_fk_i on pfi_entitat (custodiainfoid);

    create index pfi_entitat_segelltemps_fk_i on pfi_entitat (pluginid);

    create index pfi_entitat_logosegellid_fk_i on pfi_entitat (logosegellid);

    create index pfi_entitat_logowebpeuid_fk_i on pfi_entitat (logowebpeuid);

    create index pfi_entitat_logowebid_fk_i on pfi_entitat (logowebid);

    alter table pfi_entitat 
        add constraint pfi_entitat_traduccio_firm_fk 
        foreign key (firmatperformatid) 
        references pfi_traduccio;

    alter table pfi_entitat 
        add constraint pfi_entitat_fitxer_lope_fk 
        foreign key (logowebpeuid) 
        references pfi_fitxer;

    alter table pfi_entitat 
        add constraint pfi_entitat_plugin_vafi_fk 
        foreign key (pluginvalidafirmesid) 
        references pfi_plugin;

    alter table pfi_entitat 
        add constraint pfi_entitat_fitxer_lose_fk 
        foreign key (logosegellid) 
        references pfi_fitxer;

    alter table pfi_entitat 
        add constraint pfi_entitat_fitxer_loca_fk 
        foreign key (logowebid) 
        references pfi_fitxer;

    alter table pfi_entitat 
        add constraint pfi_entitat_fitxer_icon_fk 
        foreign key (faviconid) 
        references pfi_fitxer;

    alter table pfi_entitat 
        add constraint pfi_entitat_usrapp_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

    alter table pfi_entitat 
        add constraint pfi_entitat_traduccio_moti_fk 
        foreign key (motiudelegacioid) 
        references pfi_traduccio;

    alter table pfi_entitat 
        add constraint pfi_entitat_plugin_cert_fk 
        foreign key (pluginvalidacertificatid) 
        references pfi_plugin;

    alter table pfi_entitat 
        add constraint pfi_entitat_fitxer_pdfd_fk 
        foreign key (pdfautoritzaciodelegacioid) 
        references pfi_fitxer;

    alter table pfi_entitat 
        add constraint pfi_entitat_plugin_fk 
        foreign key (pluginid) 
        references pfi_plugin;

    alter table pfi_entitat 
        add constraint pfi_entitat_plugin_rubr_fk 
        foreign key (pluginrubricaid) 
        references pfi_plugin;

    alter table pfi_entitat 
        add constraint pfi_entitat_custodia_fk 
        foreign key (custodiainfoid) 
        references pfi_custodiainfo;

    create index pfi_estadistica_pk_i on pfi_estadistica (estadisticaid);

    create index pfi_estadistica_entitatid_fk_i on pfi_estadistica (entitatid);

    alter table pfi_estadistica 
        add constraint pfi_estadis_entitat_fk 
        foreign key (entitatid) 
        references pfi_entitat;

    create index pfi_estatdefirma_firmaid_fk_i on pfi_estatdefirma (firmaid);

    create index pfi_estatdefirma_pk_i on pfi_estatdefirma (estatdefirmaid);

    create index pfi_estatfirma_estatinid_fk_i on pfi_estatdefirma (tipusestatdefirmainicialid);

    create index pfi_estatfirma_coladele_fk_i on pfi_estatdefirma (colaboraciodelegacioid);

    create index pfi_estatfirma_usrentid_fk_i on pfi_estatdefirma (usuarientitatid);

    create index pfi_estatfirma_estatid_fk_i on pfi_estatdefirma (tipusestatdefirmafinalid);

    alter table pfi_estatdefirma 
        add constraint pfi_estatfirma_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_estatdefirma 
        add constraint pfi_estatfirma_colabdeleg_fk 
        foreign key (colaboraciodelegacioid) 
        references pfi_colaboraciodelegacio;

    alter table pfi_estatdefirma 
        add constraint pfi_estatfirma_firma_fk 
        foreign key (firmaid) 
        references pfi_firma;

    create index pfi_firma_blocdefirmaid_fk_i on pfi_firma (blocdefirmaid);

    create index pfi_firma_fitxerfirmatid_fk_i on pfi_firma (fitxerfirmatid);

    create index pfi_firma_estatfirmaid_fk_i on pfi_firma (tipusestatdefirmafinalid);

    create index pfi_firma_pk_i on pfi_firma (firmaid);

    create index pfi_firma_destinatariid_fk_i on pfi_firma (destinatariid);

    alter table pfi_firma 
        add constraint pfi_firma_fitxer_fk 
        foreign key (fitxerfirmatid) 
        references pfi_fitxer;

    alter table pfi_firma 
        add constraint pfi_firma_usrentitat_fk 
        foreign key (destinatariid) 
        references pfi_usuarientitat;

    alter table pfi_firma 
        add constraint pfi_firma_blocfirmes_fk 
        foreign key (blocdefirmaid) 
        references pfi_blocdefirmes;

    create index pfi_fitxer_pk_i on pfi_fitxer (fitxerid);

    create index pfi_fluxdefirmes_pk_i on pfi_fluxdefirmes (fluxdefirmesid);

    create index pfi_grupentitat_entitatid_fk_i on pfi_grupentitat (entitatid);

    create index pfi_grupentitat_pk_i on pfi_grupentitat (grupentitatid);

    alter table pfi_grupentitat 
        add constraint pfi_grupentita_entitat_fk 
        foreign key (entitatid) 
        references pfi_entitat;

    create index pfi_grupusrent_usrentid_fk_i on pfi_grupentitatusuarientitat (usuarientitatid);

    create index pfi_grupusrent_grupentid_fk_i on pfi_grupentitatusuarientitat (grupentitatid);

    create index pfi_grupusrent_pk_i on pfi_grupentitatusuarientitat (grupentitatusuarientitatid);

    alter table pfi_grupentitatusuarientitat 
        add constraint pfi_grupusrent_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_grupentitatusuarientitat 
        add constraint pfi_grupusrent_grupentita_fk 
        foreign key (grupentitatid) 
        references pfi_grupentitat;

    create index pfi_idioma_pk_i on pfi_idioma (idiomaid);

    create index pfi_metadada_tipusmetaid_fk_i on pfi_metadada (tipusmetadadaid);

    create index pfi_metadada_peticioid_fk_i on pfi_metadada (peticiodefirmaid);

    create index pfi_metadada_pk_i on pfi_metadada (metadadaid);

    alter table pfi_metadada 
        add constraint pfi_metadada_petifirma_fk 
        foreign key (peticiodefirmaid) 
        references pfi_peticiodefirma;

    create index pfi_mofitido_modfirma_fk_i on pfi_modulfirmapertipusdoc (pluginid);

    create index pfi_modulfirmapertipusdoc_pk_i on pfi_modulfirmapertipusdoc (id);

    create index pfi_mofitido_tipusdoc_fk_i on pfi_modulfirmapertipusdoc (tipusdocumentid);

    alter table pfi_modulfirmapertipusdoc 
        add constraint pfi_mofitido_tipusdoc_fk 
        foreign key (tipusdocumentid) 
        references pfi_tipusdocument;

    alter table pfi_modulfirmapertipusdoc 
        add constraint pfi_mofitido_plugin_fk 
        foreign key (pluginid) 
        references pfi_plugin;

    create index pfi_notifica_peticioid_fk_i on pfi_notificacio (peticiodefirmaid);

    create index pfi_notificacio_pk_i on pfi_notificacio (notificacioid);

    create index pfi_notifica_tiponotiid_fk_i on pfi_notificacio (tipusnotificacioid);

    alter table pfi_notificacio 
        add constraint pfi_notifica_tipnotific_fk 
        foreign key (tipusnotificacioid) 
        references pfi_tipusnotificacio;

    create index pfi_perfilsperusrapp_pk_i on pfi_perfilsperusrapp (perfilsperusrappid);

    create index pfi_perfilsua_usuappid_fk_i on pfi_perfilsperusrapp (usuariaplicacioid);

    create index pfi_perfilsua_perfilid_fk_i on pfi_perfilsperusrapp (usuariaplicacioperfilid);

    alter table pfi_perfilsperusrapp 
        add constraint pfi_perfilsua_perfilapp_p_fk 
        foreign key (usuariaplicacioperfilid) 
        references pfi_usuariaplicacioperfil;

    alter table pfi_perfilsperusrapp 
        add constraint pfi_perfilsua_usrapp_usr_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

    create index pfi_permisgrpl_fluxid_fk_i on pfi_permisgrupplantilla (fluxdefirmesid);

    create index pfi_permisgrpl_grupentid_fk_i on pfi_permisgrupplantilla (grupentitatid);

    create index pfi_permisgrupplantilla_pk_i on pfi_permisgrupplantilla (permisgrupplantillaid);

    alter table pfi_permisgrupplantilla 
        add constraint pfi_permisgrpl_grupentita_fk 
        foreign key (grupentitatid) 
        references pfi_grupentitat;

    alter table pfi_permisgrupplantilla 
        add constraint pfi_permisgrpl_plantiflfi_fk 
        foreign key (fluxdefirmesid) 
        references pfi_plantillafluxdefirmes;

    create index pfi_permisuspl_usrentid_fk_i on pfi_permisusuariplantilla (usuarientitatid);

    create index pfi_permisuspl_fluxid_fk_i on pfi_permisusuariplantilla (fluxdefirmesid);

    create index pfi_permisusuariplantilla_pk_i on pfi_permisusuariplantilla (permisusuariplantillaid);

    alter table pfi_permisusuariplantilla 
        add constraint pfi_permisuspl_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_permisusuariplantilla 
        add constraint pfi_permisuspl_plantiflfi_fk 
        foreign key (fluxdefirmesid) 
        references pfi_plantillafluxdefirmes;

    create index pfi_petifirma_solipers3_fk_i on pfi_peticiodefirma (solicitantpersona3id);

    create index pfi_petifirma_tipusdocid_fk_i on pfi_peticiodefirma (tipusdocumentid);

    create index pfi_peticiodefirma_pk_i on pfi_peticiodefirma (peticiodefirmaid);

    create index pfi_petifirma_custinfoid_fk_i on pfi_peticiodefirma (custodiainfoid);

    create index pfi_petifirma_logosegid_fk_i on pfi_peticiodefirma (logosegellid);

    create index pfi_petifirma_usrentiid_fk_i on pfi_peticiodefirma (usuarientitatid);

    create index pfi_petifirma_conffirma_fk_i on pfi_peticiodefirma (configuraciodefirmaid);

    create index pfi_petifirma_prioritatid_fk_i on pfi_peticiodefirma (prioritatid);

    create index pfi_petifirma_fitxeadaid_fk_i on pfi_peticiodefirma (fitxeradaptatid);

    create index pfi_petifirma_idiomaid_fk_i on pfi_peticiodefirma (idiomaid);

    create index pfi_petifirma_solipers2_fk_i on pfi_peticiodefirma (solicitantpersona2id);

    create index pfi_petifirma_usrappid_fk_i on pfi_peticiodefirma (usuariaplicacioid);

    create index pfi_petifirma_fitxerid_fk_i on pfi_peticiodefirma (fitxerafirmarid);

    create index pfi_petifirma_fluxid_fk_i on pfi_peticiodefirma (fluxdefirmesid);

    create index pfi_petifirma_algofirmid_fk_i on pfi_peticiodefirma (algorismedefirmaid);

    create index pfi_petifirma_firmaori_fk_i on pfi_peticiodefirma (firmaoriginaldetachedid);

    create index pfi_petifirma_estatid_fk_i on pfi_peticiodefirma (tipusestatpeticiodefirmaid);

    create index pfi_petifirma_tipofirmid_fk_i on pfi_peticiodefirma (tipusfirmaid);

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_usrentitat_2_fk 
        foreign key (solicitantpersona2id) 
        references pfi_usuarientitat;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_fitxer_log_fk 
        foreign key (logosegellid) 
        references pfi_fitxer;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_usrapp_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_fitxer_ada_fk 
        foreign key (fitxeradaptatid) 
        references pfi_fitxer;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_usrentitat_3_fk 
        foreign key (solicitantpersona3id) 
        references pfi_usuarientitat;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_confapp_fk 
        foreign key (configuraciodefirmaid) 
        references pfi_usuariaplicacioconfig;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_tipusdoc_fk 
        foreign key (tipusdocumentid) 
        references pfi_tipusdocument;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_fitxer_ori_fk 
        foreign key (firmaoriginaldetachedid) 
        references pfi_fitxer;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_fitxer_fir_fk 
        foreign key (fitxerafirmarid) 
        references pfi_fitxer;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_fluxfirmes_fk 
        foreign key (fluxdefirmesid) 
        references pfi_fluxdefirmes;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_idioma_fk 
        foreign key (idiomaid) 
        references pfi_idioma;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_custodia_fk 
        foreign key (custodiainfoid) 
        references pfi_custodiainfo;

    create index pfi_plantiflfi_usrappid_fk_i on pfi_plantillafluxdefirmes (usuariaplicacioid);

    create index pfi_plantiflfi_usrentiid_fk_i on pfi_plantillafluxdefirmes (usuarientitatid);

    create index pfi_plantillafluxdefirmes_pk_i on pfi_plantillafluxdefirmes (fluxdefirmesid);

    alter table pfi_plantillafluxdefirmes 
        add constraint pfi_plantiflfi_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_plantillafluxdefirmes 
        add constraint pfi_plantiflfi_usrapp_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

    create index pfi_plugin_nomid_fk_i on pfi_plugin (nomid);

    create index pfi_plugin_pk_i on pfi_plugin (pluginid);

    create index pfi_plugin_desccurtaid_fk_i on pfi_plugin (descripciocurtaid);

    create index pfi_plugin_entitatid_fk_i on pfi_plugin (entitatid);

    alter table pfi_plugin 
        add constraint pfi_plugin_entitat_fk 
        foreign key (entitatid) 
        references pfi_entitat;

    alter table pfi_plugin 
        add constraint pfi_plugin_traduccio_nom_fk 
        foreign key (nomid) 
        references pfi_traduccio;

    alter table pfi_plugin 
        add constraint pfi_plugin_traduccio_desc_fk 
        foreign key (descripciocurtaid) 
        references pfi_traduccio;

    create index pfi_plugcrida_retorfitxer_fk_i on pfi_plugincridada (retornfitxerid);

    create index pfi_plugincridada_pk_i on pfi_plugincridada (plugincridadaid);

    create index pfi_plugcrida_pluginid_fk_i on pfi_plugincridada (pluginid);

    create index pfi_plugcrida_paramfitxer_fk_i on pfi_plugincridada (parametresfitxerid);

    create index pfi_plugcrida_entitatid_fk_i on pfi_plugincridada (entitatid);

    alter table pfi_plugincridada 
        add constraint pfi_plugcrida_fitxer_retor_fk 
        foreign key (retornfitxerid) 
        references pfi_fitxer;

    alter table pfi_plugincridada 
        add constraint pfi_plugcrida_entitat_fk 
        foreign key (entitatid) 
        references pfi_entitat;

    alter table pfi_plugincridada 
        add constraint pfi_plugcrida_fitxer_param_fk 
        foreign key (parametresfitxerid) 
        references pfi_fitxer;

    alter table pfi_plugincridada 
        add constraint pfi_plugcrida_plugin_fk 
        foreign key (pluginid) 
        references pfi_plugin;

    create index pfi_pfwpua_usrappid_fk_i on pfi_pluginfirmawebperusrapp (usuariaplicacioid);

    create index pfi_pfwpua_pk_i on pfi_pluginfirmawebperusrapp (pluginfirmawebperusrappid);

    create index pfi_pfwpua_plugin_fk_i on pfi_pluginfirmawebperusrapp (pluginfirmawebid);

    alter table pfi_pluginfirmawebperusrapp 
        add constraint pfi_pfwpua_plugin_fk 
        foreign key (pluginfirmawebid) 
        references pfi_plugin;

    alter table pfi_pluginfirmawebperusrapp 
        add constraint pfi_pfwpua_usrapp_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

    create index pfi_pfwpue_plugin_fk_i on pfi_pluginfirmawebperusrent (pluginfirmawebid);

    create index pfi_pfwpue_usrentid_fk_i on pfi_pluginfirmawebperusrent (usuarientitatid);

    create index pfi_pfwpue_pk_i on pfi_pluginfirmawebperusrent (pluginfirmawebperusrentid);

    alter table pfi_pluginfirmawebperusrent 
        add constraint pfi_pfwpue_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_pluginfirmawebperusrent 
        add constraint pfi_pfwpue_plugin_fk 
        foreign key (pluginfirmawebid) 
        references pfi_plugin;

    create index pfi_propietat_entitatid_fk_i on pfi_propietatglobal (entitatid);

    create index pfi_propietatglobal_pk_i on pfi_propietatglobal (propietatglobalid);

    alter table pfi_propietatglobal 
        add constraint pfi_propietat_entitat_fk 
        foreign key (entitatid) 
        references pfi_entitat;

    create index pfi_rebreavis_usrentid_fk_i on pfi_rebreavis (usuarientitatid);

    create index pfi_rebreavis_tiponotiid_fk_i on pfi_rebreavis (tipusnotificacioid);

    create index pfi_rebreavis_pk_i on pfi_rebreavis (id);

    alter table pfi_rebreavis 
        add constraint pfi_rebreavis_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_rebreavis 
        add constraint pfi_rebreavis_tipnotific_fk 
        foreign key (tipusnotificacioid) 
        references pfi_tipusnotificacio;

    create index pfi_revisordefirma_pk_i on pfi_revisordefirma (revisordefirmaid);

    create index pfi_revfirma_firmaid_fk_i on pfi_revisordefirma (firmaid);

    create index pfi_revfirma_usrentitat_fk_i on pfi_revisordefirma (usuarientitatid);

    alter table pfi_revisordefirma 
        add constraint pfi_revfirma_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_revisordefirma 
        add constraint pfi_revfirma_firma_fk 
        foreign key (firmaid) 
        references pfi_firma;

    create index pfi_role_pk_i on pfi_role (roleid);

    create index pfi_roleusrent_usrentid_fk_i on pfi_roleusuarientitat (usuarientitatid);

    create index pfi_roleusrent_roleid_fk_i on pfi_roleusuarientitat (roleid);

    create index pfi_roleusuarientitat_pk_i on pfi_roleusuarientitat (id);

    alter table pfi_roleusuarientitat 
        add constraint pfi_roleusrent_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_roleusuarientitat 
        add constraint pfi_roleusrent_role_fk 
        foreign key (roleid) 
        references pfi_role;

    create index pfi_tipusdocument_nom_fk_i on pfi_tipusdocument (nom);

    create index pfi_tipusdoc_usuariappid_fk_i on pfi_tipusdocument (usuariaplicacioid);

    create index pfi_tipusdocument_pk_i on pfi_tipusdocument (tipusdocumentid);

    alter table pfi_tipusdocument 
        add constraint pfi_tipusdoc_traduccio_fk 
        foreign key (nom) 
        references pfi_traduccio;

    alter table pfi_tipusdocument 
        add constraint pfi_tipusdoc_usrapp_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

    create index pfi_tipusdoccd_coldelid_fk_i on pfi_tipusdocumentcoladele (colaboraciodelegacioid);

    create index pfi_tipusdoccd_tipusdocid_fk_i on pfi_tipusdocumentcoladele (tipusdocumentid);

    create index pfi_tipusdocumentcoladele_pk_i on pfi_tipusdocumentcoladele (id);

    alter table pfi_tipusdocumentcoladele 
        add constraint pfi_tipusdoccd_tipusdoc_fk 
        foreign key (tipusdocumentid) 
        references pfi_tipusdocument;

    alter table pfi_tipusdocumentcoladele 
        add constraint pfi_tipusdoccd_colabdeleg_fk 
        foreign key (colaboraciodelegacioid) 
        references pfi_colaboraciodelegacio;

    create index pfi_tipusnotificacio_pk_i on pfi_tipusnotificacio (tipusnotificacioid);

    create index pfi_traduccio_pk_i on pfi_traduccio (traduccioid);

    alter table pfi_traducciomap 
        add constraint pfi_traducmap_traduccio_fk 
        foreign key (traducciomapid) 
        references pfi_traduccio;

    create index pfi_usrapp_entitatid_fk_i on pfi_usuariaplicacio (entitatid);

    create index pfi_usuariaplicacio_pk_i on pfi_usuariaplicacio (usuariaplicacioid);

    create index pfi_usrapp_custodia_fk_i on pfi_usuariaplicacio (custodiainfoid);

    create index pfi_usrapp_idiomaid_fk_i on pfi_usuariaplicacio (idiomaid);

    create index pfi_usrapp_logosegellid_fk_i on pfi_usuariaplicacio (logosegellid);

    alter table pfi_usuariaplicacio 
        add constraint pfi_usrapp_entitat_fk 
        foreign key (entitatid) 
        references pfi_entitat;

    alter table pfi_usuariaplicacio 
        add constraint pfi_usrapp_fitxer_fk 
        foreign key (logosegellid) 
        references pfi_fitxer;

    alter table pfi_usuariaplicacio 
        add constraint pfi_usrapp_idioma_fk 
        foreign key (idiomaid) 
        references pfi_idioma;

    alter table pfi_usuariaplicacio 
        add constraint pfi_usrapp_custodia_fk 
        foreign key (custodiainfoid) 
        references pfi_custodiainfo;

    create index pfi_confapp_motiudele_fk_i on pfi_usuariaplicacioconfig (motiudelegacioid);

    create index pfi_confapp_algofirma_fk_i on pfi_usuariaplicacioconfig (algorismedefirmaid);

    create index pfi_usuariaplicacioconfig_pk_i on pfi_usuariaplicacioconfig (usuariaplicacioconfigid);

    create index pfi_confapp_plugsegell_fk_i on pfi_usuariaplicacioconfig (pluginsegellatid);

    create index pfi_confapp_tipusfirma_fk_i on pfi_usuariaplicacioconfig (tipusfirmaid);

    create index pfi_confapp_firmatper_fk_i on pfi_usuariaplicacioconfig (firmatperformatid);

    create index pfi_confapp_firmaserv_fk_i on pfi_usuariaplicacioconfig (pluginfirmaservidorid);

    create index pfi_confapp_entitatid_fk_i on pfi_usuariaplicacioconfig (entitatid);

    alter table pfi_usuariaplicacioconfig 
        add constraint pfi_confapp_traduccio_moti_fk 
        foreign key (motiudelegacioid) 
        references pfi_traduccio;

    alter table pfi_usuariaplicacioconfig 
        add constraint pfi_confapp_traduccio_firm_fk 
        foreign key (firmatperformatid) 
        references pfi_traduccio;

    alter table pfi_usuariaplicacioconfig 
        add constraint pfi_confapp_entitat_ent_fk 
        foreign key (entitatid) 
        references pfi_entitat;

    alter table pfi_usuariaplicacioconfig 
        add constraint pfi_confapp_plugin_fsrv_fk 
        foreign key (pluginfirmaservidorid) 
        references pfi_plugin;

    alter table pfi_usuariaplicacioconfig 
        add constraint pfi_confapp_plugin_seg_fk 
        foreign key (pluginsegellatid) 
        references pfi_plugin;

    create index pfi_perfilapp_appconf1id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio1id);

    create index pfi_perfilapp_appconf2id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio2id);

    create index pfi_usuariaplicacioperfil_pk_i on pfi_usuariaplicacioperfil (usuariaplicacioperfilid);

    create index pfi_perfilapp_appconf4id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio4id);

    create index pfi_perfilapp_appconf3id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio3id);

    create index pfi_perfilapp_appconf5id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio5id);

    alter table pfi_usuariaplicacioperfil 
        add constraint pfi_perfilapp_confapp_5_fk 
        foreign key (usrappconfiguracio5id) 
        references pfi_usuariaplicacioconfig;

    alter table pfi_usuariaplicacioperfil 
        add constraint pfi_perfilapp_confapp_1_fk 
        foreign key (usrappconfiguracio1id) 
        references pfi_usuariaplicacioconfig;

    alter table pfi_usuariaplicacioperfil 
        add constraint pfi_perfilapp_confapp_4_fk 
        foreign key (usrappconfiguracio4id) 
        references pfi_usuariaplicacioconfig;

    alter table pfi_usuariaplicacioperfil 
        add constraint pfi_perfilapp_confapp_2_fk 
        foreign key (usrappconfiguracio2id) 
        references pfi_usuariaplicacioconfig;

    alter table pfi_usuariaplicacioperfil 
        add constraint pfi_perfilapp_confapp_3_fk 
        foreign key (usrappconfiguracio3id) 
        references pfi_usuariaplicacioconfig;

    create index pfi_usrentitat_entitatid_fk_i on pfi_usuarientitat (entitatid);

    create index pfi_usuarientitat_pk_i on pfi_usuarientitat (usuarientitatid);

    create index pfi_usrentitat_logosegid_fk_i on pfi_usuarientitat (logosegellid);

    create index pfi_usrentitat_personaid_fk_i on pfi_usuarientitat (usuaripersonaid);

    create index pfi_usrentitat_custinfo_fk_i on pfi_usuarientitat (custodiainfoid);

    alter table pfi_usuarientitat 
        add constraint pfi_usrentitat_entitat_fk 
        foreign key (entitatid) 
        references pfi_entitat;

    alter table pfi_usuarientitat 
        add constraint pfi_usrentitat_fitxer_fk 
        foreign key (logosegellid) 
        references pfi_fitxer;

    alter table pfi_usuarientitat 
        add constraint pfi_usrentitat_persona_fk 
        foreign key (usuaripersonaid) 
        references pfi_usuaripersona;

    alter table pfi_usuarientitat 
        add constraint pfi_usrentitat_custodia_fk 
        foreign key (custodiainfoid) 
        references pfi_custodiainfo;

    create index pfi_usuarientitatfavorit_pk_i on pfi_usuarientitatfavorit (id);

    create index pfi_favorit_origenid_fk_i on pfi_usuarientitatfavorit (origenid);

    create index pfi_favorit_favoritid_fk_i on pfi_usuarientitatfavorit (favoritid);

    alter table pfi_usuarientitatfavorit 
        add constraint pfi_favorit_usrentitat_fav_fk 
        foreign key (favoritid) 
        references pfi_usuarientitat;

    alter table pfi_usuarientitatfavorit 
        add constraint pfi_favorit_usrentitat_ori_fk 
        foreign key (origenid) 
        references pfi_usuarientitat;

    create index pfi_usuaripersona_nif_i on pfi_usuaripersona (nif);

    create index pfi_persona_idiomaid_fk_i on pfi_usuaripersona (idiomaid);

    create index pfi_usuaripersona_pk_i on pfi_usuaripersona (usuaripersonaid);

    create index pfi_persona_rubricaid_fk_i on pfi_usuaripersona (rubricaid);

    alter table pfi_usuaripersona 
        add constraint pfi_persona_fitxer_fk 
        foreign key (rubricaid) 
        references pfi_fitxer;

    alter table pfi_usuaripersona 
        add constraint pfi_persona_idioma_fk 
        foreign key (idiomaid) 
        references pfi_idioma;

    create sequence pfi_portafib_seq;
