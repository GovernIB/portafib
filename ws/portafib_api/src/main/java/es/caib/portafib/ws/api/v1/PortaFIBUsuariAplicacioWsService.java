package es.caib.portafib.ws.api.v1;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.4
 * 2016-05-03T13:15:25.751+02:00
 * Generated source version: 2.6.4
 * 
 */
@WebServiceClient(name = "PortaFIBUsuariAplicacioWsService", 
                  wsdlLocation = "http://localhost:8080/portafib/ws/v1/PortaFIBUsuariAplicacio?wsdl",
                  targetNamespace = "http://impl.v1.ws.portafib.caib.es/") 
public class PortaFIBUsuariAplicacioWsService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://impl.v1.ws.portafib.caib.es/", "PortaFIBUsuariAplicacioWsService");
    public final static QName PortaFIBUsuariAplicacioWs = new QName("http://impl.v1.ws.portafib.caib.es/", "PortaFIBUsuariAplicacioWs");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/portafib/ws/v1/PortaFIBUsuariAplicacio?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PortaFIBUsuariAplicacioWsService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/portafib/ws/v1/PortaFIBUsuariAplicacio?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PortaFIBUsuariAplicacioWsService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PortaFIBUsuariAplicacioWsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PortaFIBUsuariAplicacioWsService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns PortaFIBUsuariAplicacioWs
     */
    @WebEndpoint(name = "PortaFIBUsuariAplicacioWs")
    public PortaFIBUsuariAplicacioWs getPortaFIBUsuariAplicacioWs() {
        return super.getPort(PortaFIBUsuariAplicacioWs, PortaFIBUsuariAplicacioWs.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PortaFIBUsuariAplicacioWs
     */
    @WebEndpoint(name = "PortaFIBUsuariAplicacioWs")
    public PortaFIBUsuariAplicacioWs getPortaFIBUsuariAplicacioWs(WebServiceFeature... features) {
        return super.getPort(PortaFIBUsuariAplicacioWs, PortaFIBUsuariAplicacioWs.class, features);
    }

}
