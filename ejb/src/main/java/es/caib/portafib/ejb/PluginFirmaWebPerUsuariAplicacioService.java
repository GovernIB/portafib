
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PluginFirmaWebPerUsuariAplicacioJPA;
import es.caib.portafib.persistence.PluginFirmaWebPerUsuariAplicacioIJPAManager;
import es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariAplicacioManager;

@Local
public interface PluginFirmaWebPerUsuariAplicacioService extends PluginFirmaWebPerUsuariAplicacioIJPAManager,IPluginFirmaWebPerUsuariAplicacioManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PluginFirmaWebPerUsuariAplicacioEJB!es.caib.portafib.ejb.PluginFirmaWebPerUsuariAplicacioService";

    public PluginFirmaWebPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_);
}
