package es.caib.portafib.logic;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

import es.caib.portafib.commons.utils.Constants;



/**
 * Per accessos NO AUTENTICATS
 * @author anadal
 *
 */
@Stateless(name = "SegellDeTempsPublicLogicaEJB")
@RunAs(Constants.PFI_USER)
public class SegellDeTempsPublicLogicaEJB extends SegellDeTempsLogicaEJB
    implements SegellDeTempsPublicLogicaLocal {


}
