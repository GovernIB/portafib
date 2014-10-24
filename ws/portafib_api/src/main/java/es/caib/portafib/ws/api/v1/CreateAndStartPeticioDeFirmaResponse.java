
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createAndStartPeticioDeFirmaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createAndStartPeticioDeFirmaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://impl.v1.ws.portafib.caib.es/}peticioDeFirmaWs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createAndStartPeticioDeFirmaResponse", propOrder = {
    "_return"
})
public class CreateAndStartPeticioDeFirmaResponse {

    @XmlElement(name = "return")
    protected PeticioDeFirmaWs _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link PeticioDeFirmaWs }
     *     
     */
    public PeticioDeFirmaWs getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeticioDeFirmaWs }
     *     
     */
    public void setReturn(PeticioDeFirmaWs value) {
        this._return = value;
    }

}
