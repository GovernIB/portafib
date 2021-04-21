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

  String JNDI_NAME = "portafib/RebreAvisLogicaEJB/local";

  boolean isAgruparCorreus(final String usuariEntitatId, final long eventID);

}
