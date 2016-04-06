
package es.caib.portafib.ws.api.v1.passarela;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSignatureResultsOfTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSignatureResultsOfTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="signaturesSetID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSignatureResultsOfTransaction", propOrder = {
    "signaturesSetID"
})
public class GetSignatureResultsOfTransaction {

    protected String signaturesSetID;

    /**
     * Gets the value of the signaturesSetID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignaturesSetID() {
        return signaturesSetID;
    }

    /**
     * Sets the value of the signaturesSetID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignaturesSetID(String value) {
        this.signaturesSetID = value;
    }

}
