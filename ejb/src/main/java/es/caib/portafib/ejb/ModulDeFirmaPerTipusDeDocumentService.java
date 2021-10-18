
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.ModulDeFirmaPerTipusDeDocumentJPA;
import es.caib.portafib.persistence.ModulDeFirmaPerTipusDeDocumentIJPAManager;
import es.caib.portafib.model.dao.IModulDeFirmaPerTipusDeDocumentManager;

@Local
public interface ModulDeFirmaPerTipusDeDocumentService extends ModulDeFirmaPerTipusDeDocumentIJPAManager,IModulDeFirmaPerTipusDeDocumentManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/ModulDeFirmaPerTipusDeDocumentEJB!es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentService";

    public ModulDeFirmaPerTipusDeDocumentJPA findByPrimaryKey(Long _ID_);
}
