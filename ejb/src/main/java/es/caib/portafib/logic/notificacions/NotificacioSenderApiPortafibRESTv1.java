package es.caib.portafib.logic.notificacions;


import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.ws.callback.api.v1.PortaFIBEvent;
import org.apache.commons.io.IOUtils;

import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
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
        
        
        mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Timestamp.class, new JsonStdDateSerializer());
        mapper.registerModule(module);
        
        JacksonJsonProvider provider = new JacksonJsonProvider(mapper);


        client = configuration.register(provider).build();
    }
    
    public class JsonStdDateSerializer extends JsonSerializer<Timestamp> {

        @Override
        public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            //log.info("\n\n\n\n ----------- ENTRA A TIMESTAMP SERIALIZER NUMBER---------------\n\n\n\n");

            // clone because DateFormat is not thread-safe
            gen.writeNumber(value.getTime());

        }
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

       
        try {
            

            // XXX WebResource webResource = client.resource(endPoint);
            WebTarget webResource = client.target(endPoint);
            
            String json = null;
            json = mapper.writeValueAsString(event);

            Entity<String> jsonEntity = Entity.json(json);

            // TODO
            //if (log.isDebugEnabled()) 
            {
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
                throw new Exception("Error en la resposta de la cridad al Servei Rest(" + endPoint 
                        + "): S'esperava OK i s'ha rebut " + output + " [" + response.getStatus() + "]");
            }

            if (log.isDebugEnabled()) {
                log.info("Resposta cridada REST a métode event(): ]" + output + "[ \n");
            }

        } catch (Exception e) {
            try {
                String json = null;
                json = mapper.writeValueAsString(event);
                log.error("JSON EVENT:\n" + json);
            } catch (Exception e1) {
                log.error("Error al serialitzar el JSON de la notificació", e1);
            }
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
