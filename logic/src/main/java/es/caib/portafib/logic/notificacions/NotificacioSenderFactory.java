package es.caib.portafib.logic.notificacions;

import es.caib.portafib.model.entity.UsuariAplicacio;

public class NotificacioSenderFactory {

   private static NotificacioSender[] senders = {
         new NotificacioSenderApiIndra(),   // 0 - API Indra
         new NotificacioSenderApiPortafibWSv1(), // 1 - WS v1
         new NotificacioSenderApiPortafibRESTv1() // 2 - REST v1
   };

   /**
    * Retorna la implementació de {@link NotificacioSender} adequada per l'usuari aplicació segons
    * el seu atribut callbackVersion.
    * @param ua Usuari aplicació.
    * @return implementació de NotificacioSender.
    */
   public static NotificacioSender getSender(UsuariAplicacio ua) {
      if (ua.getCallbackVersio() < 0 || ua.getCallbackVersio() >= senders.length) {
         return null;
      } else {
         return senders[ua.getCallbackVersio()];
      }
   }
}
