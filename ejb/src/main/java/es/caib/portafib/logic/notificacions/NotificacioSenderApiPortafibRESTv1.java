package es.caib.portafib.logic.notificacions;

//XXX import com.sun.jersey.api.client.Client;
//XXX import com.sun.jersey.api.client.ClientResponse;
//XXX import com.sun.jersey.api.client.WebResource;
import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.ws.callback.api.v1.PortaFIBEvent;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Implementació de l'enviament de notificacions a través de REST.
 * 
 *  @author anadal
 */
public class NotificacioSenderApiPortafibRESTv1 extends NotificacioSenderApiPortafibWSv1 {

    private static final Logger log = Logger.getLogger(NotificacioSenderApiPortafibRESTv1.class);

    private final Client client;
    private final ObjectMapper mapper;

    public NotificacioSenderApiPortafibRESTv1() {
        /* XXX
        client = Client.create();
        client.setConnectTimeout(CONNECTION_TIMEOUT);
        client.setReadTimeout(RECEIVE_TIMEOUT);
        */
        ClientBuilder configuration = ClientBuilder.newBuilder();
        configuration.connectTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        configuration.readTimeout(RECEIVE_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        client = configuration.build();

        mapper = new ObjectMapper();
    }

    @Override
    public void sendNotificacio(NotificacioInfo notificacioInfo, UsuariAplicacio ua) throws I18NException {

        final FirmaEvent fe = notificacioInfo.getFirmaEvent();

        // ENVIAR A SERVEI REST
        if (log.isDebugEnabled()) {
            log.info("--------------------");
            log.info("Enviant notificacio amb id " + notificacioInfo.getIdObjectSent() + " a l'usuari-aplicacio "
                    + ua.getUsuariAplicacioID() + " al servei REST ] " + ua.getCallbackURL() + " (Versio "
                    + ua.getCallbackVersio() + ")");
            log.info("--------------------");
        }

        String endPoint = ua.getCallbackURL();

        /// ----------- FINAL

        PortaFIBEvent event = createPortaFIBEvent(fe, ua);

        String json = null;
        try {
            json = mapper.writeValueAsString(event);

            // XXX WebResource webResource = client.resource(endPoint);
            WebTarget webResource = client.target(endPoint);

            Entity<PortaFIBEvent> jsonEntity = Entity.json(event);

            if (log.isDebugEnabled()) {
                log.info("JSON EVENT:\n" + json);
            }

            //ClientResponse response = webResource.type("application/json").post(ClientResponse.class, json);
            Builder builder = webResource.request();
            builder.accept("application/json");
            Response response = builder.post(jsonEntity);

            if (response.getStatus() != 200) {
                throw new Exception("Error Cridant a Servei Rest(" + endPoint + "): "
                        + response.readEntity(String.class) + "[" + response.getStatus() + "]");
            }

            String output = response.readEntity(String.class);
            if (!output.equals("OK")) {
                throw new Exception(
                        "Error Cridant a Servei Rest(" + endPoint + "): " + output + "[" + response.getStatus() + "]");
            }

            if (log.isDebugEnabled()) {
                log.info("Resposta cridada REST a métode event(): ]" + output + "[ \n");
            }

        } catch (Exception e) {
            log.error("JSON EVENT:\n" + json);
            log.error("CallBackException(REST): " + e.getMessage(), e);
            throw new I18NException(e, "error.unknown", new I18NArgumentString(e.getMessage()));
        }
    }

    @Override
    public void testApi(UsuariAplicacio usuariAplicacio) throws Exception {
        // Recupera Versió
        String urlStr = usuariAplicacio.getCallbackURL();

        int pos = urlStr.lastIndexOf("/");
        urlStr = urlStr.substring(0, pos) + "/versio";

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output = IOUtils.toString(br);
        if (!output.equals("1")) {
            throw new Exception("Error, s'esperava el valor '1' però s'ha obtengut: " + output);
        }

        log.info("Testing OK. API WS PortaFIB v1. Usuari aplicació " + usuariAplicacio.getUsuariAplicacioID()
                + " amb URL " + urlStr + ". Cridada a getVersionWs() amb resultat " + output);
        conn.disconnect();
    }
}
