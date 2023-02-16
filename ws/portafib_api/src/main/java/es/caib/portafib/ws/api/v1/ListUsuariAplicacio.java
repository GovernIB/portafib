
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para listUsuariAplicacio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="listUsuariAplicacio"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="usuariAplicacioFilterWs" type="{http://impl.v1.ws.portafib.caib.es/}usuariAplicacioFilterWs" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Obtiene el valor de la propiedad usuariAplicacioFilterWs.
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
     * Define el valor de la propiedad usuariAplicacioFilterWs.
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
