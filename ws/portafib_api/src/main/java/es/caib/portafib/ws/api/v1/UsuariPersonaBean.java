
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para usuariPersonaBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="usuariPersonaBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idiomaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="llinatges" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rubrica" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/&gt;
 *         &lt;element name="rubricaID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="usuariPersonaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usuariPersonaBean", propOrder = {
    "email",
    "idiomaID",
    "llinatges",
    "nif",
    "nom",
    "rubrica",
    "rubricaID",
    "usuariPersonaID"
})
public class UsuariPersonaBean {

    protected String email;
    protected String idiomaID;
    protected String llinatges;
    protected String nif;
    protected String nom;
    protected FitxerBean rubrica;
    protected Long rubricaID;
    protected String usuariPersonaID;

    /**
     * Obtiene el valor de la propiedad email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define el valor de la propiedad email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Obtiene el valor de la propiedad idiomaID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdiomaID() {
        return idiomaID;
    }

    /**
     * Define el valor de la propiedad idiomaID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdiomaID(String value) {
        this.idiomaID = value;
    }

    /**
     * Obtiene el valor de la propiedad llinatges.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLlinatges() {
        return llinatges;
    }

    /**
     * Define el valor de la propiedad llinatges.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLlinatges(String value) {
        this.llinatges = value;
    }

    /**
     * Obtiene el valor de la propiedad nif.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNif() {
        return nif;
    }

    /**
     * Define el valor de la propiedad nif.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNif(String value) {
        this.nif = value;
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
     * Obtiene el valor de la propiedad rubrica.
     * 
     * @return
     *     possible object is
     *     {@link FitxerBean }
     *     
     */
    public FitxerBean getRubrica() {
        return rubrica;
    }

    /**
     * Define el valor de la propiedad rubrica.
     * 
     * @param value
     *     allowed object is
     *     {@link FitxerBean }
     *     
     */
    public void setRubrica(FitxerBean value) {
        this.rubrica = value;
    }

    /**
     * Obtiene el valor de la propiedad rubricaID.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRubricaID() {
        return rubricaID;
    }

    /**
     * Define el valor de la propiedad rubricaID.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRubricaID(Long value) {
        this.rubricaID = value;
    }

    /**
     * Obtiene el valor de la propiedad usuariPersonaID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuariPersonaID() {
        return usuariPersonaID;
    }

    /**
     * Define el valor de la propiedad usuariPersonaID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuariPersonaID(String value) {
        this.usuariPersonaID = value;
    }

}
