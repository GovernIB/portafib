package org.fundaciobit.apifirmawebsimple.test;

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
import java.util.List;
import java.util.Properties;

import org.fundaciobit.apifirmawebsimple.ApiFirmaWebSimple;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleFile;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureResults;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleSignatureStatus;
import org.fundaciobit.apifirmawebsimple.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apifirmawebsimple.beans.FirmaWebSimpleSignaturesSet;
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
      String signID = "1";
      String name = fileToSign.getNom();
      String reason = "Per aprovar pressuposts";
      String location = "Palma";
      String signerEmail = "anadal@ibit.org";
      int signNumber = 1;
      String languageSign = "ca";

      // XYZ ZZZ
      // Falta provar si s'envien bé les dades de Map<String,String>

      FirmaSimpleFileInfoSignature fileInfoSignature = new FirmaSimpleFileInfoSignature(
          fileToSign, signID, name, reason, location, signerEmail, signNumber, languageSign);

      FirmaSimpleFileInfoSignature[] fileInfoSignatureArray;
      fileInfoSignatureArray = new FirmaSimpleFileInfoSignature[] { fileInfoSignature };

      String languageUI = "ca";
      String username = "anadal";
      String administrationID = "43096845C";

      int port = 1989;

      api = getApiFirmaWebSimple();

      FirmaSimpleCommonInfo commonInfo;
      commonInfo = new FirmaSimpleCommonInfo(languageUI, username, administrationID);

      // XYZ ZZZ Enviar la part comu de la transacció
      transactionID = api.getTransactionID(commonInfo);

      System.out.println("languageUI = |" + languageUI + "|");
      System.out.println("TransactionID = |" + transactionID + "|");
      
      final String returnUrl = "http://localhost:" + port + "/returnurl/" + transactionID;

      final String view = FirmaWebSimpleSignaturesSet.VIEW_FULLSCREEN;
      
      FirmaWebSimpleSignaturesSet signaturesSet = new FirmaWebSimpleSignaturesSet(
          transactionID, fileInfoSignatureArray, returnUrl, view);

      String redirectUrl = api.startTransaction(signaturesSet);

      System.out.println("RedirectUrl = " + redirectUrl);

      readFromSocket(port);

      // 5 reintents
      for (int i = 0; i < 5; i++) {

        FirmaSimpleSignatureStatus transactionStatus = api.getTransactionStatus(transactionID);

        int status = transactionStatus.getStatus();

        switch (status) {

        case FirmaSimpleSignatureStatus.STATUS_INITIALIZING: // = 0;
          System.out.println("Initializing ... wait (???)");
          Thread.sleep(2000);
          break;

        case FirmaSimpleSignatureStatus.STATUS_IN_PROGRESS: // = 1;
          System.out.println("In PROGRESS ... wait");
          Thread.sleep(2000);
          break;

        case FirmaSimpleSignatureStatus.STATUS_FINAL_ERROR: // = -1;
        {
          System.err.println("Error durant la realització de les firmes: "
              + transactionStatus.getErrorMessage());
          String desc = transactionStatus.getErrorStackTrace();
          if (desc != null) {
            System.err.println(desc);
          }
          return;
        }

        case FirmaSimpleSignatureStatus.STATUS_CANCELLED: // = -2;
        {
          System.err
              .println("Durant el proces de firmes, l'usuari ha cancelat la transacció.");
          return;
        }

        case FirmaSimpleSignatureStatus.STATUS_FINAL_OK: // = 2;
        {
          FirmaSimpleSignatureResults resultsHolder = api.getTransactionResults(transactionID);
          List<FirmaSimpleSignatureResult> results = resultsHolder.getResults();

          System.out.println(" ===== RESULTATS [" + results.size() + "] =========");

          for (FirmaSimpleSignatureResult fssr : results) {
            System.out.println(" ---- Signature [ " + fssr.getSignID() + " ]");
            int statusSign = fssr.getStatus();
            System.out.println("  STATUS = " + statusSign);

            switch (statusSign) {

            case FirmaSimpleSignatureStatus.STATUS_INITIALIZING: // = 0;
              System.out.println("  RESULT: STATUS_INITIALIZING => Incoherent Status");
              break;

            case FirmaSimpleSignatureStatus.STATUS_IN_PROGRESS: // = 1;
              System.out.println("  RESULT: STATUS_IN_PROGRESS => Incoherent Status");
              break;

            case FirmaSimpleSignatureStatus.STATUS_FINAL_ERROR: // = -1;
              System.err.println("  RESULT: Error en la firma: " + fssr.getErrorMessage());
              break;

            case FirmaSimpleSignatureStatus.STATUS_CANCELLED: // = -2;
              System.err.println("  RESULT: L'usuari ha cancelat la firma.");
              break;

            case FirmaSimpleSignatureStatus.STATUS_FINAL_OK: // = 2;
              FirmaSimpleFile fsf = fssr.getSignedFile();
              FileOutputStream fos = new FileOutputStream(fsf.getNom());
              fos.write(fsf.getData());
              fos.flush();
              System.out.println("  RESULT: Fitxer signat guardat en '" + fsf.getNom() + "'");
              break;
            }

            return;
          } // Final for de fitxers firmats
        } // Final Case Firma OK
        } // Final Switch Firma

      } // Final Switch Global

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
      // out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
      // out.write("Server: Apache/0.8.4\r\n");
      out.println("Content-Type: text/html");
      // out.write("Content-Length: 2\r\n");
      // out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
      // out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
      out.println("\r\n");
      out.println("<html><body>OK</body></html>");
      // out.write("<P>Ceci est une page d'exemple.</P>");

      // on ferme les flux.
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
