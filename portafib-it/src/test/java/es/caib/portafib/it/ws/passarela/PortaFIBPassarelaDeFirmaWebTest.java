package es.caib.portafib.it.ws.passarela;

import es.caib.portafib.it.ws.PortaFIBTestUtils;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.PortaFIBPassarelaDeFirmaWebWs;
import es.caib.portafib.ws.api.v1.passarelafirmaweb.WsI18NException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author anadal
 * @author areus
 */
public class PortaFIBPassarelaDeFirmaWebTest extends PortaFIBTestUtils {

  protected static PortaFIBPassarelaDeFirmaWebWs passarelaDeFirmaWebAPI;

  /**
   * S'executa una vegada abans de l'execució de tots els tests d'aquesta classe
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    passarelaDeFirmaWebAPI = PortaFIBTestUtils.getPassarelaDeFirmaApi();
  }

  @Test
  public void testVersio() {
    Assert.assertEquals("2.0.21-caib", passarelaDeFirmaWebAPI.getVersion());
  }


  @Test
  public void testVersioWS() {
    Assert.assertEquals(1, passarelaDeFirmaWebAPI.getVersionWs());
  }

  @Test
  public void testEntitatID() {
    Assert.assertEquals("fundaciobit", passarelaDeFirmaWebAPI.getEntitatID());
  }

  @Test
  public void testSupportedLangs() throws WsI18NException {
    Set<String> expectedLangs = new HashSet<String>();
    expectedLangs.add("ca");
    expectedLangs.add("es");
    Assert.assertEquals(expectedLangs, new HashSet<String>(passarelaDeFirmaWebAPI.getSupportedLanguages()));
  }


}
