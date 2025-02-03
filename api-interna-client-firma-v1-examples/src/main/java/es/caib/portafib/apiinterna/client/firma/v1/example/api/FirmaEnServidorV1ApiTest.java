/*
 * API Interna de PortaFIB de consulta de serveis per Firma en Servidor
 * Conjunt de Serveis REST de PortaFIB per atendre consultes de Firma en Servidor de Portafib
 *
 * The version of the OpenAPI document: 1.0-SNAPSHOT
 * Contact: firma@fundaciobit.org

 */

package es.caib.portafib.apiinterna.client.firma.v1.example.api;

import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaEnServidorV1Api;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleCommonInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleFile;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleFileInfoSignature;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignDocumentRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignatureResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleStatus;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleUpgradeRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleUpgradeResponse;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;

import java.io.FileOutputStream;
import java.util.Properties;

/**
 * API tests for FirmaEnServidorV1Api
 * 
 * @author fbosch
 * @author anadal
 * 
 */
public class FirmaEnServidorV1ApiTest extends AbstractV1ApiTest<FirmaEnServidorV1Api> {



    public static void main(String[] args) {
        FirmaEnServidorV1ApiTest test = new FirmaEnServidorV1ApiTest();
        try {

            test.testSignatureServerPAdES();

            test.testSignatureServerPAdESStatus401_Unathorized();

            test.testSignatureServerPAdESErrorFirmant();

            test.testUpgradePAdESSignature();

        } catch (ApiException e) {
            test.processApiException(e, "Tests de Firma en Servidor");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    public void testSignatureServerPAdESStatus401_Unathorized() throws ApiException, Exception {

        final String testName = "Firma en Servidor - Error 401- UNAUTHORIZED";
        final Integer expectedError = 401;

        ApiClient apiClient = getApiClient();

        apiClient.setPassword("badpassword");

        FirmaEnServidorV1Api apiError = new FirmaEnServidorV1Api(apiClient);

        internalTestSignatureServerPAdES(testName, expectedError, apiError);

        System.out.println("Test OK");

    }

    public void testSignatureServerPAdESErrorFirmant() throws ApiException, Exception {

        final String testName = "Firma en Servidor - Error Firmant xml amb PAdES";
        final Integer expectedError = null;

        FirmaEnServidorV1Api api = getApi();

        try {
            internalTestSignatureServerPAdES(testName, expectedError, api, "./src/main/resources/sample.xml");
            throw new Exception("S'ha enviat un fitxer XML per firma en format PAdES i s'esperava un error.");
        } catch (EstatFinalNoOK e) {

            if (getSTATUSFINALERROR().equals(e.getInternalCode())) {
                System.out.println("Test OK");
            } else {
                throw new Exception("S'ha rebut un error de EstatFinalNoOK però s'esperava un internalCode "
                        + getSTATUSFINALERROR() + " però s'ha rebut un " + e.getInternalCode());
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

        FirmaEnServidorV1Api api = getApi();

        internalTestSignatureServerPAdES(testName, expectedError, api);

    }

    protected FirmaSimpleSignatureResponse internalTestSignatureServerPAdES(final String testName,
            final Integer expectedError, FirmaEnServidorV1Api api) throws Exception, ApiException {
        return internalTestSignatureServerPAdES(testName, expectedError, api, null);
    }

    protected FirmaSimpleSignatureResponse internalTestSignatureServerPAdES(final String testName,
            final Integer expectedError, FirmaEnServidorV1Api api, String file) throws Exception, ApiException {
        Properties prop = getConfigProperties();

        String languageUI = prop.getProperty("languageUI", "ca");

        String perfil = prop.getProperty(PROFILE_PADES_PROPERTY);
        if (perfil == null || perfil.trim().isEmpty()) {
            avisPerPerfilBuit(PROFILE_PADES_PROPERTY);
            perfil = null;
        }

        FirmaSimpleFile fileToSign = llegirFitxer(file == null ? "src/main/resources/hola-test.pdf" : file,
                "application/pdf");

        System.out.println(" PERFIL => " + perfil);
        System.out.println(" FILE NOM => " + fileToSign.getNom());
        return internalSignDocument(api, perfil, fileToSign, languageUI, testName, expectedError);
    }

    protected FirmaSimpleSignatureResponse internalSignDocument(FirmaEnServidorV1Api api, final String perfil,
            FirmaSimpleFile fileToSign, String languageUI, String testName, Integer expectedError)
            throws ApiException, Exception {

        System.out.println("============================ " + testName + " ============================");
        try {
            String signID = "1";
            String name = fileToSign.getNom();
            String reason = "Per aprovar pressuposts";
            String location = "Palma";

            int signNumber = 1;
            String languageSign = "ca";
            long tipusDocumentalID = 99; // =TD99

            String alias = getConfigProperties().getProperty("alias");

            FirmaSimpleFileInfoSignature fileInfoSignature = new FirmaSimpleFileInfoSignature();
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

            FirmaSimpleCommonInfo commonInfo = new FirmaSimpleCommonInfo();
            commonInfo.setSignProfile(perfil);
            commonInfo.setLanguageUI(languageUI);
            commonInfo.setUsername(username);
            commonInfo.setAdministrationID(administrationID);
            commonInfo.setSignerEmail(signerEmail);

            System.out.println("languageUI = |" + languageUI + "|");

            FirmaSimpleSignDocumentRequest signature = new FirmaSimpleSignDocumentRequest();

            signature.setCommonInfo(commonInfo);
            signature.setFileInfoSignature(fileInfoSignature);

            FirmaSimpleSignatureResponse fullResults = api.signdocument(signature);

            FirmaSimpleStatus transactionStatus = fullResults.getStatus();

            int status = transactionStatus.getStatus();

            if (status == getSTATUSINITIALIZING()) {
                throw new EstatFinalNoOK(status, "Rebut estat Initializing ...Unknown Error (???)");

            } else if (status == getSTATUSINPROGRESS()) {
                throw new EstatFinalNoOK(status, "Rebut estat IN_PROGRESS ... Unknown Error (????) ");

            } else if (status == getSTATUSFINALERROR()) {

                throw new EstatFinalNoOK(status, "Rebut estat ERROR: " + transactionStatus.getErrorMessage(),
                        transactionStatus.getErrorStackTrace());

            } else if (status == getSTATUSCANCELLED()) {
                throw new EstatFinalNoOK(status, "Rebut estat CANCELED: S'ha cancel·lat el procés de firmat.");

            } else if (status == getSTATUSFINALOK()) {

                System.out.println(" ===== RESULTAT  =========");

                {
                    System.out.println(" ---- Signature [ " + fullResults.getSignID() + " ]");

                    System.err.println("  RESULT: OK");
                    FirmaSimpleFile fsf = fullResults.getSignedFile();
                    FileOutputStream fos = new FileOutputStream(fsf.getNom());
                    fos.write(fsf.getData());
                    fos.flush();
                    fos.close();
                    System.out.println("  RESULT: Fitxer signat guardat en '" + fsf.getNom() + "'");
                    System.out.println(fullResults.getSignedFileInfo().toString());

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

        FirmaSimpleFile fileToUpgrade = llegirFitxer("src/main/resources/hola-signed.pdf", "application/pdf");

        internalTestUpgrade(PROFILE_PADES_PROPERTY, fileToUpgrade, null, "hola-signed-upgraded.pdf", testName,
                expectedError);

    }

    protected FirmaSimpleUpgradeResponse internalTestUpgrade(final String perfilProperty, FirmaSimpleFile fileToUpgrade,
            FirmaSimpleFile documentDetached, String upgradedFileName, String testName, Integer expectedError)
            throws Exception, ApiException {

        System.out.println("============================ " + testName + " ============================");
        try {

            FirmaEnServidorV1Api api = getApi();

            Properties prop = getConfigProperties();

            String perfil = prop.getProperty(perfilProperty);

            if (perfil == null || perfil.trim().isEmpty()) {
                avisPerPerfilBuit(PROFILE_PADES_PROPERTY);
                perfil = null;
            }

            FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest = new FirmaSimpleUpgradeRequest();

            firmaSimpleUpgradeRequest.setProfileCode(perfil);
            firmaSimpleUpgradeRequest.setDetachedDocument(documentDetached);
            firmaSimpleUpgradeRequest.setSignature(fileToUpgrade);

            String languageUI = prop.getProperty("languageUI", "ca");
            firmaSimpleUpgradeRequest.setLanguageUI(languageUI);

            FirmaSimpleUpgradeResponse upgradeResponse = api.upgradeSignature(firmaSimpleUpgradeRequest);

            System.out.println(upgradeResponse.getUpgradedFileInfo().toString());

            FirmaSimpleFile upgraded = upgradeResponse.getUpgradedFile();

            guardarFitxer(upgraded.getData(), upgradedFileName);

            return upgradeResponse;

        } catch (ApiException e) {
            checkExpectedError(expectedError, e);
            return null;
        }
    }

    @Override
    public FirmaEnServidorV1Api getApi() throws Exception {
        ApiClient client = getApiClient();
        FirmaEnServidorV1Api api = new FirmaEnServidorV1Api(client);
        return api;
    }

    @Override
    protected String getConfigPropertiesName() {
        return "apifirmaenservidor";
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

}
