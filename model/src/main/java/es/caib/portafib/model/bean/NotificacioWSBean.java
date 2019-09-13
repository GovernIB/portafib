
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.NotificacioWS;


public class NotificacioWSBean implements NotificacioWS {



private static final long serialVersionUID = -1937251554L;

	long notificacioID;// PK
	long peticioDeFirmaID;
	long tipusNotificacioID;
	java.sql.Timestamp dataCreacio;
	java.sql.Timestamp dataEnviament;
	java.lang.String descripcio;
	boolean bloquejada;
	java.lang.String error;
	java.sql.Timestamp dataError;
	int reintents;
	java.lang.String usuariAplicacioID;


  /** Constructor Buit */
  public NotificacioWSBean() {
  }

  /** Constructor amb tots els camps  */
  public NotificacioWSBean(long notificacioID , long peticioDeFirmaID , long tipusNotificacioID , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataEnviament , java.lang.String descripcio , boolean bloquejada , java.lang.String error , java.sql.Timestamp dataError , int reintents , java.lang.String usuariAplicacioID) {
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
    this.usuariAplicacioID=usuariAplicacioID;
}
  /** Constructor sense valors autoincrementals */
  public NotificacioWSBean(long peticioDeFirmaID , long tipusNotificacioID , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataEnviament , java.lang.String descripcio , boolean bloquejada , java.lang.String error , java.sql.Timestamp dataError , int reintents , java.lang.String usuariAplicacioID) {
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.tipusNotificacioID=tipusNotificacioID;
    this.dataCreacio=dataCreacio;
    this.dataEnviament=dataEnviament;
    this.descripcio=descripcio;
    this.bloquejada=bloquejada;
    this.error=error;
    this.dataError=dataError;
    this.reintents=reintents;
    this.usuariAplicacioID=usuariAplicacioID;
}
  /** Constructor dels valors Not Null */
  public NotificacioWSBean(long notificacioID , long peticioDeFirmaID , long tipusNotificacioID , java.sql.Timestamp dataCreacio , int reintents , java.lang.String usuariAplicacioID) {
    this.notificacioID=notificacioID;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.tipusNotificacioID=tipusNotificacioID;
    this.dataCreacio=dataCreacio;
    this.reintents=reintents;
    this.usuariAplicacioID=usuariAplicacioID;
}
  public NotificacioWSBean(NotificacioWS __bean) {
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
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
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

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};



  // ======================================

  public static NotificacioWSBean toBean(NotificacioWS __bean) {
    if (__bean == null) { return null;}
    NotificacioWSBean __tmp = new NotificacioWSBean();
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
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
		return __tmp;
	}



}
