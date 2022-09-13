
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.EstadisticaJPA;
import es.caib.portafib.persistence.EstadisticaIJPAManager;
import es.caib.portafib.model.dao.IEstadisticaManager;

import es.caib.portafib.model.entity.Estadistica;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EstadisticaService extends EstadisticaIJPAManager,IEstadisticaManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/EstadisticaEJB!es.caib.portafib.ejb.EstadisticaService";

    public EstadisticaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Estadistica instance, FitxerService fitxerEjb) throws I18NException;
}
