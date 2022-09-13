
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PerfilDeFirmaJPA;
import es.caib.portafib.persistence.PerfilDeFirmaIJPAManager;
import es.caib.portafib.model.dao.IPerfilDeFirmaManager;

import es.caib.portafib.model.entity.PerfilDeFirma;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PerfilDeFirmaService extends PerfilDeFirmaIJPAManager,IPerfilDeFirmaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PerfilDeFirmaEJB!es.caib.portafib.ejb.PerfilDeFirmaService";

    public PerfilDeFirmaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PerfilDeFirma instance, FitxerService fitxerEjb) throws I18NException;
}
