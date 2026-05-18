package es.caib.portafib.apiinterna.client.signature.v1.example.api;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import es.caib.portafib.apiinterna.client.signature.v1.model.Document;
import es.caib.portafib.apiinterna.client.signature.v1.model.DocumentaryType;
import es.caib.portafib.apiinterna.client.signature.v1.model.FileInfoSignature;
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

    protected File getResultsDirectory() {
        File res = new File("results");
        res.mkdirs();
        return res;
    }

    protected FileInfoSignature[] getFilesToSign(Properties prop, long tipusDocumentalID) throws Exception {

        Document[] documentsToSign = getDocumentsToSign(prop);
        FileInfoSignature[] filesToSign = new FileInfoSignature[documentsToSign.length];
        int count = 0;
        for (Document fileToSign : documentsToSign) {
            FileInfoSignature fileInfoSignature = new FileInfoSignature();

            fileInfoSignature.setFileToSign(fileToSign);
            String signID = "Firma_" + count;

            fileInfoSignature.setSignID(signID);
            String name = fileToSign.getName();
            fileInfoSignature.setName(name);
            String reason = "Per aprovar pressuposts - " + fileToSign.getName();
            fileInfoSignature.setReason(reason);
            String location = "Palma";
            fileInfoSignature.setLocation(location);

            int signNumber = 1;
            fileInfoSignature.setSignNumber(signNumber);
            String languageSign = getLanguageUI(prop);
            fileInfoSignature.setLanguageSign(languageSign);

            fileInfoSignature.setDocumentType(tipusDocumentalID);

            String RequiresTimeStampInSignatureStr = prop.getProperty("RequiresTimeStampInSignature");

            if (RequiresTimeStampInSignatureStr == null || RequiresTimeStampInSignatureStr.trim().length() == 0) {
                fileInfoSignature.setRequiresTimeStampInSignature(null);
            } else if (RequiresTimeStampInSignatureStr.equalsIgnoreCase("false")) {
                fileInfoSignature.setRequiresTimeStampInSignature(false);
            } else {
                fileInfoSignature.setRequiresTimeStampInSignature(true);
            }

            filesToSign[count] = fileInfoSignature;
            count++;
        }

        return filesToSign;
    }

    protected Document[] getDocumentsToSign(Properties prop) throws IOException {
        Document[] documentsToSign;
        String files = prop.getProperty("files");
        String[] parts = files.split(",");
        documentsToSign = new Document[parts.length];

        for (int i = 0; i < parts.length; i++) {

            String nom = prop.getProperty("file." + parts[i] + ".name");
            System.out.println("*** FILE[" + parts[i] + "]");
            System.out.println("    Name = " + nom);
            String mime = prop.getProperty("file." + parts[i] + ".mime");

            System.out.println("    Mime: ]" + mime + "[");

            documentsToSign[i] = llegirFitxer(nom, mime);
            System.out.println("    Mida: " + documentsToSign[i].getData().length + " bytes");
        }
        return documentsToSign;
    }

    protected abstract Set<KeyValue> getLanguages(String lang) throws Exception;

    protected abstract Set<DocumentaryType> getDocumentaryTypes(String lang, ApiClient apiClient) throws Exception;

    protected abstract Set<Profile> getProfiles(String lang) throws Exception;

}
