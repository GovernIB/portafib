
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity
@Table(name = "pfi_colaboraciodelegacio" , indexes = { 
        @Index(name="pfi_colaboraciodelegacio_pk_i", columnList = "colaboraciodelegacioid"),
        @Index(name="pfi_colabdeleg_destid_fk_i", columnList = "destinatariid"),
        @Index(name="pfi_colabdeleg_coldelid_fk_i", columnList = "colaboradordelegatid"),
        @Index(name="pfi_colabdeleg_fitautoid_fk_i", columnList = "fitxerautoritzacioid")})
@SequenceGenerator(name="COLABORACIODELEGACIO_SEQ", sequenceName="pfi_colaboraciodelegacio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class ColaboracioDelegacioJPA implements ColaboracioDelegacio {



private static final long serialVersionUID = -637711502L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COLABORACIODELEGACIO_SEQ")
    @Column(name="colaboraciodelegacioid",nullable = false,length = 19)
    long colaboracioDelegacioID;

    @Column(name="destinatariid",nullable = false,length = 101)
    java.lang.String destinatariID;

    @Column(name="colaboradordelegatid",nullable = false,length = 101)
    java.lang.String colaboradorDelegatID;

    @Column(name="esdelegat",nullable = false,length = 1)
    boolean esDelegat;

    @Column(name="motiu",nullable = false,length = 60)
    java.lang.String motiu;

    @Column(name="descripcio",length = 255)
    java.lang.String descripcio;

    @Column(name="datainici",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataInici;

    @Column(name="datafi",length = 29,precision = 6)
    java.sql.Timestamp dataFi;

    @Column(name="activa",nullable = false,length = 1)
    boolean activa;

  /** Només es per col.laborador i indica si es obligatori que aquell col.laborador digui la seva. */
    @Column(name="revisor",nullable = false,length = 1)
    boolean revisor;

    @Column(name="motiudeshabilitada",length = 255)
    java.lang.String motiuDeshabilitada;

    @Column(name="fitxerautoritzacioid",length = 19)
    java.lang.Long fitxerAutoritzacioID;



  /** Constructor Buit */
  public ColaboracioDelegacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public ColaboracioDelegacioJPA(long colaboracioDelegacioID , java.lang.String destinatariID , java.lang.String colaboradorDelegatID , boolean esDelegat , java.lang.String motiu , java.lang.String descripcio , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , boolean activa , boolean revisor , java.lang.String motiuDeshabilitada , java.lang.Long fitxerAutoritzacioID) {
    this.colaboracioDelegacioID=colaboracioDelegacioID;
    this.destinatariID=destinatariID;
    this.colaboradorDelegatID=colaboradorDelegatID;
    this.esDelegat=esDelegat;
    this.motiu=motiu;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.activa=activa;
    this.revisor=revisor;
    this.motiuDeshabilitada=motiuDeshabilitada;
    this.fitxerAutoritzacioID=fitxerAutoritzacioID;
}
  /** Constructor sense valors autoincrementals */
  public ColaboracioDelegacioJPA(java.lang.String destinatariID , java.lang.String colaboradorDelegatID , boolean esDelegat , java.lang.String motiu , java.lang.String descripcio , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , boolean activa , boolean revisor , java.lang.String motiuDeshabilitada , java.lang.Long fitxerAutoritzacioID) {
    this.destinatariID=destinatariID;
    this.colaboradorDelegatID=colaboradorDelegatID;
    this.esDelegat=esDelegat;
    this.motiu=motiu;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.activa=activa;
    this.revisor=revisor;
    this.motiuDeshabilitada=motiuDeshabilitada;
    this.fitxerAutoritzacioID=fitxerAutoritzacioID;
}
  /** Constructor dels valors Not Null */
  public ColaboracioDelegacioJPA(long colaboracioDelegacioID , java.lang.String destinatariID , java.lang.String colaboradorDelegatID , boolean esDelegat , java.lang.String motiu , java.sql.Timestamp dataInici , boolean activa , boolean revisor) {
    this.colaboracioDelegacioID=colaboracioDelegacioID;
    this.destinatariID=destinatariID;
    this.colaboradorDelegatID=colaboradorDelegatID;
    this.esDelegat=esDelegat;
    this.motiu=motiu;
    this.dataInici=dataInici;
    this.activa=activa;
    this.revisor=revisor;
}
  public ColaboracioDelegacioJPA(ColaboracioDelegacio __bean) {
    this.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    this.setDestinatariID(__bean.getDestinatariID());
    this.setColaboradorDelegatID(__bean.getColaboradorDelegatID());
    this.setEsDelegat(__bean.isEsDelegat());
    this.setMotiu(__bean.getMotiu());
    this.setDescripcio(__bean.getDescripcio());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setActiva(__bean.isActiva());
    this.setRevisor(__bean.isRevisor());
    this.setMotiuDeshabilitada(__bean.getMotiuDeshabilitada());
    this.setFitxerAutoritzacioID(__bean.getFitxerAutoritzacioID());
    // Fitxer
    this.setFitxerAutoritzacio(FitxerJPA.toJPA(__bean.getFitxerAutoritzacio()));
	}

	public long getColaboracioDelegacioID() {
		return(colaboracioDelegacioID);
	};
	public void setColaboracioDelegacioID(long _colaboracioDelegacioID_) {
		this.colaboracioDelegacioID = _colaboracioDelegacioID_;
	};

	public java.lang.String getDestinatariID() {
		return(destinatariID);
	};
	public void setDestinatariID(java.lang.String _destinatariID_) {
		this.destinatariID = _destinatariID_;
	};

	public java.lang.String getColaboradorDelegatID() {
		return(colaboradorDelegatID);
	};
	public void setColaboradorDelegatID(java.lang.String _colaboradorDelegatID_) {
		this.colaboradorDelegatID = _colaboradorDelegatID_;
	};

	public boolean isEsDelegat() {
		return(esDelegat);
	};
	public void setEsDelegat(boolean _esDelegat_) {
		this.esDelegat = _esDelegat_;
	};

	public java.lang.String getMotiu() {
		return(motiu);
	};
	public void setMotiu(java.lang.String _motiu_) {
		this.motiu = _motiu_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Timestamp getDataFi() {
		return(dataFi);
	};
	public void setDataFi(java.sql.Timestamp _dataFi_) {
		this.dataFi = _dataFi_;
	};

	public boolean isActiva() {
		return(activa);
	};
	public void setActiva(boolean _activa_) {
		this.activa = _activa_;
	};

	public boolean isRevisor() {
		return(revisor);
	};
	public void setRevisor(boolean _revisor_) {
		this.revisor = _revisor_;
	};

	public java.lang.String getMotiuDeshabilitada() {
		return(motiuDeshabilitada);
	};
	public void setMotiuDeshabilitada(java.lang.String _motiuDeshabilitada_) {
		this.motiuDeshabilitada = _motiuDeshabilitada_;
	};

	public java.lang.Long getFitxerAutoritzacioID() {
		return(fitxerAutoritzacioID);
	};
	public void setFitxerAutoritzacioID(java.lang.Long _fitxerAutoritzacioID_) {
		this.fitxerAutoritzacioID = _fitxerAutoritzacioID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof ColaboracioDelegacio) {
      ColaboracioDelegacio __instance = (ColaboracioDelegacio)__obj;
      __result = true;
      __result = __result && (this.getColaboracioDelegacioID() == __instance.getColaboracioDelegacioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:colaboraciodelegacioid | Table: pfi_estatdefirma | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "colaboracioDelegacio")
    private Set<EstatDeFirmaJPA> estatDeFirmas = new HashSet<EstatDeFirmaJPA>(0);
    public  Set<EstatDeFirmaJPA> getEstatDeFirmas() {
    return this.estatDeFirmas;
  }

    public void setEstatDeFirmas(Set<EstatDeFirmaJPA> estatDeFirmas) {
      this.estatDeFirmas = estatDeFirmas;
    }


// EXP  Field:colaboraciodelegacioid | Table: pfi_tipusdocumentcoladele | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "colaboracioDelegacio")
    private Set<TipusDocumentColaboracioDelegacioJPA> tipusDocumentColaboracioDelegacios = new HashSet<TipusDocumentColaboracioDelegacioJPA>(0);
    public  Set<TipusDocumentColaboracioDelegacioJPA> getTipusDocumentColaboracioDelegacios() {
    return this.tipusDocumentColaboracioDelegacios;
  }

    public void setTipusDocumentColaboracioDelegacios(Set<TipusDocumentColaboracioDelegacioJPA> tipusDocumentColaboracioDelegacios) {
      this.tipusDocumentColaboracioDelegacios = tipusDocumentColaboracioDelegacios;
    }


// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destinatariid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_colabdeleg_usrentitat_d_fk"))
    private UsuariEntitatJPA destinatari;

    public UsuariEntitatJPA getDestinatari() {
    return this.destinatari;
  }

    public  void setDestinatari(UsuariEntitatJPA destinatari) {
    this.destinatari = destinatari;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaboradordelegatid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_colabdeleg_usrentitat_c_fk"))
    private UsuariEntitatJPA colaboradorDelegat;

    public UsuariEntitatJPA getColaboradorDelegat() {
    return this.colaboradorDelegat;
  }

    public  void setColaboradorDelegat(UsuariEntitatJPA colaboradorDelegat) {
    this.colaboradorDelegat = colaboradorDelegat;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxerautoritzacioid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_colabdeleg_fitxer_fk"))
    private FitxerJPA fitxerAutoritzacio;

    public FitxerJPA getFitxerAutoritzacio() {
    return this.fitxerAutoritzacio;
  }

    public  void setFitxerAutoritzacio(FitxerJPA fitxerAutoritzacio) {
    this.fitxerAutoritzacio = fitxerAutoritzacio;
  }


 // ---------------  STATIC METHODS ------------------
  public static ColaboracioDelegacioJPA toJPA(ColaboracioDelegacio __bean) {
    if (__bean == null) { return null;}
    ColaboracioDelegacioJPA __tmp = new ColaboracioDelegacioJPA();
    __tmp.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    __tmp.setDestinatariID(__bean.getDestinatariID());
    __tmp.setColaboradorDelegatID(__bean.getColaboradorDelegatID());
    __tmp.setEsDelegat(__bean.isEsDelegat());
    __tmp.setMotiu(__bean.getMotiu());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setRevisor(__bean.isRevisor());
    __tmp.setMotiuDeshabilitada(__bean.getMotiuDeshabilitada());
    __tmp.setFitxerAutoritzacioID(__bean.getFitxerAutoritzacioID());
    // Fitxer
    __tmp.setFitxerAutoritzacio(FitxerJPA.toJPA(__bean.getFitxerAutoritzacio()));
		return __tmp;
	}


  public static ColaboracioDelegacioJPA copyJPA(ColaboracioDelegacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<ColaboracioDelegacioJPA> copyJPA(java.util.Set<ColaboracioDelegacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<ColaboracioDelegacioJPA> __tmpSet = (java.util.Set<ColaboracioDelegacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<ColaboracioDelegacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (ColaboracioDelegacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static ColaboracioDelegacioJPA copyJPA(ColaboracioDelegacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    ColaboracioDelegacioJPA __tmp = (ColaboracioDelegacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"EstatDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.estatDeFirmas) || org.hibernate.Hibernate.isInitialized(__jpa.getEstatDeFirmas())) ) {
      __tmp.setEstatDeFirmas(EstatDeFirmaJPA.copyJPA(__jpa.getEstatDeFirmas(), __alreadyCopied,"ColaboracioDelegacioJPA"));
    }
    if(!"TipusDocumentColaboracioDelegacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusDocumentColaboracioDelegacios) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusDocumentColaboracioDelegacios())) ) {
      __tmp.setTipusDocumentColaboracioDelegacios(TipusDocumentColaboracioDelegacioJPA.copyJPA(__jpa.getTipusDocumentColaboracioDelegacios(), __alreadyCopied,"ColaboracioDelegacioJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.destinatari) || org.hibernate.Hibernate.isInitialized(__jpa.getDestinatari()) ) ) {
      __tmp.setDestinatari(UsuariEntitatJPA.copyJPA(__jpa.getDestinatari(), __alreadyCopied,"ColaboracioDelegacioJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.colaboradorDelegat) || org.hibernate.Hibernate.isInitialized(__jpa.getColaboradorDelegat()) ) ) {
      __tmp.setColaboradorDelegat(UsuariEntitatJPA.copyJPA(__jpa.getColaboradorDelegat(), __alreadyCopied,"ColaboracioDelegacioJPA"));
    }

    return __tmp;
  }




}
