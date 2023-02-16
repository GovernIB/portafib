
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para createUsuariAplicacio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="createUsuariAplicacio"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="usuariAplicacioBean" type="{http://impl.v1.ws.portafib.caib.es/}usuariAplicacioBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Obtiene el valor de la propiedad usuariAplicacioBean.
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
     * Define el valor de la propiedad usuariAplicacioBean.
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
