
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
        descripcio varchar2(255 char),
        entitatid varchar2(50 char) not null,
        objecteserialitzat clob,
        objecteid varchar2(100 char) not null,
        tipusobjecte number(10,0) not null,
        tipusoperacio number(10,0) not null,
        usuariid varchar2(101 char) not null
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
        csv varchar2(500 char),
        csvgenerationdefinition varchar2(500 char),
        csvvalidationweb varchar2(500 char),
        custodiapluginid varchar2(255 char),
        custodiapluginparametres varchar2(3000 char),
        custodiar number(1,0) not null,
        datacustodia timestamp,
        documentid varchar2(250 char),
        editable number(1,0) not null,
        enifiledirecturl varchar2(500 char),
        entitatid varchar2(50 char),
        expedientid varchar2(250 char),
        missatge varchar2(3000 char) not null,
        missatgeposiciopaginaid number(19,0) not null,
        nomplantilla varchar2(255 char),
        originalfiledirecturl varchar2(500 char),
        pagines varchar2(255 char) not null,
        pluginid number(19,0) not null,
        printablefiledirecturl varchar2(500 char),
        titolpeticio varchar2(255 char),
        urlfitxercustodiat varchar2(500 char),
        usuariaplicacioid varchar2(101 char),
        usuarientitatid varchar2(101 char)
    );

    create table pfi_entitat (
       entitatid varchar2(50 char) not null,
        activa number(1,0) not null,
        adrezahtml varchar2(2000 char) not null,
        algorismedefirmaid number(10,0) default 0 not null,
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
        politicacustodia number(10,0) default 0 not null,
        segelldetempsviaweb number(10,0) default 0 not null,
        politicataulafirmes number(10,0) default 2 not null,
        posiciotaulafirmes number(10,0) default 1 not null,
        propietatstaulafirmes clob,
        suportemail varchar2(100 char),
        suporttelefon varchar2(50 char),
        suportweb varchar2(250 char),
        uspoliticadefirma number(10,0) default 0 not null,
        usuariaplicacioid varchar2(101 char),
        validarfirma number(1,0) not null,
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
        valor double precision default 1 not null
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
        checkadministrationidofsigner number(1,0),
        checkdocumentmodifications number(1,0),
        checkvalidationsignature number(1,0),
        destinatariid varchar2(101 char) not null,
        emissorcertificat varchar2(1000 char),
        fitxerfirmatid number(19,0),
        minimderevisors number(10,0) default 0 not null,
        mostrarrubrica number(1,0) not null,
        motiu varchar2(255 char),
        nomcertificat varchar2(1000 char),
        numfirmadocument number(10,0),
        numeroseriecertificat number,
        obligatori number(1,0) not null,
        perfildefirma varchar2(50 char),
        tipusestatdefirmafinalid number(19,0),
        extern_email varchar2(255 char),
        extern_idioma varchar2(2 char),
        extern_llinatges varchar2(255 char),
        extern_nivellseguretat number(10,0),
        extern_nom varchar2(100 char),
        extern_token varchar2(255 char)
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
        ordre number(10,0) default 0 not null,
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
        reintents number(10,0) default 0 not null,
        tipusnotificacioid number(19,0) not null,
        usuariaplicacioid varchar2(101 char) not null
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
        configuraciodefirmaid number(19,0),
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
        origenpeticiodefirma number(10,0) default 0 not null,
        posiciotaulafirmesid number(10,0) not null,
        prioritatid number(10,0) default 5 not null,
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
        tipusoperaciofirma number(10,0) default 0 not null,
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
        politicadeus number(10,0) default 0 not null,
        politicamostrarpropietats number(10,0) default 2 not null,
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
        accio number(10,0) default 1 not null,
        pluginfirmawebid number(19,0) not null,
        usuariaplicacioid varchar2(101 char) not null
    );

    create table pfi_pluginfirmawebperusrent (
       pluginfirmawebperusrentid number(19,0) not null,
        accio number(10,0) default 1 not null,
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

    create table pfi_roleusuarientitat (
       id number(19,0) not null,
        roleid varchar2(50 char) not null,
        usuarientitatid varchar2(101 char) not null
    );

    create table pfi_tipusdocument (
       tipusdocumentid number(19,0) not null,
        descripcio varchar2(1000 char),
        nom number(19,0) not null,
        tipusdocumentbaseid number(19,0) default 99 not null,
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
        idiomaid varchar2(255 char) not null
    );

    create table pfi_usuariaplicacio (
       usuariaplicacioid varchar2(101 char) not null,
        actiu number(1,0) not null,
        callbackurl varchar2(400 char),
        callbackversio number(10,0) default 2 not null,
        crearusuaris number(1,0) not null,
        custodiainfoid number(19,0),
        descripcio varchar2(255 char),
        emailadmin varchar2(100 char) not null,
        entitatid varchar2(50 char) not null,
        idiomaid varchar2(5 char) not null,
        logosegellid number(19,0),
        politicacustodia number(10,0) default 0 not null,
        politicadepluginfirmaweb number(10,0) default 0 not null
    );

    create table pfi_usuariaplicacioconfig (
       usuariaplicacioconfigid number(19,0) not null,
        algorismedefirmaid number(10,0),
        checkcanviatdocfirmat number(1,0),
        comprovarniffirma number(1,0),
        entitatid varchar2(50 char) not null,
        esdepeticio number(1,0) not null,
        filtrecertificats clob,
        firmatperformatid number(19,0),
        htmlperllistarpluginsfirmaweb clob,
        modedefirma number(1,0) not null,
        motiudelegacioid number(19,0),
        nom varchar2(255 char) not null,
        pluginfirmaservidorid number(19,0),
        pluginsegellatid number(19,0),
        policyidentifier varchar2(100 char),
        policyidentifierhash varchar2(256 char),
        policyidentifierhashalgorithm varchar2(50 char),
        policyurldocument varchar2(255 char),
        politicasegellatdetemps number(10,0) default 0 not null,
        politicataulafirmes number(10,0) default 0 not null,
        posiciotaulafirmesid number(10,0) default 0 not null,
        propietatstaulafirmes clob,
        tipusfirmaid number(10,0) not null,
        tipusoperaciofirma number(10,0) default 0 not null,
        upgradesignformat number(10,0),
        usenfirmaapisimpleservidor number(1,0) not null,
        usenfirmaapisimpleweb number(1,0) not null,
        usenfirmaws2 number(1,0) not null,
        usenfirmapassarelaservidor number(1,0) not null,
        usenfirmapassarelaweb number(1,0) not null,
        usenfirmaws1 number(1,0) not null,
        usenfirmaweb number(1,0) not null,
        uspoliticadefirma number(10,0) default 0 not null,
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
        politicadepluginfirmaweb number(10,0) default 0 not null,
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














