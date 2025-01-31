package es.caib.portafib.apiinterna.client.firma.v1.example.api;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignatureResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleGetTransactionStatusResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignatureStatus;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignedFileInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleStatus;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleValidationInfo;

import org.jboss.logging.Logger;

import es.caib.portafib.apiinterna.client.firma.v1.model.AvailableProfilesRest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleAddFileToSignRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleCommonInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleCustodyInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleFile;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleFileInfoSignature;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleGetSignatureResultRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleStartTransactionRequest;
import es.caib.portafib.apiinterna.client.firma.v1.model.GetAvailableTypesOfDocumentsResponse;
import es.caib.portafib.apiinterna.client.firma.v1.api.FirmaWebV1Api;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;
import es.caib.portafib.commons.utils.Constants;

/**
 * API tests for FirmaWebV1Api
 */
public class FirmaWebV1ApiTest {

    protected Logger log = Logger.getLogger(getClass());

    public static final String PROFILE_WEB_PROPERTY = "PROFILE_WEB";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        FirmaWebV1ApiTest test = new FirmaWebV1ApiTest();
        try {

            test.signFileWebExample();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String signFileWebExample() throws Exception {
        String transactionID = null;
        Properties prop = loadProperties("./apifirmawebsimple.properties");
        FirmaWebV1Api api = null;
        try {
            //Es deifneix el port en el que s'enviara la peticio de firma
            int port = 1989 + (int) (Math.random() * 100.0);
            prop = getProperties();
            api = loadApiFirmaWeb(prop);

            //Perfil de firma definit al properties
            final String perfil = prop.getProperty(PROFILE_WEB_PROPERTY);

            //CommonInfo de la firma
            FirmaSimpleCommonInfo commonInfo = buildCommonInfo();

            //TransactionId
            transactionID = api.getTransactionID(commonInfo);

            //Funcio de firma i comprovacio de resultats
            String redirectUrl = internalSignFile(port, transactionID);

            return redirectUrl;

        } finally {
            if (api != null && transactionID != null) {
                try {
                    api.closeTransaction(transactionID);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    private String internalSignFile(int port, String transactionID) throws Exception {
        Properties prop = getProperties();
        FirmaWebV1Api api = loadApiFirmaWeb(prop);
        //Fitxers a firmar
        FirmaSimpleFileInfoSignature[] filesToSign = getFilesToSign();

        ArrayList<String> addFileToSignResponseList = new ArrayList<String>();
        for (int i = 0; i < filesToSign.length; i++) {
            System.out.println("Enviant firma[" + i + "]");
            FirmaSimpleAddFileToSignRequest addFileToSignRequest = new FirmaSimpleAddFileToSignRequest();
            addFileToSignRequest.setTransactionID(transactionID);
            addFileToSignRequest.setFileInfoSignature(filesToSign[i]);
            String addFileToSignResponse;
            addFileToSignResponse = api.addFileToSign(addFileToSignRequest);
            addFileToSignResponseList.add(addFileToSignResponse);

        }
        FirmaSimpleStartTransactionRequest startTransactionInfo = buildTransactionInfo(transactionID, port);

        System.out.println("--- Port => " + port);
        System.out.println("--- TransactionID => " + transactionID);

        String redirectUrl = "";
        redirectUrl = api.startTransaction(startTransactionInfo);

        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI(redirectUrl));
        } else {
            System.out.println("Per favor obri un Navegador i copia-li la URL anterior ...");
        }

        readFromSocket(port);

        
        testGetTransactionStatus(transactionID, api);
        
        return redirectUrl;
    }

    public void testGetAvailableTypesOfDocuments() throws ApiException {
        Properties prop = getProperties();
        FirmaWebV1Api api = loadApiFirmaWeb(prop);
        GetAvailableTypesOfDocumentsResponse result = api.getAvailableTypesOfDocuments("ca");
        System.out.println("AvailableTypeOfDocuments Results:");
        System.out.println(Arrays.toString(result.getGetAvailableTypesOfDocumentsResponse().toArray()));
    }

    protected static FirmaWebV1Api getApiFirmaWebSimple(Properties prop) throws Exception {

        ApiClient api = new ApiClient();
        api.setBasePath(prop.getProperty("endpoint"));
        api.setUsername(prop.getProperty("username"));
        api.setPassword(prop.getProperty("password"));

        return new FirmaWebV1Api(api);
    }

    protected static FirmaWebV1Api loadApiFirmaWeb(Properties prop) {
        //System.out.println("----------------- GET PROPERTIES");
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

    private FirmaSimpleCommonInfo firmaSimpleCommonInfoBuilder(String perfil, String languageUI, String username,
            String administrationID, String signerEmail) {

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

    private void testGetTransactionStatus(String transactionID, FirmaWebV1Api api ) throws ApiException {

        FirmaSimpleGetTransactionStatusResponse fullTransactionStatus;
        fullTransactionStatus = api.getTransactionStatus(transactionID);

        List<FirmaSimpleSignatureStatus> statusList = fullTransactionStatus.getSignaturesStatusList();

        for (int i = 0; i < statusList.size(); i++) {
            int status = fullTransactionStatus.getSignaturesStatusList().get(i).getStatus().getStatus();
            String statusMsg = "";
            switch (status) {
                case 0:
                    statusMsg = "Status-" + i + ": INITIALIZING = " + status;
                break;
                case 1:
                    statusMsg = "Status-" + i + ": IN_PROGRESS = " + status;
                break;
                case 2:
                    statusMsg = "Status-" + i + ": FINAL_OK = " + status;
                break;
                case -1:
                    statusMsg = "Status-" + i + ": FINAL_ERROR = " + status;
                break;
                case -2:
                    statusMsg = "Status-" + i + ": CANCELLED = " + status;
                break;
            }
            System.out.println(statusMsg);
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

    private FirmaSimpleSignatureResponse testGetSignatureResult(String transactionID, FirmaWebV1Api api) throws ApiException {

        FirmaSimpleGetTransactionStatusResponse fullTransactionStatus;
        fullTransactionStatus = api.getTransactionStatus(transactionID);

        List<FirmaSimpleSignatureStatus> ssl = fullTransactionStatus.getSignaturesStatusList();

        FirmaSimpleSignatureStatus signatureStatus = ssl.get(0);

        final String signID = signatureStatus.getSignID();
        System.out.println(" ---- Signature [ " + signID + " ]");

        FirmaSimpleGetSignatureResultRequest request = new FirmaSimpleGetSignatureResultRequest();
        request.setTransactionID(transactionID);
        request.setSignID(signID);

        FirmaSimpleSignatureResponse fssr = api.getSignatureResult(request);

        return fssr;

    }

    private static Properties loadProperties(String url) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(new File(url)));
        } catch (IOException e) {
            System.err.print("ERROR: No s'ha trobat el fitxer de properties: " + url);
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prop;
    }

    private FirmaSimpleCommonInfo buildCommonInfo() {

        Properties prop = getProperties();

        String perfil = prop.getProperty(PROFILE_WEB_PROPERTY);
        String languageUI = "ca";
        String username = prop.getProperty("signer.username");
        String administrationID = prop.getProperty("signer.administrationid");
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

    private FirmaSimpleFileInfoSignature[] getFilesToSign() throws Exception {
        Properties prop = getProperties();
        String files = prop.getProperty("files");
        String[] parts = files.split(",");
        FirmaSimpleFileInfoSignature[] filesToSign = new FirmaSimpleFileInfoSignature[parts.length];

        for (int i = 0; i < parts.length; i++) {

            String nom = prop.getProperty("file." + parts[i] + ".name");
            System.out.println("***FILE " + i + " Name = " + nom);
            String mime = prop.getProperty("file." + parts[i] + ".mime");

            System.out.println("Nom : ]" + nom + "[");
            System.out.println("Mime : ]" + mime + "[");

            FirmaSimpleFile fileToSign;
            fileToSign = UtilsV1ApiTest.getSimpleFileFromResource(nom, mime);

            // "hola_3mb.pdf",
            // "hola.pdf",
            // "application/pdf");

            FirmaSimpleFileInfoSignature fileInfoSignature = new FirmaSimpleFileInfoSignature();

            fileInfoSignature.setFileToSign(fileToSign);
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

        return filesToSign;
    }

    private FirmaSimpleStartTransactionRequest buildTransactionInfo(String transactionId, int port) {
        final String returnUrl = "http://localhost:" + port + "/returnurl/" + transactionId;
        final String view = "fullview";

        FirmaSimpleStartTransactionRequest startTransactionInfo = new FirmaSimpleStartTransactionRequest();
        startTransactionInfo.setTransactionID(transactionId);
        startTransactionInfo.setReturnUrl(returnUrl);
        startTransactionInfo.setView(view);

        return startTransactionInfo;
    }

    public static void readFromSocket(int port) throws IOException {

        ServerSocket serverSocket;
        serverSocket = new ServerSocket(port);

        System.err.println("Servidor escoltant al PORT: " + port);
        {
            Socket clientSocket;
            clientSocket = serverSocket.accept();

            System.err.println("Nou Client Connectat desde " + clientSocket.getRemoteSocketAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

            String s;
            System.err.println(" =========================== ");
            try {
                while ((s = in.readLine()) != null) {
                    System.out.println(s);
                    break;
                }
            } catch (IOException e) {
                System.err.print("ERROR: IOException llegint InputStream del resultat de la firma.");
                e.printStackTrace();
            }
            System.err.println(" =========================== ");

            out.println("HTTP/1.0 200 OK");
            out.println("Content-Type: text/html");
            out.println("\r\n");
            out.println(
                    "<html><body>OK (Revisi consola per saber l'estat final del proc&eacute;s de Firma)</body></html>");

            System.err.println("Connexio amb el client finalitzada.");
            out.flush();
            out.close();
            try {
                in.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.print("ERROR: Error a readFromSocket tancant el els recursos.");
                e.printStackTrace();
            }
        }

        serverSocket.close();
    }

    protected void processStatusFileOfSign(String transactionID,
            FirmaSimpleGetTransactionStatusResponse fullTransactionStatus)
            throws Exception, FileNotFoundException, IOException {
        Properties prop = getProperties();
        FirmaWebV1Api api = loadApiFirmaWeb(prop);

        List<FirmaSimpleSignatureStatus> ssl = fullTransactionStatus.getSignaturesStatusList();

        System.out.println(" ===== RESULTATS [" + ssl.size() + "] =========");

        for (FirmaSimpleSignatureStatus signatureStatus : ssl) {

            final String signID = signatureStatus.getSignID();

            System.out.println(" ---- Signature [ " + signID + " ]");

            FirmaSimpleStatus fss = signatureStatus.getStatus();

            int statusSign = fss.getStatus();

            switch (statusSign) {
                case 0: //fss.getSTATUSINITIALIZING(): // = 0;
                    System.err.println("  STATUS = " + statusSign + " (STATUS_INITIALIZING)");
                    System.err.println("  ESULT: Incoherent Status");
                break;

                case 1: //fss.getSTATUSINPROGRESS(): // = 1;
                    System.err.println("  STATUS = " + statusSign + " (STATUS_IN_PROGRESS)");
                    System.err.println("  RESULT: Incoherent Status");
                break;

                case -1: //fss.getSTATUSFINALERROR(): // = -1;
                    System.err.println("  STATUS = " + statusSign + " (STATUS_ERROR)");
                    System.err.println("  RESULT: Error en la firma: " + fss.getErrorMessage());
                break;

                case -2: //fss.getSTATUSCANCELLED(): // = -2;
                    System.err.println("  STATUS = " + statusSign + " (STATUS_CANCELLED)");
                    System.err.println("  RESULT: L'usuari ha cancel.lat la firma.");
                break;

                case 2: //fss.getSTATUSFINALOK(): // = 2;

                    FirmaSimpleGetSignatureResultRequest firmaSimplegetSignatureResultRequest = new FirmaSimpleGetSignatureResultRequest();
                    firmaSimplegetSignatureResultRequest.setTransactionID(transactionID);
                    firmaSimplegetSignatureResultRequest.setSignID(signID);

                    FirmaSimpleSignatureResponse fssr = api.getSignatureResult(firmaSimplegetSignatureResultRequest);
                    FirmaSimpleFile fsf = fssr.getSignedFile();

                    String postFix;
                    String signType = fssr.getSignedFileInfo().getSignType();
                    if (Constants.SIGN_TYPE_PADES.equals(signType)) {
                        postFix = "_signed.pdf";
                    } else if (Constants.SIGN_TYPE_CADES.equals(signType)) {
                        postFix = "_signed.csig";
                    } else if (Constants.SIGN_TYPE_XADES.equals(signType)) {
                        postFix = "_signed.xsig";
                    } else {
                        postFix = "_signed.unknown_extension_for_sign_type_" + signType;
                    }

                    final String outFile = signID + "_" + fsf.getNom() + postFix;

                    FileOutputStream fos = new FileOutputStream(outFile);
                    fos.write(fsf.getData());
                    fos.flush();
                    fos.close();

                    System.out.println("  RESULT: Fitxer signat guardat en '" + outFile + "'");
                    //System.gc();

                    System.out.println(firmaSimpleSignedFileInfoToString(fssr.getSignedFileInfo()));

                break;
            }

        } // Final for de fitxers firmats
    }

    public static String firmaSimpleSignedFileInfoToString(FirmaSimpleSignedFileInfo sfi) {
        StringBuilder str = new StringBuilder("  + INFORMACIO:");

        String operation;
        switch (sfi.getSignOperation()) {
            case Constants.SIGN_OPERATION_SIGN:
                operation = "FIRMA";
            break;
            case Constants.SIGN_OPERATION_COSIGN:
                operation = "COFIRMA";
            break;

            case Constants.SIGN_OPERATION_COUNTERSIGN:
                operation = "CONTRAFIRMA";
            break;

            default:
                operation = "DESCONEGUDA (" + sfi.getSignOperation() + ")";
        }
        str.append("\n").append("      * Operacio:\t").append(operation);

        str.append("\n").append("      * Tipus:\t").append(sfi.getSignType());

        str.append("\n").append("      * Algorisme:\t").append(sfi.getSignAlgorithm());

        str.append("\n").append("      * Mode:\t");
        if (sfi.getSignMode() == null) {
            str.append("NULL");
        } else {

            switch (sfi.getSignMode()) {
                case Constants.SIGN_MODE_ATTACHED_ENVELOPED:
                    str.append("Attached - Enveloped");
                break;
                case Constants.SIGN_MODE_ATTACHED_ENVELOPING:
                    str.append("Attached - Enveloping");
                break;
                case Constants.SIGN_MODE_DETACHED:
                    str.append("Detached");
                break;
                case Constants.SIGN_MODE_INTERNALLY_DETACHED:
                    str.append("Internally Detached");
                break;
                default:
                    str.append("DESCONEGUT (" + sfi.getSignMode() + ")");
            }
            /*str.append(
                    (sfi.getSignMode() == FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED) ? "Attached - Implicit"
                            : "Detached- Explicit"); */
        }

        String posicioTaulaDeFirmes;
        switch (sfi.getSignaturesTableLocation()) {

            case Constants.SIGNATURESTABLELOCATION_WITHOUT:
                posicioTaulaDeFirmes = "Sense taula de Firmes";
            break;
            case Constants.SIGNATURESTABLELOCATION_FIRSTPAGE:
                posicioTaulaDeFirmes = "Taula de Firmes en la primera pagina";
            break;
            case Constants.SIGNATURESTABLELOCATION_LASTPAGE:
                posicioTaulaDeFirmes = "Taula de Firmes en la darrera pagina";
            break;

            default:
                posicioTaulaDeFirmes = "Desconeguda(" + sfi.getSignaturesTableLocation() + ")";

        }
        str.append("\n").append("      * Posicio Taula De Firmes:\t").append(posicioTaulaDeFirmes);

        str.append("\n").append("      * Inclou Politica de Firmes(o sigui es EPES):\t")
                .append(sfi.getPolicyIncluded());
        str.append("\n").append("      * Inclou Segell de Temps:\t").append(sfi.getTimeStampIncluded());

        str.append("\n").append("      * eniTipoFirma:\t").append(sfi.getEniTipoFirma());
        str.append("\n").append("      * eniPerfilFirma:\t").append(sfi.getEniPerfilFirma());
        if (sfi.getSignerInfo() != null) {
            str.append("\n").append("      * Informacio del Firmant:\t");
            str.append("\n").append(sfi.getSignerInfo().toString());
        }

        FirmaSimpleCustodyInfo custody = sfi.getCustodyInfo();

        if (custody != null) {

            str.append("\n").append("  + CUSTODIA:");
            str.append("\n").append("      * custodyID: ").append(custody.getCustodyID());
            str.append("\n").append("      * CSV: ").append(custody.getCsv());
            str.append("\n").append("      * CSVValidationWeb: ").append(custody.getCsvValidationWeb());
            str.append("\n").append("      * ValidationFileUrl: ").append(custody.getValidationFileUrl());
            str.append("\n").append("      * CSVGenerationDefinition(eEMGDE17.4): ")
                    .append(custody.getCsvGenerationDefinition());
            str.append("\n").append("      * originalFileDirectURL: ").append(custody.getOriginalFileDirectURL());
            str.append("\n").append("      * printableFileDirectUrl: ").append(custody.getPrintableFileDirectUrl());
            str.append("\n").append("      * eniFileDirectUrl: ").append(custody.getEniFileDirectUrl());
        }

        FirmaSimpleValidationInfo validationInfo = sfi.getValidationInfo();
        if (validationInfo != null) {

            str.append("\n").append("  + VALIDACIO:");
            str.append("\n").append("      * CheckAdministrationIDOfSigner: ")
                    .append(null2Str(validationInfo.getCheckAdministrationIDOfSigner()));
            str.append("\n").append("      * CheckDocumentModifications: ")
                    .append(null2Str(validationInfo.getCheckDocumentModifications()));
            str.append("\n").append("      * CheckValidationSignature: ")
                    .append(null2Str(validationInfo.getCheckValidationSignature()));

            if (validationInfo.getNoCheckValidationReason() != null) {
                str.append("\n").append("      * No Validation reason: ")
                        .append(validationInfo.getNoCheckValidationReason());
            }

        }

        return str.toString();

    }

    public static String null2Str(Boolean b) {
        if (b == null) {
            return "-";
        }

        return b ? "SI" : "NO";
    }

    
}
