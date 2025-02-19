package es.caib.portafib.apiinterna.client.firma.v1.example.api;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;

import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleFile;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleStatus;
import es.caib.portafib.apiinterna.client.firma.v1.model.RestExceptionInfo;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.apiinterna.client.firma.v1.services.Configuration;

/**
 *
 * @author fbosch
 * @author anadal
 * 30 ene 2025 9:58:20
 */
public abstract class AbstractV1ApiTest<A> extends FirmaSimpleStatus {
    
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
        String username = getRequiredProperty(prop, "username");
        String password = getRequiredProperty(prop, "password");

        ApiClient client = getApiClient(basePath, username, password, languageUI);
        return client;
    }

    protected ApiClient getApiClient(String basePath, String username, String password, String languageUI) {
        ApiClient client = new ApiClient();
        client.setBasePath(basePath);
        client.setUsername(username);
        client.setPassword(password);

        client.addDefaultHeader("Accept-Language", languageUI);
        return client;
    }

    protected String getLanguageUI(Properties prop) throws Exception {
        return getRequiredProperty(prop, "languageUI");
    }

    protected String getRequiredProperty(Properties prop, String propName) throws Exception {
        String p = prop.getProperty(propName);
        if (p == null) {
            throw new Exception("No s'ha trobat la propietat " + propName + " al fitxer de configuració "
                    + getConfigPropertiesName());
        }
        return p;
    }

    protected boolean processApiException(ApiException e, final String testName) {
        System.err.println("Error durant la realització del test: " + testName);
        System.err.println("    - Code: " + e.getCode() + " (" + Status.fromStatusCode(e.getCode()).name() + ")");

        int status = e.getCode();
        if (status == 400 || status == 401 || status == 403 || status == 500) {

            try {
                ObjectMapper objectMapper = Configuration.getDefaultApiClient().getJSON().getContext(null);
                RestExceptionInfo rei = objectMapper.readValue(e.getMessage(), RestExceptionInfo.class);
                System.err.println("    - RestExceptionInfo:");
                System.err.println("          + errorCode: " + rei.getErrorCode());
                System.err.println("          + errorMessage: " + rei.getErrorMessage());
                System.err.println("          + stackTrace: " + rei.getStackTrace());
                System.err.println("          + stackTraceCause: " + rei.getStackTraceCause());
                System.err.println("          + field: " + rei.getField());
            } catch (Exception e1) {
                //e1.printStackTrace();
                // No es un objecte RestExceptionInfo
                System.err.println(
                        "    - Message: Error no controlat realitzant: " + testName + ". Error: " + e.getMessage());
                
                System.err.println("    ----------------- IMPORTANT ----------------\n"
                            + "    El body del missatge HTTP hauria de contenir\n"
                            + "    un objecte RestExceptionInfo representat en\n"
                            + "    format JSON però en el seu lloc conté:\n" + e.getMessage() + "\n"
                            + "    ---------------------------------------------");
                
                return false;
            }
        } else {
            System.err.println(
                    "    - Message: Error no controlat realitzant: " + testName + ". Error: " + e.getMessage());
        }
        return true;
    }

    protected void checkExpectedError(Integer expectedError, ApiException e) throws ApiException {
        if (expectedError == null || e.getCode() != expectedError) {
            throw e;
        }
        // Validar que l'excepció es correcta
        PrintStream err = System.err;
        try {

            System.setErr(new PrintStream(new ByteArrayOutputStream()));

            if (!processApiException(e, "Check if ApiException contains RestExceptionInfo")) {

                System.setErr(err);
                System.err.println("    ----------------- IMPORTANT ----------------\n"
                        + "    El body del missatge HTTP hauria de contenir\n"
                        + "    un objecte RestExceptionInfo representat en\n"
                        + "    format JSON però en el seu lloc conté:\n" + e.getMessage() + "\n"
                        + "    ---------------------------------------------");
            }

        } finally {
            System.setErr(err);
        }

    }

    protected Properties getConfigProperties() throws Exception {
        if (properties == null) {

            File f = new File("./" + getConfigPropertiesName() + ".properties");
            if (!f.exists()) {
                throw new Exception("No s'ha trobat el fitxer de configuració: " + f.getAbsolutePath());
            }

            Properties prop = new Properties();
            prop.load(new FileInputStream(f));
            properties = prop;
        }
        return properties;
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

    public static FirmaSimpleFile llegirFitxer(String fileName, String mime) throws IOException {

        byte[] data = readDataFromFile(fileName);

        FirmaSimpleFile asf = new FirmaSimpleFile();
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

    protected abstract A getApi() throws Exception;

    protected abstract String getConfigPropertiesName();

}
