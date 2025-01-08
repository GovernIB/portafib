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

    private final Map<String, Map<MenuOption, Class<?>>> menuOptionsCache;
    
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

        Map<String, Map<MenuOption, Class<?>>> menuOptionsCacheLocal = new HashMap<String, Map<MenuOption, Class<?>>>();

        for (ClassInfo classInfo2 : cil) {
            // Obtén la clase
            Class<?> classe = classInfo2.loadClass();

            // Imprime la clase y el valor de la anotación
            MenuOption anotacio = classe.getAnnotation(MenuOption.class);
            if (anotacio != null) {

                String grup = anotacio.group();

                Map<MenuOption, Class<?>> menuOptions = menuOptionsCacheLocal.get(grup);

                if (menuOptions == null) {
                    menuOptions = new TreeMap<MenuOption, Class<?>>(new MenuOptionComparator());
                    menuOptionsCacheLocal.put(grup, menuOptions);
                }
                menuOptions.put(anotacio, classe);

            }
        }

        menuOptionsCache = menuOptionsCacheLocal;

    }

    /**
     * 
     * @return
     */

    public Map<String, Map<MenuOption, Class<?>>> getAllMenuOptions() {
        return menuOptionsCache;
    }

    /**
     * 
     * @param filterGroup
     * @return
     */
    public Map<MenuOption, Class<?>> getMenuOptionByGroup(String filterGroup) {
        return menuOptionsCache.get(filterGroup);
    }

}
