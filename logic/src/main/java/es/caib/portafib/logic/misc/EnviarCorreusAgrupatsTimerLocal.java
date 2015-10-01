package es.caib.portafib.logic.misc;

import javax.ejb.Local;

/**
 *
 * @author anadal
 *
 */
@Local
public interface EnviarCorreusAgrupatsTimerLocal {

  public static final String JNDI_NAME = "portafib/EnviarCorreusAgrupatsTimerEJB/local";

  public void startScheduler();

  public void stopScheduler();

  public void enviarCorreus();
}
