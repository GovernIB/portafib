
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.UniqueConstraint;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "UsuariEntitatJPA")
@Table(name = "pfi_usuarientitat" , indexes = { 
        @Index(name="pfi_usuarientitat_pk_i", columnList = "usuarientitatid"),
        @Index(name="pfi_usrentitat_personaid_fk_i", columnList = "usuaripersonaid"),
        @Index(name="pfi_usrentitat_entitatid_fk_i", columnList = "entitatid"),
        @Index(name="pfi_usrentitat_logosegid_fk_i", columnList = "logosegellid"),
        @Index(name="pfi_usrentitat_custinfo_fk_i", columnList = "custodiainfoid")},
           uniqueConstraints = {
            @UniqueConstraint(name="pfi_usrentitat_perentcar_uk", columnNames={"usuaripersonaid","entitatid","carrec"}) } )
@SequenceGenerator(name="USUARIENTITAT_SEQ", sequenceName="pfi_usuarientitat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariEntitatJPA implements UsuariEntitat {

    @Id
    @Column(name="usuarientitatid",nullable = false,length = 101)
    java.lang.String usuariEntitatID;

    @Column(name="carrec",length = 150)
    java.lang.String carrec;

    @Column(name="usuaripersonaid",nullable = false,length = 50)
    java.lang.String usuariPersonaID;

    @Column(name="entitatid",nullable = false,length = 50)
    java.lang.String entitatID;

    @Column(name="actiu",nullable = false,length = 1)
    boolean actiu = true;

    @Column(name="email",length = 100)
    java.lang.String email;

    @Column(name="logosegellid",length = 19)
    java.lang.Long logoSegellID;

    @Column(name="predeterminat",nullable = false,length = 1)
    boolean predeterminat = false;

    @Column(name="rebretotselsavisos",nullable = false,length = 1)
    boolean rebreTotsElsAvisos = false;

  /**  0 - Només plugins de l''entitat, 1 - Plugins de l''entitat més plugins addicionals (afegir o llevar), 2 - Només plugins addicionals (Només els que tenguin marcat afegir)' */
    @org.hibernate.annotations.ColumnDefault("0")
    @Column(name="politicadepluginfirmaweb",nullable = false,length = 10)
    int politicaDePluginFirmaWeb = 0;

  /** -1: el que digui l'entitat, 0: No permetre, 1:Només Plantilles de l''Entitat (No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat (Per defecte Actiu), 4: Opcional plantilla Entitat (Per defecte NO Actiu), 5: Llibertat Total (selecció, edició i us) */
    @Column(name="politicacustodia",nullable = false,length = 10)
    int politicaCustodia;

  /** -1: el que digui l'entitat, 0: No permetre, 1: Només Plantilles de l''Entitat (No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat, 4: Opcional plantilla Entitat, 5: Llibertat Total (selecció, edició i us), 6: Custòdia de la Configuració de usuariAplicacio */
    @Column(name="custodiainfoid",length = 19)
    java.lang.Long custodiaInfoID;



  /** Constructor Buit */
  public UsuariEntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariEntitatJPA(java.lang.String usuariEntitatID , java.lang.String carrec , java.lang.String usuariPersonaID , java.lang.String entitatID , boolean actiu , java.lang.String email , java.lang.Long logoSegellID , boolean predeterminat , boolean rebreTotsElsAvisos , int politicaDePluginFirmaWeb , int politicaCustodia , java.lang.Long custodiaInfoID) {
    this.usuariEntitatID=usuariEntitatID;
    this.carrec=carrec;
    this.usuariPersonaID=usuariPersonaID;
    this.entitatID=entitatID;
    this.actiu=actiu;
    this.email=email;
    this.logoSegellID=logoSegellID;
    this.predeterminat=predeterminat;
    this.rebreTotsElsAvisos=rebreTotsElsAvisos;
    this.politicaDePluginFirmaWeb=politicaDePluginFirmaWeb;
    this.politicaCustodia=politicaCustodia;
    this.custodiaInfoID=custodiaInfoID;
}
  /** Constructor dels valors Not Null */
  public UsuariEntitatJPA(java.lang.String usuariEntitatID , java.lang.String usuariPersonaID , java.lang.String entitatID , boolean actiu , boolean predeterminat , boolean rebreTotsElsAvisos , int politicaDePluginFirmaWeb , int politicaCustodia) {
    this.usuariEntitatID=usuariEntitatID;
    this.usuariPersonaID=usuariPersonaID;
    this.entitatID=entitatID;
    this.actiu=actiu;
    this.predeterminat=predeterminat;
    this.rebreTotsElsAvisos=rebreTotsElsAvisos;
    this.politicaDePluginFirmaWeb=politicaDePluginFirmaWeb;
    this.politicaCustodia=politicaCustodia;
}
  public UsuariEntitatJPA(UsuariEntitat __bean) {
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setCarrec(__bean.getCarrec());
    this.setUsuariPersonaID(__bean.getUsuariPersonaID());
    this.setEntitatID(__bean.getEntitatID());
    this.setActiu(__bean.isActiu());
    this.setEmail(__bean.getEmail());
    this.setLogoSegellID(__bean.getLogoSegellID());
    this.setPredeterminat(__bean.isPredeterminat());
    this.setRebreTotsElsAvisos(__bean.isRebreTotsElsAvisos());
    this.setPoliticaDePluginFirmaWeb(__bean.getPoliticaDePluginFirmaWeb());
    this.setPoliticaCustodia(__bean.getPoliticaCustodia());
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    // Fitxer
    this.setLogoSegell(FitxerJPA.toJPA(__bean.getLogoSegell()));
	}

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public java.lang.String getCarrec() {
		return(carrec);
	};
	public void setCarrec(java.lang.String _carrec_) {
		this.carrec = _carrec_;
	};

	public java.lang.String getUsuariPersonaID() {
		return(usuariPersonaID);
	};
	public void setUsuariPersonaID(java.lang.String _usuariPersonaID_) {
		this.usuariPersonaID = _usuariPersonaID_;
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

	public java.lang.String getEmail() {
		return(email);
	};
	public void setEmail(java.lang.String _email_) {
		this.email = _email_;
	};

	public java.lang.Long getLogoSegellID() {
		return(logoSegellID);
	};
	public void setLogoSegellID(java.lang.Long _logoSegellID_) {
		this.logoSegellID = _logoSegellID_;
	};

	public boolean isPredeterminat() {
		return(predeterminat);
	};
	public void setPredeterminat(boolean _predeterminat_) {
		this.predeterminat = _predeterminat_;
	};

	public boolean isRebreTotsElsAvisos() {
		return(rebreTotsElsAvisos);
	};
	public void setRebreTotsElsAvisos(boolean _rebreTotsElsAvisos_) {
		this.rebreTotsElsAvisos = _rebreTotsElsAvisos_;
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof UsuariEntitat) {
      UsuariEntitat __instance = (UsuariEntitat)__obj;
      __result = true;
      if (this.getUsuariEntitatID() == null) {
        __result = __result && (__instance.getUsuariEntitatID() == null);
      } else {
        __result = __result && this.getUsuariEntitatID().equals(__instance.getUsuariEntitatID()) ;
      }

    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:colaboradordelegatid | Table: pfi_colaboraciodelegacio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "colaboradorDelegatID")
    private Set<ColaboracioDelegacioJPA> colaboracioDelegacio_colaboradordelegatids = new HashSet<ColaboracioDelegacioJPA>(0);
    public  Set<ColaboracioDelegacioJPA> getColaboracioDelegacio_colaboradordelegatids() {
    return this.colaboracioDelegacio_colaboradordelegatids;
  }

    public void setColaboracioDelegacio_colaboradordelegatids(Set<ColaboracioDelegacioJPA> colaboracioDelegacio_colaboradordelegatids) {
      this.colaboracioDelegacio_colaboradordelegatids = colaboracioDelegacio_colaboradordelegatids;
    }


// EXP  Field:destinatariid | Table: pfi_colaboraciodelegacio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "destinatariID")
    private Set<ColaboracioDelegacioJPA> colaboracioDelegacio_destinatariids = new HashSet<ColaboracioDelegacioJPA>(0);
    public  Set<ColaboracioDelegacioJPA> getColaboracioDelegacio_destinatariids() {
    return this.colaboracioDelegacio_destinatariids;
  }

    public void setColaboracioDelegacio_destinatariids(Set<ColaboracioDelegacioJPA> colaboracioDelegacio_destinatariids) {
      this.colaboracioDelegacio_destinatariids = colaboracioDelegacio_destinatariids;
    }


// EXP  Field:usuarientitatid | Table: pfi_custodiainfo | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
    private Set<CustodiaInfoJPA> custodiaInfos = new HashSet<CustodiaInfoJPA>(0);
    public  Set<CustodiaInfoJPA> getCustodiaInfos() {
    return this.custodiaInfos;
  }

    public void setCustodiaInfos(Set<CustodiaInfoJPA> custodiaInfos) {
      this.custodiaInfos = custodiaInfos;
    }


// EXP  Field:usuarientitatid | Table: pfi_estatdefirma | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
    private Set<EstatDeFirmaJPA> estatDeFirmas = new HashSet<EstatDeFirmaJPA>(0);
    public  Set<EstatDeFirmaJPA> getEstatDeFirmas() {
    return this.estatDeFirmas;
  }

    public void setEstatDeFirmas(Set<EstatDeFirmaJPA> estatDeFirmas) {
      this.estatDeFirmas = estatDeFirmas;
    }


// EXP  Field:destinatariid | Table: pfi_firma | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
    private Set<FirmaJPA> firmas = new HashSet<FirmaJPA>(0);
    public  Set<FirmaJPA> getFirmas() {
    return this.firmas;
  }

    public void setFirmas(Set<FirmaJPA> firmas) {
      this.firmas = firmas;
    }


// EXP  Field:usuarientitatid | Table: pfi_grupentitatusuarientitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
    private Set<GrupEntitatUsuariEntitatJPA> grupEntitatUsuariEntitats = new HashSet<GrupEntitatUsuariEntitatJPA>(0);
    public  Set<GrupEntitatUsuariEntitatJPA> getGrupEntitatUsuariEntitats() {
    return this.grupEntitatUsuariEntitats;
  }

    public void setGrupEntitatUsuariEntitats(Set<GrupEntitatUsuariEntitatJPA> grupEntitatUsuariEntitats) {
      this.grupEntitatUsuariEntitats = grupEntitatUsuariEntitats;
    }


// EXP  Field:usuarientitatid | Table: pfi_permisusuariplantilla | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
    private Set<PermisUsuariPlantillaJPA> permisUsuariPlantillas = new HashSet<PermisUsuariPlantillaJPA>(0);
    public  Set<PermisUsuariPlantillaJPA> getPermisUsuariPlantillas() {
    return this.permisUsuariPlantillas;
  }

    public void setPermisUsuariPlantillas(Set<PermisUsuariPlantillaJPA> permisUsuariPlantillas) {
      this.permisUsuariPlantillas = permisUsuariPlantillas;
    }


// EXP  Field:solicitantpersona2id | Table: pfi_peticiodefirma | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitantUsuariEntitat2ID")
    private Set<PeticioDeFirmaJPA> peticioDeFirma_solicitantpersona2ids = new HashSet<PeticioDeFirmaJPA>(0);
    public  Set<PeticioDeFirmaJPA> getPeticioDeFirma_solicitantpersona2ids() {
    return this.peticioDeFirma_solicitantpersona2ids;
  }

    public void setPeticioDeFirma_solicitantpersona2ids(Set<PeticioDeFirmaJPA> peticioDeFirma_solicitantpersona2ids) {
      this.peticioDeFirma_solicitantpersona2ids = peticioDeFirma_solicitantpersona2ids;
    }


// EXP  Field:solicitantpersona3id | Table: pfi_peticiodefirma | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitantUsuariEntitat3ID")
    private Set<PeticioDeFirmaJPA> peticioDeFirma_solicitantpersona3ids = new HashSet<PeticioDeFirmaJPA>(0);
    public  Set<PeticioDeFirmaJPA> getPeticioDeFirma_solicitantpersona3ids() {
    return this.peticioDeFirma_solicitantpersona3ids;
  }

    public void setPeticioDeFirma_solicitantpersona3ids(Set<PeticioDeFirmaJPA> peticioDeFirma_solicitantpersona3ids) {
      this.peticioDeFirma_solicitantpersona3ids = peticioDeFirma_solicitantpersona3ids;
    }


// EXP  Field:usuarientitatid | Table: pfi_peticiodefirma | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitantUsuariEntitat1ID")
    private Set<PeticioDeFirmaJPA> peticioDeFirma_usuarientitatids = new HashSet<PeticioDeFirmaJPA>(0);
    public  Set<PeticioDeFirmaJPA> getPeticioDeFirma_usuarientitatids() {
    return this.peticioDeFirma_usuarientitatids;
  }

    public void setPeticioDeFirma_usuarientitatids(Set<PeticioDeFirmaJPA> peticioDeFirma_usuarientitatids) {
      this.peticioDeFirma_usuarientitatids = peticioDeFirma_usuarientitatids;
    }


// EXP  Field:usuarientitatid | Table: pfi_plantillafluxdefirmes | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
    private Set<PlantillaFluxDeFirmesJPA> plantillaFluxDeFirmess = new HashSet<PlantillaFluxDeFirmesJPA>(0);
    public  Set<PlantillaFluxDeFirmesJPA> getPlantillaFluxDeFirmess() {
    return this.plantillaFluxDeFirmess;
  }

    public void setPlantillaFluxDeFirmess(Set<PlantillaFluxDeFirmesJPA> plantillaFluxDeFirmess) {
      this.plantillaFluxDeFirmess = plantillaFluxDeFirmess;
    }


// EXP  Field:usuarientitatid | Table: pfi_pluginfirmawebperusrent | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
    private Set<PluginFirmaWebPerUsuariEntitatJPA> pluginFirmaWebPerUsuariEntitats = new HashSet<PluginFirmaWebPerUsuariEntitatJPA>(0);
    public  Set<PluginFirmaWebPerUsuariEntitatJPA> getPluginFirmaWebPerUsuariEntitats() {
    return this.pluginFirmaWebPerUsuariEntitats;
  }

    public void setPluginFirmaWebPerUsuariEntitats(Set<PluginFirmaWebPerUsuariEntitatJPA> pluginFirmaWebPerUsuariEntitats) {
      this.pluginFirmaWebPerUsuariEntitats = pluginFirmaWebPerUsuariEntitats;
    }


// EXP  Field:usuarientitatid | Table: pfi_rebreavis | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
    private Set<RebreAvisJPA> rebreAviss = new HashSet<RebreAvisJPA>(0);
    public  Set<RebreAvisJPA> getRebreAviss() {
    return this.rebreAviss;
  }

    public void setRebreAviss(Set<RebreAvisJPA> rebreAviss) {
      this.rebreAviss = rebreAviss;
    }


// EXP  Field:destinatariid | Table: pfi_revisordedestinatari | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "destinatariID")
    private Set<RevisorDeDestinatariJPA> revisorDeDestinatari_destinatariids = new HashSet<RevisorDeDestinatariJPA>(0);
    public  Set<RevisorDeDestinatariJPA> getRevisorDeDestinatari_destinatariids() {
    return this.revisorDeDestinatari_destinatariids;
  }

    public void setRevisorDeDestinatari_destinatariids(Set<RevisorDeDestinatariJPA> revisorDeDestinatari_destinatariids) {
      this.revisorDeDestinatari_destinatariids = revisorDeDestinatari_destinatariids;
    }


// EXP  Field:revisorid | Table: pfi_revisordedestinatari | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "revisorID")
    private Set<RevisorDeDestinatariJPA> revisorDeDestinatari_revisorids = new HashSet<RevisorDeDestinatariJPA>(0);
    public  Set<RevisorDeDestinatariJPA> getRevisorDeDestinatari_revisorids() {
    return this.revisorDeDestinatari_revisorids;
  }

    public void setRevisorDeDestinatari_revisorids(Set<RevisorDeDestinatariJPA> revisorDeDestinatari_revisorids) {
      this.revisorDeDestinatari_revisorids = revisorDeDestinatari_revisorids;
    }


// EXP  Field:usuarientitatid | Table: pfi_revisordefirma | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
    private Set<RevisorDeFirmaJPA> revisorDeFirmas = new HashSet<RevisorDeFirmaJPA>(0);
    public  Set<RevisorDeFirmaJPA> getRevisorDeFirmas() {
    return this.revisorDeFirmas;
  }

    public void setRevisorDeFirmas(Set<RevisorDeFirmaJPA> revisorDeFirmas) {
      this.revisorDeFirmas = revisorDeFirmas;
    }


// EXP  Field:usuarientitatid | Table: pfi_roleusuarientitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
    private Set<RoleUsuariEntitatJPA> roleUsuariEntitats = new HashSet<RoleUsuariEntitatJPA>(0);
    public  Set<RoleUsuariEntitatJPA> getRoleUsuariEntitats() {
    return this.roleUsuariEntitats;
  }

    public void setRoleUsuariEntitats(Set<RoleUsuariEntitatJPA> roleUsuariEntitats) {
      this.roleUsuariEntitats = roleUsuariEntitats;
    }


// EXP  Field:favoritid | Table: pfi_usuarientitatfavorit | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "favoritID")
    private Set<UsuariEntitatFavoritJPA> usuariEntitatFavorit_favoritids = new HashSet<UsuariEntitatFavoritJPA>(0);
    public  Set<UsuariEntitatFavoritJPA> getUsuariEntitatFavorit_favoritids() {
    return this.usuariEntitatFavorit_favoritids;
  }

    public void setUsuariEntitatFavorit_favoritids(Set<UsuariEntitatFavoritJPA> usuariEntitatFavorit_favoritids) {
      this.usuariEntitatFavorit_favoritids = usuariEntitatFavorit_favoritids;
    }


// EXP  Field:origenid | Table: pfi_usuarientitatfavorit | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "origenID")
    private Set<UsuariEntitatFavoritJPA> usuariEntitatFavorit_origenids = new HashSet<UsuariEntitatFavoritJPA>(0);
    public  Set<UsuariEntitatFavoritJPA> getUsuariEntitatFavorit_origenids() {
    return this.usuariEntitatFavorit_origenids;
  }

    public void setUsuariEntitatFavorit_origenids(Set<UsuariEntitatFavoritJPA> usuariEntitatFavorit_origenids) {
      this.usuariEntitatFavorit_origenids = usuariEntitatFavorit_origenids;
    }


// IMP Field:usuaripersonaid | Table: pfi_usuaripersona | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuaripersonaid", referencedColumnName ="usuariPersonaID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_usrentitat_persona_fk"))
    private UsuariPersonaJPA usuariPersona;

    public UsuariPersonaJPA getUsuariPersona() {
    return this.usuariPersona;
  }

    public  void setUsuariPersona(UsuariPersonaJPA usuariPersona) {
    this.usuariPersona = usuariPersona;
  }

// IMP Field:entitatid | Table: pfi_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_usrentitat_entitat_fk"))
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "logosegellid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_usrentitat_fitxer_fk"))
    private FitxerJPA logoSegell;

    public FitxerJPA getLogoSegell() {
    return this.logoSegell;
  }

    public  void setLogoSegell(FitxerJPA logoSegell) {
    this.logoSegell = logoSegell;
  }

