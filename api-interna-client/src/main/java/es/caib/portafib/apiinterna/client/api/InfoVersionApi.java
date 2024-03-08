package es.caib.portafib.apiinterna.client.api;

import es.caib.portafib.apiinterna.client.services.ApiException;
import es.caib.portafib.apiinterna.client.services.ApiClient;
import es.caib.portafib.apiinterna.client.services.Configuration;
import es.caib.portafib.apiinterna.client.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.portafib.apiinterna.client.model.InfoVersion;
import es.caib.portafib.apiinterna.client.model.RestExceptionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class InfoVersionApi {
  private ApiClient apiClient;

  public InfoVersionApi() {
    this(Configuration.getDefaultApiClient());
  }

  public InfoVersionApi(ApiClient apiClient) {
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
   * @return a {@code InfoVersion}
   * @throws ApiException if fails to make API call
   */
  public InfoVersion versionApi() throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/public/infoversion/versionapi".replaceAll("\\{format\\}","json");

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

    GenericType<InfoVersion> localVarReturnType = new GenericType<InfoVersion>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna la versió de PortaFIB
   * 
   * @return a {@code InfoVersion}
   * @throws ApiException if fails to make API call
   */
  public InfoVersion versionApp() throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/public/infoversion/versionapp".replaceAll("\\{format\\}","json");

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

    GenericType<InfoVersion> localVarReturnType = new GenericType<InfoVersion>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
