
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.model.dao.ICustodiaInfoManager;

@Local
public interface CustodiaInfoLocal extends ICustodiaInfoManager {

 public static final String JNDI_NAME = "portafib/CustodiaInfoEJB/local";
  public CustodiaInfoJPA findByPrimaryKey(Long _ID_);
}
