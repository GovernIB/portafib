
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.EntitatIJPAManager;
import es.caib.portafib.model.dao.IEntitatManager;

import es.caib.portafib.model.entity.Entitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EntitatService extends EntitatIJPAManager,IEntitatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/EntitatEJB!es.caib.portafib.ejb.EntitatService";

    public EntitatJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(Entitat instance, FitxerService fitxerEjb) throws I18NException;
}
