
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.RevisorDeDestinatari;


public class RevisorDeDestinatariBean implements RevisorDeDestinatari {



	long revisorDeDestinatariID;// PK
	java.lang.String destinatariID;
	java.lang.String revisorID;


  /** Constructor Buit */
  public RevisorDeDestinatariBean() {
  }

  /** Constructor amb tots els camps  */
  public RevisorDeDestinatariBean(long revisorDeDestinatariID , java.lang.String destinatariID , java.lang.String revisorID) {
    this.revisorDeDestinatariID=revisorDeDestinatariID;
    this.destinatariID=destinatariID;
    this.revisorID=revisorID;
}
  /** Constructor sense valors autoincrementals */
  public RevisorDeDestinatariBean(java.lang.String destinatariID , java.lang.String revisorID) {
    this.destinatariID=destinatariID;
    this.revisorID=revisorID;
}
  public RevisorDeDestinatariBean(RevisorDeDestinatari __bean) {
    this.setRevisorDeDestinatariID(__bean.getRevisorDeDestinatariID());
    this.setDestinatariID(__bean.getDestinatariID());
    this.setRevisorID(__bean.getRevisorID());
	}

	public long getRevisorDeDestinatariID() {
		return(revisorDeDestinatariID);
	};
	public void setRevisorDeDestinatariID(long _revisorDeDestinatariID_) {
		this.revisorDeDestinatariID = _revisorDeDestinatariID_;
	};

	public java.lang.String getDestinatariID() {
		return(destinatariID);
	};
	public void setDestinatariID(java.lang.String _destinatariID_) {
		this.destinatariID = _destinatariID_;
	};

	public java.lang.String getRevisorID() {
		return(revisorID);
	};
	public void setRevisorID(java.lang.String _revisorID_) {
		this.revisorID = _revisorID_;
	};



  // ======================================

  public static RevisorDeDestinatariBean toBean(RevisorDeDestinatari __bean) {
    if (__bean == null) { return null;}
    RevisorDeDestinatariBean __tmp = new RevisorDeDestinatariBean();
    __tmp.setRevisorDeDestinatariID(__bean.getRevisorDeDestinatariID());
    __tmp.setDestinatariID(__bean.getDestinatariID());
    __tmp.setRevisorID(__bean.getRevisorID());
		return __tmp;
	}



}
