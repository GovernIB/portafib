package es.caib.portafib.ws.v1.example;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.api.v1.AnnexBean;
import es.caib.portafib.ws.api.v1.CustodiaInfoBean;
import es.caib.portafib.ws.api.v1.FitxerBean;
import es.caib.portafib.ws.api.v1.PeticioDeFirmaWs;
import es.caib.portafib.ws.api.v1.PortaFIBPeticioDeFirmaWs;
import es.caib.portafib.ws.api.v1.PortaFIBPeticioDeFirmaWsService;
import es.caib.portafib.ws.api.v1.PortaFIBUsuariEntitatWs;
import es.caib.portafib.ws.api.v1.PortaFIBUsuariEntitatWsService;
import es.caib.portafib.ws.api.v1.WsI18NException;
import es.caib.portafib.ws.api.v1.WsValidationException;
import es.caib.portafib.ws.api.v1.utils.I18NUtils;
import es.caib.portafib.ws.api.v1.utils.PeticioDeFirmaUtils;
import es.caib.portafib.ws.api.v1.utils.WsClientUtils;

/**
 * 
 * @author anadal
 *
 */
public class Exemple {
  
  public static final Logger log = Logger.getLogger(Exemple.class);
  
  private static final Properties testProperties = new Properties();
  
  static {
    // Traduccions
    try {
      Class.forName(I18NUtils.class.getName());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    // Propietats del Servidor i del test
    try {
      testProperties.load(new FileInputStream("test.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

    System.out.println("Entra dins l'exemple.");
    try {

      final String usr_app = getTestAppUserName(); // username application
      final String pwd_app = getTestAppPassword(); // password application
      
      System.out.println(" Connectant amb " + testProperties.getProperty("test_host")
          + " emprant l'usuari " + usr_app);
      
      String[] nifsDestinataris = getNifsDestinataris();

      System.out.println("nifsDestinataris = " + Arrays.toString(nifsDestinataris));
      
      if (nifsDestinataris == null) {
        throw new Exception("S'ha de definir la propietat nifsDestinataris dins test.properties");
      }
      

      PortaFIBPeticioDeFirmaWs api;
      {
        // Adreça servidor
        String endpoint = getEndPoint("PortaFIBPeticioDeFirma");;
        PortaFIBPeticioDeFirmaWsService service;
        log.info("endpoint " + endpoint);

        URL wsdl = new URL(endpoint + "?wsdl");
        service = new PortaFIBPeticioDeFirmaWsService(wsdl);
        api = service.getPortaFIBPeticioDeFirmaWs();

        Map<String, Object> reqContext;
        reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, usr_app);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd_app);
      }

      PortaFIBUsuariEntitatWs apiUE;
      {
        // Adreça servidor
        String endpoint = getEndPoint("PortaFIBUsuariEntitat");
        PortaFIBUsuariEntitatWsService service;
        URL wsdl = new URL(endpoint + "?wsdl");
        service = new PortaFIBUsuariEntitatWsService(wsdl);
        apiUE = service.getPortaFIBUsuariEntitatWs();

        Map<String, Object> reqContext;
        reqContext = ((BindingProvider) apiUE).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, usr_app);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd_app);
      }

      log.info("Versio " + api.getVersion());
      log.info("Idiomes:" + Arrays.toString(api.getSupportedLanguages().toArray()));

      final String titol = "Peticio de Test amb Custodia - "
         + ((System.currentTimeMillis() / 1000 ) % 100000);
      
      final String remitent = getRemitent();

      // Existeix sistema de custodia ??
      CustodiaInfoBean custodiaInfoBean = null;
      // Si language és un string buit s'assigna l'idioma del usuariApp
      final String lang = "";
      custodiaInfoBean = api.getDefaultCustodiaInfo(titol, lang);
      
      
      
      
      
      custodiaInfoBean = null;
      
      
      
      if (custodiaInfoBean == null) {
        log.info("Avis: No hi ha sistema de custodia definit"
            + " o l´usuari aplicació " + usr_app + " no pot custodiar");
      } else {        
        if (!custodiaInfoBean.isEditable()) {
          log.info("Avis: La custòdia per defecte NO ES MODIFICABLE");
        }
        log.info("Custodia info: " + custodiaInfoBean.getCustodiaInfoID());
        log.info("Titol petició: " + custodiaInfoBean.getTitolPeticio());
        log.info("Plantilla: " + custodiaInfoBean.getNomPlantilla());
        log.info("PluginID: " + custodiaInfoBean.getPluginID());
        log.info("Missatge posició pagina: " + custodiaInfoBean.getMissatgePosicioPaginaID());
      }
      //custodiaInfoBean = null;
      //if (true) return;

      // Annexes
      
      AnnexBean annex;
      if (getFitxerAAnnexar() == null) {
        annex = PeticioDeFirmaUtils.constructAnnexBeanFromResource(
            "/annex.txt", "text/plain", true, true);
      } else {
        annex = PeticioDeFirmaUtils.constructAnnexBeanFromFile(
            new File(getFitxerAAnnexar()), Constants.PDF_MIME_TYPE, true, true);
      }

      // Fitxer a Firmar
      FitxerBean fitxerAFirmar;
      if (getFitxerAFirmar() == null) {
        fitxerAFirmar = PeticioDeFirmaUtils.constructFitxerBeanFromResource(
             "/test.pdf", Constants.PDF_MIME_TYPE);
      } else {
        File f = new File(getFitxerAFirmar());
        String mime =  "application/octet-stream";
        fitxerAFirmar = PeticioDeFirmaUtils.constructFitxerBeanFromFile(f, mime);
      }


      // Crear Peticio
      PeticioDeFirmaWs peticioDeFirmaWs;
      peticioDeFirmaWs = PeticioDeFirmaUtils.constructPeticioDeFirma(apiUE, titol, remitent,
          fitxerAFirmar, nifsDestinataris);

      // Assignar Annexes a la Peticio
      peticioDeFirmaWs.getAnnexs().add(annex);

      // Assignar custodia a la Peticio
      peticioDeFirmaWs.setCustodiaInfo(custodiaInfoBean);

      Long peticioDeFirmaID = null;
      try {
        // Crear peticio
        if (isCreateAndStart()) {
          peticioDeFirmaWs = api.createAndStartPeticioDeFirma(peticioDeFirmaWs);
          log.info("Creada i arrancada petició: " + peticioDeFirmaWs.getPeticioDeFirmaID());
        } else {
          peticioDeFirmaWs = api.createPeticioDeFirma(peticioDeFirmaWs);
          log.info("Creada petició: " + peticioDeFirmaWs.getPeticioDeFirmaID());
//          throw new Exception("Error inventat");
          api.startPeticioDeFirma(peticioDeFirmaWs.getPeticioDeFirmaID());
          log.info("Arrancada petició: " + peticioDeFirmaWs.getPeticioDeFirmaID());
        }

        // Obtenir petició de Firma
        peticioDeFirmaID = peticioDeFirmaWs.getPeticioDeFirmaID();
        peticioDeFirmaWs = api.getPeticioDeFirma(peticioDeFirmaID);

        // Imprimir estat
        log.info("Estat de la peticio = "
            + peticioDeFirmaWs.getTipusEstatPeticioDeFirmaID());

        if (isWaitToSign()) {
          
          final int FIRMAT = Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT;
          
          final int REBUTJAT = Constants.TIPUSESTATPETICIODEFIRMA_REBUTJAT;
          
          System.out.println("Esperant a que la peticio es firmi o rebutgi ...");
          
          int estat;
          // AIXÒ NO S'HA DE FER !!!!!
          // S'HAN D'UTILITZAR ELS CALLBACKS PER RECUPERAR FITXERS SIGNATS !!!!!
          do {            
            Thread.sleep(5000);
            estat = api.getStateOfPeticioDeFirma(peticioDeFirmaID);
           
          } while (estat != FIRMAT && estat != REBUTJAT);
          
          if (estat == REBUTJAT) {
            peticioDeFirmaWs = api.getPeticioDeFirma(peticioDeFirmaID);
            System.err.println("La peticio de firma ha sigut rebutjada: " 
                + peticioDeFirmaWs.getMotiuDeRebuig());
            
          } else {

            // Info document firmat
            FitxerBean info;
            info = api.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);
    
            // Descarregar document
            FitxerBean fdoc;
            fdoc = api.downloadFileUsingEncryptedFileID(info.getEncryptedFileID());
    
            byte[] data = fdoc.getData();
            log.info("Tamany del fitxer: " + data.length);
            File signedFile = new File("signed_" + fdoc.getNom());
            FileOutputStream fos = new FileOutputStream(signedFile);
            fos.write(data);
            fos.flush();
            fos.close();
            
            System.out.println("El fitxer firmat s'ha guardat a " + signedFile.getAbsolutePath());
          }
        }

      } finally {
        if (peticioDeFirmaID != null && isDeleteOnFinish()) {
          // Esperam a que les notificacions s'enviin
          System.out.println(" Esperam a que les notificacions s'enviin .");
          for (int i = 0; i < 20; i++) {
            System.out.print(".");
            Thread.sleep(500);  
          }
          System.out.println();
          
          // Esborrar la petició
          api.deletePeticioDeFirma(peticioDeFirmaID);
        }
      }

    } catch (WsI18NException i18ne) {
      System.err.println(WsClientUtils.toString(i18ne));
    } catch (WsValidationException ve) {
      System.err.println(WsClientUtils.toString(ve));
    } catch (Exception e) {
      System.err.println("Error desconegut: " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  
  
  
  
  public static String getEndPoint(String api) {
    return testProperties.getProperty("test_host") + api;
  }

  public static String getTestAppUserName() {
    return testProperties.getProperty("test_usr");
  }
  
  public static boolean isCAIB() {
    return "true".equals(testProperties.getProperty("iscaib"));
  }

  public static String getTestAppPassword() {
    return testProperties.getProperty("test_pwd");
  }

  public static String[] getNifsDestinataris() {
     String tmp = testProperties.getProperty("nifsDestinataris");
     if (tmp == null || tmp.trim().length() == 0) {
       return null;
     }
     return tmp.split(",");
  }

  public static boolean isWaitToSign() {
    return "true".equals(testProperties.getProperty("waittosign"));
  }

  public static boolean isCreateAndStart() {
    return "true".equals(testProperties.getProperty("createAndStart"));
  }

  public static boolean isDeleteOnFinish() {
    return "true".equals(testProperties.getProperty("deleteonfinish"));
  }
  
  public static String getFitxerAFirmar() {
    return testProperties.getProperty("fitxerAFirmar");
  }
  
  public static String getFitxerAAnnexar() {
    return testProperties.getProperty("fitxerAAnnexar");
  }

  public static String getRemitent() {
      return testProperties.getProperty("remitent");
 }

}
