
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PermisGrupPlantillaJPA;
import es.caib.portafib.persistence.PermisGrupPlantillaIJPAManager;
import es.caib.portafib.model.dao.IPermisGrupPlantillaManager;

import es.caib.portafib.model.entity.PermisGrupPlantilla;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PermisGrupPlantillaService extends PermisGrupPlantillaIJPAManager,IPermisGrupPlantillaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PermisGrupPlantillaEJB!es.caib.portafib.ejb.PermisGrupPlantillaService";

    public PermisGrupPlantillaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PermisGrupPlantilla instance, FitxerService fitxerEjb) throws I18NException;
}
