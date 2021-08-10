
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.model.dao.IUsuariEntitatManager;

@Local
public interface UsuariEntitatLocal extends IUsuariEntitatManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/UsuariEntitatEJB";

  public UsuariEntitatJPA findByPrimaryKey(String _ID_);
}
