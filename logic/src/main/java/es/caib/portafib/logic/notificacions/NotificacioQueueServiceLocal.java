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

  long encolarNotificacionsPendents() throws I18NException;

  long recrearNotificacio(NotificacioInfo info) throws I18NException;

}

