
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.BitacolaJPA;
import es.caib.portafib.model.dao.IBitacolaManager;

@Local
public interface BitacolaLocal extends IBitacolaManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/BitacolaEJB";

  public BitacolaJPA findByPrimaryKey(Long _ID_);
}
