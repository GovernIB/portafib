# InfoVersionApi

All URIs are relative to */portafibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**versionApi**](InfoVersionApi.md#versionApi) | **GET** /public/infoversion/versionapi | Retorna la versió de PortaFIB REST |
| [**versionApp**](InfoVersionApi.md#versionApp) | **GET** /public/infoversion/versionapp | Retorna la versió de PortaFIB |



## versionApi

> InfoVersion versionApi()

Retorna la versió de PortaFIB REST

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.services.ApiClient;
import es.caib.portafib.apiinterna.client.services.ApiException;
import es.caib.portafib.apiinterna.client.services.Configuration;
import es.caib.portafib.apiinterna.client.services.models.*;
import es.caib.portafib.apiinterna.client.api.InfoVersionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");

        InfoVersionApi apiInstance = new InfoVersionApi(defaultClient);
        try {
            InfoVersion result = apiInstance.versionApi();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling InfoVersionApi#versionApi");
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

[**InfoVersion**](InfoVersion.md)

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


## versionApp

> InfoVersion versionApp()

Retorna la versió de PortaFIB

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.services.ApiClient;
import es.caib.portafib.apiinterna.client.services.ApiException;
import es.caib.portafib.apiinterna.client.services.Configuration;
import es.caib.portafib.apiinterna.client.services.models.*;
import es.caib.portafib.apiinterna.client.api.InfoVersionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");

        InfoVersionApi apiInstance = new InfoVersionApi(defaultClient);
        try {
            InfoVersion result = apiInstance.versionApp();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling InfoVersionApi#versionApp");
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

[**InfoVersion**](InfoVersion.md)

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

