package org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsRequest;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsResponse;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.exceptions.ClientException;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.exceptions.NoAvailablePluginException;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.exceptions.ServerException;

/**
 * 
 * @author anadal
 *
 */
public class ApiFirmaEnServidorSimpleTester {



  public static void main(String[] args) {

    try {
      
      
      ApiFirmaEnServidorSimple api = getApiFirmaEnServidorSimple();
      
      testGetMaxSignByTransaction(api);

      testSignatureServer(api);
      
      testUpgradeSignaturePAdES(api);
      
//      testUpgradeSignatureCAdES(api);
      
//      testUpgradeSignatureXAdESOfBinary(api);
//      
//      testUpgradeSignatureXAdESOfXML(api);
//      
//      testUpgradeSignatureXAdESManifest(api);
      
    } catch (NoAvailablePluginException nape) {

      nape.printStackTrace();

      System.err
          .println("No s'ha trobat cap plugin que pugui realitzar la firma o alguna de les firmes sol·licitades.");

    } catch (ClientException client) {

      client.printStackTrace();

      System.err
          .println("S'ha produït un error intentant contactar amb el servidor intermedi:"
              + client.getMessage());

    } catch (ServerException server) {

      server.printStackTrace();

      System.err.println("S'ha produït un error enb el servidor intermedi:"
          + server.getMessage());

    } catch (Exception e) {
      e.printStackTrace();

      System.err.println("Error desconegut intentant realitzar les firmes: " + e.getMessage());
    }
  }


  
  
  
  public static void testUpgradeSignatureCAdES(ApiFirmaEnServidorSimple api) throws Exception,
    FileNotFoundException, IOException {
  
     FirmaSimpleFile fileToSign = getSimpleFileFromResource("foto.jpg_cades_detached.csig", "application/octet-stream");
  
     FirmaSimpleFile upgraded = api.upgradeSignature(new FirmaSimpleUpgradeRequest(fileToSign.getData(), null));
     
     String fileName = "foto.jpg_cades_detached-upgraded.csig";
     
     guardarFitxer(upgraded, fileName);
  
  }
  
  
  
  public static void testUpgradeSignaturePAdES(ApiFirmaEnServidorSimple api) throws Exception,
     FileNotFoundException, IOException {

      FirmaSimpleFile fileToSign = getSimpleFileFromResource("hola_signed.pdf", "application/pdf");

      FirmaSimpleFile upgraded = api.upgradeSignature(new FirmaSimpleUpgradeRequest(fileToSign.getData(),null));
      
      String fileName = "hola-signed-upgraded.pdf";
      
      guardarFitxer(upgraded, fileName);

  }
  
  
  
  public static void testUpgradeSignatureXAdESOfBinary(ApiFirmaEnServidorSimple api) throws Exception,
  FileNotFoundException, IOException {

      FirmaSimpleFile fileToSign = getSimpleFileFromResource("foto_xades_attached_firmat.xsig", "application/xml");

      FirmaSimpleFile upgraded = api.upgradeSignature(new FirmaSimpleUpgradeRequest(fileToSign.getData(),null));

      String fileName = "foto_xades_attached_firmat_upgraded.xsig";
      
      guardarFitxer(upgraded, fileName);
  }
  
  public static void testUpgradeSignatureXAdESOfXML(ApiFirmaEnServidorSimple api) throws Exception,
  FileNotFoundException, IOException {

      FirmaSimpleFile fileToSign = getSimpleFileFromResource("sample.xml_signed.xsig", "application/xml");

      FirmaSimpleFile upgraded = api.upgradeSignature(new FirmaSimpleUpgradeRequest(fileToSign.getData(),null));

      String fileName = "sample.xml_signed_upgraded.xsig";
      
      guardarFitxer(upgraded, fileName);
  }
 
  
  
  public static void testUpgradeSignatureXAdESManifest(ApiFirmaEnServidorSimple api) throws Exception,
  FileNotFoundException, IOException {

      FirmaSimpleFile fileToSign = getSimpleFileFromResource("ORVE_firma0.xsig", "application/xml");

      FirmaSimpleFile upgraded = api.upgradeSignature(new FirmaSimpleUpgradeRequest(fileToSign.getData(),null));
      
      String fileName = "ORVE_firma0_upgraded.xsig";
      
      guardarFitxer(upgraded, fileName);
  }

  private static void guardarFitxer(FirmaSimpleFile upgraded, String fileName)
      throws FileNotFoundException, IOException {
    File parent = new File("results");
    parent.mkdirs();
    
    File f = new File(parent, fileName);
    FileOutputStream fos = new FileOutputStream(f);
    fos.write(upgraded.getData());
    fos.flush();
    fos.close();

    System.out.println("Guardat " + fileName);
  }
  
  
  
  
  
