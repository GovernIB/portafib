
package es.indra.portafirmasws.cws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para UploadStep complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="UploadStep">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.indra.es/portafirmasws/cws}Step">
 *       &lt;sequence>
 *         &lt;element name="minimal-signers" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="signers" type="{http://www.indra.es/portafirmasws/cws}Signers"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UploadStep", propOrder = {
    "minimalSigners",
    "signers"
})
public class UploadStep
    extends Step
{

    @XmlElement(name = "minimal-signers")
    protected Integer minimalSigners;
    @XmlElement(required = true)
    protected Signers signers;

    /**
     * Obtiene el valor de la propiedad minimalSigners.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinimalSigners() {
        return minimalSigners;
    }

    /**
     * Define el valor de la propiedad minimalSigners.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinimalSigners(Integer value) {
        this.minimalSigners = value;
    }

    /**
     * Obtiene el valor de la propiedad signers.
     * 
     * @return
     *     possible object is
     *     {@link Signers }
     *     
     */
    public Signers getSigners() {
        return signers;
    }

    /**
     * Define el valor de la propiedad signers.
     * 
     * @param value
     *     allowed object is
     *     {@link Signers }
     *     
     */
    public void setSigners(Signers value) {
        this.signers = value;
    }

}
