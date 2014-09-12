
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.GrupEntitat;


public class GrupEntitatBean implements GrupEntitat {



private static final long serialVersionUID = 1747201171L;

	long grupEntitatID;// PK
	java.lang.String nom;
	java.lang.String descripcio;
	java.lang.String entitatID;


  /** Constructor Buit */
  public GrupEntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public GrupEntitatBean(long grupEntitatID , java.lang.String nom , java.lang.String descripcio , java.lang.String entitatID) {
    this.grupEntitatID=grupEntitatID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public GrupEntitatBean(java.lang.String nom , java.lang.String descripcio , java.lang.String entitatID) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
}
  public GrupEntitatBean(GrupEntitat __bean) {
    this.setGrupEntitatID(__bean.getGrupEntitatID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getGrupEntitatID() {
		return(grupEntitatID);
	};
	public void setGrupEntitatID(long _grupEntitatID_) {
		this.grupEntitatID = _grupEntitatID_;
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

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};



  // ======================================

  public static GrupEntitatBean toBean(GrupEntitat __bean) {
    if (__bean == null) { return null;}
    GrupEntitatBean __tmp = new GrupEntitatBean();
    __tmp.setGrupEntitatID(__bean.getGrupEntitatID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}



}
