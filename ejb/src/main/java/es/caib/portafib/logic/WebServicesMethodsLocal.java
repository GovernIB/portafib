package es.caib.portafib.logic;

import java.util.List;

import es.caib.portafib.persistence.PeticioDeFirmaJPA;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface WebServicesMethodsLocal extends PeticioDeFirmaLogicaLocal {

  String JNDI_NAME = "java:app/portafib-ejb/WebServicesMethodsEJB";

  public PeticioDeFirmaJPA createAndStartPeticioDeFirma(PeticioDeFirmaJPA peticioDeFirma, String usuariAplicacioID)
    throws Exception, I18NException, I18NValidationException;

  public List<Long> deletePeticionsDeFirma(Where filtre, String usuariAplicacioID) throws Exception, I18NException;

}

