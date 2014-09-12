
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.GrupEntitatJPA;
import es.caib.portafib.model.dao.IGrupEntitatManager;

@Local
public interface GrupEntitatLocal extends IGrupEntitatManager {

 public static final String JNDI_NAME = "portafib/GrupEntitatEJB/local";
  public GrupEntitatJPA findByPrimaryKey(Long _ID_);
}
