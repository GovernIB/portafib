package es.caib.portafib.apiinterna.client.signature.v1.example.api;

import java.util.Set;

import es.caib.portafib.apiinterna.client.signature.v1.model.DocumentaryType;
import es.caib.portafib.apiinterna.client.signature.v1.model.KeyValue;
import es.caib.portafib.apiinterna.client.signature.v1.model.Profile;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;

/**
 *
 * @author fbosch
 * @author anadal
 * 30 ene 2025 9:58:20
 */
public abstract class AbstractV1ApiTest<A> extends BasicAbstractV1ApiTest<A> {

    public static final String PROFILE_PADES_PROPERTY = "PROFILE_PADES";

    public static final String PROFILE_XADES_PROPERTY = "PROFILE_XADES";

    public static final String PROFILE_CADES_PROPERTY = "PROFILE_CADES";

    public static final String PROFILE_MIX_PADES_XADES_CADES = "PROFILE_MIX_PADES_XADES_CADES";

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

}
