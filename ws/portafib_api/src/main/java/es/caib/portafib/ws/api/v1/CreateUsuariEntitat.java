
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createUsuariEntitat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createUsuariEntitat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuariEntitatBean" type="{http://impl.v1.ws.portafib.caib.es/}usuariEntitatBean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createUsuariEntitat", propOrder = {
    "usuariEntitatBean"
})
public class CreateUsuariEntitat {

    protected UsuariEntitatBean usuariEntitatBean;

    /**
     * Gets the value of the usuariEntitatBean property.
     * 
     * @return
     *     possible object is
     *     {@link UsuariEntitatBean }
     *     
     */
    public UsuariEntitatBean getUsuariEntitatBean() {
        return usuariEntitatBean;
    }

    /**
     * Sets the value of the usuariEntitatBean property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsuariEntitatBean }
     *     
     */
    public void setUsuariEntitatBean(UsuariEntitatBean value) {
        this.usuariEntitatBean = value;
    }

}
