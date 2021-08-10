
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.model.dao.IFluxDeFirmesManager;

@Local
public interface FluxDeFirmesLocal extends IFluxDeFirmesManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/FluxDeFirmesEJB";

  public FluxDeFirmesJPA findByPrimaryKey(Long _ID_);
}
