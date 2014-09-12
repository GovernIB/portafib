
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.RebreAvis;


public class RebreAvisBean implements RebreAvis {



private static final long serialVersionUID = 842764657L;

	long id;// PK
	java.lang.String usuariEntitatID;
	long tipusNotificacioID;


  /** Constructor Buit */
  public RebreAvisBean() {
  }

  /** Constructor amb tots els camps  */
  public RebreAvisBean(long id , java.lang.String usuariEntitatID , long tipusNotificacioID) {
    this.id=id;
    this.usuariEntitatID=usuariEntitatID;
    this.tipusNotificacioID=tipusNotificacioID;
}
  /** Constructor sense valors autoincrementals */
  public RebreAvisBean(java.lang.String usuariEntitatID , long tipusNotificacioID) {
    this.usuariEntitatID=usuariEntitatID;
    this.tipusNotificacioID=tipusNotificacioID;
}
  public RebreAvisBean(RebreAvis __bean) {
    this.setId(__bean.getId());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setTipusNotificacioID(__bean.getTipusNotificacioID());
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



  // ======================================

  public static RebreAvisBean toBean(RebreAvis __bean) {
    if (__bean == null) { return null;}
    RebreAvisBean __tmp = new RebreAvisBean();
    __tmp.setId(__bean.getId());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setTipusNotificacioID(__bean.getTipusNotificacioID());
		return __tmp;
	}



}
