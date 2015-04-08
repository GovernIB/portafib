package es.caib.portafib.ws.callback.api.v1;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.4
 * 2015-04-08T08:45:40.096+02:00
 * Generated source version: 2.6.4
 * 
 */
@WebServiceClient(name = "PortaFIBCallBackWsService", 
                  wsdlLocation = "http://localhost:8080/portafib/cb/v1/PortaFIBCallBack?wsdl",
                  targetNamespace = "http://v1.server.callback.ws.portafib.caib.es/") 
public class PortaFIBCallBackWsService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://v1.server.callback.ws.portafib.caib.es/", "PortaFIBCallBackWsService");
    public final static QName PortaFIBCallBackWs = new QName("http://v1.server.callback.ws.portafib.caib.es/", "PortaFIBCallBackWs");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/portafib/cb/v1/PortaFIBCallBack?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PortaFIBCallBackWsService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/portafib/cb/v1/PortaFIBCallBack?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PortaFIBCallBackWsService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PortaFIBCallBackWsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PortaFIBCallBackWsService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns PortaFIBCallBackWs
     */
    @WebEndpoint(name = "PortaFIBCallBackWs")
    public PortaFIBCallBackWs getPortaFIBCallBackWs() {
        return super.getPort(PortaFIBCallBackWs, PortaFIBCallBackWs.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PortaFIBCallBackWs
     */
    @WebEndpoint(name = "PortaFIBCallBackWs")
    public PortaFIBCallBackWs getPortaFIBCallBackWs(WebServiceFeature... features) {
        return super.getPort(PortaFIBCallBackWs, PortaFIBCallBackWs.class, features);
    }

}
