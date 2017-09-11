package es.caib.portafib.logic;

import javax.ejb.Local;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface UsuariEntitatNonSecureLogicaLocal extends UsuariEntitatLogicaLocal {

  public static final String JNDI_NAME = "portafib/UsuariEntitatNonSecureLogicaEJB/local";

}
