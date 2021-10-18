
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.CodiBarresJPA;
import es.caib.portafib.model.dao.ICodiBarresManager;

@Local
public interface CodiBarresLocal extends ICodiBarresManager {

  public static final String JNDI_NAME = "java:app/portafib-ejb/CodiBarresEJB";

  public CodiBarresJPA findByPrimaryKey(String _ID_);
}
