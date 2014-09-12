
package es.indra.portafirmasws.cws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArchiveOptions complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArchiveOptions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="source-locators" type="{http://www.indra.es/portafirmasws/cws}SourceLocators" minOccurs="0"/>
 *         &lt;element name="destination-locators" type="{http://www.indra.es/portafirmasws/cws}DestinationLocators" minOccurs="0"/>
 *         &lt;element name="archive-metadatas" type="{http://www.indra.es/portafirmasws/cws}ArchiveMetadatas" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArchiveOptions", propOrder = {
    "sourceLocators",
    "destinationLocators",
    "archiveMetadatas"
})
public class ArchiveOptions {

    @XmlElement(name = "source-locators", nillable = true)
    protected SourceLocators sourceLocators;
    @XmlElement(name = "destination-locators", nillable = true)
    protected DestinationLocators destinationLocators;
    @XmlElement(name = "archive-metadatas", nillable = true)
    protected ArchiveMetadatas archiveMetadatas;

    /**
     * Obtiene el valor de la propiedad sourceLocators.
     * 
     * @return
     *     possible object is
     *     {@link SourceLocators }
     *     
     */
    public SourceLocators getSourceLocators() {
        return sourceLocators;
    }

    /**
     * Define el valor de la propiedad sourceLocators.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceLocators }
     *     
     */
    public void setSourceLocators(SourceLocators value) {
        this.sourceLocators = value;
    }

    /**
     * Obtiene el valor de la propiedad destinationLocators.
     * 
     * @return
     *     possible object is
     *     {@link DestinationLocators }
     *     
     */
    public DestinationLocators getDestinationLocators() {
        return destinationLocators;
    }

    /**
     * Define el valor de la propiedad destinationLocators.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinationLocators }
     *     
     */
    public void setDestinationLocators(DestinationLocators value) {
        this.destinationLocators = value;
    }

    /**
     * Obtiene el valor de la propiedad archiveMetadatas.
     * 
     * @return
     *     possible object is
     *     {@link ArchiveMetadatas }
     *     
     */
    public ArchiveMetadatas getArchiveMetadatas() {
        return archiveMetadatas;
    }

    /**
     * Define el valor de la propiedad archiveMetadatas.
     * 
     * @param value
     *     allowed object is
     *     {@link ArchiveMetadatas }
     *     
     */
    public void setArchiveMetadatas(ArchiveMetadatas value) {
        this.archiveMetadatas = value;
    }

}
