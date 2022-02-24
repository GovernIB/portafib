package es.caib.portafib.logic;

import es.caib.portafib.ejb.FitxerService;
import es.caib.portafib.persistence.FitxerJPA;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
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
public interface FitxerLogicaLocal extends FitxerService {

  String JNDI_NAME = "java:app/portafib-ejb/FitxerLogicaEJB";

  public FitxerJPA createFull(FitxerJPA fitxer) throws I18NException, I18NValidationException;

  public FitxerJPA checkBasic(long fitxerID) throws I18NException;
  
  public boolean deleteFull(long fitxerID) throws I18NException;

  public FitxerJPA createFitxerField(FitxerJPA fitxer, IPortaFIBDataSource data, Set<Long> fitxersCreats, Field<?> field)
          throws I18NException, I18NValidationException;


  public void cleanSet(Set<Long> fitxersCreats);
}
