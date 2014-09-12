
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.GrupEntitatUsuariEntitatJPA;
import es.caib.portafib.model.dao.IGrupEntitatUsuariEntitatManager;

@Local
public interface GrupEntitatUsuariEntitatLocal extends IGrupEntitatUsuariEntitatManager {

 public static final String JNDI_NAME = "portafib/GrupEntitatUsuariEntitatEJB/local";
  public GrupEntitatUsuariEntitatJPA findByPrimaryKey(Long _ID_);
}
