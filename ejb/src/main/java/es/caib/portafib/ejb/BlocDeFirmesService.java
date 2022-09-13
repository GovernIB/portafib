
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.BlocDeFirmesJPA;
import es.caib.portafib.persistence.BlocDeFirmesIJPAManager;
import es.caib.portafib.model.dao.IBlocDeFirmesManager;

import es.caib.portafib.model.entity.BlocDeFirmes;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface BlocDeFirmesService extends BlocDeFirmesIJPAManager,IBlocDeFirmesManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/BlocDeFirmesEJB!es.caib.portafib.ejb.BlocDeFirmesService";

    public BlocDeFirmesJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(BlocDeFirmes instance, FitxerService fitxerEjb) throws I18NException;
}
