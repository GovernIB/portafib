
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.RebreAvisJPA;
import es.caib.portafib.model.dao.IRebreAvisManager;

@Local
public interface RebreAvisLocal extends IRebreAvisManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/RebreAvisEJB";

  public RebreAvisJPA findByPrimaryKey(Long _ID_);
}
