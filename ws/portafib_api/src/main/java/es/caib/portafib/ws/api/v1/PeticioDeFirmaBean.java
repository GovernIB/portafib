
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
 * <p>Clase Java para peticioDeFirmaBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="peticioDeFirmaBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="algorismeDeFirmaID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="avisWeb" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="custodiaInfoID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="dataCaducitat" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataFinal" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataSolicitud" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="descripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descripcioTipusDocument" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fitxerAFirmar" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/&gt;
 *         &lt;element name="fitxerAFirmarID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="fitxerAdaptat" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/&gt;
 *         &lt;element name="fitxerAdaptatID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="fluxDeFirmesID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="idiomaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="informacioAdicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="logoSegell" type="{http://impl.v1.ws.portafib.caib.es/}fitxerBean" minOccurs="0"/&gt;
 *         &lt;element name="logoSegellID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="modeDeFirma" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="motiu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="motiuDeRebuig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="peticioDeFirmaID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="posicioTaulaFirmesID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="prioritatID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="remitentDescripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="remitentNom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="segellatDeTemps" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="tipusDocumentID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="tipusEstatPeticioDeFirmaID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="tipusFirmaID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="titol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="usuariAplicacioID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="usuariEntitatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
    "segellatDeTemps",
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

    protected int algorismeDeFirmaID;
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
    protected boolean segellatDeTemps;
    protected long tipusDocumentID;
    protected int tipusEstatPeticioDeFirmaID;
    protected int tipusFirmaID;
    protected String titol;
    protected String usuariAplicacioID;
    protected String usuariEntitatID;

    /**
     * Obtiene el valor de la propiedad algorismeDeFirmaID.
     * 
     */
    public int getAlgorismeDeFirmaID() {
        return algorismeDeFirmaID;
    }

    /**
     * Define el valor de la propiedad algorismeDeFirmaID.
     * 
     */
    public void setAlgorismeDeFirmaID(int value) {
        this.algorismeDeFirmaID = value;
    }

    /**
     * Obtiene el valor de la propiedad avisWeb.
     * 
     */
    public boolean isAvisWeb() {
        return avisWeb;
    }

    /**
     * Define el valor de la propiedad avisWeb.
     * 
     */
    public void setAvisWeb(boolean value) {
        this.avisWeb = value;
    }

    /**
     * Obtiene el valor de la propiedad custodiaInfoID.
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
     * Define el valor de la propiedad custodiaInfoID.
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
     * Obtiene el valor de la propiedad dataCaducitat.
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
     * Define el valor de la propiedad dataCaducitat.
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
     * Obtiene el valor de la propiedad dataFinal.
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
     * Define el valor de la propiedad dataFinal.
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
     * Obtiene el valor de la propiedad dataSolicitud.
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
     * Define el valor de la propiedad dataSolicitud.
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
     * Obtiene el valor de la propiedad descripcio.
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
     * Define el valor de la propiedad descripcio.
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
     * Obtiene el valor de la propiedad descripcioTipusDocument.
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
     * Define el valor de la propiedad descripcioTipusDocument.
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
     * Obtiene el valor de la propiedad fitxerAFirmar.
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
     * Define el valor de la propiedad fitxerAFirmar.
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
     * Obtiene el valor de la propiedad fitxerAFirmarID.
     * 
     */
    public long getFitxerAFirmarID() {
        return fitxerAFirmarID;
    }

    /**
     * Define el valor de la propiedad fitxerAFirmarID.
     * 
     */
    public void setFitxerAFirmarID(long value) {
        this.fitxerAFirmarID = value;
    }

    /**
     * Obtiene el valor de la propiedad fitxerAdaptat.
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
     * Define el valor de la propiedad fitxerAdaptat.
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
     * Obtiene el valor de la propiedad fitxerAdaptatID.
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
     * Define el valor de la propiedad fitxerAdaptatID.
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
     * Obtiene el valor de la propiedad fluxDeFirmesID.
     * 
     */
    public long getFluxDeFirmesID() {
        return fluxDeFirmesID;
    }

    /**
     * Define el valor de la propiedad fluxDeFirmesID.
     * 
     */
    public void setFluxDeFirmesID(long value) {
        this.fluxDeFirmesID = value;
    }

    /**
     * Obtiene el valor de la propiedad idiomaID.
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
     * Define el valor de la propiedad idiomaID.
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
     * Obtiene el valor de la propiedad informacioAdicional.
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
     * Define el valor de la propiedad informacioAdicional.
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
     * Obtiene el valor de la propiedad logoSegell.
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
     * Define el valor de la propiedad logoSegell.
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
     * Obtiene el valor de la propiedad logoSegellID.
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
     * Define el valor de la propiedad logoSegellID.
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
     * Obtiene el valor de la propiedad modeDeFirma.
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
     * Define el valor de la propiedad modeDeFirma.
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
     * Obtiene el valor de la propiedad motiu.
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
     * Define el valor de la propiedad motiu.
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
     * Obtiene el valor de la propiedad motiuDeRebuig.
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
     * Define el valor de la propiedad motiuDeRebuig.
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
     * Obtiene el valor de la propiedad peticioDeFirmaID.
     * 
     */
    public long getPeticioDeFirmaID() {
        return peticioDeFirmaID;
    }

    /**
     * Define el valor de la propiedad peticioDeFirmaID.
     * 
     */
    public void setPeticioDeFirmaID(long value) {
        this.peticioDeFirmaID = value;
    }

    /**
     * Obtiene el valor de la propiedad posicioTaulaFirmesID.
     * 
     */
    public int getPosicioTaulaFirmesID() {
        return posicioTaulaFirmesID;
    }

    /**
     * Define el valor de la propiedad posicioTaulaFirmesID.
     * 
     */
    public void setPosicioTaulaFirmesID(int value) {
        this.posicioTaulaFirmesID = value;
    }

    /**
     * Obtiene el valor de la propiedad prioritatID.
     * 
     */
    public int getPrioritatID() {
        return prioritatID;
    }

    /**
     * Define el valor de la propiedad prioritatID.
     * 
     */
    public void setPrioritatID(int value) {
        this.prioritatID = value;
    }

    /**
     * Obtiene el valor de la propiedad remitentDescripcio.
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
     * Define el valor de la propiedad remitentDescripcio.
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
     * Obtiene el valor de la propiedad remitentNom.
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
     * Define el valor de la propiedad remitentNom.
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
     * Obtiene el valor de la propiedad segellatDeTemps.
     * 
     */
    public boolean isSegellatDeTemps() {
        return segellatDeTemps;
    }

    /**
     * Define el valor de la propiedad segellatDeTemps.
     * 
     */
    public void setSegellatDeTemps(boolean value) {
        this.segellatDeTemps = value;
    }

    /**
     * Obtiene el valor de la propiedad tipusDocumentID.
     * 
     */
    public long getTipusDocumentID() {
        return tipusDocumentID;
    }

    /**
     * Define el valor de la propiedad tipusDocumentID.
     * 
     */
    public void setTipusDocumentID(long value) {
        this.tipusDocumentID = value;
    }

    /**
     * Obtiene el valor de la propiedad tipusEstatPeticioDeFirmaID.
     * 
     */
    public int getTipusEstatPeticioDeFirmaID() {
        return tipusEstatPeticioDeFirmaID;
    }

    /**
     * Define el valor de la propiedad tipusEstatPeticioDeFirmaID.
     * 
     */
    public void setTipusEstatPeticioDeFirmaID(int value) {
        this.tipusEstatPeticioDeFirmaID = value;
    }

    /**
     * Obtiene el valor de la propiedad tipusFirmaID.
     * 
     */
    public int getTipusFirmaID() {
        return tipusFirmaID;
    }

    /**
     * Define el valor de la propiedad tipusFirmaID.
     * 
     */
    public void setTipusFirmaID(int value) {
        this.tipusFirmaID = value;
    }

    /**
     * Obtiene el valor de la propiedad titol.
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
     * Define el valor de la propiedad titol.
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
     * Obtiene el valor de la propiedad usuariAplicacioID.
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
     * Define el valor de la propiedad usuariAplicacioID.
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
     * Obtiene el valor de la propiedad usuariEntitatID.
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
     * Define el valor de la propiedad usuariEntitatID.
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
