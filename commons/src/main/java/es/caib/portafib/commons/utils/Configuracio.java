package es.caib.portafib.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author anadal
 * 
 */
public class Configuracio implements Constants {

    private static final Logger LOG = LoggerFactory.getLogger(Configuracio.class);

    private static final Properties fileProperties = new Properties();
    
    private static final Properties fileAndSystemProperties = new Properties();

    /*
     * Agafa els fitxers de propietats definits a l'standalone
     *
     * Seguim els estandars de la CAIB 
     */
    public static Properties getFilesProperties() {
        
        if (fileProperties.isEmpty()) {
            // matches the property name as defined in the system-properties element in
            // WildFly
            String propertyFile = System.getProperty(Constants.PORTAFIB_PROPERTY_BASE + "properties");
            File file = new File(propertyFile);
    
            String propertySystemFile = System.getProperty(Constants.PORTAFIB_PROPERTY_BASE + "system.properties");
            File systemFile = new File(propertySystemFile);
    
            try {
                fileProperties.load(new FileInputStream(file));
                fileProperties.load(new FileInputStream(systemFile));
            } catch (IOException e) {
                LOG.error("No es pot carregar algun dels fitxers de propietats ... ", e);
            }
        }
        
        return fileProperties;

    }

    public static Properties getSystemAndFileProperties() {

        if (fileAndSystemProperties.isEmpty()) {
            fileAndSystemProperties.putAll(getFilesProperties());
            fileAndSystemProperties.putAll(System.getProperties());
        }
        return fileAndSystemProperties;
    }

    public static String getProperty(String key) {

        return  getFilesProperties().getProperty(key);

    }

    public static String getProperty(String key, String def) {
        
        return getFilesProperties().getProperty(key, def);

    }

    public static boolean isDesenvolupament() {

        return Boolean.parseBoolean(getProperty(PORTAFIB_PROPERTY_BASE + "development"));
    }

    public static boolean isCAIB() {
        return Boolean.parseBoolean(getProperty(PORTAFIB_PROPERTY_BASE + "iscaib"));
    }

    public static String getAppUrl() {
        return getProperty(PORTAFIB_PROPERTY_BASE + "url");
    }

    public static String getAppEmail() {
        return getProperty(PORTAFIB_PROPERTY_BASE + "email.from");
    }

    public static String getAppName() {
        return getProperty(PORTAFIB_PROPERTY_BASE + "name", "PortaFIB");
    }

    public static String getDefaultLanguage() {
        return getProperty(PORTAFIB_PROPERTY_BASE + "defaultlanguage", "ca");
    }

    public static byte[] getEncryptKey() {
        return getProperty(PORTAFIB_PROPERTY_BASE + "encryptkey", "0123456789123456").getBytes();
    }

    public static Long getMaxUploadSizeInBytes() {
        return Long.getLong(PORTAFIB_PROPERTY_BASE + "maxuploadsizeinbytes");
    }

    public static Long getMaxFitxerAdaptatSizeInBytes() {
        return Long.getLong(PORTAFIB_PROPERTY_BASE + "maxfitxeradaptatsizeinbytes");
    }

    public static File getFilesDirectory() {
        String path = getProperty(PORTAFIB_PROPERTY_BASE + "filesdirectory");
        return new File(path);
    }

    public static String getFileSystemManager() {
        return getProperty(PORTAFIB_PROPERTY_BASE + "filesystemmanagerclass");
    }

}
