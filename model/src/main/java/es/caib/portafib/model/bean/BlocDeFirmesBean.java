
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.BlocDeFirmes;


public class BlocDeFirmesBean implements BlocDeFirmes {



private static final long serialVersionUID = 481141627L;

	long blocDeFirmesID;// PK
	int ordre;
	java.sql.Timestamp dataFinalitzacio;
	long fluxDeFirmesID;
	int minimDeFirmes;


  /** Constructor Buit */
  public BlocDeFirmesBean() {
  }

  /** Constructor amb tots els camps  */
  public BlocDeFirmesBean(long blocDeFirmesID , int ordre , java.sql.Timestamp dataFinalitzacio , long fluxDeFirmesID , int minimDeFirmes) {
    this.blocDeFirmesID=blocDeFirmesID;
    this.ordre=ordre;
    this.dataFinalitzacio=dataFinalitzacio;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.minimDeFirmes=minimDeFirmes;
}
  /** Constructor sense valors autoincrementals */
  public BlocDeFirmesBean(int ordre , java.sql.Timestamp dataFinalitzacio , long fluxDeFirmesID , int minimDeFirmes) {
    this.ordre=ordre;
    this.dataFinalitzacio=dataFinalitzacio;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.minimDeFirmes=minimDeFirmes;
}
  public BlocDeFirmesBean(BlocDeFirmes __bean) {
    this.setBlocDeFirmesID(__bean.getBlocDeFirmesID());
    this.setOrdre(__bean.getOrdre());
    this.setDataFinalitzacio(__bean.getDataFinalitzacio());
    this.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    this.setMinimDeFirmes(__bean.getMinimDeFirmes());
	}

	public long getBlocDeFirmesID() {
		return(blocDeFirmesID);
	};
	public void setBlocDeFirmesID(long _blocDeFirmesID_) {
		this.blocDeFirmesID = _blocDeFirmesID_;
	};

	public int getOrdre() {
		return(ordre);
	};
	public void setOrdre(int _ordre_) {
		this.ordre = _ordre_;
	};

	public java.sql.Timestamp getDataFinalitzacio() {
		return(dataFinalitzacio);
	};
	public void setDataFinalitzacio(java.sql.Timestamp _dataFinalitzacio_) {
		this.dataFinalitzacio = _dataFinalitzacio_;
	};

	public long getFluxDeFirmesID() {
		return(fluxDeFirmesID);
	};
	public void setFluxDeFirmesID(long _fluxDeFirmesID_) {
		this.fluxDeFirmesID = _fluxDeFirmesID_;
	};

	public int getMinimDeFirmes() {
		return(minimDeFirmes);
	};
	public void setMinimDeFirmes(int _minimDeFirmes_) {
		this.minimDeFirmes = _minimDeFirmes_;
	};



  // ======================================

  public static BlocDeFirmesBean toBean(BlocDeFirmes __bean) {
    if (__bean == null) { return null;}
    BlocDeFirmesBean __tmp = new BlocDeFirmesBean();
    __tmp.setBlocDeFirmesID(__bean.getBlocDeFirmesID());
    __tmp.setOrdre(__bean.getOrdre());
    __tmp.setDataFinalitzacio(__bean.getDataFinalitzacio());
    __tmp.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    __tmp.setMinimDeFirmes(__bean.getMinimDeFirmes());
		return __tmp;
	}



}
