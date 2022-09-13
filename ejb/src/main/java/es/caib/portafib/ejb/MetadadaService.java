
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.MetadadaJPA;
import es.caib.portafib.persistence.MetadadaIJPAManager;
import es.caib.portafib.model.dao.IMetadadaManager;

import es.caib.portafib.model.entity.Metadada;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface MetadadaService extends MetadadaIJPAManager,IMetadadaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/MetadadaEJB!es.caib.portafib.ejb.MetadadaService";

    public MetadadaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Metadada instance, FitxerService fitxerEjb) throws I18NException;
}
