package es.caib.portafib.ws.v2.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.jpa.TipusDocumentJPA;
import es.caib.portafib.jpa.TraduccioMapJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.model.bean.CustodiaInfoBean;
import es.caib.portafib.model.bean.FirmaBean;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.FitxerUtilsCommon;
import es.caib.portafib.ws.utils.UsuariAplicacioCache;
import es.caib.portafib.ws.v2.impl.beans.BlocDeFirmesWs;
import es.caib.portafib.ws.v2.impl.beans.FluxDeFirmesWs;
import es.caib.portafib.ws.v2.impl.beans.PeticioDeFirmaWs;
import es.caib.portafib.ws.v2.impl.beans.TipusDocumentInfoWs;
import es.caib.portafib.ws.v2.impl.utils.AuthenticatedBaseWsImpl;


/**
 * 
 * @author anadal
 * 
 */
@SecurityDomain(Constants.SECURITY_DOMAIN)
@Stateless(name = PortaFIBPeticioDeFirmaWsImpl.NAME + "v2" + "Ejb")
@RolesAllowed({ Constants.PFI_USER, Constants.PFI_ADMIN })
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = { "es.caib.portafib.ws.v2.impl.utils.PortaFIBInInterceptor" })
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = { "es.caib.portafib.ws.v2.impl.utils.PortaFIBInInterceptor" })
@WebService(name = PortaFIBPeticioDeFirmaWsImpl.NAME_WS,
    portName = PortaFIBPeticioDeFirmaWsImpl.NAME_WS,
    serviceName = PortaFIBPeticioDeFirmaWsImpl.NAME_WS + "Service",
    endpointInterface = "es.caib.portafib.ws.v2.impl." + PortaFIBPeticioDeFirmaWsImpl.NAME_WS)
@WebContext(contextRoot = "/portafib/ws", urlPattern = "/v2/"
    + PortaFIBPeticioDeFirmaWsImpl.NAME, transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false, authMethod = "WSBASIC")
public class PortaFIBPeticioDeFirmaWsImpl extends AuthenticatedBaseWsImpl implements PortaFIBPeticioDeFirmaWs {
  
  public static final String NAME = "PortaFIBPeticioDeFirma";

  public static final String NAME_WS = NAME + "Ws";

  @EJB(mappedName = "portafib/FitxerLogicaEJB/local")
  private FitxerLogicaLocal fitxerLogicaEjb;

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;
  
  @EJB(mappedName = "portafib/FluxDeFirmesLogicaEJB/local")
  private FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;

