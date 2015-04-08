package es.caib.portafib.ws.callback.server.v1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 
 * @author anadal
 *
 */
@WebService
public interface PortaFIBCallBackWs {
  
  
  @WebMethod
  public int getVersionWs();

  @WebMethod
  public void event(@WebParam(name = "event") PortaFIBEvent event) throws Exception;
}
