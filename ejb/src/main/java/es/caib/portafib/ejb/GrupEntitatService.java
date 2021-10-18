
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.GrupEntitatJPA;
import es.caib.portafib.persistence.GrupEntitatIJPAManager;
import es.caib.portafib.model.dao.IGrupEntitatManager;

@Local
public interface GrupEntitatService extends GrupEntitatIJPAManager,IGrupEntitatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/GrupEntitatEJB!es.caib.portafib.ejb.GrupEntitatService";

    public GrupEntitatJPA findByPrimaryKey(Long _ID_);
}
