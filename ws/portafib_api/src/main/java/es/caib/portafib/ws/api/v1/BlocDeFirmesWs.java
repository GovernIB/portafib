
package es.caib.portafib.ws.api.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for blocDeFirmesWs complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="blocDeFirmesWs">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.v1.ws.portafib.caib.es/}blocDeFirmesBean">
 *       &lt;sequence>
 *         &lt;element name="firmes" type="{http://impl.v1.ws.portafib.caib.es/}firmaBean" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "blocDeFirmesWs", propOrder = {
    "firmes"
})
public class BlocDeFirmesWs
    extends BlocDeFirmesBean
{

    @XmlElement(nillable = true)
    protected List<FirmaBean> firmes;

    /**
     * Gets the value of the firmes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the firmes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFirmes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FirmaBean }
     * 
     * 
     */
    public List<FirmaBean> getFirmes() {
        if (firmes == null) {
            firmes = new ArrayList<FirmaBean>();
        }
        return this.firmes;
    }

}
