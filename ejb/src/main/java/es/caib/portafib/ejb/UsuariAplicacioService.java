
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.persistence.UsuariAplicacioIJPAManager;
import es.caib.portafib.model.dao.IUsuariAplicacioManager;

@Local
public interface UsuariAplicacioService extends UsuariAplicacioIJPAManager,IUsuariAplicacioManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/UsuariAplicacioEJB!es.caib.portafib.ejb.UsuariAplicacioService";

    public UsuariAplicacioJPA findByPrimaryKey(String _ID_);
}
