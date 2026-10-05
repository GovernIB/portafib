# SignatureValidationV1Api

All URIs are relative to */portafibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**validateSignature**](SignatureValidationV1Api.md#validateSignature) | **POST** /secure/signaturevalidation/v1/validateSignature | Operacio de validació de documents signats digitalment |



## validateSignature

> ValidateSignatureResponse validateSignature(languageUI, validateSignatureRequest)

Operacio de validació de documents signats digitalment

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.signature.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.signature.v1.services.models.*;
import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureValidationV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureValidationV1Api apiInstance = new SignatureValidationV1Api(defaultClient);
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        ValidateSignatureRequest validateSignatureRequest = new ValidateSignatureRequest(); // ValidateSignatureRequest | Document digital signat que es vol validar
        try {
            ValidateSignatureResponse result = apiInstance.validateSignature(languageUI, validateSignatureRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureValidationV1Api#validateSignature");
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
| **validateSignatureRequest** | [**ValidateSignatureRequest**](ValidateSignatureRequest.md)| Document digital signat que es vol validar | [optional] |

### Return type

[**ValidateSignatureResponse**](ValidateSignatureResponse.md)

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

