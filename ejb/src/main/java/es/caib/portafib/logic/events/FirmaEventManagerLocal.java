package es.caib.portafib.logic.events;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface FirmaEventManagerLocal extends Serializable {

  String JNDI_NAME = "java:app/portafib-ejb/FirmaEventManagerEJB";

  public Map<FirmaEvent, Throwable> processList(FirmaEventList felist, boolean wakeUpTimer) throws I18NException;
  
}
