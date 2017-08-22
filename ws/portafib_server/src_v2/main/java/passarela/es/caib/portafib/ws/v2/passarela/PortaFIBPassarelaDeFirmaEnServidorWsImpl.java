package es.caib.portafib.ws.v2.passarela;

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
import es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 */
@SecurityDomain(Constants.SECURITY_DOMAIN)
@Stateless(name = PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME + "v2" + "Ejb")
@RolesAllowed({ Constants.PFI_USER, Constants.PFI_ADMIN })
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = { "es.caib.portafib.ws.v2.impl.utils.PortaFIBInInterceptor" })
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = { "es.caib.portafib.ws.v2.impl.utils.PortaFIBInInterceptor" })
@WebService(name = PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME_WS, portName = PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME_WS, serviceName = PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME_WS
    + "Service", endpointInterface = "es.caib.portafib.ws.v2.impl."
    + PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME_WS)
@WebContext(contextRoot = "/portafib/ws", urlPattern = "/v2/"
    + PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME, transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false, authMethod = "WSBASIC")
public class PortaFIBPassarelaDeFirmaEnServidorWsImpl extends AbstractPortaFIBPassarelaDeFirmaWsImpl
  implements PortaFIBPassarelaDeFirmaEnServidorWs, Constants {

  public static final String NAME = "PortaFIBPassarelaDeFirmaEnServidor";

  public static final String NAME_WS = NAME + "Ws";

  @EJB(mappedName = PassarelaDeFirmaEnServidorLocal.JNDI_NAME)
  protected PassarelaDeFirmaEnServidorLocal passarelaDeFirmaEnServidorEjb;


  @Resource
  private WebServiceContext wsContext;


  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  @Override
  public PassarelaFullResults signDocuments(
      @WebParam(name = "signaturesSet") PassarelaSignaturesSet signaturesSet)
      throws WsI18NException, WsValidationException, Throwable {

    UsuariAplicacioJPA userapp = es.caib.portafib.ws.utils.UsuariAplicacioCache.get();
    

    return passarelaDeFirmaEnServidorEjb.signDocuments(signaturesSet,
        userapp.getEntitat(), userapp);
  }

}
