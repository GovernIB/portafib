package es.caib.portafib.logic.events;

import java.util.Map;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface FirmaEventManagerLocal {

  public Map<FirmaEvent, Throwable> processList(FirmaEventList felist) throws I18NException;
  
}
