
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PluginCridadaJPA;
import es.caib.portafib.model.dao.IPluginCridadaManager;

@Local
public interface PluginCridadaLocal extends IPluginCridadaManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/PluginCridadaEJB";

  public PluginCridadaJPA findByPrimaryKey(Long _ID_);
}
