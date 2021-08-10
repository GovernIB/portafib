
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.UsuariEntitatFavoritJPA;
import es.caib.portafib.model.dao.IUsuariEntitatFavoritManager;

@Local
public interface UsuariEntitatFavoritLocal extends IUsuariEntitatFavoritManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/UsuariEntitatFavoritEJB";

  public UsuariEntitatFavoritJPA findByPrimaryKey(Long _ID_);
}
