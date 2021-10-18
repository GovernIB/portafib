package es.caib.portafib.logic;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;



/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "ColaboracioDelegacioUnauthorizedLogicaEJB")
@RunAs("PFI_USER")
public class ColaboracioDelegacioUnauthorizedLogicaEJB 
  extends ColaboracioDelegacioLogicaEJB 
  implements ColaboracioDelegacioUnauthorizedLogicaLocal {

}
