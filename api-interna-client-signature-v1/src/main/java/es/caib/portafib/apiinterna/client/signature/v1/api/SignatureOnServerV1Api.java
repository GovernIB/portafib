package es.caib.portafib.apiinterna.client.signature.v1.api;

import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.portafib.apiinterna.client.signature.v1.model.DocumentaryType;
import es.caib.portafib.apiinterna.client.signature.v1.model.KeyValue;
import es.caib.portafib.apiinterna.client.signature.v1.model.Profile;
import es.caib.portafib.apiinterna.client.signature.v1.model.RestExceptionInfo;
import java.util.Set;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignDocumentRequest;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureResponse;
import es.caib.portafib.apiinterna.client.signature.v1.model.UpgradeRequest;
import es.caib.portafib.apiinterna.client.signature.v1.model.UpgradeResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class SignatureOnServerV1Api {
  private ApiClient apiClient;

  public SignatureOnServerV1Api() {
    this(Configuration.getDefaultApiClient());
  }

  public SignatureOnServerV1Api(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l&#39;entitat i tipus documentals de l&#39;usuari aplicació
   * 
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Set<DocumentaryType>}
   * @throws ApiException if fails to make API call
   */
  public Set<DocumentaryType> getDocumentaryTypes(String language) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureonserver/v1/getDocumentaryTypes".replaceAll("\\{format\\}","json");

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

    GenericType<Set<DocumentaryType>> localVarReturnType = new GenericType<Set<DocumentaryType>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna els idiomes disponibles.
   * 
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Set<KeyValue>}
   * @throws ApiException if fails to make API call
   */
  public Set<KeyValue> getLanguages(String language) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureonserver/v1/getLanguages".replaceAll("\\{format\\}","json");

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

    GenericType<Set<KeyValue>> localVarReturnType = new GenericType<Set<KeyValue>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna els perfils de firma.
   * 
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Set<Profile>}
   * @throws ApiException if fails to make API call
   */
  public Set<Profile> getProfiles(String language) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureonserver/v1/getProfiles".replaceAll("\\{format\\}","json");

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

    GenericType<Set<Profile>> localVarReturnType = new GenericType<Set<Profile>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Operacio de firma simple en servidor d&#39;un document
   * 
   * @param signDocumentRequest Operacio de firma simple en servidor d&#39;un document (optional)
   * @return a {@code SignatureResponse}
   * @throws ApiException if fails to make API call
   */
  public SignatureResponse signdocument(SignDocumentRequest signDocumentRequest) throws ApiException {
    Object localVarPostBody = signDocumentRequest;
    
    // create path and map variables
    String localVarPath = "/secure/signatureonserver/v1/signdocument".replaceAll("\\{format\\}","json");

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
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<SignatureResponse> localVarReturnType = new GenericType<SignatureResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Operacio de firma simple en servidor d&#39;un document
   * 
   * @param upgradeRequest Funcio de upgrade se firma digital (optional)
   * @return a {@code UpgradeResponse}
   * @throws ApiException if fails to make API call
   */
  public UpgradeResponse upgradeSignature(UpgradeRequest upgradeRequest) throws ApiException {
    Object localVarPostBody = upgradeRequest;
    
    // create path and map variables
    String localVarPath = "/secure/signatureonserver/v1/upgradeSignature".replaceAll("\\{format\\}","json");

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
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<UpgradeResponse> localVarReturnType = new GenericType<UpgradeResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna la versió d&#39;aquest Servei
   * 
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String versio() throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureonserver/v1/versio".replaceAll("\\{format\\}","json");

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

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
