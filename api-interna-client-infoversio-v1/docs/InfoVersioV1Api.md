# InfoVersioV1Api

All URIs are relative to */portafibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**versioApi**](InfoVersioV1Api.md#versioApi) | **GET** /public/infoversio/v1/versioapi | Retorna la versió de PortaFIB REST |
| [**versioApp**](InfoVersioV1Api.md#versioApp) | **GET** /public/infoversio/v1/versioapp | Retorna la versió de PortaFIB |



## versioApi

> InfoVersio versioApi()

Retorna la versió de PortaFIB REST

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.infoversio.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.infoversio.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.infoversio.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.infoversio.v1.services.models.*;
import es.caib.portafib.apiinterna.client.infoversio.v1.api.InfoVersioV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");

        InfoVersioV1Api apiInstance = new InfoVersioV1Api(defaultClient);
        try {
            InfoVersio result = apiInstance.versioApi();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling InfoVersioV1Api#versioApi");
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

[**InfoVersio**](InfoVersio.md)

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


## versioApp

> InfoVersio versioApp()

Retorna la versió de PortaFIB

### Example

```java
// Import classes:
import es.caib.portafib.apiinterna.client.infoversio.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.infoversio.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.infoversio.v1.services.Configuration;
import es.caib.portafib.apiinterna.client.infoversio.v1.services.models.*;
import es.caib.portafib.apiinterna.client.infoversio.v1.api.InfoVersioV1Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/portafibapi/interna");

        InfoVersioV1Api apiInstance = new InfoVersioV1Api(defaultClient);
        try {
            InfoVersio result = apiInstance.versioApp();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling InfoVersioV1Api#versioApp");
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

[**InfoVersio**](InfoVersio.md)

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

