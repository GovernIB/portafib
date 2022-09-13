
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PropietatGlobalJPA;
import es.caib.portafib.persistence.PropietatGlobalIJPAManager;
import es.caib.portafib.model.dao.IPropietatGlobalManager;

import es.caib.portafib.model.entity.PropietatGlobal;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PropietatGlobalService extends PropietatGlobalIJPAManager,IPropietatGlobalManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PropietatGlobalEJB!es.caib.portafib.ejb.PropietatGlobalService";

    public PropietatGlobalJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PropietatGlobal instance, FitxerService fitxerEjb) throws I18NException;
}
