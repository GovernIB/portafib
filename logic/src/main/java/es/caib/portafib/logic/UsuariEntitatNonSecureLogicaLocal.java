package es.caib.portafib.logic;

import javax.ejb.Local;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface UsuariEntitatNonSecureLogicaLocal extends UsuariEntitatLogicaLocal {

  String JNDI_NAME = "java:app/portafib-logic/UsuariEntitatNonSecureLogicaEJB";

}
