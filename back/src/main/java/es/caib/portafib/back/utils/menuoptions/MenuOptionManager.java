package es.caib.portafib.back.utils.menuoptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fundaciobit.genapp.common.web.controller.CommonBaseController;

import es.caib.portafib.back.utils.MenuItem;

/**
 * 
 * @author anadal
 * 8 ene 2025 10:34:25
 */
public class MenuOptionManager {

    private static DiscoverMenuOptionAnnotations discoverMenuOptionAnnotations = null;

    public static void setDiscoverMenuOptionAnnotations(DiscoverMenuOptionAnnotations discoverMenuOptionAnnotations) {
        MenuOptionManager.discoverMenuOptionAnnotations = discoverMenuOptionAnnotations;
    }

    /**
     * 
     * @param group
     * @return
     * @throws Exception
     */
    public static List<MenuItem> getMenuItems(String group) throws Exception {

        if (discoverMenuOptionAnnotations == null) {
            throw new Exception(
                    "MenuOptionManager no ha sigut inicialitzat. Has de cridar a MenuOptionManager.setDiscoverMenuOptionAnnotations(new DiscoverMenuOptionAnnotations(...).");
        }

        Map<MenuOption, Class<?>> menuOptions = discoverMenuOptionAnnotations.getMenuOptionByGroup(group);

        // Convertir menuoption a menu items
        List<MenuItem> items = new ArrayList<MenuItem>();
        for (MenuOption menuOption : menuOptions.keySet()) {
            String baseLink;
            if (menuOption.baseLink() == null || menuOption.baseLink().isEmpty()) {
                // Cercar el request mapping de la classe

                Class<?> classe = menuOptions.get(menuOption);
                if (CommonBaseController.class.isAssignableFrom(classe)) {
                    CommonBaseController<?, ?> cbc = (CommonBaseController<?, ?>) classe.getDeclaredConstructor()
                            .newInstance();
                    baseLink = cbc.getContextWeb();
                } else {
                    throw new Exception("La classe " + classe.getName()
                            + " no té definida l'anotació MenuOption, però aquesta requereix que la classe estengui"
                            + " de CommonBaseController o ha de definir l'atribut baseLink a l'anotació MenuOption.");
                }

            } else {
                baseLink = menuOption.baseLink();
            }

            if (menuOption.addSeparatorBefore()) {
                items.add(null);
            }

            items.add(new MenuItem(menuOption.labelCode(), baseLink + menuOption.relativeLink(), baseLink));

            if (menuOption.addSeparatorAfter()) {
                items.add(null);
            }
        }

        return items;

    }

}
