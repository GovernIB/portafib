
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.model.dao.IAnnexManager;

@Local
public interface AnnexLocal extends IAnnexManager {

 public static final String JNDI_NAME = "portafib/AnnexEJB/local";
  public AnnexJPA findByPrimaryKey(Long _ID_);
}
