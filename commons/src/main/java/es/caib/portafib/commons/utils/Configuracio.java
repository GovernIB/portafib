package es.caib.portafib.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Locale;
import java.util.Properties;

/**
 * 
 * @author anadal
 * 
 */
public class Configuracio implements Constants {

    private static final Logger LOG = LoggerFactory.getLogger(Configuracio.class);

    private static Properties portafibProperties;

    private static Properties portafibSystemProperties;

    public static Properties getPortaFIBProperties() {
        if (portafibProperties == null) {
            portafibProperties = loadPropertiesFromKey("es.caib.portafib.properties");
        }
        return portafibProperties;
    }

    public static Properties getPortaFIBSystemProperties() {
        if (portafibSystemProperties == null) {
            portafibSystemProperties = loadPropertiesFromKey("es.caib.portafib.system.properties");
        }
        return portafibSystemProperties;
    }

    private static Properties loadPropertiesFromKey(String key) {
        String propertyFileName = System.getProperty(key);
        try (Reader reader = new FileReader(propertyFileName)) {
            Properties prop = new Properties();
            prop.load(reader);
            return prop;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private static Long getLongPortaFIBProperty(String key) {
        String value = getPortaFIBProperties().getProperty(key);
        Long valueLong = null;
        if (value != null) {
            try {
                valueLong = Long.parseLong(value);
            } catch (Exception e) {
                LOG.error("Error parsing long value for key " + key, e);
            }
        }

        return valueLong;

    }

    public static Properties getSystemAndFileProperties() {
        Properties properties = new Properties();
        properties.putAll(System.getProperties());
        properties.putAll(getPortaFIBSystemProperties());
        properties.putAll(getPortaFIBProperties());
        return properties;
    }

    public static boolean isCAIB() {
        return "true".equalsIgnoreCase(getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "iscaib"));
    }

    public static String getFilesDirectory() {
        return getPortaFIBSystemProperties().getProperty(PORTAFIB_PROPERTY_BASE + "filesdirectory");
    }

    public static String getFileSystemManagerClass() {
        return getPortaFIBSystemProperties().getProperty(PORTAFIB_PROPERTY_BASE + "filesystemmanagerclass");
    }

    public static boolean isDesenvolupament() {
        return "true".equalsIgnoreCase(getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "development"));
    }
    
    public static boolean obfuscateUsernames() {
        if ("false".equalsIgnoreCase(getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "obfuscateusernames"))) {
            return false;
        } else {
            return true;
        }
    }

    public static String getDefaultLanguage() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "defaultlanguage", "ca");
    }

    public static Locale getDefaultLocale() {
        String defaultLanguage = getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "defaultlanguage", "ca");
        return new Locale(defaultLanguage);
    }

    /**
     * Permet indicar si volem mostrar als usuaris un enllaç cap a una APK de Android.
     * Si no existeix o el valor és buid, no es mostrarà cap enllaç.
     * Si el valor és "server", emprarà un APK distribuit amb l'aplicació.
     * Si el valor és una ruta de fitxers, emprarà l'APK indicat a la ruta de fitxers.
     */
    public static String getAndroidApk() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "androidapk", null);
    }

    public static byte[] getEncryptKey() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "encryptkey", "portafibportafib")
                .getBytes();
    }

    public static String getExportDataPlugins() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "exportdataplugins", null);
    }

    /**
     * Indica si s'ha de validar el certificat emprant el Plugin de CheckCertificate quan 
     * l'autenticació es realitza emprant ClientCert
     */
    public static boolean checkCertificateInClientCert() {
        return "true".equalsIgnoreCase(
                getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "checkcertificateinclientcert"));
    }

    public static String getAppUrl() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "url");
    }

    public static String getAppEmail() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "email.from");
    }

    public static String getAppName() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "name", "PortaFIB");
    }

    public static Long getMaxUploadSizeInBytes() {
        return getLongPortaFIBProperty(PORTAFIB_PROPERTY_BASE + "maxuploadsizeinbytes");
    }

    public static Long getMaxFitxerAdaptatSizeInBytes() {
        return getLongPortaFIBProperty(PORTAFIB_PROPERTY_BASE + "maxfitxeradaptatsizeinbytes");
    }

    public static boolean isCheckApplicationUserWithUserInformationPlugin() {
        final Properties sysprop = getPortaFIBSystemProperties();
        String check = sysprop.getProperty(PORTAFIB_PROPERTY_BASE + "checkapplicationuserwithuserinformationplugin");
        if (check == null) {
            return true;
        } else {
            return "true".equalsIgnoreCase(check);
        }
    }

}
