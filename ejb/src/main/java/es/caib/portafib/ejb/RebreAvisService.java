
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.RebreAvisJPA;
import es.caib.portafib.persistence.RebreAvisIJPAManager;
import es.caib.portafib.model.dao.IRebreAvisManager;

import es.caib.portafib.model.entity.RebreAvis;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface RebreAvisService extends RebreAvisIJPAManager,IRebreAvisManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/RebreAvisEJB!es.caib.portafib.ejb.RebreAvisService";

    public RebreAvisJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(RebreAvis instance, FitxerService fitxerEjb) throws I18NException;
}
