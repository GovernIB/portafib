
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listUsuariAplicacio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listUsuariAplicacio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuariAplicacioFilterWs" type="{http://impl.v1.ws.portafib.caib.es/}usuariAplicacioFilterWs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listUsuariAplicacio", propOrder = {
    "usuariAplicacioFilterWs"
})
public class ListUsuariAplicacio {

    protected UsuariAplicacioFilterWs usuariAplicacioFilterWs;

    /**
     * Gets the value of the usuariAplicacioFilterWs property.
     * 
     * @return
     *     possible object is
     *     {@link UsuariAplicacioFilterWs }
     *     
     */
    public UsuariAplicacioFilterWs getUsuariAplicacioFilterWs() {
        return usuariAplicacioFilterWs;
    }

    /**
     * Sets the value of the usuariAplicacioFilterWs property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsuariAplicacioFilterWs }
     *     
     */
    public void setUsuariAplicacioFilterWs(UsuariAplicacioFilterWs value) {
        this.usuariAplicacioFilterWs = value;
    }

}
