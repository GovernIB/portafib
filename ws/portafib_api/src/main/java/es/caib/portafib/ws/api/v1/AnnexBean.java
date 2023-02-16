
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para annexBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="annexBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="adjuntar" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="annexID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="firmar" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="fitxer" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/&gt;
 *         &lt;element name="fitxerID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
     * Obtiene el valor de la propiedad adjuntar.
     * 
     */
    public boolean isAdjuntar() {
        return adjuntar;
    }

    /**
     * Define el valor de la propiedad adjuntar.
     * 
     */
    public void setAdjuntar(boolean value) {
        this.adjuntar = value;
    }

    /**
     * Obtiene el valor de la propiedad annexID.
     * 
     */
    public long getAnnexID() {
        return annexID;
    }

    /**
     * Define el valor de la propiedad annexID.
     * 
     */
    public void setAnnexID(long value) {
        this.annexID = value;
    }

    /**
     * Obtiene el valor de la propiedad firmar.
     * 
     */
    public boolean isFirmar() {
        return firmar;
    }

    /**
     * Define el valor de la propiedad firmar.
     * 
     */
    public void setFirmar(boolean value) {
        this.firmar = value;
    }

    /**
     * Obtiene el valor de la propiedad fitxer.
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
     * Define el valor de la propiedad fitxer.
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
     * Obtiene el valor de la propiedad fitxerID.
     * 
     */
    public long getFitxerID() {
        return fitxerID;
    }

    /**
     * Define el valor de la propiedad fitxerID.
     * 
     */
    public void setFitxerID(long value) {
        this.fitxerID = value;
    }

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
