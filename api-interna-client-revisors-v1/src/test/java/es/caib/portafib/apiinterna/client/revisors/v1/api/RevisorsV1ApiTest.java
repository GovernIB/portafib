/*
 * API Interna de PortaFIB de consulta de Revisors d'un destinatari
 * Conjunt de Serveis REST de PortaFIB per atendre consultes sobre REVISORS d'un destinatari.
 *
 * The version of the OpenAPI document: 1.0-SNAPSHOT
 * Contact: otae@fundaciobit.org
 *
 */
package es.caib.portafib.apiinterna.client.revisors.v1.api;

import es.caib.portafib.apiinterna.client.revisors.v1.model.BasicUserInfoList;
import es.caib.portafib.apiinterna.client.revisors.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.revisors.v1.services.ApiException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * API tests for RevisorsV1Api
 */
public class RevisorsV1ApiTest {

    public static void main(String[] args) {
        RevisorsV1ApiTest revisorsV1ApiTest = new RevisorsV1ApiTest();
        try {
            revisorsV1ApiTest.revisorsByDestinatariNIFTest();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Retorna una llista dels Revisors globals i els associats al NIF d&#39;un Destinatari
     *
     * @throws ApiException
     *          if the Api call fails
     */

    public void revisorsByDestinatariNIFTest() throws Exception {

        /* Llegir les següents dades d'un fitxer de propietats */
        Properties prop = new Properties();

        RevisorsV1Api api = getApi(prop);

        String administrationID = prop.getProperty("administrationID");
        String languageUI = prop.getProperty("languageUI");

        BasicUserInfoList response = api.revisorsByDestinatariNIF(administrationID, languageUI);

        response.getData().forEach(System.out::println);

    }

    protected RevisorsV1Api getApi(Properties prop) throws IOException, FileNotFoundException {
        prop.load(new FileInputStream("test.properties"));

        String basePath = prop.getProperty("basePath");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        ApiClient client = new ApiClient();
        client.setBasePath(basePath);
        client.setUsername(username);
        client.setPassword(password);

        RevisorsV1Api api = new RevisorsV1Api(client);
        return api;
    }
}
