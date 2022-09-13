
package es.caib.portafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.portafib.persistence.ModulDeFirmaPerTipusDeDocumentJPA;
import es.caib.portafib.persistence.ModulDeFirmaPerTipusDeDocumentIJPAManager;
import es.caib.portafib.model.dao.IModulDeFirmaPerTipusDeDocumentManager;

import es.caib.portafib.model.entity.ModulDeFirmaPerTipusDeDocument;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface ModulDeFirmaPerTipusDeDocumentService extends ModulDeFirmaPerTipusDeDocumentIJPAManager,IModulDeFirmaPerTipusDeDocumentManager {

    public static final String JNDI_NAME = "java:app/portafib-ejb/ModulDeFirmaPerTipusDeDocumentEJB!es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentService";

    public ModulDeFirmaPerTipusDeDocumentJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(ModulDeFirmaPerTipusDeDocument instance, FitxerService fitxerEjb) throws I18NException;
}
