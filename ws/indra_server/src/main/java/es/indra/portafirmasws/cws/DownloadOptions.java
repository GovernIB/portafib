
package es.indra.portafirmasws.cws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DownloadOptions complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DownloadOptions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="download-type" type="{http://www.indra.es/portafirmasws/cws}ModeTypeEnum" minOccurs="0"/>
 *         &lt;element name="files" type="{http://www.indra.es/portafirmasws/cws}Files" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DownloadOptions", propOrder = {
    "downloadType",
    "files"
})
public class DownloadOptions {

    @XmlElement(name = "download-type")
    protected ModeTypeEnum downloadType;
    protected Files files;

    /**
     * Obtiene el valor de la propiedad downloadType.
     * 
     * @return
     *     possible object is
     *     {@link ModeTypeEnum }
     *     
     */
    public ModeTypeEnum getDownloadType() {
        return downloadType;
    }

    /**
     * Define el valor de la propiedad downloadType.
     * 
     * @param value
     *     allowed object is
     *     {@link ModeTypeEnum }
     *     
     */
    public void setDownloadType(ModeTypeEnum value) {
        this.downloadType = value;
    }

    /**
     * Obtiene el valor de la propiedad files.
     * 
     * @return
     *     possible object is
     *     {@link Files }
     *     
     */
    public Files getFiles() {
        return files;
    }

    /**
     * Define el valor de la propiedad files.
     * 
     * @param value
     *     allowed object is
     *     {@link Files }
     *     
     */
    public void setFiles(Files value) {
        this.files = value;
    }

}
