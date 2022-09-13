
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.RevisorDeFirmaJPA;
import es.caib.portafib.persistence.RevisorDeFirmaIJPAManager;
import es.caib.portafib.model.dao.IRevisorDeFirmaManager;

import es.caib.portafib.model.entity.RevisorDeFirma;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface RevisorDeFirmaService extends RevisorDeFirmaIJPAManager,IRevisorDeFirmaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/RevisorDeFirmaEJB!es.caib.portafib.ejb.RevisorDeFirmaService";

    public RevisorDeFirmaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(RevisorDeFirma instance, FitxerService fitxerEjb) throws I18NException;
}
