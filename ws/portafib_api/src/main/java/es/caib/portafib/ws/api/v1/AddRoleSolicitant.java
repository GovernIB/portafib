
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addRoleSolicitant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addRoleSolicitant">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuariEntitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addRoleSolicitant", propOrder = {
    "usuariEntitatID"
})
public class AddRoleSolicitant {

    protected String usuariEntitatID;

    /**
     * Gets the value of the usuariEntitatID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuariEntitatID() {
        return usuariEntitatID;
    }

    /**
     * Sets the value of the usuariEntitatID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuariEntitatID(String value) {
        this.usuariEntitatID = value;
    }

}
