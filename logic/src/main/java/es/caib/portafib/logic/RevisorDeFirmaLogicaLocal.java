package es.caib.portafib.logic;


import es.caib.portafib.ejb.RevisorDeFirmaLocal;
import es.caib.portafib.model.entity.RevisorDeFirma;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import java.util.List;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface RevisorDeFirmaLogicaLocal extends RevisorDeFirmaLocal {
  
  String JNDI_NAME = "portafib/RevisorDeFirmaLogicaEJB/local";

  List<RevisorDeFirma> getRevisorsFirma(long firmaID) throws I18NException;

}
