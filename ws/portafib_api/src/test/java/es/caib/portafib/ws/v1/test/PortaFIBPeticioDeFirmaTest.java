package es.caib.portafib.ws.v1.test;

import java.util.List;

import javax.xml.ws.WebServiceException;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.api.v1.AnnexBean;
import es.caib.portafib.ws.api.v1.CustodiaInfoBean;
import es.caib.portafib.ws.api.v1.FitxerBean;
import es.caib.portafib.ws.api.v1.FluxDeFirmesWs;
import es.caib.portafib.ws.api.v1.PeticioDeFirmaWs;
import es.caib.portafib.ws.api.v1.PortaFIBPeticioDeFirmaWs;
import es.caib.portafib.ws.api.v1.PortaFIBUsuariEntitatWs;
import es.caib.portafib.ws.api.v1.TipusDocumentInfoWs;
import es.caib.portafib.ws.api.v1.UsuariEntitatBean;
import es.caib.portafib.ws.api.v1.WsI18NException;
import es.caib.portafib.ws.api.v1.WsValidationException;
import es.caib.portafib.ws.api.v1.utils.PeticioDeFirmaUtils;

/**
 * 
 * @author anadal
 * 
 */
public class PortaFIBPeticioDeFirmaTest extends PortaFIBTestUtils {
  
  public static final Logger log = Logger.getLogger(PortaFIBPeticioDeFirmaTest.class);

  protected static PortaFIBPeticioDeFirmaWs peticioDeFirmaAPI;

  protected static PortaFIBUsuariEntitatWs usuariEntitatAPI;

  protected static String usuariPersonaID = null;

  protected static String usuariEntitatID = null;

