
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for usuariEntitatBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="usuariEntitatBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actiu" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="carrec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logoSegell" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/>
 *         &lt;element name="logoSegellID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="potCustodiar" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="predeterminat" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="rebreTotsElsAvisos" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="usuariEntitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuariPersonaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usuariEntitatBean", propOrder = {
    "actiu",
    "carrec",
    "email",
    "entitatID",
    "logoSegell",
    "logoSegellID",
    "potCustodiar",
    "predeterminat",
    "rebreTotsElsAvisos",
    "usuariEntitatID",
    "usuariPersonaID"
})
public class UsuariEntitatBean {

    protected boolean actiu;
    protected String carrec;
    protected String email;
    protected String entitatID;
    protected FitxerBean logoSegell;
    protected Long logoSegellID;
    protected Boolean potCustodiar;
    protected boolean predeterminat;
    protected boolean rebreTotsElsAvisos;
    protected String usuariEntitatID;
    protected String usuariPersonaID;

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
     * Gets the value of the carrec property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrec() {
        return carrec;
    }

    /**
     * Sets the value of the carrec property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrec(String value) {
        this.carrec = value;
    }

    /**
     * Gets the value of the email property.
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
     * Sets the value of the email property.
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
     * Gets the value of the predeterminat property.
     * 
     */
    public boolean isPredeterminat() {
        return predeterminat;
    }

    /**
     * Sets the value of the predeterminat property.
     * 
     */
    public void setPredeterminat(boolean value) {
        this.predeterminat = value;
    }

    /**
     * Gets the value of the rebreTotsElsAvisos property.
     * 
     */
    public boolean isRebreTotsElsAvisos() {
        return rebreTotsElsAvisos;
    }

    /**
     * Sets the value of the rebreTotsElsAvisos property.
     * 
     */
    public void setRebreTotsElsAvisos(boolean value) {
        this.rebreTotsElsAvisos = value;
    }

    /**
     * Gets the value of the usuariEntitatID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuariEntitatID() {
        return usuariEntitatID;
    }

    /**
     * Sets the value of the usuariEntitatID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuariEntitatID(String value) {
        this.usuariEntitatID = value;
    }

    /**
     * Gets the value of the usuariPersonaID property.
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
     * Sets the value of the usuariPersonaID property.
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
