package es.caib.portafib.logic.misc;

import javax.ejb.Local;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface AutofirmaLocal {
  
  public AutoFirmaBean get(Long id);
  
  public void put(Long id, AutoFirmaBean autoFirmaForm);
  
  public void cleanAutoFirmes();

}
