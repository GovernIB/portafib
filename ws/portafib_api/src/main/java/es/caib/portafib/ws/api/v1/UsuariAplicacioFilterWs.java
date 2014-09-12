
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for usuariAplicacioFilterWs complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="usuariAplicacioFilterWs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filterByActiu" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="filterByCallBackURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filterByEntitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filterByUsuariAplicacioID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the filterByActiu property.
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
     * Sets the value of the filterByActiu property.
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
     * Gets the value of the filterByCallBackURL property.
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
     * Sets the value of the filterByCallBackURL property.
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
     * Gets the value of the filterByEntitatID property.
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
     * Sets the value of the filterByEntitatID property.
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
     * Gets the value of the filterByUsuariAplicacioID property.
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
     * Sets the value of the filterByUsuariAplicacioID property.
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
