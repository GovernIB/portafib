
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.persistence.PeticioDeFirmaIJPAManager;
import es.caib.portafib.model.dao.IPeticioDeFirmaManager;

import es.caib.portafib.model.entity.PeticioDeFirma;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PeticioDeFirmaService extends PeticioDeFirmaIJPAManager,IPeticioDeFirmaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/PeticioDeFirmaEJB!es.caib.portafib.ejb.PeticioDeFirmaService";

    public PeticioDeFirmaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PeticioDeFirma instance, FitxerService fitxerEjb) throws I18NException;
}
