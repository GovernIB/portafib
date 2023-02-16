
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para createPeticioDeFirma complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="createPeticioDeFirma"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="peticioDeFirmaWs" type="{http://impl.v1.ws.portafib.caib.es/}peticioDeFirmaWs" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPeticioDeFirma", propOrder = {
    "peticioDeFirmaWs"
})
public class CreatePeticioDeFirma {

    protected PeticioDeFirmaWs peticioDeFirmaWs;

    /**
     * Obtiene el valor de la propiedad peticioDeFirmaWs.
     * 
     * @return
     *     possible object is
     *     {@link PeticioDeFirmaWs }
     *     
     */
    public PeticioDeFirmaWs getPeticioDeFirmaWs() {
        return peticioDeFirmaWs;
    }

    /**
     * Define el valor de la propiedad peticioDeFirmaWs.
     * 
     * @param value
     *     allowed object is
     *     {@link PeticioDeFirmaWs }
     *     
     */
    public void setPeticioDeFirmaWs(PeticioDeFirmaWs value) {
        this.peticioDeFirmaWs = value;
    }

}
