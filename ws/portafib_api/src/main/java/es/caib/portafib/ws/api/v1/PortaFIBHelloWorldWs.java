package es.caib.portafib.ws.api.v1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.6.4
 * 2016-05-03T13:15:24.612+02:00
 * Generated source version: 2.6.4
 * 
 */
@WebService(targetNamespace = "http://impl.v1.ws.portafib.caib.es/", name = "PortaFIBHelloWorldWs")
@XmlSeeAlso({ObjectFactory.class})
public interface PortaFIBHelloWorldWs {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getVersionWs", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.GetVersionWs")
    @WebMethod
    @ResponseWrapper(localName = "getVersionWsResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.GetVersionWsResponse")
    public int getVersionWs();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "echo", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.Echo")
    @WebMethod
    @ResponseWrapper(localName = "echoResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.EchoResponse")
    public java.lang.String echo(
        @WebParam(name = "echo", targetNamespace = "")
        java.lang.String echo
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getVersion", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.GetVersion")
    @WebMethod
    @ResponseWrapper(localName = "getVersionResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.GetVersionResponse")
    public java.lang.String getVersion();
}
