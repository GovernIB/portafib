
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.TraduccioJPA;
import es.caib.portafib.model.dao.ITraduccioManager;

@Local
public interface TraduccioLocal extends ITraduccioManager {

 public static final String JNDI_NAME = "portafib/TraduccioEJB/local";
  public TraduccioJPA findByPrimaryKey(Long _ID_);
}
