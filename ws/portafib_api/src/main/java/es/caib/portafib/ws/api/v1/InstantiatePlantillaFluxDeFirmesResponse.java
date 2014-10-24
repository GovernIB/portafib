
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for instantiatePlantillaFluxDeFirmesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="instantiatePlantillaFluxDeFirmesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://impl.v1.ws.portafib.caib.es/}fluxDeFirmesWs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "instantiatePlantillaFluxDeFirmesResponse", propOrder = {
    "_return"
})
public class InstantiatePlantillaFluxDeFirmesResponse {

    @XmlElement(name = "return")
    protected FluxDeFirmesWs _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link FluxDeFirmesWs }
     *     
     */
    public FluxDeFirmesWs getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link FluxDeFirmesWs }
     *     
     */
    public void setReturn(FluxDeFirmesWs value) {
        this._return = value;
    }

}
