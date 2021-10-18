
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PermisGrupPlantillaJPA;
import es.caib.portafib.persistence.PermisGrupPlantillaIJPAManager;
import es.caib.portafib.model.dao.IPermisGrupPlantillaManager;

@Local
public interface PermisGrupPlantillaService extends PermisGrupPlantillaIJPAManager,IPermisGrupPlantillaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PermisGrupPlantillaEJB!es.caib.portafib.ejb.PermisGrupPlantillaService";

    public PermisGrupPlantillaJPA findByPrimaryKey(Long _ID_);
}
