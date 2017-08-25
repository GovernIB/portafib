package es.caib.portafib.ws.v2.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;

import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.v2.impl.beans.CarrecWs;
import es.caib.portafib.ws.v2.impl.utils.IBaseAutenticatedWs;

/**
 * 
 * @author anadal
 *
 */
@WebService
public interface PortaFIBCarrecWs extends IBaseAutenticatedWs {

  
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ---------------------------| Carrecs |-----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  
  @RolesAllowed({ Constants.PFI_ADMIN })
  @WebMethod
  public void createCarrec(@WebParam(name = "carrec") CarrecWs carrec)
    throws WsValidationException, WsI18NException, Throwable;

  @RolesAllowed({ Constants.PFI_ADMIN })
  @WebMethod
  public CarrecWs createCarrecSimple(
      @WebParam(name = "administrationID") String administrationID,
      @WebParam(name = "entitatID") String entitatID,
      @WebParam(name = "carrecUsername") String carrecUsername,
      @WebParam(name = "carrecName") String carrecName
    ) throws WsValidationException,
      WsI18NException, Throwable;
  
  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  public CarrecWs getCarrec(
      @WebParam(name = "carrecID") String carrecID
      ) throws Throwable;
  
  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER})
  @WebMethod
  public List<CarrecWs> getCarrecsOfMyEntitat() throws Throwable;
  
  @RolesAllowed({ Constants.PFI_ADMIN})
  @WebMethod
  public List<CarrecWs> getCarrecsByEntitatID(
      @WebParam(name = "entitatID") String entitatID
      ) throws WsI18NException, Throwable;
  
  @RolesAllowed({ Constants.PFI_ADMIN })
  @WebMethod
  public void updateAdministrationIDOfCarrec(
      @WebParam(name = "carrecID") String carrecID,
      @WebParam(name = "administrationID") String administrationID
      ) throws WsValidationException, WsI18NException, Throwable;
  
  @RolesAllowed({ Constants.PFI_ADMIN })
  @WebMethod
  public void activateCarrec(@WebParam(name = "carrecID") String carrecID)
      throws WsI18NException, Throwable;

  @RolesAllowed({ Constants.PFI_ADMIN })
  @WebMethod
  public void deactivateCarrec(
      @WebParam(name = "carrecID") String carrecID)
      throws WsI18NException, Throwable;
  
  @RolesAllowed({ Constants.PFI_ADMIN })
  @WebMethod
  public void deleteCarrec(
      @WebParam(name = "carrecID") String carrecID)
      throws WsI18NException, Throwable;

}
