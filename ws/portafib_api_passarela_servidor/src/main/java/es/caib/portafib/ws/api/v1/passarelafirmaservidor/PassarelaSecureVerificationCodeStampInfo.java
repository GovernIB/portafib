
package es.caib.portafib.ws.api.v1.passarelafirmaservidor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for passarelaSecureVerificationCodeStampInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="passarelaSecureVerificationCodeStampInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="barCodePosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="barCodeText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="barCodeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="messagePosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pages" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "passarelaSecureVerificationCodeStampInfo", propOrder = {
    "barCodePosition",
    "barCodeText",
    "barCodeType",
    "message",
    "messagePosition",
    "pages"
})
public class PassarelaSecureVerificationCodeStampInfo {

    protected int barCodePosition;
    protected String barCodeText;
    protected String barCodeType;
    protected String message;
    protected int messagePosition;
    protected String pages;

    /**
     * Gets the value of the barCodePosition property.
     * 
     */
    public int getBarCodePosition() {
        return barCodePosition;
    }

    /**
     * Sets the value of the barCodePosition property.
     * 
     */
    public void setBarCodePosition(int value) {
        this.barCodePosition = value;
    }

    /**
     * Gets the value of the barCodeText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarCodeText() {
        return barCodeText;
    }

    /**
     * Sets the value of the barCodeText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarCodeText(String value) {
        this.barCodeText = value;
    }

    /**
     * Gets the value of the barCodeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarCodeType() {
        return barCodeType;
    }

    /**
     * Sets the value of the barCodeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarCodeType(String value) {
        this.barCodeType = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the messagePosition property.
     * 
     */
    public int getMessagePosition() {
        return messagePosition;
    }

    /**
     * Sets the value of the messagePosition property.
     * 
     */
    public void setMessagePosition(int value) {
        this.messagePosition = value;
    }

    /**
     * Gets the value of the pages property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPages() {
        return pages;
    }

    /**
     * Sets the value of the pages property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPages(String value) {
        this.pages = value;
    }

}
