
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.IdiomaJPA;
import es.caib.portafib.model.dao.IIdiomaManager;

@Local
public interface IdiomaLocal extends IIdiomaManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/IdiomaEJB";

  public IdiomaJPA findByPrimaryKey(String _ID_);
}
