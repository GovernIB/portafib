
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.EntitatIJPAManager;
import es.caib.portafib.model.dao.IEntitatManager;

@Local
public interface EntitatService extends EntitatIJPAManager,IEntitatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/EntitatEJB!es.caib.portafib.ejb.EntitatService";

    public EntitatJPA findByPrimaryKey(String _ID_);
}
