package es.caib.portafib.ws.api.v1;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.6
 * 2023-02-15T14:03:28.481+01:00
 * Generated source version: 3.2.6
 *
 */
@WebServiceClient(name = "PortaFIBUsuariEntitatWsService",
                  wsdlLocation = "http://localhost:8080/portafib/ws/v1/PortaFIBUsuariEntitat?wsdl",
                  targetNamespace = "http://impl.v1.ws.portafib.caib.es/")
public class PortaFIBUsuariEntitatWsService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://impl.v1.ws.portafib.caib.es/", "PortaFIBUsuariEntitatWsService");
    public final static QName PortaFIBUsuariEntitatWsPort = new QName("http://impl.v1.ws.portafib.caib.es/", "PortaFIBUsuariEntitatWsPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/portafib/ws/v1/PortaFIBUsuariEntitat?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PortaFIBUsuariEntitatWsService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/portafib/ws/v1/PortaFIBUsuariEntitat?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PortaFIBUsuariEntitatWsService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PortaFIBUsuariEntitatWsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PortaFIBUsuariEntitatWsService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public PortaFIBUsuariEntitatWsService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public PortaFIBUsuariEntitatWsService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public PortaFIBUsuariEntitatWsService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns PortaFIBUsuariEntitatWs
     */
    @WebEndpoint(name = "PortaFIBUsuariEntitatWsPort")
    public PortaFIBUsuariEntitatWs getPortaFIBUsuariEntitatWsPort() {
        return super.getPort(PortaFIBUsuariEntitatWsPort, PortaFIBUsuariEntitatWs.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PortaFIBUsuariEntitatWs
     */
    @WebEndpoint(name = "PortaFIBUsuariEntitatWsPort")
    public PortaFIBUsuariEntitatWs getPortaFIBUsuariEntitatWsPort(WebServiceFeature... features) {
        return super.getPort(PortaFIBUsuariEntitatWsPort, PortaFIBUsuariEntitatWs.class, features);
    }

}
