
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.EstadisticaJPA;
import es.caib.portafib.model.dao.IEstadisticaManager;

@Local
public interface EstadisticaLocal extends IEstadisticaManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/EstadisticaEJB";

  public EstadisticaJPA findByPrimaryKey(Long _ID_);
}
