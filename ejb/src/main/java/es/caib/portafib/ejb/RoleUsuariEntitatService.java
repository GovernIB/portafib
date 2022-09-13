
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.RoleUsuariEntitatJPA;
import es.caib.portafib.persistence.RoleUsuariEntitatIJPAManager;
import es.caib.portafib.model.dao.IRoleUsuariEntitatManager;

import es.caib.portafib.model.entity.RoleUsuariEntitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface RoleUsuariEntitatService extends RoleUsuariEntitatIJPAManager,IRoleUsuariEntitatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/RoleUsuariEntitatEJB!es.caib.portafib.ejb.RoleUsuariEntitatService";

    public RoleUsuariEntitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(RoleUsuariEntitat instance, FitxerService fitxerEjb) throws I18NException;
}
