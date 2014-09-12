
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for carrecWs complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="carrecWs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actiu" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="carrecID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="carrecName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuariPersonaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carrecWs", propOrder = {
    "actiu",
    "carrecID",
    "carrecName",
    "entitatID",
    "usuariPersonaID"
})
public class CarrecWs {

    protected boolean actiu;
    protected String carrecID;
    protected String carrecName;
    protected String entitatID;
    protected String usuariPersonaID;

    /**
     * Gets the value of the actiu property.
     * 
     */
    public boolean isActiu() {
        return actiu;
    }

    /**
     * Sets the value of the actiu property.
     * 
     */
    public void setActiu(boolean value) {
        this.actiu = value;
    }

    /**
     * Gets the value of the carrecID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrecID() {
        return carrecID;
    }

    /**
     * Sets the value of the carrecID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrecID(String value) {
        this.carrecID = value;
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

}
