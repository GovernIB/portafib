
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.TipusDocumentColaboracioDelegacio;


public class TipusDocumentColaboracioDelegacioBean implements TipusDocumentColaboracioDelegacio {



	long id;// PK
	long colaboracioDelegacioID;
	long tipusDocumentID;


  /** Constructor Buit */
  public TipusDocumentColaboracioDelegacioBean() {
  }

  /** Constructor amb tots els camps  */
  public TipusDocumentColaboracioDelegacioBean(long id , long colaboracioDelegacioID , long tipusDocumentID) {
    this.id=id;
    this.colaboracioDelegacioID=colaboracioDelegacioID;
    this.tipusDocumentID=tipusDocumentID;
}
  /** Constructor sense valors autoincrementals */
  public TipusDocumentColaboracioDelegacioBean(long colaboracioDelegacioID , long tipusDocumentID) {
    this.colaboracioDelegacioID=colaboracioDelegacioID;
    this.tipusDocumentID=tipusDocumentID;
}
  public TipusDocumentColaboracioDelegacioBean(TipusDocumentColaboracioDelegacio __bean) {
    this.setId(__bean.getId());
    this.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    this.setTipusDocumentID(__bean.getTipusDocumentID());
	}

	public long getId() {
		return(id);
	};
	public void setId(long _id_) {
		this.id = _id_;
	};

	public long getColaboracioDelegacioID() {
		return(colaboracioDelegacioID);
	};
	public void setColaboracioDelegacioID(long _colaboracioDelegacioID_) {
		this.colaboracioDelegacioID = _colaboracioDelegacioID_;
	};

	public long getTipusDocumentID() {
		return(tipusDocumentID);
	};
	public void setTipusDocumentID(long _tipusDocumentID_) {
		this.tipusDocumentID = _tipusDocumentID_;
	};



  // ======================================

  public static TipusDocumentColaboracioDelegacioBean toBean(TipusDocumentColaboracioDelegacio __bean) {
    if (__bean == null) { return null;}
    TipusDocumentColaboracioDelegacioBean __tmp = new TipusDocumentColaboracioDelegacioBean();
    __tmp.setId(__bean.getId());
    __tmp.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    __tmp.setTipusDocumentID(__bean.getTipusDocumentID());
		return __tmp;
	}



}
