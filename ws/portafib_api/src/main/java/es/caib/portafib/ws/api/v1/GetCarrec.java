
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getCarrec complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getCarrec"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="carrecID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCarrec", propOrder = {
    "carrecID"
})
public class GetCarrec {

    protected String carrecID;

    /**
     * Obtiene el valor de la propiedad carrecID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrecID() {
        return carrecID;
    }

    /**
     * Define el valor de la propiedad carrecID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrecID(String value) {
        this.carrecID = value;
    }

}
