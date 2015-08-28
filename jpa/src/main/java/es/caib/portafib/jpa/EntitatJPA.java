
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Lob;
import javax.persistence.Entity;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "pfi_entitat" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
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

  /** Identificador de la política de firma. Si es defineix aquest valors llavorses generaran 
firmes PAdES-EPES,CAdES-EPES y XAdES-EPES. */
	@Column(name="policyidentifier",length = 100)
	java.lang.String policyIdentifier;

  /** Valor en Base64. Huella digital de la política de firma. Es obligatorio indicar
este parámetro si se indicó también policyIdentifier. */
	@Column(name="policyidentifierhash",length = 2147483647)
  @Lob
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



  /** Constructor Buit */
  public EntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public EntitatJPA(java.lang.String entitatID , java.lang.String nom , java.lang.String descripcio , boolean activa , java.lang.String web , java.lang.Long faviconID , java.lang.Long logoWebID , java.lang.Long logoWebPeuID , java.lang.Long logoSegellID , java.lang.String adrezaHtml , java.lang.String filtreCertificats , java.lang.Long pdfAutoritzacioDelegacioID , java.lang.String suportTelefon , java.lang.String suportWeb , java.lang.String suportEmail , java.lang.String usuariAplicacioID , java.lang.Long maxUploadSize , java.lang.Long maxSizeFitxerAdaptat , java.lang.Integer maxFilesToSignAtSameTime , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument) {
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
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
}
  /** Constructor dels valors Not Null */
  public EntitatJPA(java.lang.String entitatID , java.lang.String nom , boolean activa , java.lang.String web , java.lang.Long faviconID , java.lang.Long logoWebID , java.lang.Long logoWebPeuID , java.lang.Long logoSegellID , java.lang.String adrezaHtml , java.lang.String filtreCertificats , java.lang.Long pdfAutoritzacioDelegacioID) {
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
    this.setPolicyIdentifier(__bean.getPolicyIdentifier());
    this.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    this.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    this.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
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


// EXP  Field:entitatid | Table: pfi_grupentitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
	private Set<GrupEntitatJPA> grupEntitats = new HashSet<GrupEntitatJPA>(0);
	public  Set<GrupEntitatJPA> getGrupEntitats() {
    return this.grupEntitats;
  }

	public void setGrupEntitats(Set<GrupEntitatJPA> grupEntitats) {
	  this.grupEntitats = grupEntitats;
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
    __tmp.setPolicyIdentifier(__bean.getPolicyIdentifier());
    __tmp.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    __tmp.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    __tmp.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
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
    if(!"GrupEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grupEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getGrupEntitats())) ) {
      __tmp.setGrupEntitats(GrupEntitatJPA.copyJPA(__jpa.getGrupEntitats(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"CustodiaInfoJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfos) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfos())) ) {
      __tmp.setCustodiaInfos(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfos(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"UsuariAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacios())) ) {
      __tmp.setUsuariAplicacios(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacios(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitats())) ) {
      __tmp.setUsuariEntitats(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitats(), __alreadyCopied,"EntitatJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"UsuariAplicacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacio) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacio()) ) ) {
      __tmp.setUsuariAplicacio(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacio(), __alreadyCopied,"EntitatJPA"));
    }

    return __tmp;
  }




}
