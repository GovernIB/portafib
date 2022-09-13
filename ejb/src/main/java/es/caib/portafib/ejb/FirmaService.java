
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.FirmaIJPAManager;
import es.caib.portafib.model.dao.IFirmaManager;

import es.caib.portafib.model.entity.Firma;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface FirmaService extends FirmaIJPAManager,IFirmaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/FirmaEJB!es.caib.portafib.ejb.FirmaService";

    public FirmaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Firma instance, FitxerService fitxerEjb) throws I18NException;
}
