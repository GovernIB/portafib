/*
 * API Interna de PortaFIB de consulta de serveis d'utilitat
 * Conjunt de Serveis REST de PortaFIB per atendre consultes generiques de Portafib
 *
 * The version of the OpenAPI document: 1.0-SNAPSHOT
 * Contact: otae@fundaciobit.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package es.caib.portafib.apiinterna.client.firma.v1.services;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class Configuration {
    public static final String VERSION = "1.0-SNAPSHOT";

    private static ApiClient defaultApiClient = new ApiClient();

    /**
     * Get the default API client, which would be used when creating API
     * instances without providing an API client.
     *
     * @return Default API client
     */
    public static ApiClient getDefaultApiClient() {
        return defaultApiClient;
    }

    /**
     * Set the default API client, which would be used when creating API
     * instances without providing an API client.
     *
     * @param apiClient API client
     */
    public static void setDefaultApiClient(ApiClient apiClient) {
        defaultApiClient = apiClient;
    }
}
