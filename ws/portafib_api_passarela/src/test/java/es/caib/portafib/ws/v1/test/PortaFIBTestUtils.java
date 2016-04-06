package es.caib.portafib.ws.v1.test;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import es.caib.portafib.ws.api.v1.passarela.PortaFIBPassarelaDeFirmaWs;
import es.caib.portafib.ws.api.v1.passarela.PortaFIBPassarelaDeFirmaWsService;
import es.caib.portafib.ws.api.v1.passarela.utils.I18NUtils;


/**
 * 
 * @author anadal
 * 
 */
public abstract class PortaFIBTestUtils {


  
  public static final String PASSARELA_DE_FIRMA = "PortaFIBPassarelaDeFirma";
  
  private static Properties testProperties = new Properties();
  
  static {
    // Traduccions
    try {
      Class.forName(I18NUtils.class.getName());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    // Propietats del Servidor
    try {
      testProperties.load(new FileInputStream("test.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
 

  public static String getEndPoint(String api) {
    return testProperties.getProperty("test_host") + api;
  }

  public static String getTestAppUserName() {
    
    return testProperties.getProperty("test_usr");
  }

  
  public static boolean isCAIB() {
    return "true".equals(testProperties.getProperty("iscaib"));
  }

  public static String getTestAppPassword() {
    return testProperties.getProperty("test_pwd");
  }

  public static String getTestPersonaNif() {
    return testProperties.getProperty("test_nif");
  }
  
  public static boolean isWaitToSign() {
    return "true".equals(testProperties.getProperty("waittosign"));
  }
  

  public static void configAddressUserPassword(String usr, String pwd,
      String endpoint, Object api) {

    Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
    reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
    reqContext.put(BindingProvider.USERNAME_PROPERTY, usr);
    reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd);
  }

 
  
  
  public static PortaFIBPassarelaDeFirmaWs getPassarelaDeFirmaApi() throws Exception  {
    final String endpoint = getEndPoint(PASSARELA_DE_FIRMA);
    URL wsdl = new URL(endpoint + "?wsdl");
    PortaFIBPassarelaDeFirmaWsService service = new PortaFIBPassarelaDeFirmaWsService(wsdl);

    PortaFIBPassarelaDeFirmaWs api = service.getPortaFIBPassarelaDeFirmaWs();

    configAddressUserPassword(getTestAppUserName(), getTestAppPassword(), endpoint, api);

    return api;
  }

}
