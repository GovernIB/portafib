package es.caib.portafib.apiinterna.client.infoversio.v1.api;

import es.caib.portafib.apiinterna.client.infoversio.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.infoversio.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.infoversio.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.infoversio.v1.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.portafib.apiinterna.client.infoversio.v1.model.InfoVersio;
import es.caib.portafib.apiinterna.client.infoversio.v1.model.RestExceptionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class InfoVersioV1Api {
  private ApiClient apiClient;

  public InfoVersioV1Api() {
    this(Configuration.getDefaultApiClient());
  }

  public InfoVersioV1Api(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Retorna la versió de PortaFIB REST
   * 
   * @return a {@code InfoVersio}
   * @throws ApiException if fails to make API call
   */
  public InfoVersio versioApi() throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/public/infoversio/v1/versioapi".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<InfoVersio> localVarReturnType = new GenericType<InfoVersio>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna la versió de PortaFIB
   * 
   * @return a {@code InfoVersio}
   * @throws ApiException if fails to make API call
   */
  public InfoVersio versioApp() throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/public/infoversio/v1/versioapp".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<InfoVersio> localVarReturnType = new GenericType<InfoVersio>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
