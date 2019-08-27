package es.caib.portafib.logic;

import es.caib.portafib.ejb.FitxerLocal;
import es.caib.portafib.jpa.FitxerJPA;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;

import javax.ejb.Local;
import java.util.Set;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface FitxerLogicaLocal extends FitxerLocal {
  
  public static final String JNDI_NAME = "portafib/FitxerLogicaEJB/local";

  public FitxerJPA createFull(FitxerJPA fitxer) throws I18NException, I18NValidationException;

  public FitxerJPA checkBasic(long fitxerID) throws I18NException;
  
  public boolean deleteFull(long fitxerID) throws I18NException;

  public FitxerJPA createFitxerField(FitxerJPA fitxer, Set<Long> fitxersCreats, Field<?> field)
          throws I18NException, I18NValidationException;

}

