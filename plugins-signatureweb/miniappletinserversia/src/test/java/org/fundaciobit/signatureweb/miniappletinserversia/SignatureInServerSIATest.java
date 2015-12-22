package org.fundaciobit.signatureweb.miniappletinserversia;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author anadal
 *
 */
public class SignatureInServerSIATest extends TestCase {
  /**
   * Create the test case
   *
   * @param testName
   *          name of the test case
   */
  public SignatureInServerSIATest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(SignatureInServerSIATest.class);
  }

  /**
   * Rigourous Test :-)
   */
  public void testApp() {
    assertTrue(true);
  }

  public static void main(String[] args) {

    // Firma SIA no es possible fer test ja que:
    // (1) Necessitam un entorn WEB en la part de client
    // (2) Necessitam un entorn de CallBack en el servidor

  }

}
