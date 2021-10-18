
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.model.dao.IPluginManager;

@Local
public interface PluginLocal extends IPluginManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/PluginEJB";

  public PluginJPA findByPrimaryKey(Long _ID_);
}
