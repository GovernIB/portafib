
package es.caib.portafib.ws.api.v1.passarelafirmaweb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WsValidationErrors complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsValidationErrors">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fieldFaults" type="{http://impl.v1.ws.portafib.caib.es/}wsFieldValidationError" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsValidationErrors", propOrder = {
    "fieldFaults"
})
public class WsValidationErrors {

    protected List<WsFieldValidationError> fieldFaults;

    /**
     * Gets the value of the fieldFaults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fieldFaults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFieldFaults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsFieldValidationError }
     * 
     * 
     */
    public List<WsFieldValidationError> getFieldFaults() {
        if (fieldFaults == null) {
            fieldFaults = new ArrayList<WsFieldValidationError>();
        }
        return this.fieldFaults;
    }

}
