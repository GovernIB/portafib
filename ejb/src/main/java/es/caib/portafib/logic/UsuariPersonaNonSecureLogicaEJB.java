package es.caib.portafib.logic;



import javax.annotation.security.RunAs;
import javax.ejb.Stateless;



/**
 *
 * @author anadal
 *
 */
@Stateless(name = "UsuariPersonaNonSecureLogicaEJB")
@RunAs("PFI_USER")
public class UsuariPersonaNonSecureLogicaEJB extends UsuariPersonaLogicaEJB implements
    UsuariPersonaNonSecureLogicaLocal {
  
}
