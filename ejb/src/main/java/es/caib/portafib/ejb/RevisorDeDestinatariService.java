
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.RevisorDeDestinatariJPA;
import es.caib.portafib.persistence.RevisorDeDestinatariIJPAManager;
import es.caib.portafib.model.dao.IRevisorDeDestinatariManager;

import es.caib.portafib.model.entity.RevisorDeDestinatari;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface RevisorDeDestinatariService extends RevisorDeDestinatariIJPAManager,IRevisorDeDestinatariManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/RevisorDeDestinatariEJB!es.caib.portafib.ejb.RevisorDeDestinatariService";

    public RevisorDeDestinatariJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(RevisorDeDestinatari instance, FitxerService fitxerEjb) throws I18NException;
}
