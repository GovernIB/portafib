
    create table pfi_annex (
        annexid number(19,0) not null,
        adjuntar number(1,0) not null,
        firmar number(1,0) not null,
        fitxerid number(19,0) not null,
        peticiodefirmaid number(19,0) not null
    );

    create table pfi_annexfirmat (
        annexfirmatid number(19,0) not null,
        annexid number(19,0) not null,
        firmaid number(19,0) not null,
        fitxerid number(19,0) not null
    );

    create table pfi_bitacola (
        bitacolaid number(19,0) not null,
        data timestamp not null,
        descripcio varchar2(255 char) not null,
        peticiodefirmaid number(19,0) not null,
        usuarientitatid varchar2(101 char)
    );

    create table pfi_blocdefirmes (
        blocdefirmesid number(19,0) not null,
        datafinalitzacio timestamp,
        fluxdefirmesid number(19,0) not null,
        minimdefirmes number(10,0) not null,
        ordre number(10,0) not null
    );

    create table pfi_codibarres (
        codibarresid varchar2(255 char) not null,
        descripcio varchar2(255 char),
        nom varchar2(50 char) not null
    );

    create table pfi_colaboraciodelegacio (
        colaboraciodelegacioid number(19,0) not null,
        activa number(1,0) not null,
        colaboradordelegatid varchar2(101 char) not null,
        datafi timestamp,
        datainici timestamp not null,
        descripcio varchar2(255 char),
        destinatariid varchar2(101 char) not null,
        esdelegat number(1,0) not null,
        fitxerautoritzacioid number(19,0),
        motiu varchar2(60 char) not null,
        motiudeshabilitada varchar2(255 char),
        revisor number(1,0) not null
    );

    create table pfi_custodiainfo (
        custodiainfoid number(19,0) not null,
        codibarresid varchar2(255 char) not null,
        codibarresposiciopaginaid number(19,0) not null,
        codibarrestext varchar2(255 char) not null,
        custodiapluginid varchar2(255 char),
        custodiapluginparametres varchar2(3000 char),
        custodiar number(1,0) not null,
        datacustodia timestamp,
        editable number(1,0) not null,
        entitatid varchar2(50 char),
        missatge varchar2(3000 char) not null,
        missatgeposiciopaginaid number(19,0) not null,
        nomplantilla varchar2(255 char),
        pagines varchar2(255 char) not null,
        pluginid number(19,0) not null,
        titolpeticio varchar2(255 char),
        urlfitxercustodiat varchar2(500 char),
        usuariaplicacioid varchar2(101 char),
        usuarientitatid varchar2(101 char)
    );

    create table pfi_entitat (
        entitatid varchar2(50 char) not null,
        activa number(1,0) not null,
        adrezahtml varchar2(2000 char) not null,
        algorismedefirmaid number(10,0) not null,
        checkcanviatdocfirmat number(1,0) not null,
        comprovarniffirma number(1,0) not null,
        custodiainfoid number(19,0),
        descripcio varchar2(255 char),
        faviconid number(19,0) not null,
        filtrecertificats clob not null,
        firmatperformatid number(19,0),
        logosegellid number(19,0) not null,
        logowebid number(19,0) not null,
        logowebpeuid number(19,0) not null,
        maxfilestosignatsametime number(10,0),
        maxsizefitxeradaptat number(19,0),
        maxuploadsize number(19,0),
        motiudelegacioid number(19,0),
        nom varchar2(50 char) not null,
        pdfautoritzaciodelegacioid number(19,0) not null,
        pluginrubricaid number(19,0),
        pluginid number(19,0),
        pluginvalidacertificatid number(19,0),
        pluginvalidafirmesid number(19,0),
        policyidentifier varchar2(100 char),
        policyidentifierhash clob,
        policyidentifierhashalgorithm varchar2(50 char),
        policyurldocument varchar2(255 char),
        politicacustodia number(10,0) not null,
        segelldetempsviaweb number(10,0) not null,
        politicataulafirmes number(10,0) not null,
        posiciotaulafirmes number(10,0) not null,
        propietatstaulafirmes clob,
        suportemail varchar2(100 char),
        suporttelefon varchar2(50 char),
        suportweb varchar2(250 char),
        uspoliticadefirma number(10,0) not null,
        usuariaplicacioid varchar2(101 char),
        web varchar2(250 char) not null
    );

    create table pfi_estadistica (
        estadisticaid number(19,0) not null,
        data timestamp not null,
        entitatid varchar2(50 char),
        parametres varchar2(3000 char),
        tipus number(10,0) not null,
        usuariaplicacioid varchar2(101 char),
        usuarientitatid varchar2(101 char),
        valor double precision not null
    );

    create table pfi_estatdefirma (
        estatdefirmaid number(19,0) not null,
        colaboraciodelegacioid number(19,0),
        datafi timestamp,
        datainici timestamp not null,
        descripcio varchar2(255 char),
        firmaid number(19,0) not null,
        tipusestatdefirmafinalid number(19,0),
        tipusestatdefirmainicialid number(19,0) not null,
        usuarientitatid varchar2(101 char) not null
    );

    create table pfi_firma (
        firmaid number(19,0) not null,
        blocdefirmaid number(19,0) not null,
        caixa_alt number(10,0),
        caixa_ample number(10,0),
        caixa_pagina number(10,0) not null,
        caixa_x number(10,0),
        caixa_y number(10,0),
        destinatariid varchar2(101 char) not null,
        emissorcertificat varchar2(1000 char),
        fitxerfirmatid number(19,0),
        minimderevisors number(10,0) not null,
        mostrarrubrica number(1,0) not null,
        motiu varchar2(255 char),
        nomcertificat varchar2(1000 char),
        numfirmadocument number(10,0),
        numeroseriecertificat number,
        obligatori number(1,0) not null,
        tipusestatdefirmafinalid number(19,0)
    );

    create table pfi_fitxer (
        fitxerid number(19,0) not null,
        descripcio varchar2(1000 char),
        mime varchar2(255 char) not null,
        nom varchar2(255 char) not null,
        tamany number(19,0) not null
    );

    create table pfi_fluxdefirmes (
        fluxdefirmesid number(19,0) not null,
        nom varchar2(255 char) not null
    );

    create table pfi_grupentitat (
        grupentitatid number(19,0) not null,
        descripcio varchar2(255 char),
        entitatid varchar2(50 char) not null,
        nom varchar2(100 char) not null
    );

    create table pfi_grupentitatusuarientitat (
        grupentitatusuarientitatid number(19,0) not null,
        grupentitatid number(19,0) not null,
        usuarientitatid varchar2(101 char) not null
    );

    create table pfi_idioma (
        idiomaid varchar2(5 char) not null,
        nom varchar2(50 char) not null,
        ordre number(10,0) not null,
        suportat number(1,0) not null
    );

    create table pfi_metadada (
        metadadaid number(19,0) not null,
        descripcio varchar2(1000 char),
        nom varchar2(50 char) not null,
        peticiodefirmaid number(19,0) not null,
        tipusmetadadaid number(10,0) not null,
        valor clob not null
    );

    create table pfi_modulfirmapertipusdoc (
        id number(19,0) not null,
        nom varchar2(100 char) not null,
        pluginid number(19,0) not null,
        tipusdocumentid number(19,0) not null
    );

    create table pfi_notificacio (
        notificacioid number(19,0) not null,
        bloquejada number(1,0),
        datacreacio timestamp not null,
        dataenviament timestamp,
        dataerror timestamp,
        descripcio clob,
        error clob,
        peticiodefirmaid number(19,0) not null,
        reintents number(10,0) not null,
        tipusnotificacioid number(19,0) not null
    );

    create table pfi_perfilsperusrapp (
        perfilsperusrappid number(19,0) not null,
        usuariaplicacioperfilid number(19,0) not null,
        usuariaplicacioid varchar2(50 char) not null
    );

    create table pfi_permisgrupplantilla (
        permisgrupplantillaid number(19,0) not null,
        grupentitatid number(19,0) not null,
        fluxdefirmesid number(19,0) not null
    );

    create table pfi_permisusuariplantilla (
        permisusuariplantillaid number(19,0) not null,
        fluxdefirmesid number(19,0) not null,
        usuarientitatid varchar2(101 char) not null
    );

    create table pfi_peticiodefirma (
        peticiodefirmaid number(19,0) not null,
        algorismedefirmaid number(10,0) not null,
        avisweb number(1,0) not null,
        custodiainfoid number(19,0),
        datacaducitat timestamp not null,
        datafinal timestamp,
        datasolicitud timestamp,
        descripcio varchar2(255 char),
        descripciotipusdocument varchar2(255 char),
        expedientcodi varchar2(255 char),
        expedientnom varchar2(255 char),
        expedienturl varchar2(255 char),
        firmaoriginaldetachedid number(19,0),
        fitxerafirmarid number(19,0),
        fitxeradaptatid number(19,0),
        fluxdefirmesid number(19,0) not null,
        idiomaid varchar2(5 char) not null,
        informacioaddicional varchar2(500 char),
        informacioaddicionalavaluable double precision,
        logosegellid number(19,0),
        modedefirma number(1,0) not null,
        motiu varchar2(255 char) not null,
        motiuderebuig varchar2(255 char),
        posiciotaulafirmesid number(10,0) not null,
        prioritatid number(10,0) not null,
        procedimentcodi varchar2(255 char),
        procedimentnom varchar2(255 char),
        remitentdescripcio varchar2(500 char),
        remitentnom varchar2(100 char) not null,
        segellatdetemps number(1,0) not null,
        usuariaplicacioid varchar2(101 char) not null,
        usuarientitatid varchar2(101 char),
        solicitantpersona2id varchar2(101 char),
        solicitantpersona3id varchar2(101 char),
        tipusdocumentid number(19,0) not null,
        tipusestatpeticiodefirmaid number(10,0) not null,
        tipusfirmaid number(10,0) not null,
        tipusoperaciofirma number(10,0) not null,
        titol varchar2(255 char) not null
    );

    create table pfi_plantillafluxdefirmes (
        fluxdefirmesid number(19,0) not null,
        compartir number(1,0),
        descripcio varchar2(1000 char) not null,
        usuariaplicacioid varchar2(101 char),
        usuarientitatid varchar2(101 char)
    );

    create table pfi_plugin (
        pluginid number(19,0) not null,
        actiu number(1,0) not null,
        classe varchar2(255 char) not null,
        codi varchar2(255 char) not null,
        descripciocurtaid number(19,0) not null,
        entitatid varchar2(50 char),
        nomid number(19,0) not null,
        ordre number(10,0),
        politicadeus number(10,0) not null,
        politicamostrarpropietats number(10,0) not null,
        propertiesadmin clob,
        propertiesentitat clob,
        tipus number(10,0) not null
    );

    create table pfi_plugincridada (
        plugincridadaid number(19,0) not null,
        data timestamp not null,
        entitatid varchar2(50 char),
        metodeplugin varchar2(100 char) not null,
        parametresfitxerid number(19,0),
        parametrestext clob,
        pluginid number(19,0) not null,
        retornfitxerid number(19,0),
        retorntext clob,
        tempsexecucio number(19,0) not null,
        tipusresultat number(10,0) not null
    );

    create table pfi_pluginfirmawebperusrapp (
        pluginfirmawebperusrappid number(19,0) not null,
        accio number(10,0) not null,
        pluginfirmawebid number(19,0) not null,
        usuariaplicacioid varchar2(101 char) not null
    );

    create table pfi_pluginfirmawebperusrent (
        pluginfirmawebperusrentid number(19,0) not null,
        accio number(10,0) not null,
        pluginfirmawebid number(19,0) not null,
        usuarientitatid varchar2(101 char) not null
    );

    create table pfi_propietatglobal (
        propietatglobalid number(19,0) not null,
        clau varchar2(255 char) not null,
        descripcio varchar2(1000 char),
        entitatid varchar2(50 char),
        valor varchar2(255 char)
    );

    create table pfi_rebreavis (
        id number(19,0) not null,
        rebreagrupat number(1,0) not null,
        tipusnotificacioid number(19,0) not null,
        usuarientitatid varchar2(101 char) not null
    );

    create table pfi_revisordefirma (
        revisordefirmaid number(19,0) not null,
        firmaid number(19,0) not null,
        obligatori number(1,0) not null,
        usuarientitatid varchar2(101 char) not null
    );

    create table pfi_role (
        roleid varchar2(50 char) not null,
        descripcio varchar2(255 char),
        nom varchar2(50 char) not null
    );

    create table pfi_roleusuariaplicacio (
        id number(19,0) not null,
        roleid varchar2(50 char) not null,
        usuariaplicacioid varchar2(50 char) not null
    );

    create table pfi_roleusuarientitat (
        id number(19,0) not null,
        roleid varchar2(50 char) not null,
        usuarientitatid varchar2(101 char) not null
    );

    create table pfi_tipusdocument (
        tipusdocumentid number(19,0) not null,
        descripcio varchar2(1000 char),
        nom number(19,0) not null,
        tipusdocumentbaseid number(19,0) not null,
        usuariaplicacioid varchar2(50 char)
    );

    create table pfi_tipusdocumentcoladele (
        id number(19,0) not null,
        colaboraciodelegacioid number(19,0) not null,
        tipusdocumentid number(19,0) not null
    );

    create table pfi_tipusnotificacio (
        tipusnotificacioid number(19,0) not null,
        descripcio varchar2(250 char),
        esavis number(1,0),
        nom varchar2(50 char) not null
    );

    create table pfi_traduccio (
        traduccioid number(19,0) not null
    );

    create table pfi_traducciomap (
        traducciomapid number(19,0) not null,
        valor varchar2(4000 char),
        idiomaid varchar2(255 char)
    );

    create table pfi_usuariaplicacio (
        usuariaplicacioid varchar2(101 char) not null,
        actiu number(1,0) not null,
        callbackurl varchar2(400 char) not null,
        callbackversio number(10,0) not null,
        contrasenya varchar2(50 char),
        custodiainfoid number(19,0),
        descripcio varchar2(255 char),
        emailadmin varchar2(100 char) not null,
        entitatid varchar2(50 char) not null,
        idiomaid varchar2(5 char) not null,
        logosegellid number(19,0),
        politicacustodia number(10,0) not null,
        politicadepluginfirmaweb number(10,0) not null
    );

    create table pfi_usuariaplicacioconfig (
        usuariaplicacioconfigid number(19,0) not null,
        algorismedefirmaid number(10,0),
        checkcanviatdocfirmat number(1,0),
        comprovarniffirma number(1,0),
        entitatid varchar2(50 char) not null,
        filtrecertificats clob,
        firmatperformatid number(19,0),
        htmlperllistarpluginsfirmaweb clob,
        logincertificateid number(19,0),
        modedefirma number(1,0) not null,
        motiudelegacioid number(19,0),
        nom varchar2(255 char) not null,
        pluginfirmaservidorid number(19,0),
        pluginsegellatid number(19,0),
        policyidentifier varchar2(100 char),
        policyidentifierhash varchar2(256 char),
        policyidentifierhashalgorithm varchar2(50 char),
        policyurldocument varchar2(255 char),
        politicasegellatdetemps number(10,0) not null,
        politicataulafirmes number(10,0) not null,
        posiciotaulafirmesid number(10,0) not null,
        propietatstaulafirmes clob,
        tipusfirmaid number(10,0) not null,
        tipusoperaciofirma number(10,0) not null,
        upgradesignformat number(10,0),
        usenfirmaapisimpleservidor number(1,0) not null,
        usenfirmaapisimpleweb number(1,0) not null,
        usenfirmapassarelaservidor number(1,0) not null,
        usenfirmapassarelaweb number(1,0) not null,
        usenfirmaws1 number(1,0) not null,
        usenfirmaws2 number(1,0) not null,
        usenfirmaweb number(1,0) not null,
        uspoliticadefirma number(10,0) not null,
        validarcertificat number(1,0),
        validarfirma number(1,0)
    );

    create table pfi_usuariaplicacioperfil (
        usuariaplicacioperfilid number(19,0) not null,
        codi varchar2(100 char) not null,
        condicio varchar2(4000 char),
        usrappconfiguracio1id number(19,0) not null,
        usrappconfiguracio2id number(19,0),
        usrappconfiguracio3id number(19,0),
        usrappconfiguracio4id number(19,0),
        usrappconfiguracio5id number(19,0),
        descripcio varchar2(500 char),
        nom varchar2(255 char) not null,
        urlbase varchar2(255 char)
    );

    create table pfi_usuarientitat (
        usuarientitatid varchar2(101 char) not null,
        actiu number(1,0) not null,
        carrec varchar2(150 char),
        custodiainfoid number(19,0),
        email varchar2(100 char),
        entitatid varchar2(50 char) not null,
        logosegellid number(19,0),
        politicacustodia number(10,0) not null,
        politicadepluginfirmaweb number(10,0) not null,
        predeterminat number(1,0) not null,
        rebretotselsavisos number(1,0) not null,
        usuaripersonaid varchar2(50 char) not null
    );

    create table pfi_usuarientitatfavorit (
        id number(19,0) not null,
        favoritid varchar2(101 char) not null,
        origenid varchar2(101 char) not null
    );

    create table pfi_usuaripersona (
        usuaripersonaid varchar2(50 char) not null,
        contrasenya varchar2(255 char),
        email varchar2(100 char) not null,
        idiomaid varchar2(5 char) not null,
        llinatges varchar2(100 char) not null,
        nif varchar2(9 char) not null,
        nom varchar2(50 char) not null,
        rubricaid number(19,0),
        usuariintern number(1,0) not null
    );

    create sequence pfi_portafib_seq;


 -- INICI Indexes
    create index pfi_annex_petdefirmaid_fk_i on pfi_annex (peticiodefirmaid);
    create index pfi_annex_pk_i on pfi_annex (annexid);
    create index pfi_annex_fitxerid_fk_i on pfi_annex (fitxerid);
    create index pfi_annexfirmat_fitxerid_fk_i on pfi_annexfirmat (fitxerid);
    create index pfi_annexfirmat_annexid_fk_i on pfi_annexfirmat (annexid);
    create index pfi_annexfirmat_pk_i on pfi_annexfirmat (annexfirmatid);
    create index pfi_annexfirmat_firmaid_fk_i on pfi_annexfirmat (firmaid);
    create index pfi_bitacola_pk_i on pfi_bitacola (bitacolaid);
    create index pfi_bitacola_usrentid_fk_i on pfi_bitacola (usuarientitatid);
    create index pfi_bitacola_peticid_fk_i on pfi_bitacola (peticiodefirmaid);
    create index pfi_blocfirmes_fluxid_fk_i on pfi_blocdefirmes (fluxdefirmesid);
    create index pfi_blocdefirmes_pk_i on pfi_blocdefirmes (blocdefirmesid);
    create index pfi_codibarres_pk_i on pfi_codibarres (codibarresid);
    create index pfi_colaboraciodelegacio_pk_i on pfi_colaboraciodelegacio (colaboraciodelegacioid);
    create index pfi_colabdeleg_fitautoid_fk_i on pfi_colaboraciodelegacio (fitxerautoritzacioid);
    create index pfi_colabdeleg_destid_fk_i on pfi_colaboraciodelegacio (destinatariid);
    create index pfi_colabdeleg_coldelid_fk_i on pfi_colaboraciodelegacio (colaboradordelegatid);
    create index pfi_custodia_codbarrpos_fk_i on pfi_custodiainfo (codibarresposiciopaginaid);
    create index pfi_custodia_usrentid_fk_i on pfi_custodiainfo (usuarientitatid);
    create index pfi_custodiainfo_pk_i on pfi_custodiainfo (custodiainfoid);
    create index pfi_custodiainfo_pluginid_fk_i on pfi_custodiainfo (pluginid);
    create index pfi_custodia_usrappid_fk_i on pfi_custodiainfo (usuariaplicacioid);
    create index pfi_custodia_msgpospagid_fk_i on pfi_custodiainfo (missatgeposiciopaginaid);
    create index pfi_custodia_codibarid_fk_i on pfi_custodiainfo (codibarresid);
    create index pfi_custodia_entitatid_fk_i on pfi_custodiainfo (entitatid);
    create index pfi_entitat_motiudele_fk_i on pfi_entitat (motiudelegacioid);
    create index pfi_entitat_algofirma_fk_i on pfi_entitat (algorismedefirmaid);
    create index pfi_entitat_pk_i on pfi_entitat (entitatid);
    create index pfi_entitat_pluginvalfir_fk_i on pfi_entitat (pluginvalidafirmesid);
    create index pfi_entitat_pluginvalcer_fk_i on pfi_entitat (pluginvalidacertificatid);
    create index pfi_entitat_pluginrubri_fk_i on pfi_entitat (pluginrubricaid);
    create index pfi_entitat_custodiadef_fk_i on pfi_entitat (custodiainfoid);
    create index pfi_entitat_segelltemps_fk_i on pfi_entitat (pluginid);
    create index pfi_entitat_pdfautoriid_fk_i on pfi_entitat (pdfautoritzaciodelegacioid);
    create index pfi_entitat_logowebpeuid_fk_i on pfi_entitat (logowebpeuid);
    create index pfi_entitat_logosegellid_fk_i on pfi_entitat (logosegellid);
    create index pfi_entitat_usrappid_fk_i on pfi_entitat (usuariaplicacioid);
    create index pfi_entitat_logowebid_fk_i on pfi_entitat (logowebid);
    create index pfi_entitat_firmatper_fk_i on pfi_entitat (firmatperformatid);
    create index pfi_entitat_faviconid_fk_i on pfi_entitat (faviconid);
    create index pfi_estadistica_pk_i on pfi_estadistica (estadisticaid);
    create index pfi_estadistica_entitatid_fk_i on pfi_estadistica (entitatid);
    create index pfi_estatdefirma_firmaid_fk_i on pfi_estatdefirma (firmaid);
    create index pfi_estatdefirma_pk_i on pfi_estatdefirma (estatdefirmaid);
    create index pfi_estatfirma_estatinid_fk_i on pfi_estatdefirma (tipusestatdefirmainicialid);
    create index pfi_estatfirma_coladele_fk_i on pfi_estatdefirma (colaboraciodelegacioid);
    create index pfi_estatfirma_usrentid_fk_i on pfi_estatdefirma (usuarientitatid);
    create index pfi_estatfirma_estatid_fk_i on pfi_estatdefirma (tipusestatdefirmafinalid);
    create index pfi_firma_blocdefirmaid_fk_i on pfi_firma (blocdefirmaid);
    create index pfi_firma_fitxerfirmatid_fk_i on pfi_firma (fitxerfirmatid);
    create index pfi_firma_estatfirmaid_fk_i on pfi_firma (tipusestatdefirmafinalid);
    create index pfi_firma_pk_i on pfi_firma (firmaid);
    create index pfi_firma_destinatariid_fk_i on pfi_firma (destinatariid);
    create index pfi_fitxer_pk_i on pfi_fitxer (fitxerid);
    create index pfi_fluxdefirmes_pk_i on pfi_fluxdefirmes (fluxdefirmesid);
    create index pfi_grupentitat_entitatid_fk_i on pfi_grupentitat (entitatid);
    create index pfi_grupentitat_pk_i on pfi_grupentitat (grupentitatid);
    create index pfi_grupusrent_usrentid_fk_i on pfi_grupentitatusuarientitat (usuarientitatid);
    create index pfi_grupusrent_grupentid_fk_i on pfi_grupentitatusuarientitat (grupentitatid);
    create index pfi_grupusrent_pk_i on pfi_grupentitatusuarientitat (grupentitatusuarientitatid);
    create index pfi_idioma_pk_i on pfi_idioma (idiomaid);
    create index pfi_metadada_tipusmetaid_fk_i on pfi_metadada (tipusmetadadaid);
    create index pfi_metadada_peticioid_fk_i on pfi_metadada (peticiodefirmaid);
    create index pfi_metadada_pk_i on pfi_metadada (metadadaid);
    create index pfi_mofitido_modfirma_fk_i on pfi_modulfirmapertipusdoc (pluginid);
    create index pfi_modulfirmapertipusdoc_pk_i on pfi_modulfirmapertipusdoc (id);
    create index pfi_mofitido_tipusdoc_fk_i on pfi_modulfirmapertipusdoc (tipusdocumentid);
    create index pfi_notifica_peticioid_fk_i on pfi_notificacio (peticiodefirmaid);
    create index pfi_notificacio_pk_i on pfi_notificacio (notificacioid);
    create index pfi_notifica_tiponotiid_fk_i on pfi_notificacio (tipusnotificacioid);
    create index pfi_perfilsperusrapp_pk_i on pfi_perfilsperusrapp (perfilsperusrappid);
    create index pfi_perfilsua_usuappid_fk_i on pfi_perfilsperusrapp (usuariaplicacioid);
    create index pfi_perfilsua_perfilid_fk_i on pfi_perfilsperusrapp (usuariaplicacioperfilid);
    create index pfi_permisgrpl_fluxid_fk_i on pfi_permisgrupplantilla (fluxdefirmesid);
    create index pfi_permisgrpl_grupentid_fk_i on pfi_permisgrupplantilla (grupentitatid);
    create index pfi_permisgrupplantilla_pk_i on pfi_permisgrupplantilla (permisgrupplantillaid);
    create index pfi_permisuspl_usrentid_fk_i on pfi_permisusuariplantilla (usuarientitatid);
    create index pfi_permisuspl_fluxid_fk_i on pfi_permisusuariplantilla (fluxdefirmesid);
    create index pfi_permisusuariplantilla_pk_i on pfi_permisusuariplantilla (permisusuariplantillaid);
    create index pfi_petifirma_solipers3_fk_i on pfi_peticiodefirma (solicitantpersona3id);
    create index pfi_petifirma_tipusdocid_fk_i on pfi_peticiodefirma (tipusdocumentid);
    create index pfi_peticiodefirma_pk_i on pfi_peticiodefirma (peticiodefirmaid);
    create index pfi_petifirma_custinfoid_fk_i on pfi_peticiodefirma (custodiainfoid);
    create index pfi_petifirma_logosegid_fk_i on pfi_peticiodefirma (logosegellid);
    create index pfi_petifirma_usrentiid_fk_i on pfi_peticiodefirma (usuarientitatid);
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
    create index pfi_plantiflfi_usrappid_fk_i on pfi_plantillafluxdefirmes (usuariaplicacioid);
    create index pfi_plantiflfi_usrentiid_fk_i on pfi_plantillafluxdefirmes (usuarientitatid);
    create index pfi_plantillafluxdefirmes_pk_i on pfi_plantillafluxdefirmes (fluxdefirmesid);
    create index pfi_plugin_nomid_fk_i on pfi_plugin (nomid);
    create index pfi_plugin_pk_i on pfi_plugin (pluginid);
    create index pfi_plugin_desccurtaid_fk_i on pfi_plugin (descripciocurtaid);
    create index pfi_plugin_entitatid_fk_i on pfi_plugin (entitatid);
    create index pfi_plugcrida_retorfitxer_fk_i on pfi_plugincridada (retornfitxerid);
    create index pfi_plugincridada_pk_i on pfi_plugincridada (plugincridadaid);
    create index pfi_plugcrida_pluginid_fk_i on pfi_plugincridada (pluginid);
    create index pfi_plugcrida_paramfitxer_fk_i on pfi_plugincridada (parametresfitxerid);
    create index pfi_plugcrida_entitatid_fk_i on pfi_plugincridada (entitatid);
    create index pfi_pfwpua_usrappid_fk_i on pfi_pluginfirmawebperusrapp (usuariaplicacioid);
    create index pfi_pfwpua_pk_i on pfi_pluginfirmawebperusrapp (pluginfirmawebperusrappid);
    create index pfi_pfwpua_plugin_fk_i on pfi_pluginfirmawebperusrapp (pluginfirmawebid);
    create index pfi_pfwpue_plugin_fk_i on pfi_pluginfirmawebperusrent (pluginfirmawebid);
    create index pfi_pfwpue_usrentid_fk_i on pfi_pluginfirmawebperusrent (usuarientitatid);
    create index pfi_pfwpue_pk_i on pfi_pluginfirmawebperusrent (pluginfirmawebperusrentid);
    create index pfi_propietat_entitatid_fk_i on pfi_propietatglobal (entitatid);
    create index pfi_propietatglobal_pk_i on pfi_propietatglobal (propietatglobalid);
    create index pfi_rebreavis_usrentid_fk_i on pfi_rebreavis (usuarientitatid);
    create index pfi_rebreavis_tiponotiid_fk_i on pfi_rebreavis (tipusnotificacioid);
    create index pfi_rebreavis_pk_i on pfi_rebreavis (id);
    create index pfi_revisordefirma_pk_i on pfi_revisordefirma (revisordefirmaid);
    create index pfi_revfirma_firmaid_fk_i on pfi_revisordefirma (firmaid);
    create index pfi_revfirma_usrentitat_fk_i on pfi_revisordefirma (usuarientitatid);
    create index pfi_role_pk_i on pfi_role (roleid);
    create index pfi_roleusuariaplicacio_pk_i on pfi_roleusuariaplicacio (id);
    create index pfi_roleusrapp_usrappid_fk_i on pfi_roleusuariaplicacio (usuariaplicacioid);
    create index pfi_roleusrapp_roleid_fk_i on pfi_roleusuariaplicacio (roleid);
    create index pfi_roleusrent_usrentid_fk_i on pfi_roleusuarientitat (usuarientitatid);
    create index pfi_roleusrent_roleid_fk_i on pfi_roleusuarientitat (roleid);
    create index pfi_roleusuarientitat_pk_i on pfi_roleusuarientitat (id);
    create index pfi_tipusdocument_nom_fk_i on pfi_tipusdocument (nom);
    create index pfi_tipusdoc_usuariappid_fk_i on pfi_tipusdocument (usuariaplicacioid);
    create index pfi_tipusdocument_pk_i on pfi_tipusdocument (tipusdocumentid);
    create index pfi_tipusdoccd_coldelid_fk_i on pfi_tipusdocumentcoladele (colaboraciodelegacioid);
    create index pfi_tipusdoccd_tipusdocid_fk_i on pfi_tipusdocumentcoladele (tipusdocumentid);
    create index pfi_tipusdocumentcoladele_pk_i on pfi_tipusdocumentcoladele (id);
    create index pfi_tipusnotificacio_pk_i on pfi_tipusnotificacio (tipusnotificacioid);
    create index pfi_traduccio_pk_i on pfi_traduccio (traduccioid);
    create index pfi_usrapp_entitatid_fk_i on pfi_usuariaplicacio (entitatid);
    create index pfi_usuariaplicacio_pk_i on pfi_usuariaplicacio (usuariaplicacioid);
    create index pfi_usrapp_custodia_fk_i on pfi_usuariaplicacio (custodiainfoid);
    create index pfi_usrapp_idiomaid_fk_i on pfi_usuariaplicacio (idiomaid);
    create index pfi_usrapp_logosegellid_fk_i on pfi_usuariaplicacio (logosegellid);
    create index pfi_confapp_motiudele_fk_i on pfi_usuariaplicacioconfig (motiudelegacioid);
    create index pfi_confapp_algofirma_fk_i on pfi_usuariaplicacioconfig (algorismedefirmaid);
    create index pfi_usuariaplicacioconfig_pk_i on pfi_usuariaplicacioconfig (usuariaplicacioconfigid);
    create index pfi_confapp_plugsegell_fk_i on pfi_usuariaplicacioconfig (pluginsegellatid);
    create index pfi_confapp_tipusfirma_fk_i on pfi_usuariaplicacioconfig (tipusfirmaid);
    create index pfi_confapp_postaula_fk_i on pfi_usuariaplicacioconfig (posiciotaulafirmesid);
    create index pfi_confapp_logincert_fk_i on pfi_usuariaplicacioconfig (logincertificateid);
    create index pfi_confapp_firmatper_fk_i on pfi_usuariaplicacioconfig (firmatperformatid);
    create index pfi_confapp_firmaserv_fk_i on pfi_usuariaplicacioconfig (pluginfirmaservidorid);
    create index pfi_confapp_entitatid_fk_i on pfi_usuariaplicacioconfig (entitatid);
    create index pfi_perfilapp_appconf1id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio1id);
    create index pfi_perfilapp_appconf2id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio2id);
    create index pfi_usuariaplicacioperfil_pk_i on pfi_usuariaplicacioperfil (usuariaplicacioperfilid);
    create index pfi_perfilapp_appconf4id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio4id);
    create index pfi_perfilapp_appconf3id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio3id);
    create index pfi_perfilapp_appconf5id_fk_i on pfi_usuariaplicacioperfil (usrappconfiguracio5id);
    create index pfi_usrentitat_entitatid_fk_i on pfi_usuarientitat (entitatid);
    create index pfi_usuarientitat_pk_i on pfi_usuarientitat (usuarientitatid);
    create index pfi_usrentitat_logosegid_fk_i on pfi_usuarientitat (logosegellid);
    create index pfi_usrentitat_personaid_fk_i on pfi_usuarientitat (usuaripersonaid);
    create index pfi_usrentitat_custinfo_fk_i on pfi_usuarientitat (custodiainfoid);
    create index pfi_usuarientitatfavorit_pk_i on pfi_usuarientitatfavorit (id);
    create index pfi_favorit_origenid_fk_i on pfi_usuarientitatfavorit (origenid);
    create index pfi_favorit_favoritid_fk_i on pfi_usuarientitatfavorit (favoritid);
    create index pfi_usuaripersona_nif_i on pfi_usuaripersona (nif);
    create index pfi_persona_idiomaid_fk_i on pfi_usuaripersona (idiomaid);
    create index pfi_usuaripersona_pk_i on pfi_usuaripersona (usuaripersonaid);
    create index pfi_persona_rubricaid_fk_i on pfi_usuaripersona (rubricaid);
 -- FINAL Indexes

 -- INICI PK's
    alter table pfi_annex add constraint pfi_annex_pk primary key (annexid);

    alter table pfi_annexfirmat add constraint pfi_annexfirmat_pk primary key (annexfirmatid);

    alter table pfi_bitacola add constraint pfi_bitacola_pk primary key (bitacolaid);

    alter table pfi_blocdefirmes add constraint pfi_blocdefirmes_pk primary key (blocdefirmesid);

    alter table pfi_codibarres add constraint pfi_codibarres_pk primary key (codibarresid);

    alter table pfi_colaboraciodelegacio add constraint pfi_colaboraciodelegacio_pk primary key (colaboraciodelegacioid);

    alter table pfi_custodiainfo add constraint pfi_custodiainfo_pk primary key (custodiainfoid);

    alter table pfi_entitat add constraint pfi_entitat_pk primary key (entitatid);

    alter table pfi_estadistica add constraint pfi_estadistica_pk primary key (estadisticaid);

    alter table pfi_estatdefirma add constraint pfi_estatdefirma_pk primary key (estatdefirmaid);

    alter table pfi_firma add constraint pfi_firma_pk primary key (firmaid);

    alter table pfi_fitxer add constraint pfi_fitxer_pk primary key (fitxerid);

    alter table pfi_fluxdefirmes add constraint pfi_fluxdefirmes_pk primary key (fluxdefirmesid);

    alter table pfi_grupentitat add constraint pfi_grupentitat_pk primary key (grupentitatid);

    alter table pfi_grupentitatusuarientitat add constraint pfi_grupusrent_pk primary key (grupentitatusuarientitatid);

    alter table pfi_idioma add constraint pfi_idioma_pk primary key (idiomaid);

    alter table pfi_metadada add constraint pfi_metadada_pk primary key (metadadaid);

    alter table pfi_modulfirmapertipusdoc add constraint pfi_modulfirmapertipusdoc_pk primary key (id);

    alter table pfi_notificacio add constraint pfi_notificacio_pk primary key (notificacioid);

    alter table pfi_perfilsperusrapp add constraint pfi_perfilsperusrapp_pk primary key (perfilsperusrappid);

    alter table pfi_permisgrupplantilla add constraint pfi_permisgrupplantilla_pk primary key (permisgrupplantillaid);

    alter table pfi_permisusuariplantilla add constraint pfi_permisusuariplantilla_pk primary key (permisusuariplantillaid);

    alter table pfi_peticiodefirma add constraint pfi_peticiodefirma_pk primary key (peticiodefirmaid);

    alter table pfi_plantillafluxdefirmes add constraint pfi_plantillafluxdefirmes_pk primary key (fluxdefirmesid);

    alter table pfi_plugin add constraint pfi_plugin_pk primary key (pluginid);

    alter table pfi_plugincridada add constraint pfi_plugincridada_pk primary key (plugincridadaid);

    alter table pfi_pluginfirmawebperusrapp add constraint pfi_pluginfirmawebperusrapp_pk primary key (pluginfirmawebperusrappid);

    alter table pfi_pluginfirmawebperusrent add constraint pfi_pluginfirmawebperusrent_pk primary key (pluginfirmawebperusrentid);

    alter table pfi_propietatglobal add constraint pfi_propietatglobal_pk primary key (propietatglobalid);

    alter table pfi_rebreavis add constraint pfi_rebreavis_pk primary key (id);

    alter table pfi_revisordefirma add constraint pfi_revisordefirma_pk primary key (revisordefirmaid);

    alter table pfi_role add constraint pfi_role_pk primary key (roleid);

    alter table pfi_roleusuariaplicacio add constraint pfi_roleusuariaplicacio_pk primary key (id);

    alter table pfi_roleusuarientitat add constraint pfi_roleusuarientitat_pk primary key (id);

    alter table pfi_tipusdocument add constraint pfi_tipusdocument_pk primary key (tipusdocumentid);

    alter table pfi_tipusdocumentcoladele add constraint pfi_tipusdocumentcoladele_pk primary key (id);

    alter table pfi_tipusnotificacio add constraint pfi_tipusnotificacio_pk primary key (tipusnotificacioid);

    alter table pfi_traduccio add constraint pfi_traduccio_pk primary key (traduccioid);

    alter table pfi_traducciomap add constraint pfi_traducciomap_pk primary key (traducciomapid, idiomaid);

    alter table pfi_usuariaplicacio add constraint pfi_usuariaplicacio_pk primary key (usuariaplicacioid);

    alter table pfi_usuariaplicacioconfig add constraint pfi_usuariaplicacioconfig_pk primary key (usuariaplicacioconfigid);

    alter table pfi_usuariaplicacioperfil add constraint pfi_usuariaplicacioperfil_pk primary key (usuariaplicacioperfilid);

    alter table pfi_usuarientitat add constraint pfi_usuarientitat_pk primary key (usuarientitatid);

    alter table pfi_usuarientitatfavorit add constraint pfi_usuarientitatfavorit_pk primary key (id);

    alter table pfi_usuaripersona add constraint pfi_usuaripersona_pk primary key (usuaripersonaid);

 -- FINAL PK's

 -- INICI FK's

    alter table pfi_annex 
        add constraint pfi_annex_petifirma_fk 
        foreign key (peticiodefirmaid) 
        references pfi_peticiodefirma;

    alter table pfi_annex 
        add constraint pfi_annex_fitxer_fk 
        foreign key (fitxerid) 
        references pfi_fitxer;

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

    alter table pfi_bitacola 
        add constraint pfi_bitacola_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_blocdefirmes 
        add constraint pfi_blocfirmes_fluxfirmes_fk 
        foreign key (fluxdefirmesid) 
        references pfi_fluxdefirmes;

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

    alter table pfi_estadistica 
        add constraint pfi_estadis_entitat_fk 
        foreign key (entitatid) 
        references pfi_entitat;

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

    alter table pfi_grupentitat 
        add constraint pfi_grupentita_entitat_fk 
        foreign key (entitatid) 
        references pfi_entitat;

    alter table pfi_grupentitatusuarientitat 
        add constraint pfi_grupusrent_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_grupentitatusuarientitat 
        add constraint pfi_grupusrent_grupentita_fk 
        foreign key (grupentitatid) 
        references pfi_grupentitat;

    alter table pfi_metadada 
        add constraint pfi_metadada_petifirma_fk 
        foreign key (peticiodefirmaid) 
        references pfi_peticiodefirma;

    alter table pfi_modulfirmapertipusdoc 
        add constraint pfi_mofitido_tipusdoc_fk 
        foreign key (tipusdocumentid) 
        references pfi_tipusdocument;

    alter table pfi_modulfirmapertipusdoc 
        add constraint pfi_mofitido_plugin_fk 
        foreign key (pluginid) 
        references pfi_plugin;

    alter table pfi_notificacio 
        add constraint pfi_notifica_petifirma_fk 
        foreign key (peticiodefirmaid) 
        references pfi_peticiodefirma;

    alter table pfi_notificacio 
        add constraint pfi_notifica_tipnotific_fk 
        foreign key (tipusnotificacioid) 
        references pfi_tipusnotificacio;

    alter table pfi_perfilsperusrapp 
        add constraint pfi_perfilsua_perfilapp_p_fk 
        foreign key (usuariaplicacioperfilid) 
        references pfi_usuariaplicacioperfil;

    alter table pfi_perfilsperusrapp 
        add constraint pfi_perfilsua_usrapp_usr_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

    alter table pfi_permisgrupplantilla 
        add constraint pfi_permisgrpl_grupentita_fk 
        foreign key (grupentitatid) 
        references pfi_grupentitat;

    alter table pfi_permisgrupplantilla 
        add constraint pfi_permisgrpl_plantiflfi_fk 
        foreign key (fluxdefirmesid) 
        references pfi_plantillafluxdefirmes;

    alter table pfi_permisusuariplantilla 
        add constraint pfi_permisuspl_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_permisusuariplantilla 
        add constraint pfi_permisuspl_plantiflfi_fk 
        foreign key (fluxdefirmesid) 
        references pfi_plantillafluxdefirmes;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_usrentitat_3_fk 
        foreign key (solicitantpersona3id) 
        references pfi_usuarientitat;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_fitxer_ada_fk 
        foreign key (fitxeradaptatid) 
        references pfi_fitxer;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_usrentitat_2_fk 
        foreign key (solicitantpersona2id) 
        references pfi_usuarientitat;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_tipusdoc_fk 
        foreign key (tipusdocumentid) 
        references pfi_tipusdocument;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_fitxer_log_fk 
        foreign key (logosegellid) 
        references pfi_fitxer;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_fitxer_ori_fk 
        foreign key (firmaoriginaldetachedid) 
        references pfi_fitxer;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_fluxfirmes_fk 
        foreign key (fluxdefirmesid) 
        references pfi_fluxdefirmes;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_fitxer_fir_fk 
        foreign key (fitxerafirmarid) 
        references pfi_fitxer;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_idioma_fk 
        foreign key (idiomaid) 
        references pfi_idioma;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_usrapp_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

    alter table pfi_peticiodefirma 
        add constraint pfi_petifirma_custodia_fk 
        foreign key (custodiainfoid) 
        references pfi_custodiainfo;

    alter table pfi_plantillafluxdefirmes 
        add constraint pfi_plantiflfi_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_plantillafluxdefirmes 
        add constraint pfi_plantiflfi_usrapp_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

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

    alter table pfi_pluginfirmawebperusrapp 
        add constraint pfi_pfwpua_plugin_fk 
        foreign key (pluginfirmawebid) 
        references pfi_plugin;

    alter table pfi_pluginfirmawebperusrapp 
        add constraint pfi_pfwpua_usrapp_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

    alter table pfi_pluginfirmawebperusrent 
        add constraint pfi_pfwpue_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_pluginfirmawebperusrent 
        add constraint pfi_pfwpue_plugin_fk 
        foreign key (pluginfirmawebid) 
        references pfi_plugin;

    alter table pfi_propietatglobal 
        add constraint pfi_propietat_entitat_fk 
        foreign key (entitatid) 
        references pfi_entitat;

    alter table pfi_rebreavis 
        add constraint pfi_rebreavis_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_rebreavis 
        add constraint pfi_rebreavis_tipnotific_fk 
        foreign key (tipusnotificacioid) 
        references pfi_tipusnotificacio;

    alter table pfi_revisordefirma 
        add constraint pfi_revfirma_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_revisordefirma 
        add constraint pfi_revfirma_firma_fk 
        foreign key (firmaid) 
        references pfi_firma;

    alter table pfi_roleusuariaplicacio 
        add constraint pfi_roleusrapp_role_fk 
        foreign key (roleid) 
        references pfi_role;

    alter table pfi_roleusuariaplicacio 
        add constraint pfi_roleusrapp_usrapp_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

    alter table pfi_roleusuarientitat 
        add constraint pfi_roleusrent_usrentitat_fk 
        foreign key (usuarientitatid) 
        references pfi_usuarientitat;

    alter table pfi_roleusuarientitat 
        add constraint pfi_roleusrent_role_fk 
        foreign key (roleid) 
        references pfi_role;

    alter table pfi_tipusdocument 
        add constraint pfi_tipusdoc_traduccio_fk 
        foreign key (nom) 
        references pfi_traduccio;

    alter table pfi_tipusdocument 
        add constraint pfi_tipusdoc_usrapp_fk 
        foreign key (usuariaplicacioid) 
        references pfi_usuariaplicacio;

    alter table pfi_tipusdocumentcoladele 
        add constraint pfi_tipusdoccd_tipusdoc_fk 
        foreign key (tipusdocumentid) 
        references pfi_tipusdocument;

    alter table pfi_tipusdocumentcoladele 
        add constraint pfi_tipusdoccd_colabdeleg_fk 
        foreign key (colaboraciodelegacioid) 
        references pfi_colaboraciodelegacio;

    alter table pfi_traducciomap 
        add constraint pfi_traducmap_traduccio_fk 
        foreign key (traducciomapid) 
        references pfi_traduccio;

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
        add constraint pfi_confapp_fitxer_cert_fk 
        foreign key (logincertificateid) 
        references pfi_fitxer;

    alter table pfi_usuariaplicacioconfig 
        add constraint pfi_confapp_plugin_seg_fk 
        foreign key (pluginsegellatid) 
        references pfi_plugin;

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

    alter table pfi_usuarientitatfavorit 
        add constraint pfi_favorit_usrentitat_fav_fk 
        foreign key (favoritid) 
        references pfi_usuarientitat;

    alter table pfi_usuarientitatfavorit 
        add constraint pfi_favorit_usrentitat_ori_fk 
        foreign key (origenid) 
        references pfi_usuarientitat;

    alter table pfi_usuaripersona 
        add constraint pfi_persona_fitxer_fk 
        foreign key (rubricaid) 
        references pfi_fitxer;

    alter table pfi_usuaripersona 
        add constraint pfi_persona_idioma_fk 
        foreign key (idiomaid) 
        references pfi_idioma;
 -- FINAL FK's

 -- INICI UNIQUES
    alter table pfi_grupentitat add constraint pfi_grupentita_nomentitat_uk unique (nom, entitatid);
    alter table pfi_grupentitatusuarientitat add constraint pfi_grupusrent_usrgrup_uk unique (usuarientitatid, grupentitatid);
    alter table pfi_modulfirmapertipusdoc add constraint pfi_mofitido_modfirm_tipdoc_uk unique (tipusdocumentid, pluginid);
    alter table pfi_perfilsperusrapp add constraint pfi_perfilsua_multiple_uk unique (usuariaplicacioperfilid, usuariaplicacioid);
    alter table pfi_permisgrupplantilla add constraint pfi_permisgrpl_grupflux_uk unique (grupentitatid, fluxdefirmesid);
    alter table pfi_permisusuariplantilla add constraint pfi_permisuspl_usrflux_uk unique (usuarientitatid, fluxdefirmesid);
    alter table pfi_peticiodefirma add constraint pfi_petifirma_fluxfirmesid_uk unique (fluxdefirmesid);
    alter table pfi_pluginfirmawebperusrapp add constraint pfi_pfwpua_usuapp_plug_uk unique (usuariaplicacioid, pluginfirmawebid);
    alter table pfi_pluginfirmawebperusrent add constraint pfi_pfwpue_usuent_plug_uk unique (usuarientitatid, pluginfirmawebid);
    alter table pfi_propietatglobal add constraint pfi_propietat_clau_entitat_uk unique (clau, entitatid);
    alter table pfi_rebreavis add constraint pfi_rebreavis_tnotiusr_uk unique (tipusnotificacioid, usuarientitatid);
    alter table pfi_roleusuariaplicacio add constraint pfi_roleusrapp_approle_uk unique (usuariaplicacioid, roleid);
    alter table pfi_roleusuarientitat add constraint pfi_roleusrent_roleusrent_uk unique (roleid, usuarientitatid);
    alter table pfi_tipusdocumentcoladele add constraint pfi_tipusdoccd_codetdoc_uk unique (colaboraciodelegacioid, tipusdocumentid);
    alter table pfi_usuariaplicacioperfil add constraint pfi_perfilapp_codi_uk unique (codi);
    alter table pfi_usuarientitat add constraint pfi_usrentitat_perentcar_uk unique (usuaripersonaid, entitatid, carrec);
    alter table pfi_usuarientitatfavorit add constraint pfi_favorit_origfavo_uk unique (origenid, favoritid);
    alter table pfi_usuaripersona add constraint pfi_persona_nif_uk unique (nif);
 -- FINAL UNIQUES

 -- INICI GRANTS
    grant select,insert,delete,update on pfi_annex to www_portafib;
    grant select,insert,delete,update on pfi_annexfirmat to www_portafib;
    grant select,insert,delete,update on pfi_bitacola to www_portafib;
    grant select,insert,delete,update on pfi_blocdefirmes to www_portafib;
    grant select,insert,delete,update on pfi_codibarres to www_portafib;
    grant select,insert,delete,update on pfi_colaboraciodelegacio to www_portafib;
    grant select,insert,delete,update on pfi_custodiainfo to www_portafib;
    grant select,insert,delete,update on pfi_entitat to www_portafib;
    grant select,insert,delete,update on pfi_estadistica to www_portafib;
    grant select,insert,delete,update on pfi_estatdefirma to www_portafib;
    grant select,insert,delete,update on pfi_firma to www_portafib;
    grant select,insert,delete,update on pfi_fitxer to www_portafib;
    grant select,insert,delete,update on pfi_fluxdefirmes to www_portafib;
    grant select,insert,delete,update on pfi_grupentitat to www_portafib;
    grant select,insert,delete,update on pfi_grupentitatusuarientitat to www_portafib;
    grant select,insert,delete,update on pfi_idioma to www_portafib;
    grant select,insert,delete,update on pfi_metadada to www_portafib;
    grant select,insert,delete,update on pfi_modulfirmapertipusdoc to www_portafib;
    grant select,insert,delete,update on pfi_notificacio to www_portafib;
    grant select,insert,delete,update on pfi_perfilsperusrapp to www_portafib;
    grant select,insert,delete,update on pfi_permisgrupplantilla to www_portafib;
    grant select,insert,delete,update on pfi_permisusuariplantilla to www_portafib;
    grant select,insert,delete,update on pfi_peticiodefirma to www_portafib;
    grant select,insert,delete,update on pfi_plantillafluxdefirmes to www_portafib;
    grant select,insert,delete,update on pfi_plugin to www_portafib;
    grant select,insert,delete,update on pfi_plugincridada to www_portafib;
    grant select,insert,delete,update on pfi_pluginfirmawebperusrapp to www_portafib;
    grant select,insert,delete,update on pfi_pluginfirmawebperusrent to www_portafib;
    grant select,insert,delete,update on pfi_propietatglobal to www_portafib;
    grant select,insert,delete,update on pfi_rebreavis to www_portafib;
    grant select,insert,delete,update on pfi_revisordefirma to www_portafib;
    grant select,insert,delete,update on pfi_role to www_portafib;
    grant select,insert,delete,update on pfi_roleusuariaplicacio to www_portafib;
    grant select,insert,delete,update on pfi_roleusuarientitat to www_portafib;
    grant select,insert,delete,update on pfi_tipusdocument to www_portafib;
    grant select,insert,delete,update on pfi_tipusdocumentcoladele to www_portafib;
    grant select,insert,delete,update on pfi_tipusnotificacio to www_portafib;
    grant select,insert,delete,update on pfi_traduccio to www_portafib;
    grant select,insert,delete,update on pfi_traducciomap to www_portafib;
    grant select,insert,delete,update on pfi_usuariaplicacio to www_portafib;
    grant select,insert,delete,update on pfi_usuariaplicacioconfig to www_portafib;
    grant select,insert,delete,update on pfi_usuariaplicacioperfil to www_portafib;
    grant select,insert,delete,update on pfi_usuarientitat to www_portafib;
    grant select,insert,delete,update on pfi_usuarientitatfavorit to www_portafib;
    grant select,insert,delete,update on pfi_usuaripersona to www_portafib;
    grant select on pfi_portafib_seq to www_portafib;
 -- FINAL GRANTS

