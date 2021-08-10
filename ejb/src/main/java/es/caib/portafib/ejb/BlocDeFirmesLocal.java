
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.model.dao.IBlocDeFirmesManager;

@Local
public interface BlocDeFirmesLocal extends IBlocDeFirmesManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/BlocDeFirmesEJB";

  public BlocDeFirmesJPA findByPrimaryKey(Long _ID_);
}
