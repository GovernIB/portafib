package es.caib.portafib.logic;

import es.caib.portafib.ejb.RebreAvisService;
import javax.ejb.Local;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface RebreAvisLogicaLocal extends RebreAvisService {

  String JNDI_NAME = "java:app/portafib-ejb/RebreAvisLogicaEJB";

  boolean isAgruparCorreus(final String usuariEntitatId, final long eventID);

}
