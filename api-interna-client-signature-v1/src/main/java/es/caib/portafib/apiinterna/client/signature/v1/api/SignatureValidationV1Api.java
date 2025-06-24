package es.caib.portafib.apiinterna.client.signature.v1.api;

import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.portafib.apiinterna.client.signature.v1.model.CertificateTypeMineturConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.RestExceptionInfo;
import es.caib.portafib.apiinterna.client.signature.v1.model.ValidateSignatureRequest;
import es.caib.portafib.apiinterna.client.signature.v1.model.ValidateSignatureResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class SignatureValidationV1Api {
  private ApiClient apiClient;

  public SignatureValidationV1Api() {
    this(Configuration.getDefaultApiClient());
  }

  public SignatureValidationV1Api(ApiClient apiClient) {
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
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @param validateSignatureRequest Operacio de firma simple en servidor d&#39;un document (optional)
   * @return a {@code ValidateSignatureResponse}
   * @throws ApiException if fails to make API call
   */
  public ValidateSignatureResponse validateSignature(String languageUI, ValidateSignatureRequest validateSignatureRequest) throws ApiException {
    Object localVarPostBody = validateSignatureRequest;
    
    // create path and map variables
    String localVarPath = "/secure/signaturevalidation/v1/validateSignature".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "languageUI", languageUI));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<ValidateSignatureResponse> localVarReturnType = new GenericType<ValidateSignatureResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
