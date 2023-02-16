
package es.caib.portafib.ws.api.v1;

import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para custodiaInfoBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="custodiaInfoBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiBarresID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiBarresPosicioPaginaID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="codiBarresText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="custodiaDocumentID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="custodiaInfoID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="custodiaPluginParameters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="custodiar" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="dataCustodia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="entitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="missatge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="missatgePosicioPaginaID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="nomPlantilla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pagines" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pluginID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="titolPeticio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="urlFitxerCustodiat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="usuariAplicacioID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="usuariEntitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Obtiene el valor de la propiedad codiBarresID.
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
     * Define el valor de la propiedad codiBarresID.
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
     * Obtiene el valor de la propiedad codiBarresPosicioPaginaID.
     * 
     */
    public long getCodiBarresPosicioPaginaID() {
        return codiBarresPosicioPaginaID;
    }

    /**
     * Define el valor de la propiedad codiBarresPosicioPaginaID.
     * 
     */
    public void setCodiBarresPosicioPaginaID(long value) {
        this.codiBarresPosicioPaginaID = value;
    }

    /**
     * Obtiene el valor de la propiedad codiBarresText.
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
     * Define el valor de la propiedad codiBarresText.
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
     * Obtiene el valor de la propiedad custodiaDocumentID.
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
     * Define el valor de la propiedad custodiaDocumentID.
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
     * Obtiene el valor de la propiedad custodiaInfoID.
     * 
     */
    public long getCustodiaInfoID() {
        return custodiaInfoID;
    }

    /**
     * Define el valor de la propiedad custodiaInfoID.
     * 
     */
    public void setCustodiaInfoID(long value) {
        this.custodiaInfoID = value;
    }

    /**
     * Obtiene el valor de la propiedad custodiaPluginParameters.
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
     * Define el valor de la propiedad custodiaPluginParameters.
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
     * Obtiene el valor de la propiedad custodiar.
     * 
     */
    public boolean isCustodiar() {
        return custodiar;
    }

    /**
     * Define el valor de la propiedad custodiar.
     * 
     */
    public void setCustodiar(boolean value) {
        this.custodiar = value;
    }

    /**
     * Obtiene el valor de la propiedad dataCustodia.
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
     * Define el valor de la propiedad dataCustodia.
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
     * Obtiene el valor de la propiedad editable.
     * 
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Define el valor de la propiedad editable.
     * 
     */
    public void setEditable(boolean value) {
        this.editable = value;
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
     * Obtiene el valor de la propiedad missatge.
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
     * Define el valor de la propiedad missatge.
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
     * Obtiene el valor de la propiedad missatgePosicioPaginaID.
     * 
     */
    public long getMissatgePosicioPaginaID() {
        return missatgePosicioPaginaID;
    }

    /**
     * Define el valor de la propiedad missatgePosicioPaginaID.
     * 
     */
    public void setMissatgePosicioPaginaID(long value) {
        this.missatgePosicioPaginaID = value;
    }

    /**
     * Obtiene el valor de la propiedad nomPlantilla.
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
     * Define el valor de la propiedad nomPlantilla.
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
     * Obtiene el valor de la propiedad pagines.
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
     * Define el valor de la propiedad pagines.
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
     * Obtiene el valor de la propiedad pluginID.
     * 
     */
    public long getPluginID() {
        return pluginID;
    }

    /**
     * Define el valor de la propiedad pluginID.
     * 
     */
    public void setPluginID(long value) {
        this.pluginID = value;
    }

    /**
     * Obtiene el valor de la propiedad titolPeticio.
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
     * Define el valor de la propiedad titolPeticio.
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
     * Obtiene el valor de la propiedad urlFitxerCustodiat.
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
     * Define el valor de la propiedad urlFitxerCustodiat.
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

}
