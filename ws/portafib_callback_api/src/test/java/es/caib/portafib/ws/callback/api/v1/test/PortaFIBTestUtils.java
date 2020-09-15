package es.caib.portafib.ws.callback.api.v1.test;

import java.io.FileInputStream;
import java.net.URL;
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

  //public static final String CALLBACK = "PortaFIBCallBack";
  
  private static Properties testProperties = new Properties();
  
  static {
    // Propietats del Servidor
    try {
      testProperties.load(new FileInputStream("test.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
 

  public static String getEndPoint() {
    return testProperties.getProperty("test_host");
  }


  public static PortaFIBCallBackWs getCallBackApi() throws Exception {
    
    

    final String endpoint = getEndPoint();
    
    PortaFIBCallBackWsService callbackService = new PortaFIBCallBackWsService(new URL(endpoint + "?wsdl"));

    PortaFIBCallBackWs callbackApi = callbackService.getPortaFIBCallBackWs();
    
    
    
    /*
    String keyPassword = "ckpass";
    KeyStore keyStore = KeyStore.getInstance("JKS");
    keyStore.load(new FileInputStream(keyStoreLoc), "cspass".toCharArray());
    KeyManager[] myKeyManagers = getKeyManagers(keyStore, keyPassword);
    tlsCP.setKeyManagers(myKeyManagers);

    
    KeyStore trustStore = KeyStore.getInstance("JKS");
    trustStore.load(new FileInputStream(keyStoreLoc), "cspass".toCharArray());
    TrustManager[] myTrustStoreKeyManagers = getTrustManagers(trustStore);
    tlsCP.setTrustManagers(myTrustStoreKeyManagers);
    tlsCP.setDisableCNCheck(true);
    */
    
    
    /*
    HTTPConduit httpConduit = (HTTPConduit) ClientProxy.getClient(callbackApi).getConduit();
    
    TLSClientParameters tlsCP = new TLSClientParameters();
    
    KeyManager[] myKeyManagers = new KeyManager[] {
        
        //new KeyManager() {    }
        
    };
    tlsCP.setKeyManagers(myKeyManagers);
    
    
    TrustManager[] myTrustStoreKeyManagers = new TrustManager[] { new X509TrustManager() {
      public X509Certificate[] getAcceptedIssuers() {
        return null;
      }

      public void checkClientTrusted(X509Certificate[] certs, String authType) {
      }

      public void checkServerTrusted(X509Certificate[] certs, String authType) {
      }
    } };
    
    tlsCP.setTrustManagers(myTrustStoreKeyManagers);
    
    
    tlsCP.setDisableCNCheck(true);
    
//    tlsCP.setUseHttpsURLConnectionDefaultHostnameVerifier(false);
//    tlsCP.setUseHttpsURLConnectionDefaultSslSocketFactory(true);
    
    httpConduit.setTlsClientParameters(tlsCP);
    */
    
    
    

    // Adreça servidor
    Map<String, Object> reqContext = ((BindingProvider) callbackApi).getRequestContext();
    reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

    return callbackApi;

  }

}
