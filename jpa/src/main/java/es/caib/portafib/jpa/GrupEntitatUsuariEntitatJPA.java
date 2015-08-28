
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
import javax.persistence.UniqueConstraint;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_grupentitatusuarientitat"  , uniqueConstraints = {
            @UniqueConstraint( columnNames={"usuarientitatid","grupentitatid"}) } )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class GrupEntitatUsuariEntitatJPA implements GrupEntitatUsuariEntitat {



private static final long serialVersionUID = -1726984778L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_grupusrent_pk_i")
	@Column(name="grupentitatusuarientitatid",nullable = false,length = 19)
	long grupEntitatUsuariEntitatID;

	@Index(name="pfi_grupusrent_usrentid_fk_i")
	@Column(name="usuarientitatid",nullable = false,length = 101)
	java.lang.String usuariEntitatID;

	@Index(name="pfi_grupusrent_grupentid_fk_i")
	@Column(name="grupentitatid",nullable = false,length = 19)
	java.lang.Long grupEntitatID;



  /** Constructor Buit */
  public GrupEntitatUsuariEntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public GrupEntitatUsuariEntitatJPA(long grupEntitatUsuariEntitatID , java.lang.String usuariEntitatID , java.lang.Long grupEntitatID) {
    this.grupEntitatUsuariEntitatID=grupEntitatUsuariEntitatID;
    this.usuariEntitatID=usuariEntitatID;
    this.grupEntitatID=grupEntitatID;
}
  /** Constructor sense valors autoincrementals */
  public GrupEntitatUsuariEntitatJPA(java.lang.String usuariEntitatID , java.lang.Long grupEntitatID) {
    this.usuariEntitatID=usuariEntitatID;
    this.grupEntitatID=grupEntitatID;
}
  public GrupEntitatUsuariEntitatJPA(GrupEntitatUsuariEntitat __bean) {
    this.setGrupEntitatUsuariEntitatID(__bean.getGrupEntitatUsuariEntitatID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setGrupEntitatID(__bean.getGrupEntitatID());
	}

	public long getGrupEntitatUsuariEntitatID() {
		return(grupEntitatUsuariEntitatID);
	};
	public void setGrupEntitatUsuariEntitatID(long _grupEntitatUsuariEntitatID_) {
		this.grupEntitatUsuariEntitatID = _grupEntitatUsuariEntitatID_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public java.lang.Long getGrupEntitatID() {
		return(grupEntitatID);
	};
	public void setGrupEntitatID(java.lang.Long _grupEntitatID_) {
		this.grupEntitatID = _grupEntitatID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof GrupEntitatUsuariEntitat) {
      GrupEntitatUsuariEntitat __instance = (GrupEntitatUsuariEntitat)__obj;
      __result = true;
      __result = __result && (this.getGrupEntitatUsuariEntitatID() == __instance.getGrupEntitatUsuariEntitatID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_grupusrent_usrentitat_fk")
	@JoinColumn(name = "usuarientitatid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false)
	private UsuariEntitatJPA usuariEntitat;

	public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

	public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }

// IMP Field:grupentitatid | Table: pfi_grupentitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_grupusrent_grupentita_fk")
	@JoinColumn(name = "grupentitatid", referencedColumnName ="grupEntitatID", nullable = false, insertable=false, updatable=false)
	private GrupEntitatJPA grupEntitat;

	public GrupEntitatJPA getGrupEntitat() {
    return this.grupEntitat;
  }

	public  void setGrupEntitat(GrupEntitatJPA grupEntitat) {
    this.grupEntitat = grupEntitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static GrupEntitatUsuariEntitatJPA toJPA(GrupEntitatUsuariEntitat __bean) {
    if (__bean == null) { return null;}
    GrupEntitatUsuariEntitatJPA __tmp = new GrupEntitatUsuariEntitatJPA();
    __tmp.setGrupEntitatUsuariEntitatID(__bean.getGrupEntitatUsuariEntitatID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setGrupEntitatID(__bean.getGrupEntitatID());
		return __tmp;
	}


  public static GrupEntitatUsuariEntitatJPA copyJPA(GrupEntitatUsuariEntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<GrupEntitatUsuariEntitatJPA> copyJPA(java.util.Set<GrupEntitatUsuariEntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<GrupEntitatUsuariEntitatJPA> __tmpSet = (java.util.Set<GrupEntitatUsuariEntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<GrupEntitatUsuariEntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (GrupEntitatUsuariEntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static GrupEntitatUsuariEntitatJPA copyJPA(GrupEntitatUsuariEntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    GrupEntitatUsuariEntitatJPA __tmp = (GrupEntitatUsuariEntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitat()) ) ) {
      __tmp.setUsuariEntitat(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitat(), __alreadyCopied,"GrupEntitatUsuariEntitatJPA"));
    }
    if(!"GrupEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grupEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getGrupEntitat()) ) ) {
      __tmp.setGrupEntitat(GrupEntitatJPA.copyJPA(__jpa.getGrupEntitat(), __alreadyCopied,"GrupEntitatUsuariEntitatJPA"));
    }

    return __tmp;
  }




}
