
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para fitxerBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="fitxerBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="descripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="encryptedFileID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fitxerID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="mime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tamany" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fitxerBean", propOrder = {
    "data",
    "descripcio",
    "encryptedFileID",
    "fitxerID",
    "mime",
    "nom",
    "tamany"
})
public class FitxerBean {

    protected byte[] data;
    protected String descripcio;
    protected String encryptedFileID;
    protected long fitxerID;
    protected String mime;
    protected String nom;
    protected long tamany;

    /**
     * Obtiene el valor de la propiedad data.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Define el valor de la propiedad data.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setData(byte[] value) {
        this.data = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Define el valor de la propiedad descripcio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcio(String value) {
        this.descripcio = value;
    }

    /**
     * Obtiene el valor de la propiedad encryptedFileID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptedFileID() {
        return encryptedFileID;
    }

    /**
     * Define el valor de la propiedad encryptedFileID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptedFileID(String value) {
        this.encryptedFileID = value;
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
     * Obtiene el valor de la propiedad mime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMime() {
        return mime;
    }

    /**
     * Define el valor de la propiedad mime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMime(String value) {
        this.mime = value;
    }

    /**
     * Obtiene el valor de la propiedad nom.
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
     * Define el valor de la propiedad nom.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Obtiene el valor de la propiedad tamany.
     * 
     */
    public long getTamany() {
        return tamany;
    }

    /**
     * Define el valor de la propiedad tamany.
     * 
     */
    public void setTamany(long value) {
        this.tamany = value;
    }

}
