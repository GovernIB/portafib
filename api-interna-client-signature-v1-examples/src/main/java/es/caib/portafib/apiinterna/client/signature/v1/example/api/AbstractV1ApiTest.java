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
import java.util.Set;

import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.portafib.apiinterna.client.signature.v1.model.Document;
import es.caib.portafib.apiinterna.client.signature.v1.model.DocumentaryType;
import es.caib.portafib.apiinterna.client.signature.v1.model.KeyValue;
import es.caib.portafib.apiinterna.client.signature.v1.model.ProcessStatus;
import es.caib.portafib.apiinterna.client.signature.v1.model.Profile;
import es.caib.portafib.apiinterna.client.signature.v1.model.RestExceptionInfo;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.signature.v1.services.Configuration;

/**
 *
 * @author fbosch
 * @author anadal
 * 30 ene 2025 9:58:20
 */
public abstract class AbstractV1ApiTest<A> extends ProcessStatus {

    public static final String PROFILE_PADES_PROPERTY = "PROFILE_PADES";

    public static final String PROFILE_XADES_PROPERTY = "PROFILE_XADES";

    public static final String PROFILE_CADES_PROPERTY = "PROFILE_CADES";

    public static final String PROFILE_MIX_PADES_XADES_CADES = "PROFILE_MIX_PADES_XADES_CADES";

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
            throw new Exception(
                    "No s'ha trobat la propietat " + propName + " al fitxer de configuració " + getConfigPropertiesFile());
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

    public static Document llegirFitxer(String fileName, String mime) throws IOException {

        byte[] data = readDataFromFile(fileName);

        Document asf = new Document();
        asf.setNom(fileName);
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

    public void callCommonTests() throws ApiException, Exception {

        callTipusDocumentalListTest();

        callTipusDocumentalListWithNotValidAppUserTest();

        callPerfilsDeFirmaListTest();

        callAvailableLanguagesTest();
    }

    public void callTipusDocumentalListWithNotValidAppUserTest() throws ApiException, Exception {

        final Integer expectedError = 401;
        ApiClient apiClient = getApiClient();
        apiClient.setPassword("holacaracola");
        internalTestTipusDocumentalList(expectedError, "callTipusDocumentalListWithNotValidAppUserTest", apiClient);
        System.out.println("Test OK");
    }

    public void callTipusDocumentalListTest() throws ApiException, Exception {

        final Integer expectedError = null;

        ApiClient apiClient = getApiClient();

        internalTestTipusDocumentalList(expectedError, "callTipusDocumentalListTest", apiClient);
    }

    public void callPerfilsDeFirmaListTest() throws ApiException, Exception {

        Integer expectedError = null;

        internalTestPerfilsDeFirmaList(expectedError, "callPerfilsDeFirmaListTest");
    }

    public void callAvailableLanguagesTest() throws ApiException, Exception {

        Integer expectedError = null;

        internalTestAvailableLanguages(expectedError, "callAvailable LanguagesTest");
    }

    protected Set<KeyValue> internalTestAvailableLanguages(Integer expectedError, String testName)
            throws ApiException, Exception {
        System.out.println("============================ " + testName + " ============================");
        try {

            String languageUI = getLanguageUI(getConfigProperties());

            Set<KeyValue> response = getLanguages(languageUI);
            if (expectedError != null) {
                log.error(testName + ": S'espera un error " + expectedError + " i la cridada ha funcionat.");
            }
            System.out.println(response.toString());
            return response;
        } catch (ApiException e) {
            checkExpectedError(expectedError, e);
            return null;
        }

    }

    protected Set<Profile> internalTestPerfilsDeFirmaList(Integer expectedError, String testName)
            throws ApiException, Exception {
        System.out.println("============================ " + testName + " ============================");
        try {
            String languageUI = getLanguageUI(getConfigProperties());

            Set<Profile> response = getProfiles(languageUI);
            if (expectedError != null) {
                log.error(testName + ": S'espera un error " + expectedError + " i la cridada ha funcionat.");
            }
            System.out.println(response.toString());
            return response;
        } catch (ApiException e) {
            checkExpectedError(expectedError, e);
            return null;
        }

    }

    protected Set<DocumentaryType> internalTestTipusDocumentalList(Integer expectedError, String testName,
            ApiClient apiClient) throws ApiException, Exception {
        System.out.println("============================ " + testName + " ============================");

        try {

            String languageUI = getLanguageUI(getConfigProperties());

            //ApiClient apiClient = getApiClient();

            Set<DocumentaryType> response = getDocumentaryTypes(languageUI, apiClient);
            if (expectedError != null) {
                throw new Exception(
                        testName + ": S'espera un error " + expectedError + " però la cridada ha funcionat.");
            }

            System.out.println(response.toString());
            return response;
        } catch (ApiException e) {
            checkExpectedError(expectedError, e);
            return null;
        }

    }

    protected abstract Set<KeyValue> getLanguages(String lang) throws Exception;

    protected abstract Set<DocumentaryType> getDocumentaryTypes(String lang, ApiClient apiClient) throws Exception;

    protected abstract Set<Profile> getProfiles(String lang) throws Exception;

    protected abstract A getApi() throws Exception;

    protected abstract A getApi(ApiClient apiClient) throws Exception;

}
