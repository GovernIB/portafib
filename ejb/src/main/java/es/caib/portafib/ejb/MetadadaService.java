
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.MetadadaJPA;
import es.caib.portafib.persistence.MetadadaIJPAManager;
import es.caib.portafib.model.dao.IMetadadaManager;

@Local
public interface MetadadaService extends MetadadaIJPAManager,IMetadadaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/MetadadaEJB!es.caib.portafib.ejb.MetadadaService";

    public MetadadaJPA findByPrimaryKey(Long _ID_);
}
