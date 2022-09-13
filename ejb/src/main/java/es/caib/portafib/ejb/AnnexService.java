
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.AnnexJPA;
import es.caib.portafib.persistence.AnnexIJPAManager;
import es.caib.portafib.model.dao.IAnnexManager;

import es.caib.portafib.model.entity.Annex;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface AnnexService extends AnnexIJPAManager,IAnnexManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/AnnexEJB!es.caib.portafib.ejb.AnnexService";

    public AnnexJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Annex instance, FitxerService fitxerEjb) throws I18NException;
}
