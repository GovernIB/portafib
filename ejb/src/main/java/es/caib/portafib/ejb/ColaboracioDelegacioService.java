
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.ColaboracioDelegacioJPA;
import es.caib.portafib.persistence.ColaboracioDelegacioIJPAManager;
import es.caib.portafib.model.dao.IColaboracioDelegacioManager;

import es.caib.portafib.model.entity.ColaboracioDelegacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface ColaboracioDelegacioService extends ColaboracioDelegacioIJPAManager,IColaboracioDelegacioManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/ColaboracioDelegacioEJB!es.caib.portafib.ejb.ColaboracioDelegacioService";

    public ColaboracioDelegacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(ColaboracioDelegacio instance, FitxerService fitxerEjb) throws I18NException;
}
