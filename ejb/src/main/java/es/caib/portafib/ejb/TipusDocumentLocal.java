
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.TipusDocumentJPA;
import es.caib.portafib.model.dao.ITipusDocumentManager;

@Local
public interface TipusDocumentLocal extends ITipusDocumentManager {

 public static final String JNDI_NAME = "portafib/TipusDocumentEJB/local";
  public TipusDocumentJPA findByPrimaryKey(Long _ID_);
}
