package es.caib.portafib.ws.v1.impl;

import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.UsuariAplicacioCache;
import es.caib.portafib.ws.v1.utils.AuthenticatedBaseV1WsImpl;
import es.caib.portafib.ws.v1.utils.JPAConversion;
import org.apache.cxf.interceptor.InFaultInterceptors;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;
import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Servei web per la gestió d'usuaris aplicació. Les operacoions amb roles ja no estan disponibles i llancen una
 * UnsupportedOperationException.
 * @author anadal
 * @author areus
 */
@SecurityDomain(Constants.SECURITY_DOMAIN)
@Stateless(name = PortaFIBUsuariAplicacioWsImpl.NAME + "Ejb")
@RolesAllowed({ Constants.PFI_USER, Constants.PFI_ADMIN })
@SOAPBinding(style = SOAPBinding.Style.RPC)
@InInterceptors(interceptors = {"es.caib.portafib.ws.utils.PortaFIBInInterceptor"})
@InFaultInterceptors(interceptors = {"es.caib.portafib.ws.utils.PortaFIBInInterceptor"})
@OutInterceptors(interceptors = {"es.caib.portafib.ws.utils.PortaFIBOutInterceptor"})
@OutFaultInterceptors(interceptors = {"es.caib.portafib.ws.utils.PortaFIBOutInterceptor"})
@WebService(name = PortaFIBUsuariAplicacioWsImpl.NAME_WS, 
            portName = PortaFIBUsuariAplicacioWsImpl.NAME_WS,
            serviceName = PortaFIBUsuariAplicacioWsImpl.NAME_WS + "Service",
            endpointInterface = "es.caib.portafib.ws.v1.impl." + PortaFIBUsuariAplicacioWsImpl.NAME_WS)
@WebContext(contextRoot = "/portafib/ws", urlPattern = "/v1/"
    + PortaFIBUsuariAplicacioWsImpl.NAME, transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false, authMethod = "WSBASIC")
public class PortaFIBUsuariAplicacioWsImpl extends AuthenticatedBaseV1WsImpl 
  implements PortaFIBUsuariAplicacioWs {

  public static final String NAME = "PortaFIBUsuariAplicacio";

  public static final String NAME_WS = NAME + "Ws";

  @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
  protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;
  
  @EJB(mappedName = FitxerLogicaLocal.JNDI_NAME)
  private FitxerLogicaLocal fitxerLogicaEjb;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ----------------------| Usuari Aplicacio |-----------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  
  @WebMethod
  @RolesAllowed({ PFI_ADMIN })
  public UsuariAplicacioBean getUsuariAplicacio(
      @WebParam(name = "usuariAplicacioID") String usuariAplicacioID)
      throws WsI18NException, Throwable {

    UsuariAplicacioJPA userJPA;
    FitxerJPA.enableEncryptedFileIDGeneration();
    try {
      userJPA = usuariAplicacioLogicaEjb.checkBasicUsuariAplicacioID(usuariAplicacioID);      
    } finally {
      FitxerJPA.disableEncryptedFileIDGeneration();
    }

    UsuariAplicacioBean bean = UsuariAplicacioBean.toBean(userJPA);
    bean.setContrasenya("");

    return bean;
  }

  @WebMethod
  @RolesAllowed({ PFI_ADMIN })
  public void createUsuariAplicacio(
      @WebParam(name = "usuariAplicacioBean") UsuariAplicacioBean usuariAplicacioBean)
      throws WsValidationException, WsI18NException, Throwable {

    UsuariAplicacioJPA loginUserApp = UsuariAplicacioCache.get();

    Set<Long> fitxersCreats = new HashSet<Long>();
    try {
      UsuariAplicacioJPA user = JPAConversion.toUsuariAplicacioJPA(usuariAplicacioBean, fitxerLogicaEjb,fitxersCreats);
      user = usuariAplicacioLogicaEjb.createFull(user, loginUserApp.getEntitatID());
    } catch (Throwable e) {
      fitxerLogicaEjb.cleanSet(fitxersCreats);
      throw e;
    }
  }

  @WebMethod
  @RolesAllowed({ PFI_ADMIN })
  public void deleteUsuariAplicacio(
      @WebParam(name = "usuariAplicacioID") String usuariAplicacioID) throws WsI18NException,
      Throwable {
    Set<Long> fitxers = usuariAplicacioLogicaEjb.deleteFull(usuariAplicacioID);
    FileSystemManager.eliminarArxius(fitxers);
  }
  
  
  @WebMethod
  @RolesAllowed({ PFI_ADMIN })
  public List<UsuariAplicacioBean> listUsuariAplicacio(
      @WebParam(name = "usuariAplicacioFilterWs")  UsuariAplicacioFilterWs usuariAplicacioFilterWs
      ) throws WsI18NException, Throwable {
    
    Where w = null;
    if (usuariAplicacioFilterWs != null) {
      if (usuariAplicacioFilterWs.getFilterByUsuariAplicacioID() != null) {
        w = Where.AND(w, UsuariAplicacioFields.USUARIAPLICACIOID.like(usuariAplicacioFilterWs.getFilterByUsuariAplicacioID()));
      }
      if (usuariAplicacioFilterWs.getFilterByEntitatID() != null) {
        w = Where.AND(w, UsuariAplicacioFields.ENTITATID.like(usuariAplicacioFilterWs.getFilterByEntitatID()));
      }
      if (usuariAplicacioFilterWs.getFilterByCallBackURL() != null) {
        w = Where.AND(w, UsuariAplicacioFields.CALLBACKURL.like(usuariAplicacioFilterWs.getFilterByCallBackURL()));
      }
      if (usuariAplicacioFilterWs.getFilterByActiu() != null) {
        w = Where.AND(w, UsuariAplicacioFields.ACTIU.equal(usuariAplicacioFilterWs.getFilterByActiu()));
      }
    }
    
    List<UsuariAplicacio> list = usuariAplicacioLogicaEjb.select(w);
    List<UsuariAplicacioBean> listBean = new ArrayList<UsuariAplicacioBean>();
    for (UsuariAplicacio usuariAplicacio : list) {
      UsuariAplicacioBean bean = UsuariAplicacioBean.toBean(usuariAplicacio);
      bean.setContrasenya("");
      listBean.add(bean);
    }
    return listBean;
  }
  
  

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public boolean addRolUserToUsuariAplicacio(@WebParam(name = "usuariAplicacioID") String usuariAplicacioID) throws WsI18NException, Throwable {
    throw new UnsupportedOperationException();
  }

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  public boolean removeRolUserToUsuariAplicacio(@WebParam(name = "usuariAplicacioID") String usuariAplicacioID) throws WsI18NException, Throwable {
    throw new UnsupportedOperationException();
  }

  @RolesAllowed(PFI_ADMIN)
  @WebMethod
  public boolean addRolAdminToUsuariAplicacio(@WebParam(name = "usuariAplicacioID") String usuariAplicacioID) throws WsI18NException, Throwable {
    throw new UnsupportedOperationException();
  }

  @RolesAllowed(PFI_ADMIN)
  @WebMethod
  public boolean removeRolAdminToUsuariAplicacio(@WebParam(name = "usuariAplicacioID") String usuariAplicacioID) throws WsI18NException, Throwable {
    throw new UnsupportedOperationException();
  }

}
