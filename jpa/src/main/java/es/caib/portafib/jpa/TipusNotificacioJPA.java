
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;


@Entity
@Table(name = "pfi_tipusnotificacio" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
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

  public static TipusNotificacioJPA toJPA(TipusNotificacio __bean) {
    if (__bean == null) { return null;}
    TipusNotificacioJPA __tmp = new TipusNotificacioJPA();
    __tmp.setTipusNotificacioID(__bean.getTipusNotificacioID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEsAvis(__bean.getEsAvis());
		return __tmp;
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




}
