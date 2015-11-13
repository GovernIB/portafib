
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.ModulDeFirmaJPA;
import es.caib.portafib.model.dao.IModulDeFirmaManager;

@Local
public interface ModulDeFirmaLocal extends IModulDeFirmaManager {

 public static final String JNDI_NAME = "portafib/ModulDeFirmaEJB/local";
  public ModulDeFirmaJPA findByPrimaryKey(Long _ID_);
}
