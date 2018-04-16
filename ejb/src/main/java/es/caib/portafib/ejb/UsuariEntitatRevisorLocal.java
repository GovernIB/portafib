
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.UsuariEntitatRevisorJPA;
import es.caib.portafib.model.dao.IUsuariEntitatRevisorManager;

@Local
public interface UsuariEntitatRevisorLocal extends IUsuariEntitatRevisorManager {

 public static final String JNDI_NAME = "portafib/UsuariEntitatRevisorEJB/local";
  public UsuariEntitatRevisorJPA findByPrimaryKey(Long _ID_);
}
