
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PermisUsuariPlantillaJPA;
import es.caib.portafib.model.dao.IPermisUsuariPlantillaManager;

@Local
public interface PermisUsuariPlantillaLocal extends IPermisUsuariPlantillaManager {

 public static final String JNDI_NAME = "portafib/PermisUsuariPlantillaEJB/local";
  public PermisUsuariPlantillaJPA findByPrimaryKey(Long _ID_);
}
