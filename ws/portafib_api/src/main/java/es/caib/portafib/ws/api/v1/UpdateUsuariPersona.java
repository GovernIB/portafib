
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para updateUsuariPersona complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="updateUsuariPersona"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="usuariPersonaBean" type="{http://impl.v1.ws.portafib.caib.es/}usuariPersonaBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Obtiene el valor de la propiedad usuariPersonaBean.
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
     * Define el valor de la propiedad usuariPersonaBean.
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
