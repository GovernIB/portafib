
package es.caib.portafib.ws.api.v1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para firmaBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="firmaBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="blocDeFirmaID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="caixaAlt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="caixaAmple" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="caixaPagina" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="caixaX" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="caixaY" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="destinatariID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="emissorCertificat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="firmaID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="fitxerFirmat" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/&gt;
 *         &lt;element name="fitxerFirmatID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="mostrarRubrica" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="nomCertificat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numFirmaDocument" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroSerieCertificat" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="obligatori" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="tipusEstatDeFirmaFinalID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "firmaBean", propOrder = {
    "blocDeFirmaID",
    "caixaAlt",
    "caixaAmple",
    "caixaPagina",
    "caixaX",
    "caixaY",
    "destinatariID",
    "emissorCertificat",
    "firmaID",
    "fitxerFirmat",
    "fitxerFirmatID",
    "mostrarRubrica",
    "nomCertificat",
    "numFirmaDocument",
    "numeroSerieCertificat",
    "obligatori",
    "tipusEstatDeFirmaFinalID"
})
public class FirmaBean {

    protected long blocDeFirmaID;
    protected Integer caixaAlt;
    protected Integer caixaAmple;
    protected int caixaPagina;
    protected Integer caixaX;
    protected Integer caixaY;
    protected String destinatariID;
    protected String emissorCertificat;
    protected long firmaID;
    protected FitxerBean fitxerFirmat;
    protected Long fitxerFirmatID;
    protected boolean mostrarRubrica;
    protected String nomCertificat;
    protected Integer numFirmaDocument;
    protected BigInteger numeroSerieCertificat;
    protected boolean obligatori;
    protected Long tipusEstatDeFirmaFinalID;

    /**
     * Obtiene el valor de la propiedad blocDeFirmaID.
     * 
     */
    public long getBlocDeFirmaID() {
        return blocDeFirmaID;
    }

    /**
     * Define el valor de la propiedad blocDeFirmaID.
     * 
     */
    public void setBlocDeFirmaID(long value) {
        this.blocDeFirmaID = value;
    }

    /**
     * Obtiene el valor de la propiedad caixaAlt.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCaixaAlt() {
        return caixaAlt;
    }

    /**
     * Define el valor de la propiedad caixaAlt.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCaixaAlt(Integer value) {
        this.caixaAlt = value;
    }

    /**
     * Obtiene el valor de la propiedad caixaAmple.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCaixaAmple() {
        return caixaAmple;
    }

    /**
     * Define el valor de la propiedad caixaAmple.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCaixaAmple(Integer value) {
        this.caixaAmple = value;
    }

    /**
     * Obtiene el valor de la propiedad caixaPagina.
     * 
     */
    public int getCaixaPagina() {
        return caixaPagina;
    }

    /**
     * Define el valor de la propiedad caixaPagina.
     * 
     */
    public void setCaixaPagina(int value) {
        this.caixaPagina = value;
    }

    /**
     * Obtiene el valor de la propiedad caixaX.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCaixaX() {
        return caixaX;
    }

    /**
     * Define el valor de la propiedad caixaX.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCaixaX(Integer value) {
        this.caixaX = value;
    }

    /**
     * Obtiene el valor de la propiedad caixaY.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCaixaY() {
        return caixaY;
    }

    /**
     * Define el valor de la propiedad caixaY.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCaixaY(Integer value) {
        this.caixaY = value;
    }

    /**
     * Obtiene el valor de la propiedad destinatariID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatariID() {
        return destinatariID;
    }

    /**
     * Define el valor de la propiedad destinatariID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatariID(String value) {
        this.destinatariID = value;
    }

    /**
     * Obtiene el valor de la propiedad emissorCertificat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmissorCertificat() {
        return emissorCertificat;
    }

    /**
     * Define el valor de la propiedad emissorCertificat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmissorCertificat(String value) {
        this.emissorCertificat = value;
    }

    /**
     * Obtiene el valor de la propiedad firmaID.
     * 
     */
    public long getFirmaID() {
        return firmaID;
    }

    /**
     * Define el valor de la propiedad firmaID.
     * 
     */
    public void setFirmaID(long value) {
        this.firmaID = value;
    }

    /**
     * Obtiene el valor de la propiedad fitxerFirmat.
     * 
     * @return
     *     possible object is
     *     {@link FitxerBean }
     *     
     */
    public FitxerBean getFitxerFirmat() {
        return fitxerFirmat;
    }

    /**
     * Define el valor de la propiedad fitxerFirmat.
     * 
     * @param value
     *     allowed object is
     *     {@link FitxerBean }
     *     
     */
    public void setFitxerFirmat(FitxerBean value) {
        this.fitxerFirmat = value;
    }

    /**
     * Obtiene el valor de la propiedad fitxerFirmatID.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFitxerFirmatID() {
        return fitxerFirmatID;
    }

    /**
     * Define el valor de la propiedad fitxerFirmatID.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFitxerFirmatID(Long value) {
        this.fitxerFirmatID = value;
    }

    /**
     * Obtiene el valor de la propiedad mostrarRubrica.
     * 
     */
    public boolean isMostrarRubrica() {
        return mostrarRubrica;
    }

    /**
     * Define el valor de la propiedad mostrarRubrica.
     * 
     */
    public void setMostrarRubrica(boolean value) {
        this.mostrarRubrica = value;
    }

    /**
     * Obtiene el valor de la propiedad nomCertificat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomCertificat() {
        return nomCertificat;
    }

    /**
     * Define el valor de la propiedad nomCertificat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomCertificat(String value) {
        this.nomCertificat = value;
    }

    /**
     * Obtiene el valor de la propiedad numFirmaDocument.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumFirmaDocument() {
        return numFirmaDocument;
    }

    /**
     * Define el valor de la propiedad numFirmaDocument.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumFirmaDocument(Integer value) {
        this.numFirmaDocument = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroSerieCertificat.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroSerieCertificat() {
        return numeroSerieCertificat;
    }

    /**
     * Define el valor de la propiedad numeroSerieCertificat.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroSerieCertificat(BigInteger value) {
        this.numeroSerieCertificat = value;
    }

    /**
     * Obtiene el valor de la propiedad obligatori.
     * 
     */
    public boolean isObligatori() {
        return obligatori;
    }

    /**
     * Define el valor de la propiedad obligatori.
     * 
     */
    public void setObligatori(boolean value) {
        this.obligatori = value;
    }

    /**
     * Obtiene el valor de la propiedad tipusEstatDeFirmaFinalID.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipusEstatDeFirmaFinalID() {
        return tipusEstatDeFirmaFinalID;
    }

    /**
     * Define el valor de la propiedad tipusEstatDeFirmaFinalID.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipusEstatDeFirmaFinalID(Long value) {
        this.tipusEstatDeFirmaFinalID = value;
    }

}
