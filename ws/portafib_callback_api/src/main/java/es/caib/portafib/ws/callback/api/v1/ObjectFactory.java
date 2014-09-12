
package es.caib.portafib.ws.callback.api.v1;

import javax.xml.bind.annotation.XmlRegistry;


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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.caib.portafib.ws.callback.api.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SigningRequest }
     * 
     */
    public SigningRequest createSigningRequest() {
        return new SigningRequest();
    }

    /**
     * Create an instance of {@link PortaFIBEvent }
     * 
     */
    public PortaFIBEvent createPortaFIBEvent() {
        return new PortaFIBEvent();
    }

    /**
     * Create an instance of {@link Sign }
     * 
     */
    public Sign createSign() {
        return new Sign();
    }

    /**
     * Create an instance of {@link Actor }
     * 
     */
    public Actor createActor() {
        return new Actor();
    }

}
