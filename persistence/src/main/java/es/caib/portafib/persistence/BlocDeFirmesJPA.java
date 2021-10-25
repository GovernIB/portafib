
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "pfi_blocdefirmes" )
@SequenceGenerator(name="BLOCDEFIRMES_SEQ", sequenceName="pfi_blocdefirmes_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class BlocDeFirmesJPA implements BlocDeFirmes {



private static final long serialVersionUID = 985359024L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BLOCDEFIRMES_SEQ")
    @Index(name="pfi_blocdefirmes_pk_i")
    @Column(name="blocdefirmesid",nullable = false,length = 19)
    long blocDeFirmesID;

    @Column(name="ordre",nullable = false,length = 10)
    int ordre;

  /** Indica el moment en que aquest bloc de firma s'ha completat */
    @Column(name="datafinalitzacio",length = 29,precision = 6)
    java.sql.Timestamp dataFinalitzacio;

    @Index(name="pfi_blocfirmes_fluxid_fk_i")
    @Column(name="fluxdefirmesid",nullable = false,length = 19)
    long fluxDeFirmesID;

    @Column(name="minimdefirmes",nullable = false,length = 10)
    int minimDeFirmes;



  /** Constructor Buit */
  public BlocDeFirmesJPA() {
  }

  /** Constructor amb tots els camps  */
  public BlocDeFirmesJPA(long blocDeFirmesID , int ordre , java.sql.Timestamp dataFinalitzacio , long fluxDeFirmesID , int minimDeFirmes) {
    this.blocDeFirmesID=blocDeFirmesID;
    this.ordre=ordre;
    this.dataFinalitzacio=dataFinalitzacio;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.minimDeFirmes=minimDeFirmes;
}
  /** Constructor sense valors autoincrementals */
  public BlocDeFirmesJPA(int ordre , java.sql.Timestamp dataFinalitzacio , long fluxDeFirmesID , int minimDeFirmes) {
    this.ordre=ordre;
    this.dataFinalitzacio=dataFinalitzacio;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.minimDeFirmes=minimDeFirmes;
}
  public BlocDeFirmesJPA(BlocDeFirmes __bean) {
    this.setBlocDeFirmesID(__bean.getBlocDeFirmesID());
    this.setOrdre(__bean.getOrdre());
    this.setDataFinalitzacio(__bean.getDataFinalitzacio());
    this.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    this.setMinimDeFirmes(__bean.getMinimDeFirmes());
	}

	public long getBlocDeFirmesID() {
		return(blocDeFirmesID);
	};
	public void setBlocDeFirmesID(long _blocDeFirmesID_) {
		this.blocDeFirmesID = _blocDeFirmesID_;
	};

	public int getOrdre() {
		return(ordre);
	};
	public void setOrdre(int _ordre_) {
		this.ordre = _ordre_;
	};

	public java.sql.Timestamp getDataFinalitzacio() {
		return(dataFinalitzacio);
	};
	public void setDataFinalitzacio(java.sql.Timestamp _dataFinalitzacio_) {
		this.dataFinalitzacio = _dataFinalitzacio_;
	};

	public long getFluxDeFirmesID() {
		return(fluxDeFirmesID);
	};
	public void setFluxDeFirmesID(long _fluxDeFirmesID_) {
		this.fluxDeFirmesID = _fluxDeFirmesID_;
	};

	public int getMinimDeFirmes() {
		return(minimDeFirmes);
	};
	public void setMinimDeFirmes(int _minimDeFirmes_) {
		this.minimDeFirmes = _minimDeFirmes_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof BlocDeFirmes) {
      BlocDeFirmes __instance = (BlocDeFirmes)__obj;
      __result = true;
      __result = __result && (this.getBlocDeFirmesID() == __instance.getBlocDeFirmesID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:blocdefirmaid | Table: pfi_firma | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "blocDeFirmes")
    private Set<FirmaJPA> firmas = new HashSet<FirmaJPA>(0);
    public  Set<FirmaJPA> getFirmas() {
    return this.firmas;
  }

    public void setFirmas(Set<FirmaJPA> firmas) {
      this.firmas = firmas;
    }


// IMP Field:fluxdefirmesid | Table: pfi_fluxdefirmes | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_blocfirmes_fluxfirmes_fk")
    @JoinColumn(name = "fluxdefirmesid", referencedColumnName ="fluxDeFirmesID", nullable = false, insertable=false, updatable=false)
    private FluxDeFirmesJPA fluxDeFirmes;

    public FluxDeFirmesJPA getFluxDeFirmes() {
    return this.fluxDeFirmes;
  }

    public  void setFluxDeFirmes(FluxDeFirmesJPA fluxDeFirmes) {
    this.fluxDeFirmes = fluxDeFirmes;
  }


 // ---------------  STATIC METHODS ------------------
  public static BlocDeFirmesJPA toJPA(BlocDeFirmes __bean) {
    if (__bean == null) { return null;}
    BlocDeFirmesJPA __tmp = new BlocDeFirmesJPA();
    __tmp.setBlocDeFirmesID(__bean.getBlocDeFirmesID());
    __tmp.setOrdre(__bean.getOrdre());
    __tmp.setDataFinalitzacio(__bean.getDataFinalitzacio());
    __tmp.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    __tmp.setMinimDeFirmes(__bean.getMinimDeFirmes());
		return __tmp;
	}


  public static BlocDeFirmesJPA copyJPA(BlocDeFirmesJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<BlocDeFirmesJPA> copyJPA(java.util.Set<BlocDeFirmesJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    //@SuppressWarnings("unchecked")
    java.util.Set<BlocDeFirmesJPA> __tmpSet = (java.util.Set<BlocDeFirmesJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<BlocDeFirmesJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (BlocDeFirmesJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static BlocDeFirmesJPA copyJPA(BlocDeFirmesJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    BlocDeFirmesJPA __tmp = (BlocDeFirmesJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"FirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.firmas) || org.hibernate.Hibernate.isInitialized(__jpa.getFirmas())) ) {
      __tmp.setFirmas(FirmaJPA.copyJPA(__jpa.getFirmas(), __alreadyCopied,"BlocDeFirmesJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"FluxDeFirmesJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.fluxDeFirmes) || org.hibernate.Hibernate.isInitialized(__jpa.getFluxDeFirmes()) ) ) {
      __tmp.setFluxDeFirmes(FluxDeFirmesJPA.copyJPA(__jpa.getFluxDeFirmes(), __alreadyCopied,"BlocDeFirmesJPA"));
    }

    return __tmp;
  }




}
