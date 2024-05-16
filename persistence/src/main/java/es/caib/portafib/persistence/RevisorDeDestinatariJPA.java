
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "RevisorDeDestinatariJPA")
@Table(name = "pfi_revisordedestinatari" , indexes = { 
        @Index(name="pfi_revisordedestinatari_pk_i", columnList = "revisordedestinatariid"),
        @Index(name="pfi_revdedest_destid_fk_i", columnList = "destinatariid"),
        @Index(name="pfi_revdedest_revisorid_fk_i", columnList = "revisorid")},
           uniqueConstraints = {
            @UniqueConstraint(name="pfi_revdedest_dest_rev_uk", columnNames={"destinatariid","revisorid"}) } )
@SequenceGenerator(name="REVISORDEDESTINATARI_SEQ", sequenceName="pfi_revisordedestinatari_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class RevisorDeDestinatariJPA implements RevisorDeDestinatari {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="REVISORDEDESTINATARI_SEQ")
    @Column(name="revisordedestinatariid",nullable = false,length = 19)
    long revisorDeDestinatariID;

    @Column(name="destinatariid",nullable = false,length = 101)
    java.lang.String destinatariID;

    @Column(name="revisorid",nullable = false,length = 101)
    java.lang.String revisorID;



  /** Constructor Buit */
  public RevisorDeDestinatariJPA() {
  }

  /** Constructor amb tots els camps  */
  public RevisorDeDestinatariJPA(long revisorDeDestinatariID , java.lang.String destinatariID , java.lang.String revisorID) {
    this.revisorDeDestinatariID=revisorDeDestinatariID;
    this.destinatariID=destinatariID;
    this.revisorID=revisorID;
}
  /** Constructor sense valors autoincrementals */
  public RevisorDeDestinatariJPA(java.lang.String destinatariID , java.lang.String revisorID) {
    this.destinatariID=destinatariID;
    this.revisorID=revisorID;
}
  public RevisorDeDestinatariJPA(RevisorDeDestinatari __bean) {
    this.setRevisorDeDestinatariID(__bean.getRevisorDeDestinatariID());
    this.setDestinatariID(__bean.getDestinatariID());
    this.setRevisorID(__bean.getRevisorID());
	}

	public long getRevisorDeDestinatariID() {
		return(revisorDeDestinatariID);
	};
	public void setRevisorDeDestinatariID(long _revisorDeDestinatariID_) {
		this.revisorDeDestinatariID = _revisorDeDestinatariID_;
	};

	public java.lang.String getDestinatariID() {
		return(destinatariID);
	};
	public void setDestinatariID(java.lang.String _destinatariID_) {
		this.destinatariID = _destinatariID_;
	};

	public java.lang.String getRevisorID() {
		return(revisorID);
	};
	public void setRevisorID(java.lang.String _revisorID_) {
		this.revisorID = _revisorID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof RevisorDeDestinatari) {
      RevisorDeDestinatari __instance = (RevisorDeDestinatari)__obj;
      __result = true;
      __result = __result && (this.getRevisorDeDestinatariID() == __instance.getRevisorDeDestinatariID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destinatariid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_revdedest_usrentitat_de_fk"))
    private UsuariEntitatJPA destinatari;

    public UsuariEntitatJPA getDestinatari() {
    return this.destinatari;
  }

    public  void setDestinatari(UsuariEntitatJPA destinatari) {
    this.destinatari = destinatari;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "revisorid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_revdedest_usrentitat_re_fk"))
    private UsuariEntitatJPA revisor;

    public UsuariEntitatJPA getRevisor() {
    return this.revisor;
  }

    public  void setRevisor(UsuariEntitatJPA revisor) {
    this.revisor = revisor;
  }


 // ---------------  STATIC METHODS ------------------
  public static RevisorDeDestinatariJPA toJPA(RevisorDeDestinatari __bean) {
    if (__bean == null) { return null;}
    RevisorDeDestinatariJPA __tmp = new RevisorDeDestinatariJPA();
    __tmp.setRevisorDeDestinatariID(__bean.getRevisorDeDestinatariID());
    __tmp.setDestinatariID(__bean.getDestinatariID());
    __tmp.setRevisorID(__bean.getRevisorID());
		return __tmp;
	}


  public static RevisorDeDestinatariJPA copyJPA(RevisorDeDestinatariJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<RevisorDeDestinatariJPA> copyJPA(java.util.Set<RevisorDeDestinatariJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<RevisorDeDestinatariJPA> __tmpSet = (java.util.Set<RevisorDeDestinatariJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<RevisorDeDestinatariJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (RevisorDeDestinatariJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static RevisorDeDestinatariJPA copyJPA(RevisorDeDestinatariJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    RevisorDeDestinatariJPA __tmp = (RevisorDeDestinatariJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.revisor) || org.hibernate.Hibernate.isInitialized(__jpa.getRevisor()) ) ) {
      __tmp.setRevisor(UsuariEntitatJPA.copyJPA(__jpa.getRevisor(), __alreadyCopied,"RevisorDeDestinatariJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.destinatari) || org.hibernate.Hibernate.isInitialized(__jpa.getDestinatari()) ) ) {
      __tmp.setDestinatari(UsuariEntitatJPA.copyJPA(__jpa.getDestinatari(), __alreadyCopied,"RevisorDeDestinatariJPA"));
    }

    return __tmp;
  }




}
