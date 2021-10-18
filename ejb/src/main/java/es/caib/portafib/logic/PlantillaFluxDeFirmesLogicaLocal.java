package es.caib.portafib.logic;

import es.caib.portafib.ejb.PlantillaFluxDeFirmesService;
import es.caib.portafib.persistence.PlantillaFluxDeFirmesJPA;

import javax.ejb.Local;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface PlantillaFluxDeFirmesLogicaLocal extends PlantillaFluxDeFirmesService {

  String JNDI_NAME = "java:app/portafib-ejb/PlantillaFluxDeFirmesLogicaEJB";

  public PlantillaFluxDeFirmesJPA findByPrimaryKeyFull(Long PlantillaFluxDeFirmesID);

}
