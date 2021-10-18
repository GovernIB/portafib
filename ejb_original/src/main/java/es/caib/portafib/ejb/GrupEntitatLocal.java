
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.GrupEntitatJPA;
import es.caib.portafib.model.dao.IGrupEntitatManager;

@Local
public interface GrupEntitatLocal extends IGrupEntitatManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/GrupEntitatEJB";

  public GrupEntitatJPA findByPrimaryKey(Long _ID_);
}
