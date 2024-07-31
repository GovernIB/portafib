package es.caib.portafib.logic.notificacions;

import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.model.entity.UsuariAplicacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

public interface NotificacioSender {

   int CONNECTION_TIMEOUT_MS = 10000;

   int RECEIVE_TIMEOUT_MS = 10000;

   void sendNotificacio(NotificacioInfo notificacioInfo, UsuariAplicacio ua) throws I18NException;

   void testApi(UsuariAplicacio usuariAplicacio) throws Exception;
}
