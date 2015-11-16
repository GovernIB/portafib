
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.model.dao.IPluginManager;

@Local
public interface PluginLocal extends IPluginManager {

 public static final String JNDI_NAME = "portafib/PluginEJB/local";
  public PluginJPA findByPrimaryKey(Long _ID_);
}
