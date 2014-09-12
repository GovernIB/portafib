
package es.indra.portafirmasws.cws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para DocumentAttributes complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DocumentAttributes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="state" type="{http://www.indra.es/portafirmasws/cws}StateEnum" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extension" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sender" type="{http://www.indra.es/portafirmasws/cws}Sender" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importance" type="{http://www.indra.es/portafirmasws/cws}ImportanceEnum" minOccurs="0"/>
 *         &lt;element name="date-entry" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="date-limit" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="date-last-update" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="date-notice" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="number-annexes" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sign-annexes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="external-data" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type-sign" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="is-file-sign" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="generate-visuals" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="external-ids" type="{http://www.indra.es/portafirmasws/cws}ExternalIDs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentAttributes", propOrder = {
    "state",
    "title",
    "type",
    "subject",
    "description",
    "extension",
    "sender",
    "url",
    "importance",
    "dateEntry",
    "dateLimit",
    "dateLastUpdate",
    "dateNotice",
    "numberAnnexes",
    "signAnnexes",
    "externalData",
    "typeSign",
    "isFileSign",
    "generateVisuals",
    "externalIds"
})
public class DocumentAttributes {

    protected Integer state;
    protected String title;
    protected Integer type;
    protected String subject;
    protected String description;
    protected String extension;
    protected Sender sender;
    protected String url;
    protected ImportanceEnum importance;
    @XmlElement(name = "date-entry", nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateEntry;
    @XmlElement(name = "date-limit", nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateLimit;
    @XmlElement(name = "date-last-update", nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateLastUpdate;
    @XmlElement(name = "date-notice", nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateNotice;
    @XmlElement(name = "number-annexes", nillable = true)
    protected Integer numberAnnexes;
    @XmlElement(name = "sign-annexes", nillable = true)
    protected Boolean signAnnexes;
    @XmlElement(name = "external-data", nillable = true)
    protected String externalData;
    @XmlElement(name = "type-sign", nillable = true)
    protected Integer typeSign;
    @XmlElement(name = "is-file-sign", nillable = true)
    protected Boolean isFileSign;
    @XmlElement(name = "generate-visuals", nillable = true)
    protected Boolean generateVisuals;
    @XmlElement(name = "external-ids", nillable = true)
    protected ExternalIDs externalIds;

    /**
     * Obtiene el valor de la propiedad state.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getState() {
        return state;
    }

    /**
     * Define el valor de la propiedad state.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setState(Integer value) {
        this.state = value;
    }

    /**
     * Obtiene el valor de la propiedad title.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Define el valor de la propiedad title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Obtiene el valor de la propiedad type.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getType() {
        return type;
    }

    /**
     * Define el valor de la propiedad type.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setType(Integer value) {
        this.type = value;
    }

    /**
     * Obtiene el valor de la propiedad subject.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Define el valor de la propiedad subject.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Obtiene el valor de la propiedad description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define el valor de la propiedad description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtiene el valor de la propiedad extension.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Define el valor de la propiedad extension.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtension(String value) {
        this.extension = value;
    }

    /**
     * Obtiene el valor de la propiedad sender.
     * 
     * @return
     *     possible object is
     *     {@link Sender }
     *     
     */
    public Sender getSender() {
        return sender;
    }

    /**
     * Define el valor de la propiedad sender.
     * 
     * @param value
     *     allowed object is
     *     {@link Sender }
     *     
     */
    public void setSender(Sender value) {
        this.sender = value;
    }

    /**
     * Obtiene el valor de la propiedad url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Define el valor de la propiedad url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Obtiene el valor de la propiedad importance.
     * 
     * @return
     *     possible object is
     *     {@link ImportanceEnum }
     *     
     */
    public ImportanceEnum getImportance() {
        return importance;
    }

    /**
     * Define el valor de la propiedad importance.
     * 
     * @param value
     *     allowed object is
     *     {@link ImportanceEnum }
     *     
     */
    public void setImportance(ImportanceEnum value) {
        this.importance = value;
    }

    /**
     * Obtiene el valor de la propiedad dateEntry.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateEntry() {
        return dateEntry;
    }

    /**
     * Define el valor de la propiedad dateEntry.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateEntry(XMLGregorianCalendar value) {
        this.dateEntry = value;
    }

    /**
     * Obtiene el valor de la propiedad dateLimit.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateLimit() {
        return dateLimit;
    }

    /**
     * Define el valor de la propiedad dateLimit.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateLimit(XMLGregorianCalendar value) {
        this.dateLimit = value;
    }

    /**
     * Obtiene el valor de la propiedad dateLastUpdate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateLastUpdate() {
        return dateLastUpdate;
    }

    /**
     * Define el valor de la propiedad dateLastUpdate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateLastUpdate(XMLGregorianCalendar value) {
        this.dateLastUpdate = value;
    }

    /**
     * Obtiene el valor de la propiedad dateNotice.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateNotice() {
        return dateNotice;
    }

    /**
     * Define el valor de la propiedad dateNotice.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateNotice(XMLGregorianCalendar value) {
        this.dateNotice = value;
    }

    /**
     * Obtiene el valor de la propiedad numberAnnexes.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberAnnexes() {
        return numberAnnexes;
    }

    /**
     * Define el valor de la propiedad numberAnnexes.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberAnnexes(Integer value) {
        this.numberAnnexes = value;
    }

    /**
     * Obtiene el valor de la propiedad signAnnexes.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSignAnnexes() {
        return signAnnexes;
    }

    /**
     * Define el valor de la propiedad signAnnexes.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSignAnnexes(Boolean value) {
        this.signAnnexes = value;
    }

    /**
     * Obtiene el valor de la propiedad externalData.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalData() {
        return externalData;
    }

    /**
     * Define el valor de la propiedad externalData.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalData(String value) {
        this.externalData = value;
    }

    /**
     * Obtiene el valor de la propiedad typeSign.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTypeSign() {
        return typeSign;
    }

    /**
     * Define el valor de la propiedad typeSign.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTypeSign(Integer value) {
        this.typeSign = value;
    }

    /**
     * Obtiene el valor de la propiedad isFileSign.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsFileSign() {
        return isFileSign;
    }

    /**
     * Define el valor de la propiedad isFileSign.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsFileSign(Boolean value) {
        this.isFileSign = value;
    }

    /**
     * Obtiene el valor de la propiedad generateVisuals.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGenerateVisuals() {
        return generateVisuals;
    }

    /**
     * Define el valor de la propiedad generateVisuals.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGenerateVisuals(Boolean value) {
        this.generateVisuals = value;
    }

    /**
     * Obtiene el valor de la propiedad externalIds.
     * 
     * @return
     *     possible object is
     *     {@link ExternalIDs }
     *     
     */
    public ExternalIDs getExternalIds() {
        return externalIds;
    }

    /**
     * Define el valor de la propiedad externalIds.
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalIDs }
     *     
     */
    public void setExternalIds(ExternalIDs value) {
        this.externalIds = value;
    }

}
