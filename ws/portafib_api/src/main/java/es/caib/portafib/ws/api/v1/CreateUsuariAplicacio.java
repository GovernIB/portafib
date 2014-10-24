
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createUsuariAplicacio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createUsuariAplicacio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuariAplicacioBean" type="{http://impl.v1.ws.portafib.caib.es/}usuariAplicacioBean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createUsuariAplicacio", propOrder = {
    "usuariAplicacioBean"
})
public class CreateUsuariAplicacio {

    protected UsuariAplicacioBean usuariAplicacioBean;

    /**
     * Gets the value of the usuariAplicacioBean property.
     * 
     * @return
     *     possible object is
     *     {@link UsuariAplicacioBean }
     *     
     */
    public UsuariAplicacioBean getUsuariAplicacioBean() {
        return usuariAplicacioBean;
    }

    /**
     * Sets the value of the usuariAplicacioBean property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsuariAplicacioBean }
     *     
     */
    public void setUsuariAplicacioBean(UsuariAplicacioBean value) {
        this.usuariAplicacioBean = value;
    }

}
