package es.caib.portafib.logic;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;



/**
 * Per accessos NO AUTENTICATS
 * @author anadal
 *
 */
@Stateless(name = "SegellDeTempsPublicLogicaEJB")
@RunAs("PFI_USER")
public class SegellDeTempsPublicLogicaEJB extends SegellDeTempsLogicaEJB
    implements SegellDeTempsPublicLogicaLocal {


}
