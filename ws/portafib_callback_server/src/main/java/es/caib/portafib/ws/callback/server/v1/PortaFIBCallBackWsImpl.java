package es.caib.portafib.ws.callback.server.v1;


import es.caib.portafib.callback.beans.v1.PortaFIBEvent;
import es.caib.portafib.callback.beans.v1.tester.PortaFIBEventStore;
import es.caib.portafib.ws.utils.VersionsWs;
import org.apache.log4j.Logger;
import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = PortaFIBCallBackWsImpl.NAME + "Ejb")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService(name = PortaFIBCallBackWsImpl.NAME_WS,
             portName = PortaFIBCallBackWsImpl.NAME_WS,
             serviceName = PortaFIBCallBackWsImpl.NAME_WS + "Service",
             targetNamespace="http://v1.server.callback.ws.portafib.caib.es/",
             endpointInterface = "es.caib.portafib.ws.callback.server.v1." + PortaFIBCallBackWsImpl.NAME_WS
             )
@WebContext(contextRoot = "/portafib/cb", urlPattern = "/v1/" + PortaFIBCallBackWsImpl.NAME, transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false)
public class PortaFIBCallBackWsImpl implements PortaFIBCallBackWs {

  public static final String NAME = "PortaFIBCallBack";

  public static final String NAME_WS = NAME + "Ws";

  private final Logger log = Logger.getLogger(getClass());

  @WebMethod
  public int getVersionWs() {
    log.info("getVersionWs()");
    return VersionsWs.VERSIO_WS_1;
  }

  @WebMethod
  public void event(@WebParam(name = "event") PortaFIBEvent event) throws CallBackException {
    log.info("Rebut event");
    PortaFIBEventStore.addEvent(event);
    log.info("Event processat");
  }

}
