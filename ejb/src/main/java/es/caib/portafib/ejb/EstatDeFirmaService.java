
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.EstatDeFirmaJPA;
import es.caib.portafib.persistence.EstatDeFirmaIJPAManager;
import es.caib.portafib.model.dao.IEstatDeFirmaManager;

@Local
public interface EstatDeFirmaService extends EstatDeFirmaIJPAManager,IEstatDeFirmaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/EstatDeFirmaEJB!es.caib.portafib.ejb.EstatDeFirmaService";

    public EstatDeFirmaJPA findByPrimaryKey(Long _ID_);
}
