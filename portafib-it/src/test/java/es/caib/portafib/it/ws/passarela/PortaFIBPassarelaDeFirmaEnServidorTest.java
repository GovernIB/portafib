package es.caib.portafib.it.ws.passarela;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;

import es.caib.portafib.it.ws.PortaFIBTestUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;

import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.FitxerBean;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaCommonInfoSignature;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaFileInfoSignature;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaFullResults;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaPolicyInfoSignature;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaSignatureResult;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaSignatureStatus;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaSignaturesSet;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PassarelaSignaturesTableHeader;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.PortaFIBPassarelaDeFirmaEnServidorWs;
import es.caib.portafib.ws.api.v1.passarelafirmaservidor.utils.PassarelaDeFirmaEnServidorUtils;

/**
 *
 * @author anadal
 *
 */
public class PortaFIBPassarelaDeFirmaEnServidorTest extends PortaFIBTestUtils {

  protected static PortaFIBPassarelaDeFirmaEnServidorWs passarelaDeFirmaAPI;

  /**
   * S'executa una vegada abans de l'execució de tots els tests d'aquesta classe
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    passarelaDeFirmaAPI = getApiDeFirmaServidor();
  }

  @AfterClass
  public static void setUpAfterClass() throws Exception {

  }

  @Test
  public void testPeticioDeFirmaEnServidor() throws Exception {

    FitxerBean source = PassarelaDeFirmaEnServidorUtils.constructFitxerBeanFromResource(
            "/testfiles/test.pdf", Constants.PDF_MIME_TYPE);

    String signType = FileInfoSignature.SIGN_TYPE_PADES;
    int signMode = FileInfoSignature.SIGN_MODE_IMPLICIT;

    FitxerBean fitxerbean = signFile(source, signType, signMode);

    Assert.assertNotNull(fitxerbean);
  }

  private FitxerBean signFile(FitxerBean source, String signType, int signMode)
      throws Exception, FileNotFoundException, IOException {
    String languageUI = "ca";
    String filtreCertificats = "";
    String username = "afirmades-firma";

    PassarelaPolicyInfoSignature policyInfoSignature = null;

    PassarelaCommonInfoSignature commonInfoSignature = new PassarelaCommonInfoSignature();
    commonInfoSignature.setLanguageUI(languageUI);
    commonInfoSignature.setFiltreCertificats(filtreCertificats);
    commonInfoSignature.setUsername(username);
    commonInfoSignature.setAdministrationID(null);
    commonInfoSignature.setPolicyInfoSignature(policyInfoSignature);

    String signID = "999";

    String reason = "TEST SIGN";
    String location = "Palma";
    String signerEmail = "test@fundaciobit.org";

    int signNumber = 1;
    String languageSign = "ca";

    String signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA1;

    int signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;

    final boolean userRequiresTimeStamp = false;

    // Valors per defcte
    final PassarelaSignaturesTableHeader signaturesTableHeader = null;
    final PassarelaSecureVerificationCodeStampInfo csvStampInfo = null;

    PassarelaFileInfoSignature fileInfo = new PassarelaFileInfoSignature();

    fileInfo.setSignID(signID);

    fileInfo.setFileToSign(source);
    fileInfo.setName(source.getNom());
    fileInfo.setReason(reason);
    fileInfo.setLocation(location);
    // Encarregat de l'aplicació client que fa l'enviament
    fileInfo.setSignerEmail(signerEmail);
    fileInfo.setSignNumber(signNumber);
    fileInfo.setLanguageSign(languageSign);
    fileInfo.setSignType(signType);
    fileInfo.setSignAlgorithm(signAlgorithm);
    fileInfo.setSignMode(signMode);
    fileInfo.setSignaturesTableLocation(signaturesTableLocation);
    fileInfo.setSignaturesTableHeader(signaturesTableHeader);
    fileInfo.setSecureVerificationCodeStampInfo(csvStampInfo);
    fileInfo.setUseTimeStamp(userRequiresTimeStamp);

    PassarelaSignaturesSet signaturesSet;
    signaturesSet = new PassarelaSignaturesSet();
    final String signaturesSetID = "" + System.currentTimeMillis();
    signaturesSet.setSignaturesSetID(signaturesSetID);

    signaturesSet.setCommonInfoSignature(commonInfoSignature);

    signaturesSet.getFileInfoSignatureArray().add(fileInfo);

    Timestamp ts = new Timestamp(System.currentTimeMillis() + 10 * 60 * 1000);
    signaturesSet.setExpiryDate(ts);

    PortaFIBPassarelaDeFirmaEnServidorWs api = PortaFIBTestUtils.getApiDeFirmaServidor();

    PassarelaFullResults results = api.signDocuments(signaturesSet);

    PassarelaSignatureStatus status = results.getSignaturesSetStatus();
    if (status.getStatus() != StatusSignaturesSet.STATUS_FINAL_OK) {

      String title = "ERROR GENERAL";
      printError(status, title);

    } else {

      // Nomes obtenim la primera firma
      PassarelaSignatureResult signedFile = results.getSignResults().get(0);

      if (signedFile.getStatus() != StatusSignaturesSet.STATUS_FINAL_OK) {

        String title = "ERROR FIRMA " + signedFile.getSignID();
        printError(status, title);

      } else {

        return signedFile.getSignedFile();

      }

    }

    return null;
  }

  private void printError(PassarelaSignatureStatus status, String title) {
    System.err.println("=========== " + title + " ============");
    System.err.println(" Code: " + status.getStatus());
    System.err.println(" MSG: " + status.getErrorMessage());

    if (status.getErrorStackTrace() != null) {
      System.err.println(status.getErrorStackTrace());
    }
  }

}
