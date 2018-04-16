
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.RevisorDeFirma;


public class RevisorDeFirmaBean implements RevisorDeFirma {



private static final long serialVersionUID = 1313788674L;

	long revisorDeFirmaID;// PK
	long usuariEntitatRevisorID;
	long firmaID;
	boolean obligatori;


  /** Constructor Buit */
  public RevisorDeFirmaBean() {
  }

  /** Constructor amb tots els camps  */
  public RevisorDeFirmaBean(long revisorDeFirmaID , long usuariEntitatRevisorID , long firmaID , boolean obligatori) {
    this.revisorDeFirmaID=revisorDeFirmaID;
    this.usuariEntitatRevisorID=usuariEntitatRevisorID;
    this.firmaID=firmaID;
    this.obligatori=obligatori;
}
  /** Constructor sense valors autoincrementals */
  public RevisorDeFirmaBean(long usuariEntitatRevisorID , long firmaID , boolean obligatori) {
    this.usuariEntitatRevisorID=usuariEntitatRevisorID;
    this.firmaID=firmaID;
    this.obligatori=obligatori;
}
  public RevisorDeFirmaBean(RevisorDeFirma __bean) {
    this.setRevisorDeFirmaID(__bean.getRevisorDeFirmaID());
    this.setUsuariEntitatRevisorID(__bean.getUsuariEntitatRevisorID());
    this.setFirmaID(__bean.getFirmaID());
    this.setObligatori(__bean.isObligatori());
	}

	public long getRevisorDeFirmaID() {
		return(revisorDeFirmaID);
	};
	public void setRevisorDeFirmaID(long _revisorDeFirmaID_) {
		this.revisorDeFirmaID = _revisorDeFirmaID_;
	};

	public long getUsuariEntitatRevisorID() {
		return(usuariEntitatRevisorID);
	};
	public void setUsuariEntitatRevisorID(long _usuariEntitatRevisorID_) {
		this.usuariEntitatRevisorID = _usuariEntitatRevisorID_;
	};

	public long getFirmaID() {
		return(firmaID);
	};
	public void setFirmaID(long _firmaID_) {
		this.firmaID = _firmaID_;
	};

	public boolean isObligatori() {
		return(obligatori);
	};
	public void setObligatori(boolean _obligatori_) {
		this.obligatori = _obligatori_;
	};



  // ======================================

  public static RevisorDeFirmaBean toBean(RevisorDeFirma __bean) {
    if (__bean == null) { return null;}
    RevisorDeFirmaBean __tmp = new RevisorDeFirmaBean();
    __tmp.setRevisorDeFirmaID(__bean.getRevisorDeFirmaID());
    __tmp.setUsuariEntitatRevisorID(__bean.getUsuariEntitatRevisorID());
    __tmp.setFirmaID(__bean.getFirmaID());
    __tmp.setObligatori(__bean.isObligatori());
		return __tmp;
	}



}
