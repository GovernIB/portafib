
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.Prioritat;


public class PrioritatBean implements Prioritat {



private static final long serialVersionUID = 971176984L;

	int prioritatID;// PK
	java.lang.String nom;


  /** Constructor Buit */
  public PrioritatBean() {
  }

  /** Constructor amb tots els camps  */
  public PrioritatBean(int prioritatID , java.lang.String nom) {
    this.prioritatID=prioritatID;
    this.nom=nom;
}
  public PrioritatBean(Prioritat __bean) {
    this.setPrioritatID(__bean.getPrioritatID());
    this.setNom(__bean.getNom());
	}

	public int getPrioritatID() {
		return(prioritatID);
	};
	public void setPrioritatID(int _prioritatID_) {
		this.prioritatID = _prioritatID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};



  // ======================================

  public static PrioritatBean toBean(Prioritat __bean) {
    if (__bean == null) { return null;}
    PrioritatBean __tmp = new PrioritatBean();
    __tmp.setPrioritatID(__bean.getPrioritatID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}



}
