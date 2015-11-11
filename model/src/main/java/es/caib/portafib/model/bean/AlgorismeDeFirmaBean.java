
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.AlgorismeDeFirma;


public class AlgorismeDeFirmaBean implements AlgorismeDeFirma {



private static final long serialVersionUID = 1636748497L;

	int algorismeDeFirmaID;// PK
	java.lang.String nom;
	java.lang.String descripcio;
	boolean suportat;


  /** Constructor Buit */
  public AlgorismeDeFirmaBean() {
  }

  /** Constructor amb tots els camps  */
  public AlgorismeDeFirmaBean(int algorismeDeFirmaID , java.lang.String nom , java.lang.String descripcio , boolean suportat) {
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.suportat=suportat;
}
  /** Constructor dels valors Not Null */
  public AlgorismeDeFirmaBean(int algorismeDeFirmaID , java.lang.String nom) {
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.nom=nom;
}
  public AlgorismeDeFirmaBean(AlgorismeDeFirma __bean) {
    this.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setSuportat(__bean.isSuportat());
	}

	public int getAlgorismeDeFirmaID() {
		return(algorismeDeFirmaID);
	};
	public void setAlgorismeDeFirmaID(int _algorismeDeFirmaID_) {
		this.algorismeDeFirmaID = _algorismeDeFirmaID_;
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

	public boolean isSuportat() {
		return(suportat);
	};
	public void setSuportat(boolean _suportat_) {
		this.suportat = _suportat_;
	};



  // ======================================

  public static AlgorismeDeFirmaBean toBean(AlgorismeDeFirma __bean) {
    if (__bean == null) { return null;}
    AlgorismeDeFirmaBean __tmp = new AlgorismeDeFirmaBean();
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setSuportat(__bean.isSuportat());
		return __tmp;
	}



}
