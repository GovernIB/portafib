
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
 * <p>Java class for peticioDeFirmaBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="peticioDeFirmaBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="algorismeDeFirmaID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="avisWeb" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="custodiaInfoID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="dataCaducitat" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFinal" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataSolicitud" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="descripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcioTipusDocument" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fitxerAFirmar" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/>
 *         &lt;element name="fitxerAFirmarID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fitxerAdaptat" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/>
 *         &lt;element name="fitxerAdaptatID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="fluxDeFirmesID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idiomaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="informacioAdicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logoSegell" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/>
 *         &lt;element name="logoSegellID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="modeDeFirma" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="motiu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motiuDeRebuig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="peticioDeFirmaID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="posicioTaulaFirmesID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="prioritatID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="remitentDescripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remitentNom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipusDocumentID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tipusEstatPeticioDeFirmaID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipusFirmaID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="titol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuariAplicacioID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuariEntitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "peticioDeFirmaBean", propOrder = {
    "algorismeDeFirmaID",
    "avisWeb",
    "custodiaInfoID",
    "dataCaducitat",
    "dataFinal",
    "dataSolicitud",
    "descripcio",
    "descripcioTipusDocument",
    "fitxerAFirmar",
    "fitxerAFirmarID",
    "fitxerAdaptat",
    "fitxerAdaptatID",
    "fluxDeFirmesID",
    "idiomaID",
    "informacioAdicional",
    "logoSegell",
    "logoSegellID",
    "modeDeFirma",
    "motiu",
    "motiuDeRebuig",
    "peticioDeFirmaID",
    "posicioTaulaFirmesID",
    "prioritatID",
    "remitentDescripcio",
    "remitentNom",
    "tipusDocumentID",
    "tipusEstatPeticioDeFirmaID",
    "tipusFirmaID",
    "titol",
    "usuariAplicacioID",
    "usuariEntitatID"
})
@XmlSeeAlso({
    PeticioDeFirmaWs.class
})
public class PeticioDeFirmaBean {

    protected long algorismeDeFirmaID;
    protected boolean avisWeb;
    protected Long custodiaInfoID;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Timestamp dataCaducitat;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Timestamp dataFinal;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Timestamp dataSolicitud;
    protected String descripcio;
    protected String descripcioTipusDocument;
    protected FitxerBean fitxerAFirmar;
    protected long fitxerAFirmarID;
    protected FitxerBean fitxerAdaptat;
    protected Long fitxerAdaptatID;
    protected long fluxDeFirmesID;
    protected String idiomaID;
    protected String informacioAdicional;
    protected FitxerBean logoSegell;
    protected Long logoSegellID;
    protected Boolean modeDeFirma;
    protected String motiu;
    protected String motiuDeRebuig;
    protected long peticioDeFirmaID;
    protected int posicioTaulaFirmesID;
    protected int prioritatID;
    protected String remitentDescripcio;
    protected String remitentNom;
    protected long tipusDocumentID;
    protected int tipusEstatPeticioDeFirmaID;
    protected int tipusFirmaID;
    protected String titol;
    protected String usuariAplicacioID;
    protected String usuariEntitatID;

    /**
     * Gets the value of the algorismeDeFirmaID property.
     * 
     */
    public long getAlgorismeDeFirmaID() {
        return algorismeDeFirmaID;
    }

    /**
     * Sets the value of the algorismeDeFirmaID property.
     * 
     */
    public void setAlgorismeDeFirmaID(long value) {
        this.algorismeDeFirmaID = value;
    }

    /**
     * Gets the value of the avisWeb property.
     * 
     */
    public boolean isAvisWeb() {
        return avisWeb;
    }

    /**
     * Sets the value of the avisWeb property.
     * 
     */
    public void setAvisWeb(boolean value) {
        this.avisWeb = value;
    }

