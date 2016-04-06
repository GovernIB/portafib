package es.caib.portafib.logic;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * Per accessos NO AUTENTICATS
 * @author anadal
 *
 */
@Stateless(name = "SegellDeTempsPublicLogicaEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class SegellDeTempsPublicLogicaEJB extends SegellDeTempsLogicaEJB
    implements SegellDeTempsPublicLogicaLocal {


}
