
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PluginCridadaJPA;
import es.caib.portafib.persistence.PluginCridadaIJPAManager;
import es.caib.portafib.model.dao.IPluginCridadaManager;

@Local
public interface PluginCridadaService extends PluginCridadaIJPAManager,IPluginCridadaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PluginCridadaEJB!es.caib.portafib.ejb.PluginCridadaService";

    public PluginCridadaJPA findByPrimaryKey(Long _ID_);
}
