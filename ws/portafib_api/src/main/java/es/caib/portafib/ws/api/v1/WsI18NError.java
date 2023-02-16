
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para WsI18NError complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="WsI18NError"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="translation" type="{http://impl.v1.ws.portafib.caib.es/}wsI18NTranslation" minOccurs="0"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsI18NError", propOrder = {
    "translation",
    "message"
})
public class WsI18NError {

    protected WsI18NTranslation translation;
    protected String message;

    /**
     * Obtiene el valor de la propiedad translation.
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
     * Define el valor de la propiedad translation.
     * 
     * @param value
     *     allowed object is
     *     {@link WsI18NTranslation }
     *     
     */
    public void setTranslation(WsI18NTranslation value) {
        this.translation = value;
    }

    /**
     * Obtiene el valor de la propiedad message.
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
     * Define el valor de la propiedad message.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
