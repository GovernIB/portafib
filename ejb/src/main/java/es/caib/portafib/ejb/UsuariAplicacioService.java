
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.persistence.UsuariAplicacioIJPAManager;
import es.caib.portafib.model.dao.IUsuariAplicacioManager;

import es.caib.portafib.model.entity.UsuariAplicacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariAplicacioService extends UsuariAplicacioIJPAManager,IUsuariAplicacioManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/UsuariAplicacioEJB!es.caib.portafib.ejb.UsuariAplicacioService";

    public UsuariAplicacioJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(UsuariAplicacio instance, FitxerService fitxerEjb) throws I18NException;
}
