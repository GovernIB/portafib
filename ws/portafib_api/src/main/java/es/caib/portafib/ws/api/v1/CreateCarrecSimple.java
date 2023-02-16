
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para createCarrecSimple complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="createCarrecSimple"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="administrationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="entitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="carrecUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="carrecName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Obtiene el valor de la propiedad administrationID.
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
     * Define el valor de la propiedad administrationID.
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
     * Obtiene el valor de la propiedad entitatID.
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
     * Define el valor de la propiedad entitatID.
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
     * Obtiene el valor de la propiedad carrecUsername.
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
     * Define el valor de la propiedad carrecUsername.
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
     * Obtiene el valor de la propiedad carrecName.
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
     * Define el valor de la propiedad carrecName.
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
