package org.fundaciobit.apifirmasimple.v1.test;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.List;
import java.util.Properties;

import org.fundaciobit.apifirmasimple.v1.ApiFirmaWebSimple;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleAddFileToSignRequest;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleGetSignatureResultRequest;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleGetTransactionStatusResponse;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignatureStatus;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleStartTransactionRequest;
import org.fundaciobit.plugins.utils.FileUtils;

/**
 * 
 * @author anadal
 *
 */
public class ApiFirmaWebSimpleTester {

  public static FirmaSimpleFile getSimpleFileFromResource(String fileName, String mime)
      throws Exception {

    InputStream is = FileUtils.readResource(ApiFirmaWebSimpleTester.class, "testfiles/"
        + fileName);
    File tmp = File.createTempFile("testFile", fileName);
    tmp.deleteOnExit();
    ByteArrayOutputStream fos = new ByteArrayOutputStream();

    FileUtils.copy(is, fos);

    FirmaSimpleFile asf = new FirmaSimpleFile(fileName, mime, fos.toByteArray());

    return asf;

  }

  public static void main(String[] args) {

    ApiFirmaWebSimple api = null;
    String transactionID = null;
    try {

      FirmaSimpleFile fileToSign = getSimpleFileFromResource("hola.pdf", "application/pdf");

      FirmaSimpleFileInfoSignature fileInfoSignature;
      {
        String signID = "1";
        String name = fileToSign.getNom();
        String reason = "Per aprovar pressuposts";
        String location = "Palma";
        String signerEmail = "anadal@ibit.org";
        int signNumber = 1;
        String languageSign = "ca";

        fileInfoSignature = new FirmaSimpleFileInfoSignature(fileToSign, signID, name, reason,
            location, signerEmail, signNumber, languageSign);
      }

      String languageUI = "ca";
      String username = "anadal";
      String administrationID = "12345678C";

      int port = 1989;

      api = getApiFirmaWebSimple();

      FirmaSimpleCommonInfo commonInfo;
      commonInfo = new FirmaSimpleCommonInfo(languageUI, username, administrationID);

      // Enviam la part comu de la transacció
      transactionID = api.getTransactionID(commonInfo);

      System.out.println("languageUI = |" + languageUI + "|");
      System.out.println("TransactionID = |" + transactionID + "|");

      final String returnUrl = "http://localhost:" + port + "/returnurl/" + transactionID;

      final String view = FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN;

      api.addFileToSign(new FirmaSimpleAddFileToSignRequest(transactionID, fileInfoSignature));

      FirmaSimpleStartTransactionRequest startTransactionInfo;
      startTransactionInfo = new FirmaSimpleStartTransactionRequest(transactionID, returnUrl,
          view);

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
        System.err.println("Error durant la realització de les firmes: "
            + transactionStatus.getErrorMessage());
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

        List<FirmaSimpleSignatureStatus> ssl = fullTransactionStatus.getSignaturesStatusList();

        System.out.println(" ===== RESULTATS [" + ssl.size() + "] =========");

        for (FirmaSimpleSignatureStatus signatureStatus : ssl) {

          final String signID = signatureStatus.getSignID();

          System.out.println(" ---- Signature [ " + signID + " ]");

          FirmaSimpleStatus fss = signatureStatus.getStatus();

          int statusSign = fss.getStatus();
          System.out.println("  STATUS = " + statusSign);

          switch (statusSign) {

          case FirmaSimpleStatus.STATUS_INITIALIZING: // = 0;
            System.out.println("  RESULT: STATUS_INITIALIZING => Incoherent Status");
            break;

          case FirmaSimpleStatus.STATUS_IN_PROGRESS: // = 1;
            System.out.println("  RESULT: STATUS_IN_PROGRESS => Incoherent Status");
            break;

          case FirmaSimpleStatus.STATUS_FINAL_ERROR: // = -1;
            System.err.println("  RESULT: Error en la firma: " + fss.getErrorMessage());
            break;

          case FirmaSimpleStatus.STATUS_CANCELLED: // = -2;
            System.err.println("  RESULT: L'usuari ha cancelat la firma.");
            break;

          case FirmaSimpleStatus.STATUS_FINAL_OK: // = 2;

            FirmaSimpleSignatureResult fssr = api
                .getSignatureResult(new FirmaSimpleGetSignatureResultRequest(transactionID,
                    signID));
            FirmaSimpleFile fsf = fssr.getSignedFile();
            
            FileOutputStream fos = new FileOutputStream(fsf.getNom());
            fos.write(fsf.getData());
            fos.flush();

            System.out.println("  RESULT: Fitxer signat guardat en '" + fsf.getNom() + "'");
            System.gc();

            // XYZ ZZZ 
            // Falta guardar en un fitxer a part o com a mínim imprimir informació de custòdia
            String custID = fssr.getCustodyFileID();
            String custURL = fssr.getCustodyFileURL();
            if (custID != null || custURL != null) {
              System.out.println("CustodiaID = " + custID);
              System.out.println("CustodiaURL = " + custURL);
            }

            break;
          }

          return;
        } // Final for de fitxers firmats
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

  public static void readFromSocket(int port) throws Exception {

    ServerSocket serverSocket = new ServerSocket(port);
    System.err.println("Servidor escoltant al PORT: " + port);
    {
      Socket clientSocket = serverSocket.accept();
      System.err
          .println("Nou Client Connectat desde " + clientSocket.getRemoteSocketAddress());

      BufferedReader in = new BufferedReader(new InputStreamReader(
          clientSocket.getInputStream()));
      PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
          clientSocket.getOutputStream())), true);

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
      out.println("<html><body>OK</body></html>");

      System.err.println("Connexio amb el client finalitzada.");
      out.flush();
      out.close();
      in.close();
      clientSocket.close();
    }

    serverSocket.close();

  }

  protected static ApiFirmaWebSimple getApiFirmaWebSimple() throws Exception {

    Properties prop = new Properties();

    prop.load(new FileInputStream(new File("./apifirmawebsimple.properties")));

    return new ApiFirmaWebSimple(prop.getProperty("endpoint"), prop.getProperty("username"),
        prop.getProperty("password"));

  }

}
