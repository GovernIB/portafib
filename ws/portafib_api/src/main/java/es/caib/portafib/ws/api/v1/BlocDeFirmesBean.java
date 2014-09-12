
package es.caib.portafib.ws.api.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for blocDeFirmesBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="blocDeFirmesBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="blocDeFirmesID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="dataFinalitzacio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fluxDeFirmesID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="minimDeFirmes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ordre" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFinalitzacio;
    protected long fluxDeFirmesID;
    protected int minimDeFirmes;
    protected int ordre;

    /**
     * Gets the value of the blocDeFirmesID property.
     * 
     */
    public long getBlocDeFirmesID() {
        return blocDeFirmesID;
    }

    /**
     * Sets the value of the blocDeFirmesID property.
     * 
     */
    public void setBlocDeFirmesID(long value) {
        this.blocDeFirmesID = value;
    }

    /**
     * Gets the value of the dataFinalitzacio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFinalitzacio() {
        return dataFinalitzacio;
    }

    /**
     * Sets the value of the dataFinalitzacio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFinalitzacio(XMLGregorianCalendar value) {
        this.dataFinalitzacio = value;
    }

    /**
     * Gets the value of the fluxDeFirmesID property.
     * 
     */
    public long getFluxDeFirmesID() {
        return fluxDeFirmesID;
    }

    /**
     * Sets the value of the fluxDeFirmesID property.
     * 
     */
    public void setFluxDeFirmesID(long value) {
        this.fluxDeFirmesID = value;
    }

    /**
     * Gets the value of the minimDeFirmes property.
     * 
     */
    public int getMinimDeFirmes() {
        return minimDeFirmes;
    }

    /**
     * Sets the value of the minimDeFirmes property.
     * 
     */
    public void setMinimDeFirmes(int value) {
        this.minimDeFirmes = value;
    }

    /**
     * Gets the value of the ordre property.
     * 
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * Sets the value of the ordre property.
     * 
     */
    public void setOrdre(int value) {
        this.ordre = value;
    }

}
