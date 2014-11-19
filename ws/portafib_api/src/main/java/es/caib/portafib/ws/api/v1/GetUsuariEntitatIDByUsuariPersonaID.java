
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getUsuariEntitatIDByUsuariPersonaID complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getUsuariEntitatIDByUsuariPersonaID">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuariPersonaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUsuariEntitatIDByUsuariPersonaID", propOrder = {
    "usuariPersonaID",
    "entitatID"
})
public class GetUsuariEntitatIDByUsuariPersonaID {

    protected String usuariPersonaID;
    protected String entitatID;

    /**
     * Gets the value of the usuariPersonaID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuariPersonaID() {
        return usuariPersonaID;
    }

    /**
     * Sets the value of the usuariPersonaID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuariPersonaID(String value) {
        this.usuariPersonaID = value;
    }

    /**
     * Gets the value of the entitatID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntitatID() {
        return entitatID;
    }

    /**
     * Sets the value of the entitatID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntitatID(String value) {
        this.entitatID = value;
    }

}
