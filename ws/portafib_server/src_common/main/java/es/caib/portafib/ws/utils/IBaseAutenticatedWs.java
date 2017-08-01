package es.caib.portafib.ws.utils;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebMethod;

import org.fundaciobit.genapp.common.ws.WsI18NException;

import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
public interface IBaseAutenticatedWs extends IBaseWs {

  @RolesAllowed({ Constants.PFI_USER, Constants.PFI_ADMIN })
  @WebMethod
  public List<String> getSupportedLanguages() throws WsI18NException, Throwable;
  
  
  @RolesAllowed({ Constants.PFI_USER, Constants.PFI_ADMIN })
  @WebMethod
  public String getEntitatID();


}
