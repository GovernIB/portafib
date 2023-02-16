
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para addRolAdminToUsuariAplicacio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="addRolAdminToUsuariAplicacio"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="usuariAplicacioID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addRolAdminToUsuariAplicacio", propOrder = {
    "usuariAplicacioID"
})
public class AddRolAdminToUsuariAplicacio {

    protected String usuariAplicacioID;

    /**
     * Obtiene el valor de la propiedad usuariAplicacioID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuariAplicacioID() {
        return usuariAplicacioID;
    }

    /**
     * Define el valor de la propiedad usuariAplicacioID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuariAplicacioID(String value) {
        this.usuariAplicacioID = value;
    }

}
