
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.NotificacioWSJPA;
import es.caib.portafib.persistence.NotificacioWSIJPAManager;
import es.caib.portafib.model.dao.INotificacioWSManager;

@Local
public interface NotificacioWSService extends NotificacioWSIJPAManager,INotificacioWSManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/NotificacioWSEJB!es.caib.portafib.ejb.NotificacioWSService";

    public NotificacioWSJPA findByPrimaryKey(Long _ID_);
}
