package es.caib.portafib.logic;

import es.caib.portafib.ejb.PlantillaFluxDeFirmesLocal;
import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;

import javax.ejb.Local;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface PlantillaFluxDeFirmesLogicaLocal extends PlantillaFluxDeFirmesLocal {

  String JNDI_NAME = "java:app/portafib-logic/PlantillaFluxDeFirmesLogicaEJB";

  public PlantillaFluxDeFirmesJPA findByPrimaryKeyFull(Long PlantillaFluxDeFirmesID);

}
