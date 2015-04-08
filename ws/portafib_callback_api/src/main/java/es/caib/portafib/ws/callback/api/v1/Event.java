
package es.caib.portafib.ws.callback.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for event complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="event">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="event" type="{http://v1.server.callback.ws.portafib.caib.es/}portaFIBEvent" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "event", propOrder = {
    "event"
})
public class Event {

    protected PortaFIBEvent event;

    /**
     * Gets the value of the event property.
     * 
     * @return
     *     possible object is
     *     {@link PortaFIBEvent }
     *     
     */
    public PortaFIBEvent getEvent() {
        return event;
    }

    /**
     * Sets the value of the event property.
     * 
     * @param value
     *     allowed object is
     *     {@link PortaFIBEvent }
     *     
     */
    public void setEvent(PortaFIBEvent value) {
        this.event = value;
    }

}
