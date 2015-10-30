package org.fundaciobit.plugins.signatureweb.miniappletutils;

import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.PdfInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.PdfRubricRectangle;
import org.fundaciobit.plugins.signatureweb.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.utils.Base64;
import org.fundaciobit.plugins.utils.FileUtils;

import es.gob.afirma.core.signers.AOSignConstants;

/**
 * 
 * @author anadal
 *
 */
public class MiniAppletUtils {

  public static final String PROPERTY_POLICY_IDENTIFIER = "policyIdentifier"; //$NON-NLS-1$

  public static final String PROPERTY_POLICY_HASH_ALGORITHM = "policyIdentifierHashAlgorithm"; //$NON-NLS-1$

  public static final String PROPERTY_POLICY_HASH = "policyIdentifierHash"; //$NON-NLS-1$

  public static final String PROPERTY_POLICY_QUALIFIER = "policyQualifier"; //$NON-NLS-1$

  public static final String PROPERTY_POLICY_DESCRIPTION = "policyDescription"; //$NON-NLS-1$
  
  
  private final static Logger log = Logger.getLogger(MiniAppletUtils.class.getName());

  public static MiniAppletSignInfo convert(SignaturesSet signaturesSet,
      FileInfoSignature fileInfo, X509Certificate certificate,
      boolean isLocalSignature) throws Exception {
    MiniAppletSignInfo info;

    Properties xParams = new Properties();

    xParams.setProperty("mode", "implicit");
    xParams.setProperty("alwaysCreateRevision", "true");

    
    // POLITICA DE FIRMA
    PolicyInfoSignature policy = signaturesSet.getCommonInfoSignature().getPolicyInfoSignature();
    
    if (policy == null || policy.getPolicyIdentifier() == null
        || policy.getPolicyIdentifier().trim().length() == 0) {

      xParams.setProperty("signatureSubFilter", AOSignConstants.PADES_SUBFILTER_BASIC);
    } else {
      
      String oid = policy.getPolicyIdentifier();
      xParams.setProperty("signatureSubFilter", AOSignConstants.PADES_SUBFILTER_BES);

      log.info("XYZ -- Aplicant politica de firma " + oid);
      
      xParams.setProperty(PROPERTY_POLICY_IDENTIFIER, oid);

      xParams.setProperty(PROPERTY_POLICY_HASH, policy.getPolicyIdentifierHash());

      xParams.setProperty(PROPERTY_POLICY_HASH_ALGORITHM, policy.getPolicyIdentifierHashAlgorithm());

      String val = policy.getPolicyUrlDocument();
      if (val != null) {        
        xParams.setProperty(PROPERTY_POLICY_QUALIFIER, val);
      }
    }


    
    
    String tipusFirma;
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())) {
      
        tipusFirma = AOSignConstants.SIGN_FORMAT_PDF;

        // PDF Visible
        PdfInfoSignature pdfSign = fileInfo.getPdfInfoSignature();
        if (pdfSign != null
            && pdfSign.getSignaturesTableLocation() != PdfInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {

          // XYZ S'ha de moure a lloc comu i fer switch
          xParams.setProperty("signaturePage",
              String.valueOf(pdfSign.getSignaturesTableLocation()));

          PdfRubricRectangle rr = pdfSign.getPdfRubricRectangle();

          xParams.setProperty("signaturePositionOnPageLowerLeftX",
              String.valueOf((int) rr.getLowerLeftX()));
          xParams.setProperty("signaturePositionOnPageUpperRightX",
              String.valueOf((int) rr.getUpperRightX()));
          xParams.setProperty("signaturePositionOnPageLowerLeftY",
              String.valueOf((int) rr.getLowerLeftY()));
          xParams.setProperty("signaturePositionOnPageUpperRightY",
              String.valueOf((int) rr.getUpperRightY()));

          if (isLocalSignature) {
            byte[] signatureRubricImage;
            signatureRubricImage = pdfSign.getRubricGenerator().genLocalRubricGeneration(
                certificate, new Date());
  
            xParams.setProperty("signatureRubricImage", Base64.encode(signatureRubricImage));
          } else {
            xParams.setProperty("signatureRubricImage", pdfSign.getRubricGenerator().getRemoteURLRubricGeneration());
          }
        }
        
    } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(fileInfo.getSignType())) {
      // TODO Alguna cosa mes ???
      tipusFirma = AOSignConstants.SIGN_FORMAT_CADES;
    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(fileInfo.getSignType())) {
      // TODO Alguna cosa mes ???
      tipusFirma = AOSignConstants.SIGN_FORMAT_XADES;
    } else {
      // TODO Traduir
      throw new Exception("Tipus de firma no suportada: " + fileInfo.getSignType());
    }
    
    xParams.setProperty("signType", tipusFirma);

    // ALGORISME DE FIRMA
    String algorisme;
    if (FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(fileInfo.getSignAlgorithm())) {
        algorisme = AOSignConstants.SIGN_ALGORITHM_SHA1WITHRSA;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(fileInfo.getSignAlgorithm())) {
        algorisme = AOSignConstants.SIGN_ALGORITHM_SHA256WITHRSA;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(fileInfo.getSignAlgorithm())) {
        algorisme = AOSignConstants.SIGN_ALGORITHM_SHA384WITHRSA;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(fileInfo.getSignAlgorithm())) {
        algorisme = AOSignConstants.SIGN_ALGORITHM_SHA512WITHRSA;
    } else  {
        throw new Exception("Tipus d'algorisme no suportat " + fileInfo.getSignAlgorithm());
    }
    xParams.setProperty("signAlgorithm", algorisme);

    byte[] pdf = FileUtils.readFromFile(fileInfo.getSource());

    info = new MiniAppletSignInfo(pdf, algorisme, certificate, xParams);

    return info;
  }

}
