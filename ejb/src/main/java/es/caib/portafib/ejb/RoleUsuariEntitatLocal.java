
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.model.dao.IRoleUsuariEntitatManager;

@Local
public interface RoleUsuariEntitatLocal extends IRoleUsuariEntitatManager {

 public static final String JNDI_NAME = "portafib/RoleUsuariEntitatEJB/local";
  public RoleUsuariEntitatJPA findByPrimaryKey(Long _ID_);
}
