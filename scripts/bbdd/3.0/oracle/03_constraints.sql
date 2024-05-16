
 -- INICI PKs
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

    alter table pfi_revisordedestinatari add constraint pfi_revisordedestinatari_pk primary key (revisordedestinatariid);

    alter table pfi_revisordefirma add constraint pfi_revisordefirma_pk primary key (revisordefirmaid);

    alter table pfi_role add constraint pfi_role_pk primary key (roleid);

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

 -- FINAL PKs


 -- INICI FKs

    alter table pfi_annex 
       add constraint pfi_annex_fitxer_fk 
       foreign key (fitxerid) 
       references pfi_fitxer;

    alter table pfi_annex 
       add constraint pfi_annex_petifirma_fk 
       foreign key (peticiodefirmaid) 
       references pfi_peticiodefirma;

    alter table pfi_annexfirmat 
       add constraint pfi_anexfirmat_annex_fk 
       foreign key (annexid) 
       references pfi_annex;

    alter table pfi_annexfirmat 
       add constraint pfi_anexfirmat_firma_fk 
       foreign key (firmaid) 
       references pfi_firma;

    alter table pfi_annexfirmat 
       add constraint pfi_anexfirmat_fitxer_fk 
       foreign key (fitxerid) 
       references pfi_fitxer;

    alter table pfi_blocdefirmes 
       add constraint pfi_blocfirmes_fluxfirmes_fk 
       foreign key (fluxdefirmesid) 
       references pfi_fluxdefirmes;

    alter table pfi_colaboraciodelegacio 
       add constraint pfi_colabdeleg_usrentitat_c_fk 
       foreign key (colaboradordelegatid) 
       references pfi_usuarientitat;

    alter table pfi_colaboraciodelegacio 
       add constraint pfi_colabdeleg_usrentitat_d_fk 
       foreign key (destinatariid) 
       references pfi_usuarientitat;

    alter table pfi_colaboraciodelegacio 
       add constraint pfi_colabdeleg_fitxer_fk 
       foreign key (fitxerautoritzacioid) 
       references pfi_fitxer;

    alter table pfi_custodiainfo 
       add constraint pfi_custodia_codibarres_fk 
       foreign key (codibarresid) 
       references pfi_codibarres;

    alter table pfi_custodiainfo 
       add constraint pfi_custodia_entitat_fk 
       foreign key (entitatid) 
       references pfi_entitat;

    alter table pfi_custodiainfo 
       add constraint pfi_custodia_plugin_fk 
       foreign key (pluginid) 
       references pfi_plugin;

    alter table pfi_custodiainfo 
       add constraint pfi_custodia_usrapp_fk 
       foreign key (usuariaplicacioid) 
       references pfi_usuariaplicacio;

    alter table pfi_custodiainfo 
       add constraint pfi_custodia_usrentitat_fk 
       foreign key (usuarientitatid) 
       references pfi_usuarientitat;

    alter table pfi_entitat 
       add constraint pfi_entitat_custodia_fk 
       foreign key (custodiainfoid) 
       references pfi_custodiainfo;

    alter table pfi_entitat 
       add constraint pfi_entitat_fitxer_icon_fk 
       foreign key (faviconid) 
       references pfi_fitxer;

    alter table pfi_entitat 
       add constraint pfi_entitat_traduccio_firm_fk 
       foreign key (firmatperformatid) 
       references pfi_traduccio;

    alter table pfi_entitat 
       add constraint pfi_entitat_fitxer_lose_fk 
       foreign key (logosegellid) 
       references pfi_fitxer;

    alter table pfi_entitat 
       add constraint pfi_entitat_fitxer_loca_fk 
       foreign key (logowebid) 
       references pfi_fitxer;

    alter table pfi_entitat 
       add constraint pfi_entitat_fitxer_lope_fk 
       foreign key (logowebpeuid) 
       references pfi_fitxer;

    alter table pfi_entitat 
       add constraint pfi_entitat_traduccio_moti_fk 
       foreign key (motiudelegacioid) 
       references pfi_traduccio;

    alter table pfi_entitat 
       add constraint pfi_entitat_fitxer_pdfd_fk 
       foreign key (pdfautoritzaciodelegacioid) 
       references pfi_fitxer;

    alter table pfi_entitat 
       add constraint pfi_entitat_plugin_rubr_fk 
       foreign key (pluginrubricaid) 
       references pfi_plugin;

    alter table pfi_entitat 
       add constraint pfi_entitat_plugin_fk 
       foreign key (pluginid) 
       references pfi_plugin;

    alter table pfi_entitat 
       add constraint pfi_entitat_plugin_cert_fk 
       foreign key (pluginvalidacertificatid) 
       references pfi_plugin;

    alter table pfi_entitat 
       add constraint pfi_entitat_plugin_vafi_fk 
       foreign key (pluginvalidafirmesid) 
       references pfi_plugin;

    alter table pfi_entitat 
       add constraint pfi_entitat_usrapp_fk 
       foreign key (usuariaplicacioid) 
       references pfi_usuariaplicacio;

    alter table pfi_estadistica 
       add constraint pfi_estadis_entitat_fk 
       foreign key (entitatid) 
       references pfi_entitat;

    alter table pfi_estatdefirma 
       add constraint pfi_estatfirma_colabdeleg_fk 
       foreign key (colaboraciodelegacioid) 
       references pfi_colaboraciodelegacio;

    alter table pfi_estatdefirma 
       add constraint pfi_estatfirma_firma_fk 
       foreign key (firmaid) 
       references pfi_firma;

    alter table pfi_estatdefirma 
       add constraint pfi_estatfirma_usrentitat_fk 
       foreign key (usuarientitatid) 
       references pfi_usuarientitat;

    alter table pfi_firma 
       add constraint pfi_firma_blocfirmes_fk 
       foreign key (blocdefirmaid) 
       references pfi_blocdefirmes;

    alter table pfi_firma 
       add constraint pfi_firma_fitxer_fk 
       foreign key (fitxerfirmatid) 
       references pfi_fitxer;

    alter table pfi_firma 
       add constraint pfi_firma_usrentitat_fk 
       foreign key (destinatariid) 
       references pfi_usuarientitat;

    alter table pfi_grupentitat 
       add constraint pfi_grupentita_entitat_fk 
       foreign key (entitatid) 
       references pfi_entitat;

    alter table pfi_grupentitatusuarientitat 
       add constraint pfi_grupusrent_grupentita_fk 
       foreign key (grupentitatid) 
       references pfi_grupentitat;

    alter table pfi_grupentitatusuarientitat 
       add constraint pfi_grupusrent_usrentitat_fk 
       foreign key (usuarientitatid) 
       references pfi_usuarientitat;

    alter table pfi_metadada 
       add constraint pfi_metadada_petifirma_fk 
       foreign key (peticiodefirmaid) 
       references pfi_peticiodefirma;

    alter table pfi_modulfirmapertipusdoc 
       add constraint pfi_mofitido_plugin_fk 
       foreign key (pluginid) 
       references pfi_plugin;

    alter table pfi_modulfirmapertipusdoc 
       add constraint pfi_mofitido_tipusdoc_fk 
       foreign key (tipusdocumentid) 
       references pfi_tipusdocument;

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
       add constraint pfi_permisuspl_plantiflfi_fk 
       foreign key (fluxdefirmesid) 
       references pfi_plantillafluxdefirmes;

    alter table pfi_permisusuariplantilla 
       add constraint pfi_permisuspl_usrentitat_fk 
       foreign key (usuarientitatid) 
       references pfi_usuarientitat;

    alter table pfi_peticiodefirma 
       add constraint pfi_petifirma_custodia_fk 
       foreign key (custodiainfoid) 
       references pfi_custodiainfo;

    alter table pfi_peticiodefirma 
       add constraint pfi_petifirma_fitxer_ori_fk 
       foreign key (firmaoriginaldetachedid) 
       references pfi_fitxer;

    alter table pfi_peticiodefirma 
       add constraint pfi_petifirma_fitxer_fir_fk 
       foreign key (fitxerafirmarid) 
       references pfi_fitxer;

    alter table pfi_peticiodefirma 
       add constraint pfi_petifirma_fitxer_ada_fk 
       foreign key (fitxeradaptatid) 
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
       add constraint pfi_petifirma_fitxer_log_fk 
       foreign key (logosegellid) 
       references pfi_fitxer;

    alter table pfi_peticiodefirma 
       add constraint pfi_petifirma_usrentitat_fk 
       foreign key (usuarientitatid) 
       references pfi_usuarientitat;

    alter table pfi_peticiodefirma 
       add constraint pfi_petifirma_usrentitat_2_fk 
       foreign key (solicitantpersona2id) 
       references pfi_usuarientitat;

    alter table pfi_peticiodefirma 
       add constraint pfi_petifirma_usrentitat_3_fk 
       foreign key (solicitantpersona3id) 
       references pfi_usuarientitat;

    alter table pfi_peticiodefirma 
       add constraint pfi_petifirma_tipusdoc_fk 
       foreign key (tipusdocumentid) 
       references pfi_tipusdocument;

    alter table pfi_peticiodefirma 
       add constraint pfi_petifirma_usrapp_fk 
       foreign key (usuariaplicacioid) 
       references pfi_usuariaplicacio;

    alter table pfi_peticiodefirma 
       add constraint pfi_petifirma_confapp_fk 
       foreign key (configuraciodefirmaid) 
       references pfi_usuariaplicacioconfig;

    alter table pfi_plantillafluxdefirmes 
       add constraint pfi_plantiflfi_usrapp_fk 
       foreign key (usuariaplicacioid) 
       references pfi_usuariaplicacio;

    alter table pfi_plantillafluxdefirmes 
       add constraint pfi_plantiflfi_usrentitat_fk 
       foreign key (usuarientitatid) 
       references pfi_usuarientitat;

    alter table pfi_plugin 
       add constraint pfi_plugin_traduccio_desc_fk 
       foreign key (descripciocurtaid) 
       references pfi_traduccio;

    alter table pfi_plugin 
       add constraint pfi_plugin_entitat_fk 
       foreign key (entitatid) 
       references pfi_entitat;

    alter table pfi_plugin 
       add constraint pfi_plugin_traduccio_nom_fk 
       foreign key (nomid) 
       references pfi_traduccio;

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

    alter table pfi_plugincridada 
       add constraint pfi_plugcrida_fitxer_retor_fk 
       foreign key (retornfitxerid) 
       references pfi_fitxer;

    alter table pfi_pluginfirmawebperusrapp 
       add constraint pfi_pfwpua_plugin_fk 
       foreign key (pluginfirmawebid) 
       references pfi_plugin;

    alter table pfi_pluginfirmawebperusrapp 
       add constraint pfi_pfwpua_usrapp_fk 
       foreign key (usuariaplicacioid) 
       references pfi_usuariaplicacio;

    alter table pfi_pluginfirmawebperusrent 
       add constraint pfi_pfwpue_plugin_fk 
       foreign key (pluginfirmawebid) 
       references pfi_plugin;

    alter table pfi_pluginfirmawebperusrent 
       add constraint pfi_pfwpue_usrentitat_fk 
       foreign key (usuarientitatid) 
       references pfi_usuarientitat;

    alter table pfi_propietatglobal 
       add constraint pfi_propietat_entitat_fk 
       foreign key (entitatid) 
       references pfi_entitat;

    alter table pfi_rebreavis 
       add constraint pfi_rebreavis_tipnotific_fk 
       foreign key (tipusnotificacioid) 
       references pfi_tipusnotificacio;

    alter table pfi_rebreavis 
       add constraint pfi_rebreavis_usrentitat_fk 
       foreign key (usuarientitatid) 
       references pfi_usuarientitat;

    alter table pfi_revisordedestinatari 
       add constraint pfi_revdedest_usrentitat_de_fk 
       foreign key (destinatariid) 
       references pfi_usuarientitat;

    alter table pfi_revisordedestinatari 
       add constraint pfi_revdedest_usrentitat_re_fk 
       foreign key (revisorid) 
       references pfi_usuarientitat;

    alter table pfi_revisordefirma 
       add constraint pfi_revfirma_firma_fk 
       foreign key (firmaid) 
       references pfi_firma;

    alter table pfi_revisordefirma 
       add constraint pfi_revfirma_usrentitat_fk 
       foreign key (usuarientitatid) 
       references pfi_usuarientitat;

    alter table pfi_roleusuarientitat 
       add constraint pfi_roleusrent_role_fk 
       foreign key (roleid) 
       references pfi_role;

    alter table pfi_roleusuarientitat 
       add constraint pfi_roleusrent_usrentitat_fk 
       foreign key (usuarientitatid) 
       references pfi_usuarientitat;

    alter table pfi_tipusdocument 
       add constraint pfi_tipusdoc_traduccio_fk 
       foreign key (nom) 
       references pfi_traduccio;

    alter table pfi_tipusdocument 
       add constraint pfi_tipusdoc_usrapp_fk 
       foreign key (usuariaplicacioid) 
       references pfi_usuariaplicacio;

    alter table pfi_tipusdocumentcoladele 
       add constraint pfi_tipusdoccd_colabdeleg_fk 
       foreign key (colaboraciodelegacioid) 
       references pfi_colaboraciodelegacio;

    alter table pfi_tipusdocumentcoladele 
       add constraint pfi_tipusdoccd_tipusdoc_fk 
       foreign key (tipusdocumentid) 
       references pfi_tipusdocument;

    alter table pfi_traducciomap 
       add constraint pfi_traducmap_traduccio_fk 
       foreign key (traducciomapid) 
       references pfi_traduccio;

    alter table pfi_usuariaplicacio 
       add constraint pfi_usrapp_custodia_fk 
       foreign key (custodiainfoid) 
       references pfi_custodiainfo;

    alter table pfi_usuariaplicacio 
       add constraint pfi_usrapp_entitat_fk 
       foreign key (entitatid) 
       references pfi_entitat;

    alter table pfi_usuariaplicacio 
       add constraint pfi_usrapp_idioma_fk 
       foreign key (idiomaid) 
       references pfi_idioma;

    alter table pfi_usuariaplicacio 
       add constraint pfi_usrapp_fitxer_fk 
       foreign key (logosegellid) 
       references pfi_fitxer;

    alter table pfi_usuariaplicacioconfig 
       add constraint pfi_confapp_entitat_ent_fk 
       foreign key (entitatid) 
       references pfi_entitat;

    alter table pfi_usuariaplicacioconfig 
       add constraint pfi_confapp_traduccio_firm_fk 
       foreign key (firmatperformatid) 
       references pfi_traduccio;

    alter table pfi_usuariaplicacioconfig 
       add constraint pfi_confapp_traduccio_moti_fk 
       foreign key (motiudelegacioid) 
       references pfi_traduccio;

    alter table pfi_usuariaplicacioconfig 
       add constraint pfi_confapp_plugin_fsrv_fk 
       foreign key (pluginfirmaservidorid) 
       references pfi_plugin;

    alter table pfi_usuariaplicacioconfig 
       add constraint pfi_confapp_plugin_seg_fk 
       foreign key (pluginsegellatid) 
       references pfi_plugin;

    alter table pfi_usuariaplicacioperfil 
       add constraint pfi_perfilapp_confapp_1_fk 
       foreign key (usrappconfiguracio1id) 
       references pfi_usuariaplicacioconfig;

    alter table pfi_usuariaplicacioperfil 
       add constraint pfi_perfilapp_confapp_2_fk 
       foreign key (usrappconfiguracio2id) 
       references pfi_usuariaplicacioconfig;

    alter table pfi_usuariaplicacioperfil 
       add constraint pfi_perfilapp_confapp_3_fk 
       foreign key (usrappconfiguracio3id) 
       references pfi_usuariaplicacioconfig;

    alter table pfi_usuariaplicacioperfil 
       add constraint pfi_perfilapp_confapp_4_fk 
       foreign key (usrappconfiguracio4id) 
       references pfi_usuariaplicacioconfig;

    alter table pfi_usuariaplicacioperfil 
       add constraint pfi_perfilapp_confapp_5_fk 
       foreign key (usrappconfiguracio5id) 
       references pfi_usuariaplicacioconfig;

    alter table pfi_usuarientitat 
       add constraint pfi_usrentitat_custodia_fk 
       foreign key (custodiainfoid) 
       references pfi_custodiainfo;

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

    alter table pfi_usuarientitatfavorit 
       add constraint pfi_favorit_usrentitat_fav_fk 
       foreign key (favoritid) 
       references pfi_usuarientitat;

    alter table pfi_usuarientitatfavorit 
       add constraint pfi_favorit_usrentitat_ori_fk 
       foreign key (origenid) 
       references pfi_usuarientitat;

    alter table pfi_usuaripersona 
       add constraint pfi_persona_idioma_fk 
       foreign key (idiomaid) 
       references pfi_idioma;

    alter table pfi_usuaripersona 
       add constraint pfi_persona_fitxer_fk 
       foreign key (rubricaid) 
       references pfi_fitxer;
 -- FINAL FKs


 -- INICI UNIQUEs

    alter table pfi_grupentitat 
       add constraint pfi_grupentita_nomentitat_uk unique (nom, entitatid);

    alter table pfi_grupentitatusuarientitat 
       add constraint pfi_grupusrent_usrgrup_uk unique (usuarientitatid, grupentitatid);

    alter table pfi_modulfirmapertipusdoc 
       add constraint pfi_mofitido_modfirm_tipdoc_uk unique (tipusdocumentid, pluginid);

    alter table pfi_perfilsperusrapp 
       add constraint pfi_perfilsua_uaid_perf_uk unique (usuariaplicacioperfilid, usuariaplicacioid);

    alter table pfi_permisgrupplantilla 
       add constraint pfi_permisgrpl_grupflux_uk unique (grupentitatid, fluxdefirmesid);

    alter table pfi_permisusuariplantilla 
       add constraint pfi_permisuspl_usrflux_uk unique (usuarientitatid, fluxdefirmesid);

    alter table pfi_peticiodefirma 
       add constraint UK_87fpw4enhdg2y66r7ogren3i3 unique (fluxdefirmesid);

    alter table pfi_pluginfirmawebperusrapp 
       add constraint pfi_pfwpua_usuapp_plug_uk unique (usuariaplicacioid, pluginfirmawebid);

    alter table pfi_pluginfirmawebperusrent 
       add constraint pfi_pfwpue_usuent_plug_uk unique (usuarientitatid, pluginfirmawebid);

    alter table pfi_propietatglobal 
       add constraint pfi_propietat_clau_entitat_uk unique (clau, entitatid);

    alter table pfi_rebreavis 
       add constraint pfi_rebreavis_tnotiusr_uk unique (tipusnotificacioid, usuarientitatid);

    alter table pfi_revisordedestinatari 
       add constraint pfi_revdedest_dest_rev_uk unique (destinatariid, revisorid);

    alter table pfi_roleusuarientitat 
       add constraint pfi_roleusrent_roleusrent_uk unique (roleid, usuarientitatid);

    alter table pfi_tipusdocumentcoladele 
       add constraint pfi_tipusdoccd_codetdoc_uk unique (colaboraciodelegacioid, tipusdocumentid);

    alter table pfi_usuariaplicacioperfil 
       add constraint UK_52h7t3udtqosk1c4ld6kt5ep3 unique (codi);

    alter table pfi_usuarientitat 
       add constraint pfi_usrentitat_perentcar_uk unique (usuaripersonaid, entitatid, carrec);

    alter table pfi_usuarientitatfavorit 
       add constraint pfi_favorit_origfavo_uk unique (origenid, favoritid);

    alter table pfi_usuaripersona 
       add constraint pfi_persona_nif_extern_uk unique (nif, usuariintern);
 -- FINAL UNIQUEs

