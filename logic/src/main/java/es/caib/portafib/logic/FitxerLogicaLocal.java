package es.caib.portafib.logic;

import es.caib.portafib.ejb.FitxerLocal;
import es.caib.portafib.jpa.FitxerJPA;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface FitxerLogicaLocal extends FitxerLocal {
  
  public static final String JNDI_NAME = "portafib/FitxerLogicaEJB/local";

  public FitxerJPA createFull(FitxerJPA fitxer) throws I18NException;

  public FitxerJPA checkBasic(long fitxerID) throws I18NException;
  
  public void deleteFull(long fitxerID) throws I18NException;

}

