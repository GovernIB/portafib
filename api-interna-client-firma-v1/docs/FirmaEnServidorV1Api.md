# FirmaEnServidorV1Api

All URIs are relative to */portafibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**signdocument**](FirmaEnServidorV1Api.md#signdocument) | **POST** /secure/firmaenservidor/v1/signdocument | Operacio de firma simple en servidor d&#39;un document |
| [**versio**](FirmaEnServidorV1Api.md#versio) | **GET** /secure/firmaenservidor/v1/versio | Retorna la versió d&#39;aquest Servei |



## signdocument

> FirmaSimpleSignatureRest signdocument(firmaSimpleSignDocumentRequest)

Operacio de firma simple en servidor d&#39;un document

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.firma.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.firma.v1.services.models.*;
import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaEnServidorV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FirmaEnServidorV1Api apiInstance = new FirmaEnServidorV1Api(defaultClient);
        FirmaSimpleSignDocumentRequest firmaSimpleSignDocumentRequest = new FirmaSimpleSignDocumentRequest(); // FirmaSimpleSignDocumentRequest | Operacio de firma simple en servidor d'un document
        try {
            FirmaSimpleSignatureRest result = apiInstance.signdocument(firmaSimpleSignDocumentRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FirmaEnServidorV1Api#signdocument");
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
| **firmaSimpleSignDocumentRequest** | [**FirmaSimpleSignDocumentRequest**](FirmaSimpleSignDocumentRequest.md)| Operacio de firma simple en servidor d&#39;un document | [optional] |

### Return type

[**FirmaSimpleSignatureRest**](FirmaSimpleSignatureRest.md)

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


## versio

> String versio()

Retorna la versió d&#39;aquest Servei

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.firma.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.firma.v1.services.models.*;
import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaEnServidorV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FirmaEnServidorV1Api apiInstance = new FirmaEnServidorV1Api(defaultClient);
        try {
            String result = apiInstance.versio();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FirmaEnServidorV1Api#versio");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

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
| **200** | Retornada correctament la versió d&#39;aquest Servei |  -  |
| **401** | No Autenticat |  -  |
| **403** | No Autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **510** | Només s&#39;utilitza per crear fitxer de constants... |  -  |

