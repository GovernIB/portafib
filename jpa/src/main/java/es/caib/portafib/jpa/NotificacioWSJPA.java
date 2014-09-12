
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_notificacio" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class NotificacioWSJPA implements NotificacioWS {



private static final long serialVersionUID = 1184441005L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_notificacio_pk_i")
	@Column(name="notificacioid",nullable = false,length = 19)
	long notificacioID;

	@Index(name="pfi_notifica_peticioid_fk_i")
	@Column(name="peticiodefirmaid",nullable = false,length = 19)
	long peticioDeFirmaID;

	@Index(name="pfi_notifica_tiponotiid_fk_i")
	@Column(name="tipusnotificacioid",nullable = false,length = 19)
	long tipusNotificacioID;

	@Column(name="datacreacio",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp dataCreacio;

	@Column(name="dataenviament",length = 29,precision = 6)
	java.sql.Timestamp dataEnviament;

	@Column(name="descripcio",length = 2147483647)
  @Lob
	java.lang.String descripcio;

	@Column(name="bloquejada",length = 1)
	boolean bloquejada;

	@Column(name="error",length = 2147483647)
  @Lob
	java.lang.String error;

	@Column(name="dataerror",length = 35,precision = 6)
	java.sql.Timestamp dataError;

	@Column(name="reintents",nullable = false,length = 10)
	int reintents;



  /** Constructor Buit */
  public NotificacioWSJPA() {
  }

  /** Constructor amb tots els camps  */
  public NotificacioWSJPA(long notificacioID , long peticioDeFirmaID , long tipusNotificacioID , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataEnviament , java.lang.String descripcio , boolean bloquejada , java.lang.String error , java.sql.Timestamp dataError , int reintents) {
    this.notificacioID=notificacioID;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.tipusNotificacioID=tipusNotificacioID;
    this.dataCreacio=dataCreacio;
    this.dataEnviament=dataEnviament;
    this.descripcio=descripcio;
    this.bloquejada=bloquejada;
    this.error=error;
    this.dataError=dataError;
    this.reintents=reintents;
}
  /** Constructor sense valors autoincrementals */
  public NotificacioWSJPA(long peticioDeFirmaID , long tipusNotificacioID , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataEnviament , java.lang.String descripcio , boolean bloquejada , java.lang.String error , java.sql.Timestamp dataError , int reintents) {
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.tipusNotificacioID=tipusNotificacioID;
    this.dataCreacio=dataCreacio;
    this.dataEnviament=dataEnviament;
    this.descripcio=descripcio;
    this.bloquejada=bloquejada;
    this.error=error;
    this.dataError=dataError;
    this.reintents=reintents;
}
  /** Constructor dels valors Not Null */
  public NotificacioWSJPA(long notificacioID , long peticioDeFirmaID , long tipusNotificacioID , java.sql.Timestamp dataCreacio , int reintents) {
    this.notificacioID=notificacioID;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.tipusNotificacioID=tipusNotificacioID;
    this.dataCreacio=dataCreacio;
    this.reintents=reintents;
}
  public NotificacioWSJPA(NotificacioWS __bean) {
    this.setNotificacioID(__bean.getNotificacioID());
    this.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    this.setTipusNotificacioID(__bean.getTipusNotificacioID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setDataEnviament(__bean.getDataEnviament());
    this.setDescripcio(__bean.getDescripcio());
    this.setBloquejada(__bean.isBloquejada());
    this.setError(__bean.getError());
    this.setDataError(__bean.getDataError());
    this.setReintents(__bean.getReintents());
	}

  public static NotificacioWSJPA toJPA(NotificacioWS __bean) {
    if (__bean == null) { return null;}
    NotificacioWSJPA __tmp = new NotificacioWSJPA();
    __tmp.setNotificacioID(__bean.getNotificacioID());
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setTipusNotificacioID(__bean.getTipusNotificacioID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setDataEnviament(__bean.getDataEnviament());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setBloquejada(__bean.isBloquejada());
    __tmp.setError(__bean.getError());
    __tmp.setDataError(__bean.getDataError());
    __tmp.setReintents(__bean.getReintents());
		return __tmp;
	}

	public long getNotificacioID() {
		return(notificacioID);
	};
	public void setNotificacioID(long _notificacioID_) {
		this.notificacioID = _notificacioID_;
	};

	public long getPeticioDeFirmaID() {
		return(peticioDeFirmaID);
	};
	public void setPeticioDeFirmaID(long _peticioDeFirmaID_) {
		this.peticioDeFirmaID = _peticioDeFirmaID_;
	};

	public long getTipusNotificacioID() {
		return(tipusNotificacioID);
	};
	public void setTipusNotificacioID(long _tipusNotificacioID_) {
		this.tipusNotificacioID = _tipusNotificacioID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.sql.Timestamp getDataEnviament() {
		return(dataEnviament);
	};
	public void setDataEnviament(java.sql.Timestamp _dataEnviament_) {
		this.dataEnviament = _dataEnviament_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public boolean isBloquejada() {
		return(bloquejada);
	};
	public void setBloquejada(boolean _bloquejada_) {
		this.bloquejada = _bloquejada_;
	};

	public java.lang.String getError() {
		return(error);
	};
	public void setError(java.lang.String _error_) {
		this.error = _error_;
	};

	public java.sql.Timestamp getDataError() {
		return(dataError);
	};
	public void setDataError(java.sql.Timestamp _dataError_) {
		this.dataError = _dataError_;
	};

	public int getReintents() {
		return(reintents);
	};
	public void setReintents(int _reintents_) {
		this.reintents = _reintents_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof NotificacioWS) {
      NotificacioWS __instance = (NotificacioWS)__obj;
      __result = true;
      __result = __result && (this.getNotificacioID() == __instance.getNotificacioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:peticiodefirmaid | Table: pfi_peticiodefirma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_notifica_petifirma_fk")
	@JoinColumn(name = "peticiodefirmaid", referencedColumnName ="peticioDeFirmaID", nullable = false, insertable=false, updatable=false)
	private PeticioDeFirmaJPA peticioDeFirma;

	public PeticioDeFirmaJPA getPeticioDeFirma() {
    return this.peticioDeFirma;
  }

	public  void setPeticioDeFirma(PeticioDeFirmaJPA peticioDeFirma) {
    this.peticioDeFirma = peticioDeFirma;
  }

// IMP Field:tipusnotificacioid | Table: pfi_tipusnotificacio | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_notifica_tipnotific_fk")
	@JoinColumn(name = "tipusnotificacioid", referencedColumnName ="tipusNotificacioID", nullable = false, insertable=false, updatable=false)
	private TipusNotificacioJPA tipusNotificacio;

	public TipusNotificacioJPA getTipusNotificacio() {
    return this.tipusNotificacio;
  }

	public  void setTipusNotificacio(TipusNotificacioJPA tipusNotificacio) {
    this.tipusNotificacio = tipusNotificacio;
  }



}
