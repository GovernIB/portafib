    -- ALERTA! Script només per entorns de proves
    -- Permet borrar completament la base de dades per recrear-la si ha quedat amb un estat inconsistent.

    drop table pfi_annex cascade constraints;

    drop table pfi_annexfirmat cascade constraints;

    drop table pfi_bitacola cascade constraints;

    drop table pfi_blocdefirmes cascade constraints;

    drop table pfi_codibarres cascade constraints;

    drop table pfi_colaboraciodelegacio cascade constraints;

    drop table pfi_custodiainfo cascade constraints;

    drop table pfi_entitat cascade constraints;

    drop table pfi_estadistica cascade constraints;

    drop table pfi_estatdefirma cascade constraints;

    drop table pfi_firma cascade constraints;

    drop table pfi_fitxer cascade constraints;

    drop table pfi_fluxdefirmes cascade constraints;

    drop table pfi_grupentitat cascade constraints;

    drop table pfi_grupentitatusuarientitat cascade constraints;

    drop table pfi_idioma cascade constraints;

    drop table pfi_metadada cascade constraints;

    drop table pfi_modulfirmapertipusdoc cascade constraints;

    drop table pfi_notificacio cascade constraints;

    drop table pfi_perfilsperusrapp cascade constraints;

    drop table pfi_permisgrupplantilla cascade constraints;

    drop table pfi_permisusuariplantilla cascade constraints;

    drop table pfi_peticiodefirma cascade constraints;

    drop table pfi_plantillafluxdefirmes cascade constraints;

    drop table pfi_plugin cascade constraints;

    drop table pfi_plugincridada cascade constraints;

    drop table pfi_pluginfirmawebperusrapp cascade constraints;

    drop table pfi_pluginfirmawebperusrent cascade constraints;

    drop table pfi_propietatglobal cascade constraints;

    drop table pfi_rebreavis cascade constraints;

    drop table pfi_revisordefirma cascade constraints;

    drop table pfi_role cascade constraints;

    drop table pfi_roleusuarientitat cascade constraints;

    drop table pfi_tipusdocument cascade constraints;

    drop table pfi_tipusdocumentcoladele cascade constraints;

    drop table pfi_tipusnotificacio cascade constraints;

    drop table pfi_traduccio cascade constraints;

    drop table pfi_traducciomap cascade constraints;

    drop table pfi_usuariaplicacio cascade constraints;

    drop table pfi_usuariaplicacioconfig cascade constraints;

    drop table pfi_usuariaplicacioperfil cascade constraints;

    drop table pfi_usuarientitat cascade constraints;

    drop table pfi_usuarientitatfavorit cascade constraints;

    drop table pfi_usuaripersona cascade constraints;

    drop sequence pfi_portafib_seq;
