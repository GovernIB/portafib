package org.fundaciobit.plugins.signatureserver.exempleapp;

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
import org.fundaciobit.pluginsib.core.utils.PluginsManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author anadal
 *
 */
public class ExempleApp {

  public static final String BASE_PACKAGE = "org.fundaciobit.exemple.signatureserverplugins";

  public static void main(String[] args) {

    String def = "../../pluginsib-signatureweb/exemplepassarela/plugins_server.properties";

    File propertiesFile;

    if (args.length == 0) {
      propertiesFile = new File(def);
    } else {
      propertiesFile = new File(args[0]);
    }

    try {

      Map<Long, Plugin> tmpplugins = getPlugins(propertiesFile);

      Scanner scan = new Scanner(System.in);

      long plugID = -1;

      do {

        System.out.println();
        System.out.println();
        System.out.println("Selecciona un plugin " + tmpplugins.keySet().toString() + ":");

        try {
          plugID = scan.nextLong();
        } catch (Exception e) {
          continue;
        }

      } while (!tmpplugins.containsKey(plugID));

      Plugin plugin = tmpplugins.get(plugID);

      ISignatureServerPlugin pluginInstance;
      pluginInstance = (ISignatureServerPlugin) PluginsManager.instancePluginByClassName(
          plugin.getClasse(), BASE_PACKAGE + ".", plugin.getProperties());

      final String pdfsource = "sample.pdf";

      IRubricGenerator rubricGenerator = null;

      // PAdES SIGN
      {
        String signType = FileInfoSignature.SIGN_TYPE_PADES;
        int signMode = FileInfoSignature.SIGN_MODE_IMPLICIT;
        boolean userRequiresTimeStamp = false;
        String pdfdest = "sample_signed.pdf";
        signFile(pdfsource, pdfdest, signType, signMode, userRequiresTimeStamp,
            rubricGenerator, pluginInstance);
      }

      // XAdES Attached SIGN
      {
        String signType = FileInfoSignature.SIGN_TYPE_XADES;
        int signMode = FileInfoSignature.SIGN_MODE_IMPLICIT; // Attached
        // FileInfoSignature.SIGN_MODE_EXPLICIT; // Detached
        boolean userRequiresTimeStamp = false;
        String xadesAttachedDest = "sample.pdf.xades_attached.xml";
        signFile(pdfsource, xadesAttachedDest, signType, signMode, userRequiresTimeStamp,
            rubricGenerator, pluginInstance);
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //

  }

  protected static Map<Long, Plugin> getPlugins(File propertiesFile) throws IOException,
      FileNotFoundException {

    Map<Long, Plugin> tmpplugins = new HashMap<Long, Plugin>();

    Properties pluginProperties = new Properties();

    pluginProperties.load(new FileReader(propertiesFile));

    String pluginsItems = pluginProperties
        .getProperty("org.fundaciobit.exemple.signatureserverplugins");

    String[] ids = pluginsItems.split(",");

    for (int i = 0; i < ids.length; i++) {
      long pluginID = Long.parseLong(ids[i]);
      String base = BASE_PACKAGE + "." + pluginID + ".";

      String nom = pluginProperties.getProperty(base + "nom");

      System.out.println("Plugin a carregar NOM[" + base + "nom" + "]: " + nom);
      String classe = pluginProperties.getProperty(base + "class");
      String descripcioCurta = pluginProperties.getProperty(base + "desc");

      // Llegir propietats
      Properties properties = new Properties();
      Set<Object> keys = pluginProperties.keySet();

      for (Object object : keys) {
        String key = (String) object;

        if (key.startsWith(base)) {
          String value = pluginProperties.getProperty(key);

          // Posam la mateixa BASE a totes les propietats de tots els plugins

          String nomFinal = key.substring(base.length());
          properties.put(BASE_PACKAGE + "." + nomFinal, value);
        }

      }

      System.out.println(" -------------  PLUGIN " + pluginID + "------------------");
      System.out.println("nom: " + nom);
      System.out.println("descripcioCurta: " + descripcioCurta);
      System.out.println("classe: " + classe);
      System.out.println("properties: " + properties);

      tmpplugins.put(pluginID, new Plugin(pluginID, nom, descripcioCurta, classe, properties));

    }

    return tmpplugins;
  }

  public static void signFile(String pdfsource, String pdfdest, String signType, int signMode,
      boolean userRequiresTimeStamp, IRubricGenerator rubricGenerator,
      ISignatureServerPlugin plugin) throws Exception, FileNotFoundException, IOException {

    String languageUI = "ca";
    String filtreCertificats = "";
    String username = "anadal"; // configuracio
    String administrationID = null; // No te sentit en API Firma En Servidor
    boolean alwaysCreateRevision = true;

    CommonInfoSignature commonInfoSignature = new CommonInfoSignature(languageUI,
        filtreCertificats, username, administrationID, alwaysCreateRevision);

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
    final File previusSignatureDetachedFile = null;
    final int signOperation = FileInfoSignature.SIGN_OPERATION_SIGN;
    PolicyInfoSignature policyInfoSignature = null;
    final String expedientCode=null;
    final String expedientName=null;
    final String expedientUrl=null;
    final String procedureCode=null;
    final String procedureName=null;
    

    FileInfoSignature fileInfo = new FileInfoSignature(signID, source,
        previusSignatureDetachedFile, 
        FileInfoSignature.PDF_MIME_TYPE, name, reason, location, signerEmail, signNumber,
        languageSign, signOperation, signType, signAlgorithm, signMode, signaturesTableLocation,
        signaturesTableHeader, pdfInfoSignature, csvStampInfo, userRequiresTimeStamp,
        timeStampGenerator, policyInfoSignature, expedientCode,
          expedientName, expedientUrl, procedureCode, procedureName);

    final String signaturesSetID = String.valueOf(System.currentTimeMillis());
    SignaturesSet signaturesSet = new SignaturesSet(signaturesSetID, commonInfoSignature,
        new FileInfoSignature[] { fileInfo });

    String timestampUrlBase = null;
    final Map<String, Object> parameters = new HashMap<String, Object>();
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
