
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.AnnexFirmat;


public class AnnexFirmatBean implements AnnexFirmat {



	long annexfirmatID;// PK
	long fitxerID;
	long annexID;
	long firmaID;


  /** Constructor Buit */
  public AnnexFirmatBean() {
  }

  /** Constructor amb tots els camps  */
  public AnnexFirmatBean(long annexfirmatID , long fitxerID , long annexID , long firmaID) {
    this.annexfirmatID=annexfirmatID;
    this.fitxerID=fitxerID;
    this.annexID=annexID;
    this.firmaID=firmaID;
}
  /** Constructor sense valors autoincrementals */
  public AnnexFirmatBean(long fitxerID , long annexID , long firmaID) {
    this.fitxerID=fitxerID;
    this.annexID=annexID;
    this.firmaID=firmaID;
}
  public AnnexFirmatBean(AnnexFirmat __bean) {
    this.setAnnexfirmatID(__bean.getAnnexfirmatID());
    this.setFitxerID(__bean.getFitxerID());
    this.setAnnexID(__bean.getAnnexID());
    this.setFirmaID(__bean.getFirmaID());
    // Fitxer
    this.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
	}

	public long getAnnexfirmatID() {
		return(annexfirmatID);
	};
	public void setAnnexfirmatID(long _annexfirmatID_) {
		this.annexfirmatID = _annexfirmatID_;
	};

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public long getAnnexID() {
		return(annexID);
	};
	public void setAnnexID(long _annexID_) {
		this.annexID = _annexID_;
	};

	public long getFirmaID() {
		return(firmaID);
	};
	public void setFirmaID(long _firmaID_) {
		this.firmaID = _firmaID_;
	};



  // ======================================

  public static AnnexFirmatBean toBean(AnnexFirmat __bean) {
    if (__bean == null) { return null;}
    AnnexFirmatBean __tmp = new AnnexFirmatBean();
    __tmp.setAnnexfirmatID(__bean.getAnnexfirmatID());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setAnnexID(__bean.getAnnexID());
    __tmp.setFirmaID(__bean.getFirmaID());
    // Fitxer
    __tmp.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
		return __tmp;
	}

  protected FitxerBean fitxer;
  public FitxerBean getFitxer() {
    return fitxer;
  }
  public void setFitxer(FitxerBean __field) {
    this. fitxer = __field;
  }


}
