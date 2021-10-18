
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.model.dao.IFirmaManager;

@Local
public interface FirmaLocal extends IFirmaManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/FirmaEJB";

  public FirmaJPA findByPrimaryKey(Long _ID_);
}
