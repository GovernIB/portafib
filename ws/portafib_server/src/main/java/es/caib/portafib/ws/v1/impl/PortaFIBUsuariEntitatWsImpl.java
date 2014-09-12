package es.caib.portafib.ws.v1.impl;

import java.util.ArrayList;
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
import org.fundaciobit.plugins.userinformation.UserInfo;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;

import es.caib.portafib.logic.RoleUsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;

import es.caib.portafib.model.bean.UsuariEntitatBean;
import es.caib.portafib.model.bean.UsuariPersonaBean;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariPersonaFields;

import es.caib.portafib.utils.CompileConstants;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.AuthenticatedBaseWsImpl;
import es.caib.portafib.ws.utils.FitxerUtils;
import es.caib.portafib.ws.utils.JPAConversion;
import es.caib.portafib.ws.utils.UsuariAplicacioCache;

/**
 * 
 * @author anadal
 * 
 */
@SecurityDomain(Constants.SECURITY_DOMAIN)
@Stateless(name = PortaFIBUsuariEntitatWsImpl.NAME + "Ejb")
@RolesAllowed({ Constants.PFI_USER, Constants.PFI_ADMIN })
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = { "es.caib.portafib.ws.utils.PortaFIBInInterceptor" })
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = { "es.caib.portafib.ws.utils.PortaFIBInInterceptor" })
@WebService(name = PortaFIBUsuariEntitatWsImpl.NAME_WS, portName = PortaFIBUsuariEntitatWsImpl.NAME_WS, serviceName = PortaFIBUsuariEntitatWsImpl.NAME_WS
    + "Service")
@WebContext(contextRoot = "/portafib/ws", urlPattern = "/v1/"
    + PortaFIBUsuariEntitatWsImpl.NAME, transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false, authMethod = CompileConstants.AUTH_METHOD)
public class PortaFIBUsuariEntitatWsImpl extends AuthenticatedBaseWsImpl {

  public static final String NAME = "PortaFIBUsuariEntitat";

  public static final String NAME_WS = NAME + "Ws";
  
  @EJB(mappedName = RoleUsuariEntitatLogicaLocal.JNDI_NAME)
  protected RoleUsuariEntitatLogicaLocal roleUsuariEntitatLogicaEjb;

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @Resource
  private WebServiceContext wsContext;


  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ------------------------| Usuari Persona |-------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @RolesAllowed({ PFI_ADMIN})
  @WebMethod
  public UsuariPersonaBean getInfoFromPluginUserInfo(
      @WebParam(name = "administrationID") String administrationID) 
  throws WsI18NException, Throwable {

    //Llançarà errors si el nif no és correcte
    UserInfo userInfo = usuariPersonaLogicaEjb.checkAdministrationIDInUserInformationPlugin(administrationID);
    
    UsuariPersonaBean userBean= new UsuariPersonaBean();
    userBean.setEmail(userInfo.getEmail());
    userBean.setIdiomaID(userInfo.getLanguage());
    userBean.setLlinatges(userInfo.getSurname1() + (userInfo.getSurname2() == null? "" :  (" " + userInfo.getSurname2() )));
    userBean.setNif(userInfo.getAdministrationID());
    userBean.setNom(userInfo.getName());
    userBean.setUsuariPersonaID(userInfo.getUsername());
    
    return userBean;
  }

  @RolesAllowed({ PFI_ADMIN, PFI_USER})
  @WebMethod
  public String getUsuariPersonaIDByAdministrationID(
      @WebParam(name = "administrationID") String administrationID)
      throws WsValidationException, WsI18NException, Throwable {
    UsuariPersonaJPA up;
    up = usuariPersonaLogicaEjb.getUsuariPersonaIDByAdministrationID(administrationID);

    return up == null ? null :  up.getUsuariPersonaID();
  }
  
  
  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public UsuariPersonaBean getUsuariPersona(
      @WebParam(name = "usuariPersonaID") String usuariPersonaID)
      throws WsValidationException, WsI18NException, Throwable {
    UsuariPersonaJPA up = null;
    FitxerJPA.enableEncryptedFileIDGeneration();
    try {
      up = usuariPersonaLogicaEjb.findByPrimaryKey(usuariPersonaID);      
    } finally {
      FitxerJPA.disableEncryptedFileIDGeneration();
    }      
    return UsuariPersonaBean.toBean(up);
  }

