
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.TipusFirma;


public class TipusFirmaBean implements TipusFirma {



private static final long serialVersionUID = -1949401126L;

	int tipusFirmaID;// PK
	java.lang.String nom;
	boolean suportada;
	java.lang.String descripcio;


  /** Constructor Buit */
  public TipusFirmaBean() {
  }

  /** Constructor amb tots els camps  */
  public TipusFirmaBean(int tipusFirmaID , java.lang.String nom , boolean suportada , java.lang.String descripcio) {
    this.tipusFirmaID=tipusFirmaID;
    this.nom=nom;
    this.suportada=suportada;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public TipusFirmaBean(int tipusFirmaID , java.lang.String nom , boolean suportada) {
    this.tipusFirmaID=tipusFirmaID;
    this.nom=nom;
    this.suportada=suportada;
}
  public TipusFirmaBean(TipusFirma __bean) {
    this.setTipusFirmaID(__bean.getTipusFirmaID());
    this.setNom(__bean.getNom());
    this.setSuportada(__bean.isSuportada());
    this.setDescripcio(__bean.getDescripcio());
	}

	public int getTipusFirmaID() {
		return(tipusFirmaID);
	};
	public void setTipusFirmaID(int _tipusFirmaID_) {
		this.tipusFirmaID = _tipusFirmaID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public boolean isSuportada() {
		return(suportada);
	};
	public void setSuportada(boolean _suportada_) {
		this.suportada = _suportada_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};



  // ======================================

  public static TipusFirmaBean toBean(TipusFirma __bean) {
    if (__bean == null) { return null;}
    TipusFirmaBean __tmp = new TipusFirmaBean();
    __tmp.setTipusFirmaID(__bean.getTipusFirmaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setSuportada(__bean.isSuportada());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
