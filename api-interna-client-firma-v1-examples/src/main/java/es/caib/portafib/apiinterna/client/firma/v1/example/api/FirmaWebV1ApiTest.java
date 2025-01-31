package es.caib.portafib.apiinterna.client.firma.v1.example.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignatureResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleGetTransactionStatusResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignatureStatus;

import org.jboss.logging.Logger;

import es.caib.portafib.apiinterna.client.firma.v1.model.AvailableProfilesRest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleAddFileToSignRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleCommonInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleFile;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleFileInfoSignature;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleGetSignatureResultRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleStartTransactionRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.GetAvailableTypesOfDocumentsResponse;
import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaWebV1Api;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;

/**
 * API tests for FirmaWebV1Api
 */
public class FirmaWebV1ApiTest {
    
    protected Logger log = Logger.getLogger(getClass());


    public static final String PROFILE_WEB_PROPERTY = "PROFILE_WEB";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        FirmaWebV1ApiTest test = new FirmaWebV1ApiTest();
        try {
            
            FirmaWebV1Api api = test.getApi();
            
            Properties prop = new Properties();

            prop.load(new FileInputStream(new File("./apifirmawebsimple.properties")));
            
            String transactionId = test.testGetTransactionId(api, prop);
            
            test.testGetAvailableProfiles();
            
            ArrayList<String> filesToSignResponseList = test.testAddFileToSign(api, prop);
            
            test.testGetAvailableTypesOfDocuments(api, prop);
            
            test.testStartTransaction(api, prop);
            
            test.testGetTransactionStatus(api, prop);
            
            test.testGetSignatureResult(api, prop);
            
            //test.testCloseTransaction();
            
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    public void testGetAvailableProfiles() throws Exception{
        String language = "ca";
        String expectedError = null;
        
        // Comu
        //internalTestPerfilsDeFirmaList(language, expectedError);

    }
    
    public ArrayList<String> testAddFileToSign(FirmaWebV1Api api, Properties prop) throws Exception {

        String files = prop.getProperty("files");

        String[] parts = files.split(",");

        
        FirmaSimpleFileInfoSignature[] filesToSign = new FirmaSimpleFileInfoSignature[parts.length];
        
        final String perfil = prop.getProperty(PROFILE_WEB_PROPERTY);
        
        final String languageUI = "ca";
        final String username = prop.getProperty("signer.username");
        final String administrationID = prop.getProperty("signer.administrationid");
        String signerEmail = prop.getProperty("signer.email");

        System.out.println("Signer.Username = |" + username + "|");
        System.out.println("Signer.administrationid = |" + administrationID + "|");
        System.out.println("Signer.email = |" + signerEmail + "|");

        
        FirmaSimpleCommonInfo commonInfo = new FirmaSimpleCommonInfo();
        commonInfo.setSignProfile(perfil);
        commonInfo.setLanguageUI(languageUI);
        commonInfo.setUsername(username);
        commonInfo.setAdministrationID(administrationID);
        commonInfo.setSignerEmail(signerEmail);
        
        String transactionID = api.getTransactionID(commonInfo);


        for (int i = 0; i < parts.length; i++) {

            String nom = prop.getProperty("file." + parts[i] + ".name");
            String mime = prop.getProperty("file." + parts[i] + ".mime");

            System.out.println("Nom : ]" + nom + "[");
            System.out.println("Mime : ]" + mime + "[");

            FirmaSimpleFile fileToSign = AbstractV1ApiTest.llegirFitxer(nom, mime);
            // "hola_3mb.pdf",
            // "hola.pdf",
            // "application/pdf");

            FirmaSimpleFileInfoSignature fileInfoSignature = new FirmaSimpleFileInfoSignature();

            String signID = parts[i];
            fileInfoSignature.setSignID(signID);
            String name = fileToSign.getNom();
            fileInfoSignature.setName(name);
            String reason = "Per aprovar pressuposts - " + parts[i];
            fileInfoSignature.setReason(reason);
            String location = "Palma";
            fileInfoSignature.setLocation(location);

            int signNumber = 1;
            fileInfoSignature.setSignNumber(signNumber);
            String languageSign = "ca";
            fileInfoSignature.setLanguageSign(languageSign);
            long tipusDocumentalID = 99; // =TD99
            fileInfoSignature.setDocumentType(tipusDocumentalID);
           
            filesToSign[i] = fileInfoSignature;
        }
        ArrayList<String> addFileToSignResponseList = new ArrayList<String>();
        for (int i = 0; i < filesToSign.length; i++) {
            System.out.println("Enviant firma[" + i + "]");
            FirmaSimpleAddFileToSignRequest addFileToSignRequest = new FirmaSimpleAddFileToSignRequest();
            addFileToSignRequest.setTransactionID(transactionID);
            addFileToSignRequest.setFileInfoSignature(filesToSign[i]);
            addFileToSignResponseList.add(api.addFileToSign(addFileToSignRequest));
            
        }
        
        return addFileToSignResponseList;
        
    }
    
    public String testGetTransactionId(FirmaWebV1Api api, Properties prop) throws Exception {
        
        try {
            final String perfil = prop.getProperty(PROFILE_WEB_PROPERTY);
            
            final String languageUI = "ca";
            final String username = prop.getProperty("signer.username");
            final String administrationID = prop.getProperty("signer.administrationid");
            String signerEmail = prop.getProperty("signer.email");

            System.out.println("Signer.Username = |" + username + "|");
            System.out.println("Signer.administrationid = |" + administrationID + "|");
            System.out.println("Signer.email = |" + signerEmail + "|");

            
            FirmaSimpleCommonInfo commonInfo = new FirmaSimpleCommonInfo();
            commonInfo.setSignProfile(perfil);
            commonInfo.setLanguageUI(languageUI);
            commonInfo.setUsername(username);
            commonInfo.setAdministrationID(administrationID);
            commonInfo.setSignerEmail(signerEmail);
            
            api = getApi();

            // Enviam la part comu de la transacció
            String transactionID = api.getTransactionID(commonInfo);
            
            System.out.println("TransactionID => "+transactionID);
            
            return transactionID;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            
            System.err.println(
                    "No s'ha trobat el fitxer a signar.");
        } catch (IOException e) {
            
            e.printStackTrace();
            
            System.err.println(
                    "Error: IOException");
        } catch (Exception e) {
            e.printStackTrace();
            
            System.err.println(
                    "Error: Exception");
        }
        throw new Exception("Error: Response Null");
    }
    
    public void testGetAvailableTypesOfDocuments(FirmaWebV1Api api, Properties prop) throws ApiException {
        GetAvailableTypesOfDocumentsResponse result = api.getAvailableTypesOfDocuments("ca");
        log.info("AvailableTypeOfDocuments Results:");
        log.info(Arrays.toString(result.getGetAvailableTypesOfDocumentsResponse().toArray()));
    }
    
    
    public void testStartTransaction(FirmaWebV1Api api, Properties prop) throws Exception {
        
        final String perfil = prop.getProperty(PROFILE_WEB_PROPERTY);

        if (perfil == null) {
            System.err.println("La propietat " + PROFILE_WEB_PROPERTY
                    + " està buida. Com l'usuari aplicació tengui més d'un perfil definit no es podrà executar.");
        }
        
        final String languageUI = "ca";
        final String username = prop.getProperty("signer.username");
        final String administrationID = prop.getProperty("signer.administrationid");
        String signerEmail = prop.getProperty("signer.email");

        System.out.println("Signer.Username = |" + username + "|");
        System.out.println("Signer.administrationid = |" + administrationID + "|");
        System.out.println("Signer.email = |" + signerEmail + "|");
        
        
        FirmaSimpleCommonInfo commonInfo = new FirmaSimpleCommonInfo();
        commonInfo.setSignProfile(perfil);
        commonInfo.setLanguageUI(languageUI);
        commonInfo.setUsername(username);
        commonInfo.setAdministrationID(administrationID);
        commonInfo.setSignerEmail(signerEmail);

        api = getApiFirmaWebSimple(prop);
        
        

        System.out.println(Arrays.toString(api.getAvailableTypesOfDocuments("ca").getGetAvailableTypesOfDocumentsResponse().toArray()));

        
        String transactionID = api.getTransactionID(commonInfo);

        
        transactionID = api.getTransactionID(commonInfo);

        System.out.println("languageUI = |" + languageUI + "|");

        System.out.println("TransactionID = |" + transactionID + "|");
        
        String files = prop.getProperty("files");
        String[] parts = files.split(",");

        
        FirmaSimpleFileInfoSignature[] filesToSign = new FirmaSimpleFileInfoSignature[parts.length];

        for (int i = 0; i < parts.length; i++) {

            String nom = prop.getProperty("file." + parts[i] + ".name");
            String mime = prop.getProperty("file." + parts[i] + ".mime");

            System.out.println("Nom : ]" + nom + "[");
            System.out.println("Mime : ]" + mime + "[");

            FirmaSimpleFile fileToSign = AbstractV1ApiTest.llegirFitxer(nom, mime);
            // "hola_3mb.pdf",
            // "hola.pdf",
            // "application/pdf");

            FirmaSimpleFileInfoSignature fileInfoSignature = new FirmaSimpleFileInfoSignature();

            String signID = parts[i];
            fileInfoSignature.setSignID(signID);
            String name = fileToSign.getNom();
            fileInfoSignature.setName(name);
            String reason = "Per aprovar pressuposts - " + parts[i];
            fileInfoSignature.setReason(reason);
            String location = "Palma";
            fileInfoSignature.setLocation(location);

            int signNumber = 1;
            fileInfoSignature.setSignNumber(signNumber);
            String languageSign = "ca";
            fileInfoSignature.setLanguageSign(languageSign);
            long tipusDocumentalID = 99; // =TD99
            fileInfoSignature.setDocumentType(tipusDocumentalID);
           
            filesToSign[i] = fileInfoSignature;
        }

        for (int i = 0; i < filesToSign.length; i++) {
            System.out.println("Enviant firma[" + i + "]");
            FirmaSimpleAddFileToSignRequest fileToSignRequest = new FirmaSimpleAddFileToSignRequest();
            fileToSignRequest.setTransactionID(transactionID);
            fileToSignRequest.setFileInfoSignature(filesToSign[i]);
            api.addFileToSign(fileToSignRequest);
        }
        
        int port = 1989 + (int) (Math.random() * 100.0);
        final String returnUrl = "http://localhost:" + port + "/returnurl/" + transactionID;
        //final String view = FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN;
        final String view = "fullview";

        
        FirmaSimpleStartTransactionRequest startTransactionInfo = new FirmaSimpleStartTransactionRequest();
        startTransactionInfo.setTransactionID(transactionID);
        startTransactionInfo.setReturnUrl(returnUrl);
        startTransactionInfo.setView(view);
        
        String redirectUrl = api.startTransaction(startTransactionInfo);

    }
    
     private AvailableProfilesRest internalTestPerfilsDeFirmaList(String language, String expectedError)
            throws Exception {
        FirmaWebV1Api api;
        api = getApi();

        AvailableProfilesRest response = api.getAvailableProfiles(language);
        if (expectedError != null) {
            log.error("internalTestPerfilsDeFirmaList: S'espera un error i la cridada ha funcionat.");
        }
        System.out.println(response.toString());
        return response;
    }
    
    protected static FirmaWebV1Api getApiFirmaWebSimple(Properties prop) throws Exception {
        
        ApiClient api = new ApiClient();
        api.setBasePath(prop.getProperty("endpoint"));
        api.setUsername(prop.getProperty("username"));
        api.setPassword(prop.getProperty("password"));
        
        return new FirmaWebV1Api(api);
    }
    
    protected FirmaWebV1Api getApi() throws Exception {
        System.out.println("----------------- GET PROPERTIES");

        Properties prop = new Properties();
        File f = new File("apifirmawebsimple.properties");
        if (!f.exists()) {
            System.out.println("----------------- PROPERTIES NOT FOUND");
            log.info("----------------- PROPERTIES NOT FOUND");
            return null;
        }
        prop.load(new FileInputStream(f));

        String basePath = prop.getProperty("basePath");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        System.out.println("----------------- GET basePath = " + basePath);
        System.out.println("----------------- GET username = " + username);
        System.out.println("----------------- GET password = " + password);

        ApiClient client = new ApiClient();
        client.setBasePath(basePath);
        client.setUsername(username);
        client.setPassword(password);

        FirmaWebV1Api api = new FirmaWebV1Api(client);
        return api;
    }
    
    
    private FirmaSimpleCommonInfo firmaSimpleCommonInfoBuilder(String perfil, String languageUI, String username, String administrationID, String signerEmail) {
        FirmaSimpleCommonInfo commonInfo = new FirmaSimpleCommonInfo();
        commonInfo.setSignProfile(perfil);
        commonInfo.setLanguageUI(languageUI);
        commonInfo.setUsername(username);
        commonInfo.setAdministrationID(administrationID);
        commonInfo.setSignerEmail(signerEmail);
        return commonInfo;
    }
    
    
    private void testGetTransactionStatus(FirmaWebV1Api api, Properties prop) throws ApiException {
        
        final String perfil = prop.getProperty(PROFILE_WEB_PROPERTY);

        if (perfil == null) {
            System.err.println("La propietat " + PROFILE_WEB_PROPERTY
                    + " està buida. Com l'usuari aplicació tengui més d'un perfil definit no es podrà executar.");
        }
        final String languageUI = "ca";
        final String username = prop.getProperty("signer.username");
        final String administrationID = prop.getProperty("signer.administrationid");
        String signerEmail = prop.getProperty("signer.email");

        System.out.println("Signer.Username = |" + username + "|");
        System.out.println("Signer.administrationid = |" + administrationID + "|");
        System.out.println("Signer.email = |" + signerEmail + "|");
        
        FirmaSimpleCommonInfo commonInfo = firmaSimpleCommonInfoBuilder(perfil, languageUI, username, administrationID, signerEmail);

        String transactionID = api.getTransactionID(commonInfo);
        
        FirmaSimpleGetTransactionStatusResponse fullTransactionStatus;
        FirmaSimpleStartTransactionRequest request = new FirmaSimpleStartTransactionRequest();
        request.setTransactionID(transactionID);
        fullTransactionStatus = api.getTransactionStatus(request);
        
        List<FirmaSimpleSignatureStatus> statusList = fullTransactionStatus.getSignaturesStatusList();
        

        for (int i=0; i<statusList.size(); i++) {
            int status = fullTransactionStatus.getSignaturesStatusList().get(i).getStatus().getStatus();
            String statusMsg;
            switch (status) {
                case 0:
                    statusMsg = "Status-"+i+": INITIALIZING = "+ status;
                    break;
                case 1:
                    statusMsg = "Status-"+i+": IN_PROGRESS = "+ status;
                    break;
                case 2:
                    statusMsg = "Status-"+i+": FINAL_OK = "+ status;
                    break;
                case -1:
                    statusMsg = "Status-"+i+": FINAL_ERROR = "+ status;
                    break;
                case -2:
                    statusMsg = "Status-"+i+": CANCELLED = "+ status;
                    break;
            }
            
        }
        
        
        
    }
    
    private Properties getProperties() {
        Properties prop = new Properties();
        File f = new File("apifirmawebsimple.properties");
        if (!f.exists()) {
            System.out.println("----------------- PROPERTIES NOT FOUND");
            log.info("----------------- PROPERTIES NOT FOUND");
            return null;
        }
        try {
            prop.load(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            log.error("ERROR: Fitxer Properties not found.");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("ERROR: IOException llegint Properties.");
            e.printStackTrace();
        }
        return prop;
    }
    
    private void testGetSignatureResult(FirmaWebV1Api api, Properties prop) throws ApiException {
        
        FirmaSimpleCommonInfo commonInfo = firmaSimpleCommonInfoBuilder();
        
        String transactionID = api.getTransactionID(commonInfo);

        
        
        
        FirmaSimpleGetTransactionStatusResponse fullTransactionStatus;
        FirmaSimpleStartTransactionRequest startTransactionrequest = new FirmaSimpleStartTransactionRequest();
        startTransactionrequest.setTransactionID(transactionID);
        fullTransactionStatus = api.getTransactionStatus(startTransactionrequest);
        
        List<FirmaSimpleSignatureStatus> ssl = fullTransactionStatus.getSignaturesStatusList();

        FirmaSimpleSignatureStatus signatureStatus = ssl.get(0);
                
        
        final String signID = signatureStatus.getSignID();
        System.out.println(" ---- Signature [ " + signID + " ]");
        
        
        FirmaSimpleGetSignatureResultRequest request = new FirmaSimpleGetSignatureResultRequest();
        request.setTransactionID(transactionID);
        request.setSignID(signID);
        
        FirmaSimpleSignatureResponse fssr = api.getSignatureResult(request);
        
        
    }
    
    private FirmaSimpleCommonInfo firmaSimpleCommonInfoBuilder () {
        
        Properties prop = getProperties();
        
        
        final String perfil = prop.getProperty(PROFILE_WEB_PROPERTY);

        if (perfil == null) {
            System.err.println("La propietat " + PROFILE_WEB_PROPERTY
                    + " està buida. Com l'usuari aplicació tengui més d'un perfil definit no es podrà executar.");
        }
        
        final String languageUI = "ca";
        final String username = prop.getProperty("signer.username");
        final String administrationID = prop.getProperty("signer.administrationid");
        String signerEmail = prop.getProperty("signer.email");

        System.out.println("Signer.Username = |" + username + "|");
        System.out.println("Signer.administrationid = |" + administrationID + "|");
        System.out.println("Signer.email = |" + signerEmail + "|");
        
        
        FirmaSimpleCommonInfo commonInfo = new FirmaSimpleCommonInfo();
        commonInfo.setSignProfile(perfil);
        commonInfo.setLanguageUI(languageUI);
        commonInfo.setUsername(username);
        commonInfo.setAdministrationID(administrationID);
        commonInfo.setSignerEmail(signerEmail);
        
        return commonInfo;
    }
    
}
