
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager;

@Local
public interface PlantillaFluxDeFirmesLocal extends IPlantillaFluxDeFirmesManager {

 public static final String JNDI_NAME = "portafib/PlantillaFluxDeFirmesEJB/local";
  public PlantillaFluxDeFirmesJPA findByPrimaryKey(Long _ID_);
}
