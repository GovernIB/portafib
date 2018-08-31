package es.caib.portafib.ws.test.v1;

import junit.framework.Assert;

import org.fundaciobit.pluginsib.core.utils.XTrustProvider;
import org.junit.Test;

import es.caib.portafib.ws.v1.HelloWorldIndraProxy;


/**
 * 
 * @author anadal
 * 
 */
public class HelloWorldIndraTest extends IndraTestUtils {

  /**
   * @param args
   */
  @Test
  public void testEcho() throws Exception  {

      String endPoint =  getEndPoint("HelloWorldIndra");
      
      if (endPoint.startsWith("https")) {
        XTrustProvider.install();
      }

      HelloWorldIndraProxy proxy = new HelloWorldIndraProxy(endPoint);

      String value = "Message";
      
      String echo = proxy.echo(value);
      
      Assert.assertEquals("Echo value is {" + value + "} (v1)", echo);
      
      // System.out.println("Echo: " + echo);


  }

}
