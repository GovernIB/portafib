
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createCarrec complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createCarrec">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="carrec" type="{http://impl.v1.ws.portafib.caib.es/}carrecWs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCarrec", propOrder = {
    "carrec"
})
public class CreateCarrec {

    protected CarrecWs carrec;

    /**
     * Gets the value of the carrec property.
     * 
     * @return
     *     possible object is
     *     {@link CarrecWs }
     *     
     */
    public CarrecWs getCarrec() {
        return carrec;
    }

    /**
     * Sets the value of the carrec property.
     * 
     * @param value
     *     allowed object is
     *     {@link CarrecWs }
     *     
     */
    public void setCarrec(CarrecWs value) {
        this.carrec = value;
    }

}
