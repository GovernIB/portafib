
package es.caib.portafib.ws.api.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para peticioDeFirmaWs complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="peticioDeFirmaWs"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://impl.v1.ws.portafib.caib.es/}peticioDeFirmaBean"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="annexs" type="{http://impl.v1.ws.portafib.caib.es/}annexBean" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="custodiaInfo" type="{http://impl.v1.ws.portafib.caib.es/}custodiaInfoBean" minOccurs="0"/&gt;
 *         &lt;element name="fluxDeFirmes" type="{http://impl.v1.ws.portafib.caib.es/}fluxDeFirmesWs" minOccurs="0"/&gt;
 *         &lt;element name="metadades" type="{http://impl.v1.ws.portafib.caib.es/}metadadaBean" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "peticioDeFirmaWs", propOrder = {
    "annexs",
    "custodiaInfo",
    "fluxDeFirmes",
    "metadades"
})
public class PeticioDeFirmaWs
    extends PeticioDeFirmaBean
{

    @XmlElement(nillable = true)
    protected List<AnnexBean> annexs;
    protected CustodiaInfoBean custodiaInfo;
    protected FluxDeFirmesWs fluxDeFirmes;
    @XmlElement(nillable = true)
    protected List<MetadadaBean> metadades;

    /**
     * Gets the value of the annexs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the annexs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnnexs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnnexBean }
     * 
     * 
     */
    public List<AnnexBean> getAnnexs() {
        if (annexs == null) {
            annexs = new ArrayList<AnnexBean>();
        }
        return this.annexs;
    }

    /**
     * Obtiene el valor de la propiedad custodiaInfo.
     * 
     * @return
     *     possible object is
     *     {@link CustodiaInfoBean }
     *     
     */
    public CustodiaInfoBean getCustodiaInfo() {
        return custodiaInfo;
    }

    /**
     * Define el valor de la propiedad custodiaInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link CustodiaInfoBean }
     *     
     */
    public void setCustodiaInfo(CustodiaInfoBean value) {
        this.custodiaInfo = value;
    }

    /**
     * Obtiene el valor de la propiedad fluxDeFirmes.
     * 
     * @return
     *     possible object is
     *     {@link FluxDeFirmesWs }
     *     
     */
    public FluxDeFirmesWs getFluxDeFirmes() {
        return fluxDeFirmes;
    }

    /**
     * Define el valor de la propiedad fluxDeFirmes.
     * 
     * @param value
     *     allowed object is
     *     {@link FluxDeFirmesWs }
     *     
     */
    public void setFluxDeFirmes(FluxDeFirmesWs value) {
        this.fluxDeFirmes = value;
    }

    /**
     * Gets the value of the metadades property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadades property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadades().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetadadaBean }
     * 
     * 
     */
    public List<MetadadaBean> getMetadades() {
        if (metadades == null) {
            metadades = new ArrayList<MetadadaBean>();
        }
        return this.metadades;
    }

}
