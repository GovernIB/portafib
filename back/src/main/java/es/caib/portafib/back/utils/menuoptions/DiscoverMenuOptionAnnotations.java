package es.caib.portafib.back.utils.menuoptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

/**
 * 
 * @author anadal
 * 7 ene 2025 12:34:50
 */
public class DiscoverMenuOptionAnnotations {

    private final Map<String, TreeMap<MenuItemOption, Class<?>>> menuOptionsCache;

    public DiscoverMenuOptionAnnotations() {
        this(null);
    }

    public DiscoverMenuOptionAnnotations(String packageToScan) {

        ClassGraph cg = new ClassGraph().enableAnnotationInfo();

        if (packageToScan != null) {
            cg.acceptPackages(packageToScan);
        }

        // Habilitar escaneo de anotaciones
        ScanResult scanResult = cg.scan();

        ArrayList<ClassInfo> cil = scanResult.getClassesWithAnnotation(MenuOption.class);

        Map<String, TreeMap<MenuItemOption, Class<?>>> menuOptionsCacheLocal = new HashMap<String, TreeMap<MenuItemOption, Class<?>>>();

        for (ClassInfo classInfo2 : cil) {
            // Obtén la clase
            Class<?> classe = classInfo2.loadClass();

            // Imprime la clase y el valor de la anotación
            MenuOption anotacio = classe.getAnnotation(MenuOption.class);
            if (anotacio != null) {

                String grup = anotacio.group();

                TreeMap<MenuItemOption, Class<?>> menuOptions = menuOptionsCacheLocal.get(grup);

                if (menuOptions == null) {
                    menuOptions = new TreeMap<MenuItemOption, Class<?>>(new MenuItemOptionComparator());
                    menuOptionsCacheLocal.put(grup, menuOptions);
                }

                MenuItemOption menuItemOption = new MenuItemOption(anotacio.labelCode(), anotacio.relativeLink(),
                        anotacio.baseLink(), anotacio.order(), anotacio.addSeparatorBefore(),
                        anotacio.addSeparatorAfter(), anotacio.group());

                menuOptions.put(menuItemOption, classe);

            }
        }

        menuOptionsCache = menuOptionsCacheLocal;

    }

    /**
     * 
     * @return
     */

    public Map<String, TreeMap<MenuItemOption, Class<?>>> getAllMenuOptions() {
        return menuOptionsCache;
    }

    /**
     * 
     * @param filterGroup
     * @return
     */
    public TreeMap<MenuItemOption, Class<?>> getMenuOptionByGroup(String filterGroup) {
        return menuOptionsCache.get(filterGroup);
    }

}
