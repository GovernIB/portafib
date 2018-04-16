
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PluginFirmaWebPerUsuariAplicacioJPA;
import es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariAplicacioManager;

@Local
public interface PluginFirmaWebPerUsuariAplicacioLocal extends IPluginFirmaWebPerUsuariAplicacioManager {

 public static final String JNDI_NAME = "portafib/PluginFirmaWebPerUsuariAplicacioEJB/local";
  public PluginFirmaWebPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_);
}
