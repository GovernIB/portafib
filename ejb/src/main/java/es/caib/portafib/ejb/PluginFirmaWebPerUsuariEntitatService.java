
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PluginFirmaWebPerUsuariEntitatJPA;
import es.caib.portafib.persistence.PluginFirmaWebPerUsuariEntitatIJPAManager;
import es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariEntitatManager;

import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariEntitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PluginFirmaWebPerUsuariEntitatService extends PluginFirmaWebPerUsuariEntitatIJPAManager,IPluginFirmaWebPerUsuariEntitatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PluginFirmaWebPerUsuariEntitatEJB!es.caib.portafib.ejb.PluginFirmaWebPerUsuariEntitatService";

    public PluginFirmaWebPerUsuariEntitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PluginFirmaWebPerUsuariEntitat instance, FitxerService fitxerEjb) throws I18NException;
}
