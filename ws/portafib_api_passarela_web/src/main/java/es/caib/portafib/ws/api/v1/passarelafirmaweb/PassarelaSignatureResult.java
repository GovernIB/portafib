
package es.caib.portafib.ws.api.v1.passarelafirmaweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for passarelaSignatureResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="passarelaSignatureResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.v1.ws.portafib.caib.es/}passarelaSignatureStatus">
 *       &lt;sequence>
 *         &lt;element name="signID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signedFile" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "passarelaSignatureResult", propOrder = {
    "signID",
    "signedFile"
})
public class PassarelaSignatureResult
    extends PassarelaSignatureStatus
{

    protected String signID;
    protected FitxerBean signedFile;

    /**
     * Gets the value of the signID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignID() {
        return signID;
    }

    /**
     * Sets the value of the signID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignID(String value) {
        this.signID = value;
    }

    /**
     * Gets the value of the signedFile property.
     * 
     * @return
     *     possible object is
     *     {@link FitxerBean }
     *     
     */
    public FitxerBean getSignedFile() {
        return signedFile;
    }

    /**
     * Sets the value of the signedFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link FitxerBean }
     *     
     */
    public void setSignedFile(FitxerBean value) {
        this.signedFile = value;
    }

}
