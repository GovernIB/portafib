
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.TipusDocumentColaboracioDelegacioJPA;
import es.caib.portafib.persistence.TipusDocumentColaboracioDelegacioIJPAManager;
import es.caib.portafib.model.dao.ITipusDocumentColaboracioDelegacioManager;

import es.caib.portafib.model.entity.TipusDocumentColaboracioDelegacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TipusDocumentColaboracioDelegacioService extends TipusDocumentColaboracioDelegacioIJPAManager,ITipusDocumentColaboracioDelegacioManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/TipusDocumentColaboracioDelegacioEJB!es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioService";

    public TipusDocumentColaboracioDelegacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TipusDocumentColaboracioDelegacio instance, FitxerService fitxerEjb) throws I18NException;
}
