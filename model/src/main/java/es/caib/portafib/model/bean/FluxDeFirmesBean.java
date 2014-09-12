
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.FluxDeFirmes;


public class FluxDeFirmesBean implements FluxDeFirmes {



private static final long serialVersionUID = 2129091910L;

	long fluxDeFirmesID;// PK
	java.lang.String nom;


  /** Constructor Buit */
  public FluxDeFirmesBean() {
  }

  /** Constructor amb tots els camps  */
  public FluxDeFirmesBean(long fluxDeFirmesID , java.lang.String nom) {
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.nom=nom;
}
  /** Constructor sense valors autoincrementals */
  public FluxDeFirmesBean(java.lang.String nom) {
    this.nom=nom;
}
  public FluxDeFirmesBean(FluxDeFirmes __bean) {
    this.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    this.setNom(__bean.getNom());
	}

	public long getFluxDeFirmesID() {
		return(fluxDeFirmesID);
	};
	public void setFluxDeFirmesID(long _fluxDeFirmesID_) {
		this.fluxDeFirmesID = _fluxDeFirmesID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};



  // ======================================

  public static FluxDeFirmesBean toBean(FluxDeFirmes __bean) {
    if (__bean == null) { return null;}
    FluxDeFirmesBean __tmp = new FluxDeFirmesBean();
    __tmp.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}



}
