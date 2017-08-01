package es.caib.portafib.ws.v1.impl;

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
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.UsuariAplicacioCache;
import es.caib.portafib.ws.v1.utils.PassarelaConversion;

/**
 * 
 * @author anadal
 */
@SecurityDomain(Constants.SECURITY_DOMAIN)
@Stateless(name = PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME + "Ejb")
@RolesAllowed({ Constants.PFI_USER, Constants.PFI_ADMIN })
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = { "es.caib.portafib.ws.utils.PortaFIBInInterceptor" })
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = { "es.caib.portafib.ws.utils.PortaFIBInInterceptor" })
@WebService(name = PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME_WS, portName = PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME_WS, serviceName = PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME_WS
    + "Service", endpointInterface = "es.caib.portafib.ws.v1.impl."
    + PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME_WS)
@WebContext(contextRoot = "/portafib/ws", urlPattern = "/v1/"
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
  public PassarelaFullResultsWs signDocuments(
      @WebParam(name = "signaturesSet") PassarelaSignaturesSetWs signaturesSet)
      throws WsI18NException, WsValidationException, Throwable {

    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();

    es.caib.portafib.logic.passarela.api.PassarelaFullResults results;

    results = passarelaDeFirmaEnServidorEjb.signDocuments(
        PassarelaConversion.convert(signaturesSet),
        userapp.getEntitat(), userapp);
    
    return PassarelaConversion.convert(results);
  }


}
