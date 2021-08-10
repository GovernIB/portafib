
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.TraduccioJPA;
import es.caib.portafib.model.dao.ITraduccioManager;

@Local
public interface TraduccioLocal extends ITraduccioManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/TraduccioEJB";

  public TraduccioJPA findByPrimaryKey(Long _ID_);
}
