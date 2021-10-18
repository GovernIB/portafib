
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "pfi_tipusnotificacio" )
@SequenceGenerator(name="TIPUSNOTIFICACIO_SEQ", sequenceName="pfi_tipusnotificacio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TipusNotificacioJPA implements TipusNotificacio {



private static final long serialVersionUID = -1316357342L;

    @Id
    @Index(name="pfi_tipusnotificacio_pk_i")
    @Column(name="tipusnotificacioid",nullable = false,length = 19)
    long tipusNotificacioID;

    @Column(name="nom",nullable = false,length = 50)
    java.lang.String nom;

    @Column(name="descripcio",length = 250)
    java.lang.String descripcio;

  /** Si es avis val true Si és notificació es false Si pot ser avis i notificació llavors val null  */
    @Column(name="esavis",length = 1)
    java.lang.Boolean esAvis;



  /** Constructor Buit */
  public TipusNotificacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public TipusNotificacioJPA(long tipusNotificacioID , java.lang.String nom , java.lang.String descripcio , java.lang.Boolean esAvis) {
    this.tipusNotificacioID=tipusNotificacioID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.esAvis=esAvis;
}
  /** Constructor dels valors Not Null */
  public TipusNotificacioJPA(long tipusNotificacioID , java.lang.String nom) {
    this.tipusNotificacioID=tipusNotificacioID;
    this.nom=nom;
}
  public TipusNotificacioJPA(TipusNotificacio __bean) {
    this.setTipusNotificacioID(__bean.getTipusNotificacioID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setEsAvis(__bean.getEsAvis());
	}

	public long getTipusNotificacioID() {
		return(tipusNotificacioID);
	};
	public void setTipusNotificacioID(long _tipusNotificacioID_) {
		this.tipusNotificacioID = _tipusNotificacioID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.Boolean getEsAvis() {
		return(esAvis);
	};
	public void setEsAvis(java.lang.Boolean _esAvis_) {
		this.esAvis = _esAvis_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TipusNotificacio) {
      TipusNotificacio __instance = (TipusNotificacio)__obj;
      __result = true;
      __result = __result && (this.getTipusNotificacioID() == __instance.getTipusNotificacioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:tipusnotificacioid | Table: pfi_notificacio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusNotificacio")
    private Set<NotificacioWSJPA> notificacioWSs = new HashSet<NotificacioWSJPA>(0);
    public  Set<NotificacioWSJPA> getNotificacioWSs() {
    return this.notificacioWSs;
  }

    public void setNotificacioWSs(Set<NotificacioWSJPA> notificacioWSs) {
      this.notificacioWSs = notificacioWSs;
    }


// EXP  Field:tipusnotificacioid | Table: pfi_rebreavis | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusNotificacio")
    private Set<RebreAvisJPA> rebreAviss = new HashSet<RebreAvisJPA>(0);
    public  Set<RebreAvisJPA> getRebreAviss() {
    return this.rebreAviss;
  }

    public void setRebreAviss(Set<RebreAvisJPA> rebreAviss) {
      this.rebreAviss = rebreAviss;
    }



 // ---------------  STATIC METHODS ------------------
  public static TipusNotificacioJPA toJPA(TipusNotificacio __bean) {
    if (__bean == null) { return null;}
    TipusNotificacioJPA __tmp = new TipusNotificacioJPA();
    __tmp.setTipusNotificacioID(__bean.getTipusNotificacioID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEsAvis(__bean.getEsAvis());
		return __tmp;
	}


  public static TipusNotificacioJPA copyJPA(TipusNotificacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TipusNotificacioJPA> copyJPA(java.util.Set<TipusNotificacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<TipusNotificacioJPA> __tmpSet = (java.util.Set<TipusNotificacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TipusNotificacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TipusNotificacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TipusNotificacioJPA copyJPA(TipusNotificacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TipusNotificacioJPA __tmp = (TipusNotificacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"NotificacioWSJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.notificacioWSs) || org.hibernate.Hibernate.isInitialized(__jpa.getNotificacioWSs())) ) {
      __tmp.setNotificacioWSs(NotificacioWSJPA.copyJPA(__jpa.getNotificacioWSs(), __alreadyCopied,"TipusNotificacioJPA"));
    }
    if(!"RebreAvisJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.rebreAviss) || org.hibernate.Hibernate.isInitialized(__jpa.getRebreAviss())) ) {
      __tmp.setRebreAviss(RebreAvisJPA.copyJPA(__jpa.getRebreAviss(), __alreadyCopied,"TipusNotificacioJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
