package es.caib.portafib.ws.v1.impl;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import es.caib.portafib.utils.Constants;

import org.jboss.ws.api.annotation.TransportGuarantee;
import org.jboss.ws.api.annotation.WebContext;

import es.caib.portafib.ws.v1.utils.BaseV1WsImpl;

/**
 * 
 * @author anadal
 * 
 */
@Stateless(name= PortaFIBHelloWorldWsImpl.NAME + "Ejb")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService
(
    name=PortaFIBHelloWorldWsImpl.NAME_WS,
    portName = PortaFIBHelloWorldWsImpl.NAME_WS,
    serviceName = PortaFIBHelloWorldWsImpl.NAME_WS + "Service",
    endpointInterface = "es.caib.portafib.ws.v1.impl." + PortaFIBHelloWorldWsImpl.NAME_WS
)
@WebContext
(
    contextRoot="/portafib/ws",
    urlPattern="/v1/" + PortaFIBHelloWorldWsImpl.NAME,    
    transportGuarantee= TransportGuarantee.NONE,
    secureWSDLAccess = false
)
public class PortaFIBHelloWorldWsImpl extends BaseV1WsImpl {
  
  public static final String NAME = "PortaFIBHelloWorld";
  
  public static final String NAME_WS = NAME + "Ws";
  
  @WebMethod
  public String echo(@WebParam (name ="echo") String echo) {
    log.info("PortaFIBHelloWorldWsImpl :: echo = " + echo);
    return echo;
  }

}
