
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.CorreuAgrupatJPA;
import es.caib.portafib.persistence.CorreuAgrupatIJPAManager;
import es.caib.portafib.model.dao.ICorreuAgrupatManager;

import es.caib.portafib.model.entity.CorreuAgrupat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface CorreuAgrupatService extends CorreuAgrupatIJPAManager,ICorreuAgrupatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/CorreuAgrupatEJB!es.caib.portafib.ejb.CorreuAgrupatService";

    public CorreuAgrupatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(CorreuAgrupat instance, FitxerService fitxerEjb) throws I18NException;
}
