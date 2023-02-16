
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para createPlantillaFluxDeFirmes complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="createPlantillaFluxDeFirmes"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fluxDeFirmesWs" type="{http://impl.v1.ws.portafib.caib.es/}fluxDeFirmesWs" minOccurs="0"/&gt;
 *         &lt;element name="compartir" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Obtiene el valor de la propiedad fluxDeFirmesWs.
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
     * Define el valor de la propiedad fluxDeFirmesWs.
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
     * Obtiene el valor de la propiedad compartir.
     * 
     */
    public boolean isCompartir() {
        return compartir;
    }

    /**
     * Define el valor de la propiedad compartir.
     * 
     */
    public void setCompartir(boolean value) {
        this.compartir = value;
    }

}
