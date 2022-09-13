
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.RoleJPA;
import es.caib.portafib.persistence.RoleIJPAManager;
import es.caib.portafib.model.dao.IRoleManager;

import es.caib.portafib.model.entity.Role;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface RoleService extends RoleIJPAManager,IRoleManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/RoleEJB!es.caib.portafib.ejb.RoleService";

    public RoleJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(Role instance, FitxerService fitxerEjb) throws I18NException;
}
