package es.caib.portafib.logic.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.cert.X509Certificate;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.fundaciobit.pluginsib.core.utils.FileUtils;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ValidationsCAdESTester {
  
  protected static final Logger log = Logger.getLogger(ValidationsCAdESTester.class);

  

  public static void main(String[] args) {

    try {

      // String doc= "foto.jpg";
      // String csig = "foto.jpg_cades_detached_ts.csig";

      //String doc = null;
      //String csig = "firma_implicita.csig";
      
      
      //String doc = "data";
      //String csig = "firma_explicita.csig";
       
       //String doc = "data";
       // String csig = "autofirma_explicit_data_signed.csig";
      
      String doc = null;
      String csig = "autofirma_implicit_data_signed.csig";
      

      byte[] document = doc == null ? null : FileUtils.readFromFile(new File(doc));
      File eSignature = new File(csig);

      X509Certificate[] certificates = ValidationsCAdES.getCertificatesOfCadesSignature(new FileInputStream(
          eSignature), document);

      // File target = new File(xml.getName() + ".signature");
      System.out.println("NIF => " + CertificateUtils.getDNI(certificates[0]));
      
      if (doc == null) {
        byte[] fitxeroriginal = ValidationsCAdES.getOriginalDocumentOfCadesAttachedSignature(new FileInputStream(eSignature));
        File target = new File(eSignature.getName() + ".document_original.bin");

        FileOutputStream fos = new FileOutputStream(target);
        fos.write(fitxeroriginal);
        fos.flush();
        fos.close();

        // flush & close writers
        // ...

        System.out.println("Document guardat a " + target.getAbsolutePath());
      }
      

      System.out.println("Final de Certificats ");
    
      
    } catch (I18NException e) {
      log.error(I18NCommonUtils.getMessage(e, new Locale("ca")), e);
    } catch(Exception e) {
      log.error(e.getMessage(), e);
    }

  }
  
}
