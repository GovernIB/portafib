
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.AnnexFirmatJPA;
import es.caib.portafib.model.dao.IAnnexFirmatManager;

@Local
public interface AnnexFirmatLocal extends IAnnexFirmatManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/AnnexFirmatEJB";

  public AnnexFirmatJPA findByPrimaryKey(Long _ID_);
}
