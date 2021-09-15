package es.caib.portafib.it.simple.misc;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import es.caib.portafib.callback.beans.v1.Actor;
import es.caib.portafib.callback.beans.v1.PortaFIBEvent;
import es.caib.portafib.callback.beans.v1.Sign;
import es.caib.portafib.callback.beans.v1.SigningRequest;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * @author anadal
 *
 */
public class TestCallbackRestV1 {

  private static final String URL_BASE = "http://localhost:8080/portafib/cbrest/v1";

  @Test
  public void testCreateEvent() {
    try {

      Client client = Client.create();

      WebResource webResource = client
          .resource(URL_BASE + "/event");

      PortaFIBEvent event = createEvent();

      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(event);

      System.out.println(json);

      ClientResponse response = webResource.type("application/json").post(
          ClientResponse.class, json);

      if (response.getStatus() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
      }

      String output = response.getEntity(String.class);
      Assert.assertEquals("OK", output);
      System.out.println("Resposta cridada REST a mètode event(): ]" + output + "[ \n");

    } catch (Exception e) {

      e.printStackTrace();

    }
  }

  @Test
  public void testVersio() throws Exception {
    // =================================
    // Recupera Versió

    URL url = new URL(URL_BASE + "/versio");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");

    if (conn.getResponseCode() != 200) {
      throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
    }

    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

    String output = IOUtils.toString(br);
    Assert.assertEquals("1", output);
    System.out.print("VERSIO REST CALLBACK ]" + output + "[");

    conn.disconnect();
  }


  private PortaFIBEvent createEvent() {
    PortaFIBEvent event = new PortaFIBEvent();

    Actor actor = new Actor();
    actor.setAdministrationID("12345678Z");
    actor.setName("Usuari Test");
    actor.setID("entitytest_usertest");

    event.setActor(actor);

    event.setApplicationID("entitytest_userapp");

    event.setEntityID("entitytest");

    event.setEventDate(new Timestamp(new Date().getTime()));

    event.setEventTypeID((int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_EN_PROCES);

    Sign sign = new Sign();

    sign.setID(12345L);
    sign.setIssuer("Camerfirma Certificate");
    sign.setSubject("Subject Certificate");
    sign.setSerialNumber(new BigInteger("123456432109876"));

    event.setSign(sign);

    SigningRequest signingRequest = new SigningRequest();

    signingRequest.setID(1234L);
    signingRequest.setTitle("Titol peticio");
    signingRequest.setState(ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES);
    signingRequest.setAdditionalInformation("additiona info REST");
    signingRequest.setRejectionReason("Rebutjar per ...");
    signingRequest.setCustodyURL("http://vd.caib.es/holacaracola/12345");

    event.setSigningRequest(signingRequest);
    event.setVersion(1);

    return event;
  }

}
