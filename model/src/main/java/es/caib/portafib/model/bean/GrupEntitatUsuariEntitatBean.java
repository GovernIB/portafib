
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.GrupEntitatUsuariEntitat;


public class GrupEntitatUsuariEntitatBean implements GrupEntitatUsuariEntitat {



	long grupEntitatUsuariEntitatID;// PK
	java.lang.String usuariEntitatID;
	java.lang.Long grupEntitatID;


  /** Constructor Buit */
  public GrupEntitatUsuariEntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public GrupEntitatUsuariEntitatBean(long grupEntitatUsuariEntitatID , java.lang.String usuariEntitatID , java.lang.Long grupEntitatID) {
    this.grupEntitatUsuariEntitatID=grupEntitatUsuariEntitatID;
    this.usuariEntitatID=usuariEntitatID;
    this.grupEntitatID=grupEntitatID;
}
  /** Constructor sense valors autoincrementals */
  public GrupEntitatUsuariEntitatBean(java.lang.String usuariEntitatID , java.lang.Long grupEntitatID) {
    this.usuariEntitatID=usuariEntitatID;
    this.grupEntitatID=grupEntitatID;
}
  public GrupEntitatUsuariEntitatBean(GrupEntitatUsuariEntitat __bean) {
    this.setGrupEntitatUsuariEntitatID(__bean.getGrupEntitatUsuariEntitatID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setGrupEntitatID(__bean.getGrupEntitatID());
	}

	public long getGrupEntitatUsuariEntitatID() {
		return(grupEntitatUsuariEntitatID);
	};
	public void setGrupEntitatUsuariEntitatID(long _grupEntitatUsuariEntitatID_) {
		this.grupEntitatUsuariEntitatID = _grupEntitatUsuariEntitatID_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public java.lang.Long getGrupEntitatID() {
		return(grupEntitatID);
	};
	public void setGrupEntitatID(java.lang.Long _grupEntitatID_) {
		this.grupEntitatID = _grupEntitatID_;
	};



  // ======================================

  public static GrupEntitatUsuariEntitatBean toBean(GrupEntitatUsuariEntitat __bean) {
    if (__bean == null) { return null;}
    GrupEntitatUsuariEntitatBean __tmp = new GrupEntitatUsuariEntitatBean();
    __tmp.setGrupEntitatUsuariEntitatID(__bean.getGrupEntitatUsuariEntitatID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setGrupEntitatID(__bean.getGrupEntitatID());
		return __tmp;
	}



}
