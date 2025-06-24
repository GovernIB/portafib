# AsyncSignatureOnWebV1Api

All URIs are relative to */portafibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createAndStartSignatureRequestWithFlowTemplateCode**](AsyncSignatureOnWebV1Api.md#createAndStartSignatureRequestWithFlowTemplateCode) | **POST** /secure/asyncsignatureonweb/v1/createAndStartSignatureRequestWithFlowTemplateCode | Crea i posa en marxa una Petició de Firma a partir d&#39;una codi de Plantilla de Flux de Firmes previament creada al servidor |
| [**createAndStartSignatureRequestWithSignBlockList**](AsyncSignatureOnWebV1Api.md#createAndStartSignatureRequestWithSignBlockList) | **POST** /secure/asyncsignatureonweb/v1/createAndStartSignatureRequestWithSignBlockList | Crea i posa en marxa una Petició de Firma a partir d&#39;una llista de Bloc de Firmes |
| [**deleteSignatureRequest**](AsyncSignatureOnWebV1Api.md#deleteSignatureRequest) | **DELETE** /secure/asyncsignatureonweb/v1/deleteSignatureRequest/{signatureRequestID} | Elimina una petició de firma. |
| [**getDocumentaryTypes**](AsyncSignatureOnWebV1Api.md#getDocumentaryTypes) | **GET** /secure/asyncsignatureonweb/v1/getDocumentaryTypes | Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l&#39;entitat i tipus documentals de l&#39;usuari aplicació |
| [**getLanguages**](AsyncSignatureOnWebV1Api.md#getLanguages) | **GET** /secure/asyncsignatureonweb/v1/getLanguages | Retorna els idiomes disponibles. |
| [**getOriginalFileOfSignatureRequest**](AsyncSignatureOnWebV1Api.md#getOriginalFileOfSignatureRequest) | **GET** /secure/asyncsignatureonweb/v1/getOriginalFileOfSignatureRequest/{signatureRequestID} | Retorna el Fitxer original amb el que es va crear la petició de firma. |
| [**getProfiles**](AsyncSignatureOnWebV1Api.md#getProfiles) | **GET** /secure/asyncsignatureonweb/v1/getProfiles | Retorna els perfils de firma. |
| [**getSignatureRequestState**](AsyncSignatureOnWebV1Api.md#getSignatureRequestState) | **GET** /secure/asyncsignatureonweb/v1/getSignatureRequestState/{signatureRequestID} | Informació de l&#39;estat d&#39;una Petició de firma |
| [**getSignedFileOfSignatureRequest**](AsyncSignatureOnWebV1Api.md#getSignedFileOfSignatureRequest) | **GET** /secure/asyncsignatureonweb/v1/getSignedFileOfSignatureRequest/{signatureRequestID} | Retorna el Fitxer Signat acompanyats de Informació de la Firma, Signants, custòdia i validacions realitzades. |
| [**getUrlToViewFlow**](AsyncSignatureOnWebV1Api.md#getUrlToViewFlow) | **GET** /secure/asyncsignatureonweb/v1/getUrlToViewFlow/{signatureRequestID} | Obté una URL des de la que es pot visualitzar el diagrama de flux amb l&#39;estat de la petició (per emprar-la per exemple dins un \&quot;&lt;iframe&gt;\&quot;) |
| [**versio**](AsyncSignatureOnWebV1Api.md#versio) | **GET** /secure/asyncsignatureonweb/v1/versio | Retorna la versió d&#39;aquest Servei |



## createAndStartSignatureRequestWithFlowTemplateCode

> Long createAndStartSignatureRequestWithFlowTemplateCode(signatureRequestWithFlowTemplateCode)

Crea i posa en marxa una Petició de Firma a partir d&#39;una codi de Plantilla de Flux de Firmes previament creada al servidor

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.AsyncSignatureOnWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        AsyncSignatureOnWebV1Api apiInstance = new AsyncSignatureOnWebV1Api(defaultClient);
        SignatureRequestWithFlowTemplateCode signatureRequestWithFlowTemplateCode = new SignatureRequestWithFlowTemplateCode(); // SignatureRequestWithFlowTemplateCode | Informació de la Petició de Firma a crear.
        try {
            Long result = apiInstance.createAndStartSignatureRequestWithFlowTemplateCode(signatureRequestWithFlowTemplateCode);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AsyncSignatureOnWebV1Api#createAndStartSignatureRequestWithFlowTemplateCode");
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
| **signatureRequestWithFlowTemplateCode** | [**SignatureRequestWithFlowTemplateCode**](SignatureRequestWithFlowTemplateCode.md)| Informació de la Petició de Firma a crear. | [optional] |

### Return type

**Long**

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


## createAndStartSignatureRequestWithSignBlockList

> Long createAndStartSignatureRequestWithSignBlockList(signatureRequestWithSignBlockList)

Crea i posa en marxa una Petició de Firma a partir d&#39;una llista de Bloc de Firmes

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.AsyncSignatureOnWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        AsyncSignatureOnWebV1Api apiInstance = new AsyncSignatureOnWebV1Api(defaultClient);
        SignatureRequestWithSignBlockList signatureRequestWithSignBlockList = new SignatureRequestWithSignBlockList(); // SignatureRequestWithSignBlockList | Informació de la Petició de Firma a crear i posar en marxa.
        try {
            Long result = apiInstance.createAndStartSignatureRequestWithSignBlockList(signatureRequestWithSignBlockList);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AsyncSignatureOnWebV1Api#createAndStartSignatureRequestWithSignBlockList");
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
| **signatureRequestWithSignBlockList** | [**SignatureRequestWithSignBlockList**](SignatureRequestWithSignBlockList.md)| Informació de la Petició de Firma a crear i posar en marxa. | [optional] |

### Return type

**Long**

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


## deleteSignatureRequest

> deleteSignatureRequest(signatureRequestID, languageUI)

Elimina una petició de firma.

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.AsyncSignatureOnWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        AsyncSignatureOnWebV1Api apiInstance = new AsyncSignatureOnWebV1Api(defaultClient);
        Long signatureRequestID = 56L; // Long | Identificador de Petició de firma
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            apiInstance.deleteSignatureRequest(signatureRequestID, languageUI);
        } catch (ApiException e) {
            System.err.println("Exception when calling AsyncSignatureOnWebV1Api#deleteSignatureRequest");
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
| **signatureRequestID** | **Long**| Identificador de Petició de firma | |
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

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
import es.caib.portafib.apiinterna.client.signature.v1.api.AsyncSignatureOnWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        AsyncSignatureOnWebV1Api apiInstance = new AsyncSignatureOnWebV1Api(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            Set<DocumentaryType> result = apiInstance.getDocumentaryTypes(language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AsyncSignatureOnWebV1Api#getDocumentaryTypes");
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
import es.caib.portafib.apiinterna.client.signature.v1.api.AsyncSignatureOnWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        AsyncSignatureOnWebV1Api apiInstance = new AsyncSignatureOnWebV1Api(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            Set<KeyValue> result = apiInstance.getLanguages(language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AsyncSignatureOnWebV1Api#getLanguages");
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


## getOriginalFileOfSignatureRequest

> Document getOriginalFileOfSignatureRequest(signatureRequestID, languageUI)

Retorna el Fitxer original amb el que es va crear la petició de firma.

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.AsyncSignatureOnWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        AsyncSignatureOnWebV1Api apiInstance = new AsyncSignatureOnWebV1Api(defaultClient);
        Long signatureRequestID = 56L; // Long | Identificador de Petició de firma
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            Document result = apiInstance.getOriginalFileOfSignatureRequest(signatureRequestID, languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AsyncSignatureOnWebV1Api#getOriginalFileOfSignatureRequest");
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
| **signatureRequestID** | **Long**| Identificador de Petició de firma | |
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**Document**](Document.md)

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
import es.caib.portafib.apiinterna.client.signature.v1.api.AsyncSignatureOnWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        AsyncSignatureOnWebV1Api apiInstance = new AsyncSignatureOnWebV1Api(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            Set<Profile> result = apiInstance.getProfiles(language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AsyncSignatureOnWebV1Api#getProfiles");
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


## getSignatureRequestState

> SignatureRequestState getSignatureRequestState(signatureRequestID, languageUI)

Informació de l&#39;estat d&#39;una Petició de firma

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.AsyncSignatureOnWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        AsyncSignatureOnWebV1Api apiInstance = new AsyncSignatureOnWebV1Api(defaultClient);
        Long signatureRequestID = 56L; // Long | Identificador de Petició de firma
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            SignatureRequestState result = apiInstance.getSignatureRequestState(signatureRequestID, languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AsyncSignatureOnWebV1Api#getSignatureRequestState");
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
| **signatureRequestID** | **Long**| Identificador de Petició de firma | |
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**SignatureRequestState**](SignatureRequestState.md)

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


## getSignedFileOfSignatureRequest

> SignedFile getSignedFileOfSignatureRequest(signatureRequestID, languageUI)

Retorna el Fitxer Signat acompanyats de Informació de la Firma, Signants, custòdia i validacions realitzades.

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.AsyncSignatureOnWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        AsyncSignatureOnWebV1Api apiInstance = new AsyncSignatureOnWebV1Api(defaultClient);
        Long signatureRequestID = 56L; // Long | Identificador de Petició de firma
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            SignedFile result = apiInstance.getSignedFileOfSignatureRequest(signatureRequestID, languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AsyncSignatureOnWebV1Api#getSignedFileOfSignatureRequest");
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
| **signatureRequestID** | **Long**| Identificador de Petició de firma | |
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**SignedFile**](SignedFile.md)

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


## getUrlToViewFlow

> String getUrlToViewFlow(signatureRequestID, languageUI)

Obté una URL des de la que es pot visualitzar el diagrama de flux amb l&#39;estat de la petició (per emprar-la per exemple dins un \&quot;&lt;iframe&gt;\&quot;)

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.AsyncSignatureOnWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        AsyncSignatureOnWebV1Api apiInstance = new AsyncSignatureOnWebV1Api(defaultClient);
        Long signatureRequestID = 56L; // Long | Identificador de Petició de firma
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            String result = apiInstance.getUrlToViewFlow(signatureRequestID, languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AsyncSignatureOnWebV1Api#getUrlToViewFlow");
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
| **signatureRequestID** | **Long**| Identificador de Petició de firma | |
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
import es.caib.portafib.apiinterna.client.signature.v1.api.AsyncSignatureOnWebV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        AsyncSignatureOnWebV1Api apiInstance = new AsyncSignatureOnWebV1Api(defaultClient);
        try {
            String result = apiInstance.versio();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AsyncSignatureOnWebV1Api#versio");
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

