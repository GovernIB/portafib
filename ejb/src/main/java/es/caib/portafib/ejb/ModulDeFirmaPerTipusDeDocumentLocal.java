
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.ModulDeFirmaPerTipusDeDocumentJPA;
import es.caib.portafib.model.dao.IModulDeFirmaPerTipusDeDocumentManager;

@Local
public interface ModulDeFirmaPerTipusDeDocumentLocal extends IModulDeFirmaPerTipusDeDocumentManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/ModulDeFirmaPerTipusDeDocumentEJB";

  public ModulDeFirmaPerTipusDeDocumentJPA findByPrimaryKey(Long _ID_);
}
