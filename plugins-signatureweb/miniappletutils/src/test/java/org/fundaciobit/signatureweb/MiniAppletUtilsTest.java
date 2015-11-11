package org.fundaciobit.signatureweb;

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
/*
    try {

      AbstractTriFaseSigner atps = new AbstractTriFaseSigner();
      byte[] inPDF = "lkasjdhalksjdfhalskdfj".getBytes();

      Properties extraParams = new Properties();

      final GregorianCalendar signTime = new GregorianCalendar();

      byte[] data = atps.invoke_PdfTimestamper_timestampPdf(inPDF, extraParams, signTime);
      
      System.out.println("BYTE TIME = " + new String(data));
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    */
  }

}
