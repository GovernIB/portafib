
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for instantiatePlantillaFluxDeFirmes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="instantiatePlantillaFluxDeFirmes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="plantillaDeFluxDeFirmesID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the plantillaDeFluxDeFirmesID property.
     * 
     */
    public long getPlantillaDeFluxDeFirmesID() {
        return plantillaDeFluxDeFirmesID;
    }

    /**
     * Sets the value of the plantillaDeFluxDeFirmesID property.
     * 
     */
    public void setPlantillaDeFluxDeFirmesID(long value) {
        this.plantillaDeFluxDeFirmesID = value;
    }

}