// IMP Field:custodiainfoid | Table: pfi_custodiainfo | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custodiainfoid", referencedColumnName ="custodiaInfoID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_usrentitat_custodia_fk"))
    private CustodiaInfoJPA custodiaInfo;

    public CustodiaInfoJPA getCustodiaInfo() {
    return this.custodiaInfo;
  }

    public  void setCustodiaInfo(CustodiaInfoJPA custodiaInfo) {
    this.custodiaInfo = custodiaInfo;
  }


 // ---------------  STATIC METHODS ------------------
  public static UsuariEntitatJPA toJPA(UsuariEntitat __bean) {
    if (__bean == null) { return null;}
    UsuariEntitatJPA __tmp = new UsuariEntitatJPA();
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setCarrec(__bean.getCarrec());
    __tmp.setUsuariPersonaID(__bean.getUsuariPersonaID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setEmail(__bean.getEmail());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    __tmp.setPredeterminat(__bean.isPredeterminat());
    __tmp.setRebreTotsElsAvisos(__bean.isRebreTotsElsAvisos());
    __tmp.setPoliticaDePluginFirmaWeb(__bean.getPoliticaDePluginFirmaWeb());
    __tmp.setPoliticaCustodia(__bean.getPoliticaCustodia());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    // Fitxer
    __tmp.setLogoSegell(FitxerJPA.toJPA(__bean.getLogoSegell()));
		return __tmp;
	}


  public static UsuariEntitatJPA copyJPA(UsuariEntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariEntitatJPA> copyJPA(java.util.Set<UsuariEntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<UsuariEntitatJPA> __tmpSet = (java.util.Set<UsuariEntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariEntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariEntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariEntitatJPA copyJPA(UsuariEntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariEntitatJPA __tmp = (UsuariEntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"EstatDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.estatDeFirmas) || org.hibernate.Hibernate.isInitialized(__jpa.getEstatDeFirmas())) ) {
      __tmp.setEstatDeFirmas(EstatDeFirmaJPA.copyJPA(__jpa.getEstatDeFirmas(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"FirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.firmas) || org.hibernate.Hibernate.isInitialized(__jpa.getFirmas())) ) {
      __tmp.setFirmas(FirmaJPA.copyJPA(__jpa.getFirmas(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"PlantillaFluxDeFirmesJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plantillaFluxDeFirmess) || org.hibernate.Hibernate.isInitialized(__jpa.getPlantillaFluxDeFirmess())) ) {
      __tmp.setPlantillaFluxDeFirmess(PlantillaFluxDeFirmesJPA.copyJPA(__jpa.getPlantillaFluxDeFirmess(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"ColaboracioDelegacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.colaboracioDelegacio_destinatariids) || org.hibernate.Hibernate.isInitialized(__jpa.getColaboracioDelegacio_destinatariids())) ) {
      __tmp.setColaboracioDelegacio_destinatariids(ColaboracioDelegacioJPA.copyJPA(__jpa.getColaboracioDelegacio_destinatariids(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"GrupEntitatUsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grupEntitatUsuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getGrupEntitatUsuariEntitats())) ) {
      __tmp.setGrupEntitatUsuariEntitats(GrupEntitatUsuariEntitatJPA.copyJPA(__jpa.getGrupEntitatUsuariEntitats(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"PermisUsuariPlantillaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.permisUsuariPlantillas) || org.hibernate.Hibernate.isInitialized(__jpa.getPermisUsuariPlantillas())) ) {
      __tmp.setPermisUsuariPlantillas(PermisUsuariPlantillaJPA.copyJPA(__jpa.getPermisUsuariPlantillas(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"CustodiaInfoJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfos) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfos())) ) {
      __tmp.setCustodiaInfos(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfos(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"RoleUsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.roleUsuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getRoleUsuariEntitats())) ) {
      __tmp.setRoleUsuariEntitats(RoleUsuariEntitatJPA.copyJPA(__jpa.getRoleUsuariEntitats(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"RevisorDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.revisorDeFirmas) || org.hibernate.Hibernate.isInitialized(__jpa.getRevisorDeFirmas())) ) {
      __tmp.setRevisorDeFirmas(RevisorDeFirmaJPA.copyJPA(__jpa.getRevisorDeFirmas(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"UsuariEntitatFavoritJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitatFavorit_favoritids) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitatFavorit_favoritids())) ) {
      __tmp.setUsuariEntitatFavorit_favoritids(UsuariEntitatFavoritJPA.copyJPA(__jpa.getUsuariEntitatFavorit_favoritids(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"RevisorDeDestinatariJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.revisorDeDestinatari_destinatariids) || org.hibernate.Hibernate.isInitialized(__jpa.getRevisorDeDestinatari_destinatariids())) ) {
      __tmp.setRevisorDeDestinatari_destinatariids(RevisorDeDestinatariJPA.copyJPA(__jpa.getRevisorDeDestinatari_destinatariids(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"UsuariEntitatFavoritJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitatFavorit_origenids) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitatFavorit_origenids())) ) {
      __tmp.setUsuariEntitatFavorit_origenids(UsuariEntitatFavoritJPA.copyJPA(__jpa.getUsuariEntitatFavorit_origenids(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"RebreAvisJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.rebreAviss) || org.hibernate.Hibernate.isInitialized(__jpa.getRebreAviss())) ) {
      __tmp.setRebreAviss(RebreAvisJPA.copyJPA(__jpa.getRebreAviss(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirma_solicitantpersona2ids) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirma_solicitantpersona2ids())) ) {
      __tmp.setPeticioDeFirma_solicitantpersona2ids(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirma_solicitantpersona2ids(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"ColaboracioDelegacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.colaboracioDelegacio_colaboradordelegatids) || org.hibernate.Hibernate.isInitialized(__jpa.getColaboracioDelegacio_colaboradordelegatids())) ) {
      __tmp.setColaboracioDelegacio_colaboradordelegatids(ColaboracioDelegacioJPA.copyJPA(__jpa.getColaboracioDelegacio_colaboradordelegatids(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"PluginFirmaWebPerUsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginFirmaWebPerUsuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginFirmaWebPerUsuariEntitats())) ) {
      __tmp.setPluginFirmaWebPerUsuariEntitats(PluginFirmaWebPerUsuariEntitatJPA.copyJPA(__jpa.getPluginFirmaWebPerUsuariEntitats(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirma_usuarientitatids) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirma_usuarientitatids())) ) {
      __tmp.setPeticioDeFirma_usuarientitatids(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirma_usuarientitatids(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"RevisorDeDestinatariJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.revisorDeDestinatari_revisorids) || org.hibernate.Hibernate.isInitialized(__jpa.getRevisorDeDestinatari_revisorids())) ) {
      __tmp.setRevisorDeDestinatari_revisorids(RevisorDeDestinatariJPA.copyJPA(__jpa.getRevisorDeDestinatari_revisorids(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirma_solicitantpersona3ids) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirma_solicitantpersona3ids())) ) {
      __tmp.setPeticioDeFirma_solicitantpersona3ids(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirma_solicitantpersona3ids(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"UsuariPersonaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariPersona) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariPersona()) ) ) {
      __tmp.setUsuariPersona(UsuariPersonaJPA.copyJPA(__jpa.getUsuariPersona(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"CustodiaInfoJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfo) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfo()) ) ) {
      __tmp.setCustodiaInfo(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfo(), __alreadyCopied,"UsuariEntitatJPA"));
    }

    return __tmp;
  }




}
