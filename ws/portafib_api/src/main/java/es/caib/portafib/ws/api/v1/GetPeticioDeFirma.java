
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getPeticioDeFirma complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getPeticioDeFirma"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="peticioDeFirmaID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPeticioDeFirma", propOrder = {
    "peticioDeFirmaID"
})
public class GetPeticioDeFirma {

    protected long peticioDeFirmaID;

    /**
     * Obtiene el valor de la propiedad peticioDeFirmaID.
     * 
     */
    public long getPeticioDeFirmaID() {
        return peticioDeFirmaID;
    }

    /**
     * Define el valor de la propiedad peticioDeFirmaID.
     * 
     */
    public void setPeticioDeFirmaID(long value) {
        this.peticioDeFirmaID = value;
    }

}
