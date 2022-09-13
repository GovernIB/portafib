
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


@Entity(name = "RebreAvisJPA")
@Table(name = "pfi_rebreavis" , indexes = { 
        @Index(name="pfi_rebreavis_pk_i", columnList = "id"),
        @Index(name="pfi_rebreavis_usrentid_fk_i", columnList = "usuarientitatid"),
        @Index(name="pfi_rebreavis_tiponotiid_fk_i", columnList = "tipusnotificacioid")},
           uniqueConstraints = {
            @UniqueConstraint(name="pfi_rebreavis_tnotiusr_uk", columnNames={"tipusnotificacioid","usuarientitatid"}) } )
@SequenceGenerator(name="REBREAVIS_SEQ", sequenceName="pfi_rebreavis_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class RebreAvisJPA implements RebreAvis {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="REBREAVIS_SEQ")
    @Column(name="id",nullable = false,length = 19)
    long id;

    @Column(name="usuarientitatid",nullable = false,length = 101)
    java.lang.String usuariEntitatID;

    @Column(name="tipusnotificacioid",nullable = false,length = 19)
    long tipusNotificacioID;

    @Column(name="rebreagrupat",nullable = false,length = 1)
    boolean rebreAgrupat = false;



  /** Constructor Buit */
  public RebreAvisJPA() {
  }

  /** Constructor amb tots els camps  */
  public RebreAvisJPA(long id , java.lang.String usuariEntitatID , long tipusNotificacioID , boolean rebreAgrupat) {
    this.id=id;
    this.usuariEntitatID=usuariEntitatID;
    this.tipusNotificacioID=tipusNotificacioID;
    this.rebreAgrupat=rebreAgrupat;
}
  /** Constructor sense valors autoincrementals */
  public RebreAvisJPA(java.lang.String usuariEntitatID , long tipusNotificacioID , boolean rebreAgrupat) {
    this.usuariEntitatID=usuariEntitatID;
    this.tipusNotificacioID=tipusNotificacioID;
    this.rebreAgrupat=rebreAgrupat;
}
  public RebreAvisJPA(RebreAvis __bean) {
    this.setId(__bean.getId());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setTipusNotificacioID(__bean.getTipusNotificacioID());
    this.setRebreAgrupat(__bean.isRebreAgrupat());
	}

	public long getId() {
		return(id);
	};
	public void setId(long _id_) {
		this.id = _id_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public long getTipusNotificacioID() {
		return(tipusNotificacioID);
	};
	public void setTipusNotificacioID(long _tipusNotificacioID_) {
		this.tipusNotificacioID = _tipusNotificacioID_;
	};

	public boolean isRebreAgrupat() {
		return(rebreAgrupat);
	};
	public void setRebreAgrupat(boolean _rebreAgrupat_) {
		this.rebreAgrupat = _rebreAgrupat_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof RebreAvis) {
      RebreAvis __instance = (RebreAvis)__obj;
      __result = true;
      __result = __result && (this.getId() == __instance.getId()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarientitatid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_rebreavis_usrentitat_fk"))
    private UsuariEntitatJPA usuariEntitat;

    public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

    public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }

// IMP Field:tipusnotificacioid | Table: pfi_tipusnotificacio | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipusnotificacioid", referencedColumnName ="tipusNotificacioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_rebreavis_tipnotific_fk"))
    private TipusNotificacioJPA tipusNotificacio;

    public TipusNotificacioJPA getTipusNotificacio() {
    return this.tipusNotificacio;
  }

    public  void setTipusNotificacio(TipusNotificacioJPA tipusNotificacio) {
    this.tipusNotificacio = tipusNotificacio;
  }


 // ---------------  STATIC METHODS ------------------
  public static RebreAvisJPA toJPA(RebreAvis __bean) {
    if (__bean == null) { return null;}
    RebreAvisJPA __tmp = new RebreAvisJPA();
    __tmp.setId(__bean.getId());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setTipusNotificacioID(__bean.getTipusNotificacioID());
    __tmp.setRebreAgrupat(__bean.isRebreAgrupat());
		return __tmp;
	}


  public static RebreAvisJPA copyJPA(RebreAvisJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<RebreAvisJPA> copyJPA(java.util.Set<RebreAvisJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<RebreAvisJPA> __tmpSet = (java.util.Set<RebreAvisJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<RebreAvisJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (RebreAvisJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static RebreAvisJPA copyJPA(RebreAvisJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    RebreAvisJPA __tmp = (RebreAvisJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TipusNotificacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusNotificacio) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusNotificacio()) ) ) {
      __tmp.setTipusNotificacio(TipusNotificacioJPA.copyJPA(__jpa.getTipusNotificacio(), __alreadyCopied,"RebreAvisJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitat()) ) ) {
      __tmp.setUsuariEntitat(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitat(), __alreadyCopied,"RebreAvisJPA"));
    }

    return __tmp;
  }




}
