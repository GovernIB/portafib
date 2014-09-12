
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.TipusEstatDeFirmaInicial;


public class TipusEstatDeFirmaInicialBean implements TipusEstatDeFirmaInicial {



private static final long serialVersionUID = 1936479875L;

	long tipusEstatDeFirmaInicialID;// PK
	java.lang.String nom;
	java.lang.String descripcio;


  /** Constructor Buit */
  public TipusEstatDeFirmaInicialBean() {
  }

  /** Constructor amb tots els camps  */
  public TipusEstatDeFirmaInicialBean(long tipusEstatDeFirmaInicialID , java.lang.String nom , java.lang.String descripcio) {
    this.tipusEstatDeFirmaInicialID=tipusEstatDeFirmaInicialID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public TipusEstatDeFirmaInicialBean(long tipusEstatDeFirmaInicialID , java.lang.String nom) {
    this.tipusEstatDeFirmaInicialID=tipusEstatDeFirmaInicialID;
    this.nom=nom;
}
  public TipusEstatDeFirmaInicialBean(TipusEstatDeFirmaInicial __bean) {
    this.setTipusEstatDeFirmaInicialID(__bean.getTipusEstatDeFirmaInicialID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getTipusEstatDeFirmaInicialID() {
		return(tipusEstatDeFirmaInicialID);
	};
	public void setTipusEstatDeFirmaInicialID(long _tipusEstatDeFirmaInicialID_) {
		this.tipusEstatDeFirmaInicialID = _tipusEstatDeFirmaInicialID_;
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

  public static TipusEstatDeFirmaInicialBean toBean(TipusEstatDeFirmaInicial __bean) {
    if (__bean == null) { return null;}
    TipusEstatDeFirmaInicialBean __tmp = new TipusEstatDeFirmaInicialBean();
    __tmp.setTipusEstatDeFirmaInicialID(__bean.getTipusEstatDeFirmaInicialID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
