
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.persistence.UsuariPersonaIJPAManager;
import es.caib.portafib.model.dao.IUsuariPersonaManager;

import es.caib.portafib.model.entity.UsuariPersona;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariPersonaService extends UsuariPersonaIJPAManager,IUsuariPersonaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/UsuariPersonaEJB!es.caib.portafib.ejb.UsuariPersonaService";

    public UsuariPersonaJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(UsuariPersona instance, FitxerService fitxerEjb) throws I18NException;
}
