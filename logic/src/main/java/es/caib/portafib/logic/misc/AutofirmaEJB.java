package es.caib.portafib.logic.misc;

import java.util.HashMap;
import java.util.HashSet;
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
  
  
  @Override
  public void remove(Long id) {
    autofirmes.remove(id);
  }
  
  
  @Override
  public AutoFirmaBean get(Long id) {
    return autofirmes.get(id);
  }
  
  @Override
  public void put(Long id, AutoFirmaBean autoFirmaForm) {
    autofirmes.put(id, autoFirmaForm);
  }

  @Override
  public void cleanAutoFirmes() {
    
    log.debug("Entra a cleanAutoFirmes");
    
    Set<Long> time = autofirmes.keySet();
    // 30 minuts per firmar i descarregar
    long border = System.currentTimeMillis() - 30*60*1000;
    Set<Long> itemsToDelete = new HashSet<Long>();
    for (Long t : time) {
      if (t < border) {
        
        itemsToDelete.add(t);

      }
    }
    for (Long t : itemsToDelete) {
      autofirmes.remove(t);  
    }
    
  }
  
  
}