  @Resource
  private WebServiceContext wsContext;

  
  
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| Custodia |---------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  
  
  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public CustodiaInfoBean getDefaultCustodiaInfo(
      @WebParam(name = "title") String title,
      @WebParam(name = "language") String language)
     throws WsI18NException, Throwable {
    
    
    if (!LogicUtils.checkPotCustodiar(UsuariAplicacioCache.get())) {
      return null;
    }

    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();
    final String usuariEntitatID = null;
    if (language.trim().length() == 0) {
      language = userapp.getIdiomaID();
    }
    final String usuariAplicacioID = userapp.getUsuariAplicacioID();
    return peticioDeFirmaLogicaEjb.constructDefaultCustodiaInfo(title,
        userapp.getEntitatID(), usuariEntitatID, usuariAplicacioID, language);

  }

  
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| Plantilles |---------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  
  /**
   * @return retornarà null si la plantilla no existeix, no es propietat de l'usuari
   *  aplicació que fa la cridada o no esta compartida.  
   */
  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public FluxDeFirmesWs instantiatePlantillaFluxDeFirmes(
      @WebParam(name = "plantillaDeFluxDeFirmesID") long plantillaDeFluxDeFirmesID) 
    throws WsI18NException, Throwable {
    
    FluxDeFirmesJPA flux = fluxDeFirmesLogicaEjb.findByPrimaryKeyFullForPlantilla(plantillaDeFluxDeFirmesID);
    if (flux == null || flux.getPlantillaFluxDeFirmes() == null) {
      return null;
    }
    // Check que la plantilla és de l'usuari que crida o esta compartida
    // per algun usuari-entitat o usuari-aplicacio de la pròpia entitat
    PlantillaFluxDeFirmesJPA plantilla = flux.getPlantillaFluxDeFirmes(); 
    if (!plantilla.getCompartir()) {
      String userapp = wsContext.getUserPrincipal().getName();
      if (!userapp.equals(plantilla.getUsuariAplicacioID())) {
        // TODO Traduir i llançar una excepció
        String msg = "L'usuari app connectat " + userapp + 
        " no té permis sobre la plantilla amd ID " + plantillaDeFluxDeFirmesID; 
        log.error(msg);
     // Error desconegut: {0}
        throw new I18NException("error.unknown", msg);
      }
    }

    FluxDeFirmesWs fluxWs = FluxDeFirmesWs.toWs(flux);
    
    // Eliminar identificadors
    fluxWs.setFluxDeFirmesID(0);
    for (BlocDeFirmesWs blocDeFirmesWs : fluxWs.getBlocsDeFirmes()) {
      blocDeFirmesWs.setBlocDeFirmesID(0);
      blocDeFirmesWs.setFluxDeFirmesID(0);
      
      for(FirmaBean firma : blocDeFirmesWs.getFirmes()) {;
        firma.setFirmaID(0);
        firma.setBlocDeFirmaID(0);
      }      
    }

    return fluxWs;
  }
  
  
  /**
   * @return retornarà null si la plantilla no existeix, no es propietat de l'usuari
   *  aplicació que fa la cridada o no esta compartida.  
   */
  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  @Override
  public void deletePlantillaFluxDeFirmes(
      @WebParam(name = "plantillaDeFluxDeFirmesID") long plantillaDeFluxDeFirmesID) 
    throws WsI18NException, Throwable {
    
    
    FluxDeFirmesJPA flux = fluxDeFirmesLogicaEjb.findByPrimaryKeyFullForPlantilla(plantillaDeFluxDeFirmesID);
    if (flux == null || flux.getPlantillaFluxDeFirmes() == null) {
      return;
    }
    // Check que el propietari de la plantilla és de l'usuari app que crida
    PlantillaFluxDeFirmesJPA plantilla = flux.getPlantillaFluxDeFirmes(); 
    {
      String userapp = wsContext.getUserPrincipal().getName();
      if (!userapp.equals(plantilla.getUsuariAplicacioID())) {
        // TODO Traduir i llançar una excepció
        String msg = "L'usuari app connectat " + userapp + 
        " no és propietari de la plantilla ('"
        + plantilla.getUsuariAplicacioID() + "')";
        log.error(msg);
        // Error desconegut: {0}
        throw new I18NException("error.unknown", msg);
      }
    }

    fluxDeFirmesLogicaEjb.deleteFull(plantillaDeFluxDeFirmesID);
  
  }
  
  
  
