package es.caib.portafib.ws.v2.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.validation.constraints.Null;


/**
 * 
 * @author anadal
 * 
 */
@WebService
public interface PortaFIBHelloWorldWs {
  
  @WebMethod
  public String echo(@WebParam (name ="echo") @Null String echo);
  
  
  @WebMethod
  public String getVersion();


  @WebMethod
  public int getVersionWs();

}