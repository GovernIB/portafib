package es.caib.portafib.ws.v1.utils;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebMethod;

import org.fundaciobit.genapp.common.ws.WsI18NException;

import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.IBaseAutenticatedWs;
import es.caib.portafib.ws.v1.impl.FitxerBean;

/**
 * 
 * @author anadal
 *
 */
public interface IBaseAutenticatedV1Ws extends IBaseAutenticatedWs {


  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  public FitxerBean downloadFileUsingEncryptedFileID(String encryptedFileID)
      throws WsI18NException, Throwable;
}