  /**
   * 
   * @param fluxDeFirmesWs
   * @param compartir
   * @return
   * @throws WsValidationException
   * @throws WsI18NException
   * @throws Throwable
   */
  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public Long createPlantillaFluxDeFirmes(
      @WebParam(name = "fluxDeFirmesWs") FluxDeFirmesWs fluxDeFirmesWs,
      @WebParam(name = "compartir") boolean compartir)
    throws WsValidationException,WsI18NException, Throwable {
    
    if (fluxDeFirmesWs == null) {
      return null;
    }
    
    PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes = new PlantillaFluxDeFirmesJPA();

    plantillaFluxDeFirmes.setDescripcio(fluxDeFirmesWs.getNom());
    plantillaFluxDeFirmes.setUsuariEntitatID(null);
    String userapp = wsContext.getUserPrincipal().getName();
    plantillaFluxDeFirmes.setUsuariAplicacioID(userapp);
    plantillaFluxDeFirmes.setCompartir(compartir);

    
    Set<Long> fitxersCreats = new HashSet<Long>();
    try {
      FluxDeFirmesJPA fluxJPA = FluxDeFirmesWs.toJPA(
        fluxDeFirmesWs, fitxerLogicaEjb, fitxersCreats);

      fluxJPA.setPlantillaFluxDeFirmes(plantillaFluxDeFirmes);
    
      fluxJPA = fluxDeFirmesLogicaEjb.createFull(fluxJPA);
      
      return fluxJPA.getFluxDeFirmesID();
    
    } catch (Throwable e) {
      FitxerUtilsCommon.cleanPostError(fitxerLogicaEjb, fitxersCreats);
      throw e;
    } 
  }

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // -----------------------| Tipus de Document |-----------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  
  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public List<TipusDocumentInfoWs> getTipusDeDocuments(
      @WebParam(name = "idioma")  String idioma)
      throws WsI18NException, Throwable {
 
    UsuariAplicacioJPA ua = UsuariAplicacioCache.get();
    
    String userapp = ua.getUsuariAplicacioID();
    
    Where whereTD = Where.OR(
        TipusDocumentFields.USUARIAPLICACIOID.equal(userapp),
        TipusDocumentFields.USUARIAPLICACIOID.isNull()
    );
    
    List<TipusDocument> list = tipusDocumentEjb.select(whereTD);
    
    List<TipusDocumentInfoWs> tipus = new ArrayList<TipusDocumentInfoWs>();

    if (idioma == null || idioma.trim().length() != 2) {
      idioma = ua.getIdiomaID();
    } else {
      long count = idiomaEjb.count(Where.AND(IdiomaFields.IDIOMAID.equal(idioma),
          IdiomaFields.SUPORTAT.equal(true)));
      if (count == 0) {
        idioma = ua.getIdiomaID();
      }
    }

    for (TipusDocument td : list) {
      
      TraduccioMapJPA tramap;
      tramap = ((TipusDocumentJPA)td).getNom().getTraduccio(ua.getIdiomaID());
      if (tramap == null) {
        tramap = ((TipusDocumentJPA)td).getNom().getTraduccio(Configuracio.getDefaultLanguage());
      }

      long id = td.getTipusDocumentID();
      String nom = tramap.getValor();
      
      // XYZ ZZZ PORTAFIB v2:  Falta el pare del document NTI
      Long tipusDocumentNTIID = (( id >= 0) && (id <= 99))?null:99L; // ALTRES

      tipus.add( new TipusDocumentInfoWs(id, nom, tipusDocumentNTIID));
    }

    Collections.sort(tipus);

    return tipus;

  }
  
  

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // -----------------------| Peticio de Firma |------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public PeticioDeFirmaWs createPeticioDeFirma(
      @WebParam(name = "peticioDeFirmaWs") PeticioDeFirmaWs peticioDeFirmaWs)
      throws WsI18NException, WsValidationException, Throwable {

    Set<Long> fitxersCreats = new HashSet<Long>();

    FitxerJPA.enableEncryptedFileIDGeneration();
    try {
      Fitxer fileToConvertInfo = peticioDeFirmaWs.getFitxerAFirmar();
      
      log.info(" XYZ ZZZ  fileToConvertInfo = " + fileToConvertInfo );
      
      if (fileToConvertInfo == null) {
        throw new I18NException("genapp.validation.required",
            PeticioDeFirmaFields.FITXERAFIRMARID.fullName);
      }
      
      PeticioDeFirmaJPA peticioDeFirmaJPA = PeticioDeFirmaWs.toJPA(peticioDeFirmaWs,
          fitxerLogicaEjb, fitxersCreats);
      
      // Convertir Fitxers
      Long fitxerAFirmarID = peticioDeFirmaJPA.getFitxerAFirmarID();
      {
  

          File fileToConvert =  FileSystemManager.getFile(fitxerAFirmarID);
          Fitxer fitxerConvertit = PdfUtils.convertToPDF(fileToConvert, fileToConvertInfo);
  
          if (fitxerConvertit == fileToConvertInfo) {
            // Es un PDF.
            // No feim res
          } else {
            // No és un PDF, ho substituim pel fitxer convertit
  
            // Actualitzam el Fitxer a firmar
            InputStream is = fitxerConvertit.getData().getInputStream();            
            FileOutputStream fos = new FileOutputStream(fileToConvert);
            try {
              FileSystemManager.copy(is,fos);
            } finally {
              try { is.close(); } catch(Throwable th) {}
            }
            fos.flush();
            fos.close();
            // Canviar BBDD
            Fitxer f = fitxerLogicaEjb.findByPrimaryKey(fitxerAFirmarID);
            f.setNom(f.getNom()+ ".pdf");
            f.setMime(Constants.PDF_MIME_TYPE);
            f.setTamany(fitxerConvertit.getTamany());
            
            fitxerLogicaEjb.update(f);
          }
        
      }
      // Final Convertir Fitxer
      

      String userapp = wsContext.getUserPrincipal().getName();
      peticioDeFirmaJPA.setUsuariAplicacioID(userapp);

      peticioDeFirmaJPA = peticioDeFirmaLogicaEjb.createFull(peticioDeFirmaJPA);

      System.gc();

      return PeticioDeFirmaWs.toWs(peticioDeFirmaJPA);

    } catch (Throwable e) {
      FitxerUtilsCommon.cleanPostError(fitxerLogicaEjb, fitxersCreats);
      throw e;
    } finally {
      FitxerJPA.disableEncryptedFileIDGeneration();
    }
  }

  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public PeticioDeFirmaWs createAndStartPeticioDeFirma(
      @WebParam(name = "peticioDeFirmaWs") PeticioDeFirmaWs peticioDeFirmaWs)
      throws WsI18NException, WsValidationException, Throwable {

    PeticioDeFirmaWs peticioCreada;
    peticioCreada = createPeticioDeFirma(peticioDeFirmaWs);

    try {
      peticioDeFirmaLogicaEjb.start(peticioCreada.getPeticioDeFirmaID());
    } catch (Throwable th) {
      deletePeticioDeFirma(peticioCreada.getPeticioDeFirmaID());
      throw th;
    }

    return peticioCreada;
  }

  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public void startPeticioDeFirma(@WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID)
      throws WsI18NException, Throwable {

    // Check propietari
    checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID);

    peticioDeFirmaLogicaEjb.start(peticioDeFirmaID);
  }

  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public Integer getStateOfPeticioDeFirma(
      @WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID) throws WsI18NException,
      Throwable {

    // Check propietari
    checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID);

    Integer estat = peticioDeFirmaLogicaEjb.executeQueryOne(
        PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID,
        PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));
    return estat;
  }

  /**
   * @param peticioDeFirmaID
   * @return El darrer fitxer firmat si la petició esta en marxa i algu ha
   *         firmat, els fitxer adaptat si la petició esta en marxa i ningú ha
   *         firmat o el fitxer original si la peticio no s'ha iniciat.
   * @throws WsI18NException
   * @throws Throwable
   */
  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public FitxerBean getLastSignedFileOfPeticioDeFirma(
      @WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID) throws WsI18NException,
      Throwable {

    // Check propietari
    checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID);

    FitxerJPA.enableEncryptedFileIDGeneration();
    try {
      FitxerJPA fitxerJPA = peticioDeFirmaLogicaEjb
          .getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);
      FitxerBean fb = FitxerBean.toBean(fitxerJPA);
      
      System.gc();
      
      return fb;
    } finally {
      FitxerJPA.disableEncryptedFileIDGeneration();
    }
  }

  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public void deletePeticioDeFirma(@WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID)
      throws WsI18NException, Throwable {
    String userapp = wsContext.getUserPrincipal().getName();
    Set<Long> fitxers = peticioDeFirmaLogicaEjb.deleteFullUsingUsuariAplicacio(
        peticioDeFirmaID, userapp);
    FileSystemManager.eliminarArxius(fitxers);
  }

  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public void pausePeticioDeFirma(@WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID)
      throws WsI18NException, Throwable {
    // Check propietari
    checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID);
    peticioDeFirmaLogicaEjb.pause(peticioDeFirmaID);
  }

  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public PeticioDeFirmaWs resetPeticioDeFirma(
      @WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID)
     throws WsI18NException, Throwable {

    // Check propietari
    checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID);

    FitxerJPA.enableEncryptedFileIDGeneration();
    try {
      PeticioDeFirmaJPA peticioDeFirmaJPA;
      peticioDeFirmaJPA = peticioDeFirmaLogicaEjb.resetPeticioDeFirma(peticioDeFirmaID);
      return PeticioDeFirmaWs.toWs(peticioDeFirmaJPA);
    } finally {
      FitxerJPA.disableEncryptedFileIDGeneration();
    }
  }

  @RolesAllowed({ PFI_ADMIN ,PFI_USER })
  @WebMethod
  public PeticioDeFirmaWs getPeticioDeFirma(
    @WebParam(name = "peticioDeFirmaID") long peticioDeFirmaID)
      throws WsI18NException, Throwable {

    PeticioDeFirmaJPA peticio = peticioDeFirmaLogicaEjb.findByPrimaryKeyFull(peticioDeFirmaID);
    
    if (peticio == null) {
      return null;
    }

    checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID);
    
    return PeticioDeFirmaWs.toWs(peticio);

  }


  /** Check propietari de la peticio de firma
   * 
   * @param peticioDeFirmaID
   * @throws I18NException
   */
  private void checkIfPeticioDeFirmaIsPropertyOfUsrApp(long peticioDeFirmaID)
      throws I18NException {
    
    if (!wsContext.isUserInRole(PFI_ADMIN)) {
      String usrappPropietari = peticioDeFirmaLogicaEjb.executeQueryOne(
          PeticioDeFirmaFields.USUARIAPLICACIOID,
          PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));
  
      String userapp = wsContext.getUserPrincipal().getName();
      if (!userapp.equals(usrappPropietari)) {
        throw new I18NException("peticiodefirma.error.nopropietari", userapp,
            String.valueOf(peticioDeFirmaID));
      }
    }
  }

}
