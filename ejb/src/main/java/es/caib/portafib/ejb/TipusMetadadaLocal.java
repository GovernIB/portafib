
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.TipusMetadadaJPA;
import es.caib.portafib.model.dao.ITipusMetadadaManager;

@Local
public interface TipusMetadadaLocal extends ITipusMetadadaManager {

 public static final String JNDI_NAME = "portafib/TipusMetadadaEJB/local";
  public TipusMetadadaJPA findByPrimaryKey(Integer _ID_);
}
