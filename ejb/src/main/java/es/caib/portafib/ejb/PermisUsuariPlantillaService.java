
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PermisUsuariPlantillaJPA;
import es.caib.portafib.persistence.PermisUsuariPlantillaIJPAManager;
import es.caib.portafib.model.dao.IPermisUsuariPlantillaManager;

import es.caib.portafib.model.entity.PermisUsuariPlantilla;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PermisUsuariPlantillaService extends PermisUsuariPlantillaIJPAManager,IPermisUsuariPlantillaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PermisUsuariPlantillaEJB!es.caib.portafib.ejb.PermisUsuariPlantillaService";

    public PermisUsuariPlantillaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PermisUsuariPlantilla instance, FitxerService fitxerEjb) throws I18NException;
}