    /**
     * Gets the value of the custodiaInfoID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCustodiaInfoID() {
        return custodiaInfoID;
    }

    /**
     * Sets the value of the custodiaInfoID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCustodiaInfoID(Long value) {
        this.custodiaInfoID = value;
    }

    /**
     * Gets the value of the dataCaducitat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Timestamp getDataCaducitat() {
        return dataCaducitat;
    }

    /**
     * Sets the value of the dataCaducitat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataCaducitat(Timestamp value) {
        this.dataCaducitat = value;
    }

    /**
     * Gets the value of the dataFinal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Timestamp getDataFinal() {
        return dataFinal;
    }

    /**
     * Sets the value of the dataFinal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFinal(Timestamp value) {
        this.dataFinal = value;
    }

    /**
     * Gets the value of the dataSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Timestamp getDataSolicitud() {
        return dataSolicitud;
    }

    /**
     * Sets the value of the dataSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSolicitud(Timestamp value) {
        this.dataSolicitud = value;
    }

    /**
     * Gets the value of the descripcio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Sets the value of the descripcio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcio(String value) {
        this.descripcio = value;
    }

    /**
     * Gets the value of the descripcioTipusDocument property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcioTipusDocument() {
        return descripcioTipusDocument;
    }

    /**
     * Sets the value of the descripcioTipusDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcioTipusDocument(String value) {
        this.descripcioTipusDocument = value;
    }

    /**
     * Gets the value of the fitxerAFirmar property.
     * 
     * @return
     *     possible object is
     *     {@link FitxerBean }
     *     
     */
    public FitxerBean getFitxerAFirmar() {
        return fitxerAFirmar;
    }

    /**
     * Sets the value of the fitxerAFirmar property.
     * 
     * @param value
     *     allowed object is
     *     {@link FitxerBean }
     *     
     */
    public void setFitxerAFirmar(FitxerBean value) {
        this.fitxerAFirmar = value;
    }

    /**
     * Gets the value of the fitxerAFirmarID property.
     * 
     */
    public long getFitxerAFirmarID() {
        return fitxerAFirmarID;
    }

    /**
     * Sets the value of the fitxerAFirmarID property.
     * 
     */
    public void setFitxerAFirmarID(long value) {
        this.fitxerAFirmarID = value;
    }

    /**
     * Gets the value of the fitxerAdaptat property.
     * 
     * @return
     *     possible object is
     *     {@link FitxerBean }
     *     
     */
    public FitxerBean getFitxerAdaptat() {
        return fitxerAdaptat;
    }

    /**
     * Sets the value of the fitxerAdaptat property.
     * 
     * @param value
     *     allowed object is
     *     {@link FitxerBean }
     *     
     */
    public void setFitxerAdaptat(FitxerBean value) {
        this.fitxerAdaptat = value;
    }

    /**
     * Gets the value of the fitxerAdaptatID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFitxerAdaptatID() {
        return fitxerAdaptatID;
    }

    /**
     * Sets the value of the fitxerAdaptatID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFitxerAdaptatID(Long value) {
        this.fitxerAdaptatID = value;
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
     * Gets the value of the idiomaID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdiomaID() {
        return idiomaID;
    }

    /**
     * Sets the value of the idiomaID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdiomaID(String value) {
        this.idiomaID = value;
    }

    /**
     * Gets the value of the informacioAdicional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInformacioAdicional() {
        return informacioAdicional;
    }

    /**
     * Sets the value of the informacioAdicional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInformacioAdicional(String value) {
        this.informacioAdicional = value;
    }

    /**
     * Gets the value of the logoSegell property.
     * 
     * @return
     *     possible object is
     *     {@link FitxerBean }
     *     
     */
    public FitxerBean getLogoSegell() {
        return logoSegell;
    }

    /**
     * Sets the value of the logoSegell property.
     * 
     * @param value
     *     allowed object is
     *     {@link FitxerBean }
     *     
     */
    public void setLogoSegell(FitxerBean value) {
        this.logoSegell = value;
    }

