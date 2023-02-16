
package es.caib.portafib.ws.api.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para blocDeFirmesWs complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="blocDeFirmesWs"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://impl.v1.ws.portafib.caib.es/}blocDeFirmesBean"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="firmes" type="{http://impl.v1.ws.portafib.caib.es/}firmaBean" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
