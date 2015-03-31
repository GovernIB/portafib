
package es.indra.portafirmasws.cws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ListStep complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ListStep">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.indra.es/portafirmasws/cws}Step">
 *       &lt;sequence>
 *         &lt;element name="minimal-signers" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="signers-action" type="{http://www.indra.es/portafirmasws/cws}Signers" minOccurs="0"/>
 *         &lt;element name="signers-reject" type="{http://www.indra.es/portafirmasws/cws}Signers" minOccurs="0"/>
 *         &lt;element name="signers-none" type="{http://www.indra.es/portafirmasws/cws}Signers" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListStep", propOrder = {
    "minimalSigners",
    "signersAction",
    "signersReject",
    "signersNone"
})
public class ListStep
    extends Step
{

    @XmlElement(name = "minimal-signers")
    protected Integer minimalSigners;
    @XmlElement(name = "signers-action")
    protected Signers signersAction;
    @XmlElement(name = "signers-reject")
    protected Signers signersReject;
    @XmlElement(name = "signers-none")
    protected Signers signersNone;

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
     * Obtiene el valor de la propiedad signersAction.
     * 
     * @return
     *     possible object is
     *     {@link Signers }
     *     
     */
    public Signers getSignersAction() {
        return signersAction;
    }

    /**
     * Define el valor de la propiedad signersAction.
     * 
     * @param value
     *     allowed object is
     *     {@link Signers }
     *     
     */
    public void setSignersAction(Signers value) {
        this.signersAction = value;
    }

    /**
     * Obtiene el valor de la propiedad signersReject.
     * 
     * @return
     *     possible object is
     *     {@link Signers }
     *     
     */
    public Signers getSignersReject() {
        return signersReject;
    }

    /**
     * Define el valor de la propiedad signersReject.
     * 
     * @param value
     *     allowed object is
     *     {@link Signers }
     *     
     */
    public void setSignersReject(Signers value) {
        this.signersReject = value;
    }

    /**
     * Obtiene el valor de la propiedad signersNone.
     * 
     * @return
     *     possible object is
     *     {@link Signers }
     *     
     */
    public Signers getSignersNone() {
        return signersNone;
    }

    /**
     * Define el valor de la propiedad signersNone.
     * 
     * @param value
     *     allowed object is
     *     {@link Signers }
     *     
     */
    public void setSignersNone(Signers value) {
        this.signersNone = value;
    }

}
