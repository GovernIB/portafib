package es.caib.portafib.logic;

import es.caib.portafib.ejb.EntitatLocal;


import es.caib.portafib.jpa.EntitatJPA;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface EntitatLogicaLocal extends EntitatLocal {

  String JNDI_NAME = "java:app/portafib-logic/EntitatLogicaEJB";

  public void deleteFull(String entitatID) throws I18NException;
  
  public EntitatJPA findByPrimaryKeyPublic(String entitatID) throws I18NException;

}
