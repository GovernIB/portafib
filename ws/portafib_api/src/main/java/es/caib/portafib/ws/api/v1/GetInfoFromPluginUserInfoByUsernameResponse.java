
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getInfoFromPluginUserInfoByUsernameResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getInfoFromPluginUserInfoByUsernameResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://impl.v1.ws.portafib.caib.es/}usuariPersonaBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getInfoFromPluginUserInfoByUsernameResponse", propOrder = {
    "_return"
})
public class GetInfoFromPluginUserInfoByUsernameResponse {

    @XmlElement(name = "return")
    protected UsuariPersonaBean _return;

    /**
     * Obtiene el valor de la propiedad return.
     * 
     * @return
     *     possible object is
     *     {@link UsuariPersonaBean }
     *     
     */
    public UsuariPersonaBean getReturn() {
        return _return;
    }

    /**
     * Define el valor de la propiedad return.
     * 
     * @param value
     *     allowed object is
     *     {@link UsuariPersonaBean }
     *     
     */
    public void setReturn(UsuariPersonaBean value) {
        this._return = value;
    }

}
