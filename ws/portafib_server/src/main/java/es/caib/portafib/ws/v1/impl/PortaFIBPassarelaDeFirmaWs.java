package es.caib.portafib.ws.v1.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;

import es.caib.portafib.logic.passarela.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSet;
import es.caib.portafib.model.bean.CustodiaInfoBean;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.IBaseAutenticatedWs;

/**
 * 
 * @author anadal
 *
 */
@WebService
public interface PortaFIBPassarelaDeFirmaWs extends IBaseAutenticatedWs {

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| Custodia |-----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
 
  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  public int getCustodiaPolicy() throws WsI18NException, Throwable; 
  
  
  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  public CustodiaInfoBean getDefaultCustodiaInfo(
      @WebParam(name = "title") String title,
      @WebParam(name = "language") String language)
     throws WsI18NException, Throwable;


  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  public List<CustodiaInfoBean> getAllCustodiaInfoTemplates() throws WsI18NException, Throwable;
  
  
  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  public List<Long> getAllPluginIDCustodia() throws WsI18NException, Throwable;


 //--------------------------------------------------------------------
 // -------------------------------------------------------------------
 // ----------------------| Segellat de Temps |------------------------
 // -------------------------------------------------------------------
 // -------------------------------------------------------------------

  /**
   * 
   * @return SEGELLDETEMPSVIAWEB_NOUSAR=0: no es suporta segell de temps.
             SEGELLDETEMPSVIAWEB_SEMPREUSAR=1. és obligatori l'ús de TimeStamp
             SEGELLDETEMPSVIAWEB_USUARIELEGEIX_PER_DEFECTE_SI=2: es deixà a elecció de
                 l'usuari l'us de segell de temps (Per defecte si)
             SEGELLDETEMPSVIAWEB_USUARIELEGEIX_PER_DEFECTE_NO=3: es deixà a elecció de
                 l'usuari l'us de segell de temps (Per defecte no)
   * @throws WsI18NException
   * @throws Throwable
   */
 @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
 @WebMethod
 public int getTimeStampPolicy() throws WsI18NException, Throwable;

 
 @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
 @WebMethod
 public boolean providesTimeStampGenerator(String signType,  
     List<Long> filterByPluginID, List<String> filterByPluginCode)  throws WsI18NException, Throwable;
 
 
//--------------------------------------------------------------------
// -------------------------------------------------------------------
// ----------------------| Estampació CSV |------------------------
// -------------------------------------------------------------------
// -------------------------------------------------------------------

 
 @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
 @WebMethod
 public List<String> getSupportedBarCodeTypes() throws WsI18NException, Throwable;

  
 //--------------------------------------------------------------------
 // -------------------------------------------------------------------
 // -------------------| Inici i estat de Transacció  |----------------
 // -------------------------------------------------------------------
 // -------------------------------------------------------------------

 
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public String[] getSupportedSignatureTypes(
     List<Long> filterByPluginID, List<String> filterByPluginCode)
         throws WsI18NException, Throwable;

  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public String[] getSupportedSignatureAlgorithms(
     String signType, List<Long> filterByPluginID, List<String> filterByPluginCode)
         throws WsI18NException, Throwable;

  
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
