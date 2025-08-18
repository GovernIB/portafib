package es.caib.portafib.apiinterna.client.signature.v1.example.api;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureResponse;
import es.caib.portafib.apiinterna.client.signature.v1.model.TransactionStatusResponse;
import es.caib.portafib.apiinterna.client.signature.v1.model.KeyValue;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignatureStatus;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignedFileInfo;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignerInfo;
import es.caib.portafib.apiinterna.client.signature.v1.model.ProcessStatus;
import es.caib.portafib.apiinterna.client.signature.v1.model.Profile;
import es.caib.portafib.apiinterna.client.signature.v1.model.ValidationInfo;
import es.caib.portafib.apiinterna.client.signature.v1.model.ViewConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.AddFileToSignRequest;
import es.caib.portafib.apiinterna.client.signature.v1.model.CommonInfo;
import es.caib.portafib.apiinterna.client.signature.v1.model.CustodyInfo;
import es.caib.portafib.apiinterna.client.signature.v1.model.Document;
import es.caib.portafib.apiinterna.client.signature.v1.model.DocumentaryType;
import es.caib.portafib.apiinterna.client.signature.v1.model.FileInfoSignature;
import es.caib.portafib.apiinterna.client.signature.v1.model.StartTransactionRequest;
import es.caib.portafib.apiinterna.client.signature.v1.model.StatusConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignModeConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignOperationConstants;
import es.caib.portafib.apiinterna.client.signature.v1.model.SignaturesTableLocationConstants;
import es.caib.portafib.apiinterna.client.signature.v1.api.DirectSignatureOnWebV1Api;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.signature.v1.services.ApiException;

/**
 * API tests for FirmaWebV1Api
 * 
 * @author anadal
 * @author fbosch
 * 2 may 2025 12:16:15
 */
