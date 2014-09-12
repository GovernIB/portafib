
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.TipusNotificacio;


public class TipusNotificacioBean implements TipusNotificacio {



private static final long serialVersionUID = 2142378313L;

	long tipusNotificacioID;// PK
	java.lang.String nom;
	java.lang.String descripcio;
	java.lang.Boolean esAvis;


  /** Constructor Buit */
  public TipusNotificacioBean() {
  }

  /** Constructor amb tots els camps  */
  public TipusNotificacioBean(long tipusNotificacioID , java.lang.String nom , java.lang.String descripcio , java.lang.Boolean esAvis) {
    this.tipusNotificacioID=tipusNotificacioID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.esAvis=esAvis;
}
  /** Constructor dels valors Not Null */
  public TipusNotificacioBean(long tipusNotificacioID , java.lang.String nom) {
    this.tipusNotificacioID=tipusNotificacioID;
    this.nom=nom;
}
  public TipusNotificacioBean(TipusNotificacio __bean) {
    this.setTipusNotificacioID(__bean.getTipusNotificacioID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setEsAvis(__bean.getEsAvis());
	}

	public long getTipusNotificacioID() {
		return(tipusNotificacioID);
	};
	public void setTipusNotificacioID(long _tipusNotificacioID_) {
		this.tipusNotificacioID = _tipusNotificacioID_;
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

	public java.lang.Boolean getEsAvis() {
		return(esAvis);
	};
	public void setEsAvis(java.lang.Boolean _esAvis_) {
		this.esAvis = _esAvis_;
	};



  // ======================================

  public static TipusNotificacioBean toBean(TipusNotificacio __bean) {
    if (__bean == null) { return null;}
    TipusNotificacioBean __tmp = new TipusNotificacioBean();
    __tmp.setTipusNotificacioID(__bean.getTipusNotificacioID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEsAvis(__bean.getEsAvis());
		return __tmp;
	}



}
