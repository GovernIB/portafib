
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.RoleJPA;
import es.caib.portafib.persistence.RoleIJPAManager;
import es.caib.portafib.model.dao.IRoleManager;

@Local
public interface RoleService extends RoleIJPAManager,IRoleManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/RoleEJB!es.caib.portafib.ejb.RoleService";

    public RoleJPA findByPrimaryKey(String _ID_);
}
