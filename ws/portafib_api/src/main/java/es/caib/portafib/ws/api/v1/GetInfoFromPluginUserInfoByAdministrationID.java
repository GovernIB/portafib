
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getInfoFromPluginUserInfoByAdministrationID complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getInfoFromPluginUserInfoByAdministrationID"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="administrationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getInfoFromPluginUserInfoByAdministrationID", propOrder = {
    "administrationID"
})
public class GetInfoFromPluginUserInfoByAdministrationID {

    protected String administrationID;

    /**
     * Obtiene el valor de la propiedad administrationID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdministrationID() {
        return administrationID;
    }

    /**
     * Define el valor de la propiedad administrationID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdministrationID(String value) {
        this.administrationID = value;
    }

}
