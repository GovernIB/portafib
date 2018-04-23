
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

  /** 0 => no usar politica de firma, 1=> usar politica de firma de l'entitat, 2=> usar politica d'aquesta configuracio  */
	@Column(name="uspoliticadefirma",nullable = false,length = 10)
	int usPoliticaDeTirma;

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

	@Index(name="pfi_confapp_motiudele_fk_i")
	@Column(name="motiudelegacioid",length = 19)
	java.lang.Long motiuDelegacioID;

	@Index(name="pfi_confapp_firmatper_fk_i")
	@Column(name="firmatperformatid",length = 19)
	java.lang.Long firmatPerFormatID;

	@Index(name="pfi_confapp_custinfo_fk_i")
	@Column(name="custodiainfoid",length = 19)
	java.lang.Long custodiaInfoID;

  /** Si val null s'utilitza la info de l'entitat. Valors: SENSETAULA = 0; PRIMERAPAGINA = 1; DARRERAPAGINA = -1;DEFINIT_EN_FIRMA(RUBRICA)=2 */
	@Index(name="pfi_confapp_postaula_fk_i")
	@Column(name="posiciotaulafirmesid",length = 10)
	int posicioTaulaFirmesID;

	@Index(name="pfi_confapp_plugsegell_fk_i")
	@Column(name="pluginsegellatid",length = 19)
	java.lang.Long pluginSegellatID;

	@Index(name="pfi_confapp_firmaserv_fk_i")
	@Column(name="pluginfirmaservidorid",length = 19)
	java.lang.Long pluginFirmaServidorID;

	@Column(name="htmlperllistarpluginsfirmaweb",length = 2147483647)
  @Lob
	java.lang.String htmlPerLlistarPluginsFirmaWeb;

	@Index(name="pfi_confapp_logincert_fk_i")
	@Column(name="logincertificateid",length = 19)
	java.lang.Long loginCertificateID;

  /** Null => Valor definit a l'entitat */
	@Column(name="comprovarniffirma",length = 1)
	boolean comprovarNifFirma;

  /** -- Null => Valor definit a l'entitat */
	@Column(name="checkcanviatdocfirmat",length = 1)
	boolean checkCanviatDocFirmat;

  /** Indica si validar la firma amb el Plugin de validació definit a l'entitat */
	@Column(name="validarfirma",length = 1)
	boolean validarFirma;

  /** NULL => Lo que digui l'entitat */
	@Column(name="validarcertificat",length = 1)
	boolean validarCertificat;



  /** Constructor Buit */
  public UsuariAplicacioConfiguracioJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariAplicacioConfiguracioJPA(long usuariAplicacioConfigID , java.lang.String usuariAplicacioID , int usPoliticaDeTirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , java.lang.String filtreCertificats , int tipusOperacioFirma , int tipusFirmaID , java.lang.Integer algorismeDeFirmaID , boolean modeDeFirma , java.lang.Long motiuDelegacioID , java.lang.Long firmatPerFormatID , java.lang.Long custodiaInfoID , int posicioTaulaFirmesID , java.lang.Long pluginSegellatID , java.lang.Long pluginFirmaServidorID , java.lang.String htmlPerLlistarPluginsFirmaWeb , java.lang.Long loginCertificateID , boolean comprovarNifFirma , boolean checkCanviatDocFirmat , boolean validarFirma , boolean validarCertificat) {
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.usPoliticaDeTirma=usPoliticaDeTirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.filtreCertificats=filtreCertificats;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.motiuDelegacioID=motiuDelegacioID;
    this.firmatPerFormatID=firmatPerFormatID;
    this.custodiaInfoID=custodiaInfoID;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.pluginSegellatID=pluginSegellatID;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.htmlPerLlistarPluginsFirmaWeb=htmlPerLlistarPluginsFirmaWeb;
    this.loginCertificateID=loginCertificateID;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.validarFirma=validarFirma;
    this.validarCertificat=validarCertificat;
}
  /** Constructor sense valors autoincrementals */
  public UsuariAplicacioConfiguracioJPA(java.lang.String usuariAplicacioID , int usPoliticaDeTirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , java.lang.String filtreCertificats , int tipusOperacioFirma , int tipusFirmaID , java.lang.Integer algorismeDeFirmaID , boolean modeDeFirma , java.lang.Long motiuDelegacioID , java.lang.Long firmatPerFormatID , java.lang.Long custodiaInfoID , int posicioTaulaFirmesID , java.lang.Long pluginSegellatID , java.lang.Long pluginFirmaServidorID , java.lang.String htmlPerLlistarPluginsFirmaWeb , java.lang.Long loginCertificateID , boolean comprovarNifFirma , boolean checkCanviatDocFirmat , boolean validarFirma , boolean validarCertificat) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.usPoliticaDeTirma=usPoliticaDeTirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.filtreCertificats=filtreCertificats;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.motiuDelegacioID=motiuDelegacioID;
    this.firmatPerFormatID=firmatPerFormatID;
    this.custodiaInfoID=custodiaInfoID;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.pluginSegellatID=pluginSegellatID;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.htmlPerLlistarPluginsFirmaWeb=htmlPerLlistarPluginsFirmaWeb;
    this.loginCertificateID=loginCertificateID;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.validarFirma=validarFirma;
    this.validarCertificat=validarCertificat;
}
  /** Constructor dels valors Not Null */
  public UsuariAplicacioConfiguracioJPA(long usuariAplicacioConfigID , java.lang.String usuariAplicacioID , int usPoliticaDeTirma , int tipusOperacioFirma , int tipusFirmaID , boolean modeDeFirma) {
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.usPoliticaDeTirma=usPoliticaDeTirma;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.modeDeFirma=modeDeFirma;
}
  public UsuariAplicacioConfiguracioJPA(UsuariAplicacioConfiguracio __bean) {
    this.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setUsPoliticaDeTirma(__bean.getUsPoliticaDeTirma());
    this.setPolicyIdentifier(__bean.getPolicyIdentifier());
    this.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    this.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    this.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    this.setFiltreCertificats(__bean.getFiltreCertificats());
    this.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    this.setTipusFirmaID(__bean.getTipusFirmaID());
    this.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    this.setModeDeFirma(__bean.isModeDeFirma());
    this.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    this.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    this.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    this.setPluginSegellatID(__bean.getPluginSegellatID());
    this.setPluginFirmaServidorID(__bean.getPluginFirmaServidorID());
    this.setHtmlPerLlistarPluginsFirmaWeb(__bean.getHtmlPerLlistarPluginsFirmaWeb());
    this.setLoginCertificateID(__bean.getLoginCertificateID());
    this.setComprovarNifFirma(__bean.isComprovarNifFirma());
    this.setCheckCanviatDocFirmat(__bean.isCheckCanviatDocFirmat());
    this.setValidarFirma(__bean.isValidarFirma());
    this.setValidarCertificat(__bean.isValidarCertificat());
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

	public int getUsPoliticaDeTirma() {
		return(usPoliticaDeTirma);
	};
	public void setUsPoliticaDeTirma(int _usPoliticaDeTirma_) {
		this.usPoliticaDeTirma = _usPoliticaDeTirma_;
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

	public java.lang.Long getCustodiaInfoID() {
		return(custodiaInfoID);
	};
	public void setCustodiaInfoID(java.lang.Long _custodiaInfoID_) {
		this.custodiaInfoID = _custodiaInfoID_;
	};

	public int getPosicioTaulaFirmesID() {
		return(posicioTaulaFirmesID);
	};
	public void setPosicioTaulaFirmesID(int _posicioTaulaFirmesID_) {
		this.posicioTaulaFirmesID = _posicioTaulaFirmesID_;
	};

	public java.lang.Long getPluginSegellatID() {
		return(pluginSegellatID);
	};
	public void setPluginSegellatID(java.lang.Long _pluginSegellatID_) {
		this.pluginSegellatID = _pluginSegellatID_;
	};

	public java.lang.Long getPluginFirmaServidorID() {
		return(pluginFirmaServidorID);
	};
	public void setPluginFirmaServidorID(java.lang.Long _pluginFirmaServidorID_) {
		this.pluginFirmaServidorID = _pluginFirmaServidorID_;
	};

	public java.lang.String getHtmlPerLlistarPluginsFirmaWeb() {
		return(htmlPerLlistarPluginsFirmaWeb);
	};
	public void setHtmlPerLlistarPluginsFirmaWeb(java.lang.String _htmlPerLlistarPluginsFirmaWeb_) {
		this.htmlPerLlistarPluginsFirmaWeb = _htmlPerLlistarPluginsFirmaWeb_;
	};

	public java.lang.Long getLoginCertificateID() {
		return(loginCertificateID);
	};
	public void setLoginCertificateID(java.lang.Long _loginCertificateID_) {
		this.loginCertificateID = _loginCertificateID_;
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

	public boolean isValidarFirma() {
		return(validarFirma);
	};
	public void setValidarFirma(boolean _validarFirma_) {
		this.validarFirma = _validarFirma_;
	};

	public boolean isValidarCertificat() {
		return(validarCertificat);
	};
	public void setValidarCertificat(boolean _validarCertificat_) {
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
    __tmp.setUsPoliticaDeTirma(__bean.getUsPoliticaDeTirma());
    __tmp.setPolicyIdentifier(__bean.getPolicyIdentifier());
    __tmp.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    __tmp.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    __tmp.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    __tmp.setFiltreCertificats(__bean.getFiltreCertificats());
    __tmp.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    __tmp.setTipusFirmaID(__bean.getTipusFirmaID());
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setModeDeFirma(__bean.isModeDeFirma());
    __tmp.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    __tmp.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    __tmp.setPluginSegellatID(__bean.getPluginSegellatID());
    __tmp.setPluginFirmaServidorID(__bean.getPluginFirmaServidorID());
    __tmp.setHtmlPerLlistarPluginsFirmaWeb(__bean.getHtmlPerLlistarPluginsFirmaWeb());
    __tmp.setLoginCertificateID(__bean.getLoginCertificateID());
    __tmp.setComprovarNifFirma(__bean.isComprovarNifFirma());
    __tmp.setCheckCanviatDocFirmat(__bean.isCheckCanviatDocFirmat());
    __tmp.setValidarFirma(__bean.isValidarFirma());
    __tmp.setValidarCertificat(__bean.isValidarCertificat());
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
