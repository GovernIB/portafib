package es.caib.portafib.logic;

import es.caib.portafib.ejb.GrupEntitatLocal;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface GrupEntitatLogicaLocal extends GrupEntitatLocal {

  String JNDI_NAME = "java:app/portafib-logic/GrupEntitatLogicaEJB";

  public void deleteFull(Long grupEntitatID) throws I18NException ;

}
