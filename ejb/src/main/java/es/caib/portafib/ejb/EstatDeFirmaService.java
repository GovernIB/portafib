
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.EstatDeFirmaJPA;
import es.caib.portafib.persistence.EstatDeFirmaIJPAManager;
import es.caib.portafib.model.dao.IEstatDeFirmaManager;

import es.caib.portafib.model.entity.EstatDeFirma;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EstatDeFirmaService extends EstatDeFirmaIJPAManager,IEstatDeFirmaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/EstatDeFirmaEJB!es.caib.portafib.ejb.EstatDeFirmaService";

    public EstatDeFirmaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(EstatDeFirma instance, FitxerService fitxerEjb) throws I18NException;
}
