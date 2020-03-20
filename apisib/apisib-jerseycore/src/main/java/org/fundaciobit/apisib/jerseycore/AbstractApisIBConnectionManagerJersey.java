package org.fundaciobit.apisib.jerseycore;


import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.codehaus.jackson.map.ObjectMapper;


import org.fundaciobit.apisib.core.beans.ApisIBError;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.apisib.core.exceptions.ApisIBClientException;
import org.fundaciobit.apisib.core.exceptions.ApisIBServerException;
import org.fundaciobit.apisib.core.exceptions.ApisIBTimeOutException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractApisIBConnectionManagerJersey<E extends ApisIBError> {

  protected final String endPointBase;

  protected String username = null;

  protected String password = null;

  protected boolean ignoreServerCertificates = false;

  /**
   * @param endPointBase
   */
  public AbstractApisIBConnectionManagerJersey(String endPointBase) {
    super();
    this.endPointBase = endPointBase;
  }

  /**
   * @param endPointBase
   * @param username
   * @param password
   */
  public AbstractApisIBConnectionManagerJersey(String endPointBase, String username, String password) {
    this(endPointBase);
    this.username = username;
    this.password = password;
  }

  public AbstractApisIBConnectionManagerJersey(String endPointBase, String username, String password,
      boolean ignoreServerCertificates) {
    this(endPointBase, username, password);
    this.ignoreServerCertificates = ignoreServerCertificates;
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
      throws AbstractApisIBException {

    ClientResponse response;
    try {
      String endPoint = endPointBase + (endPointBase.endsWith("/") ? "" : "/") + method;

      final Client client;
      ClientConfig config = new DefaultClientConfig();
      if (endPoint.toLowerCase().startsWith("https") && ignoreServerCertificates) {
        // Ignorar Certificats de la part servidora
        HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();

        SSLContext ctx = SSLContext.getInstance("SSL");
        ctx.init(null, new TrustManager[] { new InsecureTrustManager() }, null);
        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
            new HTTPSProperties(hostnameVerifier, ctx));
      }

      config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

      client = Client.create(config);

      if (this.username != null) {
        client.addFilter(new HTTPBasicAuthFilter(this.username, this.password));
      }

      WebResource webResource = client.resource(endPoint);

      if (parameter == null) {
        response = webResource.type("application/json").post(ClientResponse.class);
      } else {
        ObjectMapper mapper = new ObjectMapper();
        // System.out.println(" XXXXXXXXXXXXXXXXXXXXXXXXXX [" +parameter +"]");
        // mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
        String json = mapper.writeValueAsString(parameter);
        response = webResource.type("application/json").post(ClientResponse.class, json);
      }

    } catch (Exception e) {
      throw new ApisIBClientException(e.getMessage(), e);
    }

    if (response.getStatus() == 200) {
      return response;
    } else {

      // Miram si ho podem transformar a ApiSimpleError
      E simple = null;
      try {
        simple = response.getEntity(getErrorClass());
      } catch (Exception e) {
        // Error no controlat
        e.printStackTrace();
      }

      // System.out.println(" ERROR SIMPLE: ]" + simple + "[");

      if (simple != null) {
        String tipus = simple.getType();
        // System.out.println(" ERROR TIPUS: ]" + tipus + "[");

        if (tipus != null && tipus.trim().length() != 0) {

          // System.out.println(" ERROR TIPUS IS SERVER EXCEPTION: ]"
          // + tipus.equals(ServerException.class.getName()) + "[");

          processException(simple, tipus);

        }
      }

      // Error de Comunicació o no controlat
      String raw_msg = response.getEntity(String.class);
      throw new ApisIBClientException("Error desconegut (Codi de servidor " + +response.getStatus()
          + "): " + raw_msg);

    }

  }

  protected void processException(E simple, String tipus)
      throws AbstractApisIBException {
    if (tipus.equals(ApisIBTimeOutException.class.getName())) {
      throw new ApisIBTimeOutException(simple.getMessage(), simple.getStackTrace());
    } else if (tipus.equals(ApisIBServerException.class.getName())) {
      throw new ApisIBServerException(simple.getMessage(), simple.getStackTrace());
    } else {
      // TODO Altres Excepcions
      throw new ApisIBClientException(simple.getMessage(), simple.getStackTrace());
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

  
  protected abstract Class<E> getErrorClass();
  
}
