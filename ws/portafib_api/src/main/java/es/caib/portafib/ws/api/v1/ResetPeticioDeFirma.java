
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for resetPeticioDeFirma complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="resetPeticioDeFirma">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="peticioDeFirmaID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resetPeticioDeFirma", propOrder = {
    "peticioDeFirmaID"
})
public class ResetPeticioDeFirma {

    protected long peticioDeFirmaID;

    /**
     * Gets the value of the peticioDeFirmaID property.
     * 
     */
    public long getPeticioDeFirmaID() {
        return peticioDeFirmaID;
    }

    /**
     * Sets the value of the peticioDeFirmaID property.
     * 
     */
    public void setPeticioDeFirmaID(long value) {
        this.peticioDeFirmaID = value;
    }

}