  @WebMethod
  @RolesAllowed({ PFI_ADMIN })
  public void createUsuariPersona(
      @WebParam(name = "usuariPersonaBean") UsuariPersonaBean usuariPersonaBean)
      throws WsValidationException, WsI18NException, Throwable {

    Set<Long> fitxersCreats = new HashSet<Long>();
    try {
      UsuariPersonaJPA user = JPAConversion.toUsuariPersonaJPA(usuariPersonaBean, fitxerLogicaEjb, fitxersCreats);
      user = usuariPersonaLogicaEjb.createFull(user);
    } catch (Throwable e) {
      FitxerUtils.cleanPostError(fitxerLogicaEjb, fitxersCreats);
      throw e;
    }
  }

  @WebMethod
  @RolesAllowed({ PFI_ADMIN })
  public void deleteUsuariPersona(@WebParam(name = "usuariPersonaID") String usuariPersonaID)
      throws WsI18NException, Throwable {
    Set<Long> fitxers = usuariPersonaLogicaEjb.deleteFull(usuariPersonaID);
    FileSystemManager.eliminarArxius(fitxers);
  }

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ------------------------| Usuari Entitat |-------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------


  @WebMethod
  @RolesAllowed({ PFI_ADMIN})
  public UsuariEntitatBean getUsuariEntitat(
      @WebParam(name = "usuariEntitatID") String usuariEntitatID
      ) throws Throwable {
    
    if (usuariEntitatID == null) {
      return null;
    }
    UsuariEntitat ue;
    FitxerJPA.enableEncryptedFileIDGeneration();
    try {
      ue = usuariEntitatLogicaEjb.findByPrimaryKey(usuariEntitatID);
    } finally {
      FitxerJPA.disableEncryptedFileIDGeneration();
    }
    
    if (!wsContext.isUserInRole(PFI_ADMIN)) {
      // S'ha de verificar que l'entitat de l'usuari app. que fa 
      // la petició sigui igual que la de l'usuari entitat
      if (!ue.getEntitatID().equals(UsuariAplicacioCache.get().getEntitatID())) {
        // No podem mostrar informació d'usuaris-entitat d'altres entitats sense ser ADMIN
        return null;
      }
    }
    
    return UsuariEntitatBean.toBean(ue);
  }

  

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public String getUsuariEntitatIDByAdministrationID(
      @WebParam(name = "administrationID") String administrationID,
      @WebParam(name = "entitatID") String entitatID)
      throws WsI18NException, Throwable {
    UsuariEntitatJPA ue;
    FitxerJPA.enableEncryptedFileIDGeneration();
    try {
      ue = usuariEntitatLogicaEjb.findUsuariEntitatByNif(entitatID, administrationID);
    } finally {
      FitxerJPA.disableEncryptedFileIDGeneration();
    }
    
    if (ue == null) {
      // usuaripersona.noexisteix=No existeix usuari persona amb {0} {1}
      throw new I18NException("usuaripersona.noexisteix", "NIF", administrationID);      
    } else {
      return ue.getUsuariEntitatID();
    }
  }
  

