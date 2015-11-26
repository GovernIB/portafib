
package es.caib.portafib.ws.callback.api.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.caib.portafib.ws.callback.api.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetVersionWs_QNAME = new QName("http://v1.server.callback.ws.portafib.caib.es/", "getVersionWs");
    private final static QName _EventResponse_QNAME = new QName("http://v1.server.callback.ws.portafib.caib.es/", "eventResponse");
    private final static QName _Event_QNAME = new QName("http://v1.server.callback.ws.portafib.caib.es/", "event");
    private final static QName _GetVersionWsResponse_QNAME = new QName("http://v1.server.callback.ws.portafib.caib.es/", "getVersionWsResponse");
    private final static QName _CallBackFault_QNAME = new QName("http://v1.server.callback.ws.portafib.caib.es/", "CallBackFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.caib.portafib.ws.callback.api.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Sign }
     * 
     */
    public Sign createSign() {
        return new Sign();
    }

    /**
     * Create an instance of {@link SigningRequest }
     * 
     */
    public SigningRequest createSigningRequest() {
        return new SigningRequest();
    }

    /**
     * Create an instance of {@link CallBackFault }
     * 
     */
    public CallBackFault createCallBackFault() {
        return new CallBackFault();
    }

    /**
     * Create an instance of {@link GetVersionWsResponse }
     * 
     */
    public GetVersionWsResponse createGetVersionWsResponse() {
        return new GetVersionWsResponse();
    }

    /**
     * Create an instance of {@link EventResponse }
     * 
     */
    public EventResponse createEventResponse() {
        return new EventResponse();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link Actor }
     * 
     */
    public Actor createActor() {
        return new Actor();
    }

    /**
     * Create an instance of {@link GetVersionWs }
     * 
     */
    public GetVersionWs createGetVersionWs() {
        return new GetVersionWs();
    }

    /**
     * Create an instance of {@link PortaFIBEvent }
     * 
     */
    public PortaFIBEvent createPortaFIBEvent() {
        return new PortaFIBEvent();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVersionWs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://v1.server.callback.ws.portafib.caib.es/", name = "getVersionWs")
    public JAXBElement<GetVersionWs> createGetVersionWs(GetVersionWs value) {
        return new JAXBElement<GetVersionWs>(_GetVersionWs_QNAME, GetVersionWs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://v1.server.callback.ws.portafib.caib.es/", name = "eventResponse")
    public JAXBElement<EventResponse> createEventResponse(EventResponse value) {
        return new JAXBElement<EventResponse>(_EventResponse_QNAME, EventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Event }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://v1.server.callback.ws.portafib.caib.es/", name = "event")
    public JAXBElement<Event> createEvent(Event value) {
        return new JAXBElement<Event>(_Event_QNAME, Event.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVersionWsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://v1.server.callback.ws.portafib.caib.es/", name = "getVersionWsResponse")
    public JAXBElement<GetVersionWsResponse> createGetVersionWsResponse(GetVersionWsResponse value) {
        return new JAXBElement<GetVersionWsResponse>(_GetVersionWsResponse_QNAME, GetVersionWsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CallBackFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://v1.server.callback.ws.portafib.caib.es/", name = "CallBackFault")
    public JAXBElement<CallBackFault> createCallBackFault(CallBackFault value) {
        return new JAXBElement<CallBackFault>(_CallBackFault_QNAME, CallBackFault.class, null, value);
    }

}
