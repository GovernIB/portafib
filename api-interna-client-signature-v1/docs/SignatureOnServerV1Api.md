# SignatureOnServerV1Api

All URIs are relative to */portafibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getDocumentaryTypes**](SignatureOnServerV1Api.md#getDocumentaryTypes) | **GET** /secure/signatureonserver/v1/getDocumentaryTypes | Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l&#39;entitat i tipus documentals de l&#39;usuari aplicació |
| [**getLanguages**](SignatureOnServerV1Api.md#getLanguages) | **GET** /secure/signatureonserver/v1/getLanguages | Retorna els idiomes disponibles. |
| [**getProfiles**](SignatureOnServerV1Api.md#getProfiles) | **GET** /secure/signatureonserver/v1/getProfiles | Retorna els perfils de firma. |
| [**signdocument**](SignatureOnServerV1Api.md#signdocument) | **POST** /secure/signatureonserver/v1/signdocument | Operacio de firma simple en servidor d&#39;un document |
| [**upgradeSignature**](SignatureOnServerV1Api.md#upgradeSignature) | **POST** /secure/signatureonserver/v1/upgradeSignature | Operacio de firma simple en servidor d&#39;un document |
| [**versio**](SignatureOnServerV1Api.md#versio) | **GET** /secure/signatureonserver/v1/versio | Retorna la versió d&#39;aquest Servei |



## getDocumentaryTypes

> Set&lt;DocumentaryType&gt; getDocumentaryTypes(language)

Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l&#39;entitat i tipus documentals de l&#39;usuari aplicació

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureOnServerV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureOnServerV1Api apiInstance = new SignatureOnServerV1Api(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            Set<DocumentaryType> result = apiInstance.getDocumentaryTypes(language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureOnServerV1Api#getDocumentaryTypes");
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
| **language** | **String**| Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**Set&lt;DocumentaryType&gt;**](DocumentaryType.md)

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


## getLanguages

> Set&lt;KeyValue&gt; getLanguages(language)

Retorna els idiomes disponibles.

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureOnServerV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureOnServerV1Api apiInstance = new SignatureOnServerV1Api(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            Set<KeyValue> result = apiInstance.getLanguages(language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureOnServerV1Api#getLanguages");
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
| **language** | **String**| Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

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


## getProfiles

> Set&lt;Profile&gt; getProfiles(language)

Retorna els perfils de firma.

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureOnServerV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureOnServerV1Api apiInstance = new SignatureOnServerV1Api(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            Set<Profile> result = apiInstance.getProfiles(language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureOnServerV1Api#getProfiles");
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
| **language** | **String**| Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**Set&lt;Profile&gt;**](Profile.md)

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


## signdocument

> SignDocumentResponse signdocument(signDocumentRequest)

Operacio de firma simple en servidor d&#39;un document

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureOnServerV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureOnServerV1Api apiInstance = new SignatureOnServerV1Api(defaultClient);
        SignDocumentRequest signDocumentRequest = new SignDocumentRequest(); // SignDocumentRequest | Operacio de firma simple en servidor d'un document
        try {
            SignDocumentResponse result = apiInstance.signdocument(signDocumentRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureOnServerV1Api#signdocument");
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
| **signDocumentRequest** | [**SignDocumentRequest**](SignDocumentRequest.md)| Operacio de firma simple en servidor d&#39;un document | [optional] |

### Return type

[**SignDocumentResponse**](SignDocumentResponse.md)

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
| **200** | Operació realitzada correctament |  -  |


## upgradeSignature

> UpgradeResponse upgradeSignature(languageUI, upgradeRequest)

Operacio de firma simple en servidor d&#39;un document

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureOnServerV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureOnServerV1Api apiInstance = new SignatureOnServerV1Api(defaultClient);
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        UpgradeRequest upgradeRequest = new UpgradeRequest(); // UpgradeRequest | Funcio de upgrade se firma digital
        try {
            UpgradeResponse result = apiInstance.upgradeSignature(languageUI, upgradeRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureOnServerV1Api#upgradeSignature");
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
| **upgradeRequest** | [**UpgradeRequest**](UpgradeRequest.md)| Funcio de upgrade se firma digital | [optional] |

### Return type

[**UpgradeResponse**](UpgradeResponse.md)

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
| **200** | Operació realitzada correctament |  -  |


## versio

> String versio()

Retorna la versió d&#39;aquest Servei

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureOnServerV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureOnServerV1Api apiInstance = new SignatureOnServerV1Api(defaultClient);
        try {
            String result = apiInstance.versio();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureOnServerV1Api#versio");
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
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Retornada correctament la versió d&#39;aquest Servei |  -  |

