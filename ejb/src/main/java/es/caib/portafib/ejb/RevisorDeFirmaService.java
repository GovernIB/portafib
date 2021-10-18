
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.RevisorDeFirmaJPA;
import es.caib.portafib.persistence.RevisorDeFirmaIJPAManager;
import es.caib.portafib.model.dao.IRevisorDeFirmaManager;

@Local
public interface RevisorDeFirmaService extends RevisorDeFirmaIJPAManager,IRevisorDeFirmaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/RevisorDeFirmaEJB!es.caib.portafib.ejb.RevisorDeFirmaService";

    public RevisorDeFirmaJPA findByPrimaryKey(Long _ID_);
}
