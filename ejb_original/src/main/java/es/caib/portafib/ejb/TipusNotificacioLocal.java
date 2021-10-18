
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.TipusNotificacioJPA;
import es.caib.portafib.model.dao.ITipusNotificacioManager;

@Local
public interface TipusNotificacioLocal extends ITipusNotificacioManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/TipusNotificacioEJB";

  public TipusNotificacioJPA findByPrimaryKey(Long _ID_);
}
