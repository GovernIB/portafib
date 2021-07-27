package es.caib.portafib.it.indra;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * @author anadal
 * 
 */
public abstract class IndraTestUtils {

  
  private static Properties testProperties = new Properties();
  
  static {
    try {
      testProperties.load(new FileInputStream("indra.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
 

  public static String getEndPoint(String api) {
    return testProperties.getProperty("test_host") + api;
  }

  public static String getUserName() {
    return testProperties.getProperty("test_usr");
  }

  public static String getPassword() {
    return testProperties.getProperty("test_pwd");
  }
  
  
  public static String getSigner() {
    return testProperties.getProperty("test_signer");
  }
  

  public static boolean waitToSign() {
    return "true".equals(testProperties.getProperty("wait_to_sign"));
  }
  
  public static int getNombreClients() {
    return Integer.parseInt(testProperties.getProperty("nombreClients"));
  }
  
  public static int getNombrePeticionsClient() {
    return Integer.parseInt(testProperties.getProperty("nombrePeticionsClient"));
  }


}
