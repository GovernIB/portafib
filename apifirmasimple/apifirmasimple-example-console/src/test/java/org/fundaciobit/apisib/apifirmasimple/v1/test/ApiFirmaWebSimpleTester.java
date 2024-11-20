package org.fundaciobit.apisib.apifirmasimple.v1.test;

import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaWebSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAddFileToSignRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetSignatureResultRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetTransactionStatusResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.jersey.ApiFirmaWebSimpleJersey;


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
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author anadal(u80067)
 *
 */
public class ApiFirmaWebSimpleTester {

    public static final String PROFILE_WEB_PROPERTY = "PROFILE_WEB";

    public static void main(String[] args) {

        ApiFirmaWebSimple api = null;
        String transactionID = null;
        try {

            Properties prop = new Properties();

            prop.load(new FileInputStream(new File("./apifirmawebsimple.properties")));

            final String perfil = prop.getProperty(PROFILE_WEB_PROPERTY);

            if (perfil == null) {
                System.err.println("La propietat " + PROFILE_WEB_PROPERTY
                        + " està buida. Com l'usuari aplicació tengui més d'un perfil definit no es podrà executar.");
            }

            String files = prop.getProperty("files");
            String[] parts = files.split(",");

            FirmaSimpleFileInfoSignature[] filesToSign = new FirmaSimpleFileInfoSignature[parts.length];

            for (int i = 0; i < parts.length; i++) {

                String nom = prop.getProperty("file." + parts[i] + ".name");
                String mime = prop.getProperty("file." + parts[i] + ".mime");

                System.out.println("Nom : ]" + nom + "[");
                System.out.println("Mime : ]" + mime + "[");

                FirmaSimpleFile fileToSign = ApiFirmaEnServidorSimpleTester.getSimpleFileFromResource(nom, mime);
                // "hola_3mb.pdf",
                // "hola.pdf",
                // "application/pdf");

                FirmaSimpleFileInfoSignature fileInfoSignature;

                String signID = parts[i];
                String name = fileToSign.getNom();
                String reason = "Per aprovar pressuposts - " + parts[i];
                String location = "Palma";

                int signNumber = 1;
                String languageSign = "ca";
                long tipusDocumentalID = 99; // =TD99

                fileInfoSignature = new FirmaSimpleFileInfoSignature(fileToSign, signID, name, reason, location,
                        signNumber, languageSign, tipusDocumentalID);

                filesToSign[i] = fileInfoSignature;
            }

            final String languageUI = "ca";
            final String username = prop.getProperty("signer.username");
            final String administrationID = prop.getProperty("signer.administrationid");
            String signerEmail = prop.getProperty("signer.email");

            System.out.println("Signer.Username = |" + username + "|");
            System.out.println("Signer.administrationid = |" + administrationID + "|");
            System.out.println("Signer.email = |" + signerEmail + "|");

            FirmaSimpleCommonInfo commonInfo;
            commonInfo = new FirmaSimpleCommonInfo(perfil, languageUI, username, administrationID, signerEmail);

            api = getApiFirmaWebSimple(prop);

            System.out.println(Arrays.toString(api.getAvailableTypesOfDocuments("ca").toArray()));

            //if (true) { return; }

            // Enviam la part comu de la transacció
            transactionID = api.getTransactionID(commonInfo);

            System.out.println("languageUI = |" + languageUI + "|");

            System.out.println("TransactionID = |" + transactionID + "|");

            for (int i = 0; i < filesToSign.length; i++) {
                System.out.println("Enviant firma[" + i + "]");
                api.addFileToSign(new FirmaSimpleAddFileToSignRequest(transactionID, filesToSign[i]));
            }

            int port = 1989 + (int) (Math.random() * 100.0);
            final String returnUrl = "http://localhost:" + port + "/returnurl/" + transactionID;
            final String view = FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN;

            FirmaSimpleStartTransactionRequest startTransactionInfo;
            startTransactionInfo = new FirmaSimpleStartTransactionRequest(transactionID, returnUrl, view);

            String redirectUrl = api.startTransaction(startTransactionInfo);

            System.out.println("RedirectUrl = " + redirectUrl);

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(redirectUrl));
            } else {
                System.out.println("Per favor obri un Navegador i copia-li la URL anterior ...");
            }

            readFromSocket(port);

            FirmaSimpleGetTransactionStatusResponse fullTransactionStatus;
            fullTransactionStatus = api.getTransactionStatus(transactionID);

            FirmaSimpleStatus transactionStatus = fullTransactionStatus.getTransactionStatus();

            int status = transactionStatus.getStatus();

