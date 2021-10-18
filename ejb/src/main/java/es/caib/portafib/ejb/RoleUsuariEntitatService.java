
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.RoleUsuariEntitatJPA;
import es.caib.portafib.persistence.RoleUsuariEntitatIJPAManager;
import es.caib.portafib.model.dao.IRoleUsuariEntitatManager;

@Local
public interface RoleUsuariEntitatService extends RoleUsuariEntitatIJPAManager,IRoleUsuariEntitatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/RoleUsuariEntitatEJB!es.caib.portafib.ejb.RoleUsuariEntitatService";

    public RoleUsuariEntitatJPA findByPrimaryKey(Long _ID_);
}
