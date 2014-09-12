package es.caib.portafib.ws.v1.test;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

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
import es.caib.portafib.ws.api.v1.utils.PeticioDeFirmaUtils;
import es.caib.portafib.ws.api.v1.utils.WsClientUtils;
/**
 * 
 * @author anadal
 *
 */
public class Exemple {
  
  public static final Logger log = Logger.getLogger(Exemple.class);

  public static void main(String[] args) {

    try {

      String usr_app = "fundaciobit_usrapp"; // username application
      String pwd_app = "fundaciobit_usrapp"; // password application
      // Adreça servidor
      final String endpointBase = "http://localhost:8080/portafib/ws/v1/";

      PortaFIBPeticioDeFirmaWs api;
      {
        PortaFIBPeticioDeFirmaWsService service;
        service = new PortaFIBPeticioDeFirmaWsService();
        api = service.getPortaFIBPeticioDeFirmaWs();

        String endpoint = endpointBase + "PortaFIBPeticioDeFirma";

        Map<String, Object> reqContext;
        reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, usr_app);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd_app);
      }

      PortaFIBUsuariEntitatWs apiUE;
      {
        PortaFIBUsuariEntitatWsService service;
        service = new PortaFIBUsuariEntitatWsService();
        apiUE = service.getPortaFIBUsuariEntitatWs();

        String endpoint = endpointBase + "PortaFIBUsuariEntitat";

        Map<String, Object> reqContext;
        reqContext = ((BindingProvider) apiUE).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, usr_app);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd_app);
      }

      log.info("Versio " + api.getVersion());
      log.info("Idiomes:"
          + Arrays.toString(api.getSupportedLanguages().getItem().toArray()));

      final String titol = "Peticio de Test amb Custodia";
      final String remitent = "Helium";

      // Existeix sistema de custodia
      CustodiaInfoBean custodiaInfoBean = null;
      String pluginClassID = api.getCurrentCustodiaPluginClass();
      if (pluginClassID == null) {
        log.info("Avis: No hi ha sistema de custodia definit"
            + " o l´usuari aplicació " + usr_app + " no pot custodiar");
      } else {
        // Obtenir sistema de custodia
        // Si language és un string buit s'assigna l'idioma del usuariApp
        final String lang = "";
        custodiaInfoBean = api.getDefaultCustodiaInfo(titol, lang);
      }

      // Annexes
      AnnexBean annex = PeticioDeFirmaUtils.constructAnnexBeanFromFile(new File(
          "c:\\annex.txt"), "text/plain", true, true);

      // Fitxer a Firmar
      FitxerBean fitxerAFirmar;
      fitxerAFirmar = PeticioDeFirmaUtils.constructFitxerBeanFromFile(
          new File("c:\\test.pdf"), Constants.PDF_MIME_TYPE);

      String[] nifs = new String[] { "11223344T" };

      // Crear Peticio
      PeticioDeFirmaWs peticioDeFirmaWs;
      peticioDeFirmaWs = PeticioDeFirmaUtils.constructPeticioDeFirma(apiUE, titol, remitent,
          fitxerAFirmar, nifs);

      // Assignar Annexes a la Peticio
      peticioDeFirmaWs.getAnnexs().add(annex);

      // Assignar custodia a la Peticio
      peticioDeFirmaWs.setCustodiaInfo(custodiaInfoBean);

      Long peticioDeFirmaID = null;
      try {
        // Crear peticio
        peticioDeFirmaWs = api.createPeticioDeFirma(peticioDeFirmaWs);
        peticioDeFirmaID = peticioDeFirmaWs.getPeticioDeFirmaID();
        log.info("Creada peticio amb ID = " + peticioDeFirmaID);

        // Arrancar
        api.startPeticioDeFirma(peticioDeFirmaID);

        // Obtenir petició de Firma
        peticioDeFirmaWs = api.getPeticioDeFirma(peticioDeFirmaID);

        // Imprimir estat
        log.info("Estat de la peticio = "
            + peticioDeFirmaWs.getTipusEstatPeticioDeFirmaID());

        // Decaregar Document adaptat o esperar a que
        // la petició finalitzi pel doc firmat

        // Info document firmat
        FitxerBean info;
        info = api.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);

        // Descarregar document
        FitxerBean fdoc;
        fdoc = api.downloadFileUsingEncryptedFileID(info.getEncryptedFileID());

        byte[] data = fdoc.getData();
        log.info("Tamany del fitxer: " + data.length);

      } finally {
        if (peticioDeFirmaID != null) {
          // Borrar la petició
          api.deletePeticioDeFirma(peticioDeFirmaID);
        }
      }

    } catch (WsI18NException i18ne) {
      System.err.println(WsClientUtils.toString(i18ne));
    } catch (WsValidationException ve) {
      System.err.println(WsClientUtils.toString(ve));
    } catch (Exception e) {
      System.err.println("Error desconegut: " + e.getMessage());
    }

  }
}
