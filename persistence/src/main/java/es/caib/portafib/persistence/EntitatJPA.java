
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import org.hibernate.annotations.Type;
import java.util.HashSet;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity
@Table(name = "pfi_entitat" )
@SequenceGenerator(name="ENTITAT_SEQ", sequenceName="pfi_entitat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EntitatJPA implements Entitat {



private static final long serialVersionUID = 489209138L;

    @Id
    @Index(name="pfi_entitat_pk_i")
    @Column(name="entitatid",nullable = false,length = 50)
    java.lang.String entitatID;

    @Column(name="nom",nullable = false,length = 50)
    java.lang.String nom;

    @Column(name="descripcio",length = 255)
    java.lang.String descripcio;

    @Column(name="activa",nullable = false,length = 1)
    boolean activa;

  /** Pàgina web */
    @Column(name="web",nullable = false,length = 250)
    java.lang.String web;

    @Index(name="pfi_entitat_faviconid_fk_i")
    @Column(name="faviconid",nullable = false,length = 19)
    java.lang.Long faviconID;

    @Index(name="pfi_entitat_logowebid_fk_i")
    @Column(name="logowebid",nullable = false,length = 19)
    java.lang.Long logoWebID;

    @Index(name="pfi_entitat_logowebpeuid_fk_i")
    @Column(name="logowebpeuid",nullable = false,length = 19)
    java.lang.Long logoWebPeuID;

    @Index(name="pfi_entitat_logosegellid_fk_i")
    @Column(name="logosegellid",nullable = false,length = 19)
    java.lang.Long logoSegellID;

    @Column(name="adrezahtml",nullable = false,length = 2000)
    java.lang.String adrezaHtml;

    @Column(name="filtrecertificats",nullable = false,length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String filtreCertificats;

    @Index(name="pfi_entitat_pdfautoriid_fk_i")
    @Column(name="pdfautoritzaciodelegacioid",nullable = false,length = 19)
    java.lang.Long pdfAutoritzacioDelegacioID;

    @Column(name="suporttelefon",length = 50)
    java.lang.String suportTelefon;

    @Column(name="suportweb",length = 250)
    java.lang.String suportWeb;

    @Column(name="suportemail",length = 100)
    java.lang.String suportEmail;

  /** Si val null, llavors els usuaris d'aquesta entitat no poden fer peticions */
    @Index(name="pfi_entitat_usrappid_fk_i")
    @Column(name="usuariaplicacioid",length = 101)
    java.lang.String usuariAplicacioID;

    @Column(name="maxuploadsize",length = 19)
    java.lang.Long maxUploadSize;

    @Column(name="maxsizefitxeradaptat",length = 19)
    java.lang.Long maxSizeFitxerAdaptat;

    @Column(name="maxfilestosignatsametime",length = 10)
    java.lang.Integer maxFilesToSignAtSameTime;

  /** -1=> usar politica de firma de l'entitat, 0 => no usar politica de firma,  1=> usar politica d'aquesta configuracio, 2 => L'usuari web o usuari-app elegeixen la politica de firma */
    @Column(name="uspoliticadefirma",nullable = false,length = 10)
    int usPoliticaDeFirma;

  /** Identificador de la política de firma. Si es defineix aquest valors llavorses generaran 
firmes PAdES-EPES,CAdES-EPES y XAdES-EPES. */
    @Column(name="policyidentifier",length = 100)
    java.lang.String policyIdentifier;

  /** Valor en Base64. Huella digital de la política de firma. Es obligatorio indicar
este parámetro si se indicó también policyIdentifier. */
    @Column(name="policyidentifierhash",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String policyIdentifierHash;

  /** Indica el algoritmo utilizado para generar la Huella Digital definida en
el campo policyIdentifierHash.  Es obligatorio indicar este parámetro 
si se indicó también policyIdentifier. Los valores posibles son: 
SHA1, SHA-256, SHA-384 o SHA-512 */
    @Column(name="policyidentifierhashalgorithm",length = 50)
    java.lang.String policyIdentifierHashAlgorithm;

  /** URL (universalmente accesible) hacia el documento (normalmente PDF) que
contiene una descripción textual de la política de firma. Este parámetro es
opcional incluso cuando se genera una firma EPES. */
    @Column(name="policyurldocument",length = 255)
    java.lang.String policyUrlDocument;

    @Index(name="pfi_entitat_motiudele_fk_i")
    @Column(name="motiudelegacioid",length = 19)
    java.lang.Long motiuDelegacioID;

    @Index(name="pfi_entitat_firmatper_fk_i")
    @Column(name="firmatperformatid",length = 19)
    java.lang.Long firmatPerFormatID;

    @Index(name="pfi_entitat_algofirma_fk_i")
    @Column(name="algorismedefirmaid",nullable = false,length = 19)
    int algorismeDeFirmaID;

  /** 0: No permetre, 1:Només Plantilles de l'Entitat (No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat (Per defecte Actiu), 4: Opcional plantilla Entitat (Per defecte NO Actiu), 5: Llibertat Total (selecció, edició i us) */
    @Column(name="politicacustodia",nullable = false,length = 10)
    int politicaCustodia;

    @Index(name="pfi_entitat_custodiadef_fk_i")
    @Column(name="custodiainfoid",length = 19)
    java.lang.Long custodiaInfoID;

  /** 0 no es permet taules de firmes, 1 definit en l'entitat, 2 opcional per defecte el definit a l'entitat, 3 opcional per defecte sense taula de firmes */
    @Column(name="politicataulafirmes",nullable = false,length = 10)
    int politicaTaulaFirmes;

  /** SENSETAULA = 0; PRIMERAPAGINA = 1; DARRERAPAGINA = -1;DEFINIT_EN_FIRMA(RUBRICA)=2 */
    @Column(name="posiciotaulafirmes",nullable = false,length = 10)
    int posicioTaulaFirmes;

    @Column(name="propietatstaulafirmes",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String propietatsTaulaFirmes;

    @Column(name="segelldetempsviaweb",nullable = false,length = 10)
    int politicaSegellatDeTemps;

  /** Plugin de segellat de temps */
    @Index(name="pfi_entitat_segelltemps_fk_i")
    @Column(name="pluginid",length = 19)
    java.lang.Long pluginSegellTempsID;

    @Index(name="pfi_entitat_pluginrubri_fk_i")
    @Column(name="pluginrubricaid",length = 19)
    java.lang.Long pluginRubricaID;

    @Column(name="validarfirma",nullable = false,length = 1)
    boolean validarfirma;

    @Column(name="comprovarniffirma",nullable = false,length = 1)
    boolean comprovarNifFirma;

    @Column(name="checkcanviatdocfirmat",nullable = false,length = 1)
    boolean checkCanviatDocFirmat;

    @Index(name="pfi_entitat_pluginvalfir_fk_i")
    @Column(name="pluginvalidafirmesid",length = 19)
    java.lang.Long pluginValidaFirmesID;

    @Index(name="pfi_entitat_pluginvalcer_fk_i")
    @Column(name="pluginvalidacertificatid",length = 19)
    java.lang.Long pluginValidaCertificatID;



  /** Constructor Buit */
  public EntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public EntitatJPA(java.lang.String entitatID , java.lang.String nom , java.lang.String descripcio , boolean activa , java.lang.String web , java.lang.Long faviconID , java.lang.Long logoWebID , java.lang.Long logoWebPeuID , java.lang.Long logoSegellID , java.lang.String adrezaHtml , java.lang.String filtreCertificats , java.lang.Long pdfAutoritzacioDelegacioID , java.lang.String suportTelefon , java.lang.String suportWeb , java.lang.String suportEmail , java.lang.String usuariAplicacioID , java.lang.Long maxUploadSize , java.lang.Long maxSizeFitxerAdaptat , java.lang.Integer maxFilesToSignAtSameTime , int usPoliticaDeFirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , java.lang.Long motiuDelegacioID , java.lang.Long firmatPerFormatID , int algorismeDeFirmaID , int politicaCustodia , java.lang.Long custodiaInfoID , int politicaTaulaFirmes , int posicioTaulaFirmes , java.lang.String propietatsTaulaFirmes , int politicaSegellatDeTemps , java.lang.Long pluginSegellTempsID , java.lang.Long pluginRubricaID , boolean validarfirma , boolean comprovarNifFirma , boolean checkCanviatDocFirmat , java.lang.Long pluginValidaFirmesID , java.lang.Long pluginValidaCertificatID) {
    this.entitatID=entitatID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.activa=activa;
    this.web=web;
    this.faviconID=faviconID;
    this.logoWebID=logoWebID;
    this.logoWebPeuID=logoWebPeuID;
    this.logoSegellID=logoSegellID;
    this.adrezaHtml=adrezaHtml;
    this.filtreCertificats=filtreCertificats;
    this.pdfAutoritzacioDelegacioID=pdfAutoritzacioDelegacioID;
    this.suportTelefon=suportTelefon;
    this.suportWeb=suportWeb;
    this.suportEmail=suportEmail;
    this.usuariAplicacioID=usuariAplicacioID;
    this.maxUploadSize=maxUploadSize;
    this.maxSizeFitxerAdaptat=maxSizeFitxerAdaptat;
    this.maxFilesToSignAtSameTime=maxFilesToSignAtSameTime;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.motiuDelegacioID=motiuDelegacioID;
    this.firmatPerFormatID=firmatPerFormatID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.politicaCustodia=politicaCustodia;
    this.custodiaInfoID=custodiaInfoID;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmes=posicioTaulaFirmes;
    this.propietatsTaulaFirmes=propietatsTaulaFirmes;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
    this.pluginSegellTempsID=pluginSegellTempsID;
    this.pluginRubricaID=pluginRubricaID;
    this.validarfirma=validarfirma;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.pluginValidaFirmesID=pluginValidaFirmesID;
    this.pluginValidaCertificatID=pluginValidaCertificatID;
}
  /** Constructor dels valors Not Null */
  public EntitatJPA(java.lang.String entitatID , java.lang.String nom , boolean activa , java.lang.String web , java.lang.Long faviconID , java.lang.Long logoWebID , java.lang.Long logoWebPeuID , java.lang.Long logoSegellID , java.lang.String adrezaHtml , java.lang.String filtreCertificats , java.lang.Long pdfAutoritzacioDelegacioID , int usPoliticaDeFirma , int algorismeDeFirmaID , int politicaCustodia , int politicaTaulaFirmes , int posicioTaulaFirmes , int politicaSegellatDeTemps , boolean validarfirma , boolean comprovarNifFirma , boolean checkCanviatDocFirmat) {
    this.entitatID=entitatID;
    this.nom=nom;
    this.activa=activa;
    this.web=web;
    this.faviconID=faviconID;
    this.logoWebID=logoWebID;
    this.logoWebPeuID=logoWebPeuID;
    this.logoSegellID=logoSegellID;
    this.adrezaHtml=adrezaHtml;
    this.filtreCertificats=filtreCertificats;
    this.pdfAutoritzacioDelegacioID=pdfAutoritzacioDelegacioID;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.politicaCustodia=politicaCustodia;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmes=posicioTaulaFirmes;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
    this.validarfirma=validarfirma;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
}
  public EntitatJPA(Entitat __bean) {
    this.setEntitatID(__bean.getEntitatID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setActiva(__bean.isActiva());
    this.setWeb(__bean.getWeb());
    this.setFaviconID(__bean.getFaviconID());
    this.setLogoWebID(__bean.getLogoWebID());
    this.setLogoWebPeuID(__bean.getLogoWebPeuID());
    this.setLogoSegellID(__bean.getLogoSegellID());
    this.setAdrezaHtml(__bean.getAdrezaHtml());
    this.setFiltreCertificats(__bean.getFiltreCertificats());
    this.setPdfAutoritzacioDelegacioID(__bean.getPdfAutoritzacioDelegacioID());
    this.setSuportTelefon(__bean.getSuportTelefon());
    this.setSuportWeb(__bean.getSuportWeb());
    this.setSuportEmail(__bean.getSuportEmail());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setMaxUploadSize(__bean.getMaxUploadSize());
    this.setMaxSizeFitxerAdaptat(__bean.getMaxSizeFitxerAdaptat());
    this.setMaxFilesToSignAtSameTime(__bean.getMaxFilesToSignAtSameTime());
    this.setUsPoliticaDeFirma(__bean.getUsPoliticaDeFirma());
    this.setPolicyIdentifier(__bean.getPolicyIdentifier());
    this.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    this.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    this.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    this.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    this.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    this.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    this.setPoliticaCustodia(__bean.getPoliticaCustodia());
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    this.setPoliticaTaulaFirmes(__bean.getPoliticaTaulaFirmes());
    this.setPosicioTaulaFirmes(__bean.getPosicioTaulaFirmes());
    this.setPropietatsTaulaFirmes(__bean.getPropietatsTaulaFirmes());
    this.setPoliticaSegellatDeTemps(__bean.getPoliticaSegellatDeTemps());
    this.setPluginSegellTempsID(__bean.getPluginSegellTempsID());
    this.setPluginRubricaID(__bean.getPluginRubricaID());
    this.setValidarfirma(__bean.isValidarfirma());
    this.setComprovarNifFirma(__bean.isComprovarNifFirma());
    this.setCheckCanviatDocFirmat(__bean.isCheckCanviatDocFirmat());
    this.setPluginValidaFirmesID(__bean.getPluginValidaFirmesID());
    this.setPluginValidaCertificatID(__bean.getPluginValidaCertificatID());
    // Fitxer
    this.setFavicon(FitxerJPA.toJPA(__bean.getFavicon()));
    // Fitxer
    this.setLogoWeb(FitxerJPA.toJPA(__bean.getLogoWeb()));
    // Fitxer
    this.setLogoWebPeu(FitxerJPA.toJPA(__bean.getLogoWebPeu()));
    // Fitxer
    this.setLogoSegell(FitxerJPA.toJPA(__bean.getLogoSegell()));
    // Fitxer
    this.setPdfAutoritzacioDelegacio(FitxerJPA.toJPA(__bean.getPdfAutoritzacioDelegacio()));
	}

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public boolean isActiva() {
		return(activa);
	};
	public void setActiva(boolean _activa_) {
		this.activa = _activa_;
	};

	public java.lang.String getWeb() {
		return(web);
	};
	public void setWeb(java.lang.String _web_) {
		this.web = _web_;
	};

	public java.lang.Long getFaviconID() {
		return(faviconID);
	};
	public void setFaviconID(java.lang.Long _faviconID_) {
		this.faviconID = _faviconID_;
	};

	public java.lang.Long getLogoWebID() {
		return(logoWebID);
	};
	public void setLogoWebID(java.lang.Long _logoWebID_) {
		this.logoWebID = _logoWebID_;
	};

	public java.lang.Long getLogoWebPeuID() {
		return(logoWebPeuID);
	};
	public void setLogoWebPeuID(java.lang.Long _logoWebPeuID_) {
		this.logoWebPeuID = _logoWebPeuID_;
	};

	public java.lang.Long getLogoSegellID() {
		return(logoSegellID);
	};
	public void setLogoSegellID(java.lang.Long _logoSegellID_) {
		this.logoSegellID = _logoSegellID_;
	};

	public java.lang.String getAdrezaHtml() {
		return(adrezaHtml);
	};
	public void setAdrezaHtml(java.lang.String _adrezaHtml_) {
		this.adrezaHtml = _adrezaHtml_;
	};

	public java.lang.String getFiltreCertificats() {
		return(filtreCertificats);
	};
	public void setFiltreCertificats(java.lang.String _filtreCertificats_) {
		this.filtreCertificats = _filtreCertificats_;
	};

	public java.lang.Long getPdfAutoritzacioDelegacioID() {
		return(pdfAutoritzacioDelegacioID);
	};
	public void setPdfAutoritzacioDelegacioID(java.lang.Long _pdfAutoritzacioDelegacioID_) {
		this.pdfAutoritzacioDelegacioID = _pdfAutoritzacioDelegacioID_;
	};

	public java.lang.String getSuportTelefon() {
		return(suportTelefon);
	};
	public void setSuportTelefon(java.lang.String _suportTelefon_) {
		this.suportTelefon = _suportTelefon_;
	};

	public java.lang.String getSuportWeb() {
		return(suportWeb);
	};
	public void setSuportWeb(java.lang.String _suportWeb_) {
		this.suportWeb = _suportWeb_;
	};

	public java.lang.String getSuportEmail() {
		return(suportEmail);
	};
	public void setSuportEmail(java.lang.String _suportEmail_) {
		this.suportEmail = _suportEmail_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};

	public java.lang.Long getMaxUploadSize() {
		return(maxUploadSize);
	};
	public void setMaxUploadSize(java.lang.Long _maxUploadSize_) {
		this.maxUploadSize = _maxUploadSize_;
	};

	public java.lang.Long getMaxSizeFitxerAdaptat() {
		return(maxSizeFitxerAdaptat);
	};
	public void setMaxSizeFitxerAdaptat(java.lang.Long _maxSizeFitxerAdaptat_) {
		this.maxSizeFitxerAdaptat = _maxSizeFitxerAdaptat_;
	};

	public java.lang.Integer getMaxFilesToSignAtSameTime() {
		return(maxFilesToSignAtSameTime);
	};
	public void setMaxFilesToSignAtSameTime(java.lang.Integer _maxFilesToSignAtSameTime_) {
		this.maxFilesToSignAtSameTime = _maxFilesToSignAtSameTime_;
	};

	public int getUsPoliticaDeFirma() {
		return(usPoliticaDeFirma);
	};
	public void setUsPoliticaDeFirma(int _usPoliticaDeFirma_) {
		this.usPoliticaDeFirma = _usPoliticaDeFirma_;
	};

	public java.lang.String getPolicyIdentifier() {
		return(policyIdentifier);
	};
	public void setPolicyIdentifier(java.lang.String _policyIdentifier_) {
		this.policyIdentifier = _policyIdentifier_;
	};

	public java.lang.String getPolicyIdentifierHash() {
		return(policyIdentifierHash);
	};
	public void setPolicyIdentifierHash(java.lang.String _policyIdentifierHash_) {
		this.policyIdentifierHash = _policyIdentifierHash_;
	};

	public java.lang.String getPolicyIdentifierHashAlgorithm() {
		return(policyIdentifierHashAlgorithm);
	};
	public void setPolicyIdentifierHashAlgorithm(java.lang.String _policyIdentifierHashAlgorithm_) {
		this.policyIdentifierHashAlgorithm = _policyIdentifierHashAlgorithm_;
	};

	public java.lang.String getPolicyUrlDocument() {
		return(policyUrlDocument);
	};
	public void setPolicyUrlDocument(java.lang.String _policyUrlDocument_) {
		this.policyUrlDocument = _policyUrlDocument_;
	};

	public java.lang.Long getMotiuDelegacioID() {
		return(motiuDelegacioID);
	};
	public void setMotiuDelegacioID(java.lang.Long _motiuDelegacioID_) {
		this.motiuDelegacioID = _motiuDelegacioID_;
	};

	public java.lang.Long getFirmatPerFormatID() {
		return(firmatPerFormatID);
	};
	public void setFirmatPerFormatID(java.lang.Long _firmatPerFormatID_) {
		this.firmatPerFormatID = _firmatPerFormatID_;
	};

	public int getAlgorismeDeFirmaID() {
		return(algorismeDeFirmaID);
	};
	public void setAlgorismeDeFirmaID(int _algorismeDeFirmaID_) {
		this.algorismeDeFirmaID = _algorismeDeFirmaID_;
	};

	public int getPoliticaCustodia() {
		return(politicaCustodia);
	};
	public void setPoliticaCustodia(int _politicaCustodia_) {
		this.politicaCustodia = _politicaCustodia_;
	};

	public java.lang.Long getCustodiaInfoID() {
		return(custodiaInfoID);
	};
	public void setCustodiaInfoID(java.lang.Long _custodiaInfoID_) {
		this.custodiaInfoID = _custodiaInfoID_;
	};

	public int getPoliticaTaulaFirmes() {
		return(politicaTaulaFirmes);
	};
	public void setPoliticaTaulaFirmes(int _politicaTaulaFirmes_) {
		this.politicaTaulaFirmes = _politicaTaulaFirmes_;
	};

	public int getPosicioTaulaFirmes() {
		return(posicioTaulaFirmes);
	};
	public void setPosicioTaulaFirmes(int _posicioTaulaFirmes_) {
		this.posicioTaulaFirmes = _posicioTaulaFirmes_;
	};

	public java.lang.String getPropietatsTaulaFirmes() {
		return(propietatsTaulaFirmes);
	};
	public void setPropietatsTaulaFirmes(java.lang.String _propietatsTaulaFirmes_) {
		this.propietatsTaulaFirmes = _propietatsTaulaFirmes_;
	};

	public int getPoliticaSegellatDeTemps() {
		return(politicaSegellatDeTemps);
	};
	public void setPoliticaSegellatDeTemps(int _politicaSegellatDeTemps_) {
		this.politicaSegellatDeTemps = _politicaSegellatDeTemps_;
	};

	public java.lang.Long getPluginSegellTempsID() {
		return(pluginSegellTempsID);
	};
	public void setPluginSegellTempsID(java.lang.Long _pluginSegellTempsID_) {
		this.pluginSegellTempsID = _pluginSegellTempsID_;
	};

	public java.lang.Long getPluginRubricaID() {
		return(pluginRubricaID);
	};
	public void setPluginRubricaID(java.lang.Long _pluginRubricaID_) {
		this.pluginRubricaID = _pluginRubricaID_;
	};

	public boolean isValidarfirma() {
		return(validarfirma);
	};
	public void setValidarfirma(boolean _validarfirma_) {
		this.validarfirma = _validarfirma_;
	};

	public boolean isComprovarNifFirma() {
		return(comprovarNifFirma);
	};
	public void setComprovarNifFirma(boolean _comprovarNifFirma_) {
		this.comprovarNifFirma = _comprovarNifFirma_;
	};

	public boolean isCheckCanviatDocFirmat() {
		return(checkCanviatDocFirmat);
	};
	public void setCheckCanviatDocFirmat(boolean _checkCanviatDocFirmat_) {
		this.checkCanviatDocFirmat = _checkCanviatDocFirmat_;
	};

	public java.lang.Long getPluginValidaFirmesID() {
		return(pluginValidaFirmesID);
	};
	public void setPluginValidaFirmesID(java.lang.Long _pluginValidaFirmesID_) {
		this.pluginValidaFirmesID = _pluginValidaFirmesID_;
	};

	public java.lang.Long getPluginValidaCertificatID() {
		return(pluginValidaCertificatID);
	};
	public void setPluginValidaCertificatID(java.lang.Long _pluginValidaCertificatID_) {
		this.pluginValidaCertificatID = _pluginValidaCertificatID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Entitat) {
      Entitat __instance = (Entitat)__obj;
      __result = true;
      if (this.getEntitatID() == null) {
        __result = __result && (__instance.getEntitatID() == null);
      } else {
        __result = __result && this.getEntitatID().equals(__instance.getEntitatID()) ;
      }

    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:entitatid | Table: pfi_custodiainfo | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<CustodiaInfoJPA> custodiaInfos = new HashSet<CustodiaInfoJPA>(0);
    public  Set<CustodiaInfoJPA> getCustodiaInfos() {
    return this.custodiaInfos;
  }

    public void setCustodiaInfos(Set<CustodiaInfoJPA> custodiaInfos) {
      this.custodiaInfos = custodiaInfos;
    }


// EXP  Field:entitatid | Table: pfi_estadistica | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<EstadisticaJPA> estadisticas = new HashSet<EstadisticaJPA>(0);
    public  Set<EstadisticaJPA> getEstadisticas() {
    return this.estadisticas;
  }

    public void setEstadisticas(Set<EstadisticaJPA> estadisticas) {
      this.estadisticas = estadisticas;
    }


// EXP  Field:entitatid | Table: pfi_grupentitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<GrupEntitatJPA> grupEntitats = new HashSet<GrupEntitatJPA>(0);
    public  Set<GrupEntitatJPA> getGrupEntitats() {
    return this.grupEntitats;
  }

    public void setGrupEntitats(Set<GrupEntitatJPA> grupEntitats) {
      this.grupEntitats = grupEntitats;
    }


// EXP  Field:entitatid | Table: pfi_plugin | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<PluginJPA> plugins = new HashSet<PluginJPA>(0);
    public  Set<PluginJPA> getPlugins() {
    return this.plugins;
  }

    public void setPlugins(Set<PluginJPA> plugins) {
      this.plugins = plugins;
    }


// EXP  Field:entitatid | Table: pfi_plugincridada | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<PluginCridadaJPA> pluginCridadas = new HashSet<PluginCridadaJPA>(0);
    public  Set<PluginCridadaJPA> getPluginCridadas() {
    return this.pluginCridadas;
  }

    public void setPluginCridadas(Set<PluginCridadaJPA> pluginCridadas) {
      this.pluginCridadas = pluginCridadas;
    }


// EXP  Field:entitatid | Table: pfi_propietatglobal | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<PropietatGlobalJPA> propietatGlobals = new HashSet<PropietatGlobalJPA>(0);
    public  Set<PropietatGlobalJPA> getPropietatGlobals() {
    return this.propietatGlobals;
  }

    public void setPropietatGlobals(Set<PropietatGlobalJPA> propietatGlobals) {
      this.propietatGlobals = propietatGlobals;
    }


// EXP  Field:entitatid | Table: pfi_usuariaplicacio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<UsuariAplicacioJPA> usuariAplicacios = new HashSet<UsuariAplicacioJPA>(0);
    public  Set<UsuariAplicacioJPA> getUsuariAplicacios() {
    return this.usuariAplicacios;
  }

    public void setUsuariAplicacios(Set<UsuariAplicacioJPA> usuariAplicacios) {
      this.usuariAplicacios = usuariAplicacios;
    }


// EXP  Field:entitatid | Table: pfi_usuariaplicacioconfig | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<UsuariAplicacioConfiguracioJPA> usuariAplicacioConfiguracios = new HashSet<UsuariAplicacioConfiguracioJPA>(0);
    public  Set<UsuariAplicacioConfiguracioJPA> getUsuariAplicacioConfiguracios() {
    return this.usuariAplicacioConfiguracios;
  }

    public void setUsuariAplicacioConfiguracios(Set<UsuariAplicacioConfiguracioJPA> usuariAplicacioConfiguracios) {
      this.usuariAplicacioConfiguracios = usuariAplicacioConfiguracios;
    }


// EXP  Field:entitatid | Table: pfi_usuarientitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<UsuariEntitatJPA> usuariEntitats = new HashSet<UsuariEntitatJPA>(0);
    public  Set<UsuariEntitatJPA> getUsuariEntitats() {
    return this.usuariEntitats;
  }

    public void setUsuariEntitats(Set<UsuariEntitatJPA> usuariEntitats) {
      this.usuariEntitats = usuariEntitats;
    }


// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @ForeignKey(name="pfi_entitat_fitxer_icon_fk")
    @JoinColumn(name = "faviconid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false)
    private FitxerJPA favicon;

    public FitxerJPA getFavicon() {
    return this.favicon;
  }

    public  void setFavicon(FitxerJPA favicon) {
    this.favicon = favicon;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @ForeignKey(name="pfi_entitat_fitxer_loca_fk")
    @JoinColumn(name = "logowebid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false)
    private FitxerJPA logoWeb;

    public FitxerJPA getLogoWeb() {
    return this.logoWeb;
  }

    public  void setLogoWeb(FitxerJPA logoWeb) {
    this.logoWeb = logoWeb;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @ForeignKey(name="pfi_entitat_fitxer_lope_fk")
    @JoinColumn(name = "logowebpeuid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false)
    private FitxerJPA logoWebPeu;

    public FitxerJPA getLogoWebPeu() {
    return this.logoWebPeu;
  }

    public  void setLogoWebPeu(FitxerJPA logoWebPeu) {
    this.logoWebPeu = logoWebPeu;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @ForeignKey(name="pfi_entitat_fitxer_lose_fk")
    @JoinColumn(name = "logosegellid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false)
    private FitxerJPA logoSegell;

    public FitxerJPA getLogoSegell() {
    return this.logoSegell;
  }

    public  void setLogoSegell(FitxerJPA logoSegell) {
    this.logoSegell = logoSegell;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @ForeignKey(name="pfi_entitat_fitxer_pdfd_fk")
    @JoinColumn(name = "pdfautoritzaciodelegacioid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false)
    private FitxerJPA pdfAutoritzacioDelegacio;

    public FitxerJPA getPdfAutoritzacioDelegacio() {
    return this.pdfAutoritzacioDelegacio;
  }

    public  void setPdfAutoritzacioDelegacio(FitxerJPA pdfAutoritzacioDelegacio) {
    this.pdfAutoritzacioDelegacio = pdfAutoritzacioDelegacio;
  }

// IMP Field:usuariaplicacioid | Table: pfi_usuariaplicacio | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_entitat_usrapp_fk")
    @JoinColumn(name = "usuariaplicacioid", referencedColumnName ="usuariAplicacioID", nullable = true, insertable=false, updatable=false)
    private UsuariAplicacioJPA usuariAplicacio;

    public UsuariAplicacioJPA getUsuariAplicacio() {
    return this.usuariAplicacio;
  }

    public  void setUsuariAplicacio(UsuariAplicacioJPA usuariAplicacio) {
    this.usuariAplicacio = usuariAplicacio;
  }

// IMP Field:traduccioid | Table: pfi_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @ForeignKey(name="pfi_entitat_traduccio_moti_fk")
    @JoinColumn(name = "motiudelegacioid", referencedColumnName ="traduccioID", nullable = true, insertable=false, updatable=false)
    private TraduccioJPA motiuDelegacio;

    public TraduccioJPA getMotiuDelegacio() {
    return this.motiuDelegacio;
  }

    public  void setMotiuDelegacio(TraduccioJPA motiuDelegacio) {
    this.motiuDelegacio = motiuDelegacio;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.portafib.persistence.TraduccioMapJPA> getMotiuDelegacioTraduccions() {
    return this.motiuDelegacio.getTraduccions();
  }

  public void setMotiuDelegacioTraduccions(java.util.Map<String, es.caib.portafib.persistence.TraduccioMapJPA> __traduccions__) {
    this.motiuDelegacio.setTraduccions(__traduccions__);
  }


// IMP Field:traduccioid | Table: pfi_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @ForeignKey(name="pfi_entitat_traduccio_firm_fk")
    @JoinColumn(name = "firmatperformatid", referencedColumnName ="traduccioID", nullable = true, insertable=false, updatable=false)
    private TraduccioJPA firmatPerFormat;

    public TraduccioJPA getFirmatPerFormat() {
    return this.firmatPerFormat;
  }

    public  void setFirmatPerFormat(TraduccioJPA firmatPerFormat) {
    this.firmatPerFormat = firmatPerFormat;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.portafib.persistence.TraduccioMapJPA> getFirmatPerFormatTraduccions() {
    return this.firmatPerFormat.getTraduccions();
  }

  public void setFirmatPerFormatTraduccions(java.util.Map<String, es.caib.portafib.persistence.TraduccioMapJPA> __traduccions__) {
    this.firmatPerFormat.setTraduccions(__traduccions__);
  }


// IMP Field:custodiainfoid | Table: pfi_custodiainfo | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_entitat_custodia_fk")
    @JoinColumn(name = "custodiainfoid", referencedColumnName ="custodiaInfoID", nullable = true, insertable=false, updatable=false)
    private CustodiaInfoJPA custodiaInfo;

    public CustodiaInfoJPA getCustodiaInfo() {
    return this.custodiaInfo;
  }

    public  void setCustodiaInfo(CustodiaInfoJPA custodiaInfo) {
    this.custodiaInfo = custodiaInfo;
  }

// IMP Field:pluginid | Table: pfi_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_entitat_plugin_fk")
    @JoinColumn(name = "pluginid", referencedColumnName ="pluginID", nullable = true, insertable=false, updatable=false)
    private PluginJPA pluginSegellTemps;

    public PluginJPA getPluginSegellTemps() {
    return this.pluginSegellTemps;
  }

    public  void setPluginSegellTemps(PluginJPA pluginSegellTemps) {
    this.pluginSegellTemps = pluginSegellTemps;
  }

// IMP Field:pluginid | Table: pfi_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_entitat_plugin_rubr_fk")
    @JoinColumn(name = "pluginrubricaid", referencedColumnName ="pluginID", nullable = true, insertable=false, updatable=false)
    private PluginJPA pluginRubrica;

    public PluginJPA getPluginRubrica() {
    return this.pluginRubrica;
  }

    public  void setPluginRubrica(PluginJPA pluginRubrica) {
    this.pluginRubrica = pluginRubrica;
  }

// IMP Field:pluginid | Table: pfi_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_entitat_plugin_vafi_fk")
    @JoinColumn(name = "pluginvalidafirmesid", referencedColumnName ="pluginID", nullable = true, insertable=false, updatable=false)
    private PluginJPA pluginValidaFirmes;

    public PluginJPA getPluginValidaFirmes() {
    return this.pluginValidaFirmes;
  }

    public  void setPluginValidaFirmes(PluginJPA pluginValidaFirmes) {
    this.pluginValidaFirmes = pluginValidaFirmes;
  }

// IMP Field:pluginid | Table: pfi_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_entitat_plugin_cert_fk")
    @JoinColumn(name = "pluginvalidacertificatid", referencedColumnName ="pluginID", nullable = true, insertable=false, updatable=false)
    private PluginJPA pluginValidaCertificat;

    public PluginJPA getPluginValidaCertificat() {
    return this.pluginValidaCertificat;
  }

    public  void setPluginValidaCertificat(PluginJPA pluginValidaCertificat) {
    this.pluginValidaCertificat = pluginValidaCertificat;
  }


 // ---------------  STATIC METHODS ------------------
  public static EntitatJPA toJPA(Entitat __bean) {
    if (__bean == null) { return null;}
    EntitatJPA __tmp = new EntitatJPA();
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setWeb(__bean.getWeb());
    __tmp.setFaviconID(__bean.getFaviconID());
    __tmp.setLogoWebID(__bean.getLogoWebID());
    __tmp.setLogoWebPeuID(__bean.getLogoWebPeuID());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    __tmp.setAdrezaHtml(__bean.getAdrezaHtml());
    __tmp.setFiltreCertificats(__bean.getFiltreCertificats());
    __tmp.setPdfAutoritzacioDelegacioID(__bean.getPdfAutoritzacioDelegacioID());
    __tmp.setSuportTelefon(__bean.getSuportTelefon());
    __tmp.setSuportWeb(__bean.getSuportWeb());
    __tmp.setSuportEmail(__bean.getSuportEmail());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setMaxUploadSize(__bean.getMaxUploadSize());
    __tmp.setMaxSizeFitxerAdaptat(__bean.getMaxSizeFitxerAdaptat());
    __tmp.setMaxFilesToSignAtSameTime(__bean.getMaxFilesToSignAtSameTime());
    __tmp.setUsPoliticaDeFirma(__bean.getUsPoliticaDeFirma());
    __tmp.setPolicyIdentifier(__bean.getPolicyIdentifier());
    __tmp.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    __tmp.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    __tmp.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    __tmp.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    __tmp.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setPoliticaCustodia(__bean.getPoliticaCustodia());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setPoliticaTaulaFirmes(__bean.getPoliticaTaulaFirmes());
    __tmp.setPosicioTaulaFirmes(__bean.getPosicioTaulaFirmes());
    __tmp.setPropietatsTaulaFirmes(__bean.getPropietatsTaulaFirmes());
    __tmp.setPoliticaSegellatDeTemps(__bean.getPoliticaSegellatDeTemps());
    __tmp.setPluginSegellTempsID(__bean.getPluginSegellTempsID());
    __tmp.setPluginRubricaID(__bean.getPluginRubricaID());
    __tmp.setValidarfirma(__bean.isValidarfirma());
    __tmp.setComprovarNifFirma(__bean.isComprovarNifFirma());
    __tmp.setCheckCanviatDocFirmat(__bean.isCheckCanviatDocFirmat());
    __tmp.setPluginValidaFirmesID(__bean.getPluginValidaFirmesID());
    __tmp.setPluginValidaCertificatID(__bean.getPluginValidaCertificatID());
    // Fitxer
    __tmp.setFavicon(FitxerJPA.toJPA(__bean.getFavicon()));
    // Fitxer
    __tmp.setLogoWeb(FitxerJPA.toJPA(__bean.getLogoWeb()));
    // Fitxer
    __tmp.setLogoWebPeu(FitxerJPA.toJPA(__bean.getLogoWebPeu()));
    // Fitxer
    __tmp.setLogoSegell(FitxerJPA.toJPA(__bean.getLogoSegell()));
    // Fitxer
    __tmp.setPdfAutoritzacioDelegacio(FitxerJPA.toJPA(__bean.getPdfAutoritzacioDelegacio()));
		return __tmp;
	}


  public static EntitatJPA copyJPA(EntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EntitatJPA> copyJPA(java.util.Set<EntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EntitatJPA> __tmpSet = (java.util.Set<EntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EntitatJPA copyJPA(EntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EntitatJPA __tmp = (EntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PluginCridadaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginCridadas) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginCridadas())) ) {
      __tmp.setPluginCridadas(PluginCridadaJPA.copyJPA(__jpa.getPluginCridadas(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"PropietatGlobalJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.propietatGlobals) || org.hibernate.Hibernate.isInitialized(__jpa.getPropietatGlobals())) ) {
      __tmp.setPropietatGlobals(PropietatGlobalJPA.copyJPA(__jpa.getPropietatGlobals(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"EstadisticaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.estadisticas) || org.hibernate.Hibernate.isInitialized(__jpa.getEstadisticas())) ) {
      __tmp.setEstadisticas(EstadisticaJPA.copyJPA(__jpa.getEstadisticas(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"UsuariAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacios())) ) {
      __tmp.setUsuariAplicacios(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacios(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacioConfiguracios) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacioConfiguracios())) ) {
      __tmp.setUsuariAplicacioConfiguracios(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getUsuariAplicacioConfiguracios(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitats())) ) {
      __tmp.setUsuariEntitats(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitats(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"CustodiaInfoJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfos) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfos())) ) {
      __tmp.setCustodiaInfos(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfos(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"GrupEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grupEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getGrupEntitats())) ) {
      __tmp.setGrupEntitats(GrupEntitatJPA.copyJPA(__jpa.getGrupEntitats(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugins) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugins())) ) {
      __tmp.setPlugins(PluginJPA.copyJPA(__jpa.getPlugins(), __alreadyCopied,"EntitatJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.firmatPerFormat) || org.hibernate.Hibernate.isInitialized(__jpa.getFirmatPerFormat()) ) ) {
      __tmp.setFirmatPerFormat(TraduccioJPA.copyJPA(__jpa.getFirmatPerFormat(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.motiuDelegacio) || org.hibernate.Hibernate.isInitialized(__jpa.getMotiuDelegacio()) ) ) {
      __tmp.setMotiuDelegacio(TraduccioJPA.copyJPA(__jpa.getMotiuDelegacio(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginSegellTemps) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginSegellTemps()) ) ) {
      __tmp.setPluginSegellTemps(PluginJPA.copyJPA(__jpa.getPluginSegellTemps(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"UsuariAplicacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacio) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacio()) ) ) {
      __tmp.setUsuariAplicacio(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacio(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginValidaCertificat) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginValidaCertificat()) ) ) {
      __tmp.setPluginValidaCertificat(PluginJPA.copyJPA(__jpa.getPluginValidaCertificat(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginRubrica) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginRubrica()) ) ) {
      __tmp.setPluginRubrica(PluginJPA.copyJPA(__jpa.getPluginRubrica(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginValidaFirmes) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginValidaFirmes()) ) ) {
      __tmp.setPluginValidaFirmes(PluginJPA.copyJPA(__jpa.getPluginValidaFirmes(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"CustodiaInfoJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfo) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfo()) ) ) {
      __tmp.setCustodiaInfo(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfo(), __alreadyCopied,"EntitatJPA"));
    }

    return __tmp;
  }




}