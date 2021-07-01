package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test;

import es.caib.portafib.ws.api.v1.PortaFIBUsuariEntitatWs;
import es.caib.portafib.ws.api.v1.PortaFIBUsuariEntitatWsService;
import es.caib.portafib.ws.api.v1.UsuariPersonaBean;
import es.caib.portafib.ws.api.v1.WsI18NException;
import es.caib.portafib.ws.api.v1.WsValidationException;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.DestinatariUsuari;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.ws.BindingProvider;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import static org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_RUNNING;

public class CrearDestinatariTest extends ApiFirmaAsyncTestBase {

    private DestinatariUsuari destinatariNou;
    private DestinatariUsuari destinatariSenseNif;
    private DestinatariUsuari destinatariInexistent;

    private static PortaFIBUsuariEntitatWs usuariEntitatWs;
    private static String baseUrl;

    @BeforeClass
    public static void setup() throws IOException {
        Properties properties = new Properties();
        FileReader reader = new FileReader("destinatarisign.properties");
        properties.load(reader);
        reader.close();

        baseUrl = properties.getProperty("usuari.baseUrl");

        initApi(properties);

        // Adreça servidor
        String endpoint = baseUrl + "/ws/v1/PortaFIBUsuariEntitat";
        PortaFIBUsuariEntitatWsService service;
        URL wsdl = new URL(endpoint + "?wsdl");
        service = new PortaFIBUsuariEntitatWsService(wsdl);
        usuariEntitatWs = service.getPortaFIBUsuariEntitatWs();

        Map<String, Object> reqContext;
        reqContext = ((BindingProvider) usuariEntitatWs).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, "$appadmin");
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, "appadmin");
    }

    @Before
    public void setupBefore() {
        destinatariNou = new DestinatariUsuari("11223344T", "test", "test", "x", baseUrl);
        destinatariSenseNif = new DestinatariUsuari(null, "test", "test", "x", baseUrl);
        destinatariInexistent  = new DestinatariUsuari(null, "test-inexistent", "test", "x", baseUrl);
    }

    @Test
    public void testCreateAndDelete()  {

        int firmes = destinatariNou.tasquesPendents();
        long peticio = 0;
        try {
            peticio = crearPeticioDestinataris(destinatariNou);

            // el nombre de firmes pendents s'ha incrementat en un i l'estat de la petició és running....
            Assert.assertEquals(firmes + 1, destinatariNou.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        } finally {
            if (peticio != 0) {
                deletePeticio(peticio);
            }
            cleanUpDestinatari(destinatariNou.getUsername());
        }
    }

    @Test
    public void testCreateAndDeleteSenseNif()  {

        int firmes = destinatariSenseNif.tasquesPendents();
        long peticio = 0;
        try {
            peticio = crearPeticioDestinataris(destinatariSenseNif);

            // el nombre de firmes pendents s'ha incrementat en un i l'estat de la petició és running....
            Assert.assertEquals(firmes + 1, destinatariSenseNif.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        } finally {
            if (peticio != 0) {
                deletePeticio(peticio);
            }
            cleanUpDestinatari(destinatariSenseNif.getUsername());
        }
    }


    @Test
    public void testFailCreate()  {
        try {
            crearPeticioDestinataris(destinatariInexistent);
            Assert.fail("no hauria d'haver pogut crear");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cleanUpDestinatari(String username) {
        try {
            UsuariPersonaBean usuariPersona = usuariEntitatWs.getUsuariPersona(username);
            if (usuariPersona != null) {
                System.out.println("Existeix usuari persona, el borrarem");
                String usuariEntitat = usuariEntitatWs.getUsuariEntitatIDInMyEntitatByUsuariPersonaID(username);
                if (usuariEntitat != null) {
                    System.out.println("Existeix usuari entitat, el borrarem");
                    usuariEntitatWs.deleteUsuariEntitat(usuariEntitat);
                    System.out.println("Usuari entitat borrat");
                }
                usuariEntitatWs.deleteUsuariPersona(username);
                System.out.println("Usuari persona borrat");
            }
        } catch (WsI18NException e) {
            throw new RuntimeException("Error netejant destintari", e);
        } catch (WsValidationException e) {
            throw new RuntimeException("Error netejant destintari", e);
        }
    }
}
