
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.TipusFirmaJPA;
import es.caib.portafib.model.dao.ITipusFirmaManager;

@Local
public interface TipusFirmaLocal extends ITipusFirmaManager {

 public static final String JNDI_NAME = "portafib/TipusFirmaEJB/local";
  public TipusFirmaJPA findByPrimaryKey(Integer _ID_);
}
