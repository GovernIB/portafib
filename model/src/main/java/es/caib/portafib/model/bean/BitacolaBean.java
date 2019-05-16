
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.Bitacola;


public class BitacolaBean implements Bitacola {



private static final long serialVersionUID = -965139643L;

	long bitacolaID;// PK
	java.sql.Timestamp data;
	java.lang.String descripcio;
	long peticioDeFirmaID;
	java.lang.String usuariEntitatID;
	java.lang.String usuariAplicacioID;


  /** Constructor Buit */
  public BitacolaBean() {
  }

  /** Constructor amb tots els camps  */
  public BitacolaBean(long bitacolaID , java.sql.Timestamp data , java.lang.String descripcio , long peticioDeFirmaID , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID) {
    this.bitacolaID=bitacolaID;
    this.data=data;
    this.descripcio=descripcio;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.usuariEntitatID=usuariEntitatID;
    this.usuariAplicacioID=usuariAplicacioID;
}
  /** Constructor sense valors autoincrementals */
  public BitacolaBean(java.sql.Timestamp data , java.lang.String descripcio , long peticioDeFirmaID , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID) {
    this.data=data;
    this.descripcio=descripcio;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.usuariEntitatID=usuariEntitatID;
    this.usuariAplicacioID=usuariAplicacioID;
}
  /** Constructor dels valors Not Null */
  public BitacolaBean(long bitacolaID , java.sql.Timestamp data , java.lang.String descripcio , long peticioDeFirmaID) {
    this.bitacolaID=bitacolaID;
    this.data=data;
    this.descripcio=descripcio;
    this.peticioDeFirmaID=peticioDeFirmaID;
}
  public BitacolaBean(Bitacola __bean) {
    this.setBitacolaID(__bean.getBitacolaID());
    this.setData(__bean.getData());
    this.setDescripcio(__bean.getDescripcio());
    this.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
	}

	public long getBitacolaID() {
		return(bitacolaID);
	};
	public void setBitacolaID(long _bitacolaID_) {
		this.bitacolaID = _bitacolaID_;
	};

	public java.sql.Timestamp getData() {
		return(data);
	};
	public void setData(java.sql.Timestamp _data_) {
		this.data = _data_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public long getPeticioDeFirmaID() {
		return(peticioDeFirmaID);
	};
	public void setPeticioDeFirmaID(long _peticioDeFirmaID_) {
		this.peticioDeFirmaID = _peticioDeFirmaID_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};



  // ======================================

  public static BitacolaBean toBean(Bitacola __bean) {
    if (__bean == null) { return null;}
    BitacolaBean __tmp = new BitacolaBean();
    __tmp.setBitacolaID(__bean.getBitacolaID());
    __tmp.setData(__bean.getData());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
		return __tmp;
	}



}