  /**
   * 
   * @param api
   * @throws Exception
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void testSignatureServer(ApiFirmaEnServidorSimple api) throws Exception,
      FileNotFoundException, IOException {
    
    FirmaSimpleFile fileToSign = getSimpleFileFromResource("hola.pdf", "application/pdf");
    String signID = "1";
    String name = fileToSign.getNom();
    String reason = "Per aprovar pressuposts";
    String location = "Palma";
    String signerEmail = "anadal@ibit.org";
    int signNumber = 1;
    String languageSign = "ca";

    FirmaSimpleFileInfoSignature fileInfoSignature = new FirmaSimpleFileInfoSignature(
        fileToSign, signID, name, reason, location, signerEmail, signNumber, languageSign);

    FirmaSimpleFileInfoSignature[] fileInfoSignatureArray;
    fileInfoSignatureArray = new FirmaSimpleFileInfoSignature[] { fileInfoSignature };

    String languageUI = "ca";
    String username = null; //"anadal"; //  Es la configuració del Servidor (deixam el valor per defecte)
    String administrationID = null;


    FirmaSimpleCommonInfo commonInfo;
    commonInfo = new FirmaSimpleCommonInfo(languageUI, username, administrationID);

    System.out.println("languageUI = |" + languageUI + "|");

    FirmaSimpleSignDocumentsRequest signaturesSet;
    signaturesSet = new FirmaSimpleSignDocumentsRequest(commonInfo,
        fileInfoSignatureArray);

    FirmaSimpleSignDocumentsResponse fullResults = api.signDocuments(signaturesSet);

    FirmaSimpleStatus transactionStatus = fullResults.getStatusSignatureProcess();

    int status = transactionStatus.getStatus();

    switch (status) {

    case FirmaSimpleStatus.STATUS_INITIALIZING: // = 0;
      System.err.println("Initializing ...Unknown Error (???)");
      return;

    case FirmaSimpleStatus.STATUS_IN_PROGRESS: // = 1;
      System.err.println("In PROGRESS ... Unknown Error (????) ");
      return;

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
      System.err.println("S'ha cancel·lat el procés de firmat.");
      return;
    }

    case FirmaSimpleStatus.STATUS_FINAL_OK: // = 2;
    {
      List<FirmaSimpleSignatureResult> results = fullResults.getResults();

      System.out.println(" ===== RESULTATS [" + results.size() + "] =========");

      for (FirmaSimpleSignatureResult fssr : results) {
        System.out.println(" ---- Signature [ " + fssr.getSignID() + " ]");
        
        FirmaSimpleStatus statusSign = fssr.getStatus();
        
        int estat = statusSign.getStatus();
        System.out.println("  STATUS SIGN = " + estat);

        switch (estat) {

        case FirmaSimpleStatus.STATUS_INITIALIZING: // = 0;
          System.out.println("  RESULT: STATUS_INITIALIZING => Incoherent Status");
          break;

        case FirmaSimpleStatus.STATUS_IN_PROGRESS: // = 1;
          System.out.println("  RESULT: STATUS_IN_PROGRESS => Incoherent Status");
          break;

        case FirmaSimpleStatus.STATUS_FINAL_ERROR: // = -1;
          System.err.println("  RESULT: Error en la firma: " + statusSign.getErrorMessage());
          break;

        case FirmaSimpleStatus.STATUS_CANCELLED: // = -2;
          System.err.println("  RESULT: L'usuari ha cancelat la firma.");
          break;

        case FirmaSimpleStatus.STATUS_FINAL_OK: // = 2;
          FirmaSimpleFile fsf = fssr.getSignedFile();
          FileOutputStream fos = new FileOutputStream(fsf.getNom());
          fos.write(fsf.getData());
          fos.flush();
          fos.close();
          System.out.println("  RESULT: Fitxer signat guardat en '" + fsf.getNom() + "'");
          
          String custID = fssr.getCustodyFileID();
          String custURL = fssr.getCustodyFileURL();
          if (custID != null || custURL != null) {
            System.out.println("  CustodiaID = " + custID);
            System.out.println("  CustodiaURL = " + custURL);
          }

          break;
        }

        return;
      } // Final for de fitxers firmats
    } // Final Case Firma OK
    } // Final Switch Firma
  }

  public static void testGetMaxSignByTransaction(ApiFirmaEnServidorSimple api)
      throws Exception {
    Integer max = api.getMaxNumberOfSignaturesByTransaction();
    
    System.out.println("getMaxNumberOfSignaturesByTransaction = " + max);
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public static ApiFirmaEnServidorSimple getApiFirmaEnServidorSimple() throws Exception {

    Properties prop = new Properties();

    prop.load(new FileInputStream(new File("./apifirmaenservidorsimple.properties")));

    return new ApiFirmaEnServidorSimple(prop.getProperty("endpoint"),
        prop.getProperty("username"), prop.getProperty("password"));

  }
  
  /**
   * 
   * @param fileName
   * @param mime
   * @return
   * @throws Exception
   */
  public static FirmaSimpleFile getSimpleFileFromResource(String fileName, String mime)
      throws Exception {

    InputStream is = FileUtils.readResource(ApiFirmaEnServidorSimpleTester.class, "testfiles/"
        + fileName);
    File tmp = File.createTempFile("testFile", fileName);
    tmp.deleteOnExit();
    ByteArrayOutputStream fos = new ByteArrayOutputStream();

    FileUtils.copy(is, fos);

    FirmaSimpleFile asf = new FirmaSimpleFile(fileName, mime, fos.toByteArray());

    return asf;

  }

}
