
package es.caib.portafib.ws.api.v1.passarela;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for passarelaFileInfoSignature complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="passarelaFileInfoSignature">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileToSign" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/>
 *         &lt;element name="signID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signerEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="languageSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signAlgorithm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signMode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="signaturesTableLocation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="signaturesTableHeader" type="{http://impl.v1.ws.portafib.caib.es/}passarelaSignaturesTableHeader" minOccurs="0"/>
 *         &lt;element name="secureVerificationCodeStampInfo" type="{http://impl.v1.ws.portafib.caib.es/}passarelaSecureVerificationCodeStampInfo" minOccurs="0"/>
 *         &lt;element name="useTimeStamp" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="custodiaInfo" type="{http://impl.v1.ws.portafib.caib.es/}custodiaInfoBean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "passarelaFileInfoSignature", propOrder = {
    "fileToSign",
    "signID",
    "name",
    "reason",
    "location",
    "signerEmail",
    "signNumber",
    "languageSign",
    "signType",
    "signAlgorithm",
    "signMode",
    "signaturesTableLocation",
    "signaturesTableHeader",
    "secureVerificationCodeStampInfo",
    "useTimeStamp",
    "custodiaInfo"
})
public class PassarelaFileInfoSignature {

    protected FitxerBean fileToSign;
    protected String signID;
    protected String name;
    protected String reason;
    protected String location;
    protected String signerEmail;
    protected int signNumber;
    protected String languageSign;
    protected String signType;
    protected String signAlgorithm;
    protected int signMode;
    protected int signaturesTableLocation;
    protected PassarelaSignaturesTableHeader signaturesTableHeader;
    protected PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo;
    protected boolean useTimeStamp;
    protected CustodiaInfoBean custodiaInfo;

    /**
     * Gets the value of the fileToSign property.
     * 
     * @return
     *     possible object is
     *     {@link FitxerBean }
     *     
     */
    public FitxerBean getFileToSign() {
        return fileToSign;
    }

    /**
     * Sets the value of the fileToSign property.
     * 
     * @param value
     *     allowed object is
     *     {@link FitxerBean }
     *     
     */
    public void setFileToSign(FitxerBean value) {
        this.fileToSign = value;
    }

    /**
     * Gets the value of the signID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignID() {
        return signID;
    }

    /**
     * Sets the value of the signID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignID(String value) {
        this.signID = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the signerEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignerEmail() {
        return signerEmail;
    }

    /**
     * Sets the value of the signerEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignerEmail(String value) {
        this.signerEmail = value;
    }

    /**
     * Gets the value of the signNumber property.
     * 
     */
    public int getSignNumber() {
        return signNumber;
    }

    /**
     * Sets the value of the signNumber property.
     * 
     */
    public void setSignNumber(int value) {
        this.signNumber = value;
    }

    /**
     * Gets the value of the languageSign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageSign() {
        return languageSign;
    }

    /**
     * Sets the value of the languageSign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageSign(String value) {
        this.languageSign = value;
    }

    /**
     * Gets the value of the signType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignType() {
        return signType;
    }

    /**
     * Sets the value of the signType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignType(String value) {
        this.signType = value;
    }

    /**
     * Gets the value of the signAlgorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignAlgorithm() {
        return signAlgorithm;
    }

    /**
     * Sets the value of the signAlgorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignAlgorithm(String value) {
        this.signAlgorithm = value;
    }

    /**
     * Gets the value of the signMode property.
     * 
     */
    public int getSignMode() {
        return signMode;
    }

    /**
     * Sets the value of the signMode property.
     * 
     */
    public void setSignMode(int value) {
        this.signMode = value;
    }

    /**
     * Gets the value of the signaturesTableLocation property.
     * 
     */
    public int getSignaturesTableLocation() {
        return signaturesTableLocation;
    }

    /**
     * Sets the value of the signaturesTableLocation property.
     * 
     */
    public void setSignaturesTableLocation(int value) {
        this.signaturesTableLocation = value;
    }

    /**
     * Gets the value of the signaturesTableHeader property.
     * 
     * @return
     *     possible object is
     *     {@link PassarelaSignaturesTableHeader }
     *     
     */
    public PassarelaSignaturesTableHeader getSignaturesTableHeader() {
        return signaturesTableHeader;
    }

    /**
     * Sets the value of the signaturesTableHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link PassarelaSignaturesTableHeader }
     *     
     */
    public void setSignaturesTableHeader(PassarelaSignaturesTableHeader value) {
        this.signaturesTableHeader = value;
    }

    /**
     * Gets the value of the secureVerificationCodeStampInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PassarelaSecureVerificationCodeStampInfo }
     *     
     */
    public PassarelaSecureVerificationCodeStampInfo getSecureVerificationCodeStampInfo() {
        return secureVerificationCodeStampInfo;
    }

    /**
     * Sets the value of the secureVerificationCodeStampInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PassarelaSecureVerificationCodeStampInfo }
     *     
     */
    public void setSecureVerificationCodeStampInfo(PassarelaSecureVerificationCodeStampInfo value) {
        this.secureVerificationCodeStampInfo = value;
    }

    /**
     * Gets the value of the useTimeStamp property.
     * 
     */
    public boolean isUseTimeStamp() {
        return useTimeStamp;
    }

    /**
     * Sets the value of the useTimeStamp property.
     * 
     */
    public void setUseTimeStamp(boolean value) {
        this.useTimeStamp = value;
    }

    /**
     * Gets the value of the custodiaInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CustodiaInfoBean }
     *     
     */
    public CustodiaInfoBean getCustodiaInfo() {
        return custodiaInfo;
    }

    /**
     * Sets the value of the custodiaInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustodiaInfoBean }
     *     
     */
    public void setCustodiaInfo(CustodiaInfoBean value) {
        this.custodiaInfo = value;
    }

}
