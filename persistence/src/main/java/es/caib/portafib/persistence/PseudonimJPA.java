
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;


@Entity(name = "PseudonimJPA")
@Table(name = "pfi_pseudonim" , indexes = { 
        @Index(name="pfi_pseudonim_pk_i", columnList = "pseudonimid")})
@SequenceGenerator(name="PSEUDONIM_SEQ", sequenceName="pfi_pseudonim_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PseudonimJPA implements Pseudonim {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PSEUDONIM_SEQ")
    @Column(name="pseudonimid",nullable = false,length = 19)
    long pseudonimid;

    @Column(name="pseudonim",nullable = false,length = 255)
    java.lang.String pseudonim;

    @Column(name="nif",nullable = false,length = 255)
    java.lang.String nif;



  /** Constructor Buit */
  public PseudonimJPA() {
  }

  /** Constructor amb tots els camps  */
  public PseudonimJPA(long pseudonimid , java.lang.String pseudonim , java.lang.String nif) {
    this.pseudonimid=pseudonimid;
    this.pseudonim=pseudonim;
    this.nif=nif;
}
  /** Constructor sense valors autoincrementals */
  public PseudonimJPA(java.lang.String pseudonim , java.lang.String nif) {
    this.pseudonim=pseudonim;
    this.nif=nif;
}
  public PseudonimJPA(Pseudonim __bean) {
    this.setPseudonimid(__bean.getPseudonimid());
    this.setPseudonim(__bean.getPseudonim());
    this.setNif(__bean.getNif());
	}

	public long getPseudonimid() {
		return(pseudonimid);
	};
	public void setPseudonimid(long _pseudonimid_) {
		this.pseudonimid = _pseudonimid_;
	};

	public java.lang.String getPseudonim() {
		return(pseudonim);
	};
	public void setPseudonim(java.lang.String _pseudonim_) {
		this.pseudonim = _pseudonim_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Pseudonim) {
            Pseudonim __instance = (Pseudonim)__obj;
            __result = true;
            __result = __result && (this.getPseudonimid() == __instance.getPseudonimid()) ;
        } else {
            __result = false;
        }
        return __result;
    }


 // ---------------  STATIC METHODS ------------------
  public static PseudonimJPA toJPA(Pseudonim __bean) {
    if (__bean == null) { return null;}
    PseudonimJPA __tmp = new PseudonimJPA();
    __tmp.setPseudonimid(__bean.getPseudonimid());
    __tmp.setPseudonim(__bean.getPseudonim());
    __tmp.setNif(__bean.getNif());
		return __tmp;
	}


  public static PseudonimJPA copyJPA(PseudonimJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PseudonimJPA> copyJPA(java.util.Set<PseudonimJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PseudonimJPA> __tmpSet = (java.util.Set<PseudonimJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PseudonimJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PseudonimJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PseudonimJPA copyJPA(PseudonimJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PseudonimJPA __tmp = (PseudonimJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
