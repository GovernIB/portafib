
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PrioritatJPA;
import es.caib.portafib.model.dao.IPrioritatManager;

@Local
public interface PrioritatLocal extends IPrioritatManager {

 public static final String JNDI_NAME = "portafib/PrioritatEJB/local";
  public PrioritatJPA findByPrimaryKey(Integer _ID_);
}
