
package es.caib.portafib.ws.api.v1.passarelafirmaweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for startTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="startTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="signaturesSet" type="{http://impl.v1.ws.portafib.caib.es/}passarelaSignaturesSet" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "startTransaction", propOrder = {
    "signaturesSet"
})
public class StartTransaction {

    protected PassarelaSignaturesSet signaturesSet;

    /**
     * Gets the value of the signaturesSet property.
     * 
     * @return
     *     possible object is
     *     {@link PassarelaSignaturesSet }
     *     
     */
    public PassarelaSignaturesSet getSignaturesSet() {
        return signaturesSet;
    }

    /**
     * Sets the value of the signaturesSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link PassarelaSignaturesSet }
     *     
     */
    public void setSignaturesSet(PassarelaSignaturesSet value) {
        this.signaturesSet = value;
    }

}
