
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para usuariAplicacioFilterWs complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="usuariAplicacioFilterWs"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="filterByActiu" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="filterByCallBackURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="filterByEntitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="filterByUsuariAplicacioID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usuariAplicacioFilterWs", propOrder = {
    "filterByActiu",
    "filterByCallBackURL",
    "filterByEntitatID",
    "filterByUsuariAplicacioID"
})
public class UsuariAplicacioFilterWs {

    protected Boolean filterByActiu;
    protected String filterByCallBackURL;
    protected String filterByEntitatID;
    protected String filterByUsuariAplicacioID;

    /**
     * Obtiene el valor de la propiedad filterByActiu.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFilterByActiu() {
        return filterByActiu;
    }

    /**
     * Define el valor de la propiedad filterByActiu.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFilterByActiu(Boolean value) {
        this.filterByActiu = value;
    }

    /**
     * Obtiene el valor de la propiedad filterByCallBackURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilterByCallBackURL() {
        return filterByCallBackURL;
    }

    /**
     * Define el valor de la propiedad filterByCallBackURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilterByCallBackURL(String value) {
        this.filterByCallBackURL = value;
    }

    /**
     * Obtiene el valor de la propiedad filterByEntitatID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilterByEntitatID() {
        return filterByEntitatID;
    }

    /**
     * Define el valor de la propiedad filterByEntitatID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilterByEntitatID(String value) {
        this.filterByEntitatID = value;
    }

    /**
     * Obtiene el valor de la propiedad filterByUsuariAplicacioID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilterByUsuariAplicacioID() {
        return filterByUsuariAplicacioID;
    }

    /**
     * Define el valor de la propiedad filterByUsuariAplicacioID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilterByUsuariAplicacioID(String value) {
        this.filterByUsuariAplicacioID = value;
    }

}
