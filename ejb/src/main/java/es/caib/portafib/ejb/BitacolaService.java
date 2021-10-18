
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.BitacolaJPA;
import es.caib.portafib.persistence.BitacolaIJPAManager;
import es.caib.portafib.model.dao.IBitacolaManager;

@Local
public interface BitacolaService extends BitacolaIJPAManager,IBitacolaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/BitacolaEJB!es.caib.portafib.ejb.BitacolaService";

    public BitacolaJPA findByPrimaryKey(Long _ID_);
}
