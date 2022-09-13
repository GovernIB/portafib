
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.CodiBarres;


public class CodiBarresBean implements CodiBarres {



	java.lang.String codiBarresID;// PK
	java.lang.String nom;
	java.lang.String descripcio;


  /** Constructor Buit */
  public CodiBarresBean() {
  }

  /** Constructor amb tots els camps  */
  public CodiBarresBean(java.lang.String codiBarresID , java.lang.String nom , java.lang.String descripcio) {
    this.codiBarresID=codiBarresID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public CodiBarresBean(java.lang.String codiBarresID , java.lang.String nom) {
    this.codiBarresID=codiBarresID;
    this.nom=nom;
}
  public CodiBarresBean(CodiBarres __bean) {
    this.setCodiBarresID(__bean.getCodiBarresID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public java.lang.String getCodiBarresID() {
		return(codiBarresID);
	};
	public void setCodiBarresID(java.lang.String _codiBarresID_) {
		this.codiBarresID = _codiBarresID_;
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

  public static CodiBarresBean toBean(CodiBarres __bean) {
    if (__bean == null) { return null;}
    CodiBarresBean __tmp = new CodiBarresBean();
    __tmp.setCodiBarresID(__bean.getCodiBarresID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
