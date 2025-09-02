package es.caib.portafib.back.preparer;

import java.util.HashMap;
import java.util.Map;

import org.fundaciobit.genapp.common.web.tiles.TilesXml2AnnotationTiles;

public class ConvertTiles {

    public static void main(String[] args) {
        try {
            // Posar aquí el package base del BACK
            String packageBackBase = "es.caib.portafib.back";

            // Aqui definir totes les pipelles
            Map<String, String> tabs = new HashMap<String, String>();
            tabs.put("admin", "Tab.MENU_ADMIN");
            tabs.put("user", "Tab.MENU_USER");
            tabs.put("common", "Tab.MENU_PUBLIC_AND_COMMON");
            tabs.put("webdb", "Tab.MENU_WEBDB");

            // ALTRES Pipelles
            tabs.put("role_admin", "\"role_admin\"");
            tabs.put("role_user", "\"role_user\"");
            tabs.put("role_any", "\"role_any\"");
            tabs.put("role_dest", "\"role_dest\"");
            tabs.put("role_dele", "\"role_dele\"");
            tabs.put("role_cola", "\"role_cola\"");
            tabs.put("role_aden", "\"role_aden\"");
            tabs.put("role_adapp", "\"role_adapp\"");
            tabs.put("role_soli", "\"role_soli\"");
            tabs.put("role_revi", "\"role_revi\"");
            tabs.put("principal", "\"principal\"");

            // Ruta al fitxer tiles.xml
            String filePath = "src\\main\\webapp\\WEB-INF\\tiles.xml";

            TilesXml2AnnotationTiles.tileXml2ProgrammaticTiles(packageBackBase, tabs, filePath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
