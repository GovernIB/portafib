# UtilsV1Api

All URIs are relative to */portafibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**tipusdocumentalslist**](UtilsV1Api.md#tipusdocumentalslist) | **GET** /secure/utils/v1/tipusdocumentalslist | Retorna la versió de PortaFIB REST |



## tipusdocumentalslist

> LlistaTipusDocumentalRest tipusdocumentalslist(language, appuser)

Retorna la versió de PortaFIB REST

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.utils.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.utils.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.utils.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.utils.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.utils.v1.services.models.*;
import es.caib.portafib.apiinterna.client.utils.v1.api.UtilsV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        UtilsV1Api apiInstance = new UtilsV1Api(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        String appuser = "appuser_example"; // String | Filtre pel nom de l'usuari aplicacio. Opcional.
        try {
            LlistaTipusDocumentalRest result = apiInstance.tipusdocumentalslist(language, appuser);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UtilsV1Api#tipusdocumentalslist");
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
| **appuser** | **String**| Filtre pel nom de l&#39;usuari aplicacio. Opcional. | [optional] |

### Return type

[**LlistaTipusDocumentalRest**](LlistaTipusDocumentalRest.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **500** | Error no controlat |  -  |

