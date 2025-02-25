package es.caib.portafib.apiinterna.client.firma.v1.example.api;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignatureResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleGetTransactionStatusResponse;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignatureStatus;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignedFileInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleSignerInfo;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleStatus;
import es.caib.portafib.apiinterna.client.firma.v1.model.FirmaSimpleValidationInfo;

import org.jboss.logging.Logger;

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

/**
 * API tests for FirmaWebV1Api
 */
public class FirmaWebV1ApiTest extends AbstractV1ApiTest<FirmaWebV1Api> {

    protected static final FirmaSimpleSignedFileInfo C = new FirmaSimpleSignedFileInfo();

    protected Logger log = Logger.getLogger(getClass());

    public static void main(String[] args) throws FileNotFoundException, IOException {
        FirmaWebV1ApiTest test = new FirmaWebV1ApiTest();
        try {
            test.signPdfUsingPadesWithSyncWebExample();
        } catch (ApiException ae) {
            test.processApiException(ae, "Tests de Firma Web Sincrona");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    public String signPdfUsingPadesWithSyncWebExample() throws ApiException, Exception {
        String transactionID = null;
        Properties prop = getConfigProperties();
        FirmaWebV1Api api = null;
        try {
            //Es deifneix el port en el que s'enviara la peticio de firma
            api = getApi();
            String languageUI = getLanguageUI(prop);

            //CommonInfo de la firma
            {
                //Perfil de firma definit al properties
                final String perfil = prop.getProperty(PROFILE_PADES_PROPERTY);

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

                //TransactionId
                transactionID = api.getTransactionID(commonInfo);
                System.out.println("--- TransactionID => " + transactionID);
            }

            long tipusDocumentalID;
            {
                GetAvailableTypesOfDocumentsResponse documentTypes = api.getAvailableTypesOfDocuments(languageUI);
                // Recollim el primer tipus documental de la llista retornada
                tipusDocumentalID = documentTypes.getGetAvailableTypesOfDocumentsResponse().get(0).getDocumentType();
            }

            //Fitxers a firmar
            FirmaSimpleFileInfoSignature[] filesToSign = getFilesToSign(prop, tipusDocumentalID);

            ArrayList<String> addFileToSignResponseList = new ArrayList<String>();
            for (int i = 0; i < filesToSign.length; i++) {
                System.out.println("Enviant document a firmar [" + i + "]");
                FirmaSimpleAddFileToSignRequest addFileToSignRequest = new FirmaSimpleAddFileToSignRequest();
                addFileToSignRequest.setTransactionID(transactionID);
                addFileToSignRequest.setFileInfoSignature(filesToSign[i]);
                String addFileToSignResponse;
                addFileToSignResponse = api.addFileToSign(addFileToSignRequest);
                addFileToSignResponseList.add(addFileToSignResponse);

            }

            // Funcio de firma 
            final int port = 1989 + (int) (Math.random() * 100.0);
            final String returnUrl = "http://localhost:" + port + "/returnurl/" + transactionID;
            final String view = "fullview";

            FirmaSimpleStartTransactionRequest startTransactionInfo = new FirmaSimpleStartTransactionRequest();
            startTransactionInfo.setTransactionID(transactionID);
            startTransactionInfo.setReturnUrl(returnUrl);
            startTransactionInfo.setView(view);

            String redirectUrl = api.startTransaction(startTransactionInfo);

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(redirectUrl));
            } else {
                System.out.println("Per favor obri un Navegador i copia-li la URL anterior ...");
            }

            // Esperam a que POrtaFIB ens cridi a la URL de retorn 
            readFromSocket(port);

            // comprovacio de resultats
            FirmaSimpleGetTransactionStatusResponse fullTransactionStatus;
            fullTransactionStatus = api.getTransactionStatus(transactionID);

            /*
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
            */

            List<FirmaSimpleSignatureStatus> ssl = fullTransactionStatus.getSignaturesStatusList();

            for (FirmaSimpleSignatureStatus signatureStatus : ssl) {

                final String signID = signatureStatus.getSignID();

                System.out.println("======= Resultats Signature [ " + signID + " ] =======");

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

                        FirmaSimpleSignatureResponse fssr = api
                                .getSignatureResult(firmaSimplegetSignatureResultRequest);
                        FirmaSimpleFile fsf = fssr.getSignedFile();

                        String postFix;
                        String signType = fssr.getSignedFileInfo().getSignType();
                        // XYZ ZZZ Canviar comparacio amb String Values de Enumerat SignType (Encara no es generava be quan es va fer el test)
                        if (signType.equals("PAdES")) {
                            postFix = "_signed.pdf";
                        } else if (signType.equals("CAdES")) {
                            postFix = "_signed.csig";
                        } else if (signType.equals("XAdES")) {
                            postFix = "_signed.xsig";
                        } else {
                            postFix = "_signed.unknown_extension_for_sign_type_" + signType;
                        }

                        final String outFile = signID + "_" + fsf.getNom() + postFix;

                        FileOutputStream fos = new FileOutputStream(outFile);
                        fos.write(fsf.getData());
                        fos.flush();
                        fos.close();

                        System.out.println("  + Fitxer signat guardat en '" + outFile + "'");
                        System.out.println(firmaSimpleSignedFileInfoToString(fssr.getSignedFileInfo()));

                    break;
                }

            } // Final for de fitxers firmats

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

    protected FirmaSimpleFileInfoSignature[] getFilesToSign(Properties prop, long tipusDocumentalID) throws Exception {

        String files = prop.getProperty("files");
        String[] parts = files.split(",");
        FirmaSimpleFileInfoSignature[] filesToSign = new FirmaSimpleFileInfoSignature[parts.length];

        for (int i = 0; i < parts.length; i++) {

            String nom = prop.getProperty("file." + parts[i] + ".name");
            System.out.println("*** FILE[" + parts[i] + "]");
            System.out.println("    Name = " + nom);
            String mime = prop.getProperty("file." + parts[i] + ".mime");

            System.out.println("    Mime: ]" + mime + "[");

            FirmaSimpleFile fileToSign = llegirFitxer(nom, mime);
            System.out.println("    Mida: " + fileToSign.getData().length + " bytes");

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
            String languageSign = getLanguageUI(prop);
            fileInfoSignature.setLanguageSign(languageSign);

            fileInfoSignature.setDocumentType(tipusDocumentalID);

            filesToSign[i] = fileInfoSignature;
        }

        return filesToSign;
    }

    public static void readFromSocket(int port) throws IOException {

        ServerSocket serverSocket;
        serverSocket = new ServerSocket(port);

        System.out.println("Servidor escoltant al PORT: " + port);
        {
            Socket clientSocket;
            clientSocket = serverSocket.accept();

            System.out.println("Nou Client Connectat desde " + clientSocket.getRemoteSocketAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

            String s;
            System.out.println(" =========================== ");
            try {
                while ((s = in.readLine()) != null) {
                    System.out.println(s);
                    break;
                }
            } catch (IOException e) {
                System.err.print("ERROR: IOException llegint InputStream del resultat de la firma.");
                e.printStackTrace();
            }
            System.out.println(" =========================== ");

            out.println("HTTP/1.0 200 OK");
            out.println("Content-Type: text/html");
            out.println();
            out.println("<html><body>" + "OK (Revisi consola per saber l'estat final del proc&eacute;s de Firma)"
                    + "</body></html>");

            System.out.println("Connexio amb el client finalitzada.");
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

    protected String firmaSimpleSignedFileInfoToString(FirmaSimpleSignedFileInfo sfi) {
        StringBuilder str = new StringBuilder("  + INFORMACIO:");

        String operation;
        Integer signOperation = sfi.getSignOperation();
        if (signOperation == null) {
            operation = " -NULL- ";
        } else if (signOperation.equals(C.getSIGNOPERATIONSIGN())) {
            operation = "FIRMA";
        } else if (signOperation.equals(C.getSIGNOPERATIONCOSIGN())) {
            operation = "COFIRMA";
        } else if (signOperation.equals(C.getSIGNOPERATIONCOUNTERSIGN())) {
            operation = "CONTRAFIRMA";
        } else {
            operation = "DESCONEGUDA (" + signOperation + ")";
        }
        str.append("\n").append("      * Operacio:\t").append(operation);

        str.append("\n").append("      * Tipus:\t").append(sfi.getSignType());

        str.append("\n").append("      * Algorisme:\t").append(sfi.getSignAlgorithm());

        str.append("\n").append("      * Mode:\t");
        Integer signMode = sfi.getSignMode();
        if (signMode == null) {
            str.append("NULL");
        } else if (signMode.equals(C.getSIGNMODEATTACHEDENVELOPED())) {
            str.append("Attached - Enveloped");
        } else if (signMode.equals(C.getSIGNMODEATTACHEDENVELOPING())) {
            str.append("Attached - Enveloping");
        } else if (signMode.equals(C.getSIGNMODEDETACHED())) {
            str.append("Detached");
        } else if (signMode.equals(C.getSIGNMODEINTERNALLYDETACHED())) {
            str.append("Internally Detached");
        } else {
            str.append("DESCONEGUT (" + signMode + ")");
        }

        {
            String posicioTaulaDeFirmes;
            Integer signaturesTableLocation = sfi.getSignaturesTableLocation();
            if (signaturesTableLocation == null) {
                posicioTaulaDeFirmes = " -NULL- ";
            } else if (signaturesTableLocation.equals(C.getSIGNATURESTABLELOCATIONWITHOUT())) {
                posicioTaulaDeFirmes = "Sense taula de Firmes";
            } else if (signaturesTableLocation.equals(C.getSIGNATURESTABLELOCATIONFIRSTPAGE())) {
                posicioTaulaDeFirmes = "Taula de Firmes en la primera pagina";
            } else if (signaturesTableLocation.equals(C.getSIGNATURESTABLELOCATIONLASTPAGE())) {
                posicioTaulaDeFirmes = "Taula de Firmes en la darrera pagina";
            } else {
                posicioTaulaDeFirmes = "Desconeguda(" + sfi.getSignaturesTableLocation() + ")";
            }
            str.append("\n").append("      * Posicio Taula De Firmes:\t").append(posicioTaulaDeFirmes);
        }
        str.append("\n").append("      * Inclou Politica de Firmes(o sigui es EPES):\t")
                .append(sfi.getPolicyIncluded());
        str.append("\n").append("      * Inclou Segell de Temps:\t").append(sfi.getTimeStampIncluded());

        str.append("\n").append("      * eniTipoFirma:\t").append(sfi.getEniTipoFirma());
        str.append("\n").append("      * eniPerfilFirma:\t").append(sfi.getEniPerfilFirma());
        FirmaSimpleSignerInfo fssfi = sfi.getSignerInfo();
        str.append("\n").append("      * Informacio del Firmant:\t");
        if (fssfi == null) {
            str.append(" -- NO DISPONIBLE --\n");
        } else {
            str.append(fssfi.toString().replace("class FirmaSimpleSignerInfo {", "").replace("    ", "          - ")
                    .replace("}", ""));
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

    @Override
    protected FirmaWebV1Api getApi() throws Exception {
        ApiClient client = getApiClient();
        return new FirmaWebV1Api(client);
    }

    @Override
    protected String getConfigPropertiesName() {
        return "apifirmawebsimple";
    }

}
