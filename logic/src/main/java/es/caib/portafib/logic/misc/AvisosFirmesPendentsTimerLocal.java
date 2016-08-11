package es.caib.portafib.logic.misc;

import javax.ejb.Local;

/**
 *
 * @author anadal
 *
 */
@Local
public interface AvisosFirmesPendentsTimerLocal extends AbstractTimerLocal {

  public static final String JNDI_NAME = "portafib/AvisosFirmesPendentsTimerEJB/local";

}
