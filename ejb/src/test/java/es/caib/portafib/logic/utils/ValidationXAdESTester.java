package es.caib.portafib.logic.utils;

import es.caib.portafib.logic.utils.datasource.FileDataSource;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.v3.utils.CertificateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Locale;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ValidationXAdESTester {

  protected static final Logger log = Logger.getLogger(ValidationXAdESTester.class);

  static {
    I18NCommonUtils.BUNDLES = new String[] { "logicmissatges", "portafib_genapp", "genapp" };
  }

  public static void main(String[] args) {

    File f = new File("roda_orig.png_signed.xsig");
    // File f = new File("sample_xades_attached_firmat_ts.xml");

    try {
      extractDocumentOfXades(f);
      extractCertificates(f);
    } catch (I18NException e) {

      log.error(I18NCommonUtils.getMessage(e, new Locale("ca")), e);

    }

  }

  public static X509Certificate[] extractCertificates(File xml) throws I18NException {

    FileInputStream in;
    try {
      in = new FileInputStream(xml);
    } catch (FileNotFoundException e) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Error llegint fitxer " + xml + ": "
          + e.getMessage());
    }

    // write to file.
    X509Certificate[] certificates = ValidationsXAdES.getCertificatesOfXadesSignature(in);

    if(log.isDebugEnabled()) {
      log.debug("extractCertificates(XML)::NIF => " + CertificateUtils.getDNI(certificates[0]));
    }

    return certificates;
  }

  public static void extractDocumentOfXades(File xml) throws I18NException {

    FileInputStream in;
    try {
      in = new FileInputStream(xml);
    } catch (FileNotFoundException e) {
      throw new I18NException("genapp.comodi", "Error llegint fitxer " + xml + ": "
          + e.getMessage());
    }


    byte[] bytes = ValidationsXAdES.getOriginalDocumentOfXadesAttachedSignature(in);

    File target = new File(xml.getName() + ".document_original.bin");

    try {
      FileOutputStream fos = new FileOutputStream(target);
      fos.write(bytes);
      fos.flush();
      fos.close();
    } catch (Exception e) {
      throw new I18NException("genapp.comodi", "Error escrivint fitxer " + target + ": "
          + e.getMessage());
    }

    // flush & close writers
    // ...

    System.out.println("Document guardat a " + target.getAbsolutePath());

  }

  @Test
  public void testProcesarDocumentOriginalXAdES() throws I18NException, URISyntaxException {

    URL originalUrl = getClass().getResource("/sample.xml");
    IPortaFIBDataSource dataSource = new FileDataSource(new File(originalUrl.toURI()));

    byte[] originalData = ValidationsXAdES.getProcessedOriginalData(dataSource);

    byte[] sampleData = ("<catalog>\r\n" +
          "  <book id=\"bk101\">\r\n" +
          "    <author>Gambardella, Matthew</author>\r\n" +
          "    <title>XML Developer's Guide</title>\r\n" +
          "    <genre>Computer</genre>\r\n" +
          "    <price>44.95</price>\r\n" +
          "    <publish_date>2000-10-01</publish_date>\r\n" +
          "    <description>An in-depth look at creating applications with XML.</description>\r\n" +
          "  </book>\r\n" +
          "</catalog>").getBytes();

    System.out.println(new String(originalData));
    System.out.println(new String(sampleData));

    Assert.assertArrayEquals(sampleData, originalData);
    /*
    try {
      // Internament els mètodes empren 'integra' que només té missatges en castellà.
      Locale.setDefault(new Locale("es", "ES"));

      InputStream signatura = getClass().getResourceAsStream("/xades_attached_sample_xml.xsig");
      byte[] originalFromSignature = ValidationsXAdES.getOriginalDocumentOfXadesAttachedSignature(signatura);

      InputStream original = getClass().getResourceAsStream("/sample.xml");

      Assert.assertTrue(IOUtils.contentEquals(original, new ByteArrayInputStream(originalFromSignature)));

    } catch (I18NException e) {
      Assert.fail(I18NCommonUtils.getMessage(e, new Locale("ca")));
    }
    */
  }

  @Test
  public void testExtractCertificateSignResign() throws I18NException {

    Locale.setDefault(new Locale("es", "ES"));

    InputStream inputStream = getClass().getResourceAsStream("/pdf-xades-signat-resignat.xsig");
    X509Certificate[] certificates = ValidationsXAdES.getCertificatesOfXadesSignature(inputStream);
    for (X509Certificate certificate : certificates) {
      System.out.println(certificate.getSubjectDN());
    }
    Assert.assertEquals(2, certificates.length);
  }

  @Test
  public void testExtractCertificateSignCosign() throws I18NException {

    Locale.setDefault(new Locale("es", "ES"));

    InputStream inputStream = getClass().getResourceAsStream("/pdf-xades-signat-cosignat.xsig");
    X509Certificate[] certificates = ValidationsXAdES.getCertificatesOfXadesSignature(inputStream);
    for (X509Certificate certificate : certificates) {
      System.out.println(certificate.getSubjectDN());
    }
    Assert.assertEquals(2, certificates.length);
  }

  @Test
  public void testNumberOfSignaturesSignResign() throws I18NException {
    InputStream inputStream = getClass().getResourceAsStream("/pdf-xades-signat-resignat.xsig");
    Assert.assertEquals(2, ValidationsXAdES.getNumberOfXADESSignatures(inputStream));
  }

  @Test
  public void testNumberOfSignaturesSignCosign() throws I18NException {
    InputStream inputStream = getClass().getResourceAsStream("/pdf-xades-signat-cosignat.xsig");
    Assert.assertEquals(2, ValidationsXAdES.getNumberOfXADESSignatures(inputStream));
  }

  @Test
  public void testExtractCertificateXades() throws I18NException {
    String[] names = {
            "/xades_attached_binary_autofirma.xsig",
            "/xades_attached_sample_xml.xsig",
            "/xades_dettached_binary_europe.xml",
            "/xades_idettached_binary_autofirma.xsig",
            "/xades_idettached_xml_autofirma.xsig",
            "/xades_idettached_xml_europe.xml"};
    for (String name : names) {
      System.out.println(name);
      InputStream inputStream = getClass().getResourceAsStream(name);
      try {
        X509Certificate[] certificates = ValidationsXAdES.getCertificatesOfXadesSignature(inputStream);
        Assert.assertEquals(1, certificates.length);
      } finally {
        try {
          inputStream.close();
        } catch (IOException ignored) {}
      }
    }
  }
}
