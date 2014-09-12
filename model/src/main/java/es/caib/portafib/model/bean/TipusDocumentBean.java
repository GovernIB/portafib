
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.TipusDocument;


public class TipusDocumentBean implements TipusDocument {



private static final long serialVersionUID = 1572605540L;

	long tipusDocumentID;// PK
	long nomID;
	java.lang.String descripcio;
	java.lang.String usuariAplicacioID;


  /** Constructor Buit */
  public TipusDocumentBean() {
  }

  /** Constructor amb tots els camps  */
  public TipusDocumentBean(long tipusDocumentID , long nomID , java.lang.String descripcio , java.lang.String usuariAplicacioID) {
    this.tipusDocumentID=tipusDocumentID;
    this.nomID=nomID;
    this.descripcio=descripcio;
    this.usuariAplicacioID=usuariAplicacioID;
}
  /** Constructor dels valors Not Null */
  public TipusDocumentBean(long tipusDocumentID , long nomID) {
    this.tipusDocumentID=tipusDocumentID;
    this.nomID=nomID;
}
  public TipusDocumentBean(TipusDocument __bean) {
    this.setTipusDocumentID(__bean.getTipusDocumentID());
    this.setNomID(__bean.getNomID());
    this.setDescripcio(__bean.getDescripcio());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
	}

	public long getTipusDocumentID() {
		return(tipusDocumentID);
	};
	public void setTipusDocumentID(long _tipusDocumentID_) {
		this.tipusDocumentID = _tipusDocumentID_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};



  // ======================================

  public static TipusDocumentBean toBean(TipusDocument __bean) {
    if (__bean == null) { return null;}
    TipusDocumentBean __tmp = new TipusDocumentBean();
    __tmp.setTipusDocumentID(__bean.getTipusDocumentID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
		return __tmp;
	}



}
