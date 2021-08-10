
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager;

@Local
public interface UsuariAplicacioConfiguracioLocal extends IUsuariAplicacioConfiguracioManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/UsuariAplicacioConfiguracioEJB";

  public UsuariAplicacioConfiguracioJPA findByPrimaryKey(Long _ID_);
}
