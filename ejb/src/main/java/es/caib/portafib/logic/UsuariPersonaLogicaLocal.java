package es.caib.portafib.logic;

import java.util.Set;

import es.caib.portafib.ejb.UsuariPersonaService;
import es.caib.portafib.persistence.UsuariPersonaJPA;

import javax.ejb.Local;

import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;


/**
 * 
 * @author anadal
 * 
 */
@Local
public interface UsuariPersonaLogicaLocal extends UsuariPersonaService {

  String JNDI_NAME = "java:app/portafib-ejb/UsuariPersonaLogicaEJB";

  public UsuariPersonaJPA findByPrimaryKeyFull(String paramString);

  public Set<Long> deleteFull(String usuariPersonaID) throws I18NException;

  public UsuariPersonaJPA createFull(UsuariPersonaJPA usuariPersonaJPA) throws I18NException,
      I18NValidationException;

  public UserInfo checkAdministrationIDInUserInformationPlugin(String nif) throws I18NException;
  
  public UserInfo checkUsernameInUserInformationPlugin(String username) throws I18NException;

  public UsuariPersonaJPA getUsuariPersonaIDByAdministrationID(String administrationID)
      throws I18NException;
  
  public UsuariPersonaJPA getUsuariPersonaIDByUsernameOrAdministrationID(String usernameOrAdministrationID)
      throws I18NException;

  public Set<String>  getRolesOfLoggedUser() throws I18NException;

}
