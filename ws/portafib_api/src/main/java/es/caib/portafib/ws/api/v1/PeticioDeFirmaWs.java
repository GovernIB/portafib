
package es.caib.portafib.ws.api.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for peticioDeFirmaWs complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="peticioDeFirmaWs">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.v1.ws.portafib.caib.es/}peticioDeFirmaBean">
 *       &lt;sequence>
 *         &lt;element name="annexs" type="{http://impl.v1.ws.portafib.caib.es/}annexBean" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="custodiaInfo" type="{http://impl.v1.ws.portafib.caib.es/}custodiaInfoBean" minOccurs="0"/>
 *         &lt;element name="fluxDeFirmes" type="{http://impl.v1.ws.portafib.caib.es/}fluxDeFirmesWs" minOccurs="0"/>
 *         &lt;element name="metadades" type="{http://impl.v1.ws.portafib.caib.es/}metadadaBean" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the custodiaInfo property.
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
     * Sets the value of the custodiaInfo property.
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
     * Gets the value of the fluxDeFirmes property.
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
     * Sets the value of the fluxDeFirmes property.
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
