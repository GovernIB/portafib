
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for annexBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="annexBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adjuntar" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="annexID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="firmar" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fitxer" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/>
 *         &lt;element name="fitxerID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="peticioDeFirmaID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "annexBean", propOrder = {
    "adjuntar",
    "annexID",
    "firmar",
    "fitxer",
    "fitxerID",
    "peticioDeFirmaID"
})
public class AnnexBean {

    protected boolean adjuntar;
    protected long annexID;
    protected boolean firmar;
    protected FitxerBean fitxer;
    protected long fitxerID;
    protected long peticioDeFirmaID;

    /**
     * Gets the value of the adjuntar property.
     * 
     */
    public boolean isAdjuntar() {
        return adjuntar;
    }

    /**
     * Sets the value of the adjuntar property.
     * 
     */
    public void setAdjuntar(boolean value) {
        this.adjuntar = value;
    }

    /**
     * Gets the value of the annexID property.
     * 
     */
    public long getAnnexID() {
        return annexID;
    }

    /**
     * Sets the value of the annexID property.
     * 
     */
    public void setAnnexID(long value) {
        this.annexID = value;
    }

    /**
     * Gets the value of the firmar property.
     * 
     */
    public boolean isFirmar() {
        return firmar;
    }

    /**
     * Sets the value of the firmar property.
     * 
     */
    public void setFirmar(boolean value) {
        this.firmar = value;
    }

    /**
     * Gets the value of the fitxer property.
     * 
     * @return
     *     possible object is
     *     {@link FitxerBean }
     *     
     */
    public FitxerBean getFitxer() {
        return fitxer;
    }

    /**
     * Sets the value of the fitxer property.
     * 
     * @param value
     *     allowed object is
     *     {@link FitxerBean }
     *     
     */
    public void setFitxer(FitxerBean value) {
        this.fitxer = value;
    }

    /**
     * Gets the value of the fitxerID property.
     * 
     */
    public long getFitxerID() {
        return fitxerID;
    }

    /**
     * Sets the value of the fitxerID property.
     * 
     */
    public void setFitxerID(long value) {
        this.fitxerID = value;
    }

    /**
     * Gets the value of the peticioDeFirmaID property.
     * 
     */
    public long getPeticioDeFirmaID() {
        return peticioDeFirmaID;
    }

    /**
     * Sets the value of the peticioDeFirmaID property.
     * 
     */
    public void setPeticioDeFirmaID(long value) {
        this.peticioDeFirmaID = value;
    }

}
