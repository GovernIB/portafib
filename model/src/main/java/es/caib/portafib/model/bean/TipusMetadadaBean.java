
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.TipusMetadada;


public class TipusMetadadaBean implements TipusMetadada {



private static final long serialVersionUID = -386661240L;

	int tipusMetadadaID;// PK
	java.lang.String nom;
	java.lang.String descripcio;


  /** Constructor Buit */
  public TipusMetadadaBean() {
  }

  /** Constructor amb tots els camps  */
  public TipusMetadadaBean(int tipusMetadadaID , java.lang.String nom , java.lang.String descripcio) {
    this.tipusMetadadaID=tipusMetadadaID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public TipusMetadadaBean(int tipusMetadadaID , java.lang.String nom) {
    this.tipusMetadadaID=tipusMetadadaID;
    this.nom=nom;
}
  public TipusMetadadaBean(TipusMetadada __bean) {
    this.setTipusMetadadaID(__bean.getTipusMetadadaID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public int getTipusMetadadaID() {
		return(tipusMetadadaID);
	};
	public void setTipusMetadadaID(int _tipusMetadadaID_) {
		this.tipusMetadadaID = _tipusMetadadaID_;
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

  public static TipusMetadadaBean toBean(TipusMetadada __bean) {
    if (__bean == null) { return null;}
    TipusMetadadaBean __tmp = new TipusMetadadaBean();
    __tmp.setTipusMetadadaID(__bean.getTipusMetadadaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
