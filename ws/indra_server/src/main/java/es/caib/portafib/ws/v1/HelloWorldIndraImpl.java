package es.caib.portafib.ws.v1;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.jboss.ws.api.annotation.TransportGuarantee;
import org.jboss.ws.api.annotation.WebContext;
/**
 * 
 * @author anadal
 *
 */
@RunAs("PFI_USER")
@Stateless(name="HelloWorldIndra")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService
(
    name="HelloWorldIndra",
    portName = "HelloWorldIndra",
    serviceName = "HelloWorldIndraService",    
    endpointInterface = "es.caib.portafib.ws.v1.HelloWorldIndra"
)
@WebContext
(
    contextRoot="/portafib/portafirmasws/web/services",
    urlPattern="/HelloWorldIndra",
    transportGuarantee= TransportGuarantee.NONE,
    secureWSDLAccess = false    
)
public class HelloWorldIndraImpl implements HelloWorldIndra {

  @WebMethod
  public String echo(String input) {
    return "Echo value is {" + input + "} (v1)";
  }

}
