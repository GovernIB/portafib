
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createPlantillaFluxDeFirmes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createPlantillaFluxDeFirmes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fluxDeFirmesWs" type="{http://impl.v1.ws.portafib.caib.es/}fluxDeFirmesWs" minOccurs="0"/>
 *         &lt;element name="compartir" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPlantillaFluxDeFirmes", propOrder = {
    "fluxDeFirmesWs",
    "compartir"
})
public class CreatePlantillaFluxDeFirmes {

    protected FluxDeFirmesWs fluxDeFirmesWs;
    protected boolean compartir;

    /**
     * Gets the value of the fluxDeFirmesWs property.
     * 
     * @return
     *     possible object is
     *     {@link FluxDeFirmesWs }
     *     
     */
    public FluxDeFirmesWs getFluxDeFirmesWs() {
        return fluxDeFirmesWs;
    }

    /**
     * Sets the value of the fluxDeFirmesWs property.
     * 
     * @param value
     *     allowed object is
     *     {@link FluxDeFirmesWs }
     *     
     */
    public void setFluxDeFirmesWs(FluxDeFirmesWs value) {
        this.fluxDeFirmesWs = value;
    }

    /**
     * Gets the value of the compartir property.
     * 
     */
    public boolean isCompartir() {
        return compartir;
    }

    /**
     * Sets the value of the compartir property.
     * 
     */
    public void setCompartir(boolean value) {
        this.compartir = value;
    }

}
