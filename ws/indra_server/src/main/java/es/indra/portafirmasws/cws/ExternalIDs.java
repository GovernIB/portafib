
package es.indra.portafirmasws.cws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ExternalIDs complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ExternalIDs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="logical-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="verification-code" type="{http://www.indra.es/portafirmasws/cws}VerificationCode" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExternalIDs", propOrder = {
    "logicalId",
    "verificationCode"
})
public class ExternalIDs {

    @XmlElement(name = "logical-id", nillable = true)
    protected String logicalId;
    @XmlElement(name = "verification-code", nillable = true)
    protected VerificationCode verificationCode;

    /**
     * Obtiene el valor de la propiedad logicalId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogicalId() {
        return logicalId;
    }

    /**
     * Define el valor de la propiedad logicalId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogicalId(String value) {
        this.logicalId = value;
    }

    /**
     * Obtiene el valor de la propiedad verificationCode.
     * 
     * @return
     *     possible object is
     *     {@link VerificationCode }
     *     
     */
    public VerificationCode getVerificationCode() {
        return verificationCode;
    }

    /**
     * Define el valor de la propiedad verificationCode.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationCode }
     *     
     */
    public void setVerificationCode(VerificationCode value) {
        this.verificationCode = value;
    }

}
