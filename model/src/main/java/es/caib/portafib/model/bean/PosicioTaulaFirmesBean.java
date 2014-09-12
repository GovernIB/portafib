
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PosicioTaulaFirmes;


public class PosicioTaulaFirmesBean implements PosicioTaulaFirmes {



private static final long serialVersionUID = -1530080859L;

	int posicioTaulaFirmesID;// PK
	java.lang.String nom;
	java.lang.String descripcio;
	boolean suportada;


  /** Constructor Buit */
  public PosicioTaulaFirmesBean() {
  }

  /** Constructor amb tots els camps  */
  public PosicioTaulaFirmesBean(int posicioTaulaFirmesID , java.lang.String nom , java.lang.String descripcio , boolean suportada) {
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.suportada=suportada;
}
  /** Constructor dels valors Not Null */
  public PosicioTaulaFirmesBean(int posicioTaulaFirmesID , java.lang.String nom , boolean suportada) {
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.nom=nom;
    this.suportada=suportada;
}
  public PosicioTaulaFirmesBean(PosicioTaulaFirmes __bean) {
    this.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setSuportada(__bean.isSuportada());
	}

	public int getPosicioTaulaFirmesID() {
		return(posicioTaulaFirmesID);
	};
	public void setPosicioTaulaFirmesID(int _posicioTaulaFirmesID_) {
		this.posicioTaulaFirmesID = _posicioTaulaFirmesID_;
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

	public boolean isSuportada() {
		return(suportada);
	};
	public void setSuportada(boolean _suportada_) {
		this.suportada = _suportada_;
	};



  // ======================================

  public static PosicioTaulaFirmesBean toBean(PosicioTaulaFirmes __bean) {
    if (__bean == null) { return null;}
    PosicioTaulaFirmesBean __tmp = new PosicioTaulaFirmesBean();
    __tmp.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setSuportada(__bean.isSuportada());
		return __tmp;
	}



}
