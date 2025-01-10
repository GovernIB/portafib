package es.caib.portafib.back.utils.menuoptions;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.fundaciobit.genapp.common.web.controller.CommonBaseController;

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
    public static List<MenuItem> getMenuItems(String group, MenuItem... additionalMenuItems) throws Exception {

        if (discoverMenuOptionAnnotations == null) {
            throw new Exception("MenuOptionManager no ha sigut inicialitzat. Has de cridar a "
                    + "MenuOptionManager.setDiscoverMenuOptionAnnotations(new DiscoverMenuOptionAnnotations(...).");
        }

        TreeMap<MenuItemOption, Class<?>> menuOptions = discoverMenuOptionAnnotations.getMenuOptionByGroup(group);
        
        if (additionalMenuItems != null && additionalMenuItems.length != 0) {
            
            for (MenuItem menuItem : additionalMenuItems) {
                MenuItemOption mio = new MenuItemOption(menuItem.getLabel(), menuItem.getUrl(), menuItem.getUrlbase(), menuItem.getOrder());
                mio.setGroup(group);
                menuOptions.put(mio, null);
            }
            
        }
        

        // Convertir menuoption a menu items
        List<MenuItem> items = new ArrayList<MenuItem>();
        for (MenuItemOption menuOption : menuOptions.keySet()) {
            String baseLink;
            if (menuOption.getUrlbase() == null || menuOption.getUrlbase().isEmpty()) {
                // Cercar el request mapping de la classe

                Class<?> classe = menuOptions.get(menuOption);
                if (CommonBaseController.class.isAssignableFrom(classe)) {
                    CommonBaseController<?, ?> cbc = (CommonBaseController<?, ?>) classe.getDeclaredConstructor()
                            .newInstance();
                    baseLink = cbc.getContextWeb();
                } else {
                    throw new Exception("La classe " + classe.getName()
                            + " no té definida l'anotació MenuOption, però aquesta requereix que la classe estengui"
                            + " de CommonBaseController o ha de definir l'atribut baseLink a l'anotació @MenuOption.");
                }

            } else {
                baseLink = menuOption.getUrlbase();
            }

            if (menuOption.isAddSeparatorBefore()) {
                items.add(null);
            }

            items.add(new MenuItem(menuOption.getLabel(), baseLink + menuOption.getUrl(), baseLink,
                    menuOption.getOrder()));

            if (menuOption.isAddSeparatorAfter()) {
                items.add(null);
            }
        }

        return items;

    }

}
