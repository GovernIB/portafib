package es.caib.portafib.logic.misc;

import javax.ejb.Local;

/**
 *
 * @author anadal
 *
 */
@Local
public interface EnviarCorreusAgrupatsTimerLocal extends AbstractTimerLocal {

  String JNDI_NAME = "java:app/portafib-logic/EnviarCorreusAgrupatsTimerEJB";

}
