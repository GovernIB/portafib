
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity
@Table(name = "pfi_tipusdocument" )
@SequenceGenerator(name="TIPUSDOCUMENT_SEQ", sequenceName="pfi_tipusdocument_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TipusDocumentJPA implements TipusDocument {



private static final long serialVersionUID = -1473284441L;

    @Id
    @Index(name="pfi_tipusdocument_pk_i")
    @Column(name="tipusdocumentid",nullable = false,length = 19)
    long tipusDocumentID;

    @Index(name="pfi_tipusdocument_nom_fk_i")
    @Column(name="nom",nullable = false,length = 19)
    long nomID;

  /** Correspon només al tipus estandard (1 al 99) definits a les NTI */
    @Column(name="tipusdocumentbaseid",nullable = false,length = 19)
    long tipusDocumentBaseID;

    @Column(name="descripcio",length = 1000)
    java.lang.String descripcio;

  /** Els tipus de documents van associats a un UsuariMàquina */
    @Index(name="pfi_tipusdoc_usuariappid_fk_i")
    @Column(name="usuariaplicacioid",length = 50)
    java.lang.String usuariAplicacioID;



  /** Constructor Buit */
  public TipusDocumentJPA() {
  }

  /** Constructor amb tots els camps  */
  public TipusDocumentJPA(long tipusDocumentID , long nomID , long tipusDocumentBaseID , java.lang.String descripcio , java.lang.String usuariAplicacioID) {
    this.tipusDocumentID=tipusDocumentID;
    this.nomID=nomID;
    this.tipusDocumentBaseID=tipusDocumentBaseID;
    this.descripcio=descripcio;
    this.usuariAplicacioID=usuariAplicacioID;
}
  /** Constructor dels valors Not Null */
  public TipusDocumentJPA(long tipusDocumentID , long nomID , long tipusDocumentBaseID) {
    this.tipusDocumentID=tipusDocumentID;
    this.nomID=nomID;
    this.tipusDocumentBaseID=tipusDocumentBaseID;
}
  public TipusDocumentJPA(TipusDocument __bean) {
    this.setTipusDocumentID(__bean.getTipusDocumentID());
    this.setNomID(__bean.getNomID());
    this.setTipusDocumentBaseID(__bean.getTipusDocumentBaseID());
    this.setDescripcio(__bean.getDescripcio());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
	}

	public long getTipusDocumentID() {
		return(tipusDocumentID);
	};
	public void setTipusDocumentID(long _tipusDocumentID_) {
		this.tipusDocumentID = _tipusDocumentID_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public long getTipusDocumentBaseID() {
		return(tipusDocumentBaseID);
	};
	public void setTipusDocumentBaseID(long _tipusDocumentBaseID_) {
		this.tipusDocumentBaseID = _tipusDocumentBaseID_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TipusDocument) {
      TipusDocument __instance = (TipusDocument)__obj;
      __result = true;
      __result = __result && (this.getTipusDocumentID() == __instance.getTipusDocumentID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:tipusdocumentid | Table: pfi_modulfirmapertipusdoc | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusDocument")
    private Set<ModulDeFirmaPerTipusDeDocumentJPA> modulDeFirmaPerTipusDeDocuments = new HashSet<ModulDeFirmaPerTipusDeDocumentJPA>(0);
    public  Set<ModulDeFirmaPerTipusDeDocumentJPA> getModulDeFirmaPerTipusDeDocuments() {
    return this.modulDeFirmaPerTipusDeDocuments;
  }

    public void setModulDeFirmaPerTipusDeDocuments(Set<ModulDeFirmaPerTipusDeDocumentJPA> modulDeFirmaPerTipusDeDocuments) {
      this.modulDeFirmaPerTipusDeDocuments = modulDeFirmaPerTipusDeDocuments;
    }


// EXP  Field:tipusdocumentid | Table: pfi_peticiodefirma | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusDocument")
    private Set<PeticioDeFirmaJPA> peticioDeFirmas = new HashSet<PeticioDeFirmaJPA>(0);
    public  Set<PeticioDeFirmaJPA> getPeticioDeFirmas() {
    return this.peticioDeFirmas;
  }

    public void setPeticioDeFirmas(Set<PeticioDeFirmaJPA> peticioDeFirmas) {
      this.peticioDeFirmas = peticioDeFirmas;
    }


// EXP  Field:tipusdocumentid | Table: pfi_tipusdocumentcoladele | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusDocument")
    private Set<TipusDocumentColaboracioDelegacioJPA> tipusDocumentColaboracioDelegacios = new HashSet<TipusDocumentColaboracioDelegacioJPA>(0);
    public  Set<TipusDocumentColaboracioDelegacioJPA> getTipusDocumentColaboracioDelegacios() {
    return this.tipusDocumentColaboracioDelegacios;
  }

    public void setTipusDocumentColaboracioDelegacios(Set<TipusDocumentColaboracioDelegacioJPA> tipusDocumentColaboracioDelegacios) {
      this.tipusDocumentColaboracioDelegacios = tipusDocumentColaboracioDelegacios;
    }


// IMP Field:traduccioid | Table: pfi_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @ForeignKey(name="pfi_tipusdoc_traduccio_fk")
    @JoinColumn(name = "nom", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false)
    private TraduccioJPA nom;

    public TraduccioJPA getNom() {
    return this.nom;
  }

    public  void setNom(TraduccioJPA nom) {
    this.nom = nom;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.portafib.persistence.TraduccioMapJPA> getNomTraduccions() {
    return this.nom.getTraduccions();
  }

  public void setNomTraduccions(java.util.Map<String, es.caib.portafib.persistence.TraduccioMapJPA> __traduccions__) {
    this.nom.setTraduccions(__traduccions__);
  }


// IMP Field:usuariaplicacioid | Table: pfi_usuariaplicacio | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_tipusdoc_usrapp_fk")
    @JoinColumn(name = "usuariaplicacioid", referencedColumnName ="usuariAplicacioID", nullable = true, insertable=false, updatable=false)
    private UsuariAplicacioJPA usuariAplicacio;

    public UsuariAplicacioJPA getUsuariAplicacio() {
    return this.usuariAplicacio;
  }

    public  void setUsuariAplicacio(UsuariAplicacioJPA usuariAplicacio) {
    this.usuariAplicacio = usuariAplicacio;
  }


 // ---------------  STATIC METHODS ------------------
  public static TipusDocumentJPA toJPA(TipusDocument __bean) {
    if (__bean == null) { return null;}
    TipusDocumentJPA __tmp = new TipusDocumentJPA();
    __tmp.setTipusDocumentID(__bean.getTipusDocumentID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setTipusDocumentBaseID(__bean.getTipusDocumentBaseID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
		return __tmp;
	}


  public static TipusDocumentJPA copyJPA(TipusDocumentJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TipusDocumentJPA> copyJPA(java.util.Set<TipusDocumentJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TipusDocumentJPA> __tmpSet = (java.util.Set<TipusDocumentJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TipusDocumentJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TipusDocumentJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TipusDocumentJPA copyJPA(TipusDocumentJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TipusDocumentJPA __tmp = (TipusDocumentJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"TipusDocumentColaboracioDelegacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusDocumentColaboracioDelegacios) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusDocumentColaboracioDelegacios())) ) {
      __tmp.setTipusDocumentColaboracioDelegacios(TipusDocumentColaboracioDelegacioJPA.copyJPA(__jpa.getTipusDocumentColaboracioDelegacios(), __alreadyCopied,"TipusDocumentJPA"));
    }
    if(!"ModulDeFirmaPerTipusDeDocumentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.modulDeFirmaPerTipusDeDocuments) || org.hibernate.Hibernate.isInitialized(__jpa.getModulDeFirmaPerTipusDeDocuments())) ) {
      __tmp.setModulDeFirmaPerTipusDeDocuments(ModulDeFirmaPerTipusDeDocumentJPA.copyJPA(__jpa.getModulDeFirmaPerTipusDeDocuments(), __alreadyCopied,"TipusDocumentJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirmas) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirmas())) ) {
      __tmp.setPeticioDeFirmas(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirmas(), __alreadyCopied,"TipusDocumentJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"UsuariAplicacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacio) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacio()) ) ) {
      __tmp.setUsuariAplicacio(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacio(), __alreadyCopied,"TipusDocumentJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.nom) || org.hibernate.Hibernate.isInitialized(__jpa.getNom()) ) ) {
      __tmp.setNom(TraduccioJPA.copyJPA(__jpa.getNom(), __alreadyCopied,"TipusDocumentJPA"));
    }

    return __tmp;
  }




}