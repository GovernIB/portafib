package es.caib.portafib.logic;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

import es.caib.portafib.commons.utils.Constants;



/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "ColaboracioDelegacioUnauthorizedLogicaEJB")
@RunAs(Constants.PFI_USER)
public class ColaboracioDelegacioUnauthorizedLogicaEJB 
  extends ColaboracioDelegacioLogicaEJB 
  implements ColaboracioDelegacioUnauthorizedLogicaLocal {

}
