
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PerfilDeFirmaJPA;
import es.caib.portafib.persistence.PerfilDeFirmaIJPAManager;
import es.caib.portafib.model.dao.IPerfilDeFirmaManager;

@Local
public interface PerfilDeFirmaService extends PerfilDeFirmaIJPAManager,IPerfilDeFirmaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PerfilDeFirmaEJB!es.caib.portafib.ejb.PerfilDeFirmaService";

    public PerfilDeFirmaJPA findByPrimaryKey(Long _ID_);
}
