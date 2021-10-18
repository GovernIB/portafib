
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.IdiomaJPA;
import es.caib.portafib.persistence.IdiomaIJPAManager;
import es.caib.portafib.model.dao.IIdiomaManager;

@Local
public interface IdiomaService extends IdiomaIJPAManager,IIdiomaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/IdiomaEJB!es.caib.portafib.ejb.IdiomaService";

    public IdiomaJPA findByPrimaryKey(String _ID_);
}
