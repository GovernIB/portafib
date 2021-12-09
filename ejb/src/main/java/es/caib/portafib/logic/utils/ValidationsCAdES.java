package es.caib.portafib.logic.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.util.Store;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.cert.X509Certificate;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ValidationsCAdES {

  public static byte[] getOriginalDocumentOfCadesAttachedSignature(InputStream eSignature)
      throws I18NException {

    try {

      CMSSignedData signedData = null;

      signedData = new CMSSignedData(eSignature);
      CMSTypedData data = signedData.getSignedContent();

      if (data == null) {
        // Detached -- Se exige que se suministre el documento
        // XYZ ZZZ ZZZ TRA
        throw new Exception("Se exige que se suministre el documento");
      }

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      data.write(baos);
      baos.close();

      return baos.toByteArray();

    } catch (Exception e) {
      // XYZ ZZZ TRA
      throw new I18NException(
          "genapp.comodi",
          "Error desconegut a l'hora d´extreure el document Original d'una Firma"
          + " CAdEs-Attached certificats d'una firma CAdES "
              + e.getMessage());
    }

  }

  /**
   * Verifies a CAdES signature.
   * 
   * @param eSignature
   *          signature data.
   * @param document
   *          document source (before it was signed). Optional for implicit signatures.
   * @return true if signature is verified and false otherwise.
   * @throws SigningException
   *           in error case.
   */
  public static X509Certificate[] getCertificatesOfCadesSignature(InputStream eSignature,
      byte[] document) throws I18NException {
    if (eSignature == null) {
      throw new I18NException("genapp.comodi",
          "No es pot extreure els certificats d'una firma CAdES ja que la firma val null");
    }

    try {
      // Creación del objeto que representa el mensaje PKCS7-Signature.
      CMSSignedData signedData = null;
      if (document != null) {
        // Convertir a CMSProcessableFile
        signedData = new CMSSignedData(new CMSProcessableByteArray(document), eSignature);
      } else {
        signedData = new CMSSignedData(eSignature);
        if (signedData.getSignedContent() == null) {
          // Detached -- Se exige que se suministre el documento
          // XYZ ZZZ ZZZ TRA
          throw new Exception("Se exige que se suministre el documento");
        }
      }

      // validación de todos los firmantes que contiene la firma.
      return getCertificatesOfCadesSignature(signedData.getSignerInfos(),
          signedData.getCertificates());

    } catch (Exception e) {

      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi",
          "Error desconegut a l'hora d´extreure els certificats d'una firma CAdES "
              + e.getMessage());
    }
  }

  /**
   * Verifies a collection of signers.
   * 
   * @param signerInfos
   *          collection of SignerInformation instances.
   * @param certificates
   *          collection of certificates used by signers.
   * @return true if the signer information is verified, false otherwise.
   * @throws SigningException
   *           in error case.
   */
  private static X509Certificate[] getCertificatesOfCadesSignature(
      SignerInformationStore signerInfos, Store certificates) throws Exception {
    List<X509Certificate> certificatesList = new ArrayList<X509Certificate>();
    try {
      for (Iterator<?> iterator = signerInfos.getSigners().iterator(); iterator.hasNext();) {
        SignerInformation signerInformation = (SignerInformation) iterator.next();
        X509CertificateHolder x509certificateholder = getCertificateBySignerId(certificates,
            signerInformation.getSID());
        if (x509certificateholder == null) {

          throw new Exception("El certificat per l'ID " + signerInformation.getSID()
              + " es NULL");
        }

        X509Certificate x509certificate = CertificateUtils
            .decodeCertificate(new ByteArrayInputStream(x509certificateholder.getEncoded()));
        // new
        // JcaX509CertificateConverter().setProvider("BC").getCertificate(x509certificateholder);

        System.out.println("CERT => " + x509certificate.getSubjectDN());

        certificatesList.add(x509certificate);

        // XYZ ZZZ ZZZ PENDNETS LES CONTRAFIRMES

        // SignerInformationVerifier signerInformationVerifier;
        //
        // signerInformationVerifier = new JcaSimpleSignerInfoVerifierBuilder().setProvider(
        // BC_PROVIDER).build(x509certificate);

        // isValid = signerInformation.verify(signerInformationVerifier);
        // if (!isValid) {
        // return false;
        // }
        // validación de todas las contrafirmas.
        // SignerInformationStore counterSignatures = signerInformation.getCounterSignatures();
        // if (!counterSignatures.getSigners().isEmpty()) {
        // isValid = verifySignerInfos(counterSignatures, certificates);
        // }
      }

      return certificatesList.toArray(new X509Certificate[certificatesList.size()]);

    } catch (Exception e) {

      throw new Exception(e.getMessage(), e);
    }

  }

  /**
   * Gets a certificate from a certificate store.
   * 
   * @param certificates
   *          store with a collection of certificates.
   * @param signerId
   *          signer identifier.
   * @return the searched certificate
   */
  private static X509CertificateHolder getCertificateBySignerId(Store<?> certificates,
      SignerId signerId) {
    if (certificates != null && certificates.getMatches(null) != null && signerId != null) {
      for (Iterator<?> iterator = certificates.getMatches(null).iterator(); iterator.hasNext();) {
        X509CertificateHolder cert = (X509CertificateHolder) iterator.next();
        if (signerId.match(cert)) {
          return cert;
        }
      }
    }
    return null;
  }
}
