
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "UsuariAplicacioJPA")
@Table(name = "pfi_usuariaplicacio" , indexes = { 
        @Index(name="pfi_usuariaplicacio_pk_i", columnList = "usuariaplicacioid"),
        @Index(name="pfi_usrapp_entitatid_fk_i", columnList = "entitatid"),
        @Index(name="pfi_usrapp_idiomaid_fk_i", columnList = "idiomaid"),
        @Index(name="pfi_usrapp_logosegellid_fk_i", columnList = "logosegellid"),
        @Index(name="pfi_usrapp_custodia_fk_i", columnList = "custodiainfoid")})
@SequenceGenerator(name="USUARIAPLICACIO_SEQ", sequenceName="pfi_usuariaplicacio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariAplicacioJPA implements UsuariAplicacio {

    @Id
    @Column(name="usuariaplicacioid",nullable = false,length = 101)
    java.lang.String usuariAplicacioID;

  /** Només pot ser null en el cas de definir l'usuari aplicació emprat per realitzar peticions dels usuaris-persona */
    @Column(name="entitatid",nullable = false,length = 50)
    java.lang.String entitatID;

  /** Correu de la persona encarregada d'aquest usuari-Màquina */
    @Column(name="emailadmin",nullable = false,length = 100)
    java.lang.String emailAdmin;

  /** La versió 0 és la compatible amb INDRA i la versió 1 és l'especifica del nou Portafirmes */
    @org.hibernate.annotations.ColumnDefault("2")
    @Column(name="callbackversio",nullable = false,length = 10)
    int callbackVersio = 2;

  /** Adreça on esta implementat el servei de recepció de notificacions associades a les peticions de firma realitzades per aquest usuari-màquina */
    @Column(name="callbackurl",length = 400)
    java.lang.String callbackURL;

    @Column(name="actiu",nullable = false,length = 1)
    boolean actiu = true;

    @Column(name="idiomaid",nullable = false,length = 5)
    java.lang.String idiomaID;

    @Column(name="descripcio",length = 255)
    java.lang.String descripcio;

    @Column(name="logosegellid",length = 19)
    java.lang.Long logoSegellID;

  /** 0 - Només plugins de l'entitat, 1 - Plugins de l'entitat més plugins addicionals (afegir o llevar), 2 - Només plugins addicionals (Només els que tenguin marcat afegir) */
    @org.hibernate.annotations.ColumnDefault("0")
    @Column(name="politicadepluginfirmaweb",nullable = false,length = 10)
    int politicaDePluginFirmaWeb = 0;

  /** -1: el que digui l'entitat, 0: No permetre, 1:Només Plantilles de l''Entitat (No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat (Per defecte Actiu), 4: Opcional plantilla Entitat (Per defecte NO Actiu), 5: Llibertat Totalselecció, edició i us), 6: La plantilla definida en l''usuari-aplicació */
    @org.hibernate.annotations.ColumnDefault("0")
    @Column(name="politicacustodia",nullable = false,length = 10)
    int politicaCustodia = 0;

    @Column(name="custodiainfoid",length = 19)
    java.lang.Long custodiaInfoID;

    @Column(name="crearusuaris",nullable = false,length = 1)
    boolean crearUsuaris = false;

  /** En les consultes que requereixen revisors, indica quin tipus de revisors utilitzarà:
    * true: revisors globals
    * false: revisors de destinatari
    * null: revisors globals i revisors de destinatari */
    @Column(name="tipusrevisors",length = 1)
    java.lang.Boolean tipusRevisors = false;



  /** Constructor Buit */
  public UsuariAplicacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariAplicacioJPA(java.lang.String usuariAplicacioID , java.lang.String entitatID , java.lang.String emailAdmin , int callbackVersio , java.lang.String callbackURL , boolean actiu , java.lang.String idiomaID , java.lang.String descripcio , java.lang.Long logoSegellID , int politicaDePluginFirmaWeb , int politicaCustodia , java.lang.Long custodiaInfoID , boolean crearUsuaris , java.lang.Boolean tipusRevisors) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.entitatID=entitatID;
    this.emailAdmin=emailAdmin;
    this.callbackVersio=callbackVersio;
    this.callbackURL=callbackURL;
    this.actiu=actiu;
    this.idiomaID=idiomaID;
    this.descripcio=descripcio;
    this.logoSegellID=logoSegellID;
    this.politicaDePluginFirmaWeb=politicaDePluginFirmaWeb;
    this.politicaCustodia=politicaCustodia;
    this.custodiaInfoID=custodiaInfoID;
    this.crearUsuaris=crearUsuaris;
    this.tipusRevisors=tipusRevisors;
}
  /** Constructor dels valors Not Null */
  public UsuariAplicacioJPA(java.lang.String usuariAplicacioID , java.lang.String entitatID , java.lang.String emailAdmin , int callbackVersio , boolean actiu , java.lang.String idiomaID , int politicaDePluginFirmaWeb , int politicaCustodia , boolean crearUsuaris) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.entitatID=entitatID;
    this.emailAdmin=emailAdmin;
    this.callbackVersio=callbackVersio;
    this.actiu=actiu;
    this.idiomaID=idiomaID;
    this.politicaDePluginFirmaWeb=politicaDePluginFirmaWeb;
    this.politicaCustodia=politicaCustodia;
    this.crearUsuaris=crearUsuaris;
}
  public UsuariAplicacioJPA(UsuariAplicacio __bean) {
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setEntitatID(__bean.getEntitatID());
    this.setEmailAdmin(__bean.getEmailAdmin());
    this.setCallbackVersio(__bean.getCallbackVersio());
    this.setCallbackURL(__bean.getCallbackURL());
    this.setActiu(__bean.isActiu());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setDescripcio(__bean.getDescripcio());
    this.setLogoSegellID(__bean.getLogoSegellID());
    this.setPoliticaDePluginFirmaWeb(__bean.getPoliticaDePluginFirmaWeb());
    this.setPoliticaCustodia(__bean.getPoliticaCustodia());
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    this.setCrearUsuaris(__bean.isCrearUsuaris());
    this.setTipusRevisors(__bean.getTipusRevisors());
    // Fitxer
    this.setLogoSegell(FitxerJPA.toJPA(__bean.getLogoSegell()));
	}

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
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

	public int getPoliticaDePluginFirmaWeb() {
		return(politicaDePluginFirmaWeb);
	};
	public void setPoliticaDePluginFirmaWeb(int _politicaDePluginFirmaWeb_) {
		this.politicaDePluginFirmaWeb = _politicaDePluginFirmaWeb_;
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

	public boolean isCrearUsuaris() {
		return(crearUsuaris);
	};
	public void setCrearUsuaris(boolean _crearUsuaris_) {
		this.crearUsuaris = _crearUsuaris_;
	};

	public java.lang.Boolean getTipusRevisors() {
		return(tipusRevisors);
	};
	public void setTipusRevisors(java.lang.Boolean _tipusRevisors_) {
		this.tipusRevisors = _tipusRevisors_;
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


// EXP  Field:usuariaplicacioid | Table: pfi_perfilsperusrapp | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariAplicacio")
    private Set<PerfilsPerUsuariAplicacioJPA> perfilsPerUsuariAplicacios = new HashSet<PerfilsPerUsuariAplicacioJPA>(0);
    public  Set<PerfilsPerUsuariAplicacioJPA> getPerfilsPerUsuariAplicacios() {
    return this.perfilsPerUsuariAplicacios;
  }

    public void setPerfilsPerUsuariAplicacios(Set<PerfilsPerUsuariAplicacioJPA> perfilsPerUsuariAplicacios) {
      this.perfilsPerUsuariAplicacios = perfilsPerUsuariAplicacios;
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


// EXP  Field:usuariaplicacioid | Table: pfi_tipusdocument | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariAplicacio")
    private Set<TipusDocumentJPA> tipusDocuments = new HashSet<TipusDocumentJPA>(0);
    public  Set<TipusDocumentJPA> getTipusDocuments() {
    return this.tipusDocuments;
  }

    public void setTipusDocuments(Set<TipusDocumentJPA> tipusDocuments) {
      this.tipusDocuments = tipusDocuments;
    }


// IMP Field:entitatid | Table: pfi_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_usrapp_entitat_fk"))
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }

// IMP Field:idiomaid | Table: pfi_idioma | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idiomaid", referencedColumnName ="idiomaID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_usrapp_idioma_fk"))
    private IdiomaJPA idioma;

    public IdiomaJPA getIdioma() {
    return this.idioma;
  }

    public  void setIdioma(IdiomaJPA idioma) {
    this.idioma = idioma;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "logosegellid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_usrapp_fitxer_fk"))
    private FitxerJPA logoSegell;

    public FitxerJPA getLogoSegell() {
    return this.logoSegell;
  }

    public  void setLogoSegell(FitxerJPA logoSegell) {
    this.logoSegell = logoSegell;
  }

// IMP Field:custodiainfoid | Table: pfi_custodiainfo | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custodiainfoid", referencedColumnName ="custodiaInfoID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_usrapp_custodia_fk"))
    private CustodiaInfoJPA custodiaInfo;

    public CustodiaInfoJPA getCustodiaInfo() {
    return this.custodiaInfo;
  }

    public  void setCustodiaInfo(CustodiaInfoJPA custodiaInfo) {
    this.custodiaInfo = custodiaInfo;
  }


 // ---------------  STATIC METHODS ------------------
  public static UsuariAplicacioJPA toJPA(UsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    UsuariAplicacioJPA __tmp = new UsuariAplicacioJPA();
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setEmailAdmin(__bean.getEmailAdmin());
    __tmp.setCallbackVersio(__bean.getCallbackVersio());
    __tmp.setCallbackURL(__bean.getCallbackURL());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    __tmp.setPoliticaDePluginFirmaWeb(__bean.getPoliticaDePluginFirmaWeb());
    __tmp.setPoliticaCustodia(__bean.getPoliticaCustodia());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setCrearUsuaris(__bean.isCrearUsuaris());
    __tmp.setTipusRevisors(__bean.getTipusRevisors());
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
    if(!"TipusDocumentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusDocuments) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusDocuments())) ) {
      __tmp.setTipusDocuments(TipusDocumentJPA.copyJPA(__jpa.getTipusDocuments(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"PlantillaFluxDeFirmesJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plantillaFluxDeFirmess) || org.hibernate.Hibernate.isInitialized(__jpa.getPlantillaFluxDeFirmess())) ) {
      __tmp.setPlantillaFluxDeFirmess(PlantillaFluxDeFirmesJPA.copyJPA(__jpa.getPlantillaFluxDeFirmess(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitats) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitats())) ) {
      __tmp.setEntitats(EntitatJPA.copyJPA(__jpa.getEntitats(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"CustodiaInfoJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfos) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfos())) ) {
      __tmp.setCustodiaInfos(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfos(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"PerfilsPerUsuariAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilsPerUsuariAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilsPerUsuariAplicacios())) ) {
      __tmp.setPerfilsPerUsuariAplicacios(PerfilsPerUsuariAplicacioJPA.copyJPA(__jpa.getPerfilsPerUsuariAplicacios(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirmas) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirmas())) ) {
      __tmp.setPeticioDeFirmas(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirmas(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"PluginFirmaWebPerUsuariAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginFirmaWebPerUsuariAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginFirmaWebPerUsuariAplicacios())) ) {
      __tmp.setPluginFirmaWebPerUsuariAplicacios(PluginFirmaWebPerUsuariAplicacioJPA.copyJPA(__jpa.getPluginFirmaWebPerUsuariAplicacios(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"IdiomaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.idioma) || org.hibernate.Hibernate.isInitialized(__jpa.getIdioma()) ) ) {
      __tmp.setIdioma(IdiomaJPA.copyJPA(__jpa.getIdioma(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    if(!"CustodiaInfoJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfo) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfo()) ) ) {
      __tmp.setCustodiaInfo(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfo(), __alreadyCopied,"UsuariAplicacioJPA"));
    }

    return __tmp;
  }




}
