
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.model.dao.IEstatDeFirmaManager;

@Local
public interface EstatDeFirmaLocal extends IEstatDeFirmaManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/EstatDeFirmaEJB";

  public EstatDeFirmaJPA findByPrimaryKey(Long _ID_);
}
