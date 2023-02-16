
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para usuariAplicacioBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="usuariAplicacioBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="actiu" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="callbackURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="callbackVersio" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="contrasenya" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="emailAdmin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="entitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idiomaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="logoSegell" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/&gt;
 *         &lt;element name="logoSegellID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="potCustodiar" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="usuariAplicacioID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usuariAplicacioBean", propOrder = {
    "actiu",
    "callbackURL",
    "callbackVersio",
    "contrasenya",
    "descripcio",
    "emailAdmin",
    "entitatID",
    "idiomaID",
    "logoSegell",
    "logoSegellID",
    "potCustodiar",
    "usuariAplicacioID"
})
public class UsuariAplicacioBean {

    protected boolean actiu;
    protected String callbackURL;
    protected int callbackVersio;
    protected String contrasenya;
    protected String descripcio;
    protected String emailAdmin;
    protected String entitatID;
    protected String idiomaID;
    protected FitxerBean logoSegell;
    protected Long logoSegellID;
    protected Boolean potCustodiar;
    protected String usuariAplicacioID;

    /**
     * Obtiene el valor de la propiedad actiu.
     * 
     */
    public boolean isActiu() {
        return actiu;
    }

    /**
     * Define el valor de la propiedad actiu.
     * 
     */
    public void setActiu(boolean value) {
        this.actiu = value;
    }

    /**
     * Obtiene el valor de la propiedad callbackURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallbackURL() {
        return callbackURL;
    }

    /**
     * Define el valor de la propiedad callbackURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallbackURL(String value) {
        this.callbackURL = value;
    }

    /**
     * Obtiene el valor de la propiedad callbackVersio.
     * 
     */
    public int getCallbackVersio() {
        return callbackVersio;
    }

    /**
     * Define el valor de la propiedad callbackVersio.
     * 
     */
    public void setCallbackVersio(int value) {
        this.callbackVersio = value;
    }

    /**
     * Obtiene el valor de la propiedad contrasenya.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
     * Define el valor de la propiedad contrasenya.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrasenya(String value) {
        this.contrasenya = value;
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
     * Obtiene el valor de la propiedad emailAdmin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAdmin() {
        return emailAdmin;
    }

    /**
     * Define el valor de la propiedad emailAdmin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAdmin(String value) {
        this.emailAdmin = value;
    }

    /**
     * Obtiene el valor de la propiedad entitatID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntitatID() {
        return entitatID;
    }

    /**
     * Define el valor de la propiedad entitatID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntitatID(String value) {
        this.entitatID = value;
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
     * Obtiene el valor de la propiedad logoSegell.
     * 
     * @return
     *     possible object is
     *     {@link FitxerBean }
     *     
     */
    public FitxerBean getLogoSegell() {
        return logoSegell;
    }

    /**
     * Define el valor de la propiedad logoSegell.
     * 
     * @param value
     *     allowed object is
     *     {@link FitxerBean }
     *     
     */
    public void setLogoSegell(FitxerBean value) {
        this.logoSegell = value;
    }

    /**
     * Obtiene el valor de la propiedad logoSegellID.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLogoSegellID() {
        return logoSegellID;
    }

    /**
     * Define el valor de la propiedad logoSegellID.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLogoSegellID(Long value) {
        this.logoSegellID = value;
    }

    /**
     * Obtiene el valor de la propiedad potCustodiar.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPotCustodiar() {
        return potCustodiar;
    }

    /**
     * Define el valor de la propiedad potCustodiar.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPotCustodiar(Boolean value) {
        this.potCustodiar = value;
    }

    /**
     * Obtiene el valor de la propiedad usuariAplicacioID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuariAplicacioID() {
        return usuariAplicacioID;
    }

    /**
     * Define el valor de la propiedad usuariAplicacioID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuariAplicacioID(String value) {
        this.usuariAplicacioID = value;
    }

}
