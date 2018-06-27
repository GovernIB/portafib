
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Lob;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_usuariaplicacioconfig" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariAplicacioConfiguracioJPA implements UsuariAplicacioConfiguracio {



private static final long serialVersionUID = 2088976150L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_usuariaplicacioconfig_pk_i")
	@Column(name="usuariaplicacioconfigid",nullable = false,length = 19)
	long usuariAplicacioConfigID;

	@Index(name="pfi_confapp_usuappid_fk_i")
	@Column(name="usuariaplicacioid",nullable = false,unique = true,length = 101)
	java.lang.String usuariAplicacioID;

  /** -1=> usar politica de firma de l'entitat, 0 => no usar politica de firma,  1=> usar politica d'aquesta configuracio, 2 => L'usuari web o usuari-app elegeixen la politica de firma */
	@Column(name="uspoliticadefirma",nullable = false,length = 10)
	int usPoliticaDeFirma;

  /** Identificador de la política de firma. Si es defineix aquest valors llavorses generaran 
firmes PAdES-EPES,CAdES-EPES y XAdES-EPES. */
	@Column(name="policyidentifier",length = 100)
	java.lang.String policyIdentifier;

	@Column(name="policyidentifierhash",length = 256)
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

	@Column(name="filtrecertificats",length = 2147483647)
  @Lob
	java.lang.String filtreCertificats;

  /** 0 firma, 1 contrafirma 2, cofirma */
	@Column(name="tipusoperaciofirma",nullable = false,length = 10)
	int tipusOperacioFirma;

	@Index(name="pfi_confapp_tipusfirma_fk_i")
	@Column(name="tipusfirmaid",nullable = false,length = 10)
	int tipusFirmaID;

	@Index(name="pfi_confapp_algofirma_fk_i")
	@Column(name="algorismedefirmaid",length = 10)
	java.lang.Integer algorismeDeFirmaID;

	@Column(name="modedefirma",nullable = false,length = 1)
	boolean modeDeFirma;

  /** -1: el que digui l'entitat, 0: No permetre, 1: Només Plantilles de l''Entitat (No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat, 4: Opcional plantilla Entitat, 5: Llibertat Total (selecció, edició i us), 6: Custòdia de la Configuració de usuariAplicacio */
	@Column(name="politicacustodia",nullable = false,length = 10)
	int politicaCustodia;

	@Index(name="pfi_confapp_custinfo_fk_i")
	@Column(name="custodiainfoid",length = 19)
	java.lang.Long custodiaInfoID;

  /** -1 definit en l'entitat, 0 no es permet taules de firmes, 1  obligatori politica definida en la configuració d'usuari aplicació o entitat, 2 opcional, per defecte el definit a l'entitat */
	@Column(name="politicataulafirmes",nullable = false,length = 10)
	int politicaTaulaFirmes;

  /** Si val null s'utilitza la info de l'entitat. Valors: SENSETAULA = 0; PRIMERAPAGINA = 1; DARRERAPAGINA = -1;DEFINIT_EN_FIRMA(RUBRICA)=2 */
	@Index(name="pfi_confapp_postaula_fk_i")
	@Column(name="posiciotaulafirmesid",nullable = false,length = 10)
	int posicioTaulaFirmesID;

	@Index(name="pfi_confapp_firmatper_fk_i")
	@Column(name="firmatperformatid",length = 19)
	java.lang.Long firmatPerFormatID;

	@Index(name="pfi_confapp_motiudele_fk_i")
	@Column(name="motiudelegacioid",length = 19)
	java.lang.Long motiuDelegacioID;

	@Column(name="propietatstaulafirmes",length = 2147483647)
  @Lob
	java.lang.String propietatsTaulaFirmes;

  /** DEFINIT_EN_ENTITAT=-1;NOUSAR=0;US_OBLIGATORI=1;USUARI_ELEGEIX_PER_DEFECTE_SI=2;USUARI_ELEGEIX_PER_DEFECTE_NO=3; */
	@Column(name="politicasegellatdetemps",nullable = false,length = 10)
	int politicaSegellatDeTemps;

	@Index(name="pfi_confapp_plugsegell_fk_i")
	@Column(name="pluginsegellatid",length = 19)
	java.lang.Long pluginSegellatID;

	@Column(name="htmlperllistarpluginsfirmaweb",length = 2147483647)
  @Lob
	java.lang.String htmlPerLlistarPluginsFirmaWeb;

	@Index(name="pfi_confapp_firmaserv_fk_i")
	@Column(name="pluginfirmaservidorid",length = 19)
	java.lang.Long pluginFirmaServidorID;

	@Column(name="maxfirmesenservidor",length = 10)
	java.lang.Integer maxFirmesEnServidor;

	@Column(name="upgradesignformat",length = 10)
	java.lang.Integer upgradeSignFormat;

	@Index(name="pfi_confapp_logincert_fk_i")
	@Column(name="logincertificateid",length = 19)
	java.lang.Long loginCertificateID;

  /** Null => Valor definit a l'entitat */
	@Column(name="comprovarniffirma",length = 1)
	java.lang.Boolean comprovarNifFirma;

  /** -- Null => Valor definit a l'entitat */
	@Column(name="checkcanviatdocfirmat",length = 1)
	java.lang.Boolean checkCanviatDocFirmat;

  /** Indica si validar la firma amb el Plugin de validació definit a l'entitat */
	@Column(name="validarfirma",length = 1)
	java.lang.Boolean validarFirma;

  /** NULL => Lo que digui l'entitat */
	@Column(name="validarcertificat",length = 1)
	java.lang.Boolean validarCertificat;



  /** Constructor Buit */
  public UsuariAplicacioConfiguracioJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariAplicacioConfiguracioJPA(long usuariAplicacioConfigID , java.lang.String usuariAplicacioID , int usPoliticaDeFirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , java.lang.String filtreCertificats , int tipusOperacioFirma , int tipusFirmaID , java.lang.Integer algorismeDeFirmaID , boolean modeDeFirma , int politicaCustodia , java.lang.Long custodiaInfoID , int politicaTaulaFirmes , int posicioTaulaFirmesID , java.lang.Long firmatPerFormatID , java.lang.Long motiuDelegacioID , java.lang.String propietatsTaulaFirmes , int politicaSegellatDeTemps , java.lang.Long pluginSegellatID , java.lang.String htmlPerLlistarPluginsFirmaWeb , java.lang.Long pluginFirmaServidorID , java.lang.Integer maxFirmesEnServidor , java.lang.Integer upgradeSignFormat , java.lang.Long loginCertificateID , java.lang.Boolean comprovarNifFirma , java.lang.Boolean checkCanviatDocFirmat , java.lang.Boolean validarFirma , java.lang.Boolean validarCertificat) {
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.filtreCertificats=filtreCertificats;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.politicaCustodia=politicaCustodia;
    this.custodiaInfoID=custodiaInfoID;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.firmatPerFormatID=firmatPerFormatID;
    this.motiuDelegacioID=motiuDelegacioID;
    this.propietatsTaulaFirmes=propietatsTaulaFirmes;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
    this.pluginSegellatID=pluginSegellatID;
    this.htmlPerLlistarPluginsFirmaWeb=htmlPerLlistarPluginsFirmaWeb;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.maxFirmesEnServidor=maxFirmesEnServidor;
    this.upgradeSignFormat=upgradeSignFormat;
    this.loginCertificateID=loginCertificateID;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.validarFirma=validarFirma;
    this.validarCertificat=validarCertificat;
}
  /** Constructor sense valors autoincrementals */
  public UsuariAplicacioConfiguracioJPA(java.lang.String usuariAplicacioID , int usPoliticaDeFirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , java.lang.String filtreCertificats , int tipusOperacioFirma , int tipusFirmaID , java.lang.Integer algorismeDeFirmaID , boolean modeDeFirma , int politicaCustodia , java.lang.Long custodiaInfoID , int politicaTaulaFirmes , int posicioTaulaFirmesID , java.lang.Long firmatPerFormatID , java.lang.Long motiuDelegacioID , java.lang.String propietatsTaulaFirmes , int politicaSegellatDeTemps , java.lang.Long pluginSegellatID , java.lang.String htmlPerLlistarPluginsFirmaWeb , java.lang.Long pluginFirmaServidorID , java.lang.Integer maxFirmesEnServidor , java.lang.Integer upgradeSignFormat , java.lang.Long loginCertificateID , java.lang.Boolean comprovarNifFirma , java.lang.Boolean checkCanviatDocFirmat , java.lang.Boolean validarFirma , java.lang.Boolean validarCertificat) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.filtreCertificats=filtreCertificats;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.politicaCustodia=politicaCustodia;
    this.custodiaInfoID=custodiaInfoID;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.firmatPerFormatID=firmatPerFormatID;
    this.motiuDelegacioID=motiuDelegacioID;
    this.propietatsTaulaFirmes=propietatsTaulaFirmes;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
    this.pluginSegellatID=pluginSegellatID;
    this.htmlPerLlistarPluginsFirmaWeb=htmlPerLlistarPluginsFirmaWeb;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.maxFirmesEnServidor=maxFirmesEnServidor;
    this.upgradeSignFormat=upgradeSignFormat;
    this.loginCertificateID=loginCertificateID;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.validarFirma=validarFirma;
    this.validarCertificat=validarCertificat;
}
  /** Constructor dels valors Not Null */
  public UsuariAplicacioConfiguracioJPA(long usuariAplicacioConfigID , java.lang.String usuariAplicacioID , int usPoliticaDeFirma , int tipusOperacioFirma , int tipusFirmaID , boolean modeDeFirma , int politicaCustodia , int politicaTaulaFirmes , int posicioTaulaFirmesID , int politicaSegellatDeTemps) {
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.politicaCustodia=politicaCustodia;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
}
  public UsuariAplicacioConfiguracioJPA(UsuariAplicacioConfiguracio __bean) {
    this.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setUsPoliticaDeFirma(__bean.getUsPoliticaDeFirma());
    this.setPolicyIdentifier(__bean.getPolicyIdentifier());
    this.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    this.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    this.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    this.setFiltreCertificats(__bean.getFiltreCertificats());
    this.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    this.setTipusFirmaID(__bean.getTipusFirmaID());
    this.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    this.setModeDeFirma(__bean.isModeDeFirma());
    this.setPoliticaCustodia(__bean.getPoliticaCustodia());
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    this.setPoliticaTaulaFirmes(__bean.getPoliticaTaulaFirmes());
    this.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    this.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    this.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    this.setPropietatsTaulaFirmes(__bean.getPropietatsTaulaFirmes());
    this.setPoliticaSegellatDeTemps(__bean.getPoliticaSegellatDeTemps());
    this.setPluginSegellatID(__bean.getPluginSegellatID());
    this.setHtmlPerLlistarPluginsFirmaWeb(__bean.getHtmlPerLlistarPluginsFirmaWeb());
    this.setPluginFirmaServidorID(__bean.getPluginFirmaServidorID());
    this.setMaxFirmesEnServidor(__bean.getMaxFirmesEnServidor());
    this.setUpgradeSignFormat(__bean.getUpgradeSignFormat());
    this.setLoginCertificateID(__bean.getLoginCertificateID());
    this.setComprovarNifFirma(__bean.getComprovarNifFirma());
    this.setCheckCanviatDocFirmat(__bean.getCheckCanviatDocFirmat());
    this.setValidarFirma(__bean.getValidarFirma());
    this.setValidarCertificat(__bean.getValidarCertificat());
    // Fitxer
    this.setLoginCertificate(FitxerJPA.toJPA(__bean.getLoginCertificate()));
	}

	public long getUsuariAplicacioConfigID() {
		return(usuariAplicacioConfigID);
	};
	public void setUsuariAplicacioConfigID(long _usuariAplicacioConfigID_) {
		this.usuariAplicacioConfigID = _usuariAplicacioConfigID_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
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

	public java.lang.String getFiltreCertificats() {
		return(filtreCertificats);
	};
	public void setFiltreCertificats(java.lang.String _filtreCertificats_) {
		this.filtreCertificats = _filtreCertificats_;
	};

	public int getTipusOperacioFirma() {
		return(tipusOperacioFirma);
	};
	public void setTipusOperacioFirma(int _tipusOperacioFirma_) {
		this.tipusOperacioFirma = _tipusOperacioFirma_;
	};

	public int getTipusFirmaID() {
		return(tipusFirmaID);
	};
	public void setTipusFirmaID(int _tipusFirmaID_) {
		this.tipusFirmaID = _tipusFirmaID_;
	};

	public java.lang.Integer getAlgorismeDeFirmaID() {
		return(algorismeDeFirmaID);
	};
	public void setAlgorismeDeFirmaID(java.lang.Integer _algorismeDeFirmaID_) {
		this.algorismeDeFirmaID = _algorismeDeFirmaID_;
	};

	public boolean isModeDeFirma() {
		return(modeDeFirma);
	};
	public void setModeDeFirma(boolean _modeDeFirma_) {
		this.modeDeFirma = _modeDeFirma_;
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

	public int getPosicioTaulaFirmesID() {
		return(posicioTaulaFirmesID);
	};
	public void setPosicioTaulaFirmesID(int _posicioTaulaFirmesID_) {
		this.posicioTaulaFirmesID = _posicioTaulaFirmesID_;
	};

	public java.lang.Long getFirmatPerFormatID() {
		return(firmatPerFormatID);
	};
	public void setFirmatPerFormatID(java.lang.Long _firmatPerFormatID_) {
		this.firmatPerFormatID = _firmatPerFormatID_;
	};

	public java.lang.Long getMotiuDelegacioID() {
		return(motiuDelegacioID);
	};
	public void setMotiuDelegacioID(java.lang.Long _motiuDelegacioID_) {
		this.motiuDelegacioID = _motiuDelegacioID_;
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

	public java.lang.Long getPluginSegellatID() {
		return(pluginSegellatID);
	};
	public void setPluginSegellatID(java.lang.Long _pluginSegellatID_) {
		this.pluginSegellatID = _pluginSegellatID_;
	};

	public java.lang.String getHtmlPerLlistarPluginsFirmaWeb() {
		return(htmlPerLlistarPluginsFirmaWeb);
	};
	public void setHtmlPerLlistarPluginsFirmaWeb(java.lang.String _htmlPerLlistarPluginsFirmaWeb_) {
		this.htmlPerLlistarPluginsFirmaWeb = _htmlPerLlistarPluginsFirmaWeb_;
	};

	public java.lang.Long getPluginFirmaServidorID() {
		return(pluginFirmaServidorID);
	};
	public void setPluginFirmaServidorID(java.lang.Long _pluginFirmaServidorID_) {
		this.pluginFirmaServidorID = _pluginFirmaServidorID_;
	};

	public java.lang.Integer getMaxFirmesEnServidor() {
		return(maxFirmesEnServidor);
	};
	public void setMaxFirmesEnServidor(java.lang.Integer _maxFirmesEnServidor_) {
		this.maxFirmesEnServidor = _maxFirmesEnServidor_;
	};

	public java.lang.Integer getUpgradeSignFormat() {
		return(upgradeSignFormat);
	};
	public void setUpgradeSignFormat(java.lang.Integer _upgradeSignFormat_) {
		this.upgradeSignFormat = _upgradeSignFormat_;
	};

	public java.lang.Long getLoginCertificateID() {
		return(loginCertificateID);
	};
	public void setLoginCertificateID(java.lang.Long _loginCertificateID_) {
		this.loginCertificateID = _loginCertificateID_;
	};

	public java.lang.Boolean getComprovarNifFirma() {
		return(comprovarNifFirma);
	};
	public void setComprovarNifFirma(java.lang.Boolean _comprovarNifFirma_) {
		this.comprovarNifFirma = _comprovarNifFirma_;
	};

	public java.lang.Boolean getCheckCanviatDocFirmat() {
		return(checkCanviatDocFirmat);
	};
	public void setCheckCanviatDocFirmat(java.lang.Boolean _checkCanviatDocFirmat_) {
		this.checkCanviatDocFirmat = _checkCanviatDocFirmat_;
	};

	public java.lang.Boolean getValidarFirma() {
		return(validarFirma);
	};
	public void setValidarFirma(java.lang.Boolean _validarFirma_) {
		this.validarFirma = _validarFirma_;
	};

	public java.lang.Boolean getValidarCertificat() {
		return(validarCertificat);
	};
	public void setValidarCertificat(java.lang.Boolean _validarCertificat_) {
		this.validarCertificat = _validarCertificat_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof UsuariAplicacioConfiguracio) {
      UsuariAplicacioConfiguracio __instance = (UsuariAplicacioConfiguracio)__obj;
      __result = true;
      __result = __result && (this.getUsuariAplicacioConfigID() == __instance.getUsuariAplicacioConfigID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:usuariaplicacioid | Table: pfi_usuariaplicacio | Type: 1  

	@OneToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_confapp_usrapp_fk")
	@JoinColumn(name = "usuariaplicacioid", nullable = false, insertable=false, updatable=false)
	private UsuariAplicacioJPA usuariAplicacio;

	public UsuariAplicacioJPA getUsuariAplicacio() {
    return this.usuariAplicacio;
  }

	public  void setUsuariAplicacio(UsuariAplicacioJPA usuariAplicacio) {
    this.usuariAplicacio = usuariAplicacio;
  }

// IMP Field:tipusfirmaid | Table: pfi_tipusfirma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_confapp_tipusfirma_fk")
	@JoinColumn(name = "tipusfirmaid", referencedColumnName ="tipusFirmaID", nullable = false, insertable=false, updatable=false)
	private TipusFirmaJPA tipusFirma;

	public TipusFirmaJPA getTipusFirma() {
    return this.tipusFirma;
  }

	public  void setTipusFirma(TipusFirmaJPA tipusFirma) {
    this.tipusFirma = tipusFirma;
  }

// IMP Field:algorismedefirmaid | Table: pfi_algorismedefirma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_confapp_algofirma_fk")
	@JoinColumn(name = "algorismedefirmaid", referencedColumnName ="algorismeDeFirmaID", nullable = true, insertable=false, updatable=false)
	private AlgorismeDeFirmaJPA algorismeDeFirma;

	public AlgorismeDeFirmaJPA getAlgorismeDeFirma() {
    return this.algorismeDeFirma;
  }

	public  void setAlgorismeDeFirma(AlgorismeDeFirmaJPA algorismeDeFirma) {
    this.algorismeDeFirma = algorismeDeFirma;
  }

// IMP Field:custodiainfoid | Table: pfi_custodiainfo | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_confapp_custodia_fk")
	@JoinColumn(name = "custodiainfoid", referencedColumnName ="custodiaInfoID", nullable = true, insertable=false, updatable=false)
	private CustodiaInfoJPA custodiaInfo;

	public CustodiaInfoJPA getCustodiaInfo() {
    return this.custodiaInfo;
  }

	public  void setCustodiaInfo(CustodiaInfoJPA custodiaInfo) {
    this.custodiaInfo = custodiaInfo;
  }

// IMP Field:traduccioid | Table: pfi_traduccio | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@ForeignKey(name="pfi_confapp_traduccio_firm_fk")
	@JoinColumn(name = "firmatperformatid", referencedColumnName ="traduccioID", nullable = true, insertable=false, updatable=false)
	private TraduccioJPA firmatPerFormat;

	public TraduccioJPA getFirmatPerFormat() {
    return this.firmatPerFormat;
  }

	public  void setFirmatPerFormat(TraduccioJPA firmatPerFormat) {
    this.firmatPerFormat = firmatPerFormat;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.portafib.jpa.TraduccioMapJPA> getFirmatPerFormatTraduccions() {
    return this.firmatPerFormat.getTraduccions();
  }

  public void setFirmatPerFormatTraduccions(java.util.Map<String, es.caib.portafib.jpa.TraduccioMapJPA> __traduccions__) {
    this.firmatPerFormat.setTraduccions(__traduccions__);
  }


// IMP Field:traduccioid | Table: pfi_traduccio | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@ForeignKey(name="pfi_confapp_traduccio_moti_fk")
	@JoinColumn(name = "motiudelegacioid", referencedColumnName ="traduccioID", nullable = true, insertable=false, updatable=false)
	private TraduccioJPA motiuDelegacio;

	public TraduccioJPA getMotiuDelegacio() {
    return this.motiuDelegacio;
  }

	public  void setMotiuDelegacio(TraduccioJPA motiuDelegacio) {
    this.motiuDelegacio = motiuDelegacio;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.portafib.jpa.TraduccioMapJPA> getMotiuDelegacioTraduccions() {
    return this.motiuDelegacio.getTraduccions();
  }

  public void setMotiuDelegacioTraduccions(java.util.Map<String, es.caib.portafib.jpa.TraduccioMapJPA> __traduccions__) {
    this.motiuDelegacio.setTraduccions(__traduccions__);
  }


// IMP Field:pluginid | Table: pfi_plugin | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_confapp_plugin_seg_fk")
	@JoinColumn(name = "pluginsegellatid", referencedColumnName ="pluginID", nullable = true, insertable=false, updatable=false)
	private PluginJPA pluginSegellat;

	public PluginJPA getPluginSegellat() {
    return this.pluginSegellat;
  }

	public  void setPluginSegellat(PluginJPA pluginSegellat) {
    this.pluginSegellat = pluginSegellat;
  }

// IMP Field:pluginid | Table: pfi_plugin | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_confapp_plugin_fsrv_fk")
	@JoinColumn(name = "pluginfirmaservidorid", referencedColumnName ="pluginID", nullable = true, insertable=false, updatable=false)
	private PluginJPA pluginFirmaServidor;

	public PluginJPA getPluginFirmaServidor() {
    return this.pluginFirmaServidor;
  }

	public  void setPluginFirmaServidor(PluginJPA pluginFirmaServidor) {
    this.pluginFirmaServidor = pluginFirmaServidor;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pfi_confapp_fitxer_cert_fk")
	@JoinColumn(name = "logincertificateid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA loginCertificate;

	public FitxerJPA getLoginCertificate() {
    return this.loginCertificate;
  }

	public  void setLoginCertificate(FitxerJPA loginCertificate) {
    this.loginCertificate = loginCertificate;
  }


 // ---------------  STATIC METHODS ------------------
  public static UsuariAplicacioConfiguracioJPA toJPA(UsuariAplicacioConfiguracio __bean) {
    if (__bean == null) { return null;}
    UsuariAplicacioConfiguracioJPA __tmp = new UsuariAplicacioConfiguracioJPA();
    __tmp.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setUsPoliticaDeFirma(__bean.getUsPoliticaDeFirma());
    __tmp.setPolicyIdentifier(__bean.getPolicyIdentifier());
    __tmp.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    __tmp.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    __tmp.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    __tmp.setFiltreCertificats(__bean.getFiltreCertificats());
    __tmp.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    __tmp.setTipusFirmaID(__bean.getTipusFirmaID());
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setModeDeFirma(__bean.isModeDeFirma());
    __tmp.setPoliticaCustodia(__bean.getPoliticaCustodia());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setPoliticaTaulaFirmes(__bean.getPoliticaTaulaFirmes());
    __tmp.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    __tmp.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    __tmp.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    __tmp.setPropietatsTaulaFirmes(__bean.getPropietatsTaulaFirmes());
    __tmp.setPoliticaSegellatDeTemps(__bean.getPoliticaSegellatDeTemps());
    __tmp.setPluginSegellatID(__bean.getPluginSegellatID());
    __tmp.setHtmlPerLlistarPluginsFirmaWeb(__bean.getHtmlPerLlistarPluginsFirmaWeb());
    __tmp.setPluginFirmaServidorID(__bean.getPluginFirmaServidorID());
    __tmp.setMaxFirmesEnServidor(__bean.getMaxFirmesEnServidor());
    __tmp.setUpgradeSignFormat(__bean.getUpgradeSignFormat());
    __tmp.setLoginCertificateID(__bean.getLoginCertificateID());
    __tmp.setComprovarNifFirma(__bean.getComprovarNifFirma());
    __tmp.setCheckCanviatDocFirmat(__bean.getCheckCanviatDocFirmat());
    __tmp.setValidarFirma(__bean.getValidarFirma());
    __tmp.setValidarCertificat(__bean.getValidarCertificat());
    // Fitxer
    __tmp.setLoginCertificate(FitxerJPA.toJPA(__bean.getLoginCertificate()));
		return __tmp;
	}


  public static UsuariAplicacioConfiguracioJPA copyJPA(UsuariAplicacioConfiguracioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariAplicacioConfiguracioJPA> copyJPA(java.util.Set<UsuariAplicacioConfiguracioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<UsuariAplicacioConfiguracioJPA> __tmpSet = (java.util.Set<UsuariAplicacioConfiguracioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariAplicacioConfiguracioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariAplicacioConfiguracioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariAplicacioConfiguracioJPA copyJPA(UsuariAplicacioConfiguracioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariAplicacioConfiguracioJPA __tmp = (UsuariAplicacioConfiguracioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TipusFirmaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusFirma) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusFirma()) ) ) {
      __tmp.setTipusFirma(TipusFirmaJPA.copyJPA(__jpa.getTipusFirma(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.motiuDelegacio) || org.hibernate.Hibernate.isInitialized(__jpa.getMotiuDelegacio()) ) ) {
      __tmp.setMotiuDelegacio(TraduccioJPA.copyJPA(__jpa.getMotiuDelegacio(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.firmatPerFormat) || org.hibernate.Hibernate.isInitialized(__jpa.getFirmatPerFormat()) ) ) {
      __tmp.setFirmatPerFormat(TraduccioJPA.copyJPA(__jpa.getFirmatPerFormat(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"CustodiaInfoJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfo) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfo()) ) ) {
      __tmp.setCustodiaInfo(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfo(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginFirmaServidor) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginFirmaServidor()) ) ) {
      __tmp.setPluginFirmaServidor(PluginJPA.copyJPA(__jpa.getPluginFirmaServidor(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginSegellat) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginSegellat()) ) ) {
      __tmp.setPluginSegellat(PluginJPA.copyJPA(__jpa.getPluginSegellat(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"AlgorismeDeFirmaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.algorismeDeFirma) || org.hibernate.Hibernate.isInitialized(__jpa.getAlgorismeDeFirma()) ) ) {
      __tmp.setAlgorismeDeFirma(AlgorismeDeFirmaJPA.copyJPA(__jpa.getAlgorismeDeFirma(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"UsuariAplicacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacio) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacio()) ) ) {
      __tmp.setUsuariAplicacio(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacio(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }

    return __tmp;
  }




}
