package es.caib.portafib.ws.v2.passarela;

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

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;
import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.UsuariAplicacioCache;

/**
 * 
 * @author anadal
 *
 */
@SecurityDomain(Constants.SECURITY_DOMAIN)
@Stateless(name = PortaFIBPassarelaDeFirmaWebWsImpl.NAME + "v2" + "Ejb")
@RolesAllowed({ Constants.PFI_USER, Constants.PFI_ADMIN })
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = { "es.caib.portafib.ws.v2.impl.utils.PortaFIBInInterceptor" })
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = { "es.caib.portafib.ws.v2.impl.utils.PortaFIBInInterceptor" })
@WebService(name = PortaFIBPassarelaDeFirmaWebWsImpl.NAME_WS, portName = PortaFIBPassarelaDeFirmaWebWsImpl.NAME_WS, serviceName = PortaFIBPassarelaDeFirmaWebWsImpl.NAME_WS
    + "Service", endpointInterface = "es.caib.portafib.ws.v2.passarela."
    + PortaFIBPassarelaDeFirmaWebWsImpl.NAME_WS)
@WebContext(contextRoot = "/portafib/ws", urlPattern = "/v2/"
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
  
  @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
  public ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

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
      @WebParam(name = "signaturesSet") PassarelaSignaturesSet signaturesSet)
      throws WsI18NException, WsValidationException, Throwable {

    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();
    final String applicationID = userapp.getUsuariAplicacioID(); 

    final boolean esFirmaEnServidor = false;
    
    final PerfilDeFirma perfilDeFirma;
    try {
      perfilDeFirma = configuracioUsuariAplicacioLogicaLocalEjb.
          getPerfilDeFirmaPerPassarela(applicationID, esFirmaEnServidor);
    } catch (I18NException e) {
      String msg = "Error cercant Perfil de Firma de l´usuariaplicacio = " + applicationID + " per Passarela Web"; 
      throw new Exception(msg, e);
    }

    String baseUrl = LogicUtils.getUrlBase(perfilDeFirma);

    final boolean fullView = false;
    return passarelaDeFirmaWebEjb.startTransaction(signaturesSet, userapp.getEntitatID(), fullView, userapp, baseUrl);
  }

  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  @Override
  public PassarelaSignatureStatus getStatusTransaction(
      @WebParam(name = "signaturesSetID") String signaturesSetID) throws WsI18NException,
      Throwable {

    return passarelaDeFirmaWebEjb.getStatusTransaction(signaturesSetID);

  }

  @Override
  public List<PassarelaSignatureResult> getSignatureResultsOfTransaction(
      @WebParam(name = "signaturesSetID") String signaturesSetID) throws WsI18NException,
      Throwable {
    final boolean includeFiles = true;
    return passarelaDeFirmaWebEjb.getSignatureResults(signaturesSetID, includeFiles);

  }

  @Override
  public void closeTransaction(String signaturesSetID) throws WsI18NException, Throwable {
    passarelaDeFirmaWebEjb.closeTransaction(signaturesSetID);
  }

}
