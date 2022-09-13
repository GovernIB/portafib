
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.UsuariEntitatFavoritJPA;
import es.caib.portafib.persistence.UsuariEntitatFavoritIJPAManager;
import es.caib.portafib.model.dao.IUsuariEntitatFavoritManager;

import es.caib.portafib.model.entity.UsuariEntitatFavorit;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariEntitatFavoritService extends UsuariEntitatFavoritIJPAManager,IUsuariEntitatFavoritManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/UsuariEntitatFavoritEJB!es.caib.portafib.ejb.UsuariEntitatFavoritService";

    public UsuariEntitatFavoritJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(UsuariEntitatFavorit instance, FitxerService fitxerEjb) throws I18NException;
}
