
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para wsFieldValidationError complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="wsFieldValidationError"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="field" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="translation" type="{http://impl.v1.ws.portafib.caib.es/}wsI18NTranslation"/&gt;
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsFieldValidationError", propOrder = {
    "label",
    "field",
    "translation",
    "error"
})
public class WsFieldValidationError {

    @XmlElement(required = true)
    protected String label;
    @XmlElement(required = true)
    protected String field;
    @XmlElement(required = true)
    protected WsI18NTranslation translation;
    @XmlElement(required = true)
    protected String error;

    /**
     * Obtiene el valor de la propiedad label.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Define el valor de la propiedad label.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Obtiene el valor de la propiedad field.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getField() {
        return field;
    }

    /**
     * Define el valor de la propiedad field.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setField(String value) {
        this.field = value;
    }

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
     * Obtiene el valor de la propiedad error.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Define el valor de la propiedad error.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
    }

}
