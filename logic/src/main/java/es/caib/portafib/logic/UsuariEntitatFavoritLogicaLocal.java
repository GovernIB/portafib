package es.caib.portafib.logic;

import es.caib.portafib.ejb.UsuariEntitatFavoritLocal;
import javax.ejb.Local;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface UsuariEntitatFavoritLogicaLocal extends UsuariEntitatFavoritLocal {

    String JNDI_NAME = "java:app/portafib-logic/UsuariEntitatFavoritLogicaEJB";

}
