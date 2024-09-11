# TipusDocumentalV1Api

All URIs are relative to */portafibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**callList**](TipusDocumentalV1Api.md#callList) | **GET** /public/tipusdocumental/v1/list | Retorna la versió de PortaFIB REST |



## callList

> LlistaTipusDocumentalRest callList(language, appuser)

Retorna la versió de PortaFIB REST

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.tipusdocumentals.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.tipusdocumentals.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.tipusdocumentals.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.tipusdocumentals.v1.services.models.*;
import es.caib.portafib.apiinterna.client.tipusdocumentals.v1.api.TipusDocumentalV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");

        TipusDocumentalV1Api apiInstance = new TipusDocumentalV1Api(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        String appuser = "appuser_example"; // String | Filtre pel nom de l'usuari aplicacio. Opcional.
        try {
            LlistaTipusDocumentalRest result = apiInstance.callList(language, appuser);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TipusDocumentalV1Api#callList");
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

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Operació realitzada correctament |  -  |
| **404** | Paràmetres incorrectes |  -  |
| **500** | Error no controlat |  -  |

