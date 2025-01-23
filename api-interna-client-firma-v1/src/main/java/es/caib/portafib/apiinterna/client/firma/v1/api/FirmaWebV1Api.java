package es.caib.portafib.apiinterna.client.firma.v1.api;

import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.firma.v1.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.portafib.apiinterna.client.firma.v1.model.AvailableProfilesRest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleAddFileToSignRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleCommonInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleGetSignatureResultRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleGetTransactionStatusResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignatureResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleStartTransactionRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.GetAvailableTypesOfDocumentsResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.RestExceptionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class FirmaWebV1Api {
  private ApiClient apiClient;

  public FirmaWebV1Api() {
    this(Configuration.getDefaultApiClient());
  }

  public FirmaWebV1Api(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Afegeix un document  al conjunt de Peticions de Firma a realitzar per l&#39;usuari.
   * 
   * @param firmaSimpleAddFileToSignRequest Document a signar i dades específiques de la firma a realitzar. (optional)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String addFileToSign(FirmaSimpleAddFileToSignRequest firmaSimpleAddFileToSignRequest) throws ApiException {
    Object localVarPostBody = firmaSimpleAddFileToSignRequest;
    
    // create path and map variables
    String localVarPath = "/secure/firmaweb/v1/addFileToSign".replaceAll("\\{format\\}","json");

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

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Indica al component de firma que la informació s’ha recuperat correctament i que pot fer neteja en el servidor.
   * 
   * @param body Identificador de la transacció. (optional)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String closeTransaction(String body) throws ApiException {
    Object localVarPostBody = body;
    
    // create path and map variables
    String localVarPath = "/secure/firmaweb/v1/closeTransaction".replaceAll("\\{format\\}","json");

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

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna una llista dels perfils o profiles de firma en servidor disponibles per l&#39;usuari aplicació que realitza la cridada
   * 
   * @param body Idioma en què es retornarà el nom i descripció dels perfils, així com els missatges d&#39;errors (optional)
   * @return a {@code AvailableProfilesRest}
   * @throws ApiException if fails to make API call
   */
  public AvailableProfilesRest getAvailableProfiles(String body) throws ApiException {
    Object localVarPostBody = body;
    
    // create path and map variables
    String localVarPath = "/secure/firmaweb/v1/getAvailableProfiles".replaceAll("\\{format\\}","json");

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

    GenericType<AvailableProfilesRest> localVarReturnType = new GenericType<AvailableProfilesRest>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna una llista dels Tipus Documentals disponibles en el servidor
   * 
   * @param body Idioma en què es retornarà el nom dels tipus de documents així com els missatges d&#39;errors (optional)
   * @return a {@code GetAvailableTypesOfDocumentsResponse}
   * @throws ApiException if fails to make API call
   */
  public GetAvailableTypesOfDocumentsResponse getAvailableTypesOfDocuments(String body) throws ApiException {
    Object localVarPostBody = body;
    
    // create path and map variables
    String localVarPath = "/secure/firmaweb/v1/getAvailableTypesOfDocuments".replaceAll("\\{format\\}","json");

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

    GenericType<GetAvailableTypesOfDocumentsResponse> localVarReturnType = new GenericType<GetAvailableTypesOfDocumentsResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Document signat  i informació d&#39;una firma
   * 
   * @param firmaSimpleGetSignatureResultRequest Identificador de transacció i de firma. (optional)
   * @return a {@code FirmaSimpleSignatureResponse}
   * @throws ApiException if fails to make API call
   */
  public FirmaSimpleSignatureResponse getSignatureResult(FirmaSimpleGetSignatureResultRequest firmaSimpleGetSignatureResultRequest) throws ApiException {
    Object localVarPostBody = firmaSimpleGetSignatureResultRequest;
    
    // create path and map variables
    String localVarPath = "/secure/firmaweb/v1/getSignatureResult".replaceAll("\\{format\\}","json");

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

    GenericType<FirmaSimpleSignatureResponse> localVarReturnType = new GenericType<FirmaSimpleSignatureResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Operacio per obtenir el Id de una transaccio de la API
   * 
   * @param firmaSimpleCommonInfo Solicita i configura una transacció de firma web (optional)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String getTransactionID(FirmaSimpleCommonInfo firmaSimpleCommonInfo) throws ApiException {
    Object localVarPostBody = firmaSimpleCommonInfo;
    
    // create path and map variables
    String localVarPath = "/secure/firmaweb/v1/getTransactionID".replaceAll("\\{format\\}","json");

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

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna estat de la transacció (el procés de firma en general) i resultat del procés de cada firma
   * 
   * @param firmaSimpleStartTransactionRequest Identificador de transacció retornat de la cridada getTransactionID(). (optional)
   * @return a {@code FirmaSimpleGetTransactionStatusResponse}
   * @throws ApiException if fails to make API call
   */
  public FirmaSimpleGetTransactionStatusResponse getTransactionStatus(FirmaSimpleStartTransactionRequest firmaSimpleStartTransactionRequest) throws ApiException {
    Object localVarPostBody = firmaSimpleStartTransactionRequest;
    
    // create path and map variables
    String localVarPath = "/secure/firmaweb/v1/getTransactionStatus".replaceAll("\\{format\\}","json");

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

    GenericType<FirmaSimpleGetTransactionStatusResponse> localVarReturnType = new GenericType<FirmaSimpleGetTransactionStatusResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Envia identificador de la transacció, url de retorn i tipus de vista web (amb o sense iframe) i inicia el procés de firma retornant una URL de redirecció.
   * 
   * @param firmaSimpleStartTransactionRequest Envia identificador de la transacció, url de retorn i tipus de vista web (amb o sense iframe) (optional)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String startTransaction(FirmaSimpleStartTransactionRequest firmaSimpleStartTransactionRequest) throws ApiException {
    Object localVarPostBody = firmaSimpleStartTransactionRequest;
    
    // create path and map variables
    String localVarPath = "/secure/firmaweb/v1/startTransaction".replaceAll("\\{format\\}","json");

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

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
