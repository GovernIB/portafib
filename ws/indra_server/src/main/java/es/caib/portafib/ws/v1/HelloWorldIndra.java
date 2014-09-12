package es.caib.portafib.ws.v1;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 
 * @author anadal
 *
 */
@WebService
public interface HelloWorldIndra {

  @WebMethod
  public String echo(String input);

}
