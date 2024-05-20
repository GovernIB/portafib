package es.caib.portafib.commons.utils;

/**
 * 
 * @author anadal (u80067)
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