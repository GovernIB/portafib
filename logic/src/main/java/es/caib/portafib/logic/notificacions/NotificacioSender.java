package es.caib.portafib.logic.notificacions;

import es.caib.portafib.logic.utils.NotificacioInfo;
import es.caib.portafib.model.entity.UsuariAplicacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

public interface NotificacioSender {

   void sendNotificacio(NotificacioInfo notificacioInfo, UsuariAplicacio ua) throws I18NException;

   void testApi(UsuariAplicacio usuariAplicacio) throws Exception;
}
