
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.model.dao.IFitxerManager;

@Local
public interface FitxerLocal extends IFitxerManager {

 public static final String JNDI_NAME = "portafib/FitxerEJB/local";
  public FitxerJPA findByPrimaryKey(Long _ID_);
}
