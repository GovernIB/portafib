package es.caib.portafib.logic;

import es.caib.portafib.ejb.UsuariAplicacioLocal;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;

import javax.ejb.Local;
import java.util.Set;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface UsuariAplicacioLogicaLocal extends UsuariAplicacioLocal {

  public static final String BEAN_NAME = "UsuariAplicacioLogicaEJB";

  public static final String JNDI_NAME = "portafib/" + BEAN_NAME + "/local";

  public UsuariAplicacioJPA findByPrimaryKeyFull(String _usuariAplicacioID_);

  public UsuariAplicacioJPA checkForDeletion(String usuariAplicacioID)
      throws Exception, I18NException;

  public void checkForDisable(String usuariAplicacioID) throws Exception, I18NException;

  public Set<Long> deleteFull(String usuariAplicacio) throws Exception, I18NException;

  public void activar(String usuariAplicacioID) throws I18NException, Exception;

  public void desactivar(String usuariAplicacioID) throws I18NException, Exception;

  public UsuariAplicacioJPA createFull(UsuariAplicacioJPA _usuariAplicacio_, String entitatID)
      throws I18NException, I18NValidationException;

  public UsuariAplicacioJPA checkBasicUsuariAplicacioID(String usuariAplicacioID)
      throws I18NException;

}
