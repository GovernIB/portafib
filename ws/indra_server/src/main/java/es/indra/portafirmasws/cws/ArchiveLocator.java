
package es.indra.portafirmasws.cws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArchiveLocator complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArchiveLocator">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="repository-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="archive-uri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="archive-version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repository-base" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folder-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="file-path-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signature-files-path-pattern" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visual-file-path-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retention-policy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signature-custody" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArchiveLocator", propOrder = {
    "repositoryId",
    "archiveUri",
    "archiveVersion",
    "repositoryBase",
    "folderName",
    "filePathName",
    "signatureFilesPathPattern",
    "visualFilePathName",
    "retentionPolicy",
    "signatureCustody"
})
public class ArchiveLocator {

    @XmlElement(name = "repository-id", required = true)
    protected String repositoryId;
    @XmlElement(name = "archive-uri")
    protected String archiveUri;
    @XmlElement(name = "archive-version")
    protected String archiveVersion;
    @XmlElement(name = "repository-base")
    protected String repositoryBase;
    @XmlElement(name = "folder-name")
    protected String folderName;
    @XmlElement(name = "file-path-name")
    protected String filePathName;
    @XmlElement(name = "signature-files-path-pattern")
    protected String signatureFilesPathPattern;
    @XmlElement(name = "visual-file-path-name")
    protected String visualFilePathName;
    @XmlElement(name = "retention-policy")
    protected String retentionPolicy;
    @XmlElement(name = "signature-custody")
    protected Boolean signatureCustody;

    /**
     * Obtiene el valor de la propiedad repositoryId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepositoryId() {
        return repositoryId;
    }

    /**
     * Define el valor de la propiedad repositoryId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepositoryId(String value) {
        this.repositoryId = value;
    }

    /**
     * Obtiene el valor de la propiedad archiveUri.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArchiveUri() {
        return archiveUri;
    }

    /**
     * Define el valor de la propiedad archiveUri.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArchiveUri(String value) {
        this.archiveUri = value;
    }

    /**
     * Obtiene el valor de la propiedad archiveVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArchiveVersion() {
        return archiveVersion;
    }

    /**
     * Define el valor de la propiedad archiveVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArchiveVersion(String value) {
        this.archiveVersion = value;
    }

    /**
     * Obtiene el valor de la propiedad repositoryBase.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepositoryBase() {
        return repositoryBase;
    }

    /**
     * Define el valor de la propiedad repositoryBase.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepositoryBase(String value) {
        this.repositoryBase = value;
    }

    /**
     * Obtiene el valor de la propiedad folderName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     * Define el valor de la propiedad folderName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolderName(String value) {
        this.folderName = value;
    }

    /**
     * Obtiene el valor de la propiedad filePathName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilePathName() {
        return filePathName;
    }

    /**
     * Define el valor de la propiedad filePathName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilePathName(String value) {
        this.filePathName = value;
    }

    /**
     * Obtiene el valor de la propiedad signatureFilesPathPattern.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureFilesPathPattern() {
        return signatureFilesPathPattern;
    }

    /**
     * Define el valor de la propiedad signatureFilesPathPattern.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureFilesPathPattern(String value) {
        this.signatureFilesPathPattern = value;
    }

    /**
     * Obtiene el valor de la propiedad visualFilePathName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisualFilePathName() {
        return visualFilePathName;
    }

    /**
     * Define el valor de la propiedad visualFilePathName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisualFilePathName(String value) {
        this.visualFilePathName = value;
    }

    /**
     * Obtiene el valor de la propiedad retentionPolicy.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetentionPolicy() {
        return retentionPolicy;
    }

    /**
     * Define el valor de la propiedad retentionPolicy.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetentionPolicy(String value) {
        this.retentionPolicy = value;
    }

    /**
     * Obtiene el valor de la propiedad signatureCustody.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSignatureCustody() {
        return signatureCustody;
    }

    /**
     * Define el valor de la propiedad signatureCustody.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSignatureCustody(Boolean value) {
        this.signatureCustody = value;
    }

}
