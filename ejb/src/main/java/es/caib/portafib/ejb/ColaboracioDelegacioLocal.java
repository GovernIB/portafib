
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.ColaboracioDelegacioJPA;
import es.caib.portafib.model.dao.IColaboracioDelegacioManager;

@Local
public interface ColaboracioDelegacioLocal extends IColaboracioDelegacioManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/ColaboracioDelegacioEJB";

  public ColaboracioDelegacioJPA findByPrimaryKey(Long _ID_);
}
