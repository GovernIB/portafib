/*
 * API Interna de PortaFIB de consulta de serveis per Firma en Servidor
 * Conjunt de Serveis REST de PortaFIB per atendre consultes de Firma en Servidor de Portafib
 *
 * The version of the OpenAPI document: 1.0-SNAPSHOT
 * Contact: firma@fundaciobit.org

 */

package es.caib.portafib.apiinterna.client.signature.v1.example.api;

import es.caib.portafib.apiinterna.client.signature.v1.api.SignatureOnServerV1Api;
import es.caib.portafib.apiinterna.client.signature.v1.model.CommonInfo;
import es.caib.portafib.apiinterna.client.signature.v1.model.Document;
import es.caib.portafib.apiinterna.client.signature.v1.model.DocumentaryType;
import es.caib.portafib.apiinterna.client.signature.v1.model.FileInfoSignature;
import es.caib.portafib.apiinterna.client.signature.v1.model.KeyValue;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignDocumentRequest;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignDocumentResponse;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignModeConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignedFileInfo;
import es.caib.portafib.apiinterna.client.signature.v1.model.StatusConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.ProcessStatus;
import es.caib.portafib.apiinterna.client.signature.v1.model.Profile;
import es.caib.portafib.apiinterna.client.signature.v1.model.UpgradeRequest;
import es.caib.portafib.apiinterna.client.signature.v1.model.UpgradeResponse;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Set;

/**
 * API tests for FirmaEnServidorV1Api
 * 
 * @author fbosch
 * @author anadal
 * 
 */
public class FirmaEnServidorV1ApiTest extends AbstractV1ApiTest<SignatureOnServerV1Api> {

