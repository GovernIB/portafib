
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_revisordefirma" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class RevisorDeFirmaJPA implements RevisorDeFirma {



private static final long serialVersionUID = -234707383L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_revisordefirma_pk_i")
	@Column(name="revisordefirmaid",nullable = false,length = 19)
	long revisorDeFirmaID;

	@Index(name="pfi_revfirma_usuentrev_fk_i")
	@Column(name="usuarientitatrevisorid",nullable = false,length = 19)
	long usuariEntitatRevisorID;

	@Index(name="pfi_revfirma_firmaid_fk_i")
	@Column(name="firmaid",nullable = false,length = 19)
	long firmaID;

	@Column(name="obligatori",nullable = false,length = 1)
	boolean obligatori;



  /** Constructor Buit */
  public RevisorDeFirmaJPA() {
  }

  /** Constructor amb tots els camps  */
  public RevisorDeFirmaJPA(long revisorDeFirmaID , long usuariEntitatRevisorID , long firmaID , boolean obligatori) {
    this.revisorDeFirmaID=revisorDeFirmaID;
    this.usuariEntitatRevisorID=usuariEntitatRevisorID;
    this.firmaID=firmaID;
    this.obligatori=obligatori;
}
  /** Constructor sense valors autoincrementals */
  public RevisorDeFirmaJPA(long usuariEntitatRevisorID , long firmaID , boolean obligatori) {
    this.usuariEntitatRevisorID=usuariEntitatRevisorID;
    this.firmaID=firmaID;
    this.obligatori=obligatori;
}
  public RevisorDeFirmaJPA(RevisorDeFirma __bean) {
    this.setRevisorDeFirmaID(__bean.getRevisorDeFirmaID());
    this.setUsuariEntitatRevisorID(__bean.getUsuariEntitatRevisorID());
    this.setFirmaID(__bean.getFirmaID());
    this.setObligatori(__bean.isObligatori());
	}

	public long getRevisorDeFirmaID() {
		return(revisorDeFirmaID);
	};
	public void setRevisorDeFirmaID(long _revisorDeFirmaID_) {
		this.revisorDeFirmaID = _revisorDeFirmaID_;
	};

	public long getUsuariEntitatRevisorID() {
		return(usuariEntitatRevisorID);
	};
	public void setUsuariEntitatRevisorID(long _usuariEntitatRevisorID_) {
		this.usuariEntitatRevisorID = _usuariEntitatRevisorID_;
	};

	public long getFirmaID() {
		return(firmaID);
	};
	public void setFirmaID(long _firmaID_) {
		this.firmaID = _firmaID_;
	};

	public boolean isObligatori() {
		return(obligatori);
	};
	public void setObligatori(boolean _obligatori_) {
		this.obligatori = _obligatori_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof RevisorDeFirma) {
      RevisorDeFirma __instance = (RevisorDeFirma)__obj;
      __result = true;
      __result = __result && (this.getRevisorDeFirmaID() == __instance.getRevisorDeFirmaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:usuarientitatrevisorid | Table: pfi_usuarientitatrevisor | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_revfirma_usuentrev_fk")
	@JoinColumn(name = "usuarientitatrevisorid", referencedColumnName ="usuariEntitatRevisorId", nullable = false, insertable=false, updatable=false)
	private UsuariEntitatRevisorJPA usuariEntitatRevisor;

	public UsuariEntitatRevisorJPA getUsuariEntitatRevisor() {
    return this.usuariEntitatRevisor;
  }

	public  void setUsuariEntitatRevisor(UsuariEntitatRevisorJPA usuariEntitatRevisor) {
    this.usuariEntitatRevisor = usuariEntitatRevisor;
  }

// IMP Field:firmaid | Table: pfi_firma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_revfirma_firma_fk")
	@JoinColumn(name = "firmaid", referencedColumnName ="firmaID", nullable = false, insertable=false, updatable=false)
	private FirmaJPA firma;

	public FirmaJPA getFirma() {
    return this.firma;
  }

	public  void setFirma(FirmaJPA firma) {
    this.firma = firma;
  }


 // ---------------  STATIC METHODS ------------------
  public static RevisorDeFirmaJPA toJPA(RevisorDeFirma __bean) {
    if (__bean == null) { return null;}
    RevisorDeFirmaJPA __tmp = new RevisorDeFirmaJPA();
    __tmp.setRevisorDeFirmaID(__bean.getRevisorDeFirmaID());
    __tmp.setUsuariEntitatRevisorID(__bean.getUsuariEntitatRevisorID());
    __tmp.setFirmaID(__bean.getFirmaID());
    __tmp.setObligatori(__bean.isObligatori());
		return __tmp;
	}


  public static RevisorDeFirmaJPA copyJPA(RevisorDeFirmaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<RevisorDeFirmaJPA> copyJPA(java.util.Set<RevisorDeFirmaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<RevisorDeFirmaJPA> __tmpSet = (java.util.Set<RevisorDeFirmaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<RevisorDeFirmaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (RevisorDeFirmaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static RevisorDeFirmaJPA copyJPA(RevisorDeFirmaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    RevisorDeFirmaJPA __tmp = (RevisorDeFirmaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"UsuariEntitatRevisorJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitatRevisor) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitatRevisor()) ) ) {
      __tmp.setUsuariEntitatRevisor(UsuariEntitatRevisorJPA.copyJPA(__jpa.getUsuariEntitatRevisor(), __alreadyCopied,"RevisorDeFirmaJPA"));
    }
    if(!"FirmaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.firma) || org.hibernate.Hibernate.isInitialized(__jpa.getFirma()) ) ) {
      __tmp.setFirma(FirmaJPA.copyJPA(__jpa.getFirma(), __alreadyCopied,"RevisorDeFirmaJPA"));
    }

    return __tmp;
  }




}
