
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.TipusDocumentColaboracioDelegacioJPA;
import es.caib.portafib.persistence.TipusDocumentColaboracioDelegacioIJPAManager;
import es.caib.portafib.model.dao.ITipusDocumentColaboracioDelegacioManager;

@Local
public interface TipusDocumentColaboracioDelegacioService extends TipusDocumentColaboracioDelegacioIJPAManager,ITipusDocumentColaboracioDelegacioManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/TipusDocumentColaboracioDelegacioEJB!es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioService";

    public TipusDocumentColaboracioDelegacioJPA findByPrimaryKey(Long _ID_);
}
