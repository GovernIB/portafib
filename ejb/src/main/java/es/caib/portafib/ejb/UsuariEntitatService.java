
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariEntitatIJPAManager;
import es.caib.portafib.model.dao.IUsuariEntitatManager;

@Local
public interface UsuariEntitatService extends UsuariEntitatIJPAManager,IUsuariEntitatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/UsuariEntitatEJB!es.caib.portafib.ejb.UsuariEntitatService";

    public UsuariEntitatJPA findByPrimaryKey(String _ID_);
}
