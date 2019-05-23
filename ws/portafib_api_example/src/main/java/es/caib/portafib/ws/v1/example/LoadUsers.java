package es.caib.portafib.ws.v1.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.api.v1.FitxerBean;
import es.caib.portafib.ws.api.v1.PeticioDeFirmaWs;
import es.caib.portafib.ws.api.v1.PortaFIBPeticioDeFirmaWs;
import es.caib.portafib.ws.api.v1.PortaFIBPeticioDeFirmaWsService;
import es.caib.portafib.ws.api.v1.PortaFIBUsuariEntitatWs;
import es.caib.portafib.ws.api.v1.PortaFIBUsuariEntitatWsService;
import es.caib.portafib.ws.api.v1.UsuariEntitatBean;
import es.caib.portafib.ws.api.v1.UsuariPersonaBean;
import es.caib.portafib.ws.api.v1.WsI18NException;
import es.caib.portafib.ws.api.v1.WsValidationException;
import es.caib.portafib.ws.api.v1.utils.PeticioDeFirmaUtils;
import es.caib.portafib.ws.api.v1.utils.WsClientUtils;

/**
 * 
 * @author anadal
 *
 */
public class LoadUsers extends Exemple {

  public static final Logger log = Logger.getLogger(LoadUsers.class);

  public static void main(String[] args) {
    try {
      
      log.info("Entra dins LoadUsers(" + Arrays.toString(args) + ").");
      
      if (args.length != 2) {
        log.error("Utilitzar java LoadUsers [entitatID] [Fitxer_de_Usernames(un per linia)]");
        System.exit(-1);
      }

      String entitatID = args[0];

      // Verificar Fitxer
      File file = new File(args[1]);
      if (!file.exists()) {
        log.error("No es troba el fitxer " + args[1]);
        System.exit(-1);
      }

      List<String> usernames = llegirUsuarisDeFitxer(file);

      List<String> resumErrors = new ArrayList<String>();
      List<String> resumWarnings = new ArrayList<String>();

      List<String> nifsDestinataris = new ArrayList<String>();
      
      int total = 0;

      

      final String usr_app = getTestAppUserName(); // username application
      final String pwd_app = getTestAppPassword(); // password application

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
      
      PortaFIBPeticioDeFirmaWs apiPF;
      {
        // Adreça servidor
        String endpoint = getEndPoint("PortaFIBPeticioDeFirma");;
        PortaFIBPeticioDeFirmaWsService service;
        URL wsdl = new URL(endpoint + "?wsdl");
        service = new PortaFIBPeticioDeFirmaWsService(wsdl);
        apiPF = service.getPortaFIBPeticioDeFirmaWs();

        Map<String, Object> reqContext;
        reqContext = ((BindingProvider) apiPF).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, usr_app);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd_app);
      }

      // Check Entitat
      String entitatUserApp = apiUE.getEntitatID();

      if (!entitatUserApp.equals(entitatID)) {
        log.error("L'usuari aplicacio '" + usr_app + "' té entitat " + entitatUserApp
            + " però l'entitat on es volen donar d'alta els usuaris és " + entitatID);
        System.exit(-1);
      }

