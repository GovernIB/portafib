
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
@Table(name = "pfi_rebreavis"  , uniqueConstraints = {
            @UniqueConstraint( columnNames={"tipusnotificacioid","usuarientitatid"}) } )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
public class RebreAvisJPA implements RebreAvis {



private static final long serialVersionUID = -111354374L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_rebreavis_pk_i")
	@Column(name="id",nullable = false,length = 19)
	long id;

	@Index(name="pfi_rebreavis_usrentid_fk_i")
	@Column(name="usuarientitatid",nullable = false,length = 101)
	java.lang.String usuariEntitatID;

	@Index(name="pfi_rebreavis_tiponotiid_fk_i")
	@Column(name="tipusnotificacioid",nullable = false,length = 19)
	long tipusNotificacioID;



  /** Constructor Buit */
  public RebreAvisJPA() {
  }

  /** Constructor amb tots els camps  */
  public RebreAvisJPA(long id , java.lang.String usuariEntitatID , long tipusNotificacioID) {
    this.id=id;
    this.usuariEntitatID=usuariEntitatID;
    this.tipusNotificacioID=tipusNotificacioID;
}
  /** Constructor sense valors autoincrementals */
  public RebreAvisJPA(java.lang.String usuariEntitatID , long tipusNotificacioID) {
    this.usuariEntitatID=usuariEntitatID;
    this.tipusNotificacioID=tipusNotificacioID;
}
  public RebreAvisJPA(RebreAvis __bean) {
    this.setId(__bean.getId());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setTipusNotificacioID(__bean.getTipusNotificacioID());
	}

  public static RebreAvisJPA toJPA(RebreAvis __bean) {
    if (__bean == null) { return null;}
    RebreAvisJPA __tmp = new RebreAvisJPA();
    __tmp.setId(__bean.getId());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setTipusNotificacioID(__bean.getTipusNotificacioID());
		return __tmp;
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
	@ForeignKey(name="pfi_rebreavis_usrentitat_fk")
	@JoinColumn(name = "usuarientitatid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false)
	private UsuariEntitatJPA usuariEntitat;

	public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

	public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }

// IMP Field:tipusnotificacioid | Table: pfi_tipusnotificacio | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_rebreavis_tipnotific_fk")
	@JoinColumn(name = "tipusnotificacioid", referencedColumnName ="tipusNotificacioID", nullable = false, insertable=false, updatable=false)
	private TipusNotificacioJPA tipusNotificacio;

	public TipusNotificacioJPA getTipusNotificacio() {
    return this.tipusNotificacio;
  }

	public  void setTipusNotificacio(TipusNotificacioJPA tipusNotificacio) {
    this.tipusNotificacio = tipusNotificacio;
  }



}
