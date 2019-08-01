package es.caib.portafib.ws.v1.example;


import es.caib.portafib.utils.Constants;
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
import org.apache.log4j.Logger;

import javax.xml.ws.BindingProvider;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author anadal
 *
 */
public class ExempleMultiple {

  public static final Logger log = Logger.getLogger(ExempleMultiple.class);

  private static Properties testProperties = new Properties();

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

    System.out.println("Entra dins l'exemple multiple.");
    try {

      final String usr_app = getTestAppUserName(); // username application
      final String pwd_app = getTestAppPassword(); // password application

      System.out.println(" Connectant amb " + testProperties.getProperty("test_host")
          + " emprant l'usuari " + usr_app);

      final String[] nifsDestinataris = getNifsDestinataris();

      System.out.println("nifsDestinataris = " + Arrays.toString(nifsDestinataris));

      if (nifsDestinataris == null) {
        throw new Exception("S'ha de definir la propietat nifsDestinataris dins test.properties");
      }

      final int nombreThreads;
      String nombreThreadsString = testProperties.getProperty("nombreThreads");
      if (nombreThreadsString != null) {
        nombreThreads = Integer.valueOf(nombreThreadsString);
        if (nombreThreads < 1) {
          throw new Exception("nombreThreads ha de ser al manco 1 test.properties");
        }
      } else {
        nombreThreads = 1;
      }

      final int peticionsThread;
      String peticionsThreadString = testProperties.getProperty("peticionsThread");
      if (peticionsThreadString != null) {
        peticionsThread = Integer.valueOf(peticionsThreadString);
        if (peticionsThread < 1) {
          throw new Exception("peticionsThread ha de ser al manco 1 test.properties");
        }
      } else {
        peticionsThread = 1;
      }


      final PortaFIBPeticioDeFirmaWs api;
      {
        // Adreça servidor
        String endpoint = getEndPoint("PortaFIBPeticioDeFirma");;
        PortaFIBPeticioDeFirmaWsService service;
        URL wsdl = new URL(endpoint + "?wsdl");
        service = new PortaFIBPeticioDeFirmaWsService(wsdl);
        api = service.getPortaFIBPeticioDeFirmaWs();

        Map<String, Object> reqContext;
        reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, usr_app);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd_app);
      }

      final PortaFIBUsuariEntitatWs apiUE;
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

      final String titol = "Peticio Test Multiple ("
         + ((System.currentTimeMillis() / 1000 ) % 100000) + ")";
      final String remitent = "Helium";

      // Fitxer a Firmar
      final FitxerBean fitxerAFirmar;
      if (getFitxerAFirmar() == null) {
        fitxerAFirmar = PeticioDeFirmaUtils.constructFitxerBeanFromResource(
             "test.pdf", Constants.PDF_MIME_TYPE);
      } else {
        File f = new File(getFitxerAFirmar());
        String mime;
        if (f.getName().toLowerCase().endsWith(".pdf")) {
          mime = Constants.PDF_MIME_TYPE;
        } else {
          mime = "application/octet-stream";
        }
        fitxerAFirmar = PeticioDeFirmaUtils.constructFitxerBeanFromFile(f, mime);
      }

      final CountDownLatch createCountDown = new CountDownLatch(nombreThreads);
      final Long peticionsID[][] = new Long[nombreThreads][peticionsThread];
      long currentNanoTime = System.nanoTime();

      for (int i = 1; i <= nombreThreads; i++) {

        final int threadNumber = i;
        final String threadName = "[Thread " + i + "]";

        Runnable runnable = new Runnable() {
          public void run() {
            log.info(threadName + " iniciat");

              for (int j = 1; j <= peticionsThread; j++) {

                final String afegit = threadName + "(" + j + "/" + peticionsThread + ")";
                // Crear Peticio
                log.info(afegit + " Generant petició");

                Long peticioDeFirmaID = null;
                try {

                PeticioDeFirmaWs peticioDeFirmaWs = PeticioDeFirmaUtils.constructPeticioDeFirma(apiUE,
                      titol + afegit, remitent,
                      fitxerAFirmar, nifsDestinataris);
                peticioDeFirmaWs.setPosicioTaulaFirmesID(Constants.TAULADEFIRMES_SENSETAULA);

                log.info(afegit + " Enviant petició");
                // Crear peticio
                peticioDeFirmaWs = api.createPeticioDeFirma(peticioDeFirmaWs);
                peticioDeFirmaID = peticioDeFirmaWs.getPeticioDeFirmaID();
                log.info(afegit + " Creada petició ID=" + peticioDeFirmaID);

                // Arrancar
                api.startPeticioDeFirma(peticioDeFirmaID);
                log.info(afegit + " Arrancada peticio");

                } catch (WsI18NException i18ne) {
                  log.error(WsClientUtils.toString(i18ne));
                } catch (WsValidationException ve) {
                  log.error(WsClientUtils.toString(ve));
                } finally {
                  peticionsID[threadNumber-1][j-1] = peticioDeFirmaID;
                }
              }


            log.info(threadName + " finalitzat");
            createCountDown.countDown();
          }
        };

        Thread t = new Thread(runnable, threadName);
        t.start();
      }

      createCountDown.await();

      int count = 0;
      int expectedCount = nombreThreads * peticionsThread;
      for (int i = 0; i < nombreThreads; i++) {
        for (int j = 0; j < peticionsThread; j++) {
          if (peticionsID[i][j] != null) {
            count++;
          }
        }
      }

      long elapsedTime = (System.nanoTime() - currentNanoTime) / 1000000000;
      if (count == expectedCount) {
        log.info("Creades " + count + " peticions sense cap error en " + elapsedTime + "s");
      } else {
        log.info("Creades " + count + " peticions quan se n'esperaven " + expectedCount + " en " + elapsedTime + "s");
      }

      if (isDeleteOnFinish()) {

        Thread.sleep(10000L);

        log.info("Borram les peticions");

        final CountDownLatch deleteCountDown = new CountDownLatch(nombreThreads);

        for (int i = 1; i <= nombreThreads; i++) {

          final int threadNumber = i;
          final String threadName = "[Thread " + i + "]";

          Runnable runnable = new Runnable() {
            public void run() {
              log.info(threadName + " iniciat");

              for (int j = 1; j <= peticionsThread; j++) {

                final String afegit = threadName + "(" + j + "/" + peticionsThread + ")";

                Long peticioDeFirmaID = peticionsID[threadNumber-1][j-1];
                if (peticioDeFirmaID != null) {
                  log.info(afegit + " Borrant petició amb ID=" + peticioDeFirmaID);

                  try {
                    api.deletePeticioDeFirma(peticioDeFirmaID);
                    log.info(afegit + " Borrada peticio amb ID=" + peticioDeFirmaID);
                  } catch (WsI18NException i18ne) {
                    log.error(WsClientUtils.toString(i18ne));
                  }
                } else {
                  log.info(afegit + " Ometent petició null");
                }

              }

              log.info(threadName + " finalitzat");
              deleteCountDown.countDown();
            }
          };

          Thread t = new Thread(runnable, threadName);
          t.start();
        }

        deleteCountDown.await();

        log.info("Finalitzat el borrat");
      }


    } catch (Exception e) {
      System.err.println("Error desconegut: " + e.getMessage());
      e.printStackTrace();
    }



  }

  public static boolean isDeleteOnFinish() {
    return "true".equals(testProperties.getProperty("deleteonfinish"));
  }

  public static String getEndPoint(String api) {
    return testProperties.getProperty("test_host") + api;
  }

  public static String getTestAppUserName() {
    return testProperties.getProperty("test_usr");
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

  public static String getFitxerAFirmar() {
    return testProperties.getProperty("fitxerAFirmar");
  }
  
}

