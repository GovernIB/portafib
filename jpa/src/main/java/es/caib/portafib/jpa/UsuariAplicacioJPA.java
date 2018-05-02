
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "pfi_usuariaplicacio" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariAplicacioJPA implements UsuariAplicacio {



private static final long serialVersionUID = -360699331L;

	@Id
	@Index(name="pfi_usuariaplicacio_pk_i")
	@Column(name="usuariaplicacioid",nullable = false,length = 101)
	java.lang.String usuariAplicacioID;

	@Column(name="contrasenya",length = 50)
	java.lang.String contrasenya;

  /** Només pot ser null en el cas de definir l'usuari aplicació emprat per realitzar peticions dels usuaris-persona */
	@Index(name="pfi_usrapp_entitatid_fk_i")
	@Column(name="entitatid",nullable = false,length = 50)
	java.lang.String entitatID;

  /** Correu de la persona encarregada d'aquest usuari-Màquina */
	@Column(name="emailadmin",nullable = false,length = 100)
	java.lang.String emailAdmin;

  /** La versió 0 és la compatible amb INDRA i la versió 1 és l'especifica del nou Portafirmes */
	@Column(name="callbackversio",nullable = false,length = 10)
	int callbackVersio;

  /** Adreça on esta implementat el servei de recepció de notificacions associades a les peticions de firma realitzades per aquest usuari-màquina */
	@Column(name="callbackurl",nullable = false,length = 400)
	java.lang.String callbackURL;

	@Column(name="actiu",nullable = false,length = 1)
	boolean actiu;

	@Index(name="pfi_usrapp_idiomaid_fk_i")
	@Column(name="idiomaid",nullable = false,length = 5)
	java.lang.String idiomaID;

	@Column(name="descripcio",length = 255)
	java.lang.String descripcio;

	@Index(name="pfi_usrapp_logosegellid_fk_i")
	@Column(name="logosegellid",length = 19)
	java.lang.Long logoSegellID;

  /** Indica si aquest usuari aplicacio pot custodiar en les peticions de firma:
     + null = Només plantilles de Entitat
     + false = No pot custodiar
     + true = Pot emprar plantilles o establie el mateix usuari la configuració de custodia */
	@Column(name="potcustodiar",length = 1)
	java.lang.Boolean potCustodiar;

  /** 0 - Només plugins de l'entitat, 1 - Plugins de l'entitat més plugins addicionals (afegir o llevar), 2 - Només plugins addicionals (Només els que tenguin marcat afegir) */
	@Column(name="politicadepluginfirmaweb",nullable = false,length = 10)
	int politicaDePluginFirmaWeb;



  /** Constructor Buit */
  public UsuariAplicacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariAplicacioJPA(java.lang.String usuariAplicacioID , java.lang.String contrasenya , java.lang.String entitatID , java.lang.String emailAdmin , int callbackVersio , java.lang.String callbackURL , boolean actiu , java.lang.String idiomaID , java.lang.String descripcio , java.lang.Long logoSegellID , java.lang.Boolean potCustodiar , int politicaDePluginFirmaWeb) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.contrasenya=contrasenya;
    this.entitatID=entitatID;
    this.emailAdmin=emailAdmin;
    this.callbackVersio=callbackVersio;
    this.callbackURL=callbackURL;
    this.actiu=actiu;
    this.idiomaID=idiomaID;
    this.descripcio=descripcio;
    this.logoSegellID=logoSegellID;
    this.potCustodiar=potCustodiar;
    this.politicaDePluginFirmaWeb=politicaDePluginFirmaWeb;
}
  /** Constructor dels valors Not Null */
  public UsuariAplicacioJPA(java.lang.String usuariAplicacioID , java.lang.String entitatID , java.lang.String emailAdmin , int callbackVersio , java.lang.String callbackURL , boolean actiu , java.lang.String idiomaID , int politicaDePluginFirmaWeb) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.entitatID=entitatID;
    this.emailAdmin=emailAdmin;
    this.callbackVersio=callbackVersio;
    this.callbackURL=callbackURL;
    this.actiu=actiu;
    this.idiomaID=idiomaID;
    this.politicaDePluginFirmaWeb=politicaDePluginFirmaWeb;
}
  public UsuariAplicacioJPA(UsuariAplicacio __bean) {
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setContrasenya(__bean.getContrasenya());
    this.setEntitatID(__bean.getEntitatID());
    this.setEmailAdmin(__bean.getEmailAdmin());
    this.setCallbackVersio(__bean.getCallbackVersio());
    this.setCallbackURL(__bean.getCallbackURL());
    this.setActiu(__bean.isActiu());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setDescripcio(__bean.getDescripcio());
    this.setLogoSegellID(__bean.getLogoSegellID());
    this.setPotCustodiar(__bean.getPotCustodiar());
    this.setPoliticaDePluginFirmaWeb(__bean.getPoliticaDePluginFirmaWeb());
    // Fitxer
    this.setLogoSegell(FitxerJPA.toJPA(__bean.getLogoSegell()));
	}

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};

	public java.lang.String getContrasenya() {
		return(contrasenya);
	};
	public void setContrasenya(java.lang.String _contrasenya_) {
		this.contrasenya = _contrasenya_;
	};

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getEmailAdmin() {
		return(emailAdmin);
	};
	public void setEmailAdmin(java.lang.String _emailAdmin_) {
		this.emailAdmin = _emailAdmin_;
	};

	public int getCallbackVersio() {
		return(callbackVersio);
	};
	public void setCallbackVersio(int _callbackVersio_) {
		this.callbackVersio = _callbackVersio_;
	};

	public java.lang.String getCallbackURL() {
		return(callbackURL);
	};
	public void setCallbackURL(java.lang.String _callbackURL_) {
		this.callbackURL = _callbackURL_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.Long getLogoSegellID() {
		return(logoSegellID);
	};
	public void setLogoSegellID(java.lang.Long _logoSegellID_) {
		this.logoSegellID = _logoSegellID_;
	};

	public java.lang.Boolean getPotCustodiar() {
		return(potCustodiar);
	};
	public void setPotCustodiar(java.lang.Boolean _potCustodiar_) {
		this.potCustodiar = _potCustodiar_;
	};

	public int getPoliticaDePluginFirmaWeb() {
		return(politicaDePluginFirmaWeb);
	};
	public void setPoliticaDePluginFirmaWeb(int _politicaDePluginFirmaWeb_) {
		this.politicaDePluginFirmaWeb = _politicaDePluginFirmaWeb_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof UsuariAplicacio) {
      UsuariAplicacio __instance = (UsuariAplicacio)__obj;
      __result = true;
      if (this.getUsuariAplicacioID() == null) {
        __result = __result && (__instance.getUsuariAplicacioID() == null);
      } else {
        __result = __result && this.getUsuariAplicacioID().equals(__instance.getUsuariAplicacioID()) ;
      }

    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:usuariaplicacioid | Table: pfi_custodiainfo | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariAplicacio")
	private Set<CustodiaInfoJPA> custodiaInfos = new HashSet<CustodiaInfoJPA>(0);
	public  Set<CustodiaInfoJPA> getCustodiaInfos() {
    return this.custodiaInfos;
  }

	public void setCustodiaInfos(Set<CustodiaInfoJPA> custodiaInfos) {
	  this.custodiaInfos = custodiaInfos;
	}


// EXP  Field:usuariaplicacioid | Table: pfi_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariAplicacio")
	private Set<EntitatJPA> entitats = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitats() {
    return this.entitats;
  }

	public void setEntitats(Set<EntitatJPA> entitats) {
	  this.entitats = entitats;
	}


// EXP  Field:usuariaplicacioid | Table: pfi_peticiodefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariAplicacio")
	private Set<PeticioDeFirmaJPA> peticioDeFirmas = new HashSet<PeticioDeFirmaJPA>(0);
	public  Set<PeticioDeFirmaJPA> getPeticioDeFirmas() {
    return this.peticioDeFirmas;
  }

	public void setPeticioDeFirmas(Set<PeticioDeFirmaJPA> peticioDeFirmas) {
	  this.peticioDeFirmas = peticioDeFirmas;
	}


// EXP  Field:usuariaplicacioid | Table: pfi_plantillafluxdefirmes | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariAplicacio")
	private Set<PlantillaFluxDeFirmesJPA> plantillaFluxDeFirmess = new HashSet<PlantillaFluxDeFirmesJPA>(0);
	public  Set<PlantillaFluxDeFirmesJPA> getPlantillaFluxDeFirmess() {
    return this.plantillaFluxDeFirmess;
  }

	public void setPlantillaFluxDeFirmess(Set<PlantillaFluxDeFirmesJPA> plantillaFluxDeFirmess) {
	  this.plantillaFluxDeFirmess = plantillaFluxDeFirmess;
	}


// EXP  Field:usuariaplicacioid | Table: pfi_pluginfirmawebperusrapp | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariAplicacio")
	private Set<PluginFirmaWebPerUsuariAplicacioJPA> pluginFirmaWebPerUsuariAplicacios = new HashSet<PluginFirmaWebPerUsuariAplicacioJPA>(0);
	public  Set<PluginFirmaWebPerUsuariAplicacioJPA> getPluginFirmaWebPerUsuariAplicacios() {
    return this.pluginFirmaWebPerUsuariAplicacios;
  }

	public void setPluginFirmaWebPerUsuariAplicacios(Set<PluginFirmaWebPerUsuariAplicacioJPA> pluginFirmaWebPerUsuariAplicacios) {
	  this.pluginFirmaWebPerUsuariAplicacios = pluginFirmaWebPerUsuariAplicacios;
	}


// EXP  Field:usuariaplicacioid | Table: pfi_roleusuariaplicacio | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariAplicacio")
	private Set<RoleUsuariAplicacioJPA> roleUsuariAplicacios = new HashSet<RoleUsuariAplicacioJPA>(0);
	public  Set<RoleUsuariAplicacioJPA> getRoleUsuariAplicacios() {
    return this.roleUsuariAplicacios;
  }

	public void setRoleUsuariAplicacios(Set<RoleUsuariAplicacioJPA> roleUsuariAplicacios) {
	  this.roleUsuariAplicacios = roleUsuariAplicacios;
	}


// EXP  Field:usuariaplicacioid | Table: pfi_tipusdocument | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariAplicacio")
	private Set<TipusDocumentJPA> tipusDocuments = new HashSet<TipusDocumentJPA>(0);
	public  Set<TipusDocumentJPA> getTipusDocuments() {
    return this.tipusDocuments;
  }

	public void setTipusDocuments(Set<TipusDocumentJPA> tipusDocuments) {
	  this.tipusDocuments = tipusDocuments;
	}


// EXP  Field:usuariaplicacioid | Table: pfi_usuariaplicacioconfig | Type: 0  

	@OneToOne(mappedBy="usuariAplicacio")
	private UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio;
	public UsuariAplicacioConfiguracioJPA getUsuariAplicacioConfiguracio() {
	  return this.usuariAplicacioConfiguracio;
	}

	public  void setUsuariAplicacioConfiguracio(UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio) {
	  this.usuariAplicacioConfiguracio = usuariAplicacioConfiguracio;
	}


// IMP Field:entitatid | Table: pfi_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_usrapp_entitat_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }

// IMP Field:idiomaid | Table: pfi_idioma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_usrapp_idioma_fk")
	@JoinColumn(name = "idiomaid", referencedColumnName ="idiomaID", nullable = false, insertable=false, updatable=false)
	private IdiomaJPA idioma;

	public IdiomaJPA getIdioma() {
    return this.idioma;
  }

	public  void setIdioma(IdiomaJPA idioma) {
    this.idioma = idioma;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pfi_usrapp_fitxer_fk")
	@JoinColumn(name = "logosegellid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA logoSegell;

	public FitxerJPA getLogoSegell() {
    return this.logoSegell;
  }

	public  void setLogoSegell(FitxerJPA logoSegell) {
    this.logoSegell = logoSegell;
  }


 // ---------------  STATIC METHODS ------------------
  public static UsuariAplicacioJPA toJPA(UsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    UsuariAplicacioJPA __tmp = new UsuariAplicacioJPA();
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setContrasenya(__bean.getContrasenya());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setEmailAdmin(__bean.getEmailAdmin());
    __tmp.setCallbackVersio(__bean.getCallbackVersio());
    __tmp.setCallbackURL(__bean.getCallbackURL());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    __tmp.setPotCustodiar(__bean.getPotCustodiar());
    __tmp.setPoliticaDePluginFirmaWeb(__bean.getPoliticaDePluginFirmaWeb());
    // Fitxer
    __tmp.setLogoSegell(FitxerJPA.toJPA(__bean.getLogoSegell()));
		return __tmp;
	}


  public static UsuariAplicacioJPA copyJPA(UsuariAplicacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariAplicacioJPA> copyJPA(java.util.Set<UsuariAplicacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<UsuariAplicacioJPA> __tmpSet = (java.util.Set<UsuariAplicacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariAplicacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariAplicacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariAplicacioJPA copyJPA(UsuariAplicacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariAplicacioJPA __tmp = (UsuariAplicacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PluginFirmaWebPerUsuariAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginFirmaWebPerUsuariAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginFirmaWebPerUsuariAplicacios())) ) {
      __tmp.setPluginFirmaWebPerUsuariAplicacios(PluginFirmaWebPerUsuariAplicacioJPA.copyJPA(__jpa.getPluginFirmaWebPerUsuariAplicacios(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"PlantillaFluxDeFirmesJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plantillaFluxDeFirmess) || org.hibernate.Hibernate.isInitialized(__jpa.getPlantillaFluxDeFirmess())) ) {
      __tmp.setPlantillaFluxDeFirmess(PlantillaFluxDeFirmesJPA.copyJPA(__jpa.getPlantillaFluxDeFirmess(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacioConfiguracio) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacioConfiguracio())) ) {
      __tmp.setUsuariAplicacioConfiguracio(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getUsuariAplicacioConfiguracio(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitats) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitats())) ) {
      __tmp.setEntitats(EntitatJPA.copyJPA(__jpa.getEntitats(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"TipusDocumentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusDocuments) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusDocuments())) ) {
      __tmp.setTipusDocuments(TipusDocumentJPA.copyJPA(__jpa.getTipusDocuments(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirmas) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirmas())) ) {
      __tmp.setPeticioDeFirmas(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirmas(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"CustodiaInfoJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfos) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfos())) ) {
      __tmp.setCustodiaInfos(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfos(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"RoleUsuariAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.roleUsuariAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getRoleUsuariAplicacios())) ) {
      __tmp.setRoleUsuariAplicacios(RoleUsuariAplicacioJPA.copyJPA(__jpa.getRoleUsuariAplicacios(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"IdiomaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.idioma) || org.hibernate.Hibernate.isInitialized(__jpa.getIdioma()) ) ) {
      __tmp.setIdioma(IdiomaJPA.copyJPA(__jpa.getIdioma(), __alreadyCopied,"UsuariAplicacioJPA"));
    }

    return __tmp;
  }




}
