# RevisorsV1Api

All URIs are relative to */portafibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**revisorsByDestinatariNIF**](RevisorsV1Api.md#revisorsByDestinatariNIF) | **GET** /secure/revisors/v1/revisorsbydestinatarinif | Retorna una llista dels Revisors globals i els associats al NIF d&#39;un Destinatari |



## revisorsByDestinatariNIF

> BasicUserInfoList revisorsByDestinatariNIF(administrationID, languageUI)

Retorna una llista dels Revisors globals i els associats al NIF d&#39;un Destinatari

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.revisors.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.revisors.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.revisors.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.revisors.v1.services.auth.*;
import es.caib.portafib.apiinterna.client.revisors.v1.services.models.*;
import es.caib.portafib.apiinterna.client.revisors.v1.api.RevisorsV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        RevisorsV1Api apiInstance = new RevisorsV1Api(defaultClient);
        String administrationID = "administrationID_example"; // String | DNI del destinatari del qual volen els revisors associats. Opcional.
        String languageUI = "ca"; // String | Idioma en que s'enviaran els missatges d'error
        try {
            BasicUserInfoList result = apiInstance.revisorsByDestinatariNIF(administrationID, languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling RevisorsV1Api#revisorsByDestinatariNIF");
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
| **administrationID** | **String**| DNI del destinatari del qual volen els revisors associats. Opcional. | [optional] |
| **languageUI** | **String**| Idioma en que s&#39;enviaran els missatges d&#39;error | [optional] |

### Return type

[**BasicUserInfoList**](BasicUserInfoList.md)

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
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |

