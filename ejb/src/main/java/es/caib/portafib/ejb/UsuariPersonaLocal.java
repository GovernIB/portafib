
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.model.dao.IUsuariPersonaManager;

@Local
public interface UsuariPersonaLocal extends IUsuariPersonaManager {

 public static final String JNDI_NAME = "portafib/UsuariPersonaEJB/local";
  public UsuariPersonaJPA findByPrimaryKey(String _ID_);
}
