package es.caib.portafib.ws.v1.example;


import es.caib.portafib.ws.api.v1.PortaFIBUsuariEntitatWs;
import es.caib.portafib.ws.api.v1.PortaFIBUsuariEntitatWsService;
import es.caib.portafib.ws.api.v1.UsuariEntitatBean;
import es.caib.portafib.ws.api.v1.WsI18NException;
import es.caib.portafib.ws.api.v1.WsValidationException;
import es.caib.portafib.ws.api.v1.utils.WsClientUtils;
import org.apache.log4j.Logger;

import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

/**
 * @author anadal
 */
public class TestApiUsers extends Exemple {

    public static final Logger log = Logger.getLogger(TestApiUsers.class);

    public static void main(String[] args) {

        log.info("Entra dins LoadUsers(" + Arrays.toString(args) + ").");

        final String usr_app = getTestAppUserName(); // username application
        final String pwd_app = getTestAppPassword(); // password application

        final String[] nifsDestinataris = getNifsDestinataris();
        try {
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

            for (String nif : nifsDestinataris) {

                String usuariEntitatID = apiUE.getUsuariEntitatIDByAdministrationID(nif, "caib");

                if (usuariEntitatID == null) {

                    String msg = "No puc obtenir informació de la PERSONA" + " amb nif '" + nif + "' ";
                    log.error(msg);

                }

                UsuariEntitatBean ueb = apiUE.getUsuariEntitat(usuariEntitatID);

            }
        } catch (Throwable th) {
            String msg;
            if (th instanceof WsI18NException) {
                msg = WsClientUtils.toString((WsI18NException) th);
            } else if (th instanceof WsValidationException) {
                msg = WsClientUtils.toString((WsValidationException) th);
            } else {
                msg = th.getMessage();
            }
            String fullMsg = "Error desconegut: " + msg;
            log.error(fullMsg, th);
        }

        /*
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
                    fitxerAFirmar, new String[][]{(String[]) nifsDestinataris.toArray(new String[0])});
        
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
        } catch (Throwable th) {
            String msg = "No s'ha pogut assignar el permis de DESTINATARI"
                    + " als usuaris creats: " + th.getMessage();
            resumErrors.add(msg);
            log.error(msg, th);
        }*/

    }

}
