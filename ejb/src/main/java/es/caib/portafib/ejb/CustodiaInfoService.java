
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.CustodiaInfoJPA;
import es.caib.portafib.persistence.CustodiaInfoIJPAManager;
import es.caib.portafib.model.dao.ICustodiaInfoManager;

@Local
public interface CustodiaInfoService extends CustodiaInfoIJPAManager,ICustodiaInfoManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/CustodiaInfoEJB!es.caib.portafib.ejb.CustodiaInfoService";

    public CustodiaInfoJPA findByPrimaryKey(Long _ID_);
}
