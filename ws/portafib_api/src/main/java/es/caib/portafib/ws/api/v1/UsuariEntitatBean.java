
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para usuariEntitatBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="usuariEntitatBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="actiu" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="carrec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="entitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="logoSegell" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/&gt;
 *         &lt;element name="logoSegellID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="potCustodiar" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="predeterminat" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="rebreTotsElsAvisos" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="usuariEntitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
     * Obtiene el valor de la propiedad carrec.
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
     * Define el valor de la propiedad carrec.
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
     * Obtiene el valor de la propiedad predeterminat.
     * 
     */
    public boolean isPredeterminat() {
        return predeterminat;
    }

    /**
     * Define el valor de la propiedad predeterminat.
     * 
     */
    public void setPredeterminat(boolean value) {
        this.predeterminat = value;
    }

    /**
     * Obtiene el valor de la propiedad rebreTotsElsAvisos.
     * 
     */
    public boolean isRebreTotsElsAvisos() {
        return rebreTotsElsAvisos;
    }

    /**
     * Define el valor de la propiedad rebreTotsElsAvisos.
     * 
     */
    public void setRebreTotsElsAvisos(boolean value) {
        this.rebreTotsElsAvisos = value;
    }

    /**
     * Obtiene el valor de la propiedad usuariEntitatID.
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
     * Define el valor de la propiedad usuariEntitatID.
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
