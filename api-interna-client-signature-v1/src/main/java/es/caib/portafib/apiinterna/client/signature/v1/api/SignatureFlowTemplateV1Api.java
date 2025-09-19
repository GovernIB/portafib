package es.caib.portafib.apiinterna.client.signature.v1.api;

import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.portafib.apiinterna.client.signature.v1.model.BasicUserInfo;
import es.caib.portafib.apiinterna.client.signature.v1.model.KeyValue;
import es.caib.portafib.apiinterna.client.signature.v1.model.RestExceptionInfo;
import java.util.Set;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureFlowTemplate;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureFlowTemplateEdit;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureFlowTemplateInfo;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureFlowTemplateStartTransactionRequest;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureFlowTemplateTransactionIdRequest;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureFlowTemplateTransactionResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class SignatureFlowTemplateV1Api {
  private ApiClient apiClient;

  public SignatureFlowTemplateV1Api() {
    this(Configuration.getDefaultApiClient());
  }

  public SignatureFlowTemplateV1Api(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Tanca una transacció de creació de flux de firmes
   * 
   * @param transactionID Identificador de la Transacció que volem finalitzar (required)
   * @throws ApiException if fails to make API call
   */
  public void closeTransaction(String transactionID) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'transactionID' is set
    if (transactionID == null) {
      throw new ApiException(400, "Missing the required parameter 'transactionID' when calling closeTransaction");
    }
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/closeTransaction/{transactionID}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "transactionID" + "\\}", apiClient.escapeString(transactionID.toString()));

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


    apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Crea una Plantilla de Flux de Firmes a partir de la informació d&#39;un Flux De Firmes
   * 
   * @param signatureFlowTemplate Informació de la plantilla de Flux de Firmes (required)
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String createSignatureFlowTemplate(SignatureFlowTemplate signatureFlowTemplate, String languageUI) throws ApiException {
    Object localVarPostBody = signatureFlowTemplate;
    
    // verify the required parameter 'signatureFlowTemplate' is set
    if (signatureFlowTemplate == null) {
      throw new ApiException(400, "Missing the required parameter 'signatureFlowTemplate' when calling createSignatureFlowTemplate");
    }
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/createSignatureFlowTemplate".replaceAll("\\{format\\}","json");

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

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Esborra una Plantilla de Flux de Firmes a partir del seu ID
   * 
   * @param flowTemplateID Identificador del Flux de Firmes el qual volem esborrar. (required)
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Boolean}
   * @throws ApiException if fails to make API call
   */
  public Boolean deleteFlowTemplate(String flowTemplateID, String languageUI) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'flowTemplateID' is set
    if (flowTemplateID == null) {
      throw new ApiException(400, "Missing the required parameter 'flowTemplateID' when calling deleteFlowTemplate");
    }
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/deleteFlowTemplate/{flowTemplateID}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "flowTemplateID" + "\\}", apiClient.escapeString(flowTemplateID.toString()));

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
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<Boolean> localVarReturnType = new GenericType<Boolean>() {};
    return apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna una llista de totes les plantilles de flux de firmes associades a l&#39;usuari aplicació amb el que s&#39;autentica.Requereix com a mínim PortaFIB 3.0.9
   * 
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Set<SignatureFlowTemplateInfo>}
   * @throws ApiException if fails to make API call
   */
  public Set<SignatureFlowTemplateInfo> getAllFlowTemplateInfo(String languageUI) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/getAllFlowTemplateInfo".replaceAll("\\{format\\}","json");

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
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<Set<SignatureFlowTemplateInfo>> localVarReturnType = new GenericType<Set<SignatureFlowTemplateInfo>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna una llista de totes les plantilles de flux de firmes associades a l&#39;usuari aplicació amb el que s&#39;autentica. Deprecat: Usar getAllFlowTemplateInfo(String)
   * 
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Set<KeyValue>}
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public Set<KeyValue> getAllFlowTemplates(String languageUI) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/getAllFlowTemplates".replaceAll("\\{format\\}","json");

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
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<Set<KeyValue>> localVarReturnType = new GenericType<Set<KeyValue>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna una llista de totes les plantilles de flux de firmes associades a l&#39;usuari aplicació amb el que s&#39;autentica.Deprecat: usar getAllFlowTemplateInfoByFilter(String, String, String)
   * 
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @param nameFilter Patró per filtrar a partir del Nom (optional)
   * @param descriptionFilter Patró per filtrar a partir de la Descripció (optional)
   * @return a {@code Set<KeyValue>}
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public Set<KeyValue> getAllFlowTemplatesByFilter(String languageUI, String nameFilter, String descriptionFilter) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/getAllFlowTemplatesByFilter".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "languageUI", languageUI));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "nameFilter", nameFilter));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "descriptionFilter", descriptionFilter));

    
    
    
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
   * Retorna una llista de totes les plantilles de flux de firmes associades a l&#39;usuari aplicació amb el que s&#39;autentica. Requereix com a mínim PortaFIB 3.0.9
   * 
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @param nameFilter Patró per filtrar a partir del Nom (optional)
   * @param descriptionFilter Patró per filtrar a partir de la Descripció (optional)
   * @return a {@code Set<SignatureFlowTemplateInfo>}
   * @throws ApiException if fails to make API call
   */
  public Set<SignatureFlowTemplateInfo> getAllFlowTemplatesInfoByFilter(String languageUI, String nameFilter, String descriptionFilter) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/getAllFlowTemplateInfoByFilter".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "languageUI", languageUI));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "nameFilter", nameFilter));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "descriptionFilter", descriptionFilter));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<Set<SignatureFlowTemplateInfo>> localVarReturnType = new GenericType<Set<SignatureFlowTemplateInfo>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Serveix per obtenir Informació completa d&#39;una Plantilla de Flux de Firmes a partir del seu ID
   * 
   * @param encryptedFlowTemplateID Identificador del Flux de Firmes a obtenir (required)
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code SignatureFlowTemplate}
   * @throws ApiException if fails to make API call
   */
  public SignatureFlowTemplate getFlowInfoByFlowTemplateID(String encryptedFlowTemplateID, String languageUI) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'encryptedFlowTemplateID' is set
    if (encryptedFlowTemplateID == null) {
      throw new ApiException(400, "Missing the required parameter 'encryptedFlowTemplateID' when calling getFlowInfoByFlowTemplateID");
    }
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/getFlowInfoByFlowTemplateID/{encryptedFlowTemplateID}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "encryptedFlowTemplateID" + "\\}", apiClient.escapeString(encryptedFlowTemplateID.toString()));

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
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<SignatureFlowTemplate> localVarReturnType = new GenericType<SignatureFlowTemplate>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Serveix per obtenir l&#39;ID intern del flux a partir de l&#39;ID públic de la Plantilla de Flux de Firmes
   * 
   * @param flowTemplateID Identificador del Flux de Firmes del qual volem l&#39;ID intern (required)
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Long}
   * @throws ApiException if fails to make API call
   */
  public Long getInternalFlowIDByFlowTemplateID(String flowTemplateID, String languageUI) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'flowTemplateID' is set
    if (flowTemplateID == null) {
      throw new ApiException(400, "Missing the required parameter 'flowTemplateID' when calling getInternalFlowIDByFlowTemplateID");
    }
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/getInternalFlowIDByFlowTemplateID/{flowTemplateID}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "flowTemplateID" + "\\}", apiClient.escapeString(flowTemplateID.toString()));

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
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<Long> localVarReturnType = new GenericType<Long>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna una llista dels Revisors globals i els associats al NIF d&#39;un Destinatari
   * 
   * @param administrationID DNI del destinatari del qual volem obtenir els revisors associats. (required)
   * @param languageUI Idioma en que s&#39;enviaran els missatges d&#39;error (optional)
   * @return a {@code Set<BasicUserInfo>}
   * @throws ApiException if fails to make API call
   */
  public Set<BasicUserInfo> getReviseursByDestinationAdministrationID(String administrationID, String languageUI) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'administrationID' is set
    if (administrationID == null) {
      throw new ApiException(400, "Missing the required parameter 'administrationID' when calling getReviseursByDestinationAdministrationID");
    }
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/getReviseursByDestinationAdministrationID/{administrationID}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "administrationID" + "\\}", apiClient.escapeString(administrationID.toString()));

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
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<Set<BasicUserInfo>> localVarReturnType = new GenericType<Set<BasicUserInfo>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Metode per obtenir els resultats de la creació d&#39;un flux o plantilla de flux de firmes
   * 
   * @param transactionID Identificador de la Transacció de creació de Flux de Firmes (required)
   * @return a {@code SignatureFlowTemplateTransactionResult}
   * @throws ApiException if fails to make API call
   */
  public SignatureFlowTemplateTransactionResult getSignatureFlowTransactionResult(String transactionID) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'transactionID' is set
    if (transactionID == null) {
      throw new ApiException(400, "Missing the required parameter 'transactionID' when calling getSignatureFlowTransactionResult");
    }
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/getSignatureFlowTransactionResult/{transactionID}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "transactionID" + "\\}", apiClient.escapeString(transactionID.toString()));

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

    GenericType<SignatureFlowTemplateTransactionResult> localVarReturnType = new GenericType<SignatureFlowTemplateTransactionResult>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Mètode per obtenir un Identificador de Transacció  per a la creació d&#39;una plantilla de flux de firmes via web.
   * 
   * @param signatureFlowTemplateTransactionIdRequest Dades requerides per a l&#39;obtenció d&#39;un ID de transacció per a la creació d&#39;una plantilla de flux de firmes via web. (optional)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String getTransactionID(SignatureFlowTemplateTransactionIdRequest signatureFlowTemplateTransactionIdRequest) throws ApiException {
    Object localVarPostBody = signatureFlowTemplateTransactionIdRequest;
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/getTransactionID".replaceAll("\\{format\\}","json");

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
   * Retorna una URL per poder editar una Plantilla de Flux de Firmes de forma gràfica
   * 
   * @param signatureFlowTemplateEdit Dades de petició de la URL (optional)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String getUrlToEditFlowTemplate(SignatureFlowTemplateEdit signatureFlowTemplateEdit) throws ApiException {
    Object localVarPostBody = signatureFlowTemplateEdit;
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/getUrlToEditFlowTemplate".replaceAll("\\{format\\}","json");

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
   * Retorna una URL que mostra una Plantilla de Flux de Firmes de forma gràfica en model només lectura
   * 
   * @param flowTemplateID Identificador del Flux de Firmes del qual volen la URL per mostrar-ho (required)
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String getUrlToViewFlowTemplate(String flowTemplateID, String languageUI) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'flowTemplateID' is set
    if (flowTemplateID == null) {
      throw new ApiException(400, "Missing the required parameter 'flowTemplateID' when calling getUrlToViewFlowTemplate");
    }
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/getUrlToViewFlowTemplate/{flowTemplateID}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "flowTemplateID" + "\\}", apiClient.escapeString(flowTemplateID.toString()));

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
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Mètode per iniciar una Transacció.
   * 
   * @param signatureFlowTemplateStartTransactionRequest Dades requerides per l&#39;inici d&#39;una transacció (optional)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String startTransaction(SignatureFlowTemplateStartTransactionRequest signatureFlowTemplateStartTransactionRequest) throws ApiException {
    Object localVarPostBody = signatureFlowTemplateStartTransactionRequest;
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/startTransaction".replaceAll("\\{format\\}","json");

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
   * Actualitza la descripció d&#39;una Plantilla de Flux de Firmes a partir del seu ID
   * 
   * @param flowTemplateID Identificador de la Plantilla de Flux de Firmes de la qual volem actualitzar la descripció (required)
   * @param body Nova descripció del flux de firmes (required)
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Boolean}
   * @throws ApiException if fails to make API call
   */
  public Boolean updateDescriptionOfSignatureFlowTemplate(String flowTemplateID, String body, String languageUI) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'flowTemplateID' is set
    if (flowTemplateID == null) {
      throw new ApiException(400, "Missing the required parameter 'flowTemplateID' when calling updateDescriptionOfSignatureFlowTemplate");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling updateDescriptionOfSignatureFlowTemplate");
    }
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/updateDescriptionOfSignatureFlowTemplate/{flowTemplateID}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "flowTemplateID" + "\\}", apiClient.escapeString(flowTemplateID.toString()));

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

    GenericType<Boolean> localVarReturnType = new GenericType<Boolean>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Actualitza el nom d&#39;una Plantilla de Flux de Firmes a partir del seu ID
   * 
   * @param flowTemplateID Identificador de la Plantilla de Flux de Firmes de la qual volem actualitzar la descripció (required)
   * @param body Nou nom de la plantilla de flux de firmes (required)
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Boolean}
   * @throws ApiException if fails to make API call
   */
  public Boolean updateNameOfSignatureFlowTemplate(String flowTemplateID, String body, String languageUI) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'flowTemplateID' is set
    if (flowTemplateID == null) {
      throw new ApiException(400, "Missing the required parameter 'flowTemplateID' when calling updateNameOfSignatureFlowTemplate");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling updateNameOfSignatureFlowTemplate");
    }
    
    // create path and map variables
    String localVarPath = "/secure/signatureflowtemplate/v1/updateNameOfSignatureFlowTemplate/{flowTemplateID}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "flowTemplateID" + "\\}", apiClient.escapeString(flowTemplateID.toString()));

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

    GenericType<Boolean> localVarReturnType = new GenericType<Boolean>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
