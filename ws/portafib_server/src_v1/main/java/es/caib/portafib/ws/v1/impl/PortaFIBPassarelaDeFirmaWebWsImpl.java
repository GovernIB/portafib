package es.caib.portafib.ws.v1.impl;

import java.util.List;

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
import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.UsuariAplicacioCache;
import es.caib.portafib.ws.v1.utils.PassarelaConversion;

/**
 * 
 * @author anadal
 *
 */
@SecurityDomain(Constants.SECURITY_DOMAIN)
@Stateless(name = PortaFIBPassarelaDeFirmaWebWsImpl.NAME + "Ejb")
@RolesAllowed({ Constants.PFI_USER, Constants.PFI_ADMIN })
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = { "es.caib.portafib.ws.utils.PortaFIBInInterceptor" })
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = { "es.caib.portafib.ws.utils.PortaFIBInInterceptor" })
@WebService(name = PortaFIBPassarelaDeFirmaWebWsImpl.NAME_WS, portName = PortaFIBPassarelaDeFirmaWebWsImpl.NAME_WS, serviceName = PortaFIBPassarelaDeFirmaWebWsImpl.NAME_WS
    + "Service", endpointInterface = "es.caib.portafib.ws.v1.impl."
    + PortaFIBPassarelaDeFirmaWebWsImpl.NAME_WS)
@WebContext(contextRoot = "/portafib/ws", urlPattern = "/v1/"
    + PortaFIBPassarelaDeFirmaWebWsImpl.NAME, transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false, authMethod = "WSBASIC")
public class PortaFIBPassarelaDeFirmaWebWsImpl extends AbstractPortaFIBPassarelaDeFirmaWsImpl
    implements PortaFIBPassarelaDeFirmaWebWs, Constants {

  public static final String NAME = "PortaFIBPassarelaDeFirmaWeb";

  public static final String NAME_WS = NAME + "Ws";

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal.JNDI_NAME)
  protected es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal passarelaDeFirmaWebEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PluginLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginLocal pluginEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  @Resource
  private WebServiceContext wsContext;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ----------------------| Gestió de la Transacció |------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  @Override
  public String startTransaction(
      @WebParam(name = "signaturesSet") PassarelaSignaturesSetWs signaturesSet)
      throws WsI18NException, WsValidationException, Throwable {

    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();

    final boolean fullView = false;
    return passarelaDeFirmaWebEjb.startTransaction(
        PassarelaConversion.convert(signaturesSet), userapp.getEntitatID(), fullView, userapp);
  }

  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  @Override
  public PassarelaSignatureStatusWs getStatusTransaction(
      @WebParam(name = "signaturesSetID") String signaturesSetID) throws WsI18NException,
      Throwable {

    return PassarelaConversion.convert(passarelaDeFirmaWebEjb.getStatusTransaction(signaturesSetID));

  }

  @Override
  public List<PassarelaSignatureResultWs> getSignatureResultsOfTransaction(
      @WebParam(name = "signaturesSetID") String signaturesSetID) throws WsI18NException,
      Throwable {
    final boolean includeFiles = true;
    return PassarelaConversion.convert(passarelaDeFirmaWebEjb.getSignatureResults(signaturesSetID, includeFiles));

  }

  @Override
  public void closeTransaction(String signaturesSetID) throws WsI18NException, Throwable {
    passarelaDeFirmaWebEjb.closeTransaction(signaturesSetID);
  }

}
