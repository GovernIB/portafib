package es.caib.portafib.logic;

import javax.ejb.Local;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ModulDeFirmaWebPublicLogicaLocal extends ModulDeFirmaWebLogicaLocal {

  String JNDI_NAME = "java:app/portafib-logic/ModulDeFirmaWebPublicLogicaEJB";

}
