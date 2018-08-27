
package es.caib.portafib.ws.api.v1.passarelafirmaservidor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDefaultCustodiaInfoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDefaultCustodiaInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://impl.v1.ws.portafib.caib.es/}custodiaInfoBean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDefaultCustodiaInfoResponse", propOrder = {
    "_return"
})
public class GetDefaultCustodiaInfoResponse {

    @XmlElement(name = "return")
    protected CustodiaInfoBean _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link CustodiaInfoBean }
     *     
     */
    public CustodiaInfoBean getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustodiaInfoBean }
     *     
     */
    public void setReturn(CustodiaInfoBean value) {
        this._return = value;
    }

}