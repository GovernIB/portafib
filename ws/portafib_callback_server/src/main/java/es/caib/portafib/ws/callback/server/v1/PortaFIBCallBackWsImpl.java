package es.caib.portafib.ws.callback.server.v1;


import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.VersionsWs;

/**
 * 
 * @author anadal
 * 
 */
@Stateless(name = PortaFIBCallBackWsImpl.NAME + "Ejb")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService(name = PortaFIBCallBackWsImpl.NAME_WS, portName = PortaFIBCallBackWsImpl.NAME_WS, serviceName = PortaFIBCallBackWsImpl.NAME_WS
    + "Service")
@WebContext(contextRoot = "/portafib/cb", urlPattern = "/v1/" + PortaFIBCallBackWsImpl.NAME, transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false)
public class PortaFIBCallBackWsImpl implements Constants {

  public static final String NAME = "PortaFIBCallBack";

  public static final String NAME_WS = NAME + "Ws";

  @WebMethod
  public int getVersionWs() {
    return VersionsWs.VERSIO_WS_1;
  }

  @WebMethod
  public void event(@WebParam(name = "event") PortaFIBEvent event) throws Exception {
    PortaFIBEventStore.llistat.add(event);
  }

}
