
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.GrupEntitatJPA;
import es.caib.portafib.persistence.GrupEntitatIJPAManager;
import es.caib.portafib.model.dao.IGrupEntitatManager;

import es.caib.portafib.model.entity.GrupEntitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface GrupEntitatService extends GrupEntitatIJPAManager,IGrupEntitatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/GrupEntitatEJB!es.caib.portafib.ejb.GrupEntitatService";

    public GrupEntitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(GrupEntitat instance, FitxerService fitxerEjb) throws I18NException;
}
