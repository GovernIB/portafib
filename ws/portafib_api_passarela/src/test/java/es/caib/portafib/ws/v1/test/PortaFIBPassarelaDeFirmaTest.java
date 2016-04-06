package es.caib.portafib.ws.v1.test;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import es.caib.portafib.ws.api.v1.passarela.PortaFIBPassarelaDeFirmaWs;


/**
 *
 * @author anadal
 *
 */
public class PortaFIBPassarelaDeFirmaTest extends PortaFIBTestUtils {
  
  public static final Logger log = Logger.getLogger(PortaFIBPassarelaDeFirmaTest.class);

  protected static PortaFIBPassarelaDeFirmaWs passarelaDeFirmaAPI;

  protected static String usuariPersonaID = null;

  /**
   * S'executa una vegada abans de l'execució de tots els tests d'aquesta classe
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {

    passarelaDeFirmaAPI = getPassarelaDeFirmaApi();
  }

  @AfterClass
  public static void setUpAfterClass() throws Exception {

   
  }

  /*

  @Test
  public void testPeticioDeFirmaAmbErrors() throws Exception {

    PeticioDeFirmaWs peticioDeFirmaWs = null;

    try {
      peticioDeFirmaAPI.createPeticioDeFirma(peticioDeFirmaWs);
      Assert.fail("Hauria d'haver llançat una excepció: Null");
    } catch (WebServiceException wse) {
    }

    peticioDeFirmaWs = new PeticioDeFirmaWs();

    try {
      peticioDeFirmaAPI.createPeticioDeFirma(peticioDeFirmaWs);
      Assert.fail("Hauria d'haver llançat una excepció: Validation");
    } catch (WsValidationException ve) {
      Assert.assertEquals(9, ve.getFaultInfo().getFieldFaults().size());
    }

    String titol = "Peticio de Test";
    String remitent = "Helium";

    FitxerBean fitxerAFirmar = PeticioDeFirmaUtils.constructFitxerBeanFromResource("test.pdf",
        Constants.PDF_MIME_TYPE);

    String[] nifsNoExisteix = new String[] { "87654321X" };
    try {
      PeticioDeFirmaUtils.constructPeticioDeFirma(usuariEntitatAPI, titol, remitent,
          fitxerAFirmar, nifsNoExisteix);
      Assert.fail("Hauria d'haver llançat un error de nif desconegut");
    } catch (WsI18NException i18n) {
      log.info(i18n.getFaultInfo().getTranslation().getCode());
    }

    String[] nifs = new String[] { getTestPersonaNif() };
    peticioDeFirmaWs = PeticioDeFirmaUtils.constructPeticioDeFirma(usuariEntitatAPI, titol,
        remitent, fitxerAFirmar, nifs);

    peticioDeFirmaWs.setTitol(null);
    peticioDeFirmaWs.setFluxDeFirmes(null);

    try {
      peticioDeFirmaAPI.createPeticioDeFirma(peticioDeFirmaWs);
      Assert.fail("Hauria d'haver llançat una excepció: Validation");
    } catch (WsValidationException ve) {
      final int expected = 2;
      final int current = ve.getFaultInfo().getFieldFaults().size();
      if (expected != current) {
        log.info(ve.getMessage());
      }
      Assert.assertEquals(expected, current);
    }

  }

 */
  
  

}
