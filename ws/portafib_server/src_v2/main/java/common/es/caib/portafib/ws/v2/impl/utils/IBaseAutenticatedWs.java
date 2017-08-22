package es.caib.portafib.ws.v2.impl.utils;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebMethod;

import org.fundaciobit.genapp.common.ws.WsI18NException;

import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.IBaseWs;

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

  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  public FitxerBean downloadFileUsingEncryptedFileID(String encryptedFileID)
      throws WsI18NException, Throwable;
}
