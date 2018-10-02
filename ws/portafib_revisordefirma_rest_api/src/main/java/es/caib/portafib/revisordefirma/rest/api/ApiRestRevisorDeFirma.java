package es.caib.portafib.revisordefirma.rest.api;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.codehaus.jackson.map.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

/**
 * 
 * @author anadal
 *
 */
public class ApiRestRevisorDeFirma {

  // Nom de les operacions en constants
  public static final String ADD_REVISOR_DE_FIRMA = "addRevisorDeFirmaToFirma";

  protected final String endPointBase;

  protected String username = null;

  protected String password = null;

  protected boolean ignoreServerCertificates = false;

  /**
   * @param endPointBase
   */
  public ApiRestRevisorDeFirma(String endPointBase) {
    super();
    this.endPointBase = endPointBase;
  }

  /**
   * @param endPointBase
   * @param username
   * @param password
   */
  public ApiRestRevisorDeFirma(String endPointBase, String username, String password) {
    this(endPointBase);
    this.username = username;
    this.password = password;
  }

  public ApiRestRevisorDeFirma(String endPointBase, String username, String password,
      boolean ignoreServerCertificates) {
    this(endPointBase, username, password);
    this.ignoreServerCertificates = ignoreServerCertificates;
  }

  public RevisorDeFirmaRest addRevisorDeFirmaToFirma(RevisorDeFirmaRest rev)
      throws RevisorDeFirmaException {

    ClientResponse response = commonCall(rev, ApiRestRevisorDeFirma.ADD_REVISOR_DE_FIRMA);

    RevisorDeFirmaRest result = response.getEntity(RevisorDeFirmaRest.class);

    return result;

  }

  /**
   * 
   * @param result
   * @return
   */
  protected String cleanString(String result) {
    if (result != null) {
      result = result.trim();
      if (result.startsWith("\"")) {
        result = result.substring(1);
      }
      if (result.endsWith("\"")) {
        result = result.substring(0, result.length() - 1);
      }
    }
    return result;
  }

  /**
   * 
   * @param parameter
   * @param method
   * @return
   * @throws AbstractFirmaSimpleException
   */
  protected ClientResponse commonCall(Object parameter, String method)
      throws RevisorDeFirmaException {

    ClientResponse response;
    try {
      String endPoint = endPointBase + (endPointBase.endsWith("/") ? "" : "/") + method;

      final Client client;
      if (endPoint.toLowerCase().startsWith("https") && ignoreServerCertificates) {
        // Ignorar Certificats de la part servidora
        HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
        ClientConfig config = new DefaultClientConfig();
        SSLContext ctx = SSLContext.getInstance("SSL");
        ctx.init(null, new TrustManager[] { new InsecureTrustManager() }, null);
        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
            new HTTPSProperties(hostnameVerifier, ctx));

        client = Client.create(config);
      } else {
        client = Client.create();
      }

      if (this.username != null) {
        client.addFilter(new HTTPBasicAuthFilter(this.username, this.password));
      }

      WebResource webResource = client.resource(endPoint);

      if (parameter == null) {
        response = webResource.type("application/json").post(ClientResponse.class);
      } else {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(parameter);
        response = webResource.type("application/json").post(ClientResponse.class, json);
      }

    } catch (Exception e) {
      throw new RevisorDeFirmaException(e.getMessage(), e);
    }

    if (response.getStatus() == 200) {
      return response;
    } else {

      // Miram si ho podem transformar a ApiSimpleError
      RevisorDeFirmaSimpleError simple = null;
      try {
        simple = response.getEntity(RevisorDeFirmaSimpleError.class);
      } catch (Exception e) {
        // Error no controlat
      }

      // System.out.println(" ERROR SIMPLE: ]" + simple + "[");

      if (simple != null) {
        // String tipus = simple.getType();
        // System.out.println(" ERROR TIPUS: ]" + tipus + "[");

        throw new RevisorDeFirmaException(simple.getMessage(), simple.getStackTrace());

      }

      // Error de Comunicació o no controlat
      String raw_msg = response.getEntity(String.class);
      throw new RevisorDeFirmaException("Error desconegut (Codi de servidor "
          + response.getStatus() + "): " + raw_msg);

    }

  }

  /**
   * 
   * @author anadal
   *
   */
  public class InsecureTrustManager implements X509TrustManager {
    /**
     * {@inheritDoc}
     */
    @Override
    public void checkClientTrusted(final java.security.cert.X509Certificate[] chain,
        final String authType) throws CertificateException {
      // Everyone is trusted!
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkServerTrusted(final java.security.cert.X509Certificate[] chain,
        final String authType) throws CertificateException {
      // Everyone is trusted!
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
      return new X509Certificate[0];
    }
  }

}
