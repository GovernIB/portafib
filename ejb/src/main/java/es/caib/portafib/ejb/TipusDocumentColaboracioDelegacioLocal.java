
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.TipusDocumentColaboracioDelegacioJPA;
import es.caib.portafib.model.dao.ITipusDocumentColaboracioDelegacioManager;

@Local
public interface TipusDocumentColaboracioDelegacioLocal extends ITipusDocumentColaboracioDelegacioManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/TipusDocumentColaboracioDelegacioEJB";

  public TipusDocumentColaboracioDelegacioJPA findByPrimaryKey(Long _ID_);
}
