package es.caib.portafib.logic.misc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "AutofirmaEJB")
@SecurityDomain("seycon")
public class AutofirmaEJB implements AutofirmaLocal {
  
  protected Logger log = Logger.getLogger(getClass());

  private static Map<Long,AutoFirmaBean> autofirmes = new HashMap<Long, AutoFirmaBean>();
  
  
  
  public AutoFirmaBean get(Long id) {
    return autofirmes.get(id);
  }
  
  
  public void put(Long id, AutoFirmaBean autoFirmaForm) {
    autofirmes.put(id, autoFirmaForm);
  }


  public void cleanAutoFirmes() {
    
    log.info("Entra a cleanAutoFirmes");
    
    Set<Long> time = autofirmes.keySet();
    
    long border = System.currentTimeMillis() - 24*60*60*1000;
    Set<Long> itemsToDelete = new HashSet<Long>();
    for (Long t : time) {
      if (t < border) {
        log.info("cleanAutoFirmes: borrant " + t);
        AutoFirmaBean form = autofirmes.get(t);
        // TODO Clean autofirmes
        
        List<File> files = new ArrayList<File>();
        if (form.fitxerAFirmarIDFile != null) {
          files.add(form.fitxerAFirmarIDFile);
        }
        if (form.taulaDeFirmesFile != null) {
          files.add(form.taulaDeFirmesFile);
        }
        if (form.signedFile != null) {
          files.add(form.signedFile);
        }
        
        if (form.attachmentsFiles != null) {
          for (int i = 0; i < form.attachmentsFiles.length; i++) {
            files.add(form.attachmentsFiles[i]);  
          } 
        }

        for (File file : files) {
          try {            
           if (!file.delete()) {
             file.deleteOnExit();
           }            
          } catch(Exception e) {
            log.error("Error borrant fitxers de autofirmes: " + e.getMessage(), e);
          }
        }
        
        itemsToDelete.add(t);

      }
    }
    for (Long t : itemsToDelete) {
      autofirmes.remove(t);  
    }
    
  }
  
  
}
