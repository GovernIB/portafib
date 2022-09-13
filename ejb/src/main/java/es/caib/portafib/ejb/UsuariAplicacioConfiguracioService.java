
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioIJPAManager;
import es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager;

import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariAplicacioConfiguracioService extends UsuariAplicacioConfiguracioIJPAManager,IUsuariAplicacioConfiguracioManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/UsuariAplicacioConfiguracioEJB!es.caib.portafib.ejb.UsuariAplicacioConfiguracioService";

    public UsuariAplicacioConfiguracioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(UsuariAplicacioConfiguracio instance, FitxerService fitxerEjb) throws I18NException;
}
