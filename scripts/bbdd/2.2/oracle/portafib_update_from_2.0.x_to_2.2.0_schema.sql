

 
DECLARE

    seqvalue NUMBER;

BEGIN

    SELECT pfi_portafib_seq.NEXTVAL INTO seqvalue FROM dual;

    execute immediate ('create sequence pfi_annex_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_annexfirmat_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_bitacola_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_blocdefirmes_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_colaboraciodelegacio_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_custodiainfo_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_estadistica_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_estatdefirma_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_firma_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_fitxer_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_fluxdefirmes_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_grupentitat_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_grupentitatusuarientitat_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_metadada_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_modulfirmapertipusdoc_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_notificacio_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_perfilsperusrapp_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_permisgrupplantilla_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_permisusuariplantilla_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_peticiodefirma_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_plugin_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_plugincridada_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_pluginfirmawebperusrapp_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_pluginfirmawebperusrent_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_propietatglobal_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_rebreavis_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_revisordefirma_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_roleusuarientitat_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_tipusdocumentcoladele_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_traduccio_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_usuariaplicacioconfig_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_usuariaplicacioperfil_seq start with ' || seqvalue || ' increment by 1');
    execute immediate ('create sequence pfi_usuarientitatfavorit_seq start with ' || seqvalue || ' increment by 1');

END;