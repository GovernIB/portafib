package es.caib.portafib.logic;

import javax.ejb.Local;


/**
 * 
 * @author anadal
 * 
 */
@Local
public interface UsuariPersonaNonSecureLogicaLocal extends UsuariPersonaLogicaLocal {

  String JNDI_NAME = "java:app/portafib-logic/UsuariPersonaNonSecureLogicaEJB";

}
