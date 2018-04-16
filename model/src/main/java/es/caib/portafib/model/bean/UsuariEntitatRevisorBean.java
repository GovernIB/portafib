
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.UsuariEntitatRevisor;


public class UsuariEntitatRevisorBean implements UsuariEntitatRevisor {



private static final long serialVersionUID = 960191768L;

	long usuariEntitatRevisorId;// PK
	java.lang.String usuariEntitatID;
	boolean actiu;


  /** Constructor Buit */
  public UsuariEntitatRevisorBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariEntitatRevisorBean(long usuariEntitatRevisorId , java.lang.String usuariEntitatID , boolean actiu) {
    this.usuariEntitatRevisorId=usuariEntitatRevisorId;
    this.usuariEntitatID=usuariEntitatID;
    this.actiu=actiu;
}
  /** Constructor sense valors autoincrementals */
  public UsuariEntitatRevisorBean(java.lang.String usuariEntitatID , boolean actiu) {
    this.usuariEntitatID=usuariEntitatID;
    this.actiu=actiu;
}
  public UsuariEntitatRevisorBean(UsuariEntitatRevisor __bean) {
    this.setUsuariEntitatRevisorId(__bean.getUsuariEntitatRevisorId());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setActiu(__bean.isActiu());
	}

	public long getUsuariEntitatRevisorId() {
		return(usuariEntitatRevisorId);
	};
	public void setUsuariEntitatRevisorId(long _usuariEntitatRevisorId_) {
		this.usuariEntitatRevisorId = _usuariEntitatRevisorId_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};



  // ======================================

  public static UsuariEntitatRevisorBean toBean(UsuariEntitatRevisor __bean) {
    if (__bean == null) { return null;}
    UsuariEntitatRevisorBean __tmp = new UsuariEntitatRevisorBean();
    __tmp.setUsuariEntitatRevisorId(__bean.getUsuariEntitatRevisorId());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setActiu(__bean.isActiu());
		return __tmp;
	}



}