      for (String username : usernames) {

        try {

          log.info("---------------------------------------------------------------");
          log.info("--------- Processant usuari " + username + " ------------------");

          // Check si usuari persona ja existeix

          UsuariPersonaBean upb = apiUE.getUsuariPersona(username);

          if (upb == null) {

            upb = apiUE.getInfoFromPluginUserInfoByUsername(username);

            if (upb == null) {
              String msg = "No puc obtenir informació per donar d'alta la PERSONA"
                  + " amb usuari '" + username + "' ";
              log.error(msg);
              resumErrors.add(msg);
              continue;
            }
            
            apiUE.createUsuariPersona(upb);
            log.info("Creada Persona " + username);

          }  else {
            String msg = "La PERSONA associada a l'usuari '" + username 
                + "' ja existeix al sistema";
            resumWarnings.add(msg);
            log.warn(msg);
          }

          log.info(username + ":: NOM = " + upb.getNom());
          log.info(username + ":: LLINATGES = " + upb.getLlinatges());
          log.info(username + ":: NIF = " + upb.getNif());
          log.info(username + ":: EMAIL = " + upb.getEmail());
          
          
          
          
          
          // Verificar si existeix usuari entitat
          String usuariEntitatID;
          usuariEntitatID = apiUE.getUsuariEntitatIDByUsuariPersonaID(username, entitatID);
          
          
          if (usuariEntitatID != null) {
            String msg = "La PERSONA associada a l'usuari '" + username 
                + "' ja està donada d'alta dins l'entitat " + entitatID;
            resumWarnings.add(msg);
            log.warn(msg);

          } else {
          
            UsuariEntitatBean usuariEntitat = new UsuariEntitatBean();
            usuariEntitat.setActiu(true);
            usuariEntitat.setCarrec(null);
            usuariEntitat.setEmail(null);
            usuariEntitat.setEntitatID(entitatID);
            usuariEntitat.setLogoSegell(null);
            usuariEntitat.setLogoSegellID(null);
            usuariEntitat.setPotCustodiar(false);
            usuariEntitat.setPredeterminat(true);
            usuariEntitat.setRebreTotsElsAvisos(false);
            usuariEntitat.setUsuariEntitatID(null); // Ho retornaran
            usuariEntitat.setUsuariPersonaID(upb.getUsuariPersonaID());

            usuariEntitat = apiUE.createUsuariEntitat(usuariEntitat);

            log.info("Associada la persona " + username + " a l'entitat " + entitatID 
                + ". Identificador de usuari-entitat = " + usuariEntitat.getUsuariEntitatID());
            
            usuariEntitatID = usuariEntitat.getUsuariEntitatID();
            
            total++;

          }

          nifsDestinataris.add(upb.getNif());

         

        } catch (Throwable th) {
          String msg;
          if (th instanceof WsI18NException) {
            msg = WsClientUtils.toString((WsI18NException) th);
          } else if (th instanceof WsValidationException) {
            msg = WsClientUtils.toString((WsValidationException) th);
          } else {
            msg = th.getMessage();
          }
          String fullMsg = "Error desconegut donant d'alta l'usuari [" + username + "]: "
              + msg ;
          resumErrors.add(fullMsg);
          log.error(fullMsg, th);
        }
        
        
        Long peticioDeFirmaID = null;
        try {
        // Posarem en marxa una petició de firma i d'aquesta forma els hi assignarem 
        // el rols de destinatari

        // Crear Peticio
        FitxerBean fitxerAFirmar;
        fitxerAFirmar = PeticioDeFirmaUtils.constructFitxerBeanFromResource(
               "test.pdf", Constants.PDF_MIME_TYPE);
        PeticioDeFirmaWs peticioDeFirmaWs;
        peticioDeFirmaWs = PeticioDeFirmaUtils.constructPeticioDeFirma(apiUE, "titol", "remitent",
            fitxerAFirmar, new String[][] { (String[])nifsDestinataris.toArray(new String[nifsDestinataris.size()]) });

        try {
          // Crear peticio
          peticioDeFirmaWs = apiPF.createPeticioDeFirma(peticioDeFirmaWs);
          peticioDeFirmaID = peticioDeFirmaWs.getPeticioDeFirmaID();
          log.info("Creada peticio amb ID = " + peticioDeFirmaID);

          // Arrancar
          apiPF.startPeticioDeFirma(peticioDeFirmaID);

        } finally {
          if (peticioDeFirmaID != null) {
            // Esborrar la petició
            apiPF.deletePeticioDeFirma(peticioDeFirmaID);
          }
        }
        } catch(Throwable th) {
          String msg = "No s'ha pogut assignar el permis de DESTINATARI"
              + " als usuaris creats: " + th.getMessage();
          resumErrors.add(msg);
          log.error(msg, th);
        }


        // AVISOS
        StringBuffer str = new StringBuffer("\n ======== AVISOS ========\n");        
        for (String s : resumWarnings) {
          if (str.length() != 0) {
            str.append('\n');
          }
          str.append(" - " + s);
        }
        str.append("\n\n");
        log.info(str.toString());
        
        // ERRORS
        str = new StringBuffer("\n ======== ERRORS ========\n");
        for (String s : resumErrors) {
          if (str.length() != 0) {
            str.append('\n');
          }
          str.append(" - " + s);
        }
        str.append("\n\n");
        log.info(str.toString());
        
        log.info("S'han creat " + total + " usuaris.");
      }
      /*
       * } catch (WsI18NException i18ne) {
       * System.err.println(WsClientUtils.toString(i18ne));
       */
    } catch (Exception e) {
      log.error("Error desconegut: " + e.getMessage(), e);
    }

  }

  public static List<String> llegirUsuarisDeFitxer(File file) throws Exception {
    List<String> usernames = new ArrayList<String>();
    BufferedReader br = new BufferedReader(new FileReader(file));
    try {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.trim().length() == 0) {
          continue;
        }
        usernames.add(line.trim());
      }
    } finally {
      br.close();
    }
    return usernames;

  }

}
