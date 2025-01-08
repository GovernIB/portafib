package es.caib.portafib.back.utils.menuoptions;

import java.util.Comparator;

/**
 * 
 * @author anadal
 * 7 ene 2025 12:28:57
 */
public class MenuOptionComparator implements Comparator<MenuOption> {

    @Override
    public int compare(MenuOption o1, MenuOption o2) {
        return o1.order() - o2.order();
    }

}