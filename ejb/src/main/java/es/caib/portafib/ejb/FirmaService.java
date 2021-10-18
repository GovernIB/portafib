
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.FirmaIJPAManager;
import es.caib.portafib.model.dao.IFirmaManager;

@Local
public interface FirmaService extends FirmaIJPAManager,IFirmaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/FirmaEJB!es.caib.portafib.ejb.FirmaService";

    public FirmaJPA findByPrimaryKey(Long _ID_);
}
