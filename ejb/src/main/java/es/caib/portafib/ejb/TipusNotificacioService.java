
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.TipusNotificacioJPA;
import es.caib.portafib.persistence.TipusNotificacioIJPAManager;
import es.caib.portafib.model.dao.ITipusNotificacioManager;

import es.caib.portafib.model.entity.TipusNotificacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TipusNotificacioService extends TipusNotificacioIJPAManager,ITipusNotificacioManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/TipusNotificacioEJB!es.caib.portafib.ejb.TipusNotificacioService";

    public TipusNotificacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TipusNotificacio instance, FitxerService fitxerEjb) throws I18NException;
}
