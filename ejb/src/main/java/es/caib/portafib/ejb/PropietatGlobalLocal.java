
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PropietatGlobalJPA;
import es.caib.portafib.model.dao.IPropietatGlobalManager;

@Local
public interface PropietatGlobalLocal extends IPropietatGlobalManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/PropietatGlobalEJB";

  public PropietatGlobalJPA findByPrimaryKey(Long _ID_);
}
