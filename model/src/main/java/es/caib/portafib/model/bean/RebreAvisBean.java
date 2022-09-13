
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.RebreAvis;


public class RebreAvisBean implements RebreAvis {



	long id;// PK
	java.lang.String usuariEntitatID;
	long tipusNotificacioID;
	boolean rebreAgrupat;


  /** Constructor Buit */
  public RebreAvisBean() {
  }

  /** Constructor amb tots els camps  */
  public RebreAvisBean(long id , java.lang.String usuariEntitatID , long tipusNotificacioID , boolean rebreAgrupat) {
    this.id=id;
    this.usuariEntitatID=usuariEntitatID;
    this.tipusNotificacioID=tipusNotificacioID;
    this.rebreAgrupat=rebreAgrupat;
}
  /** Constructor sense valors autoincrementals */
  public RebreAvisBean(java.lang.String usuariEntitatID , long tipusNotificacioID , boolean rebreAgrupat) {
    this.usuariEntitatID=usuariEntitatID;
    this.tipusNotificacioID=tipusNotificacioID;
    this.rebreAgrupat=rebreAgrupat;
}
  public RebreAvisBean(RebreAvis __bean) {
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



  // ======================================

  public static RebreAvisBean toBean(RebreAvis __bean) {
    if (__bean == null) { return null;}
    RebreAvisBean __tmp = new RebreAvisBean();
    __tmp.setId(__bean.getId());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setTipusNotificacioID(__bean.getTipusNotificacioID());
    __tmp.setRebreAgrupat(__bean.isRebreAgrupat());
		return __tmp;
	}



}
