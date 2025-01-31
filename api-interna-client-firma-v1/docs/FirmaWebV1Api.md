# FirmaWebV1Api

All URIs are relative to */portafibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addFileToSign**](FirmaWebV1Api.md#addFileToSign) | **POST** /secure/firmaweb/v1/addFileToSign | Afegeix un document  al conjunt de Peticions de Firma a realitzar per l&#39;usuari. |
| [**closeTransaction**](FirmaWebV1Api.md#closeTransaction) | **POST** /secure/firmaweb/v1/closeTransaction | Indica al component de firma que la informació s’ha recuperat correctament i que pot fer neteja en el servidor. |
| [**getAvailableProfiles**](FirmaWebV1Api.md#getAvailableProfiles) | **POST** /secure/firmaweb/v1/getAvailableProfiles | Retorna una llista dels perfils o profiles de firma en servidor disponibles per l&#39;usuari aplicació que realitza la cridada |
| [**getAvailableTypesOfDocuments**](FirmaWebV1Api.md#getAvailableTypesOfDocuments) | **POST** /secure/firmaweb/v1/getAvailableTypesOfDocuments | Retorna una llista dels Tipus Documentals disponibles en el servidor |
| [**getSignatureResult**](FirmaWebV1Api.md#getSignatureResult) | **POST** /secure/firmaweb/v1/getSignatureResult | Document signat  i informació d&#39;una firma |
| [**getTransactionID**](FirmaWebV1Api.md#getTransactionID) | **POST** /secure/firmaweb/v1/getTransactionID | Operacio per obtenir el Id de una transaccio de la API |
| [**getTransactionStatus**](FirmaWebV1Api.md#getTransactionStatus) | **POST** /secure/firmaweb/v1/getTransactionStatus | Retorna estat de la transacció (el procés de firma en general) i resultat del procés de cada firma |
| [**startTransaction**](FirmaWebV1Api.md#startTransaction) | **POST** /secure/firmaweb/v1/startTransaction | Envia identificador de la transacció, url de retorn i tipus de vista web (amb o sense iframe) i inicia el procés de firma retornant una URL de redirecció. |



## addFileToSign

> String addFileToSign(firmaSimpleAddFileToSignRequest)

Afegeix un document  al conjunt de Peticions de Firma a realitzar per l&#39;usuari.

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.firma.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.firma.v1.services.models.*;
import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FirmaWebV1Api apiInstance = new FirmaWebV1Api(defaultClient);
        FirmaSimpleAddFileToSignRequest firmaSimpleAddFileToSignRequest = new FirmaSimpleAddFileToSignRequest(); // FirmaSimpleAddFileToSignRequest | Document a signar i dades específiques de la firma a realitzar.
        try {
            String result = apiInstance.addFileToSign(firmaSimpleAddFileToSignRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FirmaWebV1Api#addFileToSign");
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
| **firmaSimpleAddFileToSignRequest** | [**FirmaSimpleAddFileToSignRequest**](FirmaSimpleAddFileToSignRequest.md)| Document a signar i dades específiques de la firma a realitzar. | [optional] |

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
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **500** | Error no controlat |  -  |


## closeTransaction

> String closeTransaction(body)

Indica al component de firma que la informació s’ha recuperat correctament i que pot fer neteja en el servidor.

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.firma.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.firma.v1.services.models.*;
import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FirmaWebV1Api apiInstance = new FirmaWebV1Api(defaultClient);
        String body = "body_example"; // String | Identificador de la transacció.
        try {
            String result = apiInstance.closeTransaction(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FirmaWebV1Api#closeTransaction");
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
| **body** | **String**| Identificador de la transacció. | [optional] |

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
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **500** | Error no controlat |  -  |


## getAvailableProfiles

> AvailableProfilesRest getAvailableProfiles(body)

Retorna una llista dels perfils o profiles de firma en servidor disponibles per l&#39;usuari aplicació que realitza la cridada

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.firma.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.firma.v1.services.models.*;
import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FirmaWebV1Api apiInstance = new FirmaWebV1Api(defaultClient);
        String body = "body_example"; // String | Idioma en què es retornarà el nom i descripció dels perfils, així com els missatges d'errors
        try {
            AvailableProfilesRest result = apiInstance.getAvailableProfiles(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FirmaWebV1Api#getAvailableProfiles");
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
| **body** | **String**| Idioma en què es retornarà el nom i descripció dels perfils, així com els missatges d&#39;errors | [optional] |

### Return type

[**AvailableProfilesRest**](AvailableProfilesRest.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **500** | Error no controlat |  -  |


## getAvailableTypesOfDocuments

> GetAvailableTypesOfDocumentsResponse getAvailableTypesOfDocuments(body)

Retorna una llista dels Tipus Documentals disponibles en el servidor

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.firma.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.firma.v1.services.models.*;
import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FirmaWebV1Api apiInstance = new FirmaWebV1Api(defaultClient);
        String body = "body_example"; // String | Idioma en què es retornarà el nom dels tipus de documents així com els missatges d'errors
        try {
            GetAvailableTypesOfDocumentsResponse result = apiInstance.getAvailableTypesOfDocuments(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FirmaWebV1Api#getAvailableTypesOfDocuments");
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
| **body** | **String**| Idioma en què es retornarà el nom dels tipus de documents així com els missatges d&#39;errors | [optional] |

### Return type

[**GetAvailableTypesOfDocumentsResponse**](GetAvailableTypesOfDocumentsResponse.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **500** | Error no controlat |  -  |


## getSignatureResult

> FirmaSimpleSignatureResponse getSignatureResult(firmaSimpleGetSignatureResultRequest)

Document signat  i informació d&#39;una firma

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.firma.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.firma.v1.services.models.*;
import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FirmaWebV1Api apiInstance = new FirmaWebV1Api(defaultClient);
        FirmaSimpleGetSignatureResultRequest firmaSimpleGetSignatureResultRequest = new FirmaSimpleGetSignatureResultRequest(); // FirmaSimpleGetSignatureResultRequest | Identificador de transacció i de firma.
        try {
            FirmaSimpleSignatureResponse result = apiInstance.getSignatureResult(firmaSimpleGetSignatureResultRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FirmaWebV1Api#getSignatureResult");
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
| **firmaSimpleGetSignatureResultRequest** | [**FirmaSimpleGetSignatureResultRequest**](FirmaSimpleGetSignatureResultRequest.md)| Identificador de transacció i de firma. | [optional] |

### Return type

[**FirmaSimpleSignatureResponse**](FirmaSimpleSignatureResponse.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **500** | Error no controlat |  -  |


## getTransactionID

> String getTransactionID(firmaSimpleCommonInfo)

Operacio per obtenir el Id de una transaccio de la API

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.firma.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.firma.v1.services.models.*;
import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FirmaWebV1Api apiInstance = new FirmaWebV1Api(defaultClient);
        FirmaSimpleCommonInfo firmaSimpleCommonInfo = new FirmaSimpleCommonInfo(); // FirmaSimpleCommonInfo | Solicita i configura una transacció de firma web
        try {
            String result = apiInstance.getTransactionID(firmaSimpleCommonInfo);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FirmaWebV1Api#getTransactionID");
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
| **firmaSimpleCommonInfo** | [**FirmaSimpleCommonInfo**](FirmaSimpleCommonInfo.md)| Solicita i configura una transacció de firma web | [optional] |

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
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **500** | Error no controlat |  -  |


## getTransactionStatus

> FirmaSimpleGetTransactionStatusResponse getTransactionStatus(body)

Retorna estat de la transacció (el procés de firma en general) i resultat del procés de cada firma

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.firma.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.firma.v1.services.models.*;
import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FirmaWebV1Api apiInstance = new FirmaWebV1Api(defaultClient);
        String body = "body_example"; // String | Identificador de transacció retornat de la cridada getTransactionID().
        try {
            FirmaSimpleGetTransactionStatusResponse result = apiInstance.getTransactionStatus(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FirmaWebV1Api#getTransactionStatus");
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
| **body** | **String**| Identificador de transacció retornat de la cridada getTransactionID(). | [optional] |

### Return type

[**FirmaSimpleGetTransactionStatusResponse**](FirmaSimpleGetTransactionStatusResponse.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **500** | Error no controlat |  -  |


## startTransaction

> String startTransaction(firmaSimpleStartTransactionRequest)

Envia identificador de la transacció, url de retorn i tipus de vista web (amb o sense iframe) i inicia el procés de firma retornant una URL de redirecció.

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.firma.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.firma.v1.services.models.*;
import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FirmaWebV1Api apiInstance = new FirmaWebV1Api(defaultClient);
        FirmaSimpleStartTransactionRequest firmaSimpleStartTransactionRequest = new FirmaSimpleStartTransactionRequest(); // FirmaSimpleStartTransactionRequest | Envia identificador de la transacció, url de retorn i tipus de vista web (amb o sense iframe)
        try {
            String result = apiInstance.startTransaction(firmaSimpleStartTransactionRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FirmaWebV1Api#startTransaction");
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
| **firmaSimpleStartTransactionRequest** | [**FirmaSimpleStartTransactionRequest**](FirmaSimpleStartTransactionRequest.md)| Envia identificador de la transacció, url de retorn i tipus de vista web (amb o sense iframe) | [optional] |

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
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **500** | Error no controlat |  -  |

