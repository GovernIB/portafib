
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fluxDeFirmesBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fluxDeFirmesBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fluxDeFirmesID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fluxDeFirmesBean", propOrder = {
    "fluxDeFirmesID",
    "nom"
})
@XmlSeeAlso({
    FluxDeFirmesWs.class
})
public class FluxDeFirmesBean {

    protected long fluxDeFirmesID;
    protected String nom;

    /**
     * Gets the value of the fluxDeFirmesID property.
     * 
     */
    public long getFluxDeFirmesID() {
        return fluxDeFirmesID;
    }

    /**
     * Sets the value of the fluxDeFirmesID property.
     * 
     */
    public void setFluxDeFirmesID(long value) {
        this.fluxDeFirmesID = value;
    }

    /**
     * Gets the value of the nom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the value of the nom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNom(String value) {
        this.nom = value;
    }

}
