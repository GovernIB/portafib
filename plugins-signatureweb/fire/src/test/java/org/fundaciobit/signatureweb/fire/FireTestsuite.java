package org.fundaciobit.signatureweb.fire;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author anadal
 *
 */
public class FireTestsuite extends TestCase {
  /**
   * Create the test case
   *
   * @param testName
   *          name of the test case
   */
  public FireTestsuite(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(FireTestsuite.class);
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

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
