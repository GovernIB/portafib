
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.Bitacola;


public class BitacolaBean implements Bitacola {



	long bitacolaID;// PK
	java.lang.String entitatid;
	java.lang.String usuariid;
	java.sql.Timestamp data;
	int tipusObjecte;
	java.lang.String objecteid;
	int tipusOperacio;
	java.lang.String descripcio;
	java.lang.String objecteSerialitzat;


  /** Constructor Buit */
  public BitacolaBean() {
  }

  /** Constructor amb tots els camps  */
  public BitacolaBean(long bitacolaID , java.lang.String entitatid , java.lang.String usuariid , java.sql.Timestamp data , int tipusObjecte , java.lang.String objecteid , int tipusOperacio , java.lang.String descripcio , java.lang.String objecteSerialitzat) {
    this.bitacolaID=bitacolaID;
    this.entitatid=entitatid;
    this.usuariid=usuariid;
    this.data=data;
    this.tipusObjecte=tipusObjecte;
    this.objecteid=objecteid;
    this.tipusOperacio=tipusOperacio;
    this.descripcio=descripcio;
    this.objecteSerialitzat=objecteSerialitzat;
}
  /** Constructor sense valors autoincrementals */
  public BitacolaBean(java.lang.String entitatid , java.lang.String usuariid , java.sql.Timestamp data , int tipusObjecte , java.lang.String objecteid , int tipusOperacio , java.lang.String descripcio , java.lang.String objecteSerialitzat) {
    this.entitatid=entitatid;
    this.usuariid=usuariid;
    this.data=data;
    this.tipusObjecte=tipusObjecte;
    this.objecteid=objecteid;
    this.tipusOperacio=tipusOperacio;
    this.descripcio=descripcio;
    this.objecteSerialitzat=objecteSerialitzat;
}
  /** Constructor dels valors Not Null */
  public BitacolaBean(long bitacolaID , java.lang.String entitatid , java.lang.String usuariid , java.sql.Timestamp data , int tipusObjecte , java.lang.String objecteid , int tipusOperacio) {
    this.bitacolaID=bitacolaID;
    this.entitatid=entitatid;
    this.usuariid=usuariid;
    this.data=data;
    this.tipusObjecte=tipusObjecte;
    this.objecteid=objecteid;
    this.tipusOperacio=tipusOperacio;
}
  public BitacolaBean(Bitacola __bean) {
    this.setBitacolaID(__bean.getBitacolaID());
    this.setEntitatid(__bean.getEntitatid());
    this.setUsuariid(__bean.getUsuariid());
    this.setData(__bean.getData());
    this.setTipusObjecte(__bean.getTipusObjecte());
    this.setObjecteid(__bean.getObjecteid());
    this.setTipusOperacio(__bean.getTipusOperacio());
    this.setDescripcio(__bean.getDescripcio());
    this.setObjecteSerialitzat(__bean.getObjecteSerialitzat());
	}

	public long getBitacolaID() {
		return(bitacolaID);
	};
	public void setBitacolaID(long _bitacolaID_) {
		this.bitacolaID = _bitacolaID_;
	};

	public java.lang.String getEntitatid() {
		return(entitatid);
	};
	public void setEntitatid(java.lang.String _entitatid_) {
		this.entitatid = _entitatid_;
	};

	public java.lang.String getUsuariid() {
		return(usuariid);
	};
	public void setUsuariid(java.lang.String _usuariid_) {
		this.usuariid = _usuariid_;
	};

	public java.sql.Timestamp getData() {
		return(data);
	};
	public void setData(java.sql.Timestamp _data_) {
		this.data = _data_;
	};

	public int getTipusObjecte() {
		return(tipusObjecte);
	};
	public void setTipusObjecte(int _tipusObjecte_) {
		this.tipusObjecte = _tipusObjecte_;
	};

	public java.lang.String getObjecteid() {
		return(objecteid);
	};
	public void setObjecteid(java.lang.String _objecteid_) {
		this.objecteid = _objecteid_;
	};

	public int getTipusOperacio() {
		return(tipusOperacio);
	};
	public void setTipusOperacio(int _tipusOperacio_) {
		this.tipusOperacio = _tipusOperacio_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getObjecteSerialitzat() {
		return(objecteSerialitzat);
	};
	public void setObjecteSerialitzat(java.lang.String _objecteSerialitzat_) {
		this.objecteSerialitzat = _objecteSerialitzat_;
	};



  // ======================================

  public static BitacolaBean toBean(Bitacola __bean) {
    if (__bean == null) { return null;}
    BitacolaBean __tmp = new BitacolaBean();
    __tmp.setBitacolaID(__bean.getBitacolaID());
    __tmp.setEntitatid(__bean.getEntitatid());
    __tmp.setUsuariid(__bean.getUsuariid());
    __tmp.setData(__bean.getData());
    __tmp.setTipusObjecte(__bean.getTipusObjecte());
    __tmp.setObjecteid(__bean.getObjecteid());
    __tmp.setTipusOperacio(__bean.getTipusOperacio());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setObjecteSerialitzat(__bean.getObjecteSerialitzat());
		return __tmp;
	}



}
