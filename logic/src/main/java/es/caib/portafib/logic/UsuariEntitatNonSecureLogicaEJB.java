package es.caib.portafib.logic;



import javax.annotation.security.RunAs;
import javax.ejb.Startup;
import javax.ejb.Stateless;

/**
 * Gestiona Usuaris Entitat d'alt nivell
 * @author anadal
 * 
 */
@Stateless(name = "UsuariEntitatNonSecureLogicaEJB")
@RunAs("PFI_USER")
@Startup
public class UsuariEntitatNonSecureLogicaEJB extends UsuariEntitatLogicaEJB implements
    UsuariEntitatNonSecureLogicaLocal {


}
