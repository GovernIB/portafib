
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PluginJPA;
import es.caib.portafib.persistence.PluginIJPAManager;
import es.caib.portafib.model.dao.IPluginManager;

@Local
public interface PluginService extends PluginIJPAManager,IPluginManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PluginEJB!es.caib.portafib.ejb.PluginService";

    public PluginJPA findByPrimaryKey(Long _ID_);
}
