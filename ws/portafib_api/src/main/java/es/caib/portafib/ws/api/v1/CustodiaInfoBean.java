
package es.caib.portafib.ws.api.v1;

import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for custodiaInfoBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="custodiaInfoBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiBarresID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiBarresPosicioPaginaID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="codiBarresText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custodiaDocumentID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custodiaInfoID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="custodiaPluginParameters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custodiar" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dataCustodia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="entitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="missatge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="missatgePosicioPaginaID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nomPlantilla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pagines" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pluginID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="titolPeticio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlFitxerCustodiat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuariAplicacioID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuariEntitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "custodiaInfoBean", propOrder = {
    "codiBarresID",
    "codiBarresPosicioPaginaID",
    "codiBarresText",
    "custodiaDocumentID",
    "custodiaInfoID",
    "custodiaPluginParameters",
    "custodiar",
    "dataCustodia",
    "editable",
    "entitatID",
    "missatge",
    "missatgePosicioPaginaID",
    "nomPlantilla",
    "pagines",
    "pluginID",
    "titolPeticio",
    "urlFitxerCustodiat",
    "usuariAplicacioID",
    "usuariEntitatID"
})
public class CustodiaInfoBean {

    protected String codiBarresID;
    protected long codiBarresPosicioPaginaID;
    protected String codiBarresText;
    protected String custodiaDocumentID;
    protected long custodiaInfoID;
    protected String custodiaPluginParameters;
    protected boolean custodiar;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Timestamp dataCustodia;
    protected boolean editable;
    protected String entitatID;
    protected String missatge;
    protected long missatgePosicioPaginaID;
    protected String nomPlantilla;
    protected String pagines;
    protected long pluginID;
    protected String titolPeticio;
    protected String urlFitxerCustodiat;
    protected String usuariAplicacioID;
    protected String usuariEntitatID;

    /**
     * Gets the value of the codiBarresID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiBarresID() {
        return codiBarresID;
    }

    /**
     * Sets the value of the codiBarresID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiBarresID(String value) {
        this.codiBarresID = value;
    }

    /**
     * Gets the value of the codiBarresPosicioPaginaID property.
     * 
     */
    public long getCodiBarresPosicioPaginaID() {
        return codiBarresPosicioPaginaID;
    }

    /**
     * Sets the value of the codiBarresPosicioPaginaID property.
     * 
     */
    public void setCodiBarresPosicioPaginaID(long value) {
        this.codiBarresPosicioPaginaID = value;
    }

    /**
     * Gets the value of the codiBarresText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiBarresText() {
        return codiBarresText;
    }

    /**
     * Sets the value of the codiBarresText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiBarresText(String value) {
        this.codiBarresText = value;
    }

    /**
     * Gets the value of the custodiaDocumentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustodiaDocumentID() {
        return custodiaDocumentID;
    }

    /**
     * Sets the value of the custodiaDocumentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustodiaDocumentID(String value) {
        this.custodiaDocumentID = value;
    }

    /**
     * Gets the value of the custodiaInfoID property.
     * 
     */
    public long getCustodiaInfoID() {
        return custodiaInfoID;
    }

    /**
     * Sets the value of the custodiaInfoID property.
     * 
     */
    public void setCustodiaInfoID(long value) {
        this.custodiaInfoID = value;
    }

    /**
     * Gets the value of the custodiaPluginParameters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustodiaPluginParameters() {
        return custodiaPluginParameters;
    }

    /**
     * Sets the value of the custodiaPluginParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustodiaPluginParameters(String value) {
        this.custodiaPluginParameters = value;
    }

    /**
     * Gets the value of the custodiar property.
     * 
     */
    public boolean isCustodiar() {
        return custodiar;
    }

    /**
     * Sets the value of the custodiar property.
     * 
     */
    public void setCustodiar(boolean value) {
        this.custodiar = value;
    }

    /**
     * Gets the value of the dataCustodia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Timestamp getDataCustodia() {
        return dataCustodia;
    }

    /**
     * Sets the value of the dataCustodia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataCustodia(Timestamp value) {
        this.dataCustodia = value;
    }

    /**
     * Gets the value of the editable property.
     * 
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Sets the value of the editable property.
     * 
     */
    public void setEditable(boolean value) {
        this.editable = value;
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
     * Gets the value of the missatge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMissatge() {
        return missatge;
    }

    /**
     * Sets the value of the missatge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMissatge(String value) {
        this.missatge = value;
    }

    /**
     * Gets the value of the missatgePosicioPaginaID property.
     * 
     */
    public long getMissatgePosicioPaginaID() {
        return missatgePosicioPaginaID;
    }

    /**
     * Sets the value of the missatgePosicioPaginaID property.
     * 
     */
    public void setMissatgePosicioPaginaID(long value) {
        this.missatgePosicioPaginaID = value;
    }

    /**
     * Gets the value of the nomPlantilla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomPlantilla() {
        return nomPlantilla;
    }

    /**
     * Sets the value of the nomPlantilla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomPlantilla(String value) {
        this.nomPlantilla = value;
    }

    /**
     * Gets the value of the pagines property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagines() {
        return pagines;
    }

    /**
     * Sets the value of the pagines property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagines(String value) {
        this.pagines = value;
    }

    /**
     * Gets the value of the pluginID property.
     * 
     */
    public long getPluginID() {
        return pluginID;
    }

    /**
     * Sets the value of the pluginID property.
     * 
     */
    public void setPluginID(long value) {
        this.pluginID = value;
    }

    /**
     * Gets the value of the titolPeticio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitolPeticio() {
        return titolPeticio;
    }

    /**
     * Sets the value of the titolPeticio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitolPeticio(String value) {
        this.titolPeticio = value;
    }

    /**
     * Gets the value of the urlFitxerCustodiat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlFitxerCustodiat() {
        return urlFitxerCustodiat;
    }

    /**
     * Sets the value of the urlFitxerCustodiat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlFitxerCustodiat(String value) {
        this.urlFitxerCustodiat = value;
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

}
