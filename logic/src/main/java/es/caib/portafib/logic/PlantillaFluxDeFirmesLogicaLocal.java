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

  public static final String JNDI_NAME = "portafib/PlantillaFluxDeFirmesLogicaEJB/local";

  public PlantillaFluxDeFirmesJPA findByPrimaryKeyFull(Long PlantillaFluxDeFirmesID);

}
