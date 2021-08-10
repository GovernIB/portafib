
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.model.dao.IUsuariAplicacioManager;

@Local
public interface UsuariAplicacioLocal extends IUsuariAplicacioManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/UsuariAplicacioEJB";

  public UsuariAplicacioJPA findByPrimaryKey(String _ID_);
}
