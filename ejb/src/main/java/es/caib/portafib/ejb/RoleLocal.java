
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.RoleJPA;
import es.caib.portafib.model.dao.IRoleManager;

@Local
public interface RoleLocal extends IRoleManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/RoleEJB";

  public RoleJPA findByPrimaryKey(String _ID_);
}
