package org.fundaciobit.plugins.signatureweb.miniappletutils;

import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signatureweb.api.PdfInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.PdfRubricRectangle;
import org.fundaciobit.plugins.signatureweb.api.PolicyInfoSignature;
import org.fundaciobit.plugins.utils.Base64;
import org.fundaciobit.plugins.utils.FileUtils;

/**
 * 
 * @author anadal
 *
 */
public class MiniAppletUtils {

  protected final static Logger log = Logger.getLogger(MiniAppletUtils.class.getName());

  public static MiniAppletSignInfo convertRemoteSignature(
      CommonInfoSignature commonInfoSignature, FileInfoSignature fileInfo,
      String timeStampURL, String rubricURL) throws Exception {
    final boolean isLocalSignature = false;
    return convert(commonInfoSignature, fileInfo, timeStampURL, null, rubricURL,
        isLocalSignature);

  }

  public static MiniAppletSignInfo convertLocalSignature(
      CommonInfoSignature commonInfoSignature, FileInfoSignature fileInfo,
      String timeStampURL, X509Certificate certificate) throws Exception {
    final boolean isLocalSignature = true;
    return convert(commonInfoSignature, fileInfo, timeStampURL, certificate, null,
        isLocalSignature);
  }

  private static MiniAppletSignInfo convert(CommonInfoSignature commonInfoSignature,
      FileInfoSignature fileInfo, String timeStampURL, X509Certificate certificate,
      String rubricURL, boolean isLocalSignature) throws Exception {
    MiniAppletSignInfo info;

    Properties miniAppletProperties = new Properties();

    /*
     * explicit La firma resultante no incluirá los datos firmados. Si no se
     * indica el parámetro mode se configura automáticamente este
     * comportamiento.
     * 
     * implicit La firma resultante incluirá internamente una copia de los datos
     * firmados. El uso de este valor podría generar firmas de gran tamaño.
     */
    if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_IMPLICIT) {
      miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_SIGN_MODE,
          MiniAppletConstants.VALUE_SIGN_MODE_IMPLICIT);
    } else {
      miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_SIGN_MODE,
          MiniAppletConstants.VALUE_SIGN_MODE_EXPLICIT);
    }

    // POLITICA DE FIRMA
    PolicyInfoSignature policy = commonInfoSignature.getPolicyInfoSignature();

    if (policy != null && policy.getPolicyIdentifier() == null
        && policy.getPolicyIdentifier().trim().length() != 0) {

      String oid = policy.getPolicyIdentifier();

      miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_POLICY_IDENTIFIER, oid);

      miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_POLICY_HASH,
          policy.getPolicyIdentifierHash());

      miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_POLICY_HASH_ALGORITHM,
          policy.getPolicyIdentifierHashAlgorithm());

      String val = policy.getPolicyUrlDocument();
      if (val != null) {
        miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_POLICY_QUALIFIER, val);
      }
    }

    String tipusFirma;
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())) {

      miniAppletProperties.setProperty("alwaysCreateRevision", "true");

      tipusFirma = MiniAppletConstants.VALUE_SIGN_TYPE_PADES;

      // POLITICA DE FIRMA PADES
      if (policy == null || policy.getPolicyIdentifier() == null
          || policy.getPolicyIdentifier().trim().length() == 0) {
        miniAppletProperties.setProperty("signatureSubFilter",
            MiniAppletConstants.PADES_SUBFILTER_BASIC);
      } else {
        miniAppletProperties.setProperty("signatureSubFilter",
            MiniAppletConstants.PADES_SUBFILTER_BES);
      }

      // PDF Visible
      PdfInfoSignature pdfSign = fileInfo.getPdfInfoSignature();
      if (pdfSign != null
          && pdfSign.getSignaturesTableLocation() != PdfInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {

        miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_SIGNATUREPAGE,
            String.valueOf(pdfSign.getSignaturesTableLocation()));

        PdfRubricRectangle rr = pdfSign.getPdfRubricRectangle();

        miniAppletProperties.setProperty(
            MiniAppletConstants.PROPERTY_SIGNATUREPOSITIONONPAGELOWERLEFTX,
            String.valueOf((int) rr.getLowerLeftX()));
        miniAppletProperties.setProperty(
            MiniAppletConstants.PROPERTY_SIGNATUREPOSITIONONPAGEUPPERRIGHTX,
            String.valueOf((int) rr.getUpperRightX()));
        miniAppletProperties.setProperty(
            MiniAppletConstants.PROPERTY_SIGNATUREPOSITIONONPAGELOWERLEFTY,
            String.valueOf((int) rr.getLowerLeftY()));
        miniAppletProperties.setProperty(
            MiniAppletConstants.PROPERTY_SIGNATUREPOSITIONONPAGEUPPERRIGHTY,
            String.valueOf((int) rr.getUpperRightY()));

        if (isLocalSignature) {
          byte[] signatureRubricImage;
          signatureRubricImage = pdfSign.getRubricGenerator().genenerateRubricImage(
              certificate, new Date());
          miniAppletProperties.setProperty(
              MiniAppletConstants.PROPERTY_SIGNATURE_RUBRIC_IMAGE,
              Base64.encode(signatureRubricImage));
        } else {
          miniAppletProperties.setProperty(
              MiniAppletConstants.PROPERTY_SIGNATURE_RUBRIC_IMAGE, rubricURL);
        }
      }

    } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(fileInfo.getSignType())) {
      // TODO Alguna cosa mes ???
      tipusFirma = MiniAppletConstants.VALUE_SIGN_TYPE_CADES;
    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(fileInfo.getSignType())) {
      // TODO Alguna cosa mes ???
      tipusFirma = MiniAppletConstants.VALUE_SIGN_TYPE_XADES;
    } else {
      // TODO Traduir
      throw new Exception("Tipus de firma no suportada: " + fileInfo.getSignType());
    }
    miniAppletProperties.setProperty(MiniAppletConstants.APPLET_SIGN_TYPE, tipusFirma);

    // ALGORISME DE FIRMA
    String algorisme;
    if (FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(fileInfo.getSignAlgorithm())) {
      algorisme = MiniAppletConstants.VALUE_SIGN_ALGORITHM_SHA1;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(fileInfo.getSignAlgorithm())) {
      algorisme = MiniAppletConstants.VALUE_SIGN_ALGORITHM_SHA256;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(fileInfo.getSignAlgorithm())) {
      algorisme = MiniAppletConstants.VALUE_SIGN_ALGORITHM_SHA384;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(fileInfo.getSignAlgorithm())) {
      algorisme = MiniAppletConstants.VALUE_SIGN_ALGORITHM_SHA512;
    } else {
      throw new Exception("Tipus d'algorisme no suportat " + fileInfo.getSignAlgorithm());
    }
    miniAppletProperties.setProperty(MiniAppletConstants.APPLET_SIGN_ALGORITHM, algorisme);

    // Segell de Temps (Segellat de temps)
    if (timeStampURL != null) {

      miniAppletProperties.setProperty("tsaURL", timeStampURL);

      ITimeStampGenerator timestampGenerator = fileInfo.getTimeStampGenerator();

      miniAppletProperties
          .setProperty("tsaPolicy", timestampGenerator.getTimeStampPolicyOID());

      final String CATCERT_REQUIRECERT = "" + Boolean.TRUE;
      miniAppletProperties.setProperty("tsaRequireCert", CATCERT_REQUIRECERT);

      miniAppletProperties.setProperty("tsaHashAlgorithm",
          timestampGenerator.getTimeStampHashAlgorithm());
      // Sello de tiempo a nivel de firma.
      miniAppletProperties.setProperty("tsType", "" + MiniAppletConstants.TS_SIGN);

    }

    byte[] pdf = FileUtils.readFromFile(fileInfo.getSource());

    info = new MiniAppletSignInfo(pdf, tipusFirma, algorisme, certificate,
        miniAppletProperties);

    return info;
  }

}