    public static void main(String[] args) {
        FirmaEnServidorV1ApiTest test = new FirmaEnServidorV1ApiTest();
        try {

            //test.callCommonTests();

            //test.testSignatureServerPAdES();
                  
            //test.testSignatureServerPAdESStatus401_Unathorized();
            
            //test.testSignatureServerPAdESErrorFirmant();
            
            //test.testUpgradePAdESSignature();
            
        } catch (ApiException e) {
            test.processApiException(e, "Tests de Firma en Servidor", true);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void testSignatureServerPAdESStatus401_Unathorized() throws ApiException, Exception {

        final String testName = "Firma en Servidor - Error 401- UNAUTHORIZED";
        final Integer expectedError = 401;

        ApiClient apiClient = getApiClient();
        apiClient.setPassword("badpassword");
        SignatureOnServerV1Api apiError = new SignatureOnServerV1Api(apiClient);

        Document fileToSign = llegirFitxer("./testfiles/hola.pdf", "application/pdf");
        internalTestSignatureServerPAdES(testName, expectedError, apiError, fileToSign);

        System.out.println("Test OK");

    }

    public void testSignatureServerPAdESErrorFirmant() throws ApiException, Exception {

        final String testName = "Firma en Servidor - Error Firmant xml amb PAdES";
        final Integer expectedError = null;

        SignatureOnServerV1Api api = getApi();

        try {
            Document fileToSign = llegirFitxer("./src/main/resources/sample.xml", "application/xml");
            internalTestSignatureServerPAdES(testName, expectedError, api, fileToSign);
            throw new Exception("S'ha enviat un fitxer XML per firma en format PAdES i s'esperava un error.");
        } catch (EstatFinalNoOK e) {

            if (StatusConstants.STATUS_FINAL_ERROR.getValue().equals(e.getInternalCode())) {
                System.out.println("Test OK");
            } else {
                throw new Exception("S'ha rebut un error de EstatFinalNoOK però s'esperava un internalCode "
                        + StatusConstants.STATUS_FINAL_ERROR.getValue() + " però s'ha rebut un " + e.getInternalCode());
            }
        }

    }

    /**
     * Operacio de firma simple en servidor d&#39;un document PDF
     *
     * @throws ApiException if the Api call fails
     */
    public void testSignatureServerPAdES() throws ApiException, Exception {

        final String testName = "Firma PAdES en Servidor";
        final Integer expectedError = null;

        SignatureOnServerV1Api api = getApi();

        Document[] documents = getDocumentsToSign(getConfigProperties());
        for (Document document : documents) {
            internalTestSignatureServerPAdES(testName, expectedError, api, document);
        }

    }


    protected SignDocumentResponse internalTestSignatureServerPAdES(final String testName, final Integer expectedError,
            SignatureOnServerV1Api api, Document fileToSign) throws Exception, ApiException {
        Properties prop = getConfigProperties();

        String languageUI = prop.getProperty("languageUI", "ca");

        String perfil = prop.getProperty(PROFILE_PADES_PROPERTY);
        if (perfil == null || perfil.trim().isEmpty()) {
            avisPerPerfilBuit(PROFILE_PADES_PROPERTY);
            perfil = null;
        }

        // Document fileToSign = llegirFitxer(file == null ? "src/main/resources/hola-test.pdf" : file, "application/pdf");

        System.out.println(" PERFIL => " + perfil);
        System.out.println(" FILE NOM => " + fileToSign.getName());
        return internalSignDocument(api, perfil, fileToSign, languageUI, testName, expectedError);
    }

    protected SignDocumentResponse internalSignDocument(SignatureOnServerV1Api api, final String perfil,
            Document fileToSign, String languageUI, String testName, Integer expectedError)
            throws ApiException, Exception {

        System.out.println("============================ " + testName + " ============================");
        try {
            String signID = "1";
            String name = fileToSign.getName();
            String reason = "Per aprovar pressuposts";
            String location = "Palma";

            int signNumber = 1;
            String languageSign = "ca";
            long tipusDocumentalID = 99; // =TD99

            String alias = getConfigProperties().getProperty("alias");

            FileInfoSignature fileInfoSignature = new FileInfoSignature();
            fileInfoSignature.setFileToSign(fileToSign);
            fileInfoSignature.setSignID(signID);
            fileInfoSignature.setName(name);
            fileInfoSignature.setReason(reason);
            fileInfoSignature.setLocation(location);
            fileInfoSignature.setSignNumber(signNumber);
            fileInfoSignature.setLanguageSign(languageSign);

            fileInfoSignature.setDocumentType(tipusDocumentalID);

            // Es la configuració del Servidor (deixam el valor per defecte)
            String username = alias;
            String administrationID = null;
            String signerEmail = null;

            CommonInfo commonInfo = new CommonInfo();
            commonInfo.setSignProfile(perfil);
            commonInfo.setLanguageUI(languageUI);
            commonInfo.setUsername(username);
            commonInfo.setAdministrationID(administrationID);
            commonInfo.setSignerEmail(signerEmail);

            System.out.println("languageUI = |" + languageUI + "|");

            SignDocumentRequest signature = new SignDocumentRequest();

            signature.setCommonInfo(commonInfo);
            signature.setFileInfoSignature(fileInfoSignature);

            SignDocumentResponse fullResults = api.signdocument(signature);

            System.out.println(fullResults.getSignPlugin());

            ProcessStatus transactionStatus = fullResults.getStatus();

            int status = transactionStatus.getStatus();

            if (status == (int) StatusConstants.STATUS_INITIALIZING.getValue()) {
                throw new EstatFinalNoOK(status, "Rebut estat Initializing ...Unknown Error (???)");

            } else if (status == (int) StatusConstants.STATUS_IN_PROGRESS.getValue()) {
                throw new EstatFinalNoOK(status, "Rebut estat IN_PROGRESS ... Unknown Error (????) ");

            } else if (status == (int) StatusConstants.STATUS_FINAL_ERROR.getValue()) {

                throw new EstatFinalNoOK(status, "Rebut estat ERROR: " + transactionStatus.getErrorMessage(),
                        transactionStatus.getErrorStackTrace());

            } else if (status == (int) StatusConstants.STATUS_CANCELLED.getValue()) {
                throw new EstatFinalNoOK(status, "Rebut estat CANCELED: S'ha cancel·lat el procés de firmat.");

            } else if (status == (int) StatusConstants.STATUS_FINAL_OK.getValue()) {

                System.out.println(" ========= RESULTAT  =========");

                {

                    SignedFileInfo signedFileInfo = fullResults.getSignedFileInfo();
                    if (signedFileInfo != null) {
                        System.out.println(fullResults.getSignedFileInfo());
                    } else {
                        System.out.println("  Signed File Info: NULL");
                    }

                    System.err.println("  RESULT: OK");
                    Document fsf = fullResults.getSignedFile();
                    File result = new File(getResultsDirectory(), fsf.getName());
                    FileOutputStream fos = new FileOutputStream(result);
                    fos.write(fsf.getData());
                    fos.flush();
                    fos.close();
                    System.out.println("  RESULT: Fitxer signat guardat en '" + result.getAbsolutePath() + "'");

                    return fullResults;

                } // Final for de fitxers firmats
            } else {
                throw new EstatFinalNoOK(null, "Rebut estat desconegut (" + status + ")");
            } // Final for de fitxers firmats
              // Final Case Firma OK
              // Final Switch Firma
        } catch (ApiException e) {
            checkExpectedError(expectedError, e);

            return null;
        }

    }

    protected void avisPerPerfilBuit(final String perfilProperty) {
        System.out.println("           ================= AVIS ==============\n" + "La propietat " + perfilProperty
                + " està buida.\n"
                + "Això significa que si l'usuari aplicacio té més d'un perfil assignat, llavors llançarà un error.\n"
                + "          =====================================\n");
    }

    public void testUpgradePAdESSignature() throws ApiException, Exception {

        final String testName = "testUpgradePAdESSignature";
        final Integer expectedError = null;

        Document fileToUpgrade = llegirFitxer("testfiles/hola_signed.pdf", "application/pdf");

        File upgradedFileName = new File("results/hola_signed-upgraded.pdf");

        internalTestUpgrade(PROFILE_PADES_PROPERTY, fileToUpgrade, null, upgradedFileName, testName, expectedError);

    }

    protected UpgradeResponse internalTestUpgrade(final String perfilProperty, Document fileToUpgrade,
            Document documentDetached, File upgradedFileName, String testName, Integer expectedError)
            throws Exception, ApiException {

        System.out.println("============================ " + testName + " ============================");
        try {

            SignatureOnServerV1Api api = getApi();

            Properties prop = getConfigProperties();

            String perfil = prop.getProperty(perfilProperty);

            if (perfil == null || perfil.trim().isEmpty()) {
                avisPerPerfilBuit(PROFILE_PADES_PROPERTY);
                perfil = null;
            }

            UpgradeRequest upgradeRequest = new UpgradeRequest();

            upgradeRequest.setProfileCode(perfil);
            upgradeRequest.setDetachedDocument(documentDetached);
            upgradeRequest.setSignature(fileToUpgrade);

            String languageUI = prop.getProperty("languageUI", "ca");

            UpgradeResponse upgradeResponse = api.upgradeSignature(languageUI, upgradeRequest);
            
            System.out.println("============ SIGN MODE VALUES ============");
            for (SignModeConstants mode : SignModeConstants.values()) {
                System.out.println(mode.getValue() + " => " + mode.name());
            }
            
            System.out.println("==========================================");
            

            System.out.println(upgradeResponse.getUpgradedFileInfo().toString());

            Document upgraded = upgradeResponse.getUpgradedFile();

            guardarFitxer(upgraded.getData(), upgradedFileName);

            return upgradeResponse;

        } catch (ApiException e) {
            checkExpectedError(expectedError, e);
            return null;
        }
    }

    @Override
    public SignatureOnServerV1Api getApi() throws Exception {
        return getApi(getApiClient());
    }

    @Override
    public SignatureOnServerV1Api getApi(ApiClient client) throws Exception {
        SignatureOnServerV1Api api = new SignatureOnServerV1Api(client);
        return api;
    }

    public class EstatFinalNoOK extends Exception {

        protected final Integer internalCode;

        protected final String errorStackTrace;

        public EstatFinalNoOK(Integer internalCode, String message) {
            this(internalCode, message, null);
        }

        public EstatFinalNoOK(Integer internalCode, String message, String errorStackTrace) {
            super(message);
            this.errorStackTrace = errorStackTrace;
            this.internalCode = internalCode;
        }

        public String getErrorStackTrace() {
            return errorStackTrace;
        }

        public Integer getInternalCode() {
            return internalCode;
        }

    }

    @Override
    protected Set<KeyValue> getLanguages(String lang) throws Exception {
        return getApi().getLanguages(lang);
    }

    @Override
    protected Set<DocumentaryType> getDocumentaryTypes(String lang, ApiClient apiClient) throws Exception {
        return getApi(apiClient).getDocumentaryTypes(lang);
    }

    @Override
    protected Set<Profile> getProfiles(String lang) throws Exception {
        return getApi().getProfiles(lang);
    }

    @Override
    protected String getConfigPropertiesFile() {
        return "signatureonserver.properties";
    }

}
