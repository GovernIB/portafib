package es.caib.portafib.apiinterna.client.signature.v1.example.api;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.portafib.apiinterna.client.signature.v1.model.Document;
import es.caib.portafib.apiinterna.client.signature.v1.model.RestExceptionInfo;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;

public abstract class BasicAbstractV1ApiTest<A> {

    protected Logger log = Logger.getLogger(getClass());

    private Properties properties = null;

    protected ApiClient getApiClient() throws Exception {

        Properties prop = getConfigProperties();
        String languageUI = getLanguageUI(prop);

        String basePath = getRequiredProperty(prop, "basePath");
        log.debug("BasePath: " + basePath);
        String username = getRequiredProperty(prop, "username");
        log.debug("Username: " + username);
        String password = getRequiredProperty(prop, "password");

        ApiClient apiClient = getApiClient(basePath, username, password, languageUI);

        return apiClient;
    }

    protected ApiClient getApiClient(String basePath, String username, String password, String languageUI) {
        ApiClient client = new ApiClient();
        client.setBasePath(basePath);
        client.setUsername(username);
        client.setPassword(password);

        client.setDebugging(true);

        client.addDefaultHeader("Accept-Language", languageUI);
        return client;
    }

    protected String getLanguageUI(Properties prop) throws Exception {
        return getRequiredProperty(prop, "languageUI");
    }

    protected String getLanguageUI() throws Exception {
        Properties prop = getConfigProperties();
        return getRequiredProperty(prop, "languageUI");
    }

    protected String getRequiredProperty(Properties prop, String propName) throws Exception {
        String p = prop.getProperty(propName);
        if (p == null) {
            throw new Exception("No s'ha trobat la propietat " + propName + " al fitxer de configuració "
                    + getConfigPropertiesFile());
        }
        return p;
    }

    protected boolean processApiException(ApiException e, final String testName, boolean printLogs) {

        if (printLogs) {
            log.error("Stack trace:", e);
            log.error("Error durant la realització del test: " + testName);
            log.error("    - Code: " + e.getCode() + " (" + Status.fromStatusCode(e.getCode()).name() + ")");
        }

        int status = e.getCode();
        if (status == 400 || status == 401 || status == 403 || status == 500) {

            try {
                ObjectMapper objectMapper = Configuration.getDefaultApiClient().getJSON().getContext(null);
                RestExceptionInfo rei = objectMapper.readValue(e.getMessage(), RestExceptionInfo.class);
                if (printLogs) {
                    log.error("    - RestExceptionInfo:" + "\n" + "          + errorCode: " + rei.getErrorCode() + "\n"
                            + "          + errorMessage: " + rei.getErrorMessage() + "\n" + "          + stackTrace: "
                            + rei.getStackTrace() + "\n" + "          + stackTraceCause: " + rei.getStackTraceCause()
                            + "\n" + "          + field: " + rei.getField());
                }
            } catch (Exception e1) {
                //e1.printStackTrace();
                // No es un objecte RestExceptionInfo
                if (printLogs) {
                    log.error(
                            "    - Message: Error no controlat realitzant: " + testName + ". Error: " + e.getMessage());
                    log.error("\n"
                            + "    ---------------------------------- IMPORTANT -------------------------------\n"
                            + "    El body del missatge HTTP hauria de contenir un objecte RestExceptionInfo\n"
                            + "    representat en format JSON però en el seu lloc conté:\n" + e.getMessage() + "\n"
                            + "    ----------------------------------------------------------------------------\n");
                }
                return false;
            }
        } else {
            if (printLogs) {
                log.error("    - Message: Error no controlat realitzant: " + testName + ". Error: " + e.getMessage());
            }
        }
        return true;
    }

    protected void checkExpectedError(Integer expectedError, ApiException e) throws ApiException {
        if (expectedError == null || e.getCode() != expectedError) {
            throw e;
        }
        // Validar que l'excepció es correcta

        if (!processApiException(e, "Check if ApiException contains RestExceptionInfo", false)) {

            log.error("    ----------------- IMPORTANT ----------------\n"
                    + "    El body del missatge HTTP hauria de contenir\n"
                    + "    un objecte RestExceptionInfo representat en\n"
                    + "    format JSON però en el seu lloc conté:\n" + e.getMessage() + "\n"
                    + "    ---------------------------------------------");
        }

    }

    protected Properties getConfigProperties() throws Exception {
        if (properties == null) {

            File f = new File(getConfigPropertiesFile());
            if (!f.exists()) {
                throw new Exception("No s'ha trobat el fitxer de configuració: " + f.getAbsolutePath());
            }

            Properties prop = new Properties();
            prop.load(new FileInputStream(f));
            properties = prop;
        }
        return properties;
    }

    protected String getConfigPropertiesFile() {
        return "./signature.properties";
    }

    public static Document llegirFitxer(String fileName, String mime) throws IOException {

        byte[] data = readDataFromFile(fileName);

        Document asf = new Document();
        asf.setName(fileName);
        asf.setMime(mime);
        asf.setData(data);

        return asf;
    }

    protected void guardarFitxer(byte[] data, String fileName) throws FileNotFoundException, IOException {

        File f = new File(fileName);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(data);
        fos.flush();
        fos.close();

        System.out.println("Guardat " + fileName);
    }

    public static byte[] readDataFromFile(String fileName) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(new File(fileName));
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        copyFileToOutputStream(is, fos);
        byte[] data = fos.toByteArray();
        return data;
    }

    public static void copyFileToOutputStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
    }

    protected abstract A getApi() throws Exception;

    protected abstract A getApi(ApiClient apiClient) throws Exception;

}
