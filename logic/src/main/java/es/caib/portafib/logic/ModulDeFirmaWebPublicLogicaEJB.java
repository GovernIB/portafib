package es.caib.portafib.logic;


import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * Per accesos no autenticats
 * @author anadal
 *
 */
@Stateless(name = "ModulDeFirmaWebPublicLogicaEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class ModulDeFirmaWebPublicLogicaEJB extends ModulDeFirmaWebLogicaEJB
    implements ModulDeFirmaWebPublicLogicaLocal {

}
