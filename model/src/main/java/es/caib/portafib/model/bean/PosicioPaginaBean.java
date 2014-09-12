
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PosicioPagina;


public class PosicioPaginaBean implements PosicioPagina {



private static final long serialVersionUID = -1130159254L;

	long posicioPaginaID;// PK
	java.lang.String nom;


  /** Constructor Buit */
  public PosicioPaginaBean() {
  }

  /** Constructor amb tots els camps  */
  public PosicioPaginaBean(long posicioPaginaID , java.lang.String nom) {
    this.posicioPaginaID=posicioPaginaID;
    this.nom=nom;
}
  public PosicioPaginaBean(PosicioPagina __bean) {
    this.setPosicioPaginaID(__bean.getPosicioPaginaID());
    this.setNom(__bean.getNom());
	}

	public long getPosicioPaginaID() {
		return(posicioPaginaID);
	};
	public void setPosicioPaginaID(long _posicioPaginaID_) {
		this.posicioPaginaID = _posicioPaginaID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};



  // ======================================

  public static PosicioPaginaBean toBean(PosicioPagina __bean) {
    if (__bean == null) { return null;}
    PosicioPaginaBean __tmp = new PosicioPaginaBean();
    __tmp.setPosicioPaginaID(__bean.getPosicioPaginaID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}



}
