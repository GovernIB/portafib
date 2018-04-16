
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager;

@Local
public interface UsuariAplicacioConfiguracioLocal extends IUsuariAplicacioConfiguracioManager {

 public static final String JNDI_NAME = "portafib/UsuariAplicacioConfiguracioEJB/local";
  public UsuariAplicacioConfiguracioJPA findByPrimaryKey(Long _ID_);
}
