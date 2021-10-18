
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.GrupEntitatUsuariEntitatJPA;
import es.caib.portafib.persistence.GrupEntitatUsuariEntitatIJPAManager;
import es.caib.portafib.model.dao.IGrupEntitatUsuariEntitatManager;

@Local
public interface GrupEntitatUsuariEntitatService extends GrupEntitatUsuariEntitatIJPAManager,IGrupEntitatUsuariEntitatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/GrupEntitatUsuariEntitatEJB!es.caib.portafib.ejb.GrupEntitatUsuariEntitatService";

    public GrupEntitatUsuariEntitatJPA findByPrimaryKey(Long _ID_);
}