    /**
     * Gets the value of the logoSegellID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLogoSegellID() {
        return logoSegellID;
    }

    /**
     * Sets the value of the logoSegellID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLogoSegellID(Long value) {
        this.logoSegellID = value;
    }

    /**
     * Gets the value of the modeDeFirma property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isModeDeFirma() {
        return modeDeFirma;
    }

    /**
     * Sets the value of the modeDeFirma property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setModeDeFirma(Boolean value) {
        this.modeDeFirma = value;
    }

    /**
     * Gets the value of the motiu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotiu() {
        return motiu;
    }

    /**
     * Sets the value of the motiu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotiu(String value) {
        this.motiu = value;
    }

    /**
     * Gets the value of the motiuDeRebuig property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotiuDeRebuig() {
        return motiuDeRebuig;
    }

    /**
     * Sets the value of the motiuDeRebuig property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotiuDeRebuig(String value) {
        this.motiuDeRebuig = value;
    }

    /**
     * Gets the value of the peticioDeFirmaID property.
     * 
     */
    public long getPeticioDeFirmaID() {
        return peticioDeFirmaID;
    }

    /**
     * Sets the value of the peticioDeFirmaID property.
     * 
     */
    public void setPeticioDeFirmaID(long value) {
        this.peticioDeFirmaID = value;
    }

    /**
     * Gets the value of the posicioTaulaFirmesID property.
     * 
     */
    public int getPosicioTaulaFirmesID() {
        return posicioTaulaFirmesID;
    }

    /**
     * Sets the value of the posicioTaulaFirmesID property.
     * 
     */
    public void setPosicioTaulaFirmesID(int value) {
        this.posicioTaulaFirmesID = value;
    }

    /**
     * Gets the value of the prioritatID property.
     * 
     */
    public int getPrioritatID() {
        return prioritatID;
    }

    /**
     * Sets the value of the prioritatID property.
     * 
     */
    public void setPrioritatID(int value) {
        this.prioritatID = value;
    }

    /**
     * Gets the value of the remitentDescripcio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemitentDescripcio() {
        return remitentDescripcio;
    }

    /**
     * Sets the value of the remitentDescripcio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemitentDescripcio(String value) {
        this.remitentDescripcio = value;
    }

    /**
     * Gets the value of the remitentNom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemitentNom() {
        return remitentNom;
    }

    /**
     * Sets the value of the remitentNom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemitentNom(String value) {
        this.remitentNom = value;
    }

    /**
     * Gets the value of the tipusDocumentID property.
     * 
     */
    public long getTipusDocumentID() {
        return tipusDocumentID;
    }

    /**
     * Sets the value of the tipusDocumentID property.
     * 
     */
    public void setTipusDocumentID(long value) {
        this.tipusDocumentID = value;
    }

    /**
     * Gets the value of the tipusEstatPeticioDeFirmaID property.
     * 
     */
    public int getTipusEstatPeticioDeFirmaID() {
        return tipusEstatPeticioDeFirmaID;
    }

    /**
     * Sets the value of the tipusEstatPeticioDeFirmaID property.
     * 
     */
    public void setTipusEstatPeticioDeFirmaID(int value) {
        this.tipusEstatPeticioDeFirmaID = value;
    }

    /**
     * Gets the value of the tipusFirmaID property.
     * 
     */
    public int getTipusFirmaID() {
        return tipusFirmaID;
    }

    /**
     * Sets the value of the tipusFirmaID property.
     * 
     */
    public void setTipusFirmaID(int value) {
        this.tipusFirmaID = value;
    }

    /**
     * Gets the value of the titol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitol() {
        return titol;
    }

    /**
     * Sets the value of the titol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitol(String value) {
        this.titol = value;
    }

    /**
     * Gets the value of the usuariAplicacioID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuariAplicacioID() {
        return usuariAplicacioID;
    }

    /**
     * Sets the value of the usuariAplicacioID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuariAplicacioID(String value) {
        this.usuariAplicacioID = value;
    }

    /**
     * Gets the value of the usuariEntitatID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuariEntitatID() {
        return usuariEntitatID;
    }

    /**
     * Sets the value of the usuariEntitatID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuariEntitatID(String value) {
        this.usuariEntitatID = value;
    }

}
