
package es.caib.portafib.ws.api.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para fluxDeFirmesWs complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="fluxDeFirmesWs"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://impl.v1.ws.portafib.caib.es/}fluxDeFirmesBean"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="blocsDeFirmes" type="{http://impl.v1.ws.portafib.caib.es/}blocDeFirmesWs" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fluxDeFirmesWs", propOrder = {
    "blocsDeFirmes"
})
public class FluxDeFirmesWs
    extends FluxDeFirmesBean
{

    @XmlElement(nillable = true)
    protected List<BlocDeFirmesWs> blocsDeFirmes;

    /**
     * Gets the value of the blocsDeFirmes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the blocsDeFirmes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlocsDeFirmes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BlocDeFirmesWs }
     * 
     * 
     */
    public List<BlocDeFirmesWs> getBlocsDeFirmes() {
        if (blocsDeFirmes == null) {
            blocsDeFirmes = new ArrayList<BlocDeFirmesWs>();
        }
        return this.blocsDeFirmes;
    }

}
