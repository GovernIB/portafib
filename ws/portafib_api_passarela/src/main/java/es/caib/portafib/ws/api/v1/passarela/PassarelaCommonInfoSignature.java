
package es.caib.portafib.ws.api.v1.passarela;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for passarelaCommonInfoSignature complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="passarelaCommonInfoSignature">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="languageUI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filtreCertificats" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usePortafibCertificateFilter" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="administrationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlFinal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="acceptedPlugins" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="policyInfoSignature" type="{http://impl.v1.ws.portafib.caib.es/}passarelaPolicyInfoSignature" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "passarelaCommonInfoSignature", propOrder = {
    "languageUI",
    "filtreCertificats",
    "usePortafibCertificateFilter",
    "username",
    "administrationID",
    "urlFinal",
    "acceptedPlugins",
    "policyInfoSignature"
})
public class PassarelaCommonInfoSignature {

    protected String languageUI;
    protected String filtreCertificats;
    protected boolean usePortafibCertificateFilter;
    protected String username;
    protected String administrationID;
    protected String urlFinal;
    @XmlElement(nillable = true)
    protected List<Long> acceptedPlugins;
    protected PassarelaPolicyInfoSignature policyInfoSignature;

    /**
     * Gets the value of the languageUI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageUI() {
        return languageUI;
    }

    /**
     * Sets the value of the languageUI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageUI(String value) {
        this.languageUI = value;
    }

    /**
     * Gets the value of the filtreCertificats property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiltreCertificats() {
        return filtreCertificats;
    }

    /**
     * Sets the value of the filtreCertificats property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiltreCertificats(String value) {
        this.filtreCertificats = value;
    }

    /**
     * Gets the value of the usePortafibCertificateFilter property.
     * 
     */
    public boolean isUsePortafibCertificateFilter() {
        return usePortafibCertificateFilter;
    }

    /**
     * Sets the value of the usePortafibCertificateFilter property.
     * 
     */
    public void setUsePortafibCertificateFilter(boolean value) {
        this.usePortafibCertificateFilter = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the administrationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdministrationID() {
        return administrationID;
    }

    /**
     * Sets the value of the administrationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdministrationID(String value) {
        this.administrationID = value;
    }

    /**
     * Gets the value of the urlFinal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlFinal() {
        return urlFinal;
    }

    /**
     * Sets the value of the urlFinal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlFinal(String value) {
        this.urlFinal = value;
    }

    /**
     * Gets the value of the acceptedPlugins property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the acceptedPlugins property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAcceptedPlugins().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getAcceptedPlugins() {
        if (acceptedPlugins == null) {
            acceptedPlugins = new ArrayList<Long>();
        }
        return this.acceptedPlugins;
    }

    /**
     * Gets the value of the policyInfoSignature property.
     * 
     * @return
     *     possible object is
     *     {@link PassarelaPolicyInfoSignature }
     *     
     */
    public PassarelaPolicyInfoSignature getPolicyInfoSignature() {
        return policyInfoSignature;
    }

    /**
     * Sets the value of the policyInfoSignature property.
     * 
     * @param value
     *     allowed object is
     *     {@link PassarelaPolicyInfoSignature }
     *     
     */
    public void setPolicyInfoSignature(PassarelaPolicyInfoSignature value) {
        this.policyInfoSignature = value;
    }

}
