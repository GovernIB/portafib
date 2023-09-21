package es.caib.portafib.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Locale;

/**
 * 
 * @author anadal
 * 
 */
public class Configuracio implements ConstantsV2 {

  static {
    loadPropertiesFromKey("es.caib.portafib.properties");
    loadPropertiesFromKey("es.caib.portafib.system.properties");
  }

  private static void loadPropertiesFromKey(String key) {
    String propertyFileName = System.getProperty(key);
    try (Reader reader = new FileReader(propertyFileName)){
      System.getProperties().load(reader);
    } catch (IOException ioe) {
      throw new RuntimeException(ioe);
    }
  }

  public static boolean isCAIB() {
    return Boolean.getBoolean(PORTAFIB_PROPERTY_BASE + "iscaib");
  }

  public static String getFilesDirectory() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "filesdirectory");
  }
  
  public static String getFileSystemManagerClass() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "filesystemmanagerclass");
  }
  
  public static boolean isDesenvolupament() {
    return Boolean.getBoolean(PORTAFIB_PROPERTY_BASE + "development");
  }

  public static String getDefaultLanguage() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "defaultlanguage", "ca");
  }

  public static Locale getDefaultLocale() {
    String defaultLanguage = System.getProperty(PORTAFIB_PROPERTY_BASE + "defaultlanguage", "ca");
    return new Locale(defaultLanguage);
  }


  /**
   * Permet indicar si volem mostrar als usuaris un enllaç cap a una APK de Android.
   * Si no existeix o el valor és buid, no es mostrarà cap enllaç.
   * Si el valor és "server", emprarà un APK distribuit amb l'aplicació.
   * Si el valor és una ruta de fitxers, emprarà l'APK indicat a la ruta de fitxers.
   */
  public static String getAndroidApk() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "androidapk", null);
  }

  public static byte[] getEncryptKey() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "encryptkey", "portafibportafib").getBytes();
  }


  public static String getExportDataPlugins() {
    return System.getProperty(PORTAFIB_PROPERTY_BASE + "exportdataplugins", null);
  }

  /**
   * Indica si s'ha de validar el certificat emprant el Plugin de CheckCertificate quan 
   * l'autenticació es realitza emprant ClientCert
   */
  public static boolean checkCertificateInClientCert() {
    return  Boolean.getBoolean(PORTAFIB_PROPERTY_BASE + "checkcertificateinclientcert");
  }
  
  
}
