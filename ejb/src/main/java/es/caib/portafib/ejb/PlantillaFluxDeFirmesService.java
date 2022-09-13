
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.persistence.PlantillaFluxDeFirmesIJPAManager;
import es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager;

import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PlantillaFluxDeFirmesService extends PlantillaFluxDeFirmesIJPAManager,IPlantillaFluxDeFirmesManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PlantillaFluxDeFirmesEJB!es.caib.portafib.ejb.PlantillaFluxDeFirmesService";

    public PlantillaFluxDeFirmesJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PlantillaFluxDeFirmes instance, FitxerService fitxerEjb) throws I18NException;
}
