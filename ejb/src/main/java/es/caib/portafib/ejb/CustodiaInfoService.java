
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.CustodiaInfoJPA;
import es.caib.portafib.persistence.CustodiaInfoIJPAManager;
import es.caib.portafib.model.dao.ICustodiaInfoManager;

import es.caib.portafib.model.entity.CustodiaInfo;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface CustodiaInfoService extends CustodiaInfoIJPAManager,ICustodiaInfoManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/CustodiaInfoEJB!es.caib.portafib.ejb.CustodiaInfoService";

    public CustodiaInfoJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(CustodiaInfo instance, FitxerService fitxerEjb) throws I18NException;
}
