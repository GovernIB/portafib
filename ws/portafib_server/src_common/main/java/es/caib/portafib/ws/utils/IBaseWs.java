package es.caib.portafib.ws.utils;

import javax.jws.WebMethod;

/**
 * 
 * @author anadal
 *
 */
public interface IBaseWs {

    @WebMethod
    public String getVersion();

    @WebMethod
    public int getVersionWs();

}
