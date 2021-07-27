package es.caib.portafib.it.misc;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.Base64;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author anadal
 * @author areus
 * 
 * Test de Serveis REST que es troben dins /back en la classes:
 *  -src/main/java/es/caib/portafib/back/controller/common/
 *         RestAutenticatedController.java 
 *  -src/main/java/es/caib/portafib/back/controller/common/
           RestController.java
 *
 */
public class TestPortaFIBRestServices {

  private static final String URL_BASE = "http://localhost:8080/portafib";
  private static final String USERNAME = "pruebas";
  private static final String PASSWORD = "pruebas";

  /**
   * Cridada a recuperar TipusDeDocument emprant llibreria Jersey
   */
  @Test
  public void testTipusDocumentJersey() throws Exception {

    Client client = Client.create();

    client.addFilter(new HTTPBasicAuthFilter(USERNAME, PASSWORD));

    StringBuilder params = new StringBuilder("?");
    params.append("lang=").append("ca");

    WebResource webResource = client.resource(URL_BASE
        + "/public/rest/tipusdocument/v1/list"
        + ((params.length() == 1) ? "" : params.toString()));

    ClientResponse response = webResource.type("application/json").get(ClientResponse.class);

    if (response.getStatus() != 200) {
      throw new Exception("Error " + response.getEntity(String.class) + "(Error code = "
          + response.getStatus() + ")");
    }

    String json = response.getEntity(String.class);

    System.out.println("JSON TIPUS DOCUMENT " + json);

    ObjectMapper mapper = new ObjectMapper();
    List<TipusDocumentRest> listTipus = mapper.readValue(json,
        new TypeReference<List<TipusDocumentRest>>() {
        });

    System.out.println("Resposta cridada REST a mètode tipusdocument():");

    for (TipusDocumentRest tipus : listTipus) {
      System.out.println(" * '" + tipus.getTipusDocumentID() + "'\t" + tipus.getNom()
          + "\t[NTI:" + tipus.getTipusDocumentNTI() + "]");
    }
  }

  /**
   * @author anadal
   */
  public static class TipusDocumentRest {

    protected long tipusDocumentID;

    protected String nom;

    protected Long tipusDocumentNTI;

    public TipusDocumentRest() {
      super();
    }

    public TipusDocumentRest(long tipusDocumentID, String nom, Long tipusDocumentNTI) {
      super();
      this.tipusDocumentID = tipusDocumentID;
      this.nom = nom;
      this.tipusDocumentNTI = tipusDocumentNTI;
    }

    public long getTipusDocumentID() {
      return tipusDocumentID;
    }

    public void setTipusDocumentID(long tipusDocumentID) {
      this.tipusDocumentID = tipusDocumentID;
    }

    public String getNom() {
      return nom;
    }

    public void setNom(String nom) {
      this.nom = nom;
    }

    public Long getTipusDocumentNTI() {
      return tipusDocumentNTI;
    }

    public void setTipusDocumentNTI(Long tipusDocumentNTI) {
      this.tipusDocumentNTI = tipusDocumentNTI;
    }

  }

  /**
   * Prova d'echo (Emprant HttpURLConnection)
   */
  @Test
  public void testEchoHttpURLConnection() throws Exception {

    final String basicAuth = "Basic "
        + new String(Base64.encode((USERNAME + ":" + PASSWORD).getBytes()));

    URL url = new URL(URL_BASE + "/common/rest/echo?echo=" + URLEncoder.encode("HOLA", "UTF-8"));
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");
    conn.setRequestProperty("Authorization", basicAuth);

    if (conn.getResponseCode() != 200) {
      throw new Exception("Error " + conn.getResponseMessage() + "(Error code = "
          + conn.getResponseCode() + ")");
    }

    String json = IOUtils.toString(conn.getInputStream());
    conn.disconnect();

    Assert.assertEquals("\"HOLA\"", json);

  }

  /**
   * Recupera Peticions amb Avisos (Emprant llibreria Jersey)
   */
  @Test
  public void testAvisosJersey() throws Exception {

    Client client = Client.create();

    client.addFilter(new HTTPBasicAuthFilter(USERNAME, PASSWORD));

    WebResource webResource = client.resource(URL_BASE
        + "/common/rest/usuarientitat/avisos/v1/list");

    ClientResponse response = webResource.type("application/json").get(ClientResponse.class);

    if (response.getStatus() != 200) {
      throw new Exception("Error " + response.getEntity(String.class) + "(Error code = "
          + response.getStatus() + ")");
    }

    String json = response.getEntity(String.class);

    System.out.println("JSON " + json);

    ObjectMapper mapper = new ObjectMapper();
    List<NotificacioRest> avisos = mapper.readValue(json,
        new TypeReference<List<NotificacioRest>>() {
        });

    System.out.println("Resposta cridada REST a mètode avisos():");

    for (NotificacioRest notificacioRest : avisos) {
      System.out.println(" + " + notificacioRest.getRol() + " => "
          + Arrays.toString(notificacioRest.getPeticions().toArray()));
    }

  }

  /**
   * @author anadal
   */
  public static class NotificacioRest {

    String rol;

    List<Long> peticions;

    public NotificacioRest() {
      super();
    }

    public NotificacioRest(String rol, List<Long> peticions) {
      super();
      this.rol = rol;
      this.peticions = peticions;
    }

    public String getRol() {
      return rol;
    }

    public void setRol(String rol) {
      this.rol = rol;
    }

    public List<Long> getPeticions() {
      return peticions;
    }

    public void setPeticions(List<Long> peticions) {
      this.peticions = peticions;
    }
  }

}