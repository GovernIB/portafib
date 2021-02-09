package es.caib.portafib.ws.v1.test;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.caib.portafib.ws.api.v1.PortaFIBHelloWorldWs;
import es.caib.portafib.ws.api.v1.utils.I18NUtils;

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
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    helloWorldApi = getHelloWorldApi();
  }
  
  @Test
  public void testVersio() throws Exception {
    if (isCAIB()) {
      Assert.assertEquals("2.0.16-caib", helloWorldApi.getVersion());
    } else {
      Assert.assertEquals("2.0.16", helloWorldApi.getVersion());
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
  

  public static void main(String[] args) {
    try {

      log.info(I18NUtils.tradueix(null, new Locale("ca"), "signant", new String[]{}));

      PortaFIBHelloWorldWs helloApi = getHelloWorldApi();

      log.info("Versió PortaFIB   : " + helloApi.getVersion());
      log.info("Versió PortaFIB-WS: " + helloApi.getVersionWs());

    } catch (Throwable th) {
      th.printStackTrace();
    }
  }


}
