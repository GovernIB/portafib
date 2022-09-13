
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.AnnexFirmatJPA;
import es.caib.portafib.persistence.AnnexFirmatIJPAManager;
import es.caib.portafib.model.dao.IAnnexFirmatManager;

import es.caib.portafib.model.entity.AnnexFirmat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface AnnexFirmatService extends AnnexFirmatIJPAManager,IAnnexFirmatManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/AnnexFirmatEJB!es.caib.portafib.ejb.AnnexFirmatService";

    public AnnexFirmatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(AnnexFirmat instance, FitxerService fitxerEjb) throws I18NException;
}
