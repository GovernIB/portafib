
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateUsuariPersona complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateUsuariPersona">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuariPersonaBean" type="{http://impl.v1.ws.portafib.caib.es/}usuariPersonaBean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateUsuariPersona", propOrder = {
    "usuariPersonaBean"
})
public class UpdateUsuariPersona {

    protected UsuariPersonaBean usuariPersonaBean;

    /**
     * Gets the value of the usuariPersonaBean property.
     * 
     * @return
     *     possible object is
     *     {@link UsuariPersonaBean }
     *     
     */
    public UsuariPersonaBean getUsuariPersonaBean() {
        return usuariPersonaBean;
    }

    /**
     * Sets the value of the usuariPersonaBean property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsuariPersonaBean }
     *     
     */
    public void setUsuariPersonaBean(UsuariPersonaBean value) {
        this.usuariPersonaBean = value;
    }

}
