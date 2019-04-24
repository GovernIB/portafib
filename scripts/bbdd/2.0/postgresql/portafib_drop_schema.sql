
    alter table pfi_annex 
        drop constraint pfi_annex_petifirma_fk;

    alter table pfi_annex 
        drop constraint pfi_annex_fitxer_fk;

    alter table pfi_annexfirmat 
        drop constraint pfi_anexfirmat_annex_fk;

    alter table pfi_annexfirmat 
        drop constraint pfi_anexfirmat_fitxer_fk;

    alter table pfi_annexfirmat 
        drop constraint pfi_anexfirmat_firma_fk;

    alter table pfi_bitacola 
        drop constraint pfi_bitacola_usrentitat_fk;

    alter table pfi_blocdefirmes 
        drop constraint pfi_blocfirmes_fluxfirmes_fk;

    alter table pfi_colaboraciodelegacio 
        drop constraint pfi_colabdeleg_usrentitat_d_fk;

    alter table pfi_colaboraciodelegacio 
        drop constraint pfi_colabdeleg_usrentitat_c_fk;

    alter table pfi_colaboraciodelegacio 
        drop constraint pfi_colabdeleg_fitxer_fk;

    alter table pfi_custodiainfo 
        drop constraint pfi_custodia_usrentitat_fk;

    alter table pfi_custodiainfo 
        drop constraint pfi_custodia_entitat_fk;

    alter table pfi_custodiainfo 
        drop constraint pfi_custodia_plugin_fk;

    alter table pfi_custodiainfo 
        drop constraint pfi_custodia_codibarres_fk;

    alter table pfi_custodiainfo 
        drop constraint pfi_custodia_usrapp_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_traduccio_firm_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_fitxer_lope_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_plugin_vafi_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_fitxer_lose_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_fitxer_loca_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_fitxer_icon_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_usrapp_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_traduccio_moti_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_plugin_cert_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_fitxer_pdfd_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_plugin_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_plugin_rubr_fk;

    alter table pfi_entitat 
        drop constraint pfi_entitat_custodia_fk;

    alter table pfi_estadistica 
        drop constraint pfi_estadis_entitat_fk;

    alter table pfi_estatdefirma 
        drop constraint pfi_estatfirma_usrentitat_fk;

    alter table pfi_estatdefirma 
        drop constraint pfi_estatfirma_colabdeleg_fk;

    alter table pfi_estatdefirma 
        drop constraint pfi_estatfirma_firma_fk;

    alter table pfi_firma 
        drop constraint pfi_firma_fitxer_fk;

    alter table pfi_firma 
        drop constraint pfi_firma_usrentitat_fk;

    alter table pfi_firma 
        drop constraint pfi_firma_blocfirmes_fk;

    alter table pfi_grupentitat 
        drop constraint pfi_grupentita_entitat_fk;

    alter table pfi_grupentitatusuarientitat 
        drop constraint pfi_grupusrent_usrentitat_fk;

    alter table pfi_grupentitatusuarientitat 
        drop constraint pfi_grupusrent_grupentita_fk;

    alter table pfi_metadada 
        drop constraint pfi_metadada_petifirma_fk;

    alter table pfi_modulfirmapertipusdoc 
        drop constraint pfi_mofitido_tipusdoc_fk;

    alter table pfi_modulfirmapertipusdoc 
        drop constraint pfi_mofitido_plugin_fk;

    alter table pfi_notificacio 
        drop constraint pfi_notifica_petifirma_fk;

    alter table pfi_notificacio 
        drop constraint pfi_notifica_tipnotific_fk;

    alter table pfi_perfilsperusrapp 
        drop constraint pfi_perfilsua_perfilapp_p_fk;

    alter table pfi_perfilsperusrapp 
        drop constraint pfi_perfilsua_usrapp_usr_fk;

    alter table pfi_permisgrupplantilla 
        drop constraint pfi_permisgrpl_grupentita_fk;

    alter table pfi_permisgrupplantilla 
        drop constraint pfi_permisgrpl_plantiflfi_fk;

    alter table pfi_permisusuariplantilla 
        drop constraint pfi_permisuspl_usrentitat_fk;

    alter table pfi_permisusuariplantilla 
        drop constraint pfi_permisuspl_plantiflfi_fk;

    alter table pfi_peticiodefirma 
        drop constraint pfi_petifirma_usrentitat_fk;

    alter table pfi_peticiodefirma 
        drop constraint pfi_petifirma_usrentitat_3_fk;

    alter table pfi_peticiodefirma 
        drop constraint pfi_petifirma_fitxer_ada_fk;

    alter table pfi_peticiodefirma 
        drop constraint pfi_petifirma_usrentitat_2_fk;

    alter table pfi_peticiodefirma 
        drop constraint pfi_petifirma_tipusdoc_fk;

    alter table pfi_peticiodefirma 
        drop constraint pfi_petifirma_fitxer_log_fk;

    alter table pfi_peticiodefirma 
        drop constraint pfi_petifirma_fitxer_ori_fk;

    alter table pfi_peticiodefirma 
        drop constraint pfi_petifirma_fluxfirmes_fk;

    alter table pfi_peticiodefirma 
        drop constraint pfi_petifirma_fitxer_fir_fk;

    alter table pfi_peticiodefirma 
        drop constraint pfi_petifirma_idioma_fk;

    alter table pfi_peticiodefirma 
        drop constraint pfi_petifirma_usrapp_fk;

    alter table pfi_peticiodefirma 
        drop constraint pfi_petifirma_custodia_fk;

    alter table pfi_plantillafluxdefirmes 
        drop constraint pfi_plantiflfi_usrentitat_fk;

    alter table pfi_plantillafluxdefirmes 
        drop constraint pfi_plantiflfi_usrapp_fk;

    alter table pfi_plugin 
        drop constraint pfi_plugin_entitat_fk;

    alter table pfi_plugin 
        drop constraint pfi_plugin_traduccio_nom_fk;

    alter table pfi_plugin 
        drop constraint pfi_plugin_traduccio_desc_fk;

    alter table pfi_plugincridada 
        drop constraint pfi_plugcrida_fitxer_retor_fk;

    alter table pfi_plugincridada 
        drop constraint pfi_plugcrida_entitat_fk;

    alter table pfi_plugincridada 
        drop constraint pfi_plugcrida_fitxer_param_fk;

    alter table pfi_plugincridada 
        drop constraint pfi_plugcrida_plugin_fk;

    alter table pfi_pluginfirmawebperusrapp 
        drop constraint pfi_pfwpua_plugin_fk;

    alter table pfi_pluginfirmawebperusrapp 
        drop constraint pfi_pfwpua_usrapp_fk;

    alter table pfi_pluginfirmawebperusrent 
        drop constraint pfi_pfwpue_usrentitat_fk;

    alter table pfi_pluginfirmawebperusrent 
        drop constraint pfi_pfwpue_plugin_fk;

    alter table pfi_propietatglobal 
        drop constraint pfi_propietat_entitat_fk;

    alter table pfi_rebreavis 
        drop constraint pfi_rebreavis_usrentitat_fk;

    alter table pfi_rebreavis 
        drop constraint pfi_rebreavis_tipnotific_fk;

    alter table pfi_revisordefirma 
        drop constraint pfi_revfirma_usrentitat_fk;

    alter table pfi_revisordefirma 
        drop constraint pfi_revfirma_firma_fk;

    alter table pfi_roleusuariaplicacio 
        drop constraint pfi_roleusrapp_role_fk;

    alter table pfi_roleusuariaplicacio 
        drop constraint pfi_roleusrapp_usrapp_fk;

    alter table pfi_roleusuarientitat 
        drop constraint pfi_roleusrent_usrentitat_fk;

    alter table pfi_roleusuarientitat 
        drop constraint pfi_roleusrent_role_fk;

    alter table pfi_tipusdocument 
        drop constraint pfi_tipusdoc_traduccio_fk;

    alter table pfi_tipusdocument 
        drop constraint pfi_tipusdoc_usrapp_fk;

    alter table pfi_tipusdocumentcoladele 
        drop constraint pfi_tipusdoccd_tipusdoc_fk;

    alter table pfi_tipusdocumentcoladele 
        drop constraint pfi_tipusdoccd_colabdeleg_fk;

    alter table pfi_traducciomap 
        drop constraint pfi_traducmap_traduccio_fk;

    alter table pfi_usuariaplicacio 
        drop constraint pfi_usrapp_entitat_fk;

    alter table pfi_usuariaplicacio 
        drop constraint pfi_usrapp_fitxer_fk;

    alter table pfi_usuariaplicacio 
        drop constraint pfi_usrapp_idioma_fk;

    alter table pfi_usuariaplicacio 
        drop constraint pfi_usrapp_custodia_fk;

    alter table pfi_usuariaplicacioconfig 
        drop constraint pfi_confapp_traduccio_moti_fk;

    alter table pfi_usuariaplicacioconfig 
        drop constraint pfi_confapp_traduccio_firm_fk;

    alter table pfi_usuariaplicacioconfig 
        drop constraint pfi_confapp_entitat_ent_fk;

    alter table pfi_usuariaplicacioconfig 
        drop constraint pfi_confapp_plugin_fsrv_fk;

    alter table pfi_usuariaplicacioconfig 
        drop constraint pfi_confapp_fitxer_cert_fk;

    alter table pfi_usuariaplicacioconfig 
        drop constraint pfi_confapp_plugin_seg_fk;

    alter table pfi_usuariaplicacioperfil 
        drop constraint pfi_perfilapp_confapp_5_fk;

    alter table pfi_usuariaplicacioperfil 
        drop constraint pfi_perfilapp_confapp_1_fk;

    alter table pfi_usuariaplicacioperfil 
        drop constraint pfi_perfilapp_confapp_4_fk;

    alter table pfi_usuariaplicacioperfil 
        drop constraint pfi_perfilapp_confapp_2_fk;

    alter table pfi_usuariaplicacioperfil 
        drop constraint pfi_perfilapp_confapp_3_fk;

    alter table pfi_usuarientitat 
        drop constraint pfi_usrentitat_entitat_fk;

    alter table pfi_usuarientitat 
        drop constraint pfi_usrentitat_fitxer_fk;

    alter table pfi_usuarientitat 
        drop constraint pfi_usrentitat_persona_fk;

    alter table pfi_usuarientitat 
        drop constraint pfi_usrentitat_custodia_fk;

    alter table pfi_usuarientitatfavorit 
        drop constraint pfi_favorit_usrentitat_fav_fk;

    alter table pfi_usuarientitatfavorit 
        drop constraint pfi_favorit_usrentitat_ori_fk;

    alter table pfi_usuaripersona 
        drop constraint pfi_persona_fitxer_fk;

    alter table pfi_usuaripersona 
        drop constraint pfi_persona_idioma_fk;

    drop table pfi_annex;

    drop table pfi_annexfirmat;

    drop table pfi_bitacola;

    drop table pfi_blocdefirmes;

    drop table pfi_codibarres;

    drop table pfi_colaboraciodelegacio;

    drop table pfi_custodiainfo;

    drop table pfi_entitat;

    drop table pfi_estadistica;

    drop table pfi_estatdefirma;

    drop table pfi_firma;

    drop table pfi_fitxer;

    drop table pfi_fluxdefirmes;

    drop table pfi_grupentitat;

    drop table pfi_grupentitatusuarientitat;

    drop table pfi_idioma;

    drop table pfi_metadada;

    drop table pfi_modulfirmapertipusdoc;

    drop table pfi_notificacio;

    drop table pfi_perfilsperusrapp;

    drop table pfi_permisgrupplantilla;

    drop table pfi_permisusuariplantilla;

    drop table pfi_peticiodefirma;

    drop table pfi_plantillafluxdefirmes;

    drop table pfi_plugin;

    drop table pfi_plugincridada;

    drop table pfi_pluginfirmawebperusrapp;

    drop table pfi_pluginfirmawebperusrent;

    drop table pfi_propietatglobal;

    drop table pfi_rebreavis;

    drop table pfi_revisordefirma;

    drop table pfi_role;

    drop table pfi_roleusuariaplicacio;

    drop table pfi_roleusuarientitat;

    drop table pfi_tipusdocument;

    drop table pfi_tipusdocumentcoladele;

    drop table pfi_tipusnotificacio;

    drop table pfi_traduccio;

    drop table pfi_traducciomap;

    drop table pfi_usuariaplicacio;

    drop table pfi_usuariaplicacioconfig;

    drop table pfi_usuariaplicacioperfil;

    drop table pfi_usuarientitat;

    drop table pfi_usuarientitatfavorit;

    drop table pfi_usuaripersona;

    drop sequence pfi_portafib_seq;
