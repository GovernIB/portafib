
package es.caib.portafib.ejb;

import javax.ejb.Local;

import es.caib.portafib.jpa.AlgorismeDeFirmaJPA;
import es.caib.portafib.model.dao.IAlgorismeDeFirmaManager;

@Local
public interface AlgorismeDeFirmaLocal extends IAlgorismeDeFirmaManager {

 public static final String JNDI_NAME = "portafib/AlgorismeDeFirmaEJB/local";
  public AlgorismeDeFirmaJPA findByPrimaryKey(Long _ID_);
}
