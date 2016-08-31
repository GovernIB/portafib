
package es.caib.portafib.ws.api.v1.passarelafirmaservidor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for passarelaFullResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="passarelaFullResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="signaturesSetStatus" type="{http://impl.v1.ws.portafib.caib.es/}passarelaSignatureStatus" minOccurs="0"/>
 *         &lt;element name="signResults" type="{http://impl.v1.ws.portafib.caib.es/}passarelaSignatureResult" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "passarelaFullResults", propOrder = {
    "signaturesSetStatus",
    "signResults"
})
public class PassarelaFullResults {

    protected PassarelaSignatureStatus signaturesSetStatus;
    @XmlElement(nillable = true)
    protected List<PassarelaSignatureResult> signResults;

    /**
     * Gets the value of the signaturesSetStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PassarelaSignatureStatus }
     *     
     */
    public PassarelaSignatureStatus getSignaturesSetStatus() {
        return signaturesSetStatus;
    }

    /**
     * Sets the value of the signaturesSetStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PassarelaSignatureStatus }
     *     
     */
    public void setSignaturesSetStatus(PassarelaSignatureStatus value) {
        this.signaturesSetStatus = value;
    }

    /**
     * Gets the value of the signResults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signResults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PassarelaSignatureResult }
     * 
     * 
     */
    public List<PassarelaSignatureResult> getSignResults() {
        if (signResults == null) {
            signResults = new ArrayList<PassarelaSignatureResult>();
        }
        return this.signResults;
    }

}
