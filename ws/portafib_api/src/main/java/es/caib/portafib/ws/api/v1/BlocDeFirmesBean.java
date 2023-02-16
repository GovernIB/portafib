
package es.caib.portafib.ws.api.v1;

import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para blocDeFirmesBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="blocDeFirmesBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="blocDeFirmesID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="dataFinalitzacio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="fluxDeFirmesID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="minimDeFirmes" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ordre" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "blocDeFirmesBean", propOrder = {
    "blocDeFirmesID",
    "dataFinalitzacio",
    "fluxDeFirmesID",
    "minimDeFirmes",
    "ordre"
})
@XmlSeeAlso({
    BlocDeFirmesWs.class
})
public class BlocDeFirmesBean {

    protected long blocDeFirmesID;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Timestamp dataFinalitzacio;
    protected long fluxDeFirmesID;
    protected int minimDeFirmes;
    protected int ordre;

    /**
     * Obtiene el valor de la propiedad blocDeFirmesID.
     * 
     */
    public long getBlocDeFirmesID() {
        return blocDeFirmesID;
    }

    /**
     * Define el valor de la propiedad blocDeFirmesID.
     * 
     */
    public void setBlocDeFirmesID(long value) {
        this.blocDeFirmesID = value;
    }

    /**
     * Obtiene el valor de la propiedad dataFinalitzacio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Timestamp getDataFinalitzacio() {
        return dataFinalitzacio;
    }

    /**
     * Define el valor de la propiedad dataFinalitzacio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFinalitzacio(Timestamp value) {
        this.dataFinalitzacio = value;
    }

    /**
     * Obtiene el valor de la propiedad fluxDeFirmesID.
     * 
     */
    public long getFluxDeFirmesID() {
        return fluxDeFirmesID;
    }

    /**
     * Define el valor de la propiedad fluxDeFirmesID.
     * 
     */
    public void setFluxDeFirmesID(long value) {
        this.fluxDeFirmesID = value;
    }

    /**
     * Obtiene el valor de la propiedad minimDeFirmes.
     * 
     */
    public int getMinimDeFirmes() {
        return minimDeFirmes;
    }

    /**
     * Define el valor de la propiedad minimDeFirmes.
     * 
     */
    public void setMinimDeFirmes(int value) {
        this.minimDeFirmes = value;
    }

    /**
     * Obtiene el valor de la propiedad ordre.
     * 
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * Define el valor de la propiedad ordre.
     * 
     */
    public void setOrdre(int value) {
        this.ordre = value;
    }

}
