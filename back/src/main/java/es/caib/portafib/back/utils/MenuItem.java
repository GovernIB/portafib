package es.caib.portafib.back.utils;

import java.util.HashMap;
import java.util.Map;

import es.caib.portafib.back.controller.aden.ConfiguracioDeFirmaAdenController;
import es.caib.portafib.back.controller.aden.PerfilDeFirmaAdenController;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class MenuItem {
    public final String label;

    public final String url;

    public final String urlbase;

    public MenuItem(String label, String url) {
        super();
        this.label = label;
        this.url = url;
        this.urlbase = url;
    }

    public MenuItem(String label, String url, String urlbase) {
        super();
        this.label = label;
        this.url = url;
        this.urlbase = urlbase;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlbase() {
        return urlbase;
    }

    public static MenuItem retallaDarrerPath(String label, String url) {
        int i = url.lastIndexOf('/');

        return new MenuItem(label, url, url.substring(0, i));

    }

    final static String[] menu1 = { "entitat.modificar", // Modificació de les dades d'una Entitat
            "propietatglobal.entitat.gestio", "tipusdocument.gestio", // Gestió Tipus de Documents

            "", "usuaripersona.alta", "usuaripersona.modificar", "usuarientitat.gestio", "", "carrec.gestio",
            "colaboradordecarrec.plural", "solicitant.gestio", "revisor.gestio", "grups.gestio", "",
            "moduldefirmaenservidor.gestio", "", "moduldefirma.gestio", "modulDeFirmaPerTipusDeDocument.short", // /aden/modulfirmatipusdoc
            "", "segelldetemps.gestio", "plantillacustodia.gestio", // "/aden/plantillacustodia"
            "", "validaciodefirmes.gestio", // /aden/validaciofirmes
            "", "peticiodefirma.totes.consultar.llistar", // Consulta Peticions de Firma
            "peticiodefirma.totes.gestionar.llistar", // Gestionar Peticions de Firma
            "", "peticionscaducades.llistat", // Llistar peticions de firma caducades
            "peticionsdefirma.destinatari", "peticiodefirma.netejaesborrat", "", "estadistica.estadistica.plural",
            "bitacola.menu" };

    public static final String CONFIGURACIO_DE_FIRMA = UsuariAplicacioConfiguracioFields._TABLE_MODEL + "."
            + UsuariAplicacioConfiguracioFields._TABLE_MODEL + ".plural";
    public static final String PERFIL_DE_FIRMA = PerfilDeFirmaFields._TABLE_MODEL + "."
            + PerfilDeFirmaFields._TABLE_MODEL + ".plural";

    final static String[] menu2 = { "usuariaplicacio.gestio", // Alta d'Usuari-Aplicació"}
            PERFIL_DE_FIRMA, CONFIGURACIO_DE_FIRMA, "", "plantillaFluxDeFirmes.plantillaFluxDeFirmes.plural",
            Configuracio.isCAIB() ? "" : "peticiodefirma.usrapp.llistar", // Peticions de Firma d'Usuaris-Aplicacio
            "custodiaInfo.custodiaInfo.plural", "notificaciows.llistat" };

    public static final Map<String, String> mapping;

    static {
        // Mapping to existent path
        mapping = new HashMap<String, String>();

        mapping.put("entitat.modificar", "/aden/entitat/XXXXXX/edit"); // "/aden/entitat/" +
                                                                       // LoginInfo.getInstance().getEntitatID()
                                                                       // + "/edit" );

        mapping.put("usuaripersona.alta", "/aden/usuariPersona/alta");
        mapping.put("usuaripersona.modificar", "/aden/usuariPersona/modificar");

        mapping.put("usuarientitat.gestio", "/aden/usuariEntitat/selecciousuari");

        mapping.put("carrec.gestio", "/aden/carrec/list");
        mapping.put("colaboradordecarrec.plural", "/aden/colaboradordecarrec/list");

        mapping.put("tipusdocument.gestio", "/aden/gestiotipusdoc/list");

        mapping.put("moduldefirmaenservidor.gestio", "/aden/moduldefirmaenservidor/list");

        mapping.put("moduldefirma.gestio", "/aden/modulDeFirma/list");
        mapping.put("segelldetemps.gestio", "/aden/segelldetemps/list");
        mapping.put("plantillacustodia.gestio", "/aden/plantillacustodia/list");

        mapping.put("modulDeFirmaPerTipusDeDocument.short", "/aden/modulfirmatipusdoc/list");

        mapping.put("validaciodefirmes.gestio", "/aden/validaciofirmes/list");

        mapping.put("grups.gestio", "/aden/grup/list");

        mapping.put("solicitant.gestio", "/aden/solicitant/selecciousuari");
        mapping.put("revisor.gestio", "/aden/revisor/selecciousuari");

        mapping.put("peticionscaducades.llistat", "/aden/peticionscaducades/list");
        mapping.put("peticionsdefirma.destinatari", "/aden/peticionsdedestinatari/selecciousuari");

        mapping.put("usuariaplicacio.gestio", "/aden/usuariAplicacio/list");

        mapping.put(CONFIGURACIO_DE_FIRMA, ConfiguracioDeFirmaAdenController.CONTEXT_WEB + "/list");

        mapping.put(PERFIL_DE_FIRMA, PerfilDeFirmaAdenController.CONTEXT_WEB + "/list");

        mapping.put("plantillaFluxDeFirmes.plantillaFluxDeFirmes.plural", "/aden/plantilla/list");

        mapping.put("notificaciows.llistat", "/aden/notificaciows/list");

        mapping.put("peticiodefirma.usrapp.llistar", ConstantsV2.CONTEXT_ADEN_PETICIOFIRMA_USRAPP + "/list");

        mapping.put("peticiodefirma.totes.consultar.llistar",
                ConstantsV2.CONTEXT_ADEN_PETICIOFIRMA_TOTES_CONSULTAR + "/list");

        mapping.put("peticiodefirma.totes.gestionar.llistar",
                ConstantsV2.CONTEXT_ADEN_PETICIOFIRMA_TOTES_GESTIONAR + "/list");

        mapping.put("custodiaInfo.custodiaInfo.plural", "/aden/peticio/custodiainfo/list");

        mapping.put("propietatglobal.entitat.gestio", "/aden/propietatglobal/list");

        mapping.put("peticiodefirma.netejaesborrat", "/aden/peticio/netejaesborrat/list");

        mapping.put("estadistica.estadistica.plural", "/aden/estadistica/search");

        mapping.put("bitacola.menu", "/aden/bitacola/list");
    };

    public static void main(String[] args) {
        for (int i = 0; i < menu2.length; i++) {
            String id = menu2[i];

            if (id.equals("")) {
                System.out.println(" menu2.add(null);");
            } else {

                String url = mapping.get(menu2[i]);

                if (url.endsWith("/list")) {
                    System.out
                            .println(" menu2.add(MenuItem.retallaDarrerPath(\"" + menu2[i] + "\", \"" + url + "\"));");
                } else {

                    System.out.println(" menu2.add(new MenuItem(\"" + menu2[i] + "\", \"" + url + "\"));");

                }

            }
        }

    }
}
