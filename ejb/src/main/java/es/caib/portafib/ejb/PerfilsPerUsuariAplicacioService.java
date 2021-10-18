
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PerfilsPerUsuariAplicacioJPA;
import es.caib.portafib.persistence.PerfilsPerUsuariAplicacioIJPAManager;
import es.caib.portafib.model.dao.IPerfilsPerUsuariAplicacioManager;

@Local
public interface PerfilsPerUsuariAplicacioService extends PerfilsPerUsuariAplicacioIJPAManager,IPerfilsPerUsuariAplicacioManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PerfilsPerUsuariAplicacioEJB!es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService";

    public PerfilsPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_);
}
