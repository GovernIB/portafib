
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.CodiBarresJPA;
import es.caib.portafib.persistence.CodiBarresIJPAManager;
import es.caib.portafib.model.dao.ICodiBarresManager;

@Local
public interface CodiBarresService extends CodiBarresIJPAManager,ICodiBarresManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/CodiBarresEJB!es.caib.portafib.ejb.CodiBarresService";

    public CodiBarresJPA findByPrimaryKey(String _ID_);
}
