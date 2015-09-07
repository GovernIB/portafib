package es.caib.portafib.logic;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "ColaboracioDelegacioUnauthorizedLogicaEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class ColaboracioDelegacioUnauthorizedLogicaEJB 
  extends ColaboracioDelegacioLogicaEJB 
  implements ColaboracioDelegacioUnauthorizedLogicaLocal {

}
