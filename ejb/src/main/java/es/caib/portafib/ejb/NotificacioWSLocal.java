
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.NotificacioWSJPA;
import es.caib.portafib.model.dao.INotificacioWSManager;

@Local
public interface NotificacioWSLocal extends INotificacioWSManager {

 public static final String JNDI_NAME = "portafib/NotificacioWSEJB/local";
  public NotificacioWSJPA findByPrimaryKey(Long _ID_);
}