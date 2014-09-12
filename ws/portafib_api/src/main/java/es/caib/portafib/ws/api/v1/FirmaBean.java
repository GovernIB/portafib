
package es.caib.portafib.ws.api.v1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for firmaBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="firmaBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="blocDeFirmaID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="caixaAlt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="caixaAmple" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="caixaPagina" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="caixaX" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="caixaY" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="destinatariID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emissorCertificat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firmaID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fitxerFirmat" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/>
 *         &lt;element name="fitxerFirmatID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mostrarRubrica" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="nomCertificat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numFirmaDocument" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numeroSerieCertificat" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="obligatori" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tipusEstatDeFirmaFinalID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the blocDeFirmaID property.
     * 
     */
    public long getBlocDeFirmaID() {
        return blocDeFirmaID;
    }

    /**
     * Sets the value of the blocDeFirmaID property.
     * 
     */
    public void setBlocDeFirmaID(long value) {
        this.blocDeFirmaID = value;
    }

    /**
     * Gets the value of the caixaAlt property.
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
     * Sets the value of the caixaAlt property.
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
     * Gets the value of the caixaAmple property.
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
     * Sets the value of the caixaAmple property.
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
     * Gets the value of the caixaPagina property.
     * 
     */
    public int getCaixaPagina() {
        return caixaPagina;
    }

    /**
     * Sets the value of the caixaPagina property.
     * 
     */
    public void setCaixaPagina(int value) {
        this.caixaPagina = value;
    }

    /**
     * Gets the value of the caixaX property.
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
     * Sets the value of the caixaX property.
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
     * Gets the value of the caixaY property.
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
     * Sets the value of the caixaY property.
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
     * Gets the value of the destinatariID property.
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
     * Sets the value of the destinatariID property.
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
     * Gets the value of the emissorCertificat property.
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
     * Sets the value of the emissorCertificat property.
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
     * Gets the value of the firmaID property.
     * 
     */
    public long getFirmaID() {
        return firmaID;
    }

    /**
     * Sets the value of the firmaID property.
     * 
     */
    public void setFirmaID(long value) {
        this.firmaID = value;
    }

    /**
     * Gets the value of the fitxerFirmat property.
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
     * Sets the value of the fitxerFirmat property.
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
     * Gets the value of the fitxerFirmatID property.
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
     * Sets the value of the fitxerFirmatID property.
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
     * Gets the value of the mostrarRubrica property.
     * 
     */
    public boolean isMostrarRubrica() {
        return mostrarRubrica;
    }

    /**
     * Sets the value of the mostrarRubrica property.
     * 
     */
    public void setMostrarRubrica(boolean value) {
        this.mostrarRubrica = value;
    }

    /**
     * Gets the value of the nomCertificat property.
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
     * Sets the value of the nomCertificat property.
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
     * Gets the value of the numFirmaDocument property.
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
     * Sets the value of the numFirmaDocument property.
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
     * Gets the value of the numeroSerieCertificat property.
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
     * Sets the value of the numeroSerieCertificat property.
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
     * Gets the value of the obligatori property.
     * 
     */
    public boolean isObligatori() {
        return obligatori;
    }

    /**
     * Sets the value of the obligatori property.
     * 
     */
    public void setObligatori(boolean value) {
        this.obligatori = value;
    }

    /**
     * Gets the value of the tipusEstatDeFirmaFinalID property.
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
     * Sets the value of the tipusEstatDeFirmaFinalID property.
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