public class FirmaDirecteWebV1ApiTest extends AbstractV1ApiTest<DirectSignatureOnWebV1Api> {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        FirmaDirecteWebV1ApiTest test = new FirmaDirecteWebV1ApiTest();
        try {

            //test.callCommonTests();

            test.signPdfUsingPadesWithSyncWebExample();

        } catch (ApiException ae) {
            test.processApiException(ae, "Tests de Firma Web Sincrona", true);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    public void signPdfUsingPadesWithSyncWebExample() throws ApiException, Exception {
        String transactionID = null;
        Properties props = getConfigProperties();
        DirectSignatureOnWebV1Api api = null;
        try {
            
            String useiframe = props.getProperty("useiframe");
            final boolean showInIframe = "true".equalsIgnoreCase(useiframe); 
            
            //Es defineix el port en el que s'enviara la peticio de firma
            api = getApi();
            String languageUI = getLanguageUI(props);

            //CommonInfo de la firma
            {
                //Perfil de firma definit al properties
                final String perfil = props.getProperty(PROFILE_PADES_PROPERTY);

                String username = props.getProperty("signer.username");
                String administrationID = props.getProperty("signer.administrationid");
                String signerEmail = props.getProperty("signer.email");

                System.out.println("Signer.Username = |" + username + "|");
                System.out.println("Signer.administrationid = |" + administrationID + "|");
                System.out.println("Signer.email = |" + signerEmail + "|");

                CommonInfo commonInfo = new CommonInfo();
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
                Set<DocumentaryType> documentTypes = api.getDocumentaryTypes(languageUI);
                // Recollim el primer tipus documental de la llista retornada
                tipusDocumentalID = documentTypes.iterator().next().getDocumentType();
            }

            //Fitxers a firmar
            FileInfoSignature[] filesToSign = getFilesToSign(props, tipusDocumentalID);

            ArrayList<String> addFileToSignResponseList = new ArrayList<String>();
            for (int i = 0; i < filesToSign.length; i++) {
                System.out.println("Enviant document a firmar [" + i + "]");
                AddFileToSignRequest addFileToSignRequest = new AddFileToSignRequest();
                addFileToSignRequest.setTransactionID(transactionID);
                addFileToSignRequest.setFileInfoSignature(filesToSign[i]);
                String addFileToSignResponse;
                addFileToSignResponse = api.addFileToSign(addFileToSignRequest);
                addFileToSignResponseList.add(addFileToSignResponse);

            }

            // Funcio de firma
            
            // Servidor TEMPORAL
            String host = Inet4Address.getLocalHost().getHostAddress();            
            final int port = 1900; // 1989 + (int) (Math.random() * 100.0);
            final String returnUrl = "http://" + host + ":" + port + "/returnurl/" + transactionID;
            final String view = ViewConstants.VIEW_FULLSCREEN.getValue();

            StartTransactionRequest startTransactionInfo = new StartTransactionRequest();
            startTransactionInfo.setTransactionID(transactionID);
            startTransactionInfo.setReturnUrl(returnUrl);
            startTransactionInfo.setView(view);

            String redirectUrl = api.startTransaction(startTransactionInfo);

            if (showInIframe) {
                final String iframeUrl = "http://" + host + ":" + (port+1) + "/iframe/";
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new URI(iframeUrl));
                } else {
                    System.out.println("Per favor obri un Navegador i copia-li la URL " + iframeUrl);
                }
                String htmlIframe = "<div style=\"\n"
                        + "        background-color: red;\n"
                        + "        width: 100%;\n"
                        + "        height: 100%;\n"
                        + "        display: flex;\n"
                        + "        justify-content: center;\n"
                        + "        align-items: center;\n"
                        + "    \">\n"
                        + "        <div style=\"width: calc(100% - 100px); height: calc(100% - 100px);\">\n"
                        + "            Iframe atacant URL: " + iframeUrl + "...<br/>\n"
                        + "            <iframe \n"
                        + "                src=\"" + redirectUrl + "\" \n"
                        + "                style=\"width: 100%; height: 100%; border: none;\"\n"
                        + "            ></iframe>\n"
                        + "        </div>\n"
                        + "    </div>";
                
                readFromSocket(port + 1, htmlIframe);
                System.out.println("Algun navegador ha llegit html amb iframe ...");
            } else {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new URI(redirectUrl));
                } else {
                    System.out.println("Per favor obri un Navegador i copia-li la URL " + redirectUrl);
                }
            }

            // Esperam a que POrtaFIB ens cridi a la URL de retorn 
            readFromSocket(port, "OK (Revisi consola per saber l'estat final del proc&eacute;s de Firma)" );

            // Comprovacio de resultats
            TransactionStatusResponse fullTransactionStatus;
            fullTransactionStatus = api.getTransactionStatus(transactionID);

            System.out.println(fullTransactionStatus.getSignPlugin());

            ProcessStatus processStatus = fullTransactionStatus.getTransactionStatus();

            switch (StatusConstants.fromValue(processStatus.getStatus())) {
                case STATUS_INITIALIZING: //fss.getSTATUSINITIALIZING(): // = 0;
                    System.err.println("  STATUS TRANSACCIO = " + processStatus.getStatus() + " (STATUS_INITIALIZING)");
                    System.err.println(
                            "  RESULT: Incoherent Status (Indica que hi ha hagut una mala gestió en el procés de firma)");
                break;

                case STATUS_IN_PROGRESS: //fss.getSTATUSINPROGRESS(): // = 1;
                    System.err.println("  STATUS TRANSACCIO= " + processStatus.getStatus() + " (STATUS_IN_PROGRESS)");
                    System.err.println(
                            "  RESULT: Incoherent Status (Indica que hi ha hagut una mala gestió en el procés de firma)");
                break;

                case STATUS_FINAL_ERROR: //fss.getSTATUSFINALERROR(): // = -1;
                    System.err.println("  STATUS TRANSACCIO= " + processStatus.getStatus() + " (STATUS_ERROR)");
                    System.err.println("  RESULT: Error en la firma: " + processStatus.getErrorMessage());
                break;

                case STATUS_CANCELLED: //fss.getSTATUSCANCELLED(): // = -2;
                    System.err.println("  STATUS TRANSACCIO= " + processStatus.getStatus() + " (STATUS_CANCELLED)");
                    if (processStatus.getErrorMessage() != null) {
                        System.err.println("  RESULT: " +  processStatus.getErrorMessage());
                    } else {
                        System.err.println("  RESULT: L'usuari ha cancel.lat el procés de firma.");
                    }
                break;

                case STATUS_FINAL_OK:
                    System.err.println("  STATUS TRANSACCIO= " + processStatus.getStatus() + " (STATUS_FINAL_OK)");

                { // PROCESSAR CADA FIRMA !!!!!

                    List<SignatureStatus> ssl = fullTransactionStatus.getSignaturesStatusList();

                    for (SignatureStatus signatureStatus : ssl) {

                        final String signID = signatureStatus.getSignID();

                        System.out.println("======= Resultats Signature [ " + signID + " ] =======");

                        ProcessStatus fss = signatureStatus.getStatus();

                        int status = fss.getStatus(); //fss.getSTATUS();
                        System.out.println("Estat Firma Numeric: " + status);
                        StatusConstants statusSign = StatusConstants.fromValue(status);

                        switch (statusSign) {
                            case STATUS_INITIALIZING: //fss.getSTATUSINITIALIZING(): // = 0;
                                System.err.println("  STATUS SIGN = " + statusSign + " (STATUS_INITIALIZING)");
                                System.err.println("  RESULT: Incoherent Status");
                            break;

                            case STATUS_IN_PROGRESS: //fss.getSTATUSINPROGRESS(): // = 1;
                                System.err.println("  STATUS SIGN= " + statusSign + " (STATUS_IN_PROGRESS)");
                                System.err.println("  RESULT: Incoherent Status");
                            break;

                            case STATUS_FINAL_ERROR: //fss.getSTATUSFINALERROR(): // = -1;
                                System.err.println("  STATUS SIGN= " + statusSign + " (STATUS_ERROR)");
                                System.err.println("  RESULT: Error en la firma: " + fss.getErrorMessage());
                            break;

                            case STATUS_CANCELLED: //fss.getSTATUSCANCELLED(): // = -2;
                                System.err.println("  STATUS SIGN= " + statusSign + " (STATUS_CANCELLED)");
                                System.err.println("  RESULT: L'usuari ha cancel.lat la firma.");
                            break;

                            case STATUS_FINAL_OK: //fss.getSTATUSFINALOK(): // = 2;

                                SignatureResponse fssr = api.getSignatureResult(transactionID, signID);
                                Document fsf = fssr.getSignedFile();

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

                                final File outFile = new File(getResultsDirectory(), signID + "_" + fsf.getName() + postFix);

                                FileOutputStream fos = new FileOutputStream(outFile);
                                fos.write(fsf.getData());
                                fos.flush();
                                fos.close();

                                System.out.println("  + Fitxer signat guardat en '" + outFile + "'");
                                System.out.println(SignedFileInfoToString(fssr.getSignedFileInfo()));

                            break;
                        }

                    } // Final for de fitxers firmats

                } // FINAL CASE ESTAT TRANSACCIO

            }

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

    public static void readFromSocket(int port, String html) throws IOException {

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
            out.println("<html><body>" + html + "</body></html>");

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

    protected String SignedFileInfoToString(SignedFileInfo sfi) {
        StringBuilder str = new StringBuilder("  + INFORMACIO:");

        String operation;
        Integer signOperation = sfi.getSignOperation();
        if (signOperation == null) {
            operation = " -NULL- ";
        } else if (signOperation.equals(SignOperationConstants.SIGN_OPERATION_SIGN.getValue())) {
            operation = "FIRMA";
        } else if (signOperation.equals(SignOperationConstants.SIGN_OPERATION_COSIGN.getValue())) {
            operation = "COFIRMA";
        } else if (signOperation.equals(SignOperationConstants.SIGN_OPERATION_COUNTERSIGN.getValue())) {
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
        } else if (signMode.equals(SignModeConstants.SIGN_MODE_ATTACHED_ENVELOPED.getValue())) {
            str.append("Attached - Enveloped");
        } else if (signMode.equals(SignModeConstants.SIGN_MODE_ATTACHED_ENVELOPING.getValue())) {
            str.append("Attached - Enveloping");
        } else if (signMode.equals(SignModeConstants.SIGN_MODE_DETACHED.getValue())) {
            str.append("Detached");
        } else if (signMode.equals(SignModeConstants.SIGN_MODE_INTERNALLY_DETACHED.getValue())) {
            str.append("Internally Detached");
        } else {
            str.append("DESCONEGUT (" + signMode + ")");
        }

        {
            String posicioTaulaDeFirmes;
            Integer signaturesTableLocation = sfi.getSignaturesTableLocation();
            if (signaturesTableLocation == null) {
                posicioTaulaDeFirmes = " -NULL- ";
            } else if (signaturesTableLocation
                    .equals(SignaturesTableLocationConstants.SIGNATURES_TABLE_LOCATION_WITHOUT.getValue())) {
                posicioTaulaDeFirmes = "Sense taula de Firmes";
            } else if (signaturesTableLocation
                    .equals(SignaturesTableLocationConstants.SIGNATURES_TABLE_LOCATION_FIRSTPAGE.getValue())) {
                posicioTaulaDeFirmes = "Taula de Firmes en la primera pagina";
            } else if (signaturesTableLocation
                    .equals(SignaturesTableLocationConstants.SIGNATURES_TABLE_LOCATION_LASTPAGE.getValue())) {
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
        SignerInfo fssfi = sfi.getSigners().get(0);
        str.append("\n").append("      * Informacio del Firmant:\t");
        if (fssfi == null) {
            str.append(" -- NO DISPONIBLE --\n");
        } else {
            str.append(fssfi.toString().replace("class SignerInfo {", "").replace("    ", "          - ").replace("}",
                    ""));
        }

        CustodyInfo custody = sfi.getCustodyInfo();

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

        ValidationInfo validationInfo = sfi.getValidationInfo();
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
    protected DirectSignatureOnWebV1Api getApi() throws Exception {
        return getApi(getApiClient());
    }

    @Override
    protected DirectSignatureOnWebV1Api getApi(ApiClient apiClient) throws Exception {
        return new DirectSignatureOnWebV1Api(apiClient);
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
        return "directsignatureonweb.properties";
    }


}
