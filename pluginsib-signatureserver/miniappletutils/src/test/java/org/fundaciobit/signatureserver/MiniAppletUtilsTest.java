package org.fundaciobit.signatureserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.cert.X509Certificate;

import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletUtils;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author anadal
 *
 */
public class MiniAppletUtilsTest extends TestCase {
  /**
   * Create the test case
   *
   * @param testName
   *          name of the test case
   */
  public MiniAppletUtilsTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(MiniAppletUtilsTest.class);
  }

  /**
   * Rigourous Test :-)
   */
  public void testApp() {
    assertTrue(true);
    main(null);
  }

  public static void main(String[] args) {

    try {
      String filePath = "DNIe_firma.cer";
      InputStream certstream = new FileInputStream(new File(filePath));
      X509Certificate certificate1 = CertificateUtils.decodeCertificate(certstream);

      String filter = "filter=issuer.rfc2254:|(cn=AC DNIE 001)(cn=AC DNIE 002)(cn=AC DNIE 003)";

      Boolean match = MiniAppletUtils.matchFilter(certificate1, filter);

      System.out.println(" FINAL :: match => " + match);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
