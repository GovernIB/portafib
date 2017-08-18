package es.caib.portafib.callback.rest.server.v1.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import es.caib.portafib.callback.beans.v1.Actor;
import es.caib.portafib.callback.beans.v1.PortaFIBEvent;
import es.caib.portafib.callback.beans.v1.Sign;
import es.caib.portafib.callback.beans.v1.SigningRequest;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
public class TestCallbackRestV1 {
  
  private static Properties testProperties = new Properties();
  
  static {
    // Propietats del Servidor
    try {
      testProperties.load(new FileInputStream("test.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
 

  public static String getURLBase() {
    return testProperties.getProperty("urlbase");
  }

  

  public static void main(String[] args) {

    // =================================
    // Recupera Versió
    try {

      URL url = new URL(getURLBase() + "/versio");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

      String output;
      System.out.print("VERSIO REST CALLBACK ]");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }
      System.out.println("[");
      conn.disconnect();

    } catch (Exception e) {

      e.printStackTrace();
      return;

    }

    // =================================
    // Crida a EVENT

    try {

      Client client = Client.create();

      WebResource webResource = client
          .resource(getURLBase() + "/event");

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
      System.out.println("Resposta cridada REST a mètode event(): ]" + output + "[ \n");

    } catch (Exception e) {

      e.printStackTrace();

    }

  }

  public static es.caib.portafib.callback.beans.v1.PortaFIBEvent createEvent() {
    PortaFIBEvent event = new PortaFIBEvent();

    Actor actor = new Actor();
    actor.setAdministrationID("12345678Z");
    actor.setName("Usuari Test");
    actor.setID("entitytest_usertest");

    event.setActor(actor);

    event.setApplicationID("entitytest_userapp");

    event.setEntityID("entitytest");

    event.setEventDate(new Timestamp(new Date().getTime()));

    event.setEventTypeID((int) Constants.NOTIFICACIOAVIS_PETICIO_EN_PROCES);

    Sign sign = new Sign();

    sign.setID(12345L);
    sign.setIssuer("Camerfirma Certificate");
    sign.setSubject("Subject Certificate");
    sign.setSerialNumber(new BigInteger("123456432109876"));

    event.setSign(sign);

    SigningRequest signingRequest = new SigningRequest();

    signingRequest.setID(1234L);
    signingRequest.setTitle("Titol peticio");
    signingRequest.setState(Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES);
    signingRequest.setAdditionalInformation("additiona info REST");
    signingRequest.setRejectionReason("Rebutjar per ...");
    signingRequest.setCustodyURL("http://vd.caib.es/holacaracola/12345");

    event.setSigningRequest(signingRequest);

    event.setVersion(1);

    return event;
  }

}
