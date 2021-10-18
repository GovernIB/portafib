
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.RebreAvisJPA;
import es.caib.portafib.persistence.RebreAvisIJPAManager;
import es.caib.portafib.model.dao.IRebreAvisManager;

@Local
public interface RebreAvisService extends RebreAvisIJPAManager,IRebreAvisManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/RebreAvisEJB!es.caib.portafib.ejb.RebreAvisService";

    public RebreAvisJPA findByPrimaryKey(Long _ID_);
}
