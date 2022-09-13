
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.CodiBarresJPA;
import es.caib.portafib.persistence.CodiBarresIJPAManager;
import es.caib.portafib.model.dao.ICodiBarresManager;

import es.caib.portafib.model.entity.CodiBarres;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface CodiBarresService extends CodiBarresIJPAManager,ICodiBarresManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/CodiBarresEJB!es.caib.portafib.ejb.CodiBarresService";

    public CodiBarresJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(CodiBarres instance, FitxerService fitxerEjb) throws I18NException;
}
