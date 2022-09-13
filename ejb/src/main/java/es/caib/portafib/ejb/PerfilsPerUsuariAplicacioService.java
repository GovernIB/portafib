
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PerfilsPerUsuariAplicacioJPA;
import es.caib.portafib.persistence.PerfilsPerUsuariAplicacioIJPAManager;
import es.caib.portafib.model.dao.IPerfilsPerUsuariAplicacioManager;

import es.caib.portafib.model.entity.PerfilsPerUsuariAplicacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PerfilsPerUsuariAplicacioService extends PerfilsPerUsuariAplicacioIJPAManager,IPerfilsPerUsuariAplicacioManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PerfilsPerUsuariAplicacioEJB!es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService";

    public PerfilsPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PerfilsPerUsuariAplicacio instance, FitxerService fitxerEjb) throws I18NException;
}
