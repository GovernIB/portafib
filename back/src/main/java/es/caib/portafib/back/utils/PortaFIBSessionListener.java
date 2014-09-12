package es.caib.portafib.back.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * NOTA: Aquest classe s'executa abans del AuthenticationSuccessListener.java.
 * Per activar-la s'ha d'anar al web.xml i descomentar el primer bloc de <Listener>
 * 
 * @author anadal
 * 
 */
public class PortaFIBSessionListener implements HttpSessionListener {
  
  protected final Logger log = Logger.getLogger(getClass());

  private static int totalActiveSessions;

  @Override
  public void sessionCreated(HttpSessionEvent arg0) {
    totalActiveSessions++;
    log.info("sessionCreated - add one session into counter (" + totalActiveSessions
        + ")");
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent arg0) {
    if (totalActiveSessions != 0) {
      totalActiveSessions--;
    }
    log.info("sessionDestroyed - deduct one session from counter("
        + totalActiveSessions + ")");
  }

}
