
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para createCarrec complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="createCarrec"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="carrec" type="{http://impl.v1.ws.portafib.caib.es/}carrecWs" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCarrec", propOrder = {
    "carrec"
})
public class CreateCarrec {

    protected CarrecWs carrec;

    /**
     * Obtiene el valor de la propiedad carrec.
     * 
     * @return
     *     possible object is
     *     {@link CarrecWs }
     *     
     */
    public CarrecWs getCarrec() {
        return carrec;
    }

    /**
     * Define el valor de la propiedad carrec.
     * 
     * @param value
     *     allowed object is
     *     {@link CarrecWs }
     *     
     */
    public void setCarrec(CarrecWs value) {
        this.carrec = value;
    }

}
