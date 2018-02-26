package org.fundaciobit.apifirmawebsimple;

import org.codehaus.jackson.map.ObjectMapper;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleError;
import org.fundaciobit.apifirmawebsimple.exceptions.ApiFirmaException;
import org.fundaciobit.apifirmawebsimple.exceptions.CancelledUserException;
import org.fundaciobit.apifirmawebsimple.exceptions.ClientException;
import org.fundaciobit.apifirmawebsimple.exceptions.NoAvailablePluginException;
import org.fundaciobit.apifirmawebsimple.exceptions.ServerException;
import org.fundaciobit.apifirmawebsimple.exceptions.TimeOutException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

/**
 * 
 * @author anadal
 *
 */
abstract class AbstractApiFirmaSimple {
  
  protected final String endPointBase;

  protected String username = null;

  protected String password = null;
  
  /**
   * @param endPointBase
   */
  public AbstractApiFirmaSimple(String endPointBase) {
    super();
    this.endPointBase = endPointBase;
  }

  /**
   * @param endPointBase
   * @param username
   * @param password
   */
  public AbstractApiFirmaSimple(String endPointBase, String username, String password) {
    super();
    this.endPointBase = endPointBase;
    this.username = username;
    this.password = password;
  }

  // TODO XYZ ZZZ Fer constructor per comunicació HTTPS
  // https://stackoverflow.com/questions/2145431/https-using-jersey-client

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
   * @throws ApiFirmaException
   */
  protected ClientResponse commonCall(Object parameter, String method)
      throws ApiFirmaException {

    ClientResponse response;
    try {
      String endPoint = endPointBase + (endPointBase.endsWith("/") ? "" : "/") + method;

      Client client = Client.create();

      // TODO XYZ ZZZ Fer constructor per comunicació HTTPS
      // https://stackoverflow.com/questions/2145431/https-using-jersey-client

      if (this.username != null) {
        client.addFilter(new HTTPBasicAuthFilter(this.username, this.password));
      }

      WebResource webResource = client.resource(endPoint);

      if (parameter == null) {
        //System.out.println("XYZ ZZZ JSON PARAMETER : <<BUIT>>\n");
        response = webResource.type("application/json").post(ClientResponse.class);
      } else {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(parameter);
        //System.out.println("XYZ ZZZ JSON PARAMETER :\n" + json);
        response = webResource.type("application/json").post(ClientResponse.class, json);
      }

    } catch (Exception e) {
      throw new ClientException(e.getMessage(), e);
    }

    if (response.getStatus() == 200) {
      return response;
    } else {

      // Miram si ho podem transformar a ApiSimpleError
      FirmaSimpleError simple = null;
      try {
        simple = response.getEntity(FirmaSimpleError.class);
      } catch (Exception e) {
        // Error no controlat
      }

      //System.out.println(" ERROR SIMPLE: ]" + simple + "[");

      if (simple != null) {
        String tipus = simple.getType();
        //System.out.println(" ERROR TIPUS: ]" + tipus + "[");

        if (tipus != null && tipus.trim().length() != 0) {

          //System.out.println(" ERROR TIPUS IS SERVER EXCEPTION: ]"
          //    + tipus.equals(ServerException.class.getName()) + "[");

          if (tipus.equals(NoAvailablePluginException.class.getName())) {
            throw new NoAvailablePluginException(simple.getMessage(), simple.getStackTrace());
          } else if (tipus.equals(CancelledUserException.class.getName())) {
            throw new CancelledUserException(simple.getMessage(), simple.getStackTrace());
          } else if (tipus.equals(TimeOutException.class.getName())) {
            throw new TimeOutException(simple.getMessage(), simple.getStackTrace());
          } else if (tipus.equals(ServerException.class.getName())) {
            throw new ServerException(simple.getMessage(), simple.getStackTrace());
          } else {
            // TODO Altres Excepcions
            throw new ClientException(simple.getMessage(), simple.getStackTrace());
          }

        }
      }

      // Error de Comunicació o no controlat
      String raw_msg = response.getEntity(String.class);
      throw new ClientException("Error desconegut (Codi de servidor " + +response.getStatus()
          + "): " + raw_msg);

    }

  }

}
