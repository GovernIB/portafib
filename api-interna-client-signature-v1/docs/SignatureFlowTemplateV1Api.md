# SignatureFlowTemplateV1Api

All URIs are relative to */portafibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**closeTransaction**](SignatureFlowTemplateV1Api.md#closeTransaction) | **GET** /secure/signatureflowtemplate/v1/closeTransaction/{transactionID} | Tanca una transacció de creació de flux de firmes |
| [**createSignatureFlowTemplate**](SignatureFlowTemplateV1Api.md#createSignatureFlowTemplate) | **POST** /secure/signatureflowtemplate/v1/createSignatureFlowTemplate | Crea una Plantilla de Flux de Firmes a partir de la informació d&#39;un Flux De Firmes |
| [**deleteFlowTemplate**](SignatureFlowTemplateV1Api.md#deleteFlowTemplate) | **DELETE** /secure/signatureflowtemplate/v1/deleteFlowTemplate/{flowTemplateID} | Esborra una Plantilla de Flux de Firmes a partir del seu ID |
| [**getAllFlowTemplates**](SignatureFlowTemplateV1Api.md#getAllFlowTemplates) | **GET** /secure/signatureflowtemplate/v1/getAllFlowTemplates | Retorna una llista de totes les plantilles de flux de firmes associades a l&#39;usuari aplicació amb el que s&#39;autentica. |
| [**getAllFlowTemplatesByFilter**](SignatureFlowTemplateV1Api.md#getAllFlowTemplatesByFilter) | **GET** /secure/signatureflowtemplate/v1/getAllFlowTemplatesByFilter | Retorna una llista de totes les plantilles de flux de firmes associades a l&#39;usuari aplicació amb el que s&#39;autentica. |
| [**getFlowInfoByFlowTemplateID**](SignatureFlowTemplateV1Api.md#getFlowInfoByFlowTemplateID) | **GET** /secure/signatureflowtemplate/v1/getFlowInfoByFlowTemplateID/{flowTemplateID} | Serveix per obtenir Informació completa d&#39;una Plantilla de Flux de Firmes a partir del seu ID |
| [**getInternalFlowIDByFlowTemplateID**](SignatureFlowTemplateV1Api.md#getInternalFlowIDByFlowTemplateID) | **GET** /secure/signatureflowtemplate/v1/getInternalFlowIDByFlowTemplateID/{flowTemplateID} | Serveix per obtenir l&#39;ID intern del flux a partir de l&#39;ID públic de la Plantilla de Flux de Firmes |
| [**getReviseursByDestinationAdministrationID**](SignatureFlowTemplateV1Api.md#getReviseursByDestinationAdministrationID) | **GET** /secure/signatureflowtemplate/v1/getReviseursByDestinationAdministrationID/{administrationID} | Retorna una llista dels Revisors globals i els associats al NIF d&#39;un Destinatari |
| [**getSignatureFlowTransactionResult**](SignatureFlowTemplateV1Api.md#getSignatureFlowTransactionResult) | **GET** /secure/signatureflowtemplate/v1/getSignatureFlowTransactionResult/{transactionID} | Metode per obtenir els resultats de la creació d&#39;un flux o plantilla de flux de firmes |
| [**getTransactionID**](SignatureFlowTemplateV1Api.md#getTransactionID) | **POST** /secure/signatureflowtemplate/v1/getTransactionID | Mètode per obtenir un Identificador de Transacció. |
| [**getUrlToEditFlowTemplate**](SignatureFlowTemplateV1Api.md#getUrlToEditFlowTemplate) | **POST** /secure/signatureflowtemplate/v1/getUrlToEditFlowTemplate | Retorna una URL per poder editar una Plantilla de Flux de Firmes de forma gràfica |
| [**getUrlToViewFlowTemplate**](SignatureFlowTemplateV1Api.md#getUrlToViewFlowTemplate) | **GET** /secure/signatureflowtemplate/v1/getUrlToViewFlowTemplate/{flowTemplateID} | Retorna una URL que mostra una Plantilla de Flux de Firmes de forma gràfica en model només lectura |
| [**startTransaction**](SignatureFlowTemplateV1Api.md#startTransaction) | **POST** /secure/signatureflowtemplate/v1/startTransaction | Mètode per iniciar una Transacció. |
| [**updateDescriptionOfFlowTemplate**](SignatureFlowTemplateV1Api.md#updateDescriptionOfFlowTemplate) | **PATCH** /secure/signatureflowtemplate/v1/updateDescriptionOfFlowTemplate/{flowTemplateID} | Actualitza la descripció d&#39;una Plantilla de Flux de Firmes a partir del seu ID |



## closeTransaction

> closeTransaction(transactionID)

Tanca una transacció de creació de flux de firmes

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        String transactionID = "transactionID_example"; // String | Identificador de la Transacció que volem finalitzar
        try {
            apiInstance.closeTransaction(transactionID);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#closeTransaction");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **transactionID** | **String**| Identificador de la Transacció que volem finalitzar | |

### Return type

null (empty response body)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## createSignatureFlowTemplate

> String createSignatureFlowTemplate(signatureFlowTemplate, languageUI)

Crea una Plantilla de Flux de Firmes a partir de la informació d&#39;un Flux De Firmes

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        SignatureFlowTemplate signatureFlowTemplate = new SignatureFlowTemplate(); // SignatureFlowTemplate | Informació de la plantilla de Flux de Firmes
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            String result = apiInstance.createSignatureFlowTemplate(signatureFlowTemplate, languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#createSignatureFlowTemplate");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **signatureFlowTemplate** | [**SignatureFlowTemplate**](SignatureFlowTemplate.md)| Informació de la plantilla de Flux de Firmes | |
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament. Identificador de la plantilla creada |  -  |


## deleteFlowTemplate

> Boolean deleteFlowTemplate(flowTemplateID, languageUI)

Esborra una Plantilla de Flux de Firmes a partir del seu ID

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        String flowTemplateID = "flowTemplateID_example"; // String | Identificador del Flux de Firmes el qual volem esborrar.
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            Boolean result = apiInstance.deleteFlowTemplate(flowTemplateID, languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#deleteFlowTemplate");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **flowTemplateID** | **String**| Identificador del Flux de Firmes el qual volem esborrar. | |
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

**Boolean**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## getAllFlowTemplates

> Set&lt;KeyValue&gt; getAllFlowTemplates(languageUI)

Retorna una llista de totes les plantilles de flux de firmes associades a l&#39;usuari aplicació amb el que s&#39;autentica.

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            Set<KeyValue> result = apiInstance.getAllFlowTemplates(languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#getAllFlowTemplates");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**Set&lt;KeyValue&gt;**](KeyValue.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## getAllFlowTemplatesByFilter

> Set&lt;KeyValue&gt; getAllFlowTemplatesByFilter(languageUI, nameFilter, descriptionFilter)

Retorna una llista de totes les plantilles de flux de firmes associades a l&#39;usuari aplicació amb el que s&#39;autentica.

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        String nameFilter = "nameFilter_example"; // String | Patró per filtrar a partir del Nom
        String descriptionFilter = "descriptionFilter_example"; // String | Patró per filtrar a partir de la Descripció
        try {
            Set<KeyValue> result = apiInstance.getAllFlowTemplatesByFilter(languageUI, nameFilter, descriptionFilter);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#getAllFlowTemplatesByFilter");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |
| **nameFilter** | **String**| Patró per filtrar a partir del Nom | [optional] |
| **descriptionFilter** | **String**| Patró per filtrar a partir de la Descripció | [optional] |

### Return type

[**Set&lt;KeyValue&gt;**](KeyValue.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## getFlowInfoByFlowTemplateID

> SignatureFlowTemplate getFlowInfoByFlowTemplateID(flowTemplateID, languageUI)

Serveix per obtenir Informació completa d&#39;una Plantilla de Flux de Firmes a partir del seu ID

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        String flowTemplateID = "flowTemplateID_example"; // String | Identificador del Flux de Firmes a obtenir
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            SignatureFlowTemplate result = apiInstance.getFlowInfoByFlowTemplateID(flowTemplateID, languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#getFlowInfoByFlowTemplateID");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **flowTemplateID** | **String**| Identificador del Flux de Firmes a obtenir | |
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**SignatureFlowTemplate**](SignatureFlowTemplate.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## getInternalFlowIDByFlowTemplateID

> Long getInternalFlowIDByFlowTemplateID(flowTemplateID, languageUI)

Serveix per obtenir l&#39;ID intern del flux a partir de l&#39;ID públic de la Plantilla de Flux de Firmes

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        String flowTemplateID = "flowTemplateID_example"; // String | Identificador del Flux de Firmes del qual volem l'ID intern
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            Long result = apiInstance.getInternalFlowIDByFlowTemplateID(flowTemplateID, languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#getInternalFlowIDByFlowTemplateID");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **flowTemplateID** | **String**| Identificador del Flux de Firmes del qual volem l&#39;ID intern | |
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

**Long**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament. ID intern del Flux de Firmes associat a la Plantilla. Aquest ID és el que s&#39;ha d&#39;utilitzar en el mètode createAndStartSignatureRequestWithFlowTemplateCode() de AsyncSignatureOnWebApiV1 |  -  |


## getReviseursByDestinationAdministrationID

> Set&lt;BasicUserInfo&gt; getReviseursByDestinationAdministrationID(administrationID, languageUI)

Retorna una llista dels Revisors globals i els associats al NIF d&#39;un Destinatari

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        String administrationID = "administrationID_example"; // String | DNI del destinatari del qual volem obtenir els revisors associats.
        String languageUI = "ca"; // String | Idioma en que s'enviaran els missatges d'error
        try {
            Set<BasicUserInfo> result = apiInstance.getReviseursByDestinationAdministrationID(administrationID, languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#getReviseursByDestinationAdministrationID");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **administrationID** | **String**| DNI del destinatari del qual volem obtenir els revisors associats. | |
| **languageUI** | **String**| Idioma en que s&#39;enviaran els missatges d&#39;error | [optional] |

### Return type

[**Set&lt;BasicUserInfo&gt;**](BasicUserInfo.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## getSignatureFlowTransactionResult

> SignatureFlowTemplateTransactionResult getSignatureFlowTransactionResult(transactionID)

Metode per obtenir els resultats de la creació d&#39;un flux o plantilla de flux de firmes

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        String transactionID = "transactionID_example"; // String | Identificador de la Transacció de creació de Flux de Firmes
        try {
            SignatureFlowTemplateTransactionResult result = apiInstance.getSignatureFlowTransactionResult(transactionID);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#getSignatureFlowTransactionResult");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **transactionID** | **String**| Identificador de la Transacció de creació de Flux de Firmes | |

### Return type

[**SignatureFlowTemplateTransactionResult**](SignatureFlowTemplateTransactionResult.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament.Informació de l&#39;estat de la transacció i del flux o plantilla creats. |  -  |


## getTransactionID

> String getTransactionID(signatureFlowTemplateTransactionIdRequest)

Mètode per obtenir un Identificador de Transacció.

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        SignatureFlowTemplateTransactionIdRequest signatureFlowTemplateTransactionIdRequest = new SignatureFlowTemplateTransactionIdRequest(); // SignatureFlowTemplateTransactionIdRequest | Dades requerides per la devolució d'un ID de transacció.
        try {
            String result = apiInstance.getTransactionID(signatureFlowTemplateTransactionIdRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#getTransactionID");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **signatureFlowTemplateTransactionIdRequest** | [**SignatureFlowTemplateTransactionIdRequest**](SignatureFlowTemplateTransactionIdRequest.md)| Dades requerides per la devolució d&#39;un ID de transacció. | [optional] |

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Identificador de transacció. |  -  |


## getUrlToEditFlowTemplate

> String getUrlToEditFlowTemplate(signatureFlowTemplateEdit)

Retorna una URL per poder editar una Plantilla de Flux de Firmes de forma gràfica

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        SignatureFlowTemplateEdit signatureFlowTemplateEdit = new SignatureFlowTemplateEdit(); // SignatureFlowTemplateEdit | Dades de petició de la URL
        try {
            String result = apiInstance.getUrlToEditFlowTemplate(signatureFlowTemplateEdit);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#getUrlToEditFlowTemplate");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **signatureFlowTemplateEdit** | [**SignatureFlowTemplateEdit**](SignatureFlowTemplateEdit.md)| Dades de petició de la URL | [optional] |

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament. URL a l&#39;edició de la Plantilla |  -  |


## getUrlToViewFlowTemplate

> String getUrlToViewFlowTemplate(flowTemplateID, languageUI)

Retorna una URL que mostra una Plantilla de Flux de Firmes de forma gràfica en model només lectura

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        String flowTemplateID = "flowTemplateID_example"; // String | Identificador del Flux de Firmes del qual volen la URL per mostrar-ho
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            String result = apiInstance.getUrlToViewFlowTemplate(flowTemplateID, languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#getUrlToViewFlowTemplate");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **flowTemplateID** | **String**| Identificador del Flux de Firmes del qual volen la URL per mostrar-ho | |
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament.URL a la vista de la Plantilla. |  -  |


## startTransaction

> String startTransaction(signatureFlowTemplateStartTransactionRequest)

Mètode per iniciar una Transacció.

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        SignatureFlowTemplateStartTransactionRequest signatureFlowTemplateStartTransactionRequest = new SignatureFlowTemplateStartTransactionRequest(); // SignatureFlowTemplateStartTransactionRequest | Dades requerides per l'inici d'una transacció
        try {
            String result = apiInstance.startTransaction(signatureFlowTemplateStartTransactionRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#startTransaction");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **signatureFlowTemplateStartTransactionRequest** | [**SignatureFlowTemplateStartTransactionRequest**](SignatureFlowTemplateStartTransactionRequest.md)| Dades requerides per l&#39;inici d&#39;una transacció | [optional] |

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament.Url de redirecció cap al Servidor Intermedi |  -  |


## updateDescriptionOfFlowTemplate

> Boolean updateDescriptionOfFlowTemplate(flowTemplateID, body, languageUI)

Actualitza la descripció d&#39;una Plantilla de Flux de Firmes a partir del seu ID

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureFlowTemplateV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureFlowTemplateV1Api apiInstance = new SignatureFlowTemplateV1Api(defaultClient);
        String flowTemplateID = "flowTemplateID_example"; // String | Identificador de la Plantilla de Flux de Firmes de la qual volem actualitzar la descripció
        String body = "body_example"; // String | Nova descripció del flux de firmes
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            Boolean result = apiInstance.updateDescriptionOfFlowTemplate(flowTemplateID, body, languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureFlowTemplateV1Api#updateDescriptionOfFlowTemplate");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **flowTemplateID** | **String**| Identificador de la Plantilla de Flux de Firmes de la qual volem actualitzar la descripció | |
| **body** | **String**| Nova descripció del flux de firmes | |
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

**Boolean**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament. true si s&#39;ha actualitzat la descripció, false si no s&#39;ha trobat el flux de firmes |  -  |

