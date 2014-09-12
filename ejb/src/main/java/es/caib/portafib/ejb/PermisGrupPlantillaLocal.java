
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PermisGrupPlantillaJPA;
import es.caib.portafib.model.dao.IPermisGrupPlantillaManager;

@Local
public interface PermisGrupPlantillaLocal extends IPermisGrupPlantillaManager {

 public static final String JNDI_NAME = "portafib/PermisGrupPlantillaEJB/local";
  public PermisGrupPlantillaJPA findByPrimaryKey(Long _ID_);
}
