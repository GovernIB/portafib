
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PluginFirmaWebPerUsuariEntitatJPA;
import es.caib.portafib.persistence.PluginFirmaWebPerUsuariEntitatIJPAManager;
import es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariEntitatManager;

@Local
public interface PluginFirmaWebPerUsuariEntitatService extends PluginFirmaWebPerUsuariEntitatIJPAManager,IPluginFirmaWebPerUsuariEntitatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PluginFirmaWebPerUsuariEntitatEJB!es.caib.portafib.ejb.PluginFirmaWebPerUsuariEntitatService";

    public PluginFirmaWebPerUsuariEntitatJPA findByPrimaryKey(Long _ID_);
}
