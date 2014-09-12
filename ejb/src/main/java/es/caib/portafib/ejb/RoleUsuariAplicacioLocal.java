
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.RoleUsuariAplicacioJPA;
import es.caib.portafib.model.dao.IRoleUsuariAplicacioManager;

@Local
public interface RoleUsuariAplicacioLocal extends IRoleUsuariAplicacioManager {

 public static final String JNDI_NAME = "portafib/RoleUsuariAplicacioEJB/local";
  public RoleUsuariAplicacioJPA findByPrimaryKey(Long _ID_);
}
