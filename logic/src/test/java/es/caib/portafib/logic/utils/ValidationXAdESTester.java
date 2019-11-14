package es.caib.portafib.logic.utils;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
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
      throw new I18NException("genapp.comodi", "Error llegint fitxer " + xml + ": "
          + e.getMessage());
    }

    // write to file.
    X509Certificate[] certificates = ValidationsXAdES.getCertificatesOfXadesSignature(in);

    // File target = new File(xml.getName() + ".signature");
    log.info("XYZ ZZZ ZZZ NIF => " + CertificateUtils.getDNI(certificates[0]));

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
  public void testProcesarDocumentOriginalXAdES() throws I18NException {

    InputStream original = getClass().getResourceAsStream("/sample.xml");
    byte[] originalData = ValidationsXAdES.getProcessedOriginalData(original);

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
}
