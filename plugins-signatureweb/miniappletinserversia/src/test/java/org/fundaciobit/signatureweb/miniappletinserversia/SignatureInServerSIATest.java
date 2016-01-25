package org.fundaciobit.signatureweb.miniappletinserversia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;

import org.fundaciobit.plugins.signatureweb.miniappletinserversia.MiniAppletInServerSIASignatureWebPlugin;

import com.openlandsw.rss.gateway.CertificateInfo;

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
    main(null);
  }

  public static void main(String[] args) {

    // Firma SIA no es possible fer test ja que:
    // (1) Necessitam un entorn WEB en la part de client
    // (2) Necessitam un entorn de CallBack en el servidor
    
    

    try {
      
      Properties properties = new Properties();
      
      properties.load(new FileInputStream("test.properties"));
      
      String username = properties.getProperty("username");
      String administrationID = properties.getProperty("administrationID");
      
      MiniAppletInServerSIASignatureWebPlugin plugin;
      plugin = new MiniAppletInServerSIASignatureWebPlugin("es.ibsalut.example.", properties);
      
      final String filter = "";
      final boolean supportJava = false; 
      boolean filtered = plugin.filter(null, username, administrationID, filter, supportJava);
      
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
      
      
    
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    
    

  }

}
