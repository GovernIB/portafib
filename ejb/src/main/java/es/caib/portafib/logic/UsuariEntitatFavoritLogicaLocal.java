package es.caib.portafib.logic;

import es.caib.portafib.ejb.UsuariEntitatFavoritService;
import javax.ejb.Local;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface UsuariEntitatFavoritLogicaLocal extends UsuariEntitatFavoritService {

    String JNDI_NAME = "java:app/portafib-ejb/UsuariEntitatFavoritLogicaEJB";

}
