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

  public static final String JNDI_NAME = "portafib/EntitatLogicaEJB/local";

  public void deleteFull(String entitatID) throws I18NException;
  
  public EntitatJPA findByPrimaryKeyPublic(String entitatID) throws I18NException;

}
