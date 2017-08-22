package es.caib.portafib.ws.v2.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;

import es.caib.portafib.model.bean.UsuariAplicacioBean;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.v2.impl.utils.IBaseAutenticatedWs;

/**
 * 
 * @author anadal
 * 
 */
@WebService
public interface PortaFIBUsuariAplicacioWs extends IBaseAutenticatedWs {

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ----------------------| Usuari Aplicacio |-----------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @WebMethod
  @RolesAllowed({ Constants.PFI_ADMIN })
  public UsuariAplicacioBean getUsuariAplicacio(
      @WebParam(name = "usuariAplicacioID") String usuariAplicacioID) throws WsI18NException,
      Throwable;

  @WebMethod
  @RolesAllowed({ Constants.PFI_ADMIN })
  public void createUsuariAplicacio(
      @WebParam(name = "usuariAplicacioBean") UsuariAplicacioBean usuariAplicacioBean)
      throws WsValidationException, WsI18NException, Throwable;

  @WebMethod
  @RolesAllowed({ Constants.PFI_ADMIN })
  public void deleteUsuariAplicacio(
      @WebParam(name = "usuariAplicacioID") String usuariAplicacioID) throws WsI18NException,
      Throwable;

  @WebMethod
  @RolesAllowed({ Constants.PFI_ADMIN })
  public List<UsuariAplicacioBean> listUsuariAplicacio(
      @WebParam(name = "usuariAplicacioFilterWs") UsuariAplicacioFilterWs usuariAplicacioFilterWs)
      throws WsI18NException, Throwable;

  @RolesAllowed({ Constants.PFI_ADMIN })
  @WebMethod
  public boolean addRolUserToUsuariAplicacio(
      @WebParam(name = "usuariAplicacioID") String usuariAplicacioID) throws WsI18NException,
      Throwable;

  @RolesAllowed({ Constants.PFI_ADMIN })
  @WebMethod
  public boolean removeRolUserToUsuariAplicacio(
      @WebParam(name = "usuariAplicacioID") String usuariAplicacioID) throws WsI18NException,
      Throwable;

  @RolesAllowed(Constants.PFI_ADMIN)
  @WebMethod
  public boolean addRolAdminToUsuariAplicacio(
      @WebParam(name = "usuariAplicacioID") String usuariAplicacioID) throws WsI18NException,
      Throwable;

  @RolesAllowed(Constants.PFI_ADMIN)
  @WebMethod
  public boolean removeRolAdminToUsuariAplicacio(
      @WebParam(name = "usuariAplicacioID") String usuariAplicacioID) throws WsI18NException,
      Throwable;

}
