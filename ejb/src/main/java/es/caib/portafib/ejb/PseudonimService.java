
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PseudonimJPA;
import es.caib.portafib.persistence.PseudonimIJPAManager;
import es.caib.portafib.model.dao.IPseudonimManager;

import es.caib.portafib.model.entity.Pseudonim;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PseudonimService extends PseudonimIJPAManager,IPseudonimManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PseudonimEJB!es.caib.portafib.ejb.PseudonimService";

    public PseudonimJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Pseudonim instance, FitxerService fitxerEjb) throws I18NException;
}
