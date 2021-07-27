package es.caib.portafib.it.ws;

import es.caib.portafib.ws.api.v1.PortaFIBHelloWorldWs;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author anadal
 * 
 */
public class PortaFIBHelloWorldTest extends PortaFIBTestUtils {
  
  public static final Logger log = Logger.getLogger(PortaFIBHelloWorldTest.class);
  
  protected static PortaFIBHelloWorldWs helloWorldApi;
  
  /**
   * S'executa una vegada abans de l'execució de tots els tests d'aquesta classe
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    helloWorldApi = getHelloWorldApi();
  }
  
  @Test
  public void testVersio() throws Exception {
    if (isCAIB()) {
      Assert.assertEquals("2.0.21-caib", helloWorldApi.getVersion());
    } else {
      Assert.assertEquals("2.0.21", helloWorldApi.getVersion());
    }
  }

  @Test
  public void testVersioWs() throws Exception {
    Assert.assertEquals(1,helloWorldApi.getVersionWs());
  }
  
  
  @Test
  public void testEcho() throws Exception {
    final String echo = "eco ecooooo";
    Assert.assertEquals(helloWorldApi.echo(echo), echo);
  }



}
