
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.TipusEstatDeFirmaInicialJPA;
import es.caib.portafib.model.dao.ITipusEstatDeFirmaInicialManager;

@Local
public interface TipusEstatDeFirmaInicialLocal extends ITipusEstatDeFirmaInicialManager {

 public static final String JNDI_NAME = "portafib/TipusEstatDeFirmaInicialEJB/local";
  public TipusEstatDeFirmaInicialJPA findByPrimaryKey(Long _ID_);
}
