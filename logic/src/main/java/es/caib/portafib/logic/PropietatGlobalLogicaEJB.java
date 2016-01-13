package es.caib.portafib.logic;

import es.caib.portafib.ejb.PropietatGlobalEJB;
import javax.ejb.Stateless;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 * 
 */
@Stateless(name = "PropietatGlobalLogicaEJB")
@SecurityDomain("seycon")
public class PropietatGlobalLogicaEJB extends PropietatGlobalEJB implements PropietatGlobalLogicaLocal {

  @Override
  public String getProperty(String key) throws I18NException {
    
     return getPropertyByEntitat(null, key);
   
  }

  @Override
  public String getProperty(String key, String defaultValue)  throws I18NException {
    return getPropertyByEntitat(null, key, defaultValue);
  }

  @Override
  public Boolean getBooleanProperty(String key) throws I18NException {
    return getBooleanPropertyByEntitat(null, key);
  }

  @Override
  public boolean getBooleanProperty(String key, boolean defaultValue) throws I18NException {
    return getBooleanPropertyByEntitat(null, key, defaultValue);
  }


  @Override
  public String getPropertyByEntitat(String entitatID, String key) throws I18NException {
    Where w;
    if (entitatID == null) {
      w = ENTITATID.isNull();
    } else {
      w = ENTITATID.equal(entitatID);
    }
    return executeQueryOne(VALOR, Where.AND(CLAU.equal(key), w));

  }

  @Override
  public String getPropertyByEntitat(String entitatID, String key, String defaultValue)
      throws I18NException {
    String value = getProperty(entitatID, key) ;
    return (value == null)?defaultValue : value;
  }


  @Override
  public Boolean getBooleanPropertyByEntitat(String entitatID, String key) throws I18NException {
    String value = getPropertyByEntitat(entitatID, key);
    if (value == null) {
      return null;
    } else {
      return "true".equals(value);
    }
  }

  @Override
  public boolean getBooleanPropertyByEntitat(String entitatID, String key, boolean defaultValue)
      throws I18NException {
    Boolean value = getBooleanPropertyByEntitat(entitatID, key);
    return (value == null) ? defaultValue : value;
  }

  @Override
  public Long getLongProperty(String key) throws I18NException {

    return getLongPropertyByEntitat(null, key);
  }

  @Override
  public long getLongProperty(String key, long defaultValue) throws I18NException {
    return getLongPropertyByEntitat(null, key, defaultValue);
  }

  @Override
  public Long getLongPropertyByEntitat(String entitatID, String key) throws I18NException {
    String value = getPropertyByEntitat(entitatID, key);
    if (value == null) {
      return null;
    } else {
      try {
        return Long.parseLong(value);
      } catch(NumberFormatException e) {
        log.error("Error convertint a long el valor (" + value + ")  de la propietat "
           + key + ": " + e.getMessage(), e);
        return null;
      }
    }
  }

  @Override
  public long getLongPropertyByEntitat(String entitatID, String key, long defaultValue)
      throws I18NException {
    Long value = getLongPropertyByEntitat(entitatID, key);
    if (value == null) {
      return defaultValue;
    } else {
      return value;
    }
  }




}
