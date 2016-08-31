
package es.caib.portafib.ws.api.v1.passarelafirmaservidor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for passarelaPolicyInfoSignature complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="passarelaPolicyInfoSignature">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="policyIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="policyIdentifierHash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="policyIdentifierHashAlgorithm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="policyUrlDocument" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "passarelaPolicyInfoSignature", propOrder = {
    "policyIdentifier",
    "policyIdentifierHash",
    "policyIdentifierHashAlgorithm",
    "policyUrlDocument"
})
public class PassarelaPolicyInfoSignature {

    protected String policyIdentifier;
    protected String policyIdentifierHash;
    protected String policyIdentifierHashAlgorithm;
    protected String policyUrlDocument;

    /**
     * Gets the value of the policyIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyIdentifier() {
        return policyIdentifier;
    }

    /**
     * Sets the value of the policyIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyIdentifier(String value) {
        this.policyIdentifier = value;
    }

    /**
     * Gets the value of the policyIdentifierHash property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyIdentifierHash() {
        return policyIdentifierHash;
    }

    /**
     * Sets the value of the policyIdentifierHash property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyIdentifierHash(String value) {
        this.policyIdentifierHash = value;
    }

    /**
     * Gets the value of the policyIdentifierHashAlgorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyIdentifierHashAlgorithm() {
        return policyIdentifierHashAlgorithm;
    }

    /**
     * Sets the value of the policyIdentifierHashAlgorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyIdentifierHashAlgorithm(String value) {
        this.policyIdentifierHashAlgorithm = value;
    }

    /**
     * Gets the value of the policyUrlDocument property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyUrlDocument() {
        return policyUrlDocument;
    }

    /**
     * Sets the value of the policyUrlDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyUrlDocument(String value) {
        this.policyUrlDocument = value;
    }

}
