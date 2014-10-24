
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createCarrecSimple complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createCarrecSimple">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="administrationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="carrecUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="carrecName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCarrecSimple", propOrder = {
    "administrationID",
    "entitatID",
    "carrecUsername",
    "carrecName"
})
public class CreateCarrecSimple {

    protected String administrationID;
    protected String entitatID;
    protected String carrecUsername;
    protected String carrecName;

    /**
     * Gets the value of the administrationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdministrationID() {
        return administrationID;
    }

    /**
     * Sets the value of the administrationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdministrationID(String value) {
        this.administrationID = value;
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

    /**
     * Gets the value of the carrecUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrecUsername() {
        return carrecUsername;
    }

    /**
     * Sets the value of the carrecUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrecUsername(String value) {
        this.carrecUsername = value;
    }

    /**
     * Gets the value of the carrecName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrecName() {
        return carrecName;
    }

    /**
     * Sets the value of the carrecName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrecName(String value) {
        this.carrecName = value;
    }

}
