
package es.caib.portafib.ws.api.v1.passarela;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for passarelaSignaturesSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="passarelaSignaturesSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="signaturesSetID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="commonInfoSignature" type="{http://impl.v1.ws.portafib.caib.es/}passarelaCommonInfoSignature" minOccurs="0"/>
 *         &lt;element name="fileInfoSignatureArray" type="{http://impl.v1.ws.portafib.caib.es/}passarelaFileInfoSignature" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "passarelaSignaturesSet", propOrder = {
    "signaturesSetID",
    "expiryDate",
    "commonInfoSignature",
    "fileInfoSignatureArray"
})
public class PassarelaSignaturesSet {

    protected String signaturesSetID;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Timestamp expiryDate;
    protected PassarelaCommonInfoSignature commonInfoSignature;
    @XmlElement(nillable = true)
    protected List<PassarelaFileInfoSignature> fileInfoSignatureArray;

    /**
     * Gets the value of the signaturesSetID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignaturesSetID() {
        return signaturesSetID;
    }

    /**
     * Sets the value of the signaturesSetID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignaturesSetID(String value) {
        this.signaturesSetID = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpiryDate(Timestamp value) {
        this.expiryDate = value;
    }

    /**
     * Gets the value of the commonInfoSignature property.
     * 
     * @return
     *     possible object is
     *     {@link PassarelaCommonInfoSignature }
     *     
     */
    public PassarelaCommonInfoSignature getCommonInfoSignature() {
        return commonInfoSignature;
    }

    /**
     * Sets the value of the commonInfoSignature property.
     * 
     * @param value
     *     allowed object is
     *     {@link PassarelaCommonInfoSignature }
     *     
     */
    public void setCommonInfoSignature(PassarelaCommonInfoSignature value) {
        this.commonInfoSignature = value;
    }

    /**
     * Gets the value of the fileInfoSignatureArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fileInfoSignatureArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFileInfoSignatureArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PassarelaFileInfoSignature }
     * 
     * 
     */
    public List<PassarelaFileInfoSignature> getFileInfoSignatureArray() {
        if (fileInfoSignatureArray == null) {
            fileInfoSignatureArray = new ArrayList<PassarelaFileInfoSignature>();
        }
        return this.fileInfoSignatureArray;
    }

}