  @RolesAllowed({ PFI_ADMIN, PFI_USER })
  @WebMethod
  public String getUsuariEntitatIDInMyEntitatByAdministrationID(
      @WebParam(name = "administrationID") String administrationID)
      throws WsI18NException, Throwable {
    String entitatID = UsuariAplicacioCache.get().getEntitatID();
    return getUsuariEntitatIDByAdministrationID(administrationID, entitatID);
  }


  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public UsuariEntitatBean createUsuariEntitatSimple(
      @WebParam(name = "administrationID") String administrationID,
      @WebParam(name = "entitatID") String entitatID) throws WsValidationException,
      WsI18NException, Throwable {
    
    return internalCreateCarrecOrUsuariEntitat(administrationID, entitatID, null, null);
  }

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void createCarrec(@WebParam(name = "carrec") CarrecWs carrec)
    throws WsValidationException, WsI18NException, Throwable {
    usuariEntitatLogicaEjb.createFull(CarrecWs.toUsuariEntitatJPA(carrec));
  }
  
  
  
 
  
  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public CarrecWs createCarrecSimple(
      @WebParam(name = "administrationID") String administrationID,
      @WebParam(name = "entitatID") String entitatID,
      @WebParam(name = "carrecUsername") String carrecUsername,
      @WebParam(name = "carrecName") String carrecName
    ) throws WsValidationException,
      WsI18NException, Throwable {
    return CarrecWs.toCarrecWs(internalCreateCarrecOrUsuariEntitat(
      administrationID,entitatID, carrecUsername, carrecName)); 
  }


  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public UsuariEntitatBean createUsuariEntitat(
      @WebParam(name = "usuariEntitatBean") UsuariEntitatBean usuariEntitatBean)
      throws WsValidationException, WsI18NException, Throwable {

    Set<Long> fitxersCreats = new HashSet<Long>();
    FitxerJPA.enableEncryptedFileIDGeneration();
    try {
      UsuariEntitatJPA user = JPAConversion.toUsuariEntitatJPA(usuariEntitatBean, fitxerLogicaEjb, fitxersCreats);

      if (user != null) {
        user.setCarrec(null);
        user.setUsuariEntitatID(user.getEntitatID() + "_" + user.getUsuariPersonaID());
      }
      user = usuariEntitatLogicaEjb.createFull(user);

      return UsuariEntitatBean.toBean(user);
      
    } catch (Throwable e) {
      FitxerUtils.cleanPostError(fitxerLogicaEjb, fitxersCreats);
      throw e;
    } finally {
      FitxerJPA.disableEncryptedFileIDGeneration();
    }
  }

  
  private UsuariEntitatBean internalCreateCarrecOrUsuariEntitat(
      String administrationID,String entitatID,
      String carrecUsername, String carrecName
    ) throws WsValidationException,
      WsI18NException, Throwable {

    String usuariPersonaID = usuariPersonaLogicaEjb.executeQueryOne(
        UsuariPersonaFields.USUARIPERSONAID, UsuariPersonaFields.NIF.equal(administrationID));

    if (usuariPersonaID == null) {
      throw new I18NException("usuaripersona.noexisteix", new I18NArgumentCode("nif"),
          new I18NArgumentString(administrationID));
    }
    
    boolean isCarrec = (carrecUsername != null && carrecName != null);

    UsuariEntitatJPA usuariEntitatJPA = new UsuariEntitatJPA();

    usuariEntitatJPA.setActiu(true);
    usuariEntitatJPA.setCarrec(isCarrec? carrecName : null);
    usuariEntitatJPA.setEmail(null);
    usuariEntitatJPA.setEntitatID(entitatID);
    usuariEntitatJPA.setLogoSegellID(null);
    usuariEntitatJPA.setPredeterminat(false);
    usuariEntitatJPA.setRebreTotsElsAvisos(false);
    usuariEntitatJPA.setUsuariPersonaID(usuariPersonaID);
    String usuariEntitatID = entitatID + "_" + (isCarrec? carrecUsername : usuariPersonaID);
    usuariEntitatJPA.setUsuariEntitatID(usuariEntitatID);

    usuariEntitatJPA = usuariEntitatLogicaEjb.createFull(usuariEntitatJPA);

    return new UsuariEntitatBean(usuariEntitatJPA);

  }

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void deleteUsuariEntitat(
      @WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable {
    Set<Long> fitxers = usuariEntitatLogicaEjb.deleteFull(usuariEntitatID);
    FileSystemManager.eliminarArxius(fitxers);
  }


  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void activateUsuariEntitat(@WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable {
    usuariEntitatLogicaEjb.activarUsuariEntitat(usuariEntitatID);
  }

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void deactivateUsuariEntitat(
      @WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable {
    usuariEntitatLogicaEjb.desactivarUsuariEntitat(usuariEntitatID);
  }

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void addRoleSolicitant(@WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable {
    roleUsuariEntitatLogicaEjb.createFull(new RoleUsuariEntitatJPA(Constants.ROLE_SOLI, usuariEntitatID));
  }
  
  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void removeRoleSolicitant(@WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable {
    roleUsuariEntitatLogicaEjb.deleteFull(usuariEntitatID, Constants.ROLE_SOLI);
  }
  
  
  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void addRoleAdministradorDeEntitat(@WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable {
    roleUsuariEntitatLogicaEjb.createFull(new RoleUsuariEntitatJPA(Constants.ROLE_ADEN, usuariEntitatID));
  }
  
  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void removeRoleAdministradorDeEntitat(@WebParam(name = "usuariEntitatID") String usuariEntitatID)
      throws WsI18NException, Throwable {
    roleUsuariEntitatLogicaEjb.deleteFull(usuariEntitatID, Constants.ROLE_ADEN);
  }
  
  
  
  
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ---------------------------| Carrecs |-----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  
  @RolesAllowed({ PFI_ADMIN, PFI_USER })
  @WebMethod
  public CarrecWs getCarrec(
      @WebParam(name = "carrecID") String carrecID
      ) throws Throwable {
    UsuariEntitatBean ueb = getUsuariEntitat(carrecID);
    return CarrecWs.toCarrecWs(ueb);
  }
  
  @RolesAllowed({ PFI_ADMIN, PFI_USER})
  @WebMethod
  public List<CarrecWs> getCarrecsOfMyEntitat() throws Throwable {
    return getCarrecsByEntitatID(UsuariAplicacioCache.get().getEntitatID());
  }
  
  @RolesAllowed({ PFI_ADMIN})
  @WebMethod
  public List<CarrecWs> getCarrecsByEntitatID(
      @WebParam(name = "entitatID") String entitatID
      ) throws WsI18NException, Throwable {
    
    
    Long count = entitatEjb.count(EntitatFields.ENTITATID.equal(entitatID));
    if (count == 0) {
      //error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound",
        new I18NArgumentCode(EntitatFields._TABLE_TRANSLATION),
        new I18NArgumentCode(EntitatFields.ENTITATID.fullName),
        new I18NArgumentString(String.valueOf(entitatID)));
    }
    
    Where w1 = UsuariEntitatFields.ENTITATID.equal(entitatID);
    Where w2 = UsuariEntitatFields.CARREC.isNotNull();
    List<UsuariEntitat> list;
    list = usuariEntitatLogicaEjb.select(Where.AND(w1, w2),
        new OrderBy(UsuariEntitatFields.CARREC));
    
    List<CarrecWs> carrecs = new ArrayList<CarrecWs>(list.size());
    for (UsuariEntitat usuariEntitat : list) {
      carrecs.add(CarrecWs.toCarrecWs(usuariEntitat));
    }
    return carrecs;
  }
  
  
  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void updateAdministrationIDOfCarrec(
      @WebParam(name = "carrecID") String carrecID,
      @WebParam(name = "administrationID") String administrationID
      ) throws WsValidationException, WsI18NException, Throwable {
    usuariEntitatLogicaEjb.updateCarrec(carrecID,administrationID);
  }
  
  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void activateCarrec(@WebParam(name = "carrecID") String carrecID)
      throws WsI18NException, Throwable {
    usuariEntitatLogicaEjb.activarCarrec(carrecID);
  }

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void deactivateCarrec(
      @WebParam(name = "carrecID") String carrecID)
      throws WsI18NException, Throwable {
    usuariEntitatLogicaEjb.desactivarCarrec(carrecID);
  }
  
  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public void deleteCarrec(
      @WebParam(name = "carrecID") String carrecID)
      throws WsI18NException, Throwable {
    deleteUsuariEntitat(carrecID);
  }

}
