
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for usuariAplicacioBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="usuariAplicacioBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actiu" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="callbackURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="callbackVersio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="contrasenya" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailAdmin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idiomaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logoSegell" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/>
 *         &lt;element name="logoSegellID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="potCustodiar" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="usuariAplicacioID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the actiu property.
     * 
     */
    public boolean isActiu() {
        return actiu;
    }

    /**
     * Sets the value of the actiu property.
     * 
     */
    public void setActiu(boolean value) {
        this.actiu = value;
    }

    /**
     * Gets the value of the callbackURL property.
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
     * Sets the value of the callbackURL property.
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
     * Gets the value of the callbackVersio property.
     * 
     */
    public int getCallbackVersio() {
        return callbackVersio;
    }

    /**
     * Sets the value of the callbackVersio property.
     * 
     */
    public void setCallbackVersio(int value) {
        this.callbackVersio = value;
    }

    /**
     * Gets the value of the contrasenya property.
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
     * Sets the value of the contrasenya property.
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
     * Gets the value of the descripcio property.
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
     * Sets the value of the descripcio property.
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
     * Gets the value of the emailAdmin property.
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
     * Sets the value of the emailAdmin property.
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
     * Gets the value of the entitatID property.
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
     * Sets the value of the entitatID property.
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
     * Gets the value of the idiomaID property.
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
     * Sets the value of the idiomaID property.
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
     * Gets the value of the logoSegell property.
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
     * Sets the value of the logoSegell property.
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
     * Gets the value of the logoSegellID property.
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
     * Sets the value of the logoSegellID property.
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
     * Gets the value of the potCustodiar property.
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
     * Sets the value of the potCustodiar property.
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
     * Gets the value of the usuariAplicacioID property.
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
     * Sets the value of the usuariAplicacioID property.
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
