package es.caib.portafib.logic;


import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

import es.caib.portafib.commons.utils.Constants;



/**
 * Per accesos no autenticats
 * @author anadal
 *
 */
@Stateless(name = "ModulDeFirmaWebPublicLogicaEJB")
@RunAs(Constants.PFI_USER)
public class ModulDeFirmaWebPublicLogicaEJB extends ModulDeFirmaWebLogicaEJB
    implements ModulDeFirmaWebPublicLogicaLocal {

}
