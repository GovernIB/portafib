
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para carrecWs complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="carrecWs"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="actiu" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="carrecID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="carrecName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="entitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="usuariPersonaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Obtiene el valor de la propiedad actiu.
     * 
     */
    public boolean isActiu() {
        return actiu;
    }

    /**
     * Define el valor de la propiedad actiu.
     * 
     */
    public void setActiu(boolean value) {
        this.actiu = value;
    }

    /**
     * Obtiene el valor de la propiedad carrecID.
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
     * Define el valor de la propiedad carrecID.
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
     * Obtiene el valor de la propiedad usuariPersonaID.
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
     * Define el valor de la propiedad usuariPersonaID.
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
