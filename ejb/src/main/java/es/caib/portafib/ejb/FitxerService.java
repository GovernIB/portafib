
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.FitxerJPA;
import es.caib.portafib.persistence.FitxerIJPAManager;
import es.caib.portafib.model.dao.IFitxerManager;

@Local
public interface FitxerService extends FitxerIJPAManager,IFitxerManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/FitxerEJB!es.caib.portafib.ejb.FitxerService";

    public FitxerJPA findByPrimaryKey(Long _ID_);
}