            switch (status) {

                case FirmaSimpleStatus.STATUS_INITIALIZING: // = 0;
                    throw new Exception("S'ha rebut un estat inconsistent del proces de firma"
                            + " (inicialitzant). Pot ser el PLugin de Firma no està ben desenvolupat."
                            + " Consulti amb el seu administrador.");

                case FirmaSimpleStatus.STATUS_IN_PROGRESS: // = 1;
                    throw new Exception("S'ha rebut un estat inconsistent del proces de firma"
                            + " (En Progrés). Pot ser el PLugin de Firma no està ben desenvolupat."
                            + " Consulti amb el seu administrador.");

                case FirmaSimpleStatus.STATUS_FINAL_ERROR: // = -1;
                {
                    System.err.println(
                            "Error durant la realització de les firmes: " + transactionStatus.getErrorMessage());
                    String desc = transactionStatus.getErrorStackTrace();
                    if (desc != null) {
                        System.err.println(desc);
                    }
                    return;
                }

                case FirmaSimpleStatus.STATUS_CANCELLED: // = -2;
                {
                    System.err.println("Durant el proces de firmes, l'usuari ha cancelat la transacció.");
                    return;
                }

                case FirmaSimpleStatus.STATUS_FINAL_OK: // = 2;
                {
                    processStatusFileOfSign(api, transactionID, fullTransactionStatus);
                } // Final Case Firma OK
            } // Final Switch Firma

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
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

    protected static void processStatusFileOfSign(ApiFirmaWebSimple api, String transactionID,
            FirmaSimpleGetTransactionStatusResponse fullTransactionStatus)
            throws Exception, FileNotFoundException, IOException {
        List<FirmaSimpleSignatureStatus> ssl = fullTransactionStatus.getSignaturesStatusList();

        System.out.println(" ===== RESULTATS [" + ssl.size() + "] =========");

        for (FirmaSimpleSignatureStatus signatureStatus : ssl) {

            final String signID = signatureStatus.getSignID();

            System.out.println(" ---- Signature [ " + signID + " ]");

            FirmaSimpleStatus fss = signatureStatus.getStatus();

            int statusSign = fss.getStatus();

            switch (statusSign) {

                case FirmaSimpleStatus.STATUS_INITIALIZING: // = 0;
                    System.err.println("  STATUS = " + statusSign + " (STATUS_INITIALIZING)");
                    System.err.println("  ESULT: Incoherent Status");
                break;

                case FirmaSimpleStatus.STATUS_IN_PROGRESS: // = 1;
                    System.err.println("  STATUS = " + statusSign + " (STATUS_IN_PROGRESS)");
                    System.err.println("  RESULT: Incoherent Status");
                break;

                case FirmaSimpleStatus.STATUS_FINAL_ERROR: // = -1;
                    System.err.println("  STATUS = " + statusSign + " (STATUS_ERROR)");
                    System.err.println("  RESULT: Error en la firma: " + fss.getErrorMessage());
                break;

                case FirmaSimpleStatus.STATUS_CANCELLED: // = -2;
                    System.err.println("  STATUS = " + statusSign + " (STATUS_CANCELLED)");
                    System.err.println("  RESULT: L'usuari ha cancel.lat la firma.");
                break;

                case FirmaSimpleStatus.STATUS_FINAL_OK: // = 2;

                    FirmaSimpleSignatureResult fssr = api
                            .getSignatureResult(new FirmaSimpleGetSignatureResultRequest(transactionID, signID));
                    FirmaSimpleFile fsf = fssr.getSignedFile();

                    String postFix;
                    String signType = fssr.getSignedFileInfo().getSignType();
                    if (FirmaSimpleSignedFileInfo.SIGN_TYPE_PADES.equals(signType)) {
                        postFix = "_signed.pdf";
                    } else if (FirmaSimpleSignedFileInfo.SIGN_TYPE_CADES.equals(signType)) {
                        postFix = "_signed.csig";
                    } else if (FirmaSimpleSignedFileInfo.SIGN_TYPE_XADES.equals(signType)) {
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

                    ApiFirmaEnServidorSimpleTester.printSignatureInfo(fssr);

                break;
            }

        } // Final for de fitxers firmats
    }

    public static void readFromSocket(int port) throws Exception {

        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Servidor escoltant al PORT: " + port);
        {
            Socket clientSocket = serverSocket.accept();
            System.err.println("Nou Client Connectat desde " + clientSocket.getRemoteSocketAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

            String s;
            System.err.println(" =========================== ");
            while ((s = in.readLine()) != null) {
                System.out.println(s);
                break;
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
            in.close();
            clientSocket.close();
        }

        serverSocket.close();
    }

    protected static ApiFirmaWebSimple getApiFirmaWebSimple(Properties prop) throws Exception {
        return new ApiFirmaWebSimpleJersey(prop.getProperty("endpoint"), prop.getProperty("username"),
                prop.getProperty("password"));
    }

}
