
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.TipusDocumentJPA;
import es.caib.portafib.persistence.TipusDocumentIJPAManager;
import es.caib.portafib.model.dao.ITipusDocumentManager;

@Local
public interface TipusDocumentService extends TipusDocumentIJPAManager,ITipusDocumentManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/TipusDocumentEJB!es.caib.portafib.ejb.TipusDocumentService";

    public TipusDocumentJPA findByPrimaryKey(Long _ID_);
}
