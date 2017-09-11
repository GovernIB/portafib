package es.caib.portafib.logic;



import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "UsuariPersonaNonSecureLogicaEJB")
@RunAs("PFI_USER")
@SecurityDomain("seycon")
public class UsuariPersonaNonSecureLogicaEJB extends UsuariPersonaLogicaEJB implements
    UsuariPersonaNonSecureLogicaLocal {
  
}
