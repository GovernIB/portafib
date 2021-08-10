package es.caib.portafib.logic;

import es.caib.portafib.ejb.RebreAvisLocal;
import javax.ejb.Local;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface RebreAvisLogicaLocal extends RebreAvisLocal {

  String JNDI_NAME = "java:app/portafib-logic/RebreAvisLogicaEJB";

  boolean isAgruparCorreus(final String usuariEntitatId, final long eventID);

}
