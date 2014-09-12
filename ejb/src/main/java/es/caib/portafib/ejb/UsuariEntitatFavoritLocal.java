
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.UsuariEntitatFavoritJPA;
import es.caib.portafib.model.dao.IUsuariEntitatFavoritManager;

@Local
public interface UsuariEntitatFavoritLocal extends IUsuariEntitatFavoritManager {

 public static final String JNDI_NAME = "portafib/UsuariEntitatFavoritEJB/local";
  public UsuariEntitatFavoritJPA findByPrimaryKey(Long _ID_);
}
