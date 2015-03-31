
package es.indra.portafirmasws.cws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="application" type="{http://www.indra.es/portafirmasws/cws}Application"/>
 *         &lt;element name="documents" type="{http://www.indra.es/portafirmasws/cws}DeleteRequestDocuments" minOccurs="0"/>
 *         &lt;element name="search-criterias" type="{http://www.indra.es/portafirmasws/cws}SearchCriterias" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "application",
    "documents",
    "searchCriterias"
})
@XmlRootElement(name = "delete-request")
public class DeleteRequest {

    @XmlElement(required = true)
    protected Application application;
    protected DeleteRequestDocuments documents;
    @XmlElement(name = "search-criterias")
    protected SearchCriterias searchCriterias;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Obtiene el valor de la propiedad application.
     * 
     * @return
     *     possible object is
     *     {@link Application }
     *     
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Define el valor de la propiedad application.
     * 
     * @param value
     *     allowed object is
     *     {@link Application }
     *     
     */
    public void setApplication(Application value) {
        this.application = value;
    }

    /**
     * Obtiene el valor de la propiedad documents.
     * 
     * @return
     *     possible object is
     *     {@link DeleteRequestDocuments }
     *     
     */
    public DeleteRequestDocuments getDocuments() {
        return documents;
    }

    /**
     * Define el valor de la propiedad documents.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteRequestDocuments }
     *     
     */
    public void setDocuments(DeleteRequestDocuments value) {
        this.documents = value;
    }

    /**
     * Obtiene el valor de la propiedad searchCriterias.
     * 
     * @return
     *     possible object is
     *     {@link SearchCriterias }
     *     
     */
    public SearchCriterias getSearchCriterias() {
        return searchCriterias;
    }

    /**
     * Define el valor de la propiedad searchCriterias.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchCriterias }
     *     
     */
    public void setSearchCriterias(SearchCriterias value) {
        this.searchCriterias = value;
    }

    /**
     * Obtiene el valor de la propiedad version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Define el valor de la propiedad version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
