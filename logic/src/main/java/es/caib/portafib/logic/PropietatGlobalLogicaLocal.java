package es.caib.portafib.logic;

import es.caib.portafib.ejb.PropietatGlobalLocal;
import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
/**
 * 
 * @author anadal
 *
 */
@Local
public interface PropietatGlobalLogicaLocal extends PropietatGlobalLocal {

  String JNDI_NAME = "java:app/portafib-logic/PropietatGlobalLogicaEJB";
  
  // === PROPIETATS GLOBALS

  public String getProperty(String key) throws I18NException;
  
  public String getProperty(String key, String defaultValue) throws I18NException;

  
  public Boolean getBooleanProperty(String key) throws I18NException;
  
  public boolean getBooleanProperty(String key, boolean defaultValue) throws I18NException;
  
  public Long getLongProperty(String key) throws I18NException;
  
  public long getLongProperty(String key, long defaultValue) throws I18NException;

  // PROPIETATS PER ENTITAT
  
  
  public String getPropertyByEntitat(String entitatID, String key) throws I18NException;
  
  public String getPropertyByEntitat(String entitatID, String key, String defaultValue) throws I18NException;
  
  
  public Boolean getBooleanPropertyByEntitat(String entitatID, String key) throws I18NException;
  
  public boolean getBooleanPropertyByEntitat(String entitatID, String key, boolean defaultValue) throws I18NException;

  
  public Long getLongPropertyByEntitat(String entitatID, String key) throws I18NException;
  
  public long getLongPropertyByEntitat(String entitatID, String key, long defaultValue) throws I18NException;

  public List<Long> getIdsProperty(String key) throws I18NException;
  
  public List<Long> getIdsProperty(String entitatID, String key) throws I18NException;


  
  
}

