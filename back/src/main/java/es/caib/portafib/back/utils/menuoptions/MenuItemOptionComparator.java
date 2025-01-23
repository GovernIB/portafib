package es.caib.portafib.back.utils.menuoptions;

import java.util.Comparator;

/**
 * 
 * @author anadal
 * 7 ene 2025 12:28:57
 */
class MenuItemOptionComparator implements Comparator<MenuItemOption> {

    @Override
    public int compare(MenuItemOption o1, MenuItemOption o2) {
        return o1.getOrder() - o2.getOrder();
    }

}