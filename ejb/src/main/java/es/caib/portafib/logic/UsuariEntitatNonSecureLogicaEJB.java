package es.caib.portafib.logic;



import javax.annotation.security.RunAs;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import es.caib.portafib.commons.utils.Constants;

/**
 * Gestiona Usuaris Entitat d'alt nivell
 * @author anadal
 * 
 */
@Stateless(name = "UsuariEntitatNonSecureLogicaEJB")
@RunAs(Constants.PFI_USER)
@Startup
public class UsuariEntitatNonSecureLogicaEJB extends UsuariEntitatLogicaEJB implements
    UsuariEntitatNonSecureLogicaLocal {


}
