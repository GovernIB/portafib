
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PluginFirmaWebPerUsuariAplicacioJPA;
import es.caib.portafib.persistence.PluginFirmaWebPerUsuariAplicacioIJPAManager;
import es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariAplicacioManager;

import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariAplicacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PluginFirmaWebPerUsuariAplicacioService extends PluginFirmaWebPerUsuariAplicacioIJPAManager,IPluginFirmaWebPerUsuariAplicacioManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PluginFirmaWebPerUsuariAplicacioEJB!es.caib.portafib.ejb.PluginFirmaWebPerUsuariAplicacioService";

    public PluginFirmaWebPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PluginFirmaWebPerUsuariAplicacio instance, FitxerService fitxerEjb) throws I18NException;
}
