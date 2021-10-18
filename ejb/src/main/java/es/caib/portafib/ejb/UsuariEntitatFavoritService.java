
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.UsuariEntitatFavoritJPA;
import es.caib.portafib.persistence.UsuariEntitatFavoritIJPAManager;
import es.caib.portafib.model.dao.IUsuariEntitatFavoritManager;

@Local
public interface UsuariEntitatFavoritService extends UsuariEntitatFavoritIJPAManager,IUsuariEntitatFavoritManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/UsuariEntitatFavoritEJB!es.caib.portafib.ejb.UsuariEntitatFavoritService";

    public UsuariEntitatFavoritJPA findByPrimaryKey(Long _ID_);
}
