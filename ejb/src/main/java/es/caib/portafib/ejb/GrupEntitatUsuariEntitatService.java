
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.GrupEntitatUsuariEntitatJPA;
import es.caib.portafib.persistence.GrupEntitatUsuariEntitatIJPAManager;
import es.caib.portafib.model.dao.IGrupEntitatUsuariEntitatManager;

import es.caib.portafib.model.entity.GrupEntitatUsuariEntitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface GrupEntitatUsuariEntitatService extends GrupEntitatUsuariEntitatIJPAManager,IGrupEntitatUsuariEntitatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/GrupEntitatUsuariEntitatEJB!es.caib.portafib.ejb.GrupEntitatUsuariEntitatService";

    public GrupEntitatUsuariEntitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(GrupEntitatUsuariEntitat instance, FitxerService fitxerEjb) throws I18NException;
}
