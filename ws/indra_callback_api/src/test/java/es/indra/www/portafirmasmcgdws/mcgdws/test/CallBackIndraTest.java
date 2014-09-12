package es.indra.www.portafirmasmcgdws.mcgdws.test;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import es.indra.www.portafirmasmcgdws.mcgdws.ArrayOfLogMessage;
import es.indra.www.portafirmasmcgdws.mcgdws.CallbackResponse;
import es.indra.www.portafirmasmcgdws.mcgdws.LogMessage;
import es.indra.www.portafirmasmcgdws.mcgdws.MCGDws;
import es.indra.www.portafirmasmcgdws.mcgdws.MCGDwsService;

/**
 * 
 * @author anadal
 *
 */
public class CallBackIndraTest {

  
  /**
   * @param args
   */
  public static void main(String[] args) {
    try {

      String endPoint ="http://localhost:8080/portafib/cbindra/v0/PortafirmasCallBack";
      /*
      if (endPoint.startsWith("https")) {
        XTrustProvider.install();
      }
      */      

      MCGDwsService service = new MCGDwsService();
      MCGDws api = service.getMCGDWS();

      // Adreça, Usuari i contrasenya
      Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
      reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
      
      
     CallbackResponse cbresp = api.callback(null);
     
     System.out.println("Version: " + cbresp.getVersion());
     
     if (cbresp.getReturn() == +1.0) {
       
       System.out.println("Estat: OK");  
     } else {
       // Error
       System.out.println("Estat: Error");       
     }

     ArrayOfLogMessage logs = cbresp.getLogMessages(); 
     if (logs != null && logs.getItem() != null && logs.getItem().size() != 0) {
       System.out.println(" =========== LOGS ============ ");
       int i = 0;
       for (LogMessage logMessage : logs.getItem()) {          
         System.out.println("-------------LOG[" + i + "]------------------ ");
         System.out.println("Code = " + logMessage.getCode());
         System.out.println("Title = " + logMessage.getTitle());
         System.out.println("Severity = " + logMessage.getSeverity());
         System.out.println("Desc = " + logMessage.getDescription());
         System.out.println("------------------------------- ");
         i++;
      }
     } 
    
     

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  
  
}
