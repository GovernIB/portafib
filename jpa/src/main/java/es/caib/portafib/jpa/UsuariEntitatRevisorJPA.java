
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.ForeignKey;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_usuarientitatrevisor" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariEntitatRevisorJPA implements UsuariEntitatRevisor {



private static final long serialVersionUID = 1693548915L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_usuarientitatrevisor_pk_i")
	@Column(name="usuarientitatrevisorid",nullable = false,length = 19)
	long usuariEntitatRevisorId;

	@Index(name="pfi_usuentrev_usrentitat_fk_i")
	@Column(name="usuarientitatid",nullable = false,length = 50)
	java.lang.String usuariEntitatID;

	@Column(name="actiu",nullable = false,length = 1)
	boolean actiu;



  /** Constructor Buit */
  public UsuariEntitatRevisorJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariEntitatRevisorJPA(long usuariEntitatRevisorId , java.lang.String usuariEntitatID , boolean actiu) {
    this.usuariEntitatRevisorId=usuariEntitatRevisorId;
    this.usuariEntitatID=usuariEntitatID;
    this.actiu=actiu;
}
  /** Constructor sense valors autoincrementals */
  public UsuariEntitatRevisorJPA(java.lang.String usuariEntitatID , boolean actiu) {
    this.usuariEntitatID=usuariEntitatID;
    this.actiu=actiu;
}
  public UsuariEntitatRevisorJPA(UsuariEntitatRevisor __bean) {
    this.setUsuariEntitatRevisorId(__bean.getUsuariEntitatRevisorId());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setActiu(__bean.isActiu());
	}

	public long getUsuariEntitatRevisorId() {
		return(usuariEntitatRevisorId);
	};
	public void setUsuariEntitatRevisorId(long _usuariEntitatRevisorId_) {
		this.usuariEntitatRevisorId = _usuariEntitatRevisorId_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof UsuariEntitatRevisor) {
      UsuariEntitatRevisor __instance = (UsuariEntitatRevisor)__obj;
      __result = true;
      __result = __result && (this.getUsuariEntitatRevisorId() == __instance.getUsuariEntitatRevisorId()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:usuarientitatrevisorid | Table: pfi_revisordefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariEntitatRevisor")
	private Set<RevisorDeFirmaJPA> revisorDeFirmas = new HashSet<RevisorDeFirmaJPA>(0);
	public  Set<RevisorDeFirmaJPA> getRevisorDeFirmas() {
    return this.revisorDeFirmas;
  }

	public void setRevisorDeFirmas(Set<RevisorDeFirmaJPA> revisorDeFirmas) {
	  this.revisorDeFirmas = revisorDeFirmas;
	}


// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_usuentrev_usrentitat_fk")
	@JoinColumn(name = "usuarientitatid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false)
	private UsuariEntitatJPA usuariEntitat;

	public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

	public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static UsuariEntitatRevisorJPA toJPA(UsuariEntitatRevisor __bean) {
    if (__bean == null) { return null;}
    UsuariEntitatRevisorJPA __tmp = new UsuariEntitatRevisorJPA();
    __tmp.setUsuariEntitatRevisorId(__bean.getUsuariEntitatRevisorId());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setActiu(__bean.isActiu());
		return __tmp;
	}


  public static UsuariEntitatRevisorJPA copyJPA(UsuariEntitatRevisorJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariEntitatRevisorJPA> copyJPA(java.util.Set<UsuariEntitatRevisorJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<UsuariEntitatRevisorJPA> __tmpSet = (java.util.Set<UsuariEntitatRevisorJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariEntitatRevisorJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariEntitatRevisorJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariEntitatRevisorJPA copyJPA(UsuariEntitatRevisorJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariEntitatRevisorJPA __tmp = (UsuariEntitatRevisorJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"RevisorDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.revisorDeFirmas) || org.hibernate.Hibernate.isInitialized(__jpa.getRevisorDeFirmas())) ) {
      __tmp.setRevisorDeFirmas(RevisorDeFirmaJPA.copyJPA(__jpa.getRevisorDeFirmas(), __alreadyCopied,"UsuariEntitatRevisorJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitat()) ) ) {
      __tmp.setUsuariEntitat(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitat(), __alreadyCopied,"UsuariEntitatRevisorJPA"));
    }

    return __tmp;
  }




}
