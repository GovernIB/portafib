
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PropietatGlobalJPA;
import es.caib.portafib.model.dao.IPropietatGlobalManager;

@Local
public interface PropietatGlobalLocal extends IPropietatGlobalManager {

 public static final String JNDI_NAME = "portafib/PropietatGlobalEJB/local";
  public PropietatGlobalJPA findByPrimaryKey(Long _ID_);
}
