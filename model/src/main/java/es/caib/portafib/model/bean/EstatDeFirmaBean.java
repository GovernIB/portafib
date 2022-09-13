
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.EstatDeFirma;


public class EstatDeFirmaBean implements EstatDeFirma {



	long estatDeFirmaID;// PK
	long firmaID;
	java.lang.String usuariEntitatID;
	java.sql.Timestamp dataInici;
	java.sql.Timestamp dataFi;
	long tipusEstatDeFirmaInicialID;
	java.lang.Long tipusEstatDeFirmaFinalID;
	java.lang.Long colaboracioDelegacioID;
	java.lang.String descripcio;


  /** Constructor Buit */
  public EstatDeFirmaBean() {
  }

  /** Constructor amb tots els camps  */
  public EstatDeFirmaBean(long estatDeFirmaID , long firmaID , java.lang.String usuariEntitatID , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , long tipusEstatDeFirmaInicialID , java.lang.Long tipusEstatDeFirmaFinalID , java.lang.Long colaboracioDelegacioID , java.lang.String descripcio) {
    this.estatDeFirmaID=estatDeFirmaID;
    this.firmaID=firmaID;
    this.usuariEntitatID=usuariEntitatID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.tipusEstatDeFirmaInicialID=tipusEstatDeFirmaInicialID;
    this.tipusEstatDeFirmaFinalID=tipusEstatDeFirmaFinalID;
    this.colaboracioDelegacioID=colaboracioDelegacioID;
    this.descripcio=descripcio;
}
  /** Constructor sense valors autoincrementals */
  public EstatDeFirmaBean(long firmaID , java.lang.String usuariEntitatID , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , long tipusEstatDeFirmaInicialID , java.lang.Long tipusEstatDeFirmaFinalID , java.lang.Long colaboracioDelegacioID , java.lang.String descripcio) {
    this.firmaID=firmaID;
    this.usuariEntitatID=usuariEntitatID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.tipusEstatDeFirmaInicialID=tipusEstatDeFirmaInicialID;
    this.tipusEstatDeFirmaFinalID=tipusEstatDeFirmaFinalID;
    this.colaboracioDelegacioID=colaboracioDelegacioID;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public EstatDeFirmaBean(long estatDeFirmaID , long firmaID , java.lang.String usuariEntitatID , java.sql.Timestamp dataInici , long tipusEstatDeFirmaInicialID) {
    this.estatDeFirmaID=estatDeFirmaID;
    this.firmaID=firmaID;
    this.usuariEntitatID=usuariEntitatID;
    this.dataInici=dataInici;
    this.tipusEstatDeFirmaInicialID=tipusEstatDeFirmaInicialID;
}
  public EstatDeFirmaBean(EstatDeFirma __bean) {
    this.setEstatDeFirmaID(__bean.getEstatDeFirmaID());
    this.setFirmaID(__bean.getFirmaID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setTipusEstatDeFirmaInicialID(__bean.getTipusEstatDeFirmaInicialID());
    this.setTipusEstatDeFirmaFinalID(__bean.getTipusEstatDeFirmaFinalID());
    this.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getEstatDeFirmaID() {
		return(estatDeFirmaID);
	};
	public void setEstatDeFirmaID(long _estatDeFirmaID_) {
		this.estatDeFirmaID = _estatDeFirmaID_;
	};

	public long getFirmaID() {
		return(firmaID);
	};
	public void setFirmaID(long _firmaID_) {
		this.firmaID = _firmaID_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Timestamp getDataFi() {
		return(dataFi);
	};
	public void setDataFi(java.sql.Timestamp _dataFi_) {
		this.dataFi = _dataFi_;
	};

	public long getTipusEstatDeFirmaInicialID() {
		return(tipusEstatDeFirmaInicialID);
	};
	public void setTipusEstatDeFirmaInicialID(long _tipusEstatDeFirmaInicialID_) {
		this.tipusEstatDeFirmaInicialID = _tipusEstatDeFirmaInicialID_;
	};

	public java.lang.Long getTipusEstatDeFirmaFinalID() {
		return(tipusEstatDeFirmaFinalID);
	};
	public void setTipusEstatDeFirmaFinalID(java.lang.Long _tipusEstatDeFirmaFinalID_) {
		this.tipusEstatDeFirmaFinalID = _tipusEstatDeFirmaFinalID_;
	};

	public java.lang.Long getColaboracioDelegacioID() {
		return(colaboracioDelegacioID);
	};
	public void setColaboracioDelegacioID(java.lang.Long _colaboracioDelegacioID_) {
		this.colaboracioDelegacioID = _colaboracioDelegacioID_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};



  // ======================================

  public static EstatDeFirmaBean toBean(EstatDeFirma __bean) {
    if (__bean == null) { return null;}
    EstatDeFirmaBean __tmp = new EstatDeFirmaBean();
    __tmp.setEstatDeFirmaID(__bean.getEstatDeFirmaID());
    __tmp.setFirmaID(__bean.getFirmaID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setTipusEstatDeFirmaInicialID(__bean.getTipusEstatDeFirmaInicialID());
    __tmp.setTipusEstatDeFirmaFinalID(__bean.getTipusEstatDeFirmaFinalID());
    __tmp.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
