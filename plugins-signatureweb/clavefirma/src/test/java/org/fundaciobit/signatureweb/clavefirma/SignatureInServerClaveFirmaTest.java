package org.fundaciobit.signatureweb.clavefirma;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author anadal
 *
 */
public class SignatureInServerClaveFirmaTest extends TestCase {
  /**
   * Create the test case
   *
   * @param testName
   *          name of the test case
   */
  public SignatureInServerClaveFirmaTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(SignatureInServerClaveFirmaTest.class);
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
      
      /*
      Properties properties = new Properties();
      
      properties.load(new FileInputStream("test.properties"));
      
      String username = properties.getProperty("username");
      String administrationID = properties.getProperty("administrationID");
      
      ClaveFirmaSignatureWebPlugin plugin;
      plugin = new ClaveFirmaSignatureWebPlugin("es.ibsalut.example.", properties);
      

      
      final String filter = "";
      boolean filtered = (plugin.filter(username, administrationID, filter) != 0);
      
      if (!filtered) {
        System.out.println("No ha passat el filtre del Plugin");
      } else {
        System.out.println("FILTRE OK");
      }
      
     

      Map<String, CertificateInfo> certs = plugin.listCertificates(username, administrationID);
      
      System.out.println("Certificats Size" + certs.size());

      for (CertificateInfo cinfo : certs.values()) {
        System.out.println(" ========================= ");
        System.out.println(cinfo.getDn_certificate());
        byte[] data = cinfo.getCertificate();

        String dn = cinfo.getDn_certificate();
        FileOutputStream file = new FileOutputStream(dn.substring(0, Math.max(dn.length(), 200)) + ".cer");
        
        file.write(data);
        
        file.flush();
        
        file.close();
      }
      */
      
    
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    
    

  }

}
