package es.caib.portafib.ws.v2.impl;

import java.util.ArrayList;
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
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import es.caib.portafib.logic.RoleUsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.UsuariAplicacioCache;
import es.caib.portafib.ws.v2.impl.beans.CarrecWs;
import es.caib.portafib.ws.v2.impl.utils.AuthenticatedBaseWsImpl;

/**
 * Gestiona Càrrecs
 * @author anadal
 * 
 */
@SecurityDomain(Constants.SECURITY_DOMAIN)
@Stateless(name = PortaFIBCarrecWsImpl.NAME + "v2" + "Ejb")
@RolesAllowed({ Constants.PFI_USER, Constants.PFI_ADMIN })
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = { "es.caib.portafib.ws.v2.impl.utils.PortaFIBInInterceptor" })
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = { "es.caib.portafib.ws.v2.impl.utils.PortaFIBInInterceptor" })
@WebService(name = PortaFIBCarrecWsImpl.NAME_WS, portName = PortaFIBCarrecWsImpl.NAME_WS, serviceName = PortaFIBCarrecWsImpl.NAME_WS
    + "Service", endpointInterface = "es.caib.portafib.ws.v2.impl."
    + PortaFIBCarrecWsImpl.NAME_WS)
@WebContext(contextRoot = "/portafib/ws", urlPattern = "/v2/" + PortaFIBCarrecWsImpl.NAME, transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false, authMethod = "WSBASIC")
public class PortaFIBCarrecWsImpl extends AuthenticatedBaseWsImpl implements PortaFIBCarrecWs {

  public static final String NAME = "PortaFIBCarrec";

  public static final String NAME_WS = NAME + "Ws";

  @EJB(mappedName = RoleUsuariEntitatLogicaLocal.JNDI_NAME)
  private RoleUsuariEntitatLogicaLocal roleUsuariEntitatLogicaEjb;

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  private UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  private UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  private es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @Resource
  private WebServiceContext wsContext;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ---------------------------| Carrecs |-----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  @Override
  public void createCarrec(@WebParam(name = "carrec") CarrecWs carrec)
      throws WsValidationException, WsI18NException, Throwable {

    // Validacions
    if (carrec == null) {
      throw new I18NException("genapp.validation.required", "carrec");
    }

    String[][] values = new String[][] { 
        { "CarrecID", carrec.getCarrecID() },
        { "CarrecName", carrec.getCarrecName() },
        { "EntitatID", carrec.getEntitatID() },
        { "UsuariPersonaID", carrec.getUsuariPersonaID() } };

    for (int i = 0; i < values.length; i++) {
      if (values[i][1] == null || values[i][1].trim().length() == 0) {
        throw new I18NException("genapp.validation.required", values[i][0]);
      }
    }

    usuariEntitatLogicaEjb.createFull(CarrecWs.toUsuariEntitatJPA(carrec));
  }

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  @Override
  public CarrecWs createCarrecSimple(
      @WebParam(name = "administrationID") String administrationID,
      @WebParam(name = "entitatID") String entitatID,
      @WebParam(name = "carrecUsername") String carrecUsername,
      @WebParam(name = "carrecName") String carrecName) throws WsValidationException,
      WsI18NException, Throwable {

    // Validacions
    String[][] values = new String[][] {
        { "administrationID", administrationID },
        { "entitatID", entitatID },
        { "carrecUsername", carrecUsername },
        { "carrecName", carrecName } };

    for (int i = 0; i < values.length; i++) {
      if (values[i][1] == null || values[i][1].trim().length() == 0) {
        throw new I18NException("genapp.validation.required", values[i][0]);
      }
    }

    return CarrecWs.toCarrecWs(PortaFIBUsuariEntitatWsImpl
        .internalCreateCarrecOrUsuariEntitat(usuariPersonaLogicaEjb, usuariEntitatLogicaEjb,
            administrationID, entitatID, carrecUsername, carrecName));
  }

  @RolesAllowed({ PFI_ADMIN, PFI_USER })
  @WebMethod
  @Override
  public CarrecWs getCarrec(@WebParam(name = "carrecID") String carrecID) throws Throwable {

    if (carrecID == null) {
      return null;
    }
    UsuariEntitat ue = usuariEntitatLogicaEjb.findByPrimaryKey(carrecID);

    if (ue == null || ue.getCarrec() == null) {
      return null;
    }

    if (!wsContext.isUserInRole(PFI_ADMIN)) {
      // Si només som PFI_USER, llavors nomes podem tornar info dels carrecs
      // de la nostra entitat. Per això es verificarà que l'entitat de
      // l'usuari-app
      // sigui la mateixa que la del càrrec
      if (!ue.getEntitatID().equals(
          es.caib.portafib.ws.utils.UsuariAplicacioCache.get().getEntitatID())) {
        // No podem mostrar informació de carrecs d'altres entitats sense ser
        // ADMIN
        throw new I18NException("error.acces.carrec");
      }
    }

    return CarrecWs.toCarrecWs(ue);
  }

  @RolesAllowed({ PFI_ADMIN, PFI_USER })
  @WebMethod
  @Override
  public List<CarrecWs> getCarrecsOfMyEntitat() throws Throwable {
    return getCarrecsByEntitatID(UsuariAplicacioCache.get().getEntitatID());
  }

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  @Override
  public List<CarrecWs> getCarrecsByEntitatID(@WebParam(name = "entitatID") String entitatID)
      throws WsI18NException, Throwable {

    Long count = entitatEjb.count(EntitatFields.ENTITATID.equal(entitatID));
    if (count == 0) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound", new I18NArgumentCode(
          EntitatFields._TABLE_TRANSLATION), new I18NArgumentCode(
          EntitatFields.ENTITATID.fullName), new I18NArgumentString(String.valueOf(entitatID)));
    }

    Where w1 = UsuariEntitatFields.ENTITATID.equal(entitatID);
    Where w2 = UsuariEntitatFields.CARREC.isNotNull();
    List<UsuariEntitat> list;
    list = usuariEntitatLogicaEjb.select(Where.AND(w1, w2), new OrderBy(
        UsuariEntitatFields.CARREC));

    List<CarrecWs> carrecs = new ArrayList<CarrecWs>(list.size());
    for (UsuariEntitat usuariEntitat : list) {
      carrecs.add(CarrecWs.toCarrecWs(usuariEntitat));
    }
    return carrecs;
  }

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  @Override
  public void updateAdministrationIDOfCarrec(@WebParam(name = "carrecID") String carrecID,
      @WebParam(name = "administrationID") String administrationID)
      throws WsValidationException, WsI18NException, Throwable {
    usuariEntitatLogicaEjb.updateCarrec(carrecID, administrationID);
  }

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  @Override
  public void activateCarrec(@WebParam(name = "carrecID") String carrecID)
      throws WsI18NException, Throwable {
    usuariEntitatLogicaEjb.activarCarrec(carrecID);
  }

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  @Override
  public void deactivateCarrec(@WebParam(name = "carrecID") String carrecID)
      throws WsI18NException, Throwable {
    usuariEntitatLogicaEjb.desactivarCarrec(carrecID);
  }

  @RolesAllowed({ PFI_ADMIN })
  @WebMethod
  @Override
  public void deleteCarrec(@WebParam(name = "carrecID") String carrecID)
      throws WsI18NException, Throwable {
    Set<Long> fitxers = usuariEntitatLogicaEjb.deleteFull(carrecID);
    FileSystemManager.eliminarArxius(fitxers);
  }

}
