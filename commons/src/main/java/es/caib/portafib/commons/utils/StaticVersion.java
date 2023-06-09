package es.caib.portafib.commons.utils;

import es.caib.portafib.utils.Configuracio;

/**
 * 
 * @author anadal
 *
 */
public class StaticVersion {
    
    protected static Version version = new Version();
    
    
    public static Version getVersion() {
        return version;
    }
    

    public static String getVersio() {
        return version.getVersion() + (Configuracio.isCAIB() ? "-caib" : "");
    }

    public static String getBuild() {
      return version.getBuildTime();
    }

}