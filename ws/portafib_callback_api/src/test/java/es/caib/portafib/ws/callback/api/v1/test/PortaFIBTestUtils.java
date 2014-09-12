package es.caib.portafib.ws.callback.api.v1.test;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import es.caib.portafib.ws.callback.api.v1.PortaFIBCallBackWs;
import es.caib.portafib.ws.callback.api.v1.PortaFIBCallBackWsService;


/**
 * 
 * @author anadal
 * 
 */
public abstract class PortaFIBTestUtils {

  public static final String CALLBACK = "PortaFIBCallBack";
  
  private static Properties testProperties = new Properties();
  
  static {
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


  public static PortaFIBCallBackWs getCallBackApi() {

    final String endpoint = getEndPoint(CALLBACK);

    PortaFIBCallBackWsService callbackService = new PortaFIBCallBackWsService();

    PortaFIBCallBackWs callbackApi = callbackService.getPortaFIBCallBackWs();

    // Adreça servidor
    Map<String, Object> reqContext = ((BindingProvider) callbackApi).getRequestContext();
    reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

    return callbackApi;

  }

}
