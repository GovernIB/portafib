package es.caib.portafib.logic;


import javax.annotation.security.RunAs;
import javax.ejb.Stateless;



/**
 * Per accesos no autenticats
 * @author anadal
 *
 */
@Stateless(name = "ModulDeFirmaWebPublicLogicaEJB")
@RunAs("PFI_USER")
public class ModulDeFirmaWebPublicLogicaEJB extends ModulDeFirmaWebLogicaEJB
    implements ModulDeFirmaWebPublicLogicaLocal {

}
