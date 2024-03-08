package es.caib.portafib.apiinterna.client.api;

import es.caib.portafib.apiinterna.client.services.ApiException;
import es.caib.portafib.apiinterna.client.services.ApiClient;
import es.caib.portafib.apiinterna.client.services.Configuration;
import es.caib.portafib.apiinterna.client.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.portafib.apiinterna.client.model.BasicUserInfoList;
import es.caib.portafib.apiinterna.client.model.RestExceptionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class RevisorsApi {
  private ApiClient apiClient;

  public RevisorsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public RevisorsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Retorna una llista dels Revisors globals i els associats al NIF d&#39;un Destinatari
   * 
   * @param administrationID DNI del destinatari del qual volen els revisors associats. Opcional. (optional)
   * @param languageUI Idioma en que s&#39;enviaran els missatges d&#39;error (optional)
   * @return a {@code BasicUserInfoList}
   * @throws ApiException if fails to make API call
   */
  public BasicUserInfoList revisorsByDestinatariNIF(String administrationID, String languageUI) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/revisors/revisorsbydestinatarinif".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "administrationID", administrationID));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "languageUI", languageUI));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<BasicUserInfoList> localVarReturnType = new GenericType<BasicUserInfoList>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
