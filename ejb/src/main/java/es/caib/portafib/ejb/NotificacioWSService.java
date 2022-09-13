
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.NotificacioWSJPA;
import es.caib.portafib.persistence.NotificacioWSIJPAManager;
import es.caib.portafib.model.dao.INotificacioWSManager;

import es.caib.portafib.model.entity.NotificacioWS;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface NotificacioWSService extends NotificacioWSIJPAManager,INotificacioWSManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/NotificacioWSEJB!es.caib.portafib.ejb.NotificacioWSService";

    public NotificacioWSJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(NotificacioWS instance, FitxerService fitxerEjb) throws I18NException;
}
