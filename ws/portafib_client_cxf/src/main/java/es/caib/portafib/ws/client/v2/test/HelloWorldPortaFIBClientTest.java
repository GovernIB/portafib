package es.caib.portafib.ws.client.v2.test;


import java.util.Map;
import javax.xml.ws.BindingProvider;

import es.caib.portafib.ws.client.v2.api.HelloWorldPortaFIB;
import es.caib.portafib.ws.client.v2.api.HelloWorldPortaFIBService;


/**
 * 
 * @author anadal
 * 
 */
public final class HelloWorldPortaFIBClientTest {

  public static void main(String[] args) {
    try {

      final String endpoint = "http://localhost:8080/portafibws/v2/HelloWorldPortaFIB";
      final boolean isCAIB = false;
      final String username = isCAIB? "anadal" : "anadalapp";
      final String password = isCAIB? "anadal" : "anadalapp";
      

      HelloWorldPortaFIBService service = new HelloWorldPortaFIBService();

      HelloWorldPortaFIB hello = service.getHelloWorldPortaFIB();

      // Adreça, Usuari i contrasenya
      Map<String, Object> reqContext = ((BindingProvider) hello).getRequestContext();
      reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
      reqContext.put(BindingProvider.USERNAME_PROPERTY, username);
      reqContext.put(BindingProvider.PASSWORD_PROPERTY, password);

      String res = hello.sayHi("Bones PortaFIB-WS");
      System.out.println("Resposta: " + res);
    } catch (Throwable th) {
      th.printStackTrace();
    }
  }

}
