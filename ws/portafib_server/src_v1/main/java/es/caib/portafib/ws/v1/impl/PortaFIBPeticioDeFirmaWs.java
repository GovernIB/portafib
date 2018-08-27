package es.caib.portafib.ws.v1.impl;

import java.util.List;



import javax.annotation.security.RolesAllowed;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;

import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.v1.utils.IBaseAutenticatedV1Ws;

/**
 * 
 * @author anadal
 *
 */
@WebService
public interface PortaFIBPeticioDeFirmaWs extends IBaseAutenticatedV1Ws {


  
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| Custodia |---------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
 
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public CustodiaInfoBean getDefaultCustodiaInfo(
      @WebParam(name = "title") String title,
      @WebParam(name = "language") String language)
     throws WsI18NException, Throwable;

  
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| Plantilles |---------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  
  /**
   * @return retornarà null si la plantilla no existeix, no es propietat de l'usuari
   *  aplicació que fa la cridada o no esta compartida.  
   */
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public FluxDeFirmesWs instantiatePlantillaFluxDeFirmes(
      @WebParam(name = "plantillaDeFluxDeFirmesID") long plantillaDeFluxDeFirmesID) 
    throws WsI18NException, Throwable;
  
  /**
   * 
   * @param fluxDeFirmesWs
   * @param compartir
   * @return
   * @throws WsValidationException
   * @throws WsI18NException
   * @throws Throwable
   */
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public Long createPlantillaFluxDeFirmes(
      @WebParam(name = "fluxDeFirmesWs") FluxDeFirmesWs fluxDeFirmesWs,
      @WebParam(name = "compartir") boolean compartir)
    throws WsValidationException,WsI18NException, Throwable;
  
  /**
   * 
   * @param plantillaDeFluxDeFirmesID
   * @throws WsI18NException
   * @throws Throwable
   */
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public void deletePlantillaFluxDeFirmes(
      @WebParam(name = "plantillaDeFluxDeFirmesID") long plantillaDeFluxDeFirmesID) 
    throws WsI18NException, Throwable;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // -----------------------| Tipus de Document |-----------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public List<TipusDocumentInfoWs> getTipusDeDocuments(
      @WebParam(name = "idioma")  String idioma)
      throws WsI18NException, Throwable;
  
  

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // -----------------------| Peticio de Firma |------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public PeticioDeFirmaWs createPeticioDeFirma(
      @WebParam(name = "peticioDeFirmaWs") PeticioDeFirmaWs peticioDeFirmaWs)
      throws WsI18NException, WsValidationException, Throwable;

  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public PeticioDeFirmaWs createAndStartPeticioDeFirma(
      @WebParam(name = "peticioDeFirmaWs") PeticioDeFirmaWs peticioDeFirmaWs)
      throws WsI18NException, WsValidationException, Throwable;

  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public void startPeticioDeFirma(@WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID)
      throws WsI18NException, Throwable;
  
  
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public Integer getStateOfPeticioDeFirma(
      @WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID) throws WsI18NException,
      Throwable;

  /**
   * @param peticioDeFirmaID
   * @return El darrer fitxer firmat si la petició esta en marxa i algu ha
   *         firmat, els fitxer adaptat si la petició esta en marxa i ningú ha
   *         firmat o el fitxer original si la peticio no s'ha iniciat.
   * @throws WsI18NException
   * @throws Throwable
   */
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public FitxerBean getLastSignedFileOfPeticioDeFirma(
      @WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID) throws WsI18NException,
      Throwable;

  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public void deletePeticioDeFirma(@WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID)
      throws WsI18NException, Throwable;

  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public void pausePeticioDeFirma(@WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID)
      throws WsI18NException, Throwable;

  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public PeticioDeFirmaWs resetPeticioDeFirma(
      @WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID)
     throws WsI18NException, Throwable;

  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public PeticioDeFirmaWs getPeticioDeFirma(
    @WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID)
      throws WsI18NException, Throwable;

  
}