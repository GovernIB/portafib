
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.TipusEstatDeFirmaFinal;


public class TipusEstatDeFirmaFinalBean implements TipusEstatDeFirmaFinal {



private static final long serialVersionUID = -91928060L;

	long tipusEstatDeFirmaFinalID;// PK
	java.lang.String nom;
	java.lang.String descripcio;


  /** Constructor Buit */
  public TipusEstatDeFirmaFinalBean() {
  }

  /** Constructor amb tots els camps  */
  public TipusEstatDeFirmaFinalBean(long tipusEstatDeFirmaFinalID , java.lang.String nom , java.lang.String descripcio) {
    this.tipusEstatDeFirmaFinalID=tipusEstatDeFirmaFinalID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public TipusEstatDeFirmaFinalBean(long tipusEstatDeFirmaFinalID , java.lang.String nom) {
    this.tipusEstatDeFirmaFinalID=tipusEstatDeFirmaFinalID;
    this.nom=nom;
}
  public TipusEstatDeFirmaFinalBean(TipusEstatDeFirmaFinal __bean) {
    this.setTipusEstatDeFirmaFinalID(__bean.getTipusEstatDeFirmaFinalID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getTipusEstatDeFirmaFinalID() {
		return(tipusEstatDeFirmaFinalID);
	};
	public void setTipusEstatDeFirmaFinalID(long _tipusEstatDeFirmaFinalID_) {
		this.tipusEstatDeFirmaFinalID = _tipusEstatDeFirmaFinalID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};



  // ======================================

  public static TipusEstatDeFirmaFinalBean toBean(TipusEstatDeFirmaFinal __bean) {
    if (__bean == null) { return null;}
    TipusEstatDeFirmaFinalBean __tmp = new TipusEstatDeFirmaFinalBean();
    __tmp.setTipusEstatDeFirmaFinalID(__bean.getTipusEstatDeFirmaFinalID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
