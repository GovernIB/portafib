package es.caib.portafib.logic;

import es.caib.portafib.ejb.EntitatService;
import es.caib.portafib.persistence.EntitatJPA;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface EntitatLogicaLocal extends EntitatService {

  String JNDI_NAME = "java:app/portafib-ejb/EntitatLogicaEJB";

  public void deleteFull(String entitatID) throws I18NException;
  
  public EntitatJPA findByPrimaryKeyPublic(String entitatID) throws I18NException;

}
