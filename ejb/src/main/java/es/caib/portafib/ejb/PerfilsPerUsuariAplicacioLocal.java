
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PerfilsPerUsuariAplicacioJPA;
import es.caib.portafib.model.dao.IPerfilsPerUsuariAplicacioManager;

@Local
public interface PerfilsPerUsuariAplicacioLocal extends IPerfilsPerUsuariAplicacioManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/PerfilsPerUsuariAplicacioEJB";

  public PerfilsPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_);
}
