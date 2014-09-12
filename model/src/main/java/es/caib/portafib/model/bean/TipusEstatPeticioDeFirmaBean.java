
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.TipusEstatPeticioDeFirma;


public class TipusEstatPeticioDeFirmaBean implements TipusEstatPeticioDeFirma {



private static final long serialVersionUID = 1123782995L;

	int tipusEstatPeticioDeFirmaID;// PK
	java.lang.String nom;
	java.lang.String descripcio;


  /** Constructor Buit */
  public TipusEstatPeticioDeFirmaBean() {
  }

  /** Constructor amb tots els camps  */
  public TipusEstatPeticioDeFirmaBean(int tipusEstatPeticioDeFirmaID , java.lang.String nom , java.lang.String descripcio) {
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public TipusEstatPeticioDeFirmaBean(int tipusEstatPeticioDeFirmaID , java.lang.String nom) {
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.nom=nom;
}
  public TipusEstatPeticioDeFirmaBean(TipusEstatPeticioDeFirma __bean) {
    this.setTipusEstatPeticioDeFirmaID(__bean.getTipusEstatPeticioDeFirmaID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public int getTipusEstatPeticioDeFirmaID() {
		return(tipusEstatPeticioDeFirmaID);
	};
	public void setTipusEstatPeticioDeFirmaID(int _tipusEstatPeticioDeFirmaID_) {
		this.tipusEstatPeticioDeFirmaID = _tipusEstatPeticioDeFirmaID_;
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

  public static TipusEstatPeticioDeFirmaBean toBean(TipusEstatPeticioDeFirma __bean) {
    if (__bean == null) { return null;}
    TipusEstatPeticioDeFirmaBean __tmp = new TipusEstatPeticioDeFirmaBean();
    __tmp.setTipusEstatPeticioDeFirmaID(__bean.getTipusEstatPeticioDeFirmaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
