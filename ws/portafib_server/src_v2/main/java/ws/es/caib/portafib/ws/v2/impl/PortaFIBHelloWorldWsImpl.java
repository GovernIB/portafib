package es.caib.portafib.ws.v2.impl;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.validation.constraints.Null;

import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import es.caib.portafib.ws.v2.impl.utils.BaseWsImpl;

/**
 * 
 * @author anadal
 * 
 */
@Stateless(name= PortaFIBHelloWorldWsImpl.NAME + "v2Ejb")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService
(
    name=PortaFIBHelloWorldWsImpl.NAME_WS,
    portName = PortaFIBHelloWorldWsImpl.NAME_WS,
    serviceName = PortaFIBHelloWorldWsImpl.NAME_WS + "Service",
    endpointInterface = "es.caib.portafib.ws.v2.impl." + PortaFIBHelloWorldWsImpl.NAME_WS
)
@WebContext
(
    contextRoot="/portafib/ws",
    urlPattern="/v2/" + PortaFIBHelloWorldWsImpl.NAME,    
    
    transportGuarantee= TransportGuarantee.NONE,
    secureWSDLAccess = false
)
public class PortaFIBHelloWorldWsImpl extends BaseWsImpl {
  
  public static final String NAME = "PortaFIBHelloWorld";
  
  public static final String NAME_WS = NAME + "Ws";
  
  @WebMethod
  public String echo(@WebParam (name ="echo") @Null String echo) {
    log.info("PortaFIBHelloWorldWsImpl :: echo = " + echo);
    return echo;
  }

}
