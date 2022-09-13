
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.TipusDocumentJPA;
import es.caib.portafib.persistence.TipusDocumentIJPAManager;
import es.caib.portafib.model.dao.ITipusDocumentManager;

import es.caib.portafib.model.entity.TipusDocument;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TipusDocumentService extends TipusDocumentIJPAManager,ITipusDocumentManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/TipusDocumentEJB!es.caib.portafib.ejb.TipusDocumentService";

    public TipusDocumentJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TipusDocument instance, FitxerService fitxerEjb) throws I18NException;
}
