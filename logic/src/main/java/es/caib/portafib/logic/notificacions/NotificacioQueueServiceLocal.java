package es.caib.portafib.logic.notificacions;

import es.caib.portafib.logic.utils.NotificacioInfo;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;


/**
 * 
 * @author areus
 */
@Local
public interface NotificacioQueueServiceLocal {

  String JNDI_NAME = "java:app/portafib-logic/NotificacioQueueServiceEJB";

  long encolarNotificacionsPendents() throws I18NException;

  long recrearNotificacio(NotificacioInfo info) throws I18NException;

}

