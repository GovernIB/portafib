
package es.caib.portafib.ws.api.v1.passarelafirmaservidor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WsI18NError complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsI18NError">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="translation" type="{http://impl.v1.ws.portafib.caib.es/}wsI18NTranslation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsI18NError", propOrder = {
    "translation"
})
public class WsI18NError {

    @XmlElement(required = true, nillable = true)
    protected WsI18NTranslation translation;

    /**
     * Gets the value of the translation property.
     * 
     * @return
     *     possible object is
     *     {@link WsI18NTranslation }
     *     
     */
    public WsI18NTranslation getTranslation() {
        return translation;
    }

    /**
     * Sets the value of the translation property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsI18NTranslation }
     *     
     */
    public void setTranslation(WsI18NTranslation value) {
        this.translation = value;
    }

}
