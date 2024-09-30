package es.caib.portafib.apiinterna.client.utils.v1.api;

import es.caib.portafib.apiinterna.client.utils.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.utils.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.utils.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.utils.v1.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.portafib.apiinterna.client.utils.v1.model.AvailableLanguagesRest;
import es.caib.portafib.apiinterna.client.utils.v1.model.AvailableProfilesRest;
import es.caib.portafib.apiinterna.client.utils.v1.model.LlistaTipusDocumentalRest;
import es.caib.portafib.apiinterna.client.utils.v1.model.RestExceptionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class UtilsV1Api {
  private ApiClient apiClient;

  public UtilsV1Api() {
    this(Configuration.getDefaultApiClient());
  }

  public UtilsV1Api(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Retorna els idiomes disponibles.
   * 
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code AvailableLanguagesRest}
   * @throws ApiException if fails to make API call
   */
  public AvailableLanguagesRest getAvailableLanguages(String language) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/utils/v1/getAvailableLanguages".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "language", language));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<AvailableLanguagesRest> localVarReturnType = new GenericType<AvailableLanguagesRest>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna els perfils de firma.
   * 
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code AvailableProfilesRest}
   * @throws ApiException if fails to make API call
   */
  public AvailableProfilesRest getAvailableProfiles(String language) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/utils/v1/getAvailableProfiles".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "language", language));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<AvailableProfilesRest> localVarReturnType = new GenericType<AvailableProfilesRest>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna la llista de tipus documentals disponibles.
   * 
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @param appuser Filtre pel nom de l&#39;usuari aplicacio. Opcional. (optional)
   * @return a {@code LlistaTipusDocumentalRest}
   * @throws ApiException if fails to make API call
   */
  public LlistaTipusDocumentalRest tipusdocumentalslist(String language, String appuser) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/utils/v1/tipusdocumentalslist".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "language", language));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "appuser", appuser));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<LlistaTipusDocumentalRest> localVarReturnType = new GenericType<LlistaTipusDocumentalRest>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
