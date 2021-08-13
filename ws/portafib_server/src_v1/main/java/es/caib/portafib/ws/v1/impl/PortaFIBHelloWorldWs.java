package es.caib.portafib.ws.v1.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 
 * @author anadal
 * 
 */
@WebService
public interface PortaFIBHelloWorldWs {
  
  @WebMethod
  public String echo(@WebParam (name ="echo") String echo);
  
  
  @WebMethod
  public String getVersion();


  @WebMethod
  public int getVersionWs();

}