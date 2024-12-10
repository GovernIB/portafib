package es.caib.portafib.apiinterna.client.firma.v1.api;

import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.firma.v1.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignDocumentRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignatureResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleUpgradeRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleUpgradeResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.RestExceptionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class FirmaEnServidorV1Api {
  private ApiClient apiClient;

  public FirmaEnServidorV1Api() {
    this(Configuration.getDefaultApiClient());
  }

  public FirmaEnServidorV1Api(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Operacio de firma simple en servidor d&#39;un document
   * 
   * @param firmaSimpleSignDocumentRequest Operacio de firma simple en servidor d&#39;un document (optional)
   * @return a {@code FirmaSimpleSignatureResponse}
   * @throws ApiException if fails to make API call
   */
  public FirmaSimpleSignatureResponse signdocument(FirmaSimpleSignDocumentRequest firmaSimpleSignDocumentRequest) throws ApiException {
    Object localVarPostBody = firmaSimpleSignDocumentRequest;
    
    // create path and map variables
    String localVarPath = "/secure/firmaenservidor/v1/signdocument".replaceAll("\\{format\\}","json");

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
   * Operacio de firma simple en servidor d&#39;un document
   * 
   * @param firmaSimpleUpgradeRequest Funcio de upgrade se firma digital (optional)
   * @return a {@code FirmaSimpleUpgradeResponse}
   * @throws ApiException if fails to make API call
   */
  public FirmaSimpleUpgradeResponse upgradeSignature(FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest) throws ApiException {
    Object localVarPostBody = firmaSimpleUpgradeRequest;
    
    // create path and map variables
    String localVarPath = "/secure/firmaenservidor/v1/upgradeSignature".replaceAll("\\{format\\}","json");

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

    GenericType<FirmaSimpleUpgradeResponse> localVarReturnType = new GenericType<FirmaSimpleUpgradeResponse>() {};
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
    String localVarPath = "/secure/firmaenservidor/v1/versio".replaceAll("\\{format\\}","json");

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
