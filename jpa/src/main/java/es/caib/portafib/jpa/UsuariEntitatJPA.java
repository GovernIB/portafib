
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "pfi_usuarientitat"  , uniqueConstraints = {
            @UniqueConstraint( columnNames={"usuaripersonaid","entitatid","carrec"}) } )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class UsuariEntitatJPA implements UsuariEntitat {



private static final long serialVersionUID = 2031334771L;

	@Id
	@Index(name="pfi_usuarientitat_pk_i")
	@Column(name="usuarientitatid",nullable = false,length = 101)
	java.lang.String usuariEntitatID;

	@Column(name="carrec",length = 150)
	java.lang.String carrec;

	@Index(name="pfi_usrentitat_personaid_fk_i")
	@Column(name="usuaripersonaid",nullable = false,length = 50)
	java.lang.String usuariPersonaID;

	@Index(name="pfi_usrentitat_entitatid_fk_i")
	@Column(name="entitatid",nullable = false,length = 50)
	java.lang.String entitatID;

	@Column(name="actiu",nullable = false,length = 1)
	boolean actiu;

	@Column(name="email",length = 100)
	java.lang.String email;

	@Index(name="pfi_usrentitat_logosegid_fk_i")
	@Column(name="logosegellid",length = 19)
	java.lang.Long logoSegellID;

	@Column(name="predeterminat",nullable = false,length = 1)
	boolean predeterminat;

	@Column(name="rebretotselsavisos",nullable = false,length = 1)
	boolean rebreTotsElsAvisos;

  /** Indica si aquest ususari entitat pot custodiar les peticions de firma:
     + null = Només plantilles de Entitat
     + false = No pot custodiar
     + true = Pot emprar plantilles o establie el mateix usuari la configuració de custodia */
	@Column(name="potcustodiar",length = 1)
	java.lang.Boolean potCustodiar;



  /** Constructor Buit */
  public UsuariEntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariEntitatJPA(java.lang.String usuariEntitatID , java.lang.String carrec , java.lang.String usuariPersonaID , java.lang.String entitatID , boolean actiu , java.lang.String email , java.lang.Long logoSegellID , boolean predeterminat , boolean rebreTotsElsAvisos , java.lang.Boolean potCustodiar) {
    this.usuariEntitatID=usuariEntitatID;
    this.carrec=carrec;
    this.usuariPersonaID=usuariPersonaID;
    this.entitatID=entitatID;
    this.actiu=actiu;
    this.email=email;
    this.logoSegellID=logoSegellID;
    this.predeterminat=predeterminat;
    this.rebreTotsElsAvisos=rebreTotsElsAvisos;
    this.potCustodiar=potCustodiar;
}
  /** Constructor dels valors Not Null */
  public UsuariEntitatJPA(java.lang.String usuariEntitatID , java.lang.String usuariPersonaID , java.lang.String entitatID , boolean actiu , boolean predeterminat , boolean rebreTotsElsAvisos) {
    this.usuariEntitatID=usuariEntitatID;
    this.usuariPersonaID=usuariPersonaID;
    this.entitatID=entitatID;
    this.actiu=actiu;
    this.predeterminat=predeterminat;
    this.rebreTotsElsAvisos=rebreTotsElsAvisos;
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
    this.setPotCustodiar(__bean.getPotCustodiar());
    // Fitxer
    this.setLogoSegell(FitxerJPA.toJPA(__bean.getLogoSegell()));
	}

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
    __tmp.setPotCustodiar(__bean.getPotCustodiar());
    // Fitxer
    __tmp.setLogoSegell(FitxerJPA.toJPA(__bean.getLogoSegell()));
		return __tmp;
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

	public java.lang.Boolean getPotCustodiar() {
		return(potCustodiar);
	};
	public void setPotCustodiar(java.lang.Boolean _potCustodiar_) {
		this.potCustodiar = _potCustodiar_;
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

// EXP  Field:usuarientitatid | Table: pfi_bitacola | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
	private Set<BitacolaJPA> bitacolas = new HashSet<BitacolaJPA>(0);
	public  Set<BitacolaJPA> getBitacolas() {
    return this.bitacolas;
  }

	public void setBitacolas(Set<BitacolaJPA> bitacolas) {
	  this.bitacolas = bitacolas;
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


// EXP  Field:usuarientitatid | Table: pfi_peticiodefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
	private Set<PeticioDeFirmaJPA> peticioDeFirmas = new HashSet<PeticioDeFirmaJPA>(0);
	public  Set<PeticioDeFirmaJPA> getPeticioDeFirmas() {
    return this.peticioDeFirmas;
  }

	public void setPeticioDeFirmas(Set<PeticioDeFirmaJPA> peticioDeFirmas) {
	  this.peticioDeFirmas = peticioDeFirmas;
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


// EXP  Field:usuarientitatid | Table: pfi_rebreavis | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitat")
	private Set<RebreAvisJPA> rebreAviss = new HashSet<RebreAvisJPA>(0);
	public  Set<RebreAvisJPA> getRebreAviss() {
    return this.rebreAviss;
  }

	public void setRebreAviss(Set<RebreAvisJPA> rebreAviss) {
	  this.rebreAviss = rebreAviss;
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
	@ForeignKey(name="pfi_usrentitat_persona_fk")
	@JoinColumn(name = "usuaripersonaid", referencedColumnName ="usuariPersonaID", nullable = false, insertable=false, updatable=false)
	private UsuariPersonaJPA usuariPersona;

	public UsuariPersonaJPA getUsuariPersona() {
    return this.usuariPersona;
  }

	public  void setUsuariPersona(UsuariPersonaJPA usuariPersona) {
    this.usuariPersona = usuariPersona;
  }

// IMP Field:entitatid | Table: pfi_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_usrentitat_entitat_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pfi_usrentitat_fitxer_fk")
	@JoinColumn(name = "logosegellid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA logoSegell;

	public FitxerJPA getLogoSegell() {
    return this.logoSegell;
  }

	public  void setLogoSegell(FitxerJPA logoSegell) {
    this.logoSegell = logoSegell;
  }



}
