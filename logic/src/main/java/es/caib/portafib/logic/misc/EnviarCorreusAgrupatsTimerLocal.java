package es.caib.portafib.logic.misc;

import javax.ejb.Local;

/**
 *
 * @author anadal
 *
 */
@Local
public interface EnviarCorreusAgrupatsTimerLocal extends AbstractTimerLocal {

  public static final String JNDI_NAME = "portafib/EnviarCorreusAgrupatsTimerEJB/local";

}
