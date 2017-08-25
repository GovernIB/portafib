package es.caib.portafib.ws.v2.impl;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;

import es.caib.portafib.model.bean.UsuariEntitatBean;
import es.caib.portafib.model.bean.UsuariPersonaBean;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.v2.impl.utils.IBaseAutenticatedWs;


/**
 * 
 * @author anadal
 *
 */
@WebService
public interface PortaFIBUsuariEntitatWs extends IBaseAutenticatedWs, Constants {

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ------------------------| Usuari Persona |-------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @RolesAllowed({ PFI_ADMIN})
  @WebMethod
  public UsuariPersonaBean getInfoFromPluginUserInfoByAdministrationID(
      @WebParam(name = "administrationID") String administrationID) 
  throws WsI18NException, Throwable;
  
  @RolesAllowed({ PFI_ADMIN})
  @WebMethod
  public UsuariPersonaBean getInfoFromPluginUserInfoByUsername(
      @WebParam(name = "administrationID") String administrationID) 
  throws WsI18NException, Throwable;

  @RolesAllowed({ PFI_ADMIN, PFI_USER})
  @WebMethod
  public String getUsuariPersonaIDByAdministrationID(
      @WebParam(name = "administrationID") String administrationID)
      throws WsValidationException, WsI18NException, Throwable;

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public UsuariPersonaBean getUsuariPersona(
      @WebParam(name = "usuariPersonaID") String usuariPersonaID)
      throws WsValidationException, WsI18NException, Throwable;
  
  /**
   * Nou a PortaFIB v2.0
   * @param usuariPersonaID
   * @return
   * @throws WsValidationException
   * @throws WsI18NException
   * @throws Throwable
   */
  @RolesAllowed({ PFI_ADMIN , PFI_USER })
  @WebMethod
  public String getUsuariPersonaNom(
      @WebParam(name = "usuariPersonaID") String usuariPersonaID)
      throws WsValidationException, WsI18NException, Throwable;

  @WebMethod
  @RolesAllowed({ PFI_ADMIN })
  public void createUsuariPersona(
      @WebParam(name = "usuariPersonaBean") UsuariPersonaBean usuariPersonaBean)
      throws WsValidationException, WsI18NException, Throwable;

  @WebMethod
  @RolesAllowed({ PFI_ADMIN })
  public void deleteUsuariPersona(@WebParam(name = "usuariPersonaID") String usuariPersonaID)
      throws WsI18NException, Throwable;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ------------------------| Usuari Entitat |-------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @WebMethod
  @RolesAllowed({ PFI_ADMIN})
  public UsuariEntitatBean getUsuariEntitat(
      @WebParam(name = "usuariEntitatID") String usuariEntitatID
      ) throws Throwable;

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public String getUsuariEntitatIDByAdministrationID(
      @WebParam(name = "administrationID") String administrationID,
      @WebParam(name = "entitatID") String entitatID)
      throws WsI18NException, Throwable;

  @RolesAllowed({ PFI_ADMIN, PFI_USER })
  @WebMethod
  public String getUsuariEntitatIDInMyEntitatByAdministrationID(
      @WebParam(name = "administrationID") String administrationID)
      throws WsI18NException, Throwable;
  
  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public String getUsuariEntitatIDByUsuariPersonaID(
      @WebParam(name = "usuariPersonaID") String username,
      @WebParam(name = "entitatID") String entitatID)
      throws WsI18NException, Throwable;
  
  @RolesAllowed({ PFI_ADMIN, PFI_USER })
  @WebMethod
  public String getUsuariEntitatIDInMyEntitatByUsuariPersonaID(
      @WebParam(name = "usuariPersonaID") String username)
      throws WsI18NException, Throwable;

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public UsuariEntitatBean createUsuariEntitatSimple(
      @WebParam(name = "administrationID") String administrationID,
      @WebParam(name = "entitatID") String entitatID) throws WsValidationException,
      WsI18NException, Throwable;

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public UsuariEntitatBean createUsuariEntitat(
      @WebParam(name = "usuariEntitatBean") UsuariEntitatBean usuariEntitatBean)
      throws WsValidationException, WsI18NException, Throwable;
  
  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void deleteUsuariEntitat(
      @WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable;

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void activateUsuariEntitat(@WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable;

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void deactivateUsuariEntitat(
      @WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable;

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void addRoleSolicitant(@WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable;
  
  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void removeRoleSolicitant(@WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable;

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void addRoleAdministradorDeEntitat(@WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable;

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void removeRoleAdministradorDeEntitat(@WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable;

}
