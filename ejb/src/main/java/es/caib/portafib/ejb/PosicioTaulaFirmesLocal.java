
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PosicioTaulaFirmesJPA;
import es.caib.portafib.model.dao.IPosicioTaulaFirmesManager;

@Local
public interface PosicioTaulaFirmesLocal extends IPosicioTaulaFirmesManager {

 public static final String JNDI_NAME = "portafib/PosicioTaulaFirmesEJB/local";
  public PosicioTaulaFirmesJPA findByPrimaryKey(Integer _ID_);
}
