package es.caib.portafib.logic;

import es.caib.portafib.ejb.PlantillaFluxDeFirmesService;
import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.persistence.PlantillaFluxDeFirmesJPA;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface PlantillaFluxDeFirmesLogicaLocal extends PlantillaFluxDeFirmesService {

  String JNDI_NAME = "java:app/portafib-ejb/PlantillaFluxDeFirmesLogicaEJB";

  public PlantillaFluxDeFirmesJPA findByPrimaryKeyFull(Long PlantillaFluxDeFirmesID);

  public void afegirRevisorAFlusDeFirmes(String usuariEntitatID, FluxDeFirmesJPA flux, FirmaJPA firma) throws I18NException;

}
