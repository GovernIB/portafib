
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para instantiatePlantillaFluxDeFirmes complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="instantiatePlantillaFluxDeFirmes"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="plantillaDeFluxDeFirmesID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "instantiatePlantillaFluxDeFirmes", propOrder = {
    "plantillaDeFluxDeFirmesID"
})
public class InstantiatePlantillaFluxDeFirmes {

    protected long plantillaDeFluxDeFirmesID;

    /**
     * Obtiene el valor de la propiedad plantillaDeFluxDeFirmesID.
     * 
     */
    public long getPlantillaDeFluxDeFirmesID() {
        return plantillaDeFluxDeFirmesID;
    }

    /**
     * Define el valor de la propiedad plantillaDeFluxDeFirmesID.
     * 
     */
    public void setPlantillaDeFluxDeFirmesID(long value) {
        this.plantillaDeFluxDeFirmesID = value;
    }

}
