
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


@Entity(name = "AnnexJPA")
@Table(name = "pfi_annex" , indexes = { 
        @Index(name="pfi_annex_pk_i", columnList = "annexid"),
        @Index(name="pfi_annex_petdefirmaid_fk_i", columnList = "peticiodefirmaid"),
        @Index(name="pfi_annex_fitxerid_fk_i", columnList = "fitxerid")})
@SequenceGenerator(name="ANNEX_SEQ", sequenceName="pfi_annex_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class AnnexJPA implements Annex {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ANNEX_SEQ")
    @Column(name="annexid",nullable = false,length = 19)
    long annexID;

    @Column(name="peticiodefirmaid",nullable = false,length = 19)
    long peticioDeFirmaID;

    @Column(name="fitxerid",nullable = false,length = 19)
    long fitxerID;

  /** Significa si ha d'anar junt amb el document principal a firmar o per separat */
    @Column(name="adjuntar",nullable = false,length = 1)
    boolean adjuntar;

  /** S'ha de firma aquest adjunt (Només té sentit si adjuntar es false) */
    @Column(name="firmar",nullable = false,length = 1)
    boolean firmar;



  /** Constructor Buit */
  public AnnexJPA() {
  }

  /** Constructor amb tots els camps  */
  public AnnexJPA(long annexID , long peticioDeFirmaID , long fitxerID , boolean adjuntar , boolean firmar) {
    this.annexID=annexID;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.fitxerID=fitxerID;
    this.adjuntar=adjuntar;
    this.firmar=firmar;
}
  /** Constructor sense valors autoincrementals */
  public AnnexJPA(long peticioDeFirmaID , long fitxerID , boolean adjuntar , boolean firmar) {
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.fitxerID=fitxerID;
    this.adjuntar=adjuntar;
    this.firmar=firmar;
}
  public AnnexJPA(Annex __bean) {
    this.setAnnexID(__bean.getAnnexID());
    this.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    this.setFitxerID(__bean.getFitxerID());
    this.setAdjuntar(__bean.isAdjuntar());
    this.setFirmar(__bean.isFirmar());
    // Fitxer
    this.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
	}

	public long getAnnexID() {
		return(annexID);
	};
	public void setAnnexID(long _annexID_) {
		this.annexID = _annexID_;
	};

	public long getPeticioDeFirmaID() {
		return(peticioDeFirmaID);
	};
	public void setPeticioDeFirmaID(long _peticioDeFirmaID_) {
		this.peticioDeFirmaID = _peticioDeFirmaID_;
	};

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public boolean isAdjuntar() {
		return(adjuntar);
	};
	public void setAdjuntar(boolean _adjuntar_) {
		this.adjuntar = _adjuntar_;
	};

	public boolean isFirmar() {
		return(firmar);
	};
	public void setFirmar(boolean _firmar_) {
		this.firmar = _firmar_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Annex) {
      Annex __instance = (Annex)__obj;
      __result = true;
      __result = __result && (this.getAnnexID() == __instance.getAnnexID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:annexid | Table: pfi_annexfirmat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "annex")
    private Set<AnnexFirmatJPA> annexFirmats = new HashSet<AnnexFirmatJPA>(0);
    public  Set<AnnexFirmatJPA> getAnnexFirmats() {
    return this.annexFirmats;
  }

    public void setAnnexFirmats(Set<AnnexFirmatJPA> annexFirmats) {
      this.annexFirmats = annexFirmats;
    }


// IMP Field:peticiodefirmaid | Table: pfi_peticiodefirma | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peticiodefirmaid", referencedColumnName ="peticioDeFirmaID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_annex_petifirma_fk"))
    private PeticioDeFirmaJPA peticioDeFirma;

    public PeticioDeFirmaJPA getPeticioDeFirma() {
    return this.peticioDeFirma;
  }

    public  void setPeticioDeFirma(PeticioDeFirmaJPA peticioDeFirma) {
    this.peticioDeFirma = peticioDeFirma;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxerid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_annex_fitxer_fk"))
    private FitxerJPA fitxer;

    public FitxerJPA getFitxer() {
    return this.fitxer;
  }

    public  void setFitxer(FitxerJPA fitxer) {
    this.fitxer = fitxer;
  }


 // ---------------  STATIC METHODS ------------------
  public static AnnexJPA toJPA(Annex __bean) {
    if (__bean == null) { return null;}
    AnnexJPA __tmp = new AnnexJPA();
    __tmp.setAnnexID(__bean.getAnnexID());
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setAdjuntar(__bean.isAdjuntar());
    __tmp.setFirmar(__bean.isFirmar());
    // Fitxer
    __tmp.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
		return __tmp;
	}


  public static AnnexJPA copyJPA(AnnexJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<AnnexJPA> copyJPA(java.util.Set<AnnexJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<AnnexJPA> __tmpSet = (java.util.Set<AnnexJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<AnnexJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (AnnexJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static AnnexJPA copyJPA(AnnexJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    AnnexJPA __tmp = (AnnexJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"AnnexFirmatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.annexFirmats) || org.hibernate.Hibernate.isInitialized(__jpa.getAnnexFirmats())) ) {
      __tmp.setAnnexFirmats(AnnexFirmatJPA.copyJPA(__jpa.getAnnexFirmats(), __alreadyCopied,"AnnexJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"PeticioDeFirmaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirma) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirma()) ) ) {
      __tmp.setPeticioDeFirma(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirma(), __alreadyCopied,"AnnexJPA"));
    }

    return __tmp;
  }




}
