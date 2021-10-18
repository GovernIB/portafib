package es.caib.portafib.logic;

import es.caib.portafib.ejb.GrupEntitatService;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface GrupEntitatLogicaLocal extends GrupEntitatService {

  String JNDI_NAME = "java:app/portafib-ejb/GrupEntitatLogicaEJB";

  public void deleteFull(Long grupEntitatID) throws I18NException ;

}
