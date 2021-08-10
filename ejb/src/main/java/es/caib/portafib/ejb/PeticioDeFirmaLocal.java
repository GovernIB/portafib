
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.model.dao.IPeticioDeFirmaManager;

@Local
public interface PeticioDeFirmaLocal extends IPeticioDeFirmaManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/PeticioDeFirmaEJB";

  public PeticioDeFirmaJPA findByPrimaryKey(Long _ID_);
}
