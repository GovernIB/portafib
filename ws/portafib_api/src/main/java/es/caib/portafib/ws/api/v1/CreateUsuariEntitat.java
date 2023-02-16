
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para createUsuariEntitat complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="createUsuariEntitat"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="usuariEntitatBean" type="{http://impl.v1.ws.portafib.caib.es/}usuariEntitatBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Obtiene el valor de la propiedad usuariEntitatBean.
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
     * Define el valor de la propiedad usuariEntitatBean.
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
