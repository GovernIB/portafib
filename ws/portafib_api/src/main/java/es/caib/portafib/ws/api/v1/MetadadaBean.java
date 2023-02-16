
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para metadadaBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="metadadaBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="descripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="metadadaID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="peticioDeFirmaID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="tipusMetadadaID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "metadadaBean", propOrder = {
    "descripcio",
    "metadadaID",
    "nom",
    "peticioDeFirmaID",
    "tipusMetadadaID",
    "valor"
})
public class MetadadaBean {

    protected String descripcio;
    protected long metadadaID;
    protected String nom;
    protected long peticioDeFirmaID;
    protected int tipusMetadadaID;
    protected String valor;

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
     * Obtiene el valor de la propiedad metadadaID.
     * 
     */
    public long getMetadadaID() {
        return metadadaID;
    }

    /**
     * Define el valor de la propiedad metadadaID.
     * 
     */
    public void setMetadadaID(long value) {
        this.metadadaID = value;
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

    /**
     * Obtiene el valor de la propiedad tipusMetadadaID.
     * 
     */
    public int getTipusMetadadaID() {
        return tipusMetadadaID;
    }

    /**
     * Define el valor de la propiedad tipusMetadadaID.
     * 
     */
    public void setTipusMetadadaID(int value) {
        this.tipusMetadadaID = value;
    }

    /**
     * Obtiene el valor de la propiedad valor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValor() {
        return valor;
    }

    /**
     * Define el valor de la propiedad valor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValor(String value) {
        this.valor = value;
    }

}
