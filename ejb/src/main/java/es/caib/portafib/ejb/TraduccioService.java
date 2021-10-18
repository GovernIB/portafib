
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.TraduccioJPA;
import es.caib.portafib.persistence.TraduccioIJPAManager;
import es.caib.portafib.model.dao.ITraduccioManager;

@Local
public interface TraduccioService extends TraduccioIJPAManager,ITraduccioManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/TraduccioEJB!es.caib.portafib.ejb.TraduccioService";

    public TraduccioJPA findByPrimaryKey(Long _ID_);
}
