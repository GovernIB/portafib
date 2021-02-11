package org.fundaciobit.apisib.jerseycore;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.map.ObjectMapper;
import org.fundaciobit.apisib.core.beans.ApisIBError;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.apisib.core.exceptions.ApisIBClientException;
import org.fundaciobit.apisib.core.exceptions.ApisIBServerException;
import org.fundaciobit.apisib.core.exceptions.ApisIBTimeOutException;

/**
 * 
 * @author anadal
 * @author areus
 */
public abstract class AbstractApisIBConnectionManagerJersey<E extends ApisIBError> {

  protected final String endPointBase;

  protected final String username;

  protected final String password;

  private final Client client;

  private final ObjectMapper mapper;

  public AbstractApisIBConnectionManagerJersey(String endPointBase) {
    this(endPointBase, null, null);
  }

  public AbstractApisIBConnectionManagerJersey(String endPointBase, String username, String password) {
    this.endPointBase = endPointBase.endsWith("/") ? endPointBase : (endPointBase + "/");
    this.username = username;
    this.password = password;
    this.client = initClient();
    this.mapper = new ObjectMapper();
  }

  /**
   * Emprar ignoreServerCertificates amb valor true és insegur i ja no està suportat
   */
  @Deprecated
  public AbstractApisIBConnectionManagerJersey(String endPointBase, String username, String password,
      boolean ignoreServerCertificates) {
    this(endPointBase, username, password);
    if (ignoreServerCertificates) {
      throw new UnsupportedOperationException("La propietat ignoreServerCertificate ja no està soportada per " +
              "motius de seguretat.");
    }
  }
  
  private Client initClient() {
    ClientConfig config = new DefaultClientConfig();
    config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
    Client client = Client.create(config);

    if (this.username != null) {
      client.addFilter(new HTTPBasicAuthFilter(this.username, this.password));
    }

    return client;
  }

  public Integer getReadTimeoutMs() {
    return (Integer) client.getProperties().get(ClientConfig.PROPERTY_READ_TIMEOUT);
  }

  public void setReadTimeoutMs(Integer readTimeoutMs) {
    client.setReadTimeout(readTimeoutMs);
  }

  public Integer getConnectionTimeoutMs() {
    return (Integer) client.getProperties().get(ClientConfig.PROPERTY_CONNECT_TIMEOUT);
  }

  public void setConnectionTimeoutMs(Integer connectionTimeoutMs) {
    client.setConnectTimeout(connectionTimeoutMs);
  }

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

  protected ClientResponse commonCall(Object parameter, String method)
      throws AbstractApisIBException {

    ClientResponse response;
    try {
      String endPoint = endPointBase + (endPointBase.endsWith("/") ? "" : "/") + method;

      WebResource webResource = client.resource(endPoint);
      if (parameter == null) {
        response = webResource.type("application/json").post(ClientResponse.class);
      } else {
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

      if (simple != null) {
        String tipus = simple.getType();
        if (tipus != null && tipus.trim().length() != 0) {
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
      throw new ApisIBClientException(simple.getMessage(), simple.getStackTrace());
    }
  }

  protected abstract Class<E> getErrorClass();

}
