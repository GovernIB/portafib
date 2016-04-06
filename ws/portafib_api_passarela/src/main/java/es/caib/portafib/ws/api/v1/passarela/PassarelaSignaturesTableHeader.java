
package es.caib.portafib.ws.api.v1.passarela;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for passarelaSignaturesTableHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="passarelaSignaturesTableHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="logoJpeg" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleFieldLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleFieldValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descriptionFieldLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descriptionFieldValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signatureLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "passarelaSignaturesTableHeader", propOrder = {
    "logoJpeg",
    "title",
    "titleFieldLabel",
    "titleFieldValue",
    "descriptionFieldLabel",
    "descriptionFieldValue",
    "signatureLabel"
})
public class PassarelaSignaturesTableHeader {

    protected byte[] logoJpeg;
    protected String title;
    protected String titleFieldLabel;
    protected String titleFieldValue;
    protected String descriptionFieldLabel;
    protected String descriptionFieldValue;
    protected String signatureLabel;

    /**
     * Gets the value of the logoJpeg property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getLogoJpeg() {
        return logoJpeg;
    }

    /**
     * Sets the value of the logoJpeg property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setLogoJpeg(byte[] value) {
        this.logoJpeg = ((byte[]) value);
    }

    /**
     * Gets the value of the title property.
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
     * Sets the value of the title property.
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
     * Gets the value of the titleFieldLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleFieldLabel() {
        return titleFieldLabel;
    }

    /**
     * Sets the value of the titleFieldLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleFieldLabel(String value) {
        this.titleFieldLabel = value;
    }

    /**
     * Gets the value of the titleFieldValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleFieldValue() {
        return titleFieldValue;
    }

    /**
     * Sets the value of the titleFieldValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleFieldValue(String value) {
        this.titleFieldValue = value;
    }

    /**
     * Gets the value of the descriptionFieldLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionFieldLabel() {
        return descriptionFieldLabel;
    }

    /**
     * Sets the value of the descriptionFieldLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionFieldLabel(String value) {
        this.descriptionFieldLabel = value;
    }

    /**
     * Gets the value of the descriptionFieldValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionFieldValue() {
        return descriptionFieldValue;
    }

    /**
     * Sets the value of the descriptionFieldValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionFieldValue(String value) {
        this.descriptionFieldValue = value;
    }

    /**
     * Gets the value of the signatureLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureLabel() {
        return signatureLabel;
    }

    /**
     * Sets the value of the signatureLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureLabel(String value) {
        this.signatureLabel = value;
    }

}
