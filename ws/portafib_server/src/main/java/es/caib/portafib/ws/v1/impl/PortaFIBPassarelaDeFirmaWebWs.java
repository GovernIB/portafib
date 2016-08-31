package es.caib.portafib.ws.v1.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;

import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
@WebService
public interface PortaFIBPassarelaDeFirmaWebWs extends AbstractPortaFIBPassarelaDeFirmaWs {

  //--------------------------------------------------------------------
  // -------------------------------------------------------------------
  // -------------------| Inici i estat de Transacció  |----------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public String startTransaction(
     @WebParam(name = "signaturesSet") PassarelaSignaturesSet signaturesSet) 
   throws WsI18NException, WsValidationException, Throwable;


  
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public PassarelaSignatureStatus getStatusTransaction(@WebParam(name = "signaturesSetID") String signaturesSetID) 
    throws WsI18NException, Throwable;

 
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public List<PassarelaSignatureResult> getSignatureResultsOfTransaction(@WebParam(name = "signaturesSetID") String signaturesSetID) 
    throws WsI18NException, Throwable;

  
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public void closeTransaction(@WebParam(name = "signaturesSetID") String signaturesSetID) 
    throws WsI18NException, Throwable;

  
}
