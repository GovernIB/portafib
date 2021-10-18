
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.AnnexJPA;
import es.caib.portafib.persistence.AnnexIJPAManager;
import es.caib.portafib.model.dao.IAnnexManager;

@Local
public interface AnnexService extends AnnexIJPAManager,IAnnexManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/AnnexEJB!es.caib.portafib.ejb.AnnexService";

    public AnnexJPA findByPrimaryKey(Long _ID_);
}
