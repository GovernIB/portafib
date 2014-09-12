
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.MetadadaJPA;
import es.caib.portafib.model.dao.IMetadadaManager;

@Local
public interface MetadadaLocal extends IMetadadaManager {

 public static final String JNDI_NAME = "portafib/MetadadaEJB/local";
  public MetadadaJPA findByPrimaryKey(Long _ID_);
}