  /**
   * S'executa una vegada abans de l'execució de tots els tests d'aquesta classe
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    usuariEntitatAPI = getUsuariEntitatApi();
    peticioDeFirmaAPI = getPeticioDeFirmaApi();

    // Crear usuari Persona i usuari-entitat
    String nif = getTestPersonaNif();
    String entitatID = peticioDeFirmaAPI.getEntitatID();

    usuariPersonaID = PortaFIBUsuariEntitatTest.createUsuariPersona(usuariEntitatAPI, nif);
    // Crear usuari Entitat

    UsuariEntitatBean ue = usuariEntitatAPI.createUsuariEntitatSimple(nif, entitatID);
    usuariEntitatID = ue.getUsuariEntitatID();
  }

  @AfterClass
  public static void setUpAfterClass() throws Exception {

    if (usuariPersonaID != null) {
      if (usuariEntitatID != null) {
        usuariEntitatAPI.deleteUsuariEntitat(usuariEntitatID);
      }
      usuariEntitatAPI.deleteUsuariPersona(usuariPersonaID);
    }
  }


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


  @Test
  public void testPeticioDeFirma() throws Exception {

    String titol = "Peticio de Test";
    String remitent = "Helium";

    FitxerBean fitxerAFirmar = PeticioDeFirmaUtils.constructFitxerBeanFromResource("test.pdf",
        Constants.PDF_MIME_TYPE);

    String[] nifs = new String[] { getTestPersonaNif() };

    // Petico BASE
    PeticioDeFirmaWs peticioDeFirmaWs;
    peticioDeFirmaWs = PeticioDeFirmaUtils.constructPeticioDeFirma(usuariEntitatAPI, titol,
        remitent, fitxerAFirmar, nifs);
    
    // Annexes
    List<AnnexBean> annexes = peticioDeFirmaWs.getAnnexs();
    String[] annexesResources = { "annex_1.txt", "annex_2.jpg", "annex_3.bin" };
    String[] mime = { "text/plain" , "image/jpeg" , "application/octet-stream"};
    for (int i = 0; i < annexesResources.length; i++) {
      final boolean adjuntar = true;
      final boolean firmar = true;
      annexes.add(PeticioDeFirmaUtils.constructAnnexBeanFromResource(annexesResources[i],
          mime[i], adjuntar, firmar));
    }
    

    PeticioDeFirmaWs peticioDeFirmaWsResposta = null;
    long peticioDeFirmaID;
    try {
      // Crear peticio
      peticioDeFirmaWsResposta = peticioDeFirmaAPI.createPeticioDeFirma(peticioDeFirmaWs);
      peticioDeFirmaID = peticioDeFirmaWsResposta.getPeticioDeFirmaID();
      log.info("Creada peticio amb ID = " + peticioDeFirmaID);
      
      // Check Annex
      Assert.assertEquals(annexesResources.length, peticioDeFirmaWsResposta.getAnnexs().size());
      
      // Check estat
      int status = peticioDeFirmaAPI.getStateOfPeticioDeFirma(peticioDeFirmaID);
      Assert.assertEquals(Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT, status);
      // Check fitxer
      FitxerBean fitxer;
      fitxer = peticioDeFirmaAPI.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);

      Assert.assertEquals(fitxer.getTamany(), fitxerAFirmar.getTamany());

      FitxerBean downloadedFitxer;
      downloadedFitxer = peticioDeFirmaAPI.downloadFileUsingEncryptedFileID(fitxer
          .getEncryptedFileID());
      Assert.assertEquals(downloadedFitxer.getTamany(), fitxerAFirmar.getTamany());

      // Arrancar
      peticioDeFirmaAPI.startPeticioDeFirma(peticioDeFirmaID);

      // Check estat
      status = peticioDeFirmaAPI.getStateOfPeticioDeFirma(peticioDeFirmaID);
      Assert.assertEquals(Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES, status);
      // Check fitxer
      fitxer = peticioDeFirmaAPI.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);
      Assert.assertTrue(
          "El fitxer original i el fitxer d'una peticio iniciada no han de ser iguals",
          fitxer.getTamany() != fitxerAFirmar.getTamany());


      if (isWaitToSign()) {
        
        System.out.println("Esperant a que es firmi o denegui la petició ...");
        Thread.sleep(10000);
        do {
          System.out.println(" Esperant ...");
          Thread.sleep(5000);
          status = peticioDeFirmaAPI.getStateOfPeticioDeFirma(peticioDeFirmaID);
        } while(status != Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT &&
            status != Constants.TIPUSESTATPETICIODEFIRMA_REBUTJAT);
                
        
      } else {
        
        // Pause
        peticioDeFirmaAPI.pausePeticioDeFirma(peticioDeFirmaID);
        // Check estat
        status = peticioDeFirmaAPI.getStateOfPeticioDeFirma(peticioDeFirmaID);
        Assert.assertEquals(Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT, status);
  
        // Restart
        peticioDeFirmaAPI.startPeticioDeFirma(peticioDeFirmaID);
  
        status = peticioDeFirmaAPI.getStateOfPeticioDeFirma(peticioDeFirmaID);
        Assert.assertEquals(Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES, status);
  
        // Get peticio de Firma
        PeticioDeFirmaWs peticio = peticioDeFirmaAPI.getPeticioDeFirma(peticioDeFirmaID);
  
        Assert.assertEquals(peticioDeFirmaWsResposta.getTitol(), peticio.getTitol());
        Assert.assertEquals(peticioDeFirmaWsResposta.getPeticioDeFirmaID(),
            peticio.getPeticioDeFirmaID());
        Assert.assertEquals(Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES,
            peticio.getTipusEstatPeticioDeFirmaID());
      }
      
      

    } finally {
      if (peticioDeFirmaWsResposta != null) {
        peticioDeFirmaAPI.deletePeticioDeFirma(peticioDeFirmaWsResposta.getPeticioDeFirmaID());
      }
    }

  }


  @Test
  public void testPeticioDeFirmaAmbCustodia() throws Exception {
    final String titol = "Peticio de Test amb Custodia";
    final String remitent = "Helium";

    // Existeix sistems de custodia
    String pluginClassID = peticioDeFirmaAPI.getCurrentCustodiaPluginClass();
    if (pluginClassID == null) {
      Assert.assertNotNull("No hi ha sistema de custodia definit o l´usuari aplicació "
          + getTestAppUserName() + " no pot custodiar", pluginClassID);
      return;
    }

    // Obtenir sistema de custodia
    // Si language és un string buit s'ha d'assignar l'idioma del usuariApp
    final String language = "";
    CustodiaInfoBean custodiaInfoBean = peticioDeFirmaAPI.getDefaultCustodiaInfo(titol,
        language);

    Assert.assertNotNull(custodiaInfoBean);
    Assert.assertEquals(custodiaInfoBean.getCustodiaPluginClassID(), pluginClassID);

    // Crear Peticio
    FitxerBean fitxerAFirmar = PeticioDeFirmaUtils.constructFitxerBeanFromResource("test.pdf",
        Constants.PDF_MIME_TYPE);

    String[] nifs = new String[] { getTestPersonaNif() };

    PeticioDeFirmaWs peticioDeFirmaWs;
    peticioDeFirmaWs = PeticioDeFirmaUtils.constructPeticioDeFirma(usuariEntitatAPI, titol,
        remitent, fitxerAFirmar, nifs);

    // Assignar custodia a la peticio
    peticioDeFirmaWs.setCustodiaInfo(custodiaInfoBean);

    PeticioDeFirmaWs peticioDeFirmaWsResposta = null;
    long peticioDeFirmaID;
    try {
      // Crear peticio
      peticioDeFirmaWsResposta = peticioDeFirmaAPI.createPeticioDeFirma(peticioDeFirmaWs);
      peticioDeFirmaID = peticioDeFirmaWsResposta.getPeticioDeFirmaID();
      log.info("Creada peticio amb ID = " + peticioDeFirmaID);

      // Get peticio de Firma
      PeticioDeFirmaWs peticio = peticioDeFirmaAPI.getPeticioDeFirma(peticioDeFirmaID);

      Assert.assertEquals(Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT,
          peticio.getTipusEstatPeticioDeFirmaID());

      CustodiaInfoBean custodia = peticio.getCustodiaInfo();

      Assert.assertNotNull("Custodia és null i no ho hauria de ser", custodia);
      Assert.assertNotNull("CustodiaID és null i no ho hauria de ser",
          peticio.getCustodiaInfoID());
      Assert.assertNull("URL de custodia ha de valer NULL", custodia.getUrlFitxerCustodiat());

      // Arrancar
      peticioDeFirmaAPI.startPeticioDeFirma(peticioDeFirmaID);

      peticio = peticioDeFirmaAPI.getPeticioDeFirma(peticioDeFirmaID);

      Assert.assertEquals(Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES,
          peticio.getTipusEstatPeticioDeFirmaID());

      custodia = peticio.getCustodiaInfo();

      Assert.assertNotNull("Custodia és null i no ho hauria de ser", custodia);
      Assert.assertNotNull("CustodiaID és null i no ho hauria de ser",
          peticio.getCustodiaInfoID());
      Assert.assertNotNull("URL de custodia no ha de valer NULL",
          custodia.getUrlFitxerCustodiat());

    } finally {
      if (peticioDeFirmaWsResposta != null) {
        peticioDeFirmaAPI.deletePeticioDeFirma(peticioDeFirmaWsResposta.getPeticioDeFirmaID());
      }
    }

  }
  
  
  @Test
  public void testTipusDocuments() throws Exception {
    List<TipusDocumentInfoWs> list = peticioDeFirmaAPI.getTipusDeDocuments("es");
    
    for (TipusDocumentInfoWs td : list) {
      log.info(td.getTipusDocumentID() + "\t" + td.getNom());
    }

  }
  
  
  @Test
  public void testPlantillaFluxDeFirmes() throws Exception {
    int status;
    PeticioDeFirmaWs peticioDeFirmaWsResposta = null;
    Long plantillaID = null;
    try {
    String[] nifs = new String[] { getTestPersonaNif() };
    FluxDeFirmesWs fluxPlantilla = PeticioDeFirmaUtils.constructFluxDeFirmesWs(usuariEntitatAPI, nifs);
    
    
    plantillaID = peticioDeFirmaAPI.createPlantillaFluxDeFirmes(fluxPlantilla, true);
    
       System.out.println("Plantilla ID = " + plantillaID);
    
    
    final String titol = "Peticio a Partir de Plantilla";
    final String remitent = "Sistra";
    
    
    // Crear Peticio
    FitxerBean fitxerAFirmar = PeticioDeFirmaUtils.constructFitxerBeanFromResource("test.pdf",
        Constants.PDF_MIME_TYPE);

    
    FluxDeFirmesWs plantillaInstance = peticioDeFirmaAPI.instantiatePlantillaFluxDeFirmes(plantillaID);
    

    PeticioDeFirmaWs peticioDeFirmaWs;
    peticioDeFirmaWs = PeticioDeFirmaUtils.constructPeticioDeFirma(titol,
        remitent, fitxerAFirmar, plantillaInstance);
    
    peticioDeFirmaWsResposta = peticioDeFirmaAPI.createAndStartPeticioDeFirma(peticioDeFirmaWs);
    long peticioDeFirmaID = peticioDeFirmaWsResposta.getPeticioDeFirmaID();
    
    if (isWaitToSign()) {
      
      System.out.println("Esperant a que es firmi o denegui la petició ...");
      Thread.sleep(10000);
      do {
        System.out.println(" Esperant ...");
        Thread.sleep(5000);
        status = peticioDeFirmaAPI.getStateOfPeticioDeFirma(peticioDeFirmaID);
      } while(status != Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT &&
          status != Constants.TIPUSESTATPETICIODEFIRMA_REBUTJAT);
              
      
    }
    
    } finally {
      if (peticioDeFirmaWsResposta != null) {
        peticioDeFirmaAPI.deletePeticioDeFirma(peticioDeFirmaWsResposta.getPeticioDeFirmaID());
      }
      if (plantillaID != null) {
        peticioDeFirmaAPI.deletePlantillaFluxDeFirmes(plantillaID);
      }
    }
    
    
  }
  
  
  

}
