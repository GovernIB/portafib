
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.persistence.PlantillaFluxDeFirmesIJPAManager;
import es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager;

@Local
public interface PlantillaFluxDeFirmesService extends PlantillaFluxDeFirmesIJPAManager,IPlantillaFluxDeFirmesManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PlantillaFluxDeFirmesEJB!es.caib.portafib.ejb.PlantillaFluxDeFirmesService";

    public PlantillaFluxDeFirmesJPA findByPrimaryKey(Long _ID_);
}
