
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PosicioPaginaJPA;
import es.caib.portafib.model.dao.IPosicioPaginaManager;

@Local
public interface PosicioPaginaLocal extends IPosicioPaginaManager {

 public static final String JNDI_NAME = "portafib/PosicioPaginaEJB/local";
  public PosicioPaginaJPA findByPrimaryKey(Long _ID_);
}
