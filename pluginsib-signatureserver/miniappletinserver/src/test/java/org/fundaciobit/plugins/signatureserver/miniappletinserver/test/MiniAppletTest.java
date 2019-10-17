package org.fundaciobit.plugins.signatureserver.miniappletinserver.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.IRubricGenerator;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.PdfRubricRectangle;
import org.fundaciobit.plugins.signature.api.PdfVisibleSignature;
import org.fundaciobit.plugins.signature.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.SignaturesTableHeader;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureserver.api.ISignatureServerPlugin;
import org.fundaciobit.plugins.signatureserver.miniappletinserver.MiniAppletInServerSignatureServerPlugin;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author anadal
 *
 */
public class MiniAppletTest extends TestCase {
  /**
   * Create the test case
   *
   * @param testName
   *          name of the test case
   */
  public MiniAppletTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(MiniAppletTest.class);
  }

  /**
   * Rigourous Test :-)
   */
  public void testApp() {
    assertTrue(true);
  }
  
  
  
  
  
  public static final String BASE_PACKAGE = "org.fundaciobit.exemple.signatureserverplugins.2.";

  public static void main(String[] args) {

    

    try {
      
      Properties prop = new Properties();
      
      prop.load(new FileInputStream(new File("./plugin.properties")));
      
     
      ISignatureServerPlugin pluginInstance;
      pluginInstance = (ISignatureServerPlugin) PluginsManager.instancePluginByClassName(
          MiniAppletInServerSignatureServerPlugin.class.getName(), BASE_PACKAGE, prop);

      final String pdfsource = "hola.pdf";

      IRubricGenerator rubricGenerator = null;

      // PAdES SIGN from JKS
      {
        String signType = FileInfoSignature.SIGN_TYPE_PADES;
        int signMode = FileInfoSignature.SIGN_MODE_IMPLICIT;
        boolean userRequiresTimeStamp = false;
        String username = "anadaljks"; // configuracio
        String pdfdest = "hola_firmat_jks.pdf";
        signFile(pdfsource, pdfdest, signType, signMode, userRequiresTimeStamp,
            rubricGenerator, username, pluginInstance);
      }
      
      
      // PAdES SIGN from JKS withg username null
      {
        String signType = FileInfoSignature.SIGN_TYPE_PADES;
        int signMode = FileInfoSignature.SIGN_MODE_IMPLICIT;
        boolean userRequiresTimeStamp = false;
        String username = null; // configuracio
        String pdfdest = "hola_firmat_jks_username_null.pdf";
        signFile(pdfsource, pdfdest, signType, signMode, userRequiresTimeStamp,
            rubricGenerator, username, pluginInstance);
      }
      
      
      // PAdES SIGN from P12
      {
        String signType = FileInfoSignature.SIGN_TYPE_PADES;
        int signMode = FileInfoSignature.SIGN_MODE_IMPLICIT;
        boolean userRequiresTimeStamp = false;
        String username = "anadalp12"; // configuracio
        String pdfdest = "hola_firmat_p12.pdf";
        signFile(pdfsource, pdfdest, signType, signMode, userRequiresTimeStamp,
            rubricGenerator, username, pluginInstance);
      }

      // XAdES Attached SIGN
//      {
//        String signType = FileInfoSignature.SIGN_TYPE_XADES;
//        int signMode = FileInfoSignature.SIGN_MODE_IMPLICIT; // Attached
//        // FileInfoSignature.SIGN_MODE_EXPLICIT; // Detached
//        boolean userRequiresTimeStamp = false;
//        String xadesAttachedDest = "sample.pdf.xades_attached.xml";
//        signFile(pdfsource, xadesAttachedDest, signType, signMode, userRequiresTimeStamp,
//            rubricGenerator, pluginInstance);
//      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }



  public static void signFile(String pdfsource, String pdfdest, String signType, int signMode,
      boolean userRequiresTimeStamp, IRubricGenerator rubricGenerator,
      String username, // configuracio
      ISignatureServerPlugin plugin) throws Exception, FileNotFoundException, IOException {

    String languageUI = "ca";
    String filtreCertificats = "";
   
    String administrationID = null; // No te sentit en API Firma En Servidor

    CommonInfoSignature commonInfoSignature = new CommonInfoSignature(languageUI,
        filtreCertificats, username, administrationID);

    String signID = "999";
    File source = new File(pdfsource);
    String name = source.getName();
    String reason = "TEST SIGN";
    String location = "Palma";
    String signerEmail = "anadal@ibit.org";
    int signNumber = 1;
    String languageSign = "ca";

    String signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA1;

    int signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
    PdfVisibleSignature pdfInfoSignature = null;
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType) && rubricGenerator != null) {
      signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_LASTPAGE;
      PdfRubricRectangle pdfRubricRectangle = new PdfRubricRectangle(106, 650, 555, 710);
      pdfInfoSignature = new PdfVisibleSignature(pdfRubricRectangle, rubricGenerator);
    }
    final ITimeStampGenerator timeStampGenerator = null;

    // Valors per defcte
    final SignaturesTableHeader signaturesTableHeader = null;
    final SecureVerificationCodeStampInfo csvStampInfo = null;
    
    final  File previusSignatureDetachedFile = null;
    final  int signOperation = FileInfoSignature.SIGN_OPERATION_SIGN;
    PolicyInfoSignature policyInfoSignature = null;

    final String expedientCode = null;
    final String expedientName= null;
    final String expedientUrl = null;
    final String procedureCode = null;
    final String procedureName = null;

    final String applicationID = "app";

    FileInfoSignature fileInfo = new FileInfoSignature(signID, source, previusSignatureDetachedFile,
        FileInfoSignature.PDF_MIME_TYPE, name, reason, location, signerEmail, signNumber,
        languageSign, signOperation, signType, signAlgorithm, signMode, signaturesTableLocation,
        signaturesTableHeader, pdfInfoSignature, csvStampInfo, userRequiresTimeStamp,
        timeStampGenerator, policyInfoSignature, expedientCode,
          expedientName, expedientUrl, procedureCode, procedureName, applicationID);

    final String signaturesSetID = String.valueOf(System.currentTimeMillis());
    SignaturesSet signaturesSet = new SignaturesSet(signaturesSetID, commonInfoSignature,
        new FileInfoSignature[] { fileInfo });

    final Map<String, Object> parameters = new HashMap<String, Object>();
    final String timestampUrlBase = null;
    signaturesSet = plugin.signDocuments(signaturesSet, timestampUrlBase, parameters);
    StatusSignaturesSet sss = signaturesSet.getStatusSignaturesSet();

    if (sss.getStatus() != StatusSignaturesSet.STATUS_FINAL_OK) {
      System.err.println("Error General MSG = " + sss.getErrorMsg());
      if (sss.getErrorException() != null) {
        sss.getErrorException().printStackTrace();
      }
      throw new Exception(sss.getErrorMsg());
    } else {
      FileInfoSignature fis = signaturesSet.getFileInfoSignatureArray()[0];
      StatusSignature status = fis.getStatusSignature();
      if (status.getStatus() != StatusSignaturesSet.STATUS_FINAL_OK) {
        if (status.getErrorException() != null) {
          status.getErrorException().printStackTrace();
        }
        System.err.println("Error Firma 1. MSG = " + status.getErrorMsg());
        throw new Exception(status.getErrorMsg());
      } else {
        File dest = new File(pdfdest);
        status.getSignedData().renameTo(dest);
        System.out.println();
        System.out.println();
        System.out.println(" Guardada Firma a " + dest.getAbsolutePath());
        System.out.println(" Tamany " + dest.length());
      }
    }
  }

}
