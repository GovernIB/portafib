
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.TipusEstatPeticioDeFirmaJPA;
import es.caib.portafib.model.dao.ITipusEstatPeticioDeFirmaManager;

@Local
public interface TipusEstatPeticioDeFirmaLocal extends ITipusEstatPeticioDeFirmaManager {

 public static final String JNDI_NAME = "portafib/TipusEstatPeticioDeFirmaEJB/local";
  public TipusEstatPeticioDeFirmaJPA findByPrimaryKey(Integer _ID_);
}
