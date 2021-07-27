package es.caib.portafib.it.indra;

import junit.framework.Assert;

import org.junit.Test;

import es.caib.portafib.ws.v1.HelloWorldIndraProxy;


/**
 * 
 * @author anadal
 * 
 */
public class HelloWorldIndraTest extends IndraTestUtils {

  @Test
  public void testEcho() throws Exception  {

      String endPoint =  getEndPoint("HelloWorldIndra");
      
      HelloWorldIndraProxy proxy = new HelloWorldIndraProxy(endPoint);

      String value = "Message";
      
      String echo = proxy.echo(value);
      
      Assert.assertEquals("Echo value is {" + value + "} (v1)", echo);
      
      // System.out.println("Echo: " + echo);


  }

}
