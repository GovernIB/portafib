
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariEntitatIJPAManager;
import es.caib.portafib.model.dao.IUsuariEntitatManager;

import es.caib.portafib.model.entity.UsuariEntitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariEntitatService extends UsuariEntitatIJPAManager,IUsuariEntitatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/UsuariEntitatEJB!es.caib.portafib.ejb.UsuariEntitatService";

    public UsuariEntitatJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(UsuariEntitat instance, FitxerService fitxerEjb) throws I18NException;
}
