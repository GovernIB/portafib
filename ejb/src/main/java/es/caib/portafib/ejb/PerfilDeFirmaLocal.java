
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PerfilDeFirmaJPA;
import es.caib.portafib.model.dao.IPerfilDeFirmaManager;

@Local
public interface PerfilDeFirmaLocal extends IPerfilDeFirmaManager {

 public static final String JNDI_NAME = "portafib/PerfilDeFirmaEJB/local";
  public PerfilDeFirmaJPA findByPrimaryKey(Long _ID_);
}
