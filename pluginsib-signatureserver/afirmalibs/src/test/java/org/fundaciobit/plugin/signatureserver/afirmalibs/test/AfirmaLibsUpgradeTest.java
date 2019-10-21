package org.fundaciobit.plugin.signatureserver.afirmalibs.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Properties;

import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.fundaciobit.plugins.signatureserver.afirmalibs.AfirmaLibsSignatureServerPlugin;
import org.fundaciobit.pluginsib.core.utils.FileUtils;

import es.gob.afirma.signers.tsp.pkcs7.CMSTimestamper;
import es.gob.afirma.signers.tsp.pkcs7.TsaParams;

/**
 * 
 * @author anadal
 *
 */
public class AfirmaLibsUpgradeTest {

  public static void main(String[] args) {

    try {

      byte[] fileToSign = getSimpleFileFromResource("hola_signat.pdf");

      CatCertTimeStampGenerator externalTimestamp = new CatCertTimeStampGenerator();

      byte[] pdf_t = new AfirmaLibsSignatureServerPlugin().upgradeSignature(fileToSign, null,
          SignatureTypeFormEnumForUpgrade.PAdES_T_LEVEL, externalTimestamp, null);

      FileOutputStream fos = new FileOutputStream("hola_PADES_T.pdf");
      fos.write(pdf_t);
      fos.flush();
      fos.close();

      System.out.println(" OK ");

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /*
   * 
   * public void addLtv(byte[] src, String dest, OcspClient ocsp, CrlClient crl, TSAClient tsa)
   * throws IOException, DocumentException, GeneralSecurityException { PdfReader r = new
   * PdfReader(src); FileOutputStream fos = new FileOutputStream(dest); PdfStamper stp =
   * PdfStamper.createSignature(r, fos, '\0', null, true); LtvVerification v =
   * stp.getLtvVerification(); AcroFields fields = stp.getAcroFields(); List<String> names =
   * fields.getSignatureNames(); String sigName = names.get(names.size() - 1); PdfPKCS7 pkcs7 =
   * fields.verifySignature(sigName); if (pkcs7.isTsp()) { v.addVerification(sigName, ocsp,
   * crl, LtvVerification.CertificateOption.SIGNING_CERTIFICATE,
   * LtvVerification.Level.OCSP_CRL, LtvVerification.CertificateInclusion.NO); } else { for
   * (String name : names) { v.addVerification(name, ocsp, crl,
   * LtvVerification.CertificateOption.WHOLE_CHAIN, LtvVerification.Level.OCSP_CRL,
   * LtvVerification.CertificateInclusion.NO); } } PdfSignatureAppearance sap =
   * stp.getSignatureAppearance(); LtvTimestamp.timestamp(sap, tsa, null); }
   */

  public static class CatCertTimeStampGenerator implements ITimeStampGenerator {

    @Override
    public byte[] getTimeStamp(byte[] data, Calendar cal) throws Exception {

      Properties properties = new Properties();

      properties.setProperty("tsaURL", "http://psis.catcert.net/psis/catcert/tsp");
      properties.setProperty("tsaHashAlgorithm", "SHA-512");

      // NOMÉS FORMULARI
      // properties.setProperty("tsaURL", "http://tsa.belgium.be/connect");
      // properties.setProperty("tsaHashAlgorithm","SHA-256");

      // properties.setProperty("tsaURL", "http://demo.sk.ee/tsa/");
      // properties.setProperty("tsaHashAlgorithm","SHA-256");

      final String CATCERT_REQUIRECERT = "" + Boolean.TRUE;
      properties.setProperty("tsaRequireCert", CATCERT_REQUIRECERT);

      // Sello de tiempo a nivel de doc + firma.
      properties.setProperty("tsType", "" + TsaParams.TS_SIGN_DOC);

      // Obtenemos el sellador de tiempo
      final TsaParams tsaParams = new TsaParams(properties);
      final CMSTimestamper timestamper = new CMSTimestamper(tsaParams);

      // Obtenemos el token TSP
      return timestamper.getTimeStampToken(
          MessageDigest.getInstance(tsaParams.getTsaHashAlgorithm()).digest(data),
          tsaParams.getTsaHashAlgorithm(), cal);

    }

    @Override
    public String getTimeStampPolicyOID() {
      return null;
    }

    @Override
    public String getTimeStampHashAlgorithm() {
      return "SHA-512";
    }

  }

  public static byte[] getSimpleFileFromResource(String fileName) throws Exception {

    InputStream is = FileUtils.readResource(AfirmaLibsUpgradeTest.class, "testfiles/"
        + fileName);
    File tmp = File.createTempFile("testFile", fileName);
    tmp.deleteOnExit();
    ByteArrayOutputStream fos = new ByteArrayOutputStream();

    FileUtils.copy(is, fos);

    return fos.toByteArray();
  }

}
