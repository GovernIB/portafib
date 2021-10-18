package es.caib.portafib.logic;


import es.caib.portafib.ejb.RevisorDeFirmaService;
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
public interface RevisorDeFirmaLogicaLocal extends RevisorDeFirmaService {

  String JNDI_NAME = "java:app/portafib-ejb/RevisorDeFirmaLogicaEJB";

  List<RevisorDeFirma> getRevisorsFirma(long firmaID) throws I18NException;

}
