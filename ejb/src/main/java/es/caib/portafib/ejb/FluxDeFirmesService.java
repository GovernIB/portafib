
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.persistence.FluxDeFirmesIJPAManager;
import es.caib.portafib.model.dao.IFluxDeFirmesManager;

import es.caib.portafib.model.entity.FluxDeFirmes;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface FluxDeFirmesService extends FluxDeFirmesIJPAManager,IFluxDeFirmesManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/FluxDeFirmesEJB!es.caib.portafib.ejb.FluxDeFirmesService";

    public FluxDeFirmesJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(FluxDeFirmes instance, FitxerService fitxerEjb) throws I18NException;
}
