package es.caib.portafib.logic;

import javax.ejb.Local;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ColaboracioDelegacioUnauthorizedLogicaLocal 
   extends ColaboracioDelegacioLogicaLocal {

    String JNDI_NAME = "java:app/portafib-ejb/ColaboracioDelegacioUnauthorizedLogicaEJB";
}
