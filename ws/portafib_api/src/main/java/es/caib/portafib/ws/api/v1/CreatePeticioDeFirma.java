
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createPeticioDeFirma complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createPeticioDeFirma">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="peticioDeFirmaWs" type="{http://impl.v1.ws.portafib.caib.es/}peticioDeFirmaWs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPeticioDeFirma", propOrder = {
    "peticioDeFirmaWs"
})
public class CreatePeticioDeFirma {

    protected PeticioDeFirmaWs peticioDeFirmaWs;

    /**
     * Gets the value of the peticioDeFirmaWs property.
     * 
     * @return
     *     possible object is
     *     {@link PeticioDeFirmaWs }
     *     
     */
    public PeticioDeFirmaWs getPeticioDeFirmaWs() {
        return peticioDeFirmaWs;
    }

    /**
     * Sets the value of the peticioDeFirmaWs property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeticioDeFirmaWs }
     *     
     */
    public void setPeticioDeFirmaWs(PeticioDeFirmaWs value) {
        this.peticioDeFirmaWs = value;
    }

}
