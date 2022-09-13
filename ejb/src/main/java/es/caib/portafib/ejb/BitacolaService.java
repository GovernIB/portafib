
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.BitacolaJPA;
import es.caib.portafib.persistence.BitacolaIJPAManager;
import es.caib.portafib.model.dao.IBitacolaManager;

import es.caib.portafib.model.entity.Bitacola;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface BitacolaService extends BitacolaIJPAManager,IBitacolaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/BitacolaEJB!es.caib.portafib.ejb.BitacolaService";

    public BitacolaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Bitacola instance, FitxerService fitxerEjb) throws I18NException;
}
