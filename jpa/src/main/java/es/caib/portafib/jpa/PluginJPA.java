
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.ForeignKey;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_plugin" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class PluginJPA implements Plugin {



private static final long serialVersionUID = 190357384L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_plugin_pk_i")
	@Column(name="pluginid",nullable = false,length = 19)
	long pluginID;

	@Column(name="codi",nullable = false,length = 255)
	java.lang.String codi;

	@Index(name="pfi_plugin_nomid_fk_i")
	@Column(name="nomid",nullable = false,length = 19)
	long nomID;

	@Index(name="pfi_plugin_desccurtaid_fk_i")
	@Column(name="descripciocurtaid",nullable = false,length = 19)
	long descripcioCurtaID;

	@Column(name="classe",nullable = false,length = 255)
	java.lang.String classe;

	@Column(name="ordre",length = 10)
	java.lang.Integer ordre;

	@Column(name="tipus",nullable = false,length = 10)
	int tipus;

	@Column(name="propertiesadmin",length = 2147483647)
  @Lob
	java.lang.String propertiesAdmin;

	@Column(name="propertiesentitat",length = 2147483647)
  @Lob
	java.lang.String propertiesEntitat;

  /** 0 Plantilla
1 Només entitat
2 Ho pot usar tothom */
	@Column(name="politicadeus",nullable = false,length = 10)
	int politicadeus;

  /** Si val null indica que és de l'Administrador. En cas conytrari ja és una instanciació d'una Entitat */
	@Index(name="pfi_plugin_entitatid_fk_i")
	@Column(name="entitatid",length = 50)
	java.lang.String entitatID;

	@Column(name="actiu",nullable = false,length = 1)
	boolean actiu;

  /** 0 => No mostrar ni propietats administrador ni propietats entitat, 1 => Permetre editar propietats entitat però no mostrar propietats administrador,  2 => Permetre editar propietats entitat i mostrar propietats administrador, 3 => Permetre editar propietats entitat i editar propietats administrador
 */
	@Column(name="politicamostrarpropietats",nullable = false,length = 10)
	int politicaMostrarPropietats;



  /** Constructor Buit */
  public PluginJPA() {
  }

  /** Constructor amb tots els camps  */
  public PluginJPA(long pluginID , java.lang.String codi , long nomID , long descripcioCurtaID , java.lang.String classe , java.lang.Integer ordre , int tipus , java.lang.String propertiesAdmin , java.lang.String propertiesEntitat , int politicadeus , java.lang.String entitatID , boolean actiu , int politicaMostrarPropietats) {
    this.pluginID=pluginID;
    this.codi=codi;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.ordre=ordre;
    this.tipus=tipus;
    this.propertiesAdmin=propertiesAdmin;
    this.propertiesEntitat=propertiesEntitat;
    this.politicadeus=politicadeus;
    this.entitatID=entitatID;
    this.actiu=actiu;
    this.politicaMostrarPropietats=politicaMostrarPropietats;
}
  /** Constructor sense valors autoincrementals */
  public PluginJPA(java.lang.String codi , long nomID , long descripcioCurtaID , java.lang.String classe , java.lang.Integer ordre , int tipus , java.lang.String propertiesAdmin , java.lang.String propertiesEntitat , int politicadeus , java.lang.String entitatID , boolean actiu , int politicaMostrarPropietats) {
    this.codi=codi;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.ordre=ordre;
    this.tipus=tipus;
    this.propertiesAdmin=propertiesAdmin;
    this.propertiesEntitat=propertiesEntitat;
    this.politicadeus=politicadeus;
    this.entitatID=entitatID;
    this.actiu=actiu;
    this.politicaMostrarPropietats=politicaMostrarPropietats;
}
  /** Constructor dels valors Not Null */
  public PluginJPA(long pluginID , java.lang.String codi , long nomID , long descripcioCurtaID , java.lang.String classe , int tipus , int politicadeus , boolean actiu , int politicaMostrarPropietats) {
    this.pluginID=pluginID;
    this.codi=codi;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.tipus=tipus;
    this.politicadeus=politicadeus;
    this.actiu=actiu;
    this.politicaMostrarPropietats=politicaMostrarPropietats;
}
  public PluginJPA(Plugin __bean) {
    this.setPluginID(__bean.getPluginID());
    this.setCodi(__bean.getCodi());
    this.setNomID(__bean.getNomID());
    this.setDescripcioCurtaID(__bean.getDescripcioCurtaID());
    this.setClasse(__bean.getClasse());
    this.setOrdre(__bean.getOrdre());
    this.setTipus(__bean.getTipus());
    this.setPropertiesAdmin(__bean.getPropertiesAdmin());
    this.setPropertiesEntitat(__bean.getPropertiesEntitat());
    this.setPoliticadeus(__bean.getPoliticadeus());
    this.setEntitatID(__bean.getEntitatID());
    this.setActiu(__bean.isActiu());
    this.setPoliticaMostrarPropietats(__bean.getPoliticaMostrarPropietats());
	}

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public long getDescripcioCurtaID() {
		return(descripcioCurtaID);
	};
	public void setDescripcioCurtaID(long _descripcioCurtaID_) {
		this.descripcioCurtaID = _descripcioCurtaID_;
	};

	public java.lang.String getClasse() {
		return(classe);
	};
	public void setClasse(java.lang.String _classe_) {
		this.classe = _classe_;
	};

	public java.lang.Integer getOrdre() {
		return(ordre);
	};
	public void setOrdre(java.lang.Integer _ordre_) {
		this.ordre = _ordre_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getPropertiesAdmin() {
		return(propertiesAdmin);
	};
	public void setPropertiesAdmin(java.lang.String _propertiesAdmin_) {
		this.propertiesAdmin = _propertiesAdmin_;
	};

	public java.lang.String getPropertiesEntitat() {
		return(propertiesEntitat);
	};
	public void setPropertiesEntitat(java.lang.String _propertiesEntitat_) {
		this.propertiesEntitat = _propertiesEntitat_;
	};

	public int getPoliticadeus() {
		return(politicadeus);
	};
	public void setPoliticadeus(int _politicadeus_) {
		this.politicadeus = _politicadeus_;
	};

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public int getPoliticaMostrarPropietats() {
		return(politicaMostrarPropietats);
	};
	public void setPoliticaMostrarPropietats(int _politicaMostrarPropietats_) {
		this.politicaMostrarPropietats = _politicaMostrarPropietats_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Plugin) {
      Plugin __instance = (Plugin)__obj;
      __result = true;
      __result = __result && (this.getPluginID() == __instance.getPluginID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:pluginid | Table: pfi_custodiainfo | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plugin")
	private Set<CustodiaInfoJPA> custodiaInfos = new HashSet<CustodiaInfoJPA>(0);
	public  Set<CustodiaInfoJPA> getCustodiaInfos() {
    return this.custodiaInfos;
  }

	public void setCustodiaInfos(Set<CustodiaInfoJPA> custodiaInfos) {
	  this.custodiaInfos = custodiaInfos;
	}


// EXP  Field:pluginvalidacertificatid | Table: pfi_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pluginValidaCertificatID")
	private Set<EntitatJPA> entitat_pluginvalidacertificatids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_pluginvalidacertificatids() {
    return this.entitat_pluginvalidacertificatids;
  }

	public void setEntitat_pluginvalidacertificatids(Set<EntitatJPA> entitat_pluginvalidacertificatids) {
	  this.entitat_pluginvalidacertificatids = entitat_pluginvalidacertificatids;
	}


// EXP  Field:pluginid | Table: pfi_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pluginID")
	private Set<EntitatJPA> entitat_pluginids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_pluginids() {
    return this.entitat_pluginids;
  }

	public void setEntitat_pluginids(Set<EntitatJPA> entitat_pluginids) {
	  this.entitat_pluginids = entitat_pluginids;
	}


// EXP  Field:pluginvalidafirmesid | Table: pfi_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pluginValidaFirmesID")
	private Set<EntitatJPA> entitat_pluginvalidafirmesids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_pluginvalidafirmesids() {
    return this.entitat_pluginvalidafirmesids;
  }

	public void setEntitat_pluginvalidafirmesids(Set<EntitatJPA> entitat_pluginvalidafirmesids) {
	  this.entitat_pluginvalidafirmesids = entitat_pluginvalidafirmesids;
	}


// EXP  Field:pluginrubricaid | Table: pfi_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pluginRubricaID")
	private Set<EntitatJPA> entitat_pluginrubricaids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_pluginrubricaids() {
    return this.entitat_pluginrubricaids;
  }

	public void setEntitat_pluginrubricaids(Set<EntitatJPA> entitat_pluginrubricaids) {
	  this.entitat_pluginrubricaids = entitat_pluginrubricaids;
	}


// EXP  Field:pluginid | Table: pfi_modulfirmapertipusdoc | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plugin")
	private Set<ModulDeFirmaPerTipusDeDocumentJPA> modulDeFirmaPerTipusDeDocuments = new HashSet<ModulDeFirmaPerTipusDeDocumentJPA>(0);
	public  Set<ModulDeFirmaPerTipusDeDocumentJPA> getModulDeFirmaPerTipusDeDocuments() {
    return this.modulDeFirmaPerTipusDeDocuments;
  }

	public void setModulDeFirmaPerTipusDeDocuments(Set<ModulDeFirmaPerTipusDeDocumentJPA> modulDeFirmaPerTipusDeDocuments) {
	  this.modulDeFirmaPerTipusDeDocuments = modulDeFirmaPerTipusDeDocuments;
	}


// EXP  Field:pluginfirmawebid | Table: pfi_pluginfirmawebperusrapp | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plugin")
	private Set<PluginFirmaWebPerUsuariAplicacioJPA> pluginFirmaWebPerUsuariAplicacios = new HashSet<PluginFirmaWebPerUsuariAplicacioJPA>(0);
	public  Set<PluginFirmaWebPerUsuariAplicacioJPA> getPluginFirmaWebPerUsuariAplicacios() {
    return this.pluginFirmaWebPerUsuariAplicacios;
  }

	public void setPluginFirmaWebPerUsuariAplicacios(Set<PluginFirmaWebPerUsuariAplicacioJPA> pluginFirmaWebPerUsuariAplicacios) {
	  this.pluginFirmaWebPerUsuariAplicacios = pluginFirmaWebPerUsuariAplicacios;
	}


// EXP  Field:pluginfirmawebid | Table: pfi_pluginfirmawebperusrent | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plugin")
	private Set<PluginFirmaWebPerUsuariEntitatJPA> pluginFirmaWebPerUsuariEntitats = new HashSet<PluginFirmaWebPerUsuariEntitatJPA>(0);
	public  Set<PluginFirmaWebPerUsuariEntitatJPA> getPluginFirmaWebPerUsuariEntitats() {
    return this.pluginFirmaWebPerUsuariEntitats;
  }

	public void setPluginFirmaWebPerUsuariEntitats(Set<PluginFirmaWebPerUsuariEntitatJPA> pluginFirmaWebPerUsuariEntitats) {
	  this.pluginFirmaWebPerUsuariEntitats = pluginFirmaWebPerUsuariEntitats;
	}


// EXP  Field:pluginfirmaservidorid | Table: pfi_usuariaplicacioconfig | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pluginFirmaServidorID")
	private Set<UsuariAplicacioConfiguracioJPA> usuariAplicacioConfiguracio_pluginfirmaservidorids = new HashSet<UsuariAplicacioConfiguracioJPA>(0);
	public  Set<UsuariAplicacioConfiguracioJPA> getUsuariAplicacioConfiguracio_pluginfirmaservidorids() {
    return this.usuariAplicacioConfiguracio_pluginfirmaservidorids;
  }

	public void setUsuariAplicacioConfiguracio_pluginfirmaservidorids(Set<UsuariAplicacioConfiguracioJPA> usuariAplicacioConfiguracio_pluginfirmaservidorids) {
	  this.usuariAplicacioConfiguracio_pluginfirmaservidorids = usuariAplicacioConfiguracio_pluginfirmaservidorids;
	}


// EXP  Field:pluginsegellatid | Table: pfi_usuariaplicacioconfig | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pluginSegellatID")
	private Set<UsuariAplicacioConfiguracioJPA> usuariAplicacioConfiguracio_pluginsegellatids = new HashSet<UsuariAplicacioConfiguracioJPA>(0);
	public  Set<UsuariAplicacioConfiguracioJPA> getUsuariAplicacioConfiguracio_pluginsegellatids() {
    return this.usuariAplicacioConfiguracio_pluginsegellatids;
  }

	public void setUsuariAplicacioConfiguracio_pluginsegellatids(Set<UsuariAplicacioConfiguracioJPA> usuariAplicacioConfiguracio_pluginsegellatids) {
	  this.usuariAplicacioConfiguracio_pluginsegellatids = usuariAplicacioConfiguracio_pluginsegellatids;
	}


// IMP Field:traduccioid | Table: pfi_traduccio | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@ForeignKey(name="pfi_plugin_traduccio_nom_fk")
	@JoinColumn(name = "nomid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false)
	private TraduccioJPA nom;

	public TraduccioJPA getNom() {
    return this.nom;
  }

	public  void setNom(TraduccioJPA nom) {
    this.nom = nom;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.portafib.jpa.TraduccioMapJPA> getNomTraduccions() {
    return this.nom.getTraduccions();
  }

  public void setNomTraduccions(java.util.Map<String, es.caib.portafib.jpa.TraduccioMapJPA> __traduccions__) {
    this.nom.setTraduccions(__traduccions__);
  }


// IMP Field:traduccioid | Table: pfi_traduccio | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@ForeignKey(name="pfi_plugin_traduccio_desc_fk")
	@JoinColumn(name = "descripciocurtaid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false)
	private TraduccioJPA descripcioCurta;

	public TraduccioJPA getDescripcioCurta() {
    return this.descripcioCurta;
  }

	public  void setDescripcioCurta(TraduccioJPA descripcioCurta) {
    this.descripcioCurta = descripcioCurta;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.portafib.jpa.TraduccioMapJPA> getDescripcioCurtaTraduccions() {
    return this.descripcioCurta.getTraduccions();
  }

  public void setDescripcioCurtaTraduccions(java.util.Map<String, es.caib.portafib.jpa.TraduccioMapJPA> __traduccions__) {
    this.descripcioCurta.setTraduccions(__traduccions__);
  }


// IMP Field:entitatid | Table: pfi_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_plugin_entitat_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = true, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static PluginJPA toJPA(Plugin __bean) {
    if (__bean == null) { return null;}
    PluginJPA __tmp = new PluginJPA();
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioCurtaID(__bean.getDescripcioCurtaID());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setOrdre(__bean.getOrdre());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setPropertiesAdmin(__bean.getPropertiesAdmin());
    __tmp.setPropertiesEntitat(__bean.getPropertiesEntitat());
    __tmp.setPoliticadeus(__bean.getPoliticadeus());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setPoliticaMostrarPropietats(__bean.getPoliticaMostrarPropietats());
		return __tmp;
	}


  public static PluginJPA copyJPA(PluginJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PluginJPA> copyJPA(java.util.Set<PluginJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PluginJPA> __tmpSet = (java.util.Set<PluginJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PluginJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PluginJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PluginJPA copyJPA(PluginJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PluginJPA __tmp = (PluginJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PluginFirmaWebPerUsuariAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginFirmaWebPerUsuariAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginFirmaWebPerUsuariAplicacios())) ) {
      __tmp.setPluginFirmaWebPerUsuariAplicacios(PluginFirmaWebPerUsuariAplicacioJPA.copyJPA(__jpa.getPluginFirmaWebPerUsuariAplicacios(), __alreadyCopied,"PluginJPA"));
    }
    if(!"ModulDeFirmaPerTipusDeDocumentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.modulDeFirmaPerTipusDeDocuments) || org.hibernate.Hibernate.isInitialized(__jpa.getModulDeFirmaPerTipusDeDocuments())) ) {
      __tmp.setModulDeFirmaPerTipusDeDocuments(ModulDeFirmaPerTipusDeDocumentJPA.copyJPA(__jpa.getModulDeFirmaPerTipusDeDocuments(), __alreadyCopied,"PluginJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_pluginvalidafirmesids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_pluginvalidafirmesids())) ) {
      __tmp.setEntitat_pluginvalidafirmesids(EntitatJPA.copyJPA(__jpa.getEntitat_pluginvalidafirmesids(), __alreadyCopied,"PluginJPA"));
    }
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacioConfiguracio_pluginfirmaservidorids) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacioConfiguracio_pluginfirmaservidorids())) ) {
      __tmp.setUsuariAplicacioConfiguracio_pluginfirmaservidorids(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getUsuariAplicacioConfiguracio_pluginfirmaservidorids(), __alreadyCopied,"PluginJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_pluginrubricaids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_pluginrubricaids())) ) {
      __tmp.setEntitat_pluginrubricaids(EntitatJPA.copyJPA(__jpa.getEntitat_pluginrubricaids(), __alreadyCopied,"PluginJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_pluginvalidacertificatids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_pluginvalidacertificatids())) ) {
      __tmp.setEntitat_pluginvalidacertificatids(EntitatJPA.copyJPA(__jpa.getEntitat_pluginvalidacertificatids(), __alreadyCopied,"PluginJPA"));
    }
    if(!"CustodiaInfoJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfos) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfos())) ) {
      __tmp.setCustodiaInfos(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfos(), __alreadyCopied,"PluginJPA"));
    }
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacioConfiguracio_pluginsegellatids) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacioConfiguracio_pluginsegellatids())) ) {
      __tmp.setUsuariAplicacioConfiguracio_pluginsegellatids(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getUsuariAplicacioConfiguracio_pluginsegellatids(), __alreadyCopied,"PluginJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_pluginids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_pluginids())) ) {
      __tmp.setEntitat_pluginids(EntitatJPA.copyJPA(__jpa.getEntitat_pluginids(), __alreadyCopied,"PluginJPA"));
    }
    if(!"PluginFirmaWebPerUsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginFirmaWebPerUsuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginFirmaWebPerUsuariEntitats())) ) {
      __tmp.setPluginFirmaWebPerUsuariEntitats(PluginFirmaWebPerUsuariEntitatJPA.copyJPA(__jpa.getPluginFirmaWebPerUsuariEntitats(), __alreadyCopied,"PluginJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"PluginJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.descripcioCurta) || org.hibernate.Hibernate.isInitialized(__jpa.getDescripcioCurta()) ) ) {
      __tmp.setDescripcioCurta(TraduccioJPA.copyJPA(__jpa.getDescripcioCurta(), __alreadyCopied,"PluginJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.nom) || org.hibernate.Hibernate.isInitialized(__jpa.getNom()) ) ) {
      __tmp.setNom(TraduccioJPA.copyJPA(__jpa.getNom(), __alreadyCopied,"PluginJPA"));
    }

    return __tmp;
  }




}
