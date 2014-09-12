
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.model.dao.IFluxDeFirmesManager;

@Local
public interface FluxDeFirmesLocal extends IFluxDeFirmesManager {

 public static final String JNDI_NAME = "portafib/FluxDeFirmesEJB/local";
  public FluxDeFirmesJPA findByPrimaryKey(Long _ID_);
}
