
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PermisUsuariPlantillaJPA;
import es.caib.portafib.persistence.PermisUsuariPlantillaIJPAManager;
import es.caib.portafib.model.dao.IPermisUsuariPlantillaManager;

@Local
public interface PermisUsuariPlantillaService extends PermisUsuariPlantillaIJPAManager,IPermisUsuariPlantillaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PermisUsuariPlantillaEJB!es.caib.portafib.ejb.PermisUsuariPlantillaService";

    public PermisUsuariPlantillaJPA findByPrimaryKey(Long _ID_);
}
