package es.caib.portafib.ws.test.v1;

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
      testProperties.load(new FileInputStream("test.properties"));
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
  
  

}
