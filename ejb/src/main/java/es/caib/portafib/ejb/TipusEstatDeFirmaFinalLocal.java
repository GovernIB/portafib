
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.TipusEstatDeFirmaFinalJPA;
import es.caib.portafib.model.dao.ITipusEstatDeFirmaFinalManager;

@Local
public interface TipusEstatDeFirmaFinalLocal extends ITipusEstatDeFirmaFinalManager {

 public static final String JNDI_NAME = "portafib/TipusEstatDeFirmaFinalEJB/local";
  public TipusEstatDeFirmaFinalJPA findByPrimaryKey(Long _ID_);
}
